package com.yandex.metrica.impl.ob;

import android.os.Bundle;
import android.text.TextUtils;
import com.yandex.metrica.IIdentifierCallback;
import com.yandex.metrica.IIdentifierCallback.Reason;
import com.yandex.metrica.impl.ay;
import com.yandex.metrica.impl.bk;
import com.yandex.metrica.impl.ob.dx.C4455a;
import com.yandex.metrica.impl.utils.C4528i;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.WeakHashMap;

public class dv implements dw {
    static final Map<du, Reason> f12269a = Collections.unmodifiableMap(new C44541());
    private final ay f12270b;
    private final dx f12271c;
    private final bz f12272d;
    private final Object f12273e = new Object();
    private final Map<IIdentifierCallback, Object> f12274f = new WeakHashMap();
    private final Map<IIdentifierCallback, Object> f12275g = new WeakHashMap();

    static class C44541 extends HashMap<du, Reason> {
        C44541() {
            put(du.f12264a, Reason.UNKNOWN);
            put(du.NETWORK, Reason.NETWORK);
            put(du.PARSE, Reason.INVALID_RESPONSE);
        }
    }

    public dv(ay ayVar, String str, bz bzVar) {
        this.f12270b = ayVar;
        this.f12272d = bzVar;
        this.f12271c = new dx(this.f12272d, str);
        m15809e();
    }

    public String mo7080a() {
        return this.f12271c.m15830c();
    }

    public String mo7081b() {
        return this.f12272d.m15429a();
    }

    public String mo7082c() {
        return this.f12271c.m15832d();
    }

    public void m15801a(IIdentifierCallback iIdentifierCallback) {
        synchronized (this.f12273e) {
            this.f12275g.put(iIdentifierCallback, null);
            if (!this.f12271c.m15827a(C4455a.f12278c)) {
                this.f12270b.m14761c();
            }
        }
        m15809e();
    }

    public void m15800a(Bundle bundle) {
        synchronized (this.f12273e) {
            this.f12271c.m15822a(bundle);
            this.f12271c.m15821a(System.currentTimeMillis() / 1000);
        }
        m15809e();
    }

    public void m15808d() {
        if (!this.f12271c.m15827a(C4455a.f12278c) || this.f12271c.m15826a()) {
            this.f12270b.m14761c();
        }
    }

    public void m15803a(List<String> list) {
        List b = this.f12271c.m15828b();
        if (bk.m14987a((Collection) list)) {
            if (!bk.m14987a((Collection) b)) {
                this.f12271c.m15824a(null);
                this.f12270b.m14755a(null);
            }
        } else if (bk.m14986a((Object) list, (Object) b)) {
            this.f12270b.m14755a(b);
        } else {
            this.f12271c.m15824a((List) list);
            this.f12270b.m14755a((List) list);
        }
    }

    public void m15804a(Map<String, String> map) {
        Map hashMap = new HashMap();
        if (map != null) {
            for (Entry entry : map.entrySet()) {
                Object obj;
                String str = (String) entry.getKey();
                if (TextUtils.isEmpty(str) || str.contains(":") || str.contains(",") || str.contains("&")) {
                    obj = null;
                } else {
                    obj = 1;
                }
                if (obj != null) {
                    str = (String) entry.getValue();
                    if (TextUtils.isEmpty(str) || C4528i.m16276a(str, -1) == -1) {
                        obj = null;
                    } else {
                        obj = 1;
                    }
                    if (obj != null) {
                        hashMap.put(entry.getKey(), entry.getValue());
                    }
                }
            }
        }
        this.f12270b.m14756a(hashMap);
    }

    public void m15802a(String str) {
        this.f12270b.m14763c(str);
    }

    void m15809e() {
        Map weakHashMap = new WeakHashMap();
        Map hashMap = new HashMap();
        Map weakHashMap2 = new WeakHashMap();
        Map hashMap2 = new HashMap();
        synchronized (this.f12273e) {
            if (this.f12271c.m15827a(C4455a.IDENTIFIERS)) {
                weakHashMap.putAll(this.f12274f);
                this.f12274f.clear();
                this.f12271c.m15829b(hashMap);
            }
            if (this.f12271c.m15827a(C4455a.f12278c)) {
                weakHashMap2.putAll(this.f12275g);
                this.f12275g.clear();
                this.f12271c.m15825a(hashMap2);
            }
        }
        for (IIdentifierCallback onReceive : weakHashMap.keySet()) {
            onReceive.onReceive(new HashMap(hashMap));
        }
        for (IIdentifierCallback onReceive2 : weakHashMap2.keySet()) {
            onReceive2.onReceive(new HashMap(hashMap2));
        }
        weakHashMap.clear();
        hashMap.clear();
        weakHashMap2.clear();
        hashMap2.clear();
    }

    public void m15806b(Bundle bundle) {
        Reason reason = (Reason) f12269a.get(du.m15793b(bundle));
        Map weakHashMap = new WeakHashMap();
        Map weakHashMap2 = new WeakHashMap();
        synchronized (this.f12273e) {
            weakHashMap.putAll(this.f12274f);
            weakHashMap2.putAll(this.f12275g);
            this.f12274f.clear();
            this.f12275g.clear();
        }
        for (IIdentifierCallback onRequestError : weakHashMap.keySet()) {
            onRequestError.onRequestError(reason);
        }
        for (IIdentifierCallback onRequestError2 : weakHashMap2.keySet()) {
            onRequestError2.onRequestError(reason);
        }
        weakHashMap.clear();
        weakHashMap2.clear();
    }
}
