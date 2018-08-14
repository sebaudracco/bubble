package com.integralads.avid.library.mopub.session.internal;

import android.content.Context;
import com.integralads.avid.library.mopub.session.ExternalAvidAdSessionContext;

public class InternalAvidVideoAdSession extends InternalAvidHtmlAdSession {
    public InternalAvidVideoAdSession(Context context, String avidAdSessionId, ExternalAvidAdSessionContext avidAdSessionContext) {
        super(context, avidAdSessionId, avidAdSessionContext);
    }

    public SessionType getSessionType() {
        return SessionType.VIDEO;
    }

    public MediaType getMediaType() {
        return MediaType.VIDEO;
    }
}
