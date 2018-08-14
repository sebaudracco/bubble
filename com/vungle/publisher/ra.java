package com.vungle.publisher;

import java.util.Arrays;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: vungle */
public final class ra {
    public static Boolean m13856a(JSONObject jSONObject, String str) {
        boolean optBoolean = jSONObject.optBoolean(str, false);
        if (optBoolean) {
            return Boolean.valueOf(optBoolean);
        }
        optBoolean = jSONObject.optBoolean(str, true);
        if (!optBoolean) {
            return Boolean.valueOf(optBoolean);
        }
        return null;
    }

    public static Double m13859b(JSONObject jSONObject, String str) {
        double optDouble = jSONObject.optDouble(str);
        if (optDouble != Double.NaN) {
            return Double.valueOf(optDouble);
        }
        optDouble = jSONObject.optDouble(str, -1.0d);
        if (optDouble != -1.0d) {
            return Double.valueOf(optDouble);
        }
        return null;
    }

    public static Float m13860c(JSONObject jSONObject, String str) {
        Double b = m13859b(jSONObject, str);
        return b == null ? null : Float.valueOf(b.floatValue());
    }

    public static Integer m13861d(JSONObject jSONObject, String str) {
        int optInt = jSONObject.optInt(str, -1);
        if (optInt != -1) {
            return Integer.valueOf(optInt);
        }
        optInt = jSONObject.optInt(str, -2);
        if (optInt != -2) {
            return Integer.valueOf(optInt);
        }
        return null;
    }

    public static Long m13862e(JSONObject jSONObject, String str) {
        long optLong = jSONObject.optLong(str, -1);
        if (optLong != -1) {
            return Long.valueOf(optLong);
        }
        optLong = jSONObject.optLong(str, -2);
        if (optLong != -2) {
            return Long.valueOf(optLong);
        }
        return null;
    }

    public static String m13863f(JSONObject jSONObject, String str) {
        return jSONObject.isNull(str) ? null : jSONObject.optString(str, null);
    }

    public static String[] m13864g(JSONObject jSONObject, String str) {
        JSONArray optJSONArray = jSONObject.optJSONArray(str);
        if (optJSONArray == null) {
            return null;
        }
        int length = optJSONArray.length();
        String[] strArr = new String[length];
        for (int i = 0; i < length; i++) {
            strArr[i] = optJSONArray.optString(i, null);
        }
        return strArr;
    }

    public static List<String> m13865h(JSONObject jSONObject, String str) {
        String[] g = m13864g(jSONObject, str);
        if (g != null) {
            return Arrays.asList(g);
        }
        return null;
    }

    public static <T extends vs> JSONArray m13857a(T... tArr) throws JSONException {
        JSONArray jSONArray = null;
        if (tArr != null) {
            jSONArray = new JSONArray();
            for (vs a : tArr) {
                jSONArray.put(m13858a(a));
            }
        }
        return jSONArray;
    }

    public static JSONObject m13858a(vs vsVar) throws JSONException {
        if (vsVar != null) {
            return vsVar.mo6941a();
        }
        return null;
    }
}
