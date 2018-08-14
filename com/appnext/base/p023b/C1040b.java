package com.appnext.base.p023b;

import android.text.TextUtils;
import com.appnext.base.C1061b;
import com.appnext.base.p019a.C1017a;
import com.appnext.base.p019a.p021b.C1020b;
import com.appnext.base.p019a.p021b.C1021c;
import com.appnext.base.p019a.p022c.C1026d;
import com.appnext.base.p023b.C1042c.C1041a;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public class C1040b {
    public static boolean m2130c(C1021c c1021c) {
        if (c1021c == null) {
            return false;
        }
        int i = C1048i.cy().getInt(c1021c.getKey() + C1048i.kg, 0);
        try {
            if (i >= Integer.parseInt(c1021c.bd()) || i == 0) {
                return true;
            }
            return false;
        } catch (Throwable e) {
            C1061b.m2191a(e);
            return true;
        }
    }

    private static List<C1020b> ar(String str) {
        List<C1020b> list = null;
        try {
            list = C1017a.aM().aP().ad(str);
        } catch (Throwable th) {
            C1061b.m2191a(th);
        }
        return list;
    }

    public static boolean as(String str) {
        List ar = C1040b.ar(str);
        return ar == null || ar.size() == 0;
    }

    public static JSONObject m2126a(String str, Date date, C1041a c1041a) {
        String aC = C1047h.cx().aC(str);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("data", C1057k.m2171b(aC, c1041a));
            jSONObject.put("date", C1057k.m2164a(date));
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return jSONObject;
    }

    public static String m2124a(List<C1020b> list, C1041a c1041a) {
        JSONArray jSONArray = new JSONArray();
        try {
            for (C1020b c1020b : list) {
                if (c1020b.aY() == null || c1020b.aY().isEmpty()) {
                    return "";
                }
                jSONArray.put(C1040b.m2126a(c1020b.aY(), c1020b.aZ(), c1041a));
            }
        } catch (Throwable th) {
            C1061b.m2191a(th);
        }
        return jSONArray.toString();
    }

    public static void at(String str) {
        if (str != null) {
            C1048i.cy().putInt(str + C1048i.kg, 0);
        }
    }

    public static void au(String str) {
        C1048i.cy().putLong(str + C1048i.ke, System.currentTimeMillis());
        String str2 = str + C1048i.kg;
        C1048i.cy().putInt(str2, C1048i.cy().getInt(str2, 0) + 1);
    }

    public static JSONArray m2125a(List<C1020b> list, boolean z) {
        try {
            JSONArray jSONArray = new JSONArray();
            for (C1020b c1020b : list) {
                Object aY = c1020b.aY();
                if (!aY.isEmpty()) {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put(C1026d.gD, c1020b.aX());
                    jSONObject.put("type", c1020b.getType());
                    String str = C1026d.gE;
                    if (z) {
                        aY = C1047h.cx().aB(aY);
                    }
                    jSONObject.put(str, aY);
                    jSONObject.put(C1026d.gG, c1020b.getDataType());
                    jSONArray.put(jSONObject);
                }
            }
            return jSONArray;
        } catch (Throwable th) {
            return null;
        }
    }

    public static long m2128c(String str, String str2, C1041a c1041a) {
        long j = -1;
        if (!(TextUtils.isEmpty(str) || TextUtils.isEmpty(str2))) {
            try {
                j = C1017a.aM().aP().m2093a(new C1020b(str, str2, c1041a.getType()));
            } catch (Throwable th) {
                C1061b.m2191a(th);
            }
        }
        return j;
    }

    public static long m2129c(JSONArray jSONArray) {
        long j = -1;
        if (jSONArray != null) {
            try {
                j = C1017a.aM().aP().mo2022a(jSONArray);
            } catch (Throwable th) {
                C1061b.m2191a(th);
            }
        }
        return j;
    }

    public static boolean m2127a(String str, Map<String, String> map) {
        return C1057k.m2173b(str, (Map) map);
    }
}
