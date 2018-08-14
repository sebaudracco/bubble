package rx.internal.operators;

import rx.Observable$Operator;
import rx.Producer;
import rx.Subscriber;
import rx.exceptions.Exceptions;
import rx.exceptions.OnErrorThrowable;
import rx.plugins.RxJavaHooks;

public class OperatorCast<T, R> implements Observable$Operator<R, T> {
    final Class<R> castClass;

    static final class CastSubscriber<T, R> extends Subscriber<T> {
        final Subscriber<? super R> actual;
        final Class<R> castClass;
        boolean done;

        public CastSubscriber(Subscriber<? super R> actual, Class<R> castClass) {
            this.actual = actual;
            this.castClass = castClass;
        }

        public void onNext(T t) {
            try {
                this.actual.onNext(this.castClass.cast(t));
            } catch (Throwable ex) {
                Exceptions.throwIfFatal(ex);
                unsubscribe();
                onError(OnErrorThrowable.addValueAsLastCause(ex, t));
            }
        }

        public void onError(Throwable e) {
            if (this.done) {
                RxJavaHooks.onError(e);
                return;
            }
            this.done = true;
            this.actual.onError(e);
        }

        public void onCompleted() {
            if (!this.done) {
                this.actual.onCompleted();
            }
        }

        public void setProducer(Producer p) {
            this.actual.setProducer(p);
        }
    }

    public OperatorCast(Class<R> castClass) {
        this.castClass = castClass;
    }

    public Subscriber<? super T> call(Subscriber<? super R> o) {
        CastSubscriber<T, R> parent = new CastSubscriber(o, this.castClass);
        o.add(parent);
        return parent;
    }
}
