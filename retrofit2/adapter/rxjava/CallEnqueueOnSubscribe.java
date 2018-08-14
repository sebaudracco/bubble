package retrofit2.adapter.rxjava;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observable$OnSubscribe;
import rx.Subscriber;
import rx.exceptions.Exceptions;

final class CallEnqueueOnSubscribe<T> implements Observable$OnSubscribe<Response<T>> {
    private final Call<T> originalCall;

    CallEnqueueOnSubscribe(Call<T> originalCall) {
        this.originalCall = originalCall;
    }

    public void call(Subscriber<? super Response<T>> subscriber) {
        Call<T> call = this.originalCall.clone();
        final CallArbiter<T> arbiter = new CallArbiter(call, subscriber);
        subscriber.add(arbiter);
        subscriber.setProducer(arbiter);
        call.enqueue(new Callback<T>() {
            public void onResponse(Call<T> call, Response<T> response) {
                arbiter.emitResponse(response);
            }

            public void onFailure(Call<T> call, Throwable t) {
                Exceptions.throwIfFatal(t);
                arbiter.emitError(t);
            }
        });
    }
}
