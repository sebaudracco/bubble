package com.integralads.avid.library.mopub.video;

import com.integralads.avid.library.mopub.base.AvidBaseListenerImpl;
import com.integralads.avid.library.mopub.session.internal.InternalAvidAdSession;
import com.integralads.avid.library.mopub.session.internal.jsbridge.AvidBridgeManager;
import org.json.JSONException;
import org.json.JSONObject;

public class AvidVideoPlaybackListenerImpl extends AvidBaseListenerImpl implements AvidVideoPlaybackListener {
    public static final String AD_CLICK_THRU = "AdClickThru";
    public static final String AD_DURATION = "adDuration";
    public static final String AD_DURATION_CHANGE = "AdDurationChange";
    public static final String AD_ENTERED_FULLSCREEN = "AdEnteredFullscreen";
    public static final String AD_ERROR = "AdError";
    public static final String AD_EXITED_FULLSCREEN = "AdExitedFullscreen";
    public static final String AD_EXPANDED_CHANGE = "AdExpandedChange";
    public static final String AD_IMPRESSION = "AdImpression";
    public static final String AD_LOADED = "AdLoaded";
    public static final String AD_PAUSED = "AdPaused";
    public static final String AD_PLAYING = "AdPlaying";
    public static final String AD_REMAINING_TIME = "adDuration";
    public static final String AD_SKIPPED = "AdSkipped";
    public static final String AD_STARTED = "AdStarted";
    public static final String AD_STOPPED = "AdStopped";
    public static final String AD_USER_ACCEPT_INVITATION = "AdUserAcceptInvitation";
    public static final String AD_USER_CLOSE = "AdUserClose";
    public static final String AD_USER_MINIMIZE = "AdUserMinimize";
    public static final String AD_VIDEO_COMPLETE = "AdVideoComplete";
    public static final String AD_VIDEO_FIRST_QUARTILE = "AdVideoFirstQuartile";
    public static final String AD_VIDEO_MIDPOINT = "AdVideoMidpoint";
    public static final String AD_VIDEO_START = "AdVideoStart";
    public static final String AD_VIDEO_THIRD_QUARTILE = "AdVideoThirdQuartile";
    public static final String AD_VOLUME_CHANGE = "AdVolumeChange";
    public static final String MESSAGE = "message";
    public static final String VOLUME = "volume";

    public AvidVideoPlaybackListenerImpl(InternalAvidAdSession avidAdSession, AvidBridgeManager publisher) {
        super(avidAdSession, publisher);
    }

    public void recordAdImpressionEvent() {
        publishVideoEvent("AdImpression", null);
    }

    public void recordAdStartedEvent() {
        publishVideoEvent("AdStarted", null);
    }

    public void recordAdLoadedEvent() {
        publishVideoEvent("AdLoaded", null);
    }

    public void recordAdVideoStartEvent() {
        publishVideoEvent("AdVideoStart", null);
    }

    public void recordAdStoppedEvent() {
        publishVideoEvent("AdStopped", null);
    }

    public void recordAdCompleteEvent() {
        publishVideoEvent("AdVideoComplete", null);
    }

    public void recordAdClickThruEvent() {
        publishVideoEvent("AdClickThru", null);
    }

    public void recordAdVideoFirstQuartileEvent() {
        publishVideoEvent("AdVideoFirstQuartile", null);
    }

    public void recordAdVideoMidpointEvent() {
        publishVideoEvent("AdVideoMidpoint", null);
    }

    public void recordAdVideoThirdQuartileEvent() {
        publishVideoEvent("AdVideoThirdQuartile", null);
    }

    public void recordAdPausedEvent() {
        publishVideoEvent("AdPaused", null);
    }

    public void recordAdPlayingEvent() {
        publishVideoEvent("AdPlaying", null);
    }

    public void recordAdExpandedChangeEvent() {
        publishVideoEvent("AdExpandedChange", null);
    }

    public void recordAdUserMinimizeEvent() {
        publishVideoEvent("AdUserMinimize", null);
    }

    public void recordAdUserAcceptInvitationEvent() {
        publishVideoEvent("AdUserAcceptInvitation", null);
    }

    public void recordAdUserCloseEvent() {
        publishVideoEvent("AdUserClose", null);
    }

    public void recordAdSkippedEvent() {
        publishVideoEvent("AdSkipped", null);
    }

    public void recordAdVolumeChangeEvent(Integer volume) {
        JSONObject data = new JSONObject();
        try {
            data.put("volume", volume);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        publishVideoEvent("AdVolumeChange", data);
    }

    public void recordAdEnteredFullscreenEvent() {
        publishVideoEvent("AdEnteredFullscreen", null);
    }

    public void recordAdExitedFullscreenEvent() {
        publishVideoEvent("AdExitedFullscreen", null);
    }

    public void recordAdDurationChangeEvent(String adDuration, String adRemainingTime) {
        JSONObject data = new JSONObject();
        try {
            data.put("adDuration", adDuration);
            data.put("adDuration", adRemainingTime);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        publishVideoEvent("AdDurationChange", data);
    }

    public void recordAdError(String message) {
        JSONObject data = new JSONObject();
        try {
            data.put("message", message);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        publishVideoEvent("AdError", data);
    }

    private void publishVideoEvent(String event, JSONObject data) {
        assertSessionIsNotEnded();
        assertSessionIsReady();
        getAvidBridgeManager().publishVideoEvent(event, data);
    }

    private void assertSessionIsReady() {
        if (!getAvidAdSession().isReady()) {
            throw new IllegalStateException("The AVID ad session is not ready. Please ensure you have called recordReadyEvent for the deferred AVID ad session before recording any video event.");
        }
    }
}
