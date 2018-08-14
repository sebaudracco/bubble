package com.yandex.metrica.impl;

import com.yandex.metrica.impl.utils.C4523f;
import java.util.HashMap;

public class C4373e extends C4372h {
    private final HashMap<C4371a, Integer> f11858f;
    private final C4523f f11859g;

    private enum C4371a {
        NAME,
        VALUE,
        USER_INFO
    }

    public C4373e() {
        this.f11858f = new HashMap();
        this.f11859g = new C4523f();
    }

    public C4373e(String str, int i) {
        this("", str, i);
    }

    public C4373e(String str, String str2, int i) {
        this(str, str2, i, 0);
    }

    public C4373e(String str, String str2, int i, int i2) {
        this.f11858f = new HashMap();
        this.f11859g = new C4523f();
        this.b = m15086i(str);
        this.a = m15085h(str2);
        this.c = i;
        this.d = i2;
    }

    private void m15084a(String str, String str2, C4371a c4371a) {
        if (this.f11859g.m16263a(str, str2)) {
            this.f11858f.put(c4371a, Integer.valueOf(bi.m14964c(str).length - bi.m14964c(str2).length));
        } else {
            this.f11858f.remove(c4371a);
        }
        this.e = 0;
        for (Integer num : this.f11858f.values()) {
            this.e = num.intValue() + this.e;
        }
    }

    private String m15085h(String str) {
        String a = this.f11859g.m16261a(str, 1000, "event name");
        m15084a(str, a, C4371a.NAME);
        return a;
    }

    private String m15086i(String str) {
        String a = this.f11859g.m16260a(str, 245760);
        m15084a(str, a, C4371a.VALUE);
        return a;
    }

    public C4372h mo7030a(String str) {
        String a = this.f11859g.m16261a(str, 10000, "UserInfo");
        m15084a(str, a, C4371a.USER_INFO);
        return super.mo7030a(a);
    }

    public C4372h mo7031b(String str) {
        return super.mo7031b(m15085h(str));
    }

    public C4372h mo7032c(String str) {
        return super.mo7032c(m15086i(str));
    }
}
