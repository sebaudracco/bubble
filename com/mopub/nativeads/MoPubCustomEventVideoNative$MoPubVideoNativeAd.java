package com.mopub.nativeads;

import android.content.Context;
import android.media.AudioManager.OnAudioFocusChangeListener;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import com.mopub.common.Preconditions;
import com.mopub.common.VisibleForTesting;
import com.mopub.common.event.BaseEvent.Category;
import com.mopub.common.event.BaseEvent.Name;
import com.mopub.common.event.BaseEvent.SamplingRate;
import com.mopub.common.event.Event;
import com.mopub.common.event.EventDetails;
import com.mopub.common.event.MoPubEvents;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.Utils;
import com.mopub.mobileads.VastManager;
import com.mopub.mobileads.VastManager.VastManagerListener;
import com.mopub.mobileads.VastTracker;
import com.mopub.mobileads.VastVideoConfig;
import com.mopub.mobileads.VideoViewabilityTracker;
import com.mopub.mobileads.factories.VastManagerFactory;
import com.mopub.nativeads.CustomEventNative.CustomEventNativeListener;
import com.mopub.nativeads.MediaLayout.Mode;
import com.mopub.nativeads.MediaLayout.MuteState;
import com.mopub.nativeads.MoPubCustomEventVideoNative.1;
import com.mopub.nativeads.MoPubCustomEventVideoNative.HeaderVisibilityStrategy;
import com.mopub.nativeads.MoPubCustomEventVideoNative.NativeVideoControllerFactory;
import com.mopub.nativeads.MoPubCustomEventVideoNative.PayloadVisibilityStrategy;
import com.mopub.nativeads.MoPubCustomEventVideoNative.VideoResponseHeaders;
import com.mopub.nativeads.NativeVideoController$NativeVideoProgressRunnable.ProgressListener;
import com.mopub.nativeads.NativeVideoController.VisibilityTrackingEvent;
import com.mopub.network.TrackingRequest;
import com.silvermob.sdk.Const.BannerType;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map.Entry;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONObject;

public class MoPubCustomEventVideoNative$MoPubVideoNativeAd extends VideoNativeAd implements VastManagerListener, ProgressListener, OnAudioFocusChangeListener {
    static final String PRIVACY_INFORMATION_CLICKTHROUGH_URL = "https://www.mopub.com/optout/";
    @NonNull
    private final Context mContext;
    @NonNull
    private final CustomEventNativeListener mCustomEventNativeListener;
    private boolean mEnded;
    private boolean mError;
    @Nullable
    private final EventDetails mEventDetails;
    private final long mId;
    @NonNull
    private final JSONObject mJsonObject;
    private int mLatestVideoControllerState;
    private boolean mLatestVisibility;
    @Nullable
    private MediaLayout mMediaLayout;
    @NonNull
    private final String mMoPubClickTrackingUrl;
    private boolean mMuted;
    @Nullable
    private NativeVideoController mNativeVideoController;
    @NonNull
    private final NativeVideoControllerFactory mNativeVideoControllerFactory;
    private boolean mNeedsPrepare;
    private boolean mNeedsSeek;
    private boolean mPauseCanBeTracked;
    private boolean mResumeCanBeTracked;
    @Nullable
    private View mRootView;
    @NonNull
    private final VastManager mVastManager;
    @Nullable
    VastVideoConfig mVastVideoConfig;
    @NonNull
    private final VideoResponseHeaders mVideoResponseHeaders;
    @NonNull
    private VideoState mVideoState;
    @NonNull
    private final VisibilityTracker mVideoVisibleTracking;

    public MoPubCustomEventVideoNative$MoPubVideoNativeAd(@NonNull Context context, @NonNull JSONObject jsonObject, @NonNull CustomEventNativeListener customEventNativeListener, @NonNull VideoResponseHeaders videoResponseHeaders, @Nullable EventDetails eventDetails, @NonNull String clickTrackingUrl) {
        this(context, jsonObject, customEventNativeListener, videoResponseHeaders, new VisibilityTracker(context), new NativeVideoControllerFactory(), eventDetails, clickTrackingUrl, VastManagerFactory.create(context.getApplicationContext(), false));
    }

