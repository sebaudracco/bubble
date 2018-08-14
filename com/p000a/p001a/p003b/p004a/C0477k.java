package com.p000a.p001a.p003b.p004a;

import com.p000a.p001a.C0449u;
import com.p000a.p001a.C0452t;
import com.p000a.p001a.C0552e;
import com.p000a.p001a.C0563r;
import com.p000a.p001a.p006d.C0460a;
import com.p000a.p001a.p006d.C0463c;
import com.p000a.p001a.p006d.C0544b;
import com.p000a.p001a.p007c.C0542a;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public final class C0477k extends C0452t<Time> {
    public static final C0449u f92a = new C04761();
    private final DateFormat f93b = new SimpleDateFormat("hh:mm:ss a");

    static class C04761 implements C0449u {
        C04761() {
        }

        public <T> C0452t<T> mo1792a(C0552e c0552e, C0542a<T> c0542a) {
            return c0542a.m403a() == Time.class ? new C0477k() : null;
        }
    }

    public synchronized Time m166a(C0460a c0460a) {
        Time time;
        if (c0460a.mo1801f() == C0544b.NULL) {
            c0460a.mo1805j();
            time = null;
        } else {
            try {
                time = new Time(this.f93b.parse(c0460a.mo1803h()).getTime());
            } catch (Throwable e) {
                throw new C0563r(e);
            }
        }
        return time;
    }

    public synchronized void m168a(C0463c c0463c, Time time) {
        c0463c.mo1820b(time == null ? null : this.f93b.format(time));
    }

    public /* synthetic */ Object mo1794b(C0460a c0460a) {
        return m166a(c0460a);
    }
}
