package rx.internal.operators;

import java.util.concurrent.atomic.AtomicBoolean;
import rx.Observable;
import rx.Observable$OnSubscribe;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.exceptions.CompositeException;
import rx.exceptions.Exceptions;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func0;
import rx.functions.Func1;
import rx.observers.Subscribers;

public final class OnSubscribeUsing<T, Resource> implements Observable$OnSubscribe<T> {
    private final Action1<? super Resource> dispose;
    private final boolean disposeEagerly;
    private final Func1<? super Resource, ? extends Observable<? extends T>> observableFactory;
    private final Func0<Resource> resourceFactory;

    static final class DisposeAction<Resource> extends AtomicBoolean implements Action0, Subscription {
        private static final long serialVersionUID = 4262875056400218316L;
        private Action1<? super Resource> dispose;
        private Resource resource;

        DisposeAction(Action1<? super Resource> dispose, Resource resource) {
            this.dispose = dispose;
            this.resource = resource;
            lazySet(false);
        }

        public void call() {
            if (compareAndSet(false, true)) {
                try {
                    this.dispose.call(this.resource);
                } finally {
                    this.resource = null;
                    this.dispose = null;
                }
            }
        }

        public boolean isUnsubscribed() {
            return get();
        }

        public void unsubscribe() {
            call();
        }
    }

    public OnSubscribeUsing(Func0<Resource> resourceFactory, Func1<? super Resource, ? extends Observable<? extends T>> observableFactory, Action1<? super Resource> dispose, boolean disposeEagerly) {
        this.resourceFactory = resourceFactory;
        this.observableFactory = observableFactory;
        this.dispose = dispose;
        this.disposeEagerly = disposeEagerly;
    }

    public void call(Subscriber<? super T> subscriber) {
        Throwable disposeError;
        DisposeAction<Resource> disposeOnceOnly;
        try {
            Observable<? extends T> observable;
            Resource resource = this.resourceFactory.call();
            disposeOnceOnly = new DisposeAction(this.dispose, resource);
            subscriber.add(disposeOnceOnly);
            Observable<? extends T> source = (Observable) this.observableFactory.call(resource);
            if (this.disposeEagerly) {
                observable = source.doOnTerminate(disposeOnceOnly);
            } else {
                observable = source.doAfterTerminate(disposeOnceOnly);
            }
            try {
                observable.unsafeSubscribe(Subscribers.wrap(subscriber));
            } catch (Throwable e) {
                disposeError = dispose(disposeOnceOnly);
                Exceptions.throwIfFatal(e);
                Exceptions.throwIfFatal(disposeError);
                if (disposeError != null) {
                    subscriber.onError(new CompositeException(new Throwable[]{e, disposeError}));
                    return;
                }
                subscriber.onError(e);
            }
        } catch (Throwable e2) {
            Exceptions.throwOrReport(e2, (Observer) subscriber);
        }
    }

    private Throwable dispose(Action0 disposeOnceOnly) {
        try {
            disposeOnceOnly.call();
            return null;
        } catch (Throwable e) {
            return e;
        }
    }
}
