package com.facebook.ads.internal.view.p034b;

import android.content.Context;
import android.net.http.SslError;
import android.view.MotionEvent;
import android.webkit.ConsoleMessage;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.facebook.ads.internal.p056q.p057a.C2119j;
import com.facebook.ads.internal.p056q.p057a.C2130s;
import com.facebook.ads.internal.p056q.p076c.C2141a;
import com.facebook.ads.internal.p056q.p076c.C2142b;
import com.facebook.ads.internal.p070r.C2154a;
import com.facebook.ads.internal.p070r.C2154a.C2025a;
import com.facebook.ads.internal.view.b.a.a;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

public class C2182a extends C2141a {
    private static final String f5253a = C2182a.class.getSimpleName();
    private final WeakReference<C1901b> f5254b;
    private C2130s f5255c = new C2130s();
    private C2154a f5256d;
    private C2025a f5257e;

    public interface C1901b {
        void mo3669a();

        void mo3670a(int i);

        void mo3671a(String str, Map<String, String> map);

        void mo3672b();
    }

    class C21781 extends C2025a {
        final /* synthetic */ C2182a f5247a;

        C21781(C2182a c2182a) {
            this.f5247a = c2182a;
        }

        public void mo3725a() {
            this.f5247a.f5255c.m6821a();
            if (this.f5247a.f5254b.get() != null) {
                ((C1901b) this.f5247a.f5254b.get()).mo3672b();
            }
        }
    }

    class C21792 extends WebChromeClient {
        final /* synthetic */ C2182a f5248a;

        C21792(C2182a c2182a) {
            this.f5248a = c2182a;
        }

        public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
            return true;
        }
    }

    static class C2180c implements Runnable {
        private final WeakReference<C2154a> f5249a;

        C2180c(WeakReference<C2154a> weakReference) {
            this.f5249a = weakReference;
        }

        public void run() {
            C2154a c2154a = (C2154a) this.f5249a.get();
            if (c2154a != null) {
                c2154a.m6917a();
            }
        }
    }

    static class C2181d extends WebViewClient {
        private final WeakReference<C1901b> f5250a;
        private final WeakReference<C2154a> f5251b;
        private final WeakReference<C2130s> f5252c;

        C2181d(WeakReference<C1901b> weakReference, WeakReference<C2154a> weakReference2, WeakReference<C2130s> weakReference3) {
            this.f5250a = weakReference;
            this.f5251b = weakReference2;
            this.f5252c = weakReference3;
        }

        public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
            sslErrorHandler.cancel();
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            Map hashMap = new HashMap();
            if (this.f5251b.get() != null) {
                ((C2154a) this.f5251b.get()).m6919a(hashMap);
            }
            if (this.f5252c.get() != null) {
                hashMap.put("touch", C2119j.m6798a(((C2130s) this.f5252c.get()).m6826e()));
            }
            if (this.f5250a.get() != null) {
                ((C1901b) this.f5250a.get()).mo3671a(str, hashMap);
            }
            return true;
        }
    }

    public C2182a(Context context, WeakReference<C1901b> weakReference, int i) {
        super(context);
        this.f5254b = weakReference;
        this.f5257e = new C21781(this);
        this.f5256d = new C2154a(this, i, this.f5257e);
        setWebChromeClient(mo3762a());
        setWebViewClient(mo3763b());
        getSettings().setSupportZoom(false);
        getSettings().setCacheMode(1);
        addJavascriptInterface(new a(this, (C1901b) weakReference.get(), this.f5256d, null), "AdControl");
    }

    protected WebChromeClient mo3762a() {
        return new C21792(this);
    }

    public void m6979a(int i, int i2) {
        this.f5256d.m6918a(i);
        this.f5256d.m6921b(i2);
    }

    protected WebViewClient mo3763b() {
        return new C2181d(this.f5254b, new WeakReference(this.f5256d), new WeakReference(this.f5255c));
    }

    public void destroy() {
        if (this.f5256d != null) {
            this.f5256d.m6920b();
            this.f5256d = null;
        }
        this.f5257e = null;
        this.f5255c = null;
        C2142b.m6858a(this);
        super.destroy();
    }

    public Map<String, String> getTouchData() {
        return this.f5255c.m6826e();
    }

    public C2154a getViewabilityChecker() {
        return this.f5256d;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        this.f5255c.m6822a(motionEvent, this, this);
        return super.onTouchEvent(motionEvent);
    }

    protected void onWindowVisibilityChanged(int i) {
        super.onWindowVisibilityChanged(i);
        if (this.f5254b.get() != null) {
            ((C1901b) this.f5254b.get()).mo3670a(i);
        }
        if (this.f5256d == null) {
            return;
        }
        if (i == 0) {
            this.f5256d.m6917a();
        } else if (i == 8) {
            this.f5256d.m6920b();
        }
    }
}
