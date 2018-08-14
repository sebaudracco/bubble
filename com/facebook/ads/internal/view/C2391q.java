package com.facebook.ads.internal.view;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.transition.AutoTransition;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.util.Pair;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.facebook.ads.AudienceNetworkActivity;
import com.facebook.ads.AudienceNetworkActivity$BackButtonInterceptor;
import com.facebook.ads.internal.adapters.C1900j;
import com.facebook.ads.internal.adapters.ad;
import com.facebook.ads.internal.p056q.p057a.C2119j;
import com.facebook.ads.internal.p056q.p057a.C2130s;
import com.facebook.ads.internal.p056q.p057a.C2133v;
import com.facebook.ads.internal.p068l.C2005a;
import com.facebook.ads.internal.p069m.C2012c;
import com.facebook.ads.internal.p070r.C2154a;
import com.facebook.ads.internal.p070r.C2154a.C2025a;
import com.facebook.ads.internal.view.C1923a.C1832a;
import com.facebook.ads.internal.view.C2348g.C2347a;
import com.facebook.ads.internal.view.p034b.C2186d;
import com.facebook.ads.internal.view.p053e.C2249b;
import com.facebook.ads.internal.view.p053e.C2323c;
import com.facebook.ads.internal.view.p053e.p054b.C1840m;
import com.facebook.ads.internal.view.p053e.p054b.C1844c;
import com.facebook.ads.internal.view.p053e.p054b.C1846e;
import com.facebook.ads.internal.view.p053e.p054b.C2229b;
import com.facebook.ads.internal.view.p053e.p054b.C2230d;
import com.facebook.ads.internal.view.p053e.p054b.C2235l;
import com.facebook.ads.internal.view.p053e.p054b.C2236n;
import com.facebook.ads.internal.view.p053e.p054b.C2237o;
import com.facebook.ads.internal.view.p053e.p054b.C2241t;
import com.facebook.ads.internal.view.p053e.p054b.C2242u;
import com.facebook.ads.internal.view.p053e.p054b.C2246z;
import com.facebook.ads.internal.view.p053e.p083a.C2222a;
import com.facebook.ads.internal.view.p053e.p083a.C2223b;
import com.facebook.ads.internal.view.p053e.p084d.C2336d;
import com.facebook.ads.internal.view.p053e.p085c.C2281d;
import com.facebook.ads.internal.view.p053e.p085c.C2281d.C2280a;
import com.facebook.ads.internal.view.p053e.p085c.C2286f;
import com.facebook.ads.internal.view.p053e.p085c.C2303j;
import com.facebook.ads.internal.view.p053e.p085c.C2303j.C2302a;
import com.facebook.ads.internal.view.p053e.p085c.C2305k;
import com.facebook.ads.internal.view.p053e.p085c.C2311l;
import com.facebook.ads.internal.view.p053e.p085c.C2321o;
import com.facebook.ads.internal.view.p082d.C2209a;
import com.facebook.ads.internal.view.p082d.C2215b;
import com.facebook.ads.internal.view.p082d.C2215b.C2214a;
import com.mopub.mobileads.resource.DrawableConstants.CloseButton;
import com.mopub.mobileads.resource.DrawableConstants.RadialCountdown;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

