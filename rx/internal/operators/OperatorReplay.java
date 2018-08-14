package rx.internal.operators;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import rx.Observable;
import rx.Observable$OnSubscribe;
import rx.Observer;
import rx.Producer;
import rx.Scheduler;
import rx.Subscriber;
import rx.Subscription;
import rx.exceptions.Exceptions;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func0;
import rx.functions.Func1;
import rx.internal.util.OpenHashSet;
import rx.observables.ConnectableObservable;
import rx.schedulers.Timestamped;
import rx.subscriptions.Subscriptions;

public final class OperatorReplay<T> extends ConnectableObservable<T> {
    static final Func0 DEFAULT_UNBOUNDED_FACTORY = new C71411();
    final Func0<? extends ReplayBuffer<T>> bufferFactory;
    final AtomicReference<ReplaySubscriber<T>> current;
    final Observable<? extends T> source;

    static class C71411 implements Func0 {
        C71411() {
        }

        public Object call() {
            return new UnboundedReplayBuffer(16);
        }
    }

    interface ReplayBuffer<T> {
        void complete();

        void error(Throwable th);

        void next(T t);

        void replay(InnerProducer<T> innerProducer);
    }

    static class BoundedReplayBuffer<T> extends AtomicReference<Node> implements ReplayBuffer<T> {
        private static final long serialVersionUID = 2346567790059478686L;
        long index;
        final NotificationLite<T> nl = NotificationLite.instance();
        int size;
        Node tail;

        public BoundedReplayBuffer() {
            Node n = new Node(null, 0);
            this.tail = n;
            set(n);
        }

        final void addLast(Node n) {
            this.tail.set(n);
            this.tail = n;
            this.size++;
        }

        final void removeFirst() {
            Node next = (Node) ((Node) get()).get();
            if (next == null) {
                throw new IllegalStateException("Empty list!");
            }
            this.size--;
            setFirst(next);
        }

        final void removeSome(int n) {
            Node head = (Node) get();
            while (n > 0) {
                head = (Node) head.get();
                n--;
                this.size--;
            }
            setFirst(head);
        }

        final void setFirst(Node n) {
            set(n);
        }

        Node getInitialHead() {
            return (Node) get();
        }

        public final void next(T value) {
            Object o = enterTransform(this.nl.next(value));
            long j = this.index + 1;
            this.index = j;
            addLast(new Node(o, j));
            truncate();
        }

        public final void error(Throwable e) {
            Object o = enterTransform(this.nl.error(e));
            long j = this.index + 1;
            this.index = j;
            addLast(new Node(o, j));
            truncateFinal();
        }

