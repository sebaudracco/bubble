package com.appnext.base.operations.imp;

import android.os.Bundle;
import com.appnext.base.C1061b;
import com.appnext.base.p019a.C1017a;
import com.appnext.base.p019a.p021b.C1019a;
import com.appnext.base.p019a.p021b.C1020b;
import com.appnext.base.p019a.p021b.C1021c;
import com.appnext.base.p023b.C1042c.C1041a;
import com.appnext.base.p023b.C1047h;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONArray;
import org.json.JSONObject;

public class acapc extends acap {
    public acapc(C1021c c1021c, Bundle bundle) {
        super(c1021c, bundle);
    }

    protected List<C1020b> mo2031c(List<C1020b> list) {
        List<C1020b> c = super.mo2031c(list);
        if (c == null || c.isEmpty()) {
            return null;
        }
        C1020b c1020b;
        Map hashMap = new HashMap();
        for (C1020b c1020b2 : c) {
            List Z = C1017a.aM().aO().m2085Z(C1047h.cx().aC(c1020b2.aY()));
            if (Z.size() > 0) {
                Integer aW = ((C1019a) Z.get(0)).aW();
                if (hashMap.containsKey(aW)) {
                    hashMap.put(aW, Integer.valueOf(((Integer) hashMap.get(aW)).intValue() + 1));
                } else {
                    hashMap.put(aW, Integer.valueOf(1));
                }
            }
        }
        if (hashMap.isEmpty()) {
            return null;
        }
        JSONArray jSONArray = new JSONArray();
        for (Entry entry : hashMap.entrySet()) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("category", entry.getKey());
                jSONObject.put("appcount", entry.getValue());
            } catch (Throwable th) {
                C1061b.m2191a(th);
            }
            jSONArray.put(jSONObject);
        }
        c1020b2 = new C1020b(acapc.class.getSimpleName(), acapc.class.getSimpleName(), C1047h.cx().aB(jSONArray.toString()), new Date(), C1041a.JSONArray.getType());
        List<C1020b> arrayList = new ArrayList();
        arrayList.add(c1020b2);
        return arrayList.isEmpty() ? null : arrayList;
    }

    protected C1041a bD() {
        return C1041a.JSONArray;
    }

    protected String bI() {
        return acapc.class.getSimpleName();
    }
}
