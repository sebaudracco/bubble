package com.integralads.avid.library.mopub.session;

import android.view.View;
import com.integralads.avid.library.mopub.AvidManager;
import com.integralads.avid.library.mopub.session.internal.InternalAvidManagedAdSession;

public abstract class AbstractAvidManagedAdSession extends AbstractAvidAdSession<View> {
    public void injectJavaScriptResource(String javaScriptResource) {
        InternalAvidManagedAdSession internalAvidAdSession = (InternalAvidManagedAdSession) AvidManager.getInstance().findInternalAvidAdSessionById(getAvidAdSessionId());
        if (internalAvidAdSession != null) {
            internalAvidAdSession.getJavaScriptResourceInjector().injectJavaScriptResource(javaScriptResource);
        }
    }
}
