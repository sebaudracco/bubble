package com.appnext.base.p019a;

import com.appnext.base.p019a.p022c.C1025a;
import com.appnext.base.p019a.p022c.C1027b;
import com.appnext.base.p019a.p022c.C1028c;
import com.appnext.base.p019a.p022c.C1030f;
import com.appnext.base.p019a.p022c.C1032g;

public class C1017a {
    private static volatile C1017a fR;
    private C1025a fM;
    private C1027b fN;
    private C1032g fO;
    private C1028c fP;
    private C1030f fQ;

    public static C1017a aM() {
        if (fR == null) {
            synchronized (C1017a.class) {
                if (fR == null) {
                    fR = new C1017a();
                }
            }
        }
        return fR;
    }

    private C1017a() {
        aN();
    }

    private void aN() {
        this.fM = new C1025a();
        this.fN = new C1027b();
        this.fO = new C1032g();
        this.fP = new C1028c();
        this.fQ = new C1030f();
    }

    public C1025a aO() {
        return this.fM;
    }

    public C1027b aP() {
        return this.fN;
    }

    public C1032g aQ() {
        return this.fO;
    }

    public C1028c aR() {
        return this.fP;
    }

    public C1030f aS() {
        return this.fQ;
    }
}
