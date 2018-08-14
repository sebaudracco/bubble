package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.ObjectWrapper;
import javax.annotation.concurrent.GuardedBy;

@zzadh
public final class zzmb {
    private static final Object sLock = new Object();
    @GuardedBy("sLock")
    private static zzmb zzate;
    private zzlj zzatf;
    private RewardedVideoAd zzatg;

    private zzmb() {
    }

    public static zzmb zziv() {
        zzmb com_google_android_gms_internal_ads_zzmb;
        synchronized (sLock) {
            if (zzate == null) {
                zzate = new zzmb();
            }
            com_google_android_gms_internal_ads_zzmb = zzate;
        }
        return com_google_android_gms_internal_ads_zzmb;
    }

    public final RewardedVideoAd getRewardedVideoAdInstance(Context context) {
        RewardedVideoAd rewardedVideoAd;
        synchronized (sLock) {
            if (this.zzatg != null) {
                rewardedVideoAd = this.zzatg;
            } else {
                this.zzatg = new zzahm(context, (zzagz) zzjr.zza(context, false, new zzjz(zzkb.zzig(), context, new zzxm())));
                rewardedVideoAd = this.zzatg;
            }
        }
        return rewardedVideoAd;
    }

    public final void openDebugMenu(Context context, String str) {
        Preconditions.checkState(this.zzatf != null, "MobileAds.initialize() must be called prior to opening debug menu.");
        try {
            this.zzatf.zzb(ObjectWrapper.wrap(context), str);
        } catch (Throwable e) {
            zzane.zzb("Unable to open debug menu.", e);
        }
    }

    public final void setAppMuted(boolean z) {
        Preconditions.checkState(this.zzatf != null, "MobileAds.initialize() must be called prior to setting app muted state.");
        try {
            this.zzatf.setAppMuted(z);
        } catch (Throwable e) {
            zzane.zzb("Unable to set app mute state.", e);
        }
    }

    public final void setAppVolume(float f) {
        boolean z = true;
        boolean z2 = 0.0f <= f && f <= 1.0f;
        Preconditions.checkArgument(z2, "The app volume must be a value between 0 and 1 inclusive.");
        if (this.zzatf == null) {
            z = false;
        }
        Preconditions.checkState(z, "MobileAds.initialize() must be called prior to setting the app volume.");
        try {
            this.zzatf.setAppVolume(f);
        } catch (Throwable e) {
            zzane.zzb("Unable to set app volume.", e);
        }
    }

    public final void zza(Context context, String str, zzmd com_google_android_gms_internal_ads_zzmd) {
        synchronized (sLock) {
            if (this.zzatf != null) {
            } else if (context == null) {
                throw new IllegalArgumentException("Context cannot be null.");
            } else {
                try {
                    this.zzatf = (zzlj) zzjr.zza(context, false, new zzjw(zzkb.zzig(), context));
                    this.zzatf.zza();
                    if (str != null) {
                        this.zzatf.zza(str, ObjectWrapper.wrap(new zzmc(this, context)));
                    }
                } catch (Throwable e) {
                    zzane.zzc("MobileAdsSettingManager initialization failed", e);
                }
            }
        }
    }

    public final float zzdo() {
        float f = 1.0f;
        if (this.zzatf != null) {
            try {
                f = this.zzatf.zzdo();
            } catch (Throwable e) {
                zzane.zzb("Unable to get app volume.", e);
            }
        }
        return f;
    }

    public final boolean zzdp() {
        boolean z = false;
        if (this.zzatf != null) {
            try {
                z = this.zzatf.zzdp();
            } catch (Throwable e) {
                zzane.zzb("Unable to get app mute state.", e);
            }
        }
        return z;
    }
}
