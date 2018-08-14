package com.appnext.base.operations.imp;

import android.os.Bundle;
import com.appnext.base.operations.C1067e;
import com.appnext.base.p019a.p021b.C1020b;
import com.appnext.base.p019a.p021b.C1021c;
import com.appnext.base.p019a.p022c.C1026d;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class rcd extends C1067e {
    public static final String DATA = "data";
    private List<C1020b> he;
    private String hh;

    public boolean hasPermission() {
        return true;
    }

    public rcd(C1021c c1021c, Bundle bundle) {
        super(c1021c, bundle);
        if (bundle != null) {
            this.hh = bundle.getString("data");
        }
    }

    protected List<C1020b> getData() {
        try {
            this.he = new ArrayList();
            JSONArray jSONArray = new JSONArray(this.hh);
            int length = jSONArray.length();
            for (int i = 0; i < length; i++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                try {
                    this.he.add(new C1020b(jSONObject.getString(C1026d.gD), jSONObject.getString("type"), jSONObject.getString(C1026d.gE), jSONObject.getString(C1026d.gG)));
                } catch (Throwable th) {
                }
            }
            return this.he;
        } catch (Throwable th2) {
            return null;
        }
    }

    protected boolean bz() {
        if (this.he == null || this.he.isEmpty()) {
            return false;
        }
        return true;
    }
}
