package rx;

import rx.AsyncEmitter.Cancellable;
import rx.annotations.Experimental;

@Experimental
public interface CompletableEmitter {
    void onCompleted();

    void onError(Throwable th);

    void setCancellation(Cancellable cancellable);

    void setSubscription(Subscription subscription);
}
