package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.common.util.DeviceProperties;

final class zzadw implements zzady {
    private final /* synthetic */ Context val$context;

    zzadw(Context context) {
        this.val$context = context;
    }

    public final boolean zza(zzang com_google_android_gms_internal_ads_zzang) {
        zzkb.zzif();
        boolean zzbe = zzamu.zzbe(this.val$context);
        boolean z = ((Boolean) zzkb.zzik().zzd(zznk.zzbeq)).booleanValue() && com_google_android_gms_internal_ads_zzang.zzcvg;
        if (zzadv.zzd(this.val$context, com_google_android_gms_internal_ads_zzang.zzcvg) && zzbe && !z) {
            if (!DeviceProperties.isSidewinder(this.val$context)) {
                return false;
            }
            if (((Boolean) zzkb.zzik().zzd(zznk.zzawa)).booleanValue()) {
                return false;
            }
        }
        return true;
    }
}
