package com.unit.two.p147c;

import android.content.Context;
import com.unit.two.p148d.p149a.C4120b;
import com.unit.two.p148d.p149a.C4122d;
import com.unit.two.p151f.C4140g;
import com.unit.two.p151f.C4143j;
import com.unit.two.p151f.C4144k;
import com.unit.two.p151f.C4145l;

public class C4099d {
    private static final Object f9549a = new Object();
    private static C4099d f9550e;
    private C4100e f9551b;
    private Context f9552c;
    private long f9553d = -1;

    static {
        String str = C4096a.dr;
    }

    private C4099d(Context context) {
        if (context == null) {
            throw new IllegalArgumentException(C4096a.ds);
        }
        this.f9552c = context;
    }

    public static C4099d m12676a(Context context) {
        if (f9550e == null) {
            synchronized (C4099d.class) {
                if (f9550e == null) {
                    f9550e = new C4099d(context);
                }
            }
        }
        return f9550e;
    }

    private C4100e m12677b(Context context) {
        this.f9552c = context;
        if (!m12679c()) {
            return m12680d();
        }
        synchronized (f9549a) {
            if (m12679c()) {
                C4100e e = m12681e();
                return e;
            }
            e = m12680d();
            return e;
        }
    }

    private void m12678b() {
        this.f9553d = System.currentTimeMillis();
        C4097b.m12668a(this.f9552c).m12670a(this.f9553d);
    }

    private boolean m12679c() {
        long j = this.f9553d;
        if (j == -1) {
            j = C4097b.m12668a(this.f9552c).m12669a();
        }
        return System.currentTimeMillis() - j >= 28800000;
    }

    private C4100e m12680d() {
        if (this.f9551b != null) {
            return this.f9551b;
        }
        this.f9551b = C4100e.m12684a(C4097b.m12668a(this.f9552c).m12672b());
        return this.f9551b;
    }

    private C4100e m12681e() {
        if (!C4145l.m12833c(this.f9552c)) {
            return C4100e.m12683a();
        }
        try {
            String a = new C4140g(C4104i.f9563a).m12775a(C4143j.f9685b).m12777a(C4122d.m12730a(this.f9552c, new C4120b(C4144k.m12801b(this.f9552c), null).m12721a(87).m12718a(1).m12720a(true), true)).m12778a();
            C4097b.m12668a(this.f9552c).m12671a(a);
            this.f9551b = C4100e.m12684a(a);
            m12678b();
            return this.f9551b;
        } catch (Throwable th) {
            m12678b();
            return C4100e.m12683a();
        }
    }

    public final boolean m12682a() {
        return m12677b(this.f9552c).f9554a;
    }
}
