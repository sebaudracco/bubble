package com.google.android.gms.internal.ads;

import android.view.View;
import com.google.android.gms.ads.doubleclick.CustomRenderedAd;
import com.google.android.gms.dynamic.ObjectWrapper;

@zzadh
public final class zznz implements CustomRenderedAd {
    private final zzoa zzbgv;

    public zznz(zzoa com_google_android_gms_internal_ads_zzoa) {
        this.zzbgv = com_google_android_gms_internal_ads_zzoa;
    }

    public final String getBaseUrl() {
        try {
            return this.zzbgv.zzjn();
        } catch (Throwable e) {
            zzane.zzd("#007 Could not call remote method.", e);
            return null;
        }
    }

    public final String getContent() {
        try {
            return this.zzbgv.getContent();
        } catch (Throwable e) {
            zzane.zzd("#007 Could not call remote method.", e);
            return null;
        }
    }

    public final void onAdRendered(View view) {
        try {
            this.zzbgv.zzg(view != null ? ObjectWrapper.wrap(view) : null);
        } catch (Throwable e) {
            zzane.zzd("#007 Could not call remote method.", e);
        }
    }

    public final void recordClick() {
        try {
            this.zzbgv.recordClick();
        } catch (Throwable e) {
            zzane.zzd("#007 Could not call remote method.", e);
        }
    }

    public final void recordImpression() {
        try {
            this.zzbgv.recordImpression();
        } catch (Throwable e) {
            zzane.zzd("#007 Could not call remote method.", e);
        }
    }
}
