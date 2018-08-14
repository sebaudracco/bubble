package com.integralads.avid.library.mopub.session.internal;

import android.content.Context;
import com.integralads.avid.library.mopub.session.ExternalAvidAdSessionContext;

public class InternalAvidDisplayAdSession extends InternalAvidHtmlAdSession {
    public InternalAvidDisplayAdSession(Context context, String avidAdSessionId, ExternalAvidAdSessionContext avidAdSessionContext) {
        super(context, avidAdSessionId, avidAdSessionContext);
    }

    public SessionType getSessionType() {
        return SessionType.DISPLAY;
    }

    public MediaType getMediaType() {
        return MediaType.DISPLAY;
    }
}
