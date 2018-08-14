package com.appnext.base.operations.imp;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Bundle;
import com.appnext.base.C1061b;
import com.appnext.base.operations.C1067e;
import com.appnext.base.p019a.C1017a;
import com.appnext.base.p019a.p021b.C1019a;
import com.appnext.base.p019a.p021b.C1020b;
import com.appnext.base.p019a.p021b.C1021c;
import com.appnext.base.p023b.C1042c.C1041a;
import com.appnext.base.p023b.C1043d;
import com.appnext.base.p023b.C1057k;
import com.google.firebase.analytics.FirebaseAnalytics$Param;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import org.json.JSONArray;
import org.json.JSONObject;

public class inapc extends C1067e {
    private Context mContext = C1043d.getContext();

    public inapc(C1021c c1021c, Bundle bundle) {
        super(c1021c, bundle);
    }

    public boolean hasPermission() {
        return true;
    }

    protected C1041a bD() {
        return C1041a.JSONArray;
    }

    protected List<C1020b> getData() {
        List<ApplicationInfo> a = C1057k.m2165a(this.mContext.getPackageManager(), 0);
        HashMap hashMap = new HashMap();
        for (ApplicationInfo applicationInfo : a) {
            try {
                List Z = C1017a.aM().aO().m2085Z(applicationInfo.packageName);
                if (Z.size() > 0) {
                    int intValue = ((C1019a) Z.get(0)).aW().intValue();
                    if (hashMap.get(Integer.valueOf(intValue)) == null) {
                        hashMap.put(Integer.valueOf(intValue), Integer.valueOf(1));
                    } else {
                        hashMap.put(Integer.valueOf(intValue), Integer.valueOf(((Integer) hashMap.get(Integer.valueOf(intValue))).intValue() + 1));
                    }
                }
            } catch (Throwable th) {
                C1061b.m2191a(th);
            }
        }
        if (hashMap == null || hashMap.isEmpty() || hashMap == null) {
            return null;
        }
        JSONArray jSONArray = new JSONArray();
        for (Entry entry : hashMap.entrySet()) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("categoryId", ((Integer) entry.getKey()).intValue());
                jSONObject.put(FirebaseAnalytics$Param.VALUE, ((Integer) entry.getValue()).intValue());
                jSONArray.put(jSONObject);
            } catch (Throwable th2) {
                return null;
            }
        }
        List<C1020b> arrayList = new ArrayList();
        arrayList.add(new C1020b(inapc.class.getSimpleName(), jSONArray.toString(), C1041a.JSONArray.getType()));
        return arrayList;
    }
}
