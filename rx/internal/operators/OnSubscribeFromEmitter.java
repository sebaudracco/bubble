package rx.internal.operators;

import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import rx.AsyncEmitter;
import rx.AsyncEmitter.BackpressureMode;
import rx.AsyncEmitter.Cancellable;
import rx.Observable$OnSubscribe;
import rx.Producer;
import rx.Subscriber;
import rx.Subscription;
import rx.exceptions.Exceptions;
import rx.exceptions.MissingBackpressureException;
import rx.functions.Action1;
import rx.internal.util.RxRingBuffer;
import rx.internal.util.atomic.SpscUnboundedAtomicArrayQueue;
import rx.internal.util.unsafe.SpscUnboundedArrayQueue;
import rx.internal.util.unsafe.UnsafeAccess;
import rx.plugins.RxJavaHooks;
import rx.subscriptions.SerialSubscription;

public final class OnSubscribeFromEmitter<T> implements Observable$OnSubscribe<T> {
    final Action1<AsyncEmitter<T>> asyncEmitter;
    final BackpressureMode backpressure;

    static abstract class BaseAsyncEmitter<T> extends AtomicLong implements AsyncEmitter<T>, Producer, Subscription {
        private static final long serialVersionUID = 7326289992464377023L;
        final Subscriber<? super T> actual;
        final SerialSubscription serial = new SerialSubscription();

        public BaseAsyncEmitter(Subscriber<? super T> actual) {
            this.actual = actual;
        }

        public void onCompleted() {
            if (!this.actual.isUnsubscribed()) {
                try {
                    this.actual.onCompleted();
                } finally {
                    this.serial.unsubscribe();
                }
            }
        }

        public void onError(Throwable e) {
            if (!this.actual.isUnsubscribed()) {
                try {
                    this.actual.onError(e);
                } finally {
                    this.serial.unsubscribe();
                }
            }
        }

        public final void unsubscribe() {
            this.serial.unsubscribe();
            onUnsubscribed();
        }

        void onUnsubscribed() {
        }

        public final boolean isUnsubscribed() {
            return this.serial.isUnsubscribed();
        }

        public final void request(long n) {
            if (BackpressureUtils.validate(n)) {
                BackpressureUtils.getAndAddRequest(this, n);
                onRequested();
            }
        }

        void onRequested() {
        }

        public final void setSubscription(Subscription s) {
            this.serial.set(s);
        }

        public final void setCancellation(Cancellable c) {
            setSubscription(new CancellableSubscription(c));
        }

        public final long requested() {
            return get();
        }
    }

    static final class BufferAsyncEmitter<T> extends BaseAsyncEmitter<T> {
        private static final long serialVersionUID = 2427151001689639875L;
        volatile boolean done;
        Throwable error;
        final NotificationLite<T> nl;
        final Queue<Object> queue;
        final AtomicInteger wip;

        public BufferAsyncEmitter(Subscriber<? super T> actual, int capacityHint) {
            super(actual);
            this.queue = UnsafeAccess.isUnsafeAvailable() ? new SpscUnboundedArrayQueue(capacityHint) : new SpscUnboundedAtomicArrayQueue(capacityHint);
            this.wip = new AtomicInteger();
            this.nl = NotificationLite.instance();
        }

        public void onNext(T t) {
            this.queue.offer(this.nl.next(t));
            drain();
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

        void onRequested() {
            drain();
        }

        void onUnsubscribed() {
            if (this.wip.getAndIncrement() == 0) {
                this.queue.clear();
            }
        }

        void drain() {
            if (this.wip.getAndIncrement() == 0) {
                int missed = 1;
                Subscriber<? super T> a = this.actual;
                Queue<Object> q = this.queue;
                do {
                    boolean d;
                    boolean empty;
                    Throwable ex;
                    long r = get();
                    long e = 0;
                    while (e != r) {
                        if (a.isUnsubscribed()) {
                            q.clear();
                            return;
                        }
                        d = this.done;
                        Object o = q.poll();
                        empty = o == null;
                        if (d && empty) {
                            ex = this.error;
                            if (ex != null) {
                                super.onError(ex);
                                return;
                            } else {
                                super.onCompleted();
                                return;
                            }
                        } else if (empty) {
                            break;
                        } else {
                            a.onNext(this.nl.getValue(o));
                            e++;
                        }
                    }
                    if (e == r) {
                        if (a.isUnsubscribed()) {
                            q.clear();
                            return;
                        }
                        d = this.done;
                        empty = q.isEmpty();
                        if (d && empty) {
                            ex = this.error;
                            if (ex != null) {
                                super.onError(ex);
                                return;
                            } else {
                                super.onCompleted();
                                return;
                            }
                        }
                    }
                    if (e != 0) {
                        BackpressureUtils.produced(this, e);
                    }
                    missed = this.wip.addAndGet(-missed);
                } while (missed != 0);
            }
        }
    }

    static final class CancellableSubscription extends AtomicReference<Cancellable> implements Subscription {
        private static final long serialVersionUID = 5718521705281392066L;

        public CancellableSubscription(Cancellable cancellable) {
            super(cancellable);
        }

        public boolean isUnsubscribed() {
            return get() == null;
        }

        public void unsubscribe() {
            if (get() != null) {
                Cancellable c = (Cancellable) getAndSet(null);
                if (c != null) {
                    try {
                        c.cancel();
                    } catch (Exception ex) {
                        Exceptions.throwIfFatal(ex);
                        RxJavaHooks.onError(ex);
                    }
                }
            }
        }
    }

