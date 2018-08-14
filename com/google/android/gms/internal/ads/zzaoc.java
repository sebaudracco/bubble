package com.google.android.gms.internal.ads;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.FutureTask;
import javax.annotation.Nullable;

@zzadh
final class zzaoc<V> extends FutureTask<V> implements zzanz<V> {
    private final zzaoa zzcvt = new zzaoa();

    zzaoc(Runnable runnable, @Nullable V v) {
        super(runnable, v);
    }

    zzaoc(Callable<V> callable) {
        super(callable);
    }

    protected final void done() {
        this.zzcvt.zzsm();
    }

    public final void zza(Runnable runnable, Executor executor) {
        this.zzcvt.zza(runnable, executor);
    }
}
