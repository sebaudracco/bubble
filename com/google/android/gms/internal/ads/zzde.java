package com.google.android.gms.internal.ads;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.GeneralSecurityException;

final class zzde {
    static zzauf zzso;

    static boolean zzb(zzcz com_google_android_gms_internal_ads_zzcz) throws IllegalAccessException, InvocationTargetException {
        if (zzso != null) {
            return true;
        }
        String str = (String) zzkb.zzik().zzd(zznk.zzbax);
        if (str == null || str.length() == 0) {
            if (com_google_android_gms_internal_ads_zzcz == null) {
                str = null;
            } else {
                Method zza = com_google_android_gms_internal_ads_zzcz.zza("4o7tecxtkw7XaNt5hPj+0H1LvOi0SgxCIJTY9VcbazM/HSl/sFlxBFwnc8glnvoB", "RgSY6YxU2k1vLXOV3vapBnQwJDzYDlmX50wbm2tDcnw=");
                str = zza == null ? null : (String) zza.invoke(null, new Object[0]);
            }
            if (str == null) {
                return false;
            }
        }
        try {
            try {
                zzauh zzh = zzaul.zzh(zzbi.zza(str, true));
                for (zzaxp com_google_android_gms_internal_ads_zzaxp : zzavc.zzdht.zzaal()) {
                    if (com_google_android_gms_internal_ads_zzaxp.zzyw().isEmpty()) {
                        throw new GeneralSecurityException("Missing type_url.");
                    } else if (com_google_android_gms_internal_ads_zzaxp.zzze().isEmpty()) {
                        throw new GeneralSecurityException("Missing primitive_name.");
                    } else if (com_google_android_gms_internal_ads_zzaxp.zzzh().isEmpty()) {
                        throw new GeneralSecurityException("Missing catalogue_name.");
                    } else {
                        zzauo.zza(com_google_android_gms_internal_ads_zzaxp.zzyw(), zzauo.zzdy(com_google_android_gms_internal_ads_zzaxp.zzzh()).zzb(com_google_android_gms_internal_ads_zzaxp.zzyw(), com_google_android_gms_internal_ads_zzaxp.zzze(), com_google_android_gms_internal_ads_zzaxp.zzzf()), com_google_android_gms_internal_ads_zzaxp.zzzg());
                    }
                }
                zzso = zzavf.zza(zzh);
                return zzso != null;
            } catch (GeneralSecurityException e) {
                return false;
            }
        } catch (IllegalArgumentException e2) {
            return false;
        }
    }
}
