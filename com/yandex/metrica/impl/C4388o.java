package com.yandex.metrica.impl;

import com.yandex.metrica.impl.utils.C4523f;
import com.yandex.metrica.impl.utils.C4523f.C4522a;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public class C4388o {
    private Map<String, String> f11893a = new HashMap();
    private C4523f f11894b = new C4523f();
    private C4522a f11895c;

    C4388o(C4522a c4522a) {
        this.f11895c = c4522a;
    }

    void m15136a(String str, String str2) {
        if (str2 == null) {
            this.f11893a.remove(str);
            return;
        }
        this.f11894b.m16262a(this.f11893a, str, str2, this.f11895c, "Crash Environment");
    }

    String m15135a() {
        return this.f11893a.isEmpty() ? null : new JSONObject(this.f11893a).toString();
    }
}
