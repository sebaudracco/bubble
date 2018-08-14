package com.appsgeyser.sdk.ads.fullscreenSdk.fullscreenFacades;

import android.content.Context;
import com.appsgeyser.sdk.ads.fullscreenSdk.fullscreenFacades.FullscreenSdkFacade.FullscreenAdListener;
import com.appsgeyser.sdk.configuration.models.ConfigPhp;

public abstract class AbstractFullscreenFacade implements FullscreenSdkFacade {
    protected ConfigPhp configPhp;
    protected Context context;
    protected FullscreenAdListener listener;

    protected abstract void init();

    public AbstractFullscreenFacade(Context context, ConfigPhp configPhp) {
        this.context = context;
        this.configPhp = configPhp;
        init();
    }

    public void setListener(FullscreenAdListener listener) {
        this.listener = listener;
    }

    public void onPause() {
    }

    public void onResume() {
    }
}
