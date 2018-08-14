package com.inmobi.commons.core.utilities.info;

import android.content.Context;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import com.inmobi.commons.core.utilities.C3156e;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.commons.p112a.C3105a;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;

/* compiled from: DeviceInfo */
public class C3160b {
    private static String m10436c() {
        return Locale.getDefault().toString();
    }

    private static String m10437d() {
        Context b = C3105a.m10078b();
        if (b == null) {
            return "";
        }
        String str = "";
        if (C3156e.m10410a(b, "root", "android.permission.ACCESS_NETWORK_STATE")) {
            ConnectivityManager connectivityManager = (ConnectivityManager) b.getSystemService("connectivity");
            if (connectivityManager != null) {
                NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                if (activeNetworkInfo != null) {
                    int type = activeNetworkInfo.getType();
                    int subtype = activeNetworkInfo.getSubtype();
                    if (type == 0) {
                        return type + "|" + subtype;
                    }
                    if (type == 1) {
                        return SchemaSymbols.ATTVAL_TRUE_1;
                    }
                    return Integer.toString(type);
                }
            }
        }
        return str;
    }

    public static Map<String, String> m10433a() {
        Map<String, String> hashMap = new HashMap();
        try {
            hashMap.put("d-nettype-raw", C3160b.m10437d());
            hashMap.put("d-localization", C3160b.m10436c());
            hashMap.put("d-media-volume", String.valueOf(C3160b.m10435b(C3105a.m10078b())));
        } catch (Exception e) {
            Logger.m10359a(InternalLogLevel.INTERNAL, C3160b.class.getSimpleName(), "SDK encountered unexpected error in getting device info; " + e.getMessage());
        }
        return hashMap;
    }

    public static int m10434b() {
        String d = C3160b.m10437d();
        if (d.startsWith(SchemaSymbols.ATTVAL_FALSE_0)) {
            return 0;
        }
        if (d.startsWith(SchemaSymbols.ATTVAL_TRUE_1)) {
            return 1;
        }
        return 2;
    }

    public static int m10432a(@NonNull Context context) {
        return ((AudioManager) context.getSystemService("audio")).getStreamVolume(3);
    }

    private static int m10435b(Context context) {
        if (context == null) {
            return 0;
        }
        AudioManager audioManager = (AudioManager) context.getSystemService("audio");
        return (audioManager.getStreamVolume(3) * 100) / audioManager.getStreamMaxVolume(3);
    }
}
