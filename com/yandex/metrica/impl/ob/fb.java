package com.yandex.metrica.impl.ob;

import android.util.Base64;
import java.util.HashSet;
import java.util.Set;

class fb {
    private ev f12374a;
    private String f12375b;

    fb(ev evVar, String str) {
        this(evVar, str, null);
    }

    fb(ev evVar, String str, String[] strArr) {
        this.f12374a = evVar;
        this.f12375b = str;
        if (strArr != null) {
            this.f12374a.mo7097a(this.f12375b, strArr);
        }
    }

    public void m15984a() {
        this.f12374a.mo7096a(this.f12375b, new HashSet());
    }

    Set<String> m15986b() {
        Set<String> a = this.f12374a.mo7095a(this.f12375b);
        if (a == null) {
            return new HashSet();
        }
        return a;
    }

    long m15987c() {
        return this.f12374a.mo7094a();
    }

    void m15988d() {
        this.f12374a.mo7099b();
    }

    public boolean m15985a(String str) {
        if (Base64.decode(str, 2).length == 32) {
            return this.f12374a.mo7098a(this.f12375b, str);
        }
        throw new IllegalArgumentException("pin has bad length");
    }
}
