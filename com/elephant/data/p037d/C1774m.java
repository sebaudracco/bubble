package com.elephant.data.p037d;

import android.webkit.WebSettings;
import android.webkit.WebView;
import com.elephant.data.p048g.C1814b;

final class C1774m implements Runnable {
    final /* synthetic */ C1773l f3713a;
    private /* synthetic */ C1769g f3714b;

    C1774m(C1773l c1773l, C1769g c1769g) {
        this.f3713a = c1773l;
        this.f3714b = c1769g;
    }

    public final void run() {
        try {
            if (this.f3713a.f3711c == null) {
                this.f3713a.f3711c = new WebView(this.f3713a.f3710b);
                WebSettings settings = this.f3713a.f3711c.getSettings();
                settings.setAllowContentAccess(true);
                settings.setJavaScriptEnabled(true);
                settings.setUserAgentString(System.getProperty(C1814b.hB));
                this.f3713a.f3711c.removeJavascriptInterface(C1814b.hC);
                this.f3713a.f3711c.removeJavascriptInterface(C1814b.hD);
                this.f3713a.f3711c.removeJavascriptInterface(C1814b.hE);
            }
            this.f3713a.f3711c.stopLoading();
            this.f3713a.f3711c.setWebViewClient(new C1770i(this.f3714b.f3700b, new C1775n(this)));
            this.f3713a.f3711c.setWebChromeClient(new C1776o(this));
            this.f3713a.f3711c.loadUrl(this.f3714b.f3699a);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
