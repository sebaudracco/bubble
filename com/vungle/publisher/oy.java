package com.vungle.publisher;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;
import com.vungle.publisher.env.C1613o;
import com.vungle.publisher.ji.a;
import com.vungle.publisher.log.Logger;
import com.vungle.publisher.ob.C1664a;
import com.vungle.publisher.op.C1665a;
import com.vungle.publisher.ys.C1678a;
import javax.inject.Inject;
import javax.inject.Singleton;

/* compiled from: vungle */
public class oy extends mg<jh<?, ?, ?>> {
    @Inject
    C1666a f3227l;
    @Inject
    C1678a f3228m;
    @Inject
    C1665a f3229n;
    @Inject
    C1664a f3230o;
    @Inject
    C1613o f3231p;
    private op f3232q;
    private ob f3233r;

    @Singleton
    /* compiled from: vungle */
    public static class C1667a extends me<oy> {

        @Singleton
        /* compiled from: vungle */
        public static class C1666a {
            @Inject
            C1667a f3226a;

            @Inject
            C1666a() {
            }

            public C1667a m4530a(oy oyVar) {
                this.f3226a.a = oyVar;
                return this.f3226a;
            }
        }

        @Inject
        C1667a() {
        }

        public void onEvent(aa event) {
            a a = event.a();
            Logger.v(Logger.EVENT_TAG, "cta click event: " + a);
            ((oy) this.a).m4535a(a);
        }

        public void onEvent(ay event) {
            Logger.v(Logger.EVENT_TAG, "video.onCancel()");
            ((oy) this.a).m4537d();
        }

        public void onEvent(az event) {
            Logger.v(Logger.EVENT_TAG, "video.onNext()");
            ((oy) this.a).m4537d();
        }

        public void onEvent(ao event) {
            Logger.v(Logger.EVENT_TAG, "postRoll.onCancel()");
            ((oy) this.a).m4374a(true, false);
        }
    }

    @Inject
    oy() {
    }

    public void m4534a(VungleAdActivity vungleAdActivity, jh<?, ?, ?> jhVar, String str, p pVar, Bundle bundle) {
        try {
            Logger.d(Logger.AD_TAG, "create video ad");
            vungleAdActivity.getWindow().setBackgroundDrawable(new ColorDrawable(-16777216));
            super.mo3009a(vungleAdActivity, jhVar, str, pVar, bundle);
            vungleAdActivity.setRequestedOrientation(m4531a(pVar, jhVar.m3837D()));
            s b = this.f3231p.m3923b(str);
            boolean z = b != null && b.c;
            this.f3232q = this.f3229n.m4480a(vungleAdActivity, jhVar, pVar, z, str);
            if (jhVar instanceof el) {
                em v = ((el) jhVar).m3854v();
                if (v != null) {
                    this.f3233r = (ob) this.f3230o.a(vungleAdActivity, (String) jhVar.c_(), v.m3866K(), pVar, z, x.a(jhVar.s));
                }
            }
            if ("postRollFragment".equals(this.f)) {
                m4537d();
            } else {
                m4538e();
            }
        } catch (Throwable e) {
            Logger.e(Logger.AD_TAG, "error playing video ad", e);
            m4374a(false, false);
        }
    }

    protected me<?> mo3011a() {
        return this.f3227l.m4530a(this);
    }

    protected yj<?> mo3010b() {
        return this.f3228m.m4918a((jh) this.a);
    }

    int m4531a(p pVar, jg<?> jgVar) {
        Orientation orientation = pVar.getOrientation();
        switch (1.a[orientation.ordinal()]) {
            case 1:
                Logger.d(Logger.AD_TAG, "ad orientation " + orientation);
                return 10;
            default:
                if (jgVar.K()) {
                    Logger.d(Logger.AD_TAG, "ad orientation " + orientation + " (landscape)");
                    return 6;
                } else if (jgVar.L()) {
                    Logger.d(Logger.AD_TAG, "ad orientation " + orientation + " (portrait)");
                    return 7;
                } else {
                    Logger.d(Logger.AD_TAG, "ad orientation " + orientation + " (unknown) --> auto-rotate");
                    return 10;
                }
        }
    }

    void m4537d() {
        if (this.f3233r == null) {
            m4374a(true, false);
            return;
        }
        this.i.m4568a(new ap());
        m4372a(this.f3233r);
    }

    void m4538e() {
        if (this.f3232q == null) {
            m4537d();
        } else {
            m4372a(this.f3232q);
        }
    }

    void m4535a(a aVar) {
        boolean z;
        try {
            String y = ((jh) this.a).m3845y();
            Logger.v(Logger.AD_TAG, "call to action destination " + y);
            if (y != null) {
                Intent a = this.j.m4922a("android.intent.action.VIEW", Uri.parse(y));
                a.addFlags(ErrorDialogData.BINDER_CRASH);
                this.i.m4568a(new ab(this.a, "unknown_placement", aVar));
                Activity activity = (Activity) this.b.get();
                if (activity != null) {
                    activity.startActivity(a);
                }
            }
            z = true;
        } catch (Throwable e) {
            Logger.e(Logger.AD_TAG, "error loading call-to-action URL " + null, e);
            z = false;
        }
        m4374a(z, true);
    }
}
