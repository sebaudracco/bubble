package com.appsgeyser.sdk.ads;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.WindowManager;
import android.webkit.ConsoleMessage;
import android.webkit.GeolocationPermissions.Callback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.appsgeyser.sdk.InternalEntryPoint;
import com.appsgeyser.sdk.admobutils.AdMobParameters;
import com.appsgeyser.sdk.ads.behavior.BehaviorAcceptor;
import com.appsgeyser.sdk.ads.behavior.BehaviorVisitor;
import com.appsgeyser.sdk.ads.behavior.bannerBehaviors.AdViewBehavior;
import com.appsgeyser.sdk.configuration.Constants;
import com.appsgeyser.sdk.deviceidparser.DeviceIdParameters;
import com.appsgeyser.sdk.deviceidparser.DeviceIdParser;
import com.appsgeyser.sdk.deviceidparser.IDeviceIdParserListener;
import com.appsgeyser.sdk.server.network.OnNetworkStateChangedListener;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest.Builder;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.MobileAds;
import com.mopub.mobileads.MoPubErrorCode;
import com.mopub.mobileads.MoPubView;
import com.mopub.mobileads.MoPubView.BannerAdListener;

public class AdView extends RelativeLayout implements BehaviorAcceptor {
    private static final String TAG = "AppsgeyserSDK";
    private Activity activity;
    private boolean adMobNow = false;
    private com.google.android.gms.ads.AdView admobView;
    private AdsLoader adsLoader;
    private WebView browser;
    private AdsBannerWebViewClient browserClient;
    private boolean isAdmobInitialized = false;
    private boolean moPubNow = false;
    private MoPubView mopubView;
    private OnNetworkStateChangedListener networkListener;
    private String uniqueId = "-1";
    private boolean wasPausedBefore = false;

    class C12012 implements IDeviceIdParserListener {
        C12012() {
        }

        public void onDeviceIdParametersObtained(Context context, DeviceIdParameters result) {
            AdView.this.initWithDeviceIdParameters(result);
        }
    }

    class C12023 extends WebChromeClient {
        C12023() {
        }

        public void onGeolocationPermissionsShowPrompt(String origin, Callback callback) {
            callback.invoke(origin, true, false);
        }