public class C2391q implements C1923a {
    static final /* synthetic */ boolean f5918a = (!C2391q.class.desiredAssertionStatus());
    private static final int f5919b = ((int) (12.0f * C2133v.f5083b));
    private static final int f5920c = ((int) (RadialCountdown.TEXT_SIZE_SP * C2133v.f5083b));
    private static final int f5921d = ((int) (16.0f * C2133v.f5083b));
    private static final int f5922e = ((int) (72.0f * C2133v.f5083b));
    private static final int f5923f = ((int) (C2133v.f5083b * 56.0f));
    private static final int f5924g = ((int) (C2133v.f5083b * 56.0f));
    private static final int f5925h = ((int) (28.0f * C2133v.f5083b));
    private static final int f5926i = ((int) (CloseButton.TEXT_SIZE_SP * C2133v.f5083b));
    private static final LayoutParams f5927j = new LayoutParams(-1, -1);
    private final C1900j f5928A;
    private final AtomicBoolean f5929B = new AtomicBoolean(false);
    @Nullable
    private Context f5930C;
    @Nullable
    private C2249b f5931D;
    @Nullable
    private C1832a f5932E;
    @Nullable
    private C2209a f5933F;
    @Nullable
    private C2281d f5934G;
    @Nullable
    private C2311l f5935H;
    @Nullable
    private C2303j f5936I;
    @Nullable
    private C2348g f5937J;
    private C2215b f5938K;
    private boolean f5939L = false;
    private final AudienceNetworkActivity$BackButtonInterceptor f5940k = new C23821(this);
    private final C1844c f5941l = new C23843(this);
    private final C1846e f5942m = new C23854(this);
    private final C1840m f5943n = new C23865(this);
    private final C2242u f5944o = new C23876(this);
    private final C2237o f5945p = new C23887(this);
    private final ad f5946q;
    private final C2012c f5947r;
    private final C2154a f5948s;
    private final C2025a f5949t;
    private final C2130s f5950u = new C2130s();
    private final C2321o f5951v;
    private final C2323c f5952w;
    private final RelativeLayout f5953x;
    private final RelativeLayout f5954y;
    private final C2286f f5955z;

    class C23821 implements AudienceNetworkActivity$BackButtonInterceptor {
        final /* synthetic */ C2391q f5909a;

        C23821(C2391q c2391q) {
            this.f5909a = c2391q;
        }

        public boolean interceptBackButton() {
            return !this.f5909a.f5939L;
        }
    }

    class C23843 extends C1844c {
        final /* synthetic */ C2391q f5911a;

        C23843(C2391q c2391q) {
            this.f5911a = c2391q;
        }

        public void m7530a(C2229b c2229b) {
            if (this.f5911a.f5932E != null) {
                this.f5911a.f5938K.m7070d();
                this.f5911a.m7548e();
                this.f5911a.f5932E.mo3562a(C2246z.REWARDED_VIDEO_COMPLETE.m7091a(), c2229b);
            }
        }
    }

    class C23854 extends C1846e {
        final /* synthetic */ C2391q f5912a;

        C23854(C2391q c2391q) {
            this.f5912a = c2391q;
        }

        public void m7532a(C2230d c2230d) {
            if (this.f5912a.f5932E != null) {
                this.f5912a.f5932E.mo3561a(C2246z.REWARDED_VIDEO_ERROR.m7091a());
            }
            this.f5912a.m7565c();
        }
    }

    class C23865 extends C1840m {
        final /* synthetic */ C2391q f5913a;

        C23865(C2391q c2391q) {
            this.f5913a = c2391q;
        }

        public void m7534a(C2235l c2235l) {
            if (this.f5913a.f5931D != null) {
                this.f5913a.f5931D.m7104a(C2222a.USER_STARTED);
                this.f5913a.f5948s.m6917a();
                this.f5913a.f5929B.set(this.f5913a.f5931D.m7115j());
                this.f5913a.m7553h();
            }
        }
    }

    class C23876 extends C2242u {
        final /* synthetic */ C2391q f5914a;

        C23876(C2391q c2391q) {
            this.f5914a = c2391q;
        }

        public void m7536a(C2241t c2241t) {
            this.f5914a.f5950u.m6822a(c2241t.m7089b(), this.f5914a.f5931D, c2241t.m7088a());
        }
    }

    class C23887 extends C2237o {
        final /* synthetic */ C2391q f5915a;

        C23887(C2391q c2391q) {
            this.f5915a = c2391q;
        }

        public void m7538a(C2236n c2236n) {
            if (this.f5915a.f5931D != null && this.f5915a.f5934G != null && this.f5915a.f5931D.getDuration() - this.f5915a.f5931D.getCurrentPosition() <= 3000 && this.f5915a.f5934G.m7204a()) {
                this.f5915a.f5934G.m7205b();
            }
        }
    }

    class C23898 extends C2025a {
        final /* synthetic */ C2391q f5916a;

        C23898(C2391q c2391q) {
            this.f5916a = c2391q;
        }

