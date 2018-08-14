package com.elephant.data.p037d;

import android.graphics.Bitmap;
import android.text.TextUtils;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.elephant.data.p048g.C1814b;

final class C1770i extends WebViewClient {
    private WebView f3701a;
    private C1766h f3702b;
    private C1772k f3703c;
    private String f3704d = null;
    private int f3705e = 0;
    private boolean f3706f = false;
    private Runnable f3707g = new C1771j(this);

    public C1770i(C1766h c1766h, C1772k c1772k) {
        this.f3702b = c1766h;
        this.f3703c = c1772k;
    }

    private void m5116a() {
        if (this.f3703c != null) {
            this.f3703c.mo3537a();
        }
    }

    private void m5118a(String str, String str2, String str3) {
        if (!this.f3706f) {
            this.f3706f = true;
            if (this.f3702b != null) {
                this.f3702b.mo3536d();
            }
            m5116a();
        }
    }

    public final void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        try {
            this.f3701a = webView;
            if (TextUtils.isEmpty(this.f3704d)) {
                this.f3704d = str;
            }
            this.f3705e++;
            if (this.f3705e > 30) {
                webView.stopLoading();
                C1768f.f3697c.removeCallbacks(this.f3707g);
                m5118a(this.f3704d, str, C1814b.hG);
                return;
            }
            C1768f.f3697c.removeCallbacks(this.f3707g);
            C1768f.f3697c.postDelayed(this.f3707g, 5000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public final void onReceivedError(WebView webView, int i, String str, String str2) {
        webView.stopLoading();
        m5118a(this.f3704d, str2, C1814b.hI + str);
    }

    public final boolean shouldOverrideUrlLoading(WebView webView, String str) {
        if (str == null) {
            try {
                C1768f.f3697c.removeCallbacks(this.f3707g);
                webView.stopLoading();
                m5118a(this.f3704d, "", C1814b.hH);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        boolean z = (str == null || str.trim().length() == 0) ? false : str.startsWith(C1814b.hJ) || str.startsWith(C1814b.hK) || str.startsWith(C1814b.hL) || str.startsWith(C1814b.hM) || str.startsWith(C1814b.hN);
        if (z) {
            C1768f.f3697c.removeCallbacks(this.f3707g);
            webView.stopLoading();
            String str2 = this.f3704d;
            if (this.f3706f) {
                return true;
            }
            this.f3706f = true;
            if (this.f3702b != null) {
                this.f3702b.mo3535a(str);
            }
            m5116a();
            return true;
        }
        webView.loadUrl(str);
        C1768f.f3697c.removeCallbacks(this.f3707g);
        C1768f.f3697c.postDelayed(this.f3707g, 5000);
        return true;
    }
}
