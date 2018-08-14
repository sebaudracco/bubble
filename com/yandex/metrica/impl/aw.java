package com.yandex.metrica.impl;

import android.os.Bundle;
import android.os.ResultReceiver;
import com.yandex.metrica.CounterConfiguration;
import com.yandex.metrica.impl.ob.dw;
import com.yandex.metrica.impl.utils.C4523f.C4522a;
import java.util.HashMap;

class aw {
    static final HashMap<String, String> f11695a = new C43431();
    protected final CounterConfiguration f11696b = new CounterConfiguration();
    protected C4388o f11697c;
    protected an f11698d;
    private C4512q f11699e = new C4512q();

    static class C43431 extends HashMap<String, String> {
        C43431() {
            put("20799a27-fa80-4b36-b2db-0f8141f24180", "13");
            put("01528cc0-dd34-494d-9218-24af1317e1ee", "17233");
            put("4e610cd2-753f-4bfc-9b05-772ce8905c5e", "21952");
            put("67bb016b-be40-4c08-a190-96a3f3b503d3", "22675");
            put("e4250327-8d3c-4d35-b9e8-3c1720a64b91", "22678");
            put("6c5f504e-8928-47b5-bfb5-73af8d8bf4b4", "30404");
            put("7d962ba4-a392-449a-a02d-6c5be5613928", "30407");
        }
    }

    protected aw() {
    }

    void m14711a(C4522a c4522a) {
        this.f11697c = new C4388o(c4522a);
    }

    CounterConfiguration m14714b() {
        return this.f11696b;
    }

    Bundle mo7025c() {
        return this.f11696b.m14238F();
    }

    void m14710a(dw dwVar) {
        m14715b(dwVar);
    }

    void m14717d() {
        this.f11699e.m16212b();
    }

    boolean m14718e() {
        return this.f11699e.m16211a();
    }

    boolean mo7119a() {
        return this.f11699e.m16213c();
    }

    void m14715b(dw dwVar) {
        if (dwVar != null) {
            this.f11696b.m14262d(dwVar.mo7080a());
            this.f11696b.m14265e(dwVar.mo7082c());
            this.f11696b.m14268f(dwVar.mo7081b());
        }
    }

    void m14709a(C4382j c4382j) {
        this.f11696b.m14244a((ResultReceiver) c4382j);
    }

    void m14712a(String str, String str2) {
        this.f11697c.m15136a(str, str2);
    }

    String m14719f() {
        return this.f11697c.m15135a();
    }

    an m14720g() {
        return this.f11698d;
    }

    void m14708a(an anVar) {
        this.f11698d = anVar;
    }
}
