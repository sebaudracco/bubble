package com.mopub.mobileads;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;
import com.mopub.common.AdReport;
import com.mopub.common.CreativeOrientation;
import com.mopub.common.DataKeys;
import com.mopub.common.ExternalViewabilitySessionManager;
import com.mopub.common.IntentActions;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.DeviceUtils;
import com.mopub.common.util.JavaScriptWebViewCallbacks;
import com.mopub.mobileads.CustomEventInterstitial.CustomEventInterstitialListener;
import com.mopub.mobileads.factories.HtmlInterstitialWebViewFactory;
import java.io.Serializable;

public class MoPubActivity extends BaseInterstitialActivity {
    @Nullable
    private ExternalViewabilitySessionManager mExternalViewabilitySessionManager;
    @Nullable
    private HtmlInterstitialWebView mHtmlInterstitialWebView;

    class BroadcastingInterstitialListener implements CustomEventInterstitialListener {
        BroadcastingInterstitialListener() {
        }

        public void onInterstitialLoaded() {
            if (MoPubActivity.this.mHtmlInterstitialWebView != null) {
                MoPubActivity.this.mHtmlInterstitialWebView.loadUrl(JavaScriptWebViewCallbacks.WEB_VIEW_DID_APPEAR.getUrl());
            }
        }

        public void onInterstitialFailed(MoPubErrorCode errorCode) {
            BaseBroadcastReceiver.broadcastAction(MoPubActivity.this, MoPubActivity.this.getBroadcastIdentifier().longValue(), IntentActions.ACTION_INTERSTITIAL_FAIL);
            MoPubActivity.this.finish();
        }

        public void onInterstitialShown() {
        }

        public void onInterstitialClicked() {
            BaseBroadcastReceiver.broadcastAction(MoPubActivity.this, MoPubActivity.this.getBroadcastIdentifier().longValue(), IntentActions.ACTION_INTERSTITIAL_CLICK);
        }

        public void onLeaveApplication() {
        }

        public void onInterstitialDismissed() {
        }
    }

    public static void start(Context context, String htmlData, AdReport adReport, boolean isScrollable, String redirectUrl, String clickthroughUrl, CreativeOrientation creativeOrientation, long broadcastIdentifier) {
        try {
            context.startActivity(createIntent(context, htmlData, adReport, isScrollable, redirectUrl, clickthroughUrl, creativeOrientation, broadcastIdentifier));
        } catch (ActivityNotFoundException e) {
            Log.d("MoPubActivity", "MoPubActivity not found - did you declare it in AndroidManifest.xml?");
        }
    }

    static Intent createIntent(Context context, String htmlData, AdReport adReport, boolean isScrollable, String redirectUrl, String clickthroughUrl, CreativeOrientation orientation, long broadcastIdentifier) {
        Intent intent = new Intent(context, MoPubActivity.class);
        intent.putExtra(DataKeys.HTML_RESPONSE_BODY_KEY, htmlData);
        intent.putExtra(DataKeys.SCROLLABLE_KEY, isScrollable);
        intent.putExtra(DataKeys.CLICKTHROUGH_URL_KEY, clickthroughUrl);
        intent.putExtra(DataKeys.REDIRECT_URL_KEY, redirectUrl);
        intent.putExtra(DataKeys.BROADCAST_IDENTIFIER_KEY, broadcastIdentifier);
        intent.putExtra(DataKeys.AD_REPORT_KEY, adReport);
        intent.putExtra(DataKeys.CREATIVE_ORIENTATION_KEY, orientation);
        intent.addFlags(ErrorDialogData.BINDER_CRASH);
        return intent;
    }

