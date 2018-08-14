package rx.internal.schedulers;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import rx.Completable;
import rx.Completable.OnSubscribe;
import rx.CompletableSubscriber;
import rx.Observable;
import rx.Observer;
import rx.Scheduler;
import rx.Scheduler.Worker;
import rx.Subscription;
import rx.annotations.Experimental;
import rx.functions.Action0;
import rx.functions.Func1;
import rx.internal.operators.BufferUntilSubscriber;
import rx.observers.SerializedObserver;
import rx.subjects.PublishSubject;
import rx.subscriptions.Subscriptions;

@Experimental
public class SchedulerWhen extends Scheduler implements Subscription {
    static final Subscription SUBSCRIBED = new C72303();
    static final Subscription UNSUBSCRIBED = Subscriptions.unsubscribed();
    private final Scheduler actualScheduler;
    private final Subscription subscription;
    private final Observer<Observable<Completable>> workerObserver;

    static class C72303 implements Subscription {
        C72303() {
        }

        public void unsubscribe() {
        }

        public boolean isUnsubscribed() {
            return false;
        }
    }

    private static abstract class ScheduledAction extends AtomicReference<Subscription> implements Subscription {
        protected abstract Subscription callActual(Worker worker);

        public ScheduledAction() {
            super(SchedulerWhen.SUBSCRIBED);
        }

        private void call(Worker actualWorker) {
            Subscription oldState = (Subscription) get();
            if (oldState != SchedulerWhen.UNSUBSCRIBED && oldState == SchedulerWhen.SUBSCRIBED) {
                Subscription newState = callActual(actualWorker);
                if (!compareAndSet(SchedulerWhen.SUBSCRIBED, newState)) {
                    newState.unsubscribe();
                }
            }
        }

        public boolean isUnsubscribed() {
            return ((Subscription) get()).isUnsubscribed();
        }

        public void unsubscribe() {
            Subscription newState = SchedulerWhen.UNSUBSCRIBED;
            Subscription oldState;
            do {
                oldState = (Subscription) get();
                if (oldState == SchedulerWhen.UNSUBSCRIBED) {
                    return;
                }
            } while (!compareAndSet(oldState, newState));
            if (oldState != SchedulerWhen.SUBSCRIBED) {
                oldState.unsubscribe();
            }
        }
    }

    private static class DelayedAction extends ScheduledAction {
        private final Action0 action;
        private final long delayTime;
        private final TimeUnit unit;

        public DelayedAction(Action0 action, long delayTime, TimeUnit unit) {
            this.action = action;
            this.delayTime = delayTime;
            this.unit = unit;
        }

        protected Subscription callActual(Worker actualWorker) {
            return actualWorker.schedule(this.action, this.delayTime, this.unit);
        }
    }

    private static class ImmediateAction extends ScheduledAction {
        private final Action0 action;

        public ImmediateAction(Action0 action) {
            this.action = action;
        }

        protected Subscription callActual(Worker actualWorker) {
            return actualWorker.schedule(this.action);
        }
    }

    public SchedulerWhen(Func1<Observable<Observable<Completable>>, Completable> combine, Scheduler actualScheduler) {
        this.actualScheduler = actualScheduler;
        PublishSubject<Observable<Completable>> workerSubject = PublishSubject.create();
        this.workerObserver = new SerializedObserver(workerSubject);
        this.subscription = ((Completable) combine.call(workerSubject.onBackpressureBuffer())).subscribe();
    }

    public void unsubscribe() {
        this.subscription.unsubscribe();
    }

    public boolean isUnsubscribed() {
        return this.subscription.isUnsubscribed();
    }

    public Worker createWorker() {
        final Worker actualWorker = this.actualScheduler.createWorker();
        BufferUntilSubscriber<ScheduledAction> actionSubject = BufferUntilSubscriber.create();
        final Observer<ScheduledAction> actionObserver = new SerializedObserver(actionSubject);
        Observable<Completable> actions = actionSubject.map(new Func1<ScheduledAction, Completable>() {
            public Completable call(final ScheduledAction action) {
                return Completable.create(new OnSubscribe() {
                    public void call(CompletableSubscriber actionCompletable) {
                        actionCompletable.onSubscribe(action);
                        action.call(actualWorker);
                        actionCompletable.onCompleted();
                    }
                });
            }
        });
        Worker worker = new Worker() {
            private final AtomicBoolean unsubscribed = new AtomicBoolean();

            public void unsubscribe() {
                if (this.unsubscribed.compareAndSet(false, true)) {
                    actualWorker.unsubscribe();
                    actionObserver.onCompleted();
                }
            }

            public boolean isUnsubscribed() {
                return this.unsubscribed.get();
            }

            public Subscription schedule(Action0 action, long delayTime, TimeUnit unit) {
                DelayedAction delayedAction = new DelayedAction(action, delayTime, unit);
                actionObserver.onNext(delayedAction);
                return delayedAction;
            }

            public Subscription schedule(Action0 action) {
                ImmediateAction immediateAction = new ImmediateAction(action);
                actionObserver.onNext(immediateAction);
                return immediateAction;
            }
        };
        this.workerObserver.onNext(actions);
        return worker;
    }
}
