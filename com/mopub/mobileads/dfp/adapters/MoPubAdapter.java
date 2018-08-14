package com.mopub.mobileads.dfp.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.formats.NativeAdOptions;
import com.google.android.gms.ads.mediation.MediationAdRequest;
import com.google.android.gms.ads.mediation.MediationBannerAdapter;
import com.google.android.gms.ads.mediation.MediationBannerListener;
import com.google.android.gms.ads.mediation.MediationInterstitialAdapter;
import com.google.android.gms.ads.mediation.MediationInterstitialListener;
import com.google.android.gms.ads.mediation.MediationNativeAdapter;
import com.google.android.gms.ads.mediation.MediationNativeListener;
import com.google.android.gms.ads.mediation.NativeAdMapper;
import com.google.android.gms.ads.mediation.NativeMediationAdRequest;
import com.mopub.common.logging.MoPubLog;
import com.mopub.mobileads.MoPubErrorCode;
import com.mopub.mobileads.MoPubInterstitial;
import com.mopub.mobileads.MoPubInterstitial$InterstitialAdListener;
import com.mopub.mobileads.MoPubView;
import com.mopub.mobileads.MoPubView$BannerAdListener;
import com.mopub.nativeads.BaseNativeAd;
import com.mopub.nativeads.MoPubNative;
import com.mopub.nativeads.MoPubNative$MoPubNativeNetworkListener;
import com.mopub.nativeads.MoPubStaticNativeAdRenderer;
import com.mopub.nativeads.NativeAd;
import com.mopub.nativeads.NativeAd.MoPubNativeEventListener;
import com.mopub.nativeads.NativeErrorCode;
import com.mopub.nativeads.RequestParameters;
import com.mopub.nativeads.RequestParameters.NativeAdAsset;
import com.mopub.nativeads.StaticNativeAd;
import com.mopub.nativeads.ViewBinder.Builder;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.EnumSet;
import java.util.HashMap;

public class MoPubAdapter implements MediationNativeAdapter, MediationBannerAdapter, MediationInterstitialAdapter {
    public static final double DEFAULT_MOPUB_IMAGE_SCALE = 1.0d;
    private static final int DEFAULT_MOPUB_PRIVACY_ICON_SIZE_DP = 20;
    private static final int MAXIMUM_MOPUB_PRIVACY_ICON_SIZE_DP = 30;
    private static final int MINIMUM_MOPUB_PRIVACY_ICON_SIZE_DP = 10;
    private static final String MOPUB_AD_UNIT_KEY = "adUnitId";
    private static final String MOPUB_NATIVE_CEVENT_VERSION = "gmext";
    public static final String TAG = MoPubAdapter.class.getSimpleName();
    private AdSize mAdSize;
    private MoPubInterstitial mMoPubInterstitial;
    private MoPubNativeEventListener mMoPubNativeEventListener;
    private MoPubView mMoPubView;
    private int mPrivacyIconSize;
    private int privacyIconPlacement;

    public static final class BundleBuilder {
        private static final String ARG_PRIVACY_ICON_SIZE_DP = "privacy_icon_size_dp";
        private int mPrivacyIconSizeDp;

        public BundleBuilder setPrivacyIconSize(int iconSizeDp) {
            this.mPrivacyIconSizeDp = iconSizeDp;
            return this;
        }

        public Bundle build() {
            Bundle bundle = new Bundle();
            bundle.putInt(ARG_PRIVACY_ICON_SIZE_DP, this.mPrivacyIconSizeDp);
            return bundle;
        }
    }

    private class MBannerListener implements MoPubView$BannerAdListener {
        private MediationBannerListener mMediationBannerListener;

        public MBannerListener(MediationBannerListener bannerListener) {
            this.mMediationBannerListener = bannerListener;
        }

        public void onBannerClicked(MoPubView moPubView) {
            this.mMediationBannerListener.onAdClicked(MoPubAdapter.this);
        }

        public void onBannerCollapsed(MoPubView moPubView) {
            this.mMediationBannerListener.onAdClosed(MoPubAdapter.this);
        }

