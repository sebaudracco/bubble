package com.mopub.nativeads;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.SurfaceTexture;
import android.media.AudioManager.OnAudioFocusChangeListener;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.TextureView.SurfaceTextureListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.VideoView;
import com.mopub.common.Constants;
import com.mopub.common.Preconditions;
import com.mopub.common.VisibleForTesting;
import com.mopub.common.logging.MoPubLog;
import com.mopub.mobileads.BaseVideoViewController;
import com.mopub.mobileads.BaseVideoViewController.BaseVideoViewControllerListener;
import com.mopub.mobileads.VastVideoConfig;
import com.mopub.nativeads.NativeFullScreenVideoView.Mode;
import com.mopub.nativeads.NativeVideoController.Listener;

public class NativeVideoViewController extends BaseVideoViewController implements SurfaceTextureListener, Listener, OnAudioFocusChangeListener {
    @Nullable
    private Bitmap mCachedVideoFrame;
    private boolean mEnded;
    private boolean mError;
    @NonNull
    private final NativeFullScreenVideoView mFullScreenVideoView;
    private int mLatestVideoControllerState;
    @NonNull
    private final NativeVideoController mNativeVideoController;
    @NonNull
    private VastVideoConfig mVastVideoConfig;
    @NonNull
    private VideoState mVideoState;

    public NativeVideoViewController(@NonNull Context context, @NonNull Bundle intentExtras, @NonNull Bundle savedInstanceState, @NonNull BaseVideoViewControllerListener baseVideoViewControllerListener) {
        this(context, intentExtras, savedInstanceState, baseVideoViewControllerListener, new NativeFullScreenVideoView(context, context.getResources().getConfiguration().orientation, ((VastVideoConfig) intentExtras.get(Constants.NATIVE_VAST_VIDEO_CONFIG)).getCustomCtaText()));
    }

    @VisibleForTesting
    NativeVideoViewController(@NonNull Context context, @NonNull Bundle intentExtras, @NonNull Bundle savedInstanceState, @NonNull BaseVideoViewControllerListener baseVideoViewControllerListener, @NonNull NativeFullScreenVideoView fullScreenVideoView) {
        super(context, null, baseVideoViewControllerListener);
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(intentExtras);
        Preconditions.checkNotNull(baseVideoViewControllerListener);
        Preconditions.checkNotNull(fullScreenVideoView);
        this.mVideoState = VideoState.NONE;
        this.mVastVideoConfig = (VastVideoConfig) intentExtras.get(Constants.NATIVE_VAST_VIDEO_CONFIG);
        this.mFullScreenVideoView = fullScreenVideoView;
        this.mNativeVideoController = NativeVideoController.getForId(((Long) intentExtras.get(Constants.NATIVE_VIDEO_ID)).longValue());
        Preconditions.checkNotNull(this.mVastVideoConfig);
        Preconditions.checkNotNull(this.mNativeVideoController);
    }

    protected VideoView getVideoView() {
        return null;
    }

    protected void onCreate() {
        this.mFullScreenVideoView.setSurfaceTextureListener(this);
        this.mFullScreenVideoView.setMode(Mode.LOADING);
        this.mFullScreenVideoView.setPlayControlClickListener(new 1(this));
        this.mFullScreenVideoView.setCloseControlListener(new 2(this));
        this.mFullScreenVideoView.setCtaClickListener(new 3(this));
        this.mFullScreenVideoView.setPrivacyInformationClickListener(new 4(this));
        this.mFullScreenVideoView.setLayoutParams(new LayoutParams(-1, -1));
        getBaseVideoViewControllerListener().onSetContentView(this.mFullScreenVideoView);
        this.mNativeVideoController.setProgressListener(new 5(this));
    }

    protected void onResume() {
        if (this.mCachedVideoFrame != null) {
            this.mFullScreenVideoView.setCachedVideoFrame(this.mCachedVideoFrame);
        }
        this.mNativeVideoController.prepare(this);
        this.mNativeVideoController.setListener(this);
        this.mNativeVideoController.setOnAudioFocusChangeListener(this);
    }

