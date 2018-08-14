package com.appnext.base.operations.imp;

import android.os.Bundle;
import com.appnext.base.operations.C1067e;
import com.appnext.base.p019a.p021b.C1020b;
import com.appnext.base.p019a.p021b.C1021c;
import com.appnext.base.p023b.C1042c.C1041a;
import com.appnext.base.p023b.C1057k;
import java.util.ArrayList;
import java.util.List;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;

public class ulve extends C1067e {
    public ulve(C1021c c1021c, Bundle bundle) {
        super(c1021c, bundle);
    }

    public boolean hasPermission() {
        return true;
    }

    protected List<C1020b> getData() {
        try {
            long j;
            Object a = C1057k.m2163a(wpul.class.getSimpleName() + "time", C1041a.Long);
            if (a == null || !(a instanceof Long)) {
                j = 0;
            } else {
                j = ((Long) a).longValue();
            }
            C1057k.m2176e(wpul.class.getSimpleName() + "time", String.valueOf(j + System.currentTimeMillis()), C1041a.Long);
        } catch (Throwable th) {
        }
        List<C1020b> arrayList = new ArrayList();
        arrayList.add(new C1020b(ulve.class.getSimpleName(), SchemaSymbols.ATTVAL_TRUE, C1041a.String.getType()));
        return arrayList;
    }
}
