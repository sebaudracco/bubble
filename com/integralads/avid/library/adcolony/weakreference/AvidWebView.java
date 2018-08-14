package com.integralads.avid.library.adcolony.weakreference;

import android.webkit.WebView;
import com.integralads.avid.library.adcolony.utils.AvidCommand;

public class AvidWebView extends AvidView<WebView> {
    public AvidWebView(WebView r) {
        super(r);
    }

    public void injectJavaScript(String javaScript) {
        injectFormattedJavaScript(AvidCommand.formatJavaScript(javaScript));
    }

    public void injectFormattedJavaScript(String javaScript) {
        WebView webView = (WebView) get();
        if (webView != null) {
            webView.loadUrl(javaScript);
        }
    }
}
