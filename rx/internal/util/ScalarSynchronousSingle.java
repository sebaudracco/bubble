package rx.internal.util;

import rx.Scheduler;
import rx.Scheduler.Worker;
import rx.Single;
import rx.Single.OnSubscribe;
import rx.SingleSubscriber;
import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Func1;
import rx.internal.schedulers.EventLoopsScheduler;

public final class ScalarSynchronousSingle<T> extends Single<T> {
    final T value;

    class C72421 implements OnSubscribe<T> {
        final /* synthetic */ Object val$t;

        C72421(Object obj) {
            this.val$t = obj;
        }

        public void call(SingleSubscriber<? super T> te) {
            te.onSuccess(this.val$t);
        }
    }

    static final class DirectScheduledEmission<T> implements OnSubscribe<T> {
        private final EventLoopsScheduler es;
        private final T value;

        DirectScheduledEmission(EventLoopsScheduler es, T value) {
            this.es = es;
            this.value = value;
        }

        public void call(SingleSubscriber<? super T> singleSubscriber) {
            singleSubscriber.add(this.es.scheduleDirect(new ScalarSynchronousSingleAction(singleSubscriber, this.value)));
        }
    }

    static final class NormalScheduledEmission<T> implements OnSubscribe<T> {
        private final Scheduler scheduler;
        private final T value;

        NormalScheduledEmission(Scheduler scheduler, T value) {
            this.scheduler = scheduler;
            this.value = value;
        }

        public void call(SingleSubscriber<? super T> singleSubscriber) {
            Worker worker = this.scheduler.createWorker();
            singleSubscriber.add(worker);
            worker.schedule(new ScalarSynchronousSingleAction(singleSubscriber, this.value));
        }
    }

    static final class ScalarSynchronousSingleAction<T> implements Action0 {
        private final SingleSubscriber<? super T> subscriber;
        private final T value;

        ScalarSynchronousSingleAction(SingleSubscriber<? super T> subscriber, T value) {
            this.subscriber = subscriber;
            this.value = value;
        }

        public void call() {
            try {
                this.subscriber.onSuccess(this.value);
            } catch (Throwable t) {
                this.subscriber.onError(t);
            }
        }
    }

    public static <T> ScalarSynchronousSingle<T> create(T t) {
        return new ScalarSynchronousSingle(t);
    }

    protected ScalarSynchronousSingle(T t) {
        super(new C72421(t));
        this.value = t;
    }

    public T get() {
        return this.value;
    }

    public Single<T> scalarScheduleOn(Scheduler scheduler) {
        if (scheduler instanceof EventLoopsScheduler) {
            return Single.create(new DirectScheduledEmission((EventLoopsScheduler) scheduler, this.value));
        }
        return Single.create(new NormalScheduledEmission(scheduler, this.value));
    }

    public <R> Single<R> scalarFlatMap(final Func1<? super T, ? extends Single<? extends R>> func) {
        return Single.create(new OnSubscribe<R>() {
            public void call(final SingleSubscriber<? super R> child) {
                Single<? extends R> o = (Single) func.call(ScalarSynchronousSingle.this.value);
                if (o instanceof ScalarSynchronousSingle) {
                    child.onSuccess(((ScalarSynchronousSingle) o).value);
                    return;
                }
                Subscriber<R> subscriber = new Subscriber<R>() {
                    public void onCompleted() {
                    }

                    public void onError(Throwable e) {
                        child.onError(e);
                    }

                    public void onNext(R r) {
                        child.onSuccess(r);
                    }
                };
                child.add(subscriber);
                o.unsafeSubscribe(subscriber);
            }
        });
    }
}
