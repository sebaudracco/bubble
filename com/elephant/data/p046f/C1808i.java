package com.elephant.data.p046f;

import android.text.TextUtils;
import com.elephant.data.p035a.p036a.C1711a;
import com.elephant.data.p048g.C1814b;
import com.elephant.data.p048g.C1816d;
import com.elephant.data.p048g.p049a.C1810b;
import com.elephant.data.p048g.p050b.C1813b;
import org.json.JSONObject;

final class C1808i implements C1711a {
    private /* synthetic */ C1806f f3830a;

    private C1808i(C1806f c1806f) {
        this.f3830a = c1806f;
    }

    public final void mo3544a(String str) {
        int optInt;
        int optInt2;
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject(str);
        JSONObject optJSONObject = jSONObject2.optJSONObject(C1814b.dw);
        String optString = jSONObject2.optString(C1814b.dx);
        int i;
        if (optJSONObject != null) {
            int optInt3 = optJSONObject.optInt(C1814b.dy);
            optInt = optJSONObject.optInt(C1814b.dz);
            optInt2 = optJSONObject.optInt(C1814b.dA);
            try {
                C1813b.m5262c(this.f3830a.f3825a, optJSONObject.optLong(C1814b.dB, 0));
                i = optInt3;
            } catch (Exception e) {
                e.printStackTrace();
                i = optInt3;
            } catch (Throwable th) {
                th.printStackTrace();
            }
        } else {
            optInt2 = 0;
            i = 0;
            optInt = -1;
        }
        if (!TextUtils.isEmpty(optString) && r0 == 200) {
            String a = optInt == 1 ? C1816d.m5280a(optString) : optString;
            if (optInt == 2) {
                a = C1810b.m5236b(C1816d.m5280a(a), C1814b.dC);
                if (a == null) {
                    if (this.f3830a.f3827d != null) {
                        this.f3830a.f3827d.mo3541a();
                        return;
                    }
                    return;
                }
            }
            jSONObject.put(C1814b.dD, 200);
            jSONObject.put(C1814b.dE, optInt2);
            jSONObject.put(C1814b.dF, new JSONObject(a));
            C1813b.m5263c(this.f3830a.f3825a, jSONObject.toString());
            this.f3830a.f3826c = new C1804d(jSONObject.toString());
            if (this.f3830a.f3827d != null) {
                this.f3830a.f3827d.mo3541a();
            }
        } else if (this.f3830a.f3827d != null) {
            this.f3830a.f3827d.mo3541a();
        }
    }
}