    protected void onPause() {
    }

    protected void onDestroy() {
    }

    protected void onSaveInstanceState(@NonNull Bundle outState) {
    }

    protected void onConfigurationChanged(Configuration configuration) {
        this.mFullScreenVideoView.setOrientation(configuration.orientation);
    }

    protected void onBackPressed() {
        applyState(VideoState.PAUSED, true);
    }

    public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
        this.mNativeVideoController.setTextureView(this.mFullScreenVideoView.getTextureView());
        if (!this.mEnded) {
            this.mNativeVideoController.seekTo(this.mNativeVideoController.getCurrentPosition());
        }
        this.mNativeVideoController.setPlayWhenReady(!this.mEnded);
        if (this.mNativeVideoController.getDuration() - this.mNativeVideoController.getCurrentPosition() < 750) {
            this.mEnded = true;
            maybeChangeState();
        }
    }

    public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {
    }

    public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
        this.mNativeVideoController.release(this);
        applyState(VideoState.PAUSED);
        return true;
    }

    public void onSurfaceTextureUpdated(SurfaceTexture surface) {
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

    public void onAudioFocusChange(int focusChange) {
        if (focusChange == -1 || focusChange == -2) {
            applyState(VideoState.PAUSED);
        } else if (focusChange == -3) {
            this.mNativeVideoController.setAudioVolume(0.3f);
        } else if (focusChange == 1) {
            this.mNativeVideoController.setAudioVolume(1.0f);
            maybeChangeState();
        }
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
        } else if (this.mLatestVideoControllerState == 3) {
            newState = VideoState.PLAYING;
        } else if (this.mLatestVideoControllerState == 4 || this.mLatestVideoControllerState == 5) {
            newState = VideoState.ENDED;
        }
        applyState(newState);
    }

    @VisibleForTesting
    void applyState(@NonNull VideoState videoState) {
        applyState(videoState, false);
    }

    @VisibleForTesting
    void applyState(@NonNull VideoState videoState, boolean transitionToInline) {
        Preconditions.checkNotNull(videoState);
        if (this.mVideoState != videoState) {
            switch (6.$SwitchMap$com$mopub$nativeads$NativeVideoViewController$VideoState[videoState.ordinal()]) {
                case 1:
                    this.mNativeVideoController.setPlayWhenReady(false);
                    this.mNativeVideoController.setAudioEnabled(false);
                    this.mNativeVideoController.setAppAudioEnabled(false);
                    this.mFullScreenVideoView.setMode(Mode.LOADING);
                    this.mVastVideoConfig.handleError(getContext(), null, 0);
                    break;
                case 2:
                case 3:
                    this.mNativeVideoController.setPlayWhenReady(true);
                    this.mFullScreenVideoView.setMode(Mode.LOADING);
                    break;
                case 4:
                    this.mNativeVideoController.setPlayWhenReady(true);
                    this.mNativeVideoController.setAudioEnabled(true);
                    this.mNativeVideoController.setAppAudioEnabled(true);
                    this.mFullScreenVideoView.setMode(Mode.PLAYING);
                    break;
                case 5:
                    if (!transitionToInline) {
                        this.mNativeVideoController.setAppAudioEnabled(false);
                    }
                    this.mNativeVideoController.setPlayWhenReady(false);
                    this.mFullScreenVideoView.setMode(Mode.PAUSED);
                    break;
                case 6:
                    this.mEnded = true;
                    this.mNativeVideoController.setAppAudioEnabled(false);
                    this.mFullScreenVideoView.updateProgress(1000);
                    this.mFullScreenVideoView.setMode(Mode.FINISHED);
                    this.mVastVideoConfig.handleComplete(getContext(), 0);
                    break;
            }
            this.mVideoState = videoState;
        }
    }

    @Deprecated
    @VisibleForTesting
    NativeFullScreenVideoView getNativeFullScreenVideoView() {
        return this.mFullScreenVideoView;
    }

    @Deprecated
    @VisibleForTesting
    VideoState getVideoState() {
        return this.mVideoState;
    }
}
