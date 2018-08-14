package com.appsgeyser.sdk;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.CookieSyncManager;
import android.webkit.DownloadListener;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.appsgeyser.sdk.server.StatController;
import com.appsgeyser.sdk.utils.Drawables;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;
import org.telegram.ui.ActionBar.Theme;

public class BrowserActivity extends Activity {
    public static final String BANNER_TYPE_FULLSCREEN = "banner_type_fullscreen";
    public static final String BANNER_TYPE_SMALL = "banner_type_small";
    private static final int HTML_SUBSTRING_LENGTH = 1000;
    public static final String KEY_BANNER_TYPE = "banner_type";
    public static final String KEY_BROWSER_URL = "browser_url";
    public static final String KEY_TIMER_DURATION = "timer_duration";
    public static final String KEY_UNIQ_ID = "uniqid";
    private static final int MIN_HTML_ALLOWED_LENGTH = 40;
    private static final int REDIRECT_FINISH_TIMEOUT = 1000;
    private static final AtomicInteger sNextGeneratedId = new AtomicInteger(30000);
    private final Handler handler = new Handler();
    private boolean isFullScreenBanner = false;
    private ImageButton mBackButton;
    private ImageButton mCloseButton;
    private ImageButton mForwardButton;
    private final Runnable mHtmlCheckRunnable = new C11814();
    private ImageButton mRefreshButton;
    private TextView mTimer;
    private WebView mWebView;
    private boolean marketOpen;
    private long timerDuration = -1;
    private final long timerStep = 1000;
    private String uniqid;

