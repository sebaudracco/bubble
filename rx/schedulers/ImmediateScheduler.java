package rx.schedulers;

import rx.Scheduler;
import rx.Scheduler.Worker;

@Deprecated
public final class ImmediateScheduler extends Scheduler {
    private ImmediateScheduler() {
        throw new IllegalStateException("No instances!");
    }

    public Worker createWorker() {
        return null;
    }
}
