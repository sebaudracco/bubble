package com.yandex.metrica.impl;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import com.yandex.metrica.impl.C4364d.C4370a;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONObject;

class bm implements C4364d {
    private static volatile bm f11818b;
    private static final Object f11819c = new Object();
    private final WifiManager f11820a;
    private C4370a<JSONArray> f11821d = new C4370a();
    private C4370a<List<C4363a>> f11822e = new C4370a();

    public static final class C4363a {
        public final String f11816a;
        public final String f11817b;

        public C4363a(String str, String str2) {
            this.f11816a = str;
            this.f11817b = str2;
        }
    }

    private bm(Context context) {
        this.f11820a = (WifiManager) context.getSystemService("wifi");
    }

    static bm m14994a(Context context) {
        if (f11818b == null) {
            synchronized (f11819c) {
                if (f11818b == null) {
                    f11818b = new bm(context.getApplicationContext());
                }
            }
        }
        return f11818b;
    }

    synchronized JSONArray m14998a() {
        JSONArray jSONArray;
        if (m14997d()) {
            if (this.f11821d.m15045b() || this.f11821d.m15046c()) {
                this.f11821d.m15044a(m14996c());
            }
            jSONArray = (JSONArray) this.f11821d.m15043a();
        } else {
            jSONArray = new JSONArray();
        }
        return jSONArray;
    }

    private JSONArray m14996c() {
        try {
            Object bssid;
            List<ScanResult> scanResults = this.f11820a.getScanResults();
            JSONArray jSONArray = new JSONArray();
            WifiInfo connectionInfo = this.f11820a.getConnectionInfo();
            if (connectionInfo != null) {
                bssid = connectionInfo.getBSSID();
            } else {
                bssid = null;
            }
            for (ScanResult scanResult : scanResults) {
                if (scanResult != null) {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("mac", scanResult.BSSID.toUpperCase(Locale.US).replace(":", ""));
                    jSONObject.put("signal_strength", scanResult.level);
                    jSONObject.put("ssid", scanResult.SSID);
                    jSONObject.put("is_connected", scanResult.BSSID.equals(bssid));
                    jSONArray.put(jSONObject);
                }
            }
            return jSONArray;
        } catch (Exception e) {
            return new JSONArray();
        }
    }

    private boolean m14997d() {
        try {
            return this.f11820a.isWifiEnabled();
        } catch (Exception e) {
            return false;
        }
    }

    public List<C4363a> m15000b() {
        if (this.f11822e.m15045b() || this.f11822e.m15046c()) {
            List arrayList = new ArrayList();
            m14995a(arrayList);
            this.f11822e.m15044a(arrayList);
        }
        return (List) this.f11822e.m15043a();
    }

    private static void m14995a(List<C4363a> list) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            Iterator it = Collections.list(NetworkInterface.getNetworkInterfaces()).iterator();
            while (it.hasNext()) {
                NetworkInterface networkInterface = (NetworkInterface) it.next();
                byte[] hardwareAddress = networkInterface.getHardwareAddress();
                if (hardwareAddress != null) {
                    for (byte b : hardwareAddress) {
                        stringBuilder.append(String.format(Locale.US, "%02X:", new Object[]{Byte.valueOf(b)}));
                    }
                    if (stringBuilder.length() > 0) {
                        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
                        list.add(new C4363a(networkInterface.getName(), stringBuilder.toString()));
                        stringBuilder.setLength(0);
                    }
                }
            }
        } catch (Throwable th) {
        }
    }

    public String m14999b(Context context) {
        try {
            if (al.m14592a(context, "android.permission.ACCESS_WIFI_STATE")) {
                WifiConfiguration wifiConfiguration = (WifiConfiguration) this.f11820a.getClass().getMethod("getWifiApConfiguration", new Class[0]).invoke(this.f11820a, new Object[0]);
                return wifiConfiguration != null ? wifiConfiguration.SSID : null;
            }
        } catch (Throwable th) {
        }
        return null;
    }

    public int m15001c(Context context) {
        try {
            if (al.m14592a(context, "android.permission.ACCESS_WIFI_STATE")) {
                int intValue = ((Integer) this.f11820a.getClass().getMethod("getWifiApState", new Class[0]).invoke(this.f11820a, new Object[0])).intValue();
                if (intValue > 10) {
                    return intValue - 10;
                }
                return intValue;
            }
        } catch (Throwable th) {
        }
        return -1;
    }
}
