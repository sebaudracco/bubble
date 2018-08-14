package retrofit2.adapter.rxjava;

import java.lang.reflect.Type;
import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Response;
import rx.Observable;
import rx.Observable$OnSubscribe;
import rx.Scheduler;

final class RxJavaCallAdapter<R> implements CallAdapter<R, Object> {
    private final boolean isAsync;
    private final boolean isBody;
    private final boolean isCompletable;
    private final boolean isResult;
    private final boolean isSingle;
    private final Type responseType;
    private final Scheduler scheduler;

    private static final class CompletableHelper {
        private CompletableHelper() {
        }

        static Object toCompletable(Observable<?> observable) {
            return observable.toCompletable();
        }
    }

    RxJavaCallAdapter(Type responseType, Scheduler scheduler, boolean isAsync, boolean isResult, boolean isBody, boolean isSingle, boolean isCompletable) {
        this.responseType = responseType;
        this.scheduler = scheduler;
        this.isAsync = isAsync;
        this.isResult = isResult;
        this.isBody = isBody;
        this.isSingle = isSingle;
        this.isCompletable = isCompletable;
    }

    public Type responseType() {
        return this.responseType;
    }

    public Object adapt(Call<R> call) {
        Observable$OnSubscribe<Response<R>> callFunc;
        Observable$OnSubscribe<?> func;
        if (this.isAsync) {
            callFunc = new CallEnqueueOnSubscribe(call);
        } else {
            callFunc = new CallExecuteOnSubscribe(call);
        }
        if (this.isResult) {
            func = new ResultOnSubscribe(callFunc);
        } else if (this.isBody) {
            func = new BodyOnSubscribe(callFunc);
        } else {
            func = callFunc;
        }
        Observable<?> observable = Observable.create(func);
        if (this.scheduler != null) {
            observable = observable.subscribeOn(this.scheduler);
        }
        if (this.isSingle) {
            return observable.toSingle();
        }
        if (this.isCompletable) {
            return CompletableHelper.toCompletable(observable);
        }
        return observable;
    }
}
