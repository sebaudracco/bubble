package com.moat.analytics.mobile.inm;

import android.app.Activity;
import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.os.Bundle;
import android.util.Log;
import com.moat.analytics.mobile.inm.base.exception.C3376a;

class C3382e implements ActivityLifecycleCallbacks {
    final /* synthetic */ C3380c f8595a;

    private C3382e(C3380c c3380c) {
        this.f8595a = c3380c;
    }

    private boolean m11599a(Activity activity) {
        Activity activity2 = (Activity) this.f8595a.f8591b.get();
        return activity2 != null && activity2.equals(activity);
    }

    public void onActivityCreated(Activity activity, Bundle bundle) {
    }

    public void onActivityDestroyed(Activity activity) {
        try {
            if (this.f8595a.f8593d.mo6482b()) {
                Log.d("MoatActivityState", "Activity destroyed: " + activity.getClass() + "@" + activity.hashCode());
            }
            if (m11599a(activity)) {
                this.f8595a.f8594e = false;
                ((Application) this.f8595a.f8590a.get()).unregisterActivityLifecycleCallbacks(this);
            }
        } catch (Exception e) {
            C3376a.m11557a(e);
        }
    }

    public void onActivityPaused(Activity activity) {
        if (this.f8595a.f8593d.mo6482b()) {
            Log.d("MoatActivityState", "Activity paused: " + activity.getClass() + "@" + activity.hashCode());
        }
        if (m11599a(activity)) {
            this.f8595a.f8594e = true;
        }
    }

    public void onActivityResumed(Activity activity) {
        if (this.f8595a.f8593d.mo6482b()) {
            Log.d("MoatActivityState", "Activity resumed: " + activity.getClass() + "@" + activity.hashCode());
        }
        if (m11599a(activity)) {
            this.f8595a.f8594e = false;
        }
    }

    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    public void onActivityStarted(Activity activity) {
        if (this.f8595a.f8593d.mo6482b()) {
            Log.d("MoatActivityState", "Activity started: " + activity.getClass() + "@" + activity.hashCode());
        }
        if (m11599a(activity)) {
            this.f8595a.f8594e = false;
        }
    }

    public void onActivityStopped(Activity activity) {
        if (this.f8595a.f8593d.mo6482b()) {
            Log.d("MoatActivityState", "Activity stopped: " + activity.getClass() + "@" + activity.hashCode());
        }
        if (m11599a(activity)) {
            this.f8595a.f8594e = true;
        }
    }
}
