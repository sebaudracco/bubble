package com.integralads.avid.library.mopub.session;

import android.app.Activity;
import android.view.View;
import com.integralads.avid.library.mopub.AvidManager;
import com.integralads.avid.library.mopub.deferred.AvidDeferredAdSessionListener;
import com.integralads.avid.library.mopub.session.internal.InternalAvidAdSession;
import java.util.UUID;

public abstract class AbstractAvidAdSession<T extends View> {
    private String avidAdSessionId = UUID.randomUUID().toString();

    public String getAvidAdSessionId() {
        return this.avidAdSessionId;
    }

    public void registerAdView(T adView, Activity activity) {
        InternalAvidAdSession internalAvidAdSession = AvidManager.getInstance().findInternalAvidAdSessionById(this.avidAdSessionId);
        if (internalAvidAdSession != null) {
            internalAvidAdSession.registerAdView(adView);
        }
        AvidManager.getInstance().registerActivity(activity);
    }

    public void unregisterAdView(T adView) {
        InternalAvidAdSession internalAvidAdSession = AvidManager.getInstance().findInternalAvidAdSessionById(this.avidAdSessionId);
        if (internalAvidAdSession != null) {
            internalAvidAdSession.unregisterAdView(adView);
        }
    }

    public void endSession() {
        InternalAvidAdSession internalAvidAdSession = AvidManager.getInstance().findInternalAvidAdSessionById(getAvidAdSessionId());
        if (internalAvidAdSession != null) {
            internalAvidAdSession.onEnd();
        }
    }

    public AvidDeferredAdSessionListener getAvidDeferredAdSessionListener() {
        InternalAvidAdSession internalAvidAdSession = AvidManager.getInstance().findInternalAvidAdSessionById(getAvidAdSessionId());
        AvidDeferredAdSessionListener listener = internalAvidAdSession != null ? internalAvidAdSession.getAvidDeferredAdSessionListener() : null;
        if (listener != null) {
            return listener;
        }
        throw new IllegalStateException("The AVID ad session is not deferred. Please ensure you are only using AvidDeferredAdSessionListener for deferred AVID ad session.");
    }

    public void registerFriendlyObstruction(View friendlyObstruction) {
        InternalAvidAdSession internalAvidAdSession = AvidManager.getInstance().findInternalAvidAdSessionById(getAvidAdSessionId());
        if (internalAvidAdSession != null) {
            internalAvidAdSession.getObstructionsWhiteList().add(friendlyObstruction);
        }
    }
}
