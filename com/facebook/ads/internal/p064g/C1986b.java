package com.facebook.ads.internal.p064g;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Build.VERSION;
import android.telephony.TelephonyManager;
import com.facebook.ads.internal.p056q.p057a.C2113e;

public class C1986b {
    public static final String f4639a = VERSION.RELEASE;
    private final Context f4640b;

    public C1986b(Context context) {
        this.f4640b = context.getApplicationContext();
    }

    public String m6270a() {
        return (Build.MANUFACTURER == null || Build.MANUFACTURER.length() <= 0) ? "" : Build.MANUFACTURER;
    }

    public String m6271b() {
        return (Build.MODEL == null || Build.MODEL.length() <= 0) ? "" : Build.MODEL;
    }

    public String m6272c() {
        TelephonyManager telephonyManager = (TelephonyManager) this.f4640b.getSystemService("phone");
        if (telephonyManager != null) {
            String networkOperatorName = telephonyManager.getNetworkOperatorName();
            if (networkOperatorName != null && networkOperatorName.length() > 0) {
                return networkOperatorName;
            }
        }
        return "";
    }

    public String m6273d() {
        try {
            CharSequence applicationLabel = this.f4640b.getPackageManager().getApplicationLabel(this.f4640b.getPackageManager().getApplicationInfo(m6275f(), 0));
            if (applicationLabel != null && applicationLabel.length() > 0) {
                return applicationLabel.toString();
            }
        } catch (NameNotFoundException e) {
        }
        return "";
    }

    public String m6274e() {
        try {
            String f = m6275f();
            if (f != null && f.length() >= 0) {
                f = this.f4640b.getPackageManager().getInstallerPackageName(f);
                if (f != null && f.length() > 0) {
                    return f;
                }
            }
        } catch (Exception e) {
        }
        return "";
    }

    public String m6275f() {
        PendingIntent activity = PendingIntent.getActivity(this.f4640b, 0, new Intent(), 0);
        return VERSION.SDK_INT >= 17 ? activity.getCreatorPackage() : activity.getTargetPackage();
    }

    public String m6276g() {
        try {
            return this.f4640b.getPackageManager().getPackageInfo(m6275f(), 0).versionName;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
            return "";
        }
    }

    public int m6277h() {
        int i = 0;
        try {
            return this.f4640b.getPackageManager().getPackageInfo(m6275f(), 0).versionCode;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
            return i;
        }
    }

    public boolean m6278i() {
        return this.f4640b.checkCallingOrSelfPermission("android.permission.BIND_ACCESSIBILITY_SERVICE") == 0;
    }

    public int m6279j() {
        return C2113e.m6777b(this.f4640b);
    }
}
