package rx.internal.operators;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import rx.Observable;
import rx.Observable$Operator;
import rx.Producer;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action0;
import rx.functions.Func1;
import rx.internal.util.atomic.SpscAtomicArrayQueue;
import rx.internal.util.unsafe.SpscArrayQueue;
import rx.internal.util.unsafe.UnsafeAccess;
import rx.subscriptions.Subscriptions;

public final class OperatorEagerConcatMap<T, R> implements Observable$Operator<R, T> {
    final int bufferSize;
    final Func1<? super T, ? extends Observable<? extends R>> mapper;
    private final int maxConcurrent;

    static final class EagerInnerSubscriber<T> extends Subscriber<T> {
        volatile boolean done;
        Throwable error;
        final NotificationLite<T> nl;
        final EagerOuterSubscriber<?, T> parent;
        final Queue<Object> queue;

        public EagerInnerSubscriber(EagerOuterSubscriber<?, T> parent, int bufferSize) {
            Queue<Object> q;
            this.parent = parent;
            if (UnsafeAccess.isUnsafeAvailable()) {
                q = new SpscArrayQueue(bufferSize);
            } else {
                q = new SpscAtomicArrayQueue(bufferSize);
            }
            this.queue = q;
            this.nl = NotificationLite.instance();
            request((long) bufferSize);
        }

        public void onNext(T t) {
            this.queue.offer(this.nl.next(t));
            this.parent.drain();
        }

        public void onError(Throwable e) {
            this.error = e;
            this.done = true;
            this.parent.drain();
        }

        public void onCompleted() {
            this.done = true;
            this.parent.drain();
        }

        void requestMore(long n) {
            request(n);
        }
    }

    static final class EagerOuterProducer extends AtomicLong implements Producer {
        private static final long serialVersionUID = -657299606803478389L;
        final EagerOuterSubscriber<?, ?> parent;

        public EagerOuterProducer(EagerOuterSubscriber<?, ?> parent) {
            this.parent = parent;
        }

        public void request(long n) {
            if (n < 0) {
                throw new IllegalStateException("n >= 0 required but it was " + n);
            } else if (n > 0) {
                BackpressureUtils.getAndAddRequest(this, n);
                this.parent.drain();
            }
        }
    }

    static final class EagerOuterSubscriber<T, R> extends Subscriber<T> {
        final Subscriber<? super R> actual;
        final int bufferSize;
        volatile boolean cancelled;
        volatile boolean done;
        Throwable error;
        final Func1<? super T, ? extends Observable<? extends R>> mapper;
        private EagerOuterProducer sharedProducer;
        final Queue<EagerInnerSubscriber<R>> subscribers = new LinkedList();
        final AtomicInteger wip = new AtomicInteger();

        class C71181 implements Action0 {
            C71181() {
            }

            public void call() {
                EagerOuterSubscriber.this.cancelled = true;
                if (EagerOuterSubscriber.this.wip.getAndIncrement() == 0) {
                    EagerOuterSubscriber.this.cleanup();
                }
            }
        }

        public EagerOuterSubscriber(Func1<? super T, ? extends Observable<? extends R>> mapper, int bufferSize, int maxConcurrent, Subscriber<? super R> actual) {
            this.mapper = mapper;
            this.bufferSize = bufferSize;
            this.actual = actual;
            request(maxConcurrent == Integer.MAX_VALUE ? Long.MAX_VALUE : (long) maxConcurrent);
        }

        void init() {
            this.sharedProducer = new EagerOuterProducer(this);
            add(Subscriptions.create(new C71181()));
            this.actual.add(this);
            this.actual.setProducer(this.sharedProducer);
        }

