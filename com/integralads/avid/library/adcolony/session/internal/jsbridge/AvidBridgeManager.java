package com.integralads.avid.library.adcolony.session.internal.jsbridge;

import android.text.TextUtils;
import android.webkit.WebView;
import com.integralads.avid.library.adcolony.AvidBridge;
import com.integralads.avid.library.adcolony.session.internal.InternalAvidAdSessionContext;
import com.integralads.avid.library.adcolony.utils.AvidCommand;
import com.integralads.avid.library.adcolony.weakreference.AvidWebView;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONObject;

public class AvidBridgeManager {
    public static final int VIDEO_EVENT_TAG = 1;
    private final InternalAvidAdSessionContext f8371a;
    private boolean f8372b;
    private AvidWebView f8373c;
    private boolean f8374d;
    private AvidBridgeManagerListener f8375e;
    private final ArrayList<AvidEvent> f8376f = new ArrayList();

    public interface AvidBridgeManagerListener {
        void avidBridgeManagerDidInjectAvidJs();
    }

    public AvidBridgeManager(InternalAvidAdSessionContext avidAdSessionContext) {
        this.f8371a = avidAdSessionContext;
        this.f8373c = new AvidWebView(null);
    }

    public boolean isActive() {
        return this.f8372b;
    }

    public void setListener(AvidBridgeManagerListener listener) {
        this.f8375e = listener;
    }

    public void onAvidJsReady() {
        m11145a();
    }

    public void setWebView(WebView webView) {
        if (this.f8373c.get() != webView) {
            this.f8373c.set(webView);
            this.f8372b = false;
            if (AvidBridge.isAvidJsReady()) {
                m11145a();
            }
        }
    }

    public void destroy() {
        setWebView(null);
    }

    public void callAvidbridge(String javascriptCommand) {
        this.f8373c.injectFormattedJavaScript(javascriptCommand);
    }

    public void publishReadyEventForDeferredAdSession() {
        this.f8374d = true;
        m11147b();
    }

    public void publishNativeViewState(String viewState) {
        callAvidbridge(AvidCommand.setNativeViewState(viewState));
    }

    public void publishAppState(String appState) {
        callAvidbridge(AvidCommand.setAppState(appState));
    }

    public void publishVideoEvent(String event, JSONObject data) {
        if (isActive()) {
            m11146a(event, data);
        } else {
            this.f8376f.add(new AvidEvent(1, event, data));
        }
    }

    private void m11145a() {
        if (!this.f8373c.isEmpty()) {
            this.f8372b = true;
            this.f8373c.injectJavaScript(AvidBridge.getAvidJs());
            m11148c();
            m11147b();
            m11150e();
            m11149d();
        }
    }

    private void m11147b() {
        if (isActive() && this.f8374d) {
            callAvidbridge(AvidCommand.publishReadyEventForDeferredAdSession());
        }
    }

    private void m11146a(String str, JSONObject jSONObject) {
        Object jSONObject2 = jSONObject != null ? jSONObject.toString() : null;
        if (TextUtils.isEmpty(jSONObject2)) {
            callAvidbridge(AvidCommand.publishVideoEvent(str));
        } else {
            callAvidbridge(AvidCommand.publishVideoEvent(str, jSONObject2));
        }
    }

    private void m11148c() {
        callAvidbridge(AvidCommand.setAvidAdSessionContext(this.f8371a.getFullContext().toString()));
    }

    private void m11149d() {
        if (this.f8375e != null) {
            this.f8375e.avidBridgeManagerDidInjectAvidJs();
        }
    }

    private void m11150e() {
        Iterator it = this.f8376f.iterator();
        while (it.hasNext()) {
            AvidEvent avidEvent = (AvidEvent) it.next();
            m11146a(avidEvent.getType(), avidEvent.getData());
        }
        this.f8376f.clear();
    }
}
