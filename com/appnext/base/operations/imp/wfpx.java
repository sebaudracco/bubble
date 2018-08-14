package com.appnext.base.operations.imp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Build.VERSION;
import android.os.Bundle;
import com.appnext.base.C1061b;
import com.appnext.base.operations.C1063a;
import com.appnext.base.operations.C1066d;
import com.appnext.base.p019a.p021b.C1020b;
import com.appnext.base.p019a.p021b.C1021c;
import com.appnext.base.p023b.C1042c.C1041a;
import com.appnext.base.p023b.C1043d;
import com.appnext.base.p023b.C1045f;
import com.appnext.base.p023b.C1046g;
import com.appnext.base.p023b.C1047h;
import com.appnext.base.p023b.C1058l;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONObject;

public class wfpx extends C1063a {
    private String hh;
    private Context mContext = C1043d.getContext();

    class C10741 implements Runnable {
        final /* synthetic */ wfpx hu;

        C10741(wfpx com_appnext_base_operations_imp_wfpx) {
            this.hu = com_appnext_base_operations_imp_wfpx;
        }

        public void run() {
            try {
                WifiManager wifiManager = (WifiManager) this.hu.mContext.getApplicationContext().getSystemService("wifi");
                if (this.hu.hasPermission() && wifiManager.isWifiEnabled()) {
                    List<ScanResult> scanResults = wifiManager.getScanResults();
                    if (scanResults == null || scanResults.isEmpty()) {
                        this.hu.hh = null;
                    } else {
                        Set hashSet = new HashSet();
                        JSONArray jSONArray = new JSONArray();
                        for (ScanResult scanResult : scanResults) {
                            String a = this.hu.mo2043a(scanResult);
                            int calculateSignalLevel = WifiManager.calculateSignalLevel(scanResult.level, 100);
                            if (!hashSet.contains(a) && this.hu.mo2045f(calculateSignalLevel).booleanValue()) {
                                C1058l.m2184k(getClass().getSimpleName(), "SSID: " + scanResult.SSID + " BSSID: " + scanResult.BSSID + " Level: " + calculateSignalLevel + " Hash: " + C1047h.cx().aA(scanResult.BSSID));
                                hashSet.add(a);
                                jSONArray.put(this.hu.al(a));
                            }
                        }
                        if (jSONArray.length() == 0) {
                            this.hu.hh = null;
                        }
                        this.hu.hh = jSONArray.toString();
                    }
                } else {
                    this.hu.hh = null;
                }
                this.hu.bs();
            } catch (Throwable th) {
                C1061b.m2191a(th);
            }
        }
    }

    public wfpx(C1021c c1021c, Bundle bundle) {
        super(c1021c, bundle);
    }

    @SuppressLint({"MissingPermission"})
    public void bB() {
        try {
            if (hasPermission()) {
                C1046g.cw().m2142b(new C10741(this), 20000);
            } else {
                C1066d.bG().m2200a(this);
            }
        } catch (Throwable th) {
            C1061b.m2191a(th);
            this.hh = null;
        }
    }

    public void bC() {
    }

    public boolean hasPermission() {
        if (VERSION.SDK_INT != 23 || C1045f.m2133g(C1043d.getContext(), "android.permission.ACCESS_FINE_LOCATION")) {
            return C1045f.m2133g(this.mContext.getApplicationContext(), "android.permission.ACCESS_WIFI_STATE");
        }
        return false;
    }

    protected C1041a bD() {
        return C1041a.JSONArray;
    }

    protected String mo2043a(ScanResult scanResult) {
        return scanResult.SSID;
    }

    protected Object al(String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("ssid", str);
        } catch (Throwable th) {
            C1061b.m2191a(th);
        }
        return jSONObject;
    }

    protected Boolean mo2045f(int i) {
        return Boolean.valueOf(true);
    }

    protected List<C1020b> getData() {
        if (this.hh.isEmpty()) {
            return null;
        }
        List<C1020b> arrayList = new ArrayList();
        arrayList.add(new C1020b(bw().getKey(), this.hh, C1041a.JSONArray.getType()));
        return arrayList;
    }
}
