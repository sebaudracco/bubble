package com.unit.two.p147c;

import android.text.TextUtils;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.unit.two.p151f.C4145l;

final class C4113p extends WebViewClient {
    private /* synthetic */ C4112o f9583a;

    C4113p(C4112o c4112o) {
        this.f9583a = c4112o;
    }

    public final void onPageFinished(WebView webView, String str) {
        super.onPageFinished(webView, str);
        if (this.f9583a.f9582e < this.f9583a.f9581d) {
            if (this.f9583a.f9582e == 0) {
                webView.loadUrl(C4145l.m12823a(C4145l.f9705b));
            }
            this.f9583a.f9582e = this.f9583a.f9582e + 1;
            if (!TextUtils.isEmpty(this.f9583a.f9580c)) {
                webView.loadUrl(this.f9583a.f9580c);
            }
            webView.loadUrl(C4145l.m12823a(C4145l.f9705b));
        }
    }
}
