package rx.internal.operators;

import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import rx.Observable;
import rx.Observable$OnSubscribe;
import rx.Observer;
import rx.Producer;
import rx.Subscriber;
import rx.exceptions.Exceptions;
import rx.exceptions.MissingBackpressureException;
import rx.functions.Func1;
import rx.internal.util.ExceptionsUtils;
import rx.internal.util.RxRingBuffer;
import rx.internal.util.ScalarSynchronousObservable;
import rx.internal.util.atomic.SpscAtomicArrayQueue;
import rx.internal.util.atomic.SpscLinkedArrayQueue;
import rx.internal.util.unsafe.SpscArrayQueue;
import rx.internal.util.unsafe.UnsafeAccess;
import rx.plugins.RxJavaHooks;

public final class OnSubscribeFlattenIterable<T, R> implements Observable$OnSubscribe<R> {
    final Func1<? super T, ? extends Iterable<? extends R>> mapper;
    final int prefetch;
    final Observable<? extends T> source;

    static final class FlattenIterableSubscriber<T, R> extends Subscriber<T> {
        Iterator<? extends R> active;
        final Subscriber<? super R> actual;
        volatile boolean done;
        final AtomicReference<Throwable> error = new AtomicReference();
        final long limit;
        final Func1<? super T, ? extends Iterable<? extends R>> mapper;
        final NotificationLite<T> nl = NotificationLite.instance();
        long produced;
        final Queue<Object> queue;
        final AtomicLong requested = new AtomicLong();
        final AtomicInteger wip = new AtomicInteger();

        public FlattenIterableSubscriber(Subscriber<? super R> actual, Func1<? super T, ? extends Iterable<? extends R>> mapper, int prefetch) {
            this.actual = actual;
            this.mapper = mapper;
            if (prefetch == Integer.MAX_VALUE) {
                this.limit = Long.MAX_VALUE;
                this.queue = new SpscLinkedArrayQueue(RxRingBuffer.SIZE);
            } else {
                this.limit = (long) (prefetch - (prefetch >> 2));
                if (UnsafeAccess.isUnsafeAvailable()) {
                    this.queue = new SpscArrayQueue(prefetch);
                } else {
                    this.queue = new SpscAtomicArrayQueue(prefetch);
                }
            }
            request((long) prefetch);
        }

        public void onNext(T t) {
            if (this.queue.offer(this.nl.next(t))) {
                drain();
                return;
            }
            unsubscribe();
            onError(new MissingBackpressureException());
        }

        public void onError(Throwable e) {
            if (ExceptionsUtils.addThrowable(this.error, e)) {
                this.done = true;
                drain();
                return;
            }
            RxJavaHooks.onError(e);
        }

        public void onCompleted() {
            this.done = true;
            drain();
        }

        void requestMore(long n) {
            if (n > 0) {
                BackpressureUtils.getAndAddRequest(this.requested, n);
                drain();
            } else if (n < 0) {
                throw new IllegalStateException("n >= 0 required but it was " + n);
            }
        }

