package com.google.android.exoplayer2;

import android.annotation.TargetApi;
import android.graphics.SurfaceTexture;
import android.media.PlaybackParams;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.TextureView;
import com.google.android.exoplayer2.ExoPlayer.EventListener;
import com.google.android.exoplayer2.ExoPlayer.ExoPlayerMessage;
import com.google.android.exoplayer2.audio.AudioRendererEventListener;
import com.google.android.exoplayer2.decoder.DecoderCounters;
import com.google.android.exoplayer2.metadata.MetadataRenderer.Output;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.text.TextRenderer;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.video.VideoRendererEventListener;

@TargetApi(16)
public class SimpleExoPlayer implements ExoPlayer {
    private static final String TAG = "SimpleExoPlayer";
    private AudioRendererEventListener audioDebugListener;
    private DecoderCounters audioDecoderCounters;
    private Format audioFormat;
    private final int audioRendererCount;
    private int audioSessionId;
    private int audioStreamType;
    private float audioVolume;
    private final ComponentListener componentListener = new ComponentListener(this, null);
    private Output metadataOutput;
    private boolean ownsSurface;
    private final ExoPlayer player;
    protected final Renderer[] renderers;
    private Surface surface;
    private SurfaceHolder surfaceHolder;
    private TextRenderer.Output textOutput;
    private TextureView textureView;
    private VideoRendererEventListener videoDebugListener;
    private DecoderCounters videoDecoderCounters;
    private Format videoFormat;
    private VideoListener videoListener;
    private final int videoRendererCount;
    private int videoScalingMode;

    protected SimpleExoPlayer(RenderersFactory renderersFactory, TrackSelector trackSelector, LoadControl loadControl) {
        this.renderers = renderersFactory.createRenderers(new Handler(Looper.myLooper() != null ? Looper.myLooper() : Looper.getMainLooper()), this.componentListener, this.componentListener, this.componentListener, this.componentListener);
        int videoRendererCount = 0;
        int audioRendererCount = 0;
        for (Renderer renderer : this.renderers) {
            switch (renderer.getTrackType()) {
                case 1:
                    audioRendererCount++;
                    break;
                case 2:
                    videoRendererCount++;
                    break;
                default:
                    break;
            }
        }
        this.videoRendererCount = videoRendererCount;
        this.audioRendererCount = audioRendererCount;
        this.audioVolume = 1.0f;
        this.audioSessionId = 0;
        this.audioStreamType = 3;
        this.videoScalingMode = 1;
        this.player = new ExoPlayerImpl(this.renderers, trackSelector, loadControl);
    }

    public void setVideoScalingMode(int videoScalingMode) {
        this.videoScalingMode = videoScalingMode;
        ExoPlayerMessage[] messages = new ExoPlayerMessage[this.videoRendererCount];
        Renderer[] rendererArr = this.renderers;
        int length = rendererArr.length;
        int i = 0;
        int count = 0;
        while (i < length) {
            int count2;
            Renderer renderer = rendererArr[i];
            if (renderer.getTrackType() == 2) {
                count2 = count + 1;
                messages[count] = new ExoPlayerMessage(renderer, 4, Integer.valueOf(videoScalingMode));
            } else {
                count2 = count;
            }
            i++;
            count = count2;
        }
        this.player.sendMessages(messages);
    }

    public int getVideoScalingMode() {
        return this.videoScalingMode;
    }

    public void clearVideoSurface() {
        setVideoSurface(null);
    }

    public void setVideoSurface(Surface surface) {
        removeSurfaceCallbacks();
        setVideoSurfaceInternal(surface, false);
    }

    public void clearVideoSurface(Surface surface) {
        if (surface != null && surface == this.surface) {
            setVideoSurface(null);
        }
    }

    public void setVideoSurfaceHolder(SurfaceHolder surfaceHolder) {
        removeSurfaceCallbacks();
        this.surfaceHolder = surfaceHolder;
        if (surfaceHolder == null) {
            setVideoSurfaceInternal(null, false);
            return;
        }
        setVideoSurfaceInternal(surfaceHolder.getSurface(), false);
        surfaceHolder.addCallback(this.componentListener);
    }

    public void clearVideoSurfaceHolder(SurfaceHolder surfaceHolder) {
        if (surfaceHolder != null && surfaceHolder == this.surfaceHolder) {
            setVideoSurfaceHolder(null);
        }
    }

    public void setVideoSurfaceView(SurfaceView surfaceView) {
        setVideoSurfaceHolder(surfaceView == null ? null : surfaceView.getHolder());
    }