        public final void complete() {
            Object o = enterTransform(this.nl.completed());
            long j = this.index + 1;
            this.index = j;
            addLast(new Node(o, j));
            truncateFinal();
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final void replay(rx.internal.operators.OperatorReplay.InnerProducer<T> r14) {
            /*
            r13 = this;
            r12 = 0;
            monitor-enter(r14);
            r9 = r14.emitting;	 Catch:{ all -> 0x007d }
            if (r9 == 0) goto L_0x000b;
        L_0x0006:
            r9 = 1;
            r14.missed = r9;	 Catch:{ all -> 0x007d }
            monitor-exit(r14);	 Catch:{ all -> 0x007d }
        L_0x000a:
            return;
        L_0x000b:
            r9 = 1;
            r14.emitting = r9;	 Catch:{ all -> 0x007d }
            monitor-exit(r14);	 Catch:{ all -> 0x007d }
        L_0x000f:
            r9 = r14.isUnsubscribed();
            if (r9 != 0) goto L_0x000a;
        L_0x0015:
            r4 = r14.index();
            r4 = (rx.internal.operators.OperatorReplay.Node) r4;
            if (r4 != 0) goto L_0x0028;
        L_0x001d:
            r4 = r13.getInitialHead();
            r14.index = r4;
            r10 = r4.index;
            r14.addTotalRequested(r10);
        L_0x0028:
            r9 = r14.isUnsubscribed();
            if (r9 != 0) goto L_0x000a;
        L_0x002e:
            r0 = r14.child;
            if (r0 == 0) goto L_0x000a;
        L_0x0032:
            r6 = r14.get();
            r2 = 0;
        L_0x0038:
            r9 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1));
            if (r9 == 0) goto L_0x008b;
        L_0x003c:
            r8 = r4.get();
            r8 = (rx.internal.operators.OperatorReplay.Node) r8;
            if (r8 == 0) goto L_0x008b;
        L_0x0044:
            r9 = r8.value;
            r5 = r13.leaveTransform(r9);
            r9 = r13.nl;	 Catch:{ Throwable -> 0x0056 }
            r9 = r9.accept(r0, r5);	 Catch:{ Throwable -> 0x0056 }
            if (r9 == 0) goto L_0x0080;
        L_0x0052:
            r9 = 0;
            r14.index = r9;	 Catch:{ Throwable -> 0x0056 }
            goto L_0x000a;
        L_0x0056:
            r1 = move-exception;
            r14.index = r12;
            rx.exceptions.Exceptions.throwIfFatal(r1);
            r14.unsubscribe();
            r9 = r13.nl;
            r9 = r9.isError(r5);
            if (r9 != 0) goto L_0x000a;
        L_0x0067:
            r9 = r13.nl;
            r9 = r9.isCompleted(r5);
            if (r9 != 0) goto L_0x000a;
        L_0x006f:
            r9 = r13.nl;
            r9 = r9.getValue(r5);
            r9 = rx.exceptions.OnErrorThrowable.addValueAsLastCause(r1, r9);
            r0.onError(r9);
            goto L_0x000a;
        L_0x007d:
            r9 = move-exception;
            monitor-exit(r14);	 Catch:{ all -> 0x007d }
            throw r9;
        L_0x0080:
            r10 = 1;
            r2 = r2 + r10;
            r4 = r8;
            r9 = r14.isUnsubscribed();
            if (r9 == 0) goto L_0x0038;
        L_0x008a:
            goto L_0x000a;
        L_0x008b:
            r10 = 0;
            r9 = (r2 > r10 ? 1 : (r2 == r10 ? 0 : -1));
            if (r9 == 0) goto L_0x009f;
        L_0x0091:
            r14.index = r4;
            r10 = 9223372036854775807; // 0x7fffffffffffffff float:NaN double:NaN;
            r9 = (r6 > r10 ? 1 : (r6 == r10 ? 0 : -1));
            if (r9 == 0) goto L_0x009f;
        L_0x009c:
            r14.produced(r2);
        L_0x009f:
            monitor-enter(r14);
            r9 = r14.missed;	 Catch:{ all -> 0x00aa }
            if (r9 != 0) goto L_0x00ad;
        L_0x00a4:
            r9 = 0;
            r14.emitting = r9;	 Catch:{ all -> 0x00aa }
            monitor-exit(r14);	 Catch:{ all -> 0x00aa }
            goto L_0x000a;
        L_0x00aa:
            r9 = move-exception;
            monitor-exit(r14);	 Catch:{ all -> 0x00aa }
            throw r9;
        L_0x00ad:
            r9 = 0;
            r14.missed = r9;	 Catch:{ all -> 0x00aa }
            monitor-exit(r14);	 Catch:{ all -> 0x00aa }
            goto L_0x000f;
            */
            throw new UnsupportedOperationException("Method not decompiled: rx.internal.operators.OperatorReplay.BoundedReplayBuffer.replay(rx.internal.operators.OperatorReplay$InnerProducer):void");
        }

        Object enterTransform(Object value) {
            return value;
        }

        Object leaveTransform(Object value) {
            return value;
        }

        void truncate() {
        }

        void truncateFinal() {
        }

        final void collect(Collection<? super T> output) {
            Node n = getInitialHead();
            while (true) {
                Node next = (Node) n.get();
                if (next != null) {
                    Object v = leaveTransform(next.value);
                    if (!this.nl.isCompleted(v) && !this.nl.isError(v)) {
                        output.add(this.nl.getValue(v));
                        n = next;
                    } else {
                        return;
                    }
                }
                return;
            }
        }

        boolean hasError() {
            return this.tail.value != null && this.nl.isError(leaveTransform(this.tail.value));
        }

        boolean hasCompleted() {
            return this.tail.value != null && this.nl.isCompleted(leaveTransform(this.tail.value));
        }
    }

