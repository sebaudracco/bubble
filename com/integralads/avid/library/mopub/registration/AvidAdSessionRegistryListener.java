package com.integralads.avid.library.mopub.registration;

public interface AvidAdSessionRegistryListener {
    void registryHasActiveSessionsChanged(AvidAdSessionRegistry avidAdSessionRegistry);

    void registryHasSessionsChanged(AvidAdSessionRegistry avidAdSessionRegistry);
}
