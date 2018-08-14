package com.mopub.mraid;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.ConsoleMessage;
import android.webkit.JsResult;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import com.mopub.common.AdReport;
import com.mopub.common.CloseableLayout;
import com.mopub.common.CloseableLayout.ClosePosition;
import com.mopub.common.Preconditions;
import com.mopub.common.Preconditions.NoThrow;
import com.mopub.common.UrlAction;
import com.mopub.common.UrlHandler.Builder;
import com.mopub.common.VisibleForTesting;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.DeviceUtils;
import com.mopub.common.util.Dips;
import com.mopub.common.util.Utils;
import com.mopub.common.util.Views;
import com.mopub.mobileads.MraidVideoPlayerActivity;
import com.mopub.mobileads.WebViewCacheService;
import com.mopub.mobileads.WebViewCacheService.Config;
import com.mopub.mobileads.util.WebViews;
import com.mopub.mraid.MraidBridge.MraidBridgeListener;
import com.mopub.mraid.MraidBridge.MraidWebView;
import java.lang.ref.WeakReference;
import java.net.URI;

public class MraidController {
    private final AdReport mAdReport;
    private boolean mAllowOrientationChange;
    @NonNull
    private final CloseableLayout mCloseableAdContainer;
    @NonNull
    private final Context mContext;
    @Nullable
    private MraidWebViewDebugListener mDebugListener;
    @NonNull
    private final FrameLayout mDefaultAdContainer;
    private MraidOrientation mForceOrientation;
    private boolean mIsPaused;
    @NonNull
    private final MraidBridge mMraidBridge;
    private final MraidBridgeListener mMraidBridgeListener;
    @Nullable
    private MraidListener mMraidListener;
    private final MraidNativeCommandHandler mMraidNativeCommandHandler;
    @Nullable
    private MraidWebView mMraidWebView;
    @Nullable
    private UseCustomCloseListener mOnCloseButtonListener;
    @NonNull
    private OrientationBroadcastReceiver mOrientationBroadcastReceiver;
    @Nullable
    private Integer mOriginalActivityOrientation;
    @NonNull
    private final PlacementType mPlacementType;
    @Nullable
    private ViewGroup mRootView;
    @NonNull
    private final MraidScreenMetrics mScreenMetrics;
    @NonNull
    private final ScreenMetricsWaiter mScreenMetricsWaiter;
    @NonNull
    private final MraidBridge mTwoPartBridge;
    private final MraidBridgeListener mTwoPartBridgeListener;
    @Nullable
    private MraidWebView mTwoPartWebView;
    @NonNull
    private ViewState mViewState;
    @NonNull
    private final WeakReference<Activity> mWeakActivity;

    public MraidController(@NonNull Context context, @Nullable AdReport adReport, @NonNull PlacementType placementType) {
        this(context, adReport, placementType, new MraidBridge(adReport, placementType), new MraidBridge(adReport, PlacementType.INTERSTITIAL), new ScreenMetricsWaiter());
    }

    @VisibleForTesting
    MraidController(@NonNull Context context, @Nullable AdReport adReport, @NonNull PlacementType placementType, @NonNull MraidBridge bridge, @NonNull MraidBridge twoPartBridge, @NonNull ScreenMetricsWaiter screenMetricsWaiter) {
        this.mViewState = ViewState.LOADING;
        this.mOrientationBroadcastReceiver = new OrientationBroadcastReceiver(this);
        this.mAllowOrientationChange = true;
        this.mForceOrientation = MraidOrientation.NONE;
        this.mMraidBridgeListener = new 3(this);
        this.mTwoPartBridgeListener = new 4(this);
        this.mContext = context.getApplicationContext();
        Preconditions.checkNotNull(this.mContext);
        this.mAdReport = adReport;
        if (context instanceof Activity) {
            this.mWeakActivity = new WeakReference((Activity) context);
        } else {
            this.mWeakActivity = new WeakReference(null);
        }
        this.mPlacementType = placementType;
        this.mMraidBridge = bridge;
        this.mTwoPartBridge = twoPartBridge;
        this.mScreenMetricsWaiter = screenMetricsWaiter;
        this.mViewState = ViewState.LOADING;
        this.mScreenMetrics = new MraidScreenMetrics(this.mContext, this.mContext.getResources().getDisplayMetrics().density);
        this.mDefaultAdContainer = new FrameLayout(this.mContext);
        this.mCloseableAdContainer = new CloseableLayout(this.mContext);
        this.mCloseableAdContainer.setOnCloseListener(new 1(this));
        View dimmingView = new View(this.mContext);
        dimmingView.setOnTouchListener(new 2(this));
        this.mCloseableAdContainer.addView(dimmingView, new LayoutParams(-1, -1));
        this.mOrientationBroadcastReceiver.register(this.mContext);
        this.mMraidBridge.setMraidBridgeListener(this.mMraidBridgeListener);
        this.mTwoPartBridge.setMraidBridgeListener(this.mTwoPartBridgeListener);
        this.mMraidNativeCommandHandler = new MraidNativeCommandHandler();
    }

