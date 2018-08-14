package com.vungle.publisher;

import com.vungle.publisher.wu.a;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.json.JSONException;
import org.json.JSONObject;

@Singleton
/* compiled from: vungle */
protected class wv$a extends a<wv> {
    @Inject
    protected wa$a f3428a;

    public /* synthetic */ wu m4830b(JSONObject jSONObject) throws JSONException {
        return m4828a(jSONObject);
    }

    protected /* synthetic */ Object m4831b() {
        return m4827a();
    }

    protected /* synthetic */ Object[] m4832b(int i) {
        return m4829a(i);
    }

    public /* synthetic */ Object m4833c(JSONObject jSONObject) throws JSONException {
        return m4828a(jSONObject);
    }

    @Inject
    wv$a() {
    }

    public wv m4828a(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            return null;
        }
        wv a = m4827a();
        a.a = ra.h(jSONObject, "postroll_click");
        a.b = ra.h(jSONObject, "video_click");
        a.c = ra.h(jSONObject, "video_close");
        a.d = ra.h(jSONObject, "error");
        a.e = ra.h(jSONObject, "mute");
        a.f = ra.h(jSONObject, "pause");
        a.g = (wa[]) this.f3428a.a(jSONObject.optJSONArray("play_percentage"));
        a.h = ra.h(jSONObject, "postroll_view");
        a.i = ra.h(jSONObject, "resume");
        a.j = ra.h(jSONObject, "unmute");
        JSONObject jSONObject2 = jSONObject.getJSONObject("moat");
        wv.a(a, ra.a(jSONObject2, "is_enabled"));
        wv.a(a, ra.f(jSONObject2, "extra_vast"));
        return a;
    }

    protected wv[] m4829a(int i) {
        return new wv[i];
    }

    protected wv m4827a() {
        return new wv();
    }
}
