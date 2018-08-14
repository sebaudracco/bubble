package com.p000a.p001a.p003b.p004a;

import com.p000a.p001a.C0554j;
import com.p000a.p001a.C0555g;
import com.p000a.p001a.C0559l;
import com.p000a.p001a.C0560m;
import com.p000a.p001a.C0561o;
import com.p000a.p001a.p006d.C0463c;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public final class C0464f extends C0463c {
    private static final Writer f60a = new C04621();
    private static final C0561o f61b = new C0561o("closed");
    private final List<C0554j> f62c = new ArrayList();
    private String f63d;
    private C0554j f64e = C0559l.f295a;

    static class C04621 extends Writer {
        C04621() {
        }

        public void close() {
            throw new AssertionError();
        }

        public void flush() {
            throw new AssertionError();
        }

        public void write(char[] cArr, int i, int i2) {
            throw new AssertionError();
        }
    }

    public C0464f() {
        super(f60a);
    }

    private void m122a(C0554j c0554j) {
        if (this.f63d != null) {
            if (!c0554j.m462j() || m121i()) {
                ((C0560m) m123j()).m475a(this.f63d, c0554j);
            }
            this.f63d = null;
        } else if (this.f62c.isEmpty()) {
            this.f64e = c0554j;
        } else {
            C0554j j = m123j();
            if (j instanceof C0555g) {
                ((C0555g) j).m468a(c0554j);
                return;
            }
            throw new IllegalStateException();
        }
    }

    private C0554j m123j() {
        return (C0554j) this.f62c.get(this.f62c.size() - 1);
    }

    public C0463c mo1813a(long j) {
        m122a(new C0561o(Long.valueOf(j)));
        return this;
    }

    public C0463c mo1814a(Boolean bool) {
        if (bool == null) {
            return mo1825f();
        }
        m122a(new C0561o(bool));
        return this;
    }

    public C0463c mo1815a(Number number) {
        if (number == null) {
            return mo1825f();
        }
        if (!m119g()) {
            double doubleValue = number.doubleValue();
            if (Double.isNaN(doubleValue) || Double.isInfinite(doubleValue)) {
                throw new IllegalArgumentException("JSON forbids NaN and infinities: " + number);
            }
        }
        m122a(new C0561o(number));
        return this;
    }

    public C0463c mo1816a(String str) {
        if (this.f62c.isEmpty() || this.f63d != null) {
            throw new IllegalStateException();
        } else if (m123j() instanceof C0560m) {
            this.f63d = str;
            return this;
        } else {
            throw new IllegalStateException();
        }
    }

    public C0463c mo1817a(boolean z) {
        m122a(new C0561o(Boolean.valueOf(z)));
        return this;
    }

    public C0554j mo1818a() {
        if (this.f62c.isEmpty()) {
            return this.f64e;
        }
        throw new IllegalStateException("Expected one JSON element but was " + this.f62c);
    }

    public C0463c mo1819b() {
        C0554j c0555g = new C0555g();
        m122a(c0555g);
        this.f62c.add(c0555g);
        return this;
    }

    public C0463c mo1820b(String str) {
        if (str == null) {
            return mo1825f();
        }
        m122a(new C0561o(str));
        return this;
    }

    public C0463c mo1821c() {
        if (this.f62c.isEmpty() || this.f63d != null) {
            throw new IllegalStateException();
        } else if (m123j() instanceof C0555g) {
            this.f62c.remove(this.f62c.size() - 1);
            return this;
        } else {
            throw new IllegalStateException();
        }
    }

    public void close() {
        if (this.f62c.isEmpty()) {
            this.f62c.add(f61b);
            return;
        }
        throw new IOException("Incomplete document");
    }

    public C0463c mo1823d() {
        C0554j c0560m = new C0560m();
        m122a(c0560m);
        this.f62c.add(c0560m);
        return this;
    }

    public C0463c mo1824e() {
        if (this.f62c.isEmpty() || this.f63d != null) {
            throw new IllegalStateException();
        } else if (m123j() instanceof C0560m) {
            this.f62c.remove(this.f62c.size() - 1);
            return this;
        } else {
            throw new IllegalStateException();
        }
    }

    public C0463c mo1825f() {
        m122a(C0559l.f295a);
        return this;
    }

    public void flush() {
    }
}
