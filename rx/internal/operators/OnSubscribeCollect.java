package rx.internal.operators;

import rx.Observable;
import rx.Observable$OnSubscribe;
import rx.Subscriber;
import rx.exceptions.Exceptions;
import rx.functions.Action2;
import rx.functions.Func0;

public final class OnSubscribeCollect<T, R> implements Observable$OnSubscribe<R> {
    final Func0<R> collectionFactory;
    final Action2<R, ? super T> collector;
    final Observable<T> source;

    static final class CollectSubscriber<T, R> extends DeferredScalarSubscriberSafe<T, R> {
        final Action2<R, ? super T> collector;

        public CollectSubscriber(Subscriber<? super R> actual, R initialValue, Action2<R, ? super T> collector) {
            super(actual);
            this.value = initialValue;
            this.hasValue = true;
            this.collector = collector;
        }

        public void onNext(T t) {
            if (!this.done) {
                try {
                    this.collector.call(this.value, t);
                } catch (Throwable ex) {
                    Exceptions.throwIfFatal(ex);
                    unsubscribe();
                    onError(ex);
                }
            }
        }
    }

    public OnSubscribeCollect(Observable<T> source, Func0<R> collectionFactory, Action2<R, ? super T> collector) {
        this.source = source;
        this.collectionFactory = collectionFactory;
        this.collector = collector;
    }

    public void call(Subscriber<? super R> t) {
        try {
            new CollectSubscriber(t, this.collectionFactory.call(), this.collector).subscribeTo(this.source);
        } catch (Throwable ex) {
            Exceptions.throwIfFatal(ex);
            t.onError(ex);
        }
    }
}
