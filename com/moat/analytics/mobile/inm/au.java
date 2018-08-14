package com.moat.analytics.mobile.inm;

import android.os.Handler;
import android.os.Looper;
import com.moat.analytics.mobile.inm.base.exception.C3376a;
import com.moat.analytics.mobile.inm.base.functional.C3378a;

class au implements Runnable {
    private static final long f8554b = 90000;
    private final aa f8555a;
    private final String f8556c;
    private final aw f8557d;
    private aq f8558e;

    private au(String str, aa aaVar, aw awVar) {
        this.f8558e = aq.OFF;
        this.f8555a = aaVar;
        this.f8557d = awVar;
        this.f8556c = "https://z.moatads.com/" + str + "/android/" + "f379b01b219fb72670923cc96dc29bbe34213365".substring(0, 7) + "/status.json";
    }

    private void m11538a() {
        long j = 0;
        while (true) {
            long currentTimeMillis = System.currentTimeMillis() - j;
            if (currentTimeMillis < f8554b) {
                try {
                    Thread.sleep((10 + f8554b) - currentTimeMillis);
                } catch (InterruptedException e) {
                }
            }
            j = System.currentTimeMillis();
            aq b = m11539b();
            Handler handler = new Handler(Looper.getMainLooper());
            if (b.equals(this.f8558e)) {
                this.f8558e = b;
                handler.post(new av(this, b));
            } else {
                this.f8558e = b;
                handler.post(new av(this, b));
            }
        }
    }

    private aq m11539b() {
        C3378a a = this.f8555a.mo6468a(this.f8556c + "?ts=" + System.currentTimeMillis() + "&v=" + "1.7.11");
        if (!a.m11563c()) {
            return aq.OFF;
        }
        C3395u c3395u = new C3395u((String) a.m11561b());
        ar.f8550d = c3395u.m11621a();
        ar.f8551e = c3395u.m11623c();
        return c3395u.m11622b() ? aq.ON : aq.OFF;
    }

    public void run() {
        try {
            m11538a();
        } catch (Exception e) {
            this.f8558e = aq.OFF;
            C3376a.m11557a(e);
        }
    }
}
