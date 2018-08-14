package com.integralads.avid.library.adcolony.video;

import com.integralads.avid.library.adcolony.base.AvidBaseListenerImpl;
import com.integralads.avid.library.adcolony.session.internal.InternalAvidAdSession;
import com.integralads.avid.library.adcolony.session.internal.jsbridge.AvidBridgeManager;
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
        m11166a("AdImpression", null);
    }

    public void recordAdStartedEvent() {
        m11166a("AdStarted", null);
    }

    public void recordAdLoadedEvent() {
        m11166a("AdLoaded", null);
    }

    public void recordAdVideoStartEvent() {
        m11166a("AdVideoStart", null);
    }

    public void recordAdStoppedEvent() {
        m11166a("AdStopped", null);
    }

    public void recordAdCompleteEvent() {
        m11166a("AdVideoComplete", null);
    }

    public void recordAdClickThruEvent() {
        m11166a("AdClickThru", null);
    }

    public void recordAdVideoFirstQuartileEvent() {
        m11166a("AdVideoFirstQuartile", null);
    }

    public void recordAdVideoMidpointEvent() {
        m11166a("AdVideoMidpoint", null);
    }

    public void recordAdVideoThirdQuartileEvent() {
        m11166a("AdVideoThirdQuartile", null);
    }

    public void recordAdPausedEvent() {
        m11166a("AdPaused", null);
    }

    public void recordAdPlayingEvent() {
        m11166a("AdPlaying", null);
    }

    public void recordAdExpandedChangeEvent() {
        m11166a("AdExpandedChange", null);
    }

    public void recordAdUserMinimizeEvent() {
        m11166a("AdUserMinimize", null);
    }

    public void recordAdUserAcceptInvitationEvent() {
        m11166a("AdUserAcceptInvitation", null);
    }

    public void recordAdUserCloseEvent() {
        m11166a("AdUserClose", null);
    }

    public void recordAdSkippedEvent() {
        m11166a("AdSkipped", null);
    }

    public void recordAdVolumeChangeEvent(Integer volume) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("volume", volume);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        m11166a("AdVolumeChange", jSONObject);
    }

    public void recordAdEnteredFullscreenEvent() {
        m11166a("AdEnteredFullscreen", null);
    }

    public void recordAdExitedFullscreenEvent() {
        m11166a("AdExitedFullscreen", null);
    }

    public void recordAdDurationChangeEvent(String adDuration, String adRemainingTime) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("adDuration", adDuration);
            jSONObject.put("adDuration", adRemainingTime);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        m11166a("AdDurationChange", jSONObject);
    }

    public void recordAdError(String message) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("message", message);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        m11166a("AdError", jSONObject);
    }

    private void m11166a(String str, JSONObject jSONObject) {
        assertSessionIsNotEnded();
        m11165a();
        getAvidBridgeManager().publishVideoEvent(str, jSONObject);
    }

    private void m11165a() {
        if (!getAvidAdSession().isReady()) {
            throw new IllegalStateException("The AVID ad session is not ready. Please ensure you have called recordReadyEvent for the deferred AVID ad session before recording any video event.");
        }
    }
}
