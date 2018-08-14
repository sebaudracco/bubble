package com.bgjd.ici.p025b;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.Uri;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;
import com.bgjd.ici.MarketBeaconService;
import com.bgjd.ici.json.JSONArray;
import com.bgjd.ici.json.JSONException;
import com.bgjd.ici.json.JSONObject;
import com.bgjd.ici.p025b.C1408j.C1403a;
import com.bgjd.ici.p025b.C1408j.C1404b;
import com.bgjd.ici.p025b.C1408j.C1407c;
import com.bgjd.ici.p030e.C1480e;
import com.bgjd.ici.p030e.C1485h.C1484a.C1483a;
import com.bgjd.ici.plugin.C1520d;
import com.bgjd.ici.plugin.C1532j;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;

public class aa implements C1395h {
    private static final String f2048a = "SETT";
    private Context f2049b = null;
    private Intent f2050c = null;
    private SharedPreferences f2051d = null;
    private JSONObject f2052e = null;
    private JSONObject f2053f = null;
    private boolean f2054g = false;
    private Map<String, Long> f2055h = new HashMap();

    public aa(Context context) {
        this.f2049b = context;
        this.f2051d = context.getSharedPreferences(C1403a.f2079i, 0);
    }

    private void ad() {
        if (this.f2052e == null) {
            try {
                this.f2052e = new JSONObject(this.f2051d.getString(C1404b.f2107K, "{}"));
            } catch (Throwable e) {
                C1402i.m2898a(f2048a, e, e.getMessage());
            }
        }
    }

    public SharedPreferences getPreferences() {
        return this.f2051d;
    }

    private void ae() {
        if (this.f2053f == null) {
            String string = this.f2051d.getString(C1404b.f2104H, "{}");
            try {
                if (string.length() > 10) {
                    this.f2053f = new JSONObject(string);
                } else {
                    this.f2053f = new JSONObject();
                }
            } catch (Throwable e) {
                C1402i.m2898a(f2048a, e, e.getMessage());
            }
        }
    }

    public Context getContext() {
        return this.f2049b;
    }

    public String mo2292w() {
        String str = C1403a.f2075e;
        try {
            ad();
            if (this.f2052e == null || !this.f2052e.has(C1403a.f2082l)) {
                return str;
            }
            JSONObject jSONObject = this.f2052e.getJSONObject(C1403a.f2082l);
            if (jSONObject == null) {
                return str;
            }
            String x = mo2293x();
            if (!jSONObject.has(x)) {
                return str;
            }
            String string = jSONObject.getString(x);
            if (string.length() <= 0) {
                return str;
            }
            String[] split = string.split(",");
            return split[new Random().nextInt(split.length)];
        } catch (JSONException e) {
            return str;
        }
    }

    public String getPackageFilter() {
        try {
            ad();
            if (this.f2052e != null && this.f2052e.has(C1404b.aj)) {
                JSONObject jSONObject = this.f2052e.getJSONObject(C1404b.aj);
                if (jSONObject.has(C1404b.ak)) {
                    String string = jSONObject.getString(C1404b.ak);
                    if (string.length() > 0) {
                        return string;
                    }
                }
            }
        } catch (JSONException e) {
        }
        return C1403a.f2083m;
    }

    public String getSystemFilter() {
        try {
            ad();
            if (this.f2052e != null && this.f2052e.has(C1404b.aj)) {
                JSONObject jSONObject = this.f2052e.getJSONObject(C1404b.aj);
                if (jSONObject.has(C1404b.al)) {
                    String string = jSONObject.getString(C1404b.al);
                    if (string.length() > 0) {
                        return string;
                    }
                }
            }
        } catch (JSONException e) {
        }
        return C1403a.f2084n;
    }

    public List<C1426t> mo2269k() {
        List<C1426t> arrayList = new ArrayList();
        arrayList.add(new C1426t("default", C1404b.an));
        arrayList.add(new C1426t("chrome", C1404b.ao));
        ad();
        if (this.f2052e != null) {
            try {
                if (this.f2052e.has("browsing")) {
                    JSONArray jSONArray = this.f2052e.getJSONArray("browsing");
                    for (int i = 0; i < jSONArray.length(); i++) {
                        arrayList.add(new C1426t(jSONArray.getJSONObject(i).getString("name"), Uri.parse(String.format("content://%s", new Object[]{r4.getString("uri")}))));
                    }
                }
            } catch (Throwable e) {
                C1402i.m2898a(f2048a, e, e.getMessage());
            }
        }
        return arrayList;
    }