    public void setMraidListener(@Nullable MraidListener mraidListener) {
        this.mMraidListener = mraidListener;
    }

    public void setUseCustomCloseListener(@Nullable UseCustomCloseListener listener) {
        this.mOnCloseButtonListener = listener;
    }

    public void setDebugListener(@Nullable MraidWebViewDebugListener debugListener) {
        this.mDebugListener = debugListener;
    }

    public void fillContent(@Nullable Long broadcastIdentifier, @NonNull String htmlData, @Nullable MraidWebViewCacheListener listener) {
        Preconditions.checkNotNull(htmlData, "htmlData cannot be null");
        boolean cacheHit = hydrateMraidWebView(broadcastIdentifier, listener);
        NoThrow.checkNotNull(this.mMraidWebView, "mMraidWebView cannot be null");
        this.mMraidBridge.attachView(this.mMraidWebView);
        this.mDefaultAdContainer.addView(this.mMraidWebView, new LayoutParams(-1, -1));
        if (cacheHit) {
            handlePageLoad();
        } else {
            this.mMraidBridge.setContentHtml(htmlData);
        }
    }

    private boolean hydrateMraidWebView(@Nullable Long broadcastIdentifier, @Nullable MraidWebViewCacheListener listener) {
        if (broadcastIdentifier != null) {
            Config config = WebViewCacheService.popWebViewConfig(broadcastIdentifier);
            if (config != null && (config.getWebView() instanceof MraidWebView)) {
                this.mMraidWebView = (MraidWebView) config.getWebView();
                this.mMraidWebView.enablePlugins(true);
                if (listener != null) {
                    listener.onReady(this.mMraidWebView, config.getViewabilityManager());
                }
                return true;
            }
        }
        MoPubLog.d("WebView cache miss. Creating a new MraidWebView.");
        this.mMraidWebView = new MraidWebView(this.mContext);
        if (listener != null) {
            listener.onReady(this.mMraidWebView, null);
        }
        return false;
    }

    private int getDisplayRotation() {
        return ((WindowManager) this.mContext.getSystemService("window")).getDefaultDisplay().getRotation();
    }

    @VisibleForTesting
    boolean handleConsoleMessage(@NonNull ConsoleMessage consoleMessage) {
        if (this.mDebugListener != null) {
            return this.mDebugListener.onConsoleMessage(consoleMessage);
        }
        return true;
    }

    @VisibleForTesting
    boolean handleJsAlert(@NonNull String message, @NonNull JsResult result) {
        if (this.mDebugListener != null) {
            return this.mDebugListener.onJsAlert(message, result);
        }
        result.confirm();
        return true;
    }

    @Nullable
    public MraidWebView getCurrentWebView() {
        return this.mTwoPartBridge.isAttached() ? this.mTwoPartWebView : this.mMraidWebView;
    }

    private boolean isInlineVideoAvailable() {
        Activity activity = (Activity) this.mWeakActivity.get();
        if (activity == null || getCurrentWebView() == null) {
            return false;
        }
        return this.mMraidNativeCommandHandler.isInlineVideoAvailable(activity, getCurrentWebView());
    }

    @VisibleForTesting
    void handlePageLoad() {
        setViewState(ViewState.DEFAULT, new 5(this));
        if (this.mMraidListener != null) {
            this.mMraidListener.onLoaded(this.mDefaultAdContainer);
        }
    }

    @VisibleForTesting
    void handleTwoPartPageLoad() {
        updateScreenMetricsAsync(new 6(this));
    }

    private void updateScreenMetricsAsync(@Nullable Runnable successRunnable) {
        this.mScreenMetricsWaiter.cancelLastRequest();
        View currentWebView = getCurrentWebView();
        if (currentWebView != null) {
            this.mScreenMetricsWaiter.waitFor(new View[]{this.mDefaultAdContainer, currentWebView}).start(new 7(this, currentWebView, successRunnable));
        }
    }