    public void clearVideoSurfaceView(SurfaceView surfaceView) {
        clearVideoSurfaceHolder(surfaceView == null ? null : surfaceView.getHolder());
    }

    public void setVideoTextureView(TextureView textureView) {
        Surface surface = null;
        removeSurfaceCallbacks();
        this.textureView = textureView;
        if (textureView == null) {
            setVideoSurfaceInternal(null, true);
            return;
        }
        if (textureView.getSurfaceTextureListener() != null) {
            Log.w(TAG, "Replacing existing SurfaceTextureListener.");
        }
        SurfaceTexture surfaceTexture = textureView.getSurfaceTexture();
        if (surfaceTexture != null) {
            surface = new Surface(surfaceTexture);
        }
        setVideoSurfaceInternal(surface, true);
        textureView.setSurfaceTextureListener(this.componentListener);
    }

    public void clearVideoTextureView(TextureView textureView) {
        if (textureView != null && textureView == this.textureView) {
            setVideoTextureView(null);
        }
    }

    public void setAudioStreamType(int audioStreamType) {
        this.audioStreamType = audioStreamType;
        ExoPlayerMessage[] messages = new ExoPlayerMessage[this.audioRendererCount];
        Renderer[] rendererArr = this.renderers;
        int length = rendererArr.length;
        int i = 0;
        int count = 0;
        while (i < length) {
            int count2;
            Renderer renderer = rendererArr[i];
            if (renderer.getTrackType() == 1) {
                count2 = count + 1;
                messages[count] = new ExoPlayerMessage(renderer, 3, Integer.valueOf(audioStreamType));
            } else {
                count2 = count;
            }
            i++;
            count = count2;
        }
        this.player.sendMessages(messages);
    }

    public int getAudioStreamType() {
        return this.audioStreamType;
    }

    public void setVolume(float audioVolume) {
        this.audioVolume = audioVolume;
        ExoPlayerMessage[] messages = new ExoPlayerMessage[this.audioRendererCount];
        Renderer[] rendererArr = this.renderers;
        int length = rendererArr.length;
        int i = 0;
        int count = 0;
        while (i < length) {
            int count2;
            Renderer renderer = rendererArr[i];
            if (renderer.getTrackType() == 1) {
                count2 = count + 1;
                messages[count] = new ExoPlayerMessage(renderer, 2, Float.valueOf(audioVolume));
            } else {
                count2 = count;
            }
            i++;
            count = count2;
        }
        this.player.sendMessages(messages);
    }

    public float getVolume() {
        return this.audioVolume;
    }

    @TargetApi(23)
    @Deprecated
    public void setPlaybackParams(@Nullable PlaybackParams params) {
        PlaybackParameters playbackParameters;
        if (params != null) {
            params.allowDefaults();
            playbackParameters = new PlaybackParameters(params.getSpeed(), params.getPitch());
        } else {
            playbackParameters = null;
        }
        setPlaybackParameters(playbackParameters);
    }

    public Format getVideoFormat() {
        return this.videoFormat;
    }

    public Format getAudioFormat() {
        return this.audioFormat;
    }

    public int getAudioSessionId() {
        return this.audioSessionId;
    }

    public DecoderCounters getVideoDecoderCounters() {
        return this.videoDecoderCounters;
    }

    public DecoderCounters getAudioDecoderCounters() {
        return this.audioDecoderCounters;
    }

    public void setVideoListener(VideoListener listener) {
        this.videoListener = listener;
    }

    public void clearVideoListener(VideoListener listener) {
        if (this.videoListener == listener) {
            this.videoListener = null;
        }
    }

    public void setTextOutput(TextRenderer.Output output) {
        this.textOutput = output;
    }

    public void clearTextOutput(TextRenderer.Output output) {
        if (this.textOutput == output) {
            this.textOutput = null;
        }
    }

    public void setMetadataOutput(Output output) {
        this.metadataOutput = output;
    }

    public void clearMetadataOutput(Output output) {
        if (this.metadataOutput == output) {
            this.metadataOutput = null;
        }
    }

    public void setVideoDebugListener(VideoRendererEventListener listener) {
        this.videoDebugListener = listener;
    }

    public void setAudioDebugListener(AudioRendererEventListener listener) {
        this.audioDebugListener = listener;
    }

    public void addListener(EventListener listener) {
        this.player.addListener(listener);
    }

    public void removeListener(EventListener listener) {
        this.player.removeListener(listener);
    }

