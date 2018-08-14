package com.appnext.banners;

import cz.msebera.android.httpclient.HttpStatus;

public class BannerSize {
    public static final BannerSize BANNER = new BannerSize(320, 50, "BANNER");
    public static final BannerSize LARGE_BANNER = new BannerSize(320, 100, "LARGE_BANNER");
    public static final BannerSize MEDIUM_RECTANGLE = new BannerSize(HttpStatus.SC_MULTIPLE_CHOICES, 250, "MEDIUM_RECTANGLE");
    private int height;
    private String name;
    private int width;

    private BannerSize(int i, int i2, String str) {
        this.width = i;
        this.height = i2;
        this.name = str;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof BannerSize)) {
            return false;
        }
        BannerSize bannerSize = (BannerSize) obj;
        if (this.width == bannerSize.width && this.height == bannerSize.height && this.name.equals(bannerSize.name)) {
            return true;
        }
        return false;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public String toString() {
        return this.name;
    }
}
