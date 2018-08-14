package com.facebook.ads.internal.p070r;

import java.util.HashMap;
import java.util.Map;

public class C2155b {
    private C2156c f5154a;
    private float f5155b;
    private Map<String, String> f5156c;

    public C2155b(C2156c c2156c) {
        this(c2156c, 0.0f);
    }

    public C2155b(C2156c c2156c, float f) {
        this(c2156c, f, null);
    }

    public C2155b(C2156c c2156c, float f, Map<String, String> map) {
        this.f5154a = c2156c;
        this.f5155b = f;
        if (map != null) {
            this.f5156c = map;
        } else {
            this.f5156c = new HashMap();
        }
    }

    public boolean m6923a() {
        return this.f5154a == C2156c.IS_VIEWABLE;
    }

    public int m6924b() {
        return this.f5154a.m6927a();
    }

    public float m6925c() {
        return this.f5155b;
    }

    public Map<String, String> m6926d() {
        return this.f5156c;
    }
}