    @VisibleForTesting
    MoPubCustomEventVideoNative$MoPubVideoNativeAd(@NonNull Context context, @NonNull JSONObject jsonObject, @NonNull CustomEventNativeListener customEventNativeListener, @NonNull VideoResponseHeaders videoResponseHeaders, @NonNull VisibilityTracker visibilityTracker, @NonNull NativeVideoControllerFactory nativeVideoControllerFactory, @Nullable EventDetails eventDetails, @NonNull String clickTrackingUrl, @NonNull VastManager vastManager) {
        this.mPauseCanBeTracked = false;
        this.mResumeCanBeTracked = false;
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(jsonObject);
        Preconditions.checkNotNull(customEventNativeListener);
        Preconditions.checkNotNull(videoResponseHeaders);
        Preconditions.checkNotNull(visibilityTracker);
        Preconditions.checkNotNull(nativeVideoControllerFactory);
        Preconditions.checkNotNull(clickTrackingUrl);
        Preconditions.checkNotNull(vastManager);
        this.mContext = context.getApplicationContext();
        this.mJsonObject = jsonObject;
        this.mCustomEventNativeListener = customEventNativeListener;
        this.mVideoResponseHeaders = videoResponseHeaders;
        this.mNativeVideoControllerFactory = nativeVideoControllerFactory;
        this.mMoPubClickTrackingUrl = clickTrackingUrl;
        this.mEventDetails = eventDetails;
        this.mId = Utils.generateUniqueId();
        this.mNeedsSeek = true;
        this.mVideoState = VideoState.CREATED;
        this.mNeedsPrepare = true;
        this.mLatestVideoControllerState = 1;
        this.mMuted = true;
        this.mVideoVisibleTracking = visibilityTracker;
        this.mVideoVisibleTracking.setVisibilityTrackerListener(new 1(this));
        this.mVastManager = vastManager;
    }

    void loadAd() throws IllegalArgumentException {
        if (containsRequiredKeys(this.mJsonObject)) {
            Iterator<String> keys = this.mJsonObject.keys();
            while (keys.hasNext()) {
                String key = (String) keys.next();
                Parameter parameter = Parameter.from(key);
                if (parameter != null) {
                    try {
                        addInstanceVariable(parameter, this.mJsonObject.opt(key));
                    } catch (ClassCastException e) {
                        throw new IllegalArgumentException("JSONObject key (" + key + ") contained unexpected value.");
                    }
                }
                addExtra(key, this.mJsonObject.opt(key));
            }
            setPrivacyInformationIconClickThroughUrl(PRIVACY_INFORMATION_CLICKTHROUGH_URL);
            NativeImageHelper.preCacheImages(this.mContext, getAllImageUrls(), new 2(this));
            return;
        }
        throw new IllegalArgumentException("JSONObject did not contain required keys.");
    }

