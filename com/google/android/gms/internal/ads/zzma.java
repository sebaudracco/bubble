package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Bundle;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.Correlator;
import com.google.android.gms.ads.doubleclick.AppEventListener;
import com.google.android.gms.ads.doubleclick.OnCustomRenderedAdLoadedListener;
import com.google.android.gms.ads.doubleclick.PublisherInterstitialAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import com.google.android.gms.ads.reward.zza;
import com.google.android.gms.common.util.VisibleForTesting;

@zzadh
public final class zzma {
    private final Context mContext;
    private zzjd zzapt;
    private AdListener zzapu;
    private zza zzapv;
    private final zzxm zzast;
    private Correlator zzasx;
    private zzks zzasy;
    private OnCustomRenderedAdLoadedListener zzasz;
    private boolean zzatd;
    private RewardedVideoAdListener zzhc;
    private final zzjm zzuk;
    private AppEventListener zzvo;
    private String zzye;
    private boolean zzyu;

    public zzma(Context context) {
        this(context, zzjm.zzara, null);
    }

    public zzma(Context context, PublisherInterstitialAd publisherInterstitialAd) {
        this(context, zzjm.zzara, publisherInterstitialAd);
    }

    @VisibleForTesting
    private zzma(Context context, zzjm com_google_android_gms_internal_ads_zzjm, PublisherInterstitialAd publisherInterstitialAd) {
        this.zzast = new zzxm();
        this.mContext = context;
        this.zzuk = com_google_android_gms_internal_ads_zzjm;
    }

    private final void zzaj(String str) {
        if (this.zzasy == null) {
            throw new IllegalStateException(new StringBuilder(String.valueOf(str).length() + 63).append("The ad unit ID must be set on InterstitialAd before ").append(str).append(" is called.").toString());
        }
    }

    public final AdListener getAdListener() {
        return this.zzapu;
    }

    public final String getAdUnitId() {
        return this.zzye;
    }

    public final AppEventListener getAppEventListener() {
        return this.zzvo;
    }

    public final String getMediationAdapterClassName() {
        try {
            if (this.zzasy != null) {
                return this.zzasy.zzck();
            }
        } catch (Throwable e) {
            zzane.zzd("#008 Must be called on the main UI thread.", e);
        }
        return null;
    }

    public final OnCustomRenderedAdLoadedListener getOnCustomRenderedAdLoadedListener() {
        return this.zzasz;
    }

    public final boolean isLoaded() {
        boolean z = false;
        try {
            if (this.zzasy != null) {
                z = this.zzasy.isReady();
            }
        } catch (Throwable e) {
            zzane.zzd("#008 Must be called on the main UI thread.", e);
        }
        return z;
    }

    public final boolean isLoading() {
        boolean z = false;
        try {
            if (this.zzasy != null) {
                z = this.zzasy.isLoading();
            }
        } catch (Throwable e) {
            zzane.zzd("#008 Must be called on the main UI thread.", e);
        }
        return z;
    }

    public final void setAdListener(AdListener adListener) {
        try {
            this.zzapu = adListener;
            if (this.zzasy != null) {
                this.zzasy.zza(adListener != null ? new zzjf(adListener) : null);
            }
        } catch (Throwable e) {
            zzane.zzd("#008 Must be called on the main UI thread.", e);
        }
    }

    public final void setAdUnitId(String str) {
        if (this.zzye != null) {
            throw new IllegalStateException("The ad unit ID can only be set once on InterstitialAd.");
        }
        this.zzye = str;
    }

    public final void setAppEventListener(AppEventListener appEventListener) {
        try {
            this.zzvo = appEventListener;
            if (this.zzasy != null) {
                this.zzasy.zza(appEventListener != null ? new zzjp(appEventListener) : null);
            }
        } catch (Throwable e) {
            zzane.zzd("#008 Must be called on the main UI thread.", e);
        }
    }

    public final void setCorrelator(Correlator correlator) {
        this.zzasx = correlator;
        try {
            if (this.zzasy != null) {
                this.zzasy.zza(this.zzasx == null ? null : this.zzasx.zzaz());
            }
        } catch (Throwable e) {
            zzane.zzd("#008 Must be called on the main UI thread.", e);
        }
    }

