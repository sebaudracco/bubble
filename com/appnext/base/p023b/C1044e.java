package com.appnext.base.p023b;

import com.appnext.base.p019a.C1017a;
import com.appnext.base.p019a.p021b.C1021c;
import java.util.List;

public class C1044e {
    private static volatile C1044e jX;

    private C1044e() {
    }

    public static C1044e cs() {
        if (jX == null) {
            synchronized (C1044e.class) {
                if (jX == null) {
                    jX = new C1044e();
                }
            }
        }
        return jX;
    }

    public C1021c av(String str) {
        return C1017a.aM().aR().ab(str);
    }

    public List<C1021c> ct() {
        return C1017a.aM().aR().bm();
    }
}
