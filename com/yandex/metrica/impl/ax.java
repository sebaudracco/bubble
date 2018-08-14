package com.yandex.metrica.impl;

import android.content.Context;
import android.os.Handler;
import com.yandex.metrica.C4276b;
import com.yandex.metrica.C4295e;
import com.yandex.metrica.impl.ob.dw;
import java.util.HashMap;
import java.util.Map;

class ax {
    private Context f11701a;
    private ay f11702b;
    private C4382j f11703c;
    private Handler f11704d;
    private dw f11705e;
    private Map<String, C4276b> f11706f;

    static class C4344a {
        ax f11700a = new ax();

        C4344a() {
        }

        C4344a m14721a(Context context) {
            this.f11700a.f11701a = context;
            return this;
        }

        C4344a m14723a(ay ayVar) {
            this.f11700a.f11702b = ayVar;
            return this;
        }

        C4344a m14724a(C4382j c4382j) {
            this.f11700a.f11703c = c4382j;
            return this;
        }

        C4344a m14722a(Handler handler) {
            this.f11700a.f11704d = handler;
            return this;
        }

        C4344a m14725a(dw dwVar) {
            this.f11700a.f11705e = dwVar;
            return this;
        }

        ax m14726a() {
            return this.f11700a;
        }
    }

    private ax() {
        this.f11706f = new HashMap();
    }

    C4544z m14734a(C4295e c4295e, boolean z) {
        if (this.f11706f.containsKey(c4295e.getApiKey())) {
            throw new IllegalArgumentException(String.format("Failed to activate AppMetrica with provided API Key. API Key %s has already been used by another reporter.", new Object[]{c4295e.getApiKey()}));
        }
        C4544z c4544z = new C4544z(this.f11701a, c4295e, this.f11702b);
        m14732a((C4306b) c4544z);
        c4544z.m16321a(c4295e, z);
        c4544z.m14455a();
        this.f11702b.m14750a(c4544z);
        this.f11706f.put(c4295e.getApiKey(), c4544z);
        return c4544z;
    }

    synchronized C4276b m14733a(String str) {
        C4276b c4276b;
        c4276b = (C4276b) this.f11706f.get(str);
        if (c4276b == null) {
            C4306b aaVar = new aa(this.f11701a, (String) aw.f11695a.get(str), str, this.f11702b);
            m14732a(aaVar);
            aaVar.m14455a();
            this.f11706f.put(str, aaVar);
            c4276b = aaVar;
        }
        return c4276b;
    }

    private void m14732a(C4306b c4306b) {
        c4306b.m14460a(new C4540w(this.f11704d, c4306b));
        c4306b.mo7123a(this.f11703c);
        c4306b.mo7124a(this.f11705e);
    }
}