        public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
            Log.d(Constants.LOG_SMALL_BANNER_JS_ERRORS_TAG, consoleMessage.message() + " -- From line " + consoleMessage.lineNumber() + " of " + consoleMessage.sourceId());
            return super.onConsoleMessage(consoleMessage);
        }
    }

    class C12034 extends AdListener {
        C12034() {
        }

        public void onAdLoaded() {
            Log.d("AppsgeyserSDK", "admob_onLoadStarted");
            AdView.this.admobView.setVisibility(0);
            super.onAdLoaded();
        }

        public void onAdFailedToLoad(int errorCode) {
            Log.d("AppsgeyserSDK", "admob_onAdFailedToLoad" + errorCode);
            super.onAdFailedToLoad(errorCode);
        }

        public void onAdOpened() {
            Log.d("AppsgeyserSDK", "admob_onAdOpened");
            super.onAdOpened();
        }

        public void onAdClosed() {
            Log.d("AppsgeyserSDK", "admob_onAdClosed");
            super.onAdClosed();
        }

        public void onAdLeftApplication() {
            Log.d("AppsgeyserSDK", "admob_onAdLeftApplication");
            super.onAdLeftApplication();
        }
    }

    class C12045 implements BannerAdListener {
        C12045() {
        }

        public void onBannerLoaded(MoPubView moPubView) {
            Log.d("AppsgeyserSDK", "admob_onBannerLoaded");
            moPubView.setVisibility(0);
        }

        public void onBannerFailed(MoPubView moPubView, MoPubErrorCode moPubErrorCode) {
            Log.d("AppsgeyserSDK", "mopub_onAdFailedToLoad" + moPubErrorCode.toString());
        }

        public void onBannerClicked(MoPubView moPubView) {
            Log.d("AppsgeyserSDK", "mopub_onBannerClicked");
        }

        public void onBannerExpanded(MoPubView moPubView) {
            Log.d("AppsgeyserSDK", "mopub_onBannerExpanded");
        }

        public void onBannerCollapsed(MoPubView moPubView) {
            Log.d("AppsgeyserSDK", "mopub_onBannerCollapsed");
        }
    }

    class C12056 implements Runnable {
        C12056() {
        }

        public void run() {
            AdView.this.setVisibility(8);
        }
    }

    class C12067 implements Runnable {
        C12067() {
        }

        public void run() {
            AdView.this.setVisibility(8);
        }
    }

    public AdView(Context context) {
        super(context);
        init();
    }

    public AdView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        this.activity = (Activity) getContext();
        this.adsLoader = new AdsLoader();
        this.networkListener = new OnNetworkStateChangedListener(this.activity.getLocalClassName()) {
            public void networkIsUp() {
                AdView.this.show();
                AdView.this.adsLoader.reload();
            }

            public void networkIsDown() {
                AdView.this.hide();
            }
        };
        InternalEntryPoint.getInstance().addNetworkListener(this.networkListener, getContext());
        setVisibility(8);
        if (getContext().checkCallingOrSelfPermission("android.permission.ACCESS_NETWORK_STATE") == 0 && getContext().checkCallingOrSelfPermission("android.permission.INTERNET") == 0) {
            DeviceIdParser parser = DeviceIdParser.getInstance();
            if (parser.isEmpty()) {
                parser.rescan(getContext(), new C12012());
                return;
            } else {
                initWithDeviceIdParameters(parser.getDeviceIdParameters());
                return;
            }
        }
        Log.e("AppsgeyserSDK", "You have to grant ACCESS_NETWORK_STATE and INTERNET permissions to work properly");
    }

    @SuppressLint({"AddJavascriptInterface"})
    private void initWithDeviceIdParameters(DeviceIdParameters deviceIdParameters) {
        this.browser = new WebView(getContext());
        addView(this.browser, new LayoutParams(-1, -1));
        this.adsLoader.init(this, deviceIdParameters);
        AdsHeaderReceiver headerReceiver = new AdsHeaderReceiver(this, this.adsLoader);
        this.adsLoader.setAdsLoadingFinishedListener(headerReceiver);
        this.adsLoader.setHeaderReceiver(headerReceiver);
        this.browser.addJavascriptInterface(new BannerJavascriptInterface(this, this.adsLoader), "AppsgeyserBanner");
        this.browserClient = new AdsBannerWebViewClient();
        this.browserClient.setOnPageFinishedListener(this.adsLoader);
        this.browserClient.setOnPageStartedListener(this.adsLoader);
        this.browser.setWebChromeClient(new C12023());
        this.browser.setWebViewClient(this.browserClient);
        ((WindowManager) getContext().getSystemService("window")).getDefaultDisplay().getMetrics(new DisplayMetrics());
        applyDefaultSettings();
        this.adsLoader.reload();
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    public void applyDefaultSettings() {
        setVerticalScrollBarEnabled(false);
        setHorizontalScrollBarEnabled(false);
        if (this.browser != null) {
            WebSettings settings = this.browser.getSettings();
            settings.setJavaScriptEnabled(true);
            settings.setJavaScriptCanOpenWindowsAutomatically(true);
            settings.setAllowFileAccess(true);
            settings.setGeolocationEnabled(true);
            settings.setDatabaseEnabled(true);
            settings.setDomStorageEnabled(true);
            Context ctx = getContext();
            String appCachePath = ctx.getDir("appcache", 0).getPath();
            String databasePath = ctx.getDir("databases", 0).getPath();
            String geolocationDatabasePath = ctx.getDir("geolocation", 0).getPath();
            settings.setAppCachePath(appCachePath);
            settings.setDatabasePath(databasePath);
            settings.setGeolocationDatabasePath(geolocationDatabasePath);
        }
    }

    public WebView getBrowser() {
        return this.browser;
    }

    public void acceptBehavior(BehaviorVisitor visitor) {
        if (visitor instanceof AdViewBehavior) {
            visitor.visit(this);
        }
    }

    public void hide() {
        Log.d("AppsgeyserSDK", "Hiding banner");
        setVisibility(8);
        if (this.browser != null) {
            this.browser.setWebViewClient(null);
            this.browser.loadUrl("about:blank");
        }
    }

    public void show() {
        Log.d("AppsgeyserSDK", "Showing banner");
        setVisibility(0);
        if (this.browser != null) {
            this.browser.setWebViewClient(this.browserClient);
        }
    }

    public void switchToAdMobAd(AdMobParameters adMobParameters) {
        handleAdmob(adMobParameters.getPublisherId(), null);
    }

    public void handleAdmob(String placementId, String admobAppId) {
        if (!(this.isAdmobInitialized || TextUtils.isEmpty(admobAppId))) {
            MobileAds.initialize(getContext(), admobAppId);
            this.isAdmobInitialized = true;
        }
        this.adMobNow = true;
        this.browser.setVisibility(8);
        removeAdMobBanner();
        createAdMobBanner();
        this.admobView.setAdUnitId(placementId);
        this.admobView.setAdSize(AdSize.BANNER);
        this.admobView.setAdListener(new C12034());
        this.admobView.loadAd(new Builder().build());
    }

    public void handleMoPub(String placementId) {
        this.moPubNow = true;
        this.browser.setVisibility(8);
        removeMoPubBanner();
        createMoPubBanner();
        this.mopubView.setAdUnitId(placementId);
        this.mopubView.setBannerAdListener(new C12045());
        this.mopubView.loadAd();
    }

    private void createAdMobBanner() {
        removeAdMobBanner();
        this.admobView = new com.google.android.gms.ads.AdView(this.activity);
        this.admobView.setVisibility(8);
        addView(this.admobView, new LayoutParams(-1, -1));
    }

    private void createMoPubBanner() {
        removeMoPubBanner();
        this.mopubView = new MoPubView(this.activity);
        this.mopubView.setVisibility(8);
        LayoutParams params = new LayoutParams(-1, (int) TypedValue.applyDimension(1, 50.0f, getResources().getDisplayMetrics()));
        params.addRule(13);
        addView(this.mopubView, params);
    }

    private void removeAdMobBanner() {
        removeView(this.admobView);
    }

    private void removeMoPubBanner() {
        removeView(this.mopubView);
    }

    public void switchToHtmlBanner() {
        if (this.browser != null) {
            this.adMobNow = false;
            this.moPubNow = false;
            removeAdMobBanner();
            removeMoPubBanner();
            this.browser.setVisibility(0);
        }
    }

    public void switchToAdMobBanner() {
        this.browser.setVisibility(8);
        this.moPubNow = false;
        removeMoPubBanner();
        if (this.admobView != null) {
            this.admobView.setVisibility(0);
        }
    }

    public void switchToMoPubBanner() {
        this.browser.setVisibility(8);
        this.adMobNow = false;
        removeAdMobBanner();
        if (this.mopubView != null) {
            this.mopubView.setVisibility(0);
        }
    }

    public boolean isAdMobNow() {
        return this.adMobNow;
    }

    public boolean isMoPubNow() {
        return this.moPubNow;
    }

    public Activity getActivity() {
        return this.activity;
    }

    public void onPause() {
        if (this.browser != null) {
            post(new C12056());
            if (VERSION.SDK_INT >= 11) {
                this.browser.onPause();
            }
            this.browser.clearHistory();
            this.browser.clearCache(true);
            this.wasPausedBefore = true;
            this.adsLoader.stopLoading();
            InternalEntryPoint.getInstance().removeNetworkListener(this.networkListener);
        }
    }

    public void onResume() {
        if (this.browser != null && this.adsLoader != null) {
            if (VERSION.SDK_INT >= 11) {
                this.browser.onResume();
            }
            post(new C12067());
            if (this.wasPausedBefore) {
                init();
            }
        }
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getUniqueId() {
        return this.uniqueId;
    }

    public void addJsCode(String jsCode) {
        if (this.browser != null && this.browser.getProgress() >= 100 && this.browser.getUrl() != null && !this.browser.getUrl().startsWith("https://")) {
            this.browser.loadUrl("javascript:(function(){ " + jsCode + " })()");
        }
    }
}
