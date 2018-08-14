package com.appnext.base.operations;

import android.os.Bundle;
import com.appnext.base.C1061b;
import com.appnext.base.p019a.p021b.C1021c;
import java.util.ArrayList;
import java.util.List;

public class C1066d {
    private static final String gU = "com.appnext.base.operations.imp";
    private static volatile C1066d gV;
    private List<C1063a> gW = new ArrayList();

    public static C1066d bG() {
        if (gV == null) {
            synchronized (C1066d.class) {
                if (gV == null) {
                    gV = new C1066d();
                }
            }
        }
        return gV;
    }

    private C1066d() {
    }

    public void m2201a(String str, C1021c c1021c, Bundle bundle) {
        if (c1021c != null) {
            try {
                Object newInstance = Class.forName(C1066d.getOperationClassName(str)).getConstructor(new Class[]{C1021c.class, Bundle.class}).newInstance(new Object[]{c1021c, bundle});
                if (newInstance instanceof C1063a) {
                    C1063a c1063a = (C1063a) newInstance;
                    synchronized (this) {
                        this.gW.add(c1063a);
                    }
                    c1063a.bB();
                }
            } catch (ClassNotFoundException e) {
            } catch (Throwable th) {
                C1061b.m2191a(th);
            }
        }
    }

    public void m2200a(C1063a c1063a) {
        if (c1063a != null) {
            c1063a.bC();
            synchronized (this) {
                this.gW.remove(c1063a);
            }
        }
    }

    public void bH() {
        synchronized (this) {
            for (C1063a bC : this.gW) {
                bC.bC();
            }
            this.gW.clear();
        }
    }

    public static String getOperationClassName(String str) {
        return "com.appnext.base.operations.imp." + str;
    }
}
