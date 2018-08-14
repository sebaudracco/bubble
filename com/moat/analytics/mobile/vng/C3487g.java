package com.moat.analytics.mobile.vng;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build.VERSION;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.facebook.ads.AudienceNetworkActivity;
import com.moat.analytics.mobile.vng.C3498j.C3497a;
import java.util.Locale;
import java.util.Map;
import org.json.JSONObject;

class C3487g {
    WebView f8891a;
    C3498j f8892b;
    final String f8893c = String.format(Locale.ROOT, "_moatTracker%d", new Object[]{Integer.valueOf((int) (Math.random() * 1.0E8d))});
    private final C3486a f8894d;
    private boolean f8895e = false;

    class C34841 extends WebViewClient {
        final /* synthetic */ C3487g f8886a;

        C34841(C3487g c3487g) {
            this.f8886a = c3487g;
        }

        public void onPageFinished(WebView webView, String str) {
            if (!this.f8886a.f8895e) {
                try {
                    this.f8886a.f8895e = true;
                    this.f8886a.f8892b.m11918a();
                } catch (Exception e) {
                    C3502m.m11942a(e);
                }
            }
        }
    }

    class C34852 extends WebViewClient {
        final /* synthetic */ C3487g f8887a;

        C34852(C3487g c3487g) {
            this.f8887a = c3487g;
        }

        public void onPageFinished(WebView webView, String str) {
            if (!this.f8887a.f8895e) {
                try {
                    this.f8887a.f8895e = true;
                    this.f8887a.f8892b.m11918a();
                    this.f8887a.f8892b.m11924c(this.f8887a.f8893c);
                } catch (Exception e) {
                    C3502m.m11942a(e);
                }
            }
        }
    }

    enum C3486a {
        DISPLAY,
        VIDEO
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    C3487g(Context context, C3486a c3486a) {
        this.f8894d = c3486a;
        this.f8891a = new WebView(context);
        WebSettings settings = this.f8891a.getSettings();
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
        C3497a c3497a = C3497a.NATIVE_DISPLAY;
        if (c3486a == C3486a.VIDEO) {
            c3497a = C3497a.NATIVE_VIDEO;
        }
        this.f8892b = new C3498j(this.f8891a, c3497a);
    }

    private static String m11871a(String str, String str2, Integer num, Integer num2, JSONObject jSONObject, Integer num3) {
        return String.format(Locale.ROOT, "<html><head></head><body><div id=\"%s\" style=\"width: %dpx; height: %dpx;\"></div><script>(function initMoatTracking(apiname, pcode, ids, duration) {var events = [];window[pcode + '_moatElToTrack'] = document.getElementById('%s');var moatapi = {'dropTime':%d,'adData': {'ids': ids, 'duration': duration, 'url': 'n/a'},'dispatchEvent': function(ev) {if (this.sendEvent) {if (events) { events.push(ev); ev = events; events = false; }this.sendEvent(ev);} else {events.push(ev);}},'dispatchMany': function(evs){for (var i=0, l=evs.length; i<l; i++) {this.dispatchEvent(evs[i]);}}};Object.defineProperty(window, apiname, {'value': moatapi});var s = document.createElement('script');s.src = 'https://z.moatads.com/' + pcode + '/moatvideo.js?' + apiname + '#' + apiname;document.body.appendChild(s);})('%s', '%s', %s, %s);</script></body></html>", new Object[]{"mianahwvc", num, num2, "mianahwvc", Long.valueOf(System.currentTimeMillis()), str, str2, jSONObject.toString(), num3});
    }

    private static String m11874b(String str) {
        return "<!DOCTYPE html>\n<html>\n<head lang=\"en\">\n   <meta charset=\"UTF-8\">\n   <title></title>\n</head>\n<body style=\"margin:0;padding:0;\">\n    <script src=\"https://z.moatads.com/" + str + "/moatad.js\" type=\"text/javascript\"></script>\n" + "</body>\n" + "</html>";
    }

    void m11875a() {
        this.f8892b = null;
        this.f8891a.destroy();
        this.f8891a = null;
    }

    void m11876a(String str) {
        if (this.f8894d == C3486a.DISPLAY) {
            this.f8891a.setWebViewClient(new C34841(this));
            this.f8891a.loadData(C3487g.m11874b(str), AudienceNetworkActivity.WEBVIEW_MIME_TYPE, AudienceNetworkActivity.WEBVIEW_ENCODING);
        }
    }

    void m11877a(String str, Map<String, String> map, Integer num, Integer num2, Integer num3) {
        if (this.f8894d == C3486a.VIDEO) {
            this.f8891a.setWebViewClient(new C34852(this));
            this.f8891a.loadData(C3487g.m11871a(this.f8893c, str, num, num2, new JSONObject(map), num3), AudienceNetworkActivity.WEBVIEW_MIME_TYPE, null);
        }
    }
}
