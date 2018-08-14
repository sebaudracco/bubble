package rx.internal.util;

import rx.Notification;
import rx.Observer;
import rx.functions.Action1;

public final class ActionNotificationObserver<T> implements Observer<T> {
    final Action1<Notification<? super T>> onNotification;

    public ActionNotificationObserver(Action1<Notification<? super T>> onNotification) {
        this.onNotification = onNotification;
    }

    public void onNext(T t) {
        this.onNotification.call(Notification.createOnNext(t));
    }

    public void onError(Throwable e) {
        this.onNotification.call(Notification.createOnError(e));
    }

    public void onCompleted() {
        this.onNotification.call(Notification.createOnCompleted());
    }
}
