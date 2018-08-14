package com.google.android.gms.iid;

import android.os.Binder;
import android.os.Process;
import android.util.Log;

public final class zzi extends Binder {
    private final zze zzbi;

    zzi(zze com_google_android_gms_iid_zze) {
        this.zzbi = com_google_android_gms_iid_zze;
    }

    public final void zzd(zzg com_google_android_gms_iid_zzg) {
        if (Binder.getCallingUid() != Process.myUid()) {
            throw new SecurityException("Binding only allowed within app");
        }
        if (Log.isLoggable("EnhancedIntentService", 3)) {
            Log.d("EnhancedIntentService", "service received new intent via bind strategy");
        }
        if (Log.isLoggable("EnhancedIntentService", 3)) {
            Log.d("EnhancedIntentService", "intent being queued for bg execution");
        }
        this.zzbi.zzax.execute(new zzj(this, com_google_android_gms_iid_zzg));
    }
}
