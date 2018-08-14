package com.mopub.nativeads;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.TextureView;
import android.view.TextureView.SurfaceTextureListener;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.cuebiq.cuebiqsdk.model.config.Settings;
import com.mopub.common.Preconditions;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.Dips;
import com.mopub.common.util.Drawables;
import com.mopub.common.util.Utils;
import com.mopub.mobileads.VastVideoProgressBarWidget;
import com.mopub.mobileads.resource.DrawableConstants.GradientStrip;

public class MediaLayout extends RelativeLayout {
    private static final float ASPECT_MULTIPLIER_HEIGHT_TO_WIDTH = 1.7777778f;
    private static final float ASPECT_MULTIPLIER_WIDTH_TO_HEIGHT = 0.5625f;
    private static final int CONTROL_SIZE_DIPS = 40;
    private static final int GRADIENT_STRIP_HEIGHT_DIPS = 35;
    private static final int MUTE_SIZE_DIPS = 36;
    private static final int PINNER_PADDING_DIPS = 10;
    @Nullable
    private ImageView mBottomGradient;
    private final int mControlSizePx;
    private final int mGradientStripHeightPx;
    private boolean mIsInitialized;
    @Nullable
    private ProgressBar mLoadingSpinner;
    @NonNull
    private ImageView mMainImageView;
    @NonNull
    private volatile Mode mMode;
    @Nullable
    private ImageView mMuteControl;
    private final int mMuteSizePx;
    @NonNull
    private MuteState mMuteState;
    @Nullable
    private Drawable mMutedDrawable;
    @Nullable
    private View mOverlay;
    private final int mPaddingPx;
    @Nullable
    private ImageView mPlayButton;
    @Nullable
    private ImageView mTopGradient;
    @Nullable
    private Drawable mUnmutedDrawable;
    @Nullable
    private VastVideoProgressBarWidget mVideoProgress;
    @Nullable
    private TextureView mVideoTextureView;

    class C37221 implements Runnable {
        C37221() {
        }

        public void run() {
            MediaLayout.this.updateViewState();
        }
    }

    public enum Mode {
        IMAGE,
        PLAYING,
        LOADING,
        BUFFERING,
        PAUSED,
        FINISHED
    }

    public enum MuteState {
        MUTED,
        UNMUTED
    }

    public MediaLayout(@NonNull Context context) {
        this(context, null);
    }

