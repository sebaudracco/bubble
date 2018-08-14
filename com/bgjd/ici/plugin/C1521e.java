package com.bgjd.ici.plugin;

import com.bgjd.ici.json.JSONArray;
import com.bgjd.ici.json.JSONObject;
import com.bgjd.ici.p025b.C1395h;
import com.bgjd.ici.p025b.C1402i;

public class C1521e implements C1517a {
    private static final String f2494a = "BCNPLG";
    private C1395h f2495b;
    private String f2496c = "";
    private C1517a f2497d;

    public C1521e(C1395h c1395h) {
        this.f2495b = c1395h;
    }

    public void mo2343a(String str) {
        this.f2496c = str;
    }

    public void mo2342a() {
        JSONArray q = this.f2495b.mo2281q();
        if (q != null && q.length() > 0) {
            for (int i = 0; i < q.length(); i++) {
                try {
                    JSONObject jSONObject = q.getJSONObject(i);
                    if (jSONObject != null) {
                        if (this.f2496c.contentEquals(jSONObject.getString("name"))) {
                            this.f2497d = new C1526h(this.f2495b);
                            this.f2497d.mo2343a(this.f2496c);
                            this.f2497d.mo2342a();
                            return;
                        }
                    } else {
                        continue;
                    }
                } catch (Throwable e) {
                    C1402i.m2898a(f2494a, e, e.getMessage());
                }
            }
        }
    }

    public void mo2344b() {
        if (this.f2497d != null) {
            this.f2497d.mo2344b();
        }
    }

    public void mo2345c() {
        if (this.f2497d != null) {
            this.f2497d.mo2345c();
        }
    }
}
