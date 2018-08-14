package com.yandex.metrica.impl.ob;

import com.yandex.metrica.impl.be.C4359a;

class ce {
    private final ch f12083a;
    private final C4359a f12084b;

    enum C4423a {
        THIS,
        OTHER,
        f12081c
    }

    ce(C4359a c4359a, ch chVar) {
        this.f12083a = chVar;
        this.f12084b = c4359a;
    }

    public String mo7062a() {
        return this.f12083a.m15560c();
    }

    public C4423a mo7061a(cj cjVar) {
        return C4423a.THIS;
    }

    public ch mo7063b() {
        return this.f12083a;
    }

    public String toString() {
        return "Bid{mCredentials='" + this.f12083a + '\'' + ", mDescriptor=" + this.f12084b + '}';
    }

    public C4359a mo7064c() {
        return this.f12084b;
    }
}
