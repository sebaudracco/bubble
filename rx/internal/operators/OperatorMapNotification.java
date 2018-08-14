package rx.internal.operators;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import rx.Observable$Operator;
import rx.Producer;
import rx.Subscriber;
import rx.exceptions.Exceptions;
import rx.functions.Func0;
import rx.functions.Func1;

public final class OperatorMapNotification<T, R> implements Observable$Operator<R, T> {
    final Func0<? extends R> onCompleted;
    final Func1<? super Throwable, ? extends R> onError;
    final Func1<? super T, ? extends R> onNext;

    static final class MapNotificationSubscriber<T, R> extends Subscriber<T> {
        static final long COMPLETED_FLAG = Long.MIN_VALUE;
        static final long REQUESTED_MASK = Long.MAX_VALUE;
        final Subscriber<? super R> actual;
        final AtomicLong missedRequested = new AtomicLong();
        final Func0<? extends R> onCompleted;
        final Func1<? super Throwable, ? extends R> onError;
        final Func1<? super T, ? extends R> onNext;
        long produced;
        final AtomicReference<Producer> producer = new AtomicReference();
        final AtomicLong requested = new AtomicLong();
        R value;

        public MapNotificationSubscriber(Subscriber<? super R> actual, Func1<? super T, ? extends R> onNext, Func1<? super Throwable, ? extends R> onError, Func0<? extends R> onCompleted) {
            this.actual = actual;
            this.onNext = onNext;
            this.onError = onError;
            this.onCompleted = onCompleted;
        }

        public void onNext(T t) {
            try {
                this.produced++;
                this.actual.onNext(this.onNext.call(t));
            } catch (Throwable ex) {
                Exceptions.throwOrReport(ex, this.actual, (Object) t);
            }
        }

        public void onError(Throwable e) {
            accountProduced();
            try {
                this.value = this.onError.call(e);
            } catch (Throwable ex) {
                Exceptions.throwOrReport(ex, this.actual, (Object) e);
            }
            tryEmit();
        }

        public void onCompleted() {
            accountProduced();
            try {
                this.value = this.onCompleted.call();
            } catch (Throwable ex) {
                Exceptions.throwOrReport(ex, this.actual);
            }
            tryEmit();
        }

        void accountProduced() {
            long p = this.produced;
            if (p != 0 && this.producer.get() != null) {
                BackpressureUtils.produced(this.requested, p);
            }
        }

        public void setProducer(Producer p) {
            if (this.producer.compareAndSet(null, p)) {
                long r = this.missedRequested.getAndSet(0);
                if (r != 0) {
                    p.request(r);
                    return;
                }
                return;
            }
            throw new IllegalStateException("Producer already set!");
        }

        void tryEmit() {
            long r;
            do {
                r = this.requested.get();
                if ((r & Long.MIN_VALUE) != 0) {
                    return;
                }
            } while (!this.requested.compareAndSet(r, r | Long.MIN_VALUE));
            if (r != 0 || this.producer.get() == null) {
                if (!this.actual.isUnsubscribed()) {
                    this.actual.onNext(this.value);
                }
                if (!this.actual.isUnsubscribed()) {
                    this.actual.onCompleted();
                }
            }
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        void requestInner(long r14) {
            /*
            r13 = this;
            r8 = 0;
            r8 = (r14 > r8 ? 1 : (r14 == r8 ? 0 : -1));
            if (r8 >= 0) goto L_0x0020;
        L_0x0006:
            r8 = new java.lang.IllegalArgumentException;
            r9 = new java.lang.StringBuilder;
            r9.<init>();
            r10 = "n >= 0 required but it was ";
            r9 = r9.append(r10);
            r9 = r9.append(r14);
            r9 = r9.toString();
            r8.<init>(r9);
            throw r8;
        L_0x0020:
            r8 = 0;
            r8 = (r14 > r8 ? 1 : (r14 == r8 ? 0 : -1));
            if (r8 != 0) goto L_0x0027;
        L_0x0026:
            return;
        L_0x0027:
            r8 = r13.requested;
            r2 = r8.get();
            r8 = -9223372036854775808;
            r8 = r8 & r2;
            r10 = 0;
            r8 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1));
            if (r8 == 0) goto L_0x0070;
        L_0x0036:
            r8 = 9223372036854775807; // 0x7fffffffffffffff float:NaN double:NaN;
            r6 = r2 & r8;
            r8 = rx.internal.operators.BackpressureUtils.addCap(r6, r14);
            r10 = -9223372036854775808;
            r4 = r8 | r10;
            r8 = r13.requested;
            r8 = r8.compareAndSet(r2, r4);
            if (r8 == 0) goto L_0x0027;
        L_0x004d:
            r8 = 0;
            r8 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1));
            if (r8 != 0) goto L_0x0026;
        L_0x0053:
            r8 = r13.actual;
            r8 = r8.isUnsubscribed();
            if (r8 != 0) goto L_0x0062;
        L_0x005b:
            r8 = r13.actual;
            r9 = r13.value;
            r8.onNext(r9);
        L_0x0062:
            r8 = r13.actual;
            r8 = r8.isUnsubscribed();
            if (r8 != 0) goto L_0x0026;
        L_0x006a:
            r8 = r13.actual;
            r8.onCompleted();
            goto L_0x0026;
        L_0x0070:
            r4 = rx.internal.operators.BackpressureUtils.addCap(r2, r14);
            r8 = r13.requested;
            r8 = r8.compareAndSet(r2, r4);
            if (r8 == 0) goto L_0x0027;
        L_0x007c:
            r1 = r13.producer;
            r0 = r1.get();
            r0 = (rx.Producer) r0;
            if (r0 == 0) goto L_0x008a;
        L_0x0086:
            r0.request(r14);
            goto L_0x0026;
        L_0x008a:
            r8 = r13.missedRequested;
            rx.internal.operators.BackpressureUtils.getAndAddRequest(r8, r14);
            r0 = r1.get();
            r0 = (rx.Producer) r0;
            if (r0 == 0) goto L_0x0026;
        L_0x0097:
            r8 = r13.missedRequested;
            r10 = 0;
            r2 = r8.getAndSet(r10);
            r8 = 0;
            r8 = (r2 > r8 ? 1 : (r2 == r8 ? 0 : -1));
            if (r8 == 0) goto L_0x0026;
        L_0x00a5:
            r0.request(r2);
            goto L_0x0026;
            */
            throw new UnsupportedOperationException("Method not decompiled: rx.internal.operators.OperatorMapNotification.MapNotificationSubscriber.requestInner(long):void");
        }
    }

    public OperatorMapNotification(Func1<? super T, ? extends R> onNext, Func1<? super Throwable, ? extends R> onError, Func0<? extends R> onCompleted) {
        this.onNext = onNext;
        this.onError = onError;
        this.onCompleted = onCompleted;
    }

    public Subscriber<? super T> call(Subscriber<? super R> child) {
        final MapNotificationSubscriber<T, R> parent = new MapNotificationSubscriber(child, this.onNext, this.onError, this.onCompleted);
        child.add(parent);
        child.setProducer(new Producer() {
            public void request(long n) {
                parent.requestInner(n);
            }
        });
        return parent;
    }
}