    public int getBrowsingLimit() {
        return 150;
    }

    public String mo2271l() {
        return C1403a.f2077g;
    }

    public String mo2276n() {
        return C1403a.f2072b;
    }

    public String mo2277o() {
        return C1403a.f2074d;
    }

    public JSONArray mo2281q() {
        ad();
        try {
            if (this.f2052e.has(C1407c.f2167g)) {
                return this.f2052e.getJSONArray(C1407c.f2167g);
            }
        } catch (Throwable e) {
            C1402i.m2898a(f2048a, e, e.getMessage());
        }
        return null;
    }

    public int mo2273m() {
        return 3;
    }

    public int mo2279p() {
        return C1403a.f2073c;
    }

    public void mo2203a(JSONObject jSONObject) {
        new C1432z(this, jSONObject).mo2311a();
    }

    public void mo2209a(String str, Throwable th) {
        mo2223c(300000);
    }

    public void mo2204a(String str) {
        mo2223c(300000);
    }

    public boolean IsSandbox() {
        return this.f2054g;
    }

    public void mo2229d(boolean z) {
        this.f2054g = z;
    }

    public JSONObject getBrowserDate() {
        String string = this.f2051d.getString("browsing", "");
        JSONObject jSONObject = new JSONObject();
        if (string.length() <= 0) {
            return jSONObject;
        }
        try {
            return new JSONObject(string);
        } catch (JSONException e) {
            return jSONObject;
        }
    }

    public int getEmailCount() {
        return this.f2051d.getInt("email", 0);
    }

    public long getPackageDate() {
        return this.f2051d.getLong("package", 0);
    }

    public void mo2223c(long j) {
        AlarmManager alarmManager = (AlarmManager) this.f2049b.getSystemService(NotificationCompat.CATEGORY_ALARM);
        Intent intent = new Intent();
        intent.setClassName(this.f2049b, C1404b.aQ);
        intent.setAction(C1404b.af);
        intent.putExtra(C1404b.f2099C, this.f2054g);
        PendingIntent service = PendingIntent.getService(this.f2049b, 0, intent, 0);
        alarmManager.cancel(service);
        alarmManager.set(3, SystemClock.elapsedRealtime() + j, service);
    }

    public void mo2184K() {
        AlarmManager alarmManager = (AlarmManager) this.f2049b.getSystemService(NotificationCompat.CATEGORY_ALARM);
        Intent intent = new Intent();
        intent.setClassName(this.f2049b, C1404b.aQ);
        intent.setAction(C1404b.af);
        intent.putExtra(C1404b.f2099C, this.f2054g);
        alarmManager.cancel(PendingIntent.getService(this.f2049b, 0, intent, 0));
    }

    public void setTransactionHistory(String str, Long l) {
        this.f2055h.put(str, l);
    }

    public Map<String, Long> mo2176D() {
        return this.f2055h;
    }

    public boolean mo2211a() {
        return this.f2051d.getBoolean(C1404b.f2102F, false);
    }

    public boolean mo2221b() {
        return this.f2051d.getBoolean(C1404b.f2100D, false);
    }

    public boolean mo2226c() {
        return this.f2051d.getBoolean(C1404b.f2101E, false);
    }

    public void mo2210a(boolean z) {
        Editor edit = this.f2051d.edit();
        edit.putBoolean(C1404b.f2103G, z);
        edit.commit();
    }

    public void mo2220b(boolean z) {
        Editor edit = this.f2051d.edit();
        edit.putBoolean(C1404b.f2100D, z);
        edit.commit();
    }

    public void mo2225c(boolean z) {
        Editor edit = this.f2051d.edit();
        edit.putBoolean(C1404b.f2102F, z);
        edit.commit();
    }

    public boolean isAccepted() {
        return this.f2051d.getBoolean(C1404b.f2103G, false);
    }

    public String mo2283r() {
        return this.f2051d.getString(C1404b.f2113Q, "");
    }

    public boolean mo2284s() {
        return this.f2051d.getBoolean(C1404b.f2114R, false);
    }

