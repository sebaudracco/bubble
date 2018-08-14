package com.vungle.publisher;

import com.vungle.publisher.vu.C1676a;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.json.JSONException;
import org.json.JSONObject;

@Singleton
/* compiled from: vungle */
public class wr$a extends wc$a<wr> {
    @Inject
    xb$a f3425b;
    @Inject
    wz$a f3426c;
    @Inject
    C1676a f3427d;

    public /* synthetic */ wc mo3018a(JSONObject jSONObject) throws JSONException {
        return mo3020d(jSONObject);
    }

    protected /* synthetic */ Object m4822b() {
        return m4824c();
    }

    protected /* synthetic */ Object[] m4823b(int i) {
        return m4821a(i);
    }

    public /* synthetic */ Object mo3019c(JSONObject jSONObject) throws JSONException {
        return mo3020d(jSONObject);
    }

    @Inject
    wr$a() {
    }

    public wr mo3020d(JSONObject jSONObject) throws JSONException {
        wr wrVar = (wr) super.mo3018a(jSONObject);
        if (wrVar != null) {
            JSONObject a = m4773a();
            JSONObject optJSONObject = a.optJSONObject("templateSettings");
            if (optJSONObject != null) {
                wrVar.m = optJSONObject.optJSONObject("normal_replacements");
                wrVar.n = this.f3426c.m4835a(optJSONObject.optJSONObject("cacheable_replacements"));
            }
            wrVar.p = ra.f(a, "templateId");
            wrVar.o = ra.f(a, "templateURL");
            wrVar.k = ra.f(a, "template_type");
            if (a.has("requires_sideloading")) {
                wr.a(wrVar, ra.a(a, "requires_sideloading").booleanValue());
            }
            wrVar.e = this.f3427d.m4757a(a.optJSONObject("tpat"));
        }
        return wrVar;
    }

    protected wr[] m4821a(int i) {
        return new wr[i];
    }

    protected wr m4824c() {
        return new wr();
    }
}
