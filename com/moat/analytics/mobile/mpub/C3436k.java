package com.moat.analytics.mobile.mpub;

import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import com.moat.analytics.mobile.mpub.C3445p.C3405c;
import com.moat.analytics.mobile.mpub.NoOp.C3401b;
import com.moat.analytics.mobile.mpub.NoOp.C3402c;
import com.moat.analytics.mobile.mpub.NoOp.C3403e;
import com.moat.analytics.mobile.mpub.base.functional.Optional;
import java.lang.ref.WeakReference;
import java.util.Map;

final class C3436k extends MoatFactory {
    C3436k() throws C3442o {
        if (!((C3419f) MoatAnalytics.getInstance()).m11671()) {
            String str = "Failed to initialize MoatFactory";
            String str2 = str + ", SDK was not started";
            C3412a.m11642(3, "Factory", this, str2);
            C3412a.m11639("[ERROR] ", str2);
            throw new C3442o(str);
        }
    }

    public final WebAdTracker createWebAdTracker(@NonNull WebView webView) {
        try {
            final WeakReference weakReference = new WeakReference(webView);
            return (WebAdTracker) C3445p.m11762(new C3405c<WebAdTracker>(this) {
                private /* synthetic */ C3436k f8755;

                public final Optional<WebAdTracker> mo6511() {
                    WebView webView = (WebView) weakReference.get();
                    String str = "Attempting to create WebAdTracker for " + C3412a.m11641(webView);
                    C3412a.m11642(3, "Factory", this, str);
                    C3412a.m11639("[INFO] ", str);
                    return Optional.of(new C3466v(webView));
                }
            }, WebAdTracker.class);
        } catch (Exception e) {
            C3442o.m11756(e);
            return new C3403e();
        }
    }

    public final WebAdTracker createWebAdTracker(@NonNull ViewGroup viewGroup) {
        try {
            final WeakReference weakReference = new WeakReference(viewGroup);
            return (WebAdTracker) C3445p.m11762(new C3405c<WebAdTracker>(this) {
                private /* synthetic */ C3436k f8754;

                public final Optional<WebAdTracker> mo6511() throws C3442o {
                    ViewGroup viewGroup = (ViewGroup) weakReference.get();
                    String str = "Attempting to create WebAdTracker for adContainer " + C3412a.m11641(viewGroup);
                    C3412a.m11642(3, "Factory", this, str);
                    C3412a.m11639("[INFO] ", str);
                    return Optional.of(new C3466v(viewGroup));
                }
            }, WebAdTracker.class);
        } catch (Exception e) {
            C3442o.m11756(e);
            return new C3403e();
        }
    }

    public final NativeDisplayTracker createNativeDisplayTracker(@NonNull View view, @NonNull final Map<String, String> map) {
        try {
            final WeakReference weakReference = new WeakReference(view);
            return (NativeDisplayTracker) C3445p.m11762(new C3405c<NativeDisplayTracker>(this) {
                private /* synthetic */ C3436k f8750;

                public final Optional<NativeDisplayTracker> mo6511() {
                    View view = (View) weakReference.get();
                    String str = "Attempting to create NativeDisplayTracker for " + C3412a.m11641(view);
                    C3412a.m11642(3, "Factory", this, str);
                    C3412a.m11639("[INFO] ", str);
                    return Optional.of(new C3446q(view, map));
                }
            }, NativeDisplayTracker.class);
        } catch (Exception e) {
            C3442o.m11756(e);
            return new C3402c();
        }
    }

    public final NativeVideoTracker createNativeVideoTracker(final String str) {
        try {
            return (NativeVideoTracker) C3445p.m11762(new C3405c<NativeVideoTracker>(this) {
                private /* synthetic */ C3436k f8752;

                public final Optional<NativeVideoTracker> mo6511() {
                    String str = "Attempting to create NativeVideoTracker";
                    C3412a.m11642(3, "Factory", this, str);
                    C3412a.m11639("[INFO] ", str);
                    return Optional.of(new C3451s(str));
                }
            }, NativeVideoTracker.class);
        } catch (Exception e) {
            C3442o.m11756(e);
            return new C3401b();
        }
    }

    public final <T> T createCustomTracker(C3408l<T> c3408l) {
        try {
            return c3408l.create();
        } catch (Exception e) {
            C3442o.m11756(e);
            return c3408l.createNoOp();
        }
    }
}
