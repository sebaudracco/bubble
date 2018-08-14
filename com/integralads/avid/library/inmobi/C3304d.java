package com.integralads.avid.library.inmobi;

import android.app.Activity;
import android.content.Context;
import com.integralads.avid.library.inmobi.C3296c.C3292b;
import com.integralads.avid.library.inmobi.C3308e.C3302a;
import com.integralads.avid.library.inmobi.p121a.C3286a;
import com.integralads.avid.library.inmobi.p125e.C3303b;
import com.integralads.avid.library.inmobi.p125e.C3307a;
import com.integralads.avid.library.inmobi.p126f.C3315d;
import com.integralads.avid.library.inmobi.session.C3323a;
import com.integralads.avid.library.inmobi.session.internal.C3333a;

/* compiled from: AvidManager */
public class C3304d implements C3292b, C3302a, C3303b {
    private static C3304d f8438a = new C3304d();
    private static Context f8439b;

    public static C3304d m11239b() {
        return f8438a;
    }

    public void m11247a(Context context) {
        if (f8439b == null) {
            f8439b = context.getApplicationContext();
            C3308e.m11266a().m11272a(f8439b);
            C3307a.m11255a().m11258a((C3303b) this);
            C3315d.m11296a(f8439b);
        }
    }

    public void m11249a(C3323a c3323a, C3333a c3333a) {
        C3307a.m11255a().m11259a(c3323a, c3333a);
    }

    public C3333a m11244a(String str) {
        return C3307a.m11255a().m11257a(str);
    }

    public void m11246a(Activity activity) {
        C3286a.m11179a().m11181a(activity);
    }

    private void m11240c() {
        C3308e.m11266a().m11273a((C3302a) this);
        C3308e.m11266a().m11274b();
        if (C3308e.m11266a().m11276d()) {
            C3317f.m11303a().m11317b();
        }
    }

    private void m11241d() {
        C3286a.m11179a().m11185c();
        C3317f.m11303a().m11318c();
        C3308e.m11266a().m11275c();
        f8439b = null;
    }

    private boolean m11242e() {
        return !C3307a.m11255a().m11264c();
    }

    private void m11243f() {
        C3307a.m11255a().m11258a(null);
        for (C3333a i : C3307a.m11255a().m11261b()) {
            i.m11406i().m11384b();
        }
        C3307a.m11255a().m11258a((C3303b) this);
    }

    public void mo6326a() {
        if (m11242e()) {
            m11243f();
            if (C3307a.m11255a().m11265d()) {
                m11240c();
            }
        }
    }

    public void mo6328a(boolean z) {
        if (z) {
            C3317f.m11303a().m11317b();
        } else {
            C3317f.m11303a().m11319d();
        }
    }

    public void mo6327a(C3307a c3307a) {
        if (!c3307a.m11264c() && !C3287a.m11187a()) {
            C3296c.m11205a();
            C3296c.m11207a((C3292b) this);
            C3296c.m11205a();
            C3296c.m11206a(f8439b);
        }
    }

    public void mo6329b(C3307a c3307a) {
        if (c3307a.m11265d() && C3287a.m11187a()) {
            m11240c();
        } else {
            m11241d();
        }
    }
}
