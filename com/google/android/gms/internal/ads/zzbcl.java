package com.google.android.gms.internal.ads;

final class zzbcl implements zzbct {
    private zzbct[] zzdvx;

    zzbcl(zzbct... com_google_android_gms_internal_ads_zzbctArr) {
        this.zzdvx = com_google_android_gms_internal_ads_zzbctArr;
    }

    public final boolean zza(Class<?> cls) {
        for (zzbct zza : this.zzdvx) {
            if (zza.zza(cls)) {
                return true;
            }
        }
        return false;
    }

    public final zzbcs zzb(Class<?> cls) {
        for (zzbct com_google_android_gms_internal_ads_zzbct : this.zzdvx) {
            if (com_google_android_gms_internal_ads_zzbct.zza(cls)) {
                return com_google_android_gms_internal_ads_zzbct.zzb(cls);
            }
        }
        String str = "No factory is available for message type: ";
        String valueOf = String.valueOf(cls.getName());
        throw new UnsupportedOperationException(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
    }
}
