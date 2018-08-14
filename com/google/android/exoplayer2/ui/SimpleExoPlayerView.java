package com.google.android.exoplayer2.ui;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayer$EventListener;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.SimpleExoPlayer$VideoListener;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.metadata.Metadata$Entry;
import com.google.android.exoplayer2.metadata.id3.ApicFrame;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.text.Cue;
import com.google.android.exoplayer2.text.TextRenderer.Output;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.ui.PlaybackControlView.ControlDispatcher;
import com.google.android.exoplayer2.ui.PlaybackControlView.VisibilityListener;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;
import java.util.List;

@TargetApi(16)
public final class SimpleExoPlayerView extends FrameLayout {
    private static final int SURFACE_TYPE_NONE = 0;
    private static final int SURFACE_TYPE_SURFACE_VIEW = 1;
    private static final int SURFACE_TYPE_TEXTURE_VIEW = 2;
    private final ImageView artworkView;
    private final ComponentListener componentListener;
    private final AspectRatioFrameLayout contentFrame;
    private final PlaybackControlView controller;
    private boolean controllerHideOnTouch;
    private int controllerShowTimeoutMs;
    private Bitmap defaultArtwork;
    private final FrameLayout overlayFrameLayout;
    private SimpleExoPlayer player;
    private final View shutterView;
    private final SubtitleView subtitleView;
    private final View surfaceView;
    private boolean useArtwork;
    private boolean useController;

    private final class ComponentListener implements SimpleExoPlayer$VideoListener, Output, ExoPlayer$EventListener {
        private ComponentListener() {
        }

        public void onCues(List<Cue> cues) {
            if (SimpleExoPlayerView.this.subtitleView != null) {
                SimpleExoPlayerView.this.subtitleView.onCues(cues);
            }
        }

        public void onVideoSizeChanged(int width, int height, int unappliedRotationDegrees, float pixelWidthHeightRatio) {
            if (SimpleExoPlayerView.this.contentFrame != null) {
                SimpleExoPlayerView.this.contentFrame.setAspectRatio(height == 0 ? 1.0f : (((float) width) * pixelWidthHeightRatio) / ((float) height));
            }
        }

        public void onRenderedFirstFrame() {
            if (SimpleExoPlayerView.this.shutterView != null) {
                SimpleExoPlayerView.this.shutterView.setVisibility(4);
            }
        }

        public void onTracksChanged(TrackGroupArray tracks, TrackSelectionArray selections) {
            SimpleExoPlayerView.this.updateForCurrentTrackSelections();
        }

        public void onLoadingChanged(boolean isLoading) {
        }

        public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
            SimpleExoPlayerView.this.maybeShowController(false);
        }

        public void onPlayerError(ExoPlaybackException e) {
        }

        public void onPositionDiscontinuity() {
        }

        public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {
        }

