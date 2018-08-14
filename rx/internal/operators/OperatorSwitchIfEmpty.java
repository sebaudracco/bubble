package rx.internal.operators;

import rx.Observable;
import rx.Observable$Operator;
import rx.Producer;
import rx.Subscriber;
import rx.internal.producers.ProducerArbiter;
import rx.subscriptions.SerialSubscription;

public final class OperatorSwitchIfEmpty<T> implements Observable$Operator<T, T> {
    private final Observable<? extends T> alternate;

    static final class AlternateSubscriber<T> extends Subscriber<T> {
        private final ProducerArbiter arbiter;
        private final Subscriber<? super T> child;

        AlternateSubscriber(Subscriber<? super T> child, ProducerArbiter arbiter) {
            this.child = child;
            this.arbiter = arbiter;
        }

        public void setProducer(Producer producer) {
            this.arbiter.setProducer(producer);
        }

        public void onCompleted() {
            this.child.onCompleted();
        }

        public void onError(Throwable e) {
            this.child.onError(e);
        }

        public void onNext(T t) {
            this.child.onNext(t);
            this.arbiter.produced(1);
        }
    }

    static final class ParentSubscriber<T> extends Subscriber<T> {
        private final Observable<? extends T> alternate;
        private final ProducerArbiter arbiter;
        private final Subscriber<? super T> child;
        private boolean empty = true;
        private final SerialSubscription serial;

        ParentSubscriber(Subscriber<? super T> child, SerialSubscription serial, ProducerArbiter arbiter, Observable<? extends T> alternate) {
            this.child = child;
            this.serial = serial;
            this.arbiter = arbiter;
            this.alternate = alternate;
        }

        public void setProducer(Producer producer) {
            this.arbiter.setProducer(producer);
        }

        public void onCompleted() {
            if (!this.empty) {
                this.child.onCompleted();
            } else if (!this.child.isUnsubscribed()) {
                subscribeToAlternate();
            }
        }

        private void subscribeToAlternate() {
            AlternateSubscriber<T> as = new AlternateSubscriber(this.child, this.arbiter);
            this.serial.set(as);
            this.alternate.unsafeSubscribe(as);
        }

        public void onError(Throwable e) {
            this.child.onError(e);
        }

        public void onNext(T t) {
            this.empty = false;
            this.child.onNext(t);
            this.arbiter.produced(1);
        }
    }

    public OperatorSwitchIfEmpty(Observable<? extends T> alternate) {
        this.alternate = alternate;
    }

    public Subscriber<? super T> call(Subscriber<? super T> child) {
        SerialSubscription serial = new SerialSubscription();
        ProducerArbiter arbiter = new ProducerArbiter();
        ParentSubscriber<T> parent = new ParentSubscriber(child, serial, arbiter, this.alternate);
        serial.set(parent);
        child.add(serial);
        child.setProducer(arbiter);
        return parent;
    }
}
