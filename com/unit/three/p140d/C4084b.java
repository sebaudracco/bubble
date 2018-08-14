package com.unit.three.p140d;

import com.unit.three.p138b.C4053c;
import com.unit.three.p140d.C4083a.C4057a;
import com.unit.three.p140d.p142a.C4081c;
import com.unit.three.p141c.C4078f;
import org.json.JSONObject;

final class C4084b implements C4081c {
    private /* synthetic */ C4057a f9460a;

    C4084b(C4083a c4083a, C4057a c4057a) {
        this.f9460a = c4057a;
    }

    public final void mo6920a(JSONObject jSONObject) {
        if (jSONObject != null) {
            C4083a.f9459c = new C4078f(jSONObject);
            C4078f.m12566a(C4053c.m12503a().m12515b(), "_proxy_strategy", jSONObject.toString());
            this.f9460a.mo6915a(C4083a.f9459c.m12582f());
        }
    }
}
