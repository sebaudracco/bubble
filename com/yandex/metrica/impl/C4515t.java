package com.yandex.metrica.impl;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.ResolveInfo;
import android.os.SystemClock;
import android.util.Base64;
import com.yandex.metrica.impl.utils.C4518b;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class C4515t {
    JSONObject f12570a;

    public C4515t(String str) {
        try {
            this.f12570a = new JSONObject(str);
        } catch (Exception e) {
            this.f12570a = new JSONObject();
        }
    }

    public C4515t m16228a() {
        try {
            m16232c();
            m16231b();
        } catch (Exception e) {
        }
        return this;
    }

    C4515t m16231b() throws JSONException {
        ((JSONObject) C4515t.m16227a(this.f12570a, "dfid", new JSONObject())).put("boot_time", (System.currentTimeMillis() - SystemClock.elapsedRealtime()) / 1000);
        return this;
    }

    C4515t m16230a(Context context, boolean z) throws JSONException, UnsupportedEncodingException {
        JSONObject jSONObject = (JSONObject) C4515t.m16227a((JSONObject) C4515t.m16227a(this.f12570a, "dfid", new JSONObject()), "au", new JSONObject());
        JSONArray jSONArray = (JSONArray) C4515t.m16227a(jSONObject, "aun", new JSONArray());
        JSONArray jSONArray2 = (JSONArray) C4515t.m16227a(jSONObject, "ausf", new JSONArray());
        JSONArray jSONArray3 = (JSONArray) C4515t.m16227a(jSONObject, "audf", new JSONArray());
        JSONArray jSONArray4 = (JSONArray) C4515t.m16227a(jSONObject, "aulu", new JSONArray());
        JSONArray jSONArray5 = new JSONArray();
        if (z) {
            C4515t.m16227a(jSONObject, "aufi", jSONArray5);
        }
        HashSet hashSet = new HashSet();
        for (ResolveInfo resolveInfo : bk.m14977a(context, new String(Base64.decode("YW5kcm9pZC5pbnRlbnQuYWN0aW9uLk1BSU4=", 0), "UTF-8"), new String(Base64.decode("YW5kcm9pZC5pbnRlbnQuY2F0ZWdvcnkuTEFVTkNIRVI=", 0), "UTF-8"))) {
            ApplicationInfo applicationInfo = resolveInfo.activityInfo.applicationInfo;
            if (hashSet.add(applicationInfo.packageName)) {
                jSONArray.put(applicationInfo.packageName);
                boolean z2 = (applicationInfo.flags & 1) == 1;
                jSONArray2.put(z2);
                jSONArray4.put(new File(applicationInfo.sourceDir).lastModified());
                jSONArray3.put(!applicationInfo.enabled);
                if (z) {
                    if (z2) {
                        jSONArray5.put(0);
                    } else if (bk.m14993c(context, applicationInfo.packageName) == null) {
                        jSONArray5.put(0);
                    } else {
                        jSONArray5.put(bk.m14993c(context, applicationInfo.packageName).firstInstallTime / 1000);
                    }
                }
            }
        }
        return this;
    }

    C4515t m16229a(Context context) throws JSONException {
        JSONObject jSONObject = (JSONObject) C4515t.m16227a((JSONObject) C4515t.m16227a(this.f12570a, "dfid", new JSONObject()), "apps", new JSONObject());
        JSONArray jSONArray = (JSONArray) C4515t.m16227a(jSONObject, "names", new JSONArray());
        JSONArray jSONArray2 = (JSONArray) C4515t.m16227a(jSONObject, "system_flags", new JSONArray());
        JSONArray jSONArray3 = (JSONArray) C4515t.m16227a(jSONObject, "disabled_flags", new JSONArray());
        JSONArray jSONArray4 = (JSONArray) C4515t.m16227a(jSONObject, "first_install_time", new JSONArray());
        JSONArray jSONArray5 = (JSONArray) C4515t.m16227a(jSONObject, "last_update_time", new JSONArray());
        jSONObject.put("version", 0);
        for (PackageInfo packageInfo : context.getPackageManager().getInstalledPackages(0)) {
            boolean z;
            jSONArray.put(packageInfo.packageName);
            jSONArray2.put((packageInfo.applicationInfo.flags & 1) == 1);
            if (packageInfo.applicationInfo.enabled) {
                z = false;
            } else {
                z = true;
            }
            jSONArray3.put(z);
            jSONArray4.put(packageInfo.firstInstallTime / 1000);
            jSONArray5.put(packageInfo.lastUpdateTime / 1000);
        }
        return this;
    }

    C4515t m16232c() throws JSONException {
        JSONObject jSONObject = (JSONObject) C4515t.m16227a(this.f12570a, "dfid", new JSONObject());
        long a = am.m14596a(true);
        long a2 = am.m14596a(false);
        long c = am.m14600c(true);
        long c2 = am.m14600c(false);
        jSONObject.put("tds", a + a2);
        jSONObject.put("fds", c + c2);
        return this;
    }

    static <T> T m16227a(JSONObject jSONObject, String str, T t) throws JSONException {
        if (!jSONObject.has(str)) {
            jSONObject.put(str, t);
        }
        return jSONObject.get(str);
    }

    public String toString() {
        return this.f12570a.toString();
    }

    public String m16233d() {
        return Base64.encodeToString(new C4518b().m16236a(bi.m14964c(this.f12570a.toString())), 0);
    }
}
