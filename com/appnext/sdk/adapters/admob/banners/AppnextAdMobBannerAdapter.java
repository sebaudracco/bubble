package com.appnext.sdk.adapters.admob.banners;

import android.content.Context;
import android.os.Bundle;
import com.appnext.banners.BannerAdRequest;
import com.appnext.banners.BannerListener;
import com.appnext.banners.BannerSize;
import com.appnext.banners.BannerView;
import com.appnext.core.AppnextError;
import com.appnext.core.C1128g;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.mediation.MediationAdRequest;
import com.google.android.gms.ads.mediation.customevent.CustomEventBanner;
import com.google.android.gms.ads.mediation.customevent.CustomEventBannerListener;

public class AppnextAdMobBannerAdapter implements CustomEventBanner {
    public static final String AppNextBannerAdRequestKey = "AppNextBannerAdRequestKey";
    protected BannerView bannerView;

    public void onDestroy() {
        if (this.bannerView != null) {
            this.bannerView.destroy();
            this.bannerView = null;
        }
    }

    public void onPause() {
    }

    public void onResume() {
    }

    public void requestBannerAd(Context context, final CustomEventBannerListener customEventBannerListener, String str, AdSize adSize, MediationAdRequest mediationAdRequest, Bundle bundle) {
        try {
            if (this.bannerView != null) {
                this.bannerView.destroy();
                this.bannerView = null;
            }
            this.bannerView = new AppnextAdmobBannerView(context);
            this.bannerView.setPlacementId(str);
            if (adSize.getWidth() == BannerSize.BANNER.getWidth() && adSize.getHeight() == BannerSize.BANNER.getHeight()) {
                this.bannerView.setBannerSize(BannerSize.BANNER);
            } else if (adSize.getWidth() == BannerSize.LARGE_BANNER.getWidth() && adSize.getHeight() == BannerSize.LARGE_BANNER.getHeight()) {
                this.bannerView.setBannerSize(BannerSize.LARGE_BANNER);
            } else if (adSize.getWidth() == BannerSize.MEDIUM_RECTANGLE.getWidth() && adSize.getHeight() == BannerSize.MEDIUM_RECTANGLE.getHeight()) {
                this.bannerView.setBannerSize(BannerSize.MEDIUM_RECTANGLE);
            } else {
                throw new IllegalArgumentException("Wrong size");
            }
            this.bannerView.setBannerListener(new BannerListener() {
                public void onAdLoaded(String str) {
                    customEventBannerListener.onAdLoaded(AppnextAdMobBannerAdapter.this.bannerView);
                    super.onAdLoaded(str);
                }

                public void onAdClicked() {
                    customEventBannerListener.onAdClicked();
                    super.onAdClicked();
                }

                public void onError(AppnextError appnextError) {
                    super.onError(appnextError);
                    String errorMessage = appnextError.getErrorMessage();
                    int i = -1;
                    switch (errorMessage.hashCode()) {
                        case -1958363695:
                            if (errorMessage.equals(AppnextError.NO_ADS)) {
                                i = 3;
                                break;
                            }
                            break;
                        case -1477010874:
                            if (errorMessage.equals(AppnextError.CONNECTION_ERROR)) {
                                i = 2;
                                break;
                            }
                            break;
                        case 350741825:
                            if (errorMessage.equals("Timeout")) {
                                i = 1;
                                break;
                            }
                            break;
                        case 844170097:
                            if (errorMessage.equals(AppnextError.SLOW_CONNECTION)) {
                                i = 0;
                                break;
                            }
                            break;
                    }
                    switch (i) {
                        case 0:
                        case 1:
                        case 2:
                            customEventBannerListener.onAdFailedToLoad(2);
                            return;
                        case 3:
                            customEventBannerListener.onAdFailedToLoad(3);
                            return;
                        default:
                            customEventBannerListener.onAdFailedToLoad(0);
                            return;
                    }
                }

                public void adImpression() {
                    super.adImpression();
                }
            });
            this.bannerView.loadAd((BannerAdRequest) bundle.getSerializable(AppNextBannerAdRequestKey));
        } catch (Throwable th) {
            C1128g.m2351c(th);
        }
    }
}