    static final class InnerProducer<T> extends AtomicLong implements Producer, Subscription {
        static final long UNSUBSCRIBED = Long.MIN_VALUE;
        private static final long serialVersionUID = -4453897557930727610L;
        Subscriber<? super T> child;
        boolean emitting;
        Object index;
        boolean missed;
        final ReplaySubscriber<T> parent;
        final AtomicLong totalRequested = new AtomicLong();

        public InnerProducer(ReplaySubscriber<T> parent, Subscriber<? super T> child) {
            this.parent = parent;
            this.child = child;
        }

        public void request(long n) {
            if (n >= 0) {
                long r;
                long u;
                do {
                    r = get();
                    if (r == Long.MIN_VALUE) {
                        return;
                    }
                    if (r < 0 || n != 0) {
                        u = r + n;
                        if (u < 0) {
                            u = Long.MAX_VALUE;
                        }
                    } else {
                        return;
                    }
                } while (!compareAndSet(r, u));
                addTotalRequested(n);
                this.parent.manageRequests(this);
                this.parent.buffer.replay(this);
            }
        }

        void addTotalRequested(long n) {
            long r;
            long u;
            do {
                r = this.totalRequested.get();
                u = r + n;
                if (u < 0) {
                    u = Long.MAX_VALUE;
                }
            } while (!this.totalRequested.compareAndSet(r, u));
        }

        public long produced(long n) {
            if (n <= 0) {
                throw new IllegalArgumentException("Cant produce zero or less");
            }
            long u;
            long r;
            do {
                r = get();
                if (r == Long.MIN_VALUE) {
                    return Long.MIN_VALUE;
                }
                u = r - n;
                if (u < 0) {
                    throw new IllegalStateException("More produced (" + n + ") than requested (" + r + ")");
                }
            } while (!compareAndSet(r, u));
            return u;
        }

        public boolean isUnsubscribed() {
            return get() == Long.MIN_VALUE;
        }

        public void unsubscribe() {
            if (get() != Long.MIN_VALUE && getAndSet(Long.MIN_VALUE) != Long.MIN_VALUE) {
                this.parent.remove(this);
                this.parent.manageRequests(this);
                this.child = null;
            }
        }

        <U> U index() {
            return this.index;
        }
    }

    static final class Node extends AtomicReference<Node> {
        private static final long serialVersionUID = 245354315435971818L;
        final long index;
        final Object value;

        public Node(Object value, long index) {
            this.value = value;
            this.index = index;
        }
    }

    static final class ReplaySubscriber<T> extends Subscriber<T> implements Subscription {
        static final InnerProducer[] EMPTY = new InnerProducer[0];
        static final InnerProducer[] TERMINATED = new InnerProducer[0];
        final ReplayBuffer<T> buffer;
        boolean coordinateAll;
        List<InnerProducer<T>> coordinationQueue;
        boolean done;
        boolean emitting;
        long maxChildRequested;
        long maxUpstreamRequested;
        boolean missed;
        final NotificationLite<T> nl = NotificationLite.instance();
        volatile Producer producer;
        final OpenHashSet<InnerProducer<T>> producers = new OpenHashSet();
        InnerProducer<T>[] producersCache = EMPTY;
        long producersCacheVersion;
        volatile long producersVersion;
        final AtomicBoolean shouldConnect = new AtomicBoolean();
        volatile boolean terminated;

        class C71501 implements Action0 {
            C71501() {
            }

            public void call() {
                if (!ReplaySubscriber.this.terminated) {
                    synchronized (ReplaySubscriber.this.producers) {
                        if (!ReplaySubscriber.this.terminated) {
                            ReplaySubscriber.this.producers.terminate();
                            ReplaySubscriber replaySubscriber = ReplaySubscriber.this;
                            replaySubscriber.producersVersion++;
                            ReplaySubscriber.this.terminated = true;
                        }
                    }
                }
            }
        }

        public ReplaySubscriber(ReplayBuffer<T> buffer) {
            this.buffer = buffer;
            request(0);
        }

        void init() {
            add(Subscriptions.create(new C71501()));
        }

