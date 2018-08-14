package com.inmobi.commons.core.p113a;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.Context;
import android.os.Bundle;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Picasso.Builder;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

@TargetApi(14)
/* compiled from: PicassoWrapper */
public class C3108a {
    private static final String f7602a = C3108a.class.getSimpleName();
    private static volatile Picasso f7603b;
    private static final Object f7604c = new Object();
    private static List<WeakReference<Context>> f7605d = new ArrayList();
    private static ActivityLifecycleCallbacks f7606e = new C31071();

    /* compiled from: PicassoWrapper */
    static class C31071 implements ActivityLifecycleCallbacks {
        C31071() {
        }

        public void onActivityCreated(Activity activity, Bundle bundle) {
        }

        public void onActivityStarted(Activity activity) {
        }

        public void onActivityResumed(Activity activity) {
        }

        public void onActivityPaused(Activity activity) {
        }

        public void onActivityStopped(Activity activity) {
        }

        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        public void onActivityDestroyed(Activity activity) {
            synchronized (C3108a.f7604c) {
                if (C3108a.f7603b != null && C3108a.m10109c(activity)) {
                    activity.getApplication().unregisterActivityLifecycleCallbacks(C3108a.f7606e);
                    C3108a.f7605d.remove(activity);
                    if (C3108a.f7605d.isEmpty()) {
                        Logger.m10359a(InternalLogLevel.INTERNAL, C3108a.f7602a, "Picasso instance " + C3108a.f7603b.toString() + " shutdown");
                        C3108a.f7603b.shutdown();
                        C3108a.f7603b = null;
                    }
                }
            }
        }
    }

    public static Picasso m10103a(Context context) {
        synchronized (f7604c) {
            if (!C3108a.m10109c(context)) {
                f7605d.add(new WeakReference(context));
            }
            if (f7603b == null) {
                f7603b = new Builder(context).build();
                if (context instanceof Activity) {
                    ((Activity) context).getApplication().registerActivityLifecycleCallbacks(f7606e);
                }
            }
        }
        return f7603b;
    }

    private static boolean m10109c(Context context) {
        for (int i = 0; i < f7605d.size(); i++) {
            Context context2 = (Context) ((WeakReference) f7605d.get(i)).get();
            if (context2 != null && context2.equals(context)) {
                return true;
            }
        }
        return false;
    }
}
