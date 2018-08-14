package com.moat.analytics.mobile.inm;

import android.os.Build.VERSION;
import com.appnext.base.p023b.C1042c;
import org.json.JSONArray;
import org.json.JSONObject;

public class C3395u {
    private boolean f8619a = false;
    private boolean f8620b = false;
    private int f8621c = 200;

    public C3395u(String str) {
        m11620a(str);
    }

    private boolean m11619a(JSONObject jSONObject) {
        try {
            if (jSONObject.has("ob")) {
                JSONArray jSONArray = jSONObject.getJSONArray("ob");
                int length = jSONArray.length();
                for (int i = 0; i < length; i++) {
                    if (jSONArray.getInt(i) == VERSION.SDK_INT) {
                        return true;
                    }
                }
            }
            return false;
        } catch (Exception e) {
            return true;
        }
    }

    public void m11620a(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            String string = jSONObject.getString("sa");
            boolean equals = string.equals("f379b01b219fb72670923cc96dc29bbe34213365");
            if ((string.equals(C1042c.jF) || equals) && !m11619a(jSONObject)) {
                this.f8619a = true;
                this.f8620b = equals;
            }
            if (jSONObject.has("in")) {
                int i = jSONObject.getInt("in");
                if (i >= 100 && i <= 1000) {
                    this.f8621c = i;
                }
            }
        } catch (Exception e) {
            this.f8619a = false;
            this.f8620b = false;
            this.f8621c = 200;
        }
    }

    public boolean m11621a() {
        return this.f8620b;
    }

    public boolean m11622b() {
        return this.f8619a;
    }

    public int m11623c() {
        return this.f8621c;
    }
}
