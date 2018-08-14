package com.inmobi.commons.core.p115d;

import com.inmobi.commons.core.configs.C3045a;
import com.inmobi.commons.core.configs.C3121b;
import com.inmobi.commons.core.configs.C3121b.C2911b;
import com.inmobi.commons.core.p114b.C3109a;

/* compiled from: CatchEventUtility */
public class C3131a {
    private static C3130a f7686a;
    private static C3109a f7687b;
    private static boolean f7688c = false;

    /* compiled from: CatchEventUtility */
    private static class C3130a implements C2911b {
        private C3130a() {
        }

        public void mo6102a(C3045a c3045a) {
            C3131a.f7687b = (C3109a) c3045a;
        }
    }

    public static boolean m10248a() {
        if (!f7688c) {
            f7687b = new C3109a();
            f7686a = new C3130a();
            C3121b.m10178a().m10190a(f7687b, f7686a);
            f7688c = true;
        }
        return f7687b.m10119f();
    }
}
