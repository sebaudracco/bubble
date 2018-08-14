package com.moat.analytics.mobile.vng;

import android.app.Application;
import com.moat.analytics.mobile.vng.C3523v.C3518a;

public abstract class MoatAnalytics {
    private static MoatAnalytics f8860a = null;

    public static synchronized MoatAnalytics getInstance() {
        MoatAnalytics moatAnalytics;
        synchronized (MoatAnalytics.class) {
            if (f8860a == null) {
                try {
                    f8860a = new C3500k();
                } catch (Exception e) {
                    C3502m.m11942a(e);
                    f8860a = new C3518a();
                }
            }
            moatAnalytics = f8860a;
        }
        return moatAnalytics;
    }

    public abstract void prepareNativeDisplayTracking(String str);

    public abstract void start(Application application);

    public abstract void start(MoatOptions moatOptions, Application application);
}
