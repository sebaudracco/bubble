package com.moat.analytics.mobile.mpub;

import android.app.Application;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;
import com.moat.analytics.mobile.mpub.C3412a.C3411d;
import com.moat.analytics.mobile.mpub.C3460t.C3418b;
import com.moat.analytics.mobile.mpub.C3460t.C3456a;
import java.lang.ref.WeakReference;

final class C3419f extends MoatAnalytics implements C3418b {
    private boolean f8681 = false;
    private String f8682;
    private MoatOptions f8683;
    WeakReference<Context> f8684;
    boolean f8685 = false;
    boolean f8686 = false;
    boolean f8687 = false;
    @Nullable
    C3412a f8688;

    C3419f() {
    }

    public final void start(Application application) {
        start(new MoatOptions(), application);
    }

    @UiThread
    public final void prepareNativeDisplayTracking(String str) {
        this.f8682 = str;
        if (C3460t.m11803().f8824 != C3456a.f8807) {
            try {
                m11670();
            } catch (Exception e) {
                C3442o.m11756(e);
            }
        }
    }

    @UiThread
    private void m11670() {
        if (this.f8688 == null) {
            this.f8688 = new C3412a(C3416c.m11664(), C3411d.f8654);
            this.f8688.m11646(this.f8682);
            C3412a.m11642(3, "Analytics", this, "Preparing native display tracking with partner code " + this.f8682);
            C3412a.m11639("[SUCCESS] ", "Prepared for native display tracking with partner code " + this.f8682);
        }
    }

    final boolean m11671() {
        return this.f8681;
    }

    final boolean m11672() {
        return this.f8683 != null && this.f8683.disableLocationServices;
    }

    public final void mo6514() throws C3442o {
        C3442o.m11757();
        C3441n.m11742();
        if (this.f8682 != null) {
            try {
                m11670();
            } catch (Exception e) {
                C3442o.m11756(e);
            }
        }
    }

    public final void start(MoatOptions moatOptions, Application application) {
        try {
            if (this.f8681) {
                C3412a.m11642(3, "Analytics", this, "Moat SDK has already been started.");
                return;
            }
            this.f8683 = moatOptions;
            C3460t.m11803().m11809();
            this.f8685 = moatOptions.disableLocationServices;
            if (application == null) {
                throw new C3442o("Moat Analytics SDK didn't start, application was null");
            }
            if (moatOptions.loggingEnabled && C3450r.m11778(application.getApplicationContext())) {
                this.f8686 = true;
            }
            this.f8684 = new WeakReference(application.getApplicationContext());
            this.f8681 = true;
            this.f8687 = moatOptions.autoTrackGMAInterstitials;
            C3416c.m11666(application);
            C3460t.m11803().m11808((C3418b) this);
            if (!moatOptions.disableAdIdCollection) {
                C3450r.m11781(application);
            }
            C3412a.m11639("[SUCCESS] ", "Moat Analytics SDK Version 2.4.1 started");
        } catch (Exception e) {
            C3442o.m11756(e);
        }
    }
}
