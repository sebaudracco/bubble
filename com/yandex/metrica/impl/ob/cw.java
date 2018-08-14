package com.yandex.metrica.impl.ob;

public class cw {
    private final String f12126a;
    private final boolean f12127b;

    public cw(String str, boolean z) {
        this.f12126a = str;
        this.f12127b = z;
    }

    public boolean m15619a() {
        return this.f12127b;
    }

    public String m15620b() {
        return this.f12126a;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        cw cwVar = (cw) o;
        if (this.f12127b == cwVar.f12127b) {
            return this.f12126a.equals(cwVar.f12126a);
        }
        return false;
    }

    public int hashCode() {
        return (this.f12127b ? 1 : 0) + (this.f12126a.hashCode() * 31);
    }
}
