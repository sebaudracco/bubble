package com.integralads.avid.library.adcolony.session.internal;

import android.content.Context;
import com.integralads.avid.library.adcolony.session.ExternalAvidAdSessionContext;
import com.integralads.avid.library.adcolony.video.AvidVideoPlaybackListenerImpl;

public class InternalAvidManagedVideoAdSession extends InternalAvidManagedAdSession {
    private AvidVideoPlaybackListenerImpl f8365a = new AvidVideoPlaybackListenerImpl(this, getAvidBridgeManager());

    public InternalAvidManagedVideoAdSession(Context context, String avidAdSessionId, ExternalAvidAdSessionContext avidAdSessionContext) {
        super(context, avidAdSessionId, avidAdSessionContext);
    }

    public AvidVideoPlaybackListenerImpl getAvidVideoPlaybackListener() {
        return this.f8365a;
    }

    public SessionType getSessionType() {
        return SessionType.MANAGED_VIDEO;
    }

    public MediaType getMediaType() {
        return MediaType.VIDEO;
    }

    public void onEnd() {
        this.f8365a.destroy();
        super.onEnd();
    }
}
