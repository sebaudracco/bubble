package com.integralads.avid.library.mopub.session.internal.trackingwebview;

import android.webkit.WebView;
import com.integralads.avid.library.mopub.session.internal.trackingwebview.AvidWebViewClient.AvidWebViewClientListener;
import com.integralads.avid.library.mopub.weakreference.AvidWebView;
import java.util.ArrayList;
import java.util.Iterator;

public class AvidTrackingWebViewManager implements AvidWebViewClientListener, AvidJavaScriptResourceInjector {
    private static final String HTML_DATA = "<html><body></body></html>";
    private static final String HTML_ENCODING = "text/html";
    private static final String JAVASCRIPT_RESOURCE = "(function () {\nvar script=document.createElement('script');script.setAttribute(\"type\",\"text/javascript\");script.setAttribute(\"src\",\"%SCRIPT_SRC%\");document.body.appendChild(script);\n})();";
    private static final String SCRIPT_SRC_PLACEHOLDER = "%SCRIPT_SRC%";
    static final int STATE_IDLE = 0;
    static final int STATE_LOADING = 1;
    static final int STATE_READY = 2;
    private final AvidWebView avidWebView;
    private final ArrayList<String> pendingJavaScriptResources = new ArrayList();
    private int state = 0;
    private final AvidWebViewClient webViewClient;

    public AvidTrackingWebViewManager(WebView webView) {
        this.avidWebView = new AvidWebView(webView);
        webView.getSettings().setJavaScriptEnabled(true);
        this.webViewClient = new AvidWebViewClient();
        this.webViewClient.setListener(this);
        webView.setWebViewClient(this.webViewClient);
    }

    public void loadHTML() {
        WebView webView = (WebView) this.avidWebView.get();
        if (webView != null && this.state == 0) {
            this.state = 1;
            webView.loadData(HTML_DATA, "text/html", null);
        }
    }

    public void injectJavaScriptResource(String javaScriptResource) {
        if (this.state == 2) {
            doInjectJavaScriptResource(javaScriptResource);
        } else {
            this.pendingJavaScriptResources.add(javaScriptResource);
        }
    }

    public void webViewDidLoadData() {
        this.state = 2;
        Iterator it = this.pendingJavaScriptResources.iterator();
        while (it.hasNext()) {
            doInjectJavaScriptResource((String) it.next());
        }
        this.pendingJavaScriptResources.clear();
    }

    private void doInjectJavaScriptResource(String javaScriptResource) {
        this.avidWebView.injectJavaScript(JAVASCRIPT_RESOURCE.replace(SCRIPT_SRC_PLACEHOLDER, javaScriptResource));
    }
}
