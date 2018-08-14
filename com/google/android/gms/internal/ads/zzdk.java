package com.google.android.gms.internal.ads;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

final class zzdk extends BroadcastReceiver {
    private final /* synthetic */ zzdi zztj;

    zzdk(zzdi com_google_android_gms_internal_ads_zzdi) {
        this.zztj = com_google_android_gms_internal_ads_zzdi;
    }

    public final void onReceive(Context context, Intent intent) {
        this.zztj.zzaq();
    }
}
