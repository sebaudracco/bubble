package com.vungle.publisher;

import com.vungle.publisher.wp.a;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.json.JSONException;
import org.json.JSONObject;

@Singleton
/* compiled from: vungle */
protected class wp$a$a extends vy<a> {
    protected /* synthetic */ Object m4817b() {
        return m4814a();
    }

    protected /* synthetic */ Object[] m4818b(int i) {
        return m4816a(i);
    }

    protected /* synthetic */ Object m4819c(JSONObject jSONObject) throws JSONException {
        return m4815a(jSONObject);
    }

    @Inject
    wp$a$a() {
    }

    protected a m4815a(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            return null;
        }
        a a = m4814a();
        a.a = ra.c(jSONObject, "click_area");
        a.c = ra.a(jSONObject, "enabled");
        a.d = ra.a(jSONObject, "show_onclick");
        a.e = ra.d(jSONObject, "time_show");
        a.b = ra.d(jSONObject, "time_enabled");
        return a;
    }

    protected a[] m4816a(int i) {
        return new a[i];
    }

    protected a m4814a() {
        return new a();
    }
}
