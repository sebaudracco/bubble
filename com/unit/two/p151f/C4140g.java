package com.unit.two.p151f;

import android.text.TextUtils;
import com.unit.two.p147c.C4096a;
import java.util.HashMap;
import java.util.Map;

public final class C4140g {
    private String f9677a;
    private C4143j f9678b;
    private Map f9679c;
    private Map f9680d;
    private String f9681e;
    private boolean f9682f = true;

    public C4140g(String str) {
        this.f9677a = str;
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException(C4096a.aX);
        }
    }

    public final C4140g m12775a(C4143j c4143j) {
        this.f9678b = c4143j;
        return this;
    }

    protected final C4140g m12776a(String str) {
        this.f9677a = str;
        return this;
    }

    public final C4140g m12777a(Map map) {
        this.f9680d = map;
        return this;
    }

    public final String m12778a() {
        C4150q.m12870a();
        return C4142i.m12787a(this);
    }

    public final C4140g m12779b(String str) {
        String str2 = C4096a.aY;
        if (this.f9679c == null) {
            this.f9679c = new HashMap();
        }
        this.f9679c.put(str2, str);
        return this;
    }

    protected final Map m12780b() {
        return this.f9679c;
    }

    public final C4140g m12781c(String str) {
        this.f9681e = str;
        return this;
    }

    protected final String m12782c() {
        return this.f9677a;
    }

    protected final boolean m12783d() {
        return this.f9682f;
    }

    protected final C4143j m12784e() {
        return this.f9678b == null ? C4143j.GET : this.f9678b;
    }

    protected final Map m12785f() {
        return this.f9680d;
    }

    protected final String m12786g() {
        return this.f9681e;
    }
}
