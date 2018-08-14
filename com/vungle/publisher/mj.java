package com.vungle.publisher;

import android.content.Context;
import android.content.res.Configuration;
import android.webkit.WebView;
import com.facebook.ads.AudienceNetworkActivity;
import com.vungle.publisher.log.Logger;

/* compiled from: vungle */
public abstract class mj extends WebView {
    protected qg f10753a;

    protected mj(Context context) {
        super(context);
    }

    public void m13696a(lf lfVar) {
        if (lfVar.m13613a()) {
            Logger.m13644v(Logger.AD_TAG, "loading webview with url: " + lfVar.m13614b());
            loadUrl(lfVar.m13614b());
        } else if (lfVar.m13615c()) {
            Logger.m13644v(Logger.AD_TAG, "loading webview with content: " + lfVar.m13616d());
            loadDataWithBaseURL("http://lol.vungle.com/", lfVar.m13616d(), AudienceNetworkActivity.WEBVIEW_MIME_TYPE, AudienceNetworkActivity.WEBVIEW_ENCODING, null);
        }
    }

    public void onConfigurationChanged(Configuration newConfig) {
    }
}
