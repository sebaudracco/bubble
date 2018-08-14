package com.fyber.mediation.admob.banner;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.cuebiq.cuebiqsdk.model.config.Settings;
import com.fyber.ads.banners.BannerSize;
import com.fyber.ads.banners.mediation.BannerMediationAdapter;
import com.fyber.mediation.admob.AdMobMediationAdapter;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import cz.msebera.android.httpclient.HttpStatus;
import java.util.List;

public class AdMobBannerMediationAdapter extends BannerMediationAdapter<AdMobMediationAdapter> {
    private final String adUnitId;
    private AdMobMediationAdapter adapter;
    private Handler handler = new Handler(Looper.getMainLooper());

    public AdMobBannerMediationAdapter(AdMobMediationAdapter adapter, String banId) {
        super(adapter);
        this.adUnitId = banId;
        this.adapter = adapter;
    }

    protected boolean checkForAds(final Context context, final List<BannerSize> bannerSizes) {
        this.handler.post(new Runnable() {
            public void run() {
                AdSize adSize = AdMobBannerMediationAdapter.this.mapBannerSize(bannerSizes);
                if (adSize != null) {
                    final AdView adView = new AdView(context);
                    adView.setAdUnitId(AdMobBannerMediationAdapter.this.adUnitId);
                    adView.setAdSize(adSize);
                    adView.setAdListener(new AdListener() {
                        public void onAdLoaded() {
                            AdMobBannerMediationAdapter.this.setAdAvailable(new AdMobBannerWrapper(adView));
                        }

                        public void onAdFailedToLoad(int errorCode) {
                            switch (errorCode) {
                                case 0:
                                    AdMobBannerMediationAdapter.this.setAdError("Error: internal error");
                                    return;
                                case 1:
                                    AdMobBannerMediationAdapter.this.setAdError("Error: invalid request");
                                    return;
                                case 2:
                                    AdMobBannerMediationAdapter.this.setAdError("Error: network error");
                                    return;
                                case 3:
                                    AdMobBannerMediationAdapter.this.setAdNotAvailable();
                                    return;
                                default:
                                    AdMobBannerMediationAdapter.this.setAdNotAvailable();
                                    return;
                            }
                        }
                    });
                    adView.loadAd(AdMobBannerMediationAdapter.this.adapter.getRequestBuildHelper().generateRequest());
                }
            }
        });
        return true;
    }

    private AdSize mapBannerSize(List<BannerSize> bannerSizes) {
        if (bannerSizes.isEmpty()) {
            return AdSize.SMART_BANNER;
        }
        for (BannerSize bannerSize : bannerSizes) {
            if (bannerSize.equals(BannerSize.FIXED_SIZE_320_50)) {
                return AdSize.BANNER;
            }
            if (bannerSize.getWidth() == 320 && bannerSize.getHeight() == 100) {
                return AdSize.LARGE_BANNER;
            }
            if (bannerSize.getWidth() == HttpStatus.SC_MULTIPLE_CHOICES && bannerSize.getHeight() == 250) {
                return AdSize.MEDIUM_RECTANGLE;
            }
            if (bannerSize.equals(BannerSize.FLUID_SIZE)) {
                return AdSize.FLUID;
            }
            if (bannerSize.equals(BannerSize.SMART_SIZE)) {
                return AdSize.SMART_BANNER;
            }
            if (bannerSize.getWidth() == 468 && bannerSize.getHeight() == 60) {
                return AdSize.FULL_BANNER;
            }
            if (bannerSize.getWidth() == 728 && bannerSize.getHeight() == 90) {
                return AdSize.LEADERBOARD;
            }
            if (bannerSize.getWidth() == 160 && bannerSize.getHeight() == Settings.MAX_DYNAMIC_ACQUISITION) {
                return AdSize.WIDE_SKYSCRAPER;
            }
        }
        return AdSize.SMART_BANNER;
    }
}
