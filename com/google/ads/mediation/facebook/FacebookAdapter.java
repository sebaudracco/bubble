package com.google.ads.mediation.facebook;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Keep;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.RelativeLayout;
import com.facebook.ads.Ad;
import com.facebook.ads.AdChoicesView;
import com.facebook.ads.AdError;
import com.facebook.ads.AdListener;
import com.facebook.ads.AdSettings;
import com.facebook.ads.AdView;
import com.facebook.ads.InterstitialAd;
import com.facebook.ads.InterstitialAdListener;
import com.facebook.ads.MediaView;
import com.facebook.ads.MediaViewListener;
import com.facebook.ads.NativeAd;
import com.facebook.ads.NativeAd$Rating;
import com.facebook.ads.NativeAdViewAttributes;
import com.facebook.ads.RewardedVideoAd;
import com.facebook.ads.RewardedVideoAdListener;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.formats.NativeAd.Image;
import com.google.android.gms.ads.formats.NativeAdOptions;
import com.google.android.gms.ads.formats.NativeAppInstallAdView;
import com.google.android.gms.ads.mediation.MediationAdRequest;
import com.google.android.gms.ads.mediation.MediationBannerAdapter;
import com.google.android.gms.ads.mediation.MediationBannerListener;
import com.google.android.gms.ads.mediation.MediationInterstitialAdapter;
import com.google.android.gms.ads.mediation.MediationInterstitialListener;
import com.google.android.gms.ads.mediation.MediationNativeAdapter;
import com.google.android.gms.ads.mediation.MediationNativeListener;
import com.google.android.gms.ads.mediation.NativeAppInstallAdMapper;
import com.google.android.gms.ads.mediation.NativeMediationAdRequest;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.mediation.MediationRewardedVideoAdAdapter;
import com.google.android.gms.ads.reward.mediation.MediationRewardedVideoAdListener;
import com.mopub.mobileads.dfp.adapters.MoPubAdapter;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Keep
public final class FacebookAdapter implements MediationBannerAdapter, MediationInterstitialAdapter, MediationRewardedVideoAdAdapter, MediationNativeAdapter {
    private static final int DRAWABLE_FUTURE_TIMEOUT_SECONDS = 10;
    public static final String KEY_AD_VIEW_ATTRIBUTES = "ad_view_attributes";
    public static final String KEY_AUTOPLAY = "autoplay";
    public static final String KEY_BACKGROUND_COLOR = "background_color";
    public static final String KEY_BUTTON_BORDER_COLOR = "button_border_color";
    public static final String KEY_BUTTON_COLOR = "button_color";
    public static final String KEY_BUTTON_TEXT_COLOR = "button_text_color";
    public static final String KEY_DESCRIPTION_TEXT_COLOR = "description_text_color";
    public static final String KEY_DESCRIPTION_TEXT_SIZE = "description_text_size";
    public static final String KEY_ID = "id";
    public static final String KEY_IS_BOLD = "is_bold";
    public static final String KEY_IS_ITALIC = "is_italic";
    public static final String KEY_SOCIAL_CONTEXT_ASSET = "social_context";
    public static final String KEY_STYLE = "style";
    public static final String KEY_SUBTITLE_ASSET = "subtitle";
    public static final String KEY_TITLE_TEXT_COLOR = "title_text_color";
    public static final String KEY_TITLE_TEXT_SIZE = "title_text_size";
    public static final String KEY_TYPEFACE = "typeface";
    private static final int MAX_STAR_RATING = 5;
    private static final String PLACEMENT_PARAMETER = "pubid";
    private static final String TAG = "FacebookAdapter";
    private AdView mAdView;
    private MediationBannerListener mBannerListener;
    private InterstitialAd mInterstitialAd;
    private MediationInterstitialListener mInterstitialListener;
    private boolean mIsAdChoicesIconExpandable = true;
    private boolean mIsImpressionRecorded;
    private boolean mIsInitialized;
    private MediaView mMediaView;
    private NativeAd mNativeAd;
    private MediationNativeListener mNativeListener;
    private MediationRewardedVideoAdListener mRewardedListener;
    private RewardedVideoAd mRewardedVideoAd;
    private RelativeLayout mWrappedAdView;

