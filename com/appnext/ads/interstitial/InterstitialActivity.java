package com.appnext.ads.interstitial;

import android.content.Intent;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Handler;
import android.util.Log;
import android.view.ViewGroup;
import android.webkit.ConsoleMessage;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.appnext.C0889R;
import com.appnext.ads.AdsError;
import com.appnext.ads.C0893a;
import com.appnext.ads.C0895b;
import com.appnext.core.Ad;
import com.appnext.core.AppnextActivity;
import com.appnext.core.AppnextAd;
import com.appnext.core.AppnextError;
import com.appnext.core.C0919c.C0914a;
import com.appnext.core.C0921q;
import com.appnext.core.C1123e.C0899a;
import com.appnext.core.C1128g;
import com.appnext.core.C1149r.C0897a;
import com.appnext.core.webview.AppnextWebView;
import com.appnext.core.webview.AppnextWebView.C0963c;
import com.appnext.core.webview.WebAppInterface;
import com.google.ads.mediation.facebook.FacebookAdapter;
import java.util.ArrayList;
import java.util.Locale;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;
import org.json.JSONException;
import org.json.JSONObject;

public class InterstitialActivity extends AppnextActivity {
    private ArrayList<AppnextAd> aL;
    private Boolean autoPlay;
    private Boolean canClose;
    private boolean closed = false;
    private AppnextAd dB;
    private AppnextAd dC;
    private C0895b dD;
    Runnable dG = new Runnable(this) {
        final /* synthetic */ InterstitialActivity eX;

        class C09621 implements Runnable {
            final /* synthetic */ AnonymousClass12 fa;

            C09621(AnonymousClass12 anonymousClass12) {
                this.fa = anonymousClass12;
            }

            public void run() {
                try {
                    C1128g.m2336a("https://www.fqtag.com/pixel.cgi?org=TkBXEI5C3FBIr4zXwnmK&p=" + this.fa.eX.placementID + "&a=" + this.fa.eX.dB.getBannerID() + "&cmp=" + this.fa.eX.dB.getCampaignID() + "&fmt=banner&dmn=" + this.fa.eX.dB.getAdPackage() + "&ad=&rt=displayImg&gid=" + C1128g.m2358u(this.fa.eX) + "&aid=&applng=" + Locale.getDefault().toString() + "&app=" + this.fa.eX.getPackageName() + "&c1=100&c2=" + Interstitial.currentAd.getTID() + "&c3=" + Interstitial.currentAd.getAUID() + "&c4=" + Interstitial.currentAd.getVID() + "&sl=1&fq=1", null);
                } catch (Throwable th) {
                    C1128g.m2351c(th);
                }
            }
        }

        {
            this.eX = r1;
        }

        public void run() {
            if (this.eX.userAction != null) {
                this.eX.userAction.m2398e(this.eX.dB);
                this.eX.report(C0893a.cL);
            }
            try {
                if (Boolean.parseBoolean(this.eX.getConfig().get("fq_control")) && Interstitial.currentAd.fq_status) {
                    new Thread(new C09621(this)).start();
                }
            } catch (Throwable th) {
            }
        }
    };
    Runnable dH = new C09652(this);
    protected WebView eL;
    private boolean eM = false;
    private Interstitial eN;
    private String eO = "";
    private int eP = 0;
    private Handler eQ;
    private C0899a eR;
    private WebAppInterface eS;
    private boolean eT = false;
    private boolean eU = false;
    private String eV = "";
    private Runnable eW = new Runnable(this) {
        final /* synthetic */ InterstitialActivity eX;

        {
            this.eX = r1;
        }

        public void run() {
            this.eX.m1971F();
        }
    };
    private Boolean mute;

    class C09641 implements C0963c {
        final /* synthetic */ InterstitialActivity eX;

        C09641(InterstitialActivity interstitialActivity) {
            this.eX = interstitialActivity;
        }

