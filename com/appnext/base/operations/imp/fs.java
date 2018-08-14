package com.appnext.base.operations.imp;

import android.os.Bundle;
import com.appnext.base.operations.C1065c;
import com.appnext.base.p019a.p021b.C1020b;
import com.appnext.base.p019a.p021b.C1021c;
import com.appnext.base.p023b.C1042c.C1041a;
import com.appnext.base.p023b.C1043d;
import com.appnext.base.p023b.C1045f;
import java.util.ArrayList;
import java.util.List;

public class fs extends C1065c {
    private String[] hc = new String[]{"esfs", "ess", "isfs", "iss", "esfp", "isfp"};

    public fs(C1021c c1021c, Bundle bundle) {
        super(c1021c, bundle);
    }

    protected boolean bF() {
        return true;
    }

    protected List<C1020b> getData() {
        try {
            if (hasPermission()) {
                List<C1020b> arrayList = new ArrayList();
                for (String str : this.hc) {
                    arrayList.add(new C1020b(fs.class.getSimpleName(), str, String.valueOf(C1045f.aw(str)), C1041a.Long.getType()));
                }
                return arrayList;
            }
        } catch (Throwable th) {
        }
        return null;
    }

    protected String aX() {
        return fs.class.getSimpleName();
    }

    protected C1041a bD() {
        return C1041a.Long;
    }

    public void bC() {
    }

    public boolean hasPermission() {
        return bE() && C1045f.m2133g(C1043d.getContext(), "android.permission.READ_EXTERNAL_STORAGE");
    }
}
