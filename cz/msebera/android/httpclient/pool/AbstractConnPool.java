package cz.msebera.android.httpclient.pool;

import cz.msebera.android.httpclient.annotation.ThreadSafe;
import cz.msebera.android.httpclient.concurrent.FutureCallback;
import cz.msebera.android.httpclient.util.Args;
import cz.msebera.android.httpclient.util.Asserts;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@ThreadSafe
public abstract class AbstractConnPool<T, C, E extends PoolEntry<T, C>> implements ConnPool<T, E>, ConnPoolControl<T> {
    private final LinkedList<E> available = new LinkedList();
    private final ConnFactory<T, C> connFactory;
    private volatile int defaultMaxPerRoute;
    private volatile boolean isShutDown;
    private final Set<E> leased = new HashSet();
    private final Lock lock = new ReentrantLock();
    private final Map<T, Integer> maxPerRoute = new HashMap();
    private volatile int maxTotal;
    private final LinkedList<PoolEntryFuture<E>> pending = new LinkedList();
    private final Map<T, RouteSpecificPool<T, C, E>> routeToPool = new HashMap();

    protected abstract E createEntry(T t, C c);

    public AbstractConnPool(ConnFactory<T, C> connFactory, int defaultMaxPerRoute, int maxTotal) {
        this.connFactory = (ConnFactory) Args.notNull(connFactory, "Connection factory");
        this.defaultMaxPerRoute = Args.notNegative(defaultMaxPerRoute, "Max per route value");
        this.maxTotal = Args.notNegative(maxTotal, "Max total value");
    }

    protected void onLease(E e) {
    }

    protected void onRelease(E e) {
    }

    public boolean isShutdown() {
        return this.isShutDown;
    }

    public void shutdown() throws IOException {
        if (!this.isShutDown) {
            this.isShutDown = true;
            this.lock.lock();
            try {
                Iterator it = this.available.iterator();
                while (it.hasNext()) {
                    ((PoolEntry) it.next()).close();
                }
                for (PoolEntry entry : this.leased) {
                    entry.close();
                }
                for (RouteSpecificPool<T, C, E> pool : this.routeToPool.values()) {
                    pool.shutdown();
                }
                this.routeToPool.clear();
                this.leased.clear();
                this.available.clear();
            } finally {
                this.lock.unlock();
            }
        }
    }

    private RouteSpecificPool<T, C, E> getPool(final T route) {
        RouteSpecificPool<T, C, E> pool = (RouteSpecificPool) this.routeToPool.get(route);
        if (pool != null) {
            return pool;
        }
        pool = new RouteSpecificPool<T, C, E>(route) {
            protected E createEntry(C conn) {
                return AbstractConnPool.this.createEntry(route, conn);
            }
        };
        this.routeToPool.put(route, pool);
        return pool;
    }

    public Future<E> lease(T route, Object state, FutureCallback<E> callback) {
        Args.notNull(route, "Route");
        Asserts.check(!this.isShutDown, "Connection pool shut down");
        final T t = route;
        final Object obj = state;
        return new PoolEntryFuture<E>(this.lock, callback) {
            public E getPoolEntry(long timeout, TimeUnit tunit) throws InterruptedException, TimeoutException, IOException {
                E entry = AbstractConnPool.this.getPoolEntryBlocking(t, obj, timeout, tunit, this);
                AbstractConnPool.this.onLease(entry);
                return entry;
            }
        };
    }

    public Future<E> lease(T route, Object state) {
        return lease(route, state, null);
    }

    private E getPoolEntryBlocking(T route, Object state, long timeout, TimeUnit tunit, PoolEntryFuture<E> future) throws IOException, InterruptedException, TimeoutException {
        Date deadline = null;
        if (timeout > 0) {
            deadline = new Date(System.currentTimeMillis() + tunit.toMillis(timeout));
        }
        this.lock.lock();
        RouteSpecificPool<T, C, E> pool = getPool(route);
        E entry = null;
        while (entry == null) {
            Asserts.check(!this.isShutDown, "Connection pool shut down");
            while (true) {
                entry = pool.getFree(state);
                if (entry != null) {
                    if (!entry.isClosed() && !entry.isExpired(System.currentTimeMillis())) {
                        break;
                    }
                    entry.close();
                    this.available.remove(entry);
                    pool.free(entry, false);
                } else {
                    break;
                }
            }
            if (entry == null) {
                int maxPerRoute = getMax(route);
                int excess = Math.max(0, (pool.getAllocatedCount() + 1) - maxPerRoute);
                if (excess > 0) {
                    int i = 0;
                    while (i < excess) {
                        E lastUsed = pool.getLastUsed();
                        if (lastUsed == null) {
                            break;
                        }
                        try {
                            lastUsed.close();
                            this.available.remove(lastUsed);
                            pool.remove(lastUsed);
                            i++;
                        } catch (Throwable th) {
                            this.lock.unlock();
                        }
                    }
                }
                if (pool.getAllocatedCount() < maxPerRoute) {
                    int freeCapacity = Math.max(this.maxTotal - this.leased.size(), 0);
                    if (freeCapacity > 0) {
                        if (this.available.size() > freeCapacity - 1 && !this.available.isEmpty()) {
                            PoolEntry lastUsed2 = (PoolEntry) this.available.removeLast();
                            lastUsed2.close();
                            getPool(lastUsed2.getRoute()).remove(lastUsed2);
                        }
                        entry = pool.add(this.connFactory.create(route));
                        this.leased.add(entry);
                        this.lock.unlock();
                        return entry;
                    }
                }
                pool.queue(future);
                this.pending.add(future);
                boolean success = future.await(deadline);
                pool.unqueue(future);
                this.pending.remove(future);
                if (!success && deadline != null && deadline.getTime() <= System.currentTimeMillis()) {
                    break;
                }
            } else {
                this.available.remove(entry);
                this.leased.add(entry);
                this.lock.unlock();
                return entry;
            }
        }
        throw new TimeoutException("Timeout waiting for connection");
    }

