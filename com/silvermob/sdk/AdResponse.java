package com.silvermob.sdk;

import com.silvermob.sdk.Const.BannerFormat;
import com.silvermob.sdk.Const.ClickType;
import java.io.Serializable;

class AdResponse implements Serializable {
    private String mFormat;
    private String mHTMLCode;
    private String mHeight;
    private Long mTimestamp;
    private String mType;
    private String mWidth;

    AdResponse(String htmlCode, String size) {
        this(htmlCode, size, Long.valueOf(0));
    }

    AdResponse(String htmlCode, String size, Long timestamp) {
        this.mTimestamp = timestamp;
        this.mHTMLCode = htmlCode;
        this.mType = "html";
        String[] arr = size.split("x");
        if (arr.length == 2) {
            this.mWidth = arr[0];
            this.mHeight = arr[1];
            if (this.mWidth.equals("320") && this.mHeight.equals("480")) {
                this.mFormat = "interstitial";
            } else if (this.mWidth.equals("320") && this.mHeight.equals("50")) {
                this.mFormat = BannerFormat.BANNER;
            }
        }
    }

    Long getTimestamp() {
        return this.mTimestamp;
    }

    String getClickUrl() {
        return "";
    }

    String getType() {
        return this.mType;
    }

    String getFormat() {
        return this.mFormat;
    }

    String getImageUrl() {
        return "";
    }

    String getBannerWidth() {
        return this.mWidth;
    }

    String getBannerHeight() {
        return this.mHeight;
    }

    String getHTMLCode() {
        return this.mHTMLCode;
    }

    String getClickType() {
        return ClickType.IN_APP;
    }

    int getSkipOverlay() {
        return 1;
    }
}
