package com.google.android.gms.iid;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.VisibleForTesting;
import android.util.Log;
import com.google.android.gms.tasks.Task;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import javax.annotation.concurrent.GuardedBy;

public final class zzr {
    private final ScheduledExecutorService zzbz;
    @GuardedBy("this")
    private zzt zzca;
    @GuardedBy("this")
    private int zzcb;
    private final Context zzk;

    public zzr(Context context) {
        this(context, Executors.newSingleThreadScheduledExecutor());
    }

    @VisibleForTesting
    private zzr(Context context, ScheduledExecutorService scheduledExecutorService) {
        this.zzca = new zzt();
        this.zzcb = 1;
        this.zzk = context.getApplicationContext();
        this.zzbz = scheduledExecutorService;
    }

    private final synchronized <T> Task<T> zzd(zzz<T> com_google_android_gms_iid_zzz_T) {
        if (Log.isLoggable("MessengerIpcClient", 3)) {
            String valueOf = String.valueOf(com_google_android_gms_iid_zzz_T);
            Log.d("MessengerIpcClient", new StringBuilder(String.valueOf(valueOf).length() + 9).append("Queueing ").append(valueOf).toString());
        }
        if (!this.zzca.zze(com_google_android_gms_iid_zzz_T)) {
            this.zzca = new zzt();
            this.zzca.zze(com_google_android_gms_iid_zzz_T);
        }
        return com_google_android_gms_iid_zzz_T.zzcl.getTask();
    }

    private final synchronized int zzq() {
        int i;
        i = this.zzcb;
        this.zzcb = i + 1;
        return i;
    }

    public final Task<Bundle> zzd(int i, Bundle bundle) {
        return zzd(new zzab(zzq(), 1, bundle));
    }
}
