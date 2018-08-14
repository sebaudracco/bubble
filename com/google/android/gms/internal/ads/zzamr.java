package com.google.android.gms.internal.ads;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

final class zzamr extends BroadcastReceiver {
    private final /* synthetic */ zzamq zzcuf;

    zzamr(zzamq com_google_android_gms_internal_ads_zzamq) {
        this.zzcuf = com_google_android_gms_internal_ads_zzamq;
    }

    public final void onReceive(Context context, Intent intent) {
        zzamq.zza(this.zzcuf, context, intent);
    }
}
