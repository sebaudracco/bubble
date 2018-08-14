package com.google.android.gms.internal.ads;

import android.view.View;
import android.view.View.OnAttachStateChangeListener;

final class zzara implements OnAttachStateChangeListener {
    private final /* synthetic */ zzait zzdcg;
    private final /* synthetic */ zzaqx zzdci;

    zzara(zzaqx com_google_android_gms_internal_ads_zzaqx, zzait com_google_android_gms_internal_ads_zzait) {
        this.zzdci = com_google_android_gms_internal_ads_zzaqx;
        this.zzdcg = com_google_android_gms_internal_ads_zzait;
    }

    public final void onViewAttachedToWindow(View view) {
        zzaqx.zza(this.zzdci, view, this.zzdcg, 10);
    }

    public final void onViewDetachedFromWindow(View view) {
    }
}
