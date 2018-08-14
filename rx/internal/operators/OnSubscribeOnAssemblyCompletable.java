package rx.internal.operators;

import rx.Completable.OnSubscribe;
import rx.CompletableSubscriber;
import rx.Subscription;
import rx.exceptions.AssemblyStackTraceException;

public final class OnSubscribeOnAssemblyCompletable<T> implements OnSubscribe {
    public static volatile boolean fullStackTrace;
    final OnSubscribe source;
    final String stacktrace = OnSubscribeOnAssembly.createStacktrace();

    static final class OnAssemblyCompletableSubscriber implements CompletableSubscriber {
        final CompletableSubscriber actual;
        final String stacktrace;

        public OnAssemblyCompletableSubscriber(CompletableSubscriber actual, String stacktrace) {
            this.actual = actual;
            this.stacktrace = stacktrace;
        }

        public void onSubscribe(Subscription d) {
            this.actual.onSubscribe(d);
        }

        public void onCompleted() {
            this.actual.onCompleted();
        }

        public void onError(Throwable e) {
            new AssemblyStackTraceException(this.stacktrace).attachTo(e);
            this.actual.onError(e);
        }
    }

    public OnSubscribeOnAssemblyCompletable(OnSubscribe source) {
        this.source = source;
    }

    public void call(CompletableSubscriber t) {
        this.source.call(new OnAssemblyCompletableSubscriber(t, this.stacktrace));
    }
}
