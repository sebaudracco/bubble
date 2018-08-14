package com.facebook.ads.internal.view.component;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.facebook.ads.internal.adapters.C1900j;
import com.mopub.mobileads.resource.DrawableConstants.RadialCountdown;

public class C2208i extends LinearLayout {
    private static final float f5371a = Resources.getSystem().getDisplayMetrics().density;
    private static final int f5372b = ((int) (6.0f * f5371a));
    private static final int f5373c = ((int) (8.0f * f5371a));
    private final TextView f5374d;
    private final TextView f5375e;

    public C2208i(Context context, C1900j c1900j, boolean z, boolean z2, boolean z3) {
        super(context);
        setOrientation(1);
        Typeface create = VERSION.SDK_INT >= 21 ? Typeface.create("sans-serif-medium", 0) : Typeface.create(Typeface.SANS_SERIF, 1);
        this.f5374d = new TextView(context);
        this.f5374d.setTypeface(create);
        this.f5374d.setTextSize(2, z2 ? RadialCountdown.TEXT_SIZE_SP : 22.0f);
        this.f5374d.setTextColor(c1900j.m5831c(z));
        this.f5374d.setEllipsize(TruncateAt.END);
        this.f5374d.setLineSpacing((float) f5372b, 1.0f);
        this.f5375e = new TextView(context);
        this.f5375e.setTypeface(Typeface.SANS_SERIF, 0);
        this.f5375e.setTextSize(2, z2 ? 14.0f : 16.0f);
        this.f5375e.setTextColor(c1900j.m5830b(z));
        this.f5375e.setEllipsize(TruncateAt.END);
        this.f5375e.setLineSpacing((float) f5372b, 1.0f);
        addView(this.f5374d, new LayoutParams(-1, -2));
        ViewGroup.LayoutParams layoutParams = new LayoutParams(-1, -2);
        layoutParams.setMargins(0, z3 ? f5373c / 2 : f5373c, 0, 0);
        addView(this.f5375e, layoutParams);
    }

    public void m7047a(String str, String str2, boolean z, boolean z2) {
        CharSequence charSequence;
        int i = 0;
        int i2 = 1;
        int i3 = !TextUtils.isEmpty(str) ? 1 : 0;
        if (!TextUtils.isEmpty(str2)) {
            i = 1;
        }
        TextView textView = this.f5374d;
        if (i3 == 0) {
            Object obj = str2;
        }
        textView.setText(r7);
        textView = this.f5375e;
        if (i3 == 0) {
            charSequence = "";
        }
        textView.setText(charSequence);
        if (i3 == 0 || r2 == 0) {
            TextView textView2 = this.f5374d;
            i3 = z ? 2 : z2 ? 4 : 3;
            textView2.setMaxLines(i3);
            return;
        }
        this.f5374d.setMaxLines(z ? 1 : 2);
        TextView textView3 = this.f5375e;
        if (!z) {
            i2 = z2 ? 3 : 2;
        }
        textView3.setMaxLines(i2);
    }

    public void setAlignment(int i) {
        this.f5374d.setGravity(i);
        this.f5375e.setGravity(i);
    }
}
