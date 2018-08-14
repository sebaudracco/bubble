package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.SurfaceTexture;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnBufferingUpdateListener;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnInfoListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.media.MediaPlayer.OnVideoSizeChangedListener;
import android.net.Uri;
import android.os.Build.VERSION;
import android.view.Surface;
import android.view.TextureView.SurfaceTextureListener;
import android.view.View.MeasureSpec;
import com.google.android.gms.ads.internal.zzbv;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@zzadh
@TargetApi(14)
public final class zzaov extends zzapg implements OnBufferingUpdateListener, OnCompletionListener, OnErrorListener, OnInfoListener, OnPreparedListener, OnVideoSizeChangedListener, SurfaceTextureListener {
    private static final Map<Integer, String> zzcwo = new HashMap();
    private final zzapx zzcwp;
    private final boolean zzcwq;
    private int zzcwr = 0;
    private int zzcws = 0;
    private MediaPlayer zzcwt;
    private Uri zzcwu;
    private int zzcwv;
    private int zzcww;
    private int zzcwx;
    private int zzcwy;
    private int zzcwz;
    private zzapu zzcxa;
    private boolean zzcxb;
    private int zzcxc;
    private zzapf zzcxd;

    static {
        if (VERSION.SDK_INT >= 17) {
            zzcwo.put(Integer.valueOf(-1004), "MEDIA_ERROR_IO");
            zzcwo.put(Integer.valueOf(-1007), "MEDIA_ERROR_MALFORMED");
            zzcwo.put(Integer.valueOf(-1010), "MEDIA_ERROR_UNSUPPORTED");
            zzcwo.put(Integer.valueOf(-110), "MEDIA_ERROR_TIMED_OUT");
            zzcwo.put(Integer.valueOf(3), "MEDIA_INFO_VIDEO_RENDERING_START");
        }
        zzcwo.put(Integer.valueOf(100), "MEDIA_ERROR_SERVER_DIED");
        zzcwo.put(Integer.valueOf(1), "MEDIA_ERROR_UNKNOWN");
        zzcwo.put(Integer.valueOf(1), "MEDIA_INFO_UNKNOWN");
        zzcwo.put(Integer.valueOf(700), "MEDIA_INFO_VIDEO_TRACK_LAGGING");
        zzcwo.put(Integer.valueOf(701), "MEDIA_INFO_BUFFERING_START");
        zzcwo.put(Integer.valueOf(702), "MEDIA_INFO_BUFFERING_END");
        zzcwo.put(Integer.valueOf(800), "MEDIA_INFO_BAD_INTERLEAVING");
        zzcwo.put(Integer.valueOf(801), "MEDIA_INFO_NOT_SEEKABLE");
        zzcwo.put(Integer.valueOf(802), "MEDIA_INFO_METADATA_UPDATE");
        if (VERSION.SDK_INT >= 19) {
            zzcwo.put(Integer.valueOf(901), "MEDIA_INFO_UNSUPPORTED_SUBTITLE");
            zzcwo.put(Integer.valueOf(902), "MEDIA_INFO_SUBTITLE_TIMED_OUT");
        }
    }

    public zzaov(Context context, boolean z, boolean z2, zzapv com_google_android_gms_internal_ads_zzapv, zzapx com_google_android_gms_internal_ads_zzapx) {
        super(context);
        setSurfaceTextureListener(this);
        this.zzcwp = com_google_android_gms_internal_ads_zzapx;
        this.zzcxb = z;
        this.zzcwq = z2;
        this.zzcwp.zzb(this);
    }

    private final void zza(float f) {
        if (this.zzcwt != null) {
            try {
                this.zzcwt.setVolume(f, f);
                return;
            } catch (IllegalStateException e) {
                return;
            }
        }
        zzane.zzdk("AdMediaPlayerView setMediaPlayerVolume() called before onPrepared().");
    }

    private final void zzag(int i) {
        if (i == 3) {
            this.zzcwp.zztt();
            this.zzcxl.zztt();
        } else if (this.zzcwr == 3) {
            this.zzcwp.zztu();
            this.zzcxl.zztu();
        }
        this.zzcwr = i;
    }

    private final void zzag(boolean z) {
        zzakb.m3428v("AdMediaPlayerView release");
        if (this.zzcxa != null) {
            this.zzcxa.zzti();
            this.zzcxa = null;
        }
        if (this.zzcwt != null) {
            this.zzcwt.reset();
            this.zzcwt.release();
            this.zzcwt = null;
            zzag(0);
            if (z) {
                this.zzcws = 0;
                this.zzcws = 0;
            }
        }
    }

