package rx.internal.operators;

import rx.Single;
import rx.Single.OnSubscribe;
import rx.SingleSubscriber;
import rx.exceptions.CompositeException;
import rx.exceptions.Exceptions;
import rx.functions.Action1;

public final class SingleDoOnEvent<T> implements OnSubscribe<T> {
    final Action1<Throwable> onError;
    final Action1<? super T> onSuccess;
    final Single<T> source;

    static final class SingleDoOnEventSubscriber<T> extends SingleSubscriber<T> {
        final SingleSubscriber<? super T> actual;
        final Action1<Throwable> onError;
        final Action1<? super T> onSuccess;

        SingleDoOnEventSubscriber(SingleSubscriber<? super T> actual, Action1<? super T> onSuccess, Action1<Throwable> onError) {
            this.actual = actual;
            this.onSuccess = onSuccess;
            this.onError = onError;
        }

        public void onSuccess(T value) {
            try {
                this.onSuccess.call(value);
                this.actual.onSuccess(value);
            } catch (Throwable e) {
                Exceptions.throwOrReport(e, (SingleSubscriber) this, (Object) value);
            }
        }

        public void onError(Throwable error) {
            try {
                this.onError.call(error);
                this.actual.onError(error);
            } catch (Throwable e) {
                Exceptions.throwIfFatal(e);
                this.actual.onError(new CompositeException(new Throwable[]{error, e}));
            }
        }
    }

    public SingleDoOnEvent(Single<T> source, Action1<? super T> onSuccess, Action1<Throwable> onError) {
        this.source = source;
        this.onSuccess = onSuccess;
        this.onError = onError;
    }

    public void call(SingleSubscriber<? super T> actual) {
        SingleSubscriber parent = new SingleDoOnEventSubscriber(actual, this.onSuccess, this.onError);
        actual.add(parent);
        this.source.subscribe(parent);
    }
}
