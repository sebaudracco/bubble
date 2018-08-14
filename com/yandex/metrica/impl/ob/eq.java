package com.yandex.metrica.impl.ob;

import java.io.IOException;
import java.security.GeneralSecurityException;

class eq implements ey {
    private final fd f12341a;
    private ey f12342b;
    private fh f12343c;

    eq(fe feVar, fd fdVar) {
        Object obj = null;
        this.f12341a = fdVar;
        boolean f;
        if (fdVar.m15996e()) {
            boolean a = fg.m16008a(fdVar);
            f = fdVar.m15997f();
            this.f12342b = new et();
            if (f) {
                fs a2;
                if (a) {
                    a2 = m15941a(feVar, fdVar);
                } else {
                    a2 = fa.m15983c(feVar);
                }
                this.f12343c = new fh(feVar, this.f12342b, a2, fdVar);
            }
        } else {
            Object obj2;
            boolean a3 = fg.m16008a(fdVar);
            if (86400000 != fdVar.m15991a()) {
                obj2 = 1;
            } else {
                obj2 = null;
            }
            if (obj2 != null || a3) {
                obj = 1;
            }
            f = fdVar.m15997f();
            if (a3) {
                try {
                    this.f12342b = new em(feVar, fdVar.m15993b());
                } catch (IOException e) {
                    this.f12342b = new et();
                }
            } else {
                this.f12342b = fa.m15982b(feVar);
            }
            if (f) {
                if (obj != null) {
                    this.f12343c = new fh(feVar, this.f12342b, m15941a(feVar, fdVar), fdVar);
                } else {
                    this.f12343c = fa.m15981a(feVar);
                }
            }
        }
        if (this.f12343c != null) {
            this.f12343c.m16026a(fdVar.m15995d());
        }
    }

    fh m15945d() {
        return this.f12343c;
    }

    void m15946e() {
        if (this.f12341a.m15997f()) {
            this.f12343c.m16028c();
        }
    }

    public fb mo7091a() {
        return this.f12342b.mo7091a();
    }

    public fb mo7092b() {
        return this.f12342b.mo7092b();
    }

    public fb mo7093c() {
        return this.f12342b.mo7093c();
    }

    private static fs m15941a(fe feVar, fd fdVar) {
        if (!fg.m16008a(fdVar)) {
            return feVar.m16002d();
        }
        try {
            return feVar.m15998a(fdVar.m15994c());
        } catch (GeneralSecurityException e) {
            return null;
        } catch (IOException e2) {
            return null;
        }
    }
}
