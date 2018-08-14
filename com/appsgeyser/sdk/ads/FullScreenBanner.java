package com.appsgeyser.sdk.ads;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.appsgeyser.sdk.AdActivity;
import com.appsgeyser.sdk.BrowserActivity;
import com.appsgeyser.sdk.configuration.Configuration;
import com.appsgeyser.sdk.configuration.Constants;
import com.appsgeyser.sdk.configuration.Constants.BannerLoadTags;
import com.appsgeyser.sdk.deviceidparser.DeviceIdParameters;
import com.appsgeyser.sdk.location.Geolocation;
import com.appsgeyser.sdk.server.StatController;
import com.appsgeyser.sdk.server.implementation.AppsgeyserServerClient;
import com.appsgeyser.sdk.ui.AppsgeyserProgressDialog;
import com.appsgeyser.sdk.utils.BannerUtils;
import com.appsgeyser.sdk.utils.DeviceInfoGetter;
import com.appsgeyser.sdk.utils.EndpointGetter;
import com.appsgeyser.sdk.vast.VASTPlayer;
import com.appsgeyser.sdk.vast.VASTPlayer.VASTPlayerListener;
import com.bgjd.ici.p025b.C1408j.C1404b;
import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;
import com.yandex.metrica.YandexMetrica;
import java.util.HashMap;

public class FullScreenBanner {
    public static final String FULLSCREEN_CLICKED = "fullscreen_clicked";
    private static final long NO_TIMER = -1;
    private static final String TAG = FullScreenBanner.class.getSimpleName();
    private static final String VAST_DEBUG = "VAST_DEBUG";
    private AdMobFSBannerController adMobFSBannerController = null;
    private boolean admobFailedLoad = false;
    private boolean backKeyLocked = true;
    private boolean bannerClicked = false;
    private String bannerUrl = null;
    private WebView bannerView = null;
    private String clickUrl = null;
    private Context context = null;
    private BannerTypes currentBannerType = BannerTypes.NO_BANNER;
    private int currentlyShownBanners = 0;
    private DeviceIdParameters deviceIdParameters;
    private boolean errorHappened = false;
    private FullScreenEventListener eventListener = null;
    private final Handler handler = new Handler();
    private String impressionUrl = "about:blank";
    private boolean isActivityInForeground = true;
    private boolean isFailedSend = false;
    private boolean isLoading = false;
    private boolean isOnTouchFSEnabled = true;
    private boolean isVastPlayerActive;
    private boolean keepAliveCalled = false;
    private IFullScreenBannerListener listener = null;
    private String loadTagBanner;
    private MoPubFSBannerController moPubFSBannerController = null;
    private boolean moPubFailedLoad = false;
    private boolean openInNativeBrowser = true;
    private AppsgeyserProgressDialog progressDialog;
    private boolean ready = false;
    private boolean redirect = false;
    private long timerDuration = -1;
    private int timesToShow = 1;
    private final Handler uiThreadHandler;
    private String uniqueId = "-1";
    private VASTPlayer vastPlayer;

    public interface FullScreenEventListener {
        void bannerClosed();
    }

    class C12252 extends WebChromeClient {
        C12252() {
        }

