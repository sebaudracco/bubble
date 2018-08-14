package com.facebook.ads.internal.view.p053e.p084d;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.graphics.SurfaceTexture;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnBufferingUpdateListener;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnInfoListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.media.MediaPlayer.OnSeekCompleteListener;
import android.media.MediaPlayer.OnVideoSizeChangedListener;
import android.media.MediaPlayer.TrackInfo;
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
import com.facebook.ads.internal.p056q.p077d.C2150a;
import com.facebook.ads.internal.settings.AdInternalSettings;
import com.facebook.ads.internal.view.p053e.p083a.C2222a;
import java.io.IOException;

@TargetApi(14)
public class C2335b extends TextureView implements OnBufferingUpdateListener, OnCompletionListener, OnErrorListener, OnInfoListener, OnPreparedListener, OnSeekCompleteListener, OnVideoSizeChangedListener, SurfaceTextureListener, C2330c {
    private static final String f5689s = C2335b.class.getSimpleName();
    private Uri f5690a;
    private C2248e f5691b;
    private Surface f5692c;
    @Nullable
    private MediaPlayer f5693d;
    private MediaController f5694e;
    private C2336d f5695f = C2336d.IDLE;
    private C2336d f5696g = C2336d.IDLE;
    private C2336d f5697h = C2336d.IDLE;
    private boolean f5698i = false;
    private View f5699j;
    private int f5700k = 0;
    private long f5701l;
    private int f5702m = 0;
    private int f5703n = 0;
    private float f5704o = 1.0f;
    private boolean f5705p = false;
    private int f5706q = 3;
    private boolean f5707r = false;
    private int f5708t = 0;
    private boolean f5709u = false;
    private C2222a f5710v = C2222a.NOT_STARTED;
    private final MediaPlayerControl f5711w = new C23321(this);

    class C23321 implements MediaPlayerControl {
        final /* synthetic */ C2335b f5686a;

        C23321(C2335b c2335b) {
            this.f5686a = c2335b;
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
            return this.f5686a.f5693d != null ? this.f5686a.f5693d.getAudioSessionId() : 0;
        }

        public int getBufferPercentage() {
            return 0;
        }

        public int getCurrentPosition() {
            return this.f5686a.getCurrentPosition();
        }

        public int getDuration() {
            return this.f5686a.getDuration();
        }

        public boolean isPlaying() {
            return this.f5686a.f5693d != null && this.f5686a.f5693d.isPlaying();
        }

        public void pause() {
            this.f5686a.mo3791a(true);
        }

        public void seekTo(int i) {
            this.f5686a.mo3789a(i);
        }

        public void start() {
            this.f5686a.mo3790a(C2222a.USER_STARTED);
        }
    }

    class C23332 implements OnTouchListener {
        final /* synthetic */ C2335b f5687a;

        C23332(C2335b c2335b) {
            this.f5687a = c2335b;
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (!(this.f5687a.f5709u || this.f5687a.f5694e == null || motionEvent.getAction() != 1)) {
                if (this.f5687a.f5694e.isShowing()) {
                    this.f5687a.f5694e.hide();
                } else {
                    this.f5687a.f5694e.show();
                }
            }
            return true;
        }
    }

    class C23343 implements OnTouchListener {
        final /* synthetic */ C2335b f5688a;

        C23343(C2335b c2335b) {
            this.f5688a = c2335b;
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (!(this.f5688a.f5709u || this.f5688a.f5694e == null || motionEvent.getAction() != 1)) {
                if (this.f5688a.f5694e.isShowing()) {
                    this.f5688a.f5694e.hide();
                } else {
                    this.f5688a.f5694e.show();
                }
            }
            return true;
        }
    }

    public C2335b(Context context) {
        super(context);
    }

