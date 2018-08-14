package com.google.android.exoplayer2;

import android.graphics.SurfaceTexture;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.TextureView.SurfaceTextureListener;
import com.google.android.exoplayer2.audio.AudioRendererEventListener;
import com.google.android.exoplayer2.decoder.DecoderCounters;
import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.metadata.MetadataRenderer;
import com.google.android.exoplayer2.text.Cue;
import com.google.android.exoplayer2.text.TextRenderer.Output;
import com.google.android.exoplayer2.video.VideoRendererEventListener;
import java.util.List;

final class SimpleExoPlayer$ComponentListener implements VideoRendererEventListener, AudioRendererEventListener, Output, MetadataRenderer.Output, Callback, SurfaceTextureListener {
    final /* synthetic */ SimpleExoPlayer this$0;

    private SimpleExoPlayer$ComponentListener(SimpleExoPlayer simpleExoPlayer) {
        this.this$0 = simpleExoPlayer;
    }

    public void onVideoEnabled(DecoderCounters counters) {
        SimpleExoPlayer.access$102(this.this$0, counters);
        if (SimpleExoPlayer.access$200(this.this$0) != null) {
            SimpleExoPlayer.access$200(this.this$0).onVideoEnabled(counters);
        }
    }

    public void onVideoDecoderInitialized(String decoderName, long initializedTimestampMs, long initializationDurationMs) {
        if (SimpleExoPlayer.access$200(this.this$0) != null) {
            SimpleExoPlayer.access$200(this.this$0).onVideoDecoderInitialized(decoderName, initializedTimestampMs, initializationDurationMs);
        }
    }

    public void onVideoInputFormatChanged(Format format) {
        SimpleExoPlayer.access$302(this.this$0, format);
        if (SimpleExoPlayer.access$200(this.this$0) != null) {
            SimpleExoPlayer.access$200(this.this$0).onVideoInputFormatChanged(format);
        }
    }

    public void onDroppedFrames(int count, long elapsed) {
        if (SimpleExoPlayer.access$200(this.this$0) != null) {
            SimpleExoPlayer.access$200(this.this$0).onDroppedFrames(count, elapsed);
        }
    }

    public void onVideoSizeChanged(int width, int height, int unappliedRotationDegrees, float pixelWidthHeightRatio) {
        if (SimpleExoPlayer.access$400(this.this$0) != null) {
            SimpleExoPlayer.access$400(this.this$0).onVideoSizeChanged(width, height, unappliedRotationDegrees, pixelWidthHeightRatio);
        }
        if (SimpleExoPlayer.access$200(this.this$0) != null) {
            SimpleExoPlayer.access$200(this.this$0).onVideoSizeChanged(width, height, unappliedRotationDegrees, pixelWidthHeightRatio);
        }
    }

    public void onRenderedFirstFrame(Surface surface) {
        if (SimpleExoPlayer.access$400(this.this$0) != null && SimpleExoPlayer.access$500(this.this$0) == surface) {
            SimpleExoPlayer.access$400(this.this$0).onRenderedFirstFrame();
        }
        if (SimpleExoPlayer.access$200(this.this$0) != null) {
            SimpleExoPlayer.access$200(this.this$0).onRenderedFirstFrame(surface);
        }
    }

    public void onVideoDisabled(DecoderCounters counters) {
        if (SimpleExoPlayer.access$200(this.this$0) != null) {
            SimpleExoPlayer.access$200(this.this$0).onVideoDisabled(counters);
        }
        SimpleExoPlayer.access$302(this.this$0, null);
        SimpleExoPlayer.access$102(this.this$0, null);
    }

    public void onAudioEnabled(DecoderCounters counters) {
        SimpleExoPlayer.access$602(this.this$0, counters);
        if (SimpleExoPlayer.access$700(this.this$0) != null) {
            SimpleExoPlayer.access$700(this.this$0).onAudioEnabled(counters);
        }
    }

    public void onAudioSessionId(int sessionId) {
        SimpleExoPlayer.access$802(this.this$0, sessionId);
        if (SimpleExoPlayer.access$700(this.this$0) != null) {
            SimpleExoPlayer.access$700(this.this$0).onAudioSessionId(sessionId);
        }
    }

    public void onAudioDecoderInitialized(String decoderName, long initializedTimestampMs, long initializationDurationMs) {
        if (SimpleExoPlayer.access$700(this.this$0) != null) {
            SimpleExoPlayer.access$700(this.this$0).onAudioDecoderInitialized(decoderName, initializedTimestampMs, initializationDurationMs);
        }
    }

    public void onAudioInputFormatChanged(Format format) {
        SimpleExoPlayer.access$902(this.this$0, format);
        if (SimpleExoPlayer.access$700(this.this$0) != null) {
            SimpleExoPlayer.access$700(this.this$0).onAudioInputFormatChanged(format);
        }
    }

    public void onAudioTrackUnderrun(int bufferSize, long bufferSizeMs, long elapsedSinceLastFeedMs) {
        if (SimpleExoPlayer.access$700(this.this$0) != null) {
            SimpleExoPlayer.access$700(this.this$0).onAudioTrackUnderrun(bufferSize, bufferSizeMs, elapsedSinceLastFeedMs);
        }
    }

    public void onAudioDisabled(DecoderCounters counters) {
        if (SimpleExoPlayer.access$700(this.this$0) != null) {
            SimpleExoPlayer.access$700(this.this$0).onAudioDisabled(counters);
        }
        SimpleExoPlayer.access$902(this.this$0, null);
        SimpleExoPlayer.access$602(this.this$0, null);
        SimpleExoPlayer.access$802(this.this$0, 0);
    }

    public void onCues(List<Cue> cues) {
        if (SimpleExoPlayer.access$1000(this.this$0) != null) {
            SimpleExoPlayer.access$1000(this.this$0).onCues(cues);
        }
    }

    public void onMetadata(Metadata metadata) {
        if (SimpleExoPlayer.access$1100(this.this$0) != null) {
            SimpleExoPlayer.access$1100(this.this$0).onMetadata(metadata);
        }
    }

    public void surfaceCreated(SurfaceHolder holder) {
        SimpleExoPlayer.access$1200(this.this$0, holder.getSurface(), false);
    }

    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    public void surfaceDestroyed(SurfaceHolder holder) {
        SimpleExoPlayer.access$1200(this.this$0, null, false);
    }

    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int width, int height) {
        SimpleExoPlayer.access$1200(this.this$0, new Surface(surfaceTexture), true);
    }

    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int width, int height) {
    }

    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        SimpleExoPlayer.access$1200(this.this$0, null, true);
        return true;
    }

    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
    }
}
