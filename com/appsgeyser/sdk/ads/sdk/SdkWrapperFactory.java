package com.appsgeyser.sdk.ads.sdk;

import java.security.InvalidParameterException;
import java.util.HashMap;

class SdkWrapperFactory {
    private static final SdkWrapperFactory INSTANCE = new SdkWrapperFactory();
    private final HashMap<String, SdkWrapper> map = new HashMap();

    private SdkWrapperFactory() {
    }

    SdkWrapper getWrapperByKey(String sdkKey) throws InvalidParameterException {
        if (this.map.get(sdkKey) == null) {
            SdkWrapper wrapper = newInstance(sdkKey);
            if (wrapper == null) {
                throw new InvalidParameterException("Could not create wrapper for " + sdkKey);
            }
            this.map.put(sdkKey, wrapper);
        }
        return (SdkWrapper) this.map.get(sdkKey);
    }

    static SdkWrapperFactory getInstance() {
        return INSTANCE;
    }

    private SdkWrapper newInstance(String sdkKey) {
        Object obj = -1;
        switch (sdkKey.hashCode()) {
            case -75196300:
                if (sdkKey.equals("APPNEXT")) {
                    obj = 1;
                    break;
                }
                break;
            case -59577165:
                if (sdkKey.equals("SILVERMOB")) {
                    obj = null;
                    break;
                }
                break;
        }
        switch (obj) {
            case null:
                return new SilvermobSdkWrapper();
            case 1:
                return new AppNextSdkWrapper();
            default:
                return null;
        }
    }
}
