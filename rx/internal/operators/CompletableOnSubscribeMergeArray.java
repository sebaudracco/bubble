package rx.internal.operators;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import rx.Completable;
import rx.Completable.OnSubscribe;
import rx.CompletableSubscriber;
import rx.Subscription;
import rx.plugins.RxJavaHooks;
import rx.subscriptions.CompositeSubscription;

public final class CompletableOnSubscribeMergeArray implements OnSubscribe {
    final Completable[] sources;

    public CompletableOnSubscribeMergeArray(Completable[] sources) {
        this.sources = sources;
    }

    public void call(CompletableSubscriber s) {
        final CompositeSubscription set = new CompositeSubscription();
        final AtomicInteger wip = new AtomicInteger(this.sources.length + 1);
        final AtomicBoolean once = new AtomicBoolean();
        s.onSubscribe(set);
        Completable[] arr$ = this.sources;
        int len$ = arr$.length;
        int i$ = 0;
        while (i$ < len$) {
            Completable c = arr$[i$];
            if (!set.isUnsubscribed()) {
                if (c == null) {
                    set.unsubscribe();
                    NullPointerException npe = new NullPointerException("A completable source is null");
                    if (once.compareAndSet(false, true)) {
                        s.onError(npe);
                        return;
                    }
                    RxJavaHooks.onError(npe);
                }
                final CompletableSubscriber completableSubscriber = s;
                c.unsafeSubscribe(new CompletableSubscriber() {
                    public void onSubscribe(Subscription d) {
                        set.add(d);
                    }

                    public void onError(Throwable e) {
                        set.unsubscribe();
                        if (once.compareAndSet(false, true)) {
                            completableSubscriber.onError(e);
                        } else {
                            RxJavaHooks.onError(e);
                        }
                    }

                    public void onCompleted() {
                        if (wip.decrementAndGet() == 0 && once.compareAndSet(false, true)) {
                            completableSubscriber.onCompleted();
                        }
                    }
                });
                i$++;
            } else {
                return;
            }
        }
        if (wip.decrementAndGet() == 0 && once.compareAndSet(false, true)) {
            s.onCompleted();
        }
    }
}
