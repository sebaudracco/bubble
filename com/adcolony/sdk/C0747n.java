package com.adcolony.sdk;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Build.VERSION;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import com.adcolony.sdk.aa.C0595a;
import com.bgjd.ici.p025b.C1408j.C1404b;
import com.google.firebase.analytics.FirebaseAnalytics$Param;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import mf.org.apache.xerces.impl.Constants;
import org.json.JSONArray;
import org.json.JSONObject;

class C0747n {
    static final String f1329h = "Production";
    String f1330a = "";
    boolean f1331b;
    boolean f1332c;
    boolean f1333d;
    JSONObject f1334e = C0802y.m1453a();
    int f1335f = 2;
    String f1336g = "";
    private String f1337i = "android";
    private String f1338j = "android_native";
    private JSONArray f1339k = C0802y.m1469b();

    class C07441 implements ah {
        final /* synthetic */ C0747n f1323a;

        C07441(C0747n c0747n) {
            this.f1323a = c0747n;
        }

        public void mo1863a(final af afVar) {
            az.m880a(new Runnable(this) {
                final /* synthetic */ C07441 f1322b;

                public void run() {
                    if (this.f1322b.f1323a.m1328s() < 14) {
                        new C0746a(this.f1322b.f1323a, afVar, this.f1322b.f1323a, false).execute(new Void[0]);
                    } else {
                        new C0746a(this.f1322b.f1323a, afVar, this.f1322b.f1323a, false).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
                    }
                }
            });
        }
    }

    class C07452 implements ah {
        final /* synthetic */ C0747n f1324a;

        C07452(C0747n c0747n) {
            this.f1324a = c0747n;
        }

        public void mo1863a(af afVar) {
            JSONObject a = C0802y.m1453a();
            C0802y.m1465a(a, "result", az.m881a(C0802y.m1468b(afVar.m698c(), "name")));
            C0802y.m1465a(a, "success", true);
            afVar.m694a(a).m695b();
        }
    }

    class C0746a extends AsyncTask<Void, Void, JSONObject> {
        af f1325a;
        C0747n f1326b;
        boolean f1327c;
        final /* synthetic */ C0747n f1328d;

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return m1300a((Void[]) objArr);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            m1301a((JSONObject) obj);
        }

        C0746a(C0747n c0747n, af afVar, C0747n c0747n2, boolean z) {
            this.f1328d = c0747n;
            this.f1325a = afVar;
            this.f1326b = c0747n2;
            this.f1327c = z;
        }

        protected JSONObject m1300a(Void... voidArr) {
            if (VERSION.SDK_INT < 14) {
                return null;
            }
            return this.f1328d.m1309a(this.f1326b);
        }

