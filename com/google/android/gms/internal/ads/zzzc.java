package com.google.android.gms.internal.ads;

import com.google.ads.AdRequest.ErrorCode;
import com.google.ads.AdRequest.Gender;
import com.google.ads.mediation.MediationAdRequest;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@zzadh
public final class zzzc {
    public static int zza(ErrorCode errorCode) {
        switch (zzzd.zzbvg[errorCode.ordinal()]) {
            case 2:
                return 1;
            case 3:
                return 2;
            case 4:
                return 3;
            default:
                return 0;
        }
    }

    public static MediationAdRequest zza(zzjj com_google_android_gms_internal_ads_zzjj, boolean z) {
        Gender gender;
        Set hashSet = com_google_android_gms_internal_ads_zzjj.zzapy != null ? new HashSet(com_google_android_gms_internal_ads_zzjj.zzapy) : null;
        Date date = new Date(com_google_android_gms_internal_ads_zzjj.zzapw);
        switch (com_google_android_gms_internal_ads_zzjj.zzapx) {
            case 1:
                gender = Gender.MALE;
                break;
            case 2:
                gender = Gender.FEMALE;
                break;
            default:
                gender = Gender.UNKNOWN;
                break;
        }
        return new MediationAdRequest(date, gender, hashSet, z, com_google_android_gms_internal_ads_zzjj.zzaqe);
    }
}
