package com.adcolony.sdk;

import java.util.HashMap;

class C0691f {
    String f965a;
    private HashMap<Integer, Boolean> f966b = new HashMap();
    private aq f967c;
    private ae f968d;
    private int f969e;

    C0691f(String str, int i) {
        this.f965a = str;
        this.f969e = i;
    }

    void m1162a(af afVar) {
        if (this.f967c == null) {
            this.f967c = new aq(this.f965a, this.f969e);
            this.f968d = new ae(this.f965a, this.f969e);
        }
        int c = C0802y.m1473c(afVar.m698c(), "id");
        if (C0802y.m1477d(afVar.m698c(), "use_sound_pool")) {
            this.f966b.put(Integer.valueOf(c), Boolean.valueOf(true));
            this.f967c.m775a(afVar);
            return;
        }
        this.f966b.put(Integer.valueOf(c), Boolean.valueOf(false));
        this.f968d.m685a(afVar);
    }

    void m1161a() {
        this.f967c.m774a().autoPause();
        this.f968d.m684a();
    }

    void m1163b() {
        this.f967c.m774a().autoResume();
        this.f968d.m686b();
    }

    void m1164b(af afVar) {
        if (((Boolean) this.f966b.get(Integer.valueOf(C0802y.m1473c(afVar.m698c(), "id")))).booleanValue()) {
            this.f967c.m778d(afVar);
        } else {
            this.f968d.m687b(afVar);
        }
    }

    void m1166c(af afVar) {
        if (((Boolean) this.f966b.get(Integer.valueOf(C0802y.m1473c(afVar.m698c(), "id")))).booleanValue()) {
            this.f967c.m777c(afVar);
        } else {
            this.f968d.m689c(afVar);
        }
    }

    void m1168d(af afVar) {
        if (((Boolean) this.f966b.get(Integer.valueOf(C0802y.m1473c(afVar.m698c(), "id")))).booleanValue()) {
            this.f967c.m776b(afVar);
        } else {
            this.f968d.m690d(afVar);
        }
    }

    void m1169e(af afVar) {
        if (((Boolean) this.f966b.get(Integer.valueOf(C0802y.m1473c(afVar.m698c(), "id")))).booleanValue()) {
            this.f967c.m779e(afVar);
        } else {
            this.f968d.m691e(afVar);
        }
    }

    ae m1165c() {
        return this.f968d;
    }

    aq m1167d() {
        return this.f967c;
    }
}
