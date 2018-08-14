package com.facebook.ads.internal.view;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import com.facebook.ads.AudienceNetworkActivity;
import com.facebook.ads.AudienceNetworkActivity$BackButtonInterceptor;
import com.facebook.ads.internal.adapters.C1886d;
import com.facebook.ads.internal.adapters.C1936v;
import com.facebook.ads.internal.p052j.C1998a;
import com.facebook.ads.internal.p052j.C1998a.C1996a;
import com.facebook.ads.internal.p052j.C1999b;
import com.facebook.ads.internal.p055d.C1960b;
import com.facebook.ads.internal.p056q.p057a.C2119j;
import com.facebook.ads.internal.p056q.p057a.C2130s;
import com.facebook.ads.internal.p056q.p057a.C2133v;
import com.facebook.ads.internal.p069m.C2012c;
import com.facebook.ads.internal.p070r.C2154a;
import com.facebook.ads.internal.p070r.C2154a.C2025a;
import com.facebook.ads.internal.view.component.p081a.C2197c;
import com.facebook.ads.internal.view.p053e.C2249b;
import com.facebook.ads.internal.view.p053e.C2322d;
import com.facebook.ads.internal.view.p053e.C2323c;
import com.facebook.ads.internal.view.p053e.p054b.C1840m;
import com.facebook.ads.internal.view.p053e.p054b.C1841k;
import com.facebook.ads.internal.view.p053e.p054b.C1842i;
import com.facebook.ads.internal.view.p053e.p054b.C1844c;
import com.facebook.ads.internal.view.p053e.p054b.C1846e;
import com.facebook.ads.internal.view.p053e.p054b.C2229b;
import com.facebook.ads.internal.view.p053e.p054b.C2230d;
import com.facebook.ads.internal.view.p053e.p054b.C2233h;
import com.facebook.ads.internal.view.p053e.p054b.C2234j;
import com.facebook.ads.internal.view.p053e.p054b.C2235l;
import com.facebook.ads.internal.view.p053e.p083a.C2222a;
import com.facebook.ads.internal.view.p053e.p083a.C2223b;
import com.facebook.ads.internal.view.p053e.p084d.C2336d;
import com.facebook.ads.internal.view.p053e.p085c.C2281d;
import com.facebook.ads.internal.view.p053e.p085c.C2281d.C2280a;
import com.facebook.ads.internal.view.p053e.p085c.C2286f;
import com.facebook.ads.internal.view.p053e.p085c.C2289g;
import com.facebook.ads.internal.view.p053e.p085c.C2305k;
import com.facebook.ads.internal.view.p053e.p085c.C2311l;
import com.facebook.ads.internal.view.p053e.p085c.C2321o;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

public class C2374l extends C2363m {
    private boolean f5865A = false;
    private final AudienceNetworkActivity$BackButtonInterceptor f5866f = new C23671(this);
    private final C1846e f5867g = new C23682(this);
    private final C1841k f5868h = new C23693(this);
    private final C1842i f5869i = new C23704(this);
    private final C1844c f5870j = new C23715(this);
    private final C1840m f5871k = new C23726(this);
    private final C2249b f5872l = new C2249b(getContext());
    private final C2321o f5873m;
    private final C2286f f5874n;
    private final C1936v f5875o;
    private final C1886d f5876p;
    private final C2154a f5877q;
    private final C2025a f5878r;
    private final C2130s f5879s = new C2130s();
    @Nullable
    private final C1960b f5880t;
    private final AtomicBoolean f5881u = new AtomicBoolean(false);
    private final AtomicBoolean f5882v = new AtomicBoolean(false);
    private final C2322d f5883w;
    @Nullable
    private AudienceNetworkActivity f5884x;
    @Nullable
    private C2222a f5885y;
    private long f5886z;

    class C23671 implements AudienceNetworkActivity$BackButtonInterceptor {
        final /* synthetic */ C2374l f5858a;

        C23671(C2374l c2374l) {
            this.f5858a = c2374l;
        }

        public boolean interceptBackButton() {
            return !this.f5858a.c.m7424a();
        }
    }

    class C23682 extends C1846e {
        final /* synthetic */ C2374l f5859a;

        C23682(C2374l c2374l) {
            this.f5859a = c2374l;
        }

