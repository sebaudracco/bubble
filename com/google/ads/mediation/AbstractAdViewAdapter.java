package com.google.ads.mediation;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdRequest.Builder;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.VideoController;
import com.google.android.gms.ads.formats.NativeAdOptions;
import com.google.android.gms.ads.formats.NativeCustomTemplateAd.OnCustomTemplateAdLoadedListener;
import com.google.android.gms.ads.mediation.MediationAdRequest;
import com.google.android.gms.ads.mediation.MediationAdapter;
import com.google.android.gms.ads.mediation.MediationBannerAdapter;
import com.google.android.gms.ads.mediation.MediationBannerListener;
import com.google.android.gms.ads.mediation.MediationInterstitialListener;
import com.google.android.gms.ads.mediation.MediationNativeAdapter;
import com.google.android.gms.ads.mediation.MediationNativeListener;
import com.google.android.gms.ads.mediation.NativeMediationAdRequest;
import com.google.android.gms.ads.mediation.OnImmersiveModeUpdatedListener;
import com.google.android.gms.ads.mediation.zza;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import com.google.android.gms.ads.reward.mediation.MediationRewardedVideoAdAdapter;
import com.google.android.gms.ads.reward.mediation.MediationRewardedVideoAdListener;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.ads.zzadh;
import com.google.android.gms.internal.ads.zzamu;
import com.google.android.gms.internal.ads.zzane;
import com.google.android.gms.internal.ads.zzatm;
import com.google.android.gms.internal.ads.zzkb;
import com.google.android.gms.internal.ads.zzlo;
import java.util.Date;
import java.util.Set;

@zzadh
public abstract class AbstractAdViewAdapter implements MediationBannerAdapter, MediationNativeAdapter, OnImmersiveModeUpdatedListener, zza, MediationRewardedVideoAdAdapter, zzatm {
    public static final String AD_UNIT_ID_PARAMETER = "pubid";
    private AdView zzgw;
    private InterstitialAd zzgx;
    private AdLoader zzgy;
    private Context zzgz;
    private InterstitialAd zzha;
    private MediationRewardedVideoAdListener zzhb;
    @VisibleForTesting
    private final RewardedVideoAdListener zzhc = new zza(this);

    private final AdRequest zza(Context context, MediationAdRequest mediationAdRequest, Bundle bundle, Bundle bundle2) {
        Builder builder = new Builder();
        Date birthday = mediationAdRequest.getBirthday();
        if (birthday != null) {
            builder.setBirthday(birthday);
        }
        int gender = mediationAdRequest.getGender();
        if (gender != 0) {
            builder.setGender(gender);
        }
        Set<String> keywords = mediationAdRequest.getKeywords();
        if (keywords != null) {
            for (String addKeyword : keywords) {
                builder.addKeyword(addKeyword);
            }
        }
        Location location = mediationAdRequest.getLocation();
        if (location != null) {
            builder.setLocation(location);
        }
        if (mediationAdRequest.isTesting()) {
            zzkb.zzif();
            builder.addTestDevice(zzamu.zzbc(context));
        }
        if (mediationAdRequest.taggedForChildDirectedTreatment() != -1) {
            builder.tagForChildDirectedTreatment(mediationAdRequest.taggedForChildDirectedTreatment() == 1);
        }
        builder.setIsDesignedForFamilies(mediationAdRequest.isDesignedForFamilies());
        builder.addNetworkExtrasBundle(AdMobAdapter.class, zza(bundle, bundle2));
        return builder.build();
    }

    public String getAdUnitId(Bundle bundle) {
        return bundle.getString(AD_UNIT_ID_PARAMETER);
    }

    public View getBannerView() {
        return this.zzgw;
    }

    public Bundle getInterstitialAdapterInfo() {
        return new MediationAdapter.zza().zzaj(1).zzvx();
    }

    public zzlo getVideoController() {
        if (this.zzgw != null) {
            VideoController videoController = this.zzgw.getVideoController();
            if (videoController != null) {
                return videoController.zzbc();
            }
        }
        return null;
    }

    public void initialize(Context context, MediationAdRequest mediationAdRequest, String str, MediationRewardedVideoAdListener mediationRewardedVideoAdListener, Bundle bundle, Bundle bundle2) {
        this.zzgz = context.getApplicationContext();
        this.zzhb = mediationRewardedVideoAdListener;
        this.zzhb.onInitializationSucceeded(this);
    }

    public boolean isInitialized() {
        return this.zzhb != null;
    }

