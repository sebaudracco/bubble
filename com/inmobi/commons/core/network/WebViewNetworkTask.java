package com.inmobi.commons.core.network;

import android.annotation.SuppressLint;
import android.content.Context;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.commons.p112a.C3105a;

public class WebViewNetworkTask {
    private static final String f7741a = WebViewNetworkTask.class.getSimpleName();
    private NetworkRequest f7742b;
    private WebViewClient f7743c;
    private NetworkTaskWebView f7744d;

    public class NetworkTaskWebView extends WebView {
        public boolean f7739a;
        final /* synthetic */ WebViewNetworkTask f7740b;

        public NetworkTaskWebView(WebViewNetworkTask webViewNetworkTask, Context context) {
            this.f7740b = webViewNetworkTask;
            super(context);
        }

        public void destroy() {
            this.f7739a = true;
            super.destroy();
        }
    }

    public WebViewNetworkTask(NetworkRequest networkRequest, WebViewClient webViewClient) {
        this.f7743c = webViewClient;
        this.f7742b = networkRequest;
    }

    public void m10335a() {
        try {
            m10334b();
            this.f7744d.loadUrl(this.f7742b.m9763k(), this.f7742b.m9762j());
        } catch (Exception e) {
            Logger.m10359a(InternalLogLevel.INTERNAL, f7741a, "SDK encountered unexpected error in WebViewNetworkTask.execute() method; " + e.getMessage());
        }
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    private void m10334b() {
        this.f7744d = new NetworkTaskWebView(this, C3105a.m10078b());
        this.f7744d.setWebViewClient(this.f7743c);
        this.f7744d.getSettings().setJavaScriptEnabled(true);
        this.f7744d.getSettings().setCacheMode(2);
    }
}
