package rx.internal.operators;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import rx.Observable;
import rx.Observable$OnSubscribe;
import rx.Producer;
import rx.Subscriber;
import rx.Subscription;
import rx.plugins.RxJavaHooks;

public final class OnSubscribeDetach<T> implements Observable$OnSubscribe<T> {
    final Observable<T> source;

    static final class DetachProducer<T> implements Producer, Subscription {
        final DetachSubscriber<T> parent;

        public DetachProducer(DetachSubscriber<T> parent) {
            this.parent = parent;
        }

        public void request(long n) {
            this.parent.innerRequest(n);
        }

        public boolean isUnsubscribed() {
            return this.parent.isUnsubscribed();
        }

        public void unsubscribe() {
            this.parent.innerUnsubscribe();
        }
    }

    static final class DetachSubscriber<T> extends Subscriber<T> {
        final AtomicReference<Subscriber<? super T>> actual;
        final AtomicReference<Producer> producer = new AtomicReference();
        final AtomicLong requested = new AtomicLong();

        public DetachSubscriber(Subscriber<? super T> actual) {
            this.actual = new AtomicReference(actual);
        }

        public void onNext(T t) {
            Subscriber<? super T> a = (Subscriber) this.actual.get();
            if (a != null) {
                a.onNext(t);
            }
        }

        public void onError(Throwable e) {
            this.producer.lazySet(TerminatedProducer.INSTANCE);
            Subscriber<? super T> a = (Subscriber) this.actual.getAndSet(null);
            if (a != null) {
                a.onError(e);
            } else {
                RxJavaHooks.onError(e);
            }
        }

        public void onCompleted() {
            this.producer.lazySet(TerminatedProducer.INSTANCE);
            Subscriber<? super T> a = (Subscriber) this.actual.getAndSet(null);
            if (a != null) {
                a.onCompleted();
            }
        }

        void innerRequest(long n) {
            if (n < 0) {
                throw new IllegalArgumentException("n >= 0 required but it was " + n);
            }
            Producer p = (Producer) this.producer.get();
            if (p != null) {
                p.request(n);
                return;
            }
            BackpressureUtils.getAndAddRequest(this.requested, n);
            p = (Producer) this.producer.get();
            if (p != null && p != TerminatedProducer.INSTANCE) {
                p.request(this.requested.getAndSet(0));
            }
        }

        public void setProducer(Producer p) {
            if (this.producer.compareAndSet(null, p)) {
                p.request(this.requested.getAndSet(0));
            } else if (this.producer.get() != TerminatedProducer.INSTANCE) {
                throw new IllegalStateException("Producer already set!");
            }
        }

        void innerUnsubscribe() {
            this.producer.lazySet(TerminatedProducer.INSTANCE);
            this.actual.lazySet(null);
            unsubscribe();
        }
    }

    enum TerminatedProducer implements Producer {
        INSTANCE;

        public void request(long n) {
        }
    }

    public OnSubscribeDetach(Observable<T> source) {
        this.source = source;
    }

    public void call(Subscriber<? super T> t) {
        DetachSubscriber<T> parent = new DetachSubscriber(t);
        DetachProducer<T> producer = new DetachProducer(parent);
        t.add(producer);
        t.setProducer(producer);
        this.source.unsafeSubscribe(parent);
    }
}
