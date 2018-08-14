package com.moat.analytics.mobile.inm;

import android.app.Activity;
import android.app.Application;
import android.util.Log;
import com.moat.analytics.mobile.inm.base.asserts.C3375a;
import java.lang.ref.WeakReference;

class C3380c implements C3371a {
    private final WeakReference<Application> f8590a;
    private final WeakReference<Activity> f8591b;
    private boolean f8592c;
    private final ao f8593d;
    private boolean f8594e;

    C3380c(Activity activity, ao aoVar) {
        C3375a.m11556a(activity);
        if (aoVar.mo6482b()) {
            Log.d("MoatActivityState", "Listening to Activity: " + (activity != null ? activity.getClass() + "@" + activity.hashCode() : "null"));
        }
        this.f8590a = new WeakReference(activity.getApplication());
        this.f8591b = new WeakReference(activity);
        this.f8593d = aoVar;
        this.f8592c = false;
    }

    public boolean mo6497a() {
        return this.f8594e;
    }

    public void mo6498b() {
        if (!this.f8592c) {
            ((Application) this.f8590a.get()).registerActivityLifecycleCallbacks(new C3382e());
        }
    }

    public Activity mo6499c() {
        return (Activity) this.f8591b.get();
    }
}
