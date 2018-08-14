package rx.internal.operators;

import rx.Single.OnSubscribe;
import rx.SingleSubscriber;
import rx.exceptions.AssemblyStackTraceException;

public final class OnSubscribeOnAssemblySingle<T> implements OnSubscribe<T> {
    public static volatile boolean fullStackTrace;
    final OnSubscribe<T> source;
    final String stacktrace = OnSubscribeOnAssembly.createStacktrace();

    static final class OnAssemblySingleSubscriber<T> extends SingleSubscriber<T> {
        final SingleSubscriber<? super T> actual;
        final String stacktrace;

        public OnAssemblySingleSubscriber(SingleSubscriber<? super T> actual, String stacktrace) {
            this.actual = actual;
            this.stacktrace = stacktrace;
            actual.add(this);
        }

        public void onError(Throwable e) {
            new AssemblyStackTraceException(this.stacktrace).attachTo(e);
            this.actual.onError(e);
        }

        public void onSuccess(T t) {
            this.actual.onSuccess(t);
        }
    }

    public OnSubscribeOnAssemblySingle(OnSubscribe<T> source) {
        this.source = source;
    }

    public void call(SingleSubscriber<? super T> t) {
        this.source.call(new OnAssemblySingleSubscriber(t, this.stacktrace));
    }
}
