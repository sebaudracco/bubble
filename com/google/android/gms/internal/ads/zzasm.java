package com.google.android.gms.internal.ads;

import android.view.View;
import android.view.View.OnAttachStateChangeListener;

final class zzasm implements OnAttachStateChangeListener {
    private final /* synthetic */ zzait zzdcg;
    private final /* synthetic */ zzasj zzdes;

    zzasm(zzasj com_google_android_gms_internal_ads_zzasj, zzait com_google_android_gms_internal_ads_zzait) {
        this.zzdes = com_google_android_gms_internal_ads_zzasj;
        this.zzdcg = com_google_android_gms_internal_ads_zzait;
    }

    public final void onViewAttachedToWindow(View view) {
        zzasj.zza(this.zzdes, view, this.zzdcg, 10);
    }

    public final void onViewDetachedFromWindow(View view) {
    }
}
