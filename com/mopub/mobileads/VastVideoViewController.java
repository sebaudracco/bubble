package com.mopub.mobileads;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.VideoView;
import com.mopub.common.ExternalViewabilitySession.VideoEvent;
import com.mopub.common.ExternalViewabilitySessionManager;
import com.mopub.common.IntentActions;
import com.mopub.common.Preconditions;
import com.mopub.common.VisibleForTesting;
import com.mopub.common.util.Dips;
import com.mopub.common.util.Utils;
import com.mopub.mobileads.BaseVideoViewController.BaseVideoViewControllerListener;
import java.io.Serializable;
import java.util.Map;

public class VastVideoViewController extends BaseVideoViewController {
    static final String CURRENT_POSITION = "current_position";
    static final int DEFAULT_VIDEO_DURATION_FOR_CLOSE_BUTTON = 5000;
    static final int MAX_VIDEO_DURATION_FOR_CLOSE_BUTTON = 16000;
    static final String RESUMED_VAST_CONFIG = "resumed_vast_config";
    private static final int SEEKER_POSITION_NOT_INITIALIZED = -1;
    static final String VAST_VIDEO_CONFIG = "vast_video_config";
    private static final long VIDEO_COUNTDOWN_UPDATE_INTERVAL = 250;
    private static final long VIDEO_PROGRESS_TIMER_CHECKER_DELAY = 50;
    public static final int WEBVIEW_PADDING = 16;
    @NonNull
    private View mAdsByView;
    @NonNull
    private ImageView mBlurredLastVideoFrameImageView;
    @NonNull
    private VastVideoGradientStripWidget mBottomGradientStripWidget;
    @NonNull
    private final OnTouchListener mClickThroughListener;
    @NonNull
    private VastVideoCloseButtonWidget mCloseButtonWidget;
    @NonNull
    private final VastVideoViewCountdownRunnable mCountdownRunnable;
    @NonNull
    private VastVideoCtaButtonWidget mCtaButtonWidget;
    private int mDuration;
    @NonNull
    private ExternalViewabilitySessionManager mExternalViewabilitySessionManager;
    private boolean mHasSkipOffset = false;
    private boolean mHasSocialActions = false;
    @NonNull
    private final View mIconView;
    private boolean mIsCalibrationDone = false;
    private boolean mIsClosing = false;
    private boolean mIsVideoFinishedPlaying;
    @NonNull
    private final View mLandscapeCompanionAdView;
    @NonNull
    private final View mPortraitCompanionAdView;
    @NonNull
    private VastVideoProgressBarWidget mProgressBarWidget;
    @NonNull
    private final VastVideoViewProgressRunnable mProgressCheckerRunnable;
    @NonNull
    private VastVideoRadialCountdownWidget mRadialCountdownWidget;
    private int mSeekerPositionOnPause = -1;
    private int mShowCloseButtonDelay = 5000;
    private boolean mShowCloseButtonEventFired;
    @NonNull
    private final Map<String, VastCompanionAdConfig> mSocialActionsCompanionAds;
    @NonNull
    private final View mSocialActionsView;
    @NonNull
    private VastVideoGradientStripWidget mTopGradientStripWidget;
    @Nullable
    private VastCompanionAdConfig mVastCompanionAdConfig;
    @Nullable
    private final VastIconConfig mVastIconConfig;
    private final VastVideoConfig mVastVideoConfig;
    private boolean mVideoError;
    @NonNull
    private final VastVideoView mVideoView;

