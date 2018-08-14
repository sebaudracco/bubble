package com.moat.analytics.mobile.vng;

import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build.VERSION;
import android.support.v4.content.LocalBroadcastManager;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import com.google.android.gms.common.server.FavaDiagnosticsEntity;
import com.moat.analytics.mobile.vng.C3515s.C3514a;
import com.moat.analytics.mobile.vng.C3532w.C3531d;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.WeakHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;
import org.json.JSONObject;

class C3498j {
    boolean f8926a = false;
    private int f8927b = 0;
    private boolean f8928c = false;
    private boolean f8929d = false;
    private final WeakReference<WebView> f8930e;
    private final Map<b, String> f8931f;
    private final LinkedList<String> f8932g;
    private final AtomicBoolean f8933h = new AtomicBoolean(false);
    private final long f8934i;
    private final C3514a f8935j;
    private final List<String> f8936k;
    private final C3497a f8937l;
    private final BroadcastReceiver f8938m = new C34952(this);
    private final BroadcastReceiver f8939n = new C34963(this);

    class C34941 implements ValueCallback<String> {
        final /* synthetic */ C3498j f8919a;

        C34941(C3498j c3498j) {
            this.f8919a = c3498j;
        }

        public void m11896a(String str) {
            if (str == null || str.equalsIgnoreCase("null") || str.equalsIgnoreCase(SchemaSymbols.ATTVAL_FALSE)) {
                C3511p.m11976a(3, "JavaScriptBridge", this.f8919a, "Received value is:" + (str == null ? "null" : "(String)" + str));
                if (this.f8919a.f8927b >= 50) {
                    this.f8919a.m11912f();
                }
                this.f8919a.f8927b = this.f8919a.f8927b + 1;
            } else if (str.equalsIgnoreCase(SchemaSymbols.ATTVAL_TRUE)) {
                if (this.f8919a.f8927b != 0) {
                    C3511p.m11976a(3, "JavaScriptBridge", this.f8919a, "Javascript has found ad");
                    this.f8919a.m11908e();
                }
                this.f8919a.f8927b = 0;
            } else {
                C3511p.m11976a(3, "JavaScriptBridge", this.f8919a, "Received unusual value from Javascript:" + str);
            }
        }

        public /* synthetic */ void onReceiveValue(Object obj) {
            m11896a((String) obj);
        }
    }

    class C34952 extends BroadcastReceiver {
        final /* synthetic */ C3498j f8920a;

        C34952(C3498j c3498j) {
            this.f8920a = c3498j;
        }

        public void onReceive(Context context, Intent intent) {
            try {
                this.f8920a.m11903c();
            } catch (Exception e) {
                C3502m.m11942a(e);
            }
            if (System.currentTimeMillis() - this.f8920a.f8934i > 10000) {
                this.f8920a.m11908e();
            }
        }
    }

    class C34963 extends BroadcastReceiver {
        final /* synthetic */ C3498j f8921a;

        C34963(C3498j c3498j) {
            this.f8921a = c3498j;
        }

        public void onReceive(Context context, Intent intent) {
            try {
                this.f8921a.m11904d();
            } catch (Exception e) {
                C3502m.m11942a(e);
            }
        }
    }

    enum C3497a {
        WEBVIEW,
        NATIVE_DISPLAY,
        NATIVE_VIDEO
    }

    C3498j(WebView webView, C3497a c3497a) {
        this.f8930e = new WeakReference(webView);
        this.f8937l = c3497a;
        this.f8935j = new C3514a();
        this.f8932g = new LinkedList();
        this.f8936k = new ArrayList();
        this.f8931f = new WeakHashMap();
        this.f8934i = System.currentTimeMillis();
        IntentFilter intentFilter = new IntentFilter("UPDATE_METADATA");
        IntentFilter intentFilter2 = new IntentFilter("UPDATE_VIEW_INFO");
        LocalBroadcastManager.getInstance(C3515s.m11989c()).registerReceiver(this.f8938m, intentFilter);
        LocalBroadcastManager.getInstance(C3515s.m11989c()).registerReceiver(this.f8939n, intentFilter2);
        try {
            C3511p.m11976a(3, "JavaScriptBridge", (Object) this, m11901b() ? "bridge installed" : "bridge not installed");
        } catch (Exception e) {
            C3502m.m11942a(e);
        }
    }

