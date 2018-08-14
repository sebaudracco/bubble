package com.appsgeyser.sdk.ads.behavior.bannerBehaviors;

import com.appsgeyser.sdk.ads.AdView;

public class BannerHeightBehavior extends AdViewBehavior {
    private final int height;

    public BannerHeightBehavior(int height) {
        this.height = height;
    }

    public void visit(AdView view) {
    }
}