    public C2335b(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public C2335b(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @TargetApi(21)
    public C2335b(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    private boolean m7366a(@Nullable Surface surface) {
        if (this.f5693d == null) {
            return false;
        }
        try {
            this.f5693d.setSurface(surface);
            return true;
        } catch (Throwable e) {
            C2150a.m6888a(e, getContext());
            Log.d(f5689s, "The MediaPlayer failed", e);
            return false;
        }
    }

    private boolean m7369f() {
        return this.f5695f == C2336d.PREPARED || this.f5695f == C2336d.STARTED || this.f5695f == C2336d.PAUSED || this.f5695f == C2336d.PLAYBACK_COMPLETED;
    }

    private boolean m7370g() {
        if (this.f5693d == null) {
            return false;
        }
        try {
            this.f5693d.reset();
            return true;
        } catch (Throwable e) {
            C2150a.m6888a(e, getContext());
            Log.d(f5689s, "The MediaPlayer failed", e);
            return false;
        }
    }

    private boolean m7371h() {
        return (this.f5695f == C2336d.PREPARING || this.f5695f == C2336d.PREPARED) ? false : true;
    }

    private boolean m7372i() {
        return (this.f5695f == C2336d.PREPARING || this.f5695f == C2336d.PREPARED) ? false : true;
    }

    private void setVideoState(C2336d c2336d) {
        if (c2336d != this.f5695f) {
            this.f5695f = c2336d;
            if (this.f5691b != null) {
                this.f5691b.mo3780a(c2336d);
            }
        }
    }

    public void m7373a() {
        if (!this.f5707r) {
            mo3791a(false);
        }
    }

    public void mo3789a(int i) {
        if (this.f5693d == null || !m7369f()) {
            this.f5700k = i;
        } else if (i < getDuration() && i > 0) {
            this.f5708t = getCurrentPosition();
            this.f5700k = i;
            this.f5693d.seekTo(i);
        }
    }

    public void mo3790a(C2222a c2222a) {
        this.f5696g = C2336d.STARTED;
        this.f5710v = c2222a;
        if (this.f5695f == C2336d.STARTED || this.f5695f == C2336d.PREPARED || this.f5695f == C2336d.IDLE || this.f5695f == C2336d.PAUSED || this.f5695f == C2336d.PLAYBACK_COMPLETED) {
            if (this.f5693d == null) {
                setup(this.f5690a);
            } else {
                if (this.f5700k > 0) {
                    this.f5693d.seekTo(this.f5700k);
                }
                this.f5693d.start();
                setVideoState(C2336d.STARTED);
            }
        }
        if (isAvailable()) {
            onSurfaceTextureAvailable(getSurfaceTexture(), 0, 0);
        }
    }

    public void mo3791a(boolean z) {
        this.f5696g = C2336d.PAUSED;
        if (this.f5693d == null) {
            setVideoState(C2336d.IDLE);
        } else if (m7372i()) {
            if (z) {
                this.f5697h = C2336d.PAUSED;
                this.f5698i = true;
            }
            this.f5693d.pause();
            if (this.f5695f != C2336d.PLAYBACK_COMPLETED) {
                setVideoState(C2336d.PAUSED);
            }
        }
    }

    public void mo3792b() {
        setVideoState(C2336d.PLAYBACK_COMPLETED);
        mo3794c();
        this.f5700k = 0;
    }

    public void mo3793b(boolean z) {
        if (this.f5694e != null) {
            this.f5694e.setVisibility(8);
        }
        this.f5709u = true;
    }

    public void mo3794c() {
        this.f5696g = C2336d.IDLE;
        if (this.f5693d != null) {
            int currentPosition = this.f5693d.getCurrentPosition();
            if (currentPosition > 0) {
                this.f5700k = currentPosition;
            }
            this.f5693d.stop();
            m7370g();
            this.f5693d.release();
            this.f5693d = null;
            if (this.f5694e != null) {
                this.f5694e.hide();
                this.f5694e.setEnabled(false);
            }
        }
        setVideoState(C2336d.IDLE);
    }

    @SuppressLint({"NewApi"})
    public boolean mo3795d() {
        if (this.f5693d == null || VERSION.SDK_INT < 16) {
            return false;
        }
        try {
            for (TrackInfo trackType : this.f5693d.getTrackInfo()) {
                if (trackType.getTrackType() == 2) {
                    return true;
                }
            }
            return false;
        } catch (Throwable e) {
            Log.e(f5689s, "Couldn't retrieve video information", e);
            return true;
        }
    }

    public void mo3796e() {
        if (this.f5693d != null) {
            m7366a(null);
            this.f5693d.setOnBufferingUpdateListener(null);
            this.f5693d.setOnCompletionListener(null);
            this.f5693d.setOnErrorListener(null);
            this.f5693d.setOnInfoListener(null);
            this.f5693d.setOnPreparedListener(null);
            this.f5693d.setOnVideoSizeChangedListener(null);
            this.f5693d.setOnSeekCompleteListener(null);
            m7370g();
            this.f5693d = null;
        }
    }

    public int getCurrentPosition() {
        return this.f5693d != null ? this.f5693d.getCurrentPosition() : 0;
    }

    public int getDuration() {
        return this.f5693d == null ? 0 : (getState() == C2336d.STARTED || getState() == C2336d.PAUSED || getState() == C2336d.PREPARED || getState() == C2336d.PLAYBACK_COMPLETED) ? this.f5693d.getDuration() : 0;
    }

    public long getInitialBufferTime() {
        return this.f5701l;
    }

    public C2222a getStartReason() {
        return this.f5710v;
    }

    public C2336d getState() {
        return this.f5695f;
    }

    public C2336d getTargetState() {
        return this.f5696g;
    }

    public int getVideoHeight() {
        return this.f5703n;
    }

    public int getVideoWidth() {
        return this.f5702m;
    }

    public View getView() {
        return this;
    }

    public float getVolume() {
        return this.f5704o;
    }

    public void onBufferingUpdate(MediaPlayer mediaPlayer, int i) {
    }

    public void onCompletion(MediaPlayer mediaPlayer) {
        if (this.f5693d != null) {
            this.f5693d.pause();
        }
        setVideoState(C2336d.PLAYBACK_COMPLETED);
        mo3789a(0);
        this.f5700k = 0;
    }

    public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
        if (this.f5706q <= 0 || getState() != C2336d.STARTED) {
            setVideoState(C2336d.ERROR);
            mo3794c();
        } else {
            this.f5706q--;
            mo3794c();
            mo3790a(this.f5710v);
        }
        return true;
    }

    public boolean onInfo(MediaPlayer mediaPlayer, int i, int i2) {
        switch (i) {
            case 701:
                setVideoState(C2336d.BUFFERING);
                break;
            case 702:
                if (m7371h()) {
                    setVideoState(C2336d.STARTED);
                    break;
                }
                break;
        }
        return false;
    }

    protected void onMeasure(int i, int i2) {
        int defaultSize = C2335b.getDefaultSize(this.f5702m, i);
        int defaultSize2 = C2335b.getDefaultSize(this.f5703n, i2);
        if (this.f5702m > 0 && this.f5703n > 0) {
            int mode = MeasureSpec.getMode(i);
            int size = MeasureSpec.getSize(i);
            int mode2 = MeasureSpec.getMode(i2);
            defaultSize2 = MeasureSpec.getSize(i2);
            if (mode == 1073741824 && mode2 == 1073741824) {
                if (this.f5702m * defaultSize2 < this.f5703n * size) {
                    defaultSize = (this.f5702m * defaultSize2) / this.f5703n;
                } else if (this.f5702m * defaultSize2 > this.f5703n * size) {
                    defaultSize2 = (this.f5703n * size) / this.f5702m;
                    defaultSize = size;
                } else {
                    defaultSize = size;
                }
            } else if (mode == 1073741824) {
                defaultSize = (this.f5703n * size) / this.f5702m;
                if (mode2 != Integer.MIN_VALUE || defaultSize <= defaultSize2) {
                    defaultSize2 = defaultSize;
                    defaultSize = size;
                } else {
                    defaultSize = size;
                }
            } else if (mode2 == 1073741824) {
                defaultSize = (this.f5702m * defaultSize2) / this.f5703n;
                if (mode == Integer.MIN_VALUE && defaultSize > size) {
                    defaultSize = size;
                }
            } else {
                int i3 = this.f5702m;
                defaultSize = this.f5703n;
                if (mode2 != Integer.MIN_VALUE || defaultSize <= defaultSize2) {
                    defaultSize2 = defaultSize;
                    defaultSize = i3;
                } else {
                    defaultSize = (this.f5702m * defaultSize2) / this.f5703n;
                }
                if (mode == Integer.MIN_VALUE && r1 > size) {
                    defaultSize2 = (this.f5703n * size) / this.f5702m;
                    defaultSize = size;
                }
            }
        }
        setMeasuredDimension(defaultSize, defaultSize2);
    }

    public void onPrepared(MediaPlayer mediaPlayer) {
        setVideoState(C2336d.PREPARED);
        if (this.f5705p && !this.f5709u) {
            this.f5694e = new MediaController(getContext());
            this.f5694e.setAnchorView(this.f5699j == null ? this : this.f5699j);
            this.f5694e.setMediaPlayer(this.f5711w);
            this.f5694e.setEnabled(true);
        }
        setRequestedVolume(this.f5704o);
        this.f5702m = mediaPlayer.getVideoWidth();
        this.f5703n = mediaPlayer.getVideoHeight();
        if (this.f5700k > 0) {
            if (this.f5700k >= this.f5693d.getDuration()) {
                this.f5700k = 0;
            }
            this.f5693d.seekTo(this.f5700k);
            this.f5700k = 0;
        }
        if (this.f5696g == C2336d.STARTED) {
            mo3790a(this.f5710v);
        }
    }

    public void onSeekComplete(MediaPlayer mediaPlayer) {
        if (this.f5691b != null) {
            this.f5691b.mo3779a(this.f5708t, this.f5700k);
            this.f5700k = 0;
        }
    }

    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
        if (this.f5692c == null) {
            this.f5692c = new Surface(surfaceTexture);
        }
        if (m7366a(this.f5692c)) {
            this.f5698i = false;
            if (this.f5695f == C2336d.PAUSED && this.f5697h != C2336d.PAUSED) {
                mo3790a(this.f5710v);
                return;
            }
            return;
        }
        setVideoState(C2336d.ERROR);
        mo3796e();
    }

    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        m7366a(null);
        if (this.f5692c != null) {
            this.f5692c.release();
            this.f5692c = null;
        }
        if (!this.f5698i) {
            this.f5697h = this.f5705p ? C2336d.STARTED : this.f5695f;
            this.f5698i = true;
        }
        if (this.f5695f != C2336d.PAUSED) {
            mo3791a(false);
        }
        return true;
    }

    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
    }

    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
    }

    public void onVideoSizeChanged(MediaPlayer mediaPlayer, int i, int i2) {
        this.f5702m = mediaPlayer.getVideoWidth();
        this.f5703n = mediaPlayer.getVideoHeight();
        if (this.f5702m != 0 && this.f5703n != 0) {
            requestLayout();
        }
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (this.f5693d != null) {
            if (this.f5694e != null && this.f5694e.isShowing()) {
                return;
            }
            if (z) {
                this.f5698i = false;
                if (this.f5695f == C2336d.PAUSED && this.f5697h != C2336d.PAUSED) {
                    mo3790a(this.f5710v);
                    return;
                }
                return;
            }
            if (!this.f5698i) {
                this.f5697h = this.f5705p ? C2336d.STARTED : this.f5695f;
                this.f5698i = true;
            }
            if (this.f5695f != C2336d.PAUSED) {
                m7373a();
            }
        }
    }

    public void setBackgroundDrawable(Drawable drawable) {
        if (VERSION.SDK_INT < 24) {
            super.setBackgroundDrawable(drawable);
        } else if (AdInternalSettings.isDebugBuild()) {
            Log.w(f5689s, "Google always throw an exception with setBackgroundDrawable on Nougat above. so we silently ignore it.");
        }
    }

    public void setBackgroundPlaybackEnabled(boolean z) {
        this.f5707r = z;
    }

    public void setControlsAnchorView(View view) {
        this.f5699j = view;
        view.setOnTouchListener(new C23343(this));
    }

    public void setForeground(Drawable drawable) {
        if (VERSION.SDK_INT < 24) {
            super.setForeground(drawable);
        } else if (AdInternalSettings.isDebugBuild()) {
            Log.w(f5689s, "Google always throw an exception with setForeground on Nougat above. so we silently ignore it.");
        }
    }

    public void setFullScreen(boolean z) {
        this.f5705p = z;
        if (this.f5705p && !this.f5709u) {
            setOnTouchListener(new C23332(this));
        }
    }

    public void setRequestedVolume(float f) {
        this.f5704o = f;
        if (this.f5693d != null && this.f5695f != C2336d.PREPARING && this.f5695f != C2336d.IDLE) {
            this.f5693d.setVolume(f, f);
        }
    }

    public void setVideoMPD(@Nullable String str) {
    }

    public void setVideoStateChangeListener(C2248e c2248e) {
        this.f5691b = c2248e;
    }

    public void setup(Uri uri) {
        MediaPlayer mediaPlayer;
        Object e;
        Throwable th;
        AssetFileDescriptor assetFileDescriptor = null;
        this.f5690a = uri;
        if (this.f5693d != null) {
            m7370g();
            m7366a(null);
            mediaPlayer = this.f5693d;
        } else {
            mediaPlayer = new MediaPlayer();
        }
        try {
            if (uri.getScheme().equals("asset")) {
                try {
                    AssetFileDescriptor openFd = getContext().getAssets().openFd(uri.getPath().substring(1));
                    try {
                        mediaPlayer.setDataSource(openFd.getFileDescriptor(), openFd.getStartOffset(), openFd.getLength());
                        if (openFd != null) {
                            try {
                                openFd.close();
                            } catch (IOException e2) {
                                Log.w(f5689s, "Unable to close" + e2);
                            }
                        }
                    } catch (SecurityException e3) {
                        e = e3;
                        assetFileDescriptor = openFd;
                        try {
                            Log.w(f5689s, "Failed to open assets " + e);
                            setVideoState(C2336d.ERROR);
                            if (assetFileDescriptor != null) {
                                try {
                                    assetFileDescriptor.close();
                                } catch (IOException e22) {
                                    Log.w(f5689s, "Unable to close" + e22);
                                }
                            }
                            mediaPlayer.setLooping(false);
                            mediaPlayer.setOnBufferingUpdateListener(this);
                            mediaPlayer.setOnCompletionListener(this);
                            mediaPlayer.setOnErrorListener(this);
                            mediaPlayer.setOnInfoListener(this);
                            mediaPlayer.setOnPreparedListener(this);
                            mediaPlayer.setOnVideoSizeChangedListener(this);
                            mediaPlayer.setOnSeekCompleteListener(this);
                            mediaPlayer.prepareAsync();
                            this.f5693d = mediaPlayer;
                            setVideoState(C2336d.PREPARING);
                            setSurfaceTextureListener(this);
                            if (isAvailable()) {
                                onSurfaceTextureAvailable(getSurfaceTexture(), 0, 0);
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            if (assetFileDescriptor != null) {
                                try {
                                    assetFileDescriptor.close();
                                } catch (IOException e4) {
                                    Log.w(f5689s, "Unable to close" + e4);
                                }
                            }
                            throw th;
                        }
                    } catch (IOException e5) {
                        e = e5;
                        assetFileDescriptor = openFd;
                        Log.w(f5689s, "Failed to open assets " + e);
                        setVideoState(C2336d.ERROR);
                        if (assetFileDescriptor != null) {
                            assetFileDescriptor.close();
                        }
                        mediaPlayer.setLooping(false);
                        mediaPlayer.setOnBufferingUpdateListener(this);
                        mediaPlayer.setOnCompletionListener(this);
                        mediaPlayer.setOnErrorListener(this);
                        mediaPlayer.setOnInfoListener(this);
                        mediaPlayer.setOnPreparedListener(this);
                        mediaPlayer.setOnVideoSizeChangedListener(this);
                        mediaPlayer.setOnSeekCompleteListener(this);
                        mediaPlayer.prepareAsync();
                        this.f5693d = mediaPlayer;
                        setVideoState(C2336d.PREPARING);
                        setSurfaceTextureListener(this);
                        if (isAvailable()) {
                            onSurfaceTextureAvailable(getSurfaceTexture(), 0, 0);
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        assetFileDescriptor = openFd;
                        if (assetFileDescriptor != null) {
                            assetFileDescriptor.close();
                        }
                        throw th;
                    }
                } catch (SecurityException e6) {
                    e = e6;
                    Log.w(f5689s, "Failed to open assets " + e);
                    setVideoState(C2336d.ERROR);
                    if (assetFileDescriptor != null) {
                        assetFileDescriptor.close();
                    }
                    mediaPlayer.setLooping(false);
                    mediaPlayer.setOnBufferingUpdateListener(this);
                    mediaPlayer.setOnCompletionListener(this);
                    mediaPlayer.setOnErrorListener(this);
                    mediaPlayer.setOnInfoListener(this);
                    mediaPlayer.setOnPreparedListener(this);
                    mediaPlayer.setOnVideoSizeChangedListener(this);
                    mediaPlayer.setOnSeekCompleteListener(this);
                    mediaPlayer.prepareAsync();
                    this.f5693d = mediaPlayer;
                    setVideoState(C2336d.PREPARING);
                    setSurfaceTextureListener(this);
                    if (isAvailable()) {
                        onSurfaceTextureAvailable(getSurfaceTexture(), 0, 0);
                    }
                } catch (IOException e7) {
                    e = e7;
                    Log.w(f5689s, "Failed to open assets " + e);
                    setVideoState(C2336d.ERROR);
                    if (assetFileDescriptor != null) {
                        assetFileDescriptor.close();
                    }
                    mediaPlayer.setLooping(false);
                    mediaPlayer.setOnBufferingUpdateListener(this);
                    mediaPlayer.setOnCompletionListener(this);
                    mediaPlayer.setOnErrorListener(this);
                    mediaPlayer.setOnInfoListener(this);
                    mediaPlayer.setOnPreparedListener(this);
                    mediaPlayer.setOnVideoSizeChangedListener(this);
                    mediaPlayer.setOnSeekCompleteListener(this);
                    mediaPlayer.prepareAsync();
                    this.f5693d = mediaPlayer;
                    setVideoState(C2336d.PREPARING);
                    setSurfaceTextureListener(this);
                    if (isAvailable()) {
                        onSurfaceTextureAvailable(getSurfaceTexture(), 0, 0);
                    }
                }
            }
            mediaPlayer.setDataSource(uri.toString());
            mediaPlayer.setLooping(false);
            mediaPlayer.setOnBufferingUpdateListener(this);
            mediaPlayer.setOnCompletionListener(this);
            mediaPlayer.setOnErrorListener(this);
            mediaPlayer.setOnInfoListener(this);
            mediaPlayer.setOnPreparedListener(this);
            mediaPlayer.setOnVideoSizeChangedListener(this);
            mediaPlayer.setOnSeekCompleteListener(this);
            mediaPlayer.prepareAsync();
            this.f5693d = mediaPlayer;
            setVideoState(C2336d.PREPARING);
        } catch (Exception e8) {
            setVideoState(C2336d.ERROR);
            mediaPlayer.release();
            Log.e(f5689s, "Cannot prepare media player with SurfaceTexture: " + e8);
        }
        setSurfaceTextureListener(this);
        if (isAvailable()) {
            onSurfaceTextureAvailable(getSurfaceTexture(), 0, 0);
        }
    }
}
