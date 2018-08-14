package com.google.android.gms.iid;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

final class zzah extends BroadcastReceiver {
    private final /* synthetic */ zzaf zzcx;

    zzah(zzaf com_google_android_gms_iid_zzaf) {
        this.zzcx = com_google_android_gms_iid_zzaf;
    }

    public final void onReceive(Context context, Intent intent) {
        if (Log.isLoggable("InstanceID", 3)) {
            Log.d("InstanceID", "Received GSF callback via dynamic receiver");
        }
        this.zzcx.zzh(intent);
    }
}
