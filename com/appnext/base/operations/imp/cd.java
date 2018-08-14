package com.appnext.base.operations.imp;

import android.os.Bundle;
import android.util.Pair;
import com.appnext.base.operations.C1067e;
import com.appnext.base.p019a.C1017a;
import com.appnext.base.p019a.p021b.C1020b;
import com.appnext.base.p019a.p021b.C1021c;
import com.appnext.base.p019a.p022c.C1026d;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import org.json.JSONArray;

public class cd extends C1067e {
    public cd(C1021c c1021c, Bundle bundle) {
        super(c1021c, bundle);
    }

    protected List<C1020b> getData() {
        return null;
    }

    protected HashMap<Pair<String, String>, JSONArray> mo2036c(HashMap<Pair<String, String>, JSONArray> hashMap) {
        Iterator it = hashMap.entrySet().iterator();
        while (it.hasNext()) {
            Entry entry = (Entry) it.next();
            Pair pair = (Pair) entry.getKey();
            JSONArray jSONArray = (JSONArray) entry.getValue();
            C1021c ab = C1017a.aM().aR().ab((String) pair.first);
            if (ab != null && Integer.valueOf(ab.bd()).intValue() > jSONArray.length()) {
                it.remove();
            }
        }
        return hashMap;
    }

    protected List<C1020b> bt() {
        return bA().bm();
    }

    protected boolean bz() {
        return true;
    }

    protected C1026d bA() {
        return C1017a.aM().aS();
    }

    public boolean hasPermission() {
        return true;
    }
}
