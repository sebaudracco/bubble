package com.google.android.gms.internal.ads;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.support.annotation.Nullable;
import com.google.android.gms.common.util.PlatformVersion;

@zzadh
public final class zzapo extends zzaph {
    @Nullable
    public final zzapg zza(Context context, zzapw com_google_android_gms_internal_ads_zzapw, int i, boolean z, zznx com_google_android_gms_internal_ads_zznx, zzapv com_google_android_gms_internal_ads_zzapv) {
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        Object obj = (!PlatformVersion.isAtLeastIceCreamSandwich() || (applicationInfo != null && applicationInfo.targetSdkVersion < 11)) ? null : 1;
        if (obj == null) {
            return null;
        }
        return new zzaov(context, z, com_google_android_gms_internal_ads_zzapw.zzud().zzvs(), com_google_android_gms_internal_ads_zzapv, new zzapx(context, com_google_android_gms_internal_ads_zzapw.zztq(), com_google_android_gms_internal_ads_zzapw.zzol(), com_google_android_gms_internal_ads_zznx, com_google_android_gms_internal_ads_zzapw.zztn()));
    }
}
