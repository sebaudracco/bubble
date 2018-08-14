package com.facebook.ads.internal.view;

import android.content.Context;
import android.widget.RelativeLayout;

public class C2394t extends RelativeLayout {
    private int f5961a = 0;
    private int f5962b = 0;

    public C2394t(Context context) {
        super(context);
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (this.f5962b > 0 && getMeasuredWidth() > this.f5962b) {
            setMeasuredDimension(this.f5962b, getMeasuredHeight());
        } else if (getMeasuredWidth() < this.f5961a) {
            setMeasuredDimension(this.f5961a, getMeasuredHeight());
        }
    }

    public void setMaxWidth(int i) {
        this.f5962b = i;
    }

    public void setMinWidth(int i) {
        this.f5961a = i;
    }
}
