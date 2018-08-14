package com.integralads.avid.library.adcolony.base;

import com.integralads.avid.library.adcolony.session.internal.InternalAvidAdSession;
import com.integralads.avid.library.adcolony.session.internal.jsbridge.AvidBridgeManager;

public abstract class AvidBaseListenerImpl {
    private InternalAvidAdSession f8330a;
    private AvidBridgeManager f8331b;

    public AvidBaseListenerImpl(InternalAvidAdSession avidAdSession, AvidBridgeManager publisher) {
        this.f8330a = avidAdSession;
        this.f8331b = publisher;
    }

    public void destroy() {
        this.f8330a = null;
        this.f8331b = null;
    }

    protected InternalAvidAdSession getAvidAdSession() {
        return this.f8330a;
    }

    protected AvidBridgeManager getAvidBridgeManager() {
        return this.f8331b;
    }

    protected void assertSessionIsNotEnded() {
        if (this.f8330a == null) {
            throw new IllegalStateException("The AVID ad session is ended. Please ensure you are not recording events after the session has ended.");
        }
    }
}
