package com.vungle.publisher;

import javax.inject.Inject;
import javax.inject.Singleton;
import org.json.JSONException;
import org.json.JSONObject;

@Singleton
/* compiled from: vungle */
public class wj$a extends wc$a<wj> {
    public /* synthetic */ wc mo3018a(JSONObject jSONObject) throws JSONException {
        return mo3020d(jSONObject);
    }

    protected /* synthetic */ Object m4799b() {
        return m4801c();
    }

    protected /* synthetic */ Object[] m4800b(int i) {
        return m4798a(i);
    }

    public /* synthetic */ Object mo3019c(JSONObject jSONObject) throws JSONException {
        return mo3020d(jSONObject);
    }

    @Inject
    wj$a() {
    }

    public wj mo3020d(JSONObject jSONObject) throws JSONException {
        wj wjVar = (wj) super.mo3018a(jSONObject);
        if (wjVar != null) {
            wj.a(wjVar, ra.f(jSONObject, "mraidContent"));
        }
        return wjVar;
    }

    protected wj[] m4798a(int i) {
        return new wj[i];
    }

    protected wj m4801c() {
        return new wj();
    }
}
