package com.adcolony.sdk;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build.VERSION;
import android.view.ViewGroup.LayoutParams;
import android.webkit.ConsoleMessage;
import android.webkit.ConsoleMessage.MessageLevel;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import com.adcolony.sdk.aa.C0595a;
import com.facebook.ads.AudienceNetworkActivity;
import com.github.lzyzsd.jsbridge.BridgeUtil;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import org.json.JSONArray;
import org.json.JSONObject;

class bb extends WebView implements ai {
    private String f823a;
    private String f824b;
    private String f825c = "";
    private String f826d = "";
    private String f827e;
    private String f828f = "";
    private String f829g = "";
    private int f830h;
    private int f831i;
    private int f832j;
    private int f833k;
    private int f834l;
    private int f835m;
    private boolean f836n;
    private boolean f837o;
    private boolean f838p;
    private boolean f839q;
    private boolean f840r;
    private boolean f841s;
    private JSONArray f842t = C0802y.m1469b();
    private JSONObject f843u = C0802y.m1453a();
    private C0673c f844v;
    private af f845w;

    private class C0653a extends WebViewClient {
        final /* synthetic */ bb f815b;

        private C0653a(bb bbVar) {
            this.f815b = bbVar;
        }

        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return false;
        }

        public void onLoadResource(WebView view, String url) {
            if (url.equals(this.f815b.f823a)) {
                this.f815b.m989a("if (typeof(CN) != 'undefined' && CN.div) {\n  if (typeof(cn_dispatch_on_touch_begin) != 'undefined') CN.div.removeEventListener('mousedown',  cn_dispatch_on_touch_begin, true);\n  if (typeof(cn_dispatch_on_touch_end) != 'undefined')   CN.div.removeEventListener('mouseup',  cn_dispatch_on_touch_end, true);\n  if (typeof(cn_dispatch_on_touch_move) != 'undefined')  CN.div.removeEventListener('mousemove',  cn_dispatch_on_touch_move, true);\n}\n");
            }
        }

        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            this.f815b.f839q = false;
            this.f815b.f840r = false;
            new C0595a().m622a("onPageStarted with URL = ").m622a(url).m624a(aa.f480d);
        }

        public void onPageFinished(WebView view, String url) {
            JSONObject a = C0802y.m1453a();
            C0802y.m1472b(a, "id", this.f815b.f830h);
            C0802y.m1462a(a, "url", url);
            new C0595a().m622a("onPageFinished called with URL = ").m622a(url).m624a(aa.f478b);
            if (this.f815b.f844v == null) {
                new af("WebView.on_load", this.f815b.f835m, a).m695b();
            } else {
                C0802y.m1462a(a, "ad_session_id", this.f815b.f827e);
                C0802y.m1472b(a, "container_id", this.f815b.f844v.m1060d());
                new af("WebView.on_load", this.f815b.f844v.m1057c(), a).m695b();
            }
            if ((this.f815b.f836n || this.f815b.f837o) && !this.f815b.f839q && (url.startsWith("data") || url.startsWith("file") || url.equals(this.f815b.f826d) || this.f815b.f840r)) {
                new C0595a().m622a("WebView data loaded - executing ADC3_init").m624a(aa.f478b);
                new C0595a().m622a("===============================================================================").m624a(aa.f478b);
                new C0595a().m622a("ADC3_init(").m620a(this.f815b.f835m).m622a(",").m622a(this.f815b.f843u.toString()).m622a(");").m624a(aa.f478b);
                new C0595a().m622a("===============================================================================").m624a(aa.f478b);
                this.f815b.m989a("ADC3_init(" + this.f815b.f835m + "," + this.f815b.f843u.toString() + ");");
                this.f815b.f839q = true;
            }
            if (this.f815b.f837o) {
                a = C0802y.m1453a();
                C0802y.m1465a(a, "success", true);
                C0802y.m1472b(a, "id", this.f815b.f835m);
                this.f815b.f845w.m694a(a).m695b();
            }
        }

        public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
            if (VERSION.SDK_INT >= 21 || !url.endsWith("mraid.js")) {
                return null;
            }
            try {
                InputStream byteArrayInputStream = new ByteArrayInputStream(this.f815b.f828f.getBytes("UTF-8"));
                this.f815b.f840r = true;
                return new WebResourceResponse("text/javascript", "UTF-8", byteArrayInputStream);
            } catch (UnsupportedEncodingException e) {
                new C0595a().m622a("UTF-8 not supported.").m624a(aa.f484h);
                return null;
            }
        }

        public void onReceivedError(WebView view, int error, String description, String url) {
            if (VERSION.SDK_INT < 23) {
                JSONObject a = C0802y.m1453a();
                C0802y.m1472b(a, "id", this.f815b.f830h);
                C0802y.m1462a(a, "ad_session_id", this.f815b.f827e);
                C0802y.m1472b(a, "container_id", this.f815b.f844v.m1060d());
                C0802y.m1472b(a, "code", error);
                C0802y.m1462a(a, "error", description);
                C0802y.m1462a(a, "url", url);
                new af("WebView.on_error", this.f815b.f844v.m1057c(), a).m695b();
            }
        }
    }

    class C06542 extends C0653a {
        final /* synthetic */ bb f816a;

        C06542(bb bbVar) {
            this.f816a = bbVar;
            super();
        }

        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
            if (this.f816a.f844v != null) {
                JSONObject a = C0802y.m1453a();
                C0802y.m1472b(a, "id", this.f816a.f830h);
                C0802y.m1462a(a, "ad_session_id", this.f816a.f827e);
                C0802y.m1472b(a, "container_id", this.f816a.f844v.m1060d());
                C0802y.m1472b(a, "code", error.getErrorCode());
                C0802y.m1462a(a, "error", error.getDescription().toString());
                C0802y.m1462a(a, "url", this.f816a.f823a);
                new af("WebView.on_error", this.f816a.f844v.m1057c(), a).m695b();
            }
            new C0595a().m622a("onReceivedError: ").m622a(error.getDescription().toString()).m624a(aa.f484h);
        }

        public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
            if (!request.getUrl().toString().endsWith("mraid.js")) {
                return null;
            }
            try {
                InputStream byteArrayInputStream = new ByteArrayInputStream(this.f816a.f828f.getBytes("UTF-8"));
                this.f816a.f840r = true;
                return new WebResourceResponse("text/javascript", "UTF-8", byteArrayInputStream);
            } catch (UnsupportedEncodingException e) {
                new C0595a().m622a("UTF-8 not supported.").m624a(aa.f484h);
                return null;
            }
        }
    }

    class C06553 extends C0653a {
        final /* synthetic */ bb f817a;

        C06553(bb bbVar) {
            this.f817a = bbVar;
            super();
        }

        public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
            if (!request.getUrl().toString().endsWith("mraid.js")) {
                return null;
            }
            try {
                InputStream byteArrayInputStream = new ByteArrayInputStream(this.f817a.f828f.getBytes("UTF-8"));
                this.f817a.f840r = true;
                return new WebResourceResponse("text/javascript", "UTF-8", byteArrayInputStream);
            } catch (UnsupportedEncodingException e) {
                new C0595a().m622a("UTF-8 not supported.").m624a(aa.f484h);
                return null;
            }
        }
    }

    class C06564 {
        final /* synthetic */ bb f818a;

        C06564(bb bbVar) {
            this.f818a = bbVar;
        }

        @JavascriptInterface
        public void dispatch_messages(String json_messages) {
            JSONArray b = C0802y.m1470b(json_messages);
            if (b == null) {
                new C0595a().m622a("[INTERNAL] ADCJSON parse error in dispatch_messages javascript interface function").m624a(aa.f483g);
                return;
            }
            for (int i = 0; i < b.length(); i++) {
                C0594a.m605a().m1287q().m705a(C0802y.m1476d(b, i));
            }
        }
    }

    class C06575 implements ah {
        final /* synthetic */ bb f819a;

        C06575(bb bbVar) {
            this.f819a = bbVar;
        }

        public void mo1863a(af afVar) {
            if (this.f819a.m993a(afVar)) {
                this.f819a.m997c(afVar);
            }
        }
    }

    class C06586 implements ah {
        final /* synthetic */ bb f820a;

        C06586(bb bbVar) {
            this.f820a = bbVar;
        }

        public void mo1863a(af afVar) {
            if (this.f820a.m993a(afVar)) {
                this.f820a.m995b(afVar);
            }
        }
    }

    class C06597 implements ah {
        final /* synthetic */ bb f821a;

        C06597(bb bbVar) {
            this.f821a = bbVar;
        }

        public void mo1863a(af afVar) {
            if (this.f821a.m993a(afVar)) {
                this.f821a.m989a(C0802y.m1468b(afVar.m698c(), "custom_js"));
            }
        }
    }

    class C06608 implements Runnable {
        final /* synthetic */ bb f822a;

        C06608(bb bbVar) {
            this.f822a = bbVar;
        }

        public void run() {
            String str = "";
            synchronized (this.f822a.f842t) {
                if (this.f822a.f842t.length() > 0) {
                    if (this.f822a.f836n) {
                        str = this.f822a.f842t.toString();
                    }
                    this.f822a.f842t = C0802y.m1469b();
                }
            }
            if (this.f822a.f836n) {
                this.f822a.m989a("NativeLayer.dispatch_messages(ADC3_update(" + str + "));");
            }
        }
    }

    bb(Context context, af afVar, int i, int i2, C0673c c0673c) {
        super(context);
        this.f845w = afVar;
        m987a(afVar, i, i2, c0673c);
        m998d();
    }

    bb(Context context, int i, boolean z) {
        super(context);
        this.f835m = i;
        this.f838p = z;
    }

    void m989a(String str) {
        if (this.f841s) {
            new C0595a().m622a("Ignoring call to execute_js as WebView has been destroyed.").m624a(aa.f478b);
        } else if (VERSION.SDK_INT >= 19) {
            evaluateJavascript(str, null);
        } else {
            loadUrl(BridgeUtil.JAVASCRIPT_STR + str);
        }
    }

    public int mo1841a() {
        return this.f835m;
    }

    boolean m993a(af afVar) {
        JSONObject c = afVar.m698c();
        if (C0802y.m1473c(c, "id") == this.f830h && C0802y.m1473c(c, "container_id") == this.f844v.m1060d() && C0802y.m1468b(c, "ad_session_id").equals(this.f844v.m1053b())) {
            return true;
        }
        return false;
    }

    void m998d() {
        m992a(false, null);
    }

    void m992a(boolean z, af afVar) {
        WebViewClient c06542;
        if (this.f845w == null) {
            this.f845w = afVar;
        }
        final JSONObject c = this.f845w.m698c();
        this.f837o = z;
        this.f838p = C0802y.m1477d(c, "is_display_module");
        if (z) {
            String b = C0802y.m1468b(c, "filepath");
            this.f824b = b;
            this.f823a = "file://" + b;
            this.f843u = C0802y.m1480f(c, "info");
            this.f836n = true;
        }
        setFocusable(true);
        setHorizontalScrollBarEnabled(false);
        setVerticalScrollBarEnabled(false);
        if (VERSION.SDK_INT >= 19) {
            setWebContentsDebuggingEnabled(true);
        }
        setWebChromeClient(new WebChromeClient(this) {
            final /* synthetic */ bb f814b;

            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                new C0595a().m622a("JS Alert: ").m622a(message).m624a(aa.f480d);
                return true;
            }

            public boolean onConsoleMessage(ConsoleMessage cm) {
                if (cm.messageLevel() == MessageLevel.WARNING) {
                    new C0595a().m622a("onConsoleMessage: ").m622a(cm.message()).m624a(aa.f482f);
                } else if (cm.messageLevel() == MessageLevel.ERROR) {
                    if ((cm.message().contains("ADC3_update is not defined") || cm.message().contains("NativeLayer.dispatch_messages is not a function")) && C0594a.m614d() && (C0594a.m613c() instanceof C0589b)) {
                        af afVar = new af("AdSession.finish_fullscreen_ad", 0);
                        C0802y.m1472b(c, "status", 1);
                        new C0595a().m622a("Unable to communicate with ad, closing. Please ensure ").m622a("that you have added an exception for our Javascript interface in your ProGuard ").m622a("configuration and that you do not have a faulty proxy enabled on your device.").m624a(aa.f483g);
                        ((C0589b) C0594a.m613c()).mo1845a(afVar);
                    }
                    new C0595a().m622a("onConsoleMessage: ").m622a(cm.message()).m624a(aa.f484h);
                }
                return true;
            }
        });
        WebSettings settings = getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setBuiltInZoomControls(true);
        settings.setGeolocationEnabled(true);
        settings.setUseWideViewPort(true);
        if (VERSION.SDK_INT >= 17) {
            settings.setMediaPlaybackRequiresUserGesture(false);
        }
        if (VERSION.SDK_INT >= 16) {
            settings.setAllowFileAccessFromFileURLs(true);
            settings.setAllowUniversalAccessFromFileURLs(true);
        }
        if (VERSION.SDK_INT >= 23) {
            c06542 = new C06542(this);
        } else if (VERSION.SDK_INT >= 21) {
            c06542 = new C06553(this);
        } else {
            c06542 = new C0653a();
        }
        addJavascriptInterface(new C06564(this), "NativeLayer");
        setWebViewClient(c06542);
        if (this.f838p) {
            String str = "";
            try {
                InputStream fileInputStream = new FileInputStream(this.f824b);
                StringBuilder stringBuilder = new StringBuilder(fileInputStream.available());
                byte[] bArr = new byte[1024];
                while (true) {
                    int read = fileInputStream.read(bArr, 0, 1024);
                    if (read < 0) {
                        break;
                    }
                    stringBuilder.append(new String(bArr, 0, read));
                }
                str = stringBuilder.toString().replaceFirst("var\\s*ADC_DEVICE_INFO\\s*=\\s*null\\s*;", "var ADC_DEVICE_INFO = " + C0802y.m1468b(C0802y.m1480f(c, "info"), "metadata") + ";\n");
            } catch (IOException e) {
                new C0595a().m622a("Failed to find or open display module at URL: ").m622a(this.f823a).m622a(" with error: ").m622a(e.toString()).m624a(aa.f483g);
            }
            loadDataWithBaseURL(this.f823a, str, AudienceNetworkActivity.WEBVIEW_MIME_TYPE, null, null);
        } else if (this.f823a.startsWith("http") || this.f823a.startsWith("file")) {
            loadUrl(this.f823a);
        } else {
            loadDataWithBaseURL(this.f826d.equals("") ? "data" : this.f826d, z ? C0802y.m1468b(c, "data") : this.f823a, AudienceNetworkActivity.WEBVIEW_MIME_TYPE, null, null);
        }
        if (!z) {
            m999e();
            m1000f();
        }
        if (z || this.f836n) {
            C0594a.m605a().m1287q().m701a((ai) this);
        }
        if (!this.f825c.equals("")) {
            m989a(this.f825c);
        }
    }

    void m999e() {
        this.f844v.m1080n().add(C0594a.m604a("WebView.set_visible", new C06575(this), true));
        this.f844v.m1080n().add(C0594a.m604a("WebView.set_bounds", new C06586(this), true));
        this.f844v.m1080n().add(C0594a.m604a("WebView.execute_js", new C06597(this), true));
        this.f844v.m1082o().add("WebView.set_visible");
        this.f844v.m1082o().add("WebView.set_bounds");
        this.f844v.m1082o().add("WebView.execute_js");
    }

    void m1000f() {
        setVisibility(4);
        LayoutParams layoutParams = new FrameLayout.LayoutParams(this.f833k, this.f834l);
        layoutParams.setMargins(this.f831i, this.f832j, 0, 0);
        layoutParams.gravity = 0;
        this.f844v.addView(this, layoutParams);
    }

    void m988a(af afVar, int i, C0673c c0673c) {
        m987a(afVar, i, -1, c0673c);
        m1000f();
    }

    void m987a(af afVar, int i, int i2, C0673c c0673c) {
        boolean z = false;
        JSONObject c = afVar.m698c();
        this.f823a = C0802y.m1468b(c, "url");
        if (this.f823a.equals("")) {
            this.f823a = C0802y.m1468b(c, "data");
        }
        this.f826d = C0802y.m1468b(c, "base_url");
        this.f825c = C0802y.m1468b(c, "custom_js");
        this.f827e = C0802y.m1468b(c, "ad_session_id");
        this.f843u = C0802y.m1480f(c, "info");
        this.f829g = C0802y.m1468b(c, "mraid_filepath");
        if (!this.f838p) {
            try {
                this.f828f = C0594a.m605a().m1280j().m1401a(this.f829g, false).toString();
                String str = "bridge.os_name = \"\";\nvar ADC_DEVICE_INFO = " + this.f843u.toString() + ";\n";
                this.f828f = this.f828f.replaceFirst("bridge.os_name\\s*=\\s*\"\"\\s*;", str);
            } catch (IOException e) {
                new C0595a().m622a("Could not load MRAID from filepath: ").m622a(this.f829g).m624a(aa.f483g);
                JSONObject a = C0802y.m1453a();
                C0802y.m1462a(a, "id", this.f827e);
                new af("AdSession.on_error", c0673c.m1057c(), a).m695b();
            }
        }
        this.f830h = i;
        this.f844v = c0673c;
        if (i2 >= 0) {
            this.f835m = i2;
        } else {
            m999e();
        }
        this.f833k = C0802y.m1473c(c, "width");
        this.f834l = C0802y.m1473c(c, "height");
        this.f831i = C0802y.m1473c(c, "x");
        this.f832j = C0802y.m1473c(c, "y");
        if (C0802y.m1477d(c, "enable_messages") || this.f837o) {
            z = true;
        }
        this.f836n = z;
    }

    void m995b(af afVar) {
        JSONObject c = afVar.m698c();
        this.f831i = C0802y.m1473c(c, "x");
        this.f832j = C0802y.m1473c(c, "y");
        this.f833k = C0802y.m1473c(c, "width");
        this.f834l = C0802y.m1473c(c, "height");
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) getLayoutParams();
        layoutParams.setMargins(this.f831i, this.f832j, 0, 0);
        layoutParams.width = this.f833k;
        layoutParams.height = this.f834l;
        setLayoutParams(layoutParams);
        if (this.f837o) {
            c = C0802y.m1453a();
            C0802y.m1465a(c, "success", true);
            C0802y.m1472b(c, "id", this.f835m);
            afVar.m694a(c).m695b();
        }
    }

    void m997c(af afVar) {
        if (C0802y.m1477d(afVar.m698c(), "visible")) {
            setVisibility(0);
        } else {
            setVisibility(4);
        }
        if (this.f837o) {
            JSONObject a = C0802y.m1453a();
            C0802y.m1465a(a, "success", true);
            C0802y.m1472b(a, "id", this.f835m);
            afVar.m694a(a).m695b();
        }
    }

    public void mo1842a(JSONObject jSONObject) {
        synchronized (this.f842t) {
            this.f842t.put(jSONObject);
        }
    }

    public void mo1843b() {
    }

    public void mo1844c() {
        if (C0594a.m614d() && this.f839q) {
            az.m880a(new C06608(this));
        }
    }

    boolean m1001g() {
        return this.f838p;
    }

    boolean m1002h() {
        return this.f841s;
    }

    void m991a(boolean z) {
        this.f841s = z;
    }
}
