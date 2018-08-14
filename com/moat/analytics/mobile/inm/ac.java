package com.moat.analytics.mobile.inm;

import android.content.Context;
import android.os.Build.VERSION;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.facebook.ads.AudienceNetworkActivity;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import org.json.JSONObject;

class ac {
    protected final C3371a f8502a;
    protected final ao f8503b;
    protected bc f8504c;
    private final String f8505d;
    private final String f8506e = String.format("_moatTracker%d", new Object[]{Integer.valueOf((int) (Math.random() * 1.0E8d))});
    private WeakReference<Context> f8507f;
    private WeakReference<View> f8508g;
    private WebView f8509h;
    private boolean f8510i = false;
    private boolean f8511j;
    private final LinkedList<String> f8512k = new LinkedList();

    ac(String str, ao aoVar, C3371a c3371a) {
        this.f8505d = str;
        this.f8503b = aoVar;
        this.f8502a = c3371a;
        this.f8507f = new WeakReference(c3371a.mo6499c());
    }

    void m11473a() {
        int i;
        int i2;
        if (this.f8512k.size() >= 200) {
            LinkedList linkedList = new LinkedList();
            for (i = 0; i < 10; i++) {
                linkedList.addFirst((String) this.f8512k.removeFirst());
            }
            i = Math.min(Math.min(this.f8512k.size() / 200, 10) + 200, this.f8512k.size());
            for (i2 = 0; i2 < i; i2++) {
                this.f8512k.removeFirst();
            }
            Iterator it = linkedList.iterator();
            while (it.hasNext()) {
                this.f8512k.addFirst((String) it.next());
            }
        }
        i2 = 0;
        while (!this.f8512k.isEmpty() && i2 < 200) {
            i2++;
            String str = "javascript:%s.dispatchMany([%s])";
            StringBuilder stringBuilder = new StringBuilder();
            i = 1;
            while (!this.f8512k.isEmpty() && i2 < 200) {
                int i3 = i2 + 1;
                String str2 = (String) this.f8512k.getFirst();
                if (stringBuilder.length() + str2.length() > 2000) {
                    i2 = i3;
                    break;
                }
                this.f8512k.removeFirst();
                if (i != 0) {
                    i = 0;
                } else {
                    stringBuilder.append(",");
                }
                stringBuilder.append(str2);
                i2 = i3;
            }
            m11476a(String.format(str, new Object[]{this.f8506e, stringBuilder.toString()}));
        }
        this.f8512k.clear();
    }

    public void m11474a(View view) {
        this.f8508g = new WeakReference(view);
        if (this.f8504c != null) {
            this.f8504c.mo6491a(view);
        }
    }

    public void m11475a(View view, Map<String, String> map, Integer num, Integer num2, Integer num3) {
        this.f8508g = new WeakReference(view);
        this.f8509h = new WebView((Context) this.f8507f.get());
        WebSettings settings = this.f8509h.getSettings();
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
        this.f8509h.setWebViewClient(new ad(this));
        JSONObject jSONObject = new JSONObject(map);
        this.f8509h.loadData(String.format("<html><head></head><body><div id=\"%s\" style=\"width: %dpx; height: %dpx;\"></div><script>(function initMoatTracking(apiname, pcode, ids, duration) {var events = [];window[pcode + '_moatElToTrack'] = document.getElementById('%s');var moatapi = {'dropTime':%d,'adData': {'ids': ids, 'duration': duration, 'url': 'n/a'},'dispatchEvent': function(ev) {if (this.sendEvent) {if (events) { events.push(ev); ev = events; events = false; }this.sendEvent(ev);} else {events.push(ev);}},'dispatchMany': function(evs){for (var i=0, l=evs.length; i<l; i++) {this.dispatchEvent(evs[i]);}}};Object.defineProperty(window, apiname, {'value': moatapi});var s = document.createElement('script');s.src = 'https://z.moatads.com/' + pcode + '/moatvideo.js?' + apiname + '#' + apiname;document.body.appendChild(s);})('%s', '%s', %s, %s);</script></body></html>", new Object[]{"mianahwvc", num, num2, "mianahwvc", Long.valueOf(System.currentTimeMillis()), this.f8506e, this.f8505d, jSONObject.toString(), num3}), AudienceNetworkActivity.WEBVIEW_MIME_TYPE, null);
    }

    void m11476a(String str) {
        this.f8509h.loadUrl(str);
    }

    public void m11477a(JSONObject jSONObject) {
        String jSONObject2 = jSONObject.toString();
        if (!this.f8510i || this.f8509h == null) {
            this.f8512k.add(jSONObject2);
            return;
        }
        this.f8509h.loadUrl(String.format("javascript:%s.dispatchEvent(%s);", new Object[]{this.f8506e, jSONObject2}));
    }

    public void m11478b() {
        if (!this.f8511j) {
            if (this.f8504c != null) {
                this.f8504c.mo6494d();
                this.f8504c = null;
            }
            if (this.f8509h != null) {
                this.f8509h.loadUrl("about:blank");
                this.f8509h.destroy();
                this.f8509h = null;
            }
            this.f8511j = true;
        }
    }
}
