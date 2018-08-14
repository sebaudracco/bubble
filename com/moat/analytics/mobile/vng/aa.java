package com.moat.analytics.mobile.vng;

import android.support.annotation.Nullable;
import android.webkit.WebView;

class aa extends b implements WebAdTracker {
    aa(@Nullable WebView webView) {
        super(webView, false, false);
        C3511p.m11976a(3, "WebAdTracker", (Object) this, "In initialization method.");
        super.a(webView);
        C3511p.m11978a("[SUCCESS] ", m11854a() + " created for " + e());
    }

    String m11854a() {
        return "WebAdTracker";
    }
}
