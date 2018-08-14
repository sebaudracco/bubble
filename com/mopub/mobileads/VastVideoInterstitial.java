package com.mopub.mobileads;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.mopub.common.CacheService;
import com.mopub.common.DataKeys;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.Json;
import com.mopub.mobileads.CustomEventInterstitial.CustomEventInterstitialListener;
import com.mopub.mobileads.VastManager.VastManagerListener;
import com.mopub.mobileads.factories.VastManagerFactory;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

class VastVideoInterstitial extends ResponseBodyInterstitial implements VastManagerListener {
    private CustomEventInterstitialListener mCustomEventInterstitialListener;
    @Nullable
    private Map<String, String> mExternalViewabilityTrackers;
    private VastManager mVastManager;
    private String mVastResponse;
    private VastVideoConfig mVastVideoConfig;
    @Nullable
    private JSONObject mVideoTrackers;

    VastVideoInterstitial() {
    }

    protected void extractExtras(Map<String, String> serverExtras) {
        this.mVastResponse = (String) serverExtras.get(DataKeys.HTML_RESPONSE_BODY_KEY);
        String externalViewabilityTrackers = (String) serverExtras.get(DataKeys.EXTERNAL_VIDEO_VIEWABILITY_TRACKERS_KEY);
        try {
            this.mExternalViewabilityTrackers = Json.jsonStringToMap(externalViewabilityTrackers);
        } catch (JSONException e) {
            MoPubLog.d("Failed to parse video viewability trackers to JSON: " + externalViewabilityTrackers);
        }
        String videoTrackers = (String) serverExtras.get(DataKeys.VIDEO_TRACKERS_KEY);
        if (!TextUtils.isEmpty(videoTrackers)) {
            try {
                this.mVideoTrackers = new JSONObject(videoTrackers);
            } catch (JSONException e2) {
                MoPubLog.d("Failed to parse video trackers to JSON: " + videoTrackers, e2);
                this.mVideoTrackers = null;
            }
        }
    }

    protected void preRenderHtml(CustomEventInterstitialListener customEventInterstitialListener) {
        this.mCustomEventInterstitialListener = customEventInterstitialListener;
        if (CacheService.initializeDiskCache(this.mContext)) {
            this.mVastManager = VastManagerFactory.create(this.mContext);
            this.mVastManager.prepareVastVideoConfiguration(this.mVastResponse, this, this.mAdReport.getDspCreativeId(), this.mContext);
            return;
        }
        this.mCustomEventInterstitialListener.onInterstitialFailed(MoPubErrorCode.VIDEO_CACHE_ERROR);
    }

    public void showInterstitial() {
        MraidVideoPlayerActivity.startVast(this.mContext, this.mVastVideoConfig, this.mBroadcastIdentifier);
    }

    public void onInvalidate() {
        if (this.mVastManager != null) {
            this.mVastManager.cancel();
        }
        super.onInvalidate();
    }

    public void onVastVideoConfigurationPrepared(VastVideoConfig vastVideoConfig) {
        if (vastVideoConfig == null) {
            this.mCustomEventInterstitialListener.onInterstitialFailed(MoPubErrorCode.VIDEO_DOWNLOAD_ERROR);
            return;
        }
        this.mVastVideoConfig = vastVideoConfig;
        this.mVastVideoConfig.addVideoTrackers(this.mVideoTrackers);
        this.mVastVideoConfig.addExternalViewabilityTrackers(this.mExternalViewabilityTrackers);
        this.mCustomEventInterstitialListener.onInterstitialLoaded();
    }

    @Deprecated
    String getVastResponse() {
        return this.mVastResponse;
    }

    @Deprecated
    void setVastManager(VastManager vastManager) {
        this.mVastManager = vastManager;
    }
}
