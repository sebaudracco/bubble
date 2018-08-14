package com.onesignal;

import android.app.Activity;
import android.app.Instrumentation;
import android.app.Instrumentation.ActivityMonitor;
import android.app.OnActivityPausedListener;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

class ActivityLifecycleListenerCompat {
    ActivityLifecycleListenerCompat() {
    }

    static void startListener() {
        try {
            Class activityThreadClass = Class.forName("android.app.ActivityThread");
            Object activityThread = activityThreadClass.getMethod("currentActivityThread", new Class[0]).invoke(null, new Object[0]);
            Field instrumentationField = activityThreadClass.getDeclaredField("mInstrumentation");
            instrumentationField.setAccessible(true);
            startMonitorThread(activityThreadClass, activityThread, ((Instrumentation) instrumentationField.get(activityThread)).addMonitor((String) null, null, false));
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    private static void startMonitorThread(final Class activityThreadClass, final Object activityThread, final ActivityMonitor allActivitiesMonitor) {
        new Thread(new Runnable() {

            class C38841 implements OnActivityPausedListener {
                C38841() {
                }

                public void onPaused(Activity activity) {
                    ActivityLifecycleHandler.onActivityPaused(activity);
                }
            }

            public void run() {
                try {
                    OnActivityPausedListener pausedListener = new C38841();
                    Method registerOnActivityPausedListener = activityThreadClass.getMethod("registerOnActivityPausedListener", new Class[]{Activity.class, OnActivityPausedListener.class});
                    while (true) {
                        Activity currentActivity = allActivitiesMonitor.waitForActivity();
                        if (!currentActivity.isFinishing()) {
                            ActivityLifecycleHandler.onActivityResumed(currentActivity);
                            registerOnActivityPausedListener.invoke(activityThread, new Object[]{currentActivity, pausedListener});
                        }
                    }
                } catch (Throwable t) {
                    t.printStackTrace();
                }
            }
        }, "OS_LIFECYCLE_COMPAT").start();
    }
}
