package com.facebook.ads.internal.view.p053e;

import android.annotation.TargetApi;
import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.TextureView;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;
import com.facebook.ads.internal.p052j.C1839f;
import com.facebook.ads.internal.p052j.C2001d;
import com.facebook.ads.internal.p052j.C2002e;
import com.facebook.ads.internal.p068l.C2005a;
import com.facebook.ads.internal.settings.AdInternalSettings;
import com.facebook.ads.internal.view.p053e.C2322d.C2247a;
import com.facebook.ads.internal.view.p053e.p054b.C2229b;
import com.facebook.ads.internal.view.p053e.p054b.C2230d;
import com.facebook.ads.internal.view.p053e.p054b.C2233h;
import com.facebook.ads.internal.view.p053e.p054b.C2234j;
import com.facebook.ads.internal.view.p053e.p054b.C2235l;
import com.facebook.ads.internal.view.p053e.p054b.C2236n;
import com.facebook.ads.internal.view.p053e.p054b.C2238p;
import com.facebook.ads.internal.view.p053e.p054b.C2239r;
import com.facebook.ads.internal.view.p053e.p054b.C2240s;
import com.facebook.ads.internal.view.p053e.p054b.C2241t;
import com.facebook.ads.internal.view.p053e.p054b.C2243v;
import com.facebook.ads.internal.view.p053e.p054b.C2244x;
import com.facebook.ads.internal.view.p053e.p054b.C2245y;
import com.facebook.ads.internal.view.p053e.p083a.C2222a;
import com.facebook.ads.internal.view.p053e.p083a.C2223b;
import com.facebook.ads.internal.view.p053e.p083a.C2224c;
import com.facebook.ads.internal.view.p053e.p084d.C2248e;
import com.facebook.ads.internal.view.p053e.p084d.C2330c;
import com.facebook.ads.internal.view.p053e.p084d.C2331a;
import com.facebook.ads.internal.view.p053e.p084d.C2335b;
import com.facebook.ads.internal.view.p053e.p084d.C2336d;
import java.util.ArrayList;
import java.util.List;

public class C2249b extends RelativeLayout implements C2247a, C2248e {
    private static final C2235l f5438b = new C2235l();
    private static final C2230d f5439c = new C2230d();
    private static final C2229b f5440d = new C2229b();
    private static final C2236n f5441e = new C2236n();
    private static final C2239r f5442f = new C2239r();
    private static final C2233h f5443g = new C2233h();
    private static final C2240s f5444h = new C2240s();
    private static final C2234j f5445i = new C2234j();
    private static final C2243v f5446j = new C2243v();
    private static final C2245y f5447k = new C2245y();
    private static final C2244x f5448l = new C2244x();
    protected final C2330c f5449a;
    private final List<C2223b> f5450m = new ArrayList();
    private final Handler f5451n = new Handler();
    private final C2002e<C1839f, C2001d> f5452o = new C2002e();
    private boolean f5453p;
    private boolean f5454q;
    private final OnTouchListener f5455r = new C22272(this);

    class C22261 implements Runnable {
        final /* synthetic */ C2249b f5420a;

        C22261(C2249b c2249b) {
            this.f5420a = c2249b;
        }

        public void run() {
            if (!this.f5420a.f5453p) {
                this.f5420a.f5452o.m6327a(C2249b.f5441e);
                this.f5420a.f5451n.postDelayed(this, 250);
            }
        }
    }

    class C22272 implements OnTouchListener {
        final /* synthetic */ C2249b f5421a;

        C22272(C2249b c2249b) {
            this.f5421a = c2249b;
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            this.f5421a.f5452o.m6327a(new C2241t(view, motionEvent));
            return false;
        }
    }

    public C2249b(Context context) {
        super(context);
        if (C2005a.m6339a(context)) {
            this.f5449a = new C2331a(context);
        } else {
            this.f5449a = new C2335b(context);
        }
        mo3827a();
    }

    public C2249b(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        if (C2005a.m6339a(context)) {
            this.f5449a = new C2331a(context, attributeSet);
        } else {
            this.f5449a = new C2335b(context, attributeSet);
        }
        mo3827a();
    }

    public C2249b(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        if (C2005a.m6339a(context)) {
            this.f5449a = new C2331a(context, attributeSet, i);
        } else {
            this.f5449a = new C2335b(context, attributeSet, i);
        }
        mo3827a();
    }

    @TargetApi(21)
    public C2249b(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        if (C2005a.m6339a(context)) {
            this.f5449a = new C2331a(context, attributeSet, i, i2);
        } else {
            this.f5449a = new C2335b(context, attributeSet, i, i2);
        }
        mo3827a();
    }

    private void mo3827a() {
        if (mo3781g() && (this.f5449a instanceof C2331a)) {
            ((C2331a) this.f5449a).setTestMode(AdInternalSettings.isTestMode(getContext()));
        }
        this.f5449a.setRequestedVolume(1.0f);
        this.f5449a.setVideoStateChangeListener(this);
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams.addRule(13);
        addView((View) this.f5449a, layoutParams);
        setOnTouchListener(this.f5455r);
    }

    public void m7102a(int i) {
        this.f5449a.mo3789a(i);
    }

