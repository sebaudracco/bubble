package com.mobfox.sdk.webview;

import org.json.JSONObject;

public interface MobFoxWebViewLoadAdListener {
    void onAdResponse(MobFoxWebView mobFoxWebView, JSONObject jSONObject);

    void onError(MobFoxWebView mobFoxWebView, Exception exception);

    void onNoAd(MobFoxWebView mobFoxWebView);
}
