package rx.observers;

import java.util.concurrent.atomic.AtomicReference;
import rx.CompletableSubscriber;
import rx.Subscription;
import rx.annotations.Experimental;
import rx.plugins.RxJavaHooks;

@Experimental
public abstract class AsyncCompletableSubscriber implements CompletableSubscriber, Subscription {
    static final Unsubscribed UNSUBSCRIBED = new Unsubscribed();
    private final AtomicReference<Subscription> upstream = new AtomicReference();

    static final class Unsubscribed implements Subscription {
        Unsubscribed() {
        }

        public void unsubscribe() {
        }

        public boolean isUnsubscribed() {
            return true;
        }
    }

    public final void onSubscribe(Subscription d) {
        if (this.upstream.compareAndSet(null, d)) {
            onStart();
            return;
        }
        d.unsubscribe();
        if (this.upstream.get() != UNSUBSCRIBED) {
            RxJavaHooks.onError(new IllegalStateException("Subscription already set!"));
        }
    }

    protected void onStart() {
    }

    public final boolean isUnsubscribed() {
        return this.upstream.get() == UNSUBSCRIBED;
    }

    protected final void clear() {
        this.upstream.set(UNSUBSCRIBED);
    }

    public final void unsubscribe() {
        if (((Subscription) this.upstream.get()) != UNSUBSCRIBED) {
            Subscription current = (Subscription) this.upstream.getAndSet(UNSUBSCRIBED);
            if (current != null && current != UNSUBSCRIBED) {
                current.unsubscribe();
            }
        }
    }
}
