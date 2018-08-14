package com.yandex.metrica.impl.ob;

import android.text.TextUtils;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class db {
    public static final Map<String, String> f12131a = Collections.unmodifiableMap(new C44371());
    private dc f12132b;

    static class C44371 extends HashMap<String, String> {
        C44371() {
            put("20799a27-fa80-4b36-b2db-0f8141f24180", "13");
            put("01528cc0-dd34-494d-9218-24af1317e1ee", "17233");
            put("4e610cd2-753f-4bfc-9b05-772ce8905c5e", "21952");
            put("67bb016b-be40-4c08-a190-96a3f3b503d3", "22675");
            put("e4250327-8d3c-4d35-b9e8-3c1720a64b91", "22678");
            put("6c5f504e-8928-47b5-bfb5-73af8d8bf4b4", "30404");
            put("7d962ba4-a392-449a-a02d-6c5be5613928", "30407");
        }
    }

    public db(dc dcVar) {
        this.f12132b = dcVar;
    }

    public void m15629a() {
        if (m15636f()) {
            m15637g();
            m15638h();
        }
    }

    public void m15631b() {
        Object obj;
        String d = m15634d();
        if (TextUtils.isEmpty(d) || !"DONE".equals(this.f12132b.m15661g().get(dc.m15648f(d)))) {
            obj = null;
        } else {
            obj = 1;
        }
        if (obj != null) {
            m15630a(d);
        }
    }

    public void m15633c() {
        m15630a(m15635e());
    }

    void m15630a(String str) {
        if (str != null) {
            m15632b(str);
            m15637g();
        }
    }

    String m15634d() {
        return (String) f12131a.get(this.f12132b.m15645j());
    }

    String m15635e() {
        Map g = this.f12132b.m15661g();
        for (String f : f12131a.values()) {
            g.remove(dc.m15648f(f));
        }
        LinkedList linkedList = new LinkedList();
        for (String f2 : g.keySet()) {
            try {
                linkedList.add(Integer.valueOf(Integer.parseInt(dc.m15649g(f2))));
            } catch (Throwable th) {
            }
        }
        if (linkedList.size() == 1) {
            return ((Integer) linkedList.getFirst()).toString();
        }
        return null;
    }

    boolean m15636f() {
        return this.f12132b.m15650a(null) != null;
    }

    void m15637g() {
        this.f12132b.m15651a();
    }

    void m15632b(String str) {
        this.f12132b.m15657d(str);
    }

    void m15638h() {
        this.f12132b.m15653b();
    }
}
