package com.moat.analytics.mobile.inm;

import android.graphics.Rect;
import android.os.Build.VERSION;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.facebook.ads.AudienceNetworkActivity;
import com.moat.analytics.mobile.inm.base.exception.C3376a;
import java.util.LinkedHashMap;
import java.util.Map;

class ae implements NativeDisplayTracker {
    private WebView f8514a;
    private bc f8515b;
    private final String f8516c;
    private final ao f8517d;
    private boolean f8518e;

    public ae(View view, String str, C3371a c3371a, ao aoVar) {
        if (aoVar.mo6482b()) {
            Log.d("MoatNativeDispTracker", "Initializing.");
        }
        this.f8516c = str;
        this.f8514a = new WebView(view.getContext());
        WebSettings settings = this.f8514a.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setAllowContentAccess(false);
        settings.setAllowFileAccess(false);
        settings.setDatabaseEnabled(false);
        settings.setDomStorageEnabled(false);
        settings.setGeolocationEnabled(false);
        settings.setJavaScriptCanOpenWindowsAutomatically(false);
        settings.setSaveFormData(false);
        if (VERSION.SDK_INT >= 16) {
            settings.setAllowFileAccessFromFileURLs(false);
            settings.setAllowUniversalAccessFromFileURLs(false);
        }
        if (VERSION.SDK_INT >= 21) {
            settings.setMixedContentMode(1);
        }
        this.f8517d = aoVar;
        this.f8515b = new bd(view, this.f8514a, true, c3371a, aoVar);
        this.f8518e = false;
    }

    private static String m11479a(int i, int i2, String str, String str2) {
        return "<!DOCTYPE html>\n<html>\n<head lang=\"en\">\n    <meta charset=\"UTF-8\">\n    <title></title>\n</head>\n<body style=\"margin:0;padding:0;\">\n<div id=\"mianahwvc\" style=\"width:" + i + "px;height:" + i2 + "px;\">\n" + "    <script src=\"https://z.moatads.com/" + str + "/moatad.js#" + str2 + "\" type=\"text/javascript\"></script>\n" + "</div>\n" + "</body>\n" + "</html>";
    }

    private static String m11480a(Map<String, String> map) {
        int i = 0;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (int i2 = 0; i2 < 8; i2++) {
            String str = "moatClientLevel" + i2;
            if (map.containsKey(str)) {
                linkedHashMap.put(str, map.get(str));
            }
        }
        while (i < 8) {
            String str2 = "moatClientSlicer" + i;
            if (map.containsKey(str2)) {
                linkedHashMap.put(str2, map.get(str2));
            }
            i++;
        }
        for (String str3 : map.keySet()) {
            if (!linkedHashMap.containsKey(str3)) {
                linkedHashMap.put(str3, (String) map.get(str3));
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (String str32 : linkedHashMap.keySet()) {
            if (stringBuilder.length() > 0) {
                stringBuilder.append("&");
            }
            stringBuilder.append(str32).append('=').append((String) linkedHashMap.get(str32));
        }
        return "" + stringBuilder;
    }

    private void m11481a() {
        if (!this.f8518e) {
            m11482a("Shutting down.");
            this.f8515b.mo6494d();
            this.f8514a.loadUrl("about:blank");
            this.f8514a.destroy();
            this.f8514a = null;
            this.f8515b = null;
            this.f8518e = true;
        }
    }

    private void m11482a(String str) {
        if (this.f8517d.mo6482b()) {
            Log.d("MoatNativeDispTracker", String.format("id = %s, message = %s", new Object[]{Integer.valueOf(hashCode()), str}));
        }
    }

    public void stopTracking() {
        m11482a("Called stopTracking.");
        m11481a();
    }

    public boolean track(Map<String, String> map) {
        boolean z = false;
        if (map != null) {
            try {
                if (!map.isEmpty()) {
                    z = this.f8515b.mo6493c();
                    if (z) {
                        Rect e = this.f8515b.mo6495e();
                        int width = e.width();
                        int height = e.height();
                        String a = m11480a((Map) map);
                        m11482a("Parsed ad ids = " + a);
                        this.f8514a.loadData(m11479a(width, height, this.f8516c, a), AudienceNetworkActivity.WEBVIEW_MIME_TYPE, AudienceNetworkActivity.WEBVIEW_ENCODING);
                    }
                    m11482a("Attempt to start tracking ad was " + (z ? "" : "un") + "successful.");
                    return z;
                }
            } catch (Exception e2) {
                C3376a.m11557a(e2);
            }
        }
        m11482a("adIdMap is null or empty. Shutting down.");
        m11481a();
        return z;
    }
}
