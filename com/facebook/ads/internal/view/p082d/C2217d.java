package com.facebook.ads.internal.view.p082d;

import android.content.Context;
import android.widget.ImageView;
import android.widget.RelativeLayout.LayoutParams;
import com.facebook.ads.internal.view.component.C2205f;
import com.facebook.ads.internal.view.p034b.C2186d;

class C2217d extends C2205f {
    private final ImageView f5405a;

    public C2217d(Context context) {
        super(context);
        this.f5405a = new ImageView(context);
        this.f5405a.setAdjustViewBounds(true);
        addView(this.f5405a, new LayoutParams(-2, -1));
    }

    public void m7074a(String str) {
        C2186d c2186d = new C2186d(this.f5405a);
        c2186d.m7000a();
        c2186d.m7004a(str);
    }
}
