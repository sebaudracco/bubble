package com.inmobi.ads;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.Context;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.NonNull;
import com.inmobi.ads.C3046c.C3044h;
import com.inmobi.ads.C3087t.C2969a;
import com.inmobi.ads.bv.C3024a;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import java.util.Map;
import java.util.WeakHashMap;

/* compiled from: NativeV2AdTracker */
public abstract class aj implements ActivityLifecycleCallbacks {
    private static final String f7130b = aj.class.getSimpleName();
    boolean f7131a;
    private final Map<Context, C3087t> f7132c = new WeakHashMap();

    C3087t m9352a(@NonNull Context context, @NonNull C3044h c3044h, @NonNull C2969a c2969a, @NonNull C3024a c3024a) {
        C3087t c3087t = (C3087t) this.f7132c.get(context);
        if (c3087t == null) {
            if (context instanceof Activity) {
                C3087t c3087t2 = new C3087t(c3044h, new C3076p(c3024a, (Activity) context), c2969a);
                if (VERSION.SDK_INT < 15 || this.f7131a) {
                    c3087t = c3087t2;
                } else {
                    ((Activity) context).getApplication().registerActivityLifecycleCallbacks(this);
                    this.f7131a = true;
                    c3087t = c3087t2;
                }
            } else {
                c3087t = new C3087t(c3044h, new bd(c3024a, c3044h), c2969a);
            }
            this.f7132c.put(context, c3087t);
        }
        return c3087t;
    }

    public void m9353a(@NonNull Context context, @NonNull ai aiVar) {
        C3087t c3087t = (C3087t) this.f7132c.get(context);
        if (c3087t != null) {
            c3087t.m9984a((Object) aiVar);
            if (!c3087t.m9990d()) {
                Logger.m10359a(InternalLogLevel.INTERNAL, f7130b, "Impression tracker is free, removing it");
                m9351a(context);
            }
        }
    }

    @TargetApi(15)
    private void m9351a(@NonNull Context context) {
        C3087t c3087t = (C3087t) this.f7132c.remove(context);
        if (c3087t != null) {
            c3087t.m9991e();
        }
        if ((context instanceof Activity) && VERSION.SDK_INT >= 15 && this.f7132c.isEmpty() && this.f7131a) {
            ((Activity) context).getApplication().unregisterActivityLifecycleCallbacks(this);
            this.f7131a = false;
        }
    }

    public void onActivityCreated(Activity activity, Bundle bundle) {
    }

    public void onActivityStarted(Activity activity) {
        C3087t c3087t = (C3087t) this.f7132c.get(activity);
        if (c3087t != null) {
            c3087t.m9988b();
        }
    }

    public void onActivityResumed(Activity activity) {
    }

    public void onActivityPaused(Activity activity) {
    }

    public void onActivityStopped(Activity activity) {
        C3087t c3087t = (C3087t) this.f7132c.get(activity);
        if (c3087t != null) {
            c3087t.m9985a();
        }
    }

    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    public void onActivityDestroyed(Activity activity) {
        Logger.m10359a(InternalLogLevel.INTERNAL, f7130b, "Activity destroyed, removing impression tracker");
        m9351a(activity);
    }
}
