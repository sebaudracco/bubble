package com.mopub.mobileads;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.webkit.WebSettings.PluginState;
import android.webkit.WebView;
import com.facebook.ads.AudienceNetworkActivity;
import com.mopub.common.VisibleForTesting;
import com.mopub.common.util.Views;
import com.mopub.mobileads.util.WebViews;

public class BaseWebView extends WebView {
    private static boolean sDeadlockCleared = false;
    protected boolean mIsDestroyed;

    public BaseWebView(Context context) {
        super(context.getApplicationContext());
        enablePlugins(false);
        restrictDeviceContentAccess();
        WebViews.setDisableJSChromeClient(this);
        if (!sDeadlockCleared) {
            clearWebViewDeadlock(getContext());
            sDeadlockCleared = true;
        }
    }

    public void destroy() {
        this.mIsDestroyed = true;
        Views.removeFromParent(this);
        removeAllViews();
        super.destroy();
    }

    public void enablePlugins(boolean enabled) {
        if (VERSION.SDK_INT < 18) {
            if (enabled) {
                getSettings().setPluginState(PluginState.ON);
            } else {
                getSettings().setPluginState(PluginState.OFF);
            }
        }
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    protected void enableJavascriptCaching() {
        getSettings().setJavaScriptEnabled(true);
        getSettings().setDomStorageEnabled(true);
        getSettings().setAppCacheEnabled(true);
        getSettings().setAppCachePath(getContext().getCacheDir().getAbsolutePath());
    }

    private void restrictDeviceContentAccess() {
        getSettings().setAllowFileAccess(false);
        getSettings().setAllowContentAccess(false);
        getSettings().setAllowFileAccessFromFileURLs(false);
        getSettings().setAllowUniversalAccessFromFileURLs(false);
    }

    private void clearWebViewDeadlock(@NonNull Context context) {
        if (VERSION.SDK_INT == 19) {
            WebView webView = new WebView(context.getApplicationContext());
            webView.setBackgroundColor(0);
            webView.loadDataWithBaseURL(null, "", AudienceNetworkActivity.WEBVIEW_MIME_TYPE, "UTF-8", null);
            LayoutParams params = new LayoutParams();
            params.width = 1;
            params.height = 1;
            params.type = 2005;
            params.flags = 16777240;
            params.format = -2;
            params.gravity = 8388659;
            ((WindowManager) context.getSystemService("window")).addView(webView, params);
        }
    }

    @Deprecated
    @VisibleForTesting
    void setIsDestroyed(boolean isDestroyed) {
        this.mIsDestroyed = isDestroyed;
    }
}
