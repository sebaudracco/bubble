package com.appsgeyser.sdk.ads;

import android.app.Activity;
import android.content.Context;
import com.appsgeyser.sdk.configuration.Constants.BannerLoadTags;

public class SimpleFullScreenBannerListener implements IFullScreenBannerListener {
    public void onLoadStarted() {
    }

    public void onLoadFinished(FullScreenBanner banner) {
        banner.show();
    }

    public void onAdFailedToLoad(Context context, String tag) {
        if (tag.equals(BannerLoadTags.ON_EXIT) && (context instanceof Activity)) {
            ((Activity) context).finish();
        }
    }

    public void onAdHided(Context context, String tag) {
        if (tag.equals(BannerLoadTags.ON_EXIT) && (context instanceof Activity)) {
            ((Activity) context).finish();
        }
    }
}
