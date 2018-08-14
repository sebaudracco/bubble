package com.integralads.avid.library.mopub.session.internal;

import android.content.Context;
import android.support.annotation.VisibleForTesting;
import android.view.View;
import android.webkit.WebView;
import com.integralads.avid.library.mopub.deferred.AvidDeferredAdSessionListener;
import com.integralads.avid.library.mopub.deferred.AvidDeferredAdSessionListenerImpl;
import com.integralads.avid.library.mopub.session.ExternalAvidAdSessionContext;
import com.integralads.avid.library.mopub.session.internal.jsbridge.AvidBridgeManager;
import com.integralads.avid.library.mopub.session.internal.jsbridge.AvidBridgeManager.AvidBridgeManagerListener;
import com.integralads.avid.library.mopub.session.internal.jsbridge.AvidWebViewManager;
import com.integralads.avid.library.mopub.utils.AvidJSONUtil;
import com.integralads.avid.library.mopub.utils.AvidTimestamp;
import com.integralads.avid.library.mopub.weakreference.AvidView;

public abstract class InternalAvidAdSession<T extends View> implements AvidBridgeManagerListener {
    private AdState adState;
    private AvidBridgeManager avidBridgeManager = new AvidBridgeManager(this.internalContext);
    private AvidDeferredAdSessionListenerImpl avidDeferredAdSessionListener;
    private AvidView<T> avidView;
    private final InternalAvidAdSessionContext internalContext;
    private boolean isActive;
    private boolean isReady;
    private double lastUpdated;
    private InternalAvidAdSessionListener listener;
    private final ObstructionsWhiteList obstructionsWhiteList;
    private AvidWebViewManager webViewManager;

    enum AdState {
        AD_STATE_IDLE,
        AD_STATE_VISIBLE,
        AD_STATE_HIDDEN
    }

    public abstract MediaType getMediaType();

    public abstract SessionType getSessionType();

    public abstract WebView getWebView();

    public InternalAvidAdSession(Context context, String avidAdSessionId, ExternalAvidAdSessionContext avidAdSessionContext) {
        this.internalContext = new InternalAvidAdSessionContext(context, avidAdSessionId, getSessionType().toString(), getMediaType().toString(), avidAdSessionContext);
        this.avidBridgeManager.setListener(this);
        this.webViewManager = new AvidWebViewManager(this.internalContext, this.avidBridgeManager);
        this.avidView = new AvidView(null);
        this.isReady = !avidAdSessionContext.isDeferred();
        if (!this.isReady) {
            this.avidDeferredAdSessionListener = new AvidDeferredAdSessionListenerImpl(this, this.avidBridgeManager);
        }
        this.obstructionsWhiteList = new ObstructionsWhiteList();
        onViewChanged();
    }

    public String getAvidAdSessionId() {
        return this.internalContext.getAvidAdSessionId();
    }

    public ExternalAvidAdSessionContext getAvidAdSessionContext() {
        return this.internalContext.getAvidAdSessionContext();
    }

    public T getView() {
        return (View) this.avidView.get();
    }

    public AvidDeferredAdSessionListener getAvidDeferredAdSessionListener() {
        return this.avidDeferredAdSessionListener;
    }

    public InternalAvidAdSessionListener getListener() {
        return this.listener;
    }

    public void setListener(InternalAvidAdSessionListener listener) {
        this.listener = listener;
    }

    public boolean isEmpty() {
        return this.avidView.isEmpty();
    }

    public boolean isActive() {
        return this.isActive;
    }

    public boolean isReady() {
        return this.isReady;
    }

    public AvidBridgeManager getAvidBridgeManager() {
        return this.avidBridgeManager;
    }

    public ObstructionsWhiteList getObstructionsWhiteList() {
        return this.obstructionsWhiteList;
    }

    public void registerAdView(T adView) {
        if (!doesManageView(adView)) {
            onViewChanged();
            this.avidView.set(adView);
            onViewRegistered();
            sessionStateCanBeChanged();
        }
    }

    public void unregisterAdView(T adView) {
        if (doesManageView(adView)) {
            onViewChanged();
            cleanupViewState();
            this.avidView.set(null);
            onViewUnregistered();
            sessionStateCanBeChanged();
        }
    }

    public boolean doesManageView(View view) {
        return this.avidView.contains(view);
    }

    public void onStart() {
    }

    public void onEnd() {
        cleanupViewState();
        if (this.avidDeferredAdSessionListener != null) {
            this.avidDeferredAdSessionListener.destroy();
        }
        this.avidBridgeManager.destroy();
        this.webViewManager.destroy();
        this.isReady = false;
        sessionStateCanBeChanged();
        if (this.listener != null) {
            this.listener.sessionDidEnd(this);
        }
    }

    public void onReady() {
        this.isReady = true;
        sessionStateCanBeChanged();
    }

    public void avidBridgeManagerDidInjectAvidJs() {
        sessionStateCanBeChanged();
    }

    public void setScreenMode(boolean isScreenOn) {
        if (isActive()) {
            this.avidBridgeManager.publishAppState(isScreenOn ? "active" : "inactive");
        }
    }

    public void publishNativeViewStateCommand(String viewStateCommand, double timestamp) {
        if (timestamp > this.lastUpdated) {
            this.avidBridgeManager.callAvidbridge(viewStateCommand);
            this.adState = AdState.AD_STATE_VISIBLE;
        }
    }

    public void publishEmptyNativeViewStateCommand(String viewStateCommand, double timestamp) {
        if (timestamp > this.lastUpdated && this.adState != AdState.AD_STATE_HIDDEN) {
            this.avidBridgeManager.callAvidbridge(viewStateCommand);
            this.adState = AdState.AD_STATE_HIDDEN;
        }
    }

    protected void cleanupViewState() {
        if (isActive()) {
            this.avidBridgeManager.publishNativeViewState(AvidJSONUtil.getEmptyTreeJSONObject().toString());
        }
    }

    protected void onViewRegistered() {
    }

    protected void onViewUnregistered() {
    }

    protected void updateWebViewManager() {
        this.webViewManager.setWebView(getWebView());
    }

    protected void sessionStateCanBeChanged() {
        boolean newIsActive = this.avidBridgeManager.isActive() && this.isReady && !isEmpty();
        if (this.isActive != newIsActive) {
            setActive(newIsActive);
        }
    }

    protected void setActive(boolean isActive) {
        this.isActive = isActive;
        if (this.listener == null) {
            return;
        }
        if (isActive) {
            this.listener.sessionHasBecomeActive(this);
        } else {
            this.listener.sessionHasResignedActive(this);
        }
    }

    private void onViewChanged() {
        this.lastUpdated = AvidTimestamp.getCurrentTime();
        this.adState = AdState.AD_STATE_IDLE;
    }

    @VisibleForTesting
    void setAvidBridgeManager(AvidBridgeManager avidBridgeManager) {
        this.avidBridgeManager = avidBridgeManager;
    }

    @VisibleForTesting
    AdState getAdState() {
        return this.adState;
    }

    @VisibleForTesting
    double getLastUpdated() {
        return this.lastUpdated;
    }

    @VisibleForTesting
    void setAvidWebViewManager(AvidWebViewManager webViewManager) {
        this.webViewManager = webViewManager;
    }
}
