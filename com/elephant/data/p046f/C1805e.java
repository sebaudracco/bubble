package com.elephant.data.p046f;

import android.content.Context;
import android.text.TextUtils;
import com.elephant.data.ElephantLib;
import com.elephant.data.p048g.C1814b;
import com.elephant.data.p048g.C1816d;
import com.elephant.data.p048g.p049a.C1810b;
import com.elephant.data.p048g.p050b.C1813b;
import java.util.HashMap;
import java.util.Map;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;
import org.json.JSONObject;

public final class C1805e {
    static {
        String str = C1814b.dG;
    }

    public static Map m5215a(Context context, C1803c c1803c) {
        return C1805e.m5216a(context, c1803c, false);
    }

    public static Map m5216a(Context context, C1803c c1803c, boolean z) {
        Map hashMap = new HashMap();
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(C1814b.dH, z ? C1805e.m5218c(context, c1803c) : C1805e.m5217b(context, c1803c));
            Object a = C1810b.m5233a(jSONObject.toString().getBytes(C1814b.dI), C1814b.dJ);
            Object jSONObject2 = jSONObject.toString();
            if (!TextUtils.isEmpty(a)) {
                jSONObject2 = C1816d.m5284a(a.getBytes());
            }
            hashMap.put(C1814b.dK, jSONObject2);
            hashMap.put(C1814b.dL, C1814b.dM);
            hashMap.put(C1814b.dN, C1816d.m5290b(C1814b.dO + jSONObject.toString() + C1814b.dO));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hashMap;
    }

    private static JSONObject m5217b(Context context, C1803c c1803c) {
        int i = 0;
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(C1814b.dP, C1814b.dQ);
            jSONObject.put(C1814b.dR, C1816d.m5279a(context));
            jSONObject.put(C1814b.dS, c1803c.m5192d());
            jSONObject.put(C1814b.dT, c1803c.m5191c());
            jSONObject.put(C1814b.dU, C1816d.m5289b(context));
            jSONObject.put(C1814b.dV, C1816d.m5294c(context));
            jSONObject.put(C1814b.dW, C1816d.m5299d());
            jSONObject.put(C1814b.dX, String.valueOf(c1803c.m5190b()));
            long currentTimeMillis = ((System.currentTimeMillis() - C1816d.m5297d(context)) / 86400000) + 1;
            String str = C1814b.dY;
            if (currentTimeMillis <= 0) {
                currentTimeMillis = 1;
            }
            jSONObject.put(str, String.valueOf(currentTimeMillis));
            jSONObject.put(C1814b.dZ, 0);
            jSONObject.put(C1814b.ea, C1816d.m5302e(context));
            String str2 = C1814b.eb;
            if (c1803c.m5189a()) {
                i = 1;
            }
            jSONObject.put(str2, String.valueOf(i));
            jSONObject.put(C1814b.ec, SchemaSymbols.ATTVAL_TRUE_1);
            jSONObject.put(C1814b.ed, String.valueOf(C1816d.m5285a()));
            jSONObject.put(C1814b.ee, String.valueOf(C1816d.m5292b(context, C1814b.ef)));
            jSONObject.put(C1814b.eg, String.valueOf(C1816d.m5288b()));
            jSONObject.put(C1814b.eh, C1816d.m5293c());
            jSONObject.put(C1814b.ei, String.valueOf(C1813b.m5269f(context)));
            jSONObject.put(C1814b.ej, ElephantLib.SDK_VERSIONCODE);
            jSONObject.put(C1814b.ek, C1816d.m5318i(context));
            jSONObject.put(C1814b.el, C1816d.m5332w(context));
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return jSONObject;
    }

    private static JSONObject m5218c(Context context, C1803c c1803c) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(C1814b.em, C1814b.en);
            jSONObject.put(C1814b.eo, C1816d.m5279a(context));
            jSONObject.put(C1814b.ep, c1803c.m5192d());
            jSONObject.put(C1814b.eq, c1803c.m5191c());
            jSONObject.put(C1814b.er, C1816d.m5289b(context));
            jSONObject.put(C1814b.es, C1814b.et);
            jSONObject.put(C1814b.eu, C1814b.ev);
            jSONObject.put(C1814b.ew, String.valueOf(c1803c.m5190b()));
            long currentTimeMillis = ((System.currentTimeMillis() - C1816d.m5297d(context)) / 86400000) + 1;
            String str = C1814b.ex;
            if (currentTimeMillis <= 0) {
                currentTimeMillis = 1;
            }
            jSONObject.put(str, String.valueOf(currentTimeMillis));
            jSONObject.put(C1814b.ey, C1814b.ez);
            jSONObject.put(C1814b.eA, C1814b.eB);
            jSONObject.put(C1814b.eC, C1816d.m5332w(context));
            jSONObject.put(C1814b.eD, String.valueOf(c1803c.m5189a() ? 1 : 0));
            jSONObject.put(C1814b.eE, SchemaSymbols.ATTVAL_TRUE_1);
            jSONObject.put(C1814b.eF, ElephantLib.SDK_VERSIONCODE);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return jSONObject;
    }
}