        public void m7486a(C2230d c2230d) {
            if (this.f5859a.getAudienceNetworkListener() != null) {
                this.f5859a.getAudienceNetworkListener().mo3562a("videoInterstitalEvent", c2230d);
            }
            if (!this.f5859a.f5865A) {
                this.f5859a.f5872l.m7111f();
                this.f5859a.f5872l.m7116k();
                this.f5859a.f5865A = true;
            }
            if (this.f5859a.f5884x != null) {
                this.f5859a.f5884x.finish();
            }
        }
    }

    class C23693 extends C1841k {
        final /* synthetic */ C2374l f5860a;

        C23693(C2374l c2374l) {
            this.f5860a = c2374l;
        }

        public void m7488a(C2234j c2234j) {
            if (this.f5860a.getAudienceNetworkListener() != null) {
                this.f5860a.getAudienceNetworkListener().mo3562a("videoInterstitalEvent", c2234j);
            }
        }
    }

    class C23704 extends C1842i {
        final /* synthetic */ C2374l f5861a;

        C23704(C2374l c2374l) {
            this.f5861a = c2374l;
        }

        public void m7490a(C2233h c2233h) {
            if (this.f5861a.getAudienceNetworkListener() != null) {
                this.f5861a.getAudienceNetworkListener().mo3562a("videoInterstitalEvent", c2233h);
            }
        }
    }

    class C23715 extends C1844c {
        final /* synthetic */ C2374l f5862a;

        C23715(C2374l c2374l) {
            this.f5862a = c2374l;
        }

        public void m7492a(C2229b c2229b) {
            this.f5862a.f5881u.set(true);
            if (this.f5862a.getAudienceNetworkListener() != null) {
                this.f5862a.getAudienceNetworkListener().mo3562a("videoInterstitalEvent", c2229b);
            }
        }
    }

    class C23726 extends C1840m {
        final /* synthetic */ C2374l f5863a;

        C23726(C2374l c2374l) {
            this.f5863a = c2374l;
        }

        public void m7494a(C2235l c2235l) {
            if (!this.f5863a.f5865A) {
                this.f5863a.f5882v.set(this.f5863a.f5872l.m7115j());
                this.f5863a.m7497a();
            }
            if (this.f5863a.getAudienceNetworkListener() != null) {
                this.f5863a.getAudienceNetworkListener().mo3562a("videoInterstitalEvent", c2235l);
            }
        }
    }

    class C23737 extends C2025a {
        final /* synthetic */ C2374l f5864a;

        C23737(C2374l c2374l) {
            this.f5864a = c2374l;
        }

        public void mo3725a() {
            if (!this.f5864a.f5879s.m6823b()) {
                this.f5864a.f5879s.m6821a();
                Map hashMap = new HashMap();
                if (!TextUtils.isEmpty(this.f5864a.f5875o.m6108a())) {
                    this.f5864a.f5877q.m6919a(hashMap);
                    hashMap.put("touch", C2119j.m6798a(this.f5864a.f5879s.m6826e()));
                    this.f5864a.b.mo3709a(this.f5864a.f5875o.m6108a(), hashMap);
                    if (this.f5864a.getAudienceNetworkListener() != null) {
                        this.f5864a.getAudienceNetworkListener().mo3561a("com.facebook.ads.interstitial.impression.logged");
                    }
                }
            }
        }
    }

    public C2374l(Context context, C2012c c2012c, C1936v c1936v, @Nullable C1960b c1960b) {
        super(context, c2012c);
        C2133v.m6832a(this.f5872l);
        C2133v.m6833a(this.f5872l, 0);
        this.f5875o = c1936v;
        this.f5876p = (C1886d) this.f5875o.m6111d().get(0);
        this.f5880t = c1960b;
        this.f5873m = new C2321o(getContext());
        this.f5874n = new C2286f(context);
        this.f5872l.getEventBus().m6328a(this.f5868h, this.f5869i, this.f5870j, this.f5867g, this.f5871k);
        setupPlugins(this.f5876p);
        this.f5878r = new C23737(this);
        this.f5877q = new C2154a(this.f5872l, 1, this.f5878r);
        this.f5877q.m6918a(c1936v.m6117j());
        this.f5877q.m6921b(c1936v.m6118k());
        this.f5883w = new C2323c(getContext(), this.b, this.f5872l, this.f5875o.m6108a());
        this.f5872l.setVideoURI(m7496a(this.f5876p.m5765i()));
    }

