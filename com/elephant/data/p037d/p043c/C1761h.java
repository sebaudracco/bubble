package com.elephant.data.p037d.p043c;

import android.content.Context;
import android.text.TextUtils;
import com.elephant.data.ElephantLib;
import com.elephant.data.p046f.C1804d;
import com.elephant.data.p046f.C1806f;
import com.elephant.data.p048g.C1814b;
import com.elephant.data.p048g.C1816d;
import com.elephant.data.p048g.p049a.C1810b;
import com.elephant.data.p048g.p050b.C1813b;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public class C1761h {
    private Context f3654a;
    private String f3655b;
    private C1756c f3656c;
    private C1754i f3657d;

    static {
        String str = C1814b.iM;
    }

    public C1761h(Context context, C1756c c1756c, C1754i c1754i) {
        this.f3654a = context;
        this.f3656c = c1756c;
        this.f3657d = c1754i;
    }

    private Map m5069b() {
        Map hashMap = new HashMap();
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(C1814b.iS, m5070c());
            Object a = C1810b.m5233a(jSONObject.toString().getBytes(C1814b.iT), C1814b.iU);
            Object jSONObject2 = jSONObject.toString();
            if (!TextUtils.isEmpty(a)) {
                jSONObject2 = C1816d.m5284a(a.getBytes());
            }
            String b = C1816d.m5290b(C1814b.iV + jSONObject.toString() + C1814b.iV);
            hashMap.put(C1814b.iW, jSONObject2);
            hashMap.put(C1814b.iX, C1814b.iY);
            hashMap.put(C1814b.iZ, b);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return hashMap;
    }

    private JSONObject m5070c() {
        int i = 1;
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(C1814b.ja, this.f3655b);
            jSONObject.put(C1814b.jb, "");
            jSONObject.put(C1814b.jc, System.currentTimeMillis());
            jSONObject.put(C1814b.jd, C1816d.m5294c(this.f3654a));
            jSONObject.put(C1814b.je, C1813b.m5258b(this.f3654a));
            jSONObject.put(C1814b.jf, C1816d.m5302e(this.f3654a));
            jSONObject.put(C1814b.jg, C1816d.m5318i(this.f3654a));
            jSONObject.put(C1814b.jh, C1816d.m5332w(this.f3654a));
            jSONObject.put(C1814b.ji, ElephantLib.SDK_NAME);
            jSONObject.put(C1814b.jj, "226");
            jSONObject.put(C1814b.jk, C1814b.jl);
            jSONObject.put(C1814b.jm, C1816d.m5285a() ? 1 : 0);
            String str = C1814b.jn;
            if (!C1816d.m5322m(this.f3654a)) {
                i = 0;
            }
            jSONObject.put(str, i);
            jSONObject.put(C1814b.jo, 0);
            jSONObject.put(C1814b.jp, C1816d.m5299d());
            jSONObject.put(C1814b.jq, C1816d.m5295c(this.f3654a, ""));
            jSONObject.put(C1814b.jr, C1816d.m5278a(this.f3654a, ""));
            jSONObject.put(C1814b.js, C1814b.jt);
            jSONObject.put(C1814b.ju, this.f3656c.f3642b);
            jSONObject.put(C1814b.jv, String.valueOf(C1816d.m5321l(this.f3654a)));
            C1804d a = C1806f.m5221a(this.f3654a).m5223a();
            if (a.m5193a()) {
                jSONObject.put(C1814b.jw, a.m5195b());
            }
            jSONObject.put(C1814b.jx, String.valueOf(C1813b.m5269f(this.f3654a)));
            jSONObject.put(C1814b.jy, C1816d.m5298d(this.f3654a, ""));
            jSONObject.put(C1814b.jz, C1816d.m5301e());
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return jSONObject;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void m5071a() {
        /*
        r6 = this;
        r1 = 0;
        r0 = r6.f3654a;
        r0 = com.elephant.data.p048g.p050b.C1813b.m5252a(r0);
        r6.f3655b = r0;
        r0 = r6.f3655b;
        r0 = android.text.TextUtils.isEmpty(r0);
        if (r0 == 0) goto L_0x001b;
    L_0x0011:
        r0 = r6.f3657d;
        if (r0 == 0) goto L_0x001a;
    L_0x0015:
        r0 = r6.f3657d;
        r0.mo3534a();
    L_0x001a:
        return;
    L_0x001b:
        r0 = r6.f3656c;
        r0 = r0.f3642b;
        r2 = -1;
        if (r0 != r2) goto L_0x002c;
    L_0x0022:
        r0 = r6.f3657d;
        if (r0 == 0) goto L_0x001a;
    L_0x0026:
        r0 = r6.f3657d;
        r0.mo3534a();
        goto L_0x001a;
    L_0x002c:
        r0 = r6.f3654a;
        r0 = com.elephant.data.p048g.C1816d.m5323n(r0);
        if (r0 != 0) goto L_0x003e;
    L_0x0034:
        r0 = r6.f3657d;
        if (r0 == 0) goto L_0x001a;
    L_0x0038:
        r0 = r6.f3657d;
        r0.mo3534a();
        goto L_0x001a;
    L_0x003e:
        r3 = com.elephant.data.p037d.p043c.C1761h.class;
        monitor-enter(r3);
        r0 = r6.f3654a;	 Catch:{ Exception -> 0x00d7 }
        r4 = java.lang.System.currentTimeMillis();	 Catch:{ Exception -> 0x00d7 }
        com.elephant.data.p048g.p050b.C1813b.m5265d(r0, r4);	 Catch:{ Exception -> 0x00d7 }
        r0 = r6.m5069b();	 Catch:{ Exception -> 0x00d7 }
        r2 = new com.elephant.data.a.a.e;	 Catch:{ Exception -> 0x00d7 }
        r2.<init>();	 Catch:{ Exception -> 0x00d7 }
        r4 = com.elephant.data.p039b.p040a.C1731a.f3562e;	 Catch:{ Exception -> 0x00d7 }
        r5 = r6.f3654a;	 Catch:{ Exception -> 0x00d7 }
        r5 = com.elephant.data.p048g.C1816d.m5306f(r5);	 Catch:{ Exception -> 0x00d7 }
        r0 = r2.m4957a(r4, r0, r5);	 Catch:{ Exception -> 0x00d7 }
        r2 = new org.json.JSONObject;	 Catch:{ Exception -> 0x00d7 }
        r2.<init>(r0);	 Catch:{ Exception -> 0x00d7 }
        r0 = com.elephant.data.p048g.C1814b.iN;	 Catch:{ Exception -> 0x00d7 }
        r4 = r2.optJSONObject(r0);	 Catch:{ Exception -> 0x00d7 }
        r0 = com.elephant.data.p048g.C1814b.iO;	 Catch:{ Exception -> 0x00d7 }
        r0 = r2.optString(r0);	 Catch:{ Exception -> 0x00d7 }
        if (r4 == 0) goto L_0x00dc;
    L_0x0072:
        r1 = com.elephant.data.p048g.C1814b.iP;	 Catch:{ Exception -> 0x00d7 }
        r2 = r4.optInt(r1);	 Catch:{ Exception -> 0x00d7 }
        r1 = com.elephant.data.p048g.C1814b.iQ;	 Catch:{ Exception -> 0x00d7 }
        r1 = r4.optInt(r1);	 Catch:{ Exception -> 0x00d7 }
    L_0x007e:
        r4 = android.text.TextUtils.isEmpty(r0);	 Catch:{ Exception -> 0x00d7 }
        if (r4 != 0) goto L_0x0088;
    L_0x0084:
        r4 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        if (r2 == r4) goto L_0x0096;
    L_0x0088:
        r0 = r6.f3657d;	 Catch:{ Exception -> 0x00d7 }
        if (r0 == 0) goto L_0x0091;
    L_0x008c:
        r0 = r6.f3657d;	 Catch:{ Exception -> 0x00d7 }
        r0.mo3534a();	 Catch:{ Exception -> 0x00d7 }
    L_0x0091:
        monitor-exit(r3);	 Catch:{ all -> 0x0093 }
        goto L_0x001a;
    L_0x0093:
        r0 = move-exception;
        monitor-exit(r3);	 Catch:{ all -> 0x0093 }
        throw r0;
    L_0x0096:
        r2 = 1;
        if (r1 != r2) goto L_0x009d;
    L_0x0099:
        r0 = com.elephant.data.p048g.C1816d.m5280a(r0);	 Catch:{ Exception -> 0x00d7 }
    L_0x009d:
        r2 = 2;
        if (r1 != r2) goto L_0x00aa;
    L_0x00a0:
        r0 = com.elephant.data.p048g.C1816d.m5280a(r0);	 Catch:{ Exception -> 0x00d7 }
        r1 = com.elephant.data.p048g.C1814b.iR;	 Catch:{ Exception -> 0x00d7 }
        r0 = com.elephant.data.p048g.p049a.C1810b.m5236b(r0, r1);	 Catch:{ Exception -> 0x00d7 }
    L_0x00aa:
        r1 = android.text.TextUtils.isEmpty(r0);	 Catch:{ Exception -> 0x00d7 }
        if (r1 != 0) goto L_0x00bd;
    L_0x00b0:
        r1 = new com.elephant.data.d.a.a;	 Catch:{ Exception -> 0x00d7 }
        r4 = java.lang.System.currentTimeMillis();	 Catch:{ Exception -> 0x00d7 }
        r1.<init>(r4, r0);	 Catch:{ Exception -> 0x00d7 }
        r0 = r1.toString();	 Catch:{ Exception -> 0x00d7 }
    L_0x00bd:
        r1 = new com.elephant.data.d.c.j;	 Catch:{ Exception -> 0x00d7 }
        r2 = r6.f3654a;	 Catch:{ Exception -> 0x00d7 }
        r4 = r6.f3656c;	 Catch:{ Exception -> 0x00d7 }
        r5 = r6.f3657d;	 Catch:{ Exception -> 0x00d7 }
        r1.<init>(r2, r4, r5);	 Catch:{ Exception -> 0x00d7 }
        r1.m5072a(r0);	 Catch:{ Exception -> 0x00d7 }
    L_0x00cb:
        r0 = r6.f3657d;	 Catch:{ all -> 0x0093 }
        if (r0 == 0) goto L_0x00d4;
    L_0x00cf:
        r0 = r6.f3657d;	 Catch:{ all -> 0x0093 }
        r0.mo3534a();	 Catch:{ all -> 0x0093 }
    L_0x00d4:
        monitor-exit(r3);	 Catch:{ all -> 0x0093 }
        goto L_0x001a;
    L_0x00d7:
        r0 = move-exception;
        r0.printStackTrace();	 Catch:{ all -> 0x0093 }
        goto L_0x00cb;
    L_0x00dc:
        r2 = r1;
        goto L_0x007e;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.elephant.data.d.c.h.a():void");
    }
}
