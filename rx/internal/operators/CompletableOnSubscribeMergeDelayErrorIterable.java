package rx.internal.operators;

import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;
import rx.Completable;
import rx.Completable.OnSubscribe;
import rx.CompletableSubscriber;
import rx.Subscription;
import rx.internal.util.unsafe.MpscLinkedQueue;
import rx.subscriptions.CompositeSubscription;

public final class CompletableOnSubscribeMergeDelayErrorIterable implements OnSubscribe {
    final Iterable<? extends Completable> sources;

    public CompletableOnSubscribeMergeDelayErrorIterable(Iterable<? extends Completable> sources) {
        this.sources = sources;
    }

    public void call(CompletableSubscriber s) {
        final CompositeSubscription set = new CompositeSubscription();
        s.onSubscribe(set);
        try {
            Iterator<? extends Completable> iterator = this.sources.iterator();
            if (iterator == null) {
                s.onError(new NullPointerException("The source iterator returned is null"));
                return;
            }
            final AtomicInteger wip = new AtomicInteger(1);
            final Queue<Throwable> queue = new MpscLinkedQueue();
            while (!set.isUnsubscribed()) {
                try {
                    if (iterator.hasNext()) {
                        if (!set.isUnsubscribed()) {
                            try {
                                Completable c = (Completable) iterator.next();
                                if (!set.isUnsubscribed()) {
                                    if (c == null) {
                                        queue.offer(new NullPointerException("A completable source is null"));
                                        if (wip.decrementAndGet() != 0) {
                                            return;
                                        }
                                        if (queue.isEmpty()) {
                                            s.onCompleted();
                                            return;
                                        } else {
                                            s.onError(CompletableOnSubscribeMerge.collectErrors(queue));
                                            return;
                                        }
                                    }
                                    wip.getAndIncrement();
                                    final CompletableSubscriber completableSubscriber = s;
                                    c.unsafeSubscribe(new CompletableSubscriber() {
                                        public void onSubscribe(Subscription d) {
                                            set.add(d);
                                        }

                                        public void onError(Throwable e) {
                                            queue.offer(e);
                                            tryTerminate();
                                        }

                                        public void onCompleted() {
                                            tryTerminate();
                                        }

                                        void tryTerminate() {
                                            if (wip.decrementAndGet() != 0) {
                                                return;
                                            }
                                            if (queue.isEmpty()) {
                                                completableSubscriber.onCompleted();
                                            } else {
                                                completableSubscriber.onError(CompletableOnSubscribeMerge.collectErrors(queue));
                                            }
                                        }
                                    });
                                } else {
                                    return;
                                }
                            } catch (NullPointerException e) {
                                queue.offer(e);
                                if (wip.decrementAndGet() != 0) {
                                    return;
                                }
                                if (queue.isEmpty()) {
                                    s.onCompleted();
                                    return;
                                } else {
                                    s.onError(CompletableOnSubscribeMerge.collectErrors(queue));
                                    return;
                                }
                            }
                        }
                        return;
                    } else if (wip.decrementAndGet() != 0) {
                        return;
                    } else {
                        if (queue.isEmpty()) {
                            s.onCompleted();
                            return;
                        } else {
                            s.onError(CompletableOnSubscribeMerge.collectErrors(queue));
                            return;
                        }
                    }
                } catch (Throwable e2) {
                    queue.offer(e2);
                    if (wip.decrementAndGet() != 0) {
                        return;
                    }
                    if (queue.isEmpty()) {
                        s.onCompleted();
                        return;
                    } else {
                        s.onError(CompletableOnSubscribeMerge.collectErrors(queue));
                        return;
                    }
                }
            }
        } catch (Throwable e22) {
            s.onError(e22);
        }
    }
}
