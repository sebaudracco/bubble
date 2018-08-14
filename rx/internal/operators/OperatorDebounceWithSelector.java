package rx.internal.operators;

import rx.Observable;
import rx.Observable$Operator;
import rx.Observer;
import rx.Subscriber;
import rx.exceptions.Exceptions;
import rx.functions.Func1;
import rx.observers.SerializedSubscriber;
import rx.subscriptions.SerialSubscription;

public final class OperatorDebounceWithSelector<T, U> implements Observable$Operator<T, T> {
    final Func1<? super T, ? extends Observable<U>> selector;

    public OperatorDebounceWithSelector(Func1<? super T, ? extends Observable<U>> selector) {
        this.selector = selector;
    }

    public Subscriber<? super T> call(Subscriber<? super T> child) {
        final SerializedSubscriber<T> s = new SerializedSubscriber(child);
        final SerialSubscription serial = new SerialSubscription();
        child.add(serial);
        return new Subscriber<T>(child) {
            final Subscriber<?> self = this;
            final DebounceState<T> state = new DebounceState();

            public void onStart() {
                request(Long.MAX_VALUE);
            }

            public void onNext(T t) {
                try {
                    Observable<U> debouncer = (Observable) OperatorDebounceWithSelector.this.selector.call(t);
                    final int index = this.state.next(t);
                    Subscriber<U> debounceSubscriber = new Subscriber<U>() {
                        public void onNext(U u) {
                            onCompleted();
                        }

                        public void onError(Throwable e) {
                            C71031.this.self.onError(e);
                        }

                        public void onCompleted() {
                            C71031.this.state.emit(index, s, C71031.this.self);
                            unsubscribe();
                        }
                    };
                    serial.set(debounceSubscriber);
                    debouncer.unsafeSubscribe(debounceSubscriber);
                } catch (Throwable e) {
                    Exceptions.throwOrReport(e, (Observer) this);
                }
            }

            public void onError(Throwable e) {
                s.onError(e);
                unsubscribe();
                this.state.clear();
            }

            public void onCompleted() {
                this.state.emitAndComplete(s, this);
            }
        };
    }
}
