package rx;

import java.util.concurrent.TimeUnit;
import rx.annotations.Experimental;
import rx.functions.Action0;
import rx.functions.Func1;
import rx.internal.schedulers.SchedulerWhen;
import rx.internal.subscriptions.SequentialSubscription;

public abstract class Scheduler {
    static final long CLOCK_DRIFT_TOLERANCE_NANOS = TimeUnit.MINUTES.toNanos(Long.getLong("rx.scheduler.drift-tolerance", 15).longValue());

    public static abstract class Worker implements Subscription {
        public abstract Subscription schedule(Action0 action0);

        public abstract Subscription schedule(Action0 action0, long j, TimeUnit timeUnit);

        public Subscription schedulePeriodically(Action0 action, long initialDelay, long period, TimeUnit unit) {
            final long periodInNanos = unit.toNanos(period);
            final long firstNowNanos = TimeUnit.MILLISECONDS.toNanos(now());
            final long firstStartInNanos = firstNowNanos + unit.toNanos(initialDelay);
            SequentialSubscription first = new SequentialSubscription();
            final SequentialSubscription mas = new SequentialSubscription(first);
            final Action0 action0 = action;
            first.replace(schedule(new Action0() {
                long count;
                long lastNowNanos = firstNowNanos;
                long startInNanos = firstStartInNanos;

                public void call() {
                    action0.call();
                    if (!mas.isUnsubscribed()) {
                        long nextTick;
                        long nowNanos = TimeUnit.MILLISECONDS.toNanos(Worker.this.now());
                        long j;
                        long j2;
                        if (Scheduler.CLOCK_DRIFT_TOLERANCE_NANOS + nowNanos < this.lastNowNanos || nowNanos >= (this.lastNowNanos + periodInNanos) + Scheduler.CLOCK_DRIFT_TOLERANCE_NANOS) {
                            nextTick = nowNanos + periodInNanos;
                            j = periodInNanos;
                            j2 = this.count + 1;
                            this.count = j2;
                            this.startInNanos = nextTick - (j * j2);
                        } else {
                            j = this.startInNanos;
                            j2 = this.count + 1;
                            this.count = j2;
                            nextTick = j + (j2 * periodInNanos);
                        }
                        this.lastNowNanos = nowNanos;
                        mas.replace(Worker.this.schedule(this, nextTick - nowNanos, TimeUnit.NANOSECONDS));
                    }
                }
            }, initialDelay, unit));
            return mas;
        }

        public long now() {
            return System.currentTimeMillis();
        }
    }

    public abstract Worker createWorker();

    public long now() {
        return System.currentTimeMillis();
    }

    @Experimental
    public <S extends Scheduler & Subscription> S when(Func1<Observable<Observable<Completable>>, Completable> combine) {
        return new SchedulerWhen(combine, this);
    }
}