    class AppInstallMapper extends NativeAppInstallAdMapper {
        private NativeAd mNativeAd;
        private NativeAdOptions mNativeAdOptions;

        class C26911 implements MediaViewListener {
            C26911() {
            }

            public void onPlay(MediaView mediaView) {
            }

            public void onVolumeChange(MediaView mediaView, float v) {
            }

            public void onPause(MediaView mediaView) {
            }

            public void onComplete(MediaView mediaView) {
                if (FacebookAdapter.this.mNativeListener != null) {
                    FacebookAdapter.this.mNativeListener.onVideoEnd(FacebookAdapter.this);
                }
            }

            public void onEnterFullscreen(MediaView mediaView) {
            }

            public void onExitFullscreen(MediaView mediaView) {
            }

            public void onFullscreenBackground(MediaView mediaView) {
            }

            public void onFullscreenForeground(MediaView mediaView) {
            }
        }

        public AppInstallMapper(NativeAd nativeAd, NativeAdOptions adOptions) {
            this.mNativeAd = nativeAd;
            this.mNativeAdOptions = adOptions;
        }

        public void mapNativeAd(NativeAdMapperListener mapperListener) {
            if (containsRequiredFieldsForNativeAppInstallAd(this.mNativeAd)) {
                setHeadline(this.mNativeAd.getAdTitle());
                List<Image> images = new ArrayList();
                images.add(new FacebookAdapterNativeAdImage(Uri.parse(this.mNativeAd.getAdCoverImage().getUrl())));
                setImages(images);
                setBody(this.mNativeAd.getAdBody());
                setIcon(new FacebookAdapterNativeAdImage(Uri.parse(this.mNativeAd.getAdIcon().getUrl())));
                setCallToAction(this.mNativeAd.getAdCallToAction());
                if (FacebookAdapter.this.mMediaView != null) {
                    FacebookAdapter.this.mMediaView.setListener(new C26911());
                    FacebookAdapter.this.mMediaView.setNativeAd(this.mNativeAd);
                    setMediaView(FacebookAdapter.this.mMediaView);
                    setHasVideoContent(true);
                } else {
                    Log.w(FacebookAdapter.TAG, "Couldn't set MediaView.");
                    setHasVideoContent(false);
                }
                Double starRating = getRating(this.mNativeAd.getAdStarRating());
                if (starRating != null) {
                    setStarRating(starRating.doubleValue());
                }
                Bundle extras = new Bundle();
                extras.putCharSequence("id", this.mNativeAd.getId());
                extras.putCharSequence(FacebookAdapter.KEY_SOCIAL_CONTEXT_ASSET, this.mNativeAd.getAdSocialContext());
                extras.putCharSequence(FacebookAdapter.KEY_SUBTITLE_ASSET, this.mNativeAd.getAdSubtitle());
                NativeAdViewAttributes attributes = this.mNativeAd.getAdViewAttributes();
                if (attributes != null) {
                    Bundle attributesBundle = new Bundle();
                    attributesBundle.putBoolean("autoplay", attributes.getAutoplay());
                    attributesBundle.putInt(FacebookAdapter.KEY_BACKGROUND_COLOR, attributes.getBackgroundColor());
                    attributesBundle.putInt(FacebookAdapter.KEY_BUTTON_BORDER_COLOR, attributes.getButtonBorderColor());
                    attributesBundle.putInt(FacebookAdapter.KEY_BUTTON_COLOR, attributes.getButtonColor());
                    attributesBundle.putInt(FacebookAdapter.KEY_BUTTON_TEXT_COLOR, attributes.getButtonTextColor());
                    attributesBundle.putInt(FacebookAdapter.KEY_DESCRIPTION_TEXT_COLOR, attributes.getDescriptionTextColor());
                    attributesBundle.putInt(FacebookAdapter.KEY_DESCRIPTION_TEXT_SIZE, attributes.getDescriptionTextSize());
                    attributesBundle.putInt(FacebookAdapter.KEY_TITLE_TEXT_COLOR, attributes.getTitleTextColor());
                    attributesBundle.putInt(FacebookAdapter.KEY_TITLE_TEXT_SIZE, attributes.getTitleTextSize());
                    Typeface typeface = attributes.getTypeface();
                    if (typeface != null) {
                        Bundle typefaceBundle = new Bundle();
                        typefaceBundle.putBoolean(FacebookAdapter.KEY_IS_BOLD, typeface.isBold());
                        typefaceBundle.putBoolean(FacebookAdapter.KEY_IS_ITALIC, typeface.isItalic());
                        typefaceBundle.putInt("style", typeface.getStyle());
                        attributesBundle.putBundle(FacebookAdapter.KEY_TYPEFACE, typefaceBundle);
                    }
                    extras.putBundle(FacebookAdapter.KEY_AD_VIEW_ATTRIBUTES, attributesBundle);
                }
                setExtras(extras);
                boolean urlsOnly = false;
                if (this.mNativeAdOptions != null) {
                    urlsOnly = this.mNativeAdOptions.shouldReturnUrlsForImageAssets();
                }
                if (urlsOnly) {
                    mapperListener.onMappingSuccess();
                    return;
                }
                new DownloadDrawablesAsync(mapperListener).execute(new Object[]{this});
                return;
            }
            Log.w(FacebookAdapter.TAG, "Ad from Facebook doesn't have all assets required for the app install format.");
            mapperListener.onMappingFailed();
        }

