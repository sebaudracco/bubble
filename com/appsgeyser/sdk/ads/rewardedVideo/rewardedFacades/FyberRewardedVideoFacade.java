package com.appsgeyser.sdk.ads.rewardedVideo.rewardedFacades;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.appsgeyser.sdk.ads.rewardedVideo.FyberCallbackActivity;
import com.appsgeyser.sdk.ads.rewardedVideo.FyberCallbackActivity.fyberVideoClosedEvent;
import com.appsgeyser.sdk.ads.rewardedVideo.FyberCallbackActivity.fyberVideoErrorEvent;
import com.appsgeyser.sdk.ads.rewardedVideo.FyberCallbackActivity.fyberVideoFinishedEvent;
import com.appsgeyser.sdk.ads.rewardedVideo.FyberCallbackActivity.fyberVideoOpenedEvent;
import com.appsgeyser.sdk.configuration.models.AdNetworkSdkModel;
import com.appsgeyser.sdk.configuration.models.ConfigPhp;
import com.appsgeyser.sdk.server.StatController;
import com.appsgeyser.sdk.server.StatController.AdsType;
import com.fyber.Fyber;
import com.fyber.ads.AdFormat;
import com.fyber.requesters.RequestCallback;
import com.fyber.requesters.RequestError;
import com.fyber.requesters.RewardedVideoRequester;
import de.greenrobot.event.EventBus;

public class FyberRewardedVideoFacade extends AbstractRewardedFacade {
    private Intent videoIntent = null;

    class C12841 implements RequestCallback {
        C12841() {
        }

        public void onAdAvailable(Intent intent) {
            FyberRewardedVideoFacade.this.videoIntent = intent;
            FyberRewardedVideoFacade.this.listener.onVideoLoaded();
        }

        public void onAdNotAvailable(AdFormat adFormat) {
            FyberRewardedVideoFacade.this.listener.onVideoError("No fill for Fyber rewarded video");
        }

        public void onRequestError(RequestError requestError) {
            FyberRewardedVideoFacade.this.listener.onVideoError(requestError.getDescription());
        }
    }

    public FyberRewardedVideoFacade(Context context, ConfigPhp configPhp) {
        super(context, configPhp);
    }

    protected void setPriority() {
        this.priority = ((AdNetworkSdkModel) this.configPhp.getRewardedVideoSdk().get(StatController.KEY_FYBER)).getPriority();
    }

    protected void init() {
        Fyber.with(((AdNetworkSdkModel) this.configPhp.getRewardedVideoSdk().get(StatController.KEY_FYBER)).getAppId(), (Activity) this.context).withSecurityToken(((AdNetworkSdkModel) this.configPhp.getRewardedVideoSdk().get(StatController.KEY_FYBER)).getPlacementId()).start();
        EventBus.getDefault().register(this);
    }

    public void loadRewardedVideo() {
        this.videoIntent = null;
        RewardedVideoRequester.create(new C12841()).request(this.context);
    }

    public boolean isVideoLoaded() {
        return this.videoIntent != null;
    }

    public void showRewardedVideo() {
        FyberCallbackActivity.start(this.context, this.videoIntent, FyberCallbackActivity.REWARDED_TYPE);
    }

    public void onEvent(fyberVideoOpenedEvent event) {
        this.listener.onVideoOpened();
        StatController.getInstance().sendRequestAsyncByKey(StatController.KEY_FYBER_REWARDED_SDK_IMPRESSION, StatController.generateQueryParametersForSdk(this.configPhp, this.context, AdsType.REWARDED, "fyber rewarded video impression", StatController.KEY_FYBER), this.context, false);
    }

    public void onEvent(fyberVideoFinishedEvent event) {
        this.listener.onVideoFinished();
    }

    public void onEvent(fyberVideoClosedEvent event) {
        this.listener.onVideoClosed();
    }

    public void onEvent(fyberVideoErrorEvent event) {
        this.listener.onVideoError(event.errorMessage);
    }
}
