package com.inmobi.ads;

import android.os.SystemClock;
import com.inmobi.commons.core.network.C3141a;
import com.inmobi.commons.core.network.C3141a.C3048a;
import com.inmobi.commons.core.network.C3143c;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.signals.C3276n;

/* compiled from: AdNetworkClient */
final class C3049e implements C3048a {
    private static final String f7397a = C3049e.class.getSimpleName();
    private C3050f f7398b;
    private C3030a f7399c;
    private long f7400d = 0;

    /* compiled from: AdNetworkClient */
    public interface C3030a {
        void mo6213a(C3052g c3052g);

        void mo6214b(C3052g c3052g);
    }

    public C3049e(C3050f c3050f, C3030a c3030a) {
        this.f7398b = c3050f;
        this.f7399c = c3030a;
    }

    public void m9744a() {
        this.f7400d = SystemClock.elapsedRealtime();
        new C3141a(this.f7398b, this).m10339a();
    }

    public void mo6236a(C3143c c3143c) {
        C3052g c3052g = new C3052g(this.f7398b, c3143c);
        try {
            C3276n.m10977a().m10979a(this.f7398b.m9772t());
            C3276n.m10977a().m10981b(c3143c.m10356f());
            C3276n.m10977a().m10988g(SystemClock.elapsedRealtime() - this.f7400d);
            this.f7399c.mo6213a(c3052g);
        } catch (Exception e) {
            Logger.m10359a(InternalLogLevel.INTERNAL, f7397a, "Handling ad fetch success encountered an unexpected error: " + e.getMessage());
        }
    }

    public void mo6237b(C3143c c3143c) {
        C3052g c3052g = new C3052g(this.f7398b, c3143c);
        Logger.m10359a(InternalLogLevel.INTERNAL, f7397a, "Ad fetch failed:" + c3052g.m9791d().m10333b());
        try {
            C3276n.m10977a().m10979a(this.f7398b.m9772t());
            C3276n.m10977a().m10981b(c3143c.m10356f());
            this.f7399c.mo6214b(c3052g);
        } catch (Exception e) {
            Logger.m10359a(InternalLogLevel.INTERNAL, f7397a, "Handling ad fetch failed encountered an unexpected error: " + e.getMessage());
        }
    }
}
