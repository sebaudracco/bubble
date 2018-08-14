package com.appsgeyser.sdk.ads.behavior.bannerBehaviors;

import com.appsgeyser.sdk.ads.AdView;

public class BannerWidthBehavior extends AdViewBehavior {
    private final int width;

    public BannerWidthBehavior(int width) {
        this.width = width;
    }

    public void visit(AdView view) {
    }
}
