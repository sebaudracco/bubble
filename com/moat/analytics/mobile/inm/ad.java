package com.moat.analytics.mobile.inm;

import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.moat.analytics.mobile.inm.base.exception.C3376a;

class ad extends WebViewClient {
    final /* synthetic */ ac f8513a;

    ad(ac acVar) {
        this.f8513a = acVar;
    }

    public void onPageFinished(WebView webView, String str) {
        if (!this.f8513a.f8510i) {
            try {
                this.f8513a.f8510i = true;
                this.f8513a.f8504c = new bd((View) this.f8513a.f8508g.get(), this.f8513a.f8509h, true, this.f8513a.f8502a, this.f8513a.f8503b);
                this.f8513a.f8504c.mo6493c();
                this.f8513a.m11473a();
            } catch (Exception e) {
                C3376a.m11557a(e);
            }
        }
    }
}