    void handleOrientationChange(int currentRotation) {
        updateScreenMetricsAsync(null);
    }

    public void pause(boolean isFinishing) {
        this.mIsPaused = true;
        if (this.mMraidWebView != null) {
            WebViews.onPause(this.mMraidWebView, isFinishing);
        }
        if (this.mTwoPartWebView != null) {
            WebViews.onPause(this.mTwoPartWebView, isFinishing);
        }
    }

    public void resume() {
        this.mIsPaused = false;
        if (this.mMraidWebView != null) {
            this.mMraidWebView.onResume();
        }
        if (this.mTwoPartWebView != null) {
            this.mTwoPartWebView.onResume();
        }
    }

    public void destroy() {
        this.mScreenMetricsWaiter.cancelLastRequest();
        try {
            this.mOrientationBroadcastReceiver.unregister();
        } catch (IllegalArgumentException e) {
            if (!e.getMessage().contains("Receiver not registered")) {
                throw e;
            }
        }
        if (!this.mIsPaused) {
            pause(true);
        }
        Views.removeFromParent(this.mCloseableAdContainer);
        this.mMraidBridge.detach();
        if (this.mMraidWebView != null) {
            this.mMraidWebView.destroy();
            this.mMraidWebView = null;
        }
        this.mTwoPartBridge.detach();
        if (this.mTwoPartWebView != null) {
            this.mTwoPartWebView.destroy();
            this.mTwoPartWebView = null;
        }
    }

    private void setViewState(@NonNull ViewState viewState) {
        setViewState(viewState, null);
    }

    private void setViewState(@NonNull ViewState viewState, @Nullable Runnable successRunnable) {
        MoPubLog.d("MRAID state set to " + viewState);
        ViewState previousViewState = this.mViewState;
        this.mViewState = viewState;
        this.mMraidBridge.notifyViewState(viewState);
        if (this.mTwoPartBridge.isLoaded()) {
            this.mTwoPartBridge.notifyViewState(viewState);
        }
        if (this.mMraidListener != null) {
            if (viewState == ViewState.EXPANDED) {
                this.mMraidListener.onExpand();
            } else if (previousViewState == ViewState.EXPANDED && viewState == ViewState.DEFAULT) {
                this.mMraidListener.onClose();
            } else if (viewState == ViewState.HIDDEN) {
                this.mMraidListener.onClose();
            }
        }
        updateScreenMetricsAsync(successRunnable);
    }

    int clampInt(int min, int target, int max) {
        return Math.max(min, Math.min(target, max));
    }