        private boolean containsRequiredFieldsForNativeAppInstallAd(NativeAd nativeAd) {
            return (nativeAd.getAdTitle() == null || nativeAd.getAdCoverImage() == null || nativeAd.getAdBody() == null || nativeAd.getAdIcon() == null || nativeAd.getAdCallToAction() == null) ? false : true;
        }

        public void trackView(View view) {
            ViewGroup adView = (ViewGroup) view;
            View overlayView = adView.getChildAt(adView.getChildCount() - 1);
            if (overlayView instanceof FrameLayout) {
                AdChoicesView adChoicesView = new AdChoicesView(view.getContext(), this.mNativeAd, FacebookAdapter.this.mIsAdChoicesIconExpandable);
                ((ViewGroup) overlayView).addView(adChoicesView);
                LayoutParams params = (LayoutParams) adChoicesView.getLayoutParams();
                if (this.mNativeAdOptions != null) {
                    switch (this.mNativeAdOptions.getAdChoicesPlacement()) {
                        case 0:
                            params.gravity = 51;
                            break;
                        case 2:
                            params.gravity = 85;
                            break;
                        case 3:
                            params.gravity = 83;
                            break;
                        default:
                            params.gravity = 53;
                            break;
                    }
                }
                params.gravity = 53;
                adView.requestLayout();
            } else {
                Log.w(FacebookAdapter.TAG, "Failed to show AdChoices icon.");
            }
            setOverrideImpressionRecording(true);
            setOverrideClickHandling(true);
            if (view instanceof NativeAppInstallAdView) {
                NativeAppInstallAdView appInstallAdView = (NativeAppInstallAdView) view;
                ArrayList<View> assetViews = new ArrayList();
                if (appInstallAdView.getHeadlineView() != null) {
                    assetViews.add(appInstallAdView.getHeadlineView());
                }
                if (appInstallAdView.getBodyView() != null) {
                    assetViews.add(appInstallAdView.getBodyView());
                }
                if (appInstallAdView.getCallToActionView() != null) {
                    assetViews.add(appInstallAdView.getCallToActionView());
                }
                if (appInstallAdView.getIconView() != null) {
                    assetViews.add(appInstallAdView.getIconView());
                }
                if (appInstallAdView.getImageView() != null) {
                    assetViews.add(appInstallAdView.getImageView());
                }
                if (appInstallAdView.getPriceView() != null) {
                    assetViews.add(appInstallAdView.getPriceView());
                }
                if (appInstallAdView.getStarRatingView() != null) {
                    assetViews.add(appInstallAdView.getStarRatingView());
                }
                if (appInstallAdView.getStoreView() != null) {
                    assetViews.add(appInstallAdView.getStoreView());
                }
                if (appInstallAdView.getMediaView() != null) {
                    assetViews.add(appInstallAdView.getMediaView());
                }
                this.mNativeAd.registerViewForInteraction(view, assetViews);
                return;
            }
            Log.w(FacebookAdapter.TAG, "Failed to register view for interaction.");
        }