    public MediaLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MediaLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mMode = Mode.IMAGE;
        Preconditions.checkNotNull(context);
        this.mMuteState = MuteState.MUTED;
        LayoutParams params = new LayoutParams(-1, -1);
        params.addRule(13);
        this.mMainImageView = new ImageView(context);
        this.mMainImageView.setLayoutParams(params);
        this.mMainImageView.setScaleType(ScaleType.CENTER_CROP);
        addView(this.mMainImageView);
        this.mControlSizePx = Dips.asIntPixels(40.0f, context);
        this.mGradientStripHeightPx = Dips.asIntPixels(35.0f, context);
        this.mMuteSizePx = Dips.asIntPixels(36.0f, context);
        this.mPaddingPx = Dips.asIntPixels(Settings.LOCATION_REQUEST_SMALLEST_DISPLACEMENT, context);
    }

    public void setSurfaceTextureListener(@Nullable SurfaceTextureListener stl) {
        if (this.mVideoTextureView != null) {
            this.mVideoTextureView.setSurfaceTextureListener(stl);
            SurfaceTexture st = this.mVideoTextureView.getSurfaceTexture();
            if (st != null && stl != null) {
                stl.onSurfaceTextureAvailable(st, this.mVideoTextureView.getWidth(), this.mVideoTextureView.getHeight());
            }
        }
    }

    public void initForVideo() {
        if (!this.mIsInitialized) {
            LayoutParams videoTextureLayoutParams = new LayoutParams(-1, -1);
            videoTextureLayoutParams.addRule(13);
            this.mVideoTextureView = new TextureView(getContext());
            this.mVideoTextureView.setLayoutParams(videoTextureLayoutParams);
            this.mVideoTextureView.setId((int) Utils.generateUniqueId());
            addView(this.mVideoTextureView);
            this.mMainImageView.bringToFront();
            LayoutParams loadingSpinnerParams = new LayoutParams(this.mControlSizePx, this.mControlSizePx);
            loadingSpinnerParams.addRule(10);
            loadingSpinnerParams.addRule(11);
            this.mLoadingSpinner = new ProgressBar(getContext());
            this.mLoadingSpinner.setLayoutParams(loadingSpinnerParams);
            this.mLoadingSpinner.setPadding(0, this.mPaddingPx, this.mPaddingPx, 0);
            this.mLoadingSpinner.setIndeterminate(true);
            addView(this.mLoadingSpinner);
            LayoutParams bottomGradientParams = new LayoutParams(-1, this.mGradientStripHeightPx);
            bottomGradientParams.addRule(8, this.mVideoTextureView.getId());
            this.mBottomGradient = new ImageView(getContext());
            this.mBottomGradient.setLayoutParams(bottomGradientParams);
            this.mBottomGradient.setImageDrawable(new GradientDrawable(Orientation.BOTTOM_TOP, new int[]{GradientStrip.START_COLOR, GradientStrip.END_COLOR}));
            addView(this.mBottomGradient);
            LayoutParams topGradientParams = new LayoutParams(-1, this.mGradientStripHeightPx);
            topGradientParams.addRule(6, this.mVideoTextureView.getId());
            this.mTopGradient = new ImageView(getContext());
            this.mTopGradient.setLayoutParams(topGradientParams);
            this.mTopGradient.setImageDrawable(new GradientDrawable(Orientation.TOP_BOTTOM, new int[]{GradientStrip.START_COLOR, GradientStrip.END_COLOR}));
            addView(this.mTopGradient);
            this.mVideoProgress = new VastVideoProgressBarWidget(getContext());
            this.mVideoProgress.setAnchorId(this.mVideoTextureView.getId());
            this.mVideoProgress.calibrateAndMakeVisible(1000, 0);
            addView(this.mVideoProgress);
            this.mMutedDrawable = Drawables.NATIVE_MUTED.createDrawable(getContext());
            this.mUnmutedDrawable = Drawables.NATIVE_UNMUTED.createDrawable(getContext());
            LayoutParams muteControlParams = new LayoutParams(this.mMuteSizePx, this.mMuteSizePx);
            muteControlParams.addRule(9);
            muteControlParams.addRule(2, this.mVideoProgress.getId());
            this.mMuteControl = new ImageView(getContext());
            this.mMuteControl.setLayoutParams(muteControlParams);
            this.mMuteControl.setScaleType(ScaleType.CENTER_INSIDE);
            this.mMuteControl.setPadding(this.mPaddingPx, this.mPaddingPx, this.mPaddingPx, this.mPaddingPx);
            this.mMuteControl.setImageDrawable(this.mMutedDrawable);
            addView(this.mMuteControl);
            LayoutParams overlayParams = new LayoutParams(-1, -1);
            overlayParams.addRule(13);
            this.mOverlay = new View(getContext());
            this.mOverlay.setLayoutParams(overlayParams);
            this.mOverlay.setBackgroundColor(0);
            addView(this.mOverlay);
            LayoutParams playButtonParams = new LayoutParams(this.mControlSizePx, this.mControlSizePx);
            playButtonParams.addRule(13);
            this.mPlayButton = new ImageView(getContext());
            this.mPlayButton.setLayoutParams(playButtonParams);
            this.mPlayButton.setImageDrawable(Drawables.NATIVE_PLAY.createDrawable(getContext()));
            addView(this.mPlayButton);
            this.mIsInitialized = true;
            updateViewState();
        }
    }

    public void reset() {
        setMode(Mode.IMAGE);
        setPlayButtonClickListener(null);
        setMuteControlClickListener(null);
        setVideoClickListener(null);
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int finalWidth;
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int measWidth = MeasureSpec.getSize(widthMeasureSpec);
        int measHeight = MeasureSpec.getSize(heightMeasureSpec);
        int curWidth = getMeasuredWidth();
        int curHeight = getMeasuredHeight();
        if (widthMode == 1073741824) {
            finalWidth = measWidth;
        } else if (widthMode == Integer.MIN_VALUE) {
            finalWidth = Math.min(measWidth, curWidth);
        } else {
            finalWidth = curWidth;
        }
        int finalHeight = (int) (ASPECT_MULTIPLIER_WIDTH_TO_HEIGHT * ((float) finalWidth));
        if (heightMode == 1073741824 && measHeight < finalHeight) {
            finalHeight = measHeight;
            finalWidth = (int) (ASPECT_MULTIPLIER_HEIGHT_TO_WIDTH * ((float) finalHeight));
        }
        if (Math.abs(finalHeight - curHeight) >= 2 || Math.abs(finalWidth - curWidth) >= 2) {
            MoPubLog.m12067v(String.format("Resetting mediaLayout size to w: %d h: %d", new Object[]{Integer.valueOf(finalWidth), Integer.valueOf(finalHeight)}));
            getLayoutParams().width = finalWidth;
            getLayoutParams().height = finalHeight;
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    public void setMainImageDrawable(@NonNull Drawable drawable) {
        Preconditions.checkNotNull(drawable);
        this.mMainImageView.setImageDrawable(drawable);
    }

    public void resetProgress() {
        if (this.mVideoProgress != null) {
            this.mVideoProgress.reset();
        }
    }

    public void updateProgress(int progressTenthPercentage) {
        if (this.mVideoProgress != null) {
            this.mVideoProgress.updateProgress(progressTenthPercentage);
        }
    }

    public TextureView getTextureView() {
        return this.mVideoTextureView;
    }

    public void setMode(@NonNull Mode mode) {
        Preconditions.checkNotNull(mode);
        this.mMode = mode;
        post(new C37221());
    }

    @Nullable
    public ImageView getMainImageView() {
        return this.mMainImageView;
    }

    public void setMuteControlClickListener(@Nullable OnClickListener muteControlListener) {
        if (this.mMuteControl != null) {
            this.mMuteControl.setOnClickListener(muteControlListener);
        }
    }

    public void setPlayButtonClickListener(@Nullable OnClickListener playButtonListener) {
        if (this.mPlayButton != null && this.mOverlay != null) {
            this.mOverlay.setOnClickListener(playButtonListener);
            this.mPlayButton.setOnClickListener(playButtonListener);
        }
    }

    public void setVideoClickListener(@Nullable OnClickListener videoClickListener) {
        if (this.mVideoTextureView != null) {
            this.mVideoTextureView.setOnClickListener(videoClickListener);
        }
    }

    public void setMuteState(@NonNull MuteState muteState) {
        Preconditions.checkNotNull(muteState);
        if (muteState != this.mMuteState) {
            this.mMuteState = muteState;
            if (this.mMuteControl != null) {
                switch (this.mMuteState) {
                    case MUTED:
                        this.mMuteControl.setImageDrawable(this.mMutedDrawable);
                        return;
                    default:
                        this.mMuteControl.setImageDrawable(this.mUnmutedDrawable);
                        return;
                }
            }
        }
    }

    private void updateViewState() {
        switch (this.mMode) {
            case IMAGE:
                setMainImageVisibility(0);
                setLoadingSpinnerVisibility(4);
                setVideoControlVisibility(4);
                setPlayButtonVisibility(4);
                return;
            case LOADING:
                setMainImageVisibility(0);
                setLoadingSpinnerVisibility(0);
                setVideoControlVisibility(4);
                setPlayButtonVisibility(4);
                return;
            case BUFFERING:
                setMainImageVisibility(4);
                setLoadingSpinnerVisibility(0);
                setVideoControlVisibility(0);
                setPlayButtonVisibility(4);
                break;
            case PLAYING:
                break;
            case PAUSED:
                setMainImageVisibility(4);
                setLoadingSpinnerVisibility(4);
                setVideoControlVisibility(0);
                setPlayButtonVisibility(0);
                return;
            case FINISHED:
                setMainImageVisibility(0);
                setLoadingSpinnerVisibility(4);
                setVideoControlVisibility(4);
                setPlayButtonVisibility(0);
                return;
            default:
                return;
        }
        setMainImageVisibility(4);
        setLoadingSpinnerVisibility(4);
        setVideoControlVisibility(0);
        setPlayButtonVisibility(4);
    }

    private void setMainImageVisibility(int visibility) {
        this.mMainImageView.setVisibility(visibility);
    }

    private void setLoadingSpinnerVisibility(int visibility) {
        if (this.mLoadingSpinner != null) {
            this.mLoadingSpinner.setVisibility(visibility);
        }
        if (this.mTopGradient != null) {
            this.mTopGradient.setVisibility(visibility);
        }
    }

    private void setVideoControlVisibility(int visibility) {
        if (this.mBottomGradient != null) {
            this.mBottomGradient.setVisibility(visibility);
        }
        if (this.mVideoProgress != null) {
            this.mVideoProgress.setVisibility(visibility);
        }
        if (this.mMuteControl != null) {
            this.mMuteControl.setVisibility(visibility);
        }
    }

    private void setPlayButtonVisibility(int visibility) {
        if (this.mPlayButton != null && this.mOverlay != null) {
            this.mPlayButton.setVisibility(visibility);
            this.mOverlay.setVisibility(visibility);
        }
    }
}
