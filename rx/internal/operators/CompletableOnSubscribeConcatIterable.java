package rx.internal.operators;

import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;
import rx.Completable;
import rx.Completable.OnSubscribe;
import rx.CompletableSubscriber;
import rx.Subscription;
import rx.subscriptions.SerialSubscription;
import rx.subscriptions.Subscriptions;

public final class CompletableOnSubscribeConcatIterable implements OnSubscribe {
    final Iterable<? extends Completable> sources;

    static final class ConcatInnerSubscriber extends AtomicInteger implements CompletableSubscriber {
        private static final long serialVersionUID = -7965400327305809232L;
        final CompletableSubscriber actual;
        final SerialSubscription sd = new SerialSubscription();
        final Iterator<? extends Completable> sources;

        public ConcatInnerSubscriber(CompletableSubscriber actual, Iterator<? extends Completable> sources) {
            this.actual = actual;
            this.sources = sources;
        }

        public void onSubscribe(Subscription d) {
            this.sd.set(d);
        }

        public void onError(Throwable e) {
            this.actual.onError(e);
        }

        public void onCompleted() {
            next();
        }

        void next() {
            if (!this.sd.isUnsubscribed() && getAndIncrement() == 0) {
                Iterator<? extends Completable> a = this.sources;
                while (!this.sd.isUnsubscribed()) {
                    try {
                        if (a.hasNext()) {
                            try {
                                Completable c = (Completable) a.next();
                                if (c == null) {
                                    this.actual.onError(new NullPointerException("The completable returned is null"));
                                    return;
                                }
                                c.unsafeSubscribe((CompletableSubscriber) this);
                                if (decrementAndGet() == 0) {
                                    return;
                                }
                            } catch (Throwable ex) {
                                this.actual.onError(ex);
                                return;
                            }
                        }
                        this.actual.onCompleted();
                        return;
                    } catch (Throwable ex2) {
                        this.actual.onError(ex2);
                        return;
                    }
                }
            }
        }
    }

    public CompletableOnSubscribeConcatIterable(Iterable<? extends Completable> sources) {
        this.sources = sources;
    }

    public void call(CompletableSubscriber s) {
        try {
            Iterator<? extends Completable> it = this.sources.iterator();
            if (it == null) {
                s.onSubscribe(Subscriptions.unsubscribed());
                s.onError(new NullPointerException("The iterator returned is null"));
                return;
            }
            ConcatInnerSubscriber inner = new ConcatInnerSubscriber(s, it);
            s.onSubscribe(inner.sd);
            inner.next();
        } catch (Throwable e) {
            s.onSubscribe(Subscriptions.unsubscribed());
            s.onError(e);
        }
    }
}