    @VisibleForTesting
    void handleResize(int widthDips, int heightDips, int offsetXDips, int offsetYDips, @NonNull ClosePosition closePosition, boolean allowOffscreen) throws MraidCommandException {
        if (this.mMraidWebView == null) {
            throw new MraidCommandException("Unable to resize after the WebView is destroyed");
        } else if (this.mViewState != ViewState.LOADING && this.mViewState != ViewState.HIDDEN) {
            if (this.mViewState == ViewState.EXPANDED) {
                throw new MraidCommandException("Not allowed to resize from an already expanded ad");
            } else if (this.mPlacementType == PlacementType.INTERSTITIAL) {
                throw new MraidCommandException("Not allowed to resize from an interstitial ad");
            } else {
                int width = Dips.dipsToIntPixels((float) widthDips, this.mContext);
                int height = Dips.dipsToIntPixels((float) heightDips, this.mContext);
                int offsetX = Dips.dipsToIntPixels((float) offsetXDips, this.mContext);
                int left = this.mScreenMetrics.getDefaultAdRect().left + offsetX;
                int top = this.mScreenMetrics.getDefaultAdRect().top + Dips.dipsToIntPixels((float) offsetYDips, this.mContext);
                Rect resizeRect = new Rect(left, top, left + width, top + height);
                if (!allowOffscreen) {
                    Rect bounds = this.mScreenMetrics.getRootViewRect();
                    if (resizeRect.width() > bounds.width() || resizeRect.height() > bounds.height()) {
                        throw new MraidCommandException("resizeProperties specified a size (" + widthDips + ", " + heightDips + ") and offset (" + offsetXDips + ", " + offsetYDips + ") that doesn't allow the ad to appear within the max allowed size (" + this.mScreenMetrics.getRootViewRectDips().width() + ", " + this.mScreenMetrics.getRootViewRectDips().height() + ")");
                    }
                    resizeRect.offsetTo(clampInt(bounds.left, resizeRect.left, bounds.right - resizeRect.width()), clampInt(bounds.top, resizeRect.top, bounds.bottom - resizeRect.height()));
                }
                Rect closeRect = new Rect();
                this.mCloseableAdContainer.applyCloseRegionBounds(closePosition, resizeRect, closeRect);
                if (!this.mScreenMetrics.getRootViewRect().contains(closeRect)) {
                    throw new MraidCommandException("resizeProperties specified a size (" + widthDips + ", " + heightDips + ") and offset (" + offsetXDips + ", " + offsetYDips + ") that doesn't allow the close region to appear within the max allowed size (" + this.mScreenMetrics.getRootViewRectDips().width() + ", " + this.mScreenMetrics.getRootViewRectDips().height() + ")");
                } else if (resizeRect.contains(closeRect)) {
                    this.mCloseableAdContainer.setCloseVisible(false);
                    this.mCloseableAdContainer.setClosePosition(closePosition);
                    LayoutParams layoutParams = new LayoutParams(resizeRect.width(), resizeRect.height());
                    layoutParams.leftMargin = resizeRect.left - this.mScreenMetrics.getRootViewRect().left;
                    layoutParams.topMargin = resizeRect.top - this.mScreenMetrics.getRootViewRect().top;
                    if (this.mViewState == ViewState.DEFAULT) {
                        this.mDefaultAdContainer.removeView(this.mMraidWebView);
                        this.mDefaultAdContainer.setVisibility(4);
                        this.mCloseableAdContainer.addView(this.mMraidWebView, new LayoutParams(-1, -1));
                        getAndMemoizeRootView().addView(this.mCloseableAdContainer, layoutParams);
                    } else if (this.mViewState == ViewState.RESIZED) {
                        this.mCloseableAdContainer.setLayoutParams(layoutParams);
                    }
                    this.mCloseableAdContainer.setClosePosition(closePosition);
                    setViewState(ViewState.RESIZED);
                } else {
                    throw new MraidCommandException("resizeProperties specified a size (" + widthDips + ", " + height + ") and offset (" + offsetXDips + ", " + offsetYDips + ") that don't allow the close region to appear within the resized ad.");
                }
            }
        }
    }

    void handleExpand(@Nullable URI uri, boolean shouldUseCustomClose) throws MraidCommandException {
        if (this.mMraidWebView == null) {
            throw new MraidCommandException("Unable to expand after the WebView is destroyed");
        } else if (this.mPlacementType != PlacementType.INTERSTITIAL) {
            if (this.mViewState == ViewState.DEFAULT || this.mViewState == ViewState.RESIZED) {
                applyOrientation();
                boolean isTwoPart = uri != null;
                if (isTwoPart) {
                    this.mTwoPartWebView = new MraidWebView(this.mContext);
                    this.mTwoPartBridge.attachView(this.mTwoPartWebView);
                    this.mTwoPartBridge.setContentUrl(uri.toString());
                }
                LayoutParams layoutParams = new LayoutParams(-1, -1);
                if (this.mViewState == ViewState.DEFAULT) {
                    if (isTwoPart) {
                        this.mCloseableAdContainer.addView(this.mTwoPartWebView, layoutParams);
                    } else {
                        this.mDefaultAdContainer.removeView(this.mMraidWebView);
                        this.mDefaultAdContainer.setVisibility(4);
                        this.mCloseableAdContainer.addView(this.mMraidWebView, layoutParams);
                    }
                    getAndMemoizeRootView().addView(this.mCloseableAdContainer, new LayoutParams(-1, -1));
                } else if (this.mViewState == ViewState.RESIZED && isTwoPart) {
                    this.mCloseableAdContainer.removeView(this.mMraidWebView);
                    this.mDefaultAdContainer.addView(this.mMraidWebView, layoutParams);
                    this.mDefaultAdContainer.setVisibility(4);
                    this.mCloseableAdContainer.addView(this.mTwoPartWebView, layoutParams);
                }
                this.mCloseableAdContainer.setLayoutParams(layoutParams);
                handleCustomClose(shouldUseCustomClose);
                setViewState(ViewState.EXPANDED);
            }
        }
    }