        void drain() {
            if (this.wip.getAndIncrement() == 0) {
                Subscriber<? super R> actual = this.actual;
                Queue<Object> queue = this.queue;
                int missed = 1;
                while (true) {
                    Iterator<? extends R> it = this.active;
                    if (it == null) {
                        boolean d = this.done;
                        Object v = queue.poll();
                        boolean empty = v == null;
                        if (!checkTerminated(d, empty, actual, queue)) {
                            if (!empty) {
                                long p = this.produced + 1;
                                if (p == this.limit) {
                                    this.produced = 0;
                                    request(p);
                                } else {
                                    this.produced = p;
                                }
                                try {
                                    it = ((Iterable) this.mapper.call(this.nl.getValue(v))).iterator();
                                    if (it.hasNext()) {
                                        this.active = it;
                                    } else {
                                        continue;
                                    }
                                } catch (Throwable ex) {
                                    Exceptions.throwIfFatal(ex);
                                    onError(ex);
                                }
                            }
                        } else {
                            return;
                        }
                    }
                    if (it != null) {
                        long r = this.requested.get();
                        long e = 0;
                        while (e != r) {
                            if (!checkTerminated(this.done, false, actual, queue)) {
                                try {
                                    actual.onNext(it.next());
                                    if (!checkTerminated(this.done, false, actual, queue)) {
                                        e++;
                                        try {
                                            if (!it.hasNext()) {
                                                it = null;
                                                this.active = null;
                                                break;
                                            }
                                        } catch (Throwable ex2) {
                                            Exceptions.throwIfFatal(ex2);
                                            it = null;
                                            this.active = null;
                                            onError(ex2);
                                        }
                                    } else {
                                        return;
                                    }
                                } catch (Throwable ex22) {
                                    Exceptions.throwIfFatal(ex22);
                                    it = null;
                                    this.active = null;
                                    onError(ex22);
                                }
                            } else {
                                return;
                            }
                        }
                        if (e == r) {
                            boolean z = this.done;
                            boolean z2 = queue.isEmpty() && it == null;
                            if (checkTerminated(z, z2, actual, queue)) {
                                return;
                            }
                        }
                        if (e != 0) {
                            BackpressureUtils.produced(this.requested, e);
                        }
                        if (it == null) {
                            continue;
                        }
                    }
                    missed = this.wip.addAndGet(-missed);
                    if (missed == 0) {
                        return;
                    }
                }
            }
        }

        boolean checkTerminated(boolean d, boolean empty, Subscriber<?> a, Queue<?> q) {
            if (a.isUnsubscribed()) {
                q.clear();
                this.active = null;
                return true;
            }
            if (d) {
                if (((Throwable) this.error.get()) != null) {
                    Throwable ex = ExceptionsUtils.terminate(this.error);
                    unsubscribe();
                    q.clear();
                    this.active = null;
                    a.onError(ex);
                    return true;
                } else if (empty) {
                    a.onCompleted();
                    return true;
                }
            }
            return false;
        }
    }

    static final class OnSubscribeScalarFlattenIterable<T, R> implements Observable$OnSubscribe<R> {
        final Func1<? super T, ? extends Iterable<? extends R>> mapper;
        final T value;

        public OnSubscribeScalarFlattenIterable(T value, Func1<? super T, ? extends Iterable<? extends R>> mapper) {
            this.value = value;
            this.mapper = mapper;
        }

        public void call(Subscriber<? super R> t) {
            try {
                Iterator<? extends R> iterator = ((Iterable) this.mapper.call(this.value)).iterator();
                if (iterator.hasNext()) {
                    t.setProducer(new IterableProducer(t, iterator));
                } else {
                    t.onCompleted();
                }
            } catch (Throwable ex) {
                Exceptions.throwOrReport(ex, (Observer) t, this.value);
            }
        }
    }

    protected OnSubscribeFlattenIterable(Observable<? extends T> source, Func1<? super T, ? extends Iterable<? extends R>> mapper, int prefetch) {
        this.source = source;
        this.mapper = mapper;
        this.prefetch = prefetch;
    }

    public void call(Subscriber<? super R> t) {
        final FlattenIterableSubscriber<T, R> parent = new FlattenIterableSubscriber(t, this.mapper, this.prefetch);
        t.add(parent);
        t.setProducer(new Producer() {
            public void request(long n) {
                parent.requestMore(n);
            }
        });
        this.source.unsafeSubscribe(parent);
    }

    public static <T, R> Observable<R> createFrom(Observable<? extends T> source, Func1<? super T, ? extends Iterable<? extends R>> mapper, int prefetch) {
        if (source instanceof ScalarSynchronousObservable) {
            return Observable.create(new OnSubscribeScalarFlattenIterable(((ScalarSynchronousObservable) source).get(), mapper));
        }
        return Observable.create(new OnSubscribeFlattenIterable(source, mapper, prefetch));
    }
}