    VastVideoViewController(Activity activity, Bundle intentExtras, @Nullable Bundle savedInstanceState, long broadcastIdentifier, BaseVideoViewControllerListener baseVideoViewControllerListener) throws IllegalStateException {
        super(activity, Long.valueOf(broadcastIdentifier), baseVideoViewControllerListener);
        Serializable resumedVastConfiguration = null;
        if (savedInstanceState != null) {
            resumedVastConfiguration = savedInstanceState.getSerializable(RESUMED_VAST_CONFIG);
        }
        Serializable serializable = intentExtras.getSerializable(VAST_VIDEO_CONFIG);
        if (resumedVastConfiguration != null && (resumedVastConfiguration instanceof VastVideoConfig)) {
            this.mVastVideoConfig = (VastVideoConfig) resumedVastConfiguration;
            this.mSeekerPositionOnPause = savedInstanceState.getInt(CURRENT_POSITION, -1);
        } else if (serializable == null || !(serializable instanceof VastVideoConfig)) {
            throw new IllegalStateException("VastVideoConfig is invalid");
        } else {
            this.mVastVideoConfig = (VastVideoConfig) serializable;
        }
        if (this.mVastVideoConfig.getDiskMediaFileUrl() == null) {
            throw new IllegalStateException("VastVideoConfig does not have a video disk path");
        }
        this.mVastCompanionAdConfig = this.mVastVideoConfig.getVastCompanionAd(activity.getResources().getConfiguration().orientation);
        this.mSocialActionsCompanionAds = this.mVastVideoConfig.getSocialActionsCompanionAds();
        this.mVastIconConfig = this.mVastVideoConfig.getVastIconConfig();
        this.mClickThroughListener = new 1(this, activity);
        getLayout().setBackgroundColor(-16777216);
        addBlurredLastVideoFrameImageView(activity, 4);
        this.mVideoView = createVideoView(activity, 0);
        this.mVideoView.requestFocus();
        this.mExternalViewabilitySessionManager = new ExternalViewabilitySessionManager(activity);
        this.mExternalViewabilitySessionManager.createVideoSession(activity, this.mVideoView, this.mVastVideoConfig);
        this.mExternalViewabilitySessionManager.registerVideoObstruction(this.mBlurredLastVideoFrameImageView);
        this.mLandscapeCompanionAdView = createCompanionAdView(activity, this.mVastVideoConfig.getVastCompanionAd(2), 4);
        this.mPortraitCompanionAdView = createCompanionAdView(activity, this.mVastVideoConfig.getVastCompanionAd(1), 4);
        addTopGradientStripWidget(activity);
        addProgressBarWidget(activity, 4);
        addBottomGradientStripWidget(activity);
        addRadialCountdownWidget(activity, 4);
        this.mIconView = createIconView(activity, this.mVastIconConfig, 4);
        this.mIconView.getViewTreeObserver().addOnGlobalLayoutListener(new 2(this, activity));
        addCtaButtonWidget(activity);
        Context context = activity;
        this.mSocialActionsView = createSocialActionsView(context, (VastCompanionAdConfig) this.mSocialActionsCompanionAds.get(VastXmlManagerAggregator.SOCIAL_ACTIONS_AD_SLOT_ID), Dips.dipsToIntPixels(38.0f, activity), 6, this.mCtaButtonWidget, 4, 16);
        addCloseButtonWidget(activity, 8);
        Handler mainHandler = new Handler(Looper.getMainLooper());
        this.mProgressCheckerRunnable = new VastVideoViewProgressRunnable(this, this.mVastVideoConfig, mainHandler);
        this.mCountdownRunnable = new VastVideoViewCountdownRunnable(this, mainHandler);
    }

    @VisibleForTesting
    View createAdsByView(Activity activity) {
        return createSocialActionsView(activity, (VastCompanionAdConfig) this.mSocialActionsCompanionAds.get(VastXmlManagerAggregator.ADS_BY_AD_SLOT_ID), this.mIconView.getHeight(), 1, this.mIconView, 0, 6);
    }

    @Deprecated
    @VisibleForTesting
    boolean getHasSocialActions() {
        return this.mHasSocialActions;
    }

    @Deprecated
    @VisibleForTesting
    View getSocialActionsView() {
        return this.mSocialActionsView;
    }

    protected VideoView getVideoView() {
        return this.mVideoView;
    }

