package com.google.android.gms.internal.ads;

import com.google.ads.AdRequest.ErrorCode;
import com.google.ads.mediation.MediationBannerAdapter;
import com.google.ads.mediation.MediationBannerListener;
import com.google.ads.mediation.MediationInterstitialAdapter;
import com.google.ads.mediation.MediationInterstitialListener;
import com.google.ads.mediation.MediationServerParameters;
import com.google.ads.mediation.NetworkExtras;

@zzadh
public final class zzyq<NETWORK_EXTRAS extends NetworkExtras, SERVER_PARAMETERS extends MediationServerParameters> implements MediationBannerListener, MediationInterstitialListener {
    private final zzxt zzbuu;

    public zzyq(zzxt com_google_android_gms_internal_ads_zzxt) {
        this.zzbuu = com_google_android_gms_internal_ads_zzxt;
    }

    public final void onClick(MediationBannerAdapter<?, ?> mediationBannerAdapter) {
        zzane.zzck("Adapter called onClick.");
        zzkb.zzif();
        if (zzamu.zzsh()) {
            try {
                this.zzbuu.onAdClicked();
                return;
            } catch (Throwable e) {
                zzane.zzd("#007 Could not call remote method.", e);
                return;
            }
        }
        zzane.zzd("#008 Must be called on the main UI thread.", null);
        zzamu.zzsy.post(new zzyr(this));
    }

    public final void onDismissScreen(MediationBannerAdapter<?, ?> mediationBannerAdapter) {
        zzane.zzck("Adapter called onDismissScreen.");
        zzkb.zzif();
        if (zzamu.zzsh()) {
            try {
                this.zzbuu.onAdClosed();
                return;
            } catch (Throwable e) {
                zzane.zzd("#007 Could not call remote method.", e);
                return;
            }
        }
        zzane.zzdk("#008 Must be called on the main UI thread.");
        zzamu.zzsy.post(new zzyu(this));
    }

    public final void onDismissScreen(MediationInterstitialAdapter<?, ?> mediationInterstitialAdapter) {
        zzane.zzck("Adapter called onDismissScreen.");
        zzkb.zzif();
        if (zzamu.zzsh()) {
            try {
                this.zzbuu.onAdClosed();
                return;
            } catch (Throwable e) {
                zzane.zzd("#007 Could not call remote method.", e);
                return;
            }
        }
        zzane.zzd("#008 Must be called on the main UI thread.", null);
        zzamu.zzsy.post(new zzyz(this));
    }

    public final void onFailedToReceiveAd(MediationBannerAdapter<?, ?> mediationBannerAdapter, ErrorCode errorCode) {
        String valueOf = String.valueOf(errorCode);
        zzane.zzck(new StringBuilder(String.valueOf(valueOf).length() + 47).append("Adapter called onFailedToReceiveAd with error. ").append(valueOf).toString());
        zzkb.zzif();
        if (zzamu.zzsh()) {
            try {
                this.zzbuu.onAdFailedToLoad(zzzc.zza(errorCode));
                return;
            } catch (Throwable e) {
                zzane.zzd("#007 Could not call remote method.", e);
                return;
            }
        }
        zzane.zzd("#008 Must be called on the main UI thread.", null);
        zzamu.zzsy.post(new zzyv(this, errorCode));
    }

    public final void onFailedToReceiveAd(MediationInterstitialAdapter<?, ?> mediationInterstitialAdapter, ErrorCode errorCode) {
        String valueOf = String.valueOf(errorCode);
        zzane.zzck(new StringBuilder(String.valueOf(valueOf).length() + 47).append("Adapter called onFailedToReceiveAd with error ").append(valueOf).append(".").toString());
        zzkb.zzif();
        if (zzamu.zzsh()) {
            try {
                this.zzbuu.onAdFailedToLoad(zzzc.zza(errorCode));
                return;
            } catch (Throwable e) {
                zzane.zzd("#007 Could not call remote method.", e);
                return;
            }
        }
        zzane.zzd("#008 Must be called on the main UI thread.", null);
        zzamu.zzsy.post(new zzza(this, errorCode));
    }

    public final void onLeaveApplication(MediationBannerAdapter<?, ?> mediationBannerAdapter) {
        zzane.zzck("Adapter called onLeaveApplication.");
        zzkb.zzif();
        if (zzamu.zzsh()) {
            try {
                this.zzbuu.onAdLeftApplication();
                return;
            } catch (Throwable e) {
                zzane.zzd("#007 Could not call remote method.", e);
                return;
            }
        }
        zzane.zzd("#008 Must be called on the main UI thread.", null);
        zzamu.zzsy.post(new zzyw(this));
    }

    public final void onLeaveApplication(MediationInterstitialAdapter<?, ?> mediationInterstitialAdapter) {
        zzane.zzck("Adapter called onLeaveApplication.");
        zzkb.zzif();
        if (zzamu.zzsh()) {
            try {
                this.zzbuu.onAdLeftApplication();
                return;
            } catch (Throwable e) {
                zzane.zzd("#007 Could not call remote method.", e);
                return;
            }
        }
        zzane.zzd("#008 Must be called on the main UI thread.", null);
        zzamu.zzsy.post(new zzzb(this));
    }

    public final void onPresentScreen(MediationBannerAdapter<?, ?> mediationBannerAdapter) {
        zzane.zzck("Adapter called onPresentScreen.");
        zzkb.zzif();
        if (zzamu.zzsh()) {
            try {
                this.zzbuu.onAdOpened();
                return;
            } catch (Throwable e) {
                zzane.zzd("#007 Could not call remote method.", e);
                return;
            }
        }
        zzane.zzd("#008 Must be called on the main UI thread.", null);
        zzamu.zzsy.post(new zzyx(this));
    }

    public final void onPresentScreen(MediationInterstitialAdapter<?, ?> mediationInterstitialAdapter) {
        zzane.zzck("Adapter called onPresentScreen.");
        zzkb.zzif();
        if (zzamu.zzsh()) {
            try {
                this.zzbuu.onAdOpened();
                return;
            } catch (Throwable e) {
                zzane.zzd("#007 Could not call remote method.", e);
                return;
            }
        }
        zzane.zzd("#008 Must be called on the main UI thread.", null);
        zzamu.zzsy.post(new zzys(this));
    }

    public final void onReceivedAd(MediationBannerAdapter<?, ?> mediationBannerAdapter) {
        zzane.zzck("Adapter called onReceivedAd.");
        zzkb.zzif();
        if (zzamu.zzsh()) {
            try {
                this.zzbuu.onAdLoaded();
                return;
            } catch (Throwable e) {
                zzane.zzd("#007 Could not call remote method.", e);
                return;
            }
        }
        zzane.zzd("#008 Must be called on the main UI thread.", null);
        zzamu.zzsy.post(new zzyy(this));
    }

    public final void onReceivedAd(MediationInterstitialAdapter<?, ?> mediationInterstitialAdapter) {
        zzane.zzck("Adapter called onReceivedAd.");
        zzkb.zzif();
        if (zzamu.zzsh()) {
            try {
                this.zzbuu.onAdLoaded();
                return;
            } catch (Throwable e) {
                zzane.zzd("#007 Could not call remote method.", e);
                return;
            }
        }
        zzane.zzd("#008 Must be called on the main UI thread.", null);
        zzamu.zzsy.post(new zzyt(this));
    }
}