    private boolean m11899a(WebView webView, String str) {
        if (webView == null) {
            C3511p.m11976a(6, "JavaScriptBridge", (Object) this, "WebView is null. Can't " + str);
            return false;
        } else if (webView.getSettings().getJavaScriptEnabled()) {
            return true;
        } else {
            C3511p.m11976a(6, "JavaScriptBridge", (Object) this, "JavaScript is not enabled in the given WebView. Can't " + str);
            return false;
        }
    }

    private boolean m11901b() {
        if (m11916h() != null && !m11899a(m11916h(), "installBridge")) {
            return false;
        }
        this.f8926a = true;
        C3493i.m11885a().m11893a(C3515s.m11989c(), this);
        return true;
    }

    private void m11903c() {
        try {
            if (C3532w.m12009a().f8995a != C3531d.OFF) {
                if (!this.f8929d) {
                    C3511p.m11976a(3, "JavaScriptBridge", (Object) this, "Ready for communication (setting environment variables).");
                    this.f8929d = true;
                }
                String format = String.format("javascript:(function(e,k){function l(){function f(a){var b=a.c,c=a.a,f=a.b;a=a.f;var d=[];if(c)b[c]&&d.push(b[c].fn[0]);else for(key in b)if(b[key])for(var g=0,e=b[key].fn.length;g<e;g++)d.push(b[key].fn[g]);g=0;for(e=d.length;g<e;g++){var h=d[g];if('function'===typeof h)try{f?h(f):h()}catch(k){}a&&delete b[c]}}function d(a,b,c){'function'===typeof a&&(b===kuea&&c[b]?c[b].fn.push(a):c[b]={ts:+new Date,fn:[a]})}kuea=+new Date;iymv={};briz=!1;ewat=+new Date;bnkr=[];bjmk={};dptk={};uqaj={};ryup={};yhgt={};csif={};this.g=function(a){this.namespace=a.namespace;this.version=a.version;this.appName=a.appName;this.deviceOS=a.deviceOS;this.isNative=a.isNative;this.versionHash=a.versionHash;this.aqzx=a.aqzx;this.appId=a.appId};this.nvsj=function(a){briz||(d(a,ewat,iymv),briz=!0)};this.bpsy=function(a,b){var c=b||kuea;c!==kuea&&bjmk[c]||d(a,c,bjmk)};this.qmrv=function(a,b){var c=b||kuea;c!==kuea&&uqaj[c]||d(a,c,uqaj)};this.lgpr=function(a,b){d(a,b||kuea,yhgt)};this.hgen=function(a,b){d(a,b||kuea,csif)};this.xrnk=function(a){delete yhgt[a||kuea]};this.vgft=function(a){return dptk[a||kuea]||!1};this.lkpu=function(a){return ryup[a||kuea]||!1};this.crts=function(a){var b={c:iymv,b:a,a:ewat};briz?f(b):bnkr.push(a)};this.mqjh=function(a){var b=a||kuea;dptk[b]=!0;var c={c:bjmk,f:!0};b!==kuea&&(c.b=a,c.a=a);f(c)};this.egpw=function(a){var b=a||kuea;ryup[b]=!0;var c={c:uqaj,f:!0};b!==kuea&&(c.b=a,c.a=a);f(c)};this.sglu=function(a){var b={c:yhgt,b:a.event||a,f:!1};(a.adKey||kuea)!==kuea&&(b.a=a.adKey);f(b);return 0<Object.keys(yhgt).length};this.ucbx=function(a){f({a:a.adKey||kuea,c:csif,b:a.event,f:!1})}}'undefined'===typeof e.MoatMAK&&(e.MoatMAK=new l,e.MoatMAK.g(k),e.__zMoatInit__=!0)})(window,%s);", new Object[]{m11917i()});
                if (m11915g()) {
                    m11916h().loadUrl(format);
                }
            }
        } catch (Throwable e) {
            C3511p.m11977a("JavaScriptBridge", (Object) this, "Failed to initialize communication (did not set environment variables).", e);
        }
    }