    protected void onCreate() {
        super.onCreate();
        switch (11.$SwitchMap$com$mopub$common$util$DeviceUtils$ForceOrientation[this.mVastVideoConfig.getCustomForceOrientation().ordinal()]) {
            case 1:
                getBaseVideoViewControllerListener().onSetRequestedOrientation(1);
                break;
            case 2:
                getBaseVideoViewControllerListener().onSetRequestedOrientation(6);
                break;
        }
        this.mVastVideoConfig.handleImpression(getContext(), getCurrentPosition());
        broadcastAction(IntentActions.ACTION_INTERSTITIAL_SHOW);
    }

    protected void onResume() {
        startRunnables();
        if (this.mSeekerPositionOnPause > 0) {
            this.mExternalViewabilitySessionManager.recordVideoEvent(VideoEvent.AD_PLAYING, this.mSeekerPositionOnPause);
            this.mVideoView.seekTo(this.mSeekerPositionOnPause);
        } else {
            this.mExternalViewabilitySessionManager.recordVideoEvent(VideoEvent.AD_LOADED, getCurrentPosition());
        }
        if (!this.mIsVideoFinishedPlaying) {
            this.mVideoView.start();
        }
        if (this.mSeekerPositionOnPause != -1) {
            this.mVastVideoConfig.handleResume(getContext(), this.mSeekerPositionOnPause);
        }
    }

    protected void onPause() {
        stopRunnables();
        this.mSeekerPositionOnPause = getCurrentPosition();
        this.mVideoView.pause();
        if (!this.mIsVideoFinishedPlaying && !this.mIsClosing) {
            this.mExternalViewabilitySessionManager.recordVideoEvent(VideoEvent.AD_PAUSED, getCurrentPosition());
            this.mVastVideoConfig.handlePause(getContext(), this.mSeekerPositionOnPause);
        }
    }

