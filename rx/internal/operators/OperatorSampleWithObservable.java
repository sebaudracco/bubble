package rx.internal.operators;

import java.util.concurrent.atomic.AtomicReference;
import rx.Observable;
import rx.Observable$Operator;
import rx.Subscriber;
import rx.Subscription;
import rx.observers.SerializedSubscriber;

public final class OperatorSampleWithObservable<T, U> implements Observable$Operator<T, T> {
    static final Object EMPTY_TOKEN = new Object();
    final Observable<U> sampler;

    public OperatorSampleWithObservable(Observable<U> sampler) {
        this.sampler = sampler;
    }

    public Subscriber<? super T> call(Subscriber<? super T> child) {
        final SerializedSubscriber<T> s = new SerializedSubscriber(child);
        final AtomicReference<Object> value = new AtomicReference(EMPTY_TOKEN);
        final AtomicReference<Subscription> main = new AtomicReference();
        final Subscriber<U> samplerSub = new Subscriber<U>() {
            public void onNext(U u) {
                T localValue = value.getAndSet(OperatorSampleWithObservable.EMPTY_TOKEN);
                if (localValue != OperatorSampleWithObservable.EMPTY_TOKEN) {
                    s.onNext(localValue);
                }
            }

            public void onError(Throwable e) {
                s.onError(e);
                ((Subscription) main.get()).unsubscribe();
            }

            public void onCompleted() {
                onNext(null);
                s.onCompleted();
                ((Subscription) main.get()).unsubscribe();
            }
        };
        Subscriber<T> result = new Subscriber<T>() {
            public void onNext(T t) {
                value.set(t);
            }

            public void onError(Throwable e) {
                s.onError(e);
                samplerSub.unsubscribe();
            }

            public void onCompleted() {
                samplerSub.onNext(null);
                s.onCompleted();
                samplerSub.unsubscribe();
            }
        };
        main.lazySet(result);
        child.add(result);
        child.add(samplerSub);
        this.sampler.unsafeSubscribe(samplerSub);
        return result;
    }
}
