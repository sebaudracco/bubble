package rx.internal.operators;

import rx.Observable;
import rx.Single;
import rx.Single.OnSubscribe;
import rx.SingleSubscriber;
import rx.Subscriber;
import rx.plugins.RxJavaHooks;
import rx.subscriptions.SerialSubscription;

public final class SingleOnSubscribeDelaySubscriptionOther<T> implements OnSubscribe<T> {
    final Single<? extends T> main;
    final Observable<?> other;

    public SingleOnSubscribeDelaySubscriptionOther(Single<? extends T> main, Observable<?> other) {
        this.main = main;
        this.other = other;
    }

    public void call(final SingleSubscriber<? super T> subscriber) {
        final SingleSubscriber<T> child = new SingleSubscriber<T>() {
            public void onSuccess(T value) {
                subscriber.onSuccess(value);
            }

            public void onError(Throwable error) {
                subscriber.onError(error);
            }
        };
        final SerialSubscription serial = new SerialSubscription();
        subscriber.add(serial);
        Subscriber<Object> otherSubscriber = new Subscriber<Object>() {
            boolean done;

            public void onNext(Object t) {
                onCompleted();
            }

            public void onError(Throwable e) {
                if (this.done) {
                    RxJavaHooks.onError(e);
                    return;
                }
                this.done = true;
                child.onError(e);
            }

            public void onCompleted() {
                if (!this.done) {
                    this.done = true;
                    serial.set(child);
                    SingleOnSubscribeDelaySubscriptionOther.this.main.subscribe(child);
                }
            }
        };
        serial.set(otherSubscriber);
        this.other.subscribe(otherSubscriber);
    }
}
