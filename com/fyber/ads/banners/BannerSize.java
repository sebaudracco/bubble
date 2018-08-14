package com.fyber.ads.banners;

public class BannerSize {
    public static final int AUTO_SIZE = -2;
    public static final BannerSize FIXED_HEIGHT_50 = Builder.newBuilder().withHeight(50).build();
    public static final BannerSize FIXED_HEIGHT_90 = Builder.newBuilder().withHeight(90).build();
    public static final BannerSize FIXED_SIZE_320_50 = Builder.newBuilder().withWidth(320).withHeight(50).build();
    public static final BannerSize FLUID_SIZE = Builder.newBuilder().withHeight(-1).build();
    public static final int FULL_SIZE = -1;
    public static final BannerSize SMART_SIZE = Builder.newBuilder().build();
    private int height;
    private int width;

    public static class Builder {
        private int f6023a = -1;
        private int f6024b = -2;

        private Builder() {
        }

        public static Builder newBuilder() {
            return new Builder();
        }

        public Builder withWidth(int i) {
            this.f6023a = i;
            return this;
        }

        public Builder withHeight(int i) {
            this.f6024b = i;
            return this;
        }

        public BannerSize build() {
            return new BannerSize();
        }
    }

    private BannerSize(Builder builder) {
        this.width = builder.f6023a;
        this.height = builder.f6024b;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        BannerSize bannerSize = (BannerSize) obj;
        if (this.width != bannerSize.width) {
            return false;
        }
        if (this.height != bannerSize.height) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (this.width * 31) + this.height;
    }

    public String toString() {
        String str;
        String str2;
        if (this.width == -1) {
            str = "full_width ";
        } else if (this.width == -2) {
            str = "smart_width ";
        } else {
            str = String.valueOf(this.width);
        }
        if (this.height == -1) {
            str2 = " full_height";
        } else if (this.height == -2) {
            str2 = " smart_height";
        } else {
            str2 = String.valueOf(this.height);
        }
        return "(" + str + "x" + str2 + ")";
    }
}
