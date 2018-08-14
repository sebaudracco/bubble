package com.mopub.mobileads;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import com.facebook.ads.AudienceNetworkActivity;
import com.github.lzyzsd.jsbridge.BridgeUtil;
import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;
import com.mopub.common.AdReport;
import com.mopub.common.Constants;
import com.mopub.common.DataKeys;
import com.mopub.common.ExternalViewabilitySessionManager;
import com.mopub.common.IntentActions;
import com.mopub.common.Preconditions;
import com.mopub.common.VisibleForTesting;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.JavaScriptWebViewCallbacks;
import com.mopub.mobileads.CustomEventInterstitial.CustomEventInterstitialListener;
import com.mopub.mraid.MraidBridge.MraidWebView;
import com.mopub.mraid.MraidController;
import com.mopub.mraid.MraidController$MraidListener;
import com.mopub.mraid.MraidController$MraidWebViewCacheListener;
import com.mopub.mraid.MraidController$UseCustomCloseListener;
import com.mopub.mraid.MraidWebViewClient;
import com.mopub.mraid.MraidWebViewDebugListener;
import com.mopub.mraid.PlacementType;
import com.mopub.network.Networking;

public class MraidActivity extends BaseInterstitialActivity {
    @Nullable
    private MraidWebViewDebugListener mDebugListener;
    @Nullable
    private ExternalViewabilitySessionManager mExternalViewabilitySessionManager;
    @Nullable
    private MraidController mMraidController;

    class C36582 implements MraidController$MraidListener {
        C36582() {
        }

        public void onLoaded(View view) {
            MraidActivity.this.mMraidController.loadJavascript(JavaScriptWebViewCallbacks.WEB_VIEW_DID_APPEAR.getJavascript());
        }

        public void onFailedToLoad() {
            MoPubLog.m12061d("MraidActivity failed to load. Finishing the activity");
            if (MraidActivity.this.getBroadcastIdentifier() != null) {
                BaseBroadcastReceiver.broadcastAction(MraidActivity.this, MraidActivity.this.getBroadcastIdentifier().longValue(), IntentActions.ACTION_INTERSTITIAL_FAIL);
            }
            MraidActivity.this.finish();
        }

        public void onClose() {
            MraidActivity.this.mMraidController.loadJavascript(JavaScriptWebViewCallbacks.WEB_VIEW_DID_CLOSE.getJavascript());
            MraidActivity.this.finish();
        }

        public void onExpand() {
        }

        public void onOpen() {
            if (MraidActivity.this.getBroadcastIdentifier() != null) {
                BaseBroadcastReceiver.broadcastAction(MraidActivity.this, MraidActivity.this.getBroadcastIdentifier().longValue(), IntentActions.ACTION_INTERSTITIAL_CLICK);
            }
        }
    }

    class C36593 implements MraidController$UseCustomCloseListener {
        C36593() {
        }

        public void useCustomCloseChanged(boolean useCustomClose) {
            if (useCustomClose) {
                MraidActivity.this.hideInterstitialCloseButton();
            } else {
                MraidActivity.this.showInterstitialCloseButton();
            }
        }
    }

    class C36604 implements MraidController$MraidWebViewCacheListener {
        C36604() {
        }

        public void onReady(@NonNull MraidWebView webView, @Nullable ExternalViewabilitySessionManager viewabilityManager) {
            if (viewabilityManager != null) {
                MraidActivity.this.mExternalViewabilitySessionManager = viewabilityManager;
                return;
            }
            MraidActivity.this.mExternalViewabilitySessionManager = new ExternalViewabilitySessionManager(MraidActivity.this);
            MraidActivity.this.mExternalViewabilitySessionManager.createDisplaySession(MraidActivity.this, webView, true);
        }
    }

    public static void preRenderHtml(@NonNull Interstitial mraidInterstitial, @NonNull Context context, @NonNull CustomEventInterstitialListener customEventInterstitialListener, @Nullable String htmlData, @NonNull Long broadcastIdentifier) {
        Preconditions.checkNotNull(mraidInterstitial);
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(customEventInterstitialListener);
        Preconditions.checkNotNull(broadcastIdentifier);
        preRenderHtml(mraidInterstitial, customEventInterstitialListener, htmlData, new MraidWebView(context), broadcastIdentifier);
    }

