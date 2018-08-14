package com.mopub.mobileads;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.webkit.WebSettings;
import com.facebook.ads.AudienceNetworkActivity;
import com.github.lzyzsd.jsbridge.BridgeUtil;
import com.mopub.common.AdReport;
import com.mopub.common.Constants;
import com.mopub.common.logging.MoPubLog;
import com.mopub.network.Networking;

public class BaseHtmlWebView extends BaseWebView implements ViewGestureDetector$UserClickListener {
    private boolean mClicked;
    private final ViewGestureDetector mViewGestureDetector;

    public BaseHtmlWebView(Context context, AdReport adReport) {
        super(context);
        disableScrollingAndZoom();
        getSettings().setJavaScriptEnabled(true);
        this.mViewGestureDetector = new ViewGestureDetector(context, this, adReport);
        this.mViewGestureDetector.setUserClickListener(this);
        enablePlugins(true);
        setBackgroundColor(0);
    }

    public void init(boolean isScrollable) {
        initializeOnTouchListener(isScrollable);
    }

    public void loadUrl(@Nullable String url) {
        if (url != null) {
            if (url.startsWith(BridgeUtil.JAVASCRIPT_STR)) {
                super.loadUrl(url);
            } else {
                MoPubLog.m12061d("Loading url: " + url);
            }
        }
    }

    public void stopLoading() {
        if (this.mIsDestroyed) {
            MoPubLog.m12069w(BaseHtmlWebView.class.getSimpleName() + "#stopLoading() called after destroy()");
            return;
        }
        WebSettings webSettings = getSettings();
        if (webSettings == null) {
            MoPubLog.m12069w(BaseHtmlWebView.class.getSimpleName() + "#getSettings() returned null");
            return;
        }
        webSettings.setJavaScriptEnabled(false);
        super.stopLoading();
        webSettings.setJavaScriptEnabled(true);
    }

    private void disableScrollingAndZoom() {
        setHorizontalScrollBarEnabled(false);
        setHorizontalScrollbarOverlay(false);
        setVerticalScrollBarEnabled(false);
        setVerticalScrollbarOverlay(false);
        getSettings().setSupportZoom(false);
    }

    void loadHtmlResponse(String htmlResponse) {
        loadDataWithBaseURL(Networking.getBaseUrlScheme() + "://" + Constants.HOST + BridgeUtil.SPLIT_MARK, htmlResponse, AudienceNetworkActivity.WEBVIEW_MIME_TYPE, AudienceNetworkActivity.WEBVIEW_ENCODING, null);
    }

    void initializeOnTouchListener(final boolean isScrollable) {
        setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                BaseHtmlWebView.this.mViewGestureDetector.sendTouchEvent(event);
                return event.getAction() == 2 && !isScrollable;
            }
        });
    }

    public void onUserClick() {
        this.mClicked = true;
    }

    public void onResetUserClick() {
        this.mClicked = false;
    }

    public boolean wasClicked() {
        return this.mClicked;
    }
}