        public void untrackView(View view) {
            super.untrackView(view);
            ViewGroup adView = (ViewGroup) view;
            View overlayView = adView.getChildAt(adView.getChildCount() - 1);
            if (overlayView instanceof FrameLayout) {
                ((FrameLayout) overlayView).removeAllViews();
            }
            this.mNativeAd.unregisterView();
        }

        private Double getRating(NativeAd$Rating rating) {
            if (rating == null) {
                return null;
            }
            return Double.valueOf((5.0d * rating.getValue()) / rating.getScale());
        }
    }

    private class BannerListener implements AdListener {
        private BannerListener() {
        }

        public void onAdClicked(Ad ad) {
            FacebookAdapter.this.mBannerListener.onAdClicked(FacebookAdapter.this);
            FacebookAdapter.this.mBannerListener.onAdOpened(FacebookAdapter.this);
            FacebookAdapter.this.mBannerListener.onAdLeftApplication(FacebookAdapter.this);
        }

        public void onLoggingImpression(Ad ad) {
        }

        public void onAdLoaded(Ad ad) {
            FacebookAdapter.this.mBannerListener.onAdLoaded(FacebookAdapter.this);
        }

        public void onError(Ad ad, AdError adError) {
            String errorMessage = adError.getErrorMessage();
            if (!TextUtils.isEmpty(errorMessage)) {
                Log.w(FacebookAdapter.TAG, errorMessage);
            }
            FacebookAdapter.this.mBannerListener.onAdFailedToLoad(FacebookAdapter.this, FacebookAdapter.this.convertErrorCode(adError));
        }
    }

    private static class DownloadDrawablesAsync extends AsyncTask<Object, Void, Boolean> {
        private NativeAdMapperListener mDrawableListener;

        public DownloadDrawablesAsync(NativeAdMapperListener listener) {
            this.mDrawableListener = listener;
        }

        protected Boolean doInBackground(Object... params) {
            Exception exception;
            AppInstallMapper mapper = params[0];
            ExecutorService executorService = Executors.newCachedThreadPool();
            HashMap<FacebookAdapterNativeAdImage, Future<Drawable>> futuresMap = new HashMap();
            List<Image> images = mapper.getImages();
            for (int i = 0; i < images.size(); i++) {
                FacebookAdapterNativeAdImage image = (FacebookAdapterNativeAdImage) images.get(i);
                futuresMap.put(image, getDrawableFuture(image.getUri(), executorService));
            }
            FacebookAdapterNativeAdImage iconImage = (FacebookAdapterNativeAdImage) mapper.getIcon();
            futuresMap.put(iconImage, getDrawableFuture(iconImage.getUri(), executorService));
            for (Entry<FacebookAdapterNativeAdImage, Future<Drawable>> pair : futuresMap.entrySet()) {
                try {
                    ((FacebookAdapterNativeAdImage) pair.getKey()).setDrawable((Drawable) ((Future) pair.getValue()).get(10, TimeUnit.SECONDS));
                } catch (InterruptedException e) {
                    exception = e;
                } catch (ExecutionException e2) {
                    exception = e2;
                } catch (TimeoutException e3) {
                    exception = e3;
                }
            }
            return Boolean.valueOf(true);
            Log.w(FacebookAdapter.TAG, "Exception occurred while waiting for future to return. Returning null as drawable : " + exception);
            return Boolean.valueOf(false);
        }

