package com.google.firebase.components;

import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.firebase.inject.Provider;

public /* synthetic */ class ComponentContainer$$CC {
    @KeepForSdk
    public static Object get(ComponentContainer componentContainer, Class cls) {
        Provider provider = componentContainer.getProvider(cls);
        return provider == null ? null : provider.get();
    }
}