        boolean add(InnerProducer<T> producer) {
            if (producer == null) {
                throw new NullPointerException();
            } else if (this.terminated) {
                return false;
            } else {
                synchronized (this.producers) {
                    if (this.terminated) {
                        return false;
                    }
                    this.producers.add(producer);
                    this.producersVersion++;
                    return true;
                }
            }
        }

        void remove(InnerProducer<T> producer) {
            if (!this.terminated) {
                synchronized (this.producers) {
                    if (this.terminated) {
                        return;
                    }
                    this.producers.remove(producer);
                    if (this.producers.isEmpty()) {
                        this.producersCache = EMPTY;
                    }
                    this.producersVersion++;
                }
            }
        }

        public void setProducer(Producer p) {
            if (this.producer != null) {
                throw new IllegalStateException("Only a single producer can be set on a Subscriber.");
            }
            this.producer = p;
            manageRequests(null);
            replay();
        }

        public void onNext(T t) {
            if (!this.done) {
                this.buffer.next(t);
                replay();
            }
        }

        public void onError(Throwable e) {
            if (!this.done) {
                this.done = true;
                try {
                    this.buffer.error(e);
                    replay();
                } finally {
                    unsubscribe();
                }
            }
        }

        public void onCompleted() {
            if (!this.done) {
                this.done = true;
                try {
                    this.buffer.complete();
                    replay();
                } finally {
                    unsubscribe();
                }
            }
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        void manageRequests(rx.internal.operators.OperatorReplay.InnerProducer<T> r15) {
            /*
            r14 = this;
            r11 = r14.isUnsubscribed();
            if (r11 == 0) goto L_0x0007;
        L_0x0006:
            return;
        L_0x0007:
            monitor-enter(r14);
            r11 = r14.emitting;	 Catch:{ all -> 0x0021 }
            if (r11 == 0) goto L_0x0028;
        L_0x000c:
            if (r15 == 0) goto L_0x0024;
        L_0x000e:
            r5 = r14.coordinationQueue;	 Catch:{ all -> 0x0021 }
            if (r5 != 0) goto L_0x0019;
        L_0x0012:
            r5 = new java.util.ArrayList;	 Catch:{ all -> 0x0021 }
            r5.<init>();	 Catch:{ all -> 0x0021 }
            r14.coordinationQueue = r5;	 Catch:{ all -> 0x0021 }
        L_0x0019:
            r5.add(r15);	 Catch:{ all -> 0x0021 }
        L_0x001c:
            r11 = 1;
            r14.missed = r11;	 Catch:{ all -> 0x0021 }
            monitor-exit(r14);	 Catch:{ all -> 0x0021 }
            goto L_0x0006;
        L_0x0021:
            r11 = move-exception;
            monitor-exit(r14);	 Catch:{ all -> 0x0021 }
            throw r11;
        L_0x0024:
            r11 = 1;
            r14.coordinateAll = r11;	 Catch:{ all -> 0x0021 }
            goto L_0x001c;
        L_0x0028:
            r11 = 1;
            r14.emitting = r11;	 Catch:{ all -> 0x0021 }
            monitor-exit(r14);	 Catch:{ all -> 0x0021 }
            r8 = r14.maxChildRequested;
            if (r15 == 0) goto L_0x0050;
        L_0x0030:
            r11 = r15.totalRequested;
            r12 = r11.get();
            r6 = java.lang.Math.max(r8, r12);
        L_0x003a:
            r14.makeRequest(r6, r8);
        L_0x003d:
            r11 = r14.isUnsubscribed();
            if (r11 != 0) goto L_0x0006;
        L_0x0043:
            monitor-enter(r14);
            r11 = r14.missed;	 Catch:{ all -> 0x004d }
            if (r11 != 0) goto L_0x006b;
        L_0x0048:
            r11 = 0;
            r14.emitting = r11;	 Catch:{ all -> 0x004d }
            monitor-exit(r14);	 Catch:{ all -> 0x004d }
            goto L_0x0006;
        L_0x004d:
            r11 = move-exception;
            monitor-exit(r14);	 Catch:{ all -> 0x004d }
            throw r11;
        L_0x0050:
            r6 = r8;
            r0 = r14.copyProducers();
            r2 = r0;
            r4 = r2.length;
            r3 = 0;
        L_0x0058:
            if (r3 >= r4) goto L_0x003a;
        L_0x005a:
            r10 = r2[r3];
            if (r10 == 0) goto L_0x0068;
        L_0x005e:
            r11 = r10.totalRequested;
            r12 = r11.get();
            r6 = java.lang.Math.max(r6, r12);
        L_0x0068:
            r3 = r3 + 1;
            goto L_0x0058;
        L_0x006b:
            r11 = 0;
            r14.missed = r11;	 Catch:{ all -> 0x004d }
            r5 = r14.coordinationQueue;	 Catch:{ all -> 0x004d }
            r11 = 0;
            r14.coordinationQueue = r11;	 Catch:{ all -> 0x004d }
            r1 = r14.coordinateAll;	 Catch:{ all -> 0x004d }
            r11 = 0;
            r14.coordinateAll = r11;	 Catch:{ all -> 0x004d }
            monitor-exit(r14);	 Catch:{ all -> 0x004d }
            r8 = r14.maxChildRequested;
            r6 = r8;
            if (r5 == 0) goto L_0x0099;
        L_0x007e:
            r3 = r5.iterator();
        L_0x0082:
            r11 = r3.hasNext();
            if (r11 == 0) goto L_0x0099;
        L_0x0088:
            r10 = r3.next();
            r10 = (rx.internal.operators.OperatorReplay.InnerProducer) r10;
            r11 = r10.totalRequested;
            r12 = r11.get();
            r6 = java.lang.Math.max(r6, r12);
            goto L_0x0082;
        L_0x0099:
            if (r1 == 0) goto L_0x00b5;
        L_0x009b:
            r0 = r14.copyProducers();
            r2 = r0;
            r4 = r2.length;
            r3 = 0;
        L_0x00a2:
            if (r3 >= r4) goto L_0x00b5;
        L_0x00a4:
            r10 = r2[r3];
            if (r10 == 0) goto L_0x00b2;
        L_0x00a8:
            r11 = r10.totalRequested;
            r12 = r11.get();
            r6 = java.lang.Math.max(r6, r12);
        L_0x00b2:
            r3 = r3 + 1;
            goto L_0x00a2;
        L_0x00b5:
            r14.makeRequest(r6, r8);
            goto L_0x003d;
            */
            throw new UnsupportedOperationException("Method not decompiled: rx.internal.operators.OperatorReplay.ReplaySubscriber.manageRequests(rx.internal.operators.OperatorReplay$InnerProducer):void");
        }

        InnerProducer<T>[] copyProducers() {
            InnerProducer<T>[] result;
            synchronized (this.producers) {
                Object[] a = this.producers.values();
                int n = a.length;
                result = new InnerProducer[n];
                System.arraycopy(a, 0, result, 0, n);
            }
            return result;
        }

        void makeRequest(long maxTotalRequests, long previousTotalRequests) {
            long ur = this.maxUpstreamRequested;
            Producer p = this.producer;
            long diff = maxTotalRequests - previousTotalRequests;
            if (diff != 0) {
                this.maxChildRequested = maxTotalRequests;
                if (p == null) {
                    long u = ur + diff;
                    if (u < 0) {
                        u = Long.MAX_VALUE;
                    }
                    this.maxUpstreamRequested = u;
                } else if (ur != 0) {
                    this.maxUpstreamRequested = 0;
                    p.request(ur + diff);
                } else {
                    p.request(diff);
                }
            } else if (ur != 0 && p != null) {
                this.maxUpstreamRequested = 0;
                p.request(ur);
            }
        }

        void replay() {
            InnerProducer<T>[] pc = this.producersCache;
            if (this.producersCacheVersion != this.producersVersion) {
                synchronized (this.producers) {
                    pc = this.producersCache;
                    Object[] a = this.producers.values();
                    int n = a.length;
                    if (pc.length != n) {
                        pc = new InnerProducer[n];
                        this.producersCache = pc;
                    }
                    System.arraycopy(a, 0, pc, 0, n);
                    this.producersCacheVersion = this.producersVersion;
                }
            }
            ReplayBuffer<T> b = this.buffer;
            for (InnerProducer<T> rp : pc) {
                if (rp != null) {
                    b.replay(rp);
                }
            }
        }
    }

