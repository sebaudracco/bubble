package rx.internal.operators;

import rx.Observable$Operator;
import rx.Subscriber;

public class OperatorIgnoreElements<T> implements Observable$Operator<T, T> {

    static final class Holder {
        static final OperatorIgnoreElements<?> INSTANCE = new OperatorIgnoreElements();

        Holder() {
        }
    }

    public static <T> OperatorIgnoreElements<T> instance() {
        return Holder.INSTANCE;
    }

    OperatorIgnoreElements() {
    }

    public Subscriber<? super T> call(final Subscriber<? super T> child) {
        Subscriber<T> parent = new Subscriber<T>() {
            public void onCompleted() {
                child.onCompleted();
            }

            public void onError(Throwable e) {
                child.onError(e);
            }

            public void onNext(T t) {
            }
        };
        child.add(parent);
        return parent;
    }
}