package com.mopub.mobileads.dfp.adapters;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import com.google.android.gms.ads.formats.NativeAd.Image;

public class MoPubNativeMappedImage extends Image {
    private Drawable mDrawable;
    private Uri mImageUri;
    private double mScale;

    public MoPubNativeMappedImage(Drawable drawable, String imageUrl, double scale) {
        this.mDrawable = drawable;
        this.mImageUri = Uri.parse(imageUrl);
        this.mScale = scale;
    }

    public Drawable getDrawable() {
        return this.mDrawable;
    }

    public Uri getUri() {
        return this.mImageUri;
    }

    public double getScale() {
        return this.mScale;
    }
}
