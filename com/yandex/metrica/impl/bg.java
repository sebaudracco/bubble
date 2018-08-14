package com.yandex.metrica.impl;

import android.text.TextUtils;
import com.bgjd.ici.p025b.C1408j.C1404b;
import com.coremedia.iso.boxes.UserBox;
import com.google.firebase.analytics.FirebaseAnalytics$Param;
import com.mopub.common.Constants;
import com.yandex.metrica.impl.ob.ds;
import com.yandex.metrica.impl.ob.dt;
import com.yandex.metrica.impl.utils.C4525g.C4524a;
import com.yandex.metrica.impl.utils.C4532l;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class bg {

    static class C4361a {
        private C4360a f11783a;
        private boolean f11784b;
        private boolean f11785c;
        private boolean f11786d;
        private List<String> f11787e;
        private String f11788f;
        private String f11789g;
        private String f11790h;
        private String f11791i;
        private String f11792j;
        private String f11793k;
        private String f11794l;
        private String f11795m;
        private dt f11796n = new dt();
        private ds f11797o = null;
        private boolean f11798p;
        private boolean f11799q;

        public enum C4360a {
            BAD,
            OK
        }

        C4361a() {
        }

        void m14901a(boolean z) {
            this.f11784b = z;
        }

        public boolean m14902a() {
            return this.f11784b;
        }

        void m14904b(boolean z) {
            this.f11785c = z;
        }

        public boolean m14905b() {
            return this.f11785c;
        }

        void m14900a(List<String> list) {
            this.f11787e = list;
        }

        List<String> m14906c() {
            return this.f11787e;
        }

        void m14899a(String str) {
            this.f11788f = str;
        }

        public String m14909d() {
            return this.f11788f;
        }

        void m14903b(String str) {
            this.f11789g = str;
        }

        public String m14912e() {
            return this.f11789g;
        }

        void m14907c(String str) {
            this.f11790h = str;
        }

        public String m14915f() {
            return this.f11790h;
        }

        void m14910d(String str) {
            this.f11792j = str;
        }

        public String m14917g() {
            return this.f11792j;
        }

        void m14913e(String str) {
            this.f11793k = str;
        }

        public String m14919h() {
            return this.f11793k;
        }

        void m14916f(String str) {
            this.f11794l = str;
        }

        public String m14921i() {
            return this.f11794l;
        }

        void m14918g(String str) {
            this.f11795m = str;
        }

        public String m14922j() {
            return this.f11795m;
        }

        void m14896a(C4360a c4360a) {
            this.f11783a = c4360a;
        }

        public C4360a m14923k() {
            return this.f11783a;
        }

        public void m14898a(dt dtVar) {
            this.f11796n = dtVar;
        }

        public dt m14924l() {
            return this.f11796n;
        }

        public String m14925m() {
            return this.f11791i;
        }

        public void m14920h(String str) {
            this.f11791i = str;
        }

        public boolean m14926n() {
            return this.f11786d;
        }

        public void m14908c(boolean z) {
            this.f11786d = z;
        }

        void m14897a(ds dsVar) {
            this.f11797o = dsVar;
        }

        public ds m14927o() {
            return this.f11797o;
        }

        public void m14911d(boolean z) {
            this.f11798p = z;
        }

        public boolean m14928p() {
            return this.f11798p;
        }

        public void m14914e(boolean z) {
            this.f11799q = z;
        }

        public boolean m14929q() {
            return this.f11799q;
        }
    }

    private static String m14932a(JSONObject jSONObject, String str) {
        try {
            return jSONObject.getJSONObject(str).getString(FirebaseAnalytics$Param.VALUE);
        } catch (Exception e) {
            return "";
        }
    }

    private static String m14936b(JSONObject jSONObject, String str) {
        try {
            return jSONObject.getJSONObject(str).getJSONArray(Constants.VIDEO_TRACKING_URLS_KEY).getString(0);
        } catch (Exception e) {
            return "";
        }
    }

    private static List<String> m14938c(JSONObject jSONObject, String str) {
        try {
            JSONArray jSONArray = jSONObject.getJSONObject(str).getJSONArray(Constants.VIDEO_TRACKING_URLS_KEY);
            if (jSONArray != null && jSONArray.length() > 0) {
                List<String> arrayList = new ArrayList(jSONArray.length());
                for (int i = 0; i < jSONArray.length(); i++) {
                    arrayList.add(jSONArray.getString(i));
                }
                return arrayList;
            }
        } catch (Exception e) {
        }
        return null;
    }

    public static C4361a m14930a(byte[] bArr) {
        C4361a c4361a = new C4361a();
        try {
            C4524a c4524a = new C4524a(new String(bArr, "UTF-8"));
            c4361a.m14913e(m14932a((JSONObject) c4524a, "device_id"));
            c4361a.m14916f(m14932a((JSONObject) c4524a, UserBox.TYPE));
            JSONObject jSONObject = (JSONObject) c4524a.m16266a("query_hosts", new JSONObject());
            if (jSONObject.has(SchemaSymbols.ATTVAL_LIST)) {
                Object obj;
                jSONObject = jSONObject.getJSONObject(SchemaSymbols.ATTVAL_LIST);
                String b = m14936b(jSONObject, "get_ad");
                if (m14935a(b)) {
                    c4361a.m14899a(b);
                }
                b = m14936b(jSONObject, "report");
                if (m14935a(b)) {
                    c4361a.m14903b(b);
                }
                b = m14936b(jSONObject, "report_ad");
                if (m14935a(b)) {
                    c4361a.m14907c(b);
                }
                b = m14936b(jSONObject, "ssl_pinning");
                if (m14935a(b)) {
                    c4361a.m14910d(b);
                }
                b = m14936b(jSONObject, "bind_id");
                if (m14935a(b)) {
                    c4361a.m14920h(b);
                }
                List c = m14938c(jSONObject, "startup");
                if (bk.m14987a((Collection) c)) {
                    obj = null;
                } else {
                    obj = 1;
                }
                if (obj != null) {
                    c4361a.m14900a(c);
                }
            }
            jSONObject = ((JSONObject) c4524a.m16266a("distribution_customization", new JSONObject())).optJSONObject("clids");
            if (jSONObject != null) {
                m14934a(c4361a, jSONObject);
            }
            jSONObject = (JSONObject) c4524a.m16266a(C1404b.f2119W, new JSONObject());
            c4361a.m14901a(false);
            c4361a.m14904b(false);
            if (jSONObject.has(SchemaSymbols.ATTVAL_LIST)) {
                jSONObject = jSONObject.getJSONObject(SchemaSymbols.ATTVAL_LIST);
                if (jSONObject.has("easy_collecting")) {
                    c4361a.m14901a(jSONObject.getJSONObject("easy_collecting").optBoolean("enabled", false));
                }
                if (jSONObject.has("package_info")) {
                    c4361a.m14904b(jSONObject.getJSONObject("package_info").optBoolean("enabled", false));
                }
                if (jSONObject.has("socket")) {
                    c4361a.m14908c(jSONObject.getJSONObject("socket").optBoolean("enabled", false));
                }
                if (jSONObject.has("permissions_collecting")) {
                    c4361a.m14911d(jSONObject.getJSONObject("permissions_collecting").optBoolean("enabled", false));
                }
                if (jSONObject.has("features_collecting")) {
                    c4361a.m14914e(jSONObject.getJSONObject("features_collecting").optBoolean("enabled", false));
                }
            }
            m14933a(c4361a, c4524a);
            if (c4361a.m14926n()) {
                m14937b(c4361a, c4524a);
            }
            c4361a.m14896a(C4360a.OK);
            return c4361a;
        } catch (Exception e) {
            C4361a c4361a2 = new C4361a();
            c4361a2.m14896a(C4360a.BAD);
            return c4361a2;
        }
    }

    private static void m14933a(C4361a c4361a, C4524a c4524a) throws JSONException {
        JSONObject optJSONObject = c4524a.optJSONObject("browsers");
        if (optJSONObject != null) {
            JSONArray optJSONArray = optJSONObject.optJSONArray(SchemaSymbols.ATTVAL_LIST);
            if (optJSONArray != null) {
                dt dtVar = new dt();
                for (int i = 0; i < optJSONArray.length(); i++) {
                    JSONObject jSONObject = optJSONArray.getJSONObject(i);
                    Object optString = jSONObject.optString("package_id");
                    if (!TextUtils.isEmpty(optString)) {
                        dtVar.m15792a(optString, jSONObject.optInt("min_interval_seconds"));
                    }
                }
                c4361a.m14898a(dtVar);
            }
        }
    }

    private static void m14937b(C4361a c4361a, C4524a c4524a) {
        JSONObject optJSONObject = c4524a.optJSONObject("socket");
        if (optJSONObject != null) {
            long optLong = optJSONObject.optLong("seconds_to_live");
            String optString = optJSONObject.optString(SchemaSymbols.ATTVAL_TOKEN);
            JSONArray optJSONArray = optJSONObject.optJSONArray("ports");
            if (optLong > 0 && m14935a(optString) && optJSONArray != null && optJSONArray.length() > 0) {
                List arrayList = new ArrayList(optJSONArray.length());
                for (int i = 0; i < optJSONArray.length(); i++) {
                    int optInt = optJSONArray.optInt(i);
                    if (optInt != 0) {
                        arrayList.add(Integer.valueOf(optInt));
                    }
                }
                if (!arrayList.isEmpty()) {
                    c4361a.m14897a(new ds(optLong, optString, arrayList));
                }
            }
        }
    }

    private static boolean m14935a(String str) {
        return !bi.m14957a(str);
    }

    private static void m14934a(C4361a c4361a, JSONObject jSONObject) throws JSONException {
        Map hashMap = new HashMap();
        Iterator keys = jSONObject.keys();
        while (keys.hasNext()) {
            String str = (String) keys.next();
            JSONObject optJSONObject = jSONObject.optJSONObject(str);
            if (optJSONObject != null && optJSONObject.has(FirebaseAnalytics$Param.VALUE)) {
                hashMap.put(str, optJSONObject.getString(FirebaseAnalytics$Param.VALUE));
            }
        }
        c4361a.m14918g(C4532l.m16291a(hashMap));
    }

    public static Long m14931a(Map<String, List<String>> map) {
        if (!bk.m14988a((Map) map)) {
            Collection collection = (List) map.get("Date");
            if (!bk.m14987a(collection)) {
                try {
                    return Long.valueOf(new SimpleDateFormat("E, d MMM yyyy HH:mm:ss z", Locale.US).parse((String) collection.get(0)).getTime());
                } catch (Exception e) {
                }
            }
        }
        return null;
    }
}
