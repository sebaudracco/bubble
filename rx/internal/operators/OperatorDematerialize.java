package rx.internal.operators;

import rx.Notification;
import rx.Observable$Operator;
import rx.Subscriber;

public final class OperatorDematerialize<T> implements Observable$Operator<T, Notification<T>> {

    static final class Holder {
        static final OperatorDematerialize<Object> INSTANCE = new OperatorDematerialize();

        Holder() {
        }
    }

    public static OperatorDematerialize instance() {
        return Holder.INSTANCE;
    }

    OperatorDematerialize() {
    }

    public Subscriber<? super Notification<T>> call(final Subscriber<? super T> child) {
        return new Subscriber<Notification<T>>(child) {
            boolean terminated;

            public void onNext(Notification<T> t) {
                switch (t.getKind()) {
                    case OnNext:
                        if (!this.terminated) {
                            child.onNext(t.getValue());
                            return;
                        }
                        return;
                    case OnError:
                        onError(t.getThrowable());
                        return;
                    case OnCompleted:
                        onCompleted();
                        return;
                    default:
                        onError(new IllegalArgumentException("Unsupported notification type: " + t));
                        return;
                }
            }

            public void onError(Throwable e) {
                if (!this.terminated) {
                    this.terminated = true;
                    child.onError(e);
                }
            }

            public void onCompleted() {
                if (!this.terminated) {
                    this.terminated = true;
                    child.onCompleted();
                }
            }
        };
    }
}
