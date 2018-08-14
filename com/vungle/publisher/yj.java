package com.vungle.publisher;

import com.vungle.publisher.cz.C1596b;
import com.vungle.publisher.cz.c;
import com.vungle.publisher.env.C1613o;
import com.vungle.publisher.log.Logger;
import javax.inject.Inject;

/* compiled from: vungle */
public abstract class yj<A extends cn> extends pi {
    protected A f3479a;
    protected cy<?, ?, ?> f3480b;
    protected cz<?, ?, ?, A> f3481c;
    @Inject
    C1596b f3482d;
    @Inject
    yk f3483e;
    @Inject
    tj f3484f;
    @Inject
    C1613o f3485g;

    protected abstract void mo3023a();

    void m4909b() {
        this.f3479a = null;
        this.f3481c = null;
        this.f3480b = null;
        mo3023a();
    }

    protected void m4905a(ac<A> acVar) {
        m4906a(acVar.b());
    }

    void m4906a(A a) {
        if (this.f3479a == null || !this.f3479a.a(a)) {
            Logger.i(Logger.REPORT_TAG, "new ad " + a.B());
            this.f3479a = a;
            this.f3481c = this.f3482d.m3635b((cn) a);
            this.f3480b = this.f3481c.m3665x();
            Logger.d(Logger.REPORT_TAG, "current play: " + this.f3480b.toString());
            mo3023a();
            return;
        }
        Logger.v(Logger.REPORT_TAG, "same ad " + a.B());
    }

    public void onEvent(av<A> startPlayAdEvent) {
        try {
            Logger.d(Logger.REPORT_TAG, "received play ad start");
            p a = startPlayAdEvent.a();
            String c = startPlayAdEvent.c();
            cz czVar = this.f3481c;
            czVar.m3643a(c.c);
            s b = this.f3485g.m3923b(c);
            if (b != null) {
                czVar.m3650b(b.c);
                if (b.c) {
                    czVar.m3649b(a.getIncentivizedUserId());
                }
                if (a.getOrdinalViewCount() > 0) {
                    czVar.m3642a(a.getOrdinalViewCount());
                }
            }
            czVar.m3652c(Long.valueOf(startPlayAdEvent.e()));
            czVar.e_();
        } catch (Throwable e) {
            Logger.e(Logger.REPORT_TAG, "error processing ad start event", e);
        }
    }

    public void onEvent(bs destroyedErrorEndPlayEvent) {
        try {
            Logger.d(Logger.REPORT_TAG, "received destroyed ad end");
            m4904a(destroyedErrorEndPlayEvent.e());
        } catch (Exception e) {
            Logger.w(Logger.REPORT_TAG, "error processing destroyed ad end");
        }
    }

    protected void m4907a(jf jfVar) {
        m4908a(jfVar, null);
    }

    protected void m4908a(jf jfVar, Object obj) {
        try {
            this.f3480b.a(jfVar, obj);
        } catch (Throwable e) {
            Logger.e(Logger.REPORT_TAG, "error reporting event", e);
        }
    }

    protected void m4904a(long j) {
        unregister();
        cz czVar = this.f3481c;
        if (czVar == null) {
            Logger.d(Logger.REPORT_TAG, "no current ad report");
        } else {
            czVar.m3643a(c.d);
            czVar.m3645a(Long.valueOf(j));
            czVar.e_();
        }
        this.f3483e.m4914c();
        m4909b();
    }
}
