package com.appnext.base.operations.imp;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Location;
import android.location.LocationManager;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import com.appnext.base.C1061b;
import com.appnext.base.operations.C1063a;
import com.appnext.base.p019a.p021b.C1020b;
import com.appnext.base.p019a.p021b.C1021c;
import com.appnext.base.p023b.C1042c;
import com.appnext.base.p023b.C1042c.C1041a;
import com.appnext.base.p023b.C1043d;
import com.appnext.base.p023b.C1045f;
import com.appnext.base.p023b.C1046g;
import com.appnext.base.p023b.C1047h;
import com.appnext.base.p023b.C1048i;
import com.appnext.base.p023b.C1055j;
import com.appnext.base.p023b.C1055j.C1054c;
import com.appnext.base.p023b.C1057k;
import com.appnext.base.p023b.C1058l;
import com.appnext.base.receivers.imp.wfcn;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;
import org.json.JSONArray;
import org.json.JSONObject;

public class wpul extends C1063a implements C1054c {
    private static final String hD = "level";
    private static final String hE = "level_local";
    private static final String hF = "level_local_con";
    private static final String hG = "level_local_ext";
    private static final String hH = "ext_range";
    private static final long hI = 600000;
    private static final String hl = wpul.class.getSimpleName();
    private static WifiManager hv;
    private static final String hx = (hl + "LocationCurrentLocation");
    private static final String hz = (hl + "DB_Semaphore");
    private final String hA = (hl + "_last_scan_results");
    private final String hB = (hl + "_last_sent_scan_results");
    private final String hC = (hl + "_is_in_place");
    private List<ScanResult> hJ;
    private boolean hK = false;
    private C1055j hd = null;
    private String hh = null;
    private WifiScanAvailable hw;
    private boolean hy = false;

    class C10762 implements Runnable {
        final /* synthetic */ wpul hL;

        C10762(wpul com_appnext_base_operations_imp_wpul) {
            this.hL = com_appnext_base_operations_imp_wpul;
        }

        public void run() {
            try {
                if (this.hL.bS()) {
                    this.hL.m2240c(null);
                } else {
                    this.hL.m2236b(null);
                }
            } catch (Throwable th) {
                C1057k.m2176e(wpul.hz, String.valueOf(false), C1041a.Boolean);
            }
        }
    }

    private class LocationModel {
        final /* synthetic */ wpul hL;
        private double hM;
        private double hN;

        public LocationModel(wpul com_appnext_base_operations_imp_wpul, double d, double d2) {
            this.hL = com_appnext_base_operations_imp_wpul;
            this.hM = d;
            this.hN = d2;
        }

        public double bU() {
            return this.hM;
        }

        public double bV() {
            return this.hN;
        }
    }

    private class WifiScanAvailable extends BroadcastReceiver {
        final /* synthetic */ wpul hL;

        class C10771 implements Runnable {
            final /* synthetic */ WifiScanAvailable hO;

            C10771(WifiScanAvailable wifiScanAvailable) {
                this.hO = wifiScanAvailable;
            }

            public void run() {
                try {
                    this.hO.hL.bQ();
                } catch (Throwable th) {
                    C1057k.m2176e(wpul.hz, String.valueOf(false), C1041a.Boolean);
                    C1061b.m2191a(th);
                }
            }
        }

        private WifiScanAvailable(wpul com_appnext_base_operations_imp_wpul) {
            this.hL = com_appnext_base_operations_imp_wpul;
        }

        public void onReceive(Context context, Intent intent) {
            try {
                C1043d.getContext().unregisterReceiver(this.hL.hw);
                if (!this.hL.hK) {
                    this.hL.hK = true;
                    C1046g.cw().m2141b(new C10771(this));
                }
            } catch (Throwable th) {
                C1061b.m2191a(th);
            }
        }
    }

    public wpul(C1021c c1021c, Bundle bundle) {
        super(c1021c, bundle);
    }

    public boolean hasPermission() {
        if ((VERSION.SDK_INT != 23 || C1045f.m2133g(C1043d.getContext(), "android.permission.ACCESS_FINE_LOCATION")) && C1045f.m2133g(C1043d.getContext().getApplicationContext(), "android.permission.ACCESS_WIFI_STATE") && C1045f.m2133g(C1043d.getContext().getApplicationContext(), "android.permission.CHANGE_WIFI_STATE")) {
            return true;
        }
        return false;
    }

