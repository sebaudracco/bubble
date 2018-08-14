package com.fyber.mediation.admob;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.fyber.ads.videos.mediation.RewardedVideoMediationAdapter;
import com.fyber.mediation.MediationAdapter;
import com.fyber.mediation.admob.banner.AdMobBannerMediationAdapter;
import com.fyber.mediation.admob.interstitial.AdMobInterstitialMediationAdapter;
import com.fyber.mediation.admob.rv.AdMobVideoMediationAdapter;
import com.fyber.mediation.admob.util.AdMobRequestBuildHelper;
import com.fyber.mediation.annotations.AdapterDefinition;
import com.fyber.utils.FyberLogger;
import com.fyber.utils.StringUtils;
import com.google.android.gms.ads.MobileAds;
import java.util.Map;
import java.util.Set;

@AdapterDefinition(apiVersion = 5, name = "AdMob", sdkFeatures = {"banners", "blended"}, version = "15.0.0-r1")
public class AdMobMediationAdapter extends MediationAdapter {
    public static final String ADAPTER_NAME = "AdMob";
    public static final String ADAPTER_VERSION = "15.0.0-r1";
    public static final String APP_ID = "app.id";
    public static final String BANNER_AD_UNIT_ID = "banner.ad.unit.id";
    public static final String BIRTHDAY_KEY = "birthday";
    public static final String BUILDER_CONFIG_ADD_TEST_DEVICE = "addTestDevice";
    public static final String COPPA_COMPLIANT = "isCOPPAcompliant";
    public static final String GENDER_KEY = "gender";
    public static final String INT_AD_UNIT_ID = "ad.unit.id";
    public static final String LOCATION_KEY = "location";
    public static final String REQUEST_AGENT = "fyber_mediation";
    public static final String RV_AD_UNIT_ID = "rewarded.video.ad.unit.id";
    private static final String TAG = AdMobMediationAdapter.class.getSimpleName();
    private Boolean isCoppaCompliant;
    private AdMobBannerMediationAdapter mBannerAdapter;
    private final Handler mHandler = new Handler(Looper.getMainLooper());
    private AdMobInterstitialMediationAdapter mInterstitialAdapter;
    private AdMobVideoMediationAdapter mVideoAdapter;
    private AdMobRequestBuildHelper requestBuildHelper;

    public boolean startAdapter(Activity activity, Map<String, Object> configs) {
        FyberLogger.i(TAG, "Starting mediation adapter AdMob 15.0.0-r1");
        String adMobAppId = (String) getConfiguration(configs, "app.id", String.class);
        if (StringUtils.nullOrEmpty(adMobAppId)) {
            Log.w(TAG, "Missing app.id parameter. It's recommended to add it in configuration in order to reduce time needed for the initial ad request.");
            MobileAds.initialize(activity.getApplicationContext());
        } else {
            MobileAds.initialize(activity.getApplicationContext(), adMobAppId);
        }
        String bannerAdUnitId = (String) getConfiguration(configs, BANNER_AD_UNIT_ID, String.class);
        String intAdUnitId = (String) getConfiguration(configs, INT_AD_UNIT_ID, String.class);
        String rvAdUnitId = (String) getConfiguration(configs, RV_AD_UNIT_ID, String.class);
        this.isCoppaCompliant = (Boolean) getConfiguration(configs, COPPA_COMPLIANT, Boolean.class);
        this.requestBuildHelper = new AdMobRequestBuildHelper(configs, this);
        this.mHandler.post(new 1(this, intAdUnitId, bannerAdUnitId, rvAdUnitId, activity));
        return true;
    }

    public AdMobRequestBuildHelper getRequestBuildHelper() {
        return this.requestBuildHelper;
    }

    public Boolean isCOPPACompliant() {
        return this.isCoppaCompliant;
    }

    public String getName() {
        return "AdMob";
    }

    public String getVersion() {
        return "15.0.0-r1";
    }

    public RewardedVideoMediationAdapter<AdMobMediationAdapter> getVideoMediationAdapter() {
        return this.mVideoAdapter;
    }

    public AdMobInterstitialMediationAdapter getInterstitialMediationAdapter() {
        return this.mInterstitialAdapter;
    }

    public AdMobBannerMediationAdapter getBannerMediationAdapter() {
        return this.mBannerAdapter;
    }

    protected Set<?> getListeners() {
        return null;
    }
}
