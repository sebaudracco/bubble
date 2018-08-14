package com.inmobi.ads;

import android.annotation.TargetApi;
import android.app.KeyguardManager;
import android.content.Context;
import android.graphics.SurfaceTexture;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnBufferingUpdateListener;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnInfoListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.media.MediaPlayer.OnVideoSizeChangedListener;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Message;
import android.os.PowerManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Surface;
import android.view.TextureView;
import android.view.TextureView.SurfaceTextureListener;
import android.view.View.MeasureSpec;
import android.widget.ImageView;
import android.widget.MediaController.MediaPlayerControl;
import android.widget.ProgressBar;
import com.inmobi.ads.AdContainer.RenderingProperties.PlacementType;
import com.inmobi.commons.core.p115d.C3132b;
import com.inmobi.commons.core.p115d.C3135c;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.Map;

@TargetApi(15)
public class NativeStrandVideoView extends TextureView implements MediaPlayerControl {
    private static final String f6823c = NativeStrandVideoView.class.getSimpleName();
    OnVideoSizeChangedListener f6824a = new C29391(this);
    OnPreparedListener f6825b = new C29402(this);
    private Uri f6826d;
    private Map<String, String> f6827e;
    private Surface f6828f = null;
    private af f6829g = null;
    private int f6830h;
    private int f6831i;
    private int f6832j;
    private int f6833k;
    private int f6834l = 0;
    private OnQuartileCompletedListener f6835m;
    private OnPlaybackEventListener f6836n;
    private C2946a f6837o;
    private C2947b f6838p;
    private NativeStrandVideoController f6839q;
    private int f6840r;
    private boolean f6841s;
    private boolean f6842t;
    private boolean f6843u;
    private OnCompletionListener f6844v = new C29413(this);
    private OnInfoListener f6845w = new C29424(this);
    private OnBufferingUpdateListener f6846x = new C29435(this);
    private OnErrorListener f6847y = new C29446(this);
    private final SurfaceTextureListener f6848z = new C29457(this);

    class C29391 implements OnVideoSizeChangedListener {
        final /* synthetic */ NativeStrandVideoView f6815a;

        C29391(NativeStrandVideoView nativeStrandVideoView) {
            this.f6815a = nativeStrandVideoView;
        }

        public void onVideoSizeChanged(MediaPlayer mediaPlayer, int i, int i2) {
            this.f6815a.f6831i = mediaPlayer.getVideoWidth();
            this.f6815a.f6832j = mediaPlayer.getVideoHeight();
            if (this.f6815a.f6831i != 0 && this.f6815a.f6832j != 0) {
                this.f6815a.requestLayout();
            }
        }
    }

    class C29402 implements OnPreparedListener {
        final /* synthetic */ NativeStrandVideoView f6816a;

        C29402(NativeStrandVideoView nativeStrandVideoView) {
            this.f6816a = nativeStrandVideoView;
        }

        public void onPrepared(MediaPlayer mediaPlayer) {
            if (this.f6816a.f6829g != null) {
                int i;
                this.f6816a.f6829g.m9281a(2);
                this.f6816a.f6841s = this.f6816a.f6842t = this.f6816a.f6843u = true;
                if (this.f6816a.f6839q != null) {
                    this.f6816a.f6839q.setEnabled(true);
                }
                this.f6816a.f6831i = mediaPlayer.getVideoWidth();
                this.f6816a.f6832j = mediaPlayer.getVideoHeight();
                aw awVar = (aw) this.f6816a.getTag();
                if (awVar != null && ((Boolean) awVar.m9027v().get("didCompleteQ4")).booleanValue()) {
                    this.f6816a.m8910a(8, 0);
                    if (((PlacementType) awVar.m9027v().get("placementType")) == PlacementType.PLACEMENT_TYPE_FULLSCREEN) {
                        return;
                    }
                }
                if (this.f6816a.getPlaybackEventListener() != null) {
                    this.f6816a.getPlaybackEventListener().mo6170a(PlaybackEvent.PLAYBACK_EVENT_PREPARED);
                }
                if (awVar == null || ((Boolean) awVar.m9027v().get("didCompleteQ4")).booleanValue()) {
                    i = 0;
                } else {
                    i = ((Integer) awVar.m9027v().get("seekPosition")).intValue();
                }
                if (this.f6816a.f6831i == 0 || this.f6816a.f6832j == 0) {
                    if (3 == this.f6816a.f6829g.m9284c()) {
                        this.f6816a.start();
                    }
                } else if (3 == this.f6816a.f6829g.m9284c()) {
                    this.f6816a.start();
                    if (this.f6816a.f6839q != null) {
                        this.f6816a.f6839q.m8879a();
                    }
                } else if (!this.f6816a.isPlaying()) {
                    if ((i != 0 || this.f6816a.getCurrentPosition() > 0) && this.f6816a.f6839q != null) {
                        this.f6816a.f6839q.m8880a(0);
                    }
                }
            }
        }
    }

