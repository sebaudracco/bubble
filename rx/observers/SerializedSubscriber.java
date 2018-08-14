package rx.observers;

import rx.Observer;
import rx.Subscriber;

public class SerializedSubscriber<T> extends Subscriber<T> {
    private final Observer<T> f12682s;

    public SerializedSubscriber(Subscriber<? super T> s) {
        this(s, true);
    }

    public SerializedSubscriber(Subscriber<? super T> s, boolean shareSubscriptions) {
        super(s, shareSubscriptions);
        this.f12682s = new SerializedObserver(s);
    }

    public void onCompleted() {
        this.f12682s.onCompleted();
    }

    public void onError(Throwable e) {
        this.f12682s.onError(e);
    }

    public void onNext(T t) {
        this.f12682s.onNext(t);
    }
}
