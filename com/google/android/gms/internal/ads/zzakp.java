package com.google.android.gms.internal.ads;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

final class zzakp extends BroadcastReceiver {
    private final /* synthetic */ zzakk zzcru;

    private zzakp(zzakk com_google_android_gms_internal_ads_zzakk) {
        this.zzcru = com_google_android_gms_internal_ads_zzakk;
    }

    public final void onReceive(Context context, Intent intent) {
        if ("android.intent.action.USER_PRESENT".equals(intent.getAction())) {
            zzakk.zza(this.zzcru, true);
        } else if ("android.intent.action.SCREEN_OFF".equals(intent.getAction())) {
            zzakk.zza(this.zzcru, false);
        }
    }
}