        private Future<Drawable> getDrawableFuture(final Uri uri, ExecutorService executorService) {
            return executorService.submit(new Callable<Drawable>() {
                public Drawable call() throws Exception {
                    Bitmap bitmap = BitmapFactory.decodeStream(new URL(uri.toString()).openStream());
                    bitmap.setDensity(160);
                    return new BitmapDrawable(Resources.getSystem(), bitmap);
                }
            });
        }

        protected void onPostExecute(Boolean isDownloadSuccessful) {
            super.onPostExecute(isDownloadSuccessful);
            if (isDownloadSuccessful.booleanValue()) {
                this.mDrawableListener.onMappingSuccess();
            } else {
                this.mDrawableListener.onMappingFailed();
            }
        }
    }

    private class FacebookAdapterNativeAdImage extends Image {
        private Drawable mDrawable;
        private Uri mUri;

        public FacebookAdapterNativeAdImage(Uri uri) {
            this.mUri = uri;
        }

        protected void setDrawable(Drawable drawable) {
            this.mDrawable = drawable;
        }

        public Drawable getDrawable() {
            return this.mDrawable;
        }

        public Uri getUri() {
            return this.mUri;
        }

        public double getScale() {
            return MoPubAdapter.DEFAULT_MOPUB_IMAGE_SCALE;
        }
    }

    public static class FacebookExtrasBundleBuilder {
        private static final String KEY_EXPANDABLE_ICON = "expandable_icon";
        private boolean mIsExpandableIcon;

        public FacebookExtrasBundleBuilder setNativeAdChoicesIconExpandable(boolean isExpandableIcon) {
            this.mIsExpandableIcon = isExpandableIcon;
            return this;
        }

        public Bundle build() {
            Bundle bundle = new Bundle();
            bundle.putBoolean(KEY_EXPANDABLE_ICON, this.mIsExpandableIcon);
            return bundle;
        }
    }

    private class FacebookReward implements RewardItem {
        private FacebookReward() {
        }

        public String getType() {
            return "";
        }

        public int getAmount() {
            return 1;
        }
    }

    private class InterstitialListener implements InterstitialAdListener {
        private InterstitialListener() {
        }

        public void onAdClicked(Ad ad) {
            FacebookAdapter.this.mInterstitialListener.onAdClicked(FacebookAdapter.this);
            FacebookAdapter.this.mInterstitialListener.onAdLeftApplication(FacebookAdapter.this);
        }

        public void onLoggingImpression(Ad ad) {
        }

        public void onAdLoaded(Ad ad) {
            FacebookAdapter.this.mInterstitialListener.onAdLoaded(FacebookAdapter.this);
        }

        public void onError(Ad ad, AdError adError) {
            String errorMessage = adError.getErrorMessage();
            if (!TextUtils.isEmpty(errorMessage)) {
                Log.w(FacebookAdapter.TAG, errorMessage);
            }
            FacebookAdapter.this.mInterstitialListener.onAdFailedToLoad(FacebookAdapter.this, FacebookAdapter.this.convertErrorCode(adError));
        }

        public void onInterstitialDismissed(Ad ad) {
            FacebookAdapter.this.mInterstitialListener.onAdClosed(FacebookAdapter.this);
        }

        public void onInterstitialDisplayed(Ad ad) {
            FacebookAdapter.this.mInterstitialListener.onAdOpened(FacebookAdapter.this);
        }
    }

    private interface NativeAdMapperListener {
        void onMappingFailed();

        void onMappingSuccess();
    }

    private class NativeListener implements AdListener {
        private NativeMediationAdRequest mMediationAdRequest;
        private NativeAd mNativeAd;

