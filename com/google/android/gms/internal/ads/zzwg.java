package com.google.android.gms.internal.ads;

import android.content.Context;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.GuardedBy;

@zzadh
@ParametersAreNonnullByDefault
public final class zzwg {
    private final Object mLock = new Object();
    @GuardedBy("mLock")
    private zzwn zzbrb;

    public final zzwn zzb(Context context, zzang com_google_android_gms_internal_ads_zzang) {
        zzwn com_google_android_gms_internal_ads_zzwn;
        synchronized (this.mLock) {
            if (this.zzbrb == null) {
                Context applicationContext = context.getApplicationContext();
                if (applicationContext != null) {
                    context = applicationContext;
                }
                this.zzbrb = new zzwn(context, com_google_android_gms_internal_ads_zzang, (String) zzkb.zzik().zzd(zznk.zzaub));
            }
            com_google_android_gms_internal_ads_zzwn = this.zzbrb;
        }
        return com_google_android_gms_internal_ads_zzwn;
    }
}
