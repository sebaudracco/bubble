package com.google.android.gms.internal.ads;

import android.content.Context;

@zzadh
public class zzabn extends zzabf {
    zzabn(Context context, zzaji com_google_android_gms_internal_ads_zzaji, zzaqw com_google_android_gms_internal_ads_zzaqw, zzabm com_google_android_gms_internal_ads_zzabm) {
        super(context, com_google_android_gms_internal_ads_zzaji, com_google_android_gms_internal_ads_zzaqw, com_google_android_gms_internal_ads_zzabm);
    }

    protected final void zzns() {
        if (this.zzbzf.errorCode == -2) {
            this.zzbnd.zzuf().zza((zzasd) this);
            zznu();
            zzane.zzck("Loading HTML in WebView.");
            this.zzbnd.zzc(this.zzbzf.zzbyq, this.zzbzf.zzceo, null);
        }
    }

    protected void zznu() {
    }
}
