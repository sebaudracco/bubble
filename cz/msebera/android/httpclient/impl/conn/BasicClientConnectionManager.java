package cz.msebera.android.httpclient.impl.conn;

import cz.msebera.android.httpclient.HttpClientConnection;
import cz.msebera.android.httpclient.annotation.GuardedBy;
import cz.msebera.android.httpclient.annotation.ThreadSafe;
import cz.msebera.android.httpclient.conn.ClientConnectionManager;
import cz.msebera.android.httpclient.conn.ClientConnectionOperator;
import cz.msebera.android.httpclient.conn.ClientConnectionRequest;
import cz.msebera.android.httpclient.conn.ManagedClientConnection;
import cz.msebera.android.httpclient.conn.routing.HttpRoute;
import cz.msebera.android.httpclient.conn.scheme.SchemeRegistry;
import cz.msebera.android.httpclient.extras.HttpClientAndroidLog;
import cz.msebera.android.httpclient.util.Args;
import cz.msebera.android.httpclient.util.Asserts;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

@ThreadSafe
@Deprecated
public class BasicClientConnectionManager implements ClientConnectionManager {
    private static final AtomicLong COUNTER = new AtomicLong();
    public static final String MISUSE_MESSAGE = "Invalid use of BasicClientConnManager: connection still allocated.\nMake sure to release the connection before allocating another one.";
    @GuardedBy("this")
    private ManagedClientConnectionImpl conn;
    private final ClientConnectionOperator connOperator;
    public HttpClientAndroidLog log;
    @GuardedBy("this")
    private HttpPoolEntry poolEntry;
    private final SchemeRegistry schemeRegistry;
    @GuardedBy("this")
    private volatile boolean shutdown;

    public BasicClientConnectionManager(SchemeRegistry schreg) {
        this.log = new HttpClientAndroidLog(getClass());
        Args.notNull(schreg, "Scheme registry");
        this.schemeRegistry = schreg;
        this.connOperator = createConnectionOperator(schreg);
    }

    public BasicClientConnectionManager() {
        this(SchemeRegistryFactory.createDefault());
    }

    protected void finalize() throws Throwable {
        try {
            shutdown();
        } finally {
            super.finalize();
        }
    }

    public SchemeRegistry getSchemeRegistry() {
        return this.schemeRegistry;
    }

    protected ClientConnectionOperator createConnectionOperator(SchemeRegistry schreg) {
        return new DefaultClientConnectionOperator(schreg);
    }

    public final ClientConnectionRequest requestConnection(HttpRoute route, Object state) {
        return new 1(this, route, state);
    }

    private void assertNotShutdown() {
        Asserts.check(!this.shutdown, "Connection manager has been shut down");
    }

    ManagedClientConnection getConnection(HttpRoute route, Object state) {
        ManagedClientConnection managedClientConnection;
        Args.notNull(route, "Route");
        synchronized (this) {
            assertNotShutdown();
            if (this.log.isDebugEnabled()) {
                this.log.debug("Get connection for route " + route);
            }
            Asserts.check(this.conn == null, MISUSE_MESSAGE);
            if (!(this.poolEntry == null || this.poolEntry.getPlannedRoute().equals(route))) {
                this.poolEntry.close();
                this.poolEntry = null;
            }
            if (this.poolEntry == null) {
                this.poolEntry = new HttpPoolEntry(this.log, Long.toString(COUNTER.getAndIncrement()), route, this.connOperator.createConnection(), 0, TimeUnit.MILLISECONDS);
            }
            if (this.poolEntry.isExpired(System.currentTimeMillis())) {
                this.poolEntry.close();
                this.poolEntry.getTracker().reset();
            }
            this.conn = new ManagedClientConnectionImpl(this, this.connOperator, this.poolEntry);
            managedClientConnection = this.conn;
        }
        return managedClientConnection;
    }