    public void onVastVideoConfigurationPrepared(@Nullable VastVideoConfig vastVideoConfig) {
        if (vastVideoConfig == null) {
            this.mCustomEventNativeListener.onNativeAdFailed(NativeErrorCode.INVALID_RESPONSE);
            return;
        }
        List<VisibilityTrackingEvent> visibilityTrackingEvents = new ArrayList();
        VisibilityTrackingEvent visibilityTrackingEvent = new VisibilityTrackingEvent();
        visibilityTrackingEvent.strategy = new HeaderVisibilityStrategy(this);
        visibilityTrackingEvent.minimumPercentageVisible = this.mVideoResponseHeaders.getImpressionMinVisiblePercent();
        visibilityTrackingEvent.totalRequiredPlayTimeMs = this.mVideoResponseHeaders.getImpressionVisibleMs();
        visibilityTrackingEvents.add(visibilityTrackingEvent);
        visibilityTrackingEvent.minimumVisiblePx = this.mVideoResponseHeaders.getImpressionVisiblePx();
        for (VastTracker vastTracker : vastVideoConfig.getImpressionTrackers()) {
            VisibilityTrackingEvent vastImpressionTrackingEvent = new VisibilityTrackingEvent();
            vastImpressionTrackingEvent.strategy = new PayloadVisibilityStrategy(this.mContext, vastTracker.getContent());
            vastImpressionTrackingEvent.minimumPercentageVisible = this.mVideoResponseHeaders.getImpressionMinVisiblePercent();
            vastImpressionTrackingEvent.totalRequiredPlayTimeMs = this.mVideoResponseHeaders.getImpressionVisibleMs();
            visibilityTrackingEvents.add(vastImpressionTrackingEvent);
            vastImpressionTrackingEvent.minimumVisiblePx = this.mVideoResponseHeaders.getImpressionVisiblePx();
        }
        this.mVastVideoConfig = vastVideoConfig;
        VideoViewabilityTracker vastVideoViewabilityTracker = this.mVastVideoConfig.getVideoViewabilityTracker();
        if (vastVideoViewabilityTracker != null) {
            VisibilityTrackingEvent vastVisibilityTrackingEvent = new VisibilityTrackingEvent();
            vastVisibilityTrackingEvent.strategy = new PayloadVisibilityStrategy(this.mContext, vastVideoViewabilityTracker.getContent());
            vastVisibilityTrackingEvent.minimumPercentageVisible = vastVideoViewabilityTracker.getPercentViewable();
            vastVisibilityTrackingEvent.totalRequiredPlayTimeMs = vastVideoViewabilityTracker.getViewablePlaytimeMS();
            visibilityTrackingEvents.add(vastVisibilityTrackingEvent);
        }
        Set<String> clickTrackers = new HashSet();
        clickTrackers.add(this.mMoPubClickTrackingUrl);
        clickTrackers.addAll(getClickTrackers());
        ArrayList<VastTracker> vastClickTrackers = new ArrayList();
        for (String clickTrackingUrl : clickTrackers) {
            vastClickTrackers.add(new VastTracker(clickTrackingUrl, false));
        }
        this.mVastVideoConfig.addClickTrackers(vastClickTrackers);
        this.mVastVideoConfig.setClickThroughUrl(getClickDestinationUrl());
        this.mNativeVideoController = this.mNativeVideoControllerFactory.createForId(this.mId, this.mContext, visibilityTrackingEvents, this.mVastVideoConfig, this.mEventDetails);
        this.mCustomEventNativeListener.onNativeAdLoaded(this);
        JSONObject videoTrackers = this.mVideoResponseHeaders.getVideoTrackers();
        if (videoTrackers != null) {
            this.mVastVideoConfig.addVideoTrackers(videoTrackers);
        }
    }

    private boolean containsRequiredKeys(@NonNull JSONObject jsonObject) {
        Preconditions.checkNotNull(jsonObject);
        Set<String> keys = new HashSet();
        Iterator<String> jsonKeys = jsonObject.keys();
        while (jsonKeys.hasNext()) {
            keys.add(jsonKeys.next());
        }
        return keys.containsAll(Parameter.requiredKeys);
    }

