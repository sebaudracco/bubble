package rx.internal.operators;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import rx.Completable;
import rx.Completable.OnSubscribe;
import rx.CompletableSubscriber;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.exceptions.MissingBackpressureException;
import rx.internal.util.unsafe.SpscArrayQueue;
import rx.plugins.RxJavaHooks;
import rx.subscriptions.SerialSubscription;

public final class CompletableOnSubscribeConcat implements OnSubscribe {
    final int prefetch;
    final Observable<Completable> sources;

    static final class CompletableConcatSubscriber extends Subscriber<Completable> {
        final CompletableSubscriber actual;
        volatile boolean done;
        final ConcatInnerSubscriber inner = new ConcatInnerSubscriber();
        final AtomicBoolean once = new AtomicBoolean();
        final SpscArrayQueue<Completable> queue;
        final SerialSubscription sr = new SerialSubscription();
        final AtomicInteger wip = new AtomicInteger();

        final class ConcatInnerSubscriber implements CompletableSubscriber {
            ConcatInnerSubscriber() {
            }

            public void onSubscribe(Subscription d) {
                CompletableConcatSubscriber.this.sr.set(d);
            }

            public void onError(Throwable e) {
                CompletableConcatSubscriber.this.innerError(e);
            }

            public void onCompleted() {
                CompletableConcatSubscriber.this.innerComplete();
            }
        }

        public CompletableConcatSubscriber(CompletableSubscriber actual, int prefetch) {
            this.actual = actual;
            this.queue = new SpscArrayQueue(prefetch);
            add(this.sr);
            request((long) prefetch);
        }

        public void onNext(Completable t) {
            if (!this.queue.offer(t)) {
                onError(new MissingBackpressureException());
            } else if (this.wip.getAndIncrement() == 0) {
                next();
            }
        }

        public void onError(Throwable t) {
            if (this.once.compareAndSet(false, true)) {
                this.actual.onError(t);
            } else {
                RxJavaHooks.onError(t);
            }
        }

        public void onCompleted() {
            if (!this.done) {
                this.done = true;
                if (this.wip.getAndIncrement() == 0) {
                    next();
                }
            }
        }

        void innerError(Throwable e) {
            unsubscribe();
            onError(e);
        }

        void innerComplete() {
            if (this.wip.decrementAndGet() != 0) {
                next();
            }
            if (!this.done) {
                request(1);
            }
        }

        void next() {
            boolean d = this.done;
            Completable c = (Completable) this.queue.poll();
            if (c != null) {
                c.unsafeSubscribe(this.inner);
            } else if (!d) {
                RxJavaHooks.onError(new IllegalStateException("Queue is empty?!"));
            } else if (this.once.compareAndSet(false, true)) {
                this.actual.onCompleted();
            }
        }
    }

    public CompletableOnSubscribeConcat(Observable<? extends Completable> sources, int prefetch) {
        this.sources = sources;
        this.prefetch = prefetch;
    }

    public void call(CompletableSubscriber s) {
        CompletableConcatSubscriber parent = new CompletableConcatSubscriber(s, this.prefetch);
        s.onSubscribe(parent);
        this.sources.subscribe(parent);
    }
}
