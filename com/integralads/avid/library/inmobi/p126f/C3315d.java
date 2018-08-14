package com.integralads.avid.library.inmobi.p126f;

import android.content.Context;
import android.content.res.Resources;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: AvidViewStateUtil */
public class C3315d {
    static float f8455a = Resources.getSystem().getDisplayMetrics().density;
    private static String[] f8456b = new String[]{"x", "y", "width", "height"};

    public static void m11296a(Context context) {
        if (context != null) {
            f8455a = context.getResources().getDisplayMetrics().density;
        }
    }

    public static JSONObject m11293a() {
        return C3315d.m11295a(C3315d.m11294a(0, 0, 0, 0));
    }

    public static JSONObject m11295a(JSONObject jSONObject) {
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject2.put("timestamp", C3315d.m11301b());
            jSONObject2.put("rootView", jSONObject);
        } catch (Exception e) {
            C3312a.m11279a("Error with creating treeJSONObject", e);
        }
        return jSONObject2;
    }

    public static JSONObject m11294a(int i, int i2, int i3, int i4) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("x", (double) C3315d.m11292a(i));
            jSONObject.put("y", (double) C3315d.m11292a(i2));
            jSONObject.put("width", (double) C3315d.m11292a(i3));
            jSONObject.put("height", (double) C3315d.m11292a(i4));
        } catch (Exception e) {
            C3312a.m11279a("Error with creating viewStateObject", e);
        }
        return jSONObject;
    }

    static float m11292a(int i) {
        return ((float) i) / f8455a;
    }

    public static void m11297a(JSONObject jSONObject, String str) {
        try {
            jSONObject.put("id", str);
        } catch (Exception e) {
            C3312a.m11279a("Error with setting avid id", e);
        }
    }

    public static void m11298a(JSONObject jSONObject, List<String> list) {
        if (list != null && !list.isEmpty()) {
            JSONArray jSONArray = new JSONArray();
            Collections.sort(list);
            for (String put : list) {
                jSONArray.put(put);
            }
            try {
                jSONObject.put("isFriendlyObstructionFor", jSONArray);
            } catch (Exception e) {
                C3312a.m11279a("Error with setting friendly obstruction", e);
            }
        }
    }

    public static boolean m11300a(JSONObject jSONObject, JSONObject jSONObject2) {
        for (String str : f8456b) {
            if (jSONObject.optDouble(str) != jSONObject2.optDouble(str)) {
                return false;
            }
        }
        if (!jSONObject.optString("id", "").equals(jSONObject2.optString("id", ""))) {
            return false;
        }
        JSONArray optJSONArray = jSONObject.optJSONArray("isFriendlyObstructionFor");
        JSONArray optJSONArray2 = jSONObject2.optJSONArray("isFriendlyObstructionFor");
        if (optJSONArray == null && optJSONArray2 == null) {
            return true;
        }
        if (optJSONArray == null || optJSONArray2 == null || optJSONArray.length() != optJSONArray2.length()) {
            return false;
        }
        return C3315d.m11299a(optJSONArray, optJSONArray2);
    }

    private static boolean m11299a(JSONArray jSONArray, JSONArray jSONArray2) {
        for (int i = 0; i < jSONArray.length(); i++) {
            if (!jSONArray.optString(i, "").equals(jSONArray2.optString(i, ""))) {
                return false;
            }
        }
        return true;
    }

    public static long m11301b() {
        return new Date().getTime();
    }
}
