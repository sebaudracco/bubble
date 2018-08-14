package com.oneaudience.sdk.p133a;

import android.content.Context;
import android.os.Build;
import android.os.SystemClock;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import com.moat.analytics.mobile.mpub.BuildConfig;
import com.oneaudience.sdk.C3843e;
import com.oneaudience.sdk.C3843e.C3842a;
import com.oneaudience.sdk.model.BasicData;
import com.oneaudience.sdk.p135c.C3833d;
import com.oneaudience.sdk.p135c.p137b.C3826c;

public class C3785b extends C3784a {
    private static final String[] f9086f = new String[]{"android.permission.READ_PHONE_STATE"};

    protected C3785b(Context context, String str, boolean z, boolean z2, long j) {
        super(context, str, z, z2, j, BuildConfig.FLAVOR, "disableEmailsCollector", true, true);
    }

    public String mo6804a() {
        String str = "";
        int i = 3;
        while (str.isEmpty() && i > 0) {
            str = C3843e.m12290d(this.c);
            if (str.isEmpty()) {
                i--;
                SystemClock.sleep(3000);
            }
        }
        String string = Secure.getString(this.c.getContentResolver(), "android_id");
        String str2 = "";
        if (C3843e.m12285a(this.c, "android.permission.ACCESS_NETWORK_STATE")) {
            str2 = C3842a.m12278a("wlan0");
            if (C3826c.m12233a(str2)) {
                str2 = C3842a.m12278a("eth0");
            }
        }
        TelephonyManager telephonyManager = (TelephonyManager) this.c.getSystemService("phone");
        String str3 = "";
        if (C3843e.m12285a(this.c, "android.permission.READ_PHONE_STATE")) {
            str3 = telephonyManager.getDeviceId();
        } else {
            C3833d.m12246a(a, "Don't have permissions to collect imei");
        }
        String str4 = "";
        String str5 = "";
        String[] a = C3842a.m12279a();
        if (a != null) {
            str4 = a[0];
            str5 = a[1];
        }
        return m12083a((Object) new BasicData(str, string, str2, str3, Build.MANUFACTURER, str4, str5));
    }

    public String[] mo6805b() {
        return f9086f;
    }
}
