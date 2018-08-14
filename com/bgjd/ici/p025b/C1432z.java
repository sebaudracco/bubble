package com.bgjd.ici.p025b;

import com.bgjd.ici.json.JSONObject;

public class C1432z implements C1401f {
    private static final String f2223a = "RESPHDLR";
    private JSONObject f2224b;
    private C1395h f2225c;

    public C1432z(C1395h c1395h, JSONObject jSONObject) {
        this.f2225c = c1395h;
        this.f2224b = jSONObject;
    }

    public void mo2311a() {
        if (this.f2224b.has("type")) {
            C1401f c1401f = null;
            try {
                switch (this.f2224b.getInt("type")) {
                    case 1:
                        c1401f = new C1428v(this.f2225c, this.f2224b);
                        break;
                    case 2:
                        c1401f = new C1422r(this.f2225c, this.f2224b);
                        break;
                    case 3:
                        c1401f = new C1422r(this.f2225c, this.f2224b);
                        break;
                }
                if (c1401f != null) {
                    c1401f.mo2311a();
                    return;
                } else {
                    this.f2225c.mo2223c(300000);
                    return;
                }
            } catch (Throwable e) {
                C1402i.m2898a(f2223a, e, e.getMessage());
                return;
            }
        }
        this.f2225c.mo2223c(300000);
    }
}
