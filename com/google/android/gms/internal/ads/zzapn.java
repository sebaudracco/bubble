package com.google.android.gms.internal.ads;

import android.content.Context;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;

@zzadh
public final class zzapn {
    private final Context zzcyf;
    private final zzapw zzcyg;
    private final ViewGroup zzcyh;
    private zzapi zzcyi;

    @VisibleForTesting
    private zzapn(Context context, ViewGroup viewGroup, zzapw com_google_android_gms_internal_ads_zzapw, zzapi com_google_android_gms_internal_ads_zzapi) {
        if (context.getApplicationContext() != null) {
            context = context.getApplicationContext();
        }
        this.zzcyf = context;
        this.zzcyh = viewGroup;
        this.zzcyg = com_google_android_gms_internal_ads_zzapw;
        this.zzcyi = null;
    }

    public zzapn(Context context, ViewGroup viewGroup, zzaqw com_google_android_gms_internal_ads_zzaqw) {
        this(context, viewGroup, com_google_android_gms_internal_ads_zzaqw, null);
    }

    public final void onDestroy() {
        Preconditions.checkMainThread("onDestroy must be called from the UI thread.");
        if (this.zzcyi != null) {
            this.zzcyi.destroy();
            this.zzcyh.removeView(this.zzcyi);
            this.zzcyi = null;
        }
    }

    public final void onPause() {
        Preconditions.checkMainThread("onPause must be called from the UI thread.");
        if (this.zzcyi != null) {
            this.zzcyi.pause();
        }
    }

    public final void zza(int i, int i2, int i3, int i4, int i5, boolean z, zzapv com_google_android_gms_internal_ads_zzapv) {
        if (this.zzcyi == null) {
            zznq.zza(this.zzcyg.zztp().zzji(), this.zzcyg.zztn(), "vpr2");
            this.zzcyi = new zzapi(this.zzcyf, this.zzcyg, i5, z, this.zzcyg.zztp().zzji(), com_google_android_gms_internal_ads_zzapv);
            this.zzcyh.addView(this.zzcyi, 0, new LayoutParams(-1, -1));
            this.zzcyi.zzd(i, i2, i3, i4);
            this.zzcyg.zzah(false);
        }
    }

    public final void zze(int i, int i2, int i3, int i4) {
        Preconditions.checkMainThread("The underlay may only be modified from the UI thread.");
        if (this.zzcyi != null) {
            this.zzcyi.zzd(i, i2, i3, i4);
        }
    }

    public final zzapi zzth() {
        Preconditions.checkMainThread("getAdVideoUnderlay must be called from the UI thread.");
        return this.zzcyi;
    }
}
