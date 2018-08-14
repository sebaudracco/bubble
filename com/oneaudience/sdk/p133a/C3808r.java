package com.oneaudience.sdk.p133a;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import com.bgjd.ici.p025b.C1408j.C1404b;
import com.oneaudience.sdk.C3843e;
import com.oneaudience.sdk.model.WifiNetworkInfo;
import com.oneaudience.sdk.p135c.C3833d;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class C3808r extends C3784a {
    private static final String[] f9137f = new String[]{"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"};
    private WifiManager f9138g = ((WifiManager) this.c.getApplicationContext().getSystemService("wifi"));
    private boolean f9139h;
    private boolean f9140i;
    private ArrayList<WifiNetworkInfo> f9141j;
    private boolean f9142k;
    private Handler f9143l;
    private final BroadcastReceiver f9144m = new C38061(this);
    private Runnable f9145n = new C38072(this);

    class C38061 extends BroadcastReceiver {
        final /* synthetic */ C3808r f9135a;

        C38061(C3808r c3808r) {
            this.f9135a = c3808r;
        }

        public void onReceive(Context context, Intent intent) {
            try {
                this.f9135a.f9139h = true;
                this.f9135a.d = true;
                this.f9135a.m12169a(this.f9135a.f9142k);
                this.f9135a.f9143l.removeCallbacks(this.f9135a.f9145n);
                C3833d.m12246a(C1404b.f2124b, "Saving list size after scanned finished: " + this.f9135a.f9141j.size());
                this.f9135a.m12182j();
                this.f9135a.m12084a(this.f9135a.m12083a((Object) this.f9135a.f9141j));
            } catch (Throwable th) {
                C3833d.m12248a(C1404b.f2124b, "Failed to collect wifi data: ", th);
            }
        }
    }

    class C38072 implements Runnable {
        final /* synthetic */ C3808r f9136a;

        C38072(C3808r c3808r) {
            this.f9136a = c3808r;
        }

        public void run() {
            try {
                this.f9136a.d = true;
                if (!this.f9136a.f9139h) {
                    C3833d.m12246a(C1404b.f2124b, "Wifi scan got timeout. Return last result instead.");
                    this.f9136a.m12169a(this.f9136a.f9142k);
                    C3833d.m12246a(C1404b.f2124b, "Saving list size after timeout expired: " + this.f9136a.f9141j.size());
                    this.f9136a.m12084a(this.f9136a.m12083a((Object) this.f9136a.f9141j));
                }
                this.f9136a.m12182j();
            } catch (Throwable th) {
                C3833d.m12248a(C1404b.f2124b, "Failed to collect wifi data: ", th);
            }
        }
    }

    protected C3808r(Context context, String str, boolean z, boolean z2, long j, boolean z3) {
        super(context, str, z, z2, j, "wifi_data", "disableWifiCollector", false, false);
        this.f9140i = z3;
        this.f9143l = new Handler(Looper.getMainLooper());
    }

    private void m12169a(boolean z) {
        for (ScanResult scanResult : this.f9138g.getScanResults()) {
            long currentTimeMillis = System.currentTimeMillis() / 1000;
            if (VERSION.SDK_INT > 16) {
                currentTimeMillis = scanResult.timestamp;
            }
            this.f9141j.add(new WifiNetworkInfo(2, scanResult.BSSID, scanResult.SSID, scanResult.level, currentTimeMillis, false));
        }
        List<WifiConfiguration> configuredNetworks = this.f9138g.getConfiguredNetworks();
        m12176b(z);
        if (configuredNetworks != null) {
            for (WifiConfiguration wifiConfiguration : configuredNetworks) {
                if (!m12172a(this.f9141j, m12174b(wifiConfiguration.SSID))) {
                    this.f9141j.add(new WifiNetworkInfo(3, wifiConfiguration.BSSID, wifiConfiguration.SSID, -999, System.currentTimeMillis() / 1000, true));
                }
            }
        }
        if (z) {
            WifiInfo connectionInfo = this.f9138g.getConnectionInfo();
            if (connectionInfo != null) {
                Object obj;
                Iterator it = this.f9141j.iterator();
                while (it.hasNext()) {
                    WifiNetworkInfo wifiNetworkInfo = (WifiNetworkInfo) it.next();
                    if (wifiNetworkInfo.bssid.equals(m12174b(connectionInfo.getBSSID()))) {
                        wifiNetworkInfo.status = 1;
                        obj = 1;
                        break;
                    }
                }
                obj = null;
                if (obj == null) {
                    this.f9141j.add(new WifiNetworkInfo(1, connectionInfo.getBSSID(), connectionInfo.getSSID(), -999, System.currentTimeMillis() / 1000, true));
                }
            }
        }
    }

    private boolean m12172a(List<WifiNetworkInfo> list, String str) {
        for (WifiNetworkInfo wifiNetworkInfo : list) {
            if (wifiNetworkInfo.ssid.equals(str)) {
                wifiNetworkInfo.isConfigured = true;
                return true;
            }
        }
        return false;
    }

    private String m12174b(String str) {
        return (str.startsWith("\"") && str.endsWith("\"")) ? str.substring(1, str.length() - 1) : str;
    }

    private void m12176b(boolean z) {
        if (!z && this.f9138g.isWifiEnabled()) {
            this.f9138g.setWifiEnabled(false);
        }
    }

    private ArrayList<WifiNetworkInfo> m12181i() {
        this.f9141j = new ArrayList();
        this.f9142k = this.f9138g.isWifiEnabled();
        try {
            if (this.e) {
                if (this.f9142k || !C3843e.m12285a(this.c, "android.permission.CHANGE_WIFI_STATE") || (this.f9140i && this.f9138g.setWifiEnabled(true))) {
                    this.c.getApplicationContext().registerReceiver(this.f9144m, new IntentFilter("android.net.wifi.SCAN_RESULTS"));
                    this.f9139h = false;
                    if (this.f9138g.startScan()) {
                        this.d = false;
                        this.f9143l.postDelayed(this.f9145n, 60000);
                        return null;
                    }
                    C3833d.m12246a(C1404b.f2124b, "Can't start wifi scan. Return last result instead.");
                } else {
                    C3833d.m12246a(C1404b.f2124b, "Can't enable wifi scanner. Return last result instead.");
                }
            }
            m12169a(this.f9142k);
        } catch (Exception e) {
            m12176b(this.f9142k);
        }
        return this.f9141j;
    }

    private void m12182j() {
        try {
            this.c.getApplicationContext().unregisterReceiver(this.f9144m);
        } catch (Throwable th) {
            C3833d.m12246a(C1404b.f2124b, "Failed to unregister receiver: " + th.getMessage());
        }
    }

    public String mo6804a() {
        if (C3843e.m12285a(this.c, "android.permission.ACCESS_COARSE_LOCATION") || C3843e.m12285a(this.c, "android.permission.ACCESS_FINE_LOCATION")) {
            return m12083a((Object) m12181i());
        }
        C3833d.m12246a(C1404b.f2124b, "Don't have permissions to collect wifi data");
        return "";
    }

    public String[] mo6805b() {
        return f9137f;
    }
}
