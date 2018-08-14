package com.bgjd.ici.p029d;

import com.bgjd.ici.json.JSONArray;
import com.bgjd.ici.json.JSONException;
import com.bgjd.ici.json.JSONObject;
import com.bgjd.ici.p025b.C1395h;
import com.bgjd.ici.p025b.C1408j.C1403a;
import com.bgjd.ici.p025b.C1408j.C1407c.C1406b;
import com.bgjd.ici.p025b.C1409k;
import com.bgjd.ici.p025b.C1410m;
import com.bgjd.ici.p028c.C1434d;
import com.bgjd.ici.p028c.C1435a;
import com.bgjd.ici.p028c.C1436e;
import com.bgjd.ici.p030e.C1476a;
import com.bgjd.ici.p031f.C1488a;

public class C1457h extends C1409k<JSONObject> {
    private int f2251f;

    public /* synthetic */ Object mo2325d() {
        return m3002f();
    }

    public C1457h(C1395h c1395h) {
        super(c1395h);
        this.f2251f = 0;
        this.b = C1403a.f2090t;
        this.e = C1406b.f2154d;
    }

    public JSONObject m3002f() {
        JSONObject jSONObject = new JSONObject();
        Object jSONArray = new JSONArray();
        boolean IsSandbox = this.a.IsSandbox();
        boolean isAccepted = this.a.isAccepted();
        if (!IsSandbox && isAccepted) {
            C1434d c1435a = new C1435a(new C1410m(this.a));
            c1435a.open();
            if (c1435a.IsConnected()) {
                C1436e a;
                C1476a c1476a;
                int c;
                C1488a c1488a = (C1488a) c1435a.getMapper(C1488a.class, C1476a.class);
                if (this.c == 0) {
                    a = c1488a.m3164a(1, 50);
                    while (a.read()) {
                        c1476a = (C1476a) a.fetch();
                        jSONArray.put(c1476a.m3065b());
                        this.c++;
                        c = c1476a.m3066c();
                        if (c > this.f2251f) {
                            this.f2251f = c;
                        }
                    }
                    a.close();
                }
                if (this.c < 50) {
                    a = c1488a.m3164a(0, 50);
                    while (a.read()) {
                        c1476a = (C1476a) a.fetch();
                        if (c1488a.m3162a(c1476a.m3066c()) > 0) {
                            this.c++;
                            jSONArray.put(c1476a.m3065b());
                            c = c1476a.m3066c();
                            if (c > this.f2251f) {
                                this.f2251f = c;
                            }
                        }
                    }
                    a.close();
                }
            }
            c1435a.close();
        }
        try {
            jSONObject.put("a", jSONArray);
            jSONObject.put("b", this.c);
            jSONObject.put("c", this.f2251f);
            this.a.setTransactionHistory(mo2298a(), Long.valueOf((long) this.f2251f));
        } catch (JSONException e) {
        }
        return jSONObject;
    }
}