        public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
            Log.d(Constants.LOG_FS_BANNER_JS_ERRORS_TAG, consoleMessage.message() + " -- From line " + consoleMessage.lineNumber() + " of " + consoleMessage.sourceId());
            return super.onConsoleMessage(consoleMessage);
        }
    }

    class C12263 implements Runnable {
        C12263() {
        }

        public void run() {
            FullScreenBanner.this.bannerView.loadUrl(FullScreenBanner.this.bannerUrl);
        }
    }

    class C12274 implements Runnable {
        C12274() {
        }

        public void run() {
            FullScreenBanner.this.admobFailedLoad = false;
            FullScreenBanner.this.moPubFailedLoad = false;
            FullScreenBanner.this.updateBannerUrlWithTag(FullScreenBanner.this.loadTagBanner);
            FullScreenBanner.this.progressDialog.dismiss();
            if (FullScreenBanner.this.currentBannerType.equals(BannerTypes.HTML) || FullScreenBanner.this.currentBannerType.equals(BannerTypes.NO_BANNER)) {
                AdActivity.startActivity(FullScreenBanner.this.context);
            } else if (FullScreenBanner.this.currentBannerType.equals(BannerTypes.ADMOB)) {
                FullScreenBanner.this.adMobFSBannerController.showBanner();
            } else if (FullScreenBanner.this.currentBannerType.equals(BannerTypes.MOPUB)) {
                FullScreenBanner.this.moPubFSBannerController.showBanner();
            }
        }
    }

    public enum BannerTypes {
        HTML,
        ADMOB,
        NO_BANNER,
        PENDING_BANNER,
        SDK,
        MOPUB
    }

    @SuppressLint({"AddJavascriptInterface", "SetJavaScriptEnabled"})
    public FullScreenBanner(final Context context) {
        this.context = context;
        this.progressDialog = new AppsgeyserProgressDialog(context);
        this.uiThreadHandler = new Handler(context.getMainLooper());
        this.bannerView = new WebView(this.context);
        this.adMobFSBannerController = new AdMobFSBannerController(this.context);
        this.moPubFSBannerController = new MoPubFSBannerController(this.context);
        this.bannerView.setWebViewClient(new WebViewClient() {
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                if (BannerUtils.isDataTextHtmlUrl(url)) {
                    handleRedirect(view, null);
                    StatController.getInstance().sendRequestAsyncByKey(StatController.KEY_CLICK_DATA_TEXT_HTML_LOADED_IN_BANNER);
                    return;
                }
                handleRedirect(view, url);
            }

            public void onPageFinished(WebView view, String url) {
                FullScreenBanner.this.isLoading = false;
                if (!FullScreenBanner.this.keepAliveCalled) {
                    FullScreenBanner.this.errorHappened = true;
                    if (FullScreenBanner.this.listener != null) {
                        FullScreenBanner.this.listener.onAdFailedToLoad(context, FullScreenBanner.this.loadTagBanner);
                    }
                } else if (!FullScreenBanner.this.errorHappened) {
                    FullScreenBanner.this.ready = true;
                    if (!(FullScreenBanner.this.currentBannerType.equals(BannerTypes.ADMOB) || FullScreenBanner.this.currentBannerType.equals(BannerTypes.SDK) || FullScreenBanner.this.currentBannerType.equals(BannerTypes.MOPUB))) {
                        FullScreenBanner.this.currentBannerType = BannerTypes.HTML;
                        if (FullScreenBanner.this.listener != null) {
                            FullScreenBanner.this.listener.onLoadFinished(FullScreenBanner.this);
                        }
                    }
                    if (FullScreenBanner.this.currentBannerType.equals(BannerTypes.SDK)) {
                        FullScreenBanner.this.close();
                    }
                }
            }

            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return handleRedirect(view, url);
            }

            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                if (FullScreenBanner.this.listener != null && FullScreenBanner.this.isLoading) {
                    if (VERSION.SDK_INT < 21) {
                        FullScreenBanner.this.listener.onLoadFinished(FullScreenBanner.this);
                    } else if (!failingUrl.contains(Constants.SPLASH_SERVER_DOMAIN_URL)) {
                        FullScreenBanner.this.ready = true;
                        FullScreenBanner.this.listener.onLoadFinished(FullScreenBanner.this);
                    } else if (failingUrl.contains("img") || failingUrl.contains(C1404b.aJ)) {
                        FullScreenBanner.this.ready = true;
                        FullScreenBanner.this.listener.onLoadFinished(FullScreenBanner.this);
                    } else {
                        FullScreenBanner.this.listener.onAdFailedToLoad(context, FullScreenBanner.this.loadTagBanner);
                    }
                }
                FullScreenBanner.this.isLoading = false;
            }

            public void onReceivedHttpError(WebView view, WebResourceRequest request, WebResourceResponse errorResponse) {
                super.onReceivedHttpError(view, request, errorResponse);
                if (FullScreenBanner.this.listener != null && FullScreenBanner.this.isLoading) {
                    if (VERSION.SDK_INT < 21) {
                        FullScreenBanner.this.listener.onLoadFinished(FullScreenBanner.this);
                    } else if (!request.getUrl().toString().contains(Constants.SPLASH_SERVER_DOMAIN_URL)) {
                        FullScreenBanner.this.ready = true;
                        FullScreenBanner.this.listener.onLoadFinished(FullScreenBanner.this);
                    } else if (request.getUrl().toString().contains("img") || request.getUrl().toString().contains(C1404b.aJ)) {
                        FullScreenBanner.this.ready = true;
                        FullScreenBanner.this.listener.onLoadFinished(FullScreenBanner.this);
                    } else {
                        FullScreenBanner.this.listener.onAdFailedToLoad(context, FullScreenBanner.this.loadTagBanner);
                    }
                }
                FullScreenBanner.this.isLoading = false;
            }

            private boolean handleRedirect(WebView view, String url) {
                if (url == null) {
                    FullScreenBanner.this.keepAliveCalled = false;
                    FullScreenBanner.this.close();
                    return false;
                } else if (EndpointGetter.getUrlWithoutArguments(url).equalsIgnoreCase(EndpointGetter.getUrlWithoutArguments(FullScreenBanner.this.bannerUrl)) || FullScreenBanner.this.redirect || BannerUtils.isDataTextHtmlUrl(url)) {
                    return false;
                } else {
                    Intent intent;
                    view.stopLoading();
                    FullScreenBanner.this.bannerClicked = true;
                    HashMap<String, String> details = new HashMap();
                    details.put("url", url);
                    if (FullScreenBanner.this.openInNativeBrowser) {
                        intent = new Intent(FullScreenBanner.this.context, BrowserActivity.class);
                        intent.putExtra(BrowserActivity.KEY_BROWSER_URL, url);
                        intent.putExtra(BrowserActivity.KEY_UNIQ_ID, FullScreenBanner.this.uniqueId);
                        intent.putExtra(BrowserActivity.KEY_BANNER_TYPE, BrowserActivity.BANNER_TYPE_FULLSCREEN);
                        intent.putExtra(BrowserActivity.KEY_TIMER_DURATION, FullScreenBanner.this.timerDuration);
                        intent.addFlags(ErrorDialogData.BINDER_CRASH);
                        StatController.getInstance().sendRequestAsyncByKey(StatController.KEY_CLICK_REDIRECT_START, details, context, true);
                    } else {
                        intent = new Intent("android.intent.action.VIEW", Uri.parse(url));
                        StatController.getInstance().sendRequestAsyncByKey(StatController.KEY_CLICK_EXTERNAL_BROWSER, details, context, true);
                    }
                    intent.setFlags(ErrorDialogData.BINDER_CRASH);
                    FullScreenBanner.this.close();
                    FullScreenBanner.this.context.startActivity(intent);
                    if (!TextUtils.isEmpty(FullScreenBanner.this.clickUrl)) {
                        AppsgeyserServerClient.getInstance().sendClickInfo(FullScreenBanner.this.clickUrl, view.getContext());
                        YandexMetrica.reportEvent(FullScreenBanner.FULLSCREEN_CLICKED);
                    }
                    return true;
                }
            }
        });
        this.bannerView.setWebChromeClient(new C12252());
        this.bannerView.addJavascriptInterface(new FullscreenBannerJsInterface(this, this.context), FullscreenBannerJsInterface.JS_INTERFACE_NAME);
        String appCachePath = this.context.getDir("appcache", 0).getPath();
        String geolocationDatabasePath = this.context.getDir("geolocation", 0).getPath();
        WebSettings settings = this.bannerView.getSettings();
        settings.setAppCachePath(appCachePath);
        settings.setGeolocationDatabasePath(geolocationDatabasePath);
        settings.setJavaScriptEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setAllowFileAccess(true);
        settings.setGeolocationEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setDomStorageEnabled(true);
        setListener(new SimpleFullScreenBannerListener());
    }

    public void load(String loadTag) {
        if (!this.isLoading) {
            if (!(this.admobFailedLoad && this.moPubFailedLoad)) {
                this.currentlyShownBanners = 0;
            }
            this.keepAliveCalled = false;
            this.ready = false;
            this.errorHappened = false;
            if (this.bannerUrl != null) {
                this.currentBannerType = BannerTypes.NO_BANNER;
                updateBannerUrlWithTag(loadTag);
                loadBanner();
                return;
            }
            this.currentBannerType = BannerTypes.PENDING_BANNER;
        }
    }

    private void loadBanner() {
        if (this.bannerUrl == null || this.isLoading) {
            Log.e(TAG, "initialization error, can't load banner!");
            return;
        }
        if (this.listener != null) {
            this.listener.onLoadStarted();
        }
        this.uiThreadHandler.post(new C12263());
    }

    public void show() {
        if (!this.isActivityInForeground) {
            return;
        }
        if (this.ready && !this.errorHappened && !this.isVastPlayerActive) {
            this.progressDialog.show();
            this.handler.postDelayed(new C12274(), Constants.getFullScreenDelay());
        } else if (this.isVastPlayerActive) {
            Log.i(TAG, "vast player active");
        } else {
            Log.e(TAG, this.ready + " " + this.errorHappened);
            Log.e(TAG, "banner is not ready!");
        }
    }

    public void setIsVastPlayerActive(boolean isActive) {
        this.isVastPlayerActive = isActive;
    }

    void stayAlive() {
        this.keepAliveCalled = true;
    }

    public void setListener(final IFullScreenBannerListener listener) {
        this.listener = new IFullScreenBannerListener() {
            public void onLoadStarted() {
                FullScreenBanner.this.isLoading = true;
                FullScreenBanner.this.isFailedSend = false;
                if (FullScreenBanner.this.currentlyShownBanners != FullScreenBanner.this.timesToShow) {
                    listener.onLoadStarted();
                }
            }

            public void onLoadFinished(FullScreenBanner banner) {
                FullScreenBanner.this.isLoading = false;
                if (FullScreenBanner.this.currentlyShownBanners < FullScreenBanner.this.timesToShow) {
                    FullScreenBanner.this.currentlyShownBanners = FullScreenBanner.this.currentlyShownBanners + 1;
                    banner.show();
                }
            }

            public void onAdFailedToLoad(Context context, String tag) {
                if (FullScreenBanner.this.currentlyShownBanners != FullScreenBanner.this.timesToShow && !FullScreenBanner.this.isFailedSend) {
                    FullScreenBanner.this.isFailedSend = true;
                    listener.onAdFailedToLoad(context, tag);
                }
            }

            public void onAdHided(Context context, String tag) {
                FullScreenBanner.this.currentBannerType = BannerTypes.NO_BANNER;
                if (FullScreenBanner.this.currentlyShownBanners == FullScreenBanner.this.timesToShow) {
                    listener.onAdHided(context, tag);
                }
                if (FullScreenBanner.this.currentlyShownBanners < FullScreenBanner.this.timesToShow && !FullScreenBanner.this.bannerClicked) {
                    FullScreenBanner.this.loadBanner();
                }
                FullScreenBanner.this.bannerClicked = false;
            }
        };
        this.adMobFSBannerController.setListener(this.listener);
        this.moPubFSBannerController.setListener(this.listener);
    }

    public void setEventListener(AdActivity listener) {
        this.eventListener = listener;
    }

    public synchronized void initWithDeviceIdParameters(DeviceIdParameters deviceIdParameters) {
        this.deviceIdParameters = deviceIdParameters;
        double[] coords = Geolocation.getCoords(this.context);
        Configuration configuration = Configuration.getInstance(getContext());
        String deviceIdSection = "";
        if (deviceIdParameters != null) {
            String advId = deviceIdParameters.getAdvId();
            String limitAdTrackingEnabled = deviceIdParameters.getLimitAdTrackingEnabled().toString().toLowerCase();
            String aid = deviceIdParameters.getaId();
            if (TextUtils.isEmpty(advId)) {
                deviceIdSection = "&aid=" + aid;
            } else {
                deviceIdSection = "&advid=" + advId + "&limit_ad_tracking_enabled=" + limitAdTrackingEnabled;
            }
        }
        if (this.loadTagBanner == null) {
            this.loadTagBanner = BannerLoadTags.ON_START;
        }
        this.bannerUrl = Constants.SPLASH_SERVER_DOMAIN_URL + ("?widgetid=" + configuration.getApplicationId() + "&guid=" + configuration.getAppGuid() + "&v=" + Constants.PLATFORM_VERSION + deviceIdSection + "&tlat=" + coords[0] + "&tlon=" + coords[1] + "&p=android&sdk=1" + DeviceInfoGetter.getDeviceInfo(this.context.getApplicationContext()) + "&load_tag_banner=" + this.loadTagBanner + "&templateversion=" + configuration.getTemplateVersion());
        if (this.currentBannerType.equals(BannerTypes.PENDING_BANNER)) {
            loadBanner();
        }
    }

    public WebView getWebView() {
        return this.bannerView;
    }

    void setClickUrl(String clickUrl) {
        this.clickUrl = clickUrl;
    }

    public String getClickUrl() {
        return this.clickUrl;
    }

    void setImpressionUrl(String impressionUrl) {
        this.impressionUrl = impressionUrl;
    }

    String getImpressionUrl() {
        return this.impressionUrl;
    }

    public boolean isAdmobFailedLoad() {
        return this.admobFailedLoad;
    }

    void setAdmobFailedLoad(boolean admobFailedLoad) {
        this.admobFailedLoad = admobFailedLoad;
    }

    public boolean isMoPubFailedLoad() {
        return this.moPubFailedLoad;
    }

    void setMoPubFailedLoad(boolean moPubFailedLoad) {
        this.moPubFailedLoad = moPubFailedLoad;
    }

    public void setBannerType(BannerTypes type) {
        this.currentBannerType = type;
    }

    public Context getContext() {
        return this.context;
    }

    public void setContext(@NonNull Context context) {
        if (this.context != null && !this.context.equals(context)) {
            this.context = context;
            this.progressDialog = new AppsgeyserProgressDialog(context);
        }
    }

    void setBackKeyLocked(boolean locked) {
        this.backKeyLocked = locked;
    }

    void setShowTimer(long ms) {
        if (ms > 0) {
            this.timerDuration = ms;
        }
    }

    void setUniqueId(@NonNull String uniqueId) {
        this.uniqueId = uniqueId;
    }

    String getLoadTagBanner() {
        return this.loadTagBanner;
    }

    public void setTimesToShow(int fullscreenBannerCountToShow) {
        this.timesToShow = fullscreenBannerCountToShow;
    }

    public boolean isOnTouchFSEnabled() {
        return this.isOnTouchFSEnabled;
    }

    public void setOnTouchFSEnabled(boolean onTouchFSEnabled) {
        this.isOnTouchFSEnabled = onTouchFSEnabled;
    }

    void setBannerClicked(boolean clicked) {
        this.bannerClicked = clicked;
    }

    public boolean isActivityInForeground() {
        return this.isActivityInForeground;
    }

    public void setActivityInForeground(boolean activityInForeground) {
        this.isActivityInForeground = activityInForeground;
    }

    void showVASTBanner(String vastXml) {
        Configuration configuration = Configuration.getInstance(this.context);
        final HashMap<String, String> details = new HashMap();
        details.put("wdid", configuration.getApplicationId());
        details.put("guid", configuration.getAppGuid());
        this.isVastPlayerActive = true;
        this.admobFailedLoad = false;
        this.moPubFailedLoad = false;
        updateBannerUrlWithTag(this.loadTagBanner);
        this.vastPlayer = new VASTPlayer(getContext(), new VASTPlayerListener() {

            class C12291 implements Runnable {
                C12291() {
                }

                public void run() {
                    FullScreenBanner.this.progressDialog.dismiss();
                    FullScreenBanner.this.vastPlayer.play();
                    AppsgeyserServerClient.getInstance().sendImpression(FullScreenBanner.this.impressionUrl, FullScreenBanner.this.context);
                    StatController.getInstance().sendRequestAsyncByKey(StatController.KEY_SILVERMOB_VAST_IMPRESSION_URL, details, FullScreenBanner.this.context, true);
                }
            }

            public void vastReady() {
                Log.d(FullScreenBanner.VAST_DEBUG, "vast_ready");
                if (FullScreenBanner.this.listener != null) {
                    FullScreenBanner.this.listener.onLoadFinished(FullScreenBanner.this);
                }
                FullScreenBanner.this.progressDialog.show();
                FullScreenBanner.this.handler.postDelayed(new C12291(), Constants.getFullScreenDelay());
            }

            public void vastError(int error) {
                Log.d(FullScreenBanner.VAST_DEBUG, "vast_error: " + error);
                if (FullScreenBanner.this.listener != null) {
                    FullScreenBanner.this.listener.onAdFailedToLoad(FullScreenBanner.this.context, FullScreenBanner.this.loadTagBanner);
                }
                FullScreenBanner.this.isVastPlayerActive = false;
                StatController.getInstance().sendRequestAsyncByKey(StatController.KEY_SILVERMOB_VAST_ERROR_URL, details, FullScreenBanner.this.context, true);
            }

            public void vastDismiss() {
                Log.d(FullScreenBanner.VAST_DEBUG, "vast_dismiss");
                if (FullScreenBanner.this.listener != null) {
                    FullScreenBanner.this.listener.onAdHided(FullScreenBanner.this.context, FullScreenBanner.this.loadTagBanner);
                }
                FullScreenBanner.this.isVastPlayerActive = false;
                StatController.getInstance().sendRequestAsyncByKey(StatController.KEY_SILVERMOB_VAST_CLOSE_URL, details, FullScreenBanner.this.context, true);
            }

            public void vastComplete() {
                Log.d(FullScreenBanner.VAST_DEBUG, "vast_complete");
                StatController.getInstance().sendRequestAsyncByKey(StatController.KEY_SILVERMOB_VAST_COMPLETE_URL, details, FullScreenBanner.this.context, true);
            }

            public void vastClick() {
                FullScreenBanner.this.bannerClicked = true;
                Log.d(FullScreenBanner.VAST_DEBUG, "vast_click");
                AppsgeyserServerClient.getInstance().sendClickInfo(FullScreenBanner.this.clickUrl, FullScreenBanner.this.context);
                StatController.getInstance().sendRequestAsyncByKey(StatController.KEY_SILVERMOB_VAST_CLICK_URL, details, FullScreenBanner.this.context, true);
            }
        });
        this.vastPlayer.loadVideoWithData(vastXml);
        StatController.getInstance().sendRequestAsyncByKey(StatController.KEY_SILVERMOB_VAST_REQUEST_URL, details, this.context, true);
    }

    void showAdMobFSBanner(String adUnitID, String keywords, String genderString, String birthday, String latitude, String longitude) {
        this.currentBannerType = BannerTypes.ADMOB;
        this.adMobFSBannerController.load(adUnitID, keywords, genderString, birthday, latitude, longitude, this);
    }

    void showMoPubFSBanner(String adUnitID) {
        this.currentBannerType = BannerTypes.MOPUB;
        this.moPubFSBannerController.load(adUnitID, this);
    }

    void skipNonHTMLBanner(String tag) {
        this.isLoading = false;
        load(tag);
    }

    public void close() {
        if (this.listener != null) {
            this.listener.onAdHided(this.context, this.loadTagBanner);
        }
        this.redirect = false;
        if (this.eventListener != null) {
            try {
                this.eventListener.bannerClosed();
                this.eventListener = null;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    void forceOpenInNativeBrowser(boolean openInNativeBrowser) {
        this.openInNativeBrowser = openInNativeBrowser;
    }

    private void updateBannerUrlWithTag(@NonNull String tag) {
        if (this.loadTagBanner == null) {
            this.loadTagBanner = BannerLoadTags.ON_START;
        }
        if (!this.loadTagBanner.equals(tag)) {
            this.loadTagBanner = tag;
            double[] coords = Geolocation.getCoords(this.context);
            Configuration configuration = Configuration.getInstance(getContext());
            this.bannerUrl = "http://splash.appsgeyser.com/?widgetid=" + configuration.getApplicationId() + "&guid=" + configuration.getAppGuid() + "&v=" + Constants.PLATFORM_VERSION;
            if (this.deviceIdParameters != null) {
                String deviceIdSection;
                String advId = this.deviceIdParameters.getAdvId();
                String limitAdTrackingEnabled = this.deviceIdParameters.getLimitAdTrackingEnabled().toString().toLowerCase();
                String aid = this.deviceIdParameters.getaId();
                if (TextUtils.isEmpty(advId)) {
                    deviceIdSection = "&aid=" + aid;
                } else {
                    deviceIdSection = "&advid=" + advId + "&limit_ad_tracking_enabled=" + limitAdTrackingEnabled;
                }
                this.bannerUrl += deviceIdSection;
            }
            this.bannerUrl += "&tlat=" + coords[0] + "&tlon=" + coords[1] + "&p=android&sdk=1" + DeviceInfoGetter.getDeviceInfo(this.context.getApplicationContext()) + "&load_tag_banner=" + this.loadTagBanner + "&templateversion=" + configuration.getTemplateVersion();
        }
        if (!this.admobFailedLoad) {
            this.bannerUrl = this.bannerUrl.replaceAll("&skipAdmob=true", "");
        } else if (!this.bannerUrl.contains("&skipAdmob=true")) {
            this.bannerUrl += "&skipAdmob=true";
        }
        if (!this.moPubFailedLoad) {
            this.bannerUrl = this.bannerUrl.replaceAll("&skipMoPub=true", "");
        } else if (!this.bannerUrl.contains("&skipMoPub=true")) {
            this.bannerUrl += "&skipMoPub=true";
        }
    }
}