    private void shutdownConnection(HttpClientConnection conn) {
        try {
            conn.shutdown();
        } catch (IOException iox) {
            if (this.log.isDebugEnabled()) {
                this.log.debug("I/O exception shutting down connection", iox);
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void releaseConnection(cz.msebera.android.httpclient.conn.ManagedClientConnection r7, long r8, java.util.concurrent.TimeUnit r10) {
        /*
        r6 = this;
        r3 = r7 instanceof cz.msebera.android.httpclient.impl.conn.ManagedClientConnectionImpl;
        r4 = "Connection class mismatch, connection not obtained from this manager";
        cz.msebera.android.httpclient.util.Args.check(r3, r4);
        r0 = r7;
        r0 = (cz.msebera.android.httpclient.impl.conn.ManagedClientConnectionImpl) r0;
        monitor-enter(r0);
        r3 = r6.log;	 Catch:{ all -> 0x004d }
        r3 = r3.isDebugEnabled();	 Catch:{ all -> 0x004d }
        if (r3 == 0) goto L_0x002d;
    L_0x0014:
        r3 = r6.log;	 Catch:{ all -> 0x004d }
        r4 = new java.lang.StringBuilder;	 Catch:{ all -> 0x004d }
        r4.<init>();	 Catch:{ all -> 0x004d }
        r5 = "Releasing connection ";
        r4 = r4.append(r5);	 Catch:{ all -> 0x004d }
        r4 = r4.append(r7);	 Catch:{ all -> 0x004d }
        r4 = r4.toString();	 Catch:{ all -> 0x004d }
        r3.debug(r4);	 Catch:{ all -> 0x004d }
    L_0x002d:
        r3 = r0.getPoolEntry();	 Catch:{ all -> 0x004d }
        if (r3 != 0) goto L_0x0035;
    L_0x0033:
        monitor-exit(r0);	 Catch:{ all -> 0x004d }
    L_0x0034:
        return;
    L_0x0035:
        r1 = r0.getManager();	 Catch:{ all -> 0x004d }
        if (r1 != r6) goto L_0x0050;
    L_0x003b:
        r3 = 1;
    L_0x003c:
        r4 = "Connection not obtained from this manager";
        cz.msebera.android.httpclient.util.Asserts.check(r3, r4);	 Catch:{ all -> 0x004d }
        monitor-enter(r6);	 Catch:{ all -> 0x004d }
        r3 = r6.shutdown;	 Catch:{ all -> 0x00e4 }
        if (r3 == 0) goto L_0x0052;
    L_0x0047:
        r6.shutdownConnection(r0);	 Catch:{ all -> 0x00e4 }
        monitor-exit(r6);	 Catch:{ all -> 0x00e4 }
        monitor-exit(r0);	 Catch:{ all -> 0x004d }
        goto L_0x0034;
    L_0x004d:
        r3 = move-exception;
        monitor-exit(r0);	 Catch:{ all -> 0x004d }
        throw r3;
    L_0x0050:
        r3 = 0;
        goto L_0x003c;
    L_0x0052:
        r3 = r0.isOpen();	 Catch:{ all -> 0x00d1 }
        if (r3 == 0) goto L_0x0061;
    L_0x0058:
        r3 = r0.isMarkedReusable();	 Catch:{ all -> 0x00d1 }
        if (r3 != 0) goto L_0x0061;
    L_0x005e:
        r6.shutdownConnection(r0);	 Catch:{ all -> 0x00d1 }
    L_0x0061:
        r3 = r0.isMarkedReusable();	 Catch:{ all -> 0x00d1 }
        if (r3 == 0) goto L_0x00b5;
    L_0x0067:
        r4 = r6.poolEntry;	 Catch:{ all -> 0x00d1 }
        if (r10 == 0) goto L_0x00ca;
    L_0x006b:
        r3 = r10;
    L_0x006c:
        r4.updateExpiry(r8, r3);	 Catch:{ all -> 0x00d1 }
        r3 = r6.log;	 Catch:{ all -> 0x00d1 }
        r3 = r3.isDebugEnabled();	 Catch:{ all -> 0x00d1 }
        if (r3 == 0) goto L_0x00b5;
    L_0x0077:
        r4 = 0;
        r3 = (r8 > r4 ? 1 : (r8 == r4 ? 0 : -1));
        if (r3 <= 0) goto L_0x00cd;
    L_0x007d:
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00d1 }
        r3.<init>();	 Catch:{ all -> 0x00d1 }
        r4 = "for ";
        r3 = r3.append(r4);	 Catch:{ all -> 0x00d1 }
        r3 = r3.append(r8);	 Catch:{ all -> 0x00d1 }
        r4 = " ";
        r3 = r3.append(r4);	 Catch:{ all -> 0x00d1 }
        r3 = r3.append(r10);	 Catch:{ all -> 0x00d1 }
        r2 = r3.toString();	 Catch:{ all -> 0x00d1 }
    L_0x009c:
        r3 = r6.log;	 Catch:{ all -> 0x00d1 }
        r4 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00d1 }
        r4.<init>();	 Catch:{ all -> 0x00d1 }
        r5 = "Connection can be kept alive ";
        r4 = r4.append(r5);	 Catch:{ all -> 0x00d1 }
        r4 = r4.append(r2);	 Catch:{ all -> 0x00d1 }
        r4 = r4.toString();	 Catch:{ all -> 0x00d1 }
        r3.debug(r4);	 Catch:{ all -> 0x00d1 }
    L_0x00b5:
        r0.detach();	 Catch:{ all -> 0x00e4 }
        r3 = 0;
        r6.conn = r3;	 Catch:{ all -> 0x00e4 }
        r3 = r6.poolEntry;	 Catch:{ all -> 0x00e4 }
        r3 = r3.isClosed();	 Catch:{ all -> 0x00e4 }
        if (r3 == 0) goto L_0x00c6;
    L_0x00c3:
        r3 = 0;
        r6.poolEntry = r3;	 Catch:{ all -> 0x00e4 }
    L_0x00c6:
        monitor-exit(r6);	 Catch:{ all -> 0x00e4 }
        monitor-exit(r0);	 Catch:{ all -> 0x004d }
        goto L_0x0034;
    L_0x00ca:
        r3 = java.util.concurrent.TimeUnit.MILLISECONDS;	 Catch:{ all -> 0x00d1 }
        goto L_0x006c;
    L_0x00cd:
        r2 = "indefinitely";
        goto L_0x009c;
    L_0x00d1:
        r3 = move-exception;
        r0.detach();	 Catch:{ all -> 0x00e4 }
        r4 = 0;
        r6.conn = r4;	 Catch:{ all -> 0x00e4 }
        r4 = r6.poolEntry;	 Catch:{ all -> 0x00e4 }
        r4 = r4.isClosed();	 Catch:{ all -> 0x00e4 }
        if (r4 == 0) goto L_0x00e3;
    L_0x00e0:
        r4 = 0;
        r6.poolEntry = r4;	 Catch:{ all -> 0x00e4 }
    L_0x00e3:
        throw r3;	 Catch:{ all -> 0x00e4 }
    L_0x00e4:
        r3 = move-exception;
        monitor-exit(r6);	 Catch:{ all -> 0x00e4 }
        throw r3;	 Catch:{ all -> 0x004d }
        */
        throw new UnsupportedOperationException("Method not decompiled: cz.msebera.android.httpclient.impl.conn.BasicClientConnectionManager.releaseConnection(cz.msebera.android.httpclient.conn.ManagedClientConnection, long, java.util.concurrent.TimeUnit):void");
    }

    public void closeExpiredConnections() {
        synchronized (this) {
            assertNotShutdown();
            long now = System.currentTimeMillis();
            if (this.poolEntry != null && this.poolEntry.isExpired(now)) {
                this.poolEntry.close();
                this.poolEntry.getTracker().reset();
            }
        }
    }

    public void closeIdleConnections(long idletime, TimeUnit tunit) {
        Args.notNull(tunit, "Time unit");
        synchronized (this) {
            assertNotShutdown();
            long time = tunit.toMillis(idletime);
            if (time < 0) {
                time = 0;
            }
            long deadline = System.currentTimeMillis() - time;
            if (this.poolEntry != null && this.poolEntry.getUpdated() <= deadline) {
                this.poolEntry.close();
                this.poolEntry.getTracker().reset();
            }
        }
    }

    public void shutdown() {
        synchronized (this) {
            this.shutdown = true;
            try {
                if (this.poolEntry != null) {
                    this.poolEntry.close();
                }
                this.poolEntry = null;
                this.conn = null;
            } catch (Throwable th) {
                this.poolEntry = null;
                this.conn = null;
            }
        }
    }
}