    @SuppressLint({"all"})
    public void bB() {
        try {
            C1058l.m2184k(hl, "strart operation");
            if (hasPermission()) {
                Object a = C1057k.m2163a(hz, C1041a.Boolean);
                if (a != null && (a instanceof Boolean) && ((Boolean) a).booleanValue()) {
                    bC();
                    return;
                }
                C1057k.m2176e(hz, String.valueOf(true), C1041a.Boolean);
                hv = (WifiManager) C1043d.getContext().getApplicationContext().getSystemService("wifi");
                if (hv.isWifiEnabled()) {
                    this.hw = new WifiScanAvailable();
                    C1043d.getContext().registerReceiver(this.hw, new IntentFilter("android.net.wifi.SCAN_RESULTS"));
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss", Locale.US);
                    Date date = new Date();
                    hv.startScan();
                    return;
                }
                bC();
            }
        } catch (Throwable th) {
            C1061b.m2191a(th);
        }
    }

    private void m2236b(Location location) {
        try {
            if (bS()) {
                bC();
                return;
            }
            m2244d(true);
            m2228a(location, this.hJ, false, false);
            if (location != null) {
                m2227a(location, hx);
            }
            m2241c(m2246f(this.hJ));
            bC();
        } catch (Throwable th) {
            C1061b.m2191a(th);
            bC();
        }
    }

    private void m2240c(Location location) {
        if (location != null) {
            try {
                if (!m2230a(new LocationModel(this, location.getLatitude(), location.getLongitude()), an(hx))) {
                    Set<String> am = am(this.hB);
                    Set f = m2246f(this.hJ);
                    m2228a(location, this.hJ, false, true);
                    if (am != null) {
                        for (String add : am) {
                            f.add(add);
                        }
                        m2241c(f);
                    }
                    bC();
                    return;
                }
            } catch (Throwable th) {
                C1061b.m2191a(th);
                bC();
                return;
            }
        }
        m2244d(false);
        m2228a(location, this.hJ, true, false);
        bC();
    }

    private Boolean bP() {
        try {
            Object a = C1057k.m2163a(C1042c.jU, C1041a.Boolean);
            if (a != null && (a instanceof Boolean)) {
                return (Boolean) a;
            }
        } catch (Throwable th) {
        }
        return Boolean.valueOf(false);
    }

    private void bQ() {
        try {
            this.hJ = hv.getScanResults();
            if (this.hJ == null) {
                bC();
                return;
            }
            int b;
            boolean bS = bS();
            int b2 = bw().m2065b(hE, 70);
            if (C1048i.cy().getBoolean(wfcn.iU, false)) {
                if (bS) {
                    bC();
                    return;
                }
                b2 = bw().m2065b(hF, 50);
            }
            if (bS) {
                b = bw().m2065b(hG, 25);
            } else {
                b = b2;
            }
            Iterator listIterator = this.hJ.listIterator();
            while (listIterator.hasNext()) {
                if (WifiManager.calculateSignalLevel(((ScanResult) listIterator.next()).level, 100) <= b) {
                    listIterator.remove();
                }
            }
            if (this.hJ.size() == 0) {
                bC();
                return;
            }
            Set f = m2246f(this.hJ);
            Location cA;
            C1055j c1055j;
            if (bS) {
                if (!m2239b(f, this.hB)) {
                    bC();
                } else if (bR()) {
                    cA = C1055j.cA();
                    if (m2245d(cA)) {
                        c1055j = new C1055j();
                        c1055j.m2160a((C1054c) this);
                        c1055j.init();
                        return;
                    }
                    m2240c(cA);
                } else {
                    m2240c(null);
                }
            } else if (bP().booleanValue()) {
                bC();
            } else {
                C1058l.m2184k(hl, "Searching for new location");
                boolean a = m2233a(f, this.hA);
                m2238b(f);
                if (!a) {
                    bC();
                } else if (bR()) {
                    cA = C1055j.cA();
                    if (m2245d(cA)) {
                        c1055j = new C1055j();
                        c1055j.m2160a((C1054c) this);
                        c1055j.init();
                        return;
                    }
                    m2236b(cA);
                } else {
                    m2236b(null);
                }
            }
        } catch (Throwable th) {
            C1057k.m2176e(hz, String.valueOf(false), C1041a.Boolean);
            C1058l.m2184k(hl, th.getMessage());
        }
    }

    private boolean bR() {
        try {
            if (C1045f.m2133g(C1043d.getContext(), "android.permission.ACCESS_FINE_LOCATION")) {
                return ((LocationManager) C1043d.getContext().getSystemService("location")).isProviderEnabled("gps");
            }
            return false;
        } catch (Throwable th) {
            return false;
        }
    }

