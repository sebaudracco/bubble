package com.p000a.p001a;

import com.p000a.p001a.p003b.C0528j;
import com.p000a.p001a.p006d.C0463c;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

public abstract class C0554j {
    public Number mo1834a() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public String mo1835b() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public double mo1836c() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public long mo1837d() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public int mo1838e() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public boolean mo1839f() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public boolean m459g() {
        return this instanceof C0555g;
    }

    public boolean m460h() {
        return this instanceof C0560m;
    }

    public boolean m461i() {
        return this instanceof C0561o;
    }

    public boolean m462j() {
        return this instanceof C0559l;
    }

    public C0560m m463k() {
        if (m460h()) {
            return (C0560m) this;
        }
        throw new IllegalStateException("Not a JSON Object: " + this);
    }

    public C0555g m464l() {
        if (m459g()) {
            return (C0555g) this;
        }
        throw new IllegalStateException("Not a JSON Array: " + this);
    }

    public C0561o m465m() {
        if (m461i()) {
            return (C0561o) this;
        }
        throw new IllegalStateException("Not a JSON Primitive: " + this);
    }

    Boolean mo1840n() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public String toString() {
        try {
            Writer stringWriter = new StringWriter();
            C0463c c0463c = new C0463c(stringWriter);
            c0463c.m111b(true);
            C0528j.m383a(this, c0463c);
            return stringWriter.toString();
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }
}