    static void preRenderHtml(Interstitial baseInterstitial, Context context, AdReport adReport, final CustomEventInterstitialListener customEventInterstitialListener, String htmlData, boolean isScrollable, String redirectUrl, String clickthroughUrl, long broadcastIdentifier) {
        HtmlInterstitialWebView htmlInterstitialWebView = HtmlInterstitialWebViewFactory.create(context.getApplicationContext(), adReport, customEventInterstitialListener, isScrollable, redirectUrl, clickthroughUrl);
        htmlInterstitialWebView.enablePlugins(false);
        htmlInterstitialWebView.enableJavascriptCaching();
        htmlInterstitialWebView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if ("mopub://finishLoad".equals(url)) {
                    customEventInterstitialListener.onInterstitialLoaded();
                } else if ("mopub://failLoad".equals(url)) {
                    customEventInterstitialListener.onInterstitialFailed(null);
                }
                return true;
            }
        });
        ExternalViewabilitySessionManager externalViewabilitySessionManager = new ExternalViewabilitySessionManager(context);
        externalViewabilitySessionManager.createDisplaySession(context, htmlInterstitialWebView, true);
        htmlInterstitialWebView.loadHtmlResponse(htmlData);
        WebViewCacheService.storeWebViewConfig(Long.valueOf(broadcastIdentifier), baseInterstitial, htmlInterstitialWebView, externalViewabilitySessionManager);
    }

    public View getAdView() {
        Intent intent = getIntent();
        boolean isScrollable = intent.getBooleanExtra(DataKeys.SCROLLABLE_KEY, false);
        String redirectUrl = intent.getStringExtra(DataKeys.REDIRECT_URL_KEY);
        String clickthroughUrl = intent.getStringExtra(DataKeys.CLICKTHROUGH_URL_KEY);
        String htmlResponse = intent.getStringExtra(DataKeys.HTML_RESPONSE_BODY_KEY);
        Long broadcastIdentifier = getBroadcastIdentifier();
        if (broadcastIdentifier != null) {
            WebViewCacheService$Config config = WebViewCacheService.popWebViewConfig(broadcastIdentifier);
            if (config != null && (config.getWebView() instanceof HtmlInterstitialWebView)) {
                this.mHtmlInterstitialWebView = (HtmlInterstitialWebView) config.getWebView();
                this.mHtmlInterstitialWebView.init(new BroadcastingInterstitialListener(), isScrollable, redirectUrl, clickthroughUrl, this.mAdReport != null ? this.mAdReport.getDspCreativeId() : null);
                this.mHtmlInterstitialWebView.enablePlugins(true);
                this.mHtmlInterstitialWebView.loadUrl(JavaScriptWebViewCallbacks.WEB_VIEW_DID_APPEAR.getUrl());
                this.mExternalViewabilitySessionManager = config.getViewabilityManager();
                return this.mHtmlInterstitialWebView;
            }
        }
        MoPubLog.m12061d("WebView cache miss. Recreating the WebView.");
        this.mHtmlInterstitialWebView = HtmlInterstitialWebViewFactory.create(getApplicationContext(), this.mAdReport, new BroadcastingInterstitialListener(), isScrollable, redirectUrl, clickthroughUrl);
        this.mExternalViewabilitySessionManager = new ExternalViewabilitySessionManager(this);
        this.mExternalViewabilitySessionManager.createDisplaySession(this, this.mHtmlInterstitialWebView, true);
        this.mHtmlInterstitialWebView.loadHtmlResponse(htmlResponse);
        return this.mHtmlInterstitialWebView;
    }

    protected void onCreate(Bundle savedInstanceState) {
        CreativeOrientation requestedOrientation;
        super.onCreate(savedInstanceState);
        Serializable orientationExtra = getIntent().getSerializableExtra(DataKeys.CREATIVE_ORIENTATION_KEY);
        if (orientationExtra == null || !(orientationExtra instanceof CreativeOrientation)) {
            requestedOrientation = CreativeOrientation.UNDEFINED;
        } else {
            requestedOrientation = (CreativeOrientation) orientationExtra;
        }
        DeviceUtils.lockOrientation(this, requestedOrientation);
        if (this.mExternalViewabilitySessionManager != null) {
            this.mExternalViewabilitySessionManager.startDeferredDisplaySession(this);
        }
        BaseBroadcastReceiver.broadcastAction(this, getBroadcastIdentifier().longValue(), IntentActions.ACTION_INTERSTITIAL_SHOW);
    }

    protected void onDestroy() {
        if (this.mExternalViewabilitySessionManager != null) {
            this.mExternalViewabilitySessionManager.endDisplaySession();
            this.mExternalViewabilitySessionManager = null;
        }
        if (this.mHtmlInterstitialWebView != null) {
            this.mHtmlInterstitialWebView.loadUrl(JavaScriptWebViewCallbacks.WEB_VIEW_DID_CLOSE.getUrl());
            this.mHtmlInterstitialWebView.destroy();
        }
        BaseBroadcastReceiver.broadcastAction(this, getBroadcastIdentifier().longValue(), IntentActions.ACTION_INTERSTITIAL_DISMISS);
        super.onDestroy();
    }
}
