package com.fyber.p089c;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout;

/* compiled from: DrawableLayout */
public class C2524a extends RelativeLayout {
    protected final int f6263a = m8023a(15);

    public C2524a(Context context) {
        super(context);
    }

    protected final ImageView m8024a(Drawable drawable) {
        View imageView = new ImageView(getContext());
        imageView.setImageDrawable(drawable);
        imageView.setAdjustViewBounds(true);
        imageView.setScaleType(ScaleType.CENTER);
        imageView.setPadding(this.f6263a, this.f6263a, this.f6263a, this.f6263a);
        addView(imageView);
        return imageView;
    }

    protected final int m8023a(int i) {
        return (int) TypedValue.applyDimension(1, (float) i, getResources().getDisplayMetrics());
    }
}
