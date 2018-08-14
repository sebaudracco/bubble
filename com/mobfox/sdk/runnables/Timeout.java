package com.mobfox.sdk.runnables;

import android.content.Context;
import java.util.concurrent.Callable;

public class Timeout extends MobFoxRunnable {
    boolean cancelled = false;
    boolean timeout = false;
    Callable timeoutCB;

    public boolean isTimeout() {
        boolean z;
        synchronized (this) {
            z = this.timeout;
        }
        return z;
    }

    public Timeout(Context context, Callable timeoutCB) {
        super(context);
        this.timeoutCB = timeoutCB;
    }

    public void mobFoxRun() {
        synchronized (this) {
            this.timeout = true;
            try {
                this.timeoutCB.call();
            } catch (Exception e) {
            }
        }
    }

    protected boolean condition() {
        boolean z;
        synchronized (this) {
            z = !this.cancelled;
        }
        return z;
    }

    public void reset() {
        this.timeout = false;
        this.cancelled = false;
    }

    public void cancel() {
        synchronized (this) {
            this.cancelled = true;
        }
    }
}
