package com.yandex.metrica.impl.ob;

import com.yandex.metrica.impl.be.C4359a;
import com.yandex.metrica.impl.ob.ce.C4423a;

public class cg extends ce {
    public /* bridge */ /* synthetic */ String mo7062a() {
        return super.mo7062a();
    }

    public /* bridge */ /* synthetic */ ch mo7063b() {
        return super.mo7063b();
    }

    public /* bridge */ /* synthetic */ C4359a mo7064c() {
        return super.mo7064c();
    }

    public /* bridge */ /* synthetic */ String toString() {
        return super.toString();
    }

    cg(C4359a c4359a, ch chVar, ch chVar2) {
        if (chVar != null) {
            chVar2 = chVar;
        }
        super(c4359a, chVar2);
    }

    public C4423a mo7061a(cj cjVar) {
        ch b = mo7063b();
        if (cjVar.equals(b.m15561d())) {
            return C4423a.THIS;
        }
        if (b.m15561d() != null) {
            return C4423a.OTHER;
        }
        if (b.m15559b()) {
            return C4423a.OTHER;
        }
        return C4423a.f12081c;
    }
}
