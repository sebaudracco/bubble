package com.bgjd.ici.p029d;

import com.bgjd.ici.json.JSONArray;
import com.bgjd.ici.json.JSONException;
import com.bgjd.ici.json.JSONObject;
import com.bgjd.ici.p025b.C1395h;
import com.bgjd.ici.p025b.C1408j.C1403a;
import com.bgjd.ici.p025b.C1409k;
import com.bgjd.ici.p025b.C1410m;
import com.bgjd.ici.p028c.C1434d;
import com.bgjd.ici.p028c.C1435a;
import com.bgjd.ici.p028c.C1436e;
import com.bgjd.ici.p030e.C1482g;
import com.bgjd.ici.p031f.C1492e;

public class C1463n extends C1409k<JSONObject> {
    private int f2312f;

    public /* synthetic */ Object mo2325d() {
        return m3033f();
    }

    public C1463n(C1395h c1395h) {
        super(c1395h);
        this.f2312f = 0;
        this.b = C1403a.f2091u;
        this.e = "ptrlog";
    }

    public JSONObject m3033f() {
        JSONObject jSONObject = new JSONObject();
        Object jSONArray = new JSONArray();
        boolean IsSandbox = this.a.IsSandbox();
        boolean isAccepted = this.a.isAccepted();
        if (!IsSandbox && isAccepted) {
            C1434d c1435a = new C1435a(new C1410m(this.a));
            c1435a.open();
            if (c1435a.IsConnected()) {
                C1482g c1482g;
                int a;
                C1492e c1492e = (C1492e) c1435a.getMapper(C1492e.class, C1482g.class);
                c1492e.m3186a(2);
                C1436e a2 = c1492e.m3185a(1, 20);
                if (this.c == 0) {
                    while (a2.read()) {
                        c1482g = (C1482g) a2.fetch();
                        jSONArray.put(c1482g.m3104g());
                        this.c++;
                        a = c1482g.m3098a();
                        if (a > this.f2312f) {
                            this.f2312f = a;
                        }
                    }
                }
                a2.close();
                if (this.c < 20) {
                    a2 = c1492e.m3185a(0, 20 - this.c);
                    while (a2.read()) {
                        c1482g = (C1482g) a2.fetch();
                        jSONArray.put(c1482g.m3104g());
                        this.c++;
                        a = c1482g.m3098a();
                        if (a > this.f2312f) {
                            this.f2312f = a;
                            c1492e.m3184a(1, (long) a);
                        }
                    }
                    a2.close();
                }
            }
            c1435a.close();
        }
        try {
            jSONObject.put("a", jSONArray);
            jSONObject.put("b", this.c);
            jSONObject.put("c", this.f2312f);
            this.a.setTransactionHistory(mo2298a(), Long.valueOf((long) this.f2312f));
        } catch (JSONException e) {
        }
        return jSONObject;
    }
}