    class C29413 implements OnCompletionListener {
        final /* synthetic */ NativeStrandVideoView f6817a;

        C29413(NativeStrandVideoView nativeStrandVideoView) {
            this.f6817a = nativeStrandVideoView;
        }

        public void onCompletion(MediaPlayer mediaPlayer) {
            try {
                this.f6817a.m8902g();
            } catch (Throwable e) {
                Logger.m10359a(InternalLogLevel.INTERNAL, NativeStrandVideoView.f6823c, "SDK encountered unexpected error in handling the media playback complete event; " + e.getMessage());
                C3135c.m10255a().m10279a(new C3132b(e));
            }
        }
    }

    class C29424 implements OnInfoListener {
        final /* synthetic */ NativeStrandVideoView f6818a;

        C29424(NativeStrandVideoView nativeStrandVideoView) {
            this.f6818a = nativeStrandVideoView;
        }

        @TargetApi(17)
        public boolean onInfo(MediaPlayer mediaPlayer, int i, int i2) {
            if (VERSION.SDK_INT >= 17 && 3 == i) {
                this.f6818a.m8910a(8, 8);
            }
            return true;
        }
    }

    class C29435 implements OnBufferingUpdateListener {
        final /* synthetic */ NativeStrandVideoView f6819a;

        C29435(NativeStrandVideoView nativeStrandVideoView) {
            this.f6819a = nativeStrandVideoView;
        }

        public void onBufferingUpdate(MediaPlayer mediaPlayer, int i) {
            this.f6819a.f6840r = i;
        }
    }

    class C29446 implements OnErrorListener {
        final /* synthetic */ NativeStrandVideoView f6820a;

        C29446(NativeStrandVideoView nativeStrandVideoView) {
            this.f6820a = nativeStrandVideoView;
        }

