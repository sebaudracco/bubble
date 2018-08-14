package com.moat.analytics.mobile.mpub;

import android.app.Application;
import android.support.annotation.UiThread;

public abstract class MoatAnalytics {
    private static MoatAnalytics f8647 = null;

    @UiThread
    public abstract void prepareNativeDisplayTracking(String str);

    public abstract void start(Application application);

    public abstract void start(MoatOptions moatOptions, Application application);

    public static synchronized MoatAnalytics getInstance() {
        MoatAnalytics moatAnalytics;
        synchronized (MoatAnalytics.class) {
            if (f8647 == null) {
                try {
                    f8647 = new C3419f();
                } catch (Exception e) {
                    C3442o.m11756(e);
                    f8647 = new com.moat.analytics.mobile.mpub.NoOp.MoatAnalytics();
                }
            }
            moatAnalytics = f8647;
        }
        return moatAnalytics;
    }
}