    private void addInstanceVariable(@NonNull Parameter key, @Nullable Object value) throws ClassCastException {
        Preconditions.checkNotNull(key);
        Preconditions.checkNotNull(value);
        try {
            switch (1.$SwitchMap$com$mopub$nativeads$MoPubCustomEventVideoNative$MoPubVideoNativeAd$Parameter[key.ordinal()]) {
                case 1:
                    addImpressionTrackers(value);
                    return;
                case 2:
                    setTitle((String) value);
                    return;
                case 3:
                    setText((String) value);
                    return;
                case 4:
                    setMainImageUrl((String) value);
                    return;
                case 5:
                    setIconImageUrl((String) value);
                    return;
                case 6:
                    setClickDestinationUrl((String) value);
                    return;
                case 7:
                    parseClickTrackers(value);
                    return;
                case 8:
                    setCallToAction((String) value);
                    return;
                case 9:
                    setVastVideo((String) value);
                    return;
                default:
                    MoPubLog.d("Unable to add JSON key to internal mapping: " + key.mName);
                    return;
            }
        } catch (ClassCastException e) {
            if (key.mRequired) {
                throw e;
            }
            MoPubLog.d("Ignoring class cast exception for optional key: " + key.mName);
            return;
        }
        if (key.mRequired) {
            MoPubLog.d("Ignoring class cast exception for optional key: " + key.mName);
            return;
        }
        throw e;
    }

    private void parseClickTrackers(@NonNull Object clickTrackers) {
        if (clickTrackers instanceof JSONArray) {
            addClickTrackers(clickTrackers);
        } else {
            addClickTracker((String) clickTrackers);
        }
    }

    public void render(@NonNull MediaLayout mediaLayout) {
        Preconditions.checkNotNull(mediaLayout);
        this.mVideoVisibleTracking.addView(this.mRootView, mediaLayout, this.mVideoResponseHeaders.getPlayVisiblePercent(), this.mVideoResponseHeaders.getPauseVisiblePercent(), this.mVideoResponseHeaders.getImpressionVisiblePx());
        this.mMediaLayout = mediaLayout;
        this.mMediaLayout.initForVideo();
        this.mMediaLayout.setSurfaceTextureListener(new 3(this));
        this.mMediaLayout.setPlayButtonClickListener(new 4(this));
        this.mMediaLayout.setMuteControlClickListener(new 5(this));
        this.mMediaLayout.setOnClickListener(new 6(this));
        if (this.mNativeVideoController.getPlaybackState() == 5) {
            this.mNativeVideoController.prepare(this);
        }
        applyState(VideoState.PAUSED);
    }

    public void prepare(@NonNull View view) {
        Preconditions.checkNotNull(view);
        this.mRootView = view;
        this.mRootView.setOnClickListener(new 7(this));
    }

    public void clear(@NonNull View view) {
        Preconditions.checkNotNull(view);
        this.mNativeVideoController.clear();
        cleanUpMediaLayout();
    }

    public void destroy() {
        cleanUpMediaLayout();
        this.mNativeVideoController.setPlayWhenReady(false);
        this.mNativeVideoController.release(this);
        NativeVideoController.remove(this.mId);
        this.mVideoVisibleTracking.destroy();
    }

    public void onStateChanged(boolean playWhenReady, int playbackState) {
        this.mLatestVideoControllerState = playbackState;
        maybeChangeState();
    }

    public void onError(Exception e) {
        MoPubLog.w("Error playing back video.", e);
        this.mError = true;
        maybeChangeState();
    }

    public void updateProgress(int progressTenthPercent) {
        this.mMediaLayout.updateProgress(progressTenthPercent);
    }

    public void onAudioFocusChange(int focusChange) {
        if (focusChange == -1 || focusChange == -2) {
            this.mMuted = true;
            maybeChangeState();
        } else if (focusChange == -3) {
            this.mNativeVideoController.setAudioVolume(0.3f);
        } else if (focusChange == 1) {
            this.mNativeVideoController.setAudioVolume(1.0f);
            maybeChangeState();
        }
    }

    private void cleanUpMediaLayout() {
        if (this.mMediaLayout != null) {
            this.mMediaLayout.setMode(Mode.IMAGE);
            this.mMediaLayout.setSurfaceTextureListener(null);
            this.mMediaLayout.setPlayButtonClickListener(null);
            this.mMediaLayout.setMuteControlClickListener(null);
            this.mMediaLayout.setOnClickListener(null);
            this.mVideoVisibleTracking.removeView(this.mMediaLayout);
            this.mMediaLayout = null;
        }
    }

