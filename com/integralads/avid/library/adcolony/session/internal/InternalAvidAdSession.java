package com.integralads.avid.library.adcolony.session.internal;

import android.content.Context;
import android.support.annotation.VisibleForTesting;
import android.view.View;
import android.webkit.WebView;
import com.integralads.avid.library.adcolony.deferred.AvidDeferredAdSessionListener;
import com.integralads.avid.library.adcolony.deferred.AvidDeferredAdSessionListenerImpl;
import com.integralads.avid.library.adcolony.session.ExternalAvidAdSessionContext;
import com.integralads.avid.library.adcolony.session.internal.jsbridge.AvidBridgeManager;
import com.integralads.avid.library.adcolony.session.internal.jsbridge.AvidBridgeManager.AvidBridgeManagerListener;
import com.integralads.avid.library.adcolony.session.internal.jsbridge.AvidWebViewManager;
import com.integralads.avid.library.adcolony.utils.AvidJSONUtil;
import com.integralads.avid.library.adcolony.utils.AvidTimestamp;
import com.integralads.avid.library.adcolony.weakreference.AvidView;

public abstract class InternalAvidAdSession<T extends View> implements AvidBridgeManagerListener {
    private final InternalAvidAdSessionContext f8348a;
    private AvidBridgeManager f8349b = new AvidBridgeManager(this.f8348a);
    private AvidWebViewManager f8350c;
    private AvidView<T> f8351d;
    private AvidDeferredAdSessionListenerImpl f8352e;
    private InternalAvidAdSessionListener f8353f;
    private boolean f8354g;
    private boolean f8355h;
    private final ObstructionsWhiteList f8356i;
    private C3285a f8357j;
    private double f8358k;

    enum C3285a {
        AD_STATE_IDLE,
        AD_STATE_VISIBLE,
        AD_STATE_HIDDEN
    }

    public abstract MediaType getMediaType();

    public abstract SessionType getSessionType();

    public abstract WebView getWebView();

    public InternalAvidAdSession(Context context, String avidAdSessionId, ExternalAvidAdSessionContext avidAdSessionContext) {
        this.f8348a = new InternalAvidAdSessionContext(context, avidAdSessionId, getSessionType().toString(), getMediaType().toString(), avidAdSessionContext);
        this.f8349b.setListener(this);
        this.f8350c = new AvidWebViewManager(this.f8348a, this.f8349b);
        this.f8351d = new AvidView(null);
        this.f8354g = !avidAdSessionContext.isDeferred();
        if (!this.f8354g) {
            this.f8352e = new AvidDeferredAdSessionListenerImpl(this, this.f8349b);
        }
        this.f8356i = new ObstructionsWhiteList();
        m11139c();
    }

    public String getAvidAdSessionId() {
        return this.f8348a.getAvidAdSessionId();
    }

    public ExternalAvidAdSessionContext getAvidAdSessionContext() {
        return this.f8348a.getAvidAdSessionContext();
    }

    public T getView() {
        return (View) this.f8351d.get();
    }

    public AvidDeferredAdSessionListener getAvidDeferredAdSessionListener() {
        return this.f8352e;
    }

    public InternalAvidAdSessionListener getListener() {
        return this.f8353f;
    }

    public void setListener(InternalAvidAdSessionListener listener) {
        this.f8353f = listener;
    }

    public boolean isEmpty() {
        return this.f8351d.isEmpty();
    }

    public boolean isActive() {
        return this.f8355h;
    }

    public boolean isReady() {
        return this.f8354g;
    }

    public AvidBridgeManager getAvidBridgeManager() {
        return this.f8349b;
    }

    public ObstructionsWhiteList getObstructionsWhiteList() {
        return this.f8356i;
    }

    public void registerAdView(T adView) {
        if (!doesManageView(adView)) {
            m11139c();
            this.f8351d.set(adView);
            onViewRegistered();
            sessionStateCanBeChanged();
        }
    }

    public void unregisterAdView(T adView) {
        if (doesManageView(adView)) {
            m11139c();
            cleanupViewState();
            this.f8351d.set(null);
            onViewUnregistered();
            sessionStateCanBeChanged();
        }
    }

    public boolean doesManageView(View view) {
        return this.f8351d.contains(view);
    }

    public void onStart() {
    }

    public void onEnd() {
        cleanupViewState();
        if (this.f8352e != null) {
            this.f8352e.destroy();
        }
        this.f8349b.destroy();
        this.f8350c.destroy();
        this.f8354g = false;
        sessionStateCanBeChanged();
        if (this.f8353f != null) {
            this.f8353f.sessionDidEnd(this);
        }
    }

    public void onReady() {
        this.f8354g = true;
        sessionStateCanBeChanged();
    }

    public void avidBridgeManagerDidInjectAvidJs() {
        sessionStateCanBeChanged();
    }

    public void setScreenMode(boolean isScreenOn) {
        if (isActive()) {
            this.f8349b.publishAppState(isScreenOn ? "active" : "inactive");
        }
    }

    public void publishNativeViewStateCommand(String viewStateCommand, double timestamp) {
        if (timestamp > this.f8358k) {
            this.f8349b.callAvidbridge(viewStateCommand);
            this.f8357j = C3285a.AD_STATE_VISIBLE;
        }
    }

    public void publishEmptyNativeViewStateCommand(String viewStateCommand, double timestamp) {
        if (timestamp > this.f8358k && this.f8357j != C3285a.AD_STATE_HIDDEN) {
            this.f8349b.callAvidbridge(viewStateCommand);
            this.f8357j = C3285a.AD_STATE_HIDDEN;
        }
    }

    protected void cleanupViewState() {
        if (isActive()) {
            this.f8349b.publishNativeViewState(AvidJSONUtil.getEmptyTreeJSONObject().toString());
        }
    }

    protected void onViewRegistered() {
    }

    protected void onViewUnregistered() {
    }

    protected void updateWebViewManager() {
        this.f8350c.setWebView(getWebView());
    }

    protected void sessionStateCanBeChanged() {
        boolean z = this.f8349b.isActive() && this.f8354g && !isEmpty();
        if (this.f8355h != z) {
            setActive(z);
        }
    }

    protected void setActive(boolean isActive) {
        this.f8355h = isActive;
        if (this.f8353f == null) {
            return;
        }
        if (isActive) {
            this.f8353f.sessionHasBecomeActive(this);
        } else {
            this.f8353f.sessionHasResignedActive(this);
        }
    }

    private void m11139c() {
        this.f8358k = AvidTimestamp.getCurrentTime();
        this.f8357j = C3285a.AD_STATE_IDLE;
    }

    @VisibleForTesting
    void m11141a(AvidBridgeManager avidBridgeManager) {
        this.f8349b = avidBridgeManager;
    }

    @VisibleForTesting
    C3285a m11140a() {
        return this.f8357j;
    }

    @VisibleForTesting
    double m11143b() {
        return this.f8358k;
    }

    @VisibleForTesting
    void m11142a(AvidWebViewManager avidWebViewManager) {
        this.f8350c = avidWebViewManager;
    }
}
