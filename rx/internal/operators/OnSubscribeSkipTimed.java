package rx.internal.operators;

import java.util.concurrent.TimeUnit;
import rx.Observable;
import rx.Observable$OnSubscribe;
import rx.Scheduler;
import rx.Scheduler.Worker;
import rx.Subscriber;
import rx.functions.Action0;

public final class OnSubscribeSkipTimed<T> implements Observable$OnSubscribe<T> {
    final Scheduler scheduler;
    final Observable<T> source;
    final long time;
    final TimeUnit unit;

    static final class SkipTimedSubscriber<T> extends Subscriber<T> implements Action0 {
        final Subscriber<? super T> child;
        volatile boolean gate;

        SkipTimedSubscriber(Subscriber<? super T> child) {
            this.child = child;
        }

        public void call() {
            this.gate = true;
        }

        public void onNext(T t) {
            if (this.gate) {
                this.child.onNext(t);
            }
        }

        public void onError(Throwable e) {
            try {
                this.child.onError(e);
            } finally {
                unsubscribe();
            }
        }

        public void onCompleted() {
            try {
                this.child.onCompleted();
            } finally {
                unsubscribe();
            }
        }
    }

    public OnSubscribeSkipTimed(Observable<T> source, long time, TimeUnit unit, Scheduler scheduler) {
        this.source = source;
        this.time = time;
        this.unit = unit;
        this.scheduler = scheduler;
    }

    public void call(Subscriber<? super T> child) {
        Worker worker = this.scheduler.createWorker();
        SkipTimedSubscriber<T> subscriber = new SkipTimedSubscriber(child);
        subscriber.add(worker);
        child.add(subscriber);
        worker.schedule(subscriber, this.time, this.unit);
        this.source.unsafeSubscribe(subscriber);
    }
}
