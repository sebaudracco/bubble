package com.elephant.data.p046f.p047a;

import android.content.Context;
import com.elephant.data.p048g.C1814b;
import java.util.ArrayList;
import java.util.List;

public final class C1800g {
    private static C1800g f3787b;
    private List f3788a;
    private Context f3789c;

    static {
        String str = C1814b.cC;
    }

    private C1800g(Context context) {
        this.f3789c = context;
        C1794b a = C1794b.m5169a(this.f3789c);
        if (a != null) {
            if (this.f3788a == null) {
                this.f3788a = new ArrayList();
            }
            if (!this.f3788a.contains(a)) {
                this.f3788a.add(a);
            }
        }
    }

    public static C1800g m5175a(Context context) {
        if (f3787b == null) {
            f3787b = new C1800g(context);
        }
        return f3787b;
    }

    public final void m5176a() {
        if (this.f3788a != null) {
            for (C1793a a : this.f3788a) {
                a.mo3540a();
            }
        }
    }
}