    class C11781 extends WebViewClient {
        C11781() {
        }

        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            _handleRedirect(url);
            super.onPageStarted(view, url, favicon);
        }

        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return _handleRedirect(url);
        }

        private boolean _handleRedirect(String url) {
            BrowserActivity.this.handler.removeCallbacksAndMessages(null);
            if (url == null) {
                return false;
            }
            boolean isHttpUrl = BrowserActivity._isHttpUrl(url);
            boolean isMarketUrl = BrowserActivity._isMarketUrl(url);
            if (isMarketUrl && isHttpUrl) {
                url = BrowserActivity._replaceHttpWithMarketUrl(url);
            }
            if (!isMarketUrl && isHttpUrl) {
                return false;
            }
            HashMap<String, String> urlDetails = new HashMap();
            urlDetails.put("url", url);
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(url));
            if (BrowserActivity.deviceCanHandleIntent(BrowserActivity.this, intent)) {
                if (!BrowserActivity.this.marketOpen) {
                    BrowserActivity.this.startActivity(intent);
                    BrowserActivity.this.marketOpen = true;
                }
                if (BrowserActivity.this.isFullScreenBanner) {
                    StatController.getInstance().sendRequestAsyncByKey(StatController.KEY_CLICK_FINISH_MARKET, urlDetails, BrowserActivity.this, true);
                }
                BrowserActivity.this.finish();
                return true;
            } else if (!BrowserActivity.this.isFullScreenBanner) {
                return false;
            } else {
                StatController.getInstance().sendRequestAsyncByKey(StatController.KEY_CLICK_NO_MARKET_ON_DEVICE, urlDetails, BrowserActivity.this, true);
                return false;
            }
        }

        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            if (BrowserActivity.this.isFullScreenBanner) {
                HashMap<String, String> details = new HashMap();
                details.put("details", BrowserActivity.this._trimSubstring(Integer.toString(errorCode) + " : " + description, 1000));
                details.put("url", failingUrl);
                StatController.getInstance().sendRequestAsyncByKey(StatController.KEY_CLICK_LOADING_ERROR, details, BrowserActivity.this, true);
            }
            super.onReceivedError(view, errorCode, description, failingUrl);
        }

        public void onPageFinished(WebView view, String url) {
            if (!BrowserActivity._isMarketUrl(url) && BrowserActivity._isHttpUrl(url)) {
                BrowserActivity.this.handler.postDelayed(BrowserActivity.this.mHtmlCheckRunnable, 1000);
                super.onPageFinished(view, url);
            }
        }
    }

    class C11792 extends WebChromeClient {
        C11792() {
        }

        public void onProgressChanged(WebView webView, int progress) {
            BrowserActivity.this.setTitle("Loading...");
            BrowserActivity.this.setProgress(progress * 100);
            if (progress == 100) {
                BrowserActivity.this.setTitle(webView.getUrl());
            }
        }
    }

    class C11803 implements DownloadListener {
        C11803() {
        }

        public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(url));
            ResolveInfo ri = BrowserActivity.this.getPackageManager().resolveActivity(intent, 0);
            String resultKey = StatController.KEY_CLICK_CAN_NOT_START_DOWNLOAD;
            if (ri != null) {
                BrowserActivity.this.startActivity(intent);
                resultKey = StatController.KEY_CLICK_FINISH_DOWNLOAD;
            }
            if (BrowserActivity.this.isFullScreenBanner) {
                HashMap<String, String> details = new HashMap();
                details.put("url", url);
                StatController.getInstance().sendRequestAsyncByKey(resultKey, details, BrowserActivity.this, true);
            }
        }
    }

    class C11814 implements Runnable {
        C11814() {
        }

        public void run() {
            if (BrowserActivity.this.mWebView != null) {
                BrowserActivity.this.mWebView.loadUrl("javascript:window.HtmlViewer.detectHTML(document.documentElement.innerHTML);");
            }
        }
    }

    class C11825 implements OnClickListener {
        C11825() {
        }

        public void onClick(View v) {
            if (BrowserActivity.this.isFullScreenBanner) {
                HashMap<String, String> details = new HashMap();
                details.put("url", BrowserActivity.this.mWebView.getUrl());
                StatController.getInstance().sendRequestAsyncByKey(StatController.KEY_CLICK_CROSS_MINI_BROWSER, details, BrowserActivity.this, true);
            }
            BrowserActivity.this.finish();
        }
    }

    class C11836 implements OnClickListener {
        C11836() {
        }

        public void onClick(View v) {
            if (BrowserActivity.this.mWebView.canGoBack()) {
                BrowserActivity.this.mWebView.goBack();
            }
        }
    }

    class C11847 implements OnClickListener {
        C11847() {
        }

        public void onClick(View v) {
            if (BrowserActivity.this.mWebView.canGoForward()) {
                BrowserActivity.this.mWebView.goForward();
            }
        }
    }

    class C11858 implements OnClickListener {
        C11858() {
        }

        public void onClick(View v) {
            BrowserActivity.this.mWebView.reload();
        }
    }

    private class DetectJSInterface {
        static final String NAME = "HtmlViewer";

        private DetectJSInterface() {
        }

        @JavascriptInterface
        public void detectHTML(String html) {
            String resultKey = (html == null || html.length() < 40) ? StatController.KEY_CLICK_FINISH_EMPTY_HTML : StatController.KEY_CLICK_FINISH_HTML;
            if (BrowserActivity.this.isFullScreenBanner) {
                HashMap<String, String> details = new HashMap();
                details.put("details", BrowserActivity.this._trimSubstring(html, 1000));
                StatController.getInstance().sendRequestAsyncByKey(resultKey, details, BrowserActivity.this, true);
            }
        }
    }

    @SuppressLint({"AddJavascriptInterface", "SetJavaScriptEnabled"})
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        enableCookies();
        Intent intent = getIntent();
        String url = intent.getStringExtra(KEY_BROWSER_URL);
        String bannerType = intent.getStringExtra(KEY_BANNER_TYPE);
        this.uniqid = intent.getStringExtra(KEY_UNIQ_ID);
        this.isFullScreenBanner = bannerType.equals(BANNER_TYPE_FULLSCREEN);
        this.timerDuration = intent.getLongExtra(KEY_TIMER_DURATION, -1);
        requestWindowFeature(2);
        getWindow().setFeatureInt(2, -1);
        getWindow().setFlags(1024, 1024);
        setContentView(_initBrowserView());
        this.mWebView.resumeTimers();
        this.mWebView.getSettings().setJavaScriptEnabled(true);
        this.mWebView.addJavascriptInterface(new DetectJSInterface(), "HtmlViewer");
        this.mWebView.setWebViewClient(new C11781());
        this.mWebView.setWebChromeClient(new C11792());
        this.mWebView.setDownloadListener(new C11803());
        initButtons();
        if (this.timerDuration > 0) {
            showTimer();
            startTimer();
        } else {
            showClose();
        }
        this.mWebView.loadUrl(url);
    }

    private void initButtons() {
        this.mCloseButton.setOnClickListener(new C11825());
        this.mBackButton.setOnClickListener(new C11836());
        this.mForwardButton.setOnClickListener(new C11847());
        this.mRefreshButton.setOnClickListener(new C11858());
    }

    private void enableCookies() {
        CookieSyncManager.createInstance(this);
        CookieSyncManager.getInstance().startSync();
    }

    protected void onPause() {
        super.onPause();
        CookieSyncManager.getInstance().stopSync();
    }

    protected void onResume() {
        super.onResume();
        this.mWebView.resumeTimers();
        CookieSyncManager.getInstance().startSync();
    }

    protected void onDestroy() {
        super.onDestroy();
        this.mWebView.destroy();
        this.mWebView = null;
    }

    public void onBackPressed() {
    }

    public static boolean deviceCanHandleIntent(Context context, Intent intent) {
        try {
            if (context.getPackageManager().queryIntentActivities(intent, 0).isEmpty()) {
                return false;
            }
            return true;
        } catch (NullPointerException e) {
            return false;
        }
    }

    private static String _replaceHttpWithMarketUrl(String url) {
        if (!_isMarketUrl(url)) {
            return url;
        }
        return "market://details?" + Uri.parse(url).getEncodedQuery();
    }

    private static boolean _isMarketUrl(String url) {
        Uri uri = Uri.parse(url);
        String host = uri.getHost();
        return uri.getScheme().equals("market") || (host != null && host.equals("play.google.com"));
    }

    private static boolean _isHttpUrl(String url) {
        String scheme = Uri.parse(url).getScheme();
        return scheme.equals("http") || scheme.equals("https");
    }

    private String _trimSubstring(String string, int count) {
        if (string == null) {
            return null;
        }
        if (count > string.length()) {
            count = string.length();
        }
        return string.substring(0, count - 1);
    }

    private void showTimer() {
        this.mTimer.setVisibility(0);
        this.mCloseButton.setVisibility(8);
    }

    private void startTimer() {
        new CountDownTimer(this.timerDuration, 1000) {
            public void onTick(long millisUntilFinished) {
                BrowserActivity.this.mTimer.setText(String.valueOf(millisUntilFinished / 1000));
            }

            public void onFinish() {
                BrowserActivity.this.showClose();
            }
        }.start();
    }

    private void showClose() {
        this.mTimer.setVisibility(8);
        this.mCloseButton.setVisibility(0);
    }

    public static int generateViewId() {
        int result;
        int newValue;
        do {
            result = sNextGeneratedId.get();
            newValue = result + 1;
            if (newValue > ViewCompat.MEASURED_SIZE_MASK) {
                newValue = 1;
            }
        } while (!sNextGeneratedId.compareAndSet(result, newValue));
        return result;
    }

    private View _initBrowserView() {
        LinearLayout rootView = new LinearLayout(this);
        LayoutParams rlp = new LayoutParams(-1, -1, 1.0f);
        rootView.setOrientation(1);
        RelativeLayout buttonWrapper = new RelativeLayout(this);
        RelativeLayout.LayoutParams buttonWrapperParams = new RelativeLayout.LayoutParams(-1, pixelsByDp(40));
        buttonWrapperParams.addRule(12);
        buttonWrapper.setLayoutParams(buttonWrapperParams);
        LinearLayout buttonLayout = new LinearLayout(this);
        buttonLayout.setBackgroundColor(Theme.ACTION_BAR_MEDIA_PICKER_COLOR);
        buttonLayout.setOrientation(0);
        RelativeLayout.LayoutParams rllp = new RelativeLayout.LayoutParams(-1, pixelsByDp(40));
        rllp.addRule(10);
        rllp.addRule(12);
        rllp.addRule(9);
        rllp.addRule(11);
        buttonLayout.setLayoutParams(rllp);
        this.mBackButton = _getButton(Drawables.LEFT_ARROW.createDrawable(this));
        this.mForwardButton = _getButton(Drawables.RIGHT_ARROW.createDrawable(this));
        this.mRefreshButton = _getButton(Drawables.REFRESH.createDrawable(this));
        this.mCloseButton = _getButton(Drawables.CLOSE.createDrawable(this));
        buttonLayout.addView(this.mBackButton);
        buttonLayout.addView(this.mForwardButton);
        buttonLayout.addView(this.mRefreshButton);
        buttonLayout.addView(this.mCloseButton);
        this.mTimer = new TextView(this);
        this.mTimer.setBackgroundColor(0);
        this.mTimer.setPadding(pixelsByDp(5), pixelsByDp(5), pixelsByDp(5), pixelsByDp(5));
        this.mTimer.setGravity(17);
        this.mTimer.setTextColor(-1);
        this.mTimer.setTypeface(Typeface.DEFAULT_BOLD);
        LayoutParams tlp = new LayoutParams(pixelsByDp(30), pixelsByDp(30), 1.0f);
        tlp.gravity = 17;
        this.mTimer.setLayoutParams(tlp);
        buttonLayout.addView(this.mTimer);
        buttonWrapper.addView(buttonLayout);
        this.mWebView = new WebView(this);
        this.mWebView.setId(generateViewId());
        this.mWebView.setLayoutParams(new LayoutParams(-1, 0, 1.0f));
        rootView.addView(this.mWebView);
        rootView.addView(buttonWrapper);
        TextView mUniqIdView = new TextView(this);
        RelativeLayout.LayoutParams uniqidParams = new RelativeLayout.LayoutParams(-2, -2);
        uniqidParams.addRule(9);
        uniqidParams.addRule(10);
        mUniqIdView.setBackgroundColor(Theme.ACTION_BAR_MEDIA_PICKER_COLOR);
        mUniqIdView.setTextColor(-5592406);
        mUniqIdView.setTextSize((float) pixelsByDp(3));
        mUniqIdView.setLayoutParams(uniqidParams);
        mUniqIdView.setText(this.uniqid);
        buttonWrapper.addView(mUniqIdView);
        return rootView;
    }

    private ImageButton _getButton(Drawable drawable) {
        ImageButton imageButton = new ImageButton(this);
        imageButton.setBackgroundColor(0);
        imageButton.setPadding(pixelsByDp(5), pixelsByDp(5), pixelsByDp(5), pixelsByDp(5));
        imageButton.setScaleType(ScaleType.FIT_CENTER);
        LayoutParams layoutParams = new LayoutParams(pixelsByDp(30), pixelsByDp(30), 1.0f);
        layoutParams.gravity = 17;
        imageButton.setLayoutParams(layoutParams);
        imageButton.setImageDrawable(drawable);
        if (drawable.equals(Drawables.CLOSE)) {
            imageButton.setVisibility(8);
        }
        return imageButton;
    }

    private int pixelsByDp(int dp) {
        return (int) ((((float) dp) * getResources().getDisplayMetrics().density) + 0.5f);
    }
}
