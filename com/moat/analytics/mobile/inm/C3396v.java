package com.moat.analytics.mobile.inm;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import com.moat.analytics.mobile.inm.base.asserts.C3375a;
import com.moat.analytics.mobile.inm.base.exception.C3376a;
import java.lang.ref.WeakReference;
import java.util.concurrent.atomic.AtomicReference;

class C3396v extends MoatFactory {
    private static final AtomicReference<ao> f8622c = new AtomicReference();
    private final bg f8623a = new bh();
    private final C3371a f8624b;

    C3396v(Activity activity) {
        if (f8622c.get() == null) {
            Object arVar;
            am amVar = new am();
            try {
                arVar = new ar(ab.instance);
            } catch (Exception e) {
                C3376a.m11557a(e);
                am amVar2 = amVar;
            }
            f8622c.compareAndSet(null, arVar);
        }
        this.f8624b = new C3380c(activity, (ao) f8622c.get());
        this.f8624b.mo6498b();
    }

    private NativeDisplayTracker m11624a(View view, String str) {
        C3375a.m11556a(view);
        ao aoVar = (ao) f8622c.get();
        return (NativeDisplayTracker) ax.m11540a(aoVar, new C3399y(this, new WeakReference(view), aoVar, str), new af());
    }

    private NativeVideoTracker m11625a(String str) {
        ao aoVar = (ao) f8622c.get();
        return (NativeVideoTracker) ax.m11540a(aoVar, new C3400z(this, aoVar, str), new ah());
    }

    private WebAdTracker m11626a(ViewGroup viewGroup) {
        C3375a.m11556a(viewGroup);
        ao aoVar = (ao) f8622c.get();
        return (WebAdTracker) ax.m11540a(aoVar, new C3398x(this, new WeakReference(viewGroup), aoVar), new bf());
    }

    private WebAdTracker m11627a(WebView webView) {
        C3375a.m11556a(webView);
        ao aoVar = (ao) f8622c.get();
        return (WebAdTracker) ax.m11540a(aoVar, new C3397w(this, new WeakReference(webView), aoVar), new bf());
    }

    public <T> T m11630a(MoatPlugin<T> moatPlugin) {
        return moatPlugin.m11462a(this.f8624b, (ao) f8622c.get());
    }

    public <T> T createCustomTracker(MoatPlugin<T> moatPlugin) {
        try {
            return m11630a((MoatPlugin) moatPlugin);
        } catch (Exception e) {
            C3376a.m11557a(e);
            return moatPlugin.m11461a();
        }
    }

    public NativeDisplayTracker createNativeDisplayTracker(View view, String str) {
        try {
            return m11624a(view, str);
        } catch (Exception e) {
            C3376a.m11557a(e);
            return new ak();
        }
    }

    public NativeVideoTracker createNativeVideoTracker(String str) {
        try {
            return m11625a(str);
        } catch (Exception e) {
            C3376a.m11557a(e);
            return new al();
        }
    }

    public WebAdTracker createWebAdTracker(ViewGroup viewGroup) {
        try {
            return m11626a(viewGroup);
        } catch (Exception e) {
            C3376a.m11557a(e);
            return new an();
        }
    }

    public WebAdTracker createWebAdTracker(WebView webView) {
        try {
            return m11627a(webView);
        } catch (Exception e) {
            C3376a.m11557a(e);
            return new an();
        }
    }

    public WebAdTracker createWebDisplayTracker(ViewGroup viewGroup) {
        return createWebAdTracker(viewGroup);
    }

    public WebAdTracker createWebDisplayTracker(WebView webView) {
        return createWebAdTracker(webView);
    }
}
