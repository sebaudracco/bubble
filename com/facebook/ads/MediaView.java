package com.facebook.ads;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.os.Build.VERSION;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;
import com.facebook.ads.internal.l.a;
import com.facebook.ads.internal.m.c;
import com.facebook.ads.internal.m.d;
import com.facebook.ads.internal.q.a.i;
import com.facebook.ads.internal.q.a.v;
import com.facebook.ads.internal.view.b.b;
import com.facebook.ads.internal.view.e;

public class MediaView extends RelativeLayout {
    private static final String f2551a = MediaView.class.getSimpleName();
    private static final int f2552b = Color.argb(51, 145, 150, 165);
    private b f2553c;
    private com.facebook.ads.internal.view.hscroll.b f2554d;
    private MediaViewVideoRenderer f2555e;
    @Nullable
    private MediaViewListener f2556f;
    private boolean f2557g;
    @Deprecated
    private boolean f2558h = true;

    public MediaView(Context context) {
        super(context);
        setImageRenderer(new b(context));
        setCarouselRenderer(new com.facebook.ads.internal.view.hscroll.b(context));
        setVideoRenderer(new e(context));
        m3332a();
    }

    public MediaView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setImageRenderer(new b(context, attributeSet));
        setCarouselRenderer(new com.facebook.ads.internal.view.hscroll.b(context, attributeSet));
        setVideoRenderer(new e(context, attributeSet));
        m3332a();
    }

    public MediaView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        setImageRenderer(new b(context, attributeSet, i));
        setCarouselRenderer(new com.facebook.ads.internal.view.hscroll.b(context, attributeSet, i));
        setVideoRenderer(new e(context, attributeSet, i));
        m3332a();
    }

    @TargetApi(21)
    public MediaView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        setImageRenderer(new b(context, attributeSet, i, i2));
        setCarouselRenderer(new com.facebook.ads.internal.view.hscroll.b(context, attributeSet, i));
        setVideoRenderer(new e(context, attributeSet, i, i2));
        m3332a();
    }

    private void m3332a() {
        setBackgroundColor(f2552b);
        i.a(this, i.n);
        i.a(this.f2553c, i.n);
        i.a(this.f2555e, i.n);
        i.a(this.f2554d, i.n);
    }

    private boolean m3333a(NativeAd nativeAd) {
        return VERSION.SDK_INT >= 14 && !TextUtils.isEmpty(nativeAd.m3340b());
    }

    private boolean m3334b(NativeAd nativeAd) {
        if (nativeAd.m3344f() == null) {
            return false;
        }
        for (NativeAd adCoverImage : nativeAd.m3344f()) {
            if (adCoverImage.getAdCoverImage() == null) {
                return false;
            }
        }
        return true;
    }

    private void setCarouselRenderer(com.facebook.ads.internal.view.hscroll.b bVar) {
        if (this.f2557g) {
            throw new IllegalStateException("Carousel renderer must be set before nativeAd.");
        }
        if (this.f2554d != null) {
            removeView(this.f2554d);
        }
        float f = v.b;
        int round = Math.round(4.0f * f);
        int round2 = Math.round(f * 12.0f);
        bVar.setChildSpacing(round);
        bVar.setPadding(0, round2, 0, round2);
        bVar.setVisibility(8);
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams.addRule(13);
        addView(bVar, layoutParams);
        this.f2554d = bVar;
    }

    private void setImageRenderer(b bVar) {
        if (this.f2557g) {
            throw new IllegalStateException("Image renderer must be set before nativeAd.");
        }
        if (this.f2553c != null) {
            removeView(this.f2553c);
        }
        bVar.setVisibility(8);
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams.addRule(13);
        addView(bVar, layoutParams);
        this.f2553c = bVar;
    }

    public void destroy() {
        this.f2555e.pause(false);
        this.f2555e.destroy();
    }

    protected c getAdEventManager() {
        return d.a(getContext());
    }

    @Deprecated
    public boolean isAutoplay() {
        return this.f2555e.shouldAutoplay();
    }

    @Deprecated
    public void setAutoplay(boolean z) {
        this.f2558h = z;
        if (this.f2555e instanceof e) {
            this.f2555e.setAutoplay(z);
            return;
        }
        throw new IllegalStateException("MediaView only supports setAutoplay for backwards compatibility. New MediaViewVideoRenderer classes should implement their own autoplay handling.");
    }

    @Deprecated
    public void setAutoplayOnMobile(boolean z) {
        if (this.f2555e instanceof e) {
            this.f2555e.setAutoplayOnMobile(z);
            return;
        }
        throw new IllegalStateException("MediaView only supports setAutoplayOnMobile for backwards compatibility. New MediaViewVideoRenderer classes should implement their own autoplay handling.");
    }

    public void setListener(MediaViewListener mediaViewListener) {
        this.f2556f = mediaViewListener;
        if (mediaViewListener == null) {
            this.f2555e.setListener(null);
        } else {
            this.f2555e.setListener(new 1(this, mediaViewListener));
        }
    }

    public void setNativeAd(NativeAd nativeAd) {
        this.f2557g = true;
        nativeAd.m3337a(this);
        nativeAd.setMediaViewAutoplay(this.f2558h);
        if (m3334b(nativeAd)) {
            this.f2553c.setVisibility(8);
            this.f2553c.a(null, null);
            this.f2555e.setVisibility(8);
            this.f2555e.unsetNativeAd();
            bringChildToFront(this.f2554d);
            this.f2554d.setCurrentPosition(0);
            this.f2554d.setAdapter(new com.facebook.ads.internal.adapters.i(this.f2554d, nativeAd.getInternalNativeAd().m3374A()));
            this.f2554d.setVisibility(0);
        } else if (m3333a(nativeAd)) {
            this.f2553c.setVisibility(8);
            this.f2553c.a(null, null);
            this.f2554d.setVisibility(8);
            this.f2554d.setAdapter(null);
            bringChildToFront(this.f2555e);
            this.f2555e.setNativeAd(nativeAd);
            this.f2555e.setVisibility(0);
        } else if (nativeAd.getAdCoverImage() != null) {
            this.f2555e.setVisibility(8);
            this.f2555e.unsetNativeAd();
            this.f2554d.setVisibility(8);
            this.f2554d.setAdapter(null);
            bringChildToFront(this.f2553c);
            this.f2553c.setVisibility(0);
            new com.facebook.ads.internal.view.b.d(this.f2553c).a(getHeight(), getWidth()).a(a.e(getContext())).a(nativeAd.getAdCoverImage().getUrl());
        }
    }

    public void setVideoRenderer(MediaViewVideoRenderer mediaViewVideoRenderer) {
        if (this.f2557g) {
            throw new IllegalStateException("Video renderer must be set before nativeAd.");
        }
        if (this.f2555e != null) {
            removeView(this.f2555e);
            this.f2555e.destroy();
        }
        mediaViewVideoRenderer.setAdEventManager(getAdEventManager());
        mediaViewVideoRenderer.setVisibility(8);
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams.addRule(13);
        addView(mediaViewVideoRenderer, layoutParams);
        this.f2555e = mediaViewVideoRenderer;
    }
}
