package com.appsgeyser.sdk.ads;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.webkit.WebView;
import com.appsgeyser.sdk.BrowserActivity;
import com.appsgeyser.sdk.ads.behavior.BehaviorAcceptor;
import com.appsgeyser.sdk.ads.behavior.BehaviorFactory.ClickBehavior;
import com.appsgeyser.sdk.ads.behavior.BehaviorVisitor;
import com.appsgeyser.sdk.ads.behavior.loaderBehaviors.LoaderBehavior;
import com.appsgeyser.sdk.configuration.Configuration;
import com.appsgeyser.sdk.configuration.Constants;
import com.appsgeyser.sdk.deviceidparser.DeviceIdParameters;
import com.appsgeyser.sdk.location.Geolocation;
import com.appsgeyser.sdk.server.implementation.AppsgeyserServerClient;
import com.appsgeyser.sdk.server.network.NetworkManager;
import com.appsgeyser.sdk.utils.BannerUtils;
import com.appsgeyser.sdk.utils.EndpointGetter;
import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;
import com.yandex.metrica.YandexMetrica;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class AdsLoader implements BehaviorAcceptor, OnPageFinishedListener, OnPageStartedListener {
    private static final int AD_DELAY = 1000;
    private static final int AD_DELAY_PERIOD = 100;
    private final float DEFAULT_HIDE_TIMEOUT = 60000.0f;
    private AdView adView;
    private String bannerUrl;
    private ClickBehavior clickBehavior;
    private String clickUrl;
    private Thread closeBannerThread = null;
    private HeadersReceiver headersReceiver;
    private AdsLoadingFinishedListener loadingFinishedListener;
    private boolean openInNativeBrowser = true;
    private Timer refreshTimer = new Timer();

    interface AdsLoadingFinishedListener {
        void onAdLoadFinished();
    }

    interface HeadersReceiver {
        boolean onAdHeadersReceived(Map<String, List<String>> map);
    }

    class C12081 implements OnTouchListener {
        C12081() {
        }

        public boolean onTouch(View v, MotionEvent event) {
            CorrectClickStateHolder.getInstance().allowClick();
            Log.d("AUTOCLICK_DETECT", "touch detected");
            switch (event.getAction()) {
                case 0:
                case 1:
                    if (!v.hasFocus()) {
                        v.requestFocus();
                        break;
                    }
                    break;
            }
            return false;
        }
    }

    class C12092 extends Thread {
        C12092() {
        }

        public void run() {
            AdsLoader.this.refreshTimer.cancel();
            if (AdsLoader.this.adView.getBrowser() != null) {
                AdsLoader.this.adView.getBrowser().stopLoading();
            }
            AdsLoader.this.adView.hide();
        }
    }

    class C12103 implements Runnable {
        C12103() {
        }

        public void run() {
            AdsLoader.this.adView.getBrowser().loadUrl(AdsLoader.this.bannerUrl);
        }
    }

    class C12134 extends Thread {

        class C12111 implements Runnable {
            C12111() {
            }

            public void run() {
                AdsLoader.this.adView.getBrowser().loadUrl(AdsLoader.this.bannerUrl);
            }
        }

        class C12122 implements Runnable {
            C12122() {
            }

            public void run() {
                AdsLoader.this.adView.hide();
            }
        }

        C12134() {
        }

        public void run() {
            Map<String, List<String>> headers = NetworkManager.getInstance().loadHeaders(AdsLoader.this.bannerUrl);
            if (headers != null) {
                if ((AdsLoader.this.headersReceiver == null || AdsLoader.this.headersReceiver.onAdHeadersReceived(headers)) && AdsLoader.this.adView.getBrowser() != null) {
                    AdsLoader.this.adView.post(new C12111());
                }
            } else if (AdsLoader.this.adView != null) {
                AdsLoader.this.adView.post(new C12122());
            }
        }
    }

    class C12145 extends TimerTask {
        C12145() {
        }

        public void run() {
            AdsLoader.this.reload();
            AdsLoader.this.refreshTimer.cancel();
        }
    }

    void init(AdView adView, DeviceIdParameters deviceIdParameters) {
        this.adView = adView;
        this.adView.getBrowser().setOnTouchListener(new C12081());
        this.bannerUrl = createBannerUrl(deviceIdParameters);
        this.clickBehavior = ClickBehavior.HIDE;
        this.closeBannerThread = new C12092();
    }

    private String createBannerUrl(DeviceIdParameters deviceIdParameters) {
        String deviceIdSection;
        double[] coords = Geolocation.getCoords(this.adView.getContext());
        String deviceId = "";
        String androidId = "";
        Configuration configuration = Configuration.getInstance(this.adView.getContext());
        if (deviceIdParameters != null) {
            String advId = deviceIdParameters.getAdvId();
            String limitAdTrackingEnabled = deviceIdParameters.getLimitAdTrackingEnabled().toString().toLowerCase();
            String aid = deviceIdParameters.getaId();
            if (TextUtils.isEmpty(advId)) {
                deviceIdSection = "&aid=" + aid;
            } else {
                deviceIdSection = "&advid=" + advId + "&limit_ad_tracking_enabled=" + limitAdTrackingEnabled;
            }
        } else {
            deviceIdSection = "&hid=&aid=";
        }
        return Constants.AD_SERVER_DOMAIN_URL + ("?widgetid=" + configuration.getApplicationId() + "&guid=" + configuration.getAppGuid() + "&v=" + Constants.PLATFORM_VERSION + deviceIdSection + "&tlat=" + coords[0] + "&tlon=" + coords[1] + "&p=android&sdk=1&templateversion=" + configuration.getTemplateVersion());
    }

    public String getClickUrl() {
        return this.clickUrl;
    }

    void setClickUrl(String clickUrl) {
        this.clickUrl = clickUrl;
    }

    void setHeaderReceiver(HeadersReceiver receiver) {
        this.headersReceiver = receiver;
    }

    void setAdsLoadingFinishedListener(AdsLoadingFinishedListener listener) {
        this.loadingFinishedListener = listener;
    }

    public void refresh() {
        if (this.adView != null && this.adView.getBrowser() != null) {
            this.adView.getBrowser().post(new C12103());
        }
    }

    void reload() {
        try {
            new C12134().start();
        } catch (Exception e) {
            Log.e("AdsLoader", e.getMessage());
        }
    }

    public void changeClickBehavior(ClickBehavior clickBehavior) {
        this.clickBehavior = clickBehavior;
    }

    public void setRefreshTimeout(float seconds) {
        if (((double) seconds) > 0.0d) {
            this.refreshTimer.cancel();
            this.refreshTimer = new Timer();
            this.refreshTimer.scheduleAtFixedRate(new C12145(), (long) ((int) (1000.0f * seconds)), 100);
        }
    }

    public void setHideTimeout(float seconds) {
        if (((double) seconds) <= 0.0d) {
            seconds = 60000.0f;
        }
        this.adView.removeCallbacks(this.closeBannerThread);
        this.adView.postDelayed(this.closeBannerThread, (long) (1000.0f * seconds));
    }

    public void acceptBehavior(BehaviorVisitor visitor) {
        if (visitor instanceof LoaderBehavior) {
            ((LoaderBehavior) visitor).visit((BehaviorAcceptor) this);
        }
    }

    private void setDefaults() {
        this.refreshTimer.cancel();
        setHideTimeout(60000.0f);
        this.adView.applyDefaultSettings();
    }

    public boolean loadStarted(WebView view, String url, Bitmap favicon) {
        if (url == null) {
            reload();
            return true;
        } else if (EndpointGetter.getUrlWithoutArguments(url).equals(EndpointGetter.getUrlWithoutArguments(this.bannerUrl)) || BannerUtils.isDataTextHtmlUrl(url)) {
            this.adView.switchToHtmlBanner();
            return true;
        } else if (CorrectClickStateHolder.getInstance().isClickAllowed()) {
            Intent intent;
            CorrectClickStateHolder.getInstance().reset();
            Log.d("AUTOCLICK_DETECT", "click ALLOWED!");
            if (this.clickBehavior == ClickBehavior.HIDE) {
                this.adView.hide();
                this.refreshTimer.cancel();
            } else if (this.clickBehavior == ClickBehavior.REMAIN_ON_SCREEN) {
                reload();
            }
            view.stopLoading();
            if (this.openInNativeBrowser) {
                intent = new Intent(this.adView.getContext(), BrowserActivity.class);
                intent.putExtra(BrowserActivity.KEY_BROWSER_URL, url);
                intent.putExtra(BrowserActivity.KEY_BANNER_TYPE, BrowserActivity.BANNER_TYPE_SMALL);
                intent.putExtra(BrowserActivity.KEY_UNIQ_ID, this.adView.getUniqueId());
                intent.addFlags(ErrorDialogData.BINDER_CRASH);
            } else {
                intent = new Intent("android.intent.action.VIEW", Uri.parse(url));
            }
            this.adView.getContext().startActivity(intent);
            if (!TextUtils.isEmpty(this.clickUrl)) {
                AppsgeyserServerClient.getInstance().sendClickInfo(this.clickUrl, view.getContext());
                YandexMetrica.reportEvent("small_banner_clicked");
            }
            return false;
        } else {
            String encodedUrl = "-1";
            try {
                encodedUrl = URLEncoder.encode(url, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            view.loadUrl("javascript:processACWithUrl('" + encodedUrl + "')");
            return false;
        }
    }

    public void loadFinished(WebView view, String url) {
        if (url.equals("data:text/html,chromewebdata") || url.equals("about:blank")) {
            this.adView.hide();
            return;
        }
        this.adView.show();
        if (this.adView.isAdMobNow()) {
            this.adView.switchToAdMobBanner();
        } else if (this.adView.isMoPubNow()) {
            this.adView.switchToMoPubBanner();
        } else {
            this.adView.switchToHtmlBanner();
        }
        if (url.equalsIgnoreCase(this.bannerUrl) || BannerUtils.isDataTextHtmlUrl(url)) {
            setDefaults();
            if (this.loadingFinishedListener != null) {
                this.loadingFinishedListener.onAdLoadFinished();
            }
        }
    }

    void stopLoading() {
        this.adView.hide();
    }

    void proceedClick(final String url) {
        if (this.adView.getBrowser() != null) {
            this.adView.getBrowser().post(new Runnable() {
                public void run() {
                    AdsLoader.this.adView.getBrowser().loadUrl(url);
                }
            });
        }
    }

    void forceOpenInNativeBrowser(boolean openInNativeBrowser) {
        this.openInNativeBrowser = openInNativeBrowser;
    }
}