    private Set<String> am(String str) {
        try {
            Object a = C1057k.m2163a(str, C1041a.Set);
            if (a == null || !(a instanceof Set)) {
                return null;
            }
            return (Set) a;
        } catch (Throwable th) {
            return null;
        }
    }

    private String m2226a(Set<String> set) {
        StringBuilder stringBuilder = new StringBuilder();
        int i = 1;
        for (String append : set) {
            stringBuilder.append(append);
            if (i != set.size()) {
                stringBuilder.append(",");
                i++;
            }
        }
        return stringBuilder.toString();
    }

    private void m2238b(Set<String> set) {
        C1057k.m2176e(this.hA, m2226a((Set) set), C1041a.Set);
    }

    private void m2241c(Set<String> set) {
        C1057k.m2176e(this.hB, m2226a((Set) set), C1041a.Set);
    }

    private void m2244d(boolean z) {
        C1057k.m2176e(this.hC, String.valueOf(z), C1041a.Boolean);
    }

    private boolean bS() {
        try {
            Object a = C1057k.m2163a(this.hC, C1041a.Boolean);
            if (a == null || !(a instanceof Boolean)) {
                return false;
            }
            return ((Boolean) a).booleanValue();
        } catch (Throwable th) {
            return false;
        }
    }

    private void m2227a(Location location, String str) {
        C1057k.m2176e(str + "Lat", String.valueOf(location.getLatitude()), C1041a.Double);
        C1057k.m2176e(str + "Long", String.valueOf(location.getLongitude()), C1041a.Double);
    }

    private LocationModel an(String str) {
        try {
            Object a = C1057k.m2163a(str + "Long", C1041a.Double);
            if (a == null || !(a instanceof Double)) {
                return null;
            }
            double doubleValue = ((Double) a).doubleValue();
            a = C1057k.m2163a(str + "Lat", C1041a.Double);
            if (a == null || !(a instanceof Double)) {
                return null;
            }
            return new LocationModel(this, ((Double) a).doubleValue(), doubleValue);
        } catch (Throwable th) {
            C1061b.m2191a(th);
            return null;
        }
    }

    private Set<String> m2246f(List<ScanResult> list) {
        if (list == null) {
            return null;
        }
        try {
            Set<String> hashSet = new HashSet();
            int size = list.size();
            for (int i = 0; i < size; i++) {
                CharSequence charSequence = ((ScanResult) list.get(i)).SSID;
                if (!TextUtils.isEmpty(charSequence)) {
                    hashSet.add(charSequence);
                }
            }
            return hashSet;
        } catch (Throwable th) {
            C1061b.m2191a(th);
            return null;
        }
    }

    private boolean m2233a(Set<String> set, String str) {
        if (set != null) {
            try {
                if (set.size() != 0) {
                    Set am = am(str);
                    if (am == null || am.size() == 0) {
                        return false;
                    }
                    int i = 0;
                    for (String trim : set) {
                        int i2;
                        if (am.contains(trim.trim())) {
                            i2 = i + 1;
                        } else {
                            i2 = i;
                        }
                        i = i2;
                    }
                    if (i != 0) {
                        if (((double) (((float) i) / ((float) am.size()))) < 0.75d) {
                            return false;
                        }
                    }
                    return true;
                }
            } catch (Throwable th) {
                return false;
            }
        }
        return false;
    }

    private boolean m2239b(Set<String> set, String str) {
        if (set != null) {
            try {
                if (set.size() != 0) {
                    Set am = am(str);
                    if (am == null || am.size() == 0) {
                        return false;
                    }
                    int i = 0;
                    for (String trim : set) {
                        int i2;
                        if (am.contains(trim.trim())) {
                            i2 = i + 1;
                        } else {
                            i2 = i;
                        }
                        i = i2;
                    }
                    if (i != 0) {
                        if (((double) (((float) i) / ((float) am.size()))) >= 0.15d) {
                            return false;
                        }
                    }
                    return true;
                }
            } catch (Throwable th) {
                return false;
            }
        }
        return false;
    }

