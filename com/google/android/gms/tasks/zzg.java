package com.google.android.gms.tasks;

import android.support.annotation.NonNull;
import java.util.concurrent.Executor;
import javax.annotation.concurrent.GuardedBy;

final class zzg<TResult> implements zzq<TResult> {
    private final Object mLock = new Object();
    private final Executor zzafk;
    @GuardedBy("mLock")
    private OnCanceledListener zzafq;

    public zzg(@NonNull Executor executor, @NonNull OnCanceledListener onCanceledListener) {
        this.zzafk = executor;
        this.zzafq = onCanceledListener;
    }

    public final void cancel() {
        synchronized (this.mLock) {
            this.zzafq = null;
        }
    }

    public final void onComplete(@NonNull Task task) {
        if (task.isCanceled()) {
            synchronized (this.mLock) {
                if (this.zzafq == null) {
                    return;
                }
                this.zzafk.execute(new zzh(this));
            }
        }
    }
}