    public void mo3779a(int i, int i2) {
        this.f5452o.m6327a(new C2238p(i, i2));
    }

    public void m7104a(C2222a c2222a) {
        if (this.f5453p && this.f5449a.getState() == C2336d.PLAYBACK_COMPLETED) {
            this.f5453p = false;
        }
        this.f5449a.mo3790a(c2222a);
    }

    public void m7105a(C2223b c2223b) {
        this.f5450m.add(c2223b);
    }

    public void mo3780a(C2336d c2336d) {
        if (c2336d == C2336d.PREPARED) {
            this.f5452o.m6327a(f5438b);
        } else if (c2336d == C2336d.ERROR) {
            this.f5453p = true;
            this.f5452o.m6327a(f5439c);
        } else if (c2336d == C2336d.PLAYBACK_COMPLETED) {
            this.f5453p = true;
            this.f5451n.removeCallbacksAndMessages(null);
            this.f5452o.m6327a(f5440d);
        } else if (c2336d == C2336d.STARTED) {
            this.f5452o.m6327a(f5445i);
            this.f5451n.removeCallbacksAndMessages(null);
            this.f5451n.postDelayed(new C22261(this), 250);
        } else if (c2336d == C2336d.PAUSED) {
            this.f5452o.m6327a(f5443g);
            this.f5451n.removeCallbacksAndMessages(null);
        } else if (c2336d == C2336d.IDLE) {
            this.f5452o.m6327a(f5444h);
            this.f5451n.removeCallbacksAndMessages(null);
        }
    }

    public void m7107a(boolean z) {
        this.f5449a.mo3791a(z);
    }

    public void m7108c() {
        for (C2223b c2223b : this.f5450m) {
            if (c2223b instanceof C2224c) {
                C2224c c2224c = (C2224c) c2223b;
                if (c2224c.getParent() == null) {
                    addView(c2224c);
                    c2224c.mo3777a(this);
                }
            } else {
                c2223b.mo3777a(this);
            }
        }
    }

    public void m7109d() {
        for (C2223b c2223b : this.f5450m) {
            if (c2223b instanceof C2224c) {
                C2224c c2224c = (C2224c) c2223b;
                if (c2224c.getParent() != null) {
                    c2224c.mo3778b(this);
                    removeView(c2224c);
                }
            } else {
                c2223b.mo3778b(this);
            }
        }
    }

    public void m7110e() {
        getEventBus().m6327a(f5442f);
        this.f5449a.mo3792b();
    }

    public void m7111f() {
        this.f5449a.mo3794c();
    }

    public boolean mo3781g() {
        return C2005a.m6339a(getContext());
    }

    public int getCurrentPosition() {
        return this.f5449a.getCurrentPosition();
    }

    public int getDuration() {
        return this.f5449a.getDuration();
    }

    @NonNull
    public C2002e<C1839f, C2001d> getEventBus() {
        return this.f5452o;
    }

    public long getInitialBufferTime() {
        return this.f5449a.getInitialBufferTime();
    }

    public C2336d getState() {
        return this.f5449a.getState();
    }

    public TextureView getTextureView() {
        return (TextureView) this.f5449a;
    }

    public int getVideoHeight() {
        return this.f5449a.getVideoHeight();
    }

    public C2222a getVideoStartReason() {
        return this.f5449a.getStartReason();
    }

    public View getVideoView() {
        return this.f5449a.getView();
    }

    public int getVideoWidth() {
        return this.f5449a.getVideoWidth();
    }

    public float getVolume() {
        return this.f5449a.getVolume();
    }

    public void m7113h() {
        this.f5449a.mo3793b(true);
    }

    public boolean mo3786i() {
        return this.f5454q;
    }

    public boolean m7115j() {
        return this.f5449a.mo3795d();
    }

    public void m7116k() {
        this.f5449a.setVideoStateChangeListener(null);
        this.f5449a.mo3796e();
    }

    protected void onAttachedToWindow() {
        this.f5452o.m6327a(f5448l);
        super.onAttachedToWindow();
    }

    protected void onDetachedFromWindow() {
        this.f5452o.m6327a(f5447k);
        super.onDetachedFromWindow();
    }

    public void setControlsAnchorView(View view) {
        if (this.f5449a != null) {
            this.f5449a.setControlsAnchorView(view);
        }
    }

    public void setIsFullScreen(boolean z) {
        this.f5454q = z;
        this.f5449a.setFullScreen(z);
    }

    public void setLayoutParams(LayoutParams layoutParams) {
        super.setLayoutParams(layoutParams);
    }

    public void setVideoMPD(@Nullable String str) {
        this.f5449a.setVideoMPD(str);
    }

    public void setVideoURI(@Nullable Uri uri) {
        if (uri == null) {
            m7109d();
        } else {
            m7108c();
            this.f5449a.setup(uri);
        }
        this.f5453p = false;
    }

    public void setVideoURI(@Nullable String str) {
        setVideoURI(str != null ? Uri.parse(str) : null);
    }

    public void setVolume(float f) {
        this.f5449a.setRequestedVolume(f);
        getEventBus().m6327a(f5446j);
    }
}
