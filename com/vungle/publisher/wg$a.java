package com.vungle.publisher;

import com.vungle.publisher.wu.a;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.json.JSONException;
import org.json.JSONObject;

@Singleton
/* compiled from: vungle */
public class wg$a extends wp$b<wg, wv$a> {
    @Inject
    wv$a f3423b;

    public /* synthetic */ wc mo3018a(JSONObject jSONObject) throws JSONException {
        return mo3020d(jSONObject);
    }

    protected /* synthetic */ Object m4789b() {
        return m4791c();
    }

    protected /* synthetic */ Object[] m4790b(int i) {
        return m4788a(i);
    }

    public /* synthetic */ Object mo3019c(JSONObject jSONObject) throws JSONException {
        return mo3020d(jSONObject);
    }

    public /* synthetic */ wp mo3021e(JSONObject jSONObject) throws JSONException {
        return mo3020d(jSONObject);
    }

    protected /* synthetic */ a mo3022e() {
        return m4794d();
    }

    public wg mo3020d(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            return null;
        }
        wg wgVar = (wg) super.mo3021e(jSONObject);
        JSONObject a = m4773a();
        wgVar.m = ra.f(a, "postBundle");
        wgVar.n = ra.d(a, "size");
        wgVar.o = a.optString("md5");
        a(a, "md5", wgVar.o);
        return wgVar;
    }

    protected wg[] m4788a(int i) {
        return new wg[i];
    }

    protected wg m4791c() {
        return new wg();
    }

    protected wv$a m4794d() {
        return this.f3423b;
    }
}
