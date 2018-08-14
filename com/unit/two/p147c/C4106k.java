package com.unit.two.p147c;

import android.content.Context;
import com.unit.two.p148d.C4107e;
import com.unit.two.p148d.C4124a;
import com.unit.two.p148d.C4125b;
import com.unit.two.p150e.C4128a;
import com.unit.two.p151f.C4144k;

public class C4106k {
    private static C4106k f9569b;
    private static volatile C4124a f9570c;
    private Context f9571a;
    private C4107e f9572d = new C4108l(this);
    private C4109v f9573e = new C4110m(this);
    private C4109v f9574f = new C4111n(this);

    static {
        String str = C4096a.dT;
    }

    private C4106k(Context context) {
        this.f9571a = context.getApplicationContext();
        C4125b.m12740a(context);
        f9570c = C4125b.m12738a();
        C4125b.m12740a(context);
        C4125b.m12741a(this.f9572d);
        if (f9570c != null && f9570c.m12736c()) {
            C4117t.m12706a(this.f9571a);
            f9570c.m12735b();
            C4117t.m12708a();
            m12688a();
        }
    }

    private void m12688a() {
        Object obj = null;
        int h = C4144k.m12814h(this.f9571a);
        long i = C4144k.m12815i(this.f9571a);
        if (f9570c != null && f9570c.m12736c() && h < f9570c.m12734a() && System.currentTimeMillis() - i >= ((long) (f9570c.m12735b() * 60)) * 1000) {
            obj = 1;
        }
        if (obj != null) {
            int h2 = C4144k.m12814h(this.f9571a);
            if (f9570c != null) {
                C4144k.m12795a(this.f9571a, h2 + 1);
                C4144k.m12802b(this.f9571a, System.currentTimeMillis());
                C4128a.m12748a(this.f9571a);
            }
        }
    }

    public static void m12689a(Context context) {
        if (f9569b == null) {
            synchronized (C4106k.class) {
                if (f9569b == null) {
                    f9569b = new C4106k(context);
                }
            }
        }
        C4117t.m12706a(context).m12713c(f9569b.f9573e);
        C4117t.m12706a(context).m12712b(f9569b.f9574f);
    }
}
