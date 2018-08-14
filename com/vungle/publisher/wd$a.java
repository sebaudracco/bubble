package com.vungle.publisher;

import javax.inject.Inject;
import javax.inject.Singleton;
import org.json.JSONException;
import org.json.JSONObject;

@Singleton
/* compiled from: vungle */
public class wd$a extends wc$a<wd> {
    public /* synthetic */ wc mo3018a(JSONObject jSONObject) throws JSONException {
        return mo3020d(jSONObject);
    }

    protected /* synthetic */ Object m4778b() {
        return m4780c();
    }

    protected /* synthetic */ Object[] m4779b(int i) {
        return m4777a(i);
    }

    public /* synthetic */ Object mo3019c(JSONObject jSONObject) throws JSONException {
        return mo3020d(jSONObject);
    }

    @Inject
    wd$a() {
    }

    public wd mo3020d(JSONObject jSONObject) throws JSONException {
        wd wdVar = (wd) super.mo3018a(jSONObject);
        wdVar.n = jSONObject;
        wdVar.m = wdVar.j() == null;
        return wdVar;
    }

    protected wd[] m4777a(int i) {
        return new wd[i];
    }

    protected wd m4780c() {
        return new wd();
    }
}
