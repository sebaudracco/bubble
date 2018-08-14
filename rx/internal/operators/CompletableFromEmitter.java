package rx.internal.operators;

import java.util.concurrent.atomic.AtomicBoolean;
import rx.AsyncEmitter.Cancellable;
import rx.Completable.OnSubscribe;
import rx.CompletableEmitter;
import rx.CompletableSubscriber;
import rx.Subscription;
import rx.exceptions.Exceptions;
import rx.functions.Action1;
import rx.internal.subscriptions.SequentialSubscription;
import rx.plugins.RxJavaHooks;

public final class CompletableFromEmitter implements OnSubscribe {
    final Action1<CompletableEmitter> producer;

    static final class FromEmitter extends AtomicBoolean implements CompletableEmitter, Subscription {
        private static final long serialVersionUID = 5539301318568668881L;
        final CompletableSubscriber actual;
        final SequentialSubscription resource = new SequentialSubscription();

        public FromEmitter(CompletableSubscriber actual) {
            this.actual = actual;
        }

        public void onCompleted() {
            if (compareAndSet(false, true)) {
                try {
                    this.actual.onCompleted();
                } finally {
                    this.resource.unsubscribe();
                }
            }
        }

        public void onError(Throwable t) {
            if (compareAndSet(false, true)) {
                try {
                    this.actual.onError(t);
                } finally {
                    this.resource.unsubscribe();
                }
            } else {
                RxJavaHooks.onError(t);
            }
        }

        public void setSubscription(Subscription s) {
            this.resource.update(s);
        }

        public void setCancellation(Cancellable c) {
            setSubscription(new CancellableSubscription(c));
        }

        public void unsubscribe() {
            if (compareAndSet(false, true)) {
                this.resource.unsubscribe();
            }
        }

        public boolean isUnsubscribed() {
            return get();
        }
    }

    public CompletableFromEmitter(Action1<CompletableEmitter> producer) {
        this.producer = producer;
    }

    public void call(CompletableSubscriber t) {
        FromEmitter emitter = new FromEmitter(t);
        t.onSubscribe(emitter);
        try {
            this.producer.call(emitter);
        } catch (Throwable ex) {
            Exceptions.throwIfFatal(ex);
            emitter.onError(ex);
        }
    }
}
