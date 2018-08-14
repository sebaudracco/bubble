package rx.internal.operators;

import rx.Subscriber;
import rx.plugins.RxJavaHooks;

public abstract class DeferredScalarSubscriberSafe<T, R> extends DeferredScalarSubscriber<T, R> {
    protected boolean done;

    public DeferredScalarSubscriberSafe(Subscriber<? super R> actual) {
        super(actual);
    }

    public void onError(Throwable ex) {
        if (this.done) {
            RxJavaHooks.onError(ex);
            return;
        }
        this.done = true;
        super.onError(ex);
    }

    public void onCompleted() {
        if (!this.done) {
            this.done = true;
            super.onCompleted();
        }
    }
}
