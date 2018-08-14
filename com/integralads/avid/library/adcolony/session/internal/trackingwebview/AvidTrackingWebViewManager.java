package com.integralads.avid.library.adcolony.session.internal.trackingwebview;

import android.webkit.WebView;
import com.integralads.avid.library.adcolony.session.internal.trackingwebview.AvidWebViewClient.AvidWebViewClientListener;
import com.integralads.avid.library.adcolony.weakreference.AvidWebView;
import java.util.ArrayList;
import java.util.Iterator;

public class AvidTrackingWebViewManager implements AvidJavaScriptResourceInjector, AvidWebViewClientListener {
    static final int f8385a = 0;
    static final int f8386b = 1;
    static final int f8387c = 2;
    private static final String f8388d = "<html><body></body></html>";
    private static final String f8389e = "text/html";
    private static final String f8390f = "(function () {\nvar script=document.createElement('script');script.setAttribute(\"type\",\"text/javascript\");script.setAttribute(\"src\",\"%SCRIPT_SRC%\");document.body.appendChild(script);\n})();";
    private static final String f8391g = "%SCRIPT_SRC%";
    private final AvidWebView f8392h;
    private final AvidWebViewClient f8393i;
    private int f8394j = 0;
    private final ArrayList<String> f8395k = new ArrayList();

    public AvidTrackingWebViewManager(WebView webView) {
        this.f8392h = new AvidWebView(webView);
        webView.getSettings().setJavaScriptEnabled(true);
        this.f8393i = new AvidWebViewClient();
        this.f8393i.setListener(this);
        webView.setWebViewClient(this.f8393i);
    }

    public void loadHTML() {
        WebView webView = (WebView) this.f8392h.get();
        if (webView != null && this.f8394j == 0) {
            this.f8394j = 1;
            webView.loadData(f8388d, "text/html", null);
        }
    }

    public void injectJavaScriptResource(String javaScriptResource) {
        if (this.f8394j == 2) {
            m11153a(javaScriptResource);
        } else {
            this.f8395k.add(javaScriptResource);
        }
    }

    public void webViewDidLoadData() {
        this.f8394j = 2;
        Iterator it = this.f8395k.iterator();
        while (it.hasNext()) {
            m11153a((String) it.next());
        }
        this.f8395k.clear();
    }

    private void m11153a(String str) {
        this.f8392h.injectJavaScript(f8390f.replace(f8391g, str));
    }
}
