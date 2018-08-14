package com.moat.analytics.mobile.vng;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import com.google.android.gms.ads.AdActivity;
import com.moat.analytics.mobile.vng.C3532w.C3531d;
import com.moat.analytics.mobile.vng.p130a.p132b.C3474a;
import java.lang.ref.WeakReference;

class C3483f {
    @Nullable
    private static WebAdTracker f8884a;
    private static WeakReference<Activity> f8885b = new WeakReference(null);

    C3483f() {
    }

    private static void m11867a() {
        if (f8884a != null) {
            C3511p.m11976a(3, "GMAInterstitialHelper", f8885b.get(), "Stopping to track GMA interstitial");
            f8884a.stopTracking();
            f8884a = null;
        }
    }

    static void m11868a(Activity activity) {
        try {
            if (C3532w.m12009a().f8995a != C3531d.OFF) {
                if (!C3483f.m11870b(activity)) {
                    C3483f.m11867a();
                    f8885b = new WeakReference(null);
                } else if (f8885b.get() == null || f8885b.get() != activity) {
                    View decorView = activity.getWindow().getDecorView();
                    if (decorView instanceof ViewGroup) {
                        C3474a a = ab.m11857a((ViewGroup) decorView);
                        if (a.m11845c()) {
                            f8885b = new WeakReference(activity);
                            C3483f.m11869a((WebView) a.m11843b());
                            return;
                        }
                        C3511p.m11976a(3, "GMAInterstitialHelper", (Object) activity, "Sorry, no WebView in this activity");
                    }
                }
            }
        } catch (Exception e) {
            C3502m.m11942a(e);
        }
    }

    private static void m11869a(WebView webView) {
        C3511p.m11976a(3, "GMAInterstitialHelper", f8885b.get(), "Starting to track GMA interstitial");
        f8884a = MoatFactory.create().createWebAdTracker(webView);
        f8884a.startTracking();
    }

    private static boolean m11870b(Activity activity) {
        String name = activity.getClass().getName();
        C3511p.m11976a(3, "GMAInterstitialHelper", (Object) activity, "Activity name: " + name);
        return name.contains(AdActivity.CLASS_NAME);
    }
}