    @TargetApi(19)
    private void m11904d() {
        try {
            if (C3532w.m12009a().f8995a != C3531d.OFF) {
                if (this.f8930e == null || !m11915g() || (this.f8928c && m11916h().getUrl() == null)) {
                    if (this.f8930e == null) {
                        C3511p.m11976a(3, "JavaScriptBridge", (Object) this, "WebView ref became null, stopping tracking loop");
                    } else {
                        C3511p.m11976a(3, "JavaScriptBridge", (Object) this, "WebView became null" + (m11916h() == null ? "" : "based on null url") + ", stopping tracking loop");
                    }
                    m11912f();
                    return;
                }
                if (m11916h().getUrl() != null) {
                    this.f8928c = true;
                }
                for (Entry key : this.f8931f.entrySet()) {
                    b bVar = (b) key.getKey();
                    if (bVar == null || bVar.d() == null) {
                        C3511p.m11976a(3, "JavaScriptBridge", (Object) this, "Tracker has no subject");
                        if (bVar == null || !bVar.c) {
                            m11925c(bVar);
                        }
                    }
                    if (bVar.d) {
                        m11913f(String.format("javascript: MoatMAK.mqjh(\"%s\")", new Object[]{bVar.b}));
                        String format = String.format("javascript: MoatMAK.sglu(%s)", new Object[]{bVar.f()});
                        if (VERSION.SDK_INT >= 19) {
                            m11916h().evaluateJavascript(format, new C34941(this));
                        } else {
                            m11916h().loadUrl(format);
                        }
                    }
                }
            }
        } catch (Exception e) {
            C3502m.m11942a(e);
            m11912f();
        }
    }

    private void m11905d(b bVar) {
        C3511p.m11976a(3, "JavaScriptBridge", (Object) this, "Stopping view update loop");
        if (bVar != null) {
            C3493i.m11885a().m11894a(bVar);
        }
    }

    private void m11907d(String str) {
        if (this.f8936k.size() >= 50) {
            this.f8936k.subList(0, 25).clear();
        }
        this.f8936k.add(str);
    }

    private void m11908e() {
        C3511p.m11976a(3, "JavaScriptBridge", (Object) this, "Stopping metadata reporting loop");
        C3493i.m11885a().m11895a(this);
        LocalBroadcastManager.getInstance(C3515s.m11989c()).unregisterReceiver(this.f8938m);
    }

    private boolean m11910e(String str) {
        if (this.f8926a) {
            return true;
        }
        C3511p.m11976a(6, "JavaScriptBridge", (Object) this, "Bridge is not installed in the given WebView. Can't " + str);
        return false;
    }

    private void m11912f() {
        C3511p.m11976a(3, "JavaScriptBridge", (Object) this, "Cleaning up");
        m11908e();
        for (Entry key : this.f8931f.entrySet()) {
            m11905d((b) key.getKey());
        }
        this.f8931f.clear();
        LocalBroadcastManager.getInstance(C3515s.m11989c()).unregisterReceiver(this.f8939n);
    }

    private void m11913f(String str) {
        if (m11915g()) {
            m11916h().loadUrl(str);
        }
    }

    private boolean m11915g() {
        return m11916h() != null;
    }

    private WebView m11916h() {
        return (WebView) this.f8930e.get();
    }

    private String m11917i() {
        try {
            Map hashMap = new HashMap();
            String str = "3f2ae9c1894282b5e0222f0d06bbf457191f816f";
            str = "VNG";
            str = "2.2.0";
            String a = this.f8935j.m11983a();
            String b = this.f8935j.m11984b();
            String num = Integer.toString(VERSION.SDK_INT);
            String b2 = C3515s.m11988b();
            Object obj = this.f8937l == C3497a.WEBVIEW ? SchemaSymbols.ATTVAL_FALSE_0 : SchemaSymbols.ATTVAL_TRUE_1;
            hashMap.put("versionHash", "3f2ae9c1894282b5e0222f0d06bbf457191f816f");
            hashMap.put("appName", a);
            hashMap.put(FavaDiagnosticsEntity.EXTRA_NAMESPACE, "VNG");
            hashMap.put("version", "2.2.0");
            hashMap.put("deviceOS", num);
            hashMap.put("isNative", obj);
            hashMap.put("appId", b);
            if (b2 != null) {
                hashMap.put("aqzx", b2);
            }
            return new JSONObject(hashMap).toString();
        } catch (Exception e) {
            return "{}";
        }
    }

    void m11918a() {
        C3511p.m11976a(3, "JavaScriptBridge", (Object) this, "webViewReady");
        this.f8933h.compareAndSet(false, true);
        m11908e();
        for (String f : this.f8936k) {
            m11913f(f);
        }
        this.f8936k.clear();
    }

    void m11919a(b bVar) {
        if (bVar != null) {
            C3511p.m11976a(3, "JavaScriptBridge", (Object) this, "adding tracker" + bVar.b);
            this.f8931f.put(bVar, "");
        }
    }