    private final void zzsq() {
        Throwable e;
        String valueOf;
        zzakb.m3428v("AdMediaPlayerView init MediaPlayer");
        SurfaceTexture surfaceTexture = getSurfaceTexture();
        if (this.zzcwu != null && surfaceTexture != null) {
            zzag(false);
            try {
                SurfaceTexture zztj;
                zzbv.zzfb();
                this.zzcwt = new MediaPlayer();
                this.zzcwt.setOnBufferingUpdateListener(this);
                this.zzcwt.setOnCompletionListener(this);
                this.zzcwt.setOnErrorListener(this);
                this.zzcwt.setOnInfoListener(this);
                this.zzcwt.setOnPreparedListener(this);
                this.zzcwt.setOnVideoSizeChangedListener(this);
                this.zzcwx = 0;
                if (this.zzcxb) {
                    this.zzcxa = new zzapu(getContext());
                    this.zzcxa.zza(surfaceTexture, getWidth(), getHeight());
                    this.zzcxa.start();
                    zztj = this.zzcxa.zztj();
                    if (zztj == null) {
                        this.zzcxa.zzti();
                        this.zzcxa = null;
                    }
                    this.zzcwt.setDataSource(getContext(), this.zzcwu);
                    zzbv.zzfc();
                    this.zzcwt.setSurface(new Surface(zztj));
                    this.zzcwt.setAudioStreamType(3);
                    this.zzcwt.setScreenOnWhilePlaying(true);
                    this.zzcwt.prepareAsync();
                    zzag(1);
                }
                zztj = surfaceTexture;
                this.zzcwt.setDataSource(getContext(), this.zzcwu);
                zzbv.zzfc();
                this.zzcwt.setSurface(new Surface(zztj));
                this.zzcwt.setAudioStreamType(3);
                this.zzcwt.setScreenOnWhilePlaying(true);
                this.zzcwt.prepareAsync();
                zzag(1);
            } catch (IOException e2) {
                e = e2;
                valueOf = String.valueOf(this.zzcwu);
                zzane.zzc(new StringBuilder(String.valueOf(valueOf).length() + 36).append("Failed to initialize MediaPlayer at ").append(valueOf).toString(), e);
                onError(this.zzcwt, 1, 0);
            } catch (IllegalArgumentException e3) {
                e = e3;
                valueOf = String.valueOf(this.zzcwu);
                zzane.zzc(new StringBuilder(String.valueOf(valueOf).length() + 36).append("Failed to initialize MediaPlayer at ").append(valueOf).toString(), e);
                onError(this.zzcwt, 1, 0);
            } catch (IllegalStateException e4) {
                e = e4;
                valueOf = String.valueOf(this.zzcwu);
                zzane.zzc(new StringBuilder(String.valueOf(valueOf).length() + 36).append("Failed to initialize MediaPlayer at ").append(valueOf).toString(), e);
                onError(this.zzcwt, 1, 0);
            }
        }
    }

    private final void zzsr() {
        if (this.zzcwq && zzss() && this.zzcwt.getCurrentPosition() > 0 && this.zzcws != 3) {
            zzakb.m3428v("AdMediaPlayerView nudging MediaPlayer");
            zza(0.0f);
            this.zzcwt.start();
            int currentPosition = this.zzcwt.getCurrentPosition();
            long currentTimeMillis = zzbv.zzer().currentTimeMillis();
            while (zzss() && this.zzcwt.getCurrentPosition() == currentPosition) {
                if (zzbv.zzer().currentTimeMillis() - currentTimeMillis > 250) {
                    break;
                }
            }
            this.zzcwt.pause();
            zzst();
        }
    }

    private final boolean zzss() {
        return (this.zzcwt == null || this.zzcwr == -1 || this.zzcwr == 0 || this.zzcwr == 1) ? false : true;
    }

    public final int getCurrentPosition() {
        return zzss() ? this.zzcwt.getCurrentPosition() : 0;
    }

    public final int getDuration() {
        return zzss() ? this.zzcwt.getDuration() : -1;
    }

    public final int getVideoHeight() {
        return this.zzcwt != null ? this.zzcwt.getVideoHeight() : 0;
    }

    public final int getVideoWidth() {
        return this.zzcwt != null ? this.zzcwt.getVideoWidth() : 0;
    }

    public final void onBufferingUpdate(MediaPlayer mediaPlayer, int i) {
        this.zzcwx = i;
    }

