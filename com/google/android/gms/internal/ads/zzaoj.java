package com.google.android.gms.internal.ads;

import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.zzbv;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.GuardedBy;

@zzadh
@ParametersAreNonnullByDefault
public class zzaoj<T> implements zzanz<T> {
    private final Object mLock = new Object();
    @GuardedBy("mLock")
    private T mValue;
    @GuardedBy("mLock")
    private boolean zzbuf;
    @GuardedBy("mLock")
    private Throwable zzcwf;
    @GuardedBy("mLock")
    private boolean zzcwg;
    private final zzaoa zzcwh = new zzaoa();

    @GuardedBy("mLock")
    private final boolean zzso() {
        return this.zzcwf != null || this.zzcwg;
    }

    public boolean cancel(boolean z) {
        if (!z) {
            return false;
        }
        synchronized (this.mLock) {
            if (zzso()) {
                return false;
            }
            this.zzbuf = true;
            this.zzcwg = true;
            this.mLock.notifyAll();
            this.zzcwh.zzsm();
            return true;
        }
    }

    public T get() throws CancellationException, ExecutionException, InterruptedException {
        T t;
        synchronized (this.mLock) {
            if (!zzso()) {
                try {
                    this.mLock.wait();
                } catch (InterruptedException e) {
                    throw e;
                }
            }
            if (this.zzcwf != null) {
                throw new ExecutionException(this.zzcwf);
            } else if (this.zzbuf) {
                throw new CancellationException("SettableFuture was cancelled.");
            } else {
                t = this.mValue;
            }
        }
        return t;
    }

    public T get(long j, TimeUnit timeUnit) throws CancellationException, ExecutionException, InterruptedException, TimeoutException {
        T t;
        synchronized (this.mLock) {
            if (!zzso()) {
                try {
                    long toMillis = timeUnit.toMillis(j);
                    if (toMillis != 0) {
                        this.mLock.wait(toMillis);
                    }
                } catch (InterruptedException e) {
                    throw e;
                }
            }
            if (this.zzcwf != null) {
                throw new ExecutionException(this.zzcwf);
            } else if (!this.zzcwg) {
                throw new TimeoutException("SettableFuture timed out.");
            } else if (this.zzbuf) {
                throw new CancellationException("SettableFuture was cancelled.");
            } else {
                t = this.mValue;
            }
        }
        return t;
    }

    public boolean isCancelled() {
        boolean z;
        synchronized (this.mLock) {
            z = this.zzbuf;
        }
        return z;
    }

    public boolean isDone() {
        boolean zzso;
        synchronized (this.mLock) {
            zzso = zzso();
        }
        return zzso;
    }

    public final void set(@Nullable T t) {
        synchronized (this.mLock) {
            if (this.zzbuf) {
            } else if (zzso()) {
                zzbv.zzeo().zzb(new IllegalStateException("Provided SettableFuture with multiple values."), "SettableFuture.set");
            } else {
                this.zzcwg = true;
                this.mValue = t;
                this.mLock.notifyAll();
                this.zzcwh.zzsm();
            }
        }
    }

    public final void setException(Throwable th) {
        synchronized (this.mLock) {
            if (this.zzbuf) {
            } else if (zzso()) {
                zzbv.zzeo().zzb(new IllegalStateException("Provided SettableFuture with multiple values."), "SettableFuture.setException");
            } else {
                this.zzcwf = th;
                this.mLock.notifyAll();
                this.zzcwh.zzsm();
            }
        }
    }

    public final void zza(Runnable runnable, Executor executor) {
        this.zzcwh.zza(runnable, executor);
    }
}
