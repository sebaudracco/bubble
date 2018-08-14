package com.appsgeyser.sdk.ads;

import android.content.Context;
import android.support.annotation.NonNull;
import com.appsgeyser.sdk.configuration.Configuration;
import com.appsgeyser.sdk.hasher.Hasher;

public abstract class BaseSecureJsInterface {
    protected boolean checkSecurityCode(String hashCode, @NonNull Context context) {
        Configuration config = Configuration.getInstance(context);
        return hashCode.equalsIgnoreCase(Hasher.md5(config.getAppGuid() + config.getApplicationId()));
    }
}
