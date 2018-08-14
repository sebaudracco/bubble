package com.vungle.publisher;

import com.vungle.publisher.wu.a;
import javax.inject.Inject;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: vungle */
protected abstract class wp$b<R extends wp, T extends a<?>> extends wc$a<R> {
    @Inject
    protected wp$a$a f3422c;

    protected abstract T mo3022e();

    public /* synthetic */ wc mo3018a(JSONObject jSONObject) throws JSONException {
        return mo3021e(jSONObject);
    }

    public /* synthetic */ Object mo3019c(JSONObject jSONObject) throws JSONException {
        return mo3021e(jSONObject);
    }

    protected wp$b() {
    }

    public R mo3021e(JSONObject jSONObject) throws JSONException {
        wp wpVar = (wp) super.mo3018a(jSONObject);
        if (wpVar != null) {
            JSONObject a = m4773a();
            wpVar.p = ra.f(a, "callToActionDest");
            wpVar.q = this.f3422c.m4815a(a.optJSONObject("cta_overlay"));
            wpVar.r = ra.f(a, "callToActionUrl");
            wpVar.s = ra.d(a, "showCloseIncentivized");
            wpVar.t = ra.d(a, "showClose");
            wpVar.u = ra.d(a, "countdown");
            wpVar.v = ra.d(a, "videoHeight");
            a(a, "videoHeight", wpVar.v);
            wpVar.w = ra.f(a, "url");
            a(a, "url", wpVar.w);
            wpVar.x = ra.d(a, "videoWidth");
            a(a, "videoWidth", wpVar.x);
            wpVar.e = mo3022e().b(a.optJSONObject("tpat"));
        }
        return wpVar;
    }
}