        public void onBannerExpanded(MoPubView moPubView) {
            this.mMediationBannerListener.onAdOpened(MoPubAdapter.this);
        }

        public void onBannerFailed(MoPubView moPubView, MoPubErrorCode moPubErrorCode) {
            switch (moPubErrorCode) {
                case NO_FILL:
                    this.mMediationBannerListener.onAdFailedToLoad(MoPubAdapter.this, 3);
                    return;
                case NETWORK_TIMEOUT:
                    this.mMediationBannerListener.onAdFailedToLoad(MoPubAdapter.this, 2);
                    return;
                case SERVER_ERROR:
                    this.mMediationBannerListener.onAdFailedToLoad(MoPubAdapter.this, 1);
                    return;
                default:
                    this.mMediationBannerListener.onAdFailedToLoad(MoPubAdapter.this, 0);
                    return;
            }
        }

        public void onBannerLoaded(MoPubView moPubView) {
            if (!(MoPubAdapter.this.mAdSize.getWidth() == moPubView.getAdWidth() && MoPubAdapter.this.mAdSize.getHeight() == moPubView.getAdHeight())) {
                Log.w(MoPubAdapter.TAG, "The banner ad size loaded does not match the request size. Update the ad size on your MoPub UI to match the request size.");
            }
            this.mMediationBannerListener.onAdLoaded(MoPubAdapter.this);
        }
    }

    private class mMediationInterstitialListener implements MoPubInterstitial$InterstitialAdListener {
        private MediationInterstitialListener mMediationInterstitialListener;

        public mMediationInterstitialListener(MediationInterstitialListener interstitialListener) {
            this.mMediationInterstitialListener = interstitialListener;
        }

        public void onInterstitialClicked(MoPubInterstitial moPubInterstitial) {
            this.mMediationInterstitialListener.onAdClicked(MoPubAdapter.this);
        }

        public void onInterstitialDismissed(MoPubInterstitial moPubInterstitial) {
            this.mMediationInterstitialListener.onAdClosed(MoPubAdapter.this);
        }

        public void onInterstitialFailed(MoPubInterstitial moPubInterstitial, MoPubErrorCode moPubErrorCode) {
            switch (moPubErrorCode) {
                case NO_FILL:
                    this.mMediationInterstitialListener.onAdFailedToLoad(MoPubAdapter.this, 3);
                    return;
                case NETWORK_TIMEOUT:
                    this.mMediationInterstitialListener.onAdFailedToLoad(MoPubAdapter.this, 2);
                    return;
                case SERVER_ERROR:
                    this.mMediationInterstitialListener.onAdFailedToLoad(MoPubAdapter.this, 1);
                    return;
                default:
                    this.mMediationInterstitialListener.onAdFailedToLoad(MoPubAdapter.this, 0);
                    return;
            }
        }

        public void onInterstitialLoaded(MoPubInterstitial moPubInterstitial) {
            this.mMediationInterstitialListener.onAdLoaded(MoPubAdapter.this);
        }

        public void onInterstitialShown(MoPubInterstitial moPubInterstitial) {
            this.mMediationInterstitialListener.onAdOpened(MoPubAdapter.this);
        }
    }

    public void onDestroy() {
        if (this.mMoPubInterstitial != null) {
            this.mMoPubInterstitial.destroy();
            this.mMoPubInterstitial = null;
        }
        if (this.mMoPubView != null) {
            this.mMoPubView.destroy();
            this.mMoPubView = null;
        }
    }

    public void onPause() {
    }

    public void onResume() {
    }

