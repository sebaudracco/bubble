package rx.internal.operators;

import rx.Observable;
import rx.Observable$OnSubscribe;
import rx.Subscriber;

public final class OnSubscribeTakeLastOne<T> implements Observable$OnSubscribe<T> {
    final Observable<T> source;

    static final class TakeLastOneSubscriber<T> extends DeferredScalarSubscriber<T, T> {
        static final Object EMPTY = new Object();

        public TakeLastOneSubscriber(Subscriber<? super T> actual) {
            super(actual);
            this.value = EMPTY;
        }

        public void onNext(T t) {
            this.value = t;
        }

        public void onCompleted() {
            Object o = this.value;
            if (o == EMPTY) {
                complete();
            } else {
                complete(o);
            }
        }
    }

    public OnSubscribeTakeLastOne(Observable<T> source) {
        this.source = source;
    }

    public void call(Subscriber<? super T> t) {
        new TakeLastOneSubscriber(t).subscribeTo(this.source);
    }
}
