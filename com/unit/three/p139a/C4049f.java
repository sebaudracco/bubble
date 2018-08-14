package com.unit.three.p139a;

import android.text.TextUtils;
import com.unit.three.p138b.C4053c;
import com.unit.three.p141c.C4077d;
import java.util.ArrayList;
import java.util.List;

public class C4049f {
    private static C4049f f9354b;
    private long f9355a;
    private List f9356c = new ArrayList();

    public interface C4036a {
        void mo6914e();
    }

    private C4049f() {
    }

    public static C4049f m12492a() {
        if (f9354b == null) {
            synchronized (C4049f.class) {
                if (f9354b == null) {
                    f9354b = new C4049f();
                }
            }
        }
        return f9354b;
    }

    public final void m12493a(C4036a c4036a) {
        this.f9356c.add(c4036a);
    }

    public final void m12494a(Throwable th) {
        if (!(th == null || TextUtils.isEmpty(th.getMessage()))) {
            new C4077d(th.getMessage(), C4053c.m12503a().m12517d(C4053c.m12503a().m12515b())).m12555b();
        }
        if (System.currentTimeMillis() - this.f9355a >= 5000) {
            this.f9355a = System.currentTimeMillis();
            for (C4036a c4036a : this.f9356c) {
                if (c4036a != null) {
                    c4036a.mo6914e();
                }
            }
        }
    }
}
