package com.integralads.avid.library.adcolony.session;

import android.app.Activity;
import android.view.View;
import com.integralads.avid.library.adcolony.AvidManager;
import com.integralads.avid.library.adcolony.deferred.AvidDeferredAdSessionListener;
import com.integralads.avid.library.adcolony.session.internal.InternalAvidAdSession;
import java.util.UUID;

public abstract class AbstractAvidAdSession<T extends View> {
    private String f8341a = UUID.randomUUID().toString();

    public String getAvidAdSessionId() {
        return this.f8341a;
    }

    public void registerAdView(T adView, Activity activity) {
        InternalAvidAdSession findInternalAvidAdSessionById = AvidManager.getInstance().findInternalAvidAdSessionById(this.f8341a);
        if (findInternalAvidAdSessionById != null) {
            findInternalAvidAdSessionById.registerAdView(adView);
        }
        AvidManager.getInstance().registerActivity(activity);
    }

    public void unregisterAdView(T adView) {
        InternalAvidAdSession findInternalAvidAdSessionById = AvidManager.getInstance().findInternalAvidAdSessionById(this.f8341a);
        if (findInternalAvidAdSessionById != null) {
            findInternalAvidAdSessionById.unregisterAdView(adView);
        }
    }

    public void endSession() {
        InternalAvidAdSession findInternalAvidAdSessionById = AvidManager.getInstance().findInternalAvidAdSessionById(getAvidAdSessionId());
        if (findInternalAvidAdSessionById != null) {
            findInternalAvidAdSessionById.onEnd();
        }
    }

    public AvidDeferredAdSessionListener getAvidDeferredAdSessionListener() {
        InternalAvidAdSession findInternalAvidAdSessionById = AvidManager.getInstance().findInternalAvidAdSessionById(getAvidAdSessionId());
        AvidDeferredAdSessionListener avidDeferredAdSessionListener = findInternalAvidAdSessionById != null ? findInternalAvidAdSessionById.getAvidDeferredAdSessionListener() : null;
        if (avidDeferredAdSessionListener != null) {
            return avidDeferredAdSessionListener;
        }
        throw new IllegalStateException("The AVID ad session is not deferred. Please ensure you are only using AvidDeferredAdSessionListener for deferred AVID ad session.");
    }

    public void registerFriendlyObstruction(View friendlyObstruction) {
        InternalAvidAdSession findInternalAvidAdSessionById = AvidManager.getInstance().findInternalAvidAdSessionById(getAvidAdSessionId());
        if (findInternalAvidAdSessionById != null) {
            findInternalAvidAdSessionById.getObstructionsWhiteList().add(friendlyObstruction);
        }
    }
}
