package com.integralads.avid.library.inmobi.p126f;

import android.view.View;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: AvidStateCache */
public class C3313b {
    private JSONObject f8452a;
    private boolean f8453b;

    public boolean m11284a() {
        return this.f8453b;
    }

    public JSONObject m11286b() {
        return this.f8452a;
    }

    public void m11282a(JSONObject jSONObject) {
        this.f8452a = jSONObject;
    }

    public void m11287c() {
        this.f8453b = this.f8452a == null;
    }

    public void m11288d() {
        this.f8452a = null;
        this.f8453b = false;
    }

    public JSONArray m11285b(JSONObject jSONObject) {
        if (this.f8453b || jSONObject == null) {
            return null;
        }
        return jSONObject.optJSONArray("childViews");
    }

    public JSONObject m11280a(JSONArray jSONArray, int i) {
        if (this.f8453b || jSONArray == null || i >= jSONArray.length()) {
            return null;
        }
        return jSONArray.optJSONObject(i);
    }

    public void m11281a(List<View> list, JSONArray jSONArray) {
        boolean z = false;
        if (!this.f8453b) {
            int size;
            int length;
            if (list != null) {
                size = list.size();
            } else {
                boolean z2 = false;
            }
            if (jSONArray != null) {
                length = jSONArray.length();
            } else {
                length = 0;
            }
            if (size != length) {
                z = true;
            }
            this.f8453b = z;
        }
    }

    public void m11283a(JSONObject jSONObject, JSONObject jSONObject2) {
        boolean z = true;
        if (!this.f8453b) {
            if (jSONObject2 != null && C3315d.m11300a(jSONObject, jSONObject2)) {
                z = false;
            }
            this.f8453b = z;
        }
    }
}
