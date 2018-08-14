package com.facebook.ads;

import android.annotation.TargetApi;
import android.content.Context;
import android.net.Uri;
import android.support.annotation.FloatRange;
import android.support.annotation.IntRange;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import com.facebook.ads.internal.j.f;
import com.facebook.ads.internal.q.c.d.a;
import com.facebook.ads.internal.view.e.b.c;
import com.facebook.ads.internal.view.e.b.e;
import com.facebook.ads.internal.view.e.b.i;
import com.facebook.ads.internal.view.e.b.k;
import com.facebook.ads.internal.view.e.b.m;
import com.facebook.ads.internal.view.e.b.q;
import com.facebook.ads.internal.view.e.b.w;
import com.facebook.ads.internal.view.e.d.d;
import com.facebook.ads.internal.view.n;
import com.facebook.ads.internal.view.o;

public abstract class MediaViewVideoRenderer extends FrameLayout {
    private static final String f2559d = MediaViewVideoRenderer.class.getSimpleName();
    @Nullable
    protected NativeAd f2560a;
    protected VideoAutoplayBehavior f2561b;
    final n f2562c;
    private final m f2563e = new 1(this);
    private final k f2564f = new 2(this);
    private final i f2565g = new 3(this);
    private final q f2566h = new 4(this);
    private final c f2567i = new 5(this);
    private final w f2568j = new 6(this);
    private final e f2569k = new 7(this);
    private boolean f2570l;
    private boolean f2571m;
    private boolean f2572n = true;
    private boolean f2573o = true;

    public MediaViewVideoRenderer(Context context) {
        super(context);
        this.f2562c = new n(context);
        m3335a();
    }

    public MediaViewVideoRenderer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f2562c = new n(context, attributeSet);
        m3335a();
    }

    public MediaViewVideoRenderer(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f2562c = new n(context, attributeSet, i);
        m3335a();
    }

    @TargetApi(21)
    public MediaViewVideoRenderer(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.f2562c = new n(context, attributeSet, i, i2);
        m3335a();
    }

    private void m3335a() {
        this.f2562c.setEnableBackgroundVideo(shouldAllowBackgroundPlayback());
        this.f2562c.setLayoutParams(new LayoutParams(-1, -1));
        addView(this.f2562c);
        com.facebook.ads.internal.q.a.i.a(this.f2562c, com.facebook.ads.internal.q.a.i.n);
        this.f2562c.getEventBus().a(new f[]{this.f2563e, this.f2564f, this.f2565g, this.f2566h, this.f2567i, this.f2568j, this.f2569k});
    }

    public void destroy() {
        this.f2562c.k();
    }

    public final void disengageSeek(VideoStartReason videoStartReason) {
        if (this.f2570l) {
            this.f2570l = false;
            if (this.f2571m) {
                this.f2562c.a(videoStartReason.a());
            }
            onSeekDisengaged();
            return;
        }
        Log.w(f2559d, "disengageSeek called without engageSeek.");
    }

    public final void engageSeek() {
        if (this.f2570l) {
            Log.w(f2559d, "engageSeek called without disengageSeek.");
            return;
        }
        this.f2570l = true;
        this.f2571m = d.d.equals(this.f2562c.getState());
        this.f2562c.a(false);
        onSeekEngaged();
    }

    @IntRange(from = 0)
    public final int getCurrentTimeMs() {
        return this.f2562c.getCurrentPosition();
    }

    @IntRange(from = 0)
    public final int getDuration() {
        return this.f2562c.getDuration();
    }

    @FloatRange(from = 0.0d, to = 0.0d)
    public final float getVolume() {
        return this.f2562c.getVolume();
    }

    public void onCompleted() {
    }

    public void onError() {
    }

    public void onPaused() {
    }

    public void onPlayed() {
    }

    public void onPrepared() {
    }

    public void onSeek() {
    }

    public void onSeekDisengaged() {
    }

    public void onSeekEngaged() {
    }

    public void onVolumeChanged() {
    }

    public final void pause(boolean z) {
        this.f2562c.a(z);
    }

    public final void play(VideoStartReason videoStartReason) {
        this.f2562c.a(videoStartReason.a());
    }

    public final void seekTo(@IntRange(from = 0) int i) {
        if (this.f2570l) {
            this.f2562c.a(i);
        } else {
            Log.w(f2559d, "Seeking must be preceded by a call to engageSeek, and followed by a call to disengageSeek.");
        }
    }

    final void setAdEventManager(com.facebook.ads.internal.m.c cVar) {
        this.f2562c.setAdEventManager(cVar);
    }

    @Deprecated
    void setAutoplay(boolean z) {
        this.f2572n = z;
    }

    @Deprecated
    void setAutoplayOnMobile(boolean z) {
        this.f2573o = z;
    }

    final void setListener(o oVar) {
        this.f2562c.setListener(oVar);
    }

    public void setNativeAd(NativeAd nativeAd) {
        this.f2560a = nativeAd;
        this.f2562c.a(nativeAd.m3342d(), nativeAd.m3345g());
        this.f2562c.setVideoMPD(nativeAd.m3341c());
        this.f2562c.setVideoURI(nativeAd.m3340b());
        this.f2562c.setVideoCTA(nativeAd.getAdCallToAction());
        this.f2562c.setNativeAd(nativeAd);
        this.f2561b = nativeAd.m3343e();
    }

    public final void setVolume(@FloatRange(from = 0.0d, to = 1.0d) float f) {
        this.f2562c.setVolume(f);
    }

    public boolean shouldAllowBackgroundPlayback() {
        return false;
    }

    public boolean shouldAutoplay() {
        return (this.f2562c == null || this.f2562c.getState() == d.g) ? false : this.f2561b == VideoAutoplayBehavior.DEFAULT ? this.f2572n && (this.f2573o || com.facebook.ads.internal.q.c.d.c(getContext()) == a.c) : this.f2561b == VideoAutoplayBehavior.ON;
    }

    public void unsetNativeAd() {
        pause(false);
        this.f2562c.a(null, null);
        this.f2562c.setVideoMPD(null);
        this.f2562c.setVideoURI((Uri) null);
        this.f2562c.setVideoCTA(null);
        this.f2562c.setNativeAd(null);
        this.f2561b = VideoAutoplayBehavior.DEFAULT;
        this.f2560a = null;
    }
}
