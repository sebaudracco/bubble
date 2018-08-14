package rx.internal.util;

import rx.Observer;
import rx.Subscriber;

public final class ObserverSubscriber<T> extends Subscriber<T> {
    final Observer<? super T> observer;

    public ObserverSubscriber(Observer<? super T> observer) {
        this.observer = observer;
    }

    public void onNext(T t) {
        this.observer.onNext(t);
    }

    public void onError(Throwable e) {
        this.observer.onError(e);
    }

    public void onCompleted() {
        this.observer.onCompleted();
    }
}