    void m11920a(String str) {
        String format = String.format("javascript: MoatMAK.crts(%s)", new Object[]{str});
        if (this.f8933h.get()) {
            m11913f(format);
        } else {
            m11907d(format);
        }
    }

    void m11921a(String str, JSONObject jSONObject) {
        String jSONObject2 = jSONObject.toString();
        if (this.f8933h.get() && m11915g()) {
            m11913f(String.format("javascript:%s.dispatchEvent(%s);", new Object[]{str, jSONObject2}));
            return;
        }
        this.f8932g.add(jSONObject2);
    }

    void m11922b(String str) {
        C3511p.m11976a(3, "JavaScriptBridge", (Object) this, "markUserInteractionEvent:" + str);
        String format = String.format("javascript: MoatMAK.ucbx(%s)", new Object[]{str});
        if (this.f8933h.get()) {
            m11913f(format);
        } else {
            m11907d(format);
        }
    }

    boolean m11923b(b bVar) {
        try {
            if (!m11915g() || !m11899a(m11916h(), "startTracking") || !m11910e("startTracking")) {
                return false;
            }
            if (bVar.d() == null) {
                if (bVar.c) {
                    C3511p.m11976a(3, "JavaScriptBridge", (Object) this, "Tracker subject is null at start");
                } else {
                    C3511p.m11976a(3, "JavaScriptBridge", (Object) this, "Tracker subject is null, won't start tracking");
                    return false;
                }
            }
            C3511p.m11976a(3, "JavaScriptBridge", (Object) this, "Starting tracking on tracker" + bVar.b);
            m11913f(String.format("javascript: MoatMAK.mqjh(\"%s\")", new Object[]{bVar.b}));
            C3493i.m11885a().m11892a(C3515s.m11989c(), bVar);
            return true;
        } catch (Throwable e) {
            C3511p.m11977a("JavaScriptBridge", (Object) this, "Failed to initialize impression start.", e);
            return false;
        }
    }

    void m11924c(String str) {
        int i;
        C3511p.m11976a(3, "JavaScriptBridge", (Object) this, "flushDispatchQueue");
        this.f8933h.compareAndSet(false, true);
        if (this.f8932g.size() >= 200) {
            int i2;
            LinkedList linkedList = new LinkedList();
            for (i2 = 0; i2 < 10; i2++) {
                linkedList.addFirst((String) this.f8932g.removeFirst());
            }
            i2 = Math.min(Math.min(this.f8932g.size() / 200, 10) + 200, this.f8932g.size());
            for (i = 0; i < i2; i++) {
                this.f8932g.removeFirst();
            }
            Iterator it = linkedList.iterator();
            while (it.hasNext()) {
                this.f8932g.addFirst((String) it.next());
            }
        }
        i = 0;
        while (!this.f8932g.isEmpty() && i < 200) {
            i++;
            String str2 = "javascript:%s.dispatchMany([%s])";
            StringBuilder stringBuilder = new StringBuilder();
            boolean z = true;
            while (!this.f8932g.isEmpty() && i < 200) {
                int i3 = i + 1;
                String str3 = (String) this.f8932g.getFirst();
                if (stringBuilder.length() + str3.length() > 2000) {
                    i = i3;
                    break;
                }
                this.f8932g.removeFirst();
                if (z) {
                    z = false;
                } else {
                    stringBuilder.append(",");
                }
                stringBuilder.append(str3);
                i = i3;
            }
            m11913f(String.format(str2, new Object[]{str, stringBuilder.toString()}));
        }
        this.f8932g.clear();
    }

    boolean m11925c(b bVar) {
        boolean z = true;
        if (m11915g() && m11899a(m11916h(), "stopTracking") && m11910e("stopTracking")) {
            try {
                C3511p.m11976a(3, "JavaScriptBridge", (Object) this, "Ending tracking on tracker" + bVar.b);
                m11913f(String.format("javascript: MoatMAK.egpw(\"%s\")", new Object[]{bVar.b}));
            } catch (Throwable e) {
                C3511p.m11977a("JavaScriptBridge", (Object) this, "Failed to end impression.", e);
            }
        } else {
            z = false;
        }
        if (this.f8937l == C3497a.NATIVE_DISPLAY) {
            m11905d(bVar);
        } else {
            m11912f();
        }
        this.f8931f.remove(bVar);
        return z;
    }

    protected void finalize() {
        try {
            super.finalize();
            C3511p.m11976a(3, "JavaScriptBridge", (Object) this, "finalize");
            m11912f();
        } catch (Exception e) {
            C3502m.m11942a(e);
        }
    }
}
