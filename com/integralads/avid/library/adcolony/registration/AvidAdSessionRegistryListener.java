package com.integralads.avid.library.adcolony.registration;

public interface AvidAdSessionRegistryListener {
    void registryHasActiveSessionsChanged(AvidAdSessionRegistry avidAdSessionRegistry);

    void registryHasSessionsChanged(AvidAdSessionRegistry avidAdSessionRegistry);
}
