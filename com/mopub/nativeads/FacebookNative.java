package com.mopub.nativeads;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AdListener;
import com.facebook.ads.AdSettings;
import com.facebook.ads.MediaView;
import com.facebook.ads.NativeAd;
import com.facebook.ads.NativeAd$Image;
import com.mopub.common.Preconditions.NoThrow;
import com.mopub.common.logging.MoPubLog;
import com.mopub.nativeads.CustomEventNative.CustomEventNativeListener;
import com.mopub.nativeads.NativeImageHelper.ImageListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FacebookNative extends CustomEventNative {
    private static final String PLACEMENT_ID_KEY = "placement_id";
    private static boolean VIDEO_ENABLED = false;
    private static final String VIDEO_ENABLED_KEY = "video_enabled";
    private static Boolean sIsVideoRendererAvailable = null;

    static class FacebookStaticNativeAd extends StaticNativeAd implements AdListener {
        private static final String SOCIAL_CONTEXT_FOR_AD = "socialContextForAd";
        private final Context mContext;
        private final CustomEventNativeListener mCustomEventNativeListener;
        private final NativeAd mNativeAd;

        class C37201 implements ImageListener {
            C37201() {
            }

            public void onImagesCached() {
                FacebookStaticNativeAd.this.mCustomEventNativeListener.onNativeAdLoaded(FacebookStaticNativeAd.this);
            }

            public void onImagesFailedToCache(NativeErrorCode errorCode) {
                FacebookStaticNativeAd.this.mCustomEventNativeListener.onNativeAdFailed(errorCode);
            }
        }

        FacebookStaticNativeAd(Context context, NativeAd nativeAd, CustomEventNativeListener customEventNativeListener) {
            this.mContext = context.getApplicationContext();
            this.mNativeAd = nativeAd;
            this.mCustomEventNativeListener = customEventNativeListener;
        }

        void loadAd() {
            AdSettings.setMediationService("MOPUB_4.20.0");
            this.mNativeAd.setAdListener(this);
            this.mNativeAd.loadAd();
        }

        public void onAdLoaded(Ad ad) {
            String str = null;
            if (this.mNativeAd.equals(ad) && this.mNativeAd.isAdLoaded()) {
                setTitle(this.mNativeAd.getAdTitle());
                setText(this.mNativeAd.getAdBody());
                NativeAd$Image coverImage = this.mNativeAd.getAdCoverImage();
                setMainImageUrl(coverImage == null ? null : coverImage.getUrl());
                NativeAd$Image icon = this.mNativeAd.getAdIcon();
                setIconImageUrl(icon == null ? null : icon.getUrl());
                setCallToAction(this.mNativeAd.getAdCallToAction());
                addExtra(SOCIAL_CONTEXT_FOR_AD, this.mNativeAd.getAdSocialContext());
                NativeAd$Image adChoicesIconImage = this.mNativeAd.getAdChoicesIcon();
                if (adChoicesIconImage != null) {
                    str = adChoicesIconImage.getUrl();
                }
                setPrivacyInformationIconImageUrl(str);
                setPrivacyInformationIconClickThroughUrl(this.mNativeAd.getAdChoicesLinkUrl());
                List<String> imageUrls = new ArrayList();
                if (getMainImageUrl() != null) {
                    imageUrls.add(getMainImageUrl());
                }
                if (getIconImageUrl() != null) {
                    imageUrls.add(getIconImageUrl());
                }
                String privacyInformationIconImageUrl = getPrivacyInformationIconImageUrl();
                if (privacyInformationIconImageUrl != null) {
                    imageUrls.add(privacyInformationIconImageUrl);
                }
                NativeImageHelper.preCacheImages(this.mContext, imageUrls, new C37201());
                return;
            }
            this.mCustomEventNativeListener.onNativeAdFailed(NativeErrorCode.NETWORK_INVALID_STATE);
        }

        public void onError(Ad ad, AdError adError) {
            if (adError == null) {
                this.mCustomEventNativeListener.onNativeAdFailed(NativeErrorCode.UNSPECIFIED);
            } else if (adError.getErrorCode() == AdError.NO_FILL.getErrorCode()) {
                this.mCustomEventNativeListener.onNativeAdFailed(NativeErrorCode.NETWORK_NO_FILL);
            } else if (adError.getErrorCode() == AdError.INTERNAL_ERROR.getErrorCode()) {
                this.mCustomEventNativeListener.onNativeAdFailed(NativeErrorCode.NETWORK_INVALID_STATE);
            } else {
                this.mCustomEventNativeListener.onNativeAdFailed(NativeErrorCode.UNSPECIFIED);
            }
        }

        public void onAdClicked(Ad ad) {
            notifyAdClicked();
        }

        public void onLoggingImpression(Ad ad) {
            notifyAdImpressed();
        }

        public void prepare(View view) {
            FacebookNative.registerChildViewsForInteraction(view, this.mNativeAd);
        }

        public void clear(View view) {
            this.mNativeAd.unregisterView();
        }

        public void destroy() {
            this.mNativeAd.destroy();
        }
    }

    static class FacebookVideoEnabledNativeAd extends BaseNativeAd implements AdListener {
        private static final String SOCIAL_CONTEXT_FOR_AD = "socialContextForAd";
        private final Context mContext;
        private final CustomEventNativeListener mCustomEventNativeListener;
        private final Map<String, Object> mExtras = new HashMap();
        private final NativeAd mNativeAd;

        class C37211 implements ImageListener {
            C37211() {
            }

            public void onImagesCached() {
                FacebookVideoEnabledNativeAd.this.mCustomEventNativeListener.onNativeAdLoaded(FacebookVideoEnabledNativeAd.this);
            }

            public void onImagesFailedToCache(NativeErrorCode errorCode) {
                FacebookVideoEnabledNativeAd.this.mCustomEventNativeListener.onNativeAdFailed(errorCode);
            }
        }

        FacebookVideoEnabledNativeAd(Context context, NativeAd nativeAd, CustomEventNativeListener customEventNativeListener) {
            this.mContext = context.getApplicationContext();
            this.mNativeAd = nativeAd;
            this.mCustomEventNativeListener = customEventNativeListener;
        }

        void loadAd() {
            AdSettings.setMediationService("MOPUB_4.20.0");
            this.mNativeAd.setAdListener(this);
            this.mNativeAd.loadAd();
        }

        public final String getTitle() {
            return this.mNativeAd.getAdTitle();
        }

        public final String getText() {
            return this.mNativeAd.getAdBody();
        }

        public final String getMainImageUrl() {
            NativeAd$Image coverImage = this.mNativeAd.getAdCoverImage();
            return coverImage == null ? null : coverImage.getUrl();
        }

        public final String getIconImageUrl() {
            NativeAd$Image icon = this.mNativeAd.getAdIcon();
            return icon == null ? null : icon.getUrl();
        }

        public final String getCallToAction() {
            return this.mNativeAd.getAdCallToAction();
        }

        public final String getPrivacyInformationIconClickThroughUrl() {
            return this.mNativeAd.getAdChoicesLinkUrl();
        }

        public final String getPrivacyInformationIconImageUrl() {
            return this.mNativeAd.getAdChoicesIcon() == null ? null : this.mNativeAd.getAdChoicesIcon().getUrl();
        }

        public void onAdLoaded(Ad ad) {
            if (this.mNativeAd.equals(ad) && this.mNativeAd.isAdLoaded()) {
                addExtra(SOCIAL_CONTEXT_FOR_AD, this.mNativeAd.getAdSocialContext());
                List<String> imageUrls = new ArrayList();
                String mainImageUrl = getMainImageUrl();
                if (mainImageUrl != null) {
                    imageUrls.add(mainImageUrl);
                }
                String iconImageUrl = getIconImageUrl();
                if (iconImageUrl != null) {
                    imageUrls.add(iconImageUrl);
                }
                String privacyInformationIconImageUrl = getPrivacyInformationIconImageUrl();
                if (privacyInformationIconImageUrl != null) {
                    imageUrls.add(privacyInformationIconImageUrl);
                }
                NativeImageHelper.preCacheImages(this.mContext, imageUrls, new C37211());
                return;
            }
            this.mCustomEventNativeListener.onNativeAdFailed(NativeErrorCode.NETWORK_INVALID_STATE);
        }

        public void onError(Ad ad, AdError adError) {
            if (adError == null) {
                this.mCustomEventNativeListener.onNativeAdFailed(NativeErrorCode.UNSPECIFIED);
            } else if (adError.getErrorCode() == AdError.NO_FILL.getErrorCode()) {
                this.mCustomEventNativeListener.onNativeAdFailed(NativeErrorCode.NETWORK_NO_FILL);
            } else if (adError.getErrorCode() == AdError.INTERNAL_ERROR.getErrorCode()) {
                this.mCustomEventNativeListener.onNativeAdFailed(NativeErrorCode.NETWORK_INVALID_STATE);
            } else {
                this.mCustomEventNativeListener.onNativeAdFailed(NativeErrorCode.UNSPECIFIED);
            }
        }

        public void onAdClicked(Ad ad) {
            notifyAdClicked();
        }

        public void onLoggingImpression(Ad ad) {
            notifyAdImpressed();
        }

        public void prepare(View view) {
            FacebookNative.registerChildViewsForInteraction(view, this.mNativeAd);
        }

        public void clear(View view) {
            this.mNativeAd.unregisterView();
        }

        public void destroy() {
            this.mNativeAd.destroy();
        }

        public final Object getExtra(String key) {
            if (NoThrow.checkNotNull(key, "getExtra key is not allowed to be null")) {
                return this.mExtras.get(key);
            }
            return null;
        }

        public final Map<String, Object> getExtras() {
            return new HashMap(this.mExtras);
        }

        public final void addExtra(String key, Object value) {
            if (NoThrow.checkNotNull(key, "addExtra key is not allowed to be null")) {
                this.mExtras.put(key, value);
            }
        }

        public void updateMediaView(MediaView mediaView) {
            if (mediaView != null) {
                mediaView.setNativeAd(this.mNativeAd);
            }
        }
    }

    protected void loadNativeAd(Context context, CustomEventNativeListener customEventNativeListener, Map<String, Object> map, Map<String, String> serverExtras) {
        if (extrasAreValid(serverExtras)) {
            String placementId = (String) serverExtras.get(PLACEMENT_ID_KEY);
            String videoEnabledString = (String) serverExtras.get(VIDEO_ENABLED_KEY);
            boolean videoEnabledFromServer = Boolean.parseBoolean(videoEnabledString);
            if (sIsVideoRendererAvailable == null) {
                try {
                    Class.forName("com.mopub.nativeads.FacebookAdRenderer");
                    sIsVideoRendererAvailable = Boolean.valueOf(true);
                } catch (ClassNotFoundException e) {
                    sIsVideoRendererAvailable = Boolean.valueOf(false);
                }
            }
            if (shouldUseVideoEnabledNativeAd(sIsVideoRendererAvailable.booleanValue(), videoEnabledString, videoEnabledFromServer)) {
                new FacebookVideoEnabledNativeAd(context, new NativeAd(context, placementId), customEventNativeListener).loadAd();
                return;
            } else {
                new FacebookStaticNativeAd(context, new NativeAd(context, placementId), customEventNativeListener).loadAd();
                return;
            }
        }
        customEventNativeListener.onNativeAdFailed(NativeErrorCode.NATIVE_ADAPTER_CONFIGURATION_ERROR);
    }

    public static void setVideoEnabled(boolean videoEnabled) {
        VIDEO_ENABLED = videoEnabled;
    }

    public static void setVideoRendererAvailable(boolean videoRendererAvailable) {
        sIsVideoRendererAvailable = Boolean.valueOf(videoRendererAvailable);
    }

    static boolean shouldUseVideoEnabledNativeAd(boolean isVideoRendererAvailable, String videoEnabledString, boolean videoEnabledFromServer) {
        if (!isVideoRendererAvailable) {
            return false;
        }
        if ((videoEnabledString == null || !videoEnabledFromServer) && (videoEnabledString != null || !VIDEO_ENABLED)) {
            return false;
        }
        return true;
    }

    static Boolean isVideoRendererAvailable() {
        return sIsVideoRendererAvailable;
    }

    private boolean extrasAreValid(Map<String, String> serverExtras) {
        String placementId = (String) serverExtras.get(PLACEMENT_ID_KEY);
        return placementId != null && placementId.length() > 0;
    }

    private static void registerChildViewsForInteraction(View view, NativeAd nativeAd) {
        if (nativeAd != null) {
            List<View> clickableViews = new ArrayList();
            assembleChildViewsWithLimit(view, clickableViews, 10);
            if (clickableViews.size() == 1) {
                nativeAd.registerViewForInteraction(view);
            } else {
                nativeAd.registerViewForInteraction(view, clickableViews);
            }
        }
    }

    private static void assembleChildViewsWithLimit(View view, List<View> clickableViews, int limit) {
        if (view == null) {
            MoPubLog.m12061d("View given is null. Ignoring");
        } else if (limit <= 0) {
            MoPubLog.m12061d("Depth limit reached; adding this view regardless of its type.");
            clickableViews.add(view);
        } else if (!(view instanceof ViewGroup) || ((ViewGroup) view).getChildCount() <= 0) {
            clickableViews.add(view);
        } else {
            ViewGroup vg = (ViewGroup) view;
            for (int i = 0; i < vg.getChildCount(); i++) {
                assembleChildViewsWithLimit(vg.getChildAt(i), clickableViews, limit - 1);
            }
        }
    }
}
