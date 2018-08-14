package com.fyber.ads.internal;

import com.fyber.mediation.p108b.C2580a;
import com.fyber.requesters.p097a.C2623c;
import com.fyber.requesters.p097a.p098a.C2602f;
import com.fyber.requesters.p097a.p098a.C2611k;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;

/* compiled from: CacheEvent */
public enum C2424c {
    CachedContainerFill;
    
    private final String f6063b;

    private C2424c() {
        this.f6063b = r3;
    }

    public final String toString() {
        return this.f6063b;
    }

    public static <R, E extends Exception> Map<String, String> m7673a(int i, C2602f<C2611k<R, E>, C2580a> c2602f) {
        Map<String, String> hashMap = new HashMap();
        int e = c2602f != null ? c2602f.m8346e() : -1;
        long b = c2602f != null ? c2602f.m8342b() : -1;
        if (e >= 0) {
            hashMap.put("network_fill_cache_hits", String.valueOf(e));
        }
        if (b > 0) {
            b = System.currentTimeMillis() - b;
            switch (i) {
                case 0:
                    if (e > 0) {
                        hashMap.put("network_fill_cache_age", String.valueOf(b));
                        break;
                    }
                    break;
                case 1:
                case 2:
                    hashMap.put("network_fill_cache_age", String.valueOf(b));
                    break;
            }
        }
        return hashMap;
    }

    public static <R, E extends Exception> List<String> m7672a(int i, C2602f<C2611k<R, E>, C2580a> c2602f, boolean z) {
        int e = c2602f != null ? c2602f.m8346e() : -1;
        long b = c2602f != null ? c2602f.m8342b() : -1;
        List<String> arrayList = new ArrayList();
        if (e >= 0) {
            arrayList.add("network_fill_cache_hits");
            arrayList.add(String.valueOf(e));
            if (z) {
                arrayList.add("");
            }
        }
        if (b > 0) {
            b = System.currentTimeMillis() - b;
            switch (i) {
                case 0:
                    if (e > 0) {
                        arrayList.add("network_fill_cache_age");
                        arrayList.add(String.valueOf(b));
                        if (z) {
                            arrayList.add("");
                            break;
                        }
                    }
                    break;
                case 1:
                case 2:
                    arrayList.add("network_fill_cache_age");
                    arrayList.add(String.valueOf(b));
                    if (z) {
                        arrayList.add("");
                        break;
                    }
                    break;
            }
        }
        return arrayList;
    }

    public static Map<String, String> m7674a(C2602f<?, C2623c> c2602f) {
        Map<String, String> hashMap = new HashMap(2);
        if (c2602f == null || !c2602f.m8350i() || c2602f.m8346e() <= 0) {
            hashMap.put("container_fill_cached", SchemaSymbols.ATTVAL_FALSE_0);
            hashMap.put("container_fill_cache_age", null);
        } else {
            hashMap.put("container_fill_cached", SchemaSymbols.ATTVAL_TRUE_1);
            hashMap.put("container_fill_cache_age", String.valueOf(System.currentTimeMillis() - c2602f.m8342b()));
        }
        return hashMap;
    }
}
