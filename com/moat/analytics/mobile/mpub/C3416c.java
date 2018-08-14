package com.moat.analytics.mobile.mpub;

import android.app.Activity;
import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.os.Bundle;
import com.moat.analytics.mobile.mpub.C3460t.C3456a;
import java.lang.ref.WeakReference;

final class C3416c {
    private static boolean f8674 = false;
    private static Application f8675;
    private static boolean f8676 = false;
    static WeakReference<Activity> f8677;
    private static int f8678 = 0;

    static class C3415a implements ActivityLifecycleCallbacks {
        C3415a() {
        }

        public final void onActivityCreated(Activity activity, Bundle bundle) {
            C3416c.f8678 = 1;
        }

        public final void onActivityStarted(Activity activity) {
            try {
                C3416c.f8677 = new WeakReference(activity);
                C3416c.f8678 = 2;
                if (!C3416c.f8674) {
                    C3415a.m11660(true);
                }
                C3416c.f8674 = true;
                C3412a.m11642(3, "ActivityState", this, "Activity started: " + activity.getClass() + "@" + activity.hashCode());
            } catch (Exception e) {
                C3442o.m11756(e);
            }
        }

        public final void onActivityResumed(Activity activity) {
            try {
                C3416c.f8677 = new WeakReference(activity);
                C3416c.f8678 = 3;
                C3460t.m11803().m11809();
                C3412a.m11642(3, "ActivityState", this, "Activity resumed: " + activity.getClass() + "@" + activity.hashCode());
                if (((C3419f) MoatAnalytics.getInstance()).f8687) {
                    C3417e.m11668(activity);
                }
            } catch (Exception e) {
                C3442o.m11756(e);
            }
        }

        public final void onActivityPaused(Activity activity) {
            try {
                C3416c.f8678 = 4;
                if (C3416c.m11662(activity)) {
                    C3416c.f8677 = new WeakReference(null);
                }
                C3412a.m11642(3, "ActivityState", this, "Activity paused: " + activity.getClass() + "@" + activity.hashCode());
            } catch (Exception e) {
                C3442o.m11756(e);
            }
        }

        public final void onActivityStopped(Activity activity) {
            try {
                if (C3416c.f8678 != 3) {
                    C3416c.f8674 = false;
                    C3415a.m11660(false);
                }
                C3416c.f8678 = 5;
                if (C3416c.m11662(activity)) {
                    C3416c.f8677 = new WeakReference(null);
                }
                C3412a.m11642(3, "ActivityState", this, "Activity stopped: " + activity.getClass() + "@" + activity.hashCode());
            } catch (Exception e) {
                C3442o.m11756(e);
            }
        }

        public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        public final void onActivityDestroyed(Activity activity) {
            try {
                if (!(C3416c.f8678 == 3 || C3416c.f8678 == 5)) {
                    if (C3416c.f8674) {
                        C3415a.m11660(false);
                    }
                    C3416c.f8674 = false;
                }
                C3416c.f8678 = 6;
                C3412a.m11642(3, "ActivityState", this, "Activity destroyed: " + activity.getClass() + "@" + activity.hashCode());
                if (C3416c.m11662(activity)) {
                    C3416c.f8677 = new WeakReference(null);
                }
            } catch (Exception e) {
                C3442o.m11756(e);
            }
        }

        private static void m11660(boolean z) {
            if (z) {
                C3412a.m11642(3, "ActivityState", null, "App became visible");
                if (C3460t.m11803().f8824 == C3456a.f8806 && !((C3419f) MoatAnalytics.getInstance()).f8685) {
                    C3441n.m11742().m11752();
                    return;
                }
                return;
            }
            C3412a.m11642(3, "ActivityState", null, "App became invisible");
            if (C3460t.m11803().f8824 == C3456a.f8806 && !((C3419f) MoatAnalytics.getInstance()).f8685) {
                C3441n.m11742().m11753();
            }
        }
    }

    C3416c() {
    }

    static void m11666(Application application) {
        f8675 = application;
        if (!f8676) {
            f8676 = true;
            f8675.registerActivityLifecycleCallbacks(new C3415a());
        }
    }

    static Application m11664() {
        return f8675;
    }

    static /* synthetic */ boolean m11662(Activity activity) {
        return f8677 != null && f8677.get() == activity;
    }
}
