package com.google.android.gms.internal.ads;

import android.os.Handler;
import android.os.Looper;
import com.google.android.gms.ads.internal.zzbv;
import java.util.concurrent.Executor;

final class zzaof implements Executor {
    private final Handler zzcwc = new zzakc(Looper.getMainLooper());

    zzaof() {
    }

    public final void execute(Runnable runnable) {
        if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
            try {
                runnable.run();
            } catch (Throwable th) {
                zzbv.zzek();
                zzakk.zza(zzbv.zzeo().getApplicationContext(), th);
            }
        } else {
            this.zzcwc.post(runnable);
        }
    }
}
