package com.integralads.avid.library.inmobi.session.internal.p129a;

import android.text.TextUtils;
import android.webkit.WebView;
import com.integralads.avid.library.inmobi.C3287a;
import com.integralads.avid.library.inmobi.p128h.C3322b;
import com.integralads.avid.library.inmobi.session.internal.C3334b;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONObject;

/* compiled from: AvidBridgeManager */
public class C3331a {
    private final C3334b f8473a;
    private boolean f8474b;
    private C3322b<WebView> f8475c;
    private boolean f8476d;
    private C3330a f8477e;
    private final ArrayList<C3332b> f8478f = new ArrayList();

    /* compiled from: AvidBridgeManager */
    public interface C3330a {
        void mo6353n();
    }

    public C3331a(C3334b c3334b) {
        this.f8473a = c3334b;
        this.f8475c = new C3322b(null);
    }

    public boolean m11383a() {
        return this.f8474b;
    }

    public void m11380a(C3330a c3330a) {
        this.f8477e = c3330a;
    }

    public void m11384b() {
        m11374e();
    }

    public void m11379a(WebView webView) {
        if (this.f8475c.m11354a() != webView) {
            this.f8475c.m11355a(webView);
            this.f8474b = false;
            m11374e();
        }
    }

    public void m11386c() {
        m11379a(null);
    }

    public void m11388d() {
        this.f8476d = true;
        m11375f();
    }

    public void m11381a(String str) {
        m11373d("setNativeViewState(" + str + ")");
    }

    public void m11385b(String str) {
        m11373d("setAppState(" + JSONObject.quote(str) + ")");
    }

    public void m11382a(String str, JSONObject jSONObject) {
        if (m11383a()) {
            m11372b(str, jSONObject);
        } else {
            this.f8478f.add(new C3332b(1, str, jSONObject));
        }
    }

    public void m11387c(String str) {
        C3287a.m11191c((WebView) this.f8475c.m11354a(), str);
    }

    private void m11374e() {
        WebView webView = (WebView) this.f8475c.m11354a();
        if (webView != null) {
            this.f8474b = C3287a.m11188a(webView);
            m11376g();
            if (this.f8474b) {
                m11375f();
                m11378i();
                m11377h();
            }
        }
    }

    private void m11373d(String str) {
        C3287a.m11189a((WebView) this.f8475c.m11354a(), str);
    }

    private void m11375f() {
        if (m11383a() && this.f8476d) {
            m11373d("publishReadyEventForDeferredAdSession()");
        }
    }

    private void m11372b(String str, JSONObject jSONObject) {
        Object jSONObject2 = jSONObject != null ? jSONObject.toString() : null;
        if (TextUtils.isEmpty(jSONObject2)) {
            m11373d("publishVideoEvent(" + JSONObject.quote(str) + ")");
        } else {
            m11373d("publishVideoEvent(" + JSONObject.quote(str) + "," + jSONObject2 + ")");
        }
    }

    private void m11376g() {
        m11373d("setAvidAdSessionContext(" + this.f8473a.m11420b().toString() + ")");
    }

    private void m11377h() {
        if (this.f8477e != null) {
            this.f8477e.mo6353n();
        }
    }

    private void m11378i() {
        Iterator it = this.f8478f.iterator();
        while (it.hasNext()) {
            C3332b c3332b = (C3332b) it.next();
            m11372b(c3332b.m11389a(), c3332b.m11390b());
        }
        this.f8478f.clear();
    }
}
