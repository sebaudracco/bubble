package com.appnext.core;

import android.content.Context;
import android.content.SharedPreferences;
import com.github.lzyzsd.jsbridge.BridgeUtil;
import com.mobfox.sdk.services.MobFoxService;
import java.util.HashMap;

public class C1134l {
    private static C1134l mk;
    private int ec = 24;
    private HashMap<String, SharedPreferences> ml = new HashMap();

    private C1134l() {
    }

    public void m2372i(final Context context, final String str) {
        if (!this.ml.containsKey(str.replace(BridgeUtil.SPLIT_MARK, ""))) {
            new Thread(new Runnable(this) {
                final /* synthetic */ C1134l mm;

                public void run() {
                    this.mm.ml.put(str, context.getSharedPreferences("apnxt_cap" + str.replace(BridgeUtil.SPLIT_MARK, ""), 0));
                }
            }).start();
        }
    }

    public static synchronized C1134l db() {
        C1134l c1134l;
        synchronized (C1134l.class) {
            if (mk == null) {
                mk = new C1134l();
            }
            c1134l = mk;
        }
        return c1134l;
    }

    public void m2373q(String str, String str2) {
        ((SharedPreferences) this.ml.get(str2)).edit().putLong(str, System.currentTimeMillis()).apply();
    }

    public boolean m2374r(String str, String str2) {
        long j = ((SharedPreferences) this.ml.get(str2)).getLong(str, -1);
        return j != -1 && System.currentTimeMillis() - ((long) (MobFoxService.INTERVAL_TIME_TO_SEND * this.ec)) <= j;
    }

    public boolean m2375s(String str, String str2) {
        long j = ((SharedPreferences) this.ml.get(str2)).getLong(str, -1);
        return j != -1 && System.currentTimeMillis() - 120000 <= j;
    }

    public void aS(String str) {
        ((SharedPreferences) this.ml.get(str)).edit().clear().apply();
    }

    public void m2371c(int i) {
        this.ec = i;
    }
}
