package rx.internal.operators;

import rx.Observable$OnSubscribe;
import rx.Subscriber;

public final class OnSubscribeThrow<T> implements Observable$OnSubscribe<T> {
    private final Throwable exception;

    public OnSubscribeThrow(Throwable exception) {
        this.exception = exception;
    }

    public void call(Subscriber<? super T> observer) {
        observer.onError(this.exception);
    }
}
