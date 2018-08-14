package com.appsgeyser.sdk.ads.nativeAd.nativeFacades;

import android.content.Context;
import android.view.View;
import com.appsgeyser.sdk.configuration.models.ConfigPhp;

public interface NativeAdFacade {
    void adClicked(ConfigPhp configPhp, Context context);

    void adImpression(ConfigPhp configPhp, Context context);

    String getAdDescription();

    String getAdTitle();

    String getButtonText();

    String getImageUrl();

    String getUniqueAdString();

    void registerViewForAd(View view);

    void viewRecycled(View view);
}