    private String m7496a(String str) {
        CharSequence charSequence = "";
        if (!(this.f5880t == null || str == null)) {
            charSequence = this.f5880t.m6172b(str);
        }
        return TextUtils.isEmpty(charSequence) ? str : charSequence;
    }

    private void m7497a() {
        this.f5874n.setVisibility(this.f5882v.get() ? 0 : 8);
    }

    private void setUpContent(int i) {
        View a = C2197c.m7029a(getContext(), this.b, getAudienceNetworkListener(), this.f5872l, this.d, this.e, a, i, this.f5876p.m5763g(), this.f5876p.m5764h(), this.f5873m, this.f5874n);
        m7497a();
        a.mo3771a(this.f5876p.m5758b(), this.f5876p.m5759c(), this.f5876p.m5760d(), this.f5876p.m5761e(), this.f5875o.m6108a(), ((double) this.f5876p.m5764h()) / ((double) this.f5876p.m5763g()));
        m7466a(a, a.mo3770a(), i);
    }

    private void setupPlugins(C1886d c1886d) {
        this.f5872l.m7109d();
        this.f5872l.m7105a(this.f5873m);
        this.f5872l.m7105a(this.f5874n);
        if (!TextUtils.isEmpty(c1886d.m5762f())) {
            C2223b c2289g = new C2289g(getContext());
            this.f5872l.m7105a(c2289g);
            c2289g.setImage(c1886d.m5762f());
        }
        C2223b c2311l = new C2311l(getContext(), true);
        this.f5872l.m7105a(c2311l);
        this.f5872l.m7105a(new C2281d(c2311l, c1886d.m5766j() ? C2280a.FADE_OUT_ON_PLAY : C2280a.VISIBLE, true));
        this.f5872l.m7105a(new C2305k(getContext()));
        this.f5872l.m7105a(this.c);
    }

    public void mo3683a(Intent intent, Bundle bundle, AudienceNetworkActivity audienceNetworkActivity) {
        super.m7467a(audienceNetworkActivity, this.f5875o);
        this.f5884x = audienceNetworkActivity;
        setUpContent(audienceNetworkActivity.getResources().getConfiguration().orientation);
        this.f5884x.addBackButtonInterceptor(this.f5866f);
        C1886d c1886d = (C1886d) this.f5875o.m6111d().get(0);
        if (c1886d.m5766j()) {
            this.f5872l.setVolume(c1886d.m5767k() ? 1.0f : 0.0f);
            this.f5872l.m7104a(C2222a.AUTO_STARTED);
        }
        this.f5886z = System.currentTimeMillis();
    }

    public void mo3684a(Bundle bundle) {
    }

    public void mo3686i() {
        if (!this.f5865A && this.f5872l.getState() == C2336d.STARTED) {
            this.f5885y = this.f5872l.getVideoStartReason();
            this.f5872l.m7107a(false);
        }
    }

    public void mo3687j() {
        if (!this.f5865A && this.f5885y != null) {
            this.f5872l.m7104a(this.f5885y);
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        removeAllViews();
        C2133v.m6836b(this.f5872l);
        C2133v.m6836b(this.f5873m);
        C2133v.m6836b(this.f5874n);
        setUpContent(configuration.orientation);
        super.onConfigurationChanged(configuration);
    }

    public void onDestroy() {
        if (!this.f5865A) {
            if (!this.f5881u.get()) {
                this.f5872l.m7110e();
            }
            if (this.f5875o != null) {
                C1999b.m6321a(C1998a.m6316a(this.f5886z, C1996a.XOUT, this.f5875o.m6113f()));
                if (!TextUtils.isEmpty(this.f5875o.m6108a())) {
                    Map hashMap = new HashMap();
                    this.f5877q.m6919a(hashMap);
                    hashMap.put("touch", C2119j.m6798a(this.f5879s.m6826e()));
                    this.b.mo3717h(this.f5875o.m6108a(), hashMap);
                }
            }
            this.f5872l.m7111f();
            this.f5872l.m7116k();
            this.f5865A = true;
        }
        this.f5884x = null;
        super.onDestroy();
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        this.f5879s.m6822a(motionEvent, this, this);
        return super.onTouchEvent(motionEvent);
    }

    protected void onWindowVisibilityChanged(int i) {
        super.onWindowVisibilityChanged(i);
        if (this.f5877q == null) {
            return;
        }
        if (i == 0) {
            this.f5877q.m6917a();
        } else if (i == 8) {
            this.f5877q.m6920b();
        }
    }
}
