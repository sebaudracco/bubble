package com.fyber.ads.banners;

public class NetworkBannerSize {
    private final String f6025a;
    private final BannerSize f6026b;

    public NetworkBannerSize(String str, BannerSize bannerSize) {
        this.f6025a = str;
        this.f6026b = bannerSize;
    }

    public String getNetwork() {
        return this.f6025a;
    }

    public BannerSize getSize() {
        return this.f6026b;
    }

    public String toString() {
        return this.f6025a + " " + this.f6026b.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return toString().equals(((NetworkBannerSize) obj).toString());
    }
}
