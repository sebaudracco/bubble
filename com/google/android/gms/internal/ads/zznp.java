package com.google.android.gms.internal.ads;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import javax.annotation.ParametersAreNonnullByDefault;

@zzadh
@ParametersAreNonnullByDefault
public final class zznp {
    public static void zza(zznn com_google_android_gms_internal_ads_zznn, @Nullable zznm com_google_android_gms_internal_ads_zznm) {
        if (com_google_android_gms_internal_ads_zznm.getContext() == null) {
            throw new IllegalArgumentException("Context can't be null. Please set up context in CsiConfiguration.");
        } else if (TextUtils.isEmpty(com_google_android_gms_internal_ads_zznm.zzfw())) {
            throw new IllegalArgumentException("AfmaVersion can't be null or empty. Please set up afmaVersion in CsiConfiguration.");
        } else {
            com_google_android_gms_internal_ads_zznn.zza(com_google_android_gms_internal_ads_zznm.getContext(), com_google_android_gms_internal_ads_zznm.zzfw(), com_google_android_gms_internal_ads_zznm.zzjd(), com_google_android_gms_internal_ads_zznm.zzje());
        }
    }
}