        private NativeListener(NativeAd nativeAd, NativeMediationAdRequest mediationAdRequest) {
            this.mNativeAd = nativeAd;
            this.mMediationAdRequest = mediationAdRequest;
        }

        public void onAdClicked(Ad ad) {
            FacebookAdapter.this.mNativeListener.onAdClicked(FacebookAdapter.this);
            FacebookAdapter.this.mNativeListener.onAdOpened(FacebookAdapter.this);
            FacebookAdapter.this.mNativeListener.onAdLeftApplication(FacebookAdapter.this);
        }

        public void onLoggingImpression(Ad ad) {
            if (FacebookAdapter.this.mIsImpressionRecorded) {
                Log.d(FacebookAdapter.TAG, "Received onLoggingImpression callback for a native whose impression is already recorded. Ignoring the duplicate callback.");
                return;
            }
            FacebookAdapter.this.mNativeListener.onAdImpression(FacebookAdapter.this);
            FacebookAdapter.this.mIsImpressionRecorded = true;
        }

        public void onAdLoaded(Ad ad) {
            if (ad != this.mNativeAd) {
                Log.w(FacebookAdapter.TAG, "Ad loaded is not a native ad.");
                FacebookAdapter.this.mNativeListener.onAdFailedToLoad(FacebookAdapter.this, 0);
                return;
            }
            final AppInstallMapper mapper = new AppInstallMapper(this.mNativeAd, this.mMediationAdRequest.getNativeAdOptions());
            mapper.mapNativeAd(new NativeAdMapperListener() {
                public void onMappingSuccess() {
                    FacebookAdapter.this.mNativeListener.onAdLoaded(FacebookAdapter.this, mapper);
                }

                public void onMappingFailed() {
                    FacebookAdapter.this.mNativeListener.onAdFailedToLoad(FacebookAdapter.this, 3);
                }
            });
        }

        public void onError(Ad ad, AdError adError) {
            String errorMessage = adError.getErrorMessage();
            if (!TextUtils.isEmpty(errorMessage)) {
                Log.w(FacebookAdapter.TAG, errorMessage);
            }
            FacebookAdapter.this.mNativeListener.onAdFailedToLoad(FacebookAdapter.this, FacebookAdapter.this.convertErrorCode(adError));
        }
    }

    private class RewardedVideoListener implements RewardedVideoAdListener {
        private RewardedVideoListener() {
        }

        public void onRewardedVideoCompleted() {
            FacebookAdapter.this.mRewardedListener.onRewarded(FacebookAdapter.this, new FacebookReward());
        }

        public void onError(Ad ad, AdError adError) {
            String errorMessage = adError.getErrorMessage();
            if (!TextUtils.isEmpty(errorMessage)) {
                Log.w(FacebookAdapter.TAG, errorMessage);
            }
            FacebookAdapter.this.mRewardedListener.onAdFailedToLoad(FacebookAdapter.this, FacebookAdapter.this.convertErrorCode(adError));
        }

        public void onAdLoaded(Ad ad) {
            FacebookAdapter.this.mRewardedListener.onAdLoaded(FacebookAdapter.this);
        }

        public void onAdClicked(Ad ad) {
            FacebookAdapter.this.mRewardedListener.onAdClicked(FacebookAdapter.this);
            FacebookAdapter.this.mRewardedListener.onAdLeftApplication(FacebookAdapter.this);
        }

        public void onLoggingImpression(Ad ad) {
        }

        public void onRewardedVideoClosed() {
            FacebookAdapter.this.mRewardedListener.onAdClosed(FacebookAdapter.this);
        }
    }

    public void onDestroy() {
        if (this.mAdView != null) {
            this.mAdView.destroy();
        }
        if (this.mInterstitialAd != null) {
            this.mInterstitialAd.destroy();
        }
        if (this.mNativeAd != null) {
            this.mNativeAd.unregisterView();
            this.mNativeAd.destroy();
        }
        if (this.mMediaView != null) {
            this.mMediaView.destroy();
        }
        if (this.mRewardedVideoAd != null) {
            this.mRewardedVideoAd.destroy();
        }
    }