    private void prepareToLeaveView() {
        this.mNeedsSeek = true;
        this.mNeedsPrepare = true;
        this.mNativeVideoController.setListener(null);
        this.mNativeVideoController.setOnAudioFocusChangeListener(null);
        this.mNativeVideoController.setProgressListener(null);
        this.mNativeVideoController.clear();
        applyState(VideoState.PAUSED, true);
    }

    private void maybeChangeState() {
        VideoState newState = this.mVideoState;
        if (this.mError) {
            newState = VideoState.FAILED_LOAD;
        } else if (this.mEnded) {
            newState = VideoState.ENDED;
        } else if (this.mLatestVideoControllerState == 1) {
            newState = VideoState.LOADING;
        } else if (this.mLatestVideoControllerState == 2) {
            newState = VideoState.BUFFERING;
        } else if (this.mLatestVideoControllerState == 4) {
            this.mEnded = true;
            newState = VideoState.ENDED;
        } else if (this.mLatestVideoControllerState == 3) {
            if (this.mLatestVisibility) {
                newState = this.mMuted ? VideoState.PLAYING_MUTED : VideoState.PLAYING;
            } else {
                newState = VideoState.PAUSED;
            }
        }
        applyState(newState);
    }

    @VisibleForTesting
    void applyState(@NonNull VideoState videoState) {
        applyState(videoState, false);
    }

    @VisibleForTesting
    void applyState(@NonNull VideoState videoState, boolean transitionToFullScreen) {
        Preconditions.checkNotNull(videoState);
        if (this.mVastVideoConfig != null && this.mNativeVideoController != null && this.mMediaLayout != null && this.mVideoState != videoState) {
            VideoState previousState = this.mVideoState;
            this.mVideoState = videoState;
            switch (1.$SwitchMap$com$mopub$nativeads$MoPubCustomEventVideoNative$MoPubVideoNativeAd$VideoState[videoState.ordinal()]) {
                case 1:
                    this.mVastVideoConfig.handleError(this.mContext, null, 0);
                    this.mNativeVideoController.setAppAudioEnabled(false);
                    this.mMediaLayout.setMode(Mode.IMAGE);
                    if (previousState != VideoState.PLAYING && previousState != VideoState.PLAYING_MUTED) {
                        MoPubEvents.log(Event.createEventFromDetails(Name.ERROR_FAILED_TO_PLAY, Category.NATIVE_VIDEO, SamplingRate.NATIVE_VIDEO, this.mEventDetails));
                        return;
                    }
                    return;
                case 2:
                case 3:
                    this.mNativeVideoController.setPlayWhenReady(true);
                    this.mMediaLayout.setMode(Mode.LOADING);
                    return;
                case 4:
                    this.mNativeVideoController.setPlayWhenReady(true);
                    this.mMediaLayout.setMode(Mode.BUFFERING);
                    return;
                case 5:
                    if (transitionToFullScreen) {
                        this.mResumeCanBeTracked = false;
                    }
                    if (!transitionToFullScreen) {
                        this.mNativeVideoController.setAppAudioEnabled(false);
                        if (this.mPauseCanBeTracked) {
                            TrackingRequest.makeVastTrackingHttpRequest(this.mVastVideoConfig.getPauseTrackers(), null, Integer.valueOf((int) this.mNativeVideoController.getCurrentPosition()), null, this.mContext);
                            this.mPauseCanBeTracked = false;
                            this.mResumeCanBeTracked = true;
                        }
                    }
                    this.mNativeVideoController.setPlayWhenReady(false);
                    this.mMediaLayout.setMode(Mode.PAUSED);
                    return;
                case 6:
                    handleResumeTrackersAndSeek(previousState);
                    this.mNativeVideoController.setPlayWhenReady(true);
                    this.mNativeVideoController.setAudioEnabled(true);
                    this.mNativeVideoController.setAppAudioEnabled(true);
                    this.mMediaLayout.setMode(Mode.PLAYING);
                    this.mMediaLayout.setMuteState(MuteState.UNMUTED);
                    return;
                case 7:
                    handleResumeTrackersAndSeek(previousState);
                    this.mNativeVideoController.setPlayWhenReady(true);
                    this.mNativeVideoController.setAudioEnabled(false);
                    this.mNativeVideoController.setAppAudioEnabled(false);
                    this.mMediaLayout.setMode(Mode.PLAYING);
                    this.mMediaLayout.setMuteState(MuteState.MUTED);
                    return;
                case 8:
                    if (this.mNativeVideoController.hasFinalFrame()) {
                        this.mMediaLayout.setMainImageDrawable(this.mNativeVideoController.getFinalFrame());
                    }
                    this.mPauseCanBeTracked = false;
                    this.mResumeCanBeTracked = false;
                    this.mVastVideoConfig.handleComplete(this.mContext, 0);
                    this.mNativeVideoController.setAppAudioEnabled(false);
                    this.mMediaLayout.setMode(Mode.FINISHED);
                    this.mMediaLayout.updateProgress(1000);
                    return;
                default:
                    return;
            }
        }
    }

