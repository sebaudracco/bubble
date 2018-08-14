package com.integralads.avid.library.adcolony.session;

import com.integralads.avid.library.adcolony.AvidManager;
import com.integralads.avid.library.adcolony.session.internal.InternalAvidManagedVideoAdSession;
import com.integralads.avid.library.adcolony.video.AvidVideoPlaybackListener;

public class AvidManagedVideoAdSession extends AbstractAvidManagedAdSession {
    public AvidVideoPlaybackListener getAvidVideoPlaybackListener() {
        InternalAvidManagedVideoAdSession internalAvidManagedVideoAdSession = (InternalAvidManagedVideoAdSession) AvidManager.getInstance().findInternalAvidAdSessionById(getAvidAdSessionId());
        return internalAvidManagedVideoAdSession != null ? internalAvidManagedVideoAdSession.getAvidVideoPlaybackListener() : null;
    }
}
