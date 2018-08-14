package com.fyber.utils;

import android.net.Uri;
import android.net.Uri.Builder;
import android.util.SparseArray;
import com.bgjd.ici.p025b.C1408j.C1404b;
import com.fyber.p086a.C2408a;
import com.fyber.utils.C2656g.C2651a;
import com.fyber.utils.C2656g.C2652b;
import com.fyber.utils.C2656g.C2653c;
import com.fyber.utils.C2656g.C2654d;
import com.fyber.utils.C2656g.C2655e;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

/* compiled from: UrlBuilder */
public final class C2672t {
    private static SparseArray<C2648n> f6629a;
    private String f6630b;
    private C2408a f6631c;
    private String f6632d;
    private String f6633e;
    private Map<String, String> f6634f;
    private boolean f6635g = false;
    private boolean f6636h = false;
    private boolean f6637i = true;
    private boolean f6638j = false;
    private boolean f6639k = false;
    private boolean f6640l = false;

    private C2672t(String str, C2408a c2408a) {
        this.f6630b = str;
        this.f6631c = c2408a;
    }

    private C2672t(C2672t c2672t) {
        this.f6630b = c2672t.f6630b;
        this.f6631c = c2672t.f6631c;
        this.f6632d = c2672t.f6632d;
        this.f6633e = c2672t.f6633e;
        this.f6635g = c2672t.f6635g;
        this.f6636h = c2672t.f6636h;
        this.f6637i = c2672t.f6637i;
        this.f6638j = c2672t.f6638j;
        this.f6639k = c2672t.f6639k;
        this.f6640l = c2672t.f6640l;
        if (C2664l.m8521b(c2672t.f6634f)) {
            this.f6634f = new HashMap(c2672t.f6634f);
        }
    }

    public final C2672t m8538a(String str) {
        this.f6630b = str;
        return this;
    }

    public final C2672t m8540a(Map<String, String> map) {
        if (C2664l.m8521b(map)) {
            m8536f().putAll(map);
        }
        return this;
    }

    public final C2672t m8539a(String str, String str2) {
        if (StringUtils.notNullNorEmpty(str)) {
            m8536f().put(str, str2);
        }
        return this;
    }

    public final C2672t m8537a() {
        this.f6635g = true;
        return this;
    }

    public final C2672t m8542b() {
        this.f6636h = true;
        return this;
    }

    public final C2672t m8541a(boolean z) {
        this.f6637i = z;
        return this;
    }

    public final C2672t m8544c() {
        this.f6638j = true;
        return this;
    }

    public final C2672t m8543b(String str) {
        this.f6632d = str;
        return this;
    }

    public final C2672t m8545c(String str) {
        this.f6633e = str;
        return this;
    }

    public final C2672t m8546d() {
        this.f6640l = true;
        return this;
    }

    public final String m8547e() {
        String str;
        Map hashMap = new HashMap();
        C2672t.m8535a(hashMap, 6);
        if (C2664l.m8521b(this.f6634f)) {
            hashMap.putAll(this.f6634f);
        }
        hashMap.put(C1404b.f2147y, this.f6631c.m7591a());
        if (this.f6637i) {
            hashMap.put(C1404b.f2148z, this.f6631c.m7593b());
        }
        C2672t.m8535a(hashMap, 0);
        C2672t.m8535a(hashMap, 2);
        C2672t.m8535a(hashMap, 1);
        C2672t.m8535a(hashMap, 8);
        C2672t.m8535a(hashMap, 7);
        if (this.f6633e != null) {
            hashMap.put("placement_id", this.f6633e);
        }
        if (this.f6635g) {
            C2672t.m8535a(hashMap, 3);
        }
        if (this.f6636h) {
            C2672t.m8535a(hashMap, 4);
        }
        C2672t.m8535a(hashMap, 5);
        if (StringUtils.notNullNorEmpty(this.f6632d)) {
            hashMap.put("request_id", this.f6632d);
        } else if (this.f6639k) {
            hashMap.put("request_id", UUID.randomUUID().toString());
        }
        Builder buildUpon = Uri.parse(this.f6630b).buildUpon();
        for (Entry entry : hashMap.entrySet()) {
            String str2 = (String) entry.getKey();
            if (entry.getValue() != null) {
                str = (String) entry.getValue();
            } else {
                str = "";
            }
            buildUpon.appendQueryParameter(str2, str);
        }
        if (this.f6638j) {
            str = this.f6631c.m7594c();
            if (StringUtils.notNullNorEmpty(str)) {
                buildUpon.appendQueryParameter("signature", C2669q.m8530a(hashMap, str));
            } else {
                FyberLogger.m8448d("UrlBuilder", "It was impossible to add the signature, the SecretKey has not been provided");
            }
        }
        if (this.f6640l) {
            buildUpon.scheme("http");
        }
        return buildUpon.build().toString();
    }

    private static void m8535a(Map<String, String> map, int i) {
        C2648n c2648n = (C2648n) f6629a.get(i);
        if (c2648n != null) {
            map.putAll(c2648n.mo4003a());
        }
    }

    private Map<String, String> m8536f() {
        if (this.f6634f == null) {
            this.f6634f = new HashMap();
        }
        return this.f6634f;
    }

    public static C2672t m8534a(String str, C2408a c2408a) {
        return new C2672t(str, c2408a);
    }

    public static C2672t m8533a(C2672t c2672t) {
        return new C2672t(c2672t);
    }

    static {
        SparseArray sparseArray = new SparseArray(9);
        sparseArray.put(6, new C2649f());
        sparseArray.put(0, new C2667o());
        sparseArray.put(7, new C2670r());
        sparseArray.put(3, new C2655e());
        sparseArray.put(4, new C2654d());
        sparseArray.put(5, new C2653c());
        sparseArray.put(2, new C2652b());
        sparseArray.put(1, new C2651a());
        sparseArray.put(8, new C2668p());
        f6629a = sparseArray;
    }
}
