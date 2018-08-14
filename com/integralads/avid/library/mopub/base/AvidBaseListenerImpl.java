package com.integralads.avid.library.mopub.base;

import com.integralads.avid.library.mopub.session.internal.InternalAvidAdSession;
import com.integralads.avid.library.mopub.session.internal.jsbridge.AvidBridgeManager;

public abstract class AvidBaseListenerImpl {
    private InternalAvidAdSession avidAdSession;
    private AvidBridgeManager avidBridgeManager;

    public AvidBaseListenerImpl(InternalAvidAdSession avidAdSession, AvidBridgeManager publisher) {
        this.avidAdSession = avidAdSession;
        this.avidBridgeManager = publisher;
    }

    public void destroy() {
        this.avidAdSession = null;
        this.avidBridgeManager = null;
    }

    protected InternalAvidAdSession getAvidAdSession() {
        return this.avidAdSession;
    }

    protected AvidBridgeManager getAvidBridgeManager() {
        return this.avidBridgeManager;
    }

    protected void assertSessionIsNotEnded() {
        if (this.avidAdSession == null) {
            throw new IllegalStateException("The AVID ad session is ended. Please ensure you are not recording events after the session has ended.");
        }
    }
}