    @VisibleForTesting
    static void preRenderHtml(@NonNull Interstitial mraidInterstitial, @NonNull final CustomEventInterstitialListener customEventInterstitialListener, @Nullable String htmlData, @NonNull BaseWebView mraidWebView, @NonNull Long broadcastIdentifier) {
        Preconditions.checkNotNull(mraidInterstitial);
        Preconditions.checkNotNull(customEventInterstitialListener);
        Preconditions.checkNotNull(mraidWebView);
        Preconditions.checkNotNull(broadcastIdentifier);
        mraidWebView.enablePlugins(false);
        mraidWebView.enableJavascriptCaching();
        mraidWebView.setWebViewClient(new MraidWebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if ("mopub://failLoad".equals(url)) {
                    customEventInterstitialListener.onInterstitialFailed(MoPubErrorCode.MRAID_LOAD_ERROR);
                }
                return true;
            }

            public void onPageFinished(WebView view, String url) {
                customEventInterstitialListener.onInterstitialLoaded();
            }

            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
                customEventInterstitialListener.onInterstitialFailed(MoPubErrorCode.MRAID_LOAD_ERROR);
            }
        });
        Context context = mraidWebView.getContext();
        ExternalViewabilitySessionManager externalViewabilitySessionManager = new ExternalViewabilitySessionManager(context);
        externalViewabilitySessionManager.createDisplaySession(context, mraidWebView, true);
        mraidWebView.loadDataWithBaseURL(Networking.getBaseUrlScheme() + "://" + Constants.HOST + BridgeUtil.SPLIT_MARK, htmlData, AudienceNetworkActivity.WEBVIEW_MIME_TYPE, "UTF-8", null);
        WebViewCacheService.storeWebViewConfig(broadcastIdentifier, mraidInterstitial, mraidWebView, externalViewabilitySessionManager);
    }

    public static void start(@NonNull Context context, @Nullable AdReport adreport, @Nullable String htmlData, long broadcastIdentifier) {
        try {
            context.startActivity(createIntent(context, adreport, htmlData, broadcastIdentifier));
        } catch (ActivityNotFoundException e) {
            Log.d("MraidInterstitial", "MraidActivity.class not found. Did you declare MraidActivity in your manifest?");
        }
    }

    @VisibleForTesting
    protected static Intent createIntent(@NonNull Context context, @Nullable AdReport adReport, @Nullable String htmlData, long broadcastIdentifier) {
        Intent intent = new Intent(context, MraidActivity.class);
        intent.putExtra(DataKeys.HTML_RESPONSE_BODY_KEY, htmlData);
        intent.putExtra(DataKeys.BROADCAST_IDENTIFIER_KEY, broadcastIdentifier);
        intent.putExtra(DataKeys.AD_REPORT_KEY, adReport);
        intent.addFlags(ErrorDialogData.BINDER_CRASH);
        return intent;
    }

    public View getAdView() {
        String htmlData = getIntent().getStringExtra(DataKeys.HTML_RESPONSE_BODY_KEY);
        if (htmlData == null) {
            MoPubLog.m12069w("MraidActivity received a null HTML body. Finishing the activity.");
            finish();
            return new View(this);
        }
        this.mMraidController = new MraidController(this, this.mAdReport, PlacementType.INTERSTITIAL);
        this.mMraidController.setDebugListener(this.mDebugListener);
        this.mMraidController.setMraidListener(new C36582());
        this.mMraidController.setUseCustomCloseListener(new C36593());
        this.mMraidController.fillContent(getBroadcastIdentifier(), htmlData, new C36604());
        return this.mMraidController.getAdContainer();
    }

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.mExternalViewabilitySessionManager != null) {
            this.mExternalViewabilitySessionManager.startDeferredDisplaySession(this);
        }
        if (getBroadcastIdentifier() != null) {
            BaseBroadcastReceiver.broadcastAction(this, getBroadcastIdentifier().longValue(), IntentActions.ACTION_INTERSTITIAL_SHOW);
        }
        getWindow().setFlags(16777216, 16777216);
    }

    protected void onPause() {
        if (this.mMraidController != null) {
            this.mMraidController.pause(isFinishing());
        }
        super.onPause();
    }

    protected void onResume() {
        super.onResume();
        if (this.mMraidController != null) {
            this.mMraidController.resume();
        }
    }

    protected void onDestroy() {
        if (this.mExternalViewabilitySessionManager != null) {
            this.mExternalViewabilitySessionManager.endDisplaySession();
            this.mExternalViewabilitySessionManager = null;
        }
        if (this.mMraidController != null) {
            this.mMraidController.destroy();
        }
        if (getBroadcastIdentifier() != null) {
            BaseBroadcastReceiver.broadcastAction(this, getBroadcastIdentifier().longValue(), IntentActions.ACTION_INTERSTITIAL_DISMISS);
        }
        super.onDestroy();
    }

    @VisibleForTesting
    public void setDebugListener(@Nullable MraidWebViewDebugListener debugListener) {
        this.mDebugListener = debugListener;
        if (this.mMraidController != null) {
            this.mMraidController.setDebugListener(debugListener);
        }
    }
}
