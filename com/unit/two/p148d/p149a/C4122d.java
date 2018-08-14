package com.unit.two.p148d.p149a;

import android.content.Context;
import android.text.TextUtils;
import com.unit.two.p147c.C4096a;
import com.unit.two.p151f.C4138e;
import com.unit.two.p151f.C4144k;
import com.unit.two.p151f.C4145l;
import com.unit.two.p151f.p152a.C4133b;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public final class C4122d {
    static {
        String str = C4096a.bT;
    }

    public static Map m12729a(Context context, C4120b c4120b) {
        return C4122d.m12730a(context, c4120b, false);
    }

    public static Map m12730a(Context context, C4120b c4120b, boolean z) {
        Map hashMap = new HashMap();
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(C4096a.bU, C4122d.m12731b(context, c4120b, z));
            Object a = C4133b.m12760a(jSONObject.toString().getBytes(C4096a.bV), C4096a.bW);
            Object jSONObject2 = jSONObject.toString();
            if (!TextUtils.isEmpty(a)) {
                jSONObject2 = C4145l.m12826a(a.getBytes());
            }
            hashMap.put(C4096a.bX, jSONObject2);
            hashMap.put(C4096a.bY, C4096a.bZ);
            hashMap.put(C4096a.ca, C4145l.m12845g(C4096a.cb + jSONObject.toString() + C4096a.cc));
        } catch (Exception e) {
        }
        return hashMap;
    }

    private static JSONObject m12731b(Context context, C4120b c4120b, boolean z) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(C4096a.cd, C4145l.m12835d(context));
            jSONObject.put(C4096a.ce, c4120b.m12726e());
            jSONObject.put(C4096a.cf, c4120b.m12725d());
            jSONObject.put(C4096a.cg, C4145l.m12827b(context));
            jSONObject.put(C4096a.ch, z ? C4096a.ci : C4145l.m12841f(context));
            jSONObject.put(C4096a.cj, z ? C4096a.ck : C4145l.m12834d());
            jSONObject.put(C4096a.cl, c4120b.m12727f());
            jSONObject.put(C4096a.cm, c4120b.m12723b());
            jSONObject.put(C4096a.cn, C4145l.m12846h(context));
            jSONObject.put(C4096a.co, 2);
            jSONObject.put(C4096a.cp, z ? C4096a.cq : C4145l.m12838e(context));
            jSONObject.put(C4096a.cr, z ? C4096a.cs : C4145l.m12850i(context));
            jSONObject.put(C4096a.ct, C4138e.m12770a(context));
            jSONObject.put(C4096a.cu, 1);
            jSONObject.put(C4096a.cv, C4144k.m12817k(context));
            jSONObject.put(C4096a.cw, 131);
        } catch (Throwable th) {
        }
        return jSONObject;
    }
}
