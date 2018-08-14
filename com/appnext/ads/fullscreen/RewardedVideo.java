package com.appnext.ads.fullscreen;

import android.content.Context;
import com.appnext.core.Ad;
import com.appnext.core.C0921q;

public class RewardedVideo extends Video {
    public static final String VIDEO_MODE_DEFAULT = "default";
    public static final String VIDEO_MODE_MULTI = "multi";
    public static final String VIDEO_MODE_NORMAL = "normal";
    private String mode = "default";
    private int multiTimerLength = 5;
    private RewardedServerSidePostback rewardedServerSidePostback;

    protected RewardedVideo(Context context, RewardedVideo rewardedVideo) {
        super(context, 2, rewardedVideo.getPlacementID());
        setPostback(rewardedVideo.getPostback());
        setCategories(rewardedVideo.getCategories());
        setOrientation(rewardedVideo.getOrientation());
        setVideoLength(rewardedVideo.getVideoLength());
        setMute(rewardedVideo.getMute());
        setMinVideoLength(rewardedVideo.getMinVideoLength());
        setMaxVideoLength(rewardedVideo.getMaxVideoLength());
        setRewardedServerSidePostback(rewardedVideo.getRewardedServerSidePostback());
        setMode(rewardedVideo.getMode());
        setMultiTimerLength(rewardedVideo.getMultiTimerLength());
        setShowCta(rewardedVideo.isShowCta());
        setRollCaptionTime(rewardedVideo.getRollCaptionTime());
        setOnVideoEndedCallback(rewardedVideo.getOnVideoEndedCallback());
        setOnAdClosedCallback(rewardedVideo.getOnAdClosedCallback());
        setOnAdErrorCallback(rewardedVideo.getOnAdErrorCallback());
        setOnAdClickedCallback(rewardedVideo.getOnAdClickedCallback());
        setOnAdOpenedCallback(rewardedVideo.getOnAdOpenedCallback());
        setOnAdLoadedCallback(rewardedVideo.getOnAdLoadedCallback());
        setSessionId(rewardedVideo.getSessionId());
        this.fq_status = Ad.fq;
    }

    public RewardedVideo(Context context, String str) {
        super(context, 2, str);
    }

    public String getAUID() {
        return "800";
    }

    public RewardedVideo(Context context, String str, RewardedConfig rewardedConfig) {
        super(context, 2, str, rewardedConfig);
        setMode(rewardedConfig.getMode());
        setMultiTimerLength(rewardedConfig.getMultiTimerLength());
        setShowCta(rewardedConfig.isShowCta());
    }

    protected C0921q getConfig() {
        return C0940f.al();
    }

    protected RewardedServerSidePostback getRewardedServerSidePostback() {
        if (getRewardsTransactionId().equals("") && getRewardsUserId().equals("") && getRewardsRewardTypeCurrency().equals("") && getRewardsAmountRewarded().equals("") && getRewardsCustomParameter().equals("")) {
            return null;
        }
        return this.rewardedServerSidePostback;
    }

    protected void setRewardedServerSidePostback(RewardedServerSidePostback rewardedServerSidePostback) {
        this.rewardedServerSidePostback = rewardedServerSidePostback;
    }

    public void setRewardedServerSidePostback(String str, String str2, String str3, String str4, String str5) {
        setRewardsTransactionId(str);
        setRewardsUserId(str2);
        setRewardsRewardTypeCurrency(str3);
        setRewardsAmountRewarded(str4);
        setRewardsCustomParameter(str5);
    }

    public String getRewardsTransactionId() {
        if (this.rewardedServerSidePostback == null) {
            return "";
        }
        return this.rewardedServerSidePostback.getRewardsTransactionId();
    }

    public void setRewardsTransactionId(String str) {
        if (this.rewardedServerSidePostback == null) {
            this.rewardedServerSidePostback = new RewardedServerSidePostback();
        }
        this.rewardedServerSidePostback.setRewardsTransactionId(str);
    }

    public String getRewardsUserId() {
        if (this.rewardedServerSidePostback == null) {
            return "";
        }
        return this.rewardedServerSidePostback.getRewardsUserId();
    }

    public void setRewardsUserId(String str) {
        if (this.rewardedServerSidePostback == null) {
            this.rewardedServerSidePostback = new RewardedServerSidePostback();
        }
        this.rewardedServerSidePostback.setRewardsUserId(str);
    }

    public String getRewardsRewardTypeCurrency() {
        if (this.rewardedServerSidePostback == null) {
            return "";
        }
        return this.rewardedServerSidePostback.getRewardsRewardTypeCurrency();
    }

    public void setRewardsRewardTypeCurrency(String str) {
        if (this.rewardedServerSidePostback == null) {
            this.rewardedServerSidePostback = new RewardedServerSidePostback();
        }
        this.rewardedServerSidePostback.setRewardsRewardTypeCurrency(str);
    }

    public String getRewardsAmountRewarded() {
        if (this.rewardedServerSidePostback == null) {
            return "";
        }
        return this.rewardedServerSidePostback.getRewardsAmountRewarded();
    }

    public void setRewardsAmountRewarded(String str) {
        if (this.rewardedServerSidePostback == null) {
            this.rewardedServerSidePostback = new RewardedServerSidePostback();
        }
        this.rewardedServerSidePostback.setRewardsAmountRewarded(str);
    }

    public String getRewardsCustomParameter() {
        if (this.rewardedServerSidePostback == null) {
            return "";
        }
        return this.rewardedServerSidePostback.getRewardsCustomParameter();
    }

    public void setRewardsCustomParameter(String str) {
        if (this.rewardedServerSidePostback == null) {
            this.rewardedServerSidePostback = new RewardedServerSidePostback();
        }
        this.rewardedServerSidePostback.setRewardsCustomParameter(str);
    }

    public String getMode() {
        return this.mode;
    }

    public void setMode(String str) {
        this.mode = str;
    }

    public int getMultiTimerLength() {
        return this.multiTimerLength;
    }

    public void setMultiTimerLength(int i) {
        if (i >= 1 && i <= 20) {
            this.multiTimerLength = i;
        }
    }
}
