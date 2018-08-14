package com.p000a.p001a;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class C0555g extends C0554j implements Iterable<C0554j> {
    private final List<C0554j> f294a = new ArrayList();

    public Number mo1834a() {
        if (this.f294a.size() == 1) {
            return ((C0554j) this.f294a.get(0)).mo1834a();
        }
        throw new IllegalStateException();
    }

    public void m468a(C0554j c0554j) {
        Object obj;
        if (c0554j == null) {
            obj = C0559l.f295a;
        }
        this.f294a.add(obj);
    }

    public String mo1835b() {
        if (this.f294a.size() == 1) {
            return ((C0554j) this.f294a.get(0)).mo1835b();
        }
        throw new IllegalStateException();
    }

    public double mo1836c() {
        if (this.f294a.size() == 1) {
            return ((C0554j) this.f294a.get(0)).mo1836c();
        }
        throw new IllegalStateException();
    }

    public long mo1837d() {
        if (this.f294a.size() == 1) {
            return ((C0554j) this.f294a.get(0)).mo1837d();
        }
        throw new IllegalStateException();
    }

    public int mo1838e() {
        if (this.f294a.size() == 1) {
            return ((C0554j) this.f294a.get(0)).mo1838e();
        }
        throw new IllegalStateException();
    }

    public boolean equals(Object obj) {
        return obj == this || ((obj instanceof C0555g) && ((C0555g) obj).f294a.equals(this.f294a));
    }

    public boolean mo1839f() {
        if (this.f294a.size() == 1) {
            return ((C0554j) this.f294a.get(0)).mo1839f();
        }
        throw new IllegalStateException();
    }

    public int hashCode() {
        return this.f294a.hashCode();
    }

    public Iterator<C0554j> iterator() {
        return this.f294a.iterator();
    }
}
