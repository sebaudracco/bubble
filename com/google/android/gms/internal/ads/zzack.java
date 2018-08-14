package com.google.android.gms.internal.ads;

import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import java.lang.ref.WeakReference;

final class zzack implements OnGlobalLayoutListener {
    private final /* synthetic */ zzace zzcbi;
    private final /* synthetic */ WeakReference zzcbj;

    zzack(zzace com_google_android_gms_internal_ads_zzace, WeakReference weakReference) {
        this.zzcbi = com_google_android_gms_internal_ads_zzace;
        this.zzcbj = weakReference;
    }

    public final void onGlobalLayout() {
        zzace.zza(this.zzcbi, this.zzcbj, false);
    }
}
