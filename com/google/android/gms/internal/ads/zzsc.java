package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.doubleclick.PublisherAdView;

final class zzsc implements Runnable {
    private final /* synthetic */ PublisherAdView zzblg;
    private final /* synthetic */ zzks zzblh;
    private final /* synthetic */ zzsb zzbli;

    zzsc(zzsb com_google_android_gms_internal_ads_zzsb, PublisherAdView publisherAdView, zzks com_google_android_gms_internal_ads_zzks) {
        this.zzbli = com_google_android_gms_internal_ads_zzsb;
        this.zzblg = publisherAdView;
        this.zzblh = com_google_android_gms_internal_ads_zzks;
    }

    public final void run() {
        if (this.zzblg.zza(this.zzblh)) {
            this.zzbli.zzblf.onPublisherAdViewLoaded(this.zzblg);
        } else {
            zzane.zzdk("Could not bind.");
        }
    }
}