    public void loadAd(MediationAdRequest mediationAdRequest, Bundle bundle, Bundle bundle2) {
        if (this.zzgz == null || this.zzhb == null) {
            zzane.m3427e("AdMobAdapter.loadAd called before initialize.");
            return;
        }
        this.zzha = new InterstitialAd(this.zzgz);
        this.zzha.zza(true);
        this.zzha.setAdUnitId(getAdUnitId(bundle));
        this.zzha.setRewardedVideoAdListener(this.zzhc);
        this.zzha.zza(new zzb(this));
        this.zzha.loadAd(zza(this.zzgz, mediationAdRequest, bundle2, bundle));
    }

    public void onDestroy() {
        if (this.zzgw != null) {
            this.zzgw.destroy();
            this.zzgw = null;
        }
        if (this.zzgx != null) {
            this.zzgx = null;
        }
        if (this.zzgy != null) {
            this.zzgy = null;
        }
        if (this.zzha != null) {
            this.zzha = null;
        }
    }

    public void onImmersiveModeUpdated(boolean z) {
        if (this.zzgx != null) {
            this.zzgx.setImmersiveMode(z);
        }
        if (this.zzha != null) {
            this.zzha.setImmersiveMode(z);
        }
    }

    public void onPause() {
        if (this.zzgw != null) {
            this.zzgw.pause();
        }
    }

    public void onResume() {
        if (this.zzgw != null) {
            this.zzgw.resume();
        }
    }

    public void requestBannerAd(Context context, MediationBannerListener mediationBannerListener, Bundle bundle, AdSize adSize, MediationAdRequest mediationAdRequest, Bundle bundle2) {
        this.zzgw = new AdView(context);
        this.zzgw.setAdSize(new AdSize(adSize.getWidth(), adSize.getHeight()));
        this.zzgw.setAdUnitId(getAdUnitId(bundle));
        this.zzgw.setAdListener(new zzd(this, mediationBannerListener));
        this.zzgw.loadAd(zza(context, mediationAdRequest, bundle2, bundle));
    }

    public void requestInterstitialAd(Context context, MediationInterstitialListener mediationInterstitialListener, Bundle bundle, MediationAdRequest mediationAdRequest, Bundle bundle2) {
        this.zzgx = new InterstitialAd(context);
        this.zzgx.setAdUnitId(getAdUnitId(bundle));
        this.zzgx.setAdListener(new zze(this, mediationInterstitialListener));
        this.zzgx.loadAd(zza(context, mediationAdRequest, bundle2, bundle));
    }

    public void requestNativeAd(Context context, MediationNativeListener mediationNativeListener, Bundle bundle, NativeMediationAdRequest nativeMediationAdRequest, Bundle bundle2) {
        OnCustomTemplateAdLoadedListener com_google_ads_mediation_AbstractAdViewAdapter_zzf = new zzf(this, mediationNativeListener);
        AdLoader.Builder withAdListener = new AdLoader.Builder(context, bundle.getString(AD_UNIT_ID_PARAMETER)).withAdListener(com_google_ads_mediation_AbstractAdViewAdapter_zzf);
        NativeAdOptions nativeAdOptions = nativeMediationAdRequest.getNativeAdOptions();
        if (nativeAdOptions != null) {
            withAdListener.withNativeAdOptions(nativeAdOptions);
        }
        if (nativeMediationAdRequest.isUnifiedNativeAdRequested()) {
            withAdListener.forUnifiedNativeAd(com_google_ads_mediation_AbstractAdViewAdapter_zzf);
        }
        if (nativeMediationAdRequest.isAppInstallAdRequested()) {
            withAdListener.forAppInstallAd(com_google_ads_mediation_AbstractAdViewAdapter_zzf);
        }
        if (nativeMediationAdRequest.isContentAdRequested()) {
            withAdListener.forContentAd(com_google_ads_mediation_AbstractAdViewAdapter_zzf);
        }
        if (nativeMediationAdRequest.zzna()) {
            for (String str : nativeMediationAdRequest.zznb().keySet()) {
                withAdListener.forCustomTemplateAd(str, com_google_ads_mediation_AbstractAdViewAdapter_zzf, ((Boolean) nativeMediationAdRequest.zznb().get(str)).booleanValue() ? com_google_ads_mediation_AbstractAdViewAdapter_zzf : null);
            }
        }
        this.zzgy = withAdListener.build();
        this.zzgy.loadAd(zza(context, nativeMediationAdRequest, bundle2, bundle));
    }

    public void showInterstitial() {
        this.zzgx.show();
    }

    public void showVideo() {
        this.zzha.show();
    }

    protected abstract Bundle zza(Bundle bundle, Bundle bundle2);
}
