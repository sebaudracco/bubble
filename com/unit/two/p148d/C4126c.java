package com.unit.two.p148d;

import android.content.Context;
import com.unit.two.p147c.C4117t;
import com.unit.two.p148d.p149a.C4121c;
import com.unit.two.p151f.C4144k;
import org.json.JSONObject;

final class C4126c implements C4121c {
    private /* synthetic */ Context f9614a;

    C4126c(C4125b c4125b, Context context) {
        this.f9614a = context;
    }

    public final void mo6925a(JSONObject jSONObject) {
        if (jSONObject != null) {
            C4125b.f9611c = new C4124a(jSONObject);
            C4144k.m12807c(this.f9614a, jSONObject.toString());
            if (C4125b.f9611c != null && C4125b.f9611c.m12735b() > 0) {
                C4117t.m12706a(this.f9614a);
                C4125b.f9611c.m12735b();
                C4117t.m12708a();
            }
            if (C4125b.f9611c != null && C4125b.f9612d != null) {
                for (C4107e a : C4125b.f9612d) {
                    a.mo6921a(C4125b.f9611c);
                }
            }
        }
    }
}