    public void mo2216b(String str) {
        Editor edit = this.f2051d.edit();
        edit.putString(C1404b.f2113Q, str);
        edit.commit();
    }

    public void mo2232e(boolean z) {
        Editor edit = this.f2051d.edit();
        edit.putBoolean(C1404b.f2114R, z);
        edit.commit();
    }

    public long mo2289t() {
        return this.f2051d.getLong(C1404b.f2116T, 0);
    }

    public void mo2201a(long j) {
        Editor edit = this.f2051d.edit();
        edit.putLong(C1404b.f2116T, j);
        edit.commit();
    }

    public boolean mo2290u() {
        long t = mo2289t();
        if ((t <= 0 || t > (System.currentTimeMillis() / 1000) - 60) && this.f2051d.getString(C1404b.f2113Q, "").length() > 10) {
            return true;
        }
        return false;
    }

    public boolean ab() {
        return ac().length() > 0;
    }

    public boolean mo2174B() {
        return mo2175C().length() > 0;
    }

    public String ac() {
        return this.f2051d.getString("tmp_advertiser_id", "");
    }

    public String mo2175C() {
        return this.f2051d.getString("tmp_android_id", "");
    }

    public void mo2231e(String str) {
        Editor edit = this.f2051d.edit();
        edit.putString("tmp_android_id", str);
        edit.commit();
    }

    public void mo2200a(int i) {
        Editor edit = this.f2051d.edit();
        edit.putInt("tmp_android_id_uniqueness", i);
        edit.commit();
    }

    public int mo2173A() {
        return this.f2051d.getInt("tmp_android_id_uniqueness", 0);
    }

    public boolean mo2291v() {
        long j = this.f2051d.getLong(C1404b.f2115S, 0);
        if (j <= 0 || (System.currentTimeMillis() / 1000) - j > 10) {
            return false;
        }
        return true;
    }

    public void mo2214b(long j) {
        Editor edit = this.f2051d.edit();
        edit.putLong(C1404b.f2115S, j);
        edit.commit();
    }

    public String mo2227d() {
        ae();
        try {
            return this.f2053f.getString("message").replace("arrow.png", C1431y.f2219a).replace("share.png", C1431y.f2220b).replace("optout.png", C1431y.f2221c);
        } catch (JSONException e) {
            return "";
        }
    }

    public String mo2230e() {
        ae();
        try {
            return this.f2053f.getString(C1404b.f2108L);
        } catch (JSONException e) {
            return "Accept";
        }
    }

    public String mo2234f() {
        ae();
        try {
            return this.f2053f.getString(C1404b.f2109M);
        } catch (JSONException e) {
            return C1404b.f2106J;
        }
    }

    public String mo2237g() {
        ae();
        try {
            return this.f2053f.getString("title");
        } catch (JSONException e) {
            return "EULA";
        }
    }

    public String mo2222c(String str) {
        return this.f2051d.getString(str, SchemaSymbols.ATTVAL_FALSE_0);
    }

    public void mo2208a(String str, String str2) {
        Editor edit = this.f2051d.edit();
        edit.putString(str, str2);
        edit.commit();
    }

    public JSONObject getSupportedFeatures() {
        ad();
        try {
            return this.f2052e.getJSONObject(C1404b.f2119W);
        } catch (JSONException e) {
            return null;
        }
    }

    public String mo2293x() {
        ad();
        try {
            return this.f2052e.getString("source");
        } catch (JSONException e) {
            return "socket";
        }
    }

    public String[] mo2294y() {
        ad();
        Object obj = C1403a.f2081k;
        if (this.f2052e != null) {
            try {
                if (this.f2052e.has(C1403a.f2088r)) {
                    Object split = this.f2052e.getString(C1403a.f2088r).split(",");
                    if (split.length > 0) {
                        Object obj2 = new String[(obj.length + split.length)];
                        System.arraycopy(obj, 0, obj2, 0, obj.length);
                        System.arraycopy(split, 0, obj2, obj.length, split.length);
                        return obj2;
                    }
                }
            } catch (JSONException e) {
            }
        }
        return obj;
    }

    public String getDatabasePath() {
        return C1403a.f2068J;
    }

    public int getDatabaseVersion() {
        return 1;
    }

    public String[] getDatabseCreateTables() {
        return new String[]{C1403a.f2062D, C1403a.f2063E, C1403a.f2064F, C1403a.f2065G, C1403a.f2061C, C1403a.f2066H, C1403a.f2067I};
    }

