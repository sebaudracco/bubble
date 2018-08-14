package com.moat.analytics.mobile.mpub;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import com.google.android.gms.ads.AdActivity;
import com.moat.analytics.mobile.mpub.C3460t.C3456a;
import com.moat.analytics.mobile.mpub.base.functional.Optional;
import java.lang.ref.WeakReference;

final class C3417e {
    private static WeakReference<Activity> f8679 = new WeakReference(null);
    @Nullable
    private static WebAdTracker f8680;

    C3417e() {
    }

    static void m11668(Activity activity) {
        try {
            if (C3460t.m11803().f8824 != C3456a.f8807) {
                String name = activity.getClass().getName();
                C3412a.m11642(3, "GMAInterstitialHelper", activity, "Activity name: " + name);
                if (!name.contains(AdActivity.CLASS_NAME)) {
                    if (f8680 != null) {
                        C3412a.m11642(3, "GMAInterstitialHelper", f8679.get(), "Stopping to track GMA interstitial");
                        f8680.stopTracking();
                        f8680 = null;
                    }
                    f8679 = new WeakReference(null);
                } else if (f8679.get() == null || f8679.get() != activity) {
                    View decorView = activity.getWindow().getDecorView();
                    if (decorView instanceof ViewGroup) {
                        Optional ˊ = C3468x.m11826((ViewGroup) decorView, true);
                        if (ˊ.isPresent()) {
                            f8679 = new WeakReference(activity);
                            WebView webView = (WebView) ˊ.get();
                            C3412a.m11642(3, "GMAInterstitialHelper", f8679.get(), "Starting to track GMA interstitial");
                            WebAdTracker createWebAdTracker = MoatFactory.create().createWebAdTracker(webView);
                            f8680 = createWebAdTracker;
                            createWebAdTracker.startTracking();
                            return;
                        }
                        C3412a.m11642(3, "GMAInterstitialHelper", activity, "Sorry, no WebView in this activity");
                    }
                }
            }
        } catch (Exception e) {
            C3442o.m11756(e);
        }
    }
}
