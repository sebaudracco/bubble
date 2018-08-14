package com.yandex.metrica.impl.ob;

import android.os.SystemClock;
import android.text.TextUtils;
import com.yandex.metrica.impl.ba;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import org.json.JSONObject;

public abstract class bg {
    protected C4503t f11908a;
    protected bk f11909b;
    protected long f11910c = this.f11909b.mo7046a(-1);
    protected long f11911d = this.f11909b.mo7049c(SystemClock.elapsedRealtime());
    protected AtomicLong f11912e = new AtomicLong(this.f11909b.mo7051e(0));
    private boolean f11913f = this.f11909b.mo7048b(true);
    private volatile C4393a f11914g;

    static class C4393a {
        private final String f11916a;
        private final String f11917b;
        private final String f11918c;
        private final String f11919d;
        private final String f11920e;
        private final String f11921f;
        private final int f11922g;

        C4393a(JSONObject jSONObject) {
            this.f11916a = jSONObject.optString("kitVer");
            this.f11917b = jSONObject.optString("clientKitVer");
            this.f11918c = jSONObject.optString("kitBuildNumber");
            this.f11919d = jSONObject.optString("appVer");
            this.f11920e = jSONObject.optString("appBuild");
            this.f11921f = jSONObject.optString("osVer");
            this.f11922g = jSONObject.optInt("osApiLev", -1);
        }

        boolean m15278a(ba baVar) {
            return TextUtils.equals(baVar.m14839h(), this.f11916a) && TextUtils.equals(baVar.m14841i(), this.f11917b) && TextUtils.equals(baVar.m14845k(), this.f11918c) && TextUtils.equals(baVar.m14867x(), this.f11919d) && TextUtils.equals(baVar.m14869z(), this.f11920e) && TextUtils.equals(baVar.m14857q(), this.f11921f) && this.f11922g == baVar.m14859r();
        }
    }

    protected abstract bl mo7044a();

    protected abstract int mo7045b();

    bg(C4503t c4503t, bk bkVar) {
        this.f11908a = c4503t;
        this.f11909b = bkVar;
        this.f11909b.mo7050d(this.f11911d).m15241a();
    }

    long m15228c() {
        return this.f11910c;
    }

    synchronized void m15229d() {
        this.f11910c = System.currentTimeMillis() / 1000;
        this.f11912e.set(0);
        this.f11911d = SystemClock.elapsedRealtime();
        this.f11914g = null;
        this.f11909b.mo7055i(this.f11910c).mo7054h(SystemClock.elapsedRealtime() / 1000).mo7050d(this.f11911d).mo7052f(this.f11912e.get()).m15241a();
        this.f11908a.m16165i().m15351a(this.f11910c, mo7044a());
        m15226a(true);
    }

    long m15230e() {
        return this.f11909b.mo7053g(0) - TimeUnit.MILLISECONDS.toSeconds(this.f11911d);
    }

    boolean m15231f() {
        boolean z;
        if (this.f11910c >= 0) {
            long elapsedRealtime = (SystemClock.elapsedRealtime() / 1000) - this.f11909b.mo7053g(0);
            long g = m15232g();
            if (elapsedRealtime < 0 || elapsedRealtime >= ((long) mo7045b()) || g >= bh.f11925c) {
                z = true;
            } else {
                z = false;
            }
            if (!z) {
                C4393a l = m15224l();
                z = l != null ? !l.m15278a(this.f11908a.mo7111h()) : false;
                if (!z) {
                    z = false;
                    return z;
                }
            }
        }
        z = true;
        if (z) {
        }
    }

    long m15232g() {
        return TimeUnit.MILLISECONDS.toSeconds(SystemClock.elapsedRealtime() - this.f11911d);
    }

    synchronized void m15233h() {
        this.f11909b.mo7054h(-2147483648L).m15241a();
        this.f11914g = null;
    }

    void m15234i() {
        this.f11909b.mo7054h(SystemClock.elapsedRealtime() / 1000).m15241a();
    }

    long m15235j() {
        long andIncrement = this.f11912e.getAndIncrement();
        this.f11909b.mo7052f(this.f11912e.get()).m15241a();
        return andIncrement;
    }

    boolean m15236k() {
        return this.f11913f && m15228c() > 0;
    }

    void m15226a(boolean z) {
        if (this.f11913f != z) {
            this.f11913f = z;
            this.f11909b.mo7047a(this.f11913f).m15241a();
        }
    }

    private C4393a m15224l() {
        if (this.f11914g == null) {
            synchronized (this) {
                if (this.f11914g == null) {
                    try {
                        Object asString = this.f11908a.m16165i().m15357c(m15228c(), mo7044a()).getAsString("report_request_parameters");
                        if (!TextUtils.isEmpty(asString)) {
                            this.f11914g = new C4393a(new JSONObject(asString));
                        }
                    } catch (Exception e) {
                    }
                }
            }
        }
        return this.f11914g;
    }
}
