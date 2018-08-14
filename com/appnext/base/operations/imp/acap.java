package com.appnext.base.operations.imp;

import android.os.Build.VERSION;
import android.os.Bundle;
import com.appnext.base.operations.C1067e;
import com.appnext.base.p019a.p021b.C1020b;
import com.appnext.base.p019a.p021b.C1021c;
import com.appnext.base.p023b.C1042c.C1041a;
import com.appnext.base.p023b.C1043d;
import com.appnext.base.p023b.C1045f;
import com.appnext.base.p023b.C1047h;
import com.appnext.base.p023b.C1057k;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class acap extends C1067e {
    private static final String gX = "noForGroundAPP";
    private static final String gY = "android";

    private class CollectedDataModelByDateComparator implements Comparator<C1020b> {
        final /* synthetic */ acap gZ;

        private CollectedDataModelByDateComparator(acap com_appnext_base_operations_imp_acap) {
            this.gZ = com_appnext_base_operations_imp_acap;
        }

        public /* synthetic */ int compare(Object obj, Object obj2) {
            return m2202a((C1020b) obj, (C1020b) obj2);
        }

        public int m2202a(C1020b c1020b, C1020b c1020b2) {
            return c1020b.aZ().getTime() > c1020b2.aZ().getTime() ? 1 : 0;
        }
    }

    public acap(C1021c c1021c, Bundle bundle) {
        super(c1021c, bundle);
    }

    public boolean hasPermission() {
        if (VERSION.SDK_INT < 21) {
            return C1045f.m2133g(C1043d.getContext(), "android.permission.GET_TASKS");
        }
        return C1057k.m2180n(C1043d.getContext().getApplicationContext());
    }

    protected List<C1020b> getData() {
        String str;
        List e = m2203e(C1057k.m2179m(C1043d.getContext()));
        if (e == null || e.isEmpty()) {
            str = gX;
        } else {
            str = (String) e.get(e.size() - 1);
        }
        List<C1020b> arrayList = new ArrayList();
        arrayList.add(new C1020b(bI(), str, C1041a.String.toString()));
        return arrayList;
    }

    protected List<C1020b> mo2031c(List<C1020b> list) {
        if (list == null) {
            return null;
        }
        Map hashMap = new HashMap();
        for (C1020b c1020b : list) {
            String aC = C1047h.cx().aC(c1020b.aY());
            if (!(aC.equals(gX) || hashMap.containsKey(aC))) {
                hashMap.put(aC, c1020b);
            }
        }
        if (hashMap.size() == 0) {
            return null;
        }
        List<C1020b> arrayList = new ArrayList(hashMap.values());
        if (arrayList.isEmpty()) {
            return null;
        }
        Collections.sort(arrayList, new CollectedDataModelByDateComparator());
        return arrayList;
    }

    private List<String> m2203e(List<String> list) {
        if (list == null) {
            return null;
        }
        Iterator it = list.iterator();
        while (it.hasNext()) {
            if (((String) it.next()).equalsIgnoreCase(gY)) {
                it.remove();
            }
        }
        return list;
    }

    protected String bI() {
        return acap.class.getSimpleName();
    }
}
