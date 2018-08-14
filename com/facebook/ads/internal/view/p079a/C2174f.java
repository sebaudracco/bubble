package com.facebook.ads.internal.view.p079a;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.ConsoleMessage;
import android.webkit.ConsoleMessage.MessageLevel;
import android.webkit.WebBackForwardList;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.facebook.ads.internal.p056q.p076c.C2141a;
import com.facebook.ads.internal.p056q.p076c.C2142b;
import com.github.lzyzsd.jsbridge.BridgeUtil;
import java.util.HashSet;
import java.util.Set;

@TargetApi(19)
public class C2174f extends C2141a {
    private static final String f5235a = C2174f.class.getSimpleName();
    private static final Set<String> f5236b = new HashSet(2);
    private C2173a f5237c;
    private C2169d f5238d;
    private long f5239e = -1;
    private long f5240f = -1;
    private long f5241g = -1;
    private long f5242h = -1;

    class C21711 extends WebChromeClient {
        final /* synthetic */ C2174f f5233a;

        C21711(C2174f c2174f) {
            this.f5233a = c2174f;
        }

        public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
            String message = consoleMessage.message();
            if (!TextUtils.isEmpty(message) && consoleMessage.messageLevel() == MessageLevel.LOG) {
                this.f5233a.f5238d.m6951a(message);
            }
            return true;
        }

        public void onProgressChanged(WebView webView, int i) {
            super.onProgressChanged(webView, i);
            this.f5233a.f5238d.m6950a();
            if (this.f5233a.f5237c != null) {
                this.f5233a.f5237c.mo3766a(i);
            }
        }

        public void onReceivedTitle(WebView webView, String str) {
            super.onReceivedTitle(webView, str);
            if (this.f5233a.f5237c != null) {
                this.f5233a.f5237c.mo3768b(str);
            }
        }
    }

    class C21722 extends WebViewClient {
        final /* synthetic */ C2174f f5234a;

        C21722(C2174f c2174f) {
            this.f5234a = c2174f;
        }

        public void onPageFinished(WebView webView, String str) {
            super.onPageFinished(webView, str);
            if (this.f5234a.f5237c != null) {
                this.f5234a.f5237c.mo3769c(str);
            }
        }

        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            super.onPageStarted(webView, str, bitmap);
            if (this.f5234a.f5237c != null) {
                this.f5234a.f5237c.mo3767a(str);
            }
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            Uri parse = Uri.parse(str);
            if (!C2174f.f5236b.contains(parse.getScheme())) {
                try {
                    this.f5234a.getContext().startActivity(new Intent("android.intent.action.VIEW", parse));
                    return true;
                } catch (Throwable e) {
                    Log.w(C2174f.f5235a, "Activity not found to handle URI.", e);
                } catch (Throwable e2) {
                    Log.e(C2174f.f5235a, "Unknown exception occurred when trying to handle URI.", e2);
                }
            }
            return false;
        }
    }

    public interface C2173a {
        void mo3766a(int i);

        void mo3767a(String str);

        void mo3768b(String str);

        void mo3769c(String str);
    }

    static {
        f5236b.add("http");
        f5236b.add("https");
    }

    public C2174f(Context context) {
        super(context);
        m6962f();
    }

    private void m6962f() {
        WebSettings settings = getSettings();
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);
        settings.setDisplayZoomControls(false);
        settings.setLoadWithOverviewMode(true);
        settings.setUseWideViewPort(true);
        settings.setAllowContentAccess(false);
        settings.setAllowFileAccessFromFileURLs(false);
        settings.setAllowUniversalAccessFromFileURLs(false);
        settings.setAllowFileAccess(false);
        this.f5238d = new C2169d(this);
    }

    private void m6963g() {
        if (this.f5240f > -1 && this.f5241g > -1 && this.f5242h > -1) {
            this.f5238d.m6952a(false);
        }
    }

    protected WebChromeClient mo3762a() {
        return new C21711(this);
    }

    public void m6965a(long j) {
        if (this.f5239e < 0) {
            this.f5239e = j;
        }
    }

    public void m6966a(String str) {
        try {
            evaluateJavascript(str, null);
        } catch (IllegalStateException e) {
            loadUrl(BridgeUtil.JAVASCRIPT_STR + str);
        }
    }

    protected WebViewClient mo3763b() {
        return new C21722(this);
    }

    public void m6968b(long j) {
        if (this.f5240f < 0) {
            this.f5240f = j;
        }
        m6963g();
    }

    public void m6969c(long j) {
        if (this.f5242h < 0) {
            this.f5242h = j;
        }
        m6963g();
    }

    public void destroy() {
        C2142b.m6858a(this);
        super.destroy();
    }

    public long getDomContentLoadedMs() {
        return this.f5240f;
    }

    public String getFirstUrl() {
        WebBackForwardList copyBackForwardList = copyBackForwardList();
        return copyBackForwardList.getSize() > 0 ? copyBackForwardList.getItemAtIndex(0).getUrl() : getUrl();
    }

    public long getLoadFinishMs() {
        return this.f5242h;
    }

    public long getResponseEndMs() {
        return this.f5239e;
    }

    public long getScrollReadyMs() {
        return this.f5241g;
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.f5241g < 0 && computeVerticalScrollRange() > getHeight()) {
            this.f5241g = System.currentTimeMillis();
            m6963g();
        }
    }

    public void setListener(C2173a c2173a) {
        this.f5237c = c2173a;
    }
}
