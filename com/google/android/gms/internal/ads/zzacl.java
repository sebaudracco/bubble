package com.google.android.gms.internal.ads;

import android.view.ViewTreeObserver.OnScrollChangedListener;
import java.lang.ref.WeakReference;

final class zzacl implements OnScrollChangedListener {
    private final /* synthetic */ zzace zzcbi;
    private final /* synthetic */ WeakReference zzcbj;

    zzacl(zzace com_google_android_gms_internal_ads_zzace, WeakReference weakReference) {
        this.zzcbi = com_google_android_gms_internal_ads_zzace;
        this.zzcbj = weakReference;
    }

    public final void onScrollChanged() {
        zzace.zza(this.zzcbi, this.zzcbj, true);
    }
}
