package com.elephant.data.p046f;

import android.content.Context;
import com.elephant.data.p035a.p036a.C1716e;
import com.elephant.data.p039b.p040a.C1731a;
import com.elephant.data.p048g.C1814b;
import com.elephant.data.p048g.C1816d;
import com.elephant.data.p048g.C1821i;
import com.elephant.data.p048g.p050b.C1813b;

public class C1801a {
    private static final Object f3790a = new Object();
    private static C1801a f3791e;
    private C1802b f3792b;
    private Context f3793c;
    private long f3794d = -1;

    static {
        String str = C1814b.cn;
    }

    private C1801a(Context context) {
        if (context == null) {
            throw new IllegalArgumentException(C1814b.co);
        }
        this.f3793c = context;
    }

    public static C1801a m5177a(Context context) {
        if (f3791e == null) {
            synchronized (C1801a.class) {
                if (f3791e == null) {
                    f3791e = new C1801a(context);
                }
            }
        }
        return f3791e;
    }

    private C1802b m5178b(Context context) {
        C1821i.m5345a();
        this.f3793c = context;
        if (!m5180c()) {
            return m5181d();
        }
        synchronized (f3790a) {
            if (m5180c()) {
                C1802b e = m5182e();
                return e;
            }
            e = m5181d();
            return e;
        }
    }

    private void m5179b() {
        this.f3794d = System.currentTimeMillis();
        C1813b.m5268e(this.f3793c, this.f3794d);
    }

    private boolean m5180c() {
        long j = this.f3794d;
        if (j == -1) {
            j = C1813b.m5273j(this.f3793c);
        }
        return System.currentTimeMillis() - j >= 28800000;
    }

    private C1802b m5181d() {
        if (this.f3792b != null) {
            return this.f3792b;
        }
        this.f3792b = C1802b.m5185a(C1813b.m5274k(this.f3793c));
        return this.f3792b;
    }

    private C1802b m5182e() {
        if (!C1816d.m5323n(this.f3793c)) {
            return C1802b.m5184a();
        }
        try {
            String a = new C1716e().m4957a(C1731a.f3558a, C1805e.m5216a(this.f3793c, new C1803c(C1813b.m5252a(this.f3793c)).m5188a(C1814b.cp).m5186a(1).m5187a(true), true), null);
            C1813b.m5266d(this.f3793c, a);
            this.f3792b = C1802b.m5185a(a);
            m5179b();
            return this.f3792b;
        } catch (Throwable th) {
            th.printStackTrace();
            m5179b();
            return C1802b.m5184a();
        }
    }

    public final boolean m5183a() {
        return m5178b(this.f3793c).f3795a;
    }
}