    static final class SizeAndTimeBoundReplayBuffer<T> extends BoundedReplayBuffer<T> {
        private static final long serialVersionUID = 3457957419649567404L;
        final int limit;
        final long maxAgeInMillis;
        final Scheduler scheduler;

        public SizeAndTimeBoundReplayBuffer(int limit, long maxAgeInMillis, Scheduler scheduler) {
            this.scheduler = scheduler;
            this.limit = limit;
            this.maxAgeInMillis = maxAgeInMillis;
        }

        Object enterTransform(Object value) {
            return new Timestamped(this.scheduler.now(), value);
        }

        Object leaveTransform(Object value) {
            return ((Timestamped) value).getValue();
        }

        Node getInitialHead() {
            long timeLimit = this.scheduler.now() - this.maxAgeInMillis;
            Node prev = (Node) get();
            Node next = (Node) prev.get();
            while (next != null && ((Timestamped) next.value).getTimestampMillis() <= timeLimit) {
                prev = next;
                next = (Node) next.get();
            }
            return prev;
        }

        void truncate() {
            long timeLimit = this.scheduler.now() - this.maxAgeInMillis;
            Node prev = (Node) get();
            Node next = (Node) prev.get();
            int e = 0;
            while (next != null) {
                if (this.size <= this.limit) {
                    if (next.value.getTimestampMillis() > timeLimit) {
                        break;
                    }
                    e++;
                    this.size--;
                    prev = next;
                    next = (Node) next.get();
                } else {
                    e++;
                    this.size--;
                    prev = next;
                    next = (Node) next.get();
                }
            }
            if (e != 0) {
                setFirst(prev);
            }
        }

