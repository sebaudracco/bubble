package rx.subjects;

import java.util.ArrayList;
import java.util.List;
import rx.Observable$OnSubscribe;
import rx.exceptions.Exceptions;
import rx.functions.Action1;
import rx.internal.operators.NotificationLite;
import rx.internal.producers.SingleProducer;

public final class AsyncSubject<T> extends Subject<T, T> {
    volatile Object lastValue;
    private final NotificationLite<T> nl = NotificationLite.instance();
    final SubjectSubscriptionManager<T> state;

    public static <T> AsyncSubject<T> create() {
        final SubjectSubscriptionManager<T> state = new SubjectSubscriptionManager();
        state.onTerminated = new Action1<SubjectObserver<T>>() {
            public void call(SubjectObserver<T> o) {
                Object v = state.getLatest();
                NotificationLite<T> nl = state.nl;
                if (v == null || nl.isCompleted(v)) {
                    o.onCompleted();
                } else if (nl.isError(v)) {
                    o.onError(nl.getError(v));
                } else {
                    o.actual.setProducer(new SingleProducer(o.actual, nl.getValue(v)));
                }
            }
        };
        return new AsyncSubject(state, state);
    }

    protected AsyncSubject(Observable$OnSubscribe<T> onSubscribe, SubjectSubscriptionManager<T> state) {
        super(onSubscribe);
        this.state = state;
    }

    public void onCompleted() {
        if (this.state.active) {
            Object last = this.lastValue;
            if (last == null) {
                last = this.nl.completed();
            }
            for (SubjectObserver<T> bo : this.state.terminate(last)) {
                if (last == this.nl.completed()) {
                    bo.onCompleted();
                } else {
                    bo.actual.setProducer(new SingleProducer(bo.actual, this.nl.getValue(last)));
                }
            }
        }
    }

    public void onError(Throwable e) {
        if (this.state.active) {
            List<Throwable> errors = null;
            for (SubjectObserver<T> bo : this.state.terminate(this.nl.error(e))) {
                try {
                    bo.onError(e);
                } catch (Throwable e2) {
                    if (errors == null) {
                        errors = new ArrayList();
                    }
                    errors.add(e2);
                }
            }
            Exceptions.throwIfAny(errors);
        }
    }

    public void onNext(T v) {
        this.lastValue = this.nl.next(v);
    }

    public boolean hasObservers() {
        return this.state.observers().length > 0;
    }

    public boolean hasValue() {
        return !this.nl.isError(this.state.getLatest()) && this.nl.isNext(this.lastValue);
    }

    public boolean hasThrowable() {
        return this.nl.isError(this.state.getLatest());
    }

    public boolean hasCompleted() {
        Object o = this.state.getLatest();
        return (o == null || this.nl.isError(o)) ? false : true;
    }

    public T getValue() {
        Object v = this.lastValue;
        if (this.nl.isError(this.state.getLatest()) || !this.nl.isNext(v)) {
            return null;
        }
        return this.nl.getValue(v);
    }

    public Throwable getThrowable() {
        Object o = this.state.getLatest();
        if (this.nl.isError(o)) {
            return this.nl.getError(o);
        }
        return null;
    }
}