        void cleanup() {
            synchronized (this.subscribers) {
                List<Subscription> list = new ArrayList(this.subscribers);
                this.subscribers.clear();
            }
            for (Subscription s : list) {
                s.unsubscribe();
            }
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void onNext(T r6) {
            /*
            r5 = this;
            r3 = r5.mapper;	 Catch:{ Throwable -> 0x000d }
            r2 = r3.call(r6);	 Catch:{ Throwable -> 0x000d }
            r2 = (rx.Observable) r2;	 Catch:{ Throwable -> 0x000d }
            r3 = r5.cancelled;
            if (r3 == 0) goto L_0x0014;
        L_0x000c:
            return;
        L_0x000d:
            r0 = move-exception;
            r3 = r5.actual;
            rx.exceptions.Exceptions.throwOrReport(r0, r3, r6);
            goto L_0x000c;
        L_0x0014:
            r1 = new rx.internal.operators.OperatorEagerConcatMap$EagerInnerSubscriber;
            r3 = r5.bufferSize;
            r1.<init>(r5, r3);
            r4 = r5.subscribers;
            monitor-enter(r4);
            r3 = r5.cancelled;	 Catch:{ all -> 0x0024 }
            if (r3 == 0) goto L_0x0027;
        L_0x0022:
            monitor-exit(r4);	 Catch:{ all -> 0x0024 }
            goto L_0x000c;
        L_0x0024:
            r3 = move-exception;
            monitor-exit(r4);	 Catch:{ all -> 0x0024 }
            throw r3;
        L_0x0027:
            r3 = r5.subscribers;	 Catch:{ all -> 0x0024 }
            r3.add(r1);	 Catch:{ all -> 0x0024 }
            monitor-exit(r4);	 Catch:{ all -> 0x0024 }
            r3 = r5.cancelled;
            if (r3 != 0) goto L_0x000c;
        L_0x0031:
            r2.unsafeSubscribe(r1);
            r5.drain();
            goto L_0x000c;
            */
            throw new UnsupportedOperationException("Method not decompiled: rx.internal.operators.OperatorEagerConcatMap.EagerOuterSubscriber.onNext(java.lang.Object):void");
        }

        public void onError(Throwable e) {
            this.error = e;
            this.done = true;
            drain();
        }

        public void onCompleted() {
            this.done = true;
            drain();
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        void drain() {
            /*
            r24 = this;
            r0 = r24;
            r0 = r0.wip;
            r21 = r0;
            r21 = r21.getAndIncrement();
            if (r21 == 0) goto L_0x000d;
        L_0x000c:
            return;
        L_0x000d:
            r14 = 1;
            r0 = r24;
            r0 = r0.sharedProducer;
            r17 = r0;
            r0 = r24;
            r4 = r0.actual;
            r15 = rx.internal.operators.NotificationLite.instance();
        L_0x001c:
            r0 = r24;
            r0 = r0.cancelled;
            r21 = r0;
            if (r21 == 0) goto L_0x0028;
        L_0x0024:
            r24.cleanup();
            goto L_0x000c;
        L_0x0028:
            r0 = r24;
            r0 = r0.done;
            r16 = r0;
            r0 = r24;
            r0 = r0.subscribers;
            r22 = r0;
            monitor-enter(r22);
            r0 = r24;
            r0 = r0.subscribers;	 Catch:{ all -> 0x0054 }
            r21 = r0;
            r13 = r21.peek();	 Catch:{ all -> 0x0054 }
            r13 = (rx.internal.operators.OperatorEagerConcatMap.EagerInnerSubscriber) r13;	 Catch:{ all -> 0x0054 }
            monitor-exit(r22);	 Catch:{ all -> 0x0054 }
            if (r13 != 0) goto L_0x0057;
        L_0x0044:
            r5 = 1;
        L_0x0045:
            if (r16 == 0) goto L_0x005f;
        L_0x0047:
            r0 = r24;
            r8 = r0.error;
            if (r8 == 0) goto L_0x0059;
        L_0x004d:
            r24.cleanup();
            r4.onError(r8);
            goto L_0x000c;
        L_0x0054:
            r21 = move-exception;
            monitor-exit(r22);	 Catch:{ all -> 0x0054 }
            throw r21;
        L_0x0057:
            r5 = 0;
            goto L_0x0045;
        L_0x0059:
            if (r5 == 0) goto L_0x005f;
        L_0x005b:
            r4.onCompleted();
            goto L_0x000c;
        L_0x005f:
            if (r5 != 0) goto L_0x00bf;
        L_0x0061:
            r18 = r17.get();
            r6 = 0;
            r12 = r13.queue;
            r10 = 0;
        L_0x006a:
            r0 = r13.done;
            r16 = r0;
            r20 = r12.peek();
            if (r20 != 0) goto L_0x0082;
        L_0x0074:
            r5 = 1;
        L_0x0075:
            if (r16 == 0) goto L_0x00d3;
        L_0x0077:
            r11 = r13.error;
            if (r11 == 0) goto L_0x0084;
        L_0x007b:
            r24.cleanup();
            r4.onError(r11);
            goto L_0x000c;
        L_0x0082:
            r5 = 0;
            goto L_0x0075;
        L_0x0084:
            if (r5 == 0) goto L_0x00d3;
        L_0x0086:
            r0 = r24;
            r0 = r0.subscribers;
            r22 = r0;
            monitor-enter(r22);
            r0 = r24;
            r0 = r0.subscribers;	 Catch:{ all -> 0x00d0 }
            r21 = r0;
            r21.poll();	 Catch:{ all -> 0x00d0 }
            monitor-exit(r22);	 Catch:{ all -> 0x00d0 }
            r13.unsubscribe();
            r10 = 1;
            r22 = 1;
            r0 = r24;
            r1 = r22;
            r0.request(r1);
        L_0x00a4:
            r22 = 0;
            r21 = (r6 > r22 ? 1 : (r6 == r22 ? 0 : -1));
            if (r21 == 0) goto L_0x00bd;
        L_0x00aa:
            r22 = 9223372036854775807; // 0x7fffffffffffffff float:NaN double:NaN;
            r21 = (r18 > r22 ? 1 : (r18 == r22 ? 0 : -1));
            if (r21 == 0) goto L_0x00b8;
        L_0x00b3:
            r0 = r17;
            rx.internal.operators.BackpressureUtils.produced(r0, r6);
        L_0x00b8:
            if (r10 != 0) goto L_0x00bd;
        L_0x00ba:
            r13.requestMore(r6);
        L_0x00bd:
            if (r10 != 0) goto L_0x001c;
        L_0x00bf:
            r0 = r24;
            r0 = r0.wip;
            r21 = r0;
            r0 = -r14;
            r22 = r0;
            r14 = r21.addAndGet(r22);
            if (r14 != 0) goto L_0x001c;
        L_0x00ce:
            goto L_0x000c;
        L_0x00d0:
            r21 = move-exception;
            monitor-exit(r22);	 Catch:{ all -> 0x00d0 }
            throw r21;
        L_0x00d3:
            if (r5 != 0) goto L_0x00a4;
        L_0x00d5:
            r21 = (r18 > r6 ? 1 : (r18 == r6 ? 0 : -1));
            if (r21 == 0) goto L_0x00a4;
        L_0x00d9:
            r12.poll();
            r0 = r20;
            r21 = r15.getValue(r0);	 Catch:{ Throwable -> 0x00ed }
            r0 = r21;
            r4.onNext(r0);	 Catch:{ Throwable -> 0x00ed }
            r22 = 1;
            r6 = r6 + r22;
            goto L_0x006a;
        L_0x00ed:
            r9 = move-exception;
            r0 = r20;
            rx.exceptions.Exceptions.throwOrReport(r9, r4, r0);
            goto L_0x000c;
            */
            throw new UnsupportedOperationException("Method not decompiled: rx.internal.operators.OperatorEagerConcatMap.EagerOuterSubscriber.drain():void");
        }
    }

    public OperatorEagerConcatMap(Func1<? super T, ? extends Observable<? extends R>> mapper, int bufferSize, int maxConcurrent) {
        this.mapper = mapper;
        this.bufferSize = bufferSize;
        this.maxConcurrent = maxConcurrent;
    }

    public Subscriber<? super T> call(Subscriber<? super R> t) {
        EagerOuterSubscriber<T, R> outer = new EagerOuterSubscriber(this.mapper, this.bufferSize, this.maxConcurrent, t);
        outer.init();
        return outer;
    }
}
