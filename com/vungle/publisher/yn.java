package com.vungle.publisher;

import com.vungle.publisher.co.C1593a;
import com.vungle.publisher.log.Logger;
import javax.inject.Inject;
import javax.inject.Singleton;

/* compiled from: vungle */
public class yn extends yj<dq> {
    @Inject
    C1593a f3493h;

    @Singleton
    /* compiled from: vungle */
    public static class C1677a {
        @Inject
        yn f3492a;

        @Inject
        C1677a() {
        }

        public yn m4915a(dq dqVar) {
            this.f3492a.m4906a((cn) dqVar);
            return this.f3492a;
        }
    }

    @Inject
    yn() {
    }

    protected void mo3023a() {
    }

    public void onEvent(si event) {
        jf a = event.a();
        String b = event.b();
        Logger.d(Logger.REPORT_TAG, "received mraid user action event: " + a.toString() + (b == null ? "" : ", w/ value " + b));
        m4908a(a, b);
        if (rx.a.equals(a) || rx.b.equals(a)) {
            m4904a(event.e());
        }
    }

    public void onEvent(sg event) {
        Logger.d(Logger.REPORT_TAG, "received mraid tpat event: " + event.a().toString());
        m4916b(event.a());
    }

    public void onEvent(sm event) {
        this.f3493h.m3563a((Integer) this.c.c_(), event.a()).d_();
    }

    public void onEvent(av<dq> startMraidAdEvent) {
        super.onEvent((av) startMraidAdEvent);
        try {
            this.b.a(Long.valueOf(startMraidAdEvent.e()));
            this.b.f_();
        } catch (Throwable e) {
            Logger.e(Logger.REPORT_TAG, "error updating play start millis", e);
        }
    }

    private void m4916b(jf jfVar) {
        if (this.a == null) {
            Logger.w(Logger.REPORT_TAG, "null ad in AdReportingHandler - cannot track event " + jfVar);
            return;
        }
        Logger.v(Logger.REPORT_TAG, "tpat event " + jfVar.toString());
        this.f.m4672a(this.a, jfVar, ((dq) this.a).a(jfVar));
    }
}
