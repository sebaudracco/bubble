package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.common.util.VisibleForTesting;
import javax.annotation.ParametersAreNonnullByDefault;

@zzadh
@ParametersAreNonnullByDefault
public final class zzwn {
    @VisibleForTesting
    private static final zzalo<zzuu> zzbrf = new zzwo();
    @VisibleForTesting
    private static final zzalo<zzuu> zzbrg = new zzwp();
    private final zzvf zzbrh;

    public zzwn(Context context, zzang com_google_android_gms_internal_ads_zzang, String str) {
        this.zzbrh = new zzvf(context, com_google_android_gms_internal_ads_zzang, str, zzbrf, zzbrg);
    }

    public final <I, O> zzwf<I, O> zza(String str, zzwi<I> com_google_android_gms_internal_ads_zzwi_I, zzwh<O> com_google_android_gms_internal_ads_zzwh_O) {
        return new zzwq(this.zzbrh, str, com_google_android_gms_internal_ads_zzwi_I, com_google_android_gms_internal_ads_zzwh_O);
    }
}
