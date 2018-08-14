package com.vungle.publisher;

import dagger.internal.Factory;

/* compiled from: vungle */
public enum AdConfig_Factory implements Factory<AdConfig> {
    INSTANCE;

    public AdConfig get() {
        return new AdConfig();
    }

    public static Factory<AdConfig> create() {
        return INSTANCE;
    }
}
