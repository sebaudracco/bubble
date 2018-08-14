package com.vungle.publisher;

import android.webkit.WebView;
import com.vungle.publisher.iz.b;
import com.vungle.publisher.log.Logger;
import com.vungle.publisher.rz.C1671a;
import javax.inject.Inject;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: vungle */
public class rr {
    @Inject
    mv f3317a;
    @Inject
    rb f3318b;
    @Inject
    C1671a f3319c;
    private x f3320d;

    @Inject
    rr() {
    }

    void m4623a(WebView webView, p pVar, boolean z, x xVar) {
        this.f3320d = xVar;
        rz a = this.f3319c.m4632a(xVar);
        a.m4646c();
        a.m4642a(true);
        a.m4647d();
        a.m4640a(pVar, z);
        a.m4641a(se.b);
        try {
            m4625a(webView, a.m4637a(), true);
        } catch (Throwable e) {
            Logger.e(Logger.AD_TAG, "could not update mraid properties", e);
        }
    }

    public void m4621a(WebView webView) {
        rz a = this.f3319c.m4632a(this.f3320d);
        a.m4647d();
        try {
            m4625a(webView, a.m4637a(), false);
        } catch (Throwable e) {
            Logger.e(Logger.AD_TAG, "could not update mraid dimensions", e);
        }
    }

    public void m4628a(boolean z, WebView webView) {
        rz a = this.f3319c.m4632a(this.f3320d);
        a.m4642a(z);
        try {
            m4625a(webView, a.m4637a(), false);
        } catch (Throwable e) {
            Logger.e(Logger.AD_TAG, "could not update viewable properties", e);
        }
    }

    public void m4627a(x xVar) {
        this.f3320d = xVar;
    }

    public void m4626a(WebView webView, boolean z) {
        this.f3318b.m4598b(webView, "incentivizedDialogResponse", String.valueOf(z));
    }

    public void m4629b(WebView webView) {
        this.f3318b.m4596a(webView, "requestMRAIDClose", new String[0]);
    }

    void m4625a(WebView webView, JSONObject jSONObject, boolean z) {
        this.f3318b.m4598b(webView, "notifyPropertiesChange", jSONObject.toString(), String.valueOf(z));
    }

    void m4622a(WebView webView, b bVar) throws JSONException {
        Logger.d(Logger.AD_TAG, "inject tokens into js and notify ready");
        JSONObject a = bVar.a();
        if (a.length() > 0) {
            Logger.v(Logger.AD_TAG, "tokens: " + a.toString());
            this.f3318b.m4598b(webView, "notifyReadyEvent", r0);
            return;
        }
        this.f3318b.m4598b(webView, "notifyReadyEvent", new String[0]);
    }

    void m4630c(WebView webView) {
        this.f3318b.m4598b(webView, "notifyCommandComplete", new String[0]);
    }

    void m4624a(WebView webView, String str) {
        this.f3318b.m4595a(webView, "document.querySelector('" + str + "').play()");
    }
}
