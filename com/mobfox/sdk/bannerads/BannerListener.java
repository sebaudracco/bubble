package com.mobfox.sdk.bannerads;

import android.view.View;

public interface BannerListener {
    void onBannerClicked(View view);

    void onBannerClosed(View view);

    void onBannerError(View view, Exception exception);

    void onBannerFinished();

    void onBannerLoaded(View view);

    void onNoFill(View view);
}
