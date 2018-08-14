package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.zzbv;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@zzadh
public final class zzano {
    public static <V> zzanz<V> zza(zzanz<V> com_google_android_gms_internal_ads_zzanz_V, long j, TimeUnit timeUnit, ScheduledExecutorService scheduledExecutorService) {
        zzaoj com_google_android_gms_internal_ads_zzaoj = new zzaoj();
        zza((zzanz) com_google_android_gms_internal_ads_zzaoj, (Future) com_google_android_gms_internal_ads_zzanz_V);
        Future schedule = scheduledExecutorService.schedule(new zzans(com_google_android_gms_internal_ads_zzaoj), j, timeUnit);
        zza((zzanz) com_google_android_gms_internal_ads_zzanz_V, com_google_android_gms_internal_ads_zzaoj);
        com_google_android_gms_internal_ads_zzaoj.zza(new zzant(schedule), zzaoe.zzcvz);
        return com_google_android_gms_internal_ads_zzaoj;
    }

    public static <A, B> zzanz<B> zza(zzanz<A> com_google_android_gms_internal_ads_zzanz_A, zzanj<? super A, ? extends B> com_google_android_gms_internal_ads_zzanj__super_A___extends_B, Executor executor) {
        zzanz com_google_android_gms_internal_ads_zzaoj = new zzaoj();
        com_google_android_gms_internal_ads_zzanz_A.zza(new zzanr(com_google_android_gms_internal_ads_zzaoj, com_google_android_gms_internal_ads_zzanj__super_A___extends_B, com_google_android_gms_internal_ads_zzanz_A), executor);
        zza(com_google_android_gms_internal_ads_zzaoj, (Future) com_google_android_gms_internal_ads_zzanz_A);
        return com_google_android_gms_internal_ads_zzaoj;
    }

    public static <A, B> zzanz<B> zza(zzanz<A> com_google_android_gms_internal_ads_zzanz_A, zzank<A, B> com_google_android_gms_internal_ads_zzank_A__B, Executor executor) {
        zzanz com_google_android_gms_internal_ads_zzaoj = new zzaoj();
        com_google_android_gms_internal_ads_zzanz_A.zza(new zzanq(com_google_android_gms_internal_ads_zzaoj, com_google_android_gms_internal_ads_zzank_A__B, com_google_android_gms_internal_ads_zzanz_A), executor);
        zza(com_google_android_gms_internal_ads_zzaoj, (Future) com_google_android_gms_internal_ads_zzanz_A);
        return com_google_android_gms_internal_ads_zzaoj;
    }

    public static <V, X extends Throwable> zzanz<V> zza(zzanz<? extends V> com_google_android_gms_internal_ads_zzanz__extends_V, Class<X> cls, zzanj<? super X, ? extends V> com_google_android_gms_internal_ads_zzanj__super_X___extends_V, Executor executor) {
        zzanz com_google_android_gms_internal_ads_zzaoj = new zzaoj();
        zza(com_google_android_gms_internal_ads_zzaoj, (Future) com_google_android_gms_internal_ads_zzanz__extends_V);
        com_google_android_gms_internal_ads_zzanz__extends_V.zza(new zzanu(com_google_android_gms_internal_ads_zzaoj, com_google_android_gms_internal_ads_zzanz__extends_V, cls, com_google_android_gms_internal_ads_zzanj__super_X___extends_V, executor), zzaoe.zzcvz);
        return com_google_android_gms_internal_ads_zzaoj;
    }

    public static <T> T zza(Future<T> future, T t) {
        try {
            t = future.get(((Long) zzkb.zzik().zzd(zznk.zzbam)).longValue(), TimeUnit.MILLISECONDS);
        } catch (Throwable e) {
            future.cancel(true);
            zzane.zzc("InterruptedException caught while resolving future.", e);
            Thread.currentThread().interrupt();
            zzbv.zzeo().zzb(e, "Futures.resolveFuture");
        } catch (Throwable e2) {
            future.cancel(true);
            zzane.zzb("Error waiting for future.", e2);
            zzbv.zzeo().zzb(e2, "Futures.resolveFuture");
        }
        return t;
    }

