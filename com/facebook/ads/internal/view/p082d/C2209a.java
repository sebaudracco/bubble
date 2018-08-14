package com.facebook.ads.internal.view.p082d;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.support.v4.view.GravityCompat;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.facebook.ads.internal.adapters.C1900j;
import com.facebook.ads.internal.adapters.ad;
import com.facebook.ads.internal.p056q.p057a.C2133v;
import com.facebook.ads.internal.p069m.C2012c;
import com.facebook.ads.internal.view.C1923a.C1832a;
import com.facebook.ads.internal.view.component.C2200a;
import com.facebook.ads.internal.view.component.C2204e;
import com.facebook.ads.internal.view.component.C2208i;
import com.facebook.ads.internal.view.p034b.C2186d;
import com.facebook.ads.internal.view.p053e.p054b.C2246z;
import java.util.HashMap;

public class C2209a extends LinearLayout {
    private static final int f5376a = ((int) (12.0f * C2133v.f5083b));
    private static final int f5377b = ((int) (16.0f * C2133v.f5083b));
    private final C2208i f5378c;
    private final ImageView f5379d;
    private final RelativeLayout f5380e;
    private final C2200a f5381f;
    private final int f5382g;

    public C2209a(Context context, int i, C1900j c1900j, C2012c c2012c, C1832a c1832a, boolean z, boolean z2) {
        super(context);
        this.f5382g = i;
        this.f5379d = new C2204e(context);
        C2133v.m6832a(this.f5379d);
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(i, i);
        layoutParams.addRule(15);
        layoutParams.addRule(9);
        layoutParams.setMargins(0, 0, f5376a, 0);
        if (z2) {
            this.f5379d.setVisibility(8);
        }
        this.f5378c = new C2208i(context, c1900j, true, z, true);
        this.f5378c.setAlignment(GravityCompat.START);
        LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams2.addRule(1, this.f5379d.getId());
        layoutParams2.addRule(15);
        this.f5381f = new C2200a(context, true, false, C2246z.REWARDED_VIDEO_AD_CLICK.m7091a(), c1900j, c2012c, c1832a);
        this.f5381f.setVisibility(8);
        this.f5380e = new RelativeLayout(context);
        C2133v.m6832a(this.f5380e);
        this.f5380e.addView(this.f5379d, layoutParams);
        this.f5380e.addView(this.f5378c, layoutParams2);
        addView(this.f5380e, new LinearLayout.LayoutParams(-2, -2));
        Drawable gradientDrawable = new GradientDrawable(Orientation.TOP_BOTTOM, new int[]{0, -15658735});
        gradientDrawable.setCornerRadius(0.0f);
        C2133v.m6834a((View) this, gradientDrawable);
    }

    public void m7048a() {
        this.f5381f.setVisibility(0);
    }

    public void m7049a(int i) {
        int i2 = -1;
        int i3 = 1;
        C2133v.m6836b(this.f5381f);
        int i4 = i == 1 ? 1 : 0;
        if (i4 == 0) {
            i3 = 0;
        }
        setOrientation(i3);
        LayoutParams layoutParams = new LinearLayout.LayoutParams(i4 != 0 ? -1 : 0, -2);
        layoutParams.weight = 1.0f;
        if (i4 == 0) {
            i2 = -2;
        }
        LayoutParams layoutParams2 = new LinearLayout.LayoutParams(i2, -2);
        layoutParams2.setMargins(i4 != 0 ? 0 : f5377b, i4 != 0 ? f5377b : 0, 0, 0);
        layoutParams2.gravity = 80;
        this.f5380e.setLayoutParams(layoutParams);
        addView(this.f5381f, layoutParams2);
    }

    public void setInfo(ad adVar) {
        this.f5378c.m7047a(adVar.m5728f(), adVar.m5729g(), false, false);
        this.f5381f.m7044a(adVar.m5739q(), adVar.m5738p(), adVar.m5723b(), new HashMap());
        if (!TextUtils.isEmpty(adVar.m5731i())) {
            new C2186d(this.f5379d).m7001a(this.f5382g, this.f5382g).m7004a(adVar.m5731i());
        }
    }
}
