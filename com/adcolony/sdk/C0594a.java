package com.adcolony.sdk;

import android.app.Activity;
import com.adcolony.sdk.aa.C0595a;
import java.lang.ref.WeakReference;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class C0594a {
    static boolean f472a;
    static boolean f473b;
    private static WeakReference<Activity> f474c;
    private static C0740l f475d;

    C0594a() {
    }

    static void m607a(final Activity activity, AdColonyAppOptions adColonyAppOptions, boolean z) {
        C0594a.m606a(activity);
        f473b = true;
        if (f475d == null) {
            f475d = new C0740l();
            f475d.m1252a(adColonyAppOptions, z);
        } else {
            f475d.m1251a(adColonyAppOptions);
        }
        az.f755b.execute(new Runnable() {
            public void run() {
                C0594a.f475d.m1261a(activity, null);
            }
        });
        new C0595a().m622a("Configuring AdColony").m624a(aa.f479c);
        f475d.m1267b(false);
        f475d.m1282l().m764d(true);
        f475d.m1282l().m765e(true);
        f475d.m1282l().m767f(false);
        f475d.f1298g = true;
        f475d.m1282l().m758a(false);
    }

    static C0740l m605a() {
        if (!C0594a.m612b()) {
            if (!C0594a.m614d()) {
                return new C0740l();
            }
            f475d = new C0740l();
            JSONObject c = C0802y.m1475c(C0594a.m613c().getFilesDir().getAbsolutePath() + "/adc3/AppInfo");
            JSONArray g = C0802y.m1481g(c, "zoneIds");
            f475d.m1252a(new AdColonyAppOptions().m549a(C0802y.m1468b(c, "appId")).m550a(C0802y.m1466a(g)), false);
        }
        return f475d;
    }

    static boolean m612b() {
        return f475d != null;
    }

    static void m606a(Activity activity) {
        if (activity == null) {
            f474c.clear();
        } else {
            f474c = new WeakReference(activity);
        }
    }

    static Activity m613c() {
        return (Activity) f474c.get();
    }

    static boolean m614d() {
        return (f474c == null || f474c.get() == null) ? false : true;
    }

    static boolean m615e() {
        return f472a;
    }

    static void m609a(String str, ah ahVar) {
        C0594a.m605a().m1287q().m703a(str, ahVar);
    }

    static ah m604a(String str, ah ahVar, boolean z) {
        C0594a.m605a().m1287q().m703a(str, ahVar);
        return ahVar;
    }

    static void m611b(String str, ah ahVar) {
        C0594a.m605a().m1287q().m707b(str, ahVar);
    }

    static void m616f() {
        C0594a.m605a().m1287q().m706b();
    }

    static void m610a(String str, JSONObject jSONObject) {
        if (jSONObject == null) {
            jSONObject = C0802y.m1453a();
        }
        C0802y.m1462a(jSONObject, "m_type", str);
        C0594a.m605a().m1287q().m705a(jSONObject);
    }

    static void m608a(String str) {
        try {
            af afVar = new af("CustomMessage.send", 0);
            afVar.m698c().put("message", str);
            afVar.m695b();
        } catch (JSONException e) {
            new C0595a().m622a("JSON error from ADC.java's send_custom_message(): ").m622a(e.toString()).m624a(aa.f484h);
        }
    }
}
