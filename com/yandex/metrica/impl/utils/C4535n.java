package com.yandex.metrica.impl.utils;

import android.text.TextUtils;
import com.appnext.base.p023b.C1042c;
import com.google.firebase.analytics.FirebaseAnalytics.Event;
import com.yandex.metrica.C4293d;
import org.json.JSONException;
import org.json.JSONObject;

public class C4535n {

    public enum C4534a {
        LOGIN(Event.LOGIN),
        LOGOUT("logout"),
        SWITCH("switch"),
        UPDATE("update");
        
        private String f12592e;

        private C4534a(String str) {
            this.f12592e = str;
        }

        public String toString() {
            return this.f12592e;
        }
    }

    public static C4293d m16294a(String str) {
        C4293d c4293d = new C4293d();
        if (!TextUtils.isEmpty(str)) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                c4293d.m14370a(jSONObject.optString("UserInfo.UserId", null));
                c4293d.m14373b(jSONObject.optString("UserInfo.Type", null));
                c4293d.m14371a(C4525g.m16273a(jSONObject.optJSONObject("UserInfo.Options")));
            } catch (JSONException e) {
            }
        }
        return c4293d;
    }

    public static String m16295a(C4534a c4534a) {
        String str = null;
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.putOpt(C1042c.jL, c4534a.toString());
            str = jSONObject.toString();
        } catch (JSONException e) {
        }
        return str;
    }
}
