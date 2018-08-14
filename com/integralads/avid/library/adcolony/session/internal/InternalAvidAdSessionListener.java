package com.integralads.avid.library.adcolony.session.internal;

public interface InternalAvidAdSessionListener {
    void sessionDidEnd(InternalAvidAdSession internalAvidAdSession);

    void sessionHasBecomeActive(InternalAvidAdSession internalAvidAdSession);

    void sessionHasResignedActive(InternalAvidAdSession internalAvidAdSession);
}
