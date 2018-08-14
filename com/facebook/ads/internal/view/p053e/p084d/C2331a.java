package com.facebook.ads.internal.view.p053e.p084d;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.SurfaceTexture;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build.VERSION;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.TextureView;
import android.view.TextureView.SurfaceTextureListener;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnTouchListener;
import android.widget.MediaController;
import android.widget.MediaController.MediaPlayerControl;
import com.facebook.ads.internal.p052j.C1998a;
import com.facebook.ads.internal.p052j.C1999b;
import com.facebook.ads.internal.settings.AdInternalSettings;
import com.facebook.ads.internal.view.p053e.p083a.C2222a;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayer$EventListener;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.SimpleExoPlayer$VideoListener;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection.Factory;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.TransferListener;
import com.google.android.exoplayer2.util.Util;

@TargetApi(14)
public class C2331a extends TextureView implements SurfaceTextureListener, C2330c, ExoPlayer$EventListener, SimpleExoPlayer$VideoListener {
    private static final String f5661a = C2331a.class.getSimpleName();
    private Uri f5662b;
    @Nullable
    private String f5663c;
    private C2248e f5664d;
    private Surface f5665e;
    @Nullable
    private SimpleExoPlayer f5666f;
    private MediaController f5667g;
    private C2336d f5668h = C2336d.IDLE;
    private C2336d f5669i = C2336d.IDLE;
    private C2336d f5670j = C2336d.IDLE;
    private boolean f5671k = false;
    private View f5672l;
    private boolean f5673m = false;
    private boolean f5674n = false;
    private long f5675o;
    private long f5676p;
    private long f5677q;
    private int f5678r;
    private int f5679s;
    private float f5680t = 1.0f;
    private int f5681u = -1;
    private boolean f5682v = false;
    private boolean f5683w = false;
    private C2222a f5684x = C2222a.NOT_STARTED;
    private boolean f5685y = false;

    class C23271 implements MediaPlayerControl {
        final /* synthetic */ C2331a f5658a;

        C23271(C2331a c2331a) {
            this.f5658a = c2331a;
        }

        public boolean canPause() {
            return true;
        }

        public boolean canSeekBackward() {
            return true;
        }

        public boolean canSeekForward() {
            return true;
        }

        public int getAudioSessionId() {
            return this.f5658a.f5666f != null ? this.f5658a.f5666f.getAudioSessionId() : 0;
        }

        public int getBufferPercentage() {
            return this.f5658a.f5666f != null ? this.f5658a.f5666f.getBufferedPercentage() : 0;
        }

        public int getCurrentPosition() {
            return this.f5658a.getCurrentPosition();
        }

        public int getDuration() {
            return this.f5658a.getDuration();
        }

        public boolean isPlaying() {
            return this.f5658a.f5666f != null && this.f5658a.f5666f.getPlayWhenReady();
        }

        public void pause() {
            this.f5658a.mo3791a(true);
        }

        public void seekTo(int i) {
            this.f5658a.mo3789a(i);
        }

        public void start() {
            this.f5658a.mo3790a(C2222a.USER_STARTED);
        }
    }

    class C23282 implements OnTouchListener {
        final /* synthetic */ C2331a f5659a;

        C23282(C2331a c2331a) {
            this.f5659a = c2331a;
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (this.f5659a.f5667g != null && motionEvent.getAction() == 1) {
                if (this.f5659a.f5667g.isShowing()) {
                    this.f5659a.f5667g.hide();
                } else {
                    this.f5659a.f5667g.show();
                }
            }
            return true;
        }
    }

    class C23293 implements OnTouchListener {
        final /* synthetic */ C2331a f5660a;

        C23293(C2331a c2331a) {
            this.f5660a = c2331a;
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (this.f5660a.f5667g != null && motionEvent.getAction() == 1) {
                if (this.f5660a.f5667g.isShowing()) {
                    this.f5660a.f5667g.hide();
                } else {
                    this.f5660a.f5667g.show();
                }
            }
            return true;
        }
    }

    public C2331a(Context context) {
        super(context);
    }

