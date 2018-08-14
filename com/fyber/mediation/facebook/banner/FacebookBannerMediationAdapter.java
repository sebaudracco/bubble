package com.fyber.mediation.facebook.banner;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AdListener;
import com.facebook.ads.AdSize;
import com.facebook.ads.AdView;
import com.fyber.ads.banners.BannerSize;
import com.fyber.ads.banners.mediation.BannerMediationAdapter;
import com.fyber.mediation.facebook.FacebookMediationAdapter;
import com.fyber.utils.FyberLogger;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FacebookBannerMediationAdapter extends BannerMediationAdapter<FacebookMediationAdapter> implements AdListener {
    private static final String TAG = FacebookBannerMediationAdapter.class.getSimpleName();
    private static final Map<BannerSize, AdSize> bannerSizeMapping = new HashMap(3);
    private Handler handler = new Handler(Looper.getMainLooper());
    private final String placementId;

    class BannerRequest implements Runnable {
        WeakReference<Context> context;
        List<BannerSize> sizes;

        BannerRequest(Context context, List<BannerSize> bannerSizes) {
            this.context = new WeakReference(context);
            this.sizes = bannerSizes;
        }

        public void run() {
            AdView adView = new AdView((Context) this.context.get(), FacebookBannerMediationAdapter.this.placementId, FacebookBannerMediationAdapter.this.mapBannerSize(this.sizes));
            adView.setAdListener(FacebookBannerMediationAdapter.this);
            adView.loadAd();
        }
    }

    static {
        bannerSizeMapping.put(FacebookBannerSize.HEIGHT_50, AdSize.BANNER_HEIGHT_50);
        bannerSizeMapping.put(FacebookBannerSize.HEIGHT_90, AdSize.BANNER_HEIGHT_90);
        bannerSizeMapping.put(FacebookBannerSize.RECTANGLE_300_250, AdSize.RECTANGLE_HEIGHT_250);
    }

    public FacebookBannerMediationAdapter(FacebookMediationAdapter adapter, String placementId) {
        super(adapter);
        this.placementId = placementId;
    }

    protected boolean checkForAds(Context context, List<BannerSize> bannerSizes) {
        this.handler.post(new BannerRequest(context, bannerSizes));
        return true;
    }

    public void onError(Ad ad, AdError adError) {
        if (adError.getErrorCode() == 1001) {
            FyberLogger.m8451i(TAG, "Callback message from Facebook (code " + adError.getErrorCode() + "): " + adError.getErrorMessage());
            setAdNotAvailable();
            return;
        }
        setAdError("Facebook ad error (" + adError.getErrorCode() + "): " + adError.getErrorMessage());
    }

    public void onAdLoaded(Ad ad) {
        setAdAvailable(new FacebookBannerWrapper((AdView) ad));
    }

    public void onAdClicked(Ad ad) {
    }

    public void onLoggingImpression(Ad ad) {
    }

    private AdSize mapBannerSize(List<BannerSize> bannerSizes) {
        AdSize defaultSize = AdSize.BANNER_HEIGHT_50;
        if (bannerSizes.isEmpty()) {
            return defaultSize;
        }
        for (BannerSize bannerSize : bannerSizes) {
            AdSize adSize = (AdSize) bannerSizeMapping.get(bannerSize);
            if (adSize != null) {
                return adSize;
            }
        }
        FyberLogger.m8453w(TAG, "Warning: invalid banner size provided, returning default BANNER_HEIGHT_50");
        return defaultSize;
    }
}
