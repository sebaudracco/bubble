package com.moat.analytics.mobile.vng;

import android.app.Application;
import android.content.Context;
import android.support.annotation.Nullable;
import com.moat.analytics.mobile.vng.C3487g.C3486a;
import com.moat.analytics.mobile.vng.C3532w.C3499b;
import com.moat.analytics.mobile.vng.C3532w.C3531d;
import java.lang.ref.WeakReference;

class C3500k extends MoatAnalytics implements C3499b {
    boolean f8940a = false;
    boolean f8941b = false;
    boolean f8942c = false;
    @Nullable
    C3487g f8943d;
    WeakReference<Context> f8944e;
    private boolean f8945f = false;
    private String f8946g;

    C3500k() {
    }

    private void m11928a(MoatOptions moatOptions, Application application) {
        if (this.f8945f) {
            C3511p.m11976a(3, "Analytics", (Object) this, "Moat SDK has already been started.");
            return;
        }
        C3532w.m12009a().m12022b();
        if (moatOptions.loggingEnabled && C3500k.m11929a(application.getApplicationContext())) {
            this.f8940a = true;
        }
        this.f8942c = moatOptions.disableLocationServices;
        if (application == null) {
            C3511p.m11978a("[ERROR] ", "Moat Analytics SDK didn't start, application was null");
            return;
        }
        this.f8944e = new WeakReference(application.getApplicationContext());
        this.f8945f = true;
        this.f8941b = moatOptions.autoTrackGMAInterstitials;
        C3475a.m11848a(application);
        C3532w.m12009a().m12021a((C3499b) this);
        if (!moatOptions.disableAdIdCollection) {
            C3515s.m11987a((Context) application);
        }
        C3511p.m11978a("[SUCCESS] ", "Moat Analytics SDK Version 2.2.0 started");
    }

    private static boolean m11929a(Context context) {
        return (context.getApplicationInfo().flags & 2) != 0;
    }

    private void m11930d() {
        if (this.f8943d == null) {
            this.f8943d = new C3487g(C3475a.m11847a(), C3486a.DISPLAY);
            this.f8943d.m11876a(this.f8946g);
            C3511p.m11976a(3, "Analytics", (Object) this, "Preparing native display tracking with partner code " + this.f8946g);
            C3511p.m11978a("[SUCCESS] ", "Prepared for native display tracking with partner code " + this.f8946g);
        }
    }

    boolean m11931a() {
        return this.f8945f;
    }

    public void mo6533b() {
        C3510o.m11953a();
        if (this.f8946g != null) {
            try {
                m11930d();
            } catch (Exception e) {
                C3502m.m11942a(e);
            }
        }
    }

    public void mo6534c() {
    }

    public void prepareNativeDisplayTracking(String str) {
        this.f8946g = str;
        if (C3532w.m12009a().f8995a != C3531d.OFF) {
            try {
                m11930d();
            } catch (Exception e) {
                C3502m.m11942a(e);
            }
        }
    }

    public void start(Application application) {
        start(new MoatOptions(), application);
    }

    public void start(MoatOptions moatOptions, Application application) {
        try {
            m11928a(moatOptions, application);
        } catch (Exception e) {
            C3502m.m11942a(e);
        }
    }
}