    public void release(E entry, boolean reusable) {
        this.lock.lock();
        try {
            if (this.leased.remove(entry)) {
                RouteSpecificPool<T, C, E> pool = getPool(entry.getRoute());
                pool.free(entry, reusable);
                if (!reusable || this.isShutDown) {
                    entry.close();
                } else {
                    this.available.addFirst(entry);
                    onRelease(entry);
                }
                PoolEntryFuture<E> future = pool.nextPending();
                if (future != null) {
                    this.pending.remove(future);
                } else {
                    future = (PoolEntryFuture) this.pending.poll();
                }
                if (future != null) {
                    future.wakeup();
                }
            }
            this.lock.unlock();
        } catch (Throwable th) {
            this.lock.unlock();
        }
    }

    private int getMax(T route) {
        Integer v = (Integer) this.maxPerRoute.get(route);
        if (v != null) {
            return v.intValue();
        }
        return this.defaultMaxPerRoute;
    }

    public void setMaxTotal(int max) {
        Args.notNegative(max, "Max value");
        this.lock.lock();
        try {
            this.maxTotal = max;
        } finally {
            this.lock.unlock();
        }
    }

    public int getMaxTotal() {
        this.lock.lock();
        try {
            int i = this.maxTotal;
            return i;
        } finally {
            this.lock.unlock();
        }
    }

    public void setDefaultMaxPerRoute(int max) {
        Args.notNegative(max, "Max per route value");
        this.lock.lock();
        try {
            this.defaultMaxPerRoute = max;
        } finally {
            this.lock.unlock();
        }
    }

    public int getDefaultMaxPerRoute() {
        this.lock.lock();
        try {
            int i = this.defaultMaxPerRoute;
            return i;
        } finally {
            this.lock.unlock();
        }
    }

    public void setMaxPerRoute(T route, int max) {
        Args.notNull(route, "Route");
        Args.notNegative(max, "Max per route value");
        this.lock.lock();
        try {
            this.maxPerRoute.put(route, Integer.valueOf(max));
        } finally {
            this.lock.unlock();
        }
    }

    public int getMaxPerRoute(T route) {
        Args.notNull(route, "Route");
        this.lock.lock();
        try {
            int max = getMax(route);
            return max;
        } finally {
            this.lock.unlock();
        }
    }

    public PoolStats getTotalStats() {
        this.lock.lock();
        try {
            PoolStats poolStats = new PoolStats(this.leased.size(), this.pending.size(), this.available.size(), this.maxTotal);
            return poolStats;
        } finally {
            this.lock.unlock();
        }
    }

    public PoolStats getStats(T route) {
        Args.notNull(route, "Route");
        this.lock.lock();
        try {
            RouteSpecificPool<T, C, E> pool = getPool(route);
            PoolStats poolStats = new PoolStats(pool.getLeasedCount(), pool.getPendingCount(), pool.getAvailableCount(), getMax(route));
            return poolStats;
        } finally {
            this.lock.unlock();
        }
    }

    protected void enumAvailable(PoolEntryCallback<T, C> callback) {
        this.lock.lock();
        try {
            Iterator<E> it = this.available.iterator();
            while (it.hasNext()) {
                PoolEntry entry = (PoolEntry) it.next();
                callback.process(entry);
                if (entry.isClosed()) {
                    getPool(entry.getRoute()).remove(entry);
                    it.remove();
                }
            }
            purgePoolMap();
        } finally {
            this.lock.unlock();
        }
    }

    protected void enumLeased(PoolEntryCallback<T, C> callback) {
        this.lock.lock();
        try {
            for (PoolEntry entry : this.leased) {
                callback.process(entry);
            }
        } finally {
            this.lock.unlock();
        }
    }

    private void purgePoolMap() {
        Iterator<Entry<T, RouteSpecificPool<T, C, E>>> it = this.routeToPool.entrySet().iterator();
        while (it.hasNext()) {
            RouteSpecificPool<T, C, E> pool = (RouteSpecificPool) ((Entry) it.next()).getValue();
            if (pool.getPendingCount() + pool.getAllocatedCount() == 0) {
                it.remove();
            }
        }
    }

    public void closeIdle(long idletime, TimeUnit tunit) {
        Args.notNull(tunit, "Time unit");
        long time = tunit.toMillis(idletime);
        if (time < 0) {
            time = 0;
        }
        final long deadline = System.currentTimeMillis() - time;
        enumAvailable(new PoolEntryCallback<T, C>() {
            public void process(PoolEntry<T, C> entry) {
                if (entry.getUpdated() <= deadline) {
                    entry.close();
                }
            }
        });
    }

    public void closeExpired() {
        final long now = System.currentTimeMillis();
        enumAvailable(new PoolEntryCallback<T, C>() {
            public void process(PoolEntry<T, C> entry) {
                if (entry.isExpired(now)) {
                    entry.close();
                }
            }
        });
    }

    public String toString() {
        StringBuilder buffer = new StringBuilder();
        buffer.append("[leased: ");
        buffer.append(this.leased);
        buffer.append("][available: ");
        buffer.append(this.available);
        buffer.append("][pending: ");
        buffer.append(this.pending);
        buffer.append("]");
        return buffer.toString();
    }
}