        void truncateFinal() {
            long timeLimit = this.scheduler.now() - this.maxAgeInMillis;
            Node prev = (Node) get();
            Node next = (Node) prev.get();
            int e = 0;
            while (next != null && this.size > 1 && next.value.getTimestampMillis() <= timeLimit) {
                e++;
                this.size--;
                prev = next;
                next = (Node) next.get();
            }
            if (e != 0) {
                setFirst(prev);
            }
        }
    }

    static final class SizeBoundReplayBuffer<T> extends BoundedReplayBuffer<T> {
        private static final long serialVersionUID = -5898283885385201806L;
        final int limit;

        public SizeBoundReplayBuffer(int limit) {
            this.limit = limit;
        }

        void truncate() {
            if (this.size > this.limit) {
                removeFirst();
            }
        }
    }

    static final class UnboundedReplayBuffer<T> extends ArrayList<Object> implements ReplayBuffer<T> {
        private static final long serialVersionUID = 7063189396499112664L;
        final NotificationLite<T> nl = NotificationLite.instance();
        volatile int size;

        public UnboundedReplayBuffer(int capacityHint) {
            super(capacityHint);
        }

        public void next(T value) {
            add(this.nl.next(value));
            this.size++;
        }

        public void error(Throwable e) {
            add(this.nl.error(e));
            this.size++;
        }

