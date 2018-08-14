package com.fyber.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.webkit.CookieManager;
import android.webkit.WebSettings;
import android.webkit.WebSettings.PluginState;
import android.webkit.WebView;
import java.io.File;

/* compiled from: WebViewConfigs */
public final class C2686z {
    public static void m8584a(WebSettings webSettings) {
        if (C2665m.m8524b(20)) {
            webSettings.setPluginState(PluginState.ON);
        }
    }

    @TargetApi(21)
    public static void m8585a(WebView webView) {
        if (C2665m.m8523a(21)) {
            CookieManager.getInstance().acceptThirdPartyCookies(webView);
        }
    }

    public static void m8586b(WebView webView) {
        WebSettings settings = webView.getSettings();
        Context context = webView.getContext();
        settings.setDomStorageEnabled(true);
        settings.setAppCacheEnabled(true);
        File databasePath = context.getDatabasePath("webviewCache");
        if (!databasePath.exists()) {
            databasePath.mkdirs();
        }
        settings.setAppCachePath(databasePath.getPath());
        settings.setDatabaseEnabled(true);
        if (C2665m.m8524b(19)) {
            settings.setDatabasePath(databasePath.getPath());
        }
        settings.setJavaScriptEnabled(true);
    }
}