        public void mo1985c(String str) {
            this.eX.aq();
        }

        public void error(String str) {
            this.eX.aq();
        }
    }

    class C09652 implements Runnable {
        final /* synthetic */ InterstitialActivity eX;

        C09652(InterstitialActivity interstitialActivity) {
            this.eX = interstitialActivity;
        }

        public void run() {
            this.eX.mo1936a(this.eX.dB, null);
        }
    }

    class C09684 implements C0914a {
        final /* synthetic */ InterstitialActivity eX;

        C09684(InterstitialActivity interstitialActivity) {
            this.eX = interstitialActivity;
        }

        public <T> void mo1971a(T t) {
            if (t == null) {
                try {
                    Log.d("appnext SDK", "IA GAL 1");
                    this.eX.finish();
                    this.eX.onError(AppnextError.NO_ADS);
                } catch (Throwable th) {
                    this.eX.finish();
                    this.eX.onError(AppnextError.INTERNAL_ERROR);
                    C1128g.m2351c(th);
                    this.eX.m2010b(this.eX.placementID, C1128g.m2346b(th), "InterstitialActivity_error");
                }
            } else if (((ArrayList) t).size() == 0) {
                Log.d("appnext SDK", "IA GAL 2");
                this.eX.finish();
                this.eX.onError(AppnextError.NO_ADS);
            } else {
                ArrayList b = C0977a.au().m2026b(this.eX, this.eX.eN, this.eX.eO);
                if (b == null) {
                    Log.d("appnext SDK", "IA GAL 3");
                    this.eX.finish();
                    this.eX.onError(AppnextError.NO_ADS);
                    return;
                }
                String d = C0977a.au().mo1998d(b);
                if (d == null) {
                    Log.d("appnext SDK", "IA GAL 4");
                    this.eX.finish();
                    this.eX.onError(AppnextError.NO_ADS);
                    return;
                }
                Object buttonText;
                String replace = d.replace(" ", "\\u2028").replace(" ", "\\u2029");
                JSONObject jSONObject = new JSONObject();
                if (this.eX.hasVideo((AppnextAd) b.get(0))) {
                    jSONObject.put("remote_auto_play", "" + this.eX.autoPlay);
                } else {
                    jSONObject.put("remote_auto_play", SchemaSymbols.ATTVAL_FALSE);
                }
                InterstitialAd interstitialAd = new InterstitialAd((AppnextAd) b.get(0));
                if (!interstitialAd.getButtonText().equals("")) {
                    buttonText = interstitialAd.getButtonText();
                } else if (C1128g.m2356h(this.eX, interstitialAd.getAdPackage())) {
                    buttonText = this.eX.m1972O("existing_button_text");
                } else {
                    buttonText = this.eX.m1972O("new_button_text");
                }
                jSONObject.put("b_title", buttonText);
                this.eX.dB = (AppnextAd) b.get(0);
                this.eX.m1981b("Appnext.setParams(" + jSONObject.toString() + ");");
                this.eX.m1981b("Appnext.loadInterstitial(" + replace + ");");
                C0977a.au().mo1976a(this.eX.dB.getBannerID(), Interstitial.currentAd);
                if (Interstitial.currentAd.getOnAdOpenedCallback() != null) {
                    Interstitial.currentAd.getOnAdOpenedCallback().adOpened();
                }
            }
        }

        public void error(final String str) {
            this.eX.runOnUiThread(new Runnable(this) {
                final /* synthetic */ C09684 eZ;

                public void run() {
                    C1128g.m2333W("ads error");
                    this.eZ.eX.m2010b(this.eZ.eX.placementID, str, "ads error");
                    this.eZ.eX.onError(str);
                    this.eZ.eX.finish();
                }
            });
        }
    }

    class C09695 implements C0899a {
        final /* synthetic */ InterstitialActivity eX;

        C09695(InterstitialActivity interstitialActivity) {
            this.eX = interstitialActivity;
        }

