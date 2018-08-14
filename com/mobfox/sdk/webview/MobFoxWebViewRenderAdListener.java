package com.mobfox.sdk.webview;

public interface MobFoxWebViewRenderAdListener {
    void onAdClick(MobFoxWebView mobFoxWebView, String str);

    void onAdClosed(MobFoxWebView mobFoxWebView);

    void onAutoRedirect(MobFoxWebView mobFoxWebView, String str);

    void onError(MobFoxWebView mobFoxWebView, Exception exception);

    void onRendered(MobFoxWebView mobFoxWebView, String str);

    void onVideoAdFinished(MobFoxWebView mobFoxWebView);
}