        public void complete() {
            add(this.nl.completed());
            this.size++;
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void replay(rx.internal.operators.OperatorReplay.InnerProducer<T> r15) {
            /*
            r14 = this;
            r10 = 0;
            monitor-enter(r15);
            r11 = r15.emitting;	 Catch:{ all -> 0x004b }
            if (r11 == 0) goto L_0x000b;
        L_0x0006:
            r10 = 1;
            r15.missed = r10;	 Catch:{ all -> 0x004b }
            monitor-exit(r15);	 Catch:{ all -> 0x004b }
        L_0x000a:
            return;
        L_0x000b:
            r11 = 1;
            r15.emitting = r11;	 Catch:{ all -> 0x004b }
            monitor-exit(r15);	 Catch:{ all -> 0x004b }
        L_0x000f:
            r11 = r15.isUnsubscribed();
            if (r11 != 0) goto L_0x000a;
        L_0x0015:
            r7 = r14.size;
            r2 = r15.index();
            r2 = (java.lang.Integer) r2;
            if (r2 == 0) goto L_0x004e;
        L_0x001f:
            r1 = r2.intValue();
        L_0x0023:
            r0 = r15.child;
            if (r0 == 0) goto L_0x000a;
        L_0x0027:
            r8 = r15.get();
            r4 = 0;
        L_0x002d:
            r11 = (r4 > r8 ? 1 : (r4 == r8 ? 0 : -1));
            if (r11 == 0) goto L_0x0075;
        L_0x0031:
            if (r1 >= r7) goto L_0x0075;
        L_0x0033:
            r6 = r14.get(r1);
            r11 = r14.nl;	 Catch:{ Throwable -> 0x0050 }
            r11 = r11.accept(r0, r6);	 Catch:{ Throwable -> 0x0050 }
            if (r11 != 0) goto L_0x000a;
        L_0x003f:
            r11 = r15.isUnsubscribed();
            if (r11 != 0) goto L_0x000a;
        L_0x0045:
            r1 = r1 + 1;
            r12 = 1;
            r4 = r4 + r12;
            goto L_0x002d;
        L_0x004b:
            r10 = move-exception;
            monitor-exit(r15);	 Catch:{ all -> 0x004b }
            throw r10;
        L_0x004e:
            r1 = r10;
            goto L_0x0023;
        L_0x0050:
            r3 = move-exception;
            rx.exceptions.Exceptions.throwIfFatal(r3);
            r15.unsubscribe();
            r10 = r14.nl;
            r10 = r10.isError(r6);
            if (r10 != 0) goto L_0x000a;
        L_0x005f:
            r10 = r14.nl;
            r10 = r10.isCompleted(r6);
            if (r10 != 0) goto L_0x000a;
        L_0x0067:
            r10 = r14.nl;
            r10 = r10.getValue(r6);
            r10 = rx.exceptions.OnErrorThrowable.addValueAsLastCause(r3, r10);
            r0.onError(r10);
            goto L_0x000a;
        L_0x0075:
            r12 = 0;
            r11 = (r4 > r12 ? 1 : (r4 == r12 ? 0 : -1));
            if (r11 == 0) goto L_0x008d;
        L_0x007b:
            r11 = java.lang.Integer.valueOf(r1);
            r15.index = r11;
            r12 = 9223372036854775807; // 0x7fffffffffffffff float:NaN double:NaN;
            r11 = (r8 > r12 ? 1 : (r8 == r12 ? 0 : -1));
            if (r11 == 0) goto L_0x008d;
        L_0x008a:
            r15.produced(r4);
        L_0x008d:
            monitor-enter(r15);
            r11 = r15.missed;	 Catch:{ all -> 0x0098 }
            if (r11 != 0) goto L_0x009b;
        L_0x0092:
            r10 = 0;
            r15.emitting = r10;	 Catch:{ all -> 0x0098 }
            monitor-exit(r15);	 Catch:{ all -> 0x0098 }
            goto L_0x000a;
        L_0x0098:
            r10 = move-exception;
            monitor-exit(r15);	 Catch:{ all -> 0x0098 }
            throw r10;
        L_0x009b:
            r11 = 0;
            r15.missed = r11;	 Catch:{ all -> 0x0098 }
            monitor-exit(r15);	 Catch:{ all -> 0x0098 }
            goto L_0x000f;
            */
            throw new UnsupportedOperationException("Method not decompiled: rx.internal.operators.OperatorReplay.UnboundedReplayBuffer.replay(rx.internal.operators.OperatorReplay$InnerProducer):void");
        }
    }

    public static <T, U, R> Observable<R> multicastSelector(final Func0<? extends ConnectableObservable<U>> connectableFactory, final Func1<? super Observable<U>, ? extends Observable<R>> selector) {
        return Observable.create(new Observable$OnSubscribe<R>() {
            public void call(final Subscriber<? super R> child) {
                try {
                    ConnectableObservable<U> co = (ConnectableObservable) connectableFactory.call();
                    ((Observable) selector.call(co)).subscribe(child);
                    co.connect(new Action1<Subscription>() {
                        public void call(Subscription t) {
                            child.add(t);
                        }
                    });
                } catch (Throwable e) {
                    Exceptions.throwOrReport(e, (Observer) child);
                }
            }
        });
    }

