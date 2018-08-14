package com.yandex.metrica.impl.ob;

import com.yandex.metrica.impl.utils.C4525g;
import java.util.HashMap;
import java.util.Map;

public class dt {
    private final Map<String, Integer> f12263a = new HashMap();

    public void m15792a(String str, int i) {
        this.f12263a.put(str, Integer.valueOf(i));
    }

    public String m15791a() {
        return C4525g.m16271a(this.f12263a);
    }
}
