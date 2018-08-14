package com.google.android.gms.internal.ads;

import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.RunnableFuture;
import javax.annotation.Nullable;

@zzadh
public abstract class zzani extends AbstractExecutorService implements zzaod {
    protected final <T> RunnableFuture<T> newTaskFor(Runnable runnable, T t) {
        return new zzaoc(runnable, t);
    }

    protected final <T> RunnableFuture<T> newTaskFor(Callable<T> callable) {
        return new zzaoc(callable);
    }

    public /* synthetic */ Future submit(Runnable runnable) {
        return zze(runnable);
    }

    public /* synthetic */ Future submit(Runnable runnable, @Nullable Object obj) {
        return (zzanz) super.submit(runnable, obj);
    }

    public /* synthetic */ Future submit(Callable callable) {
        return zza(callable);
    }

    public final <T> zzanz<T> zza(Callable<T> callable) throws RejectedExecutionException {
        return (zzanz) super.submit(callable);
    }

    public final zzanz<?> zze(Runnable runnable) throws RejectedExecutionException {
        return (zzanz) super.submit(runnable);
    }
}
