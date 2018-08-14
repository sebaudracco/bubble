package com.mopub.mobileads.factories;

import com.mopub.mobileads.CustomEventInterstitial;
import java.lang.reflect.Constructor;

public class CustomEventInterstitialFactory {
    private static CustomEventInterstitialFactory instance = new CustomEventInterstitialFactory();

    public static CustomEventInterstitial create(String className) throws Exception {
        return instance.internalCreate(className);
    }

    @Deprecated
    public static void setInstance(CustomEventInterstitialFactory factory) {
        instance = factory;
    }

    protected CustomEventInterstitial internalCreate(String className) throws Exception {
        Constructor<?> interstitialConstructor = Class.forName(className).asSubclass(CustomEventInterstitial.class).getDeclaredConstructor((Class[]) null);
        interstitialConstructor.setAccessible(true);
        return (CustomEventInterstitial) interstitialConstructor.newInstance(new Object[0]);
    }
}