    public String[] getDatabaseTableNames() {
        return new String[]{C1403a.f2092v, C1403a.f2093w, C1403a.f2094x, C1403a.f2095y, C1403a.f2096z, C1403a.f2059A, C1403a.f2060B};
    }

    public String mo2295z() {
        return this.f2051d.getString(C1404b.av, C1404b.ax);
    }

    public void mo2228d(String str) {
        Editor edit = this.f2051d.edit();
        edit.putString(C1404b.av, str);
        edit.commit();
    }

    public void mo2206a(String str, long j) {
        Editor edit = this.f2051d.edit();
        edit.putLong(str, j);
        edit.commit();
    }

    public long mo2233f(String str) {
        return this.f2051d.getLong(str, 0);
    }

    public int mo2262h() {
        ae();
        try {
            if (this.f2053f.has(C1404b.az)) {
                return this.f2053f.getInt(C1404b.az);
            }
        } catch (JSONException e) {
        }
        return 200;
    }

    public int mo2264i() {
        ae();
        try {
            if (this.f2053f.has(C1404b.aA)) {
                return this.f2053f.getInt(C1404b.aA);
            }
        } catch (JSONException e) {
        }
        return 201;
    }

    public void mo2205a(String str, int i) {
        Editor edit = this.f2051d.edit();
        edit.putInt(str, i);
        edit.commit();
    }

    public int mo2236g(String str) {
        return this.f2051d.getInt(str, 0);
    }

    public void m2805c(String str, long j) {
        Editor edit = this.f2051d.edit();
        edit.putLong(str, j);
        edit.commit();
    }

    public long m2841r(String str) {
        return this.f2051d.getLong(str, 0);
    }

    public void mo2215b(JSONObject jSONObject) {
        this.f2052e = jSONObject;
    }

    public void setDeclinedTransaction(String str, int i) {
        Editor edit = this.f2051d.edit();
        edit.putInt(str, i);
        edit.commit();
    }

    public int getDeclinedTransaction(String str) {
        return this.f2051d.getInt(str, 0);
    }

    public Intent mo2177E() {
        return this.f2050c;
    }

    public void mo2202a(Intent intent) {
        this.f2050c = intent;
    }

    public float getLatitude() {
        return this.f2051d.getFloat("latitude", 0.0f);
    }

    public float getLongitude() {
        return this.f2051d.getFloat("longitude", 0.0f);
    }

    public void setLocation(float f, float f2) {
        Editor edit = this.f2051d.edit();
        edit.putFloat("latitude", f);
        edit.putFloat("longitude", f2);
        edit.commit();
    }

    public JSONObject mo2267j() {
        ae();
        try {
            if (this.f2053f.has("theme")) {
                return this.f2053f.getJSONObject("theme");
            }
        } catch (JSONException e) {
        }
        return null;
    }

    public JSONArray mo2178F() {
        ad();
        try {
            if (this.f2052e.has("additional")) {
                return this.f2052e.getJSONArray("additional");
            }
        } catch (JSONException e) {
        }
        return null;
    }

    public long mo2263h(String str) {
        return this.f2051d.getLong(str, 0);
    }

    public void mo2218b(String str, long j) {
        Editor edit = this.f2051d.edit();
        edit.putLong(str, j);
        edit.commit();
    }

    public String mo2265i(String str) {
        return this.f2051d.getString(str, SchemaSymbols.ATTVAL_FALSE_0);
    }

    public void mo2219b(String str, String str2) {
        Editor edit = this.f2051d.edit();
        edit.putString(str, str2);
        edit.commit();
    }

    public void mo2224c(String str, String str2) {
        Editor edit = this.f2051d.edit();
        edit.putString(str, str2);
        edit.commit();
    }

    public String mo2268j(String str) {
        return this.f2051d.getString(str, "");
    }

    public C1394a mo2179G() {
        String string = this.f2051d.getString("notification_title", "");
        String string2 = this.f2051d.getString("notification_message", "");
        String string3 = this.f2051d.getString("notification_url", "");
        if (string.length() <= 0 || string2.length() <= 0) {
            return null;
        }
        return new C1480e(string, string2, string3);
    }

