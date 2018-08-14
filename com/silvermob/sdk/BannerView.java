package com.silvermob.sdk;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.util.Log;
import android.view.MotionEvent;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.facebook.ads.AudienceNetworkActivity;
import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.silvermob.sdk.Const.BannerType;
import com.silvermob.sdk.Const.ClickType;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.text.MessageFormat;

@SuppressLint({"ViewConstructor", "SetJavaScriptEnabled"})
public class BannerView extends RelativeLayout {
    private static Field mWebView_LAYER_TYPE_SOFTWARE;
    private static Method mWebView_SetLayerType;
    private BannerAdViewListener adListener;
    private boolean animation;
    private Animation fadeInAnimation = null;
    private int height;
    private boolean isInternalBrowser = false;
    private boolean isLoaded = false;
    private Context mContext = null;
    protected boolean mIsInForeground;
    private BroadcastReceiver mScreenStateReceiver;
    private AdResponse response;
    private boolean wasUserAction = false;
    private WebSettings webSettings;
    private WebView webView;
    private int width;

    interface BannerAdViewListener {
        void onClick();

        void onLoad();

        void onPause();

        void onRefreshed();

        void onResume();
    }

    class C39471 extends BroadcastReceiver {
        C39471() {
        }

        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("android.intent.action.SCREEN_OFF")) {
                if (BannerView.this.mIsInForeground) {
                    Log.d("BannerView", "Screen sleep with ad in foreground, disable refresh");
                    BannerView.this.pause();
                    return;
                }
                Log.d("BannerView", "Screen sleep but ad in background; refresh should already be disabled");
            } else if (!intent.getAction().equals("android.intent.action.USER_PRESENT")) {
            } else {
                if (BannerView.this.mIsInForeground) {
                    BannerView.this.resume();
                    Log.d("BannerView", "Screen wake / ad in foreground, reset refresh");
                    return;
                }
                Log.d("BannerView", "Screen wake but ad in background; don't enable refresh");
            }
        }
    }

    class C39502 implements Runnable {

        class C39492 extends WebViewClient {
            C39492() {
            }

            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (!BannerView.this.wasUserAction) {
                    return false;
                }
                if (BannerView.this.response.getSkipOverlay() == 1) {
                    BannerView.this.doOpenUrl(url);
                    return true;
                }
                BannerView.this.openLink();
                return true;
            }
        }

        C39502() {
        }

        public void run() {
            BannerView.this.webView = new WebView(BannerView.this.mContext) {
                public boolean onTouchEvent(MotionEvent event) {
                    BannerView.this.wasUserAction = true;
                    return super.onTouchEvent(event);
                }

                public void draw(Canvas canvas) {
                    if (getWidth() > 0 && getHeight() > 0) {
                        super.draw(canvas);
                    }
                }
            };
            BannerView.this.webSettings = BannerView.this.webView.getSettings();
            BannerView.this.webSettings.setJavaScriptEnabled(true);
            BannerView.this.webView.setBackgroundColor(0);
            BannerView.setLayer(BannerView.this.webView);
            if (VERSION.SDK_INT >= 19) {
                BannerView.this.webView.setWebViewClient(new C39492());
                BannerView.this.webView.setVerticalScrollBarEnabled(false);
                BannerView.this.webView.setHorizontalScrollBarEnabled(false);
            } else {
                BannerView.this.webView.setWebViewClient(new C39492());
                BannerView.this.webView.setVerticalScrollBarEnabled(false);
                BannerView.this.webView.setHorizontalScrollBarEnabled(false);
            }
        }
    }

    private void registerScreenStateBroadcastReceiver() {
        this.mScreenStateReceiver = new C39471();
        IntentFilter filter = new IntentFilter("android.intent.action.SCREEN_OFF");
        filter.addAction("android.intent.action.USER_PRESENT");
        this.mContext.registerReceiver(this.mScreenStateReceiver, filter);
    }

    protected void onWindowVisibilityChanged(int visibility) {
        super.onWindowVisibilityChanged(visibility);
        if (visibility == 0) {
            this.mIsInForeground = true;
            resume();
        } else {
            this.mIsInForeground = false;
            pause();
        }
        Log.d("BannerView", "onWindowVisibilityChanged: " + visibility);
    }

    private void pause() {
        this.adListener.onPause();
    }

    private void resume() {
        this.adListener.onResume();
    }

    public BannerView(Context context, AdResponse response, int width, int height, boolean animation, BannerAdViewListener adListener) {
        super(context);
        this.mContext = context;
        this.response = response;
        this.width = width;
        this.height = height;
        this.animation = animation;
        this.adListener = adListener;
        initialize(context);
    }

    public void updateAdRsponse(AdResponse r) {
        this.response = r;
    }

    private void createWebView() {
        ((Activity) this.mContext).runOnUiThread(new C39502());
    }

    private void doOpenUrl(String url) {
        if (this.response.getClickUrl() != null && this.response.getSkipOverlay() == 1) {
            makeTrackingRequest(this.response.getClickUrl());
        }
        if (this.response.getClickType() == null || !this.response.getClickType().equals(ClickType.IN_APP) || (!url.startsWith("http://") && !url.startsWith("https://"))) {
            startActivity(new Intent("android.intent.action.VIEW", Uri.parse(url)));
        } else if (url.endsWith(".mp4")) {
            Intent i = new Intent("android.intent.action.VIEW");
            i.setDataAndType(Uri.parse(url), "video/mp4");
            startActivity(i);
        } else {
            Intent intent = new Intent(getContext(), InAppWebView.class);
            intent.putExtra(Const.REDIRECT_URI, url);
            startActivity(intent);
        }
        this.adListener.onClick();
    }

    private void startActivity(Intent i) {
        if (!(getContext() instanceof Activity)) {
            i.addFlags(ErrorDialogData.BINDER_CRASH);
        }
        getContext().startActivity(i);
    }

    private void makeTrackingRequest(final String clickUrl) {
        new AsyncTask<Void, Void, Void>() {
            protected Void doInBackground(Void... params) {
                return clickUrl.startsWith("market") ? null : null;
            }
        }.execute(new Void[0]);
    }

    static {
        initCompatibility();
    }

    private static void initCompatibility() {
        try {
            for (Method m : WebView.class.getMethods()) {
                if (m.getName().equals("setLayerType")) {
                    mWebView_SetLayerType = m;
                    break;
                }
            }
            Log.v("Silvemob BannerView: ", "set layer " + mWebView_SetLayerType);
            mWebView_LAYER_TYPE_SOFTWARE = WebView.class.getField("LAYER_TYPE_SOFTWARE");
            Log.v("Silvemob BannerView: ", "set1 layer " + mWebView_LAYER_TYPE_SOFTWARE);
        } catch (SecurityException e) {
            Log.v("Silvemob BannerView: ", "SecurityException");
        } catch (NoSuchFieldException e2) {
            Log.v("Silvemob BannerView: ", "NoSuchFieldException");
        }
    }

    private static void setLayer(WebView webView) {
        if (mWebView_SetLayerType == null || mWebView_LAYER_TYPE_SOFTWARE == null) {
            Log.v("Silvemob BannerView: ", "Set Layer is not supported");
            return;
        }
        try {
            Log.v("Silvemob BannerView: ", "Set Layer is supported");
            mWebView_SetLayerType.invoke(webView, new Object[]{Integer.valueOf(mWebView_LAYER_TYPE_SOFTWARE.getInt(WebView.class)), null});
        } catch (InvocationTargetException e) {
            Log.v("Silvemob BannerView: ", "Set InvocationTargetException");
        } catch (IllegalArgumentException e2) {
            Log.v("Silvemob BannerView: ", "Set IllegalArgumentException");
        } catch (IllegalAccessException e3) {
            Log.v("Silvemob BannerView: ", "Set IllegalAccessException");
        }
    }

    private void buildBannerView() {
        createWebView();
        Log.d("Silvemob BannerView: ", "Create view flipper");
        float scale = this.mContext.getResources().getDisplayMetrics().density;
        if (this.width <= 0 || this.height <= 0) {
            setLayoutParams(new LayoutParams((int) ((BitmapDescriptorFactory.HUE_MAGENTA * scale) + 0.5f), (int) ((50.0f * scale) + 0.5f)));
        } else {
            setLayoutParams(new LayoutParams((int) ((((float) this.width) * scale) + 0.5f), (int) ((((float) this.height) * scale) + 0.5f)));
        }
        addView(this.webView, new LayoutParams(-1, -1));
        Log.d("Silvemob BannerView: ", "animation: " + this.animation);
        if (this.animation) {
            this.fadeInAnimation = new TranslateAnimation(2, 0.0f, 2, 0.0f, 2, 1.0f, 2, 0.0f);
            this.fadeInAnimation.setDuration(1000);
            this.webView.setAnimation(this.fadeInAnimation);
        }
    }

    private void initialize(Context context) {
        initCompatibility();
        buildBannerView();
        registerScreenStateBroadcastReceiver();
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        unregisterScreenStateBroadcastReceiver();
        Log.v("BannerView", "onDetachedFromWindow");
    }

    public void release() {
        unregisterScreenStateBroadcastReceiver();
    }

    private void unregisterScreenStateBroadcastReceiver() {
        try {
            this.mContext.unregisterReceiver(this.mScreenStateReceiver);
        } catch (Exception e) {
            Log.d("BannerView", "Failed to unregister screen state broadcast receiver (never registered).");
        }
    }

    public boolean isInternalBrowser() {
        return this.isInternalBrowser;
    }

    private void openLink() {
        if (this.response != null && this.response.getClickUrl() != null) {
            doOpenUrl(this.response.getClickUrl());
        }
    }

    public void setAdListener(BannerAdViewListener bannerListener) {
        this.adListener = bannerListener;
    }

    public void setInternalBrowser(boolean isInternalBrowser) {
        this.isInternalBrowser = isInternalBrowser;
    }

    public void showContent() {
        try {
            String text;
            if (this.response.getType().equals(BannerType.IMAGE)) {
                text = MessageFormat.format(Const.IMAGE_BODY_TEMPLATE, new Object[]{this.response.getImageUrl(), this.response.getBannerWidth(), this.response.getBannerHeight()});
                Log.d("Silvemob BannerView: ", "set image: " + text);
                text = Uri.encode(Const.HIDE_BORDER + text);
                if (!text.contains("<html>")) {
                    text = "<html><head><meta charset=\"utf-8\"></head><body style='margin:0;padding:0;'>" + text + "</body></html>";
                }
                this.webView.loadData(text, AudienceNetworkActivity.WEBVIEW_MIME_TYPE, "UTF-8");
                if (this.isLoaded) {
                    this.adListener.onRefreshed();
                } else {
                    this.adListener.onLoad();
                }
                this.isLoaded = true;
            } else if (this.response.getType().equals("html")) {
                text = Const.HIDE_BORDER + this.response.getHTMLCode();
                if (!text.contains("<html>")) {
                    text = "<html><head><meta charset=\"utf-8\"></head><body style='margin:0;padding:0;'>" + text + "</body></html>";
                }
                this.webView.setWebChromeClient(new WebChromeClient());
                this.webView.getSettings().setJavaScriptEnabled(true);
                this.webView.getSettings().setCacheMode(2);
                this.webView.getSettings().setAppCacheEnabled(false);
                if (VERSION.SDK_INT >= 11) {
                    this.webView.setLayerType(2, null);
                }
                this.webView.loadData(URLEncoder.encode(text, AudienceNetworkActivity.WEBVIEW_ENCODING).replaceAll("\\+", " "), "text/html;charset=utf-8", "UTF-8");
                if (this.isLoaded) {
                    this.adListener.onRefreshed();
                } else {
                    this.adListener.onLoad();
                }
                this.isLoaded = true;
            }
            if (this.animation) {
                this.webView.startAnimation(this.fadeInAnimation);
            }
        } catch (Throwable t) {
            Log.e("Silvemob BannerView: ", "Exception in show content: " + t.getMessage());
        }
    }
}
