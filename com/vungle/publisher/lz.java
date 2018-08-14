package com.vungle.publisher;

import android.webkit.WebView;
import com.github.lzyzsd.jsbridge.BridgeUtil;
import com.vungle.publisher.log.Logger;
import java.util.Locale;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
/* compiled from: vungle */
public class lz extends ml {
    @Inject
    qg f3095a;
    @Inject
    mc f3096b;

    public void onReceivedError(WebView v, int code, String desc, String url) {
        Logger.e(Logger.AD_TAG, "failed to load web view: code " + code + ", " + desc);
        this.f3095a.m4568a(new bg());
    }

    public void onPageFinished(WebView webView, String url) {
        Logger.v(Logger.AD_TAG, "webview finished loading. appending config string");
        if (!url.toLowerCase(Locale.US).startsWith(BridgeUtil.JAVASCRIPT_STR)) {
            StringBuilder stringBuilder = new StringBuilder("javascript:function actionClicked(m,p){ var q = prompt('vungle:'+JSON.stringify({method:m,params:(p?p:null)}));if(q&&typeof q === 'string'){return JSON.parse(q).result;}};function noTapHighlight(){var l=document.getElementsByTagName('*');for(var i=0; i<l.length; i++){l[i].style.webkitTapHighlightColor='rgba(0,0,0,0)';}};noTapHighlight();");
            try {
                stringBuilder.append("if (typeof vungleInit == 'function') {vungleInit($webviewConfig$);};".replace("$webviewConfig$", this.f3096b.e()));
            } catch (Throwable e) {
                Logger.e(Logger.AD_TAG, "webview failed to load config object", e);
            }
            String stringBuilder2 = stringBuilder.toString();
            Logger.v(Logger.AD_TAG, "webview client injecting javascript: " + stringBuilder2);
            webView.loadUrl(stringBuilder2);
            super.onPageFinished(webView, url);
        }
    }
}