    public static <T> ConnectableObservable<T> observeOn(final ConnectableObservable<T> co, Scheduler scheduler) {
        final Observable<T> observable = co.observeOn(scheduler);
        return new ConnectableObservable<T>(new Observable$OnSubscribe<T>() {
            public void call(final Subscriber<? super T> child) {
                observable.unsafeSubscribe(new Subscriber<T>(child) {
                    public void onNext(T t) {
                        child.onNext(t);
                    }

                    public void onError(Throwable e) {
                        child.onError(e);
                    }

                    public void onCompleted() {
                        child.onCompleted();
                    }
                });
            }
        }) {
            public void connect(Action1<? super Subscription> connection) {
                co.connect(connection);
            }
        };
    }

    public static <T> ConnectableObservable<T> create(Observable<? extends T> source) {
        return create((Observable) source, DEFAULT_UNBOUNDED_FACTORY);
    }

    public static <T> ConnectableObservable<T> create(Observable<? extends T> source, final int bufferSize) {
        if (bufferSize == Integer.MAX_VALUE) {
            return create(source);
        }
        return create((Observable) source, new Func0<ReplayBuffer<T>>() {
            public ReplayBuffer<T> call() {
                return new SizeBoundReplayBuffer(bufferSize);
            }
        });
    }

    public static <T> ConnectableObservable<T> create(Observable<? extends T> source, long maxAge, TimeUnit unit, Scheduler scheduler) {
        return create(source, maxAge, unit, scheduler, Integer.MAX_VALUE);
    }

    public static <T> ConnectableObservable<T> create(Observable<? extends T> source, long maxAge, TimeUnit unit, final Scheduler scheduler, final int bufferSize) {
        final long maxAgeInMillis = unit.toMillis(maxAge);
        return create((Observable) source, new Func0<ReplayBuffer<T>>() {
            public ReplayBuffer<T> call() {
                return new SizeAndTimeBoundReplayBuffer(bufferSize, maxAgeInMillis, scheduler);
            }
        });
    }

    static <T> ConnectableObservable<T> create(Observable<? extends T> source, final Func0<? extends ReplayBuffer<T>> bufferFactory) {
        final AtomicReference<ReplaySubscriber<T>> curr = new AtomicReference();
        return new OperatorReplay(new Observable$OnSubscribe<T>() {
            public void call(Subscriber<? super T> child) {
                ReplaySubscriber<T> r;
                ReplaySubscriber<T> u;
                do {
                    r = (ReplaySubscriber) curr.get();
                    if (r != null) {
                        break;
                    }
                    u = new ReplaySubscriber((ReplayBuffer) bufferFactory.call());
                    u.init();
                } while (!curr.compareAndSet(r, u));
                r = u;
                InnerProducer<T> inner = new InnerProducer(r, child);
                r.add(inner);
                child.add(inner);
                r.buffer.replay(inner);
                child.setProducer(inner);
            }
        }, source, curr, bufferFactory);
    }

    private OperatorReplay(Observable$OnSubscribe<T> onSubscribe, Observable<? extends T> source, AtomicReference<ReplaySubscriber<T>> current, Func0<? extends ReplayBuffer<T>> bufferFactory) {
        super(onSubscribe);
        this.source = source;
        this.current = current;
        this.bufferFactory = bufferFactory;
    }

    public void connect(Action1<? super Subscription> connection) {
        ReplaySubscriber<T> ps;
        ReplaySubscriber<T> u;
        boolean doConnect = true;
        do {
            ps = (ReplaySubscriber) this.current.get();
            if (ps != null && !ps.isUnsubscribed()) {
                break;
            }
            u = new ReplaySubscriber((ReplayBuffer) this.bufferFactory.call());
            u.init();
        } while (!this.current.compareAndSet(ps, u));
        ps = u;
        if (ps.shouldConnect.get() || !ps.shouldConnect.compareAndSet(false, true)) {
            doConnect = false;
        }
        connection.call(ps);
        if (doConnect) {
            this.source.unsafeSubscribe(ps);
        }
    }
}
