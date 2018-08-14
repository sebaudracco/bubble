package com.moat.analytics.mobile.vng;

import android.app.Activity;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebView;
import com.moat.analytics.mobile.vng.a.a.a;
import com.mobfox.sdk.networking.RequestParams;
import java.lang.ref.WeakReference;

abstract class C1584b {
    j f2668a;
    final String f2669b;
    final boolean f2670c;
    boolean f2671d;
    boolean f2672e;
    private WeakReference<View> f2673f;
    private WeakReference<WebView> f2674g;
    private final z f2675h;
    private final boolean f2676i;

    C1584b(@Nullable View view, boolean z, boolean z2) {
        p.a(3, "BaseTracker", this, "Initializing.");
        if (z) {
            this.f2669b = RequestParams.f9036M + hashCode();
        } else {
            this.f2669b = "";
        }
        this.f2673f = new WeakReference(view);
        this.f2676i = z;
        this.f2670c = z2;
        this.f2671d = false;
        this.f2672e = false;
        this.f2675h = new z();
    }

    private void m3449g() {
        a.a(this.f2674g);
        p.a(3, "BaseTracker", this, "Attempting bridge installation.");
        if (this.f2674g.get() != null) {
            if (!(this.f2676i || this.f2670c)) {
                this.f2668a = new j((WebView) this.f2674g.get(), j.a.a);
            }
            p.a(3, "BaseTracker", this, "Bridge " + (this.f2668a.a ? "" : "not ") + "installed.");
            return;
        }
        this.f2668a = null;
        p.a(3, "BaseTracker", this, "Bridge not installed, WebView is null.");
    }

    abstract String m3450a();

    void m3451a(WebView webView) {
        if (webView != null) {
            this.f2674g = new WeakReference(webView);
            if (this.f2668a == null) {
                m3449g();
            }
            if (this.f2668a != null && this.f2668a.a) {
                this.f2668a.a(this);
            }
        }
    }

    void m3452a(j jVar) {
        this.f2668a = jVar;
    }

    boolean m3453b() {
        p.a(3, "BaseTracker", this, "Attempting to start impression.");
        if (this.f2672e) {
            p.a(3, "BaseTracker", this, "startTracking failed, tracker already started");
            p.a("[INFO] ", m3450a() + " already started");
            return false;
        }
        boolean b = this.f2668a.b(this);
        p.a(3, "BaseTracker", this, "Impression " + (b ? "" : "not ") + "started.");
        if (!b) {
            return b;
        }
        this.f2671d = true;
        this.f2672e = true;
        return b;
    }

    boolean m3454c() {
        p.a(3, "BaseTracker", this, "Attempting to stop impression.");
        this.f2671d = false;
        boolean c = this.f2668a.c(this);
        p.a(3, "BaseTracker", this, "Impression tracking " + (c ? "" : "not ") + "stopped.");
        return c;
    }

    @CallSuper
    public void changeTargetView(View view) {
        p.a(3, "BaseTracker", this, "changing view to " + (view != null ? view.getClass().getSimpleName() + "@" + view.hashCode() : "null"));
        this.f2673f = new WeakReference(view);
    }

    View m3455d() {
        return (View) this.f2673f.get();
    }

    String m3456e() {
        return m3455d() != null ? m3455d().getClass().getSimpleName() + "@" + m3455d().hashCode() : "";
    }

    String m3457f() {
        this.f2675h.a(this.f2669b, m3455d());
        return this.f2675h.a;
    }

    @Deprecated
    public void setActivity(Activity activity) {
    }

    public void startTracking() {
        boolean z = false;
        try {
            p.a(3, "BaseTracker", this, "In startTracking method.");
            z = m3453b();
        } catch (Exception e) {
            m.a(e);
        }
        p.a(3, "BaseTracker", this, "Attempt to start tracking ad impression was " + (z ? "" : "un") + "successful.");
        p.a(z ? "[SUCCESS] " : "[ERROR] ", m3450a() + " startTracking " + (z ? "succeeded" : "failed") + " for " + m3456e());
    }

    public void stopTracking() {
        boolean z = false;
        try {
            p.a(3, "BaseTracker", this, "In stopTracking method.");
            z = m3454c();
        } catch (Exception e) {
            m.a(e);
        }
        p.a(3, "BaseTracker", this, "Attempt to stop tracking ad impression was " + (z ? "" : "un") + "successful.");
        p.a(z ? "[SUCCESS] " : "[ERROR] ", m3450a() + " stopTracking " + (z ? "succeeded" : "failed") + " for " + m3456e());
    }
}
