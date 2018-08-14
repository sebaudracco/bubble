package com.inmobi.signals.p120b;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import com.inmobi.commons.core.p115d.C3132b;
import com.inmobi.commons.core.p115d.C3135c;
import com.inmobi.commons.core.utilities.C3156e;
import com.inmobi.commons.p112a.C3105a;
import com.inmobi.signals.C3277o;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: WifiInfoUtil */
public class C3255b {
    public static C3254a m10891a() {
        if (!C3255b.m10902e() || !C3277o.m10989a().m10994e().m11072l()) {
            return null;
        }
        int j = C3277o.m10989a().m10994e().m11070j();
        return C3255b.m10893a(C3255b.m10895a(j), C3255b.m10899b(j));
    }

    public static Map<String, String> m10898b() {
        C3254a a = C3255b.m10891a();
        Map hashMap = new HashMap();
        if (a != null) {
            hashMap.put("c-ap-bssid", String.valueOf(a.m10882a()));
        }
        return hashMap;
    }

    private static boolean m10895a(int i) {
        return !C3255b.m10896a(i, 2);
    }

    private static boolean m10899b(int i) {
        return C3255b.m10896a(i, 1);
    }

    private static boolean m10902e() {
        return C3105a.m10076a() && C3156e.m10410a(C3105a.m10078b(), "signals", "android.permission.ACCESS_WIFI_STATE");
    }

    private static C3254a m10893a(boolean z, boolean z2) {
        C3254a c3254a;
        Throwable e;
        String str = null;
        Context b = C3105a.m10078b();
        if (b == null) {
            return null;
        }
        try {
            WifiInfo connectionInfo = ((WifiManager) b.getSystemService("wifi")).getConnectionInfo();
            if (connectionInfo != null) {
                String bssid = connectionInfo.getBSSID();
                String ssid = connectionInfo.getSSID();
                if (!(bssid == null || C3255b.m10897a(z, ssid))) {
                    c3254a = new C3254a();
                    try {
                        c3254a.m10884a(C3255b.m10889a(bssid));
                        if (ssid != null && ssid.startsWith("\"") && ssid.endsWith("\"")) {
                            ssid = ssid.substring(1, ssid.length() - 1);
                        }
                        if (!z2) {
                            str = ssid;
                        }
                        c3254a.m10885a(str);
                        c3254a.m10883a(connectionInfo.getRssi());
                        c3254a.m10887b(connectionInfo.getIpAddress());
                    } catch (Exception e2) {
                        e = e2;
                        C3135c.m10255a().m10279a(new C3132b(e));
                        return c3254a;
                    }
                    return c3254a;
                }
            }
            c3254a = null;
        } catch (Throwable e3) {
            Throwable th = e3;
            c3254a = null;
            e = th;
            C3135c.m10255a().m10279a(new C3132b(e));
            return c3254a;
        }
        return c3254a;
    }

    private static boolean m10897a(boolean z, String str) {
        return z && str != null && str.endsWith("_nomap");
    }

    private static long m10889a(String str) {
        String str2 = ":";
        str2 = "\\:";
        String[] split = str.split("\\:");
        byte[] bArr = new byte[6];
        int i = 0;
        while (i < 6) {
            try {
                bArr[i] = (byte) Integer.parseInt(split[i], 16);
                i++;
            } catch (NumberFormatException e) {
                return 0;
            }
        }
        return C3255b.m10890a(bArr);
    }

    private static long m10890a(byte[] bArr) {
        if (bArr == null || bArr.length != 6) {
            return 0;
        }
        return ((((C3255b.m10888a(bArr[5]) | (C3255b.m10888a(bArr[4]) << 8)) | (C3255b.m10888a(bArr[3]) << 16)) | (C3255b.m10888a(bArr[2]) << 24)) | (C3255b.m10888a(bArr[1]) << 32)) | (C3255b.m10888a(bArr[0]) << 40);
    }

    private static long m10888a(byte b) {
        return ((long) b) & 255;
    }

    private static boolean m10896a(int i, int i2) {
        return (i & i2) == i2;
    }

    public static boolean m10900c() {
        if (!C3105a.m10076a()) {
            return false;
        }
        for (String a : new String[]{"android.permission.ACCESS_WIFI_STATE", "android.permission.CHANGE_WIFI_STATE", "android.permission.ACCESS_COARSE_LOCATION"}) {
            if (!C3156e.m10410a(C3105a.m10078b(), "signals", a)) {
                return false;
            }
        }
        return true;
    }

    public static List<C3254a> m10894a(List<ScanResult> list) {
        int j = C3277o.m10989a().m10994e().m11070j();
        boolean a = C3255b.m10895a(j);
        boolean b = C3255b.m10899b(j);
        List arrayList = new ArrayList();
        if (list != null) {
            for (ScanResult scanResult : list) {
                if (!C3255b.m10897a(a, scanResult.SSID)) {
                    arrayList.add(C3255b.m10892a(scanResult, b));
                }
            }
        }
        return arrayList;
    }

    private static C3254a m10892a(ScanResult scanResult, boolean z) {
        String str = null;
        if (scanResult == null) {
            return null;
        }
        C3254a c3254a = new C3254a();
        c3254a.m10884a(C3255b.m10889a(scanResult.BSSID));
        if (!z) {
            str = scanResult.SSID;
        }
        c3254a.m10885a(str);
        c3254a.m10883a(scanResult.level);
        return c3254a;
    }

    public static Map<String, String> m10901d() {
        ArrayList arrayList = (ArrayList) C3259c.m10905a();
        Map hashMap = new HashMap();
        if (arrayList != null && arrayList.size() > 0) {
            hashMap.put("v-ap-bssid", String.valueOf(((C3254a) arrayList.get(arrayList.size() - 1)).m10882a()));
        }
        return hashMap;
    }
}