    public void mo2270k(String str) {
        Editor edit = this.f2051d.edit();
        edit.putString("notification_title", str);
        edit.commit();
    }

    public void mo2272l(String str) {
        Editor edit = this.f2051d.edit();
        edit.putString("notification_message", str);
        edit.commit();
    }

    public void mo2274m(String str) {
        Editor edit = this.f2051d.edit();
        edit.putString("notification_url", str);
        edit.commit();
    }

    public int getExtensionId() {
        ad();
        try {
            if (this.f2052e.has(SchemaSymbols.ATTVAL_EXTENSION)) {
                return this.f2052e.getJSONObject(SchemaSymbols.ATTVAL_EXTENSION).getInt("id");
            }
        } catch (JSONException e) {
        }
        return 0;
    }

    public String mo2180H() {
        ad();
        try {
            if (this.f2052e.has(SchemaSymbols.ATTVAL_EXTENSION)) {
                return this.f2052e.getJSONObject(SchemaSymbols.ATTVAL_EXTENSION).getString("name");
            }
        } catch (JSONException e) {
        }
        return null;
    }

    public int mo2275n(String str) {
        return this.f2051d.getInt(str, 0);
    }

    public void mo2217b(String str, int i) {
        Editor edit = this.f2051d.edit();
        edit.putInt(str, i);
        edit.commit();
    }

    public String mo2181I() {
        ad();
        try {
            if (this.f2052e.has(SchemaSymbols.ATTVAL_EXTENSION)) {
                String string = this.f2052e.getJSONObject(SchemaSymbols.ATTVAL_EXTENSION).getString("url");
                if (string.length() > 0) {
                    return string;
                }
            }
        } catch (JSONException e) {
        }
        return String.format("%s/api/v1/plugin/download/%s", new Object[]{C1403a.f2076f, mo2180H().toLowerCase(Locale.US)});
    }

    public JSONArray mo2183J() {
        ad();
        try {
            if (this.f2052e.has(SchemaSymbols.ATTVAL_EXTENSION)) {
                return this.f2052e.getJSONObject(SchemaSymbols.ATTVAL_EXTENSION).getJSONArray("plugin_ids");
            }
        } catch (JSONException e) {
        }
        return null;
    }

    public void mo2185L() {
        C1520d b = C1532j.m3310b();
        Map a = b.mo2358a();
        for (String str : a.keySet()) {
            Object obj = a.get(str);
            try {
                obj.getClass().getMethod(C1483a.m3107c(), new Class[0]).invoke(obj, new Object[0]);
                b.mo2362d(str);
                mo2206a(str, 0);
                C1402i.m2901b(f2048a, "Stopping " + str);
            } catch (Throwable e) {
                C1402i.m2898a(f2048a, e, e.getMessage());
            } catch (Throwable e2) {
                C1402i.m2898a(f2048a, e2, e2.getMessage());
            } catch (Throwable e22) {
                C1402i.m2898a(f2048a, e22, e22.getMessage());
            } catch (Throwable e222) {
                C1402i.m2898a(f2048a, e222, e222.getMessage());
            }
        }
        MarketBeaconService.f2015a = null;
        mo2184K();
        C1402i.m2901b(f2048a, "Stopping SDK End-user Opted-out of Ads Personalization.");
    }

    public void mo2186M() {
        Editor edit = this.f2051d.edit();
        edit.putString(C1404b.f2104H, "{}");
        edit.putBoolean(C1404b.f2102F, false);
        edit.putString(C1404b.f2107K, "{}");
        edit.putBoolean(C1404b.f2100D, false);
        edit.putBoolean(C1404b.f2103G, false);
        edit.putBoolean(C1404b.f2101E, false);
        edit.putBoolean(C1404b.aO, false);
        edit.commit();
        C1520d b = C1532j.m3310b();
        Map a = b.mo2358a();
        for (String str : a.keySet()) {
            Object obj = a.get(str);
            try {
                obj.getClass().getMethod(C1483a.m3107c(), new Class[0]).invoke(obj, new Object[0]);
                b.mo2362d(str);
                mo2206a(str, 0);
                C1402i.m2901b(f2048a, "Stopping " + str);
            } catch (Throwable e) {
                C1402i.m2898a(f2048a, e, e.getMessage());
            } catch (Throwable e2) {
                C1402i.m2898a(f2048a, e2, e2.getMessage());
            } catch (Throwable e22) {
                C1402i.m2898a(f2048a, e22, e22.getMessage());
            } catch (Throwable e222) {
                C1402i.m2898a(f2048a, e222, e222.getMessage());
            }
        }
        MarketBeaconService.f2015a = null;
    }

