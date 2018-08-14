package com.integralads.avid.library.mopub.session.internal.jsbridge;

import android.text.TextUtils;
import android.webkit.WebView;
import com.integralads.avid.library.mopub.AvidBridge;
import com.integralads.avid.library.mopub.session.internal.InternalAvidAdSessionContext;
import com.integralads.avid.library.mopub.utils.AvidCommand;
import com.integralads.avid.library.mopub.weakreference.AvidWebView;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONObject;

public class AvidBridgeManager {
    public static final int VIDEO_EVENT_TAG = 1;
    private final InternalAvidAdSessionContext avidAdSessionContext;
    private AvidWebView avidWebView;
    private boolean isAvidJsInjected;
    private boolean isReadyEventPublished;
    private AvidBridgeManagerListener listener;
    private final ArrayList<AvidEvent> pendingEvents = new ArrayList();

    public interface AvidBridgeManagerListener {
        void avidBridgeManagerDidInjectAvidJs();
    }

    public AvidBridgeManager(InternalAvidAdSessionContext avidAdSessionContext) {
        this.avidAdSessionContext = avidAdSessionContext;
        this.avidWebView = new AvidWebView(null);
    }

    public boolean isActive() {
        return this.isAvidJsInjected;
    }

    public void setListener(AvidBridgeManagerListener listener) {
        this.listener = listener;
    }

    public void onAvidJsReady() {
        injectAvid();
    }

    public void setWebView(WebView webView) {
        if (this.avidWebView.get() != webView) {
            this.avidWebView.set(webView);
            this.isAvidJsInjected = false;
            if (AvidBridge.isAvidJsReady()) {
                injectAvid();
            }
        }
    }

    public void destroy() {
        setWebView(null);
    }

    public void callAvidbridge(String javascriptCommand) {
        this.avidWebView.injectFormattedJavaScript(javascriptCommand);
    }

    public void publishReadyEventForDeferredAdSession() {
        this.isReadyEventPublished = true;
        publishReadyEventIfNeeded();
    }

    public void publishNativeViewState(String viewState) {
        callAvidbridge(AvidCommand.setNativeViewState(viewState));
    }

    public void publishAppState(String appState) {
        callAvidbridge(AvidCommand.setAppState(appState));
    }

    public void publishVideoEvent(String event, JSONObject data) {
        if (isActive()) {
            invokePublishVideoEvent(event, data);
        } else {
            this.pendingEvents.add(new AvidEvent(1, event, data));
        }
    }

    private void injectAvid() {
        if (!this.avidWebView.isEmpty()) {
            this.isAvidJsInjected = true;
            this.avidWebView.injectJavaScript(AvidBridge.getAvidJs());
            setAvidAdSessionContext();
            publishReadyEventIfNeeded();
            publishPendingEvents();
            notifyListener();
        }
    }

    private void publishReadyEventIfNeeded() {
        if (isActive() && this.isReadyEventPublished) {
            callAvidbridge(AvidCommand.publishReadyEventForDeferredAdSession());
        }
    }

    private void invokePublishVideoEvent(String event, JSONObject data) {
        String dataString = data != null ? data.toString() : null;
        if (TextUtils.isEmpty(dataString)) {
            callAvidbridge(AvidCommand.publishVideoEvent(event));
        } else {
            callAvidbridge(AvidCommand.publishVideoEvent(event, dataString));
        }
    }

    private void setAvidAdSessionContext() {
        callAvidbridge(AvidCommand.setAvidAdSessionContext(this.avidAdSessionContext.getFullContext().toString()));
    }

    private void notifyListener() {
        if (this.listener != null) {
            this.listener.avidBridgeManagerDidInjectAvidJs();
        }
    }

    private void publishPendingEvents() {
        Iterator it = this.pendingEvents.iterator();
        while (it.hasNext()) {
            AvidEvent event = (AvidEvent) it.next();
            invokePublishVideoEvent(event.getType(), event.getData());
        }
        this.pendingEvents.clear();
    }
}
