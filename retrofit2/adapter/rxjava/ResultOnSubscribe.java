package retrofit2.adapter.rxjava;

import retrofit2.Response;
import rx.Observable$OnSubscribe;
import rx.Subscriber;
import rx.exceptions.CompositeException;
import rx.exceptions.Exceptions;
import rx.plugins.RxJavaPlugins;

final class ResultOnSubscribe<T> implements Observable$OnSubscribe<Result<T>> {
    private final Observable$OnSubscribe<Response<T>> upstream;

    private static class ResultSubscriber<R> extends Subscriber<Response<R>> {
        private final Subscriber<? super Result<R>> subscriber;

        ResultSubscriber(Subscriber<? super Result<R>> subscriber) {
            super(subscriber);
            this.subscriber = subscriber;
        }

        public void onNext(Response<R> response) {
            this.subscriber.onNext(Result.response(response));
        }

        public void onError(Throwable throwable) {
            try {
                this.subscriber.onNext(Result.error(throwable));
                this.subscriber.onCompleted();
            } catch (Throwable inner) {
                Exceptions.throwIfFatal(inner);
                RxJavaPlugins.getInstance().getErrorHandler().handleError(new CompositeException(new Throwable[]{t, inner}));
            }
        }

        public void onCompleted() {
            this.subscriber.onCompleted();
        }
    }

    ResultOnSubscribe(Observable$OnSubscribe<Response<T>> upstream) {
        this.upstream = upstream;
    }

    public void call(Subscriber<? super Result<T>> subscriber) {
        this.upstream.call(new ResultSubscriber(subscriber));
    }
}