    @VisibleForTesting
    protected void handleClose() {
        if (this.mMraidWebView != null && this.mViewState != ViewState.LOADING && this.mViewState != ViewState.HIDDEN) {
            if (this.mViewState == ViewState.EXPANDED || this.mPlacementType == PlacementType.INTERSTITIAL) {
                unApplyOrientation();
            }
            if (this.mViewState == ViewState.RESIZED || this.mViewState == ViewState.EXPANDED) {
                if (!this.mTwoPartBridge.isAttached() || this.mTwoPartWebView == null) {
                    this.mCloseableAdContainer.removeView(this.mMraidWebView);
                    this.mDefaultAdContainer.addView(this.mMraidWebView, new LayoutParams(-1, -1));
                    this.mDefaultAdContainer.setVisibility(0);
                } else {
                    this.mCloseableAdContainer.removeView(this.mTwoPartWebView);
                    this.mTwoPartBridge.detach();
                }
                Views.removeFromParent(this.mCloseableAdContainer);
                setViewState(ViewState.DEFAULT);
            } else if (this.mViewState == ViewState.DEFAULT) {
                this.mDefaultAdContainer.setVisibility(4);
                setViewState(ViewState.HIDDEN);
            }
        }
    }

    @NonNull
    private ViewGroup getRootView() {
        if (this.mRootView != null) {
            return this.mRootView;
        }
        View bestRootView = Views.getTopmostView((Context) this.mWeakActivity.get(), this.mDefaultAdContainer);
        return bestRootView instanceof ViewGroup ? (ViewGroup) bestRootView : this.mDefaultAdContainer;
    }

    @NonNull
    private ViewGroup getAndMemoizeRootView() {
        if (this.mRootView == null) {
            this.mRootView = getRootView();
        }
        return this.mRootView;
    }

    @VisibleForTesting
    void handleShowVideo(@NonNull String videoUrl) {
        MraidVideoPlayerActivity.startMraid(this.mContext, videoUrl);
    }

    @VisibleForTesting
    void lockOrientation(int screenOrientation) throws MraidCommandException {
        Activity activity = (Activity) this.mWeakActivity.get();
        if (activity == null || !shouldAllowForceOrientation(this.mForceOrientation)) {
            throw new MraidCommandException("Attempted to lock orientation to unsupported value: " + this.mForceOrientation.name());
        }
        if (this.mOriginalActivityOrientation == null) {
            this.mOriginalActivityOrientation = Integer.valueOf(activity.getRequestedOrientation());
        }
        activity.setRequestedOrientation(screenOrientation);
    }

    @VisibleForTesting
    void applyOrientation() throws MraidCommandException {
        if (this.mForceOrientation != MraidOrientation.NONE) {
            lockOrientation(this.mForceOrientation.getActivityInfoOrientation());
        } else if (this.mAllowOrientationChange) {
            unApplyOrientation();
        } else {
            Activity activity = (Activity) this.mWeakActivity.get();
            if (activity == null) {
                throw new MraidCommandException("Unable to set MRAID expand orientation to 'none'; expected passed in Activity Context.");
            }
            lockOrientation(DeviceUtils.getScreenOrientation(activity));
        }
    }

    @VisibleForTesting
    void unApplyOrientation() {
        Activity activity = (Activity) this.mWeakActivity.get();
        if (!(activity == null || this.mOriginalActivityOrientation == null)) {
            activity.setRequestedOrientation(this.mOriginalActivityOrientation.intValue());
        }
        this.mOriginalActivityOrientation = null;
    }

    @VisibleForTesting
    boolean shouldAllowForceOrientation(MraidOrientation newOrientation) {
        if (newOrientation == MraidOrientation.NONE) {
            return true;
        }
        Activity activity = (Activity) this.mWeakActivity.get();
        if (activity == null) {
            return false;
        }
        try {
            ActivityInfo activityInfo = activity.getPackageManager().getActivityInfo(new ComponentName(activity, activity.getClass()), 0);
            int activityOrientation = activityInfo.screenOrientation;
            if (activityOrientation == -1) {
                boolean containsNecessaryConfigChanges;
                if (Utils.bitMaskContainsFlag(activityInfo.configChanges, 128) && Utils.bitMaskContainsFlag(activityInfo.configChanges, 1024)) {
                    containsNecessaryConfigChanges = true;
                } else {
                    containsNecessaryConfigChanges = false;
                }
                return containsNecessaryConfigChanges;
            } else if (activityOrientation != newOrientation.getActivityInfoOrientation()) {
                return false;
            } else {
                return true;
            }
        } catch (NameNotFoundException e) {
            return false;
        }
    }

