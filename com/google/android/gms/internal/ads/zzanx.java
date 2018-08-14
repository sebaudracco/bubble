package com.google.android.gms.internal.ads;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

@zzadh
final class zzanx<T> implements zzanz<T> {
    private final Throwable cause;
    private final zzaoa zzcvt = new zzaoa();

    zzanx(Throwable th) {
        this.cause = th;
        this.zzcvt.zzsm();
    }

    public final boolean cancel(boolean z) {
        return false;
    }

    public final T get() throws ExecutionException {
        throw new ExecutionException(this.cause);
    }

    public final T get(long j, TimeUnit timeUnit) throws ExecutionException {
        throw new ExecutionException(this.cause);
    }

    public final boolean isCancelled() {
        return false;
    }

    public final boolean isDone() {
        return true;
    }

    public final void zza(Runnable runnable, Executor executor) {
        this.zzcvt.zza(runnable, executor);
    }
}
