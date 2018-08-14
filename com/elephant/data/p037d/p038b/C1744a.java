package com.elephant.data.p037d.p038b;

import java.io.Serializable;

public final class C1744a implements Serializable {
    public String f3601a;
    public String f3602b;
    public String f3603c;
    public String f3604d;

    public C1744a(String str) {
        this.f3604d = str;
    }

    public final boolean equals(Object obj) {
        return obj != null && (obj instanceof C1744a) && ((C1744a) obj).f3604d.equals(this.f3604d);
    }

    public final int hashCode() {
        return (this.f3604d == null ? 0 : this.f3604d.hashCode()) + 31;
    }
}