    @VisibleForTesting
    protected void handleCustomClose(boolean useCustomClose) {
        boolean wasUsingCustomClose;
        boolean z = true;
        if (this.mCloseableAdContainer.isCloseVisible()) {
            wasUsingCustomClose = false;
        } else {
            wasUsingCustomClose = true;
        }
        if (useCustomClose != wasUsingCustomClose) {
            CloseableLayout closeableLayout = this.mCloseableAdContainer;
            if (useCustomClose) {
                z = false;
            }
            closeableLayout.setCloseVisible(z);
            if (this.mOnCloseButtonListener != null) {
                this.mOnCloseButtonListener.useCustomCloseChanged(useCustomClose);
            }
        }
    }

    @NonNull
    public FrameLayout getAdContainer() {
        return this.mDefaultAdContainer;
    }

    public void loadJavascript(@NonNull String javascript) {
        this.mMraidBridge.injectJavaScript(javascript);
    }

    @NonNull
    public Context getContext() {
        return this.mContext;
    }

    @NonNull
    WeakReference<Activity> getWeakActivity() {
        return this.mWeakActivity;
    }

    @VisibleForTesting
    void handleSetOrientationProperties(boolean allowOrientationChange, MraidOrientation forceOrientation) throws MraidCommandException {
        if (shouldAllowForceOrientation(forceOrientation)) {
            this.mAllowOrientationChange = allowOrientationChange;
            this.mForceOrientation = forceOrientation;
            if (this.mViewState == ViewState.EXPANDED || this.mPlacementType == PlacementType.INTERSTITIAL) {
                applyOrientation();
                return;
            }
            return;
        }
        throw new MraidCommandException("Unable to force orientation to " + forceOrientation);
    }

    @VisibleForTesting
    void handleOpen(@NonNull String url) {
        if (this.mMraidListener != null) {
            this.mMraidListener.onOpen();
        }
        Builder builder = new Builder();
        if (this.mAdReport != null) {
            builder.withDspCreativeId(this.mAdReport.getDspCreativeId());
        }
        builder.withSupportedUrlActions(UrlAction.IGNORE_ABOUT_SCHEME, new UrlAction[]{UrlAction.OPEN_NATIVE_BROWSER, UrlAction.OPEN_IN_APP_BROWSER, UrlAction.HANDLE_SHARE_TWEET, UrlAction.FOLLOW_DEEP_LINK_WITH_FALLBACK, UrlAction.FOLLOW_DEEP_LINK}).build().handleUrl(this.mContext, url);
    }

    @Deprecated
    @NonNull
    @VisibleForTesting
    ViewState getViewState() {
        return this.mViewState;
    }

    @Deprecated
    @VisibleForTesting
    void setViewStateForTesting(@NonNull ViewState viewState) {
        this.mViewState = viewState;
    }

    @Deprecated
    @NonNull
    @VisibleForTesting
    CloseableLayout getExpandedAdContainer() {
        return this.mCloseableAdContainer;
    }

    @Deprecated
    @VisibleForTesting
    void setRootView(FrameLayout rootView) {
        this.mRootView = rootView;
    }

    @Deprecated
    @VisibleForTesting
    void setRootViewSize(int width, int height) {
        this.mScreenMetrics.setRootViewPosition(0, 0, width, height);
    }

    @Deprecated
    @VisibleForTesting
    Integer getOriginalActivityOrientation() {
        return this.mOriginalActivityOrientation;
    }

    @Deprecated
    @VisibleForTesting
    boolean getAllowOrientationChange() {
        return this.mAllowOrientationChange;
    }

    @Deprecated
    @VisibleForTesting
    MraidOrientation getForceOrientation() {
        return this.mForceOrientation;
    }

    @Deprecated
    @VisibleForTesting
    void setOrientationBroadcastReceiver(OrientationBroadcastReceiver receiver) {
        this.mOrientationBroadcastReceiver = receiver;
    }

    @Deprecated
    @VisibleForTesting
    MraidWebView getMraidWebView() {
        return this.mMraidWebView;
    }

    @Deprecated
    @VisibleForTesting
    MraidWebView getTwoPartWebView() {
        return this.mTwoPartWebView;
    }
}