        public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
            Logger.m10359a(InternalLogLevel.INTERNAL, NativeStrandVideoView.f6823c, "Media Play Error " + i + "," + i2);
            if (this.f6820a.f6837o != null) {
                this.f6820a.f6837o.mo6171a(i);
            }
            if (this.f6820a.f6829g != null) {
                this.f6820a.f6829g.m9281a(-1);
                this.f6820a.f6829g.m9283b(-1);
            }
            if (this.f6820a.f6839q != null) {
                this.f6820a.f6839q.m8882c();
            }
            return true;
        }
    }

    class C29457 implements SurfaceTextureListener {
        final /* synthetic */ NativeStrandVideoView f6821a;

        C29457(NativeStrandVideoView nativeStrandVideoView) {
            this.f6821a = nativeStrandVideoView;
        }

        @TargetApi(16)
        public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
            this.f6821a.f6828f = new Surface(surfaceTexture);
            this.f6821a.m8905h();
        }

        public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
            Object obj = 1;
            Object obj2 = (this.f6821a.f6829g == null || this.f6821a.f6829g.m9284c() != 3) ? null : 1;
            if (i <= 0 || i2 <= 0) {
                obj = null;
            }
            if (this.f6821a.f6829g != null && obj2 != null && r0 != null) {
                if (this.f6821a.getTag() != null) {
                    int intValue = ((Integer) ((aw) this.f6821a.getTag()).m9027v().get("seekPosition")).intValue();
                    if (intValue != 0) {
                        this.f6821a.m8909a(intValue);
                    }
                }
                this.f6821a.start();
            }
        }

        public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
            if (this.f6821a.f6828f != null) {
                this.f6821a.f6828f.release();
                this.f6821a.f6828f = null;
            }
            if (this.f6821a.f6839q != null) {
                this.f6821a.f6839q.m8882c();
            }
            this.f6821a.m8912a(true);
            return true;
        }

        public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
        }
    }

    interface OnPlaybackEventListener {

        public enum PlaybackEvent {
            PLAYBACK_EVENT_PREPARED,
            PLAYBACK_EVENT_PLAY,
            PLAYBACK_EVENT_PAUSE,
            PLAYBACK_EVENT_RESUME,
            PLAYBACK_EVENT_STOP
        }

        void mo6170a(PlaybackEvent playbackEvent);
    }

    interface OnQuartileCompletedListener {

        public enum Quartile {
            Q1,
            Q2,
            Q3,
            Q4
        }

        void mo6169a(Quartile quartile);
    }

    interface C2946a {
        void mo6171a(int i);
    }

    static final class C2947b extends Handler {
        private final WeakReference<NativeStrandVideoView> f6822a;

        C2947b(NativeStrandVideoView nativeStrandVideoView) {
            this.f6822a = new WeakReference(nativeStrandVideoView);
        }

        public void handleMessage(Message message) {
            NativeStrandVideoView nativeStrandVideoView = (NativeStrandVideoView) this.f6822a.get();
            if (nativeStrandVideoView != null) {
                switch (message.what) {
                    case 1:
                        int duration = nativeStrandVideoView.getDuration();
                        int currentPosition = nativeStrandVideoView.getCurrentPosition();
                        if (!(duration == -1 || currentPosition == 0)) {
                            aw awVar = (aw) nativeStrandVideoView.getTag();
                            if (!((Boolean) awVar.m9027v().get("didCompleteQ1")).booleanValue() && (currentPosition * 4) - duration >= 0) {
                                awVar.m9027v().put("didCompleteQ1", Boolean.valueOf(true));
                                nativeStrandVideoView.getQuartileCompletedListener().mo6169a(Quartile.Q1);
                            }
                            if (!((Boolean) awVar.m9027v().get("didCompleteQ2")).booleanValue() && (currentPosition * 2) - duration >= 0) {
                                awVar.m9027v().put("didCompleteQ2", Boolean.valueOf(true));
                                nativeStrandVideoView.getQuartileCompletedListener().mo6169a(Quartile.Q2);
                            }
                            if (!((Boolean) awVar.m9027v().get("didCompleteQ3")).booleanValue() && (currentPosition * 4) - (duration * 3) >= 0) {
                                awVar.m9027v().put("didCompleteQ3", Boolean.valueOf(true));
                                nativeStrandVideoView.getQuartileCompletedListener().mo6169a(Quartile.Q3);
                            }
                        }
                        sendEmptyMessageDelayed(1, 1000);
                        break;
                }
            }
            super.handleMessage(message);
        }
    }

    private void m8902g() {
        if (this.f6829g != null) {
            this.f6829g.m9281a(5);
            this.f6829g.m9283b(5);
        }
        if (this.f6839q != null) {
            this.f6839q.m8882c();
        }
        if (this.f6838p != null) {
            this.f6838p.removeMessages(1);
        }
        if (getTag() != null) {
            aw awVar = (aw) getTag();
            if (!((Boolean) awVar.m9027v().get("didCompleteQ4")).booleanValue()) {
                awVar.m9027v().put("didCompleteQ4", Boolean.valueOf(true));
                if (getQuartileCompletedListener() != null) {
                    getQuartileCompletedListener().mo6169a(Quartile.Q4);
                }
            }
            awVar.m9027v().put("didSignalVideoCompleted", Boolean.valueOf(true));
            m8893b(awVar);
            if (awVar.m9439B()) {
                start();
            } else if (((Boolean) awVar.m9027v().get("isFullScreen")).booleanValue()) {
                m8910a(8, 0);
            }
        }
    }

    private void m8893b(aw awVar) {
        if (awVar != null) {
            awVar.m9027v().put("didCompleteQ1", Boolean.valueOf(false));
            awVar.m9027v().put("didCompleteQ2", Boolean.valueOf(false));
            awVar.m9027v().put("didCompleteQ3", Boolean.valueOf(false));
            awVar.m9027v().put("didPause", Boolean.valueOf(false));
            awVar.m9027v().put("didStartPlaying", Boolean.valueOf(false));
        }
    }

    public NativeStrandVideoView(Context context) {
        super(context);
        requestLayout();
        invalidate();
    }

    public void m8911a(@NonNull aw awVar) {
        af afVar;
        this.f6831i = 0;
        this.f6832j = 0;
        this.f6826d = Uri.parse(((bp) awVar.m9007d()).mo6219b());
        if (PlacementType.PLACEMENT_TYPE_FULLSCREEN == ((PlacementType) awVar.m9027v().get("placementType"))) {
            afVar = new af();
        } else {
            afVar = af.m9278a();
        }
        this.f6829g = afVar;
        if (this.f6830h != 0) {
            this.f6829g.setAudioSessionId(this.f6830h);
        } else {
            this.f6830h = this.f6829g.getAudioSessionId();
        }
        try {
            this.f6829g.setDataSource(getContext().getApplicationContext(), this.f6826d, this.f6827e);
            setTag(awVar);
            this.f6838p = new C2947b(this);
            setSurfaceTextureListener(this.f6848z);
            setFocusable(true);
            setFocusableInTouchMode(true);
            requestFocus();
        } catch (IOException e) {
            this.f6829g.m9281a(-1);
            this.f6829g.m9283b(-1);
        }
    }

    @NonNull
    public af getMediaPlayer() {
        return this.f6829g;
    }

    public void m8908a() {
        if (this.f6828f != null) {
            this.f6828f.release();
            this.f6828f = null;
        }
        m8912a(true);
    }

    public int getState() {
        if (this.f6829g != null) {
            return this.f6829g.m9282b();
        }
        return 0;
    }

    protected void onMeasure(int i, int i2) {
        try {
            int defaultSize = getDefaultSize(this.f6831i, i);
            int defaultSize2 = getDefaultSize(this.f6832j, i2);
            if (this.f6831i > 0 && this.f6832j > 0) {
                int mode = MeasureSpec.getMode(i);
                int size = MeasureSpec.getSize(i);
                int mode2 = MeasureSpec.getMode(i2);
                defaultSize2 = MeasureSpec.getSize(i2);
                if (mode == 1073741824 && mode2 == 1073741824) {
                    if (this.f6831i * defaultSize2 < this.f6832j * size) {
                        defaultSize2 = (this.f6832j * size) / this.f6831i;
                        defaultSize = size;
                    } else {
                        defaultSize = this.f6831i * defaultSize2 > this.f6832j * size ? (this.f6831i * defaultSize2) / this.f6832j : size;
                    }
                } else if (mode == 1073741824) {
                    defaultSize = (this.f6832j * size) / this.f6831i;
                    if (mode2 != Integer.MIN_VALUE || defaultSize <= defaultSize2) {
                        defaultSize2 = defaultSize;
                        defaultSize = size;
                    } else {
                        defaultSize = size;
                    }
                } else if (mode2 == 1073741824) {
                    defaultSize = (this.f6831i * defaultSize2) / this.f6832j;
                    if (mode == Integer.MIN_VALUE && defaultSize > size) {
                        defaultSize = size;
                    }
                } else {
                    int i3 = this.f6831i;
                    defaultSize = this.f6832j;
                    if (mode2 != Integer.MIN_VALUE || defaultSize <= defaultSize2) {
                        defaultSize2 = defaultSize;
                        defaultSize = i3;
                    } else {
                        defaultSize = (this.f6831i * defaultSize2) / this.f6832j;
                    }
                    if (mode == Integer.MIN_VALUE && r1 > size) {
                        defaultSize2 = (this.f6832j * size) / this.f6831i;
                        defaultSize = size;
                    }
                }
            }
            setMeasuredDimension(defaultSize, defaultSize2);
        } catch (Exception e) {
            Logger.m10359a(InternalLogLevel.INTERNAL, f6823c, "SDK encountered unexpected error in handling the onMeasure event; " + e.getMessage());
        }
    }

    @Nullable
    NativeStrandVideoController getMediaController() {
        return this.f6839q;
    }

    boolean m8913b() {
        if (this.f6829g == null || this.f6829g.m9282b() == -1 || this.f6829g.m9282b() == 0 || this.f6829g.m9282b() == 1) {
            return false;
        }
        return true;
    }

    @TargetApi(20)
    public void start() {
        aw awVar;
        int i;
        PowerManager powerManager = (PowerManager) getContext().getSystemService("power");
        boolean inKeyguardRestrictedInputMode = ((KeyguardManager) getContext().getSystemService("keyguard")).inKeyguardRestrictedInputMode();
        if (VERSION.SDK_INT >= 20 ? powerManager.isInteractive() : powerManager.isScreenOn()) {
            if (!inKeyguardRestrictedInputMode) {
                int i2 = 1;
                if (!(!m8913b() || r0 == 0 || this.f6829g.isPlaying())) {
                    awVar = (aw) getTag();
                    if (awVar != null || ((Boolean) awVar.m9027v().get("didCompleteQ4")).booleanValue()) {
                        i = 0;
                    } else {
                        i = ((Integer) awVar.m9027v().get("seekPosition")).intValue();
                    }
                    m8915d();
                    m8909a(i);
                    this.f6829g.start();
                    this.f6829g.m9281a(3);
                    m8910a(8, 8);
                    if (awVar != null) {
                        awVar.m9027v().put("didCompleteQ4", Boolean.valueOf(false));
                        if (awVar.m9448z()) {
                            m8916e();
                        }
                        if (((Boolean) awVar.m9027v().get("didPause")).booleanValue()) {
                            getPlaybackEventListener().mo6170a(PlaybackEvent.PLAYBACK_EVENT_PLAY);
                        } else {
                            getPlaybackEventListener().mo6170a(PlaybackEvent.PLAYBACK_EVENT_RESUME);
                            awVar.m9027v().put("didPause", Boolean.valueOf(false));
                        }
                        if (!(this.f6838p == null || this.f6838p.hasMessages(1))) {
                            this.f6838p.sendEmptyMessage(1);
                        }
                    }
                    if (this.f6839q != null) {
                        this.f6839q.m8879a();
                    }
                }
                if (this.f6829g != null) {
                    this.f6829g.m9283b(3);
                }
            }
        }
        boolean z = false;
        awVar = (aw) getTag();
        if (awVar != null) {
        }
        i = 0;
        m8915d();
        m8909a(i);
        this.f6829g.start();
        this.f6829g.m9281a(3);
        m8910a(8, 8);
        if (awVar != null) {
            awVar.m9027v().put("didCompleteQ4", Boolean.valueOf(false));
            if (awVar.m9448z()) {
                m8916e();
            }
            if (((Boolean) awVar.m9027v().get("didPause")).booleanValue()) {
                getPlaybackEventListener().mo6170a(PlaybackEvent.PLAYBACK_EVENT_PLAY);
            } else {
                getPlaybackEventListener().mo6170a(PlaybackEvent.PLAYBACK_EVENT_RESUME);
                awVar.m9027v().put("didPause", Boolean.valueOf(false));
            }
            this.f6838p.sendEmptyMessage(1);
        }
        if (this.f6839q != null) {
            this.f6839q.m8879a();
        }
        if (this.f6829g != null) {
            this.f6829g.m9283b(3);
        }
    }

    public void pause() {
        if (m8913b() && this.f6829g.isPlaying()) {
            if (getTag() != null) {
                aw awVar = (aw) getTag();
                awVar.m9027v().put("didPause", Boolean.valueOf(true));
                awVar.m9027v().put("seekPosition", Integer.valueOf(getCurrentPosition()));
            }
            this.f6829g.pause();
            this.f6829g.m9281a(4);
            getPlaybackEventListener().mo6170a(PlaybackEvent.PLAYBACK_EVENT_PAUSE);
        }
        if (this.f6829g != null) {
            this.f6829g.m9283b(4);
        }
    }

    public void m8914c() {
        if (m8913b() && this.f6829g.isPlaying()) {
            this.f6829g.pause();
            this.f6829g.seekTo(0);
            if (getTag() != null) {
                aw awVar = (aw) getTag();
                awVar.m9027v().put("didPause", Boolean.valueOf(true));
                awVar.m9027v().put("seekPosition", Integer.valueOf(0));
                awVar.m9027v().put("didCompleteQ4", Boolean.valueOf(true));
            }
            this.f6829g.m9281a(4);
            getPlaybackEventListener().mo6170a(PlaybackEvent.PLAYBACK_EVENT_STOP);
        }
        if (this.f6829g != null) {
            this.f6829g.m9283b(4);
        }
    }

    public int getDuration() {
        if (m8913b()) {
            return this.f6829g.getDuration();
        }
        return -1;
    }

    public int getCurrentPosition() {
        if (m8913b()) {
            return this.f6829g.getCurrentPosition();
        }
        return 0;
    }

    void m8909a(int i) {
        if (m8913b()) {
            this.f6829g.seekTo(i);
        }
    }

    public void seekTo(int i) {
    }

    public boolean isPlaying() {
        return m8913b() && this.f6829g.isPlaying();
    }

    public int getBufferPercentage() {
        if (this.f6829g != null) {
            return this.f6840r;
        }
        return 0;
    }

    public boolean canPause() {
        return this.f6841s;
    }

    public boolean canSeekBackward() {
        return this.f6842t;
    }

    public boolean canSeekForward() {
        return this.f6843u;
    }

    public int getAudioSessionId() {
        if (this.f6830h == 0) {
            MediaPlayer mediaPlayer = new MediaPlayer();
            this.f6830h = mediaPlayer.getAudioSessionId();
            mediaPlayer.release();
        }
        return this.f6830h;
    }

    public void setVideoPath(String str) {
        setVideoURI(Uri.parse(str));
    }

    public void setVideoURI(Uri uri) {
        setVideoURI(uri, null);
    }

    public void setVideoURI(Uri uri, Map<String, String> map) {
        this.f6826d = uri;
        this.f6827e = map;
        m8905h();
        requestLayout();
        invalidate();
    }

    private void m8905h() {
        if (this.f6826d != null && this.f6828f != null) {
            aw awVar;
            if (this.f6829g == null) {
                PlacementType placementType;
                af afVar;
                awVar = (aw) getTag();
                PlacementType placementType2 = PlacementType.PLACEMENT_TYPE_FULLSCREEN;
                if (awVar != null) {
                    placementType = (PlacementType) awVar.m9027v().get("placementType");
                } else {
                    placementType = placementType2;
                }
                if (PlacementType.PLACEMENT_TYPE_FULLSCREEN == placementType) {
                    afVar = new af();
                } else {
                    afVar = af.m9278a();
                }
                this.f6829g = afVar;
                if (this.f6830h != 0) {
                    this.f6829g.setAudioSessionId(this.f6830h);
                } else {
                    this.f6830h = this.f6829g.getAudioSessionId();
                }
                try {
                    this.f6829g.setDataSource(getContext().getApplicationContext(), this.f6826d, this.f6827e);
                } catch (IOException e) {
                    this.f6829g.m9281a(-1);
                    this.f6829g.m9283b(-1);
                    return;
                }
            }
            try {
                awVar = (aw) getTag();
                this.f6829g.setOnPreparedListener(this.f6825b);
                this.f6829g.setOnVideoSizeChangedListener(this.f6824a);
                this.f6829g.setOnCompletionListener(this.f6844v);
                this.f6829g.setOnErrorListener(this.f6847y);
                this.f6829g.setOnInfoListener(this.f6845w);
                this.f6829g.setOnBufferingUpdateListener(this.f6846x);
                this.f6829g.setSurface(this.f6828f);
                this.f6829g.setAudioStreamType(3);
                this.f6829g.prepareAsync();
                this.f6840r = 0;
                this.f6829g.m9281a(1);
                m8907j();
                if (awVar != null) {
                    if (((Boolean) awVar.m9027v().get("shouldAutoPlay")).booleanValue()) {
                        this.f6829g.m9283b(3);
                    }
                    if (((Boolean) awVar.m9027v().get("didCompleteQ4")).booleanValue()) {
                        m8910a(8, 0);
                        return;
                    }
                }
                m8910a(0, 0);
            } catch (Throwable e2) {
                this.f6829g.m9281a(-1);
                this.f6829g.m9283b(-1);
                this.f6847y.onError(this.f6829g, 1, 0);
                C3135c.m10255a().m10279a(new C3132b(e2));
            }
        }
    }

    void m8912a(boolean z) {
        if (this.f6829g != null) {
            if (this.f6838p != null) {
                this.f6838p.removeMessages(1);
            }
            if (getTag() != null) {
                ((aw) getTag()).m9027v().put("seekPosition", Integer.valueOf(getCurrentPosition()));
            }
            this.f6829g.m9281a(0);
            if (z) {
                this.f6829g.m9283b(0);
            }
            this.f6829g.reset();
            m8906i();
            if (getTag() != null) {
                if (PlacementType.PLACEMENT_TYPE_INLINE == ((aw) getTag()).m9027v().get("placementType")) {
                    this.f6829g.m9285d();
                }
            } else {
                this.f6829g.m9285d();
            }
            ((AudioManager) getContext().getSystemService("audio")).abandonAudioFocus(null);
            Logger.m10359a(InternalLogLevel.INTERNAL, f6823c, "Media Player released");
            this.f6829g = null;
        }
    }

    private void m8906i() {
        this.f6829g.setOnPreparedListener(null);
        this.f6829g.setOnVideoSizeChangedListener(null);
        this.f6829g.setOnCompletionListener(null);
        this.f6829g.setOnErrorListener(null);
        this.f6829g.setOnInfoListener(null);
        this.f6829g.setOnBufferingUpdateListener(null);
    }

    public void m8915d() {
        if (this.f6829g != null) {
            this.f6833k = 0;
            this.f6829g.setVolume(0.0f, 0.0f);
            if (getTag() != null) {
                ((aw) getTag()).m9027v().put("currentMediaVolume", Integer.valueOf(0));
            }
        }
    }

    public void m8916e() {
        if (this.f6829g != null) {
            this.f6833k = 1;
            this.f6829g.setVolume(1.0f, 1.0f);
            if (getTag() != null) {
                ((aw) getTag()).m9027v().put("currentMediaVolume", Integer.valueOf(15));
            }
        }
    }

    public int getVolume() {
        if (m8913b()) {
            return this.f6833k;
        }
        return -1;
    }

    private void m8907j() {
        if (this.f6829g != null && this.f6839q != null) {
            this.f6839q.setMediaPlayer(this);
            this.f6839q.setEnabled(m8913b());
            this.f6839q.m8879a();
        }
    }

    void m8910a(int i, int i2) {
        if (this.f6829g != null) {
            ProgressBar progressBar = ((NativeStrandVideoWrapper) getParent()).getProgressBar();
            ImageView poster = ((NativeStrandVideoWrapper) getParent()).getPoster();
            progressBar.setVisibility(i);
            poster.setVisibility(i2);
        }
    }

    public void setMediaController(NativeStrandVideoController nativeStrandVideoController) {
        if (nativeStrandVideoController != null) {
            this.f6839q = nativeStrandVideoController;
            m8907j();
        }
    }

    public OnQuartileCompletedListener getQuartileCompletedListener() {
        return this.f6835m;
    }

    public void setQuartileCompletedListener(OnQuartileCompletedListener onQuartileCompletedListener) {
        this.f6835m = onQuartileCompletedListener;
    }

    public OnPlaybackEventListener getPlaybackEventListener() {
        return this.f6836n;
    }

    public void setPlaybackEventListener(OnPlaybackEventListener onPlaybackEventListener) {
        this.f6836n = onPlaybackEventListener;
    }

    public void setMediaErrorListener(C2946a c2946a) {
        this.f6837o = c2946a;
    }
}
