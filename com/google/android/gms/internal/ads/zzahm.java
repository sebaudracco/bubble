package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.doubleclick.PublisherAdRequest;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import com.google.android.gms.dynamic.ObjectWrapper;

@zzadh
public final class zzahm implements RewardedVideoAd {
    private final Context mContext;
    private final Object mLock = new Object();
    private String zzadr;
    private final zzagz zzclj;
    private final zzahj zzclk = new zzahj(null);

    public zzahm(Context context, zzagz com_google_android_gms_internal_ads_zzagz) {
        this.zzclj = com_google_android_gms_internal_ads_zzagz;
        this.mContext = context;
    }

    private final void zza(String str, zzlw com_google_android_gms_internal_ads_zzlw) {
        synchronized (this.mLock) {
            if (this.zzclj == null) {
                return;
            }
            try {
                this.zzclj.zza(new zzahk(zzjm.zza(this.mContext, com_google_android_gms_internal_ads_zzlw), str));
            } catch (Throwable e) {
                zzane.zzd("#007 Could not call remote method.", e);
            }
        }
    }

    public final void destroy() {
        destroy(null);
    }

    public final void destroy(Context context) {
        synchronized (this.mLock) {
            this.zzclk.setRewardedVideoAdListener(null);
            if (this.zzclj == null) {
                return;
            }
            try {
                this.zzclj.zzf(ObjectWrapper.wrap(context));
            } catch (Throwable e) {
                zzane.zzd("#007 Could not call remote method.", e);
            }
        }
    }

    public final String getMediationAdapterClassName() {
        try {
            if (this.zzclj != null) {
                return this.zzclj.getMediationAdapterClassName();
            }
        } catch (Throwable e) {
            zzane.zzd("#007 Could not call remote method.", e);
        }
        return null;
    }

    public final RewardedVideoAdListener getRewardedVideoAdListener() {
        RewardedVideoAdListener rewardedVideoAdListener;
        synchronized (this.mLock) {
            rewardedVideoAdListener = this.zzclk.getRewardedVideoAdListener();
        }
        return rewardedVideoAdListener;
    }

    public final String getUserId() {
        String str;
        synchronized (this.mLock) {
            str = this.zzadr;
        }
        return str;
    }

    public final boolean isLoaded() {
        boolean z = false;
        synchronized (this.mLock) {
            if (this.zzclj == null) {
            } else {
                try {
                    z = this.zzclj.isLoaded();
                } catch (Throwable e) {
                    zzane.zzd("#007 Could not call remote method.", e);
                }
            }
        }
        return z;
    }

    public final void loadAd(String str, AdRequest adRequest) {
        zza(str, adRequest.zzay());
    }

    public final void loadAd(String str, PublisherAdRequest publisherAdRequest) {
        zza(str, publisherAdRequest.zzay());
    }

    public final void pause() {
        pause(null);
    }

    public final void pause(Context context) {
        synchronized (this.mLock) {
            if (this.zzclj == null) {
                return;
            }
            try {
                this.zzclj.zzd(ObjectWrapper.wrap(context));
            } catch (Throwable e) {
                zzane.zzd("#007 Could not call remote method.", e);
            }
        }
    }

    public final void resume() {
        resume(null);
    }

    public final void resume(Context context) {
        synchronized (this.mLock) {
            if (this.zzclj == null) {
                return;
            }
            try {
                this.zzclj.zze(ObjectWrapper.wrap(context));
            } catch (Throwable e) {
                zzane.zzd("#007 Could not call remote method.", e);
            }
        }
    }

    public final void setImmersiveMode(boolean z) {
        synchronized (this.mLock) {
            if (this.zzclj != null) {
                try {
                    this.zzclj.setImmersiveMode(z);
                } catch (Throwable e) {
                    zzane.zzd("#007 Could not call remote method.", e);
                }
            }
        }
    }

    public final void setRewardedVideoAdListener(RewardedVideoAdListener rewardedVideoAdListener) {
        synchronized (this.mLock) {
            this.zzclk.setRewardedVideoAdListener(rewardedVideoAdListener);
            if (this.zzclj != null) {
                try {
                    this.zzclj.zza(this.zzclk);
                } catch (Throwable e) {
                    zzane.zzd("#007 Could not call remote method.", e);
                }
            }
        }
    }

    public final void setUserId(String str) {
        synchronized (this.mLock) {
            this.zzadr = str;
            if (this.zzclj != null) {
                try {
                    this.zzclj.setUserId(str);
                } catch (Throwable e) {
                    zzane.zzd("#007 Could not call remote method.", e);
                }
            }
        }
    }

    public final void show() {
        synchronized (this.mLock) {
            if (this.zzclj == null) {
                return;
            }
            try {
                this.zzclj.show();
            } catch (Throwable e) {
                zzane.zzd("#007 Could not call remote method.", e);
            }
        }
    }
}
