package com.vungle.publisher;

import com.vungle.publisher.cz.C1596b;
import com.vungle.publisher.cz.c;
import com.vungle.publisher.env.C1614r;
import com.vungle.publisher.fg.C1621a;
import com.vungle.publisher.ic.C1635a;
import com.vungle.publisher.log.Logger;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
/* compiled from: vungle */
public class yk {
    @Inject
    qg f3486a;
    @Inject
    C1596b f3487b;
    @Inject
    C1621a f3488c;
    @Inject
    vc f3489d;
    @Inject
    C1614r f3490e;
    @Inject
    C1635a f3491f;

    @Inject
    yk() {
    }

    public void m4910a() {
        this.f3487b.m3639d();
        m4913b();
        m4914c();
    }

    public void m4912a(Integer num) {
        Logger.i(Logger.REPORT_TAG, "deleting report " + num);
        this.f3488c.a(new Integer[]{num});
    }

    void m4913b() {
        if (this.f3490e.m3949b()) {
            Logger.v(Logger.REPORT_TAG, "install already reported");
            return;
        }
        Logger.d(Logger.REPORT_TAG, "reporting install");
        this.f3489d.m4734b();
    }

    void m4911a(cz<?, ?, ?, ?> czVar) {
        String B = czVar.m3575B();
        try {
            Logger.d(Logger.REPORT_TAG, "sending " + B);
            this.f3489d.m4727a((cz) czVar);
        } catch (Throwable e) {
            Logger.e(Logger.REPORT_TAG, "error sending " + B, e);
            czVar.m3643a(c.b);
            czVar.f_();
        }
    }

    public void m4914c() {
        try {
            List<cz> c = this.f3487b.m3638c();
            Logger.i(Logger.REPORT_TAG, "sending " + c.size() + " ad reports");
            for (cz a : c) {
                m4911a(a);
            }
        } finally {
            this.f3486a.m4568a(new aj());
        }
    }
}
