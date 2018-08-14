package rx.internal.schedulers;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import rx.Scheduler;
import rx.Scheduler.Worker;
import rx.Subscription;
import rx.functions.Action0;
import rx.plugins.RxJavaHooks;
import rx.subscriptions.CompositeSubscription;
import rx.subscriptions.MultipleAssignmentSubscription;
import rx.subscriptions.Subscriptions;

public final class ExecutorScheduler extends Scheduler {
    final Executor executor;

    static final class ExecutorSchedulerWorker extends Worker implements Runnable {
        final Executor executor;
        final ConcurrentLinkedQueue<ScheduledAction> queue = new ConcurrentLinkedQueue();
        final ScheduledExecutorService service = GenericScheduledExecutorService.getInstance();
        final CompositeSubscription tasks = new CompositeSubscription();
        final AtomicInteger wip = new AtomicInteger();

        public ExecutorSchedulerWorker(Executor executor) {
            this.executor = executor;
        }

        public Subscription schedule(Action0 action) {
            if (isUnsubscribed()) {
                return Subscriptions.unsubscribed();
            }
            Subscription ea = new ScheduledAction(RxJavaHooks.onScheduledAction(action), this.tasks);
            this.tasks.add(ea);
            this.queue.offer(ea);
            if (this.wip.getAndIncrement() != 0) {
                return ea;
            }
            try {
                this.executor.execute(this);
                return ea;
            } catch (RejectedExecutionException t) {
                this.tasks.remove(ea);
                this.wip.decrementAndGet();
                RxJavaHooks.onError(t);
                throw t;
            }
        }

        public void run() {
            while (!this.tasks.isUnsubscribed()) {
                ScheduledAction sa = (ScheduledAction) this.queue.poll();
                if (sa != null) {
                    if (!sa.isUnsubscribed()) {
                        if (this.tasks.isUnsubscribed()) {
                            this.queue.clear();
                            return;
                        }
                        sa.run();
                    }
                    if (this.wip.decrementAndGet() == 0) {
                        return;
                    }
                }
                return;
            }
            this.queue.clear();
        }

        public Subscription schedule(Action0 action, long delayTime, TimeUnit unit) {
            if (delayTime <= 0) {
                return schedule(action);
            }
            if (isUnsubscribed()) {
                return Subscriptions.unsubscribed();
            }
            final Action0 decorated = RxJavaHooks.onScheduledAction(action);
            MultipleAssignmentSubscription first = new MultipleAssignmentSubscription();
            final MultipleAssignmentSubscription mas = new MultipleAssignmentSubscription();
            mas.set(first);
            this.tasks.add(mas);
            final Subscription removeMas = Subscriptions.create(new Action0() {
                public void call() {
                    ExecutorSchedulerWorker.this.tasks.remove(mas);
                }
            });
            ScheduledAction ea = new ScheduledAction(new Action0() {
                public void call() {
                    if (!mas.isUnsubscribed()) {
                        Subscription s2 = ExecutorSchedulerWorker.this.schedule(decorated);
                        mas.set(s2);
                        if (s2.getClass() == ScheduledAction.class) {
                            ((ScheduledAction) s2).add(removeMas);
                        }
                    }
                }
            });
            first.set(ea);
            try {
                ea.add(this.service.schedule(ea, delayTime, unit));
                return removeMas;
            } catch (RejectedExecutionException t) {
                RxJavaHooks.onError(t);
                throw t;
            }
        }

        public boolean isUnsubscribed() {
            return this.tasks.isUnsubscribed();
        }

        public void unsubscribe() {
            this.tasks.unsubscribe();
            this.queue.clear();
        }
    }

    public ExecutorScheduler(Executor executor) {
        this.executor = executor;
    }

    public Worker createWorker() {
        return new ExecutorSchedulerWorker(this.executor);
    }
}