        public void onMarket(String str) {
            C1128g.m2333W("marketUrl " + str);
            if (this.eX.handler != null) {
                this.eX.handler.removeCallbacks(null);
            }
            this.eX.cQ();
        }

        public void error(String str) {
            C1128g.m2333W("---------------------- error ----------------------");
            if (this.eX.handler != null) {
                this.eX.handler.removeCallbacks(null);
            }
            this.eX.cQ();
            this.eX.m2010b(this.eX.placementID, new InterstitialAd(this.eX.dC).getAppURL() + " " + str, C0893a.cl);
        }
    }

    class C09706 implements C0897a {
        final /* synthetic */ InterstitialActivity eX;

        C09706(InterstitialActivity interstitialActivity) {
            this.eX = interstitialActivity;
        }

        public void report(String str) {
        }

        public Ad ac() {
            return Interstitial.currentAd;
        }

        public AppnextAd ad() {
            return this.eX.dC;
        }

        public C0921q ae() {
            return this.eX.getConfig();
        }
    }

    class C09717 implements Runnable {
        final /* synthetic */ InterstitialActivity eX;

        C09717(InterstitialActivity interstitialActivity) {
            this.eX = interstitialActivity;
        }

        public void run() {
            this.eX.eV = C1128g.m2358u(this.eX);
        }
    }

    class C09728 extends WebViewClient {
        final /* synthetic */ InterstitialActivity eX;

        C09728(InterstitialActivity interstitialActivity) {
            this.eX = interstitialActivity;
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            if (str == null) {
                return false;
            }
            if (!str.startsWith("http")) {
                return super.shouldOverrideUrlLoading(webView, str);
            }
            webView.loadUrl(str);
            return true;
        }

        public void onPageFinished(WebView webView, String str) {
            super.onPageFinished(webView, str);
            this.eX.eQ.removeCallbacksAndMessages(null);
            this.eX.m1971F();
        }
    }

    class C09739 extends WebChromeClient {
        final /* synthetic */ InterstitialActivity eX;

        C09739(InterstitialActivity interstitialActivity) {
            this.eX = interstitialActivity;
        }