    private void handleResumeTrackersAndSeek(VideoState previousState) {
        if (!(!this.mResumeCanBeTracked || previousState == VideoState.PLAYING || previousState == VideoState.PLAYING_MUTED)) {
            TrackingRequest.makeVastTrackingHttpRequest(this.mVastVideoConfig.getResumeTrackers(), null, Integer.valueOf((int) this.mNativeVideoController.getCurrentPosition()), null, this.mContext);
            this.mResumeCanBeTracked = false;
        }
        this.mPauseCanBeTracked = true;
        if (this.mNeedsSeek) {
            this.mNeedsSeek = false;
            this.mNativeVideoController.seekTo(this.mNativeVideoController.getCurrentPosition());
        }
    }

    private boolean isImageKey(@Nullable String name) {
        return name != null && name.toLowerCase(Locale.US).endsWith(BannerType.IMAGE);
    }

    @NonNull
    private List<String> getExtrasImageUrls() {
        List<String> extrasBitmapUrls = new ArrayList(getExtras().size());
        for (Entry<String, Object> entry : getExtras().entrySet()) {
            if (isImageKey((String) entry.getKey()) && (entry.getValue() instanceof String)) {
                extrasBitmapUrls.add((String) entry.getValue());
            }
        }
        return extrasBitmapUrls;
    }

    @NonNull
    private List<String> getAllImageUrls() {
        List<String> imageUrls = new ArrayList();
        if (getMainImageUrl() != null) {
            imageUrls.add(getMainImageUrl());
        }
        if (getIconImageUrl() != null) {
            imageUrls.add(getIconImageUrl());
        }
        imageUrls.addAll(getExtrasImageUrls());
        return imageUrls;
    }

    @Deprecated
    @VisibleForTesting
    boolean needsPrepare() {
        return this.mNeedsPrepare;
    }

    @Deprecated
    @VisibleForTesting
    boolean hasEnded() {
        return this.mEnded;
    }

    @Deprecated
    @VisibleForTesting
    boolean needsSeek() {
        return this.mNeedsSeek;
    }

    @Deprecated
    @VisibleForTesting
    boolean isMuted() {
        return this.mMuted;
    }

    @Deprecated
    @VisibleForTesting
    long getId() {
        return this.mId;
    }

    @Deprecated
    @VisibleForTesting
    VideoState getVideoState() {
        return this.mVideoState;
    }

    @Deprecated
    @VisibleForTesting
    void setLatestVisibility(boolean latestVisibility) {
        this.mLatestVisibility = latestVisibility;
    }

    @Deprecated
    @VisibleForTesting
    void setMuted(boolean muted) {
        this.mMuted = muted;
    }

    @Deprecated
    @VisibleForTesting
    MediaLayout getMediaLayout() {
        return this.mMediaLayout;
    }
}
