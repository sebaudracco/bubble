package com.mopub.mobileads;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.mopub.common.AdFormat;
import com.mopub.common.AdType;
import com.mopub.common.util.ResponseHeader;
import com.mopub.network.HeaderUtils;
import java.util.Map;

public class AdTypeTranslator {
    public static final String BANNER_SUFFIX = "_banner";
    public static final String INTERSTITIAL_SUFFIX = "_interstitial";

    public enum CustomEventType {
        GOOGLE_PLAY_SERVICES_BANNER("admob_native_banner", "com.mopub.mobileads.GooglePlayServicesBanner", false),
        GOOGLE_PLAY_SERVICES_INTERSTITIAL("admob_full_interstitial", "com.mopub.mobileads.GooglePlayServicesInterstitial", false),
        MILLENNIAL_BANNER("millennial_native_banner", "com.mopub.mobileads.MillennialBanner", false),
        MILLENNIAL_INTERSTITIAL("millennial_full_interstitial", "com.mopub.mobileads.MillennialInterstitial", false),
        MRAID_BANNER("mraid_banner", "com.mopub.mraid.MraidBanner", true),
        MRAID_INTERSTITIAL("mraid_interstitial", "com.mopub.mraid.MraidInterstitial", true),
        HTML_BANNER("html_banner", "com.mopub.mobileads.HtmlBanner", true),
        HTML_INTERSTITIAL("html_interstitial", "com.mopub.mobileads.HtmlInterstitial", true),
        VAST_VIDEO_INTERSTITIAL("vast_interstitial", "com.mopub.mobileads.VastVideoInterstitial", true),
        MOPUB_NATIVE("mopub_native", "com.mopub.nativeads.MoPubCustomEventNative", true),
        MOPUB_VIDEO_NATIVE("mopub_video_native", "com.mopub.nativeads.MoPubCustomEventVideoNative", true),
        MOPUB_REWARDED_VIDEO(AdType.REWARDED_VIDEO, "com.mopub.mobileads.MoPubRewardedVideo", true),
        MOPUB_REWARDED_PLAYABLE(AdType.REWARDED_PLAYABLE, "com.mopub.mobileads.MoPubRewardedPlayable", true),
        UNSPECIFIED("", null, false);
        
        @Nullable
        private final String mClassName;
        private final boolean mIsMoPubSpecific;
        @NonNull
        private final String mKey;

        private CustomEventType(String key, String className, boolean isMoPubSpecific) {
            this.mKey = key;
            this.mClassName = className;
            this.mIsMoPubSpecific = isMoPubSpecific;
        }

        private static CustomEventType fromString(@Nullable String key) {
            for (CustomEventType customEventType : values()) {
                if (customEventType.mKey.equals(key)) {
                    return customEventType;
                }
            }
            return UNSPECIFIED;
        }

        private static CustomEventType fromClassName(@Nullable String className) {
            for (CustomEventType customEventType : values()) {
                if (customEventType.mClassName != null && customEventType.mClassName.equals(className)) {
                    return customEventType;
                }
            }
            return UNSPECIFIED;
        }

        public String toString() {
            return this.mClassName;
        }

        public static boolean isMoPubSpecific(@Nullable String className) {
            return fromClassName(className).mIsMoPubSpecific;
        }
    }

    static String getAdNetworkType(String adType, String fullAdType) {
        String adNetworkType;
        if ("interstitial".equals(adType)) {
            adNetworkType = fullAdType;
        } else {
            adNetworkType = adType;
        }
        return adNetworkType != null ? adNetworkType : "unknown";
    }

    public static String getCustomEventName(@NonNull AdFormat adFormat, @NonNull String adType, @Nullable String fullAdType, @NonNull Map<String, String> headers) {
        if (AdType.CUSTOM.equalsIgnoreCase(adType)) {
            return HeaderUtils.extractHeader((Map) headers, ResponseHeader.CUSTOM_EVENT_NAME);
        }
        if ("json".equalsIgnoreCase(adType)) {
            return CustomEventType.MOPUB_NATIVE.toString();
        }
        if (AdType.VIDEO_NATIVE.equalsIgnoreCase(adType)) {
            return CustomEventType.MOPUB_VIDEO_NATIVE.toString();
        }
        if (AdType.REWARDED_VIDEO.equalsIgnoreCase(adType)) {
            return CustomEventType.MOPUB_REWARDED_VIDEO.toString();
        }
        if (AdType.REWARDED_PLAYABLE.equalsIgnoreCase(adType)) {
            return CustomEventType.MOPUB_REWARDED_PLAYABLE.toString();
        }
        if ("html".equalsIgnoreCase(adType) || AdType.MRAID.equalsIgnoreCase(adType)) {
            CustomEventType access$000;
            if (AdFormat.INTERSTITIAL.equals(adFormat)) {
                access$000 = CustomEventType.fromString(adType + INTERSTITIAL_SUFFIX);
            } else {
                access$000 = CustomEventType.fromString(adType + BANNER_SUFFIX);
            }
            return access$000.toString();
        } else if ("interstitial".equalsIgnoreCase(adType)) {
            return CustomEventType.fromString(fullAdType + INTERSTITIAL_SUFFIX).toString();
        } else {
            return CustomEventType.fromString(adType + BANNER_SUFFIX).toString();
        }
    }
}