        public void onTimelineChanged(Timeline timeline, Object manifest) {
        }
    }

    public SimpleExoPlayerView(Context context) {
        this(context, null);
    }

    public SimpleExoPlayerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SimpleExoPlayerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (isInEditMode()) {
            this.contentFrame = null;
            this.shutterView = null;
            this.surfaceView = null;
            this.artworkView = null;
            this.subtitleView = null;
            this.controller = null;
            this.componentListener = null;
            this.overlayFrameLayout = null;
            ImageView logo = new ImageView(context, attrs);
            if (Util.SDK_INT >= 23) {
                configureEditModeLogoV23(getResources(), logo);
            } else {
                configureEditModeLogo(getResources(), logo);
            }
            addView(logo);
            return;
        }
        int playerLayoutId = R$layout.exo_simple_player_view;
        boolean useArtwork = true;
        int defaultArtworkId = 0;
        boolean useController = true;
        int surfaceType = 1;
        int resizeMode = 0;
        int i = 5000;
        boolean controllerHideOnTouch = true;
        if (attrs != null) {
            TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.SimpleExoPlayerView, 0, 0);
            try {
                playerLayoutId = a.getResourceId(R.styleable.SimpleExoPlayerView_player_layout_id, playerLayoutId);
                useArtwork = a.getBoolean(R.styleable.SimpleExoPlayerView_use_artwork, true);
                defaultArtworkId = a.getResourceId(R.styleable.SimpleExoPlayerView_default_artwork, 0);
                useController = a.getBoolean(R.styleable.SimpleExoPlayerView_use_controller, true);
                surfaceType = a.getInt(R.styleable.SimpleExoPlayerView_surface_type, 1);
                resizeMode = a.getInt(R.styleable.SimpleExoPlayerView_resize_mode, 0);
                i = a.getInt(R.styleable.SimpleExoPlayerView_show_timeout, 5000);
                controllerHideOnTouch = a.getBoolean(R.styleable.SimpleExoPlayerView_hide_on_touch, controllerHideOnTouch);
            } finally {
                a.recycle();
            }
        }
        LayoutInflater.from(context).inflate(playerLayoutId, this);
        this.componentListener = new ComponentListener();
        setDescendantFocusability(262144);
        this.contentFrame = (AspectRatioFrameLayout) findViewById(R$id.exo_content_frame);
        if (this.contentFrame != null) {
            setResizeModeRaw(this.contentFrame, resizeMode);
        }
        this.shutterView = findViewById(R$id.exo_shutter);
        if (this.contentFrame == null || surfaceType == 0) {
            this.surfaceView = null;
        } else {
            LayoutParams params = new LayoutParams(-1, -1);
            View textureView;
            if (surfaceType == 2) {
                textureView = new TextureView(context);
            } else {
                textureView = new SurfaceView(context);
            }
            this.surfaceView = r19;
            this.surfaceView.setLayoutParams(params);
            this.contentFrame.addView(this.surfaceView, 0);
        }
        this.overlayFrameLayout = (FrameLayout) findViewById(R$id.exo_overlay);
        this.artworkView = (ImageView) findViewById(R$id.exo_artwork);
        boolean z = useArtwork && this.artworkView != null;
        this.useArtwork = z;
        if (defaultArtworkId != 0) {
            this.defaultArtwork = BitmapFactory.decodeResource(context.getResources(), defaultArtworkId);
        }
        this.subtitleView = (SubtitleView) findViewById(R$id.exo_subtitles);
        if (this.subtitleView != null) {
            this.subtitleView.setUserDefaultStyle();
            this.subtitleView.setUserDefaultTextSize();
        }
        View controllerPlaceholder = findViewById(R$id.exo_controller_placeholder);
        if (controllerPlaceholder != null) {
            this.controller = new PlaybackControlView(context, attrs);
            this.controller.setLayoutParams(controllerPlaceholder.getLayoutParams());
            ViewGroup parent = (ViewGroup) controllerPlaceholder.getParent();
            int controllerIndex = parent.indexOfChild(controllerPlaceholder);
            parent.removeView(controllerPlaceholder);
            parent.addView(this.controller, controllerIndex);
        } else {
            this.controller = null;
        }
        if (this.controller == null) {
            i = 0;
        }
        this.controllerShowTimeoutMs = i;
        this.controllerHideOnTouch = controllerHideOnTouch;
        z = useController && this.controller != null;
        this.useController = z;
        hideController();
    }

    public static void switchTargetView(@NonNull SimpleExoPlayer player, @Nullable SimpleExoPlayerView oldPlayerView, @Nullable SimpleExoPlayerView newPlayerView) {
        if (oldPlayerView != newPlayerView) {
            if (newPlayerView != null) {
                newPlayerView.setPlayer(player);
            }
            if (oldPlayerView != null) {
                oldPlayerView.setPlayer(null);
            }
        }
    }

    public SimpleExoPlayer getPlayer() {
        return this.player;
    }

    public void setPlayer(SimpleExoPlayer player) {
        if (this.player != player) {
            if (this.player != null) {
                this.player.removeListener(this.componentListener);
                this.player.clearTextOutput(this.componentListener);
                this.player.clearVideoListener(this.componentListener);
                if (this.surfaceView instanceof TextureView) {
                    this.player.clearVideoTextureView((TextureView) this.surfaceView);
                } else if (this.surfaceView instanceof SurfaceView) {
                    this.player.clearVideoSurfaceView((SurfaceView) this.surfaceView);
                }
            }
            this.player = player;
            if (this.useController) {
                this.controller.setPlayer(player);
            }
            if (this.shutterView != null) {
                this.shutterView.setVisibility(0);
            }
            if (player != null) {
                if (this.surfaceView instanceof TextureView) {
                    player.setVideoTextureView((TextureView) this.surfaceView);
                } else if (this.surfaceView instanceof SurfaceView) {
                    player.setVideoSurfaceView((SurfaceView) this.surfaceView);
                }
                player.setVideoListener(this.componentListener);
                player.setTextOutput(this.componentListener);
                player.addListener(this.componentListener);
                maybeShowController(false);
                updateForCurrentTrackSelections();
                return;
            }
            hideController();
            hideArtwork();
        }
    }

    public void setResizeMode(int resizeMode) {
        Assertions.checkState(this.contentFrame != null);
        this.contentFrame.setResizeMode(resizeMode);
    }

    public boolean getUseArtwork() {
        return this.useArtwork;
    }

    public void setUseArtwork(boolean useArtwork) {
        boolean z = (useArtwork && this.artworkView == null) ? false : true;
        Assertions.checkState(z);
        if (this.useArtwork != useArtwork) {
            this.useArtwork = useArtwork;
            updateForCurrentTrackSelections();
        }
    }

    public Bitmap getDefaultArtwork() {
        return this.defaultArtwork;
    }

    public void setDefaultArtwork(Bitmap defaultArtwork) {
        if (this.defaultArtwork != defaultArtwork) {
            this.defaultArtwork = defaultArtwork;
            updateForCurrentTrackSelections();
        }
    }

    public boolean getUseController() {
        return this.useController;
    }

    public void setUseController(boolean useController) {
        boolean z = (useController && this.controller == null) ? false : true;
        Assertions.checkState(z);
        if (this.useController != useController) {
            this.useController = useController;
            if (useController) {
                this.controller.setPlayer(this.player);
            } else if (this.controller != null) {
                this.controller.hide();
                this.controller.setPlayer(null);
            }
        }
    }

    public boolean dispatchMediaKeyEvent(KeyEvent event) {
        return this.useController && this.controller.dispatchMediaKeyEvent(event);
    }

    public void showController() {
        if (this.useController) {
            maybeShowController(true);
        }
    }

    public void hideController() {
        if (this.controller != null) {
            this.controller.hide();
        }
    }

    public int getControllerShowTimeoutMs() {
        return this.controllerShowTimeoutMs;
    }

    public void setControllerShowTimeoutMs(int controllerShowTimeoutMs) {
        Assertions.checkState(this.controller != null);
        this.controllerShowTimeoutMs = controllerShowTimeoutMs;
    }

    public boolean getControllerHideOnTouch() {
        return this.controllerHideOnTouch;
    }

    public void setControllerHideOnTouch(boolean controllerHideOnTouch) {
        Assertions.checkState(this.controller != null);
        this.controllerHideOnTouch = controllerHideOnTouch;
    }

    public void setControllerVisibilityListener(VisibilityListener listener) {
        Assertions.checkState(this.controller != null);
        this.controller.setVisibilityListener(listener);
    }

    public void setControlDispatcher(ControlDispatcher controlDispatcher) {
        Assertions.checkState(this.controller != null);
        this.controller.setControlDispatcher(controlDispatcher);
    }

    public void setRewindIncrementMs(int rewindMs) {
        Assertions.checkState(this.controller != null);
        this.controller.setRewindIncrementMs(rewindMs);
    }

    public void setFastForwardIncrementMs(int fastForwardMs) {
        Assertions.checkState(this.controller != null);
        this.controller.setFastForwardIncrementMs(fastForwardMs);
    }

    public void setShowMultiWindowTimeBar(boolean showMultiWindowTimeBar) {
        Assertions.checkState(this.controller != null);
        this.controller.setShowMultiWindowTimeBar(showMultiWindowTimeBar);
    }

    public View getVideoSurfaceView() {
        return this.surfaceView;
    }

    public FrameLayout getOverlayFrameLayout() {
        return this.overlayFrameLayout;
    }

    public SubtitleView getSubtitleView() {
        return this.subtitleView;
    }

    public boolean onTouchEvent(MotionEvent ev) {
        if (!this.useController || this.player == null || ev.getActionMasked() != 0) {
            return false;
        }
        if (!this.controller.isVisible()) {
            maybeShowController(true);
            return true;
        } else if (!this.controllerHideOnTouch) {
            return true;
        } else {
            this.controller.hide();
            return true;
        }
    }

    public boolean onTrackballEvent(MotionEvent ev) {
        if (!this.useController || this.player == null) {
            return false;
        }
        maybeShowController(true);
        return true;
    }

    private void maybeShowController(boolean isForced) {
        int i = 0;
        if (this.useController && this.player != null) {
            boolean showIndefinitely;
            int playbackState = this.player.getPlaybackState();
            if (playbackState == 1 || playbackState == 4 || !this.player.getPlayWhenReady()) {
                showIndefinitely = true;
            } else {
                showIndefinitely = false;
            }
            boolean wasShowingIndefinitely;
            if (!this.controller.isVisible() || this.controller.getShowTimeoutMs() > 0) {
                wasShowingIndefinitely = false;
            } else {
                wasShowingIndefinitely = true;
            }
            PlaybackControlView playbackControlView = this.controller;
            if (!showIndefinitely) {
                i = this.controllerShowTimeoutMs;
            }
            playbackControlView.setShowTimeoutMs(i);
            if (isForced || showIndefinitely || wasShowingIndefinitely) {
                this.controller.show();
            }
        }
    }

    private void updateForCurrentTrackSelections() {
        if (this.player != null) {
            TrackSelectionArray selections = this.player.getCurrentTrackSelections();
            int i = 0;
            while (i < selections.length) {
                if (this.player.getRendererType(i) != 2 || selections.get(i) == null) {
                    i++;
                } else {
                    hideArtwork();
                    return;
                }
            }
            if (this.shutterView != null) {
                this.shutterView.setVisibility(0);
            }
            if (this.useArtwork) {
                for (i = 0; i < selections.length; i++) {
                    TrackSelection selection = selections.get(i);
                    if (selection != null) {
                        int j = 0;
                        while (j < selection.length()) {
                            Metadata metadata = selection.getFormat(j).metadata;
                            if (metadata == null || !setArtworkFromMetadata(metadata)) {
                                j++;
                            } else {
                                return;
                            }
                        }
                        continue;
                    }
                }
                if (setArtworkFromBitmap(this.defaultArtwork)) {
                    return;
                }
            }
            hideArtwork();
        }
    }

    private boolean setArtworkFromMetadata(Metadata metadata) {
        for (int i = 0; i < metadata.length(); i++) {
            Metadata$Entry metadataEntry = metadata.get(i);
            if (metadataEntry instanceof ApicFrame) {
                byte[] bitmapData = ((ApicFrame) metadataEntry).pictureData;
                return setArtworkFromBitmap(BitmapFactory.decodeByteArray(bitmapData, 0, bitmapData.length));
            }
        }
        return false;
    }

    private boolean setArtworkFromBitmap(Bitmap bitmap) {
        if (bitmap == null) {
            return false;
        }
        int bitmapWidth = bitmap.getWidth();
        int bitmapHeight = bitmap.getHeight();
        if (bitmapWidth <= 0 || bitmapHeight <= 0) {
            return false;
        }
        if (this.contentFrame != null) {
            this.contentFrame.setAspectRatio(((float) bitmapWidth) / ((float) bitmapHeight));
        }
        this.artworkView.setImageBitmap(bitmap);
        this.artworkView.setVisibility(0);
        return true;
    }

    private void hideArtwork() {
        if (this.artworkView != null) {
            this.artworkView.setImageResource(17170445);
            this.artworkView.setVisibility(4);
        }
    }

    @TargetApi(23)
    private static void configureEditModeLogoV23(Resources resources, ImageView logo) {
        logo.setImageDrawable(resources.getDrawable(R$drawable.exo_edit_mode_logo, null));
        logo.setBackgroundColor(resources.getColor(R$color.exo_edit_mode_background_color, null));
    }

    private static void configureEditModeLogo(Resources resources, ImageView logo) {
        logo.setImageDrawable(resources.getDrawable(R$drawable.exo_edit_mode_logo));
        logo.setBackgroundColor(resources.getColor(R$color.exo_edit_mode_background_color));
    }

    private static void setResizeModeRaw(AspectRatioFrameLayout aspectRatioFrame, int resizeMode) {
        aspectRatioFrame.setResizeMode(resizeMode);
    }
}
