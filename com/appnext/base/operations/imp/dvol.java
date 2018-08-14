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

public class dvol extends C1065c {
    private static final String KEY = dvol.class.getSimpleName();
    private String[] hb = new String[]{"dvola", "dvolc", "dvolm", "dvoln", "dvolr"};

    public dvol(C1021c c1021c, Bundle bundle) {
        super(c1021c, bundle);
    }

    protected boolean bF() {
        return true;
    }

    protected List<C1020b> getData() {
        try {
            List<C1020b> arrayList = new ArrayList();
            for (String str : this.hb) {
                arrayList.add(new C1020b(KEY, str, String.valueOf(C1045f.m2132f(C1043d.getContext().getApplicationContext(), str)), C1041a.Integer.getType()));
            }
            return arrayList;
        } catch (Throwable th) {
            return null;
        }
    }

    protected String aX() {
        return dvol.class.getSimpleName();
    }

    public void bC() {
    }

    public boolean hasPermission() {
        return bE();
    }
}