        public void mo3725a() {
            if (!this.f5916a.f5950u.m6823b()) {
                this.f5916a.f5950u.m6821a();
                Map hashMap = new HashMap();
                if (!TextUtils.isEmpty(this.f5916a.f5946q.m5723b())) {
                    this.f5916a.f5948s.m6919a(hashMap);
                    hashMap.put("touch", C2119j.m6798a(this.f5916a.f5950u.m6826e()));
                    this.f5916a.f5947r.mo3709a(this.f5916a.f5946q.m5723b(), hashMap);
                }
                if (this.f5916a.f5932E != null) {
                    this.f5916a.f5932E.mo3561a(C2246z.REWARDED_VIDEO_IMPRESSION.m7091a());
                }
            }
        }
    }

    class C23909 implements OnClickListener {
        final /* synthetic */ C2391q f5917a;

        C23909(C2391q c2391q) {
            this.f5917a = c2391q;
        }

        public void onClick(View view) {
            if (this.f5917a.f5936I != null && this.f5917a.f5936I.m7269a() && this.f5917a.f5936I.getSkipSeconds() != 0 && this.f5917a.f5931D != null) {
                this.f5917a.f5931D.m7110e();
            }
        }
    }

    public C2391q(Context context, C2012c c2012c, C2249b c2249b, C1832a c1832a, ad adVar) {
        this.f5930C = context;
        this.f5932E = c1832a;
        this.f5931D = c2249b;
        this.f5947r = c2012c;
        this.f5946q = adVar;
        this.f5928A = this.f5946q.m5736n();
        this.f5953x = new RelativeLayout(context);
        this.f5954y = new RelativeLayout(context);
        this.f5951v = new C2321o(this.f5930C);
        this.f5955z = new C2286f(this.f5930C);
        this.f5938K = new C2215b(this.f5930C, this.f5947r, this.f5946q, this.f5932E);
        new C2186d(this.f5954y, f5926i).m7000a().m7003a(C2005a.m6344e(this.f5930C)).m7004a(this.f5946q.m5732j());
        this.f5949t = new C23898(this);
        this.f5948s = new C2154a(this.f5931D, 1, this.f5949t);
        this.f5948s.m6918a(250);
        this.f5952w = new C2323c(this.f5930C, this.f5947r, this.f5931D, this.f5946q.m5723b());
        if (f5918a || this.f5931D != null) {
            this.f5931D.m7113h();
            this.f5931D.setIsFullScreen(true);
            this.f5931D.setBackgroundColor(-16777216);
            this.f5931D.getEventBus().m6328a(this.f5941l, this.f5942m, this.f5943n, this.f5944o, this.f5945p);
            return;
        }
        throw new AssertionError();
    }

    private void m7540a(int i) {
        ViewGroup.LayoutParams layoutParams;
        this.f5954y.removeAllViews();
        this.f5954y.addView(this.f5931D, f5927j);
        if (this.f5933F != null) {
            C2133v.m6832a(this.f5933F);
            this.f5933F.m7049a(i);
            layoutParams = new LayoutParams(-1, -2);
            layoutParams.addRule(12);
            this.f5933F.setPadding(f5921d, f5921d, f5921d, f5921d);
            this.f5954y.addView(this.f5933F, layoutParams);
        }
        if (this.f5936I != null) {
            layoutParams = new LayoutParams(f5923f, f5923f);
            layoutParams.addRule(10);
            layoutParams.addRule(11);
            this.f5936I.setPadding(f5921d, f5921d, f5921d, f5921d);
            this.f5954y.addView(this.f5936I, layoutParams);
        }
        layoutParams = new LayoutParams(f5925h, f5925h);
        layoutParams.addRule(10);
        layoutParams.addRule(11);
        layoutParams.setMargins(f5919b, f5919b + f5924g, f5919b, f5920c);
        this.f5954y.addView(this.f5955z, layoutParams);
        m7553h();
        layoutParams = new LayoutParams(-1, -2);
        layoutParams.addRule(12);
        this.f5954y.addView(this.f5951v, layoutParams);
    }

