package rx.internal.operators;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import rx.Completable;
import rx.Completable.OnSubscribe;
import rx.CompletableSubscriber;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.exceptions.CompositeException;
import rx.plugins.RxJavaHooks;
import rx.subscriptions.CompositeSubscription;

public final class CompletableOnSubscribeMerge implements OnSubscribe {
    final boolean delayErrors;
    final int maxConcurrency;
    final Observable<Completable> source;

    static final class CompletableMergeSubscriber extends Subscriber<Completable> {
        final CompletableSubscriber actual;
        final boolean delayErrors;
        volatile boolean done;
        final AtomicReference<Queue<Throwable>> errors = new AtomicReference();
        final AtomicBoolean once = new AtomicBoolean();
        final CompositeSubscription set = new CompositeSubscription();
        final AtomicInteger wip = new AtomicInteger(1);

        class C70551 implements CompletableSubscriber {
            Subscription f12671d;
            boolean innerDone;

            C70551() {
            }

            public void onSubscribe(Subscription d) {
                this.f12671d = d;
                CompletableMergeSubscriber.this.set.add(d);
            }

            public void onError(Throwable e) {
                if (this.innerDone) {
                    RxJavaHooks.onError(e);
                    return;
                }
                this.innerDone = true;
                CompletableMergeSubscriber.this.set.remove(this.f12671d);
                CompletableMergeSubscriber.this.getOrCreateErrors().offer(e);
                CompletableMergeSubscriber.this.terminate();
                if (CompletableMergeSubscriber.this.delayErrors && !CompletableMergeSubscriber.this.done) {
                    CompletableMergeSubscriber.this.request(1);
                }
            }

            public void onCompleted() {
                if (!this.innerDone) {
                    this.innerDone = true;
                    CompletableMergeSubscriber.this.set.remove(this.f12671d);
                    CompletableMergeSubscriber.this.terminate();
                    if (!CompletableMergeSubscriber.this.done) {
                        CompletableMergeSubscriber.this.request(1);
                    }
                }
            }
        }

        public CompletableMergeSubscriber(CompletableSubscriber actual, int maxConcurrency, boolean delayErrors) {
            this.actual = actual;
            this.delayErrors = delayErrors;
            if (maxConcurrency == Integer.MAX_VALUE) {
                request(Long.MAX_VALUE);
            } else {
                request((long) maxConcurrency);
            }
        }

        Queue<Throwable> getOrCreateErrors() {
            Queue<Throwable> q = (Queue) this.errors.get();
            if (q != null) {
                return q;
            }
            q = new ConcurrentLinkedQueue();
            if (this.errors.compareAndSet(null, q)) {
                return q;
            }
            return (Queue) this.errors.get();
        }

        public void onNext(Completable t) {
            if (!this.done) {
                this.wip.getAndIncrement();
                t.unsafeSubscribe(new C70551());
            }
        }

        public void onError(Throwable t) {
            if (this.done) {
                RxJavaHooks.onError(t);
                return;
            }
            getOrCreateErrors().offer(t);
            this.done = true;
            terminate();
        }

        public void onCompleted() {
            if (!this.done) {
                this.done = true;
                terminate();
            }
        }

        void terminate() {
            Queue<Throwable> q;
            Throwable e;
            if (this.wip.decrementAndGet() == 0) {
                q = (Queue) this.errors.get();
                if (q == null || q.isEmpty()) {
                    this.actual.onCompleted();
                    return;
                }
                e = CompletableOnSubscribeMerge.collectErrors(q);
                if (this.once.compareAndSet(false, true)) {
                    this.actual.onError(e);
                } else {
                    RxJavaHooks.onError(e);
                }
            } else if (!this.delayErrors) {
                q = (Queue) this.errors.get();
                if (q != null && !q.isEmpty()) {
                    e = CompletableOnSubscribeMerge.collectErrors(q);
                    if (this.once.compareAndSet(false, true)) {
                        this.actual.onError(e);
                    } else {
                        RxJavaHooks.onError(e);
                    }
                }
            }
        }
    }

    public CompletableOnSubscribeMerge(Observable<? extends Completable> source, int maxConcurrency, boolean delayErrors) {
        this.source = source;
        this.maxConcurrency = maxConcurrency;
        this.delayErrors = delayErrors;
    }

    public void call(CompletableSubscriber s) {
        CompletableMergeSubscriber parent = new CompletableMergeSubscriber(s, this.maxConcurrency, this.delayErrors);
        s.onSubscribe(parent);
        this.source.subscribe(parent);
    }

    public static Throwable collectErrors(Queue<Throwable> q) {
        List<Throwable> list = new ArrayList();
        while (true) {
            Throwable t = (Throwable) q.poll();
            if (t == null) {
                break;
            }
            list.add(t);
        }
        if (list.isEmpty()) {
            return null;
        }
        if (list.size() == 1) {
            return (Throwable) list.get(0);
        }
        return new CompositeException(list);
    }
}