    public int getPackageMaxId() {
        return this.f2051d.getInt("pkgMaxId", 0);
    }

    public void setPackageMaxId(int i) {
        Editor edit = this.f2051d.edit();
        edit.putInt("pkgMaxId", i);
        edit.commit();
    }

    public int mo2187N() {
        return this.f2051d.getInt(C1403a.f2088r, 0);
    }

    public void mo2213b(int i) {
        Editor edit = this.f2051d.edit();
        edit.putInt(C1403a.f2088r, i);
        edit.commit();
    }

    public void mo2278o(String str) {
        if (str.length() > 10) {
            Editor edit = this.f2051d.edit();
            edit.putString(C1404b.aL, str);
            edit.commit();
        }
    }

    public boolean mo2190Q() {
        return this.f2051d.getString(C1404b.aL, "").length() > 5 || !mo2188O().equalsIgnoreCase("default");
    }

    public void mo2207a(String str, Object obj) {
        if (obj != null && str != null && str.length() > 0) {
            Editor edit = this.f2051d.edit();
            edit.putString("mktid_" + str.trim().toLowerCase(Locale.US), obj.toString());
            edit.commit();
        }
    }

    public JSONObject mo2191R() {
        JSONObject jSONObject = new JSONObject();
        for (String str : this.f2051d.getAll().keySet()) {
            if (str.contains("mktid_")) {
                try {
                    jSONObject.put(str.replace("mktid_", ""), this.f2051d.getString(str, ""));
                } catch (JSONException e) {
                }
            }
        }
        return jSONObject;
    }

    public boolean mo2192S() {
        return true;
    }

    public String mo2189P() {
        return this.f2051d.getString(C1404b.aL, "");
    }

    public void mo2280p(String str) {
        Editor edit = this.f2051d.edit();
        edit.putString(C1404b.aM, str);
        edit.commit();
    }

    public String mo2188O() {
        return this.f2051d.getString(C1404b.aM, "default");
    }

    public boolean mo2193T() {
        return this.f2051d.getBoolean(C1404b.aN, false) && !mo2194U();
    }

    public void mo2235f(boolean z) {
        Editor edit = this.f2051d.edit();
        edit.putBoolean(C1404b.aN, z);
        edit.commit();
    }

    public boolean mo2194U() {
        return this.f2051d.getBoolean(C1404b.aO, false);
    }

    public void mo2238g(boolean z) {
        Editor edit = this.f2051d.edit();
        edit.putBoolean(C1404b.aO, z);
        edit.commit();
    }

    public boolean mo2195V() {
        return mo2196W() != null;
    }

    public JSONObject mo2196W() {
        JSONObject jSONObject = null;
        try {
            ad();
            if (this.f2052e != null && this.f2052e.has("ologger")) {
                jSONObject = this.f2052e.getJSONObject("ologger");
            }
        } catch (JSONException e) {
        }
        return jSONObject;
    }

    public void mo2282q(String str) {
        Editor edit = this.f2051d.edit();
        edit.putString("ologger_version", str);
        edit.commit();
    }

    public String mo2197X() {
        return this.f2051d.getString("ologger_version", "");
    }

    public String getBrowserCacheTable() {
        return C1403a.f2092v;
    }

    public String getInstalledAppCacheTable() {
        return C1403a.f2093w;
    }

    public String getProcessCacheTable() {
        return C1403a.f2094x;
    }

    public String getDetectedBeaconCacheTable() {
        return C1403a.f2095y;
    }

    public String getPartnerLogsTable() {
        return C1403a.f2096z;
    }

    public JSONObject mo2198Y() {
        JSONObject jSONObject = new JSONObject();
        try {
            ad();
            if (this.f2052e != null && this.f2052e.has("beacinfo")) {
                jSONObject = this.f2052e.getJSONObject("beacinfo");
            }
        } catch (JSONException e) {
        }
        return jSONObject;
    }

    public String mo2199Z() {
        return C1403a.f2059A;
    }

    public String aa() {
        return C1403a.f2060B;
    }
}
