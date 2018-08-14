package com.mobfox.sdk.customevents;

import android.view.View;

public interface CustomEventBannerListener {
    void onBannerClicked(View view);

    void onBannerClosed(View view);

    void onBannerError(View view, Exception exception);

    void onBannerFinished();

    void onBannerLoaded(View view);
}
