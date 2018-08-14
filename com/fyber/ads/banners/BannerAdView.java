package com.fyber.ads.banners;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import com.fyber.ads.Ad;
import com.fyber.ads.AdFormat;
import com.fyber.requesters.AdRequestCallback;
import com.fyber.requesters.BannerRequester;
import com.fyber.requesters.RequestError;
import com.fyber.utils.FyberLogger;

public class BannerAdView extends FrameLayout implements BannerAdListener, AdRequestCallback {
    private BannerRequester f6018a;
    private boolean f6019b;
    private Activity f6020c;
    private BannerAdListener f6021d;
    private BannerAd f6022e;

    public BannerAdView(Activity activity) {
        super(activity.getApplicationContext());
        this.f6019b = false;
        this.f6020c = activity;
        this.f6018a = BannerRequester.create(this);
    }

    public BannerAdView(Activity activity, AttributeSet attributeSet) {
        this(activity, attributeSet, 0);
    }

    public BannerAdView(Activity activity, AttributeSet attributeSet, int i) {
        super(activity, attributeSet, i);
        this.f6019b = false;
        this.f6020c = activity;
        this.f6018a = BannerRequester.create(this);
    }

    public BannerAdView withNetworkSize(@NonNull NetworkBannerSize networkBannerSize) {
        if (this.f6018a != null) {
            this.f6018a.withNetworkSize(networkBannerSize);
        } else if (this.f6020c != null) {
            FyberLogger.m8448d("BannerAdView", "This BannerAdView appears to be already displayed - this will have no effect on this instance");
        } else {
            FyberLogger.m8448d("BannerAdView", "This BannerAdView appears to have been already destroyed");
            onAdError(null, "The \"destroy()\" method appears to have been already called");
        }
        return this;
    }

    public BannerAdView withListener(@NonNull BannerAdListener bannerAdListener) {
        this.f6021d = bannerAdListener;
        return this;
    }

    public BannerAdView withPlacementId(String str) {
        if (this.f6018a != null) {
            this.f6018a.withPlacementId(str);
        } else if (this.f6020c != null) {
            FyberLogger.m8448d("BannerAdView", "This BannerAdView appears to be already displayed - this will have no effect on this instance");
        } else {
            FyberLogger.m8448d("BannerAdView", "This BannerAdView appears to have been already destroyed");
            onAdError(null, "The \"destroy()\" method appears to have been already called");
        }
        return this;
    }

    public BannerAdView loadOnAttach() {
        if (this.f6018a != null) {
            this.f6019b = true;
        } else if (this.f6020c != null) {
            FyberLogger.m8448d("BannerAdView", "This BannerAdView appears to be already displayed - this will have no effect on this instance");
        } else {
            FyberLogger.m8448d("BannerAdView", "This BannerAdView appears to have been already destroyed");
            onAdError(null, "The \"destroy()\" method appears to have been already called");
        }
        return this;
    }

    public void load() {
        if (this.f6019b || this.f6018a == null) {
            FyberLogger.m8448d("BannerAdView", "There's no BannerWrapper for this BannerAd - this banner will not be shown");
            onAdError(null, "The \"destroy()\" method appears to have been already called");
            return;
        }
        this.f6018a.request(getContext());
        this.f6018a = null;
    }

    public void destroy() {
        if (this.f6020c != null) {
            this.f6020c = null;
            if (this.f6022e != null) {
                this.f6022e.destroy();
                return;
            }
            return;
        }
        FyberLogger.m8448d("BannerAdView", "This BannerAdView appears to have been already destroyed");
        onAdError(null, "The \"destroy()\" method appears to have been already called");
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.f6019b && this.f6018a != null) {
            this.f6018a.request(getContext());
            this.f6018a = null;
        }
    }

    public void onAdAvailable(Ad ad) {
        if (ad.getAdFormat() == AdFormat.BANNER) {
            this.f6022e = (BannerAd) ad;
            ((BannerAd) this.f6022e.displayInView(this).withListener(this)).start(this.f6020c);
            return;
        }
        FyberLogger.m8448d("BannerAdView", "The ad received is not a Banner. ¯\\_(ツ)_/¯ ");
        onAdError(null, "Error occurred while requesting a banner");
    }

    public void onAdNotAvailable(AdFormat adFormat) {
        if (adFormat == AdFormat.BANNER) {
            onAdError(null, "No banner available");
            return;
        }
        FyberLogger.m8448d("BannerAdView", "The ad received is not a Banner. ¯\\_(ツ)_/¯ ");
        onAdError(null, "Error occurred while requesting a banner");
    }

    public void onRequestError(RequestError requestError) {
        FyberLogger.m8448d("BannerAdView", "Error while requesting - " + requestError.getDescription());
        onAdError(null, "Error occurred while requesting a banner - " + requestError.getDescription());
    }

    public void onAdLoaded(BannerAd bannerAd) {
        if (this.f6021d != null) {
            this.f6021d.onAdLoaded(bannerAd);
        } else {
            FyberLogger.m8448d("BannerAdView", "onAdLoaded was called");
        }
    }

    public void onAdClicked(BannerAd bannerAd) {
        if (this.f6021d != null) {
            this.f6021d.onAdClicked(bannerAd);
        } else {
            FyberLogger.m8448d("BannerAdView", "onAdClicked was called");
        }
    }

    public void onAdError(BannerAd bannerAd, String str) {
        if (this.f6021d != null) {
            this.f6021d.onAdError(bannerAd, str);
        } else {
            FyberLogger.m8448d("BannerAdView", "onAdError was called");
        }
    }

    public void onAdLeftApplication(BannerAd bannerAd) {
        if (this.f6021d != null) {
            this.f6021d.onAdLeftApplication(bannerAd);
        } else {
            FyberLogger.m8448d("BannerAdView", "onAdLeftApplication was called");
        }
    }
}