    private void m7545d() {
        boolean z = false;
        if (this.f5931D != null) {
            this.f5931D.m7109d();
            this.f5931D.m7105a(new C2305k(this.f5930C));
            this.f5931D.m7105a(this.f5955z);
            this.f5931D.m7105a(this.f5951v);
            this.f5935H = new C2311l(this.f5930C, true);
            C2223b c2281d = new C2281d(this.f5935H, C2280a.FADE_OUT_ON_PLAY, true);
            this.f5931D.m7105a(this.f5935H);
            this.f5931D.m7105a(c2281d);
            Context context = this.f5930C;
            int i = f5922e;
            C1900j c1900j = this.f5928A;
            C2012c c2012c = this.f5947r;
            C1832a c1832a = this.f5932E;
            boolean z2 = this.f5938K.m7068b() == C2214a.INFO;
            if (this.f5938K.m7068b() == C2214a.INFO) {
                z = true;
            }
            this.f5933F = new C2209a(context, i, c1900j, c2012c, c1832a, z2, z);
            this.f5933F.setInfo(this.f5946q);
            this.f5934G = new C2281d(this.f5933F, C2280a.FADE_OUT_ON_PLAY, true);
            this.f5931D.m7105a(this.f5934G);
            if (this.f5938K.m7067a() && this.f5946q.m5733k() > 0) {
                this.f5936I = new C2303j(this.f5930C, this.f5946q.m5733k(), -12286980);
                this.f5936I.setButtonMode(C2302a.SKIP_BUTTON_MODE);
                this.f5936I.setOnClickListener(new C23909(this));
                this.f5931D.m7105a(this.f5936I);
            } else if (!this.f5938K.m7067a()) {
                this.f5937J = new C2348g(this.f5930C);
                this.f5937J.m7422a(this.f5946q.m5727e(), this.f5946q.m5731i(), this.f5946q.m5742t(), this.f5946q.m5740r(), this.f5946q.m5723b(), this.f5946q.m5733k());
                if (this.f5946q.m5733k() <= 0) {
                    this.f5937J.m7425b();
                }
                if (this.f5938K.m7068b() != C2214a.INFO) {
                    this.f5937J.m7427c();
                }
                this.f5937J.setToolbarListener(new C2347a(this) {
                    final /* synthetic */ C2391q f5908a;

                    {
                        this.f5908a = r1;
                    }

                    public void mo3826a() {
                        if (!this.f5908a.f5939L && this.f5908a.f5931D != null) {
                            this.f5908a.f5939L = true;
                            this.f5908a.f5931D.m7110e();
                        } else if (this.f5908a.f5939L && this.f5908a.f5932E != null) {
                            this.f5908a.f5932E.mo3561a(C2246z.REWARDED_VIDEO_END_ACTIVITY.m7091a());
                        }
                    }
                });
                this.f5931D.m7105a(this.f5937J);
            }
        }
    }

    private void m7548e() {
        this.f5939L = true;
        m7552g();
        m7550f();
        if (this.f5931D != null) {
            this.f5931D.m7109d();
            this.f5931D.setVisibility(4);
        }
        if (this.f5937J != null) {
            this.f5937J.m7423a(true);
            this.f5937J.m7427c();
        }
        C2133v.m6835a(this.f5931D, this.f5936I, this.f5955z, this.f5951v);
        Pair c = this.f5938K.m7069c();
        ViewGroup.LayoutParams layoutParams;
        switch ((C2214a) c.first) {
            case MARKUP:
                C2133v.m6835a(this.f5933F);
                this.f5954y.addView((View) c.second, f5927j);
                return;
            case SCREENSHOTS:
                if (this.f5933F != null) {
                    this.f5933F.setVisibility(0);
                    this.f5933F.m7048a();
                }
                layoutParams = new LayoutParams(-1, -1);
                layoutParams.setMargins(0, f5924g, 0, 0);
                layoutParams.addRule(2, this.f5933F.getId());
                this.f5954y.addView((View) c.second, layoutParams);
                return;
            case INFO:
                C2133v.m6835a(this.f5933F);
                layoutParams = new LayoutParams(-1, -2);
                layoutParams.addRule(15);
                layoutParams.setMargins(f5921d, f5921d, f5921d, f5921d);
                this.f5954y.addView((View) c.second, layoutParams);
                return;
            default:
                return;
        }
    }

