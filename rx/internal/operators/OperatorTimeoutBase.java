package rx.internal.operators;

import java.util.concurrent.TimeoutException;
import rx.Observable;
import rx.Observable$Operator;
import rx.Producer;
import rx.Scheduler;
import rx.Scheduler.Worker;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Func3;
import rx.functions.Func4;
import rx.internal.producers.ProducerArbiter;
import rx.observers.SerializedSubscriber;
import rx.subscriptions.SerialSubscription;

class OperatorTimeoutBase<T> implements Observable$Operator<T, T> {
    final FirstTimeoutStub<T> firstTimeoutStub;
    final Observable<? extends T> other;
    final Scheduler scheduler;
    final TimeoutStub<T> timeoutStub;

    interface FirstTimeoutStub<T> extends Func3<TimeoutSubscriber<T>, Long, Worker, Subscription> {
    }

    interface TimeoutStub<T> extends Func4<TimeoutSubscriber<T>, Long, T, Worker, Subscription> {
    }

    static final class TimeoutSubscriber<T> extends Subscriber<T> {
        long actual;
        final ProducerArbiter arbiter = new ProducerArbiter();
        final Worker inner;
        final Observable<? extends T> other;
        final SerialSubscription serial;
        final SerializedSubscriber<T> serializedSubscriber;
        boolean terminated;
        final TimeoutStub<T> timeoutStub;

        class C71881 extends Subscriber<T> {
            C71881() {
            }

            public void onNext(T t) {
                TimeoutSubscriber.this.serializedSubscriber.onNext(t);
            }

            public void onError(Throwable e) {
                TimeoutSubscriber.this.serializedSubscriber.onError(e);
            }

            public void onCompleted() {
                TimeoutSubscriber.this.serializedSubscriber.onCompleted();
            }

            public void setProducer(Producer p) {
                TimeoutSubscriber.this.arbiter.setProducer(p);
            }
        }

        TimeoutSubscriber(SerializedSubscriber<T> serializedSubscriber, TimeoutStub<T> timeoutStub, SerialSubscription serial, Observable<? extends T> other, Worker inner) {
            this.serializedSubscriber = serializedSubscriber;
            this.timeoutStub = timeoutStub;
            this.serial = serial;
            this.other = other;
            this.inner = inner;
        }

        public void setProducer(Producer p) {
            this.arbiter.setProducer(p);
        }

        public void onNext(T value) {
            boolean onNextWins = false;
            synchronized (this) {
                long a;
                if (this.terminated) {
                    a = this.actual;
                } else {
                    a = this.actual + 1;
                    this.actual = a;
                    onNextWins = true;
                }
            }
            if (onNextWins) {
                this.serializedSubscriber.onNext(value);
                this.serial.set((Subscription) this.timeoutStub.call(this, Long.valueOf(a), value, this.inner));
            }
        }

        public void onError(Throwable error) {
            boolean onErrorWins = false;
            synchronized (this) {
                if (!this.terminated) {
                    this.terminated = true;
                    onErrorWins = true;
                }
            }
            if (onErrorWins) {
                this.serial.unsubscribe();
                this.serializedSubscriber.onError(error);
            }
        }

        public void onCompleted() {
            boolean onCompletedWins = false;
            synchronized (this) {
                if (!this.terminated) {
                    this.terminated = true;
                    onCompletedWins = true;
                }
            }
            if (onCompletedWins) {
                this.serial.unsubscribe();
                this.serializedSubscriber.onCompleted();
            }
        }

        public void onTimeout(long seqId) {
            long expected = seqId;
            boolean timeoutWins = false;
            synchronized (this) {
                if (expected == this.actual && !this.terminated) {
                    this.terminated = true;
                    timeoutWins = true;
                }
            }
            if (!timeoutWins) {
                return;
            }
            if (this.other == null) {
                this.serializedSubscriber.onError(new TimeoutException());
                return;
            }
            Subscriber<T> second = new C71881();
            this.other.unsafeSubscribe(second);
            this.serial.set(second);
        }
    }

    OperatorTimeoutBase(FirstTimeoutStub<T> firstTimeoutStub, TimeoutStub<T> timeoutStub, Observable<? extends T> other, Scheduler scheduler) {
        this.firstTimeoutStub = firstTimeoutStub;
        this.timeoutStub = timeoutStub;
        this.other = other;
        this.scheduler = scheduler;
    }

    public Subscriber<? super T> call(Subscriber<? super T> subscriber) {
        Worker inner = this.scheduler.createWorker();
        subscriber.add(inner);
        SerializedSubscriber<T> synchronizedSubscriber = new SerializedSubscriber(subscriber);
        SerialSubscription serial = new SerialSubscription();
        synchronizedSubscriber.add(serial);
        TimeoutSubscriber<T> timeoutSubscriber = new TimeoutSubscriber(synchronizedSubscriber, this.timeoutStub, serial, this.other, inner);
        synchronizedSubscriber.add(timeoutSubscriber);
        synchronizedSubscriber.setProducer(timeoutSubscriber.arbiter);
        serial.set((Subscription) this.firstTimeoutStub.call(timeoutSubscriber, Long.valueOf(0), inner));
        return timeoutSubscriber;
    }
}
