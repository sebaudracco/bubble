package com.moat.analytics.mobile.inm;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build.VERSION;
import android.util.Log;
import android.webkit.WebView;
import com.github.lzyzsd.jsbridge.BridgeUtil;
import com.moat.analytics.mobile.inm.base.exception.C3376a;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

class C3388n implements C3387l {
    private final ScheduledExecutorService f8604a;
    private ScheduledFuture<?> f8605b;
    private ScheduledFuture<?> f8606c;
    private final ao f8607d;
    private int f8608e = 0;
    private boolean f8609f = false;
    private boolean f8610g = false;
    private WebView f8611h;
    private C3379m f8612i;

    C3388n(Context context, ao aoVar) {
        this.f8607d = aoVar;
        this.f8604a = Executors.newScheduledThreadPool(1);
    }

    private void m11605b() {
        try {
            if (this.f8607d.mo6480a() != aq.OFF) {
                if (this.f8607d.mo6482b() && !this.f8610g) {
                    Log.d("MoatJavaScriptBridge", "Ready for communication (setting environment variables).");
                    this.f8610g = true;
                }
                this.f8611h.loadUrl(String.format("javascript:(function(b,f){function g(){function b(a,e){for(k in a)if(a.hasOwnProperty(k)){var c=a[k].fn;if('function'===typeof c)try{e?c(e):c()}catch(d){}}}function d(a,b,c){'function'===typeof a&&(c[b]={ts:+new Date,fn:a})}bjmk={};uqaj={};yhgt={};ryup=dptk=!1;this.a=function(a){this.namespace=a.namespace;this.version=a.version;this.appName=a.appName;this.deviceOS=a.deviceOS;this.isNative=a.isNative;this.versionHash=a.versionHash};this.bpsy=function(a){dptk||ryup||d(a,+new Date,bjmk)};this.qmrv=function(a){ryup||d(a,+new Date,uqaj)};this.lgpr=function(a,b){d(a,b,yhgt)};this.xrnk=function(a){yhgt.hasOwnProperty(a)&&delete yhgt[a]};this.vgft=function(){return dptk};this.lkpu=function(){return ryup};this.mqjh=function(){dptk||ryup||(dptk=!0,b(bjmk))};this.egpw=function(){ryup||(ryup=!0,b(uqaj))};this.sglu=function(a){b(yhgt,a);return 0<Object.keys(yhgt).length}}'undefined'===typeof b.MoatMAK&&(b.MoatMAK=new g,b.MoatMAK.a(f),b.__zMoatInit__=!0)})(window,%s);", new Object[]{this.f8612i.mo6492b()}));
            }
        } catch (Throwable e) {
            if (this.f8607d.mo6482b()) {
                Log.e("MoatJavaScriptBridge", "Failed to initialize communication (did not set environment variables).", e);
            }
        }
    }

    @TargetApi(19)
    private void m11607c() {
        try {
            if (this.f8607d.mo6480a() != aq.OFF) {
                if (this.f8611h == null || (this.f8609f && this.f8611h.getUrl() == null)) {
                    if (this.f8607d.mo6482b()) {
                        Log.d("MoatJavaScriptBridge", "WebView became null" + (this.f8611h == null ? "" : "based on null url") + ", stopping tracking loop");
                    }
                    m11614g();
                    return;
                }
                if (this.f8611h.getUrl() != null) {
                    this.f8609f = true;
                }
                String format = String.format("MoatMAK.sglu(%s)", new Object[]{this.f8612i.mo6490a()});
                if (VERSION.SDK_INT >= 19) {
                    this.f8611h.evaluateJavascript(format, new C3390p(this));
                } else {
                    this.f8611h.loadUrl(BridgeUtil.JAVASCRIPT_STR + format);
                }
            }
        } catch (Exception e) {
            C3376a.m11557a(e);
            m11611e();
            m11614g();
        }
    }

    private void m11608d() {
        if (this.f8607d.mo6482b()) {
            Log.d("MoatJavaScriptBridge", "Starting metadata reporting loop");
        }
        this.f8606c = this.f8604a.scheduleWithFixedDelay(new C3391q(this), 0, 50, TimeUnit.MILLISECONDS);
    }

    private void m11611e() {
        if (this.f8606c != null) {
            if (!this.f8606c.isCancelled() && this.f8607d.mo6482b()) {
                Log.d("MoatJavaScriptBridge", "Stopping metadata reporting loop");
            }
            this.f8606c.cancel(true);
        }
    }

    private void m11612f() {
        if (this.f8607d.mo6482b()) {
            Log.d("MoatJavaScriptBridge", "Starting view update loop");
        }
        this.f8605b = this.f8604a.scheduleWithFixedDelay(new C3393s(this), 0, (long) this.f8607d.mo6483c(), TimeUnit.MILLISECONDS);
    }

    private void m11614g() {
        if (this.f8605b != null) {
            if (this.f8605b.isCancelled() && this.f8607d.mo6482b()) {
                Log.d("MoatJavaScriptBridge", "Stopping view update loop");
            }
            this.f8605b.cancel(true);
        }
    }

    public void mo6500a() {
        if (this.f8607d.mo6480a() != aq.OFF) {
            m11611e();
            m11614g();
        }
    }

    public boolean mo6501a(WebView webView, C3379m c3379m) {
        boolean b = this.f8607d.mo6482b();
        if (webView.getSettings().getJavaScriptEnabled()) {
            this.f8611h = webView;
            this.f8612i = c3379m;
            m11608d();
            m11612f();
            this.f8604a.schedule(new C3389o(this), 10, TimeUnit.SECONDS);
            return true;
        }
        if (b) {
            Log.e("MoatJavaScriptBridge", "JavaScript is not enabled in the given WebView. Can't track.");
        }
        return false;
    }
}
