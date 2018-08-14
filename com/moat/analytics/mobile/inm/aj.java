package com.moat.analytics.mobile.inm;

import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

public class aj extends MoatFactory {
    public <T> T createCustomTracker(MoatPlugin<T> moatPlugin) {
        return null;
    }

    public NativeDisplayTracker createNativeDisplayTracker(View view, String str) {
        return new ak();
    }

    public NativeVideoTracker createNativeVideoTracker(String str) {
        return new al();
    }

    public WebAdTracker createWebAdTracker(ViewGroup viewGroup) {
        return new an();
    }

    public WebAdTracker createWebAdTracker(WebView webView) {
        return new an();
    }

    public WebAdTracker createWebDisplayTracker(ViewGroup viewGroup) {
        return new an();
    }

    public WebAdTracker createWebDisplayTracker(WebView webView) {
        return new an();
    }
}
