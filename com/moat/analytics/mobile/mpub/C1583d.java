package com.moat.analytics.mobile.mpub;

import android.app.Activity;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebView;
import com.moat.analytics.mobile.mpub.j.e;
import com.mobfox.sdk.networking.RequestParams;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

abstract class C1583d {
    private WeakReference<View> f2657;
    private final boolean f2658;
    final boolean f2659;
    TrackerListener f2660;
    private boolean f2661;
    final String f2662;
    j f2663;
    WeakReference<WebView> f2664;
    private boolean f2665;
    o f2666 = null;
    private final u f2667;

    abstract String m3442();

    C1583d(@Nullable View view, boolean z, boolean z2) {
        a.ˏ(3, "BaseTracker", this, "Initializing.");
        this.f2662 = z ? new StringBuilder(RequestParams.f9036M).append(hashCode()).toString() : "";
        this.f2657 = new WeakReference(view);
        this.f2658 = z;
        this.f2659 = z2;
        this.f2661 = false;
        this.f2665 = false;
        this.f2667 = new u();
    }

    public void setListener(TrackerListener trackerListener) {
        this.f2660 = trackerListener;
    }

    public void removeListener() {
        this.f2660 = null;
    }

    public void startTracking() {
        try {
            a.ˏ(3, "BaseTracker", this, "In startTracking method.");
            m3445();
            if (this.f2660 != null) {
                this.f2660.onTrackingStarted("Tracking started on " + a.ˏ((View) this.f2657.get()));
            }
            String str = "startTracking succeeded for " + a.ˏ((View) this.f2657.get());
            a.ˏ(3, "BaseTracker", this, str);
            a.ˊ("[SUCCESS] ", m3442() + " " + str);
        } catch (Exception e) {
            m3448("startTracking", e);
        }
    }

    @CallSuper
    public void stopTracking() {
        String str;
        StringBuilder append;
        String str2;
        Object obj = 1;
        try {
            a.ˏ(3, "BaseTracker", this, "In stopTracking method.");
            this.f2665 = true;
            if (this.f2663 != null) {
                this.f2663.ˋ(this);
                a.ˏ(3, "BaseTracker", this, "Attempt to stop tracking ad impression was " + (obj == null ? "" : "un") + "successful.");
                str = obj == null ? "[SUCCESS] " : "[ERROR] ";
                append = new StringBuilder().append(m3442()).append(" stopTracking ");
                if (obj == null) {
                    str2 = "succeeded";
                } else {
                    str2 = "failed";
                }
                a.ˊ(str, append.append(str2).append(" for ").append(a.ˏ((View) this.f2657.get())).toString());
                if (this.f2660 != null) {
                    this.f2660.onTrackingStopped("");
                    this.f2660 = null;
                }
            }
        } catch (Exception e) {
            o.ˎ(e);
        }
        obj = null;
        if (obj == null) {
        }
        a.ˏ(3, "BaseTracker", this, "Attempt to stop tracking ad impression was " + (obj == null ? "" : "un") + "successful.");
        if (obj == null) {
        }
        append = new StringBuilder().append(m3442()).append(" stopTracking ");
        if (obj == null) {
            str2 = "failed";
        } else {
            str2 = "succeeded";
        }
        a.ˊ(str, append.append(str2).append(" for ").append(a.ˏ((View) this.f2657.get())).toString());
        if (this.f2660 != null) {
            this.f2660.onTrackingStopped("");
            this.f2660 = null;
        }
    }

    @CallSuper
    public void changeTargetView(View view) {
        a.ˏ(3, "BaseTracker", this, "changing view to " + a.ˏ(view));
        this.f2657 = new WeakReference(view);
    }

    @Deprecated
    public void setActivity(Activity activity) {
    }

    @CallSuper
    void m3445() throws o {
        a.ˏ(3, "BaseTracker", this, "Attempting to start impression.");
        m3444();
        if (this.f2661) {
            throw new o("Tracker already started");
        } else if (this.f2665) {
            throw new o("Tracker already stopped");
        } else {
            m3443(new ArrayList());
            if (this.f2663 != null) {
                this.f2663.ˎ(this);
                this.f2661 = true;
                a.ˏ(3, "BaseTracker", this, "Impression started.");
                return;
            }
            a.ˏ(3, "BaseTracker", this, "Bridge is null, won't start tracking");
            throw new o("Bridge is null");
        }
    }

    final void m3447(WebView webView) throws o {
        if (webView != null) {
            this.f2664 = new WeakReference(webView);
            if (this.f2663 == null) {
                Object obj;
                if (this.f2658 || this.f2659) {
                    obj = 1;
                } else {
                    obj = null;
                }
                if (obj == null) {
                    a.ˏ(3, "BaseTracker", this, "Attempting bridge installation.");
                    if (this.f2664.get() != null) {
                        this.f2663 = new j((WebView) this.f2664.get(), e.ˏ);
                        a.ˏ(3, "BaseTracker", this, "Bridge installed.");
                    } else {
                        this.f2663 = null;
                        a.ˏ(3, "BaseTracker", this, "Bridge not installed, WebView is null.");
                    }
                }
            }
            if (this.f2663 != null) {
                this.f2663.ˊ(this);
            }
        }
    }

    final void m3444() throws o {
        if (this.f2666 != null) {
            throw new o("Tracker initialization failed: " + this.f2666.getMessage());
        }
    }

    final boolean m3441() {
        return this.f2661 && !this.f2665;
    }

    final View m3439() {
        return (View) this.f2657.get();
    }

    final String m3440() {
        this.f2667.ˋ(this.f2662, (View) this.f2657.get());
        return this.f2667.ॱ;
    }

    final void m3448(String str, Exception exception) {
        try {
            o.ˎ(exception);
            String ˎ = o.ˎ(str, exception);
            if (this.f2660 != null) {
                this.f2660.onTrackingFailedToStart(ˎ);
            }
            a.ˏ(3, "BaseTracker", this, ˎ);
            a.ˊ("[ERROR] ", m3442() + " " + ˎ);
        } catch (Exception e) {
        }
    }

    final void m3446() throws o {
        if (this.f2661) {
            throw new o("Tracker already started");
        } else if (this.f2665) {
            throw new o("Tracker already stopped");
        }
    }

    @CallSuper
    void m3443(List<String> list) throws o {
        if (((View) this.f2657.get()) == null && !this.f2659) {
            list.add("Tracker's target view is null");
        }
        if (!list.isEmpty()) {
            throw new o(TextUtils.join(" and ", list));
        }
    }

    final String m3438() {
        return a.ˏ((View) this.f2657.get());
    }
}
