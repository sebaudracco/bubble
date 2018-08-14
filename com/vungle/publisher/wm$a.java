package com.vungle.publisher;

import com.vungle.publisher.wu.a;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.json.JSONException;
import org.json.JSONObject;

@Singleton
/* compiled from: vungle */
public class wm$a extends wp$b<wm, wv$a> {
    @Inject
    wv$a f3424b;

    public /* synthetic */ wc mo3018a(JSONObject jSONObject) throws JSONException {
        return mo3020d(jSONObject);
    }

    protected /* synthetic */ Object m4806b() {
        return m4808c();
    }

    protected /* synthetic */ Object[] m4807b(int i) {
        return m4805a(i);
    }

    public /* synthetic */ Object mo3019c(JSONObject jSONObject) throws JSONException {
        return mo3020d(jSONObject);
    }

    public /* synthetic */ wp mo3021e(JSONObject jSONObject) throws JSONException {
        return mo3020d(jSONObject);
    }

    protected /* synthetic */ a mo3022e() {
        return m4811d();
    }

    @Inject
    wm$a() {
    }

    public wm mo3020d(JSONObject jSONObject) throws JSONException {
        JSONObject optJSONObject = jSONObject.optJSONObject("ad");
        wm wmVar = null;
        if (optJSONObject != null) {
            Boolean a = ra.a(optJSONObject.optJSONObject("ad_markup"), "shouldStream");
            Boolean valueOf = Boolean.valueOf(optJSONObject.has("sleepCode"));
            if (!Boolean.TRUE.equals(a) || valueOf.booleanValue()) {
                wmVar = m4808c();
            } else {
                wmVar = (wm) super.mo3021e(optJSONObject);
            }
            wmVar.m = a;
        }
        return wmVar;
    }

    protected wm[] m4805a(int i) {
        return new wm[i];
    }

    protected wm m4808c() {
        return new wm();
    }

    protected wv$a m4811d() {
        return this.f3424b;
    }
}
