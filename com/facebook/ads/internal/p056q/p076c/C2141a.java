package com.facebook.ads.internal.p056q.p076c;

import android.content.Context;
import android.os.Build.VERSION;
import android.util.Log;
import android.webkit.CookieManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public abstract class C2141a extends WebView {
    private static final String f5116a = C2141a.class.getSimpleName();
    private boolean f5117b;

    public C2141a(Context context) {
        super(context);
        m6853d();
    }

    private void m6853d() {
        setWebChromeClient(mo3762a());
        setWebViewClient(mo3763b());
        C2142b.m6859b(this);
        getSettings().setJavaScriptEnabled(true);
        getSettings().setDomStorageEnabled(true);
        if (VERSION.SDK_INT >= 17) {
            getSettings().setMediaPlaybackRequiresUserGesture(false);
        }
        setHorizontalScrollBarEnabled(false);
        setHorizontalScrollbarOverlay(false);
        setVerticalScrollBarEnabled(false);
        setVerticalScrollbarOverlay(false);
        if (VERSION.SDK_INT >= 21) {
            try {
                CookieManager.getInstance().setAcceptThirdPartyCookies(this, true);
            } catch (Exception e) {
                Log.w(f5116a, "Failed to initialize CookieManager.");
            }
        }
    }

    protected WebChromeClient mo3762a() {
        return new WebChromeClient();
    }

    protected WebViewClient mo3763b() {
        return new WebViewClient();
    }

    public boolean m6856c() {
        return this.f5117b;
    }

    public void destroy() {
        this.f5117b = true;
        super.destroy();
    }
}
