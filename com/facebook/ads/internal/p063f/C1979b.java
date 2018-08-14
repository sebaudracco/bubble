package com.facebook.ads.internal.p063f;

import java.util.HashMap;
import java.util.Map;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;

public class C1979b {
    private final String f4620a;
    private final Map<String, String> f4621b;
    private final String f4622c;

    public C1979b(String str, Map<String, String> map) {
        this(str, map, false);
    }

    public C1979b(String str, Map<String, String> map, boolean z) {
        this.f4620a = str;
        this.f4621b = map;
        this.f4622c = z ? SchemaSymbols.ATTVAL_TRUE_1 : SchemaSymbols.ATTVAL_FALSE_0;
    }

    public Map<String, String> m6243a() {
        Map<String, String> hashMap = new HashMap();
        hashMap.put("stacktrace", this.f4620a);
        hashMap.put("caught_exception", this.f4622c);
        hashMap.putAll(this.f4621b);
        return hashMap;
    }
}
