package com.facebook.ads.internal.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;
import com.facebook.ads.MediaViewVideoRenderer;
import com.facebook.ads.NativeAd;
import com.facebook.ads.VideoStartReason;
import com.facebook.ads.internal.p056q.p057a.C2133v;
import com.facebook.ads.internal.p070r.C2154a;
import com.facebook.ads.internal.p070r.C2154a.C2025a;
import com.facebook.ads.internal.view.p053e.p083a.C2223b;
import com.facebook.ads.internal.view.p053e.p084d.C2336d;
import com.facebook.ads.internal.view.p053e.p085c.C2289g;
import com.facebook.ads.internal.view.p053e.p085c.C2294h;

public final class C2337e extends MediaViewVideoRenderer {
    private static final String f5721d = C2337e.class.getSimpleName();
    private final C2289g f5722e;
    private final C2154a f5723f = m7385b();
    private final C2025a f5724g = m7388c();
    @Nullable
    private C2380n f5725h;
    private boolean f5726i;
    private boolean f5727j;
    private boolean f5728k;

    class C22201 extends C2025a {
        final /* synthetic */ C2337e f5412a;

        C22201(C2337e c2337e) {
            this.f5412a = c2337e;
        }

        public void mo3725a() {
            if (this.f5412a.f5725h != null) {
                if (!this.f5412a.f5728k && (this.f5412a.f5727j || this.f5412a.shouldAutoplay())) {
                    this.f5412a.play(VideoStartReason.AUTO_STARTED);
                }
                this.f5412a.f5727j = false;
                this.f5412a.f5728k = false;
            }
        }

        public void mo3776b() {
            if (this.f5412a.f5725h != null) {
                if (this.f5412a.f5725h.getState() == C2336d.PAUSED) {
                    this.f5412a.f5728k = true;
                } else if (this.f5412a.f5725h.getState() == C2336d.STARTED) {
                    this.f5412a.f5727j = true;
                }
                this.f5412a.pause(this.f5412a.f5728k);
            }
        }
    }

    class C22212 implements OnTouchListener {
        final /* synthetic */ C2337e f5413a;

        C22212(C2337e c2337e) {
            this.f5413a = c2337e;
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (this.f5413a.f5725h != null && motionEvent.getAction() == 1) {
                this.f5413a.f5725h.mo3827a();
            }
            return true;
        }
    }

    public C2337e(Context context) {
        super(context);
        this.f5722e = new C2289g(context);
        m7383a();
    }

    public C2337e(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f5722e = new C2289g(context);
        m7383a();
    }

    public C2337e(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f5722e = new C2289g(context);
        m7383a();
    }

    @TargetApi(21)
    public C2337e(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.f5722e = new C2289g(context);
        m7383a();
    }

    private void m7383a() {
        setVolume(0.0f);
        float f = C2133v.f5083b;
        int i = (int) (2.0f * f);
        int i2 = (int) (f * 25.0f);
        C2223b c2294h = new C2294h(getContext());
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(9);
        layoutParams.addRule(12);
        c2294h.setPadding(i, i2, i2, i);
        c2294h.setLayoutParams(layoutParams);
        for (i = 0; i < getChildCount(); i++) {
            View childAt = getChildAt(0);
            if (childAt instanceof C2380n) {
                this.f5725h = (C2380n) childAt;
                break;
            }
        }
        if (this.f5725h == null) {
            Log.e(f5721d, "Unable to find MediaViewVideo child.");
        } else {
            this.f5725h.m7105a(this.f5722e);
            this.f5725h.m7105a(c2294h);
        }
        this.f5723f.m6918a(0);
        this.f5723f.m6921b(250);
    }

    private C2154a m7385b() {
        return new C2154a(this, 50, true, this.f5724g);
    }

    private C2025a m7388c() {
        return new C22201(this);
    }

    private void m7390d() {
        if (getVisibility() == 0 && this.f5726i && hasWindowFocus()) {
            this.f5723f.m6917a();
            return;
        }
        if (this.f5725h != null && this.f5725h.getState() == C2336d.PAUSED) {
            this.f5728k = true;
        }
        this.f5723f.m6920b();
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.f5726i = true;
        m7390d();
    }

    protected void onDetachedFromWindow() {
        this.f5726i = false;
        m7390d();
        super.onDetachedFromWindow();
    }

    public void onPrepared() {
        super.onPrepared();
        setOnTouchListener(new C22212(this));
    }

    protected void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
        m7390d();
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        m7390d();
    }

    public void setNativeAd(NativeAd nativeAd) {
        super.setNativeAd(nativeAd);
        this.f5727j = false;
        this.f5728k = false;
        C2289g c2289g = this.f5722e;
        String url = (nativeAd == null || nativeAd.getAdCoverImage() == null) ? null : nativeAd.getAdCoverImage().getUrl();
        c2289g.setImage(url);
        this.f5723f.m6917a();
    }
}
