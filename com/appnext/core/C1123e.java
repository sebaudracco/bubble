package com.appnext.core;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class C1123e {
    private static final long ll = 8000;
    private static final long lm = 15000;
    @SuppressLint({"StaticFieldLeak"})
    private static C1123e lu;
    private Context context;
    private Handler handler = new Handler(Looper.getMainLooper());
    private WebView ln;
    private WebView lo;
    private C0899a lp = new C11171(this);
    private Runnable lq = new C11182(this);
    private Intent lr;
    private final ArrayList<C1122b> ls = new ArrayList();
    private int lt = 0;

    public interface C0899a {
        void error(String str);

        void onMarket(String str);
    }

    class C11171 implements C0899a {
        final /* synthetic */ C1123e lv;

        class C11161 extends WebViewClient {
            final /* synthetic */ C11171 lw;

            C11161(C11171 c11171) {
                this.lw = c11171;
            }

            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                if (str == null || str.contains("about:blank")) {
                    return false;
                }
                webView.loadUrl(str);
                return true;
            }
        }

        C11171(C1123e c1123e) {
            this.lv = c1123e;
        }

        public void onMarket(String str) {
            if (this.lv.ls.size() != 0) {
                if (((C1122b) this.lv.ls.get(0)).lp != null) {
                    ((C1122b) this.lv.ls.get(0)).lp.onMarket(str);
                }
                try {
                    String str2 = "https://admin.appnext.com/tools/navtac.html?bid=" + ((C1122b) this.lv.ls.get(0)).lB + "&guid=" + C1128g.m2357p("admin.appnext.com", "applink") + "&url=" + URLEncoder.encode(str, "UTF-8");
                    if (this.lv.lo == null) {
                        this.lv.lo = new WebView(this.lv.context);
                        this.lv.lo.getSettings().setJavaScriptEnabled(true);
                        this.lv.lo.getSettings().setDomStorageEnabled(true);
                        if (VERSION.SDK_INT >= 21) {
                            this.lv.lo.getSettings().setMixedContentMode(0);
                        }
                        this.lv.lo.setWebViewClient(new C11161(this));
                    }
                    this.lv.lo.loadUrl("about:blank");
                    this.lv.lo.loadUrl(str2);
                    if (this.lv.handler != null) {
                        this.lv.handler.removeCallbacksAndMessages(null);
                    }
                    this.lv.cS();
                } catch (UnsupportedEncodingException e) {
                    this.lv.cS();
                }
            }
        }

        public void error(String str) {
            if (this.lv.ls.size() != 0) {
                if (((C1122b) this.lv.ls.get(0)).lp != null) {
                    ((C1122b) this.lv.ls.get(0)).lp.error(str);
                }
                this.lv.cS();
            }
        }
    }

    class C11182 implements Runnable {
        final /* synthetic */ C1123e lv;

        C11182(C1123e c1123e) {
            this.lv = c1123e;
        }

        public void run() {
            if (this.lv.lp == null || this.lv.ln == null) {
                this.lv.cS();
                return;
            }
            this.lv.lp.error(this.lv.ln.getUrl());
            this.lv.ln.stopLoading();
        }
    }

    class C11193 extends WebViewClient {
        final /* synthetic */ C1123e lv;

        C11193(C1123e c1123e) {
            this.lv = c1123e;
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            if (str == null) {
                return false;
            }
            C1128g.m2333W("redirect url: " + str);
            if (str.startsWith("https://play.google.com/store/apps/")) {
                str = str.replace("https://play.google.com/store/apps/", "market://");
            }
            if (str.contains("about:blank")) {
                return false;
            }
            if (str.startsWith("http://") || str.startsWith("https://")) {
                if (this.lv.hasNewResolver(this.lv.aK(str).setComponent(null))) {
                    this.lv.handler.removeCallbacksAndMessages(null);
                    if (this.lv.lp != null) {
                        this.lv.lp.onMarket(str);
                    }
                    this.lv.handler.removeCallbacks(this.lv.lq);
                    return true;
                }
                webView.loadUrl(str);
                return true;
            } else if (str.startsWith("intent://")) {
                try {
                    r2 = Intent.parseUri(str, 1);
                    if (this.lv.context.getPackageManager().resolveActivity(r2, 65536) != null) {
                        this.lv.handler.removeCallbacksAndMessages(null);
                        if (this.lv.lp != null) {
                            this.lv.lp.onMarket(r2.getData().toString());
                        }
                        this.lv.handler.removeCallbacks(this.lv.lq);
                        return true;
                    }
                    String string;
                    if (r2.getExtras() != null && r2.getExtras().containsKey("browser_fallback_url") && !r2.getExtras().getString("browser_fallback_url").equals("")) {
                        string = r2.getExtras().getString("browser_fallback_url");
                    } else if (!r2.getExtras().containsKey("market_referrer") || r2.getExtras().getString("market_referrer").equals("")) {
                        this.lv.handler.removeCallbacksAndMessages(null);
                        if (this.lv.lp != null) {
                            this.lv.lp.error(str);
                        }
                        return true;
                    } else {
                        string = "market://details?id=" + r2.getPackage() + "&referrer=" + r2.getExtras().getString("market_referrer");
                    }
                    this.lv.handler.removeCallbacksAndMessages(null);
                    if (this.lv.lp != null) {
                        this.lv.lp.onMarket(string);
                    }
                    return true;
                } catch (Throwable th) {
                    C1128g.m2351c(th);
                    return false;
                }
            } else {
                r2 = new Intent("android.intent.action.VIEW");
                r2.setData(Uri.parse(str));
                try {
                    if (this.lv.context.getPackageManager().queryIntentActivities(r2, 0).size() > 0) {
                        this.lv.handler.removeCallbacksAndMessages(null);
                        if (this.lv.lp != null) {
                            this.lv.lp.onMarket(str);
                        }
                        this.lv.handler.removeCallbacks(this.lv.lq);
                        return true;
                    }
                    webView.loadUrl(str);
                    return false;
                } catch (Throwable th2) {
                    C1128g.m2351c(th2);
                    return false;
                }
            }
        }
    }

    private class C1122b {
        String bW;
        String lB;
        long lC;
        C0899a lp;
        final /* synthetic */ C1123e lv;

        C1122b(C1123e c1123e, String str, String str2, C0899a c0899a, long j) {
            this.lv = c1123e;
            this.bW = str;
            this.lp = c0899a;
            this.lB = str2;
            this.lC = j;
        }
    }

    public static C1123e m2320s(Context context) {
        if (lu == null) {
            lu = new C1123e(context);
        }
        return lu;
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    private C1123e(Context context) {
        this.context = context.getApplicationContext();
    }

    protected void finalize() throws Throwable {
        super.finalize();
        destroy();
    }

    public void m2321a(String str, String str2, C0899a c0899a) {
        m2322a(str, str2, c0899a, ll);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void m2322a(java.lang.String r11, java.lang.String r12, com.appnext.core.C1123e.C0899a r13, long r14) {
        /*
        r10 = this;
        r8 = r10.ls;
        monitor-enter(r8);
        r0 = r10.context;	 Catch:{ all -> 0x0027 }
        if (r0 != 0) goto L_0x0009;
    L_0x0007:
        monitor-exit(r8);	 Catch:{ all -> 0x0027 }
    L_0x0008:
        return;
    L_0x0009:
        if (r11 == 0) goto L_0x0042;
    L_0x000b:
        r0 = r10.ls;	 Catch:{ all -> 0x0027 }
        r1 = r0.iterator();	 Catch:{ all -> 0x0027 }
    L_0x0011:
        r0 = r1.hasNext();	 Catch:{ all -> 0x0027 }
        if (r0 == 0) goto L_0x002a;
    L_0x0017:
        r0 = r1.next();	 Catch:{ all -> 0x0027 }
        r0 = (com.appnext.core.C1123e.C1122b) r0;	 Catch:{ all -> 0x0027 }
        r0 = r0.bW;	 Catch:{ all -> 0x0027 }
        r0 = r0.equals(r11);	 Catch:{ all -> 0x0027 }
        if (r0 == 0) goto L_0x0011;
    L_0x0025:
        monitor-exit(r8);	 Catch:{ all -> 0x0027 }
        goto L_0x0008;
    L_0x0027:
        r0 = move-exception;
        monitor-exit(r8);	 Catch:{ all -> 0x0027 }
        throw r0;
    L_0x002a:
        r0 = "&ox=0";
        r0 = r11.endsWith(r0);	 Catch:{ all -> 0x0027 }
        if (r0 == 0) goto L_0x0060;
    L_0x0033:
        r0 = r10.ls;	 Catch:{ all -> 0x0027 }
        r1 = new com.appnext.core.e$b;	 Catch:{ all -> 0x0027 }
        r2 = r10;
        r3 = r11;
        r4 = r12;
        r5 = r13;
        r6 = r14;
        r1.<init>(r2, r3, r4, r5, r6);	 Catch:{ all -> 0x0027 }
        r0.add(r1);	 Catch:{ all -> 0x0027 }
    L_0x0042:
        r0 = r10.ls;	 Catch:{ all -> 0x0027 }
        r0 = r0.size();	 Catch:{ all -> 0x0027 }
        if (r0 <= 0) goto L_0x005e;
    L_0x004a:
        r0 = r10.lt;	 Catch:{ all -> 0x0027 }
        r1 = 1;
        if (r0 == r1) goto L_0x005e;
    L_0x004f:
        r0 = 1;
        r10.lt = r0;	 Catch:{ all -> 0x0027 }
        r0 = r10.ls;	 Catch:{ all -> 0x0027 }
        r1 = 0;
        r0 = r0.get(r1);	 Catch:{ all -> 0x0027 }
        r0 = (com.appnext.core.C1123e.C1122b) r0;	 Catch:{ all -> 0x0027 }
        r10.m2311a(r0);	 Catch:{ all -> 0x0027 }
    L_0x005e:
        monitor-exit(r8);	 Catch:{ all -> 0x0027 }
        goto L_0x0008;
    L_0x0060:
        r0 = 0;
        r10.lt = r0;	 Catch:{ all -> 0x0027 }
        r0 = r10.ls;	 Catch:{ all -> 0x0027 }
        r0 = r0.size();	 Catch:{ all -> 0x0027 }
        if (r0 <= 0) goto L_0x0085;
    L_0x006b:
        r0 = r10.ls;	 Catch:{ all -> 0x0027 }
        r1 = 0;
        r0 = r0.get(r1);	 Catch:{ all -> 0x0027 }
        r0 = (com.appnext.core.C1123e.C1122b) r0;	 Catch:{ all -> 0x0027 }
        r0 = r0.bW;	 Catch:{ all -> 0x0027 }
        r1 = "&ox=0";
        r0 = r0.endsWith(r1);	 Catch:{ all -> 0x0027 }
        if (r0 != 0) goto L_0x0085;
    L_0x007f:
        r0 = r10.ls;	 Catch:{ all -> 0x0027 }
        r1 = 0;
        r0.remove(r1);	 Catch:{ all -> 0x0027 }
    L_0x0085:
        r0 = r10.ls;	 Catch:{ all -> 0x0027 }
        r9 = 0;
        r1 = new com.appnext.core.e$b;	 Catch:{ all -> 0x0027 }
        r2 = r10;
        r3 = r11;
        r4 = r12;
        r5 = r13;
        r6 = r14;
        r1.<init>(r2, r3, r4, r5, r6);	 Catch:{ all -> 0x0027 }
        r0.add(r9, r1);	 Catch:{ all -> 0x0027 }
        goto L_0x0042;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appnext.core.e.a(java.lang.String, java.lang.String, com.appnext.core.e$a, long):void");
    }

    private void cS() {
        this.lt = 0;
        if (this.ls.size() != 0) {
            ((C1122b) this.ls.get(0)).lp = null;
            this.ls.remove(0);
            m2321a(null, null, null);
        }
    }

    public void destroy() {
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    private void m2311a(C1122b c1122b) {
        try {
            this.handler.removeCallbacksAndMessages(null);
            if (this.ln == null) {
                this.ln = new WebView(this.context);
                this.ln.getSettings().setJavaScriptEnabled(true);
                this.ln.getSettings().setDomStorageEnabled(true);
                if (VERSION.SDK_INT >= 21) {
                    this.ln.getSettings().setMixedContentMode(0);
                }
                this.ln.setWebViewClient(new C11193(this));
            }
            this.ln.stopLoading();
            this.ln.loadUrl("about:blank");
            this.lr = new Intent(aK(c1122b.bW)).setComponent(null);
            if (VERSION.SDK_INT >= 15) {
                Intent selector = this.lr.getSelector();
                if (selector != null) {
                    selector.setComponent(null);
                }
            }
            this.ln.loadUrl(c1122b.bW);
            C1128g.m2333W("appurl: " + c1122b.bW);
            this.handler.postDelayed(this.lq, c1122b.bW.endsWith("&ox=0") ? lm : c1122b.lC);
        } catch (Throwable th) {
            if (this.lp != null) {
                this.lp.error(c1122b.bW);
            }
            cS();
        }
    }

    public void m2324e(final AppnextAd appnextAd) {
        new Thread(new Runnable(this) {
            final /* synthetic */ C1123e lv;

            public void run() {
                try {
                    C1128g.m2336a(appnextAd.getImpressionURL(), null);
                } catch (Throwable th) {
                }
            }
        }).start();
    }

    public void m2323a(String str, String str2, String str3, String str4, String str5, String str6) {
        final String str7 = str;
        final String str8 = str2;
        final String str9 = str3;
        final String str10 = str4;
        final String str11 = str5;
        final String str12 = str6;
        new Thread(new Runnable(this) {
            final /* synthetic */ C1123e lv;

            public void run() {
                try {
                    HashMap hashMap = new HashMap();
                    hashMap.put("guid", str7);
                    hashMap.put("bannerId", str8);
                    hashMap.put("placementId", str9);
                    hashMap.put("vid", str10);
                    hashMap.put("url", str11);
                    C1128g.m2336a("https://admin.appnext.com/AdminService.asmx/" + str12, hashMap);
                } catch (Throwable th) {
                }
            }
        }).start();
    }

    private List m2312b(Context context, Intent intent) {
        List<ResolveInfo> queryIntentActivities = context.getPackageManager().queryIntentActivities(intent, 0);
        List arrayList = new ArrayList();
        for (ResolveInfo resolveInfo : queryIntentActivities) {
            arrayList.add(new ComponentName(resolveInfo.activityInfo.packageName, resolveInfo.activityInfo.name));
        }
        return arrayList;
    }

    public final boolean hasNewResolver(Intent intent) {
        if (this.lr == null) {
            boolean z;
            if (intent != null) {
                z = true;
            } else {
                z = false;
            }
            return z;
        } else if (intent == null) {
            return false;
        } else {
            List<ComponentName> b = m2312b(this.context, intent);
            HashSet hashSet = new HashSet();
            hashSet.addAll(m2312b(this.context, this.lr));
            for (ComponentName contains : b) {
                if (!hashSet.contains(contains)) {
                    return true;
                }
            }
            return false;
        }
    }

    private Intent aK(String str) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse(str));
        return intent;
    }
}
