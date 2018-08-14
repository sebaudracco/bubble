package com.moat.analytics.mobile.vng;

import android.os.Build.VERSION;
import com.appnext.base.p023b.C1042c;
import com.moat.analytics.mobile.vng.C3515s.C3514a;
import com.moat.analytics.mobile.vng.C3532w.C3531d;
import org.json.JSONArray;
import org.json.JSONObject;

class C3501l {
    private boolean f8947a = false;
    private boolean f8948b = false;
    private boolean f8949c = false;
    private int f8950d = 200;

    C3501l(String str) {
        m11934a(str);
    }

    private void m11934a(String str) {
        if (str != null) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                String string = jSONObject.getString("sa");
                boolean equals = string.equals("3f2ae9c1894282b5e0222f0d06bbf457191f816f");
                boolean equals2 = string.equals("8f1d08a2d6496191a5ebae8f0590f513e2619489");
                if (!((!string.equals(C1042c.jF) && !equals && !equals2) || m11935a(jSONObject) || m11936b(jSONObject))) {
                    this.f8947a = true;
                    this.f8948b = equals;
                    this.f8949c = equals2;
                    if (this.f8949c) {
                        this.f8948b = true;
                    }
                }
                if (jSONObject.has("in")) {
                    int i = jSONObject.getInt("in");
                    if (i >= 100 && i <= 1000) {
                        this.f8950d = i;
                    }
                }
                if (m11937c(jSONObject)) {
                    ((C3500k) MoatAnalytics.getInstance()).f8942c = true;
                }
            } catch (Exception e) {
                this.f8947a = false;
                this.f8948b = false;
                this.f8950d = 200;
                C3502m.m11942a(e);
            }
        }
    }

    private boolean m11935a(JSONObject jSONObject) {
        try {
            if (16 > VERSION.SDK_INT) {
                return true;
            }
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

    private boolean m11936b(JSONObject jSONObject) {
        try {
            if (!jSONObject.has("ap")) {
                return false;
            }
            CharSequence b = new C3514a().m11984b();
            JSONArray jSONArray = jSONObject.getJSONArray("ap");
            int length = jSONArray.length();
            for (int i = 0; i < length; i++) {
                if (jSONArray.getString(i).contentEquals(b)) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            C3502m.m11942a(e);
            return false;
        }
    }

    private boolean m11937c(JSONObject jSONObject) {
        try {
            if (!jSONObject.has("al")) {
                return false;
            }
            CharSequence b = new C3514a().m11984b();
            JSONArray jSONArray = jSONObject.getJSONArray("al");
            int length = jSONArray.length();
            for (int i = 0; i < length; i++) {
                if (jSONArray.getString(i).contentEquals(b)) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            C3502m.m11942a(e);
            return false;
        }
    }

    boolean m11938a() {
        return this.f8948b;
    }

    boolean m11939b() {
        return this.f8949c;
    }

    int m11940c() {
        return this.f8950d;
    }

    C3531d m11941d() {
        return this.f8947a ? C3531d.ON : C3531d.OFF;
    }
}
