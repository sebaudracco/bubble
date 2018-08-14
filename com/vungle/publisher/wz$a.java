package com.vungle.publisher;

import java.util.HashMap;
import java.util.Iterator;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.json.JSONException;
import org.json.JSONObject;

@Singleton
/* compiled from: vungle */
public class wz$a extends vy<wz> {
    protected /* synthetic */ Object m4837b() {
        return m4834a();
    }

    protected /* synthetic */ Object[] m4838b(int i) {
        return m4836a(i);
    }

    protected /* synthetic */ Object m4839c(JSONObject jSONObject) throws JSONException {
        return m4835a(jSONObject);
    }

    @Inject
    wz$a() {
    }

    protected wz m4835a(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            return null;
        }
        wz a = m4834a();
        a.a = new HashMap();
        Iterator keys = jSONObject.keys();
        while (keys.hasNext()) {
            String str = (String) keys.next();
            a.a.put(str, new wy(str, jSONObject.getJSONObject(str)));
        }
        return a;
    }

    protected wz[] m4836a(int i) {
        return new wz[i];
    }

    protected wz m4834a() {
        return new wz();
    }
}
