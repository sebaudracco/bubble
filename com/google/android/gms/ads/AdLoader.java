package com.google.android.gms.ads;

import android.content.Context;
import android.support.annotation.RequiresPermission;
import com.google.android.gms.ads.doubleclick.PublisherAdRequest;
import com.google.android.gms.internal.ads.zzane;
import com.google.android.gms.internal.ads.zzjm;
import com.google.android.gms.internal.ads.zzkk;
import com.google.android.gms.internal.ads.zzlw;

public class AdLoader {
    private final Context mContext;
    private final zzjm zzuk;
    private final zzkk zzul;

    AdLoader(Context context, zzkk com_google_android_gms_internal_ads_zzkk) {
        this(context, com_google_android_gms_internal_ads_zzkk, zzjm.zzara);
    }

    private AdLoader(Context context, zzkk com_google_android_gms_internal_ads_zzkk, zzjm com_google_android_gms_internal_ads_zzjm) {
        this.mContext = context;
        this.zzul = com_google_android_gms_internal_ads_zzkk;
        this.zzuk = com_google_android_gms_internal_ads_zzjm;
    }

    private final void zza(zzlw com_google_android_gms_internal_ads_zzlw) {
        try {
            this.zzul.zzd(zzjm.zza(this.mContext, com_google_android_gms_internal_ads_zzlw));
        } catch (Throwable e) {
            zzane.zzb("Failed to load ad.", e);
        }
    }

    @Deprecated
    public String getMediationAdapterClassName() {
        try {
            return this.zzul.zzck();
        } catch (Throwable e) {
            zzane.zzc("Failed to get the mediation adapter class name.", e);
            return null;
        }
    }

    public boolean isLoading() {
        try {
            return this.zzul.isLoading();
        } catch (Throwable e) {
            zzane.zzc("Failed to check if ad is loading.", e);
            return false;
        }
    }

    @RequiresPermission("android.permission.INTERNET")
    public void loadAd(AdRequest adRequest) {
        zza(adRequest.zzay());
    }

    public void loadAd(PublisherAdRequest publisherAdRequest) {
        zza(publisherAdRequest.zzay());
    }

    @RequiresPermission("android.permission.INTERNET")
    public void loadAds(AdRequest adRequest, int i) {
        try {
            this.zzul.zza(zzjm.zza(this.mContext, adRequest.zzay()), i);
        } catch (Throwable e) {
            zzane.zzb("Failed to load ads.", e);
        }
    }
}
