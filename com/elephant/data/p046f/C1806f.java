package com.elephant.data.p046f;

import android.content.Context;
import com.elephant.data.p035a.p036a.C1718g;
import com.elephant.data.p048g.C1814b;
import com.elephant.data.p048g.C1816d;
import com.elephant.data.p048g.p050b.C1813b;

public final class C1806f {
    private static C1806f f3824b;
    private Context f3825a;
    private C1804d f3826c;
    private C1796h f3827d;

    static {
        String str = C1814b.dv;
    }

    private C1806f(Context context) {
        this.f3825a = context.getApplicationContext();
    }

    public static synchronized C1806f m5221a(Context context) {
        C1806f c1806f;
        synchronized (C1806f.class) {
            if (f3824b == null) {
                f3824b = new C1806f(context);
            }
            c1806f = f3824b;
        }
        return c1806f;
    }

    public final C1804d m5223a() {
        if (this.f3826c == null) {
            this.f3826c = new C1804d(C1813b.m5261c(this.f3825a));
        }
        return this.f3826c;
    }

    public final void m5224a(Context context, C1803c c1803c, C1796h c1796h) {
        this.f3827d = c1796h;
        if (C1816d.m5323n(context)) {
            C1813b.m5254a(this.f3825a, System.currentTimeMillis());
            new C1718g(new C1807g(this, c1803c), new C1808i()).m4951c();
            return;
        }
        c1796h.mo3541a();
    }
}
