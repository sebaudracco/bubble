package com.yandex.metrica.impl;

import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;
import android.os.Bundle;

public class C4386m implements ActivityLifecycleCallbacks {
    private C4544z f11881a;

    public C4386m(C4544z c4544z) {
        this.f11881a = c4544z;
    }

    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
    }

    public void onActivityStarted(Activity activity) {
    }

    public void onActivityResumed(Activity activity) {
        this.f11881a.m16326b(activity);
    }

    public void onActivityPaused(Activity activity) {
        this.f11881a.m16329c(activity);
    }

    public void onActivityStopped(Activity activity) {
    }

    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
    }

    public void onActivityDestroyed(Activity activity) {
    }
}
