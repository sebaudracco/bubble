package com.mopub.common.util;

import java.util.concurrent.TimeUnit;

public class Timer {
    private long mStartTimeNanos;
    private State mState = State.STOPPED;
    private long mStopTimeNanos;

    private enum State {
        STARTED,
        STOPPED
    }

    public void start() {
        this.mStartTimeNanos = System.nanoTime();
        this.mState = State.STARTED;
    }

    public void stop() {
        if (this.mState != State.STARTED) {
            throw new IllegalStateException("EventTimer was not started.");
        }
        this.mState = State.STOPPED;
        this.mStopTimeNanos = System.nanoTime();
    }

    public long getTime() {
        long endTime;
        if (this.mState == State.STARTED) {
            endTime = System.nanoTime();
        } else {
            endTime = this.mStopTimeNanos;
        }
        return TimeUnit.MILLISECONDS.convert(endTime - this.mStartTimeNanos, TimeUnit.NANOSECONDS);
    }
}
