package com.bgjd.ici;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.bgjd.ici.p025b.C1402i;
import com.bgjd.ici.p030e.C1485h.C1484a.C1483a;
import com.bgjd.ici.plugin.C1520d;
import com.bgjd.ici.plugin.C1532j;

public class MarketConnectivityChange extends BroadcastReceiver {
    private static final String f2023a = "MKTCON";

    public void onReceive(Context context, Intent intent) {
        try {
            C1520d b = C1532j.m3310b();
            if (b != null && b.mo2360b("Freckle")) {
                Object a = b.mo2357a("Freckle");
                if (((Boolean) a.getClass().getMethod(C1483a.m3111g(), new Class[0]).invoke(a, new Object[0])).booleanValue()) {
                    Object invoke = a.getClass().getMethod(C1483a.m3118n(), new Class[0]).invoke(a, new Object[0]);
                    invoke.getClass().getMethod(C1483a.m3105a(), new Class[]{Context.class, Intent.class}).invoke(invoke, new Object[]{context, intent});
                }
            }
        } catch (Throwable e) {
            C1402i.m2898a(f2023a, e, e.getMessage());
        }
    }
}