    public static <T> T zza(Future<T> future, T t, long j, TimeUnit timeUnit) {
        try {
            t = future.get(j, timeUnit);
        } catch (Throwable e) {
            future.cancel(true);
            zzane.zzc("InterruptedException caught while resolving future.", e);
            Thread.currentThread().interrupt();
            zzbv.zzeo().zza(e, "Futures.resolveFuture");
        } catch (Throwable e2) {
            future.cancel(true);
            zzane.zzb("Error waiting for future.", e2);
            zzbv.zzeo().zza(e2, "Futures.resolveFuture");
        }
        return t;
    }

    public static <V> void zza(zzanz<V> com_google_android_gms_internal_ads_zzanz_V, zzanl<V> com_google_android_gms_internal_ads_zzanl_V, Executor executor) {
        com_google_android_gms_internal_ads_zzanz_V.zza(new zzanp(com_google_android_gms_internal_ads_zzanl_V, com_google_android_gms_internal_ads_zzanz_V), executor);
    }

    private static <V> void zza(zzanz<? extends V> com_google_android_gms_internal_ads_zzanz__extends_V, zzaoj<V> com_google_android_gms_internal_ads_zzaoj_V) {
        zza((zzanz) com_google_android_gms_internal_ads_zzaoj_V, (Future) com_google_android_gms_internal_ads_zzanz__extends_V);
        com_google_android_gms_internal_ads_zzanz__extends_V.zza(new zzanv(com_google_android_gms_internal_ads_zzaoj_V, com_google_android_gms_internal_ads_zzanz__extends_V), zzaoe.zzcvz);
    }

    private static <A, B> void zza(zzanz<A> com_google_android_gms_internal_ads_zzanz_A, Future<B> future) {
        com_google_android_gms_internal_ads_zzanz_A.zza(new zzanw(com_google_android_gms_internal_ads_zzanz_A, future), zzaoe.zzcvz);
    }

    static final /* synthetic */ void zza(zzaoj com_google_android_gms_internal_ads_zzaoj, zzanj com_google_android_gms_internal_ads_zzanj, zzanz com_google_android_gms_internal_ads_zzanz) {
        if (!com_google_android_gms_internal_ads_zzaoj.isCancelled()) {
            try {
                zza(com_google_android_gms_internal_ads_zzanj.zzc(com_google_android_gms_internal_ads_zzanz.get()), com_google_android_gms_internal_ads_zzaoj);
            } catch (CancellationException e) {
                com_google_android_gms_internal_ads_zzaoj.cancel(true);
            } catch (ExecutionException e2) {
                com_google_android_gms_internal_ads_zzaoj.setException(e2.getCause());
            } catch (Throwable e3) {
                Thread.currentThread().interrupt();
                com_google_android_gms_internal_ads_zzaoj.setException(e3);
            } catch (Throwable e32) {
                com_google_android_gms_internal_ads_zzaoj.setException(e32);
            }
        }
    }

    static final /* synthetic */ void zza(zzaoj com_google_android_gms_internal_ads_zzaoj, zzanz com_google_android_gms_internal_ads_zzanz, Class cls, zzanj com_google_android_gms_internal_ads_zzanj, Executor executor) {
        Throwable cause;
        try {
            com_google_android_gms_internal_ads_zzaoj.set(com_google_android_gms_internal_ads_zzanz.get());
            return;
        } catch (ExecutionException e) {
            cause = e.getCause();
        } catch (InterruptedException e2) {
            cause = e2;
            Thread.currentThread().interrupt();
        } catch (Exception e3) {
            cause = e3;
        }
        if (cls.isInstance(cause)) {
            zza(zza(zzi(cause), com_google_android_gms_internal_ads_zzanj, executor), com_google_android_gms_internal_ads_zzaoj);
        } else {
            com_google_android_gms_internal_ads_zzaoj.setException(cause);
        }
    }

    public static <T> zzanx<T> zzd(Throwable th) {
        return new zzanx(th);
    }

    public static <T> zzany<T> zzi(T t) {
        return new zzany(t);
    }
}