    public void onPause() {
    }

    public void onResume() {
    }

    public void requestBannerAd(Context context, MediationBannerListener listener, Bundle serverParameters, AdSize adSize, MediationAdRequest adRequest, Bundle mediationExtras) {
        this.mBannerListener = listener;
        if (!isValidRequestParameters(context, serverParameters)) {
            this.mBannerListener.onAdFailedToLoad(this, 1);
        } else if (adSize == null) {
            Log.w(TAG, "Fail to request banner ad, adSize is null");
            this.mBannerListener.onAdFailedToLoad(this, 1);
        } else {
            String placementId = serverParameters.getString("pubid");
            com.facebook.ads.AdSize facebookAdSize = getAdSize(context, adSize);
            if (facebookAdSize == null) {
                Log.w(TAG, "The input ad size " + adSize.toString() + " is not supported at this moment.");
                this.mBannerListener.onAdFailedToLoad(this, 3);
                return;
            }
            this.mAdView = new AdView(context, placementId, facebookAdSize);
            this.mAdView.setAdListener(new BannerListener());
            buildAdRequest(adRequest);
            RelativeLayout.LayoutParams adViewLayoutParams = new RelativeLayout.LayoutParams(adSize.getWidthInPixels(context), adSize.getHeightInPixels(context));
            this.mWrappedAdView = new RelativeLayout(context);
            this.mAdView.setLayoutParams(adViewLayoutParams);
            this.mWrappedAdView.addView(this.mAdView);
            this.mAdView.loadAd();
        }
    }

    public View getBannerView() {
        return this.mWrappedAdView;
    }

    public void requestInterstitialAd(Context context, MediationInterstitialListener listener, Bundle serverParameters, MediationAdRequest adRequest, Bundle mediationExtras) {
        this.mInterstitialListener = listener;
        if (isValidRequestParameters(context, serverParameters)) {
            this.mInterstitialAd = new InterstitialAd(context, serverParameters.getString("pubid"));
            this.mInterstitialAd.setAdListener(new InterstitialListener());
            buildAdRequest(adRequest);
            this.mInterstitialAd.loadAd();
            return;
        }
        this.mInterstitialListener.onAdFailedToLoad(this, 1);
    }

    public void showInterstitial() {
        if (this.mInterstitialAd.isAdLoaded()) {
            this.mInterstitialAd.show();
        }
    }

    public void initialize(Context context, MediationAdRequest mediationAdRequest, String unused, MediationRewardedVideoAdListener mediationRewardedVideoAdListener, Bundle serverParameters, Bundle networkExtras) {
        this.mRewardedListener = mediationRewardedVideoAdListener;
        if (isValidRequestParameters(context, serverParameters)) {
            this.mRewardedVideoAd = new RewardedVideoAd(context, serverParameters.getString("pubid"));
            this.mRewardedVideoAd.setAdListener(new RewardedVideoListener());
            this.mIsInitialized = true;
            this.mRewardedListener.onInitializationSucceeded(this);
            return;
        }
        this.mRewardedListener.onAdFailedToLoad(this, 1);
    }

    public void loadAd(MediationAdRequest mediationAdRequest, Bundle serverParameters, Bundle networkExtras) {
        if (this.mRewardedVideoAd == null) {
            Log.w(TAG, "Failed to request rewarded video ad, adapter has not been initialized.");
            this.mIsInitialized = false;
            if (this.mRewardedListener != null) {
                this.mRewardedListener.onAdFailedToLoad(this, 0);
            }
        } else if (this.mRewardedVideoAd.isAdLoaded()) {
            this.mRewardedListener.onAdLoaded(this);
        } else {
            buildAdRequest(mediationAdRequest);
            this.mRewardedVideoAd.loadAd(true);
        }
    }

