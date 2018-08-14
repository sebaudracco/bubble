package com.inmobi.signals;

import com.inmobi.commons.core.configs.C3045a;
import com.inmobi.commons.core.configs.C3121b;
import com.inmobi.commons.core.configs.C3121b.C2911b;
import com.inmobi.commons.core.p115d.C3135c;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.commons.core.utilities.info.C3164f;
import com.inmobi.commons.core.utilities.uid.C3169d;
import com.inmobi.signals.C3280p.C3278a;
import com.inmobi.signals.C3280p.C3279b;

/* compiled from: SignalsComponent */
public class C3277o implements C2911b {
    private static final String f8252a = C3277o.class.getSimpleName();
    private static final Object f8253b = new Object();
    private static volatile C3277o f8254c;
    private C3270i f8255d;
    private C3266g f8256e;
    private C3280p f8257f = new C3280p();
    private boolean f8258g = false;

    public static C3277o m10989a() {
        C3277o c3277o = f8254c;
        if (c3277o == null) {
            synchronized (f8253b) {
                c3277o = f8254c;
                if (c3277o == null) {
                    c3277o = new C3277o();
                    f8254c = c3277o;
                }
            }
        }
        return c3277o;
    }

    private C3277o() {
        C3121b.m10178a().m10190a(this.f8257f, (C2911b) this);
        C3164f.m10487a().m10491a(m10994e().m11069i());
        LocationInfo.m10813a().m10827a(m10994e().m11068h());
        C3135c.m10255a().m10281a(this.f8257f.mo6231a(), this.f8257f.m11086e());
    }

    public void mo6102a(C3045a c3045a) {
        this.f8257f = (C3280p) c3045a;
        LocationInfo.m10813a().m10827a(m10994e().m11068h());
        C3164f.m10487a().m10491a(m10994e().m11069i());
        C3135c.m10255a().m10281a(this.f8257f.mo6231a(), this.f8257f.m11086e());
    }

    public synchronized void m10991b() {
        if (!this.f8258g) {
            Logger.m10359a(InternalLogLevel.INTERNAL, f8252a, "Starting signals component.");
            this.f8258g = true;
            m10996g();
            LocationInfo.m10813a().m10828b();
        }
    }

    public synchronized void m10992c() {
        if (this.f8258g) {
            Logger.m10359a(InternalLogLevel.INTERNAL, f8252a, "Stopping signals component.");
            this.f8258g = false;
            m10997h();
            LocationInfo.m10813a().m10829c();
        }
    }

    C3169d m10993d() {
        return new C3169d(this.f8257f.m9709r().m10168a());
    }

    public C3279b m10994e() {
        return this.f8257f.m11087f();
    }

    public C3278a m10995f() {
        return this.f8257f.m11088g();
    }

    synchronized void m10996g() {
        if (!this.f8258g) {
            Logger.m10359a(InternalLogLevel.INTERNAL, f8252a, "Ice can not be started as Signals component has not been started.");
        } else if (m10994e().m11061a()) {
            C3276n.m10977a().m10980b();
            if (this.f8255d == null) {
                this.f8255d = new C3270i();
                this.f8255d.m10957a();
            } else {
                this.f8255d.m10957a();
            }
        } else {
            Logger.m10359a(InternalLogLevel.INTERNAL, f8252a, "User data collection is disabled.");
        }
    }

    void m10997h() {
        C3276n.m10977a().m10982c();
        if (this.f8255d != null) {
            this.f8255d.m10959c();
        }
    }

    public void m10998i() {
        if (!this.f8258g) {
            Logger.m10359a(InternalLogLevel.INTERNAL, f8252a, "Carb can not be started as Signals component has not been started.");
        } else if (!m10995f().m11015a()) {
            Logger.m10359a(InternalLogLevel.INTERNAL, f8252a, "Carb is disabled.");
        } else if (this.f8256e == null) {
            this.f8256e = new C3266g();
            this.f8256e.m10940a(m10995f());
        } else {
            this.f8256e.m10940a(m10995f());
        }
    }
}
