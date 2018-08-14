package com.moat.analytics.mobile.inm;

import android.util.Log;
import android.webkit.WebView;
import com.moat.analytics.mobile.inm.base.exception.C3376a;
import com.moat.analytics.mobile.inm.base.functional.C3378a;

class be implements WebAdTracker {
    private final C3378a<? extends bc> f8584a;
    private final ao f8585b;

    be(WebView webView, C3371a c3371a, ao aoVar) {
        this.f8585b = aoVar;
        if (aoVar.mo6482b()) {
            Log.d("MoatWebAdTracker", "In initialization method.");
        }
        if (webView == null) {
            if (aoVar.mo6482b()) {
                Log.e("MoatWebAdTracker", "WebView is null. Will not track.");
            }
            this.f8584a = C3378a.m11558a();
            return;
        }
        this.f8584a = C3378a.m11559a(new bd(webView, webView, false, c3371a, aoVar));
    }

    public boolean track() {
        boolean c;
        boolean b = this.f8585b.mo6482b();
        boolean z = false;
        if (b) {
            try {
                Log.d("MoatWebAdTracker", "In track method.");
            } catch (Exception e) {
                C3376a.m11557a(e);
            }
        }
        if (this.f8584a.m11563c()) {
            c = ((bc) this.f8584a.m11561b()).mo6493c();
        } else if (b) {
            Log.e("MoatWebAdTracker", "Internal tracker not available. Not tracking.");
            c = false;
        } else {
            c = false;
        }
        z = c;
        if (b) {
            Log.d("MoatWebAdTracker", "Attempt to start tracking ad was " + (z ? "" : "un") + "successful.");
        }
        return z;
    }
}
