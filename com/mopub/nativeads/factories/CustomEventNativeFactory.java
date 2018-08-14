package com.mopub.nativeads.factories;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.mopub.common.Preconditions;
import com.mopub.nativeads.CustomEventNative;
import com.mopub.nativeads.MoPubCustomEventNative;
import java.lang.reflect.Constructor;

public class CustomEventNativeFactory {
    protected static CustomEventNativeFactory instance = new CustomEventNativeFactory();

    public static CustomEventNative create(@Nullable String className) throws Exception {
        if (className == null) {
            return new MoPubCustomEventNative();
        }
        return instance.internalCreate(Class.forName(className).asSubclass(CustomEventNative.class));
    }

    @Deprecated
    public static void setInstance(@NonNull CustomEventNativeFactory customEventNativeFactory) {
        Preconditions.checkNotNull(customEventNativeFactory);
        instance = customEventNativeFactory;
    }

    @NonNull
    protected CustomEventNative internalCreate(@NonNull Class<? extends CustomEventNative> nativeClass) throws Exception {
        Preconditions.checkNotNull(nativeClass);
        Constructor<?> nativeConstructor = nativeClass.getDeclaredConstructor((Class[]) null);
        nativeConstructor.setAccessible(true);
        return (CustomEventNative) nativeConstructor.newInstance(new Object[0]);
    }
}
