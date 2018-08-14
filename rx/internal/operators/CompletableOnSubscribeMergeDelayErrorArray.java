package rx.internal.operators;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;
import rx.Completable;
import rx.Completable.OnSubscribe;
import rx.CompletableSubscriber;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public final class CompletableOnSubscribeMergeDelayErrorArray implements OnSubscribe {
    final Completable[] sources;

    public CompletableOnSubscribeMergeDelayErrorArray(Completable[] sources) {
        this.sources = sources;
    }

    public void call(CompletableSubscriber s) {
        final CompositeSubscription set = new CompositeSubscription();
        final AtomicInteger wip = new AtomicInteger(this.sources.length + 1);
        final Queue<Throwable> q = new ConcurrentLinkedQueue();
        s.onSubscribe(set);
        Completable[] arr$ = this.sources;
        int len$ = arr$.length;
        int i$ = 0;
        while (i$ < len$) {
            Completable c = arr$[i$];
            if (!set.isUnsubscribed()) {
                if (c == null) {
                    q.offer(new NullPointerException("A completable source is null"));
                    wip.decrementAndGet();
                } else {
                    final CompletableSubscriber completableSubscriber = s;
                    c.unsafeSubscribe(new CompletableSubscriber() {
                        public void onSubscribe(Subscription d) {
                            set.add(d);
                        }

                        public void onError(Throwable e) {
                            q.offer(e);
                            tryTerminate();
                        }

                        public void onCompleted() {
                            tryTerminate();
                        }

                        void tryTerminate() {
                            if (wip.decrementAndGet() != 0) {
                                return;
                            }
                            if (q.isEmpty()) {
                                completableSubscriber.onCompleted();
                            } else {
                                completableSubscriber.onError(CompletableOnSubscribeMerge.collectErrors(q));
                            }
                        }
                    });
                }
                i$++;
            } else {
                return;
            }
        }
        if (wip.decrementAndGet() != 0) {
            return;
        }
        if (q.isEmpty()) {
            s.onCompleted();
        } else {
            s.onError(CompletableOnSubscribeMerge.collectErrors(q));
        }
    }
}
