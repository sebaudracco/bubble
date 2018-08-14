package com.appnext.ads.fullscreen;

import android.content.Context;
import com.appnext.core.Ad;

public class FullScreenVideo extends Video {
    private boolean canClose = true;
    private long closeDelay = 0;
    private boolean showClose = true;

    protected FullScreenVideo(Context context, FullScreenVideo fullScreenVideo) {
        super(context, 1, fullScreenVideo.getPlacementID());
        setPostback(fullScreenVideo.getPostback());
        setCategories(fullScreenVideo.getCategories());
        setOrientation(fullScreenVideo.getOrientation());
        setShowClose(fullScreenVideo.isShowClose());
        setCloseDelay(fullScreenVideo.getCloseDelay());
        setVideoLength(fullScreenVideo.getVideoLength());
        setBackButtonCanClose(fullScreenVideo.isBackButtonCanClose());
        setMute(fullScreenVideo.getMute());
        setMinVideoLength(fullScreenVideo.getMinVideoLength());
        setMaxVideoLength(fullScreenVideo.getMaxVideoLength());
        setRollCaptionTime(fullScreenVideo.getRollCaptionTime());
        setShowCta(fullScreenVideo.isShowCta());
        setOnVideoEndedCallback(fullScreenVideo.getOnVideoEndedCallback());
        setOnAdClosedCallback(fullScreenVideo.getOnAdClosedCallback());
        setOnAdErrorCallback(fullScreenVideo.getOnAdErrorCallback());
        setOnAdClickedCallback(fullScreenVideo.getOnAdClickedCallback());
        setOnAdOpenedCallback(fullScreenVideo.getOnAdOpenedCallback());
        setOnAdLoadedCallback(fullScreenVideo.getOnAdLoadedCallback());
        setSessionId(fullScreenVideo.getSessionId());
        this.fq_status = Ad.fq;
    }

    public FullScreenVideo(Context context, String str) {
        super(context, 1, str);
    }

    public FullScreenVideo(Context context, String str, FullscreenConfig fullscreenConfig) {
        super(context, 1, str, fullscreenConfig);
        if (fullscreenConfig != null) {
            setBackButtonCanClose(fullscreenConfig.isBackButtonCanClose());
            if (fullscreenConfig.ah() != null) {
                setShowClose(fullscreenConfig.isShowClose());
            }
            setCloseDelay(fullscreenConfig.closeDelay);
        }
    }

    public boolean isBackButtonCanClose() {
        return this.canClose;
    }

    public void setBackButtonCanClose(boolean z) {
        this.canClose = z;
    }

    public boolean isShowClose() {
        return this.showClose;
    }

    public void setShowClose(boolean z) {
        this.showClose = z;
    }

    public void setShowClose(boolean z, long j) {
        this.showClose = z;
        if (j >= 100 && j <= 7500) {
            this.closeDelay = j;
        }
    }

    public long getCloseDelay() {
        return this.closeDelay;
    }

    protected C0922c getConfig() {
        return C0922c.aj();
    }

    public String getAUID() {
        return "700";
    }

    protected void setCloseDelay(long j) {
        this.closeDelay = j;
    }
}