    public void showVideo() {
        if (this.mRewardedVideoAd == null || !this.mRewardedVideoAd.isAdLoaded()) {
            Log.w(TAG, "No ads to show.");
            if (this.mRewardedListener != null) {
                this.mRewardedListener.onAdOpened(this);
                this.mRewardedListener.onAdClosed(this);
                return;
            }
            return;
        }
        this.mRewardedVideoAd.show();
        this.mRewardedListener.onAdOpened(this);
        this.mRewardedListener.onVideoStarted(this);
    }

    public boolean isInitialized() {
        return this.mIsInitialized;
    }

    public void requestNativeAd(Context context, MediationNativeListener listener, Bundle serverParameters, NativeMediationAdRequest mediationAdRequest, Bundle mediationExtras) {
        this.mNativeListener = listener;
        if (!isValidRequestParameters(context, serverParameters)) {
            this.mNativeListener.onAdFailedToLoad(this, 1);
        } else if (mediationAdRequest.isAppInstallAdRequested() && mediationAdRequest.isContentAdRequested()) {
            String placementId = serverParameters.getString("pubid");
            if (mediationExtras != null) {
                this.mIsAdChoicesIconExpandable = mediationExtras.getBoolean("expandable_icon", true);
            }
            this.mMediaView = new MediaView(context);
            this.mNativeAd = new NativeAd(context, placementId);
            this.mNativeAd.setAdListener(new NativeListener(this.mNativeAd, mediationAdRequest));
            buildAdRequest(mediationAdRequest);
            this.mNativeAd.loadAd();
        } else {
            Log.w(TAG, "Failed to request native ad. Both app install and content ad should be requested");
            this.mNativeListener.onAdFailedToLoad(this, 1);
        }
    }

    private static boolean isValidRequestParameters(Context context, Bundle serverParameters) {
        if (context == null) {
            Log.w(TAG, "Failed to request ad, Context is null.");
            return false;
        } else if (serverParameters == null) {
            Log.w(TAG, "Failed to request ad, serverParameters is null.");
            return false;
        } else if (!TextUtils.isEmpty(serverParameters.getString("pubid"))) {
            return true;
        } else {
            Log.w(TAG, "Failed to request ad, placementId is null or empty.");
            return false;
        }
    }

    private int convertErrorCode(AdError adError) {
        if (adError == null) {
            return 0;
        }
        switch (adError.getErrorCode()) {
            case 1000:
            case 2000:
                return 2;
            case 1001:
                return 3;
            case 1002:
                return 1;
            default:
                return 0;
        }
    }

    private void buildAdRequest(MediationAdRequest adRequest) {
        boolean z = true;
        if (adRequest != null) {
            if (adRequest.taggedForChildDirectedTreatment() != 1) {
                z = false;
            }
            AdSettings.setIsChildDirected(z);
        }
    }

    private com.facebook.ads.AdSize getAdSize(Context context, AdSize adSize) {
        if (adSize.getWidth() == com.facebook.ads.AdSize.BANNER_320_50.getWidth() && adSize.getHeight() == com.facebook.ads.AdSize.BANNER_320_50.getHeight()) {
            return com.facebook.ads.AdSize.BANNER_320_50;
        }
        int heightInDip = pixelToDip(adSize.getHeightInPixels(context));
        if (heightInDip == com.facebook.ads.AdSize.BANNER_HEIGHT_50.getHeight()) {
            return com.facebook.ads.AdSize.BANNER_HEIGHT_50;
        }
        if (heightInDip == com.facebook.ads.AdSize.BANNER_HEIGHT_90.getHeight()) {
            return com.facebook.ads.AdSize.BANNER_HEIGHT_90;
        }
        if (heightInDip == com.facebook.ads.AdSize.RECTANGLE_HEIGHT_250.getHeight()) {
            return com.facebook.ads.AdSize.RECTANGLE_HEIGHT_250;
        }
        return null;
    }

    private int pixelToDip(int pixel) {
        return Math.round(((float) pixel) / Resources.getSystem().getDisplayMetrics().density);
    }
}