    public final void setImmersiveMode(boolean z) {
        try {
            this.zzyu = z;
            if (this.zzasy != null) {
                this.zzasy.setImmersiveMode(z);
            }
        } catch (Throwable e) {
            zzane.zzd("#008 Must be called on the main UI thread.", e);
        }
    }

    public final void setOnCustomRenderedAdLoadedListener(OnCustomRenderedAdLoadedListener onCustomRenderedAdLoadedListener) {
        try {
            this.zzasz = onCustomRenderedAdLoadedListener;
            if (this.zzasy != null) {
                this.zzasy.zza(onCustomRenderedAdLoadedListener != null ? new zzog(onCustomRenderedAdLoadedListener) : null);
            }
        } catch (Throwable e) {
            zzane.zzd("#008 Must be called on the main UI thread.", e);
        }
    }

    public final void setRewardedVideoAdListener(RewardedVideoAdListener rewardedVideoAdListener) {
        try {
            this.zzhc = rewardedVideoAdListener;
            if (this.zzasy != null) {
                this.zzasy.zza(rewardedVideoAdListener != null ? new zzahj(rewardedVideoAdListener) : null);
            }
        } catch (Throwable e) {
            zzane.zzd("#008 Must be called on the main UI thread.", e);
        }
    }

    public final void show() {
        try {
            zzaj("show");
            this.zzasy.showInterstitial();
        } catch (Throwable e) {
            zzane.zzd("#008 Must be called on the main UI thread.", e);
        }
    }

    public final void zza(zza com_google_android_gms_ads_reward_zza) {
        try {
            this.zzapv = com_google_android_gms_ads_reward_zza;
            if (this.zzasy != null) {
                this.zzasy.zza(com_google_android_gms_ads_reward_zza != null ? new zzji(com_google_android_gms_ads_reward_zza) : null);
            }
        } catch (Throwable e) {
            zzane.zzd("#008 Must be called on the main UI thread.", e);
        }
    }

    public final void zza(zzjd com_google_android_gms_internal_ads_zzjd) {
        try {
            this.zzapt = com_google_android_gms_internal_ads_zzjd;
            if (this.zzasy != null) {
                this.zzasy.zza(com_google_android_gms_internal_ads_zzjd != null ? new zzje(com_google_android_gms_internal_ads_zzjd) : null);
            }
        } catch (Throwable e) {
            zzane.zzd("#008 Must be called on the main UI thread.", e);
        }
    }

    public final void zza(zzlw com_google_android_gms_internal_ads_zzlw) {
        try {
            if (this.zzasy == null) {
                String str = "loadAd";
                if (this.zzye == null) {
                    zzaj(str);
                }
                zzjn zzhx = this.zzatd ? zzjn.zzhx() : new zzjn();
                zzjr zzig = zzkb.zzig();
                Context context = this.mContext;
                this.zzasy = (zzks) zzjr.zza(context, false, new zzju(zzig, context, zzhx, this.zzye, this.zzast));
                if (this.zzapu != null) {
                    this.zzasy.zza(new zzjf(this.zzapu));
                }
                if (this.zzapt != null) {
                    this.zzasy.zza(new zzje(this.zzapt));
                }
                if (this.zzapv != null) {
                    this.zzasy.zza(new zzji(this.zzapv));
                }
                if (this.zzvo != null) {
                    this.zzasy.zza(new zzjp(this.zzvo));
                }
                if (this.zzasz != null) {
                    this.zzasy.zza(new zzog(this.zzasz));
                }
                if (this.zzasx != null) {
                    this.zzasy.zza(this.zzasx.zzaz());
                }
                if (this.zzhc != null) {
                    this.zzasy.zza(new zzahj(this.zzhc));
                }
                this.zzasy.setImmersiveMode(this.zzyu);
            }
            if (this.zzasy.zzb(zzjm.zza(this.mContext, com_google_android_gms_internal_ads_zzlw))) {
                this.zzast.zzj(com_google_android_gms_internal_ads_zzlw.zzir());
            }
        } catch (Throwable e) {
            zzane.zzd("#008 Must be called on the main UI thread.", e);
        }
    }

    public final void zza(boolean z) {
        this.zzatd = true;
    }

    public final Bundle zzba() {
        try {
            if (this.zzasy != null) {
                return this.zzasy.zzba();
            }
        } catch (Throwable e) {
            zzane.zzd("#008 Must be called on the main UI thread.", e);
        }
        return new Bundle();
    }
}
