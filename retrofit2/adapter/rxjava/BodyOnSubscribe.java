package retrofit2.adapter.rxjava;

import retrofit2.Response;
import rx.Observable$OnSubscribe;
import rx.Subscriber;
import rx.exceptions.CompositeException;
import rx.exceptions.Exceptions;
import rx.plugins.RxJavaPlugins;

final class BodyOnSubscribe<T> implements Observable$OnSubscribe<T> {
    private final Observable$OnSubscribe<Response<T>> upstream;

    private static class BodySubscriber<R> extends Subscriber<Response<R>> {
        private final Subscriber<? super R> subscriber;
        private boolean subscriberTerminated;

        BodySubscriber(Subscriber<? super R> subscriber) {
            super(subscriber);
            this.subscriber = subscriber;
        }

        public void onNext(Response<R> response) {
            if (response.isSuccessful()) {
                this.subscriber.onNext(response.body());
                return;
            }
            this.subscriberTerminated = true;
            try {
                this.subscriber.onError(new HttpException(response));
            } catch (Throwable inner) {
                Exceptions.throwIfFatal(inner);
                RxJavaPlugins.getInstance().getErrorHandler().handleError(new CompositeException(new Throwable[]{t, inner}));
            }
        }

        public void onError(Throwable throwable) {
            if (this.subscriberTerminated) {
                Throwable broken = new AssertionError("This should never happen! Report as a Retrofit bug with the full stacktrace.");
                broken.initCause(throwable);
                RxJavaPlugins.getInstance().getErrorHandler().handleError(broken);
                return;
            }
            this.subscriber.onError(throwable);
        }

        public void onCompleted() {
            if (!this.subscriberTerminated) {
                this.subscriber.onCompleted();
            }
        }
    }

    BodyOnSubscribe(Observable$OnSubscribe<Response<T>> upstream) {
        this.upstream = upstream;
    }

    public void call(Subscriber<? super T> subscriber) {
        this.upstream.call(new BodySubscriber(subscriber));
    }
}
