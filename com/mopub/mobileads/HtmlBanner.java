package com.mopub.mobileads;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import com.mopub.common.AdReport;
import com.mopub.common.DataKeys;
import com.mopub.common.ExternalViewabilitySessionManager;
import com.mopub.common.VisibleForTesting;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.JavaScriptWebViewCallbacks;
import com.mopub.mobileads.CustomEventBanner.CustomEventBannerListener;
import com.mopub.mobileads.factories.HtmlBannerWebViewFactory;
import java.lang.ref.WeakReference;
import java.util.Map;

public class HtmlBanner extends CustomEventBanner {
    private boolean mBannerImpressionPixelCountEnabled = false;
    @Nullable
    private ExternalViewabilitySessionManager mExternalViewabilitySessionManager;
    @Nullable
    private HtmlBannerWebView mHtmlBannerWebView;
    @Nullable
    private WeakReference<Activity> mWeakActivity;

    protected void loadBanner(Context context, CustomEventBannerListener customEventBannerListener, Map<String, Object> localExtras, Map<String, String> serverExtras) {
        Object bannerImpressionPixelCountEnabledObject = localExtras.get(DataKeys.BANNER_IMPRESSION_PIXEL_COUNT_ENABLED);
        if (bannerImpressionPixelCountEnabledObject instanceof Boolean) {
            this.mBannerImpressionPixelCountEnabled = ((Boolean) bannerImpressionPixelCountEnabledObject).booleanValue();
        }
        if (extrasAreValid(serverExtras)) {
            String htmlData = (String) serverExtras.get(DataKeys.HTML_RESPONSE_BODY_KEY);
            try {
                this.mHtmlBannerWebView = HtmlBannerWebViewFactory.create(context, (AdReport) localExtras.get(DataKeys.AD_REPORT_KEY), customEventBannerListener, Boolean.valueOf((String) serverExtras.get(DataKeys.SCROLLABLE_KEY)).booleanValue(), (String) serverExtras.get(DataKeys.REDIRECT_URL_KEY), (String) serverExtras.get(DataKeys.CLICKTHROUGH_URL_KEY));
                AdViewController.setShouldHonorServerDimensions(this.mHtmlBannerWebView);
                if (context instanceof Activity) {
                    Activity activity = (Activity) context;
                    this.mWeakActivity = new WeakReference(activity);
                    this.mExternalViewabilitySessionManager = new ExternalViewabilitySessionManager(activity);
                    this.mExternalViewabilitySessionManager.createDisplaySession(activity, this.mHtmlBannerWebView, this.mBannerImpressionPixelCountEnabled);
                } else {
                    MoPubLog.m12061d("Unable to start viewability session for HTML banner: Context provided was not an Activity.");
                }
                this.mHtmlBannerWebView.loadHtmlResponse(htmlData);
                return;
            } catch (ClassCastException e) {
                MoPubLog.m12063e("LocalExtras contained an incorrect type.");
                customEventBannerListener.onBannerFailed(MoPubErrorCode.INTERNAL_ERROR);
                return;
            }
        }
        customEventBannerListener.onBannerFailed(MoPubErrorCode.NETWORK_INVALID_STATE);
    }

    protected void onInvalidate() {
        if (this.mExternalViewabilitySessionManager != null) {
            this.mExternalViewabilitySessionManager.endDisplaySession();
            this.mExternalViewabilitySessionManager = null;
        }
        if (this.mHtmlBannerWebView != null) {
            this.mHtmlBannerWebView.destroy();
        }
    }

    protected void trackMpxAndThirdPartyImpressions() {
        if (this.mHtmlBannerWebView != null) {
            this.mHtmlBannerWebView.loadUrl(JavaScriptWebViewCallbacks.WEB_VIEW_DID_APPEAR.getUrl());
            if (this.mBannerImpressionPixelCountEnabled && this.mExternalViewabilitySessionManager != null && this.mWeakActivity != null) {
                Activity activity = (Activity) this.mWeakActivity.get();
                if (activity != null) {
                    this.mExternalViewabilitySessionManager.startDeferredDisplaySession(activity);
                } else {
                    MoPubLog.m12061d("Lost the activity for deferred Viewability tracking. Dropping session.");
                }
            }
        }
    }

    private boolean extrasAreValid(Map<String, String> serverExtras) {
        return serverExtras.containsKey(DataKeys.HTML_RESPONSE_BODY_KEY);
    }

    @VisibleForTesting
    boolean isBannerImpressionPixelCountEnabled() {
        return this.mBannerImpressionPixelCountEnabled;
    }
}
