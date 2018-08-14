package com.mopub.mraid;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import com.mopub.common.AdReport;
import com.mopub.common.DataKeys;
import com.mopub.common.ExternalViewabilitySessionManager;
import com.mopub.common.VisibleForTesting;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.JavaScriptWebViewCallbacks;
import com.mopub.mobileads.AdViewController;
import com.mopub.mobileads.CustomEventBanner;
import com.mopub.mobileads.CustomEventBanner.CustomEventBannerListener;
import com.mopub.mobileads.MoPubErrorCode;
import com.mopub.mobileads.factories.MraidControllerFactory;
import com.mopub.mraid.MraidBridge.MraidWebView;
import java.util.Map;

class MraidBanner extends CustomEventBanner {
    private boolean mBannerImpressionPixelCountEnabled = false;
    @Nullable
    private CustomEventBannerListener mBannerListener;
    @Nullable
    private MraidWebViewDebugListener mDebugListener;
    @Nullable
    private ExternalViewabilitySessionManager mExternalViewabilitySessionManager;
    @Nullable
    private MraidController mMraidController;

    class C36961 implements MraidController$MraidListener {
        C36961() {
        }

        public void onLoaded(View view) {
            AdViewController.setShouldHonorServerDimensions(view);
            MraidBanner.this.mBannerListener.onBannerLoaded(view);
        }

        public void onFailedToLoad() {
            MraidBanner.this.mBannerListener.onBannerFailed(MoPubErrorCode.MRAID_LOAD_ERROR);
        }

        public void onExpand() {
            MraidBanner.this.mBannerListener.onBannerExpanded();
            MraidBanner.this.mBannerListener.onBannerClicked();
        }

        public void onOpen() {
            MraidBanner.this.mBannerListener.onBannerClicked();
        }

        public void onClose() {
            MraidBanner.this.mBannerListener.onBannerCollapsed();
        }
    }

    MraidBanner() {
    }

    protected void loadBanner(@NonNull final Context context, @NonNull CustomEventBannerListener customEventBannerListener, @NonNull Map<String, Object> localExtras, @NonNull Map<String, String> serverExtras) {
        this.mBannerListener = customEventBannerListener;
        if (extrasAreValid(serverExtras)) {
            String htmlData = (String) serverExtras.get(DataKeys.HTML_RESPONSE_BODY_KEY);
            Object bannerImpressionPixelCountEnabledObject = localExtras.get(DataKeys.BANNER_IMPRESSION_PIXEL_COUNT_ENABLED);
            if (bannerImpressionPixelCountEnabledObject instanceof Boolean) {
                this.mBannerImpressionPixelCountEnabled = ((Boolean) bannerImpressionPixelCountEnabledObject).booleanValue();
            }
            try {
                this.mMraidController = MraidControllerFactory.create(context, (AdReport) localExtras.get(DataKeys.AD_REPORT_KEY), PlacementType.INLINE);
                this.mMraidController.setDebugListener(this.mDebugListener);
                this.mMraidController.setMraidListener(new C36961());
                this.mMraidController.fillContent(null, htmlData, new MraidController$MraidWebViewCacheListener() {
                    public void onReady(@NonNull MraidWebView webView, @Nullable ExternalViewabilitySessionManager viewabilityManager) {
                        webView.getSettings().setJavaScriptEnabled(true);
                        if (context instanceof Activity) {
                            MraidBanner.this.mExternalViewabilitySessionManager = new ExternalViewabilitySessionManager(context);
                            MraidBanner.this.mExternalViewabilitySessionManager.createDisplaySession(context, webView, MraidBanner.this.mBannerImpressionPixelCountEnabled);
                        }
                    }
                });
                return;
            } catch (ClassCastException e) {
                MoPubLog.m12070w("MRAID banner creating failed:", e);
                this.mBannerListener.onBannerFailed(MoPubErrorCode.MRAID_LOAD_ERROR);
                return;
            }
        }
        this.mBannerListener.onBannerFailed(MoPubErrorCode.MRAID_LOAD_ERROR);
    }

    protected void onInvalidate() {
        if (this.mExternalViewabilitySessionManager != null) {
            this.mExternalViewabilitySessionManager.endDisplaySession();
            this.mExternalViewabilitySessionManager = null;
        }
        if (this.mMraidController != null) {
            this.mMraidController.setMraidListener(null);
            this.mMraidController.destroy();
        }
    }

    protected void trackMpxAndThirdPartyImpressions() {
        if (this.mMraidController != null) {
            this.mMraidController.loadJavascript(JavaScriptWebViewCallbacks.WEB_VIEW_DID_APPEAR.getJavascript());
            if (this.mBannerImpressionPixelCountEnabled && this.mExternalViewabilitySessionManager != null) {
                Activity activity = (Activity) this.mMraidController.getWeakActivity().get();
                if (activity != null) {
                    this.mExternalViewabilitySessionManager.startDeferredDisplaySession(activity);
                } else {
                    MoPubLog.m12061d("Lost the activity for deferred Viewability tracking. Dropping session.");
                }
            }
        }
    }

    private boolean extrasAreValid(@NonNull Map<String, String> serverExtras) {
        return serverExtras.containsKey(DataKeys.HTML_RESPONSE_BODY_KEY);
    }

    @VisibleForTesting
    public void setDebugListener(@Nullable MraidWebViewDebugListener debugListener) {
        this.mDebugListener = debugListener;
        if (this.mMraidController != null) {
            this.mMraidController.setDebugListener(debugListener);
        }
    }

    @VisibleForTesting
    boolean isBannerImpressionPixelCountEnabled() {
        return this.mBannerImpressionPixelCountEnabled;
    }
}