    public void requestNativeAd(Context context, MediationNativeListener listener, Bundle serverParameters, NativeMediationAdRequest mediationAdRequest, Bundle mediationExtras) {
        String adunit = serverParameters.getString(MOPUB_AD_UNIT_KEY);
        NativeAdOptions options = mediationAdRequest.getNativeAdOptions();
        if (options != null) {
            this.privacyIconPlacement = options.getAdChoicesPlacement();
        } else {
            this.privacyIconPlacement = 1;
        }
        if (mediationAdRequest.isAppInstallAdRequested() || !mediationAdRequest.isContentAdRequested()) {
            if (mediationExtras != null) {
                int iconSizeExtra = mediationExtras.getInt("privacy_icon_size_dp");
                if (iconSizeExtra < 10) {
                    this.mPrivacyIconSize = 10;
                } else if (iconSizeExtra > 30) {
                    this.mPrivacyIconSize = 30;
                } else {
                    this.mPrivacyIconSize = iconSizeExtra;
                }
            } else {
                this.mPrivacyIconSize = 20;
            }
            final MediationNativeListener mediationNativeListener = listener;
            final Context context2 = context;
            MoPubNative$MoPubNativeNetworkListener moPubNativeNetworkListener = new MoPubNative$MoPubNativeNetworkListener() {
                public void onNativeLoad(NativeAd nativeAd) {
                    nativeAd.setMoPubNativeEventListener(MoPubAdapter.this.mMoPubNativeEventListener);
                    BaseNativeAd adData = nativeAd.getBaseNativeAd();
                    if (adData instanceof StaticNativeAd) {
                        final StaticNativeAd staticNativeAd = (StaticNativeAd) adData;
                        try {
                            HashMap<String, URL> map = new HashMap();
                            try {
                                map.put(DownloadDrawablesAsync.KEY_ICON, new URL(staticNativeAd.getIconImageUrl()));
                                map.put(DownloadDrawablesAsync.KEY_IMAGE, new URL(staticNativeAd.getMainImageUrl()));
                            } catch (MalformedURLException e) {
                                Log.d(MoPubAdapter.TAG, "Invalid ad response received from MoPub. Image URLs are invalid");
                                mediationNativeListener.onAdFailedToLoad(MoPubAdapter.this, 0);
                            }
                            new DownloadDrawablesAsync(new DrawableDownloadListener() {
                                public void onDownloadSuccess(HashMap<String, Drawable> drawableMap) {
                                    try {
                                        NativeAdMapper moPubNativeAppInstallAdMapper = new MoPubNativeAppInstallAdMapper(staticNativeAd, drawableMap, MoPubAdapter.this.privacyIconPlacement, MoPubAdapter.this.mPrivacyIconSize);
                                        ImageView imageView = new ImageView(context2);
                                        imageView.setImageDrawable((Drawable) drawableMap.get(DownloadDrawablesAsync.KEY_IMAGE));
                                        moPubNativeAppInstallAdMapper.setMediaView(imageView);
                                        mediationNativeListener.onAdLoaded(MoPubAdapter.this, moPubNativeAppInstallAdMapper);
                                    } catch (Exception e) {
                                        Log.d(MoPubAdapter.TAG, "Exception trying to download native ad drawables");
                                        mediationNativeListener.onAdFailedToLoad(MoPubAdapter.this, 0);
                                    }
                                }

                                public void onDownloadFailure() {
                                    mediationNativeListener.onAdFailedToLoad(MoPubAdapter.this, 0);
                                }
                            }).execute(new Object[]{map});
                        } catch (Exception e2) {
                            Log.d(MoPubAdapter.TAG, "Exception constructing the native ad");
                            mediationNativeListener.onAdFailedToLoad(MoPubAdapter.this, 0);
                        }
                    }
                }

                public void onNativeFail(NativeErrorCode errorCode) {
                    switch (errorCode) {
                        case EMPTY_AD_RESPONSE:
                            mediationNativeListener.onAdFailedToLoad(MoPubAdapter.this, 3);
                            return;
                        case INVALID_REQUEST_URL:
                            mediationNativeListener.onAdFailedToLoad(MoPubAdapter.this, 1);
                            return;
                        case CONNECTION_ERROR:
                            mediationNativeListener.onAdFailedToLoad(MoPubAdapter.this, 1);
                            return;
                        case UNSPECIFIED:
                            mediationNativeListener.onAdFailedToLoad(MoPubAdapter.this, 0);
                            return;
                        default:
                            mediationNativeListener.onAdFailedToLoad(MoPubAdapter.this, 0);
                            return;
                    }
                }
            };
            if (adunit == null) {
                Log.d(TAG, "Ad unit id is invalid. So failing the request.");
                listener.onAdFailedToLoad(this, 1);
                return;
            }
            MoPubNative moPubNative = new MoPubNative(context, adunit, moPubNativeNetworkListener);
            moPubNative.registerAdRenderer(new MoPubStaticNativeAdRenderer(new Builder(0).build()));
            moPubNative.makeRequest(new RequestParameters.Builder().keywords("gmextgender:" + mediationAdRequest.getGender() + ",age:" + mediationAdRequest.getBirthday()).location(mediationAdRequest.getLocation()).desiredAssets(EnumSet.of(NativeAdAsset.TITLE, NativeAdAsset.TEXT, NativeAdAsset.CALL_TO_ACTION_TEXT, NativeAdAsset.MAIN_IMAGE, NativeAdAsset.ICON_IMAGE)).build());
            mediationNativeListener = listener;
            this.mMoPubNativeEventListener = new MoPubNativeEventListener() {
                public void onImpression(View view) {
                    mediationNativeListener.onAdImpression(MoPubAdapter.this);
                    Log.d(MoPubAdapter.TAG, "onImpression");
                }

                public void onClick(View view) {
                    mediationNativeListener.onAdClicked(MoPubAdapter.this);
                    mediationNativeListener.onAdOpened(MoPubAdapter.this);
                    mediationNativeListener.onAdLeftApplication(MoPubAdapter.this);
                    Log.d(MoPubAdapter.TAG, "onClick");
                }
            };
            return;
        }
        Log.d(TAG, "Currently, MoPub only serves native app install ads. Apps requesting content ads alone will not receive ads from this adapter.");
        listener.onAdFailedToLoad(this, 1);
    }

