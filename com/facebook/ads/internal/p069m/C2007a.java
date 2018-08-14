package com.facebook.ads.internal.p069m;

import android.text.TextUtils;
import com.facebook.ads.internal.p056q.p057a.C2119j;
import com.facebook.ads.internal.p064g.C1985a;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class C2007a {
    private final String f4719a;
    private final double f4720b = (((double) System.currentTimeMillis()) / 1000.0d);
    private final double f4721c;
    private final String f4722d;
    private final Map<String, String> f4723e;
    private final C2015e f4724f;
    private final C2016f f4725g;
    private final boolean f4726h;

    public static class C2006a {
        private String f4712a;
        private double f4713b;
        private String f4714c;
        private Map<String, String> f4715d;
        private C2015e f4716e;
        private C2016f f4717f;
        private boolean f4718g;

        public C2006a m6364a(double d) {
            this.f4713b = d;
            return this;
        }

        public C2006a m6365a(C2015e c2015e) {
            this.f4716e = c2015e;
            return this;
        }

        public C2006a m6366a(C2016f c2016f) {
            this.f4717f = c2016f;
            return this;
        }

        public C2006a m6367a(String str) {
            this.f4712a = str;
            return this;
        }

        public C2006a m6368a(Map<String, String> map) {
            this.f4715d = map;
            return this;
        }

        public C2006a m6369a(boolean z) {
            this.f4718g = z;
            return this;
        }

        public C2007a m6370a() {
            return new C2007a(this.f4712a, this.f4713b, this.f4714c, this.f4715d, this.f4716e, this.f4717f, this.f4718g);
        }

        public C2006a m6371b(String str) {
            this.f4714c = str;
            return this;
        }
    }

    public C2007a(String str, double d, String str2, Map<String, String> map, C2015e c2015e, C2016f c2016f, boolean z) {
        this.f4719a = str;
        this.f4721c = d;
        this.f4722d = str2;
        this.f4724f = c2015e;
        this.f4725g = c2016f;
        this.f4726h = z;
        Map hashMap = new HashMap();
        if (!(map == null || map.isEmpty())) {
            hashMap.putAll(map);
        }
        if (m6378f()) {
            hashMap.put("analog", C2119j.m6798a(C1985a.m6258a()));
        }
        this.f4723e = C2007a.m6372a(hashMap);
    }

    private static Map<String, String> m6372a(Map<String, String> map) {
        Map<String, String> hashMap = new HashMap();
        for (Entry entry : map.entrySet()) {
            String str = (String) entry.getKey();
            String str2 = (String) entry.getValue();
            if (str2 != null) {
                hashMap.put(str, str2);
            }
        }
        return hashMap;
    }

    public String m6373a() {
        return this.f4719a;
    }

    public double m6374b() {
        return this.f4720b;
    }

    public double m6375c() {
        return this.f4721c;
    }

    public String m6376d() {
        return this.f4722d;
    }

    public Map<String, String> m6377e() {
        return this.f4723e;
    }

    final boolean m6378f() {
        return this.f4724f == C2015e.IMMEDIATE;
    }

    final boolean m6379g() {
        return !TextUtils.isEmpty(this.f4719a);
    }

    public C2015e m6380h() {
        return this.f4724f;
    }

    public C2016f m6381i() {
        return this.f4725g;
    }
}
