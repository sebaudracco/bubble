package com.p000a.p001a;

import com.p000a.p001a.p003b.C0524g;
import java.util.Map.Entry;
import java.util.Set;

public final class C0560m extends C0554j {
    private final C0524g<String, C0554j> f296a = new C0524g();

    public void m475a(String str, C0554j c0554j) {
        Object obj;
        if (c0554j == null) {
            obj = C0559l.f295a;
        }
        this.f296a.put(str, obj);
    }

    public boolean equals(Object obj) {
        return obj == this || ((obj instanceof C0560m) && ((C0560m) obj).f296a.equals(this.f296a));
    }

    public int hashCode() {
        return this.f296a.hashCode();
    }

    public Set<Entry<String, C0554j>> m476o() {
        return this.f296a.entrySet();
    }
}