    private void m7550f() {
        if (VERSION.SDK_INT > 19) {
            Transition autoTransition = new AutoTransition();
            autoTransition.setDuration(200);
            autoTransition.setInterpolator(new AccelerateDecelerateInterpolator());
            TransitionManager.beginDelayedTransition(this.f5954y, autoTransition);
        }
    }

    private void m7552g() {
        if (this.f5930C != null) {
            View frameLayout = new FrameLayout(this.f5930C);
            frameLayout.setLayoutParams(f5927j);
            C2133v.m6833a(frameLayout, -1509949440);
            this.f5954y.addView(frameLayout, 0);
        }
    }

    private void m7553h() {
        this.f5955z.setVisibility(this.f5929B.get() ? 0 : 8);
    }

    public void m7560a() {
        if (this.f5931D != null) {
            this.f5931D.m7107a(true);
        }
    }

    public void mo3683a(Intent intent, Bundle bundle, AudienceNetworkActivity audienceNetworkActivity) {
        if (this.f5931D != null && this.f5932E != null) {
            m7545d();
            audienceNetworkActivity.addBackButtonInterceptor(this.f5940k);
            if (!TextUtils.isEmpty(this.f5946q.m5721a())) {
                this.f5931D.setVideoURI(!TextUtils.isEmpty(this.f5946q.m5741s()) ? this.f5946q.m5741s() : this.f5946q.m5721a());
            }
            m7540a(audienceNetworkActivity.getResources().getConfiguration().orientation);
            this.f5953x.addView(this.f5954y, f5927j);
            if (this.f5937J != null) {
                C2133v.m6832a(this.f5937J);
                this.f5937J.m7420a(this.f5928A, true);
                this.f5953x.addView(this.f5937J, new LayoutParams(-1, f5924g));
            }
            this.f5953x.setLayoutParams(f5927j);
            this.f5932E.mo3560a(this.f5953x);
            this.f5931D.m7104a(C2222a.USER_STARTED);
        }
    }

    public void m7562a(Configuration configuration) {
        if (this.f5933F != null) {
            this.f5933F.m7049a(configuration.orientation);
        }
    }

    public void mo3684a(Bundle bundle) {
    }

    public boolean m7564b() {
        return this.f5931D == null || this.f5931D.getState() == C2336d.PAUSED;
    }

    public void m7565c() {
        if (this.f5931D != null) {
            this.f5931D.m7111f();
            this.f5931D.m7116k();
        }
        if (this.f5948s != null) {
            this.f5948s.m6920b();
        }
    }

    public void mo3686i() {
        m7560a();
    }

    public void mo3687j() {
        if (m7564b() && this.f5931D != null && this.f5932E != null) {
            this.f5931D.m7102a(this.f5931D.getCurrentPosition());
            this.f5931D.m7104a(C2222a.USER_STARTED);
        }
    }

    public void onDestroy() {
        m7565c();
        if (this.f5931D != null) {
            this.f5931D.getEventBus().m6330b(this.f5941l, this.f5942m, this.f5943n, this.f5944o, this.f5945p);
        }
        if (!TextUtils.isEmpty(this.f5946q.m5723b())) {
            Map hashMap = new HashMap();
            this.f5948s.m6919a(hashMap);
            hashMap.put("touch", C2119j.m6798a(this.f5950u.m6826e()));
            this.f5947r.mo3717h(this.f5946q.m5723b(), hashMap);
        }
        if (this.f5937J != null) {
            this.f5937J.setToolbarListener(null);
        }
        this.f5952w.m7341a();
        this.f5931D = null;
        this.f5938K.m7071e();
        this.f5936I = null;
        this.f5933F = null;
        this.f5934G = null;
        this.f5932E = null;
        this.f5930C = null;
        this.f5951v.m7312a();
    }

    public void setListener(C1832a c1832a) {
    }
}
