package com.appnext.base.operations.imp;

import android.location.Location;
import android.os.Bundle;
import com.appnext.base.C1061b;
import com.appnext.base.operations.C1067e;
import com.appnext.base.p019a.p021b.C1020b;
import com.appnext.base.p019a.p021b.C1021c;
import com.appnext.base.p023b.C1042c.C1041a;
import com.appnext.base.p023b.C1043d;
import com.appnext.base.p023b.C1045f;
import com.appnext.base.p023b.C1055j;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class savloc extends C1067e {
    private static final String hl = savloc.class.getSimpleName();
    private long hm = 0;

    public savloc(C1021c c1021c, Bundle bundle) {
        super(c1021c, bundle);
    }

    public boolean hasPermission() {
        return C1045f.m2133g(C1043d.getContext(), "android.permission.ACCESS_FINE_LOCATION") || C1045f.m2133g(C1043d.getContext(), "android.permission.ACCESS_COARSE_LOCATION");
    }

    protected List<C1020b> getData() {
        try {
            Location cA = C1055j.cA();
            if (cA == null) {
                return null;
            }
            this.hm = cA.getTime();
            String str = cA.getLatitude() + "," + cA.getLongitude();
            List<C1020b> arrayList = new ArrayList();
            arrayList.add(new C1020b(hl, hl, str, new Date(this.hm), C1041a.String.getType()));
            return arrayList;
        } catch (Throwable th) {
            C1061b.m2191a(th);
            return null;
        }
    }

    protected Date getDate() {
        return new Date(this.hm);
    }
}
