package com.adcolony.sdk;

import com.adcolony.sdk.aa.C0595a;
import java.io.IOException;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class C0802y {
    C0802y() {
    }

    static JSONObject m1453a() {
        return new JSONObject();
    }

    static JSONObject m1454a(String str) {
        try {
            return new JSONObject(str);
        } catch (JSONException e) {
            new C0595a().m622a(e.toString()).m624a(aa.f484h);
            return new JSONObject();
        }
    }

    static JSONArray m1469b() {
        return new JSONArray();
    }

    static JSONArray m1470b(String str) {
        try {
            return new JSONArray(str);
        } catch (JSONException e) {
            new C0595a().m622a(e.toString()).m624a(aa.f484h);
            return new JSONArray();
        }
    }

    static JSONObject m1455a(JSONArray jSONArray, int i) {
        try {
            return jSONArray.getJSONObject(i);
        } catch (JSONException e) {
            new C0595a().m622a(e.toString()).m624a(aa.f484h);
            return new JSONObject();
        }
    }

    static Object m1450a(JSONObject jSONObject, String str) {
        try {
            return jSONObject.get(str);
        } catch (JSONException e) {
            return Boolean.valueOf(false);
        }
    }

    static Object m1467b(JSONArray jSONArray, int i) {
        try {
            return jSONArray.get(i);
        } catch (JSONException e) {
            return Boolean.valueOf(false);
        }
    }

    static String m1474c(JSONArray jSONArray, int i) {
        try {
            return jSONArray.getString(i);
        } catch (JSONException e) {
            return "";
        }
    }

    static String m1468b(JSONObject jSONObject, String str) {
        try {
            return jSONObject.getString(str);
        } catch (JSONException e) {
            return "";
        }
    }

    static int m1473c(JSONObject jSONObject, String str) {
        try {
            return jSONObject.getInt(str);
        } catch (JSONException e) {
            return 0;
        }
    }

    static int m1449a(JSONObject jSONObject, String str, int i) {
        try {
            i = jSONObject.getInt(str);
        } catch (JSONException e) {
        }
        return i;
    }

    static boolean m1477d(JSONObject jSONObject, String str) {
        try {
            return jSONObject.getBoolean(str);
        } catch (JSONException e) {
            return false;
        }
    }

    static double m1478e(JSONObject jSONObject, String str) {
        try {
            return jSONObject.getDouble(str);
        } catch (JSONException e) {
            return 0.0d;
        }
    }

    static JSONObject m1480f(JSONObject jSONObject, String str) {
        try {
            return jSONObject.getJSONObject(str);
        } catch (JSONException e) {
            return new JSONObject();
        }
    }

    static JSONObject m1476d(JSONArray jSONArray, int i) {
        try {
            return jSONArray.getJSONObject(i);
        } catch (JSONException e) {
            return new JSONObject();
        }
    }

    static JSONArray m1481g(JSONObject jSONObject, String str) {
        try {
            return jSONObject.getJSONArray(str);
        } catch (JSONException e) {
            return new JSONArray();
        }
    }

    static boolean m1462a(JSONObject jSONObject, String str, String str2) {
        try {
            jSONObject.put(str, str2);
            return true;
        } catch (JSONException e) {
            new C0595a().m622a("JSON error in ADCJSON put_string(): ").m622a(e.toString()).m624a(aa.f484h);
            return false;
        }
    }

    static boolean m1472b(JSONObject jSONObject, String str, int i) {
        try {
            jSONObject.put(str, i);
            return true;
        } catch (JSONException e) {
            new C0595a().m622a("JSON error in ADCJSON put_integer(): ").m622a(e.toString()).m624a(aa.f484h);
            return false;
        }
    }

    static boolean m1461a(JSONObject jSONObject, String str, long j) {
        try {
            jSONObject.put(str, j);
            return true;
        } catch (JSONException e) {
            new C0595a().m622a("JSON error in ADCJSON put_integer(): ").m622a(e.toString()).m624a(aa.f484h);
            return false;
        }
    }

    static boolean m1465a(JSONObject jSONObject, String str, boolean z) {
        try {
            jSONObject.put(str, z);
            return true;
        } catch (JSONException e) {
            new C0595a().m622a("JSON error in ADCJSON put_boolean(): ").m622a(e.toString()).m624a(aa.f484h);
            return false;
        }
    }

    static boolean m1463a(JSONObject jSONObject, String str, JSONArray jSONArray) {
        try {
            jSONObject.put(str, jSONArray);
            return true;
        } catch (JSONException e) {
            new C0595a().m622a("JSON error in ADCJSON put_array(): ").m622a(e.toString()).m624a(aa.f484h);
            return false;
        }
    }

    static boolean m1464a(JSONObject jSONObject, String str, JSONObject jSONObject2) {
        try {
            jSONObject.put(str, jSONObject2);
            return true;
        } catch (JSONException e) {
            new C0595a().m622a("JSON error in ADCJSON put_object(): ").m622a(e.toString()).m624a(aa.f484h);
            return false;
        }
    }

    static boolean m1460a(JSONObject jSONObject, String str, double d) {
        try {
            jSONObject.put(str, d);
            return true;
        } catch (JSONException e) {
            new C0595a().m622a("JSON error in ADCJSON put_double(): ").m622a(e.toString()).m624a(aa.f484h);
            return false;
        }
    }

    static void m1479e(JSONArray jSONArray, int i) {
        jSONArray.put(i);
    }

    static void m1459a(JSONArray jSONArray, boolean z) {
        jSONArray.put(z);
    }

    static void m1458a(JSONArray jSONArray, String str) {
        jSONArray.put(str);
    }

    static void m1457a(JSONArray jSONArray, Object obj) {
        jSONArray.put(obj);
    }

    static boolean m1482h(JSONObject jSONObject, String str) {
        try {
            C0594a.m605a().m1280j().m1404a(str, jSONObject.toString(), false);
            return true;
        } catch (IOException e) {
            new C0595a().m622a("IOException in ADCJSON's save_object: ").m622a(e.toString()).m624a(aa.f484h);
            return false;
        }
    }

    static JSONObject m1475c(String str) {
        try {
            return C0802y.m1454a(C0594a.m605a().m1280j().m1401a(str, false).toString());
        } catch (IOException e) {
            new C0595a().m622a("IOException in ADCJSON's load_object: ").m622a(e.toString()).m624a(aa.f484h);
            return C0802y.m1453a();
        }
    }

    static String[] m1466a(JSONArray jSONArray) {
        String[] strArr = new String[jSONArray.length()];
        for (int i = 0; i < jSONArray.length(); i++) {
            strArr[i] = C0802y.m1474c(jSONArray, i);
        }
        return strArr;
    }

    static JSONArray m1452a(String[] strArr) {
        JSONArray b = C0802y.m1469b();
        for (String a : strArr) {
            C0802y.m1458a(b, a);
        }
        return b;
    }

    static boolean m1471b(JSONArray jSONArray, String str) {
        for (int i = 0; i < jSONArray.length(); i++) {
            if (C0802y.m1474c(jSONArray, i).equals(str)) {
                return true;
            }
        }
        return false;
    }

    static boolean m1483i(JSONObject jSONObject, String str) {
        Iterator keys = jSONObject.keys();
        while (keys.hasNext()) {
            if (str.equals(keys.next())) {
                return true;
            }
        }
        return false;
    }

    static JSONArray m1451a(JSONArray jSONArray, String[] strArr, boolean z) {
        for (String str : strArr) {
            if (!z || !C0802y.m1471b(jSONArray, str)) {
                C0802y.m1458a(jSONArray, str);
            }
        }
        return jSONArray;
    }

    static JSONObject m1456a(JSONObject jSONObject, JSONObject jSONObject2) {
        try {
            Iterator keys = jSONObject2.keys();
            while (keys.hasNext()) {
                String str = (String) keys.next();
                jSONObject.put(str, jSONObject2.get(str));
            }
        } catch (JSONException e) {
        }
        return jSONObject;
    }
}
