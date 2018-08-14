package com.vungle.publisher;

import com.mopub.common.Constants;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.json.JSONException;
import org.json.JSONObject;

@Singleton
/* compiled from: vungle */
protected class wa$a extends vy<wa> {
    protected /* synthetic */ Object m4768b() {
        return m4765a();
    }

    protected /* synthetic */ Object[] m4769b(int i) {
        return m4767a(i);
    }

    protected /* synthetic */ Object m4770c(JSONObject jSONObject) throws JSONException {
        return m4766a(jSONObject);
    }

    @Inject
    protected wa$a() {
    }

    protected wa m4766a(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        wa a = m4765a();
        a.a = ra.c(jSONObject, "checkpoint");
        a(jSONObject, "checkpoint", a.a);
        a.b = ra.h(jSONObject, Constants.VIDEO_TRACKING_URLS_KEY);
        a(jSONObject, Constants.VIDEO_TRACKING_URLS_KEY, a.b);
        return a;
    }

    protected wa[] m4767a(int i) {
        return new wa[i];
    }

    protected wa m4765a() {
        return new wa();
    }
}
