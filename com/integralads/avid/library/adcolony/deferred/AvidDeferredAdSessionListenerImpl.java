package com.integralads.avid.library.adcolony.deferred;

import com.integralads.avid.library.adcolony.base.AvidBaseListenerImpl;
import com.integralads.avid.library.adcolony.session.internal.InternalAvidAdSession;
import com.integralads.avid.library.adcolony.session.internal.jsbridge.AvidBridgeManager;

public class AvidDeferredAdSessionListenerImpl extends AvidBaseListenerImpl implements AvidDeferredAdSessionListener {
    public AvidDeferredAdSessionListenerImpl(InternalAvidAdSession avidAdSession, AvidBridgeManager publisher) {
        super(avidAdSession, publisher);
    }

    public void recordReadyEvent() {
        assertSessionIsNotEnded();
        if (getAvidAdSession().isReady()) {
            throw new IllegalStateException("The AVID ad session is already ready. Please ensure you are only calling recordReadyEvent once for the deferred AVID ad session.");
        }
        getAvidBridgeManager().publishReadyEventForDeferredAdSession();
        getAvidAdSession().onReady();
    }
}
