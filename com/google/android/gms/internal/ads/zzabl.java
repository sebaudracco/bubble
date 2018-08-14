package com.google.android.gms.internal.ads;

import android.content.Context;
import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.zza;
import com.google.android.gms.ads.internal.zzbc;
import com.google.android.gms.common.util.PlatformVersion;
import javax.annotation.ParametersAreNonnullByDefault;

@zzadh
@ParametersAreNonnullByDefault
public final class zzabl {
    public static zzalc zza(Context context, zza com_google_android_gms_ads_internal_zza, zzaji com_google_android_gms_internal_ads_zzaji, zzci com_google_android_gms_internal_ads_zzci, @Nullable zzaqw com_google_android_gms_internal_ads_zzaqw, zzxn com_google_android_gms_internal_ads_zzxn, zzabm com_google_android_gms_internal_ads_zzabm, zznx com_google_android_gms_internal_ads_zznx) {
        zzalc com_google_android_gms_internal_ads_zzabr;
        zzaej com_google_android_gms_internal_ads_zzaej = com_google_android_gms_internal_ads_zzaji.zzcos;
        if (com_google_android_gms_internal_ads_zzaej.zzceq) {
            com_google_android_gms_internal_ads_zzabr = new zzabr(context, com_google_android_gms_internal_ads_zzaji, com_google_android_gms_internal_ads_zzxn, com_google_android_gms_internal_ads_zzabm, com_google_android_gms_internal_ads_zznx, com_google_android_gms_internal_ads_zzaqw);
        } else if (com_google_android_gms_internal_ads_zzaej.zzare || (com_google_android_gms_ads_internal_zza instanceof zzbc)) {
            com_google_android_gms_internal_ads_zzabr = (com_google_android_gms_internal_ads_zzaej.zzare && (com_google_android_gms_ads_internal_zza instanceof zzbc)) ? new zzabt(context, (zzbc) com_google_android_gms_ads_internal_zza, com_google_android_gms_internal_ads_zzaji, com_google_android_gms_internal_ads_zzci, com_google_android_gms_internal_ads_zzabm, com_google_android_gms_internal_ads_zznx) : new zzabo(com_google_android_gms_internal_ads_zzaji, com_google_android_gms_internal_ads_zzabm);
        } else {
            com_google_android_gms_internal_ads_zzabr = (((Boolean) zzkb.zzik().zzd(zznk.zzaxd)).booleanValue() && PlatformVersion.isAtLeastKitKat() && !PlatformVersion.isAtLeastLollipop() && com_google_android_gms_internal_ads_zzaqw != null && com_google_android_gms_internal_ads_zzaqw.zzud().zzvs()) ? new zzabq(context, com_google_android_gms_internal_ads_zzaji, com_google_android_gms_internal_ads_zzaqw, com_google_android_gms_internal_ads_zzabm) : new zzabn(context, com_google_android_gms_internal_ads_zzaji, com_google_android_gms_internal_ads_zzaqw, com_google_android_gms_internal_ads_zzabm);
        }
        String str = "AdRenderer: ";
        String valueOf = String.valueOf(com_google_android_gms_internal_ads_zzabr.getClass().getName());
        zzane.zzck(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
        com_google_android_gms_internal_ads_zzabr.zznt();
        return com_google_android_gms_internal_ads_zzabr;
    }
}
