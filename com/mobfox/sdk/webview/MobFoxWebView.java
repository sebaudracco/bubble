package com.mobfox.sdk.webview;

import android.content.Context;
import android.os.Build.VERSION;
import android.os.Handler;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import com.github.lzyzsd.jsbridge.BridgeWebView;
import com.mobfox.sdk.constants.Constants;
import com.mobfox.sdk.logging.MobFoxReport;
import com.mobfox.sdk.utils.ProxyFactory;
import org.json.JSONObject;

public class MobFoxWebView extends BridgeWebView {
    public static final String DEFAULT_WATERFALLS = "";
    public static final String DOMAIN = "sdk.starbolt.io";
    private static final int JS_READY_INTERVAL = 50;
    public static final String LOAD_AD_LISTENER = "loadAdListener";
    public static final String RENDER_AD_LISTENER = "renderAdListener";
    public static final String WATERFALL_CONNECTION_EXCEPTION = "failed to connect to sdk.starbolt.io";
    public static String path = "dist";
    static boolean secure = false;
    Context context;
    MobFoxWebViewLoadAdListener loadAdListener;
    public long loadBannerStarted = 0;
    long loadJsStarted = 0;
    Handler mainHandler;
    MobFoxWebViewClient mobFoxWebViewClient;
    String options = "";
    boolean ready = false;
    MobFoxWebViewRenderAdListener renderAdListener;
    boolean userInteraction = false;
    String waterfalls = null;

    public MobFoxWebView(Context context, MobFoxWebViewLoadAdListener loadAdListener) {
        super(context);
        this.loadAdListener = loadAdListener;
        this.context = context;
        setBackgroundColor(0);
        this.mainHandler = new Handler(context.getMainLooper());
        Log.d(Constants.MOBFOX_WEBVIEW, "mobfox webview init");
        init(this);
        WebSettings settings = getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        if (VERSION.SDK_INT >= 16) {
            settings.setAllowFileAccess(true);
            settings.setAllowContentAccess(true);
            settings.setAllowFileAccessFromFileURLs(true);
            settings.setAllowUniversalAccessFromFileURLs(true);
        }
        settings.setCacheMode(-1);
        setHapticFeedbackEnabled(false);
        setSoundEffectsEnabled(false);
        if (VERSION.SDK_INT >= 21) {
            settings.setMixedContentMode(0);
        }
        if (VERSION.SDK_INT >= 17) {
            try {
                WebSettings.class.getMethod("setMediaPlaybackRequiresUserGesture", new Class[]{Boolean.TYPE}).invoke(settings, new Object[]{Boolean.valueOf(false)});
            } catch (Exception e) {
                this.mainHandler.post(new 1(this, context, self, LOAD_AD_LISTENER, self, e));
            } catch (Throwable th) {
                Log.d(Constants.MOBFOX_WEBVIEW, "Throwable err");
            }
        }
        registerHandler("click", new 2(this, context, self));
        registerHandler("close", new 3(this, context, self));
        registerHandler("finished", new 4(this, context, self));
        registerHandler("ready", new 5(this));
        registerHandler("error", new 6(this, context, self));
        registerHandler("loadAdResponse", new 7(this, context, self));
        setWebViewClient(this.mobFoxWebViewClient);
        setWebChromeClient(new MobFoxWebChromeClient());
        setOnTouchListener(new 8(this));
        try {
            if (secure) {
                loadUrl(getMobfoxUrlHttps());
            } else {
                loadUrl(getMobfoxUrl());
            }
            this.loadJsStarted = System.currentTimeMillis();
        } catch (Exception e2) {
            MobFoxReport.postException(context, e2, new 9(this));
            this.mainHandler.post(new 10(this, context, self, LOAD_AD_LISTENER, self, e2));
        } catch (Throwable t) {
            Log.d(Constants.MOBFOX_WEBVIEW, "load sdk error");
            this.mainHandler.post(new 11(this, context, self, LOAD_AD_LISTENER, self, t));
        }
    }

    void init(MobFoxWebView self) {
        addJavascriptInterface(this, "MobFoxVideoCache");
        this.mobFoxWebViewClient = new MobFoxWebViewClient(self, new 12(this, self));
    }

    public void loadAd(String options) {
        this.loadBannerStarted = System.currentTimeMillis();
        if (!this.ready || this.waterfalls == null) {
            Handler h = new Handler();
            h.postDelayed(new 13(this, h, options), 50);
            return;
        }
        _loadAd(options);
    }

    private void _loadAd(String options) {
        this.options = options;
        this.userInteraction = false;
        this.mainHandler.post(new 14(this, this));
        callHandler("setWaterfallsJson", this.waterfalls, new 15(this, this));
    }

    public void renderAd(JSONObject adResp) {
        MobFoxWebView self = this;
        if (this.ready) {
            this.userInteraction = false;
            callHandler("renderAd", adResp.toString(), new 17(this, self));
            return;
        }
        this.mainHandler.postDelayed(new 16(this, this.context, self, RENDER_AD_LISTENER, adResp), 50);
    }

    public void setLoadAdListener(MobFoxWebViewLoadAdListener loadAdListener) {
        this.loadAdListener = loadAdListener;
    }

    public void setRenderAdListener(MobFoxWebViewRenderAdListener renderAdListener) {
        this.renderAdListener = renderAdListener;
    }

    public void setWaterfalls(String waterfalls) {
        this.waterfalls = waterfalls;
    }

    public void setUserInteraction(boolean interaction) {
        this.userInteraction = interaction;
    }

    public void destroy() {
        if (this.loadAdListener != null) {
            this.loadAdListener = null;
        }
        if (this.renderAdListener != null) {
            this.renderAdListener = null;
        }
        registerHandler("click", null);
        registerHandler("close", null);
        registerHandler("finished", null);
        registerHandler("error", null);
        registerHandler("loadAdResponse", null);
        super.destroy();
    }

    public void setReady(boolean ready) {
        this.ready = ready;
    }

    public static void setSecure(boolean secure) {
        secure = secure;
    }

    public static void setPath(String path) {
        path = path;
    }

    public static String getMobfoxUrl() {
        return "http://sdk.starbolt.io/" + path + "/android.html";
    }

    public static String getMobfoxUrlBridge() {
        return "http://sdk.starbolt.io/" + path + "/WebViewJavascriptBridge.js";
    }

    public static String getMobfoxUrlVideo() {
        return "http://sdk.starbolt.io/" + path + "/sdk_video.js";
    }

    public static String getMobfoxUrlHttps() {
        return getMobfoxUrl().replace("http:", "https:");
    }

    public MobFoxWebViewRenderAdListener getRenderAdListener() {
        return this.renderAdListener;
    }

    @JavascriptInterface
    public String getCachedVideoURL(String url) {
        return ProxyFactory.getProxy(this.context).getProxyUrl(url, false);
    }
}
