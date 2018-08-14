package retrofit2.adapter.rxjava;

import java.util.concurrent.atomic.AtomicInteger;
import retrofit2.Call;
import retrofit2.Response;
import rx.Producer;
import rx.Subscriber;
import rx.Subscription;
import rx.exceptions.CompositeException;
import rx.exceptions.Exceptions;
import rx.plugins.RxJavaPlugins;

final class CallArbiter<T> extends AtomicInteger implements Subscription, Producer {
    private static final int STATE_HAS_RESPONSE = 2;
    private static final int STATE_REQUESTED = 1;
    private static final int STATE_TERMINATED = 3;
    private static final int STATE_WAITING = 0;
    private final Call<T> call;
    private volatile Response<T> response;
    private final Subscriber<? super Response<T>> subscriber;

    CallArbiter(Call<T> call, Subscriber<? super Response<T>> subscriber) {
        super(0);
        this.call = call;
        this.subscriber = subscriber;
    }

    public void unsubscribe() {
        this.call.cancel();
    }

    public boolean isUnsubscribed() {
        return this.call.isCanceled();
    }

    public void request(long amount) {
        if (amount != 0) {
            while (true) {
                int state = get();
                switch (state) {
                    case 0:
                        if (!compareAndSet(0, 1)) {
                            break;
                        }
                        return;
                    case 1:
                    case 3:
                        return;
                    case 2:
                        if (!compareAndSet(2, 3)) {
                            break;
                        }
                        deliverResponse(this.response);
                        return;
                    default:
                        throw new IllegalStateException("Unknown state: " + state);
                }
            }
        }
    }

    void emitResponse(Response<T> response) {
        while (true) {
            int state = get();
            switch (state) {
                case 0:
                    this.response = response;
                    if (!compareAndSet(0, 2)) {
                        break;
                    }
                    return;
                case 1:
                    if (!compareAndSet(1, 3)) {
                        break;
                    }
                    deliverResponse(response);
                    return;
                case 2:
                case 3:
                    throw new AssertionError();
                default:
                    throw new IllegalStateException("Unknown state: " + state);
            }
        }
    }

    private void deliverResponse(Response<T> response) {
        try {
            if (!isUnsubscribed()) {
                this.subscriber.onNext(response);
            }
            try {
                this.subscriber.onCompleted();
            } catch (Throwable t) {
                Exceptions.throwIfFatal(t);
                RxJavaPlugins.getInstance().getErrorHandler().handleError(t);
            }
        } catch (Throwable inner) {
            Exceptions.throwIfFatal(inner);
            RxJavaPlugins.getInstance().getErrorHandler().handleError(new CompositeException(new Throwable[]{t, inner}));
        }
    }

    void emitError(Throwable t) {
        set(3);
        if (!isUnsubscribed()) {
            try {
                this.subscriber.onError(t);
            } catch (Throwable inner) {
                Exceptions.throwIfFatal(inner);
                RxJavaPlugins.getInstance().getErrorHandler().handleError(new CompositeException(new Throwable[]{t, inner}));
            }
        }
    }
}
