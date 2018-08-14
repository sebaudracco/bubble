package com.google.android.gms.internal.ads;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@zzadh
public final class zzaki {
    public static final zzaod zzcrj = zzaoe.zza(new ThreadPoolExecutor(2, Integer.MAX_VALUE, 10, TimeUnit.SECONDS, new SynchronousQueue(), zzct("Default")));
    private static final zzaod zzcrk;

    static {
        Executor threadPoolExecutor = new ThreadPoolExecutor(5, 5, 10, TimeUnit.SECONDS, new LinkedBlockingQueue(), zzct("Loader"));
        threadPoolExecutor.allowCoreThreadTimeOut(true);
        zzcrk = zzaoe.zza(threadPoolExecutor);
    }

    public static <T> zzanz<T> zza(Callable<T> callable) {
        return zzcrj.zza(callable);
    }

    public static zzanz<?> zzb(Runnable runnable) {
        return zzcrj.zze(runnable);
    }

    public static zzanz<?> zzc(Runnable runnable) {
        return zzcrk.zze(runnable);
    }

    private static ThreadFactory zzct(String str) {
        return new zzakj(str);
    }
}
