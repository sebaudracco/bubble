package com.google.android.gms.iid;

import android.content.Intent;
import android.util.Log;

final class zzh implements Runnable {
    private final /* synthetic */ Intent zzbb;
    private final /* synthetic */ zzg zzbh;

    zzh(zzg com_google_android_gms_iid_zzg, Intent intent) {
        this.zzbh = com_google_android_gms_iid_zzg;
        this.zzbb = intent;
    }

    public final void run() {
        String action = this.zzbb.getAction();
        Log.w("EnhancedIntentService", new StringBuilder(String.valueOf(action).length() + 61).append("Service took too long to process intent: ").append(action).append(" App may get closed.").toString());
        this.zzbh.finish();
    }
}
