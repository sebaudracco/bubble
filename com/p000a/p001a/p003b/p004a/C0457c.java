package com.p000a.p001a.p003b.p004a;

import com.p000a.p001a.C0449u;
import com.p000a.p001a.C0452t;
import com.p000a.p001a.C0552e;
import com.p000a.p001a.C0563r;
import com.p000a.p001a.p003b.p004a.p005a.C0451a;
import com.p000a.p001a.p006d.C0460a;
import com.p000a.p001a.p006d.C0463c;
import com.p000a.p001a.p006d.C0544b;
import com.p000a.p001a.p007c.C0542a;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.util.Date;
import java.util.Locale;

public final class C0457c extends C0452t<Date> {
    public static final C0449u f23a = new C04561();
    private final DateFormat f24b = DateFormat.getDateTimeInstance(2, 2, Locale.US);
    private final DateFormat f25c = DateFormat.getDateTimeInstance(2, 2);

    static class C04561 implements C0449u {
        C04561() {
        }

        public <T> C0452t<T> mo1792a(C0552e c0552e, C0542a<T> c0542a) {
            return c0542a.m403a() == Date.class ? new C0457c() : null;
        }
    }

    private synchronized Date m31a(String str) {
        Date parse;
        try {
            parse = this.f25c.parse(str);
        } catch (ParseException e) {
            try {
                parse = this.f24b.parse(str);
            } catch (ParseException e2) {
                try {
                    parse = C0451a.m17a(str, new ParsePosition(0));
                } catch (Throwable e3) {
                    throw new C0563r(str, e3);
                }
            }
        }
        return parse;
    }

    public Date m32a(C0460a c0460a) {
        if (c0460a.mo1801f() != C0544b.NULL) {
            return m31a(c0460a.mo1803h());
        }
        c0460a.mo1805j();
        return null;
    }

    public synchronized void m34a(C0463c c0463c, Date date) {
        if (date == null) {
            c0463c.mo1825f();
        } else {
            c0463c.mo1820b(this.f24b.format(date));
        }
    }

    public /* synthetic */ Object mo1794b(C0460a c0460a) {
        return m32a(c0460a);
    }
}
