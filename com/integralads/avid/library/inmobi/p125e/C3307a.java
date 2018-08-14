package com.integralads.avid.library.inmobi.p125e;

import android.view.View;
import com.integralads.avid.library.inmobi.session.C3323a;
import com.integralads.avid.library.inmobi.session.internal.C3306c;
import com.integralads.avid.library.inmobi.session.internal.C3333a;
import java.util.Collection;
import java.util.HashMap;

/* compiled from: AvidAdSessionRegistry */
public class C3307a implements C3306c {
    private static C3307a f8441a = new C3307a();
    private final HashMap<String, C3333a> f8442b = new HashMap();
    private final HashMap<String, C3323a> f8443c = new HashMap();
    private C3303b f8444d;
    private int f8445e = 0;

    public static C3307a m11255a() {
        return f8441a;
    }

    public void m11258a(C3303b c3303b) {
        this.f8444d = c3303b;
    }

    public void m11259a(C3323a c3323a, C3333a c3333a) {
        this.f8443c.put(c3323a.m11358a(), c3323a);
        this.f8442b.put(c3323a.m11358a(), c3333a);
        c3333a.m11393a((C3306c) this);
        if (this.f8443c.size() == 1 && this.f8444d != null) {
            this.f8444d.mo6327a(this);
        }
    }

    public Collection<C3333a> m11261b() {
        return this.f8442b.values();
    }

    public boolean m11264c() {
        return this.f8443c.isEmpty();
    }

    public boolean m11265d() {
        return this.f8445e > 0;
    }

    public C3333a m11257a(String str) {
        return (C3333a) this.f8442b.get(str);
    }

    public C3333a m11256a(View view) {
        for (C3333a c3333a : this.f8442b.values()) {
            if (c3333a.m11400c(view)) {
                return c3333a;
            }
        }
        return null;
    }

    public void mo6330a(C3333a c3333a) {
        this.f8443c.remove(c3333a.m11399c());
        this.f8442b.remove(c3333a.m11399c());
        c3333a.m11393a(null);
        if (this.f8443c.size() == 0 && this.f8444d != null) {
            this.f8444d.mo6327a(this);
        }
    }

    public void mo6331b(C3333a c3333a) {
        this.f8445e++;
        if (this.f8445e == 1 && this.f8444d != null) {
            this.f8444d.mo6329b(this);
        }
    }

    public void mo6332c(C3333a c3333a) {
        this.f8445e--;
        if (this.f8445e == 0 && this.f8444d != null) {
            this.f8444d.mo6329b(this);
        }
    }
}
