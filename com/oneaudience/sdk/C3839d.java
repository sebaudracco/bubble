package com.oneaudience.sdk;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.telephony.TelephonyManager;
import com.oneaudience.sdk.p135c.p137b.C3826c;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import mf.org.apache.xerces.impl.Constants;

abstract class C3839d {
    static final Uri f9192a = Uri.parse("https://api.oneaudience.com/");
    public static String f9193b = "/eula/accepted";
    public static String f9194c = "/eula/decline";

    C3839d() {
    }

    private static void m12272a(Context context, Map<String, Object> map) {
        if (C3843e.m12285a(context, "android.permission.READ_PHONE_STATE")) {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            CharSequence line1Number = telephonyManager.getLine1Number();
            CharSequence networkCountryIso = telephonyManager.getPhoneType() != 2 ? telephonyManager.getNetworkCountryIso() : null;
            if (C3826c.m12234b(line1Number)) {
                map.put("phone_number", line1Number);
            }
            if (C3826c.m12234b(networkCountryIso)) {
                map.put("mcc", networkCountryIso);
            }
        }
    }

    private static void m12273a(Map<String, Object> map) {
        map.put("platformVer", Integer.valueOf(VERSION.SDK_INT));
        map.put("deviceModel", Build.MODEL);
    }

    protected Map<String, Object> m12274b(Context context, SharedPreferences sharedPreferences) {
        Context applicationContext = context.getApplicationContext();
        Map<String, Object> hashMap = new HashMap();
        hashMap.put("api_version", Integer.valueOf(OneAudience.VERSION_CODE));
        hashMap.put("package_name", applicationContext.getPackageName());
        hashMap.put(Constants.LOCALE_PROPERTY, Locale.getDefault());
        hashMap.put("platform", "android");
        hashMap.put("origin", "sdk");
        Object c = C3843e.m12289c(context);
        if (C3826c.m12234b(c)) {
            hashMap.put("installerPackageName", c);
            hashMap.put("storeInstall", Boolean.valueOf(c.equalsIgnoreCase("com.android.vending")));
        }
        CharSequence string = sharedPreferences.getString("client_id", "");
        if (C3826c.m12234b(string)) {
            hashMap.put("client_id", string);
        }
        int a = C3843e.m12280a(context);
        if (a != -1) {
            hashMap.put("appVer", Integer.valueOf(a));
        }
        C3839d.m12273a(hashMap);
        C3839d.m12272a(context, hashMap);
        return hashMap;
    }
}