    static abstract class NoOverflowBaseAsyncEmitter<T> extends BaseAsyncEmitter<T> {
        private static final long serialVersionUID = 4127754106204442833L;

        abstract void onOverflow();

        public NoOverflowBaseAsyncEmitter(Subscriber<? super T> actual) {
            super(actual);
        }

        public void onNext(T t) {
            if (!this.actual.isUnsubscribed()) {
                if (get() != 0) {
                    this.actual.onNext(t);
                    BackpressureUtils.produced(this, 1);
                    return;
                }
                onOverflow();
            }
        }
    }

    static final class DropAsyncEmitter<T> extends NoOverflowBaseAsyncEmitter<T> {
        private static final long serialVersionUID = 8360058422307496563L;

        public DropAsyncEmitter(Subscriber<? super T> actual) {
            super(actual);
        }

        void onOverflow() {
        }
    }

    static final class ErrorAsyncEmitter<T> extends NoOverflowBaseAsyncEmitter<T> {
        private static final long serialVersionUID = 338953216916120960L;
        private boolean done;

        public ErrorAsyncEmitter(Subscriber<? super T> actual) {
            super(actual);
        }

        public void onNext(T t) {
            if (!this.done) {
                super.onNext(t);
            }
        }

        public void onCompleted() {
            if (!this.done) {
                this.done = true;
                super.onCompleted();
            }
        }

        public void onError(Throwable e) {
            if (this.done) {
                RxJavaHooks.onError(e);
                return;
            }
            this.done = true;
            super.onError(e);
        }

        void onOverflow() {
            onError(new MissingBackpressureException("fromEmitter: could not emit value due to lack of requests"));
        }
    }

    static final class LatestAsyncEmitter<T> extends BaseAsyncEmitter<T> {
        private static final long serialVersionUID = 4023437720691792495L;
        volatile boolean done;
        Throwable error;
        final NotificationLite<T> nl = NotificationLite.instance();
        final AtomicReference<Object> queue = new AtomicReference();
        final AtomicInteger wip = new AtomicInteger();

        public LatestAsyncEmitter(Subscriber<? super T> actual) {
            super(actual);
        }

        public void onNext(T t) {
            this.queue.set(this.nl.next(t));
            drain();
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

        void onRequested() {
            drain();
        }

        void onUnsubscribed() {
            if (this.wip.getAndIncrement() == 0) {
                this.queue.lazySet(null);
            }
        }

        void drain() {
            if (this.wip.getAndIncrement() == 0) {
                int missed = 1;
                Subscriber<? super T> a = this.actual;
                AtomicReference<Object> q = this.queue;
                do {
                    boolean d;
                    boolean empty;
                    Throwable ex;
                    long r = get();
                    long e = 0;
                    while (e != r) {
                        if (a.isUnsubscribed()) {
                            q.lazySet(null);
                            return;
                        }
                        d = this.done;
                        Object o = q.getAndSet(null);
                        empty = o == null;
                        if (d && empty) {
                            ex = this.error;
                            if (ex != null) {
                                super.onError(ex);
                                return;
                            } else {
                                super.onCompleted();
                                return;
                            }
                        } else if (empty) {
                            break;
                        } else {
                            a.onNext(this.nl.getValue(o));
                            e++;
                        }
                    }
                    if (e == r) {
                        if (a.isUnsubscribed()) {
                            q.lazySet(null);
                            return;
                        }
                        d = this.done;
                        empty = q.get() == null;
                        if (d && empty) {
                            ex = this.error;
                            if (ex != null) {
                                super.onError(ex);
                                return;
                            } else {
                                super.onCompleted();
                                return;
                            }
                        }
                    }
                    if (e != 0) {
                        BackpressureUtils.produced(this, e);
                    }
                    missed = this.wip.addAndGet(-missed);
                } while (missed != 0);
            }
        }
    }

    static final class NoneAsyncEmitter<T> extends BaseAsyncEmitter<T> {
        private static final long serialVersionUID = 3776720187248809713L;

        public NoneAsyncEmitter(Subscriber<? super T> actual) {
            super(actual);
        }

        public void onNext(T t) {
            if (!this.actual.isUnsubscribed()) {
                this.actual.onNext(t);
                long r;
                do {
                    r = get();
                    if (r == 0) {
                        return;
                    }
                } while (!compareAndSet(r, r - 1));
            }
        }
    }

    public OnSubscribeFromEmitter(Action1<AsyncEmitter<T>> asyncEmitter, BackpressureMode backpressure) {
        this.asyncEmitter = asyncEmitter;
        this.backpressure = backpressure;
    }

    public void call(Subscriber<? super T> t) {
        BaseAsyncEmitter<T> emitter;
        switch (this.backpressure) {
            case NONE:
                emitter = new NoneAsyncEmitter(t);
                break;
            case ERROR:
                emitter = new ErrorAsyncEmitter(t);
                break;
            case DROP:
                emitter = new DropAsyncEmitter(t);
                break;
            case LATEST:
                emitter = new LatestAsyncEmitter(t);
                break;
            default:
                emitter = new BufferAsyncEmitter(t, RxRingBuffer.SIZE);
                break;
        }
        t.add(emitter);
        t.setProducer(emitter);
        this.asyncEmitter.call(emitter);
    }
}
