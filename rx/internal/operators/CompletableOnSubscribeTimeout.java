package rx.internal.operators;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;
import rx.Completable;
import rx.Completable.OnSubscribe;
import rx.CompletableSubscriber;
import rx.Scheduler;
import rx.Scheduler.Worker;
import rx.Subscription;
import rx.functions.Action0;
import rx.plugins.RxJavaHooks;
import rx.subscriptions.CompositeSubscription;

public final class CompletableOnSubscribeTimeout implements OnSubscribe {
    final Completable other;
    final Scheduler scheduler;
    final Completable source;
    final long timeout;
    final TimeUnit unit;

    public CompletableOnSubscribeTimeout(Completable source, long timeout, TimeUnit unit, Scheduler scheduler, Completable other) {
        this.source = source;
        this.timeout = timeout;
        this.unit = unit;
        this.scheduler = scheduler;
        this.other = other;
    }

    public void call(final CompletableSubscriber s) {
        final CompositeSubscription set = new CompositeSubscription();
        s.onSubscribe(set);
        final AtomicBoolean once = new AtomicBoolean();
        Worker w = this.scheduler.createWorker();
        set.add(w);
        w.schedule(new Action0() {

            class C70601 implements CompletableSubscriber {
                C70601() {
                }

                public void onSubscribe(Subscription d) {
                    set.add(d);
                }

                public void onError(Throwable e) {
                    set.unsubscribe();
                    s.onError(e);
                }

                public void onCompleted() {
                    set.unsubscribe();
                    s.onCompleted();
                }
            }

            public void call() {
                if (once.compareAndSet(false, true)) {
                    set.clear();
                    if (CompletableOnSubscribeTimeout.this.other == null) {
                        s.onError(new TimeoutException());
                    } else {
                        CompletableOnSubscribeTimeout.this.other.unsafeSubscribe(new C70601());
                    }
                }
            }
        }, this.timeout, this.unit);
        this.source.unsafeSubscribe(new CompletableSubscriber() {
            public void onSubscribe(Subscription d) {
                set.add(d);
            }

            public void onError(Throwable e) {
                if (once.compareAndSet(false, true)) {
                    set.unsubscribe();
                    s.onError(e);
                    return;
                }
                RxJavaHooks.onError(e);
            }

            public void onCompleted() {
                if (once.compareAndSet(false, true)) {
                    set.unsubscribe();
                    s.onCompleted();
                }
            }
        });
    }
}
