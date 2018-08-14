package com.google.android.gms.internal.ads;

import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

@zzadh
final class zzany<T> implements zzanz<T> {
    private final T value;
    private final zzaoa zzcvt = new zzaoa();

    zzany(T t) {
        this.value = t;
        this.zzcvt.zzsm();
    }

    public final boolean cancel(boolean z) {
        return false;
    }

    public final T get() {
        return this.value;
    }

    public final T get(long j, TimeUnit timeUnit) {
        return this.value;
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
