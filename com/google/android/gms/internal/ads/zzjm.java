package com.google.android.gms.internal.ads;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.search.SearchAdRequest;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@zzadh
public final class zzjm {
    public static final zzjm zzara = new zzjm();

    @VisibleForTesting
    protected zzjm() {
    }

    public static zzjj zza(Context context, zzlw com_google_android_gms_internal_ads_zzlw) {
        Date birthday = com_google_android_gms_internal_ads_zzlw.getBirthday();
        long time = birthday != null ? birthday.getTime() : -1;
        String contentUrl = com_google_android_gms_internal_ads_zzlw.getContentUrl();
        int gender = com_google_android_gms_internal_ads_zzlw.getGender();
        Collection keywords = com_google_android_gms_internal_ads_zzlw.getKeywords();
        List unmodifiableList = !keywords.isEmpty() ? Collections.unmodifiableList(new ArrayList(keywords)) : null;
        boolean isTestDevice = com_google_android_gms_internal_ads_zzlw.isTestDevice(context);
        int zzit = com_google_android_gms_internal_ads_zzlw.zzit();
        Location location = com_google_android_gms_internal_ads_zzlw.getLocation();
        Bundle networkExtrasBundle = com_google_android_gms_internal_ads_zzlw.getNetworkExtrasBundle(AdMobAdapter.class);
        boolean manualImpressionsEnabled = com_google_android_gms_internal_ads_zzlw.getManualImpressionsEnabled();
        String publisherProvidedId = com_google_android_gms_internal_ads_zzlw.getPublisherProvidedId();
        SearchAdRequest zziq = com_google_android_gms_internal_ads_zzlw.zziq();
        zzmq com_google_android_gms_internal_ads_zzmq = zziq != null ? new zzmq(zziq) : null;
        String str = null;
        Context applicationContext = context.getApplicationContext();
        if (applicationContext != null) {
            String packageName = applicationContext.getPackageName();
            zzkb.zzif();
            str = zzamu.zza(Thread.currentThread().getStackTrace(), packageName);
        }
        return new zzjj(7, time, networkExtrasBundle, gender, unmodifiableList, isTestDevice, zzit, manualImpressionsEnabled, publisherProvidedId, com_google_android_gms_internal_ads_zzmq, location, contentUrl, com_google_android_gms_internal_ads_zzlw.zzis(), com_google_android_gms_internal_ads_zzlw.getCustomTargeting(), Collections.unmodifiableList(new ArrayList(com_google_android_gms_internal_ads_zzlw.zziu())), com_google_android_gms_internal_ads_zzlw.zzip(), str, com_google_android_gms_internal_ads_zzlw.isDesignedForFamilies());
    }
}
