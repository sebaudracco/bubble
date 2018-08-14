package com.appnext.base.operations;

import android.os.Bundle;
import com.appnext.base.C1061b;
import com.appnext.base.p019a.p021b.C1021c;
import com.appnext.base.p023b.C1042c.C1041a;

public abstract class C1067e extends C1063a {
    public C1067e(C1021c c1021c, Bundle bundle) {
        super(c1021c, bundle);
    }

    public void bB() {
        try {
            if (hasPermission()) {
                bs();
            }
        } catch (Throwable th) {
            C1061b.m2191a(th);
        }
    }

    public void bC() {
    }

    protected C1041a bD() {
        return C1041a.String;
    }
}
