package com.appsgeyser.sdk.ads.fastTrack;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.view.ViewGroup;
import com.appsgeyser.sdk.InternalEntryPoint;
import com.appsgeyser.sdk.ads.fastTrack.adapters.FastTrackAdmobAdapter;
import com.appsgeyser.sdk.ads.fastTrack.adapters.FastTrackBaseAdapter;
import com.appsgeyser.sdk.ads.fastTrack.adapters.FastTrackBaseAdapter.FullscreenListener;
import com.appsgeyser.sdk.ads.fastTrack.adapters.FastTrackBaseAdapter.RewardedVideoListener;
import com.appsgeyser.sdk.ads.fastTrack.adapters.FastTrackFyberAdapter;
import com.appsgeyser.sdk.ads.fastTrack.adapters.FastTrackHtmlAdapter;
import com.appsgeyser.sdk.ads.fastTrack.adapters.FastTrackMopubAdapter;
import com.appsgeyser.sdk.configuration.models.ConfigPhp;
import com.appsgeyser.sdk.server.StatController;
import com.appsgeyser.sdk.ui.AppsgeyserProgressDialog;
import rx.subjects.BehaviorSubject;

public class FastTrackAdsController {
    private static final String KEY_HTML = "htmlSdk";
    public static final String fastTrackLogTag = "fastTrackTag";
    private FastTrackBaseAdapter adsAdapter;
    private BehaviorSubject<ViewGroup> bannerViewContainerPublishSubject = BehaviorSubject.create();
    private BehaviorSubject<ConfigPhp> consentPendingBlocker = BehaviorSubject.create();
    private Context context;
    private BehaviorSubject<FullscreenListener> fullscreenListenerPublishSubject = BehaviorSubject.create();
    private String fullscreenPendingRequestTag;
    private boolean isActive;

    public void requestInit(ConfigPhp configPhp, Context context) {
        this.context = context;
        this.consentPendingBlocker.onNext(configPhp);
    }

    public void consentRequestProcessFinished() {
        this.consentPendingBlocker.subscribe(FastTrackAdsController$$Lambda$1.lambdaFactory$(this));
    }

    private void init(ConfigPhp configPhp) {
        FastTrackSdkModel activeAdsSDK = configPhp.getActiveAdsSDK();
        String name = activeAdsSDK.getName();
        boolean z = true;
        switch (name.hashCode()) {
            case -963943683:
                if (name.equals(StatController.KEY_ADMOB)) {
                    z = false;
                    break;
                }
                break;
            case -261021665:
                if (name.equals(StatController.KEY_MOPUB)) {
                    z = true;
                    break;
                }
                break;
            case 294202302:
                if (name.equals(StatController.KEY_FYBER)) {
                    z = true;
                    break;
                }
                break;
            case 1236048015:
                if (name.equals(KEY_HTML)) {
                    z = true;
                    break;
                }
                break;
        }
        switch (z) {
            case false:
                Log.d(fastTrackLogTag, "initializing admob adsAdapter");
                this.adsAdapter = new FastTrackAdmobAdapter(activeAdsSDK, this.context);
                break;
            case true:
                Log.d(fastTrackLogTag, "initializing mopub adsAdapter");
                this.adsAdapter = new FastTrackMopubAdapter(activeAdsSDK, this.context);
                break;
            case true:
                Log.d(fastTrackLogTag, "initializing fyber adsAdapter");
                this.adsAdapter = new FastTrackFyberAdapter(activeAdsSDK, this.context);
                break;
            case true:
                Log.d(fastTrackLogTag, "initializing html adsAdapter");
                this.adsAdapter = new FastTrackHtmlAdapter(activeAdsSDK, this.context);
                break;
            default:
                Log.d(fastTrackLogTag, "unknown ads sdk: " + activeAdsSDK.getName());
                return;
        }
        this.adsAdapter.loadFullscreen();
        this.fullscreenListenerPublishSubject.subscribe(FastTrackAdsController$$Lambda$2.lambdaFactory$(this));
        this.bannerViewContainerPublishSubject.subscribe(FastTrackAdsController$$Lambda$3.lambdaFactory$(this));
        this.adsAdapter.loadRewardedVideo();
        this.isActive = true;
        if (this.fullscreenPendingRequestTag != null) {
            showFullscreen(this.fullscreenPendingRequestTag, this.context);
        }
    }

    public void showFullscreen(String loadTag, Context context) {
        if (!this.isActive || InternalEntryPoint.getInstance().isConsentRequestProcessActive()) {
            this.fullscreenPendingRequestTag = loadTag;
            Log.d(fastTrackLogTag, "fasttrack controller not activated");
            return;
        }
        if (context != null) {
            this.adsAdapter.setContext(context);
            this.adsAdapter.setProgressDialog(new AppsgeyserProgressDialog(context));
        }
        new Handler().postDelayed(FastTrackAdsController$$Lambda$4.lambdaFactory$(this, loadTag), 1000);
        this.fullscreenPendingRequestTag = null;
    }

    static /* synthetic */ void lambda$showFullscreen$2(FastTrackAdsController this_, String loadTag) {
        this_.adsAdapter.showFullscreen(loadTag);
        Log.d(fastTrackLogTag, "attempt to show fullscreen");
    }

    public void setFullscreenListener(FullscreenListener fullscreenListener) {
        if (fullscreenListener != null) {
            this.fullscreenListenerPublishSubject.onNext(fullscreenListener);
        }
    }

    public void setBannerViewContainer(ViewGroup bannerViewContainer) {
        if (bannerViewContainer != null) {
            bannerViewContainer.setVisibility(8);
            this.bannerViewContainerPublishSubject.onNext(bannerViewContainer);
        }
    }

    public void showRewardedVideo(RewardedVideoListener rewardedVideoListener) {
        if (rewardedVideoListener == null) {
            return;
        }
        if (this.isActive) {
            this.adsAdapter.showRewardedVideo(rewardedVideoListener);
            Log.d(fastTrackLogTag, "attempt to show rewardedVideo");
            return;
        }
        rewardedVideoListener.onVideoError("Video controller not activated");
        Log.d(fastTrackLogTag, "fasttrack controller not activated");
    }

    public boolean isActive() {
        return this.isActive;
    }

    public void onPause() {
        if (this.adsAdapter != null) {
            this.adsAdapter.onPause();
        }
    }

    public void onResume(Context context) {
        if (this.adsAdapter != null) {
            this.adsAdapter.onResume(context);
        }
    }

    public void onDestroy() {
        if (this.adsAdapter != null) {
            this.adsAdapter.onDestroy();
        }
    }

    public String getFullscreenPendingRequestTag() {
        return this.fullscreenPendingRequestTag;
    }
}