    public int getPlaybackState() {
        return this.player.getPlaybackState();
    }

    public void prepare(MediaSource mediaSource) {
        this.player.prepare(mediaSource);
    }

    public void prepare(MediaSource mediaSource, boolean resetPosition, boolean resetState) {
        this.player.prepare(mediaSource, resetPosition, resetState);
    }

    public void setPlayWhenReady(boolean playWhenReady) {
        this.player.setPlayWhenReady(playWhenReady);
    }

    public boolean getPlayWhenReady() {
        return this.player.getPlayWhenReady();
    }

    public boolean isLoading() {
        return this.player.isLoading();
    }

    public void seekToDefaultPosition() {
        this.player.seekToDefaultPosition();
    }

    public void seekToDefaultPosition(int windowIndex) {
        this.player.seekToDefaultPosition(windowIndex);
    }

    public void seekTo(long positionMs) {
        this.player.seekTo(positionMs);
    }

    public void seekTo(int windowIndex, long positionMs) {
        this.player.seekTo(windowIndex, positionMs);
    }

    public void setPlaybackParameters(PlaybackParameters playbackParameters) {
        this.player.setPlaybackParameters(playbackParameters);
    }

    public PlaybackParameters getPlaybackParameters() {
        return this.player.getPlaybackParameters();
    }

    public void stop() {
        this.player.stop();
    }

    public void release() {
        this.player.release();
        removeSurfaceCallbacks();
        if (this.surface != null) {
            if (this.ownsSurface) {
                this.surface.release();
            }
            this.surface = null;
        }
    }

    public void sendMessages(ExoPlayerMessage... messages) {
        this.player.sendMessages(messages);
    }

    public void blockingSendMessages(ExoPlayerMessage... messages) {
        this.player.blockingSendMessages(messages);
    }

    public int getRendererCount() {
        return this.player.getRendererCount();
    }

    public int getRendererType(int index) {
        return this.player.getRendererType(index);
    }

    public TrackGroupArray getCurrentTrackGroups() {
        return this.player.getCurrentTrackGroups();
    }

    public TrackSelectionArray getCurrentTrackSelections() {
        return this.player.getCurrentTrackSelections();
    }

    public Timeline getCurrentTimeline() {
        return this.player.getCurrentTimeline();
    }

    public Object getCurrentManifest() {
        return this.player.getCurrentManifest();
    }

    public int getCurrentPeriodIndex() {
        return this.player.getCurrentPeriodIndex();
    }

    public int getCurrentWindowIndex() {
        return this.player.getCurrentWindowIndex();
    }

    public long getDuration() {
        return this.player.getDuration();
    }

    public long getCurrentPosition() {
        return this.player.getCurrentPosition();
    }

    public long getBufferedPosition() {
        return this.player.getBufferedPosition();
    }

    public int getBufferedPercentage() {
        return this.player.getBufferedPercentage();
    }

    public boolean isCurrentWindowDynamic() {
        return this.player.isCurrentWindowDynamic();
    }

    public boolean isCurrentWindowSeekable() {
        return this.player.isCurrentWindowSeekable();
    }

    private void removeSurfaceCallbacks() {
        if (this.textureView != null) {
            if (this.textureView.getSurfaceTextureListener() != this.componentListener) {
                Log.w(TAG, "SurfaceTextureListener already unset or replaced.");
            } else {
                this.textureView.setSurfaceTextureListener(null);
            }
            this.textureView = null;
        }
        if (this.surfaceHolder != null) {
            this.surfaceHolder.removeCallback(this.componentListener);
            this.surfaceHolder = null;
        }
    }

    private void setVideoSurfaceInternal(Surface surface, boolean ownsSurface) {
        ExoPlayerMessage[] messages = new ExoPlayerMessage[this.videoRendererCount];
        Renderer[] rendererArr = this.renderers;
        int length = rendererArr.length;
        int i = 0;
        int count = 0;
        while (i < length) {
            int count2;
            Renderer renderer = rendererArr[i];
            if (renderer.getTrackType() == 2) {
                count2 = count + 1;
                messages[count] = new ExoPlayerMessage(renderer, 1, surface);
            } else {
                count2 = count;
            }
            i++;
            count = count2;
        }
        if (this.surface == null || this.surface == surface) {
            this.player.sendMessages(messages);
        } else {
            if (this.ownsSurface) {
                this.surface.release();
            }
            this.player.blockingSendMessages(messages);
        }
        this.surface = surface;
        this.ownsSurface = ownsSurface;
    }
}
