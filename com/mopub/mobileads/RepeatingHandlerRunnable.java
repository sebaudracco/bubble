package com.mopub.mobileads;

import android.os.Handler;
import android.support.annotation.NonNull;
import com.mopub.common.Preconditions;
import com.mopub.common.VisibleForTesting;

public abstract class RepeatingHandlerRunnable implements Runnable {
    @NonNull
    protected final Handler mHandler;
    private volatile boolean mIsRunning;
    protected volatile long mUpdateIntervalMillis;

    public abstract void doWork();

    public RepeatingHandlerRunnable(@NonNull Handler handler) {
        Preconditions.checkNotNull(handler);
        this.mHandler = handler;
    }

    public void run() {
        if (this.mIsRunning) {
            doWork();
            this.mHandler.postDelayed(this, this.mUpdateIntervalMillis);
        }
    }

    public void startRepeating(long intervalMillis) {
        Preconditions.checkArgument(intervalMillis > 0, "intervalMillis must be greater than 0. Saw: %d", new Object[]{Long.valueOf(intervalMillis)});
        this.mUpdateIntervalMillis = intervalMillis;
        if (!this.mIsRunning) {
            this.mIsRunning = true;
            this.mHandler.post(this);
        }
    }

    public void stop() {
        this.mIsRunning = false;
    }

    @Deprecated
    @VisibleForTesting
    public boolean isRunning() {
        return this.mIsRunning;
    }
}
