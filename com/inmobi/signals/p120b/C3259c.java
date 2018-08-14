package com.inmobi.signals.p120b;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.os.Looper;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.commons.p112a.C3105a;
import java.util.List;

/* compiled from: WifiScanner */
public class C3259c {
    private static final String f8188a = C3259c.class.getSimpleName();
    private static Context f8189b = null;
    private static C3258a f8190c = null;
    private static Handler f8191d = null;
    private static boolean f8192e = false;
    private static final IntentFilter f8193f = new IntentFilter("android.net.wifi.SCAN_RESULTS");
    private static List<C3254a> f8194g;
    private static Runnable f8195h = new C32561();
    private static final BroadcastReceiver f8196i = new C32572();

    /* compiled from: WifiScanner */
    static class C32561 implements Runnable {
        C32561() {
        }

        public void run() {
            C3258a b = C3259c.f8190c;
            C3259c.m10913f();
            if (b != null) {
                b.mo6268a();
            }
        }
    }

    /* compiled from: WifiScanner */
    static class C32572 extends BroadcastReceiver {
        C32572() {
        }

        public void onReceive(Context context, Intent intent) {
            C3258a b = C3259c.f8190c;
            WifiManager wifiManager = (WifiManager) C3259c.f8189b.getSystemService("wifi");
            C3259c.m10913f();
            if (b != null) {
                C3259c.f8194g = C3255b.m10894a(wifiManager.getScanResults());
                b.mo6269a(C3259c.f8194g);
            }
        }
    }

    /* compiled from: WifiScanner */
    public interface C3258a {
        void mo6268a();

        void mo6269a(List<C3254a> list);
    }

    public static boolean m10908a(C3258a c3258a) {
        f8189b = C3105a.m10078b();
        return C3259c.m10907a(Looper.myLooper(), c3258a, 10000, false);
    }

    public static List<C3254a> m10905a() {
        return f8194g;
    }

    private static synchronized boolean m10907a(Looper looper, C3258a c3258a, long j, boolean z) {
        boolean z2;
        synchronized (C3259c.class) {
            if (f8191d != null) {
                z2 = false;
            } else {
                Context b = C3105a.m10078b();
                if (b == null) {
                    z2 = false;
                } else {
                    WifiManager wifiManager = (WifiManager) b.getSystemService("wifi");
                    if (wifiManager.isWifiEnabled()) {
                        f8190c = c3258a;
                        f8191d = new Handler(looper);
                        f8191d.postDelayed(f8195h, j);
                        C3259c.m10915h();
                        z2 = wifiManager.startScan();
                    } else {
                        z2 = false;
                    }
                }
            }
        }
        return z2;
    }

    private static synchronized void m10913f() {
        synchronized (C3259c.class) {
            if (f8191d != null) {
                f8191d.removeCallbacks(f8195h);
                C3259c.m10914g();
                f8191d = null;
                f8190c = null;
                f8189b = null;
            }
        }
    }

    private static void m10914g() {
        if (f8192e) {
            f8192e = false;
            try {
                f8189b.unregisterReceiver(f8196i);
            } catch (IllegalArgumentException e) {
                Logger.m10359a(InternalLogLevel.INTERNAL, f8188a, "Failed to register for Wifi scanning.");
            }
        }
    }

    private static void m10915h() {
        if (!f8192e) {
            f8192e = true;
            f8189b.registerReceiver(f8196i, f8193f, null, f8191d);
        }
    }
}
