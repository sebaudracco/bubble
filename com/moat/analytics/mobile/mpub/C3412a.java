package com.moat.analytics.mobile.mpub;

import android.annotation.SuppressLint;
import android.app.Application;
import android.os.Build.VERSION;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.facebook.ads.AudienceNetworkActivity;
import com.moat.analytics.mobile.mpub.C3431j.C3430e;
import com.mopub.mobileads.VastExtensionXmlManager;
import java.util.Locale;
import java.util.Map;
import org.json.JSONObject;

final class C3412a {
    final String f8655;
    WebView f8656;
    private boolean f8657;
    C3431j f8658;
    private final int f8659;

    class C34092 extends WebViewClient {
        private /* synthetic */ C3412a f8651;

        C34092(C3412a c3412a) {
            this.f8651 = c3412a;
        }

        public final void onPageFinished(WebView webView, String str) {
            if (!this.f8651.f8657) {
                try {
                    this.f8651.f8657 = true;
                    this.f8651.f8658.m11725();
                } catch (Exception e) {
                    C3442o.m11756(e);
                }
            }
        }
    }

    class C34105 extends WebViewClient {
        private /* synthetic */ C3412a f8652;

        C34105(C3412a c3412a) {
            this.f8652 = c3412a;
        }

        public final void onPageFinished(WebView webView, String str) {
            if (!this.f8652.f8657) {
                try {
                    this.f8652.f8657 = true;
                    this.f8652.f8658.m11725();
                    this.f8652.f8658.m11724(this.f8652.f8655);
                } catch (Exception e) {
                    C3442o.m11756(e);
                }
            }
        }
    }

    enum C3411d {
        ;
        
        public static final int f8653 = 0;
        public static final int f8654 = 0;

        static {
            f8654 = 1;
            f8653 = 2;
            int[] iArr = new int[]{1, 2};
        }
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    C3412a(Application application, int i) {
        this.f8659 = i;
        this.f8657 = false;
        this.f8655 = String.format(Locale.ROOT, "_moatTracker%d", new Object[]{Integer.valueOf((int) (Math.random() * 1.0E8d))});
        this.f8656 = new WebView(application);
        WebSettings settings = this.f8656.getSettings();
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
        int i2 = C3430e.f8726;
        if (i == C3411d.f8653) {
            i2 = C3430e.f8725;
        }
        try {
            this.f8658 = new C3431j(this.f8656, i2);
        } catch (Exception e) {
            C3442o.m11756(e);
        }
    }

    final void m11646(String str) {
        if (this.f8659 == C3411d.f8654) {
            this.f8656.setWebViewClient(new C34092(this));
            this.f8656.loadData("<!DOCTYPE html>\n<html>\n<head lang=\"en\">\n   <meta charset=\"UTF-8\">\n   <title></title>\n</head>\n<body style=\"margin:0;padding:0;\">\n    <script src=\"https://z.moatads.com/" + str + "/moatad.js\" type=\"text/javascript\"></script>\n</body>\n</html>", AudienceNetworkActivity.WEBVIEW_MIME_TYPE, AudienceNetworkActivity.WEBVIEW_ENCODING);
        }
    }

    final void m11647(String str, Map<String, String> map, Integer num, Integer num2, Integer num3) {
        if (this.f8659 == C3411d.f8653) {
            this.f8656.setWebViewClient(new C34105(this));
            JSONObject jSONObject = new JSONObject(map);
            WebView webView = this.f8656;
            String str2 = this.f8655;
            webView.loadData(String.format(Locale.ROOT, "<html><head></head><body><div id=\"%s\" style=\"width: %dpx; height: %dpx;\"></div><script>(function initMoatTracking(apiname, pcode, ids, duration) {var events = [];window[pcode + '_moatElToTrack'] = document.getElementById('%s');var moatapi = {'dropTime':%d,'adData': {'ids': ids, 'duration': duration, 'url': 'n/a'},'dispatchEvent': function(ev) {if (this.sendEvent) {if (events) { events.push(ev); ev = events; events = false; }this.sendEvent(ev);} else {events.push(ev);}},'dispatchMany': function(evs){for (var i=0, l=evs.length; i<l; i++) {this.dispatchEvent(evs[i]);}}};Object.defineProperty(window, apiname, {'value': moatapi});var s = document.createElement('script');s.src = 'https://z.moatads.com/' + pcode + '/moatvideo.js?' + apiname + '#' + apiname;document.body.appendChild(s);})('%s', '%s', %s, %s);</script></body></html>", new Object[]{"mianahwvc", num, num2, "mianahwvc", Long.valueOf(System.currentTimeMillis()), str2, str, jSONObject.toString(), num3}), AudienceNetworkActivity.WEBVIEW_MIME_TYPE, null);
        }
    }

    C3412a() {
    }

    static void m11642(int i, String str, Object obj, String str2) {
        if (!C3460t.m11803().f8825) {
            return;
        }
        if (obj == null) {
            Log.println(i, new StringBuilder(VastExtensionXmlManager.MOAT).append(str).toString(), String.format("message = %s", new Object[]{str2}));
            return;
        }
        Log.println(i, new StringBuilder(VastExtensionXmlManager.MOAT).append(str).toString(), String.format("id = %s, message = %s", new Object[]{Integer.valueOf(obj.hashCode()), str2}));
    }

    static void m11643(String str, Object obj, String str2) {
        if (C3460t.m11803().f8822) {
            String str3;
            String stringBuilder = new StringBuilder(VastExtensionXmlManager.MOAT).append(str).toString();
            String str4 = "id = %s, message = %s";
            Object[] objArr = new Object[2];
            if (obj == null) {
                str3 = "null";
            } else {
                str3 = Integer.valueOf(obj.hashCode());
            }
            objArr[0] = str3;
            objArr[1] = str2;
            Log.println(2, stringBuilder, String.format(str4, objArr));
        }
    }

    static void m11644(String str, Object obj, String str2, Exception exception) {
        if (C3460t.m11803().f8825) {
            Log.e(new StringBuilder(VastExtensionXmlManager.MOAT).append(str).toString(), String.format("id = %s, message = %s", new Object[]{Integer.valueOf(obj.hashCode()), str2}), exception);
        }
    }

    static void m11639(String str, String str2) {
        if (!C3460t.m11803().f8825 && ((C3419f) MoatAnalytics.getInstance()).f8686) {
            int i = 2;
            if (str.equals("[ERROR] ")) {
                i = 6;
            }
            Log.println(i, "MoatAnalytics", str + str2);
        }
    }

    static String m11641(View view) {
        if (view != null) {
            return view.getClass().getSimpleName() + "@" + view.hashCode();
        }
        return "null";
    }
}
