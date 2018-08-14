package com.integralads.avid.library.mopub.session.internal;

public interface InternalAvidAdSessionListener {
    void sessionDidEnd(InternalAvidAdSession internalAvidAdSession);

    void sessionHasBecomeActive(InternalAvidAdSession internalAvidAdSession);

    void sessionHasResignedActive(InternalAvidAdSession internalAvidAdSession);
}
