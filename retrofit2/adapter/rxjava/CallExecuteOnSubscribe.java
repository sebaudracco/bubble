package retrofit2.adapter.rxjava;

import retrofit2.Call;
import retrofit2.Response;
import rx.Observable$OnSubscribe;
import rx.Subscriber;
import rx.exceptions.Exceptions;

final class CallExecuteOnSubscribe<T> implements Observable$OnSubscribe<Response<T>> {
    private final Call<T> originalCall;

    CallExecuteOnSubscribe(Call<T> originalCall) {
        this.originalCall = originalCall;
    }

    public void call(Subscriber<? super Response<T>> subscriber) {
        Call<T> call = this.originalCall.clone();
        CallArbiter<T> arbiter = new CallArbiter(call, subscriber);
        subscriber.add(arbiter);
        subscriber.setProducer(arbiter);
        try {
            arbiter.emitResponse(call.execute());
        } catch (Throwable t) {
            Exceptions.throwIfFatal(t);
            arbiter.emitError(t);
        }
    }
}