        public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
            C1128g.m2333W("console " + consoleMessage.message());
            if (!consoleMessage.message().contains("pause") && (consoleMessage.message().contains("TypeError") || consoleMessage.message().contains("has no method") || consoleMessage.message().contains("is not a function"))) {
                this.eX.onError(AppnextError.INTERNAL_ERROR);
                this.eX.finish();
            }
            return true;
        }
    }

    protected class WebInterface extends WebAppInterface {
        final /* synthetic */ InterstitialActivity eX;

        class C09741 implements Runnable {
            final /* synthetic */ WebInterface fb;

            C09741(WebInterface webInterface) {
                this.fb = webInterface;
            }

            public void run() {
                this.fb.eX.onClose();
                this.fb.eX.finish();
            }
        }

        class C09752 implements Runnable {
            final /* synthetic */ WebInterface fb;

            C09752(WebInterface webInterface) {
                this.fb = webInterface;
            }

            public void run() {
                this.fb.eX.onError(AdsError.AD_NOT_READY);
                this.fb.eX.finish();
            }
        }

        public WebInterface(InterstitialActivity interstitialActivity) {
            this.eX = interstitialActivity;
            super(interstitialActivity);
        }

        @JavascriptInterface
        public void destroy(String str) {
            if (str.equals("close")) {
                this.eX.runOnUiThread(new C09741(this));
            } else {
                this.eX.runOnUiThread(new C09752(this));
            }
        }

        @JavascriptInterface
        public void notifyImpression(String str) {
            super.notifyImpression(str);
            this.eX.handler.postDelayed(this.eX.dG, Long.parseLong(this.eX.getConfig().get("postpone_impression_sec")) * 1000);
            if (this.eX.autoPlay.booleanValue()) {
                play();
            }
        }

        @JavascriptInterface
        public void postView(String str) {
            if (Boolean.parseBoolean(this.eX.aL != null ? SchemaSymbols.ATTVAL_FALSE : this.eX.m1972O("pview"))) {
                this.eX.handler.postDelayed(this.eX.dH, Long.parseLong(this.eX.getConfig().get("postpone_vta_sec")) * 1000);
            }
        }

        @JavascriptInterface
        public void openStore(final String str) {
            this.eX.runOnUiThread(new Runnable(this) {
                final /* synthetic */ WebInterface fb;

                public void run() {
                    this.fb.eX.m1973P(str);
                }
            });
        }

        @JavascriptInterface
        public void play() {
            this.eX.m2010b(this.eX.placementID, "", C0893a.ci);
            this.eX.play();
        }

        @JavascriptInterface
        public void videoPlayed() {
        }

        @JavascriptInterface
        public String filterAds(String str) {
            return str;
        }

        @JavascriptInterface
        public String loadAds() {
            return "";
        }

        @JavascriptInterface
        public void openLink(String str) {
            this.eX.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
        }

        @JavascriptInterface
        public void gotoAppWall() {
        }

        @JavascriptInterface
        public void jsError(String str) {
            if (!str.contains("is not a function") && !str.contains("has no method")) {
                C1128g.m2333W("jsError " + str);
                this.eX.onError(AppnextError.INTERNAL_ERROR);
                this.eX.finish();
            } else if (this.eX.eP = this.eX.eP + 1 < 5) {
                this.eX.eQ.postDelayed(this.eX.eW, 500);
            } else {
                this.eX.onError(AppnextError.INTERNAL_ERROR);
                this.eX.finish();
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected void onCreate(android.os.Bundle r8) {
        /*
        r7 = this;
        r6 = 7;
        r3 = 2;
        r5 = 6;
        r1 = -1;
        r2 = 1;
        r0 = com.appnext.ads.interstitial.Interstitial.currentAd;
        if (r0 == 0) goto L_0x0027;
    L_0x0009:
        r0 = r7.getIntent();
        r4 = "orientation";
        r0 = r0.getBooleanExtra(r4, r2);
        if (r0 == 0) goto L_0x0027;
    L_0x0016:
        r0 = com.appnext.ads.interstitial.Interstitial.currentAd;
        r0 = r0.getOrientation();
        r4 = r0.hashCode();
        switch(r4) {
            case 729267099: goto L_0x0053;
            case 1430647483: goto L_0x0048;
            case 1673671211: goto L_0x003d;
            case 2129065206: goto L_0x0032;
            default: goto L_0x0023;
        };
    L_0x0023:
        r0 = r1;
    L_0x0024:
        switch(r0) {
            case 0: goto L_0x005e;
            case 1: goto L_0x005e;
            case 2: goto L_0x0072;
            case 3: goto L_0x0076;
            default: goto L_0x0027;
        };
    L_0x0027:
        super.onCreate(r8);
        r0 = com.appnext.ads.interstitial.Interstitial.currentAd;
        if (r0 != 0) goto L_0x007a;
    L_0x002e:
        r7.finish();
    L_0x0031:
        return;
    L_0x0032:
        r4 = "not_set";
        r0 = r0.equals(r4);
        if (r0 == 0) goto L_0x0023;
    L_0x003b:
        r0 = 0;
        goto L_0x0024;
    L_0x003d:
        r4 = "automatic";
        r0 = r0.equals(r4);
        if (r0 == 0) goto L_0x0023;
    L_0x0046:
        r0 = r2;
        goto L_0x0024;
    L_0x0048:
        r4 = "landscape";
        r0 = r0.equals(r4);
        if (r0 == 0) goto L_0x0023;
    L_0x0051:
        r0 = r3;
        goto L_0x0024;
    L_0x0053:
        r4 = "portrait";
        r0 = r0.equals(r4);
        if (r0 == 0) goto L_0x0023;
    L_0x005c:
        r0 = 3;
        goto L_0x0024;
    L_0x005e:
        r0 = r7.getResources();
        r0 = r0.getConfiguration();
        r0 = r0.orientation;
        if (r0 != r3) goto L_0x006e;
    L_0x006a:
        r7.setRequestedOrientation(r5);
        goto L_0x0027;
    L_0x006e:
        r7.setRequestedOrientation(r6);
        goto L_0x0027;
    L_0x0072:
        r7.setRequestedOrientation(r5);
        goto L_0x0027;
    L_0x0076:
        r7.setRequestedOrientation(r6);
        goto L_0x0027;
    L_0x007a:
        r0 = com.appnext.ads.interstitial.Interstitial.currentAd;
        r7.eN = r0;
        r0 = r7.getRequestedOrientation();
        if (r0 != r5) goto L_0x01bd;
    L_0x0084:
        r0 = "loaded_landscape";
        r7.report(r0);
    L_0x008a:
        r0 = new android.widget.RelativeLayout;
        r0.<init>(r7);
        r7.lc = r0;
        r0 = r7.lc;
        r7.setContentView(r0);
        r0 = r7.lc;
        r0 = r0.getLayoutParams();
        r0.width = r1;
        r0 = r7.lc;
        r0 = r0.getLayoutParams();
        r0.height = r1;
        r0 = r7.lc;
        r1 = -16777216; // 0xffffffffff000000 float:-1.7014118E38 double:NaN;
        r0.setBackgroundColor(r1);
        r0 = r7.getIntent();
        r0 = r0.getExtras();
        r1 = "id";
        r0 = r0.getString(r1);
        r7.placementID = r0;
        r0 = r7.getIntent();
        r1 = "auto_play";
        r0 = r0.hasExtra(r1);
        if (r0 == 0) goto L_0x00ea;
    L_0x00cb:
        r0 = r7.getIntent();
        r1 = "auto_play";
        r0 = r0.getBooleanExtra(r1, r2);
        r0 = java.lang.Boolean.valueOf(r0);
        r7.autoPlay = r0;
        r0 = r7.autoPlay;
        r0 = r0.booleanValue();
        if (r0 == 0) goto L_0x01c5;
    L_0x00e4:
        r0 = "auto_play_on";
        r7.report(r0);
    L_0x00ea:
        r0 = r7.getIntent();
        r1 = "can_close";
        r0 = r0.hasExtra(r1);
        if (r0 == 0) goto L_0x0108;
    L_0x00f7:
        r0 = r7.getIntent();
        r1 = "can_close";
        r0 = r0.getBooleanExtra(r1, r2);
        r0 = java.lang.Boolean.valueOf(r0);
        r7.canClose = r0;
    L_0x0108:
        r0 = r7.getIntent();
        r1 = "mute";
        r0 = r0.hasExtra(r1);
        if (r0 == 0) goto L_0x0134;
    L_0x0115:
        r0 = r7.getIntent();
        r1 = "mute";
        r0 = r0.getBooleanExtra(r1, r2);
        r0 = java.lang.Boolean.valueOf(r0);
        r7.mute = r0;
        r0 = r7.mute;
        r0 = r0.booleanValue();
        if (r0 == 0) goto L_0x01cd;
    L_0x012e:
        r0 = "mute_on";
        r7.report(r0);
    L_0x0134:
        r0 = r7.getIntent();
        r1 = "pview";
        r0 = r0.hasExtra(r1);
        if (r0 == 0) goto L_0x0168;
    L_0x0141:
        r0 = r7.getIntent();
        r1 = "pview";
        r0 = r0.getStringExtra(r1);
        r7.la = r0;
        r0 = r7.getIntent();
        r1 = "banner";
        r0 = r0.getStringExtra(r1);
        r7.banner = r0;
        r0 = r7.getIntent();
        r1 = "guid";
        r0 = r0.getStringExtra(r1);
        r7.guid = r0;
    L_0x0168:
        r0 = r7.getIntent();
        r1 = "ads";
        r0 = r0.getSerializableExtra(r1);
        r0 = (java.util.ArrayList) r0;
        r7.aL = r0;
        r0 = r7.placementID;
        r1 = "";
        r2 = "show_request";
        r7.m2010b(r0, r1, r2);
        r0 = new android.os.Handler;
        r0.<init>();
        r7.eQ = r0;
        r0 = com.appnext.core.webview.AppnextWebView.m2400D(r7);
        r1 = r7.eN;
        r1 = r1.getPageUrl();
        r2 = new com.appnext.ads.interstitial.InterstitialActivity$1;
        r2.<init>(r7);
        r0.m2410a(r1, r2);
        r0 = new com.appnext.ads.interstitial.InterstitialActivity$5;
        r0.<init>(r7);
        r7.eR = r0;
        r0 = new com.appnext.core.r;
        r1 = new com.appnext.ads.interstitial.InterstitialActivity$6;
        r1.<init>(r7);
        r0.<init>(r7, r1);
        r7.userAction = r0;
        r0 = new java.lang.Thread;
        r1 = new com.appnext.ads.interstitial.InterstitialActivity$7;
        r1.<init>(r7);
        r0.<init>(r1);
        r0.start();
        goto L_0x0031;
    L_0x01bd:
        r0 = "loaded_portrait";
        r7.report(r0);
        goto L_0x008a;
    L_0x01c5:
        r0 = "auto_play_off";
        r7.report(r0);
        goto L_0x00ea;
    L_0x01cd:
        r0 = "mute_off";
        r7.report(r0);
        goto L_0x0134;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appnext.ads.interstitial.InterstitialActivity.onCreate(android.os.Bundle):void");
    }

    private void aq() {
        try {
            AppnextWebView D = AppnextWebView.m2400D(this);
            this.eL = D.aX(this.aL != null ? "fullscreen" : "interstitial");
            this.eL = D.m2408a(this, this.eN.getPageUrl(), ar(), this.eN.getFallback(), this.aL != null ? "fullscreen" : "interstitial");
            this.eL.setWebViewClient(new C09728(this));
            this.eL.setWebChromeClient(new C09739(this));
        } catch (Throwable th) {
            C1128g.m2351c(th);
            onError(AppnextError.INTERNAL_ERROR);
            finish();
        }
    }

    private void m1971F() {
        this.eQ.removeCallbacks(this.eW);
        this.eM = true;
        try {
            m1981b("Appnext.setParams(" + at().toString() + ");");
        } catch (Throwable th) {
            finish();
            onError(AppnextError.INTERNAL_ERROR);
            C1128g.m2351c(th);
            m2010b(this.placementID, C1128g.m2346b(th), "InterstitialActivity_error");
        }
        as();
        if (this.eL == null) {
            onError(AppnextError.INTERNAL_ERROR);
            finish();
            return;
        }
        if (this.eL.getParent() != null) {
            ((ViewGroup) this.eL.getParent()).removeView(this.eL);
        }
        this.lc.addView(this.eL);
        this.eL.getLayoutParams().width = -1;
        this.eL.getLayoutParams().height = -1;
    }

    private String m1972O(String str) {
        String str2 = getConfig().get(str);
        return str2 == null ? "" : str2;
    }

    protected C0921q getConfig() {
        return C0980c.ax();
    }

    protected void onResume() {
        super.onResume();
        if (this.eM && this.autoPlay.booleanValue()) {
            play();
        }
    }

    protected void onPause() {
        super.onPause();
        if (!this.closed) {
            stop();
        }
        if (this.handler != null) {
            this.handler.removeCallbacks(this.dG);
            this.handler.removeCallbacks(this.dH);
        }
    }

    public void onBackPressed() {
        if (this.canClose != null) {
            if (!this.canClose.booleanValue()) {
                return;
            }
        } else if (!Boolean.parseBoolean(m1972O("can_close"))) {
            return;
        }
        m1981b("Appnext.Layout.destroy('internal');");
        m2010b(this.placementID, "", C0893a.ct);
        this.closed = true;
        onClose();
        super.onBackPressed();
    }

    protected void onError(final String str) {
        runOnUiThread(new Runnable(this) {
            final /* synthetic */ InterstitialActivity eX;

            public void run() {
                if (this.eX.eN != null && this.eX.eN.getOnAdErrorCallback() != null) {
                    this.eX.eN.getOnAdErrorCallback().adError(str);
                }
            }
        });
    }

    private void m1973P(String str) {
        AppnextAd appnextAd = (AppnextAd) C0977a.au().m1887g(str);
        if (appnextAd != null) {
            this.dC = appnextAd;
            if (!(this.eN == null || this.eN.getOnAdClickedCallback() == null)) {
                this.eN.getOnAdClickedCallback().adClicked();
            }
            mo1937b(appnextAd, this.eR);
            report(C0893a.da);
            if (appnextAd.getBannerID().equals(this.dB != null ? this.dB.getBannerID() : "")) {
                m2010b(this.placementID, "", C0893a.cj);
                if (!this.eT) {
                    this.eT = true;
                    report(C0893a.dp);
                    return;
                }
                return;
            }
            m2010b(this.placementID, "", C0893a.ck);
            if (!this.eU) {
                this.eU = true;
                report(C0893a.f1790do);
            }
        }
    }

    protected void mo1937b(AppnextAd appnextAd, C0899a c0899a) {
        m1830a(this.lc, getResources().getDrawable(C0889R.drawable.apnxt_loader));
        super.mo1937b(appnextAd, c0899a);
    }

    protected WebAppInterface ar() {
        if (this.eS == null) {
            this.eS = new WebInterface(this);
        }
        return this.eS;
    }

    private void play() {
        m1981b("Appnext.Layout.Video.play();");
    }

    private void stop() {
        if (this.eL != null) {
            C1128g.m2333W("stop");
            this.eL.loadUrl("javascript:(function() { Appnext.Layout.Video.pause();})()");
        }
    }

    private void onClose() {
        if (this.eN != null && this.eN.getOnAdClosedCallback() != null) {
            this.eN.getOnAdClosedCallback().onAdClosed();
        }
    }

    protected void m2010b(String str, String str2, String str3) {
    }

    private void report(String str) {
        if (this.eN != null) {
            C1128g.m2341a(this.eN.getTID(), this.eN.getVID(), this.eN.getAUID(), this.eN.getPlacementID(), this.eN.getSessionId(), str, "current_interstitial", this.dB != null ? this.dB.getBannerID() : "", this.dB != null ? this.dB.getCampaignID() : "");
        }
    }

    private void m1981b(final String str) {
        runOnUiThread(new Runnable(this) {
            final /* synthetic */ InterstitialActivity eX;

            public void run() {
                if (this.eX.eL != null) {
                    C1128g.m2333W(str);
                    this.eX.eL.loadUrl("javascript:(function() { try { " + str + "} catch(err){ Appnext.jsError(err.message); }})()");
                }
            }
        });
    }

    protected synchronized void as() {
        if (this.aL != null) {
            String str = "Appnext.loadRewarded(" + C0977a.au().mo1998d(this.aL) + ");";
            C1128g.m2333W(str);
            m1981b(str);
            this.dB = (AppnextAd) this.aL.get(0);
        } else {
            C0977a.au().m2020a(this, this.eN, this.placementID, new C09684(this), this.eO);
        }
    }

    protected JSONObject at() throws JSONException {
        Object O;
        if (this.eN.getButtonColor().equals("")) {
            O = m1972O(FacebookAdapter.KEY_BUTTON_COLOR);
        } else {
            O = this.eN.getButtonColor();
        }
        if (O.startsWith("#")) {
            O = O.substring(1);
        }
        if (this.autoPlay == null) {
            this.autoPlay = Boolean.valueOf(Boolean.parseBoolean(m1972O("auto_play")));
        }
        if (this.mute == null) {
            this.mute = Boolean.valueOf(Boolean.parseBoolean(m1972O("mute")));
        }
        this.eO = getIntent().getExtras().getString("creative");
        if (this.eO == null || this.eO.equals(Interstitial.TYPE_MANAGED)) {
            this.eO = m1972O("creative");
        }
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("id", this.placementID);
        jSONObject.put("cat", this.eN.getCategories());
        jSONObject.put("pbk", this.eN.getPostback());
        jSONObject.put("b_color", O);
        if (this.aL == null) {
            jSONObject.put("skip_title", this.eN.getSkipText().equals("") ? m1972O("skip_title") : this.eN.getSkipText());
            jSONObject.put("pview", this.aL != null ? SchemaSymbols.ATTVAL_FALSE : m1972O("pview"));
            jSONObject.put("video_length", m1972O("video_length"));
            jSONObject.put("min_internet_connection", m1972O("min_internet_connection"));
            jSONObject.put("min_internet_connection_video", m1972O("min_internet_connection_video"));
            jSONObject.put("mute", "" + this.mute);
            jSONObject.put("auto_play", "" + this.autoPlay);
            jSONObject.put("remove_poster_on_auto_play", m1972O("remove_poster_on_auto_play"));
            jSONObject.put("show_rating", m1972O("show_rating"));
            jSONObject.put("show_desc", m1972O("show_desc"));
            jSONObject.put("creative", this.eO);
            jSONObject.put("remote_auto_play", true);
        }
        jSONObject.put("ext", "t");
        jSONObject.put("dct", C1128g.m2361x(this));
        jSONObject.put("did", this.eV);
        jSONObject.put("devn", C1128g.cV());
        jSONObject.put("dosv", VERSION.SDK_INT);
        jSONObject.put("dds", SchemaSymbols.ATTVAL_FALSE_0);
        jSONObject.put("urlApp_protection", m1972O("urlApp_protection"));
        jSONObject.put("vid", this.eN.getVID());
        jSONObject.put("tid", this.eN.getTID());
        jSONObject.put("auid", this.eN.getAUID());
        jSONObject.put("osid", "100");
        jSONObject.put("ads_type", "interstitial");
        if (getIntent() != null && getIntent().hasExtra("show_desc")) {
            jSONObject.put("show_desc", getIntent().getStringExtra("show_desc"));
        }
        return jSONObject;
    }

    private boolean hasVideo(AppnextAd appnextAd) {
        return (appnextAd.getVideoUrl().equals("") && appnextAd.getVideoUrlHigh().equals("") && appnextAd.getVideoUrl30Sec().equals("") && appnextAd.getVideoUrlHigh30Sec().equals("")) ? false : true;
    }

    protected void onDestroy() {
        super.onDestroy();
        try {
            this.eN = null;
            if (this.eQ != null) {
                this.eQ.removeCallbacksAndMessages(null);
            }
            this.eQ = null;
            this.eW = null;
            this.dB = null;
            if (this.eL != null) {
                this.eL.stopLoading();
                if (this.eL.getParent() != null) {
                    ((ViewGroup) this.eL.getParent()).removeView(this.eL);
                }
                this.eL.setWebChromeClient(null);
                this.eL.setWebViewClient(null);
                this.eL = null;
            }
            AppnextWebView.m2400D(this).m2409a(ar());
            this.eS = null;
            this.eR = null;
            if (this.dD != null) {
                this.dD.mo1917d(this);
                this.dD = null;
            }
        } catch (Throwable th) {
        }
    }
}
