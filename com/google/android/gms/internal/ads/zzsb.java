package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.doubleclick.PublisherAdView;
import com.google.android.gms.ads.formats.OnPublisherAdViewLoadedListener;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;

public final class zzsb extends zzrj {
    private final OnPublisherAdViewLoadedListener zzblf;

    public zzsb(OnPublisherAdViewLoadedListener onPublisherAdViewLoadedListener) {
        this.zzblf = onPublisherAdViewLoadedListener;
    }

    public final void zza(zzks com_google_android_gms_internal_ads_zzks, IObjectWrapper iObjectWrapper) {
        if (com_google_android_gms_internal_ads_zzks != null && iObjectWrapper != null) {
            PublisherAdView publisherAdView = new PublisherAdView((Context) ObjectWrapper.unwrap(iObjectWrapper));
            try {
                if (com_google_android_gms_internal_ads_zzks.zzbx() instanceof zzjf) {
                    zzjf com_google_android_gms_internal_ads_zzjf = (zzjf) com_google_android_gms_internal_ads_zzks.zzbx();
                    publisherAdView.setAdListener(com_google_android_gms_internal_ads_zzjf != null ? com_google_android_gms_internal_ads_zzjf.getAdListener() : null);
                }
            } catch (Throwable e) {
                zzane.zzb("", e);
            }
            try {
                if (com_google_android_gms_internal_ads_zzks.zzbw() instanceof zzjp) {
                    zzjp com_google_android_gms_internal_ads_zzjp = (zzjp) com_google_android_gms_internal_ads_zzks.zzbw();
                    publisherAdView.setAppEventListener(com_google_android_gms_internal_ads_zzjp != null ? com_google_android_gms_internal_ads_zzjp.getAppEventListener() : null);
                }
            } catch (Throwable e2) {
                zzane.zzb("", e2);
            }
            zzamu.zzsy.post(new zzsc(this, publisherAdView, com_google_android_gms_internal_ads_zzks));
        }
    }
}