    protected void onDestroy() {
        stopRunnables();
        this.mExternalViewabilitySessionManager.recordVideoEvent(VideoEvent.AD_STOPPED, getCurrentPosition());
        this.mExternalViewabilitySessionManager.endVideoSession();
        broadcastAction(IntentActions.ACTION_INTERSTITIAL_DISMISS);
        this.mVideoView.onDestroy();
    }

    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt(CURRENT_POSITION, this.mSeekerPositionOnPause);
        outState.putSerializable(RESUMED_VAST_CONFIG, this.mVastVideoConfig);
    }

    protected void onConfigurationChanged(Configuration newConfig) {
        int orientation = getContext().getResources().getConfiguration().orientation;
        this.mVastCompanionAdConfig = this.mVastVideoConfig.getVastCompanionAd(orientation);
        if (this.mLandscapeCompanionAdView.getVisibility() == 0 || this.mPortraitCompanionAdView.getVisibility() == 0) {
            if (orientation == 1) {
                this.mLandscapeCompanionAdView.setVisibility(4);
                this.mPortraitCompanionAdView.setVisibility(0);
            } else {
                this.mPortraitCompanionAdView.setVisibility(4);
                this.mLandscapeCompanionAdView.setVisibility(0);
            }
            if (this.mVastCompanionAdConfig != null) {
                this.mVastCompanionAdConfig.handleImpression(getContext(), this.mDuration);
            }
        }
    }

    protected void onBackPressed() {
        if (!this.mIsVideoFinishedPlaying) {
            this.mExternalViewabilitySessionManager.recordVideoEvent(VideoEvent.AD_SKIPPED, getCurrentPosition());
        }
    }

    public boolean backButtonEnabled() {
        return this.mShowCloseButtonEventFired;
    }

    void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1 && resultCode == -1) {
            getBaseVideoViewControllerListener().onFinish();
        }
    }

    private void adjustSkipOffset() {
        int videoDuration = getDuration();
        if (this.mVastVideoConfig.isRewardedVideo()) {
            this.mShowCloseButtonDelay = videoDuration;
            return;
        }
        if (videoDuration < MAX_VIDEO_DURATION_FOR_CLOSE_BUTTON) {
            this.mShowCloseButtonDelay = videoDuration;
        }
        Integer skipOffsetMillis = this.mVastVideoConfig.getSkipOffsetMillis(videoDuration);
        if (skipOffsetMillis != null) {
            this.mShowCloseButtonDelay = skipOffsetMillis.intValue();
            this.mHasSkipOffset = true;
        }
    }

    private VastVideoView createVideoView(@NonNull Context context, int initialVisibility) {
        if (this.mVastVideoConfig.getDiskMediaFileUrl() == null) {
            throw new IllegalStateException("VastVideoConfig does not have a video disk path");
        }
        VastVideoView videoView = new VastVideoView(context);
        videoView.setId((int) Utils.generateUniqueId());
        videoView.setOnPreparedListener(new 3(this, videoView));
        videoView.setOnTouchListener(this.mClickThroughListener);
        videoView.setOnCompletionListener(new 4(this, videoView, context));
        videoView.setOnErrorListener(new 5(this));
        videoView.setVideoPath(this.mVastVideoConfig.getDiskMediaFileUrl());
        videoView.setVisibility(initialVisibility);
        return videoView;
    }

    private void addTopGradientStripWidget(@NonNull Context context) {
        boolean hasCompanionAd;
        if (this.mVastCompanionAdConfig != null) {
            hasCompanionAd = true;
        } else {
            hasCompanionAd = false;
        }
        this.mTopGradientStripWidget = new VastVideoGradientStripWidget(context, Orientation.TOP_BOTTOM, this.mVastVideoConfig.getCustomForceOrientation(), hasCompanionAd, 0, 6, getLayout().getId());
        getLayout().addView(this.mTopGradientStripWidget);
        this.mExternalViewabilitySessionManager.registerVideoObstruction(this.mTopGradientStripWidget);
    }

    private void addBottomGradientStripWidget(@NonNull Context context) {
        this.mBottomGradientStripWidget = new VastVideoGradientStripWidget(context, Orientation.BOTTOM_TOP, this.mVastVideoConfig.getCustomForceOrientation(), this.mVastCompanionAdConfig != null, 8, 2, this.mProgressBarWidget.getId());
        getLayout().addView(this.mBottomGradientStripWidget);
        this.mExternalViewabilitySessionManager.registerVideoObstruction(this.mBottomGradientStripWidget);
    }

    private void addProgressBarWidget(@NonNull Context context, int initialVisibility) {
        this.mProgressBarWidget = new VastVideoProgressBarWidget(context);
        this.mProgressBarWidget.setAnchorId(this.mVideoView.getId());
        this.mProgressBarWidget.setVisibility(initialVisibility);
        getLayout().addView(this.mProgressBarWidget);
        this.mExternalViewabilitySessionManager.registerVideoObstruction(this.mProgressBarWidget);
    }

    private void addRadialCountdownWidget(@NonNull Context context, int initialVisibility) {
        this.mRadialCountdownWidget = new VastVideoRadialCountdownWidget(context);
        this.mRadialCountdownWidget.setVisibility(initialVisibility);
        getLayout().addView(this.mRadialCountdownWidget);
        this.mExternalViewabilitySessionManager.registerVideoObstruction(this.mRadialCountdownWidget);
    }

    private void addCtaButtonWidget(@NonNull Context context) {
        boolean hasCompanionAd;
        boolean hasClickthroughUrl;
        if (this.mVastCompanionAdConfig != null) {
            hasCompanionAd = true;
        } else {
            hasCompanionAd = false;
        }
        if (TextUtils.isEmpty(this.mVastVideoConfig.getClickThroughUrl())) {
            hasClickthroughUrl = false;
        } else {
            hasClickthroughUrl = true;
        }
        this.mCtaButtonWidget = new VastVideoCtaButtonWidget(context, this.mVideoView.getId(), hasCompanionAd, hasClickthroughUrl);
        getLayout().addView(this.mCtaButtonWidget);
        this.mExternalViewabilitySessionManager.registerVideoObstruction(this.mCtaButtonWidget);
        this.mCtaButtonWidget.setOnTouchListener(this.mClickThroughListener);
        String customCtaText = this.mVastVideoConfig.getCustomCtaText();
        if (customCtaText != null) {
            this.mCtaButtonWidget.updateCtaText(customCtaText);
        }
    }

    private void addCloseButtonWidget(@NonNull Context context, int initialVisibility) {
        this.mCloseButtonWidget = new VastVideoCloseButtonWidget(context);
        this.mCloseButtonWidget.setVisibility(initialVisibility);
        getLayout().addView(this.mCloseButtonWidget);
        this.mExternalViewabilitySessionManager.registerVideoObstruction(this.mCloseButtonWidget);
        this.mCloseButtonWidget.setOnTouchListenerToContent(new 6(this));
        String customSkipText = this.mVastVideoConfig.getCustomSkipText();
        if (customSkipText != null) {
            this.mCloseButtonWidget.updateCloseButtonText(customSkipText);
        }
        String customCloseIconUrl = this.mVastVideoConfig.getCustomCloseIconUrl();
        if (customCloseIconUrl != null) {
            this.mCloseButtonWidget.updateCloseButtonIcon(customCloseIconUrl);
        }
    }

    private void addBlurredLastVideoFrameImageView(@NonNull Context context, int initialVisibility) {
        this.mBlurredLastVideoFrameImageView = new ImageView(context);
        this.mBlurredLastVideoFrameImageView.setVisibility(initialVisibility);
        getLayout().addView(this.mBlurredLastVideoFrameImageView, new LayoutParams(-1, -1));
    }

    @NonNull
    @VisibleForTesting
    View createCompanionAdView(@NonNull Context context, @Nullable VastCompanionAdConfig vastCompanionAdConfig, int initialVisibility) {
        Preconditions.checkNotNull(context);
        if (vastCompanionAdConfig == null) {
            View emptyView = new View(context);
            emptyView.setVisibility(4);
            return emptyView;
        }
        RelativeLayout relativeLayout = new RelativeLayout(context);
        relativeLayout.setGravity(17);
        getLayout().addView(relativeLayout, new LayoutParams(-1, -1));
        this.mExternalViewabilitySessionManager.registerVideoObstruction(relativeLayout);
        View companionView = createCompanionVastWebView(context, vastCompanionAdConfig);
        companionView.setVisibility(initialVisibility);
        LayoutParams companionAdLayout = new LayoutParams(Dips.dipsToIntPixels((float) (vastCompanionAdConfig.getWidth() + 16), context), Dips.dipsToIntPixels((float) (vastCompanionAdConfig.getHeight() + 16), context));
        companionAdLayout.addRule(13, -1);
        relativeLayout.addView(companionView, companionAdLayout);
        this.mExternalViewabilitySessionManager.registerVideoObstruction(companionView);
        return companionView;
    }

    @NonNull
    @VisibleForTesting
    View createSocialActionsView(@NonNull Context context, @Nullable VastCompanionAdConfig vastCompanionAdConfig, int anchorHeight, int layoutVerb, @NonNull View anchorView, int initialVisibility, int leftMarginDips) {
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(anchorView);
        if (vastCompanionAdConfig == null) {
            View emptyView = new View(context);
            emptyView.setVisibility(4);
            return emptyView;
        }
        this.mHasSocialActions = true;
        this.mCtaButtonWidget.setHasSocialActions(this.mHasSocialActions);
        View companionView = createCompanionVastWebView(context, vastCompanionAdConfig);
        int width = Dips.dipsToIntPixels((float) vastCompanionAdConfig.getWidth(), context);
        int height = Dips.dipsToIntPixels((float) vastCompanionAdConfig.getHeight(), context);
        int offset = (anchorHeight - height) / 2;
        int leftMargin = Dips.dipsToIntPixels((float) leftMarginDips, context);
        LayoutParams companionAdLayout = new LayoutParams(width, height);
        companionAdLayout.addRule(layoutVerb, anchorView.getId());
        companionAdLayout.addRule(6, anchorView.getId());
        companionAdLayout.setMargins(leftMargin, offset, 0, 0);
        RelativeLayout relativeLayout = new RelativeLayout(context);
        relativeLayout.setGravity(16);
        relativeLayout.addView(companionView, new LayoutParams(-2, -2));
        this.mExternalViewabilitySessionManager.registerVideoObstruction(companionView);
        getLayout().addView(relativeLayout, companionAdLayout);
        this.mExternalViewabilitySessionManager.registerVideoObstruction(relativeLayout);
        companionView.setVisibility(initialVisibility);
        return companionView;
    }

    @NonNull
    @VisibleForTesting
    View createIconView(@NonNull Context context, @Nullable VastIconConfig vastIconConfig, int initialVisibility) {
        Preconditions.checkNotNull(context);
        if (vastIconConfig == null) {
            return new View(context);
        }
        View iconView = VastWebView.createView(context, vastIconConfig.getVastResource());
        iconView.setVastWebViewClickListener(new 7(this, vastIconConfig, context));
        iconView.setWebViewClient(new 8(this, vastIconConfig));
        iconView.setVisibility(initialVisibility);
        LayoutParams layoutParams = new LayoutParams(Dips.asIntPixels((float) vastIconConfig.getWidth(), context), Dips.asIntPixels((float) vastIconConfig.getHeight(), context));
        layoutParams.setMargins(Dips.dipsToIntPixels(12.0f, context), Dips.dipsToIntPixels(12.0f, context), 0, 0);
        getLayout().addView(iconView, layoutParams);
        this.mExternalViewabilitySessionManager.registerVideoObstruction(iconView);
        return iconView;
    }

    int getDuration() {
        return this.mVideoView.getDuration();
    }

    int getCurrentPosition() {
        return this.mVideoView.getCurrentPosition();
    }

    void makeVideoInteractable() {
        this.mShowCloseButtonEventFired = true;
        this.mRadialCountdownWidget.setVisibility(8);
        this.mCloseButtonWidget.setVisibility(0);
        this.mCtaButtonWidget.notifyVideoSkippable();
        this.mSocialActionsView.setVisibility(0);
    }

    boolean shouldBeInteractable() {
        return !this.mShowCloseButtonEventFired && getCurrentPosition() >= this.mShowCloseButtonDelay;
    }

    void updateCountdown() {
        if (this.mIsCalibrationDone) {
            this.mRadialCountdownWidget.updateCountdownProgress(this.mShowCloseButtonDelay, getCurrentPosition());
        }
    }

    void updateProgressBar() {
        this.mProgressBarWidget.updateProgress(getCurrentPosition());
    }

    String getNetworkMediaFileUrl() {
        if (this.mVastVideoConfig == null) {
            return null;
        }
        return this.mVastVideoConfig.getNetworkMediaFileUrl();
    }

    void handleIconDisplay(int currentPosition) {
        if (this.mVastIconConfig != null && currentPosition >= this.mVastIconConfig.getOffsetMS()) {
            this.mIconView.setVisibility(0);
            this.mVastIconConfig.handleImpression(getContext(), currentPosition, getNetworkMediaFileUrl());
            if (this.mVastIconConfig.getDurationMS() != null && currentPosition >= this.mVastIconConfig.getOffsetMS() + this.mVastIconConfig.getDurationMS().intValue()) {
                this.mIconView.setVisibility(8);
            }
        }
    }

    void handleViewabilityQuartileEvent(@NonNull String enumValue) {
        this.mExternalViewabilitySessionManager.recordVideoEvent((VideoEvent) Enum.valueOf(VideoEvent.class, enumValue), getCurrentPosition());
    }

    private boolean shouldAllowClickThrough() {
        return this.mShowCloseButtonEventFired;
    }

    private void startRunnables() {
        this.mProgressCheckerRunnable.startRepeating(VIDEO_PROGRESS_TIMER_CHECKER_DELAY);
        this.mCountdownRunnable.startRepeating(VIDEO_COUNTDOWN_UPDATE_INTERVAL);
    }

    private void stopRunnables() {
        this.mProgressCheckerRunnable.stop();
        this.mCountdownRunnable.stop();
    }

    @NonNull
    private VastWebView createCompanionVastWebView(@NonNull Context context, @NonNull VastCompanionAdConfig vastCompanionAdConfig) {
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(vastCompanionAdConfig);
        VastWebView companionView = VastWebView.createView(context, vastCompanionAdConfig.getVastResource());
        companionView.setVastWebViewClickListener(new 9(this, vastCompanionAdConfig, context));
        companionView.setWebViewClient(new 10(this, vastCompanionAdConfig, context));
        return companionView;
    }

    @Deprecated
    @VisibleForTesting
    VastVideoViewProgressRunnable getProgressCheckerRunnable() {
        return this.mProgressCheckerRunnable;
    }

    @Deprecated
    @VisibleForTesting
    VastVideoViewCountdownRunnable getCountdownRunnable() {
        return this.mCountdownRunnable;
    }

    @Deprecated
    @VisibleForTesting
    boolean getHasSkipOffset() {
        return this.mHasSkipOffset;
    }

    @Deprecated
    @VisibleForTesting
    int getShowCloseButtonDelay() {
        return this.mShowCloseButtonDelay;
    }

    @Deprecated
    @VisibleForTesting
    boolean isShowCloseButtonEventFired() {
        return this.mShowCloseButtonEventFired;
    }

    @Deprecated
    @VisibleForTesting
    void setCloseButtonVisible(boolean visible) {
        this.mShowCloseButtonEventFired = visible;
    }

    @Deprecated
    @VisibleForTesting
    boolean isVideoFinishedPlaying() {
        return this.mIsVideoFinishedPlaying;
    }

    @Deprecated
    @VisibleForTesting
    boolean isCalibrationDone() {
        return this.mIsCalibrationDone;
    }

    @Deprecated
    @VisibleForTesting
    View getLandscapeCompanionAdView() {
        return this.mLandscapeCompanionAdView;
    }

    @Deprecated
    @VisibleForTesting
    View getPortraitCompanionAdView() {
        return this.mPortraitCompanionAdView;
    }

    @Deprecated
    @VisibleForTesting
    boolean getVideoError() {
        return this.mVideoError;
    }

    @Deprecated
    @VisibleForTesting
    void setVideoError() {
        this.mVideoError = true;
    }

    @Deprecated
    @VisibleForTesting
    View getIconView() {
        return this.mIconView;
    }

    @Deprecated
    @VisibleForTesting
    VastVideoGradientStripWidget getTopGradientStripWidget() {
        return this.mTopGradientStripWidget;
    }

    @Deprecated
    @VisibleForTesting
    VastVideoGradientStripWidget getBottomGradientStripWidget() {
        return this.mBottomGradientStripWidget;
    }

    @Deprecated
    @VisibleForTesting
    VastVideoProgressBarWidget getProgressBarWidget() {
        return this.mProgressBarWidget;
    }

    @Deprecated
    @VisibleForTesting
    void setProgressBarWidget(@NonNull VastVideoProgressBarWidget progressBarWidget) {
        this.mProgressBarWidget = progressBarWidget;
    }

    @Deprecated
    @VisibleForTesting
    VastVideoRadialCountdownWidget getRadialCountdownWidget() {
        return this.mRadialCountdownWidget;
    }

    @Deprecated
    @VisibleForTesting
    void setRadialCountdownWidget(@NonNull VastVideoRadialCountdownWidget radialCountdownWidget) {
        this.mRadialCountdownWidget = radialCountdownWidget;
    }

    @Deprecated
    @VisibleForTesting
    VastVideoCtaButtonWidget getCtaButtonWidget() {
        return this.mCtaButtonWidget;
    }

    @Deprecated
    @VisibleForTesting
    VastVideoCloseButtonWidget getCloseButtonWidget() {
        return this.mCloseButtonWidget;
    }

    @Deprecated
    @VisibleForTesting
    ImageView getBlurredLastVideoFrameImageView() {
        return this.mBlurredLastVideoFrameImageView;
    }

    @Deprecated
    @VisibleForTesting
    VastVideoView getVastVideoView() {
        return this.mVideoView;
    }

    @Deprecated
    @VisibleForTesting
    void setIsClosing(boolean isClosing) {
        this.mIsClosing = isClosing;
    }
}
