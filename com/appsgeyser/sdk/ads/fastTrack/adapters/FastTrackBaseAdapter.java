package com.appsgeyser.sdk.ads.fastTrack.adapters;

import android.content.Context;
import android.os.Handler;
import android.view.ViewGroup;
import com.appsgeyser.sdk.ads.fastTrack.FastTrackSdkModel;
import com.appsgeyser.sdk.configuration.Configuration;
import com.appsgeyser.sdk.ui.AppsgeyserProgressDialog;
import java.util.HashMap;

public abstract class FastTrackBaseAdapter {
    final HashMap<String, String> appDetails = new HashMap();
    final HashMap<String, String> bannerDetails = new HashMap();
    ViewGroup bannerViewContainer;
    Context context;
    FastTrackSdkModel fastTrackSdkModel;
    FullscreenListener fullscreenListener;
    Handler handler;
    final HashMap<String, String> interstitialDetails = new HashMap();
    boolean pendingFullscreenRequest;
    AppsgeyserProgressDialog progressDialog;
    final HashMap<String, String> rewardedDetails = new HashMap();
    RewardedVideoListener rewardedVideoListener;
    boolean videoDownloadError;

    public interface FullscreenListener {
        void onClose();

        void onFailedToShow();

        void onRequest();

        void onShow();
    }

    public interface RewardedVideoListener {
        void onVideoClicked();

        void onVideoClosed();

        void onVideoError(String str);

        void onVideoFinished();

        void onVideoOpened();
    }

    abstract void init();

    public abstract void initBannerView(ViewGroup viewGroup);

    public abstract void loadFullscreen();

    public abstract void loadRewardedVideo();

    public abstract void onDestroy();

    public abstract void onPause();

    public abstract void setFullscreenListener(FullscreenListener fullscreenListener);

    public abstract void showFullscreen(String str);

    public abstract void showRewardedVideo(RewardedVideoListener rewardedVideoListener);

    FastTrackBaseAdapter(FastTrackSdkModel fastTrackSdkModel, Context context) {
        this.fastTrackSdkModel = fastTrackSdkModel;
        this.context = context;
        Configuration configuration = Configuration.getInstance(context);
        this.appDetails.put("widget_id", configuration.getApplicationId());
        this.appDetails.put("wdid", configuration.getApplicationId());
        this.appDetails.put("guid", configuration.getAppGuid());
        this.progressDialog = new AppsgeyserProgressDialog(context);
        this.handler = new Handler(context.getMainLooper());
        init();
    }

    public void onResume(Context context) {
        if (this.context != null && !this.context.equals(context)) {
            this.context = context;
        }
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public Context getContext() {
        return this.context;
    }

    public AppsgeyserProgressDialog getProgressDialog() {
        return this.progressDialog;
    }

    public void setProgressDialog(AppsgeyserProgressDialog progressDialog) {
        this.progressDialog = progressDialog;
    }
}
