package com.appnext.core.webview;

import android.content.Context;
import android.webkit.JavascriptInterface;
import com.appnext.core.C1128g;

public class WebAppInterface {
    Context context;

    public WebAppInterface(Context context) {
        this.context = context;
    }

    @JavascriptInterface
    public int getAdCount() {
        C1128g.m2333W("getAdCount");
        return 0;
    }

    @JavascriptInterface
    public String getAdAt(int i) {
        C1128g.m2333W("getAdAt " + i);
        return "";
    }

    @JavascriptInterface
    public String init() {
        C1128g.m2333W("init");
        return "";
    }

    @JavascriptInterface
    public void destroy() {
        C1128g.m2333W("destroy");
    }

    @JavascriptInterface
    public void destroy(String str) {
        C1128g.m2333W("destroy with error code: " + str);
    }

    @JavascriptInterface
    public void postView(String str) {
        C1128g.m2333W("postView: " + str);
    }

    @JavascriptInterface
    public void openStore(String str) {
        C1128g.m2333W("openStore: " + str);
    }

    @JavascriptInterface
    public void play() {
        C1128g.m2333W("play");
    }

    @JavascriptInterface
    public String filterAds(String str) {
        C1128g.m2333W("filterAds: " + str);
        return str;
    }

    @JavascriptInterface
    public String loadAds() {
        C1128g.m2333W("loadAds");
        return "";
    }

    @JavascriptInterface
    public void videoPlayed() {
        C1128g.m2333W("videoPlayed");
    }

    @JavascriptInterface
    public void openLink(String str) {
        C1128g.m2333W("openLink " + str);
    }

    @JavascriptInterface
    public void gotoAppWall() {
        C1128g.m2333W("gotoAppWall");
    }

    @JavascriptInterface
    public void notifyImpression(String str) {
        C1128g.m2333W("notifyImpression");
    }

    @JavascriptInterface
    public void jsError(String str) {
        C1128g.m2333W("jsError " + str);
    }
}
