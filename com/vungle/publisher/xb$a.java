package com.vungle.publisher;

import java.util.HashMap;
import java.util.Iterator;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.json.JSONException;
import org.json.JSONObject;

@Singleton
/* compiled from: vungle */
public class xb$a extends vy<xb> {
    protected /* synthetic */ Object m4843b() {
        return m4840a();
    }

    protected /* synthetic */ Object[] m4844b(int i) {
        return m4842a(i);
    }

    protected /* synthetic */ Object m4845c(JSONObject jSONObject) throws JSONException {
        return m4841a(jSONObject);
    }

    @Inject
    xb$a() {
    }

    protected xb m4841a(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            return null;
        }
        xb a = m4840a();
        xb.a(a, new HashMap());
        Iterator keys = jSONObject.keys();
        while (keys.hasNext()) {
            String str = (String) keys.next();
            xb.a(a).put(str, ra.f(jSONObject, str));
        }
        return a;
    }

    protected xb[] m4842a(int i) {
        return new xb[i];
    }

    protected xb m4840a() {
        return new xb();
    }
}
