package com.mopub.nativeads;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.mopub.common.DataKeys;
import com.mopub.common.VisibleForTesting;
import com.mopub.common.event.EventDetails;
import com.mopub.common.logging.MoPubLog;
import com.mopub.mobileads.VastVideoConfig;
import com.mopub.nativeads.CustomEventNative.CustomEventNativeListener;
import com.mopub.network.TrackingRequest;
import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class MoPubCustomEventVideoNative extends CustomEventNative {

    static /* synthetic */ class C37331 {
        static final /* synthetic */ int[] f9047x5a67b636 = new int[MoPubCustomEventVideoNative$MoPubVideoNativeAd$Parameter.values().length];
        static final /* synthetic */ int[] f9048x761fa3e9 = new int[MoPubCustomEventVideoNative$MoPubVideoNativeAd$VideoState.values().length];

        static {
            try {
                f9048x761fa3e9[MoPubCustomEventVideoNative$MoPubVideoNativeAd$VideoState.FAILED_LOAD.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f9048x761fa3e9[MoPubCustomEventVideoNative$MoPubVideoNativeAd$VideoState.CREATED.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f9048x761fa3e9[MoPubCustomEventVideoNative$MoPubVideoNativeAd$VideoState.LOADING.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f9048x761fa3e9[MoPubCustomEventVideoNative$MoPubVideoNativeAd$VideoState.BUFFERING.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                f9048x761fa3e9[MoPubCustomEventVideoNative$MoPubVideoNativeAd$VideoState.PAUSED.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                f9048x761fa3e9[MoPubCustomEventVideoNative$MoPubVideoNativeAd$VideoState.PLAYING.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                f9048x761fa3e9[MoPubCustomEventVideoNative$MoPubVideoNativeAd$VideoState.PLAYING_MUTED.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                f9048x761fa3e9[MoPubCustomEventVideoNative$MoPubVideoNativeAd$VideoState.ENDED.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            try {
                f9047x5a67b636[MoPubCustomEventVideoNative$MoPubVideoNativeAd$Parameter.IMPRESSION_TRACKER.ordinal()] = 1;
            } catch (NoSuchFieldError e9) {
            }
            try {
                f9047x5a67b636[MoPubCustomEventVideoNative$MoPubVideoNativeAd$Parameter.TITLE.ordinal()] = 2;
            } catch (NoSuchFieldError e10) {
            }
            try {
                f9047x5a67b636[MoPubCustomEventVideoNative$MoPubVideoNativeAd$Parameter.TEXT.ordinal()] = 3;
            } catch (NoSuchFieldError e11) {
            }
            try {
                f9047x5a67b636[MoPubCustomEventVideoNative$MoPubVideoNativeAd$Parameter.IMAGE_URL.ordinal()] = 4;
            } catch (NoSuchFieldError e12) {
            }
            try {
                f9047x5a67b636[MoPubCustomEventVideoNative$MoPubVideoNativeAd$Parameter.ICON_URL.ordinal()] = 5;
            } catch (NoSuchFieldError e13) {
            }
            try {
                f9047x5a67b636[MoPubCustomEventVideoNative$MoPubVideoNativeAd$Parameter.CLICK_DESTINATION.ordinal()] = 6;
            } catch (NoSuchFieldError e14) {
            }
            try {
                f9047x5a67b636[MoPubCustomEventVideoNative$MoPubVideoNativeAd$Parameter.CLICK_TRACKER.ordinal()] = 7;
            } catch (NoSuchFieldError e15) {
            }
            try {
                f9047x5a67b636[MoPubCustomEventVideoNative$MoPubVideoNativeAd$Parameter.CALL_TO_ACTION.ordinal()] = 8;
            } catch (NoSuchFieldError e16) {
            }
            try {
                f9047x5a67b636[MoPubCustomEventVideoNative$MoPubVideoNativeAd$Parameter.VAST_VIDEO.ordinal()] = 9;
            } catch (NoSuchFieldError e17) {
            }
        }
    }

    @VisibleForTesting
    static class HeaderVisibilityStrategy implements OnTrackedStrategy {
        @NonNull
        private final WeakReference<MoPubVideoNativeAd> mMoPubVideoNativeAd;

        HeaderVisibilityStrategy(@NonNull MoPubVideoNativeAd moPubVideoNativeAd) {
            this.mMoPubVideoNativeAd = new WeakReference(moPubVideoNativeAd);
        }

        public void execute() {
            MoPubVideoNativeAd moPubVideoNativeAd = (MoPubVideoNativeAd) this.mMoPubVideoNativeAd.get();
            if (moPubVideoNativeAd != null) {
                moPubVideoNativeAd.notifyAdImpressed();
            }
        }
    }

    @VisibleForTesting
    static class NativeVideoControllerFactory {
        NativeVideoControllerFactory() {
        }

        public NativeVideoController createForId(long id, @NonNull Context context, @NonNull List<VisibilityTrackingEvent> visibilityTrackingEvents, @NonNull VastVideoConfig vastVideoConfig, @Nullable EventDetails eventDetails) {
            return NativeVideoController.createForId(id, context, visibilityTrackingEvents, vastVideoConfig, eventDetails);
        }
    }

    @VisibleForTesting
    static class PayloadVisibilityStrategy implements OnTrackedStrategy {
        @NonNull
        private final Context mContext;
        @NonNull
        private final String mUrl;

        PayloadVisibilityStrategy(@NonNull Context context, @NonNull String url) {
            this.mContext = context.getApplicationContext();
            this.mUrl = url;
        }

        public void execute() {
            TrackingRequest.makeTrackingHttpRequest(this.mUrl, this.mContext);
        }
    }

    @VisibleForTesting
    static class VideoResponseHeaders {
        private boolean mHeadersAreValid;
        private int mImpressionMinVisiblePercent;
        private int mImpressionVisibleMs;
        private Integer mImpressionVisiblePx;
        private int mMaxBufferMs;
        private int mPauseVisiblePercent;
        private int mPlayVisiblePercent;
        private JSONObject mVideoTrackers;

        VideoResponseHeaders(@NonNull Map<String, String> serverExtras) {
            try {
                this.mPlayVisiblePercent = Integer.parseInt((String) serverExtras.get(DataKeys.PLAY_VISIBLE_PERCENT));
                this.mPauseVisiblePercent = Integer.parseInt((String) serverExtras.get(DataKeys.PAUSE_VISIBLE_PERCENT));
                this.mImpressionVisibleMs = Integer.parseInt((String) serverExtras.get(DataKeys.IMPRESSION_VISIBLE_MS));
                this.mMaxBufferMs = Integer.parseInt((String) serverExtras.get(DataKeys.MAX_BUFFER_MS));
                this.mHeadersAreValid = true;
            } catch (NumberFormatException e) {
                this.mHeadersAreValid = false;
            }
            String impressionVisiblePxString = (String) serverExtras.get(DataKeys.IMPRESSION_MIN_VISIBLE_PX);
            if (!TextUtils.isEmpty(impressionVisiblePxString)) {
                try {
                    this.mImpressionVisiblePx = Integer.valueOf(Integer.parseInt(impressionVisiblePxString));
                } catch (NumberFormatException e2) {
                    MoPubLog.m12061d("Unable to parse impression min visible px from server extras.");
                }
            }
            try {
                this.mImpressionMinVisiblePercent = Integer.parseInt((String) serverExtras.get(DataKeys.IMPRESSION_MIN_VISIBLE_PERCENT));
            } catch (NumberFormatException e3) {
                MoPubLog.m12061d("Unable to parse impression min visible percent from server extras.");
                if (this.mImpressionVisiblePx == null || this.mImpressionVisiblePx.intValue() < 0) {
                    this.mHeadersAreValid = false;
                }
            }
            String videoTrackers = (String) serverExtras.get(DataKeys.VIDEO_TRACKERS_KEY);
            if (!TextUtils.isEmpty(videoTrackers)) {
                try {
                    this.mVideoTrackers = new JSONObject(videoTrackers);
                } catch (JSONException e4) {
                    MoPubLog.m12062d("Failed to parse video trackers to JSON: " + videoTrackers, e4);
                    this.mVideoTrackers = null;
                }
            }
        }

        boolean hasValidHeaders() {
            return this.mHeadersAreValid;
        }

        int getPlayVisiblePercent() {
            return this.mPlayVisiblePercent;
        }

        int getPauseVisiblePercent() {
            return this.mPauseVisiblePercent;
        }

        int getImpressionMinVisiblePercent() {
            return this.mImpressionMinVisiblePercent;
        }

        int getImpressionVisibleMs() {
            return this.mImpressionVisibleMs;
        }

        int getMaxBufferMs() {
            return this.mMaxBufferMs;
        }

        @Nullable
        Integer getImpressionVisiblePx() {
            return this.mImpressionVisiblePx;
        }

        JSONObject getVideoTrackers() {
            return this.mVideoTrackers;
        }
    }

    protected void loadNativeAd(@NonNull Context context, @NonNull CustomEventNativeListener customEventNativeListener, @NonNull Map<String, Object> localExtras, @NonNull Map<String, String> serverExtras) {
        Object json = localExtras.get(DataKeys.JSON_BODY_KEY);
        if (json instanceof JSONObject) {
            Object eventDetailsObject = localExtras.get(DataKeys.EVENT_DETAILS);
            EventDetails eventDetails = eventDetailsObject instanceof EventDetails ? (EventDetails) eventDetailsObject : null;
            VideoResponseHeaders videoResponseHeaders = new VideoResponseHeaders(serverExtras);
            if (videoResponseHeaders.hasValidHeaders()) {
                String clickTrackingUrlFromHeaderObject = localExtras.get(DataKeys.CLICK_TRACKING_URL_KEY);
                if (!(clickTrackingUrlFromHeaderObject instanceof String) || TextUtils.isEmpty(clickTrackingUrlFromHeaderObject)) {
                    customEventNativeListener.onNativeAdFailed(NativeErrorCode.UNSPECIFIED);
                    return;
                }
                try {
                    new MoPubVideoNativeAd(context, (JSONObject) json, customEventNativeListener, videoResponseHeaders, eventDetails, clickTrackingUrlFromHeaderObject).loadAd();
                    return;
                } catch (IllegalArgumentException e) {
                    customEventNativeListener.onNativeAdFailed(NativeErrorCode.UNSPECIFIED);
                    return;
                }
            }
            customEventNativeListener.onNativeAdFailed(NativeErrorCode.INVALID_RESPONSE);
            return;
        }
        customEventNativeListener.onNativeAdFailed(NativeErrorCode.INVALID_RESPONSE);
    }
}