    public C2331a(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public C2331a(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @TargetApi(21)
    public C2331a(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    private void m7354f() {
        TransferListener defaultBandwidthMeter = new DefaultBandwidthMeter();
        this.f5666f = ExoPlayerFactory.newSimpleInstance(getContext(), new DefaultTrackSelector(new Factory(defaultBandwidthMeter)), new DefaultLoadControl());
        this.f5666f.setVideoListener(this);
        this.f5666f.addListener(this);
        this.f5666f.setPlayWhenReady(false);
        if (this.f5674n && !this.f5682v) {
            this.f5667g = new MediaController(getContext());
            this.f5667g.setAnchorView(this.f5672l == null ? this : this.f5672l);
            this.f5667g.setMediaPlayer(new C23271(this));
            this.f5667g.setEnabled(true);
        }
        if (this.f5663c == null || this.f5663c.length() == 0 || this.f5685y) {
            this.f5666f.prepare(new ExtractorMediaSource(this.f5662b, new DefaultDataSourceFactory(getContext(), Util.getUserAgent(getContext(), "ads"), defaultBandwidthMeter), new DefaultExtractorsFactory(), null, null));
        }
        setVideoState(C2336d.PREPARING);
        if (isAvailable()) {
            onSurfaceTextureAvailable(getSurfaceTexture(), 0, 0);
        }
    }

    private void m7355g() {
        if (this.f5665e != null) {
            this.f5665e.release();
            this.f5665e = null;
        }
        if (this.f5666f != null) {
            this.f5666f.release();
            this.f5666f = null;
        }
        this.f5667g = null;
        this.f5673m = false;
        setVideoState(C2336d.IDLE);
    }

    private void setVideoState(C2336d c2336d) {
        if (c2336d != this.f5668h) {
            this.f5668h = c2336d;
            if (this.f5668h == C2336d.STARTED) {
                this.f5673m = true;
            }
            if (this.f5664d != null) {
                this.f5664d.mo3780a(c2336d);
            }
        }
    }

    public void m7356a() {
        if (!this.f5683w) {
            mo3791a(false);
        }
    }

    public void mo3789a(int i) {
        if (this.f5666f != null) {
            this.f5681u = getCurrentPosition();
            this.f5666f.seekTo((long) i);
            return;
        }
        this.f5677q = (long) i;
    }

    public void mo3790a(C2222a c2222a) {
        this.f5669i = C2336d.STARTED;
        this.f5684x = c2222a;
        if (this.f5666f == null) {
            setup(this.f5662b);
        } else if (this.f5668h == C2336d.PREPARED || this.f5668h == C2336d.PAUSED || this.f5668h == C2336d.PLAYBACK_COMPLETED) {
            this.f5666f.setPlayWhenReady(true);
            setVideoState(C2336d.STARTED);
        }
    }

    public void mo3791a(boolean z) {
        if (this.f5666f != null) {
            this.f5666f.setPlayWhenReady(false);
        } else {
            setVideoState(C2336d.IDLE);
        }
    }

    public void mo3792b() {
        setVideoState(C2336d.PLAYBACK_COMPLETED);
    }

    public void mo3793b(boolean z) {
        this.f5682v = z;
    }

    public void mo3794c() {
        this.f5669i = C2336d.IDLE;
        if (this.f5666f != null) {
            this.f5666f.stop();
            this.f5666f.release();
            this.f5666f = null;
        }
        setVideoState(C2336d.IDLE);
    }

    public boolean mo3795d() {
        return (this.f5666f == null || this.f5666f.getAudioFormat() == null) ? false : true;
    }

    public void mo3796e() {
        m7355g();
    }

    public int getCurrentPosition() {
        return this.f5666f != null ? (int) this.f5666f.getCurrentPosition() : 0;
    }

    public int getDuration() {
        return this.f5666f == null ? 0 : (int) this.f5666f.getDuration();
    }

    public long getInitialBufferTime() {
        return this.f5676p;
    }

    public C2222a getStartReason() {
        return this.f5684x;
    }

    public C2336d getState() {
        return this.f5668h;
    }

    public C2336d getTargetState() {
        return this.f5669i;
    }

    public int getVideoHeight() {
        return this.f5679s;
    }

    public int getVideoWidth() {
        return this.f5678r;
    }

    public View getView() {
        return this;
    }

    public float getVolume() {
        return this.f5680t;
    }

    public void onLoadingChanged(boolean z) {
    }

    protected void onMeasure(int i, int i2) {
        int defaultSize = C2331a.getDefaultSize(this.f5678r, i);
        int defaultSize2 = C2331a.getDefaultSize(this.f5679s, i2);
        if (this.f5678r > 0 && this.f5679s > 0) {
            int mode = MeasureSpec.getMode(i);
            int size = MeasureSpec.getSize(i);
            int mode2 = MeasureSpec.getMode(i2);
            defaultSize2 = MeasureSpec.getSize(i2);
            if (mode == 1073741824 && mode2 == 1073741824) {
                if (this.f5678r * defaultSize2 < this.f5679s * size) {
                    defaultSize = (this.f5678r * defaultSize2) / this.f5679s;
                } else if (this.f5678r * defaultSize2 > this.f5679s * size) {
                    defaultSize2 = (this.f5679s * size) / this.f5678r;
                    defaultSize = size;
                } else {
                    defaultSize = size;
                }
            } else if (mode == 1073741824) {
                defaultSize = (this.f5679s * size) / this.f5678r;
                if (mode2 != Integer.MIN_VALUE || defaultSize <= defaultSize2) {
                    defaultSize2 = defaultSize;
                    defaultSize = size;
                } else {
                    defaultSize = size;
                }
            } else if (mode2 == 1073741824) {
                defaultSize = (this.f5678r * defaultSize2) / this.f5679s;
                if (mode == Integer.MIN_VALUE && defaultSize > size) {
                    defaultSize = size;
                }
            } else {
                int i3 = this.f5678r;
                defaultSize = this.f5679s;
                if (mode2 != Integer.MIN_VALUE || defaultSize <= defaultSize2) {
                    defaultSize2 = defaultSize;
                    defaultSize = i3;
                } else {
                    defaultSize = (this.f5678r * defaultSize2) / this.f5679s;
                }
                if (mode == Integer.MIN_VALUE && r1 > size) {
                    defaultSize2 = (this.f5679s * size) / this.f5678r;
                    defaultSize = size;
                }
            }
        }
        setMeasuredDimension(defaultSize, defaultSize2);
    }

    public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {
    }

    public void onPlayerError(ExoPlaybackException exoPlaybackException) {
        setVideoState(C2336d.ERROR);
        exoPlaybackException.printStackTrace();
        C1999b.m6321a(C1998a.m6318a(exoPlaybackException, "[ExoPlayer] Error during playback of ExoPlayer"));
    }

    public void onPlayerStateChanged(boolean z, int i) {
        switch (i) {
            case 1:
                setVideoState(C2336d.IDLE);
                return;
            case 2:
                if (this.f5681u >= 0) {
                    int i2 = this.f5681u;
                    this.f5681u = -1;
                    this.f5664d.mo3779a(i2, getCurrentPosition());
                    return;
                }
                return;
            case 3:
                if (this.f5675o != 0) {
                    this.f5676p = System.currentTimeMillis() - this.f5675o;
                }
                setRequestedVolume(this.f5680t);
                if (this.f5677q > 0 && this.f5677q < this.f5666f.getDuration()) {
                    this.f5666f.seekTo(this.f5677q);
                    this.f5677q = 0;
                }
                if (this.f5666f.getCurrentPosition() != 0 && !z && this.f5673m) {
                    setVideoState(C2336d.PAUSED);
                    return;
                } else if (!z && this.f5668h != C2336d.PLAYBACK_COMPLETED) {
                    setVideoState(C2336d.PREPARED);
                    if (this.f5669i == C2336d.STARTED) {
                        mo3790a(this.f5684x);
                        this.f5669i = C2336d.IDLE;
                        return;
                    }
                    return;
                } else {
                    return;
                }
            case 4:
                if (z) {
                    setVideoState(C2336d.PLAYBACK_COMPLETED);
                }
                if (this.f5666f != null) {
                    this.f5666f.setPlayWhenReady(false);
                    if (!z) {
                        this.f5666f.seekToDefaultPosition();
                    }
                }
                this.f5673m = false;
                return;
            default:
                return;
        }
    }

    public void onPositionDiscontinuity() {
    }

    public void onRenderedFirstFrame() {
    }

    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
        if (this.f5665e != null) {
            this.f5665e.release();
        }
        this.f5665e = new Surface(surfaceTexture);
        if (this.f5666f != null) {
            this.f5666f.setVideoSurface(this.f5665e);
            this.f5671k = false;
            if (this.f5668h == C2336d.PAUSED && this.f5670j != C2336d.PAUSED) {
                mo3790a(this.f5684x);
            }
        }
    }

    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        if (this.f5665e != null) {
            this.f5665e.release();
            this.f5665e = null;
            if (this.f5666f != null) {
                this.f5666f.setVideoSurface(null);
            }
        }
        if (!this.f5671k) {
            this.f5670j = this.f5674n ? C2336d.STARTED : this.f5668h;
            this.f5671k = true;
        }
        if (this.f5668h != C2336d.PAUSED) {
            mo3791a(false);
        }
        return true;
    }

    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
    }

    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
    }

    public void onTimelineChanged(Timeline timeline, Object obj) {
    }

    public void onTracksChanged(TrackGroupArray trackGroupArray, TrackSelectionArray trackSelectionArray) {
    }

    public void onVideoSizeChanged(int i, int i2, int i3, float f) {
        this.f5678r = i;
        this.f5679s = i2;
        if (this.f5678r != 0 && this.f5679s != 0) {
            requestLayout();
        }
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (this.f5666f != null) {
            if (this.f5667g != null && this.f5667g.isShowing()) {
                return;
            }
            if (z) {
                this.f5671k = false;
                if (this.f5668h == C2336d.PAUSED && this.f5670j != C2336d.PAUSED) {
                    mo3790a(this.f5684x);
                    return;
                }
                return;
            }
            if (!this.f5671k) {
                this.f5670j = this.f5674n ? C2336d.STARTED : this.f5668h;
                this.f5671k = true;
            }
            if (this.f5668h != C2336d.PAUSED) {
                m7356a();
            }
        }
    }

    public void setBackgroundDrawable(Drawable drawable) {
        if (VERSION.SDK_INT < 24) {
            super.setBackgroundDrawable(drawable);
        } else if (AdInternalSettings.isDebugBuild()) {
            Log.w(f5661a, "Google always throw an exception with setBackgroundDrawable on Nougat above. so we silently ignore it.");
        }
    }

    public void setBackgroundPlaybackEnabled(boolean z) {
        this.f5683w = z;
    }

    public void setControlsAnchorView(View view) {
        this.f5672l = view;
        view.setOnTouchListener(new C23293(this));
    }

    public void setForeground(Drawable drawable) {
        if (VERSION.SDK_INT < 24) {
            super.setForeground(drawable);
        } else if (AdInternalSettings.isDebugBuild()) {
            Log.w(f5661a, "Google always throw an exception with setForeground on Nougat above. so we silently ignore it.");
        }
    }

    public void setFullScreen(boolean z) {
        this.f5674n = z;
        if (z && !this.f5682v) {
            setOnTouchListener(new C23282(this));
        }
    }

    public void setRequestedVolume(float f) {
        this.f5680t = f;
        if (this.f5666f != null && this.f5668h != C2336d.PREPARING && this.f5668h != C2336d.IDLE) {
            this.f5666f.setVolume(f);
        }
    }

    public void setTestMode(boolean z) {
        this.f5685y = z;
    }

    public void setVideoMPD(@Nullable String str) {
        this.f5663c = str;
    }

    public void setVideoStateChangeListener(C2248e c2248e) {
        this.f5664d = c2248e;
    }

    public void setup(Uri uri) {
        if (this.f5666f != null) {
            m7355g();
        }
        this.f5662b = uri;
        setSurfaceTextureListener(this);
        m7354f();
    }
}
