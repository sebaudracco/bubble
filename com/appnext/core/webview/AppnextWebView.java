package com.appnext.core.webview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.appnext.core.C1128g;
import java.io.IOException;
import java.net.HttpRetryException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class AppnextWebView {
    public static final int mJ = 1;
    public static final int mK = 2;
    private static AppnextWebView mL;
    private WebAppInterface eS;
    private final HashMap<String, C1152b> mM = new HashMap();
    private HashMap<String, WebView> mN;

    public interface C0963c {
        void mo1985c(String str);

        void error(String str);
    }

    class C11501 extends WebViewClient {
        final /* synthetic */ AppnextWebView mO;

        C11501(AppnextWebView appnextWebView) {
            this.mO = appnextWebView;
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
    }

    private class WebInterface extends WebAppInterface {
        final /* synthetic */ AppnextWebView mO;

        public WebInterface(AppnextWebView appnextWebView) {
            this.mO = appnextWebView;
        }

        @JavascriptInterface
        public void destroy(String str) {
            super.destroy(str);
            if (AppnextWebView.mL.eS != null) {
                AppnextWebView.mL.eS.destroy(str);
            }
        }

        @JavascriptInterface
        public void postView(String str) {
            super.postView(str);
            if (AppnextWebView.mL.eS != null) {
                AppnextWebView.mL.eS.postView(str);
            }
        }

        @JavascriptInterface
        public void openStore(String str) {
            super.openStore(str);
            if (AppnextWebView.mL.eS != null) {
                AppnextWebView.mL.eS.openStore(str);
            }
        }

        @JavascriptInterface
        public void play() {
            super.play();
            if (AppnextWebView.mL.eS != null) {
                AppnextWebView.mL.eS.play();
            }
        }

        @JavascriptInterface
        public String filterAds(String str) {
            super.filterAds(str);
            if (AppnextWebView.mL.eS != null) {
                return AppnextWebView.mL.eS.filterAds(str);
            }
            return str;
        }

        @JavascriptInterface
        public String loadAds() {
            super.loadAds();
            if (AppnextWebView.mL.eS != null) {
                return AppnextWebView.mL.eS.loadAds();
            }
            return "";
        }

        @JavascriptInterface
        public void openLink(String str) {
            super.openLink(str);
            if (AppnextWebView.mL.eS != null) {
                AppnextWebView.mL.eS.openLink(str);
            }
        }

        @JavascriptInterface
        public void gotoAppWall() {
            super.gotoAppWall();
            if (AppnextWebView.mL.eS != null) {
                AppnextWebView.mL.eS.gotoAppWall();
            }
        }

        @JavascriptInterface
        public void videoPlayed() {
            super.videoPlayed();
            if (AppnextWebView.mL.eS != null) {
                AppnextWebView.mL.eS.videoPlayed();
            }
        }

        @JavascriptInterface
        public void notifyImpression(String str) {
            super.notifyImpression(str);
            if (AppnextWebView.mL.eS != null) {
                AppnextWebView.mL.eS.notifyImpression(str);
            }
        }

        @JavascriptInterface
        public void jsError(String str) {
            super.jsError(str);
            if (AppnextWebView.mL.eS != null) {
                AppnextWebView.mL.eS.jsError(str);
            }
        }
    }

    private class C1151a extends AsyncTask<String, Void, String> {
        final /* synthetic */ AppnextWebView mO;
        C1152b mP;

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return m2399d((String[]) objArr);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            aV((String) obj);
        }

        public C1151a(AppnextWebView appnextWebView, C1152b c1152b) {
            this.mO = appnextWebView;
            this.mP = c1152b;
        }

        protected String m2399d(String... strArr) {
            try {
                C1152b c1152b = (C1152b) this.mO.mM.get(strArr[0]);
                c1152b.mQ = C1128g.m2337a(strArr[0], null, 3000);
                this.mO.mM.put(strArr[0], c1152b);
                return strArr[0];
            } catch (HttpRetryException e) {
                return "error: " + e.getReason();
            } catch (IOException e2) {
                return "error: network problem";
            } catch (Throwable th) {
                return "error: unknown error";
            }
        }

        protected void aV(String str) {
            super.onPostExecute(str);
            if (str.startsWith("error:")) {
                this.mP.state = 0;
                this.mO.mM.put(this.mP.bW, this.mP);
                this.mO.m2403a(this.mP, str.substring("error: ".length()));
                return;
            }
            this.mP.state = 2;
            this.mO.mM.put(this.mP.bW, this.mP);
            this.mO.m2406b(this.mP, str);
        }
    }

    private class C1152b {
        public String bW;
        final /* synthetic */ AppnextWebView mO;
        public String mQ;
        public ArrayList<C0963c> mw;
        public int state;

        private C1152b(AppnextWebView appnextWebView) {
            this.mO = appnextWebView;
            this.state = 0;
            this.mQ = "";
            this.mw = new ArrayList();
        }
    }

    public void m2409a(WebAppInterface webAppInterface) {
        if (this.eS == webAppInterface) {
            this.eS = null;
        }
    }

    @SuppressLint({"NewApi"})
    public static AppnextWebView m2400D(Context context) {
        if (mL == null) {
            mL = new AppnextWebView();
            mL.mN = new HashMap();
        }
        return mL;
    }

    private AppnextWebView() {
    }

    public synchronized void m2410a(String str, C0963c c0963c) {
        C1152b c1152b;
        if (this.mM.containsKey(str)) {
            c1152b = (C1152b) this.mM.get(str);
        } else {
            c1152b = new C1152b();
            c1152b.bW = str;
        }
        if (c1152b.state != 2) {
            if (c1152b.state == 0) {
                c1152b.state = 1;
                C1128g.m2333W("start loading config");
                new C1151a(this, c1152b).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[]{str, null});
            }
            if (c0963c != null) {
                c1152b.mw.add(c0963c);
            }
            this.mM.put(str, c1152b);
        } else if (c0963c != null) {
            c0963c.mo1985c(str);
        }
    }

    @SuppressLint({"SetJavaScriptEnabled", "NewApi", "InlinedApi"})
    private WebView m2401E(Context context) {
        WebView webView = new WebView(context.getApplicationContext());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setAllowFileAccess(true);
        webView.getSettings().setAppCacheEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setDatabaseEnabled(true);
        if (VERSION.SDK_INT >= 21) {
            webView.getSettings().setMixedContentMode(0);
        }
        if (VERSION.SDK_INT >= 17) {
            webView.getSettings().setMediaPlaybackRequiresUserGesture(false);
        }
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new C11501(this));
        return webView;
    }

    @SuppressLint({"AddJavascriptInterface"})
    public WebView m2408a(Context context, String str, WebAppInterface webAppInterface, C0978a c0978a, String str2) {
        try {
            String str3;
            mL.eS = webAppInterface;
            WebView webView = (WebView) this.mN.get(str2);
            if (!(webView == null || webView.getParent() == null)) {
                ((ViewGroup) webView.getParent()).removeView(webView);
            }
            URL url = new URL(str);
            String str4 = url.getProtocol() + "://" + url.getHost();
            webView = m2401E(context);
            if (this.mM.containsKey(str) && ((C1152b) this.mM.get(str)).state == 2 && !((C1152b) this.mM.get(str)).mQ.equals("")) {
                str3 = "<script>" + ((C1152b) this.mM.get(str)).mQ + "</script>";
            } else if (c0978a != null) {
                str3 = "<script>" + c0978a.mo1999g() + "</script>";
            } else {
                str3 = "<script src='" + str + "'></script>";
            }
            webView.loadDataWithBaseURL(str4, "<html><body>" + str3 + "</body></html>", null, "UTF-8", null);
            this.mN.put(str2, webView);
            webView.addJavascriptInterface(new WebInterface(this), "Appnext");
            return webView;
        } catch (Throwable th) {
            return null;
        }
    }

    public boolean aW(String str) {
        return this.mN.get(str) != null;
    }

    public WebView aX(String str) {
        WebView webView = (WebView) this.mN.get(str);
        if (!(webView == null || webView.getParent() == null)) {
            ((ViewGroup) webView.getParent()).removeView(webView);
        }
        return webView;
    }

    public String aY(String str) {
        C1152b c1152b = (C1152b) this.mM.get(str);
        if (c1152b == null || c1152b.state != 2) {
            return null;
        }
        return c1152b.mQ;
    }

    public void m2411b(WebAppInterface webAppInterface) {
        mL.eS = webAppInterface;
    }

    private void m2403a(C1152b c1152b, String str) {
        C1128g.m2333W("webview error: " + str);
        synchronized (this.mM) {
            Iterator it = c1152b.mw.iterator();
            while (it.hasNext()) {
                ((C0963c) it.next()).error(str);
            }
            c1152b.mw.clear();
            this.mM.remove(c1152b.bW);
        }
    }

    private void m2406b(C1152b c1152b, String str) {
        C1128g.m2333W("downloaded " + str);
        synchronized (this.mM) {
            Iterator it = c1152b.mw.iterator();
            while (it.hasNext()) {
                ((C0963c) it.next()).mo1985c(str);
            }
            c1152b.mw.clear();
        }
    }
}
