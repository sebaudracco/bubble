package com.yandex.metrica.impl.ob;

import java.util.ArrayList;
import java.util.List;

class cf {
    private final String f12086a;
    private final cj f12087b;
    private int f12088c;
    private final List<ce> f12089d = new ArrayList();
    private final List<ce> f12090e = new ArrayList();
    private final List<ce> f12091f = new ArrayList();

    cf(String str, cj cjVar) {
        this.f12086a = str;
        this.f12087b = cjVar;
    }

    public void m15546a(ce ceVar) {
        this.f12088c += ceVar.mo7064c().f11775b;
        this.f12089d.add(ceVar);
        switch (ceVar.mo7061a(this.f12087b)) {
            case THIS:
                this.f12090e.add(ceVar);
                return;
            case OTHER:
                this.f12091f.add(ceVar);
                return;
            default:
                return;
        }
    }

    public boolean m15547a() {
        return !this.f12091f.isEmpty();
    }

    public int m15548b() {
        return this.f12089d.size();
    }

    public String m15549c() {
        return this.f12086a;
    }

    public List<ce> m15550d() {
        return this.f12089d;
    }

    public Long m15551e() {
        Long valueOf = Long.valueOf(Long.MAX_VALUE);
        Long l = valueOf;
        for (ce c : this.f12089d) {
            valueOf = Long.valueOf(c.mo7064c().f11776c);
            if (valueOf.compareTo(l) >= 0) {
                valueOf = l;
            }
            l = valueOf;
        }
        return l;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        return this.f12086a.equals(((cf) o).f12086a);
    }

    public int hashCode() {
        return this.f12086a.hashCode();
    }
}
