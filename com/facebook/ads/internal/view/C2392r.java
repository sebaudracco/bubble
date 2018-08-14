package com.facebook.ads.internal.view;

import android.content.Context;
import android.text.TextUtils.TruncateAt;
import android.view.View.MeasureSpec;
import android.widget.TextView;

public class C2392r extends TextView {
    private int f5956a;
    private float f5957b;
    private float f5958c = 8.0f;

    public C2392r(Context context, int i) {
        super(context);
        setMaxLines(i);
        setEllipsize(TruncateAt.END);
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.setMaxLines(this.f5956a + 1);
        super.setTextSize(this.f5957b);
        int i5 = i3 - i;
        int i6 = i4 - i2;
        measure(MeasureSpec.makeMeasureSpec(i5, 1073741824), MeasureSpec.makeMeasureSpec(i6, 0));
        if (getMeasuredHeight() > i6) {
            float f = this.f5957b;
            while (f > this.f5958c) {
                f -= 0.5f;
                super.setTextSize(f);
                measure(MeasureSpec.makeMeasureSpec(i5, 1073741824), 0);
                if (getMeasuredHeight() <= i6 && getLineCount() <= this.f5956a) {
                    break;
                }
            }
        }
        super.setMaxLines(this.f5956a);
        setMeasuredDimension(i5, i6);
        super.onLayout(z, i, i2, i3, i4);
    }

    public void setMaxLines(int i) {
        this.f5956a = i;
        super.setMaxLines(i);
    }

    public void setMinTextSize(float f) {
        this.f5958c = f;
    }

    public void setTextSize(float f) {
        this.f5957b = f;
        super.setTextSize(f);
    }
}
