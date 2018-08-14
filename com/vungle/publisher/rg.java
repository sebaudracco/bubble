package com.vungle.publisher;

import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build.VERSION;
import android.support.v4.app.NotificationCompat;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import com.google.firebase.analytics.FirebaseAnalytics$Param;
import com.mopub.common.AdType;
import com.vungle.publisher.env.i;
import com.vungle.publisher.iz.C1641a;
import com.vungle.publisher.iz.b;
import com.vungle.publisher.jn.C1642a;
import com.vungle.publisher.log.Logger;
import java.util.HashMap;
import java.util.Map;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

/* compiled from: vungle */
public class rg extends ml {
    b f3297a;
    @Inject
    qg f3298b;
    @Inject
    rr f3299c;
    @Inject
    rv$a f3300d;
    @Inject
    bz f3301e;
    @Inject
    i f3302f;
    @Inject
    C1642a f3303g;
    private p f3304h;
    private boolean f3305i = false;
    private boolean f3306j = true;
    private boolean f3307k = false;
    private boolean f3308l = false;
    private x f3309m;
    private String f3310n;

    @Singleton
    /* compiled from: vungle */
    public static class C1670a {
        @Inject
        Provider<rg> f3295a;
        @Inject
        C1641a f3296b;

        @Inject
        C1670a() {
        }

        public rg m4605a(String str, p pVar, boolean z, x xVar) {
            rg rgVar = (rg) this.f3295a.get();
            rgVar.f3309m = xVar;
            rgVar.f3297a = this.f3296b.m4215b(str);
            rgVar.f3304h = pVar;
            rgVar.f3305i = z;
            rgVar.f3310n = str;
            return rgVar;
        }
    }

    @Inject
    rg() {
    }

    public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
        Logger.e(Logger.AD_TAG, "received error in WebViewClient: " + description, new RuntimeException());
        this.f3298b.m4568a(new sm("100"));
        super.onReceivedError(view, errorCode, description, failingUrl);
    }

    public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
        Logger.e(Logger.AD_TAG, "received ssl error: " + error.getPrimaryError());
        this.f3298b.m4568a(new sm("101"));
        super.onReceivedSslError(view, handler, error);
    }

    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        Logger.v(Logger.AD_TAG, "mraid page started loading: " + url);
    }

    public void onPageFinished(WebView webView, String url) {
        Logger.v(Logger.AD_TAG, "mraid page finished loading: " + url);
        if (this.f3306j) {
            Logger.d(Logger.AD_TAG, "mraid webview finished loading");
            this.f3299c.m4623a(webView, this.f3304h, this.f3305i, this.f3309m);
        } else {
            if (this.f3308l && !"about:blank".equalsIgnoreCase(url)) {
                Logger.v(Logger.AD_TAG, "clear history");
                this.f3308l = false;
                webView.clearHistory();
            }
            this.f3298b.m4568a(new st(ro.b.a));
            this.f3298b.m4568a(new sr(ry.c, true));
        }
        super.onPageFinished(webView, url);
    }

    public boolean shouldOverrideUrlLoading(WebView webView, String url) {
        Logger.v(Logger.AD_TAG, "mraid attempted to navigate to url: " + url);
        try {
            Uri parse = Uri.parse(url);
            String scheme = parse.getScheme();
            String host = parse.getHost();
            if (!AdType.MRAID.equals(scheme)) {
                return m4616a(parse);
            }
            try {
                Map hashMap = new HashMap();
                for (String scheme2 : zk.a(parse)) {
                    hashMap.put(scheme2, parse.getQueryParameter(scheme2));
                }
                m4613a(webView, this.f3300d.m4631a(host), hashMap);
            } catch (Throwable e) {
                Logger.e(Logger.AD_TAG, "exception while overriding mraid url", e);
                this.f3298b.m4568a(new sn());
            }
            return true;
        } catch (Throwable e2) {
            Logger.e(Logger.AD_TAG, "Invalid URL: " + url, e2);
            this.f3298b.m4568a(new sn());
            return true;
        }
    }

    void m4612a(WebView webView, Uri uri) {
        if (!m4616a(uri)) {
            webView.loadUrl("about:blank");
            webView.setBackgroundColor(-16777216);
            this.f3306j = false;
            this.f3308l = true;
            webView.loadUrl(uri.toString());
            webView.clearHistory();
            this.f3298b.m4568a(new sp());
        }
    }

    boolean m4616a(Uri uri) {
        jn jnVar = (jn) this.f3303g.m3526a((Object) this.f3310n, false);
        if (ru.a.contains(uri.getScheme()) && ((jnVar == null || !jnVar.m4255w()) && VERSION.SDK_INT < 26)) {
            return false;
        }
        Logger.d(Logger.AD_TAG, "navigating to external location: " + uri.toString());
        this.f3298b.m4568a(new so(uri));
        return true;
    }

    void m4611a(WebView webView) {
        if (!this.f3307k) {
            try {
                this.f3299c.m4622a(webView, this.f3297a);
            } catch (Throwable e) {
                Logger.e(Logger.AD_TAG, "failed to inject JSON tokens", e);
            }
            this.f3307k = true;
            this.f3298b.m4568a(new sq());
        }
    }

    void m4613a(WebView webView, rv rvVar, Map<String, String> map) {
        Logger.d(Logger.AD_TAG, "received MRAID event from js: " + rvVar);
        switch (1.a[rvVar.ordinal()]) {
            case 1:
                this.f3298b.m4568a(new sj());
                break;
            case 2:
                m4611a(webView);
                return;
            case 3:
                this.f3298b.m4568a(new st(ro.b.valueOf((String) map.get("sdkCloseButton"))));
                break;
            case 4:
                this.f3298b.m4568a(new su(zk.c((String) map.get("useCustomPrivacy"))));
                break;
            case 5:
                m4612a(webView, Uri.parse((String) map.get("url")));
                break;
            case 6:
                this.f3298b.m4568a(new aq((String) map.get("url")));
                break;
            case 7:
                boolean c = zk.c((String) map.get("allowOrientationChange"));
                this.f3298b.m4568a(new sr(zk.e((String) map.get("forceOrientation")), c));
                break;
            case 8:
                this.f3298b.m4568a(new sg(m4606a((Map) map)));
                break;
            case 9:
            case 10:
                this.f3298b.m4568a(new si(m4606a((Map) map), (String) map.get(FirebaseAnalytics$Param.VALUE)));
                break;
            case 11:
                this.f3298b.m4568a(new sm((String) map.get("code")));
                break;
            case 12:
                this.f3298b.m4568a(new ax());
                break;
            case 13:
                break;
            case 14:
                try {
                    this.f3301e.m3483a(rh.m4618a(this, webView, ut.a((String) map.get("selector"))), 500);
                    break;
                } catch (Throwable e) {
                    Logger.e(Logger.AD_TAG, "invalid mraid video selector: " + ((String) map.get("selector")), e);
                    this.f3298b.m4568a(new sn());
                    break;
                }
            default:
                Logger.w(Logger.AD_TAG, "Unknown MRAID Javascript command: " + rvVar);
                break;
        }
        this.f3299c.m4630c(webView);
    }

    /* synthetic */ void m4614a(WebView webView, String str) {
        this.f3299c.m4624a(webView, str);
    }

    public boolean m4615a() {
        return this.f3306j;
    }

    public boolean m4617b() {
        return this.f3308l;
    }

    private hf m4606a(Map<String, String> map) {
        return new hf((String) map.get(NotificationCompat.CATEGORY_EVENT));
    }
}
