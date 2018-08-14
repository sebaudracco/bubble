package com.unit.two.p148d.p149a;

import android.content.Context;
import com.unit.two.p147c.C4096a;
import com.unit.two.p151f.C4144k;
import com.unit.two.p151f.C4145l;

public class C4123e {
    private static C4123e f9603a;
    private Context f9604b;

    static {
        String str = C4096a.bE;
    }

    private C4123e(Context context) {
        this.f9604b = context;
        if (C4144k.m12812f(this.f9604b).getLong(C4144k.f9687a, 0) < 1) {
            C4144k.m12799a(this.f9604b, C4144k.f9687a, Long.valueOf(System.currentTimeMillis()));
        }
        if (C4144k.m12812f(this.f9604b).getInt(C4144k.f9689c, 0) <= 0) {
            C4144k.m12799a(this.f9604b, C4144k.f9689c, Integer.valueOf(C4145l.m12827b(this.f9604b)));
        }
    }

    public static C4123e m12732a(Context context) {
        if (f9603a != null) {
            return f9603a;
        }
        synchronized (C4123e.class) {
            if (f9603a != null) {
                C4123e c4123e = f9603a;
                return c4123e;
            }
            f9603a = new C4123e(context.getApplicationContext());
            return f9603a;
        }
    }

    public final void m12733a(C4120b c4120b) {
        new C4119a(this.f9604b, c4120b.m12719a(C4144k.m12812f(this.f9604b).getString(C4144k.f9688b, ""))).m12667b();
    }
}
