package com.mobfox.sdk.tagbanner;

import android.view.View;

public interface TagBanner$Listener {
    void onBannerClicked(View view);

    void onBannerClosed(View view);

    void onBannerError(View view, String str);

    void onBannerFinished();

    void onBannerLoaded(View view);

    void onNoFill(View view);
}
