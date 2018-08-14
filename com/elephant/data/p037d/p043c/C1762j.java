package com.elephant.data.p037d.p043c;

import android.content.Context;
import android.text.TextUtils;
import com.elephant.data.p037d.C1743a;
import com.elephant.data.p037d.C1765c;
import com.elephant.data.p037d.C1767d;
import com.elephant.data.p037d.p042a.C1742a;
import com.elephant.data.p048g.C1814b;
import com.elephant.data.p048g.C1816d;
import org.json.JSONArray;

public final class C1762j {
    private Context f3658a;
    private C1754i f3659b;
    private C1756c f3660c;

    static {
        String str = C1814b.jB;
    }

    public C1762j(Context context, C1756c c1756c, C1754i c1754i) {
        this.f3658a = context;
        this.f3660c = c1756c;
        this.f3659b = c1754i;
    }

    public final void m5072a(String str) {
        try {
            JSONArray jSONArray = new JSONArray(C1742a.m5013a(str).m5015b());
            for (int i = 0; i < jSONArray.length(); i++) {
                C1765c c1765c = new C1765c(jSONArray.optJSONObject(i));
                if (!TextUtils.isEmpty(c1765c.m5086c())) {
                    switch (c1765c.m5084a()) {
                        case 3:
                            C1816d.m5286a(this.f3658a, this.f3660c.f3641a, c1765c.m5086c());
                            break;
                        case 4:
                            if (!TextUtils.isEmpty(c1765c.m5087d())) {
                                C1743a c1743a = new C1743a(c1765c.m5089f(), c1765c.m5087d(), c1765c.m5086c(), 0, 0);
                                c1743a.m5020a(true);
                                C1767d.m5094a(this.f3658a, c1743a, new C1763k(this.f3658a, this.f3659b), false, c1765c.m5085b());
                                break;
                            }
                            break;
                        default:
                            continue;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (this.f3659b != null) {
            this.f3659b.mo3534a();
        }
    }
}
