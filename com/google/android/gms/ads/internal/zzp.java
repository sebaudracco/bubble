package com.google.android.gms.ads.internal;

import android.os.Bundle;
import android.support.annotation.Nullable;
import com.fyber.mediation.admob.AdMobMediationAdapter;
import com.google.android.gms.internal.ads.zzadh;
import com.google.android.gms.internal.ads.zzjj;
import com.google.android.gms.internal.ads.zzjn;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;
import javax.annotation.ParametersAreNonnullByDefault;

@zzadh
@ParametersAreNonnullByDefault
public final class zzp {
    private static String zza(@Nullable Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        Iterator it = new TreeSet(bundle.keySet()).iterator();
        while (it.hasNext()) {
            Object obj = bundle.get((String) it.next());
            String zza = obj == null ? "null" : obj instanceof Bundle ? zza((Bundle) obj) : obj.toString();
            stringBuilder.append(zza);
        }
        return stringBuilder.toString();
    }

    public static Object[] zza(String str, zzjj com_google_android_gms_internal_ads_zzjj, String str2, int i, @Nullable zzjn com_google_android_gms_internal_ads_zzjn) {
        Set hashSet = new HashSet(Arrays.asList(str.split(",")));
        ArrayList arrayList = new ArrayList();
        arrayList.add(str);
        arrayList.add(str2);
        if (hashSet.contains("formatString")) {
            arrayList.add(null);
        }
        if (hashSet.contains("networkType")) {
            arrayList.add(Integer.valueOf(i));
        }
        if (hashSet.contains(AdMobMediationAdapter.BIRTHDAY_KEY)) {
            arrayList.add(Long.valueOf(com_google_android_gms_internal_ads_zzjj.zzapw));
        }
        if (hashSet.contains("extras")) {
            arrayList.add(zza(com_google_android_gms_internal_ads_zzjj.extras));
        }
        if (hashSet.contains(AdMobMediationAdapter.GENDER_KEY)) {
            arrayList.add(Integer.valueOf(com_google_android_gms_internal_ads_zzjj.zzapx));
        }
        if (hashSet.contains("keywords")) {
            if (com_google_android_gms_internal_ads_zzjj.zzapy != null) {
                arrayList.add(com_google_android_gms_internal_ads_zzjj.zzapy.toString());
            } else {
                arrayList.add(null);
            }
        }
        if (hashSet.contains("isTestDevice")) {
            arrayList.add(Boolean.valueOf(com_google_android_gms_internal_ads_zzjj.zzapz));
        }
        if (hashSet.contains("tagForChildDirectedTreatment")) {
            arrayList.add(Integer.valueOf(com_google_android_gms_internal_ads_zzjj.zzaqa));
        }
        if (hashSet.contains("manualImpressionsEnabled")) {
            arrayList.add(Boolean.valueOf(com_google_android_gms_internal_ads_zzjj.zzaqb));
        }
        if (hashSet.contains("publisherProvidedId")) {
            arrayList.add(com_google_android_gms_internal_ads_zzjj.zzaqc);
        }
        if (hashSet.contains("location")) {
            if (com_google_android_gms_internal_ads_zzjj.zzaqe != null) {
                arrayList.add(com_google_android_gms_internal_ads_zzjj.zzaqe.toString());
            } else {
                arrayList.add(null);
            }
        }
        if (hashSet.contains("contentUrl")) {
            arrayList.add(com_google_android_gms_internal_ads_zzjj.zzaqf);
        }
        if (hashSet.contains("networkExtras")) {
            arrayList.add(zza(com_google_android_gms_internal_ads_zzjj.zzaqg));
        }
        if (hashSet.contains("customTargeting")) {
            arrayList.add(zza(com_google_android_gms_internal_ads_zzjj.zzaqh));
        }
        if (hashSet.contains("categoryExclusions")) {
            if (com_google_android_gms_internal_ads_zzjj.zzaqi != null) {
                arrayList.add(com_google_android_gms_internal_ads_zzjj.zzaqi.toString());
            } else {
                arrayList.add(null);
            }
        }
        if (hashSet.contains("requestAgent")) {
            arrayList.add(com_google_android_gms_internal_ads_zzjj.zzaqj);
        }
        if (hashSet.contains("requestPackage")) {
            arrayList.add(com_google_android_gms_internal_ads_zzjj.zzaqk);
        }
        if (hashSet.contains("isDesignedForFamilies")) {
            arrayList.add(Boolean.valueOf(com_google_android_gms_internal_ads_zzjj.zzaql));
        }
        return arrayList.toArray();
    }
}