    public final void onCompletion(MediaPlayer mediaPlayer) {
        zzakb.m3428v("AdMediaPlayerView completion");
        zzag(5);
        this.zzcws = 5;
        zzakk.zzcrm.post(new zzaoy(this));
    }

    public final boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
        String str = (String) zzcwo.get(Integer.valueOf(i));
        String str2 = (String) zzcwo.get(Integer.valueOf(i2));
        zzane.zzdk(new StringBuilder((String.valueOf(str).length() + 38) + String.valueOf(str2).length()).append("AdMediaPlayerView MediaPlayer error: ").append(str).append(":").append(str2).toString());
        zzag(-1);
        this.zzcws = -1;
        zzakk.zzcrm.post(new zzaoz(this, str, str2));
        return true;
    }

    public final boolean onInfo(MediaPlayer mediaPlayer, int i, int i2) {
        String str = (String) zzcwo.get(Integer.valueOf(i));
        String str2 = (String) zzcwo.get(Integer.valueOf(i2));
        zzakb.m3428v(new StringBuilder((String.valueOf(str).length() + 37) + String.valueOf(str2).length()).append("AdMediaPlayerView MediaPlayer info: ").append(str).append(":").append(str2).toString());
        return true;
    }

    protected final void onMeasure(int i, int i2) {
        int defaultSize = getDefaultSize(this.zzcwv, i);
        int defaultSize2 = getDefaultSize(this.zzcww, i2);
        if (this.zzcwv > 0 && this.zzcww > 0 && this.zzcxa == null) {
            int mode = MeasureSpec.getMode(i);
            int size = MeasureSpec.getSize(i);
            int mode2 = MeasureSpec.getMode(i2);
            defaultSize2 = MeasureSpec.getSize(i2);
            if (mode == 1073741824 && mode2 == 1073741824) {
                if (this.zzcwv * defaultSize2 < this.zzcww * size) {
                    defaultSize = (this.zzcwv * defaultSize2) / this.zzcww;
                } else if (this.zzcwv * defaultSize2 > this.zzcww * size) {
                    defaultSize2 = (this.zzcww * size) / this.zzcwv;
                    defaultSize = size;
                } else {
                    defaultSize = size;
                }
            } else if (mode == 1073741824) {
                defaultSize = (this.zzcww * size) / this.zzcwv;
                if (mode2 != Integer.MIN_VALUE || defaultSize <= defaultSize2) {
                    defaultSize2 = defaultSize;
                    defaultSize = size;
                } else {
                    defaultSize = size;
                }
            } else if (mode2 == 1073741824) {
                defaultSize = (this.zzcwv * defaultSize2) / this.zzcww;
                if (mode == Integer.MIN_VALUE && defaultSize > size) {
                    defaultSize = size;
                }
            } else {
                int i3 = this.zzcwv;
                defaultSize = this.zzcww;
                if (mode2 != Integer.MIN_VALUE || defaultSize <= defaultSize2) {
                    defaultSize2 = defaultSize;
                    defaultSize = i3;
                } else {
                    defaultSize = (this.zzcwv * defaultSize2) / this.zzcww;
                }
                if (mode == Integer.MIN_VALUE && r1 > size) {
                    defaultSize2 = (this.zzcww * size) / this.zzcwv;
                    defaultSize = size;
                }
            }
        }
        setMeasuredDimension(defaultSize, defaultSize2);
        if (this.zzcxa != null) {
            this.zzcxa.zzh(defaultSize, defaultSize2);
        }
        if (VERSION.SDK_INT == 16) {
            if ((this.zzcwy > 0 && this.zzcwy != defaultSize) || (this.zzcwz > 0 && this.zzcwz != defaultSize2)) {
                zzsr();
            }
            this.zzcwy = defaultSize;
            this.zzcwz = defaultSize2;
        }
    }

    public final void onPrepared(MediaPlayer mediaPlayer) {
        zzakb.m3428v("AdMediaPlayerView prepared");
        zzag(2);
        this.zzcwp.zzsv();
        zzakk.zzcrm.post(new zzaox(this));
        this.zzcwv = mediaPlayer.getVideoWidth();
        this.zzcww = mediaPlayer.getVideoHeight();
        if (this.zzcxc != 0) {
            seekTo(this.zzcxc);
        }
        zzsr();
        int i = this.zzcwv;
        zzane.zzdj("AdMediaPlayerView stream dimensions: " + i + " x " + this.zzcww);
        if (this.zzcws == 3) {
            play();
        }
        zzst();
    }

    public final void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
        zzakb.m3428v("AdMediaPlayerView surface created");
        zzsq();
        zzakk.zzcrm.post(new zzapa(this));
    }

    public final boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        zzakb.m3428v("AdMediaPlayerView surface destroyed");
        if (this.zzcwt != null && this.zzcxc == 0) {
            this.zzcxc = this.zzcwt.getCurrentPosition();
        }
        if (this.zzcxa != null) {
            this.zzcxa.zzti();
        }
        zzakk.zzcrm.post(new zzapc(this));
        zzag(true);
        return true;
    }

    public final void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
        Object obj = 1;
        zzakb.m3428v("AdMediaPlayerView surface changed");
        Object obj2 = this.zzcws == 3 ? 1 : null;
        if (!(this.zzcwv == i && this.zzcww == i2)) {
            obj = null;
        }
        if (!(this.zzcwt == null || obj2 == null || r1 == null)) {
            if (this.zzcxc != 0) {
                seekTo(this.zzcxc);
            }
            play();
        }
        if (this.zzcxa != null) {
            this.zzcxa.zzh(i, i2);
        }
        zzakk.zzcrm.post(new zzapb(this, i, i2));
    }

    public final void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
        this.zzcwp.zzc(this);
        this.zzcxk.zza(surfaceTexture, this.zzcxd);
    }

    public final void onVideoSizeChanged(MediaPlayer mediaPlayer, int i, int i2) {
        zzakb.m3428v("AdMediaPlayerView size changed: " + i + " x " + i2);
        this.zzcwv = mediaPlayer.getVideoWidth();
        this.zzcww = mediaPlayer.getVideoHeight();
        if (this.zzcwv != 0 && this.zzcww != 0) {
            requestLayout();
        }
    }

    protected final void onWindowVisibilityChanged(int i) {
        zzakb.m3428v("AdMediaPlayerView window visibility changed to " + i);
        zzakk.zzcrm.post(new zzaow(this, i));
        super.onWindowVisibilityChanged(i);
    }

    public final void pause() {
        zzakb.m3428v("AdMediaPlayerView pause");
        if (zzss() && this.zzcwt.isPlaying()) {
            this.zzcwt.pause();
            zzag(4);
            zzakk.zzcrm.post(new zzape(this));
        }
        this.zzcws = 4;
    }

    public final void play() {
        zzakb.m3428v("AdMediaPlayerView play");
        if (zzss()) {
            this.zzcwt.start();
            zzag(3);
            this.zzcxk.zzsw();
            zzakk.zzcrm.post(new zzapd(this));
        }
        this.zzcws = 3;
    }

    public final void seekTo(int i) {
        zzakb.m3428v("AdMediaPlayerView seek " + i);
        if (zzss()) {
            this.zzcwt.seekTo(i);
            this.zzcxc = 0;
            return;
        }
        this.zzcxc = i;
    }

    public final void setVideoPath(String str) {
        Uri parse = Uri.parse(str);
        zzhl zzd = zzhl.zzd(parse);
        if (zzd != null) {
            parse = Uri.parse(zzd.url);
        }
        this.zzcwu = parse;
        this.zzcxc = 0;
        zzsq();
        requestLayout();
        invalidate();
    }

    public final void stop() {
        zzakb.m3428v("AdMediaPlayerView stop");
        if (this.zzcwt != null) {
            this.zzcwt.stop();
            this.zzcwt.release();
            this.zzcwt = null;
            zzag(0);
            this.zzcws = 0;
        }
        this.zzcwp.onStop();
    }

    public final String toString() {
        String name = getClass().getName();
        String toHexString = Integer.toHexString(hashCode());
        return new StringBuilder((String.valueOf(name).length() + 1) + String.valueOf(toHexString).length()).append(name).append("@").append(toHexString).toString();
    }

    public final void zza(float f, float f2) {
        if (this.zzcxa != null) {
            this.zzcxa.zzb(f, f2);
        }
    }

    public final void zza(zzapf com_google_android_gms_internal_ads_zzapf) {
        this.zzcxd = com_google_android_gms_internal_ads_zzapf;
    }

    final /* synthetic */ void zzah(int i) {
        if (this.zzcxd != null) {
            this.zzcxd.onWindowVisibilityChanged(i);
        }
    }

    public final String zzsp() {
        String str = "MediaPlayer";
        String valueOf = String.valueOf(this.zzcxb ? " spherical" : "");
        return valueOf.length() != 0 ? str.concat(valueOf) : new String(str);
    }

    public final void zzst() {
        zza(this.zzcxl.getVolume());
    }
}