    public void requestBannerAd(Context context, MediationBannerListener mediationBannerListener, Bundle bundle, AdSize adSize, MediationAdRequest mediationAdRequest, Bundle bundle1) {
        String adunit = bundle.getString(MOPUB_AD_UNIT_KEY);
        this.mAdSize = adSize;
        this.mMoPubView = new MoPubView(context);
        this.mMoPubView.setBannerAdListener(new MBannerListener(mediationBannerListener));
        this.mMoPubView.setAdUnitId(adunit);
        if (mediationAdRequest.isTesting()) {
            this.mMoPubView.setTesting(true);
        }
        if (mediationAdRequest.getLocation() != null) {
            this.mMoPubView.setLocation(mediationAdRequest.getLocation());
        }
        this.mMoPubView.setKeywords(getKeywords(mediationAdRequest));
        this.mMoPubView.loadAd();
    }

    public View getBannerView() {
        return this.mMoPubView;
    }

    private String getKeywords(MediationAdRequest mediationAdRequest) {
        return MOPUB_NATIVE_CEVENT_VERSION + (mediationAdRequest.getBirthday() != null ? ",m_birthday:" + mediationAdRequest.getBirthday() : "") + (mediationAdRequest.getGender() != -1 ? ",m_gender:" + mediationAdRequest.getGender() : "");
    }

    public void requestInterstitialAd(Context context, MediationInterstitialListener mediationInterstitialListener, Bundle bundle, MediationAdRequest mediationAdRequest, Bundle bundle1) {
        this.mMoPubInterstitial = new MoPubInterstitial((Activity) context, bundle.getString(MOPUB_AD_UNIT_KEY));
        this.mMoPubInterstitial.setInterstitialAdListener(new mMediationInterstitialListener(mediationInterstitialListener));
        if (mediationAdRequest.isTesting()) {
            this.mMoPubInterstitial.setTesting(true);
        }
        this.mMoPubInterstitial.setKeywords(getKeywords(mediationAdRequest));
        this.mMoPubInterstitial.load();
    }

    public void showInterstitial() {
        if (this.mMoPubInterstitial.isReady()) {
            this.mMoPubInterstitial.show();
        } else {
            MoPubLog.m12065i("Interstitial was not ready. Unable to load the interstitial");
        }
    }
}
