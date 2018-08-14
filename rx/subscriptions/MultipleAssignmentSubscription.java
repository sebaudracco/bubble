package rx.subscriptions;

import rx.Subscription;
import rx.internal.subscriptions.SequentialSubscription;

public final class MultipleAssignmentSubscription implements Subscription {
    final SequentialSubscription state = new SequentialSubscription();

    public boolean isUnsubscribed() {
        return this.state.isUnsubscribed();
    }

    public void unsubscribe() {
        this.state.unsubscribe();
    }

    public void set(Subscription s) {
        if (s == null) {
            throw new IllegalArgumentException("Subscription can not be null");
        }
        this.state.replace(s);
    }

    public Subscription get() {
        return this.state.current();
    }
}
