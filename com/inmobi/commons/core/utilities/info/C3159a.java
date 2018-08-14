package com.inmobi.commons.core.utilities.info;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.commons.p112a.C3105a;
import java.util.HashMap;
import java.util.Map;

/* compiled from: AppInfo */
public class C3159a {
    private static final String f7790a = C3159a.class.getSimpleName();
    private static C3159a f7791b;
    private static Object f7792c = new Object();
    private String f7793d;
    private String f7794e;
    private String f7795f;
    private String f7796g;
    private Map<String, String> f7797h = new HashMap();

    private C3159a() {
        m10428a(C3105a.m10078b());
        m10429d();
    }

    private void m10428a(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            ApplicationInfo applicationInfo = packageManager.getApplicationInfo(context.getPackageName(), 128);
            if (applicationInfo != null) {
                this.f7793d = applicationInfo.packageName;
                this.f7794e = applicationInfo.loadLabel(packageManager).toString();
                this.f7796g = packageManager.getInstallerPackageName(this.f7793d);
            }
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 128);
            String str = null;
            if (packageInfo != null) {
                str = packageInfo.versionName;
                if (str == null || str.equals("")) {
                    str = packageInfo.versionCode + "";
                }
            }
            if (str != null && !str.equals("")) {
                this.f7795f = str;
            }
        } catch (Throwable e) {
            Logger.m10360a(InternalLogLevel.INTERNAL, f7790a, "Failed to fetch app info completely", e);
        }
    }

    private void m10429d() {
        this.f7797h.put("u-appbid", this.f7793d);
        this.f7797h.put("u-appdnm", this.f7794e);
        this.f7797h.put("u-appver", this.f7795f);
    }

    public static C3159a m10427a() {
        C3159a c3159a = f7791b;
        if (c3159a == null) {
            synchronized (f7792c) {
                c3159a = f7791b;
                if (c3159a == null) {
                    f7791b = new C3159a();
                    c3159a = f7791b;
                }
            }
        }
        return c3159a;
    }

    public String m10430b() {
        return this.f7796g;
    }

    public Map<String, String> m10431c() {
        return this.f7797h;
    }
}
