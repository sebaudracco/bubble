package com.mobfox.sdk.nativeads;

import android.graphics.Bitmap;

public class ImageItem {
    int f9032h;
    Bitmap img;
    String type;
    String url;
    int f9033w;

    public ImageItem(String type, String url, int h, int w) {
        this.type = type;
        this.url = url;
        this.f9032h = h;
        this.f9033w = w;
    }

    public String getType() {
        return this.type;
    }

    public String getUrl() {
        return this.url;
    }

    public int getH() {
        return this.f9032h;
    }

    public int getW() {
        return this.f9033w;
    }

    public Bitmap getImg() {
        return this.img;
    }

    public void setImg(Bitmap img) {
        this.img = img;
    }
}