    private void m2228a(Location location, List<ScanResult> list, boolean z, boolean z2) {
        try {
            int b = bw().m2065b("level", 6);
            JSONArray jSONArray = new JSONArray();
            if (!(list == null || list.isEmpty())) {
                Set hashSet = new HashSet();
                for (ScanResult scanResult : list) {
                    String b2 = m2235b(scanResult);
                    if (!hashSet.contains(b2) && WifiManager.calculateSignalLevel(scanResult.level, 100) >= b) {
                        hashSet.add(b2);
                        JSONObject jSONObject = new JSONObject();
                        jSONObject.put("bssid", scanResult.BSSID);
                        jSONObject.put("ssid", scanResult.SSID);
                        jSONObject.put("strength", scanResult.level);
                        if (VERSION.SDK_INT >= 17) {
                            jSONObject.put("age", (int) (((SystemClock.elapsedRealtime() * 1000) - scanResult.timestamp) / 1000000));
                        }
                        jSONArray.put(jSONObject);
                    }
                }
            }
            this.hh = m2225a(location, jSONArray, z, z2);
            bs();
        } catch (Throwable th) {
        }
    }

    public void bC() {
        try {
            synchronized (this) {
                if (this.hd != null) {
                    this.hd.m2160a(null);
                    this.hd.cz();
                    this.hd = null;
                }
            }
            C1057k.m2176e(hz, String.valueOf(false), C1041a.Boolean);
        } catch (Throwable th) {
            try {
                C1061b.m2191a(th);
            } finally {
                C1041a c1041a = null;
                C1057k.m2176e(hz, String.valueOf(false), C1041a.Boolean);
            }
        }
    }

    protected List<C1020b> getData() {
        if (TextUtils.isEmpty(this.hh)) {
            return null;
        }
        List<C1020b> arrayList = new ArrayList();
        arrayList.add(new C1020b(wpul.class.getSimpleName(), this.hh, C1041a.JSONObject.getType()));
        return arrayList;
    }

    protected C1041a bD() {
        return C1041a.JSONObject;
    }

    private String m2225a(Location location, JSONArray jSONArray, boolean z, boolean z2) {
        int i = 1;
        try {
            int accuracy;
            JSONObject jSONObject = new JSONObject();
            double d = 1000.1d;
            jSONObject.put(C1048i.ko, location != null ? location.getLatitude() : 1000.1d);
            String str = SchemaSymbols.ATTVAL_LONG;
            if (location != null) {
                d = location.getLongitude();
            }
            jSONObject.put(str, d);
            String str2 = "acc";
            if (location != null) {
                accuracy = (int) location.getAccuracy();
            } else {
                accuracy = 0;
            }
            jSONObject.put(str2, accuracy);
            if (z2) {
                jSONObject.put("dnv", 1);
            }
            jSONObject.put("wl", jSONArray);
            String str3 = "wpxt";
            if (!z) {
                i = 0;
            }
            jSONObject.put(str3, i);
            return jSONObject.toString();
        } catch (Throwable th) {
            C1061b.m2191a(th);
            return "";
        }
    }

    private String m2235b(ScanResult scanResult) {
        try {
            return C1047h.cx().aA(scanResult.BSSID);
        } catch (Throwable th) {
            return "";
        }
    }

    private boolean m2230a(LocationModel locationModel, LocationModel locationModel2) {
        if (locationModel != null && locationModel2 == null) {
            return true;
        }
        try {
            float[] fArr = new float[3];
            int b = bw().m2065b(hH, 150);
            Location.distanceBetween(locationModel2.bU(), locationModel2.bV(), locationModel.bU(), locationModel.bV(), fArr);
            if (Math.abs(fArr[0]) > ((float) b)) {
                return true;
            }
        } catch (Throwable th) {
            C1061b.m2191a(th);
        }
        return false;
    }

    public void mo2039a(final Location location) {
        try {
            if (!this.hy) {
                this.hy = true;
                if (this.hd != null) {
                    this.hd.m2160a(null);
                    this.hd.cz();
                    this.hd = null;
                }
                C1046g.cw().m2141b(new Runnable(this) {
                    final /* synthetic */ wpul hL;

                    public void run() {
                        try {
                            if (this.hL.bS()) {
                                this.hL.m2240c(location);
                            } else {
                                this.hL.m2236b(location);
                            }
                        } catch (Throwable th) {
                            C1057k.m2176e(wpul.hz, String.valueOf(false), C1041a.Boolean);
                        }
                    }
                });
            }
        } catch (Throwable th) {
            C1057k.m2176e(hz, String.valueOf(false), C1041a.Boolean);
        }
    }

    public void onError(String str) {
        try {
            C1046g.cw().m2141b(new C10762(this));
        } catch (Throwable th) {
        }
    }

    private boolean m2245d(Location location) {
        return location == null || location.getAccuracy() > 70.0f || System.currentTimeMillis() - location.getTime() > bw().m2063a("timeout", (long) hI);
    }
}
