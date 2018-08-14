package com.vungle.publisher;

import android.os.SystemClock;
import com.vungle.publisher.log.Logger;
import javax.inject.Inject;

/* compiled from: vungle */
public abstract class ul extends tx {
    @Inject
    protected bw f3348b;
    private int f3349c = 10;
    private int f3350d = 5;
    private int f3351e;
    private int f3352f = 2000;
    private int f3353g = 60000;

    protected void m4661c(int i) {
        this.f3350d = i;
    }

    protected void m4662d(int i) {
        this.f3349c = i;
    }

    protected void mo3017b(ub ubVar, tw twVar) {
        un b = ubVar.m4699b();
        int b2 = b.b();
        if (!(m4663e(b2) || m4659a(b.a()))) {
            int b3 = twVar.m4688b();
            if (m4664f(b3)) {
                int c = b.c();
                if (!m4656b(b3)) {
                    c = b.e();
                }
                if (!m4665g(c)) {
                    c = m4666h(b2);
                    Logger.d(Logger.NETWORK_TAG, "Retrying " + ubVar + " in " + (c / 1000) + " seconds");
                    this.f3348b.m3479b(new um(ubVar), ubVar.m4700c(), (long) c);
                    return;
                }
            }
        }
        super.mo3017b(ubVar, twVar);
    }

    protected final boolean m4663e(int i) {
        return this.f3349c > 0 && i > this.f3349c;
    }

    protected final boolean m4659a(long j) {
        return this.f3351e > 0 && SystemClock.elapsedRealtime() - j >= ((long) this.f3351e);
    }

    protected boolean m4664f(int i) {
        return (m4654a(i) || i == 601) ? false : true;
    }

    protected final boolean m4665g(int i) {
        return this.f3350d > 0 && i > this.f3350d;
    }

    protected int m4666h(int i) {
        return zn.a(i, this.f3352f, this.f3353g);
    }
}
