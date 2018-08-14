package com.integralads.avid.library.mopub.session;

import com.integralads.avid.library.mopub.AvidManager;
import com.integralads.avid.library.mopub.session.internal.InternalAvidManagedVideoAdSession;
import com.integralads.avid.library.mopub.video.AvidVideoPlaybackListener;

public class AvidManagedVideoAdSession extends AbstractAvidManagedAdSession {
    public AvidVideoPlaybackListener getAvidVideoPlaybackListener() {
        InternalAvidManagedVideoAdSession internalAvidAdSession = (InternalAvidManagedVideoAdSession) AvidManager.getInstance().findInternalAvidAdSessionById(getAvidAdSessionId());
        return internalAvidAdSession != null ? internalAvidAdSession.getAvidVideoPlaybackListener() : null;
    }
}
