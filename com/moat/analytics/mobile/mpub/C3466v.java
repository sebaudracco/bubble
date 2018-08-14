package com.moat.analytics.mobile.mpub;

import android.support.annotation.Nullable;
import android.view.ViewGroup;
import android.webkit.WebView;

final class C3466v extends d implements WebAdTracker {
    C3466v(@Nullable ViewGroup viewGroup) {
        this((WebView) C3468x.m11826(viewGroup, false).orElse(null));
        if (viewGroup == null) {
            String str = "Target ViewGroup is null";
            String str2 = "WebAdTracker initialization not successful, " + str;
            C3412a.m11642(3, "WebAdTracker", this, str2);
            C3412a.m11639("[ERROR] ", str2);
            this.ॱ = new C3442o(str);
        }
        if (this.ˏ == null) {
            str = "No WebView to track inside of ad container";
            str2 = "WebAdTracker initialization not successful, " + str;
            C3412a.m11642(3, "WebAdTracker", this, str2);
            C3412a.m11639("[ERROR] ", str2);
            this.ॱ = new C3442o(str);
        }
    }

    C3466v(@Nullable WebView webView) {
        super(webView, false, false);
        C3412a.m11642(3, "WebAdTracker", this, "Initializing.");
        if (webView == null) {
            String str = "WebView is null";
            String str2 = "WebAdTracker initialization not successful, " + str;
            C3412a.m11642(3, "WebAdTracker", this, str2);
            C3412a.m11639("[ERROR] ", str2);
            this.ॱ = new C3442o(str);
            return;
        }
        try {
            super.ॱ(webView);
            C3412a.m11639("[SUCCESS] ", "WebAdTracker created for " + ʻ());
        } catch (C3442o e) {
            this.ॱ = e;
        }
    }

    final String m11821() {
        return "WebAdTracker";
    }
}
