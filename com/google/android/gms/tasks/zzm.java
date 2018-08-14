package com.google.android.gms.tasks;

import android.support.annotation.NonNull;
import java.util.concurrent.Executor;
import javax.annotation.concurrent.GuardedBy;

final class zzm<TResult> implements zzq<TResult> {
    private final Object mLock = new Object();
    private final Executor zzafk;
    @GuardedBy("mLock")
    private OnSuccessListener<? super TResult> zzafw;

    public zzm(@NonNull Executor executor, @NonNull OnSuccessListener<? super TResult> onSuccessListener) {
        this.zzafk = executor;
        this.zzafw = onSuccessListener;
    }

    public final void cancel() {
        synchronized (this.mLock) {
            this.zzafw = null;
        }
    }

    public final void onComplete(@NonNull Task<TResult> task) {
        if (task.isSuccessful()) {
            synchronized (this.mLock) {
                if (this.zzafw == null) {
                    return;
                }
                this.zzafk.execute(new zzn(this, task));
            }
        }
    }
}
