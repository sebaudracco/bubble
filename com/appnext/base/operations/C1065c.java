package com.appnext.base.operations;

import android.os.Bundle;
import com.appnext.base.operations.imp.cd;
import com.appnext.base.p019a.C1017a;
import com.appnext.base.p019a.p021b.C1021c;
import com.appnext.base.p019a.p022c.C1026d;
import com.appnext.base.p023b.C1042c;

public abstract class C1065c extends C1063a {
    protected abstract String aX();

    protected abstract boolean bF();

    public C1065c(C1021c c1021c, Bundle bundle) {
        super(c1021c, bundle);
    }

    public void bB() {
        if (bF()) {
            bs();
        }
    }

    protected boolean bE() {
        try {
            C1021c ab = C1017a.aM().aR().ab(cd.class.getSimpleName());
            if (ab != null) {
                return ab.ba().equalsIgnoreCase(C1042c.jF);
            }
        } catch (Throwable th) {
        }
        return false;
    }

    protected boolean bz() {
        return false;
    }

    protected C1026d bA() {
        return C1017a.aM().aS();
    }
}