        protected void m1301a(JSONObject jSONObject) {
            if (this.f1327c) {
                new af("Device.update_info", 1, jSONObject).m695b();
            } else {
                this.f1325a.m694a(jSONObject).m695b();
            }
        }
    }

    C0747n() {
    }

    void m1310a(JSONObject jSONObject) {
        this.f1334e = jSONObject;
    }

    JSONObject m1308a() {
        return this.f1334e;
    }

    String m1311b() {
        if (C0594a.m614d()) {
            return Secure.getString(C0594a.m613c().getContentResolver(), "android_id");
        }
        return "";
    }

    String m1312c() {
        return this.f1330a;
    }

    String m1313d() {
        return System.getProperty("os.arch").toLowerCase();
    }

    void m1314e() {
        this.f1331b = false;
        C0594a.m609a("Device.get_info", new C07441(this));
        C0594a.m609a("Device.application_exists", new C07452(this));
    }

    String m1315f() {
        return m1329t() ? "tablet" : "phone";
    }

    boolean m1316g() {
        return this.f1332c;
    }

    String m1317h() {
        if (!C0594a.m614d()) {
            return "";
        }
        String networkOperatorName = ((TelephonyManager) C0594a.m613c().getSystemService("phone")).getNetworkOperatorName();
        if (networkOperatorName.length() == 0) {
            return "unknown";
        }
        return networkOperatorName;
    }

    int m1318i() {
        if (C0594a.m614d()) {
            return ((ActivityManager) C0594a.m613c().getApplicationContext().getSystemService(C1404b.aw)).getMemoryClass();
        }
        return 0;
    }

    String m1319j() {
        if (!C0594a.m614d()) {
            return "";
        }
        String simCountryIso = ((TelephonyManager) C0594a.m613c().getSystemService("phone")).getSimCountryIso();
        return simCountryIso == null ? "" : simCountryIso;
    }

    String m1320k() {
        return TimeZone.getDefault().getID();
    }

    int m1321l() {
        return TimeZone.getDefault().getOffset(15) / 60000;
    }

    int m1322m() {
        TimeZone timeZone = TimeZone.getDefault();
        if (timeZone.inDaylightTime(new Date())) {
            return timeZone.getDSTSavings() / 60000;
        }
        return 0;
    }

    long m1323n() {
        Runtime runtime = Runtime.getRuntime();
        return (runtime.totalMemory() - runtime.freeMemory()) / ((long) 1048576);
    }

    float m1324o() {
        if (C0594a.m614d()) {
            return C0594a.m613c().getResources().getDisplayMetrics().density;
        }
        return 0.0f;
    }

    String m1325p() {
        if (C0594a.m614d()) {
            return bg.m1011a(C0594a.m613c());
        }
        return "";
    }

    int m1326q() {
        if (!C0594a.m614d()) {
            return 0;
        }
        DisplayMetrics displayMetrics = new DisplayMetrics();
        C0594a.m613c().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    int m1327r() {
        if (!C0594a.m614d()) {
            return 0;
        }
        DisplayMetrics displayMetrics = new DisplayMetrics();
        C0594a.m613c().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }

    int m1328s() {
        return VERSION.SDK_INT;
    }

    double m1307a(Context context) {
        if (context == null) {
            return 0.0d;
        }
        Intent registerReceiver = context.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        if (registerReceiver == null) {
            return 0.0d;
        }
        int intExtra = registerReceiver.getIntExtra(FirebaseAnalytics$Param.LEVEL, -1);
        int intExtra2 = registerReceiver.getIntExtra("scale", -1);
        if (intExtra < 0 || intExtra2 < 0) {
            return 0.0d;
        }
        return ((double) intExtra) / ((double) intExtra2);
    }

    boolean m1329t() {
        if (!C0594a.m614d()) {
            return false;
        }
        DisplayMetrics displayMetrics = C0594a.m613c().getResources().getDisplayMetrics();
        float f = ((float) displayMetrics.widthPixels) / displayMetrics.xdpi;
        float f2 = ((float) displayMetrics.heightPixels) / displayMetrics.ydpi;
        if (Math.sqrt((double) ((f2 * f2) + (f * f))) >= 6.0d) {
            return true;
        }
        return false;
    }

    String m1330u() {
        return Locale.getDefault().getLanguage();
    }

    String m1331v() {
        return Locale.getDefault().getCountry();
    }

    String m1332w() {
        return "";
    }

    String m1333x() {
        return Build.MANUFACTURER;
    }

    String m1334y() {
        return Build.MODEL;
    }

    String m1335z() {
        return VERSION.RELEASE;
    }

    JSONArray m1302A() {
        return this.f1339k;
    }

    int m1303B() {
        if (!C0594a.m614d()) {
            return 2;
        }
        switch (C0594a.m613c().getResources().getConfiguration().orientation) {
            case 1:
                return 0;
            case 2:
                return 1;
            default:
                return 2;
        }
    }

    int m1304C() {
        if (!C0594a.m614d()) {
            return 0;
        }
        DisplayMetrics displayMetrics = new DisplayMetrics();
        C0594a.m613c().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.densityDpi;
    }

    String m1305D() {
        return "3.3.2";
    }

    boolean m1306E() {
        if (!C0594a.m614d()) {
            return false;
        }
        int B = m1303B();
        switch (B) {
            case 0:
                if (this.f1335f != 1) {
                    return false;
                }
                new C0595a().m622a("Sending device info update").m624a(aa.f480d);
                this.f1335f = B;
                if (m1328s() < 14) {
                    new C0746a(this, null, this, true).execute(new Void[0]);
                } else {
                    new C0746a(this, null, this, true).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
                }
                return true;
            case 1:
                if (this.f1335f != 0) {
                    return false;
                }
                new C0595a().m622a("Sending device info update").m624a(aa.f480d);
                this.f1335f = B;
                if (m1328s() < 14) {
                    new C0746a(this, null, this, true).execute(new Void[0]);
                } else {
                    new C0746a(this, null, this, true).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
                }
                return true;
            default:
                return false;
        }
    }

    JSONObject m1309a(C0747n c0747n) {
        JSONObject a = C0802y.m1453a();
        C0740l a2 = C0594a.m605a();
        C0802y.m1462a(a, "carrier_name", c0747n.m1317h());
        C0802y.m1462a(a, "data_path", C0594a.m605a().m1285o().m785e());
        C0802y.m1472b(a, "device_api", c0747n.m1328s());
        C0802y.m1462a(a, "device_id", c0747n.m1325p());
        C0802y.m1472b(a, "display_width", c0747n.m1326q());
        C0802y.m1472b(a, "display_height", c0747n.m1327r());
        C0802y.m1472b(a, "screen_width", c0747n.m1326q());
        C0802y.m1472b(a, "screen_height", c0747n.m1327r());
        C0802y.m1472b(a, "display_dpi", c0747n.m1304C());
        C0802y.m1462a(a, "device_type", c0747n.m1315f());
        C0802y.m1462a(a, "locale_language_code", c0747n.m1330u());
        C0802y.m1462a(a, "ln", c0747n.m1330u());
        C0802y.m1462a(a, "locale_country_code", c0747n.m1331v());
        C0802y.m1462a(a, Constants.LOCALE_PROPERTY, c0747n.m1331v());
        C0802y.m1462a(a, "mac_address", c0747n.m1332w());
        C0802y.m1462a(a, "manufacturer", c0747n.m1333x());
        C0802y.m1462a(a, "device_brand", c0747n.m1333x());
        C0802y.m1462a(a, "media_path", C0594a.m605a().m1285o().m784d());
        C0802y.m1462a(a, "temp_storage_path", C0594a.m605a().m1285o().m786f());
        C0802y.m1472b(a, "memory_class", c0747n.m1318i());
        C0802y.m1472b(a, "network_speed", 20);
        C0802y.m1461a(a, "memory_used_mb", c0747n.m1323n());
        C0802y.m1462a(a, "model", c0747n.m1334y());
        C0802y.m1462a(a, "device_model", c0747n.m1334y());
        C0802y.m1462a(a, "sdk_type", "android_native");
        C0802y.m1462a(a, "sdk_version", c0747n.m1305D());
        C0802y.m1462a(a, "network_type", a2.f1297e.m716c());
        C0802y.m1462a(a, "os_version", c0747n.m1335z());
        C0802y.m1462a(a, "os_name", "android");
        C0802y.m1462a(a, "platform", "android");
        C0802y.m1462a(a, "arch", c0747n.m1313d());
        C0802y.m1462a(a, "user_id", C0802y.m1468b(a2.m1271d().f394d, "user_id"));
        C0802y.m1462a(a, "app_id", a2.m1271d().f391a);
        C0802y.m1465a(a, "immersion", this.f1333d);
        C0802y.m1462a(a, "app_bundle_name", az.m893d());
        C0802y.m1462a(a, "app_bundle_version", az.m888b());
        C0802y.m1460a(a, "battery_level", c0747n.m1307a(C0594a.m613c()));
        C0802y.m1462a(a, "cell_service_country_code", c0747n.m1319j());
        C0802y.m1462a(a, "timezone_ietf", c0747n.m1320k());
        C0802y.m1472b(a, "timezone_gmt_m", c0747n.m1321l());
        C0802y.m1472b(a, "timezone_dst_m", c0747n.m1322m());
        C0802y.m1464a(a, "launch_metadata", c0747n.m1308a());
        C0802y.m1462a(a, "controller_version", a2.m1263b());
        this.f1335f = c0747n.m1303B();
        C0802y.m1472b(a, "current_orientation", this.f1335f);
        JSONArray b = C0802y.m1469b();
        if (az.m881a("com.android.vending")) {
            b.put("google");
        }
        if (az.m881a("com.amazon.venezia")) {
            b.put("amazon");
        }
        C0802y.m1463a(a, "available_stores", b);
        this.f1339k = az.m890b(C0594a.m613c());
        C0802y.m1463a(a, "permissions", this.f1339k);
        int i = 40;
        while (!c0747n.f1331b && i > 0) {
            try {
                Thread.sleep(50);
                i--;
            } catch (Exception e) {
            }
        }
        C0802y.m1462a(a, "advertiser_id", c0747n.m1312c());
        C0802y.m1465a(a, "limit_tracking", c0747n.m1316g());
        if (c0747n.m1312c() == null || c0747n.m1312c().equals("")) {
            C0802y.m1462a(a, "android_id_sha1", az.m892c(c0747n.m1311b()));
        }
        return a;
    }
}
