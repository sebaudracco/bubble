package com.google.android.gms.internal.ads;

import android.os.Bundle;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.mediation.MediationRewardedVideoAdAdapter;
import com.google.android.gms.ads.reward.mediation.MediationRewardedVideoAdListener;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.ObjectWrapper;

@zzadh
public final class zzaif implements MediationRewardedVideoAdListener {
    private final zzaic zzcmj;

    public zzaif(zzaic com_google_android_gms_internal_ads_zzaic) {
        this.zzcmj = com_google_android_gms_internal_ads_zzaic;
    }

    public final void onAdClicked(MediationRewardedVideoAdAdapter mediationRewardedVideoAdAdapter) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzane.zzck("Adapter called onAdClicked.");
        try {
            this.zzcmj.zzv(ObjectWrapper.wrap(mediationRewardedVideoAdAdapter));
        } catch (Throwable e) {
            zzane.zzd("#007 Could not call remote method.", e);
        }
    }

    public final void onAdClosed(MediationRewardedVideoAdAdapter mediationRewardedVideoAdAdapter) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzane.zzck("Adapter called onAdClosed.");
        try {
            this.zzcmj.zzu(ObjectWrapper.wrap(mediationRewardedVideoAdAdapter));
        } catch (Throwable e) {
            zzane.zzd("#007 Could not call remote method.", e);
        }
    }

    public final void onAdFailedToLoad(MediationRewardedVideoAdAdapter mediationRewardedVideoAdAdapter, int i) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzane.zzck("Adapter called onAdFailedToLoad.");
        try {
            this.zzcmj.zzd(ObjectWrapper.wrap(mediationRewardedVideoAdAdapter), i);
        } catch (Throwable e) {
            zzane.zzd("#007 Could not call remote method.", e);
        }
    }

    public final void onAdLeftApplication(MediationRewardedVideoAdAdapter mediationRewardedVideoAdAdapter) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzane.zzck("Adapter called onAdLeftApplication.");
        try {
            this.zzcmj.zzw(ObjectWrapper.wrap(mediationRewardedVideoAdAdapter));
        } catch (Throwable e) {
            zzane.zzd("#007 Could not call remote method.", e);
        }
    }

    public final void onAdLoaded(MediationRewardedVideoAdAdapter mediationRewardedVideoAdAdapter) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzane.zzck("Adapter called onAdLoaded.");
        try {
            this.zzcmj.zzr(ObjectWrapper.wrap(mediationRewardedVideoAdAdapter));
        } catch (Throwable e) {
            zzane.zzd("#007 Could not call remote method.", e);
        }
    }

    public final void onAdOpened(MediationRewardedVideoAdAdapter mediationRewardedVideoAdAdapter) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzane.zzck("Adapter called onAdOpened.");
        try {
            this.zzcmj.zzs(ObjectWrapper.wrap(mediationRewardedVideoAdAdapter));
        } catch (Throwable e) {
            zzane.zzd("#007 Could not call remote method.", e);
        }
    }

    public final void onInitializationFailed(MediationRewardedVideoAdAdapter mediationRewardedVideoAdAdapter, int i) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzane.zzck("Adapter called onInitializationFailed.");
        try {
            this.zzcmj.zzc(ObjectWrapper.wrap(mediationRewardedVideoAdAdapter), i);
        } catch (Throwable e) {
            zzane.zzd("#007 Could not call remote method.", e);
        }
    }

    public final void onInitializationSucceeded(MediationRewardedVideoAdAdapter mediationRewardedVideoAdAdapter) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzane.zzck("Adapter called onInitializationSucceeded.");
        try {
            this.zzcmj.zzq(ObjectWrapper.wrap(mediationRewardedVideoAdAdapter));
        } catch (Throwable e) {
            zzane.zzd("#007 Could not call remote method.", e);
        }
    }

    public final void onRewarded(MediationRewardedVideoAdAdapter mediationRewardedVideoAdAdapter, RewardItem rewardItem) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzane.zzck("Adapter called onRewarded.");
        if (rewardItem != null) {
            try {
                this.zzcmj.zza(ObjectWrapper.wrap(mediationRewardedVideoAdAdapter), new zzaig(rewardItem));
                return;
            } catch (Throwable e) {
                zzane.zzd("#007 Could not call remote method.", e);
                return;
            }
        }
        this.zzcmj.zza(ObjectWrapper.wrap(mediationRewardedVideoAdAdapter), new zzaig("", 1));
    }

    public final void onVideoCompleted(MediationRewardedVideoAdAdapter mediationRewardedVideoAdAdapter) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzane.zzck("Adapter called onVideoCompleted.");
        try {
            this.zzcmj.zzx(ObjectWrapper.wrap(mediationRewardedVideoAdAdapter));
        } catch (Throwable e) {
            zzane.zzd("#007 Could not call remote method.", e);
        }
    }

    public final void onVideoStarted(MediationRewardedVideoAdAdapter mediationRewardedVideoAdAdapter) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzane.zzck("Adapter called onVideoStarted.");
        try {
            this.zzcmj.zzt(ObjectWrapper.wrap(mediationRewardedVideoAdAdapter));
        } catch (Throwable e) {
            zzane.zzd("#007 Could not call remote method.", e);
        }
    }

    public final void zzc(Bundle bundle) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzane.zzck("Adapter called onAdMetadataChanged.");
        try {
            this.zzcmj.zzc(bundle);
        } catch (Throwable e) {
            zzane.zzd("#007 Could not call remote method.", e);
        }
    }
}
