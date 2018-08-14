package com.p000a.p001a.p003b.p004a;

import com.p000a.p001a.C0449u;
import com.p000a.p001a.C0452t;
import com.p000a.p001a.C0552e;
import com.p000a.p001a.C0563r;
import com.p000a.p001a.p006d.C0460a;
import com.p000a.p001a.p006d.C0463c;
import com.p000a.p001a.p006d.C0544b;
import com.p000a.p001a.p007c.C0542a;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public final class C0475j extends C0452t<Date> {
    public static final C0449u f90a = new C04741();
    private final DateFormat f91b = new SimpleDateFormat("MMM d, yyyy");

    static class C04741 implements C0449u {
        C04741() {
        }

        public <T> C0452t<T> mo1792a(C0552e c0552e, C0542a<T> c0542a) {
            return c0542a.m403a() == Date.class ? new C0475j() : null;
        }
    }

    public synchronized Date m161a(C0460a c0460a) {
        Date date;
        if (c0460a.mo1801f() == C0544b.NULL) {
            c0460a.mo1805j();
            date = null;
        } else {
            try {
                date = new Date(this.f91b.parse(c0460a.mo1803h()).getTime());
            } catch (Throwable e) {
                throw new C0563r(e);
            }
        }
        return date;
    }

    public synchronized void m163a(C0463c c0463c, Date date) {
        c0463c.mo1820b(date == null ? null : this.f91b.format(date));
    }

    public /* synthetic */ Object mo1794b(C0460a c0460a) {
        return m161a(c0460a);
    }
}
