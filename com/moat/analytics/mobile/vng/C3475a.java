package com.moat.analytics.mobile.vng;

import android.app.Activity;
import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.os.Bundle;
import com.moat.analytics.mobile.vng.C3532w.C3531d;
import java.lang.ref.WeakReference;

class C3475a {
    static WeakReference<Activity> f8865a;
    private static boolean f8866b = false;
    private static Application f8867c;
    private static int f8868d = 0;
    private static boolean f8869e = false;

    private static class C3472a implements ActivityLifecycleCallbacks {
        C3472a() {
        }

        private static void m11838a(boolean z) {
            if (z) {
                C3511p.m11976a(3, "ActivityState", null, "App became visible");
                if (C3532w.m12009a().f8995a == C3531d.ON && !((C3500k) MoatAnalytics.getInstance()).f8942c) {
                    C3510o.m11953a().m11973c();
                    return;
                }
                return;
            }
            C3511p.m11976a(3, "ActivityState", null, "App became invisible");
            if (C3532w.m12009a().f8995a == C3531d.ON && !((C3500k) MoatAnalytics.getInstance()).f8942c) {
                C3510o.m11953a().m11974d();
            }
        }

        public void onActivityCreated(Activity activity, Bundle bundle) {
            C3475a.f8868d = 1;
        }

        public void onActivityDestroyed(Activity activity) {
            try {
                if (!(C3475a.f8868d == 3 || C3475a.f8868d == 5)) {
                    if (C3475a.f8869e) {
                        C3472a.m11838a(false);
                    }
                    C3475a.f8869e = false;
                }
                C3475a.f8868d = 6;
                C3511p.m11976a(3, "ActivityState", (Object) this, "Activity destroyed: " + activity.getClass() + "@" + activity.hashCode());
                if (C3475a.m11852b(activity)) {
                    C3475a.f8865a = new WeakReference(null);
                }
            } catch (Exception e) {
                C3502m.m11942a(e);
            }
        }

        public void onActivityPaused(Activity activity) {
            try {
                C3475a.f8868d = 4;
                if (C3475a.m11852b(activity)) {
                    C3475a.f8865a = new WeakReference(null);
                }
                C3511p.m11976a(3, "ActivityState", (Object) this, "Activity paused: " + activity.getClass() + "@" + activity.hashCode());
            } catch (Exception e) {
                C3502m.m11942a(e);
            }
        }

        public void onActivityResumed(Activity activity) {
            try {
                C3475a.f8865a = new WeakReference(activity);
                C3475a.f8868d = 3;
                C3532w.m12009a().m12022b();
                C3511p.m11976a(3, "ActivityState", (Object) this, "Activity resumed: " + activity.getClass() + "@" + activity.hashCode());
                if (((C3500k) MoatAnalytics.getInstance()).f8941b) {
                    C3483f.m11868a(activity);
                }
            } catch (Exception e) {
                C3502m.m11942a(e);
            }
        }

        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        public void onActivityStarted(Activity activity) {
            try {
                C3475a.f8865a = new WeakReference(activity);
                C3475a.f8868d = 2;
                if (!C3475a.f8869e) {
                    C3472a.m11838a(true);
                }
                C3475a.f8869e = true;
                C3511p.m11976a(3, "ActivityState", (Object) this, "Activity started: " + activity.getClass() + "@" + activity.hashCode());
            } catch (Exception e) {
                C3502m.m11942a(e);
            }
        }

        public void onActivityStopped(Activity activity) {
            try {
                if (C3475a.f8868d != 3) {
                    C3475a.f8869e = false;
                    C3472a.m11838a(false);
                }
                C3475a.f8868d = 5;
                if (C3475a.m11852b(activity)) {
                    C3475a.f8865a = new WeakReference(null);
                }
                C3511p.m11976a(3, "ActivityState", (Object) this, "Activity stopped: " + activity.getClass() + "@" + activity.hashCode());
            } catch (Exception e) {
                C3502m.m11942a(e);
            }
        }
    }

    C3475a() {
    }

    static Application m11847a() {
        return f8867c;
    }

    static void m11848a(Application application) {
        f8867c = application;
        if (!f8866b) {
            f8866b = true;
            f8867c.registerActivityLifecycleCallbacks(new C3472a());
        }
    }

    private static boolean m11852b(Activity activity) {
        return f8865a != null && f8865a.get() == activity;
    }
}
