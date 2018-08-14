package com.facebook.ads.internal.p033n;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.facebook.ads.internal.n.e;
import com.facebook.ads.internal.p056q.p057a.C2133v;
import com.facebook.ads.internal.view.C2394t;
import com.facebook.ads.internal.view.component.C2202c;
import com.facebook.ads.internal.view.component.C2207h;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.util.ArrayList;

public class C2019a {
    private static final float f4772a = C2133v.f5083b;
    private final C2028h f4773b;
    private final e f4774c;
    private final RelativeLayout f4775d;
    private ArrayList<View> f4776e = new ArrayList();

    public C2019a(Context context, e eVar, RelativeLayout relativeLayout, RelativeLayout relativeLayout2, RelativeLayout relativeLayout3, C2029i c2029i, C2028h c2028h) {
        this.f4775d = relativeLayout;
        this.f4775d.setBackgroundColor(c2028h.m6483b());
        this.f4773b = c2028h;
        this.f4774c = eVar;
        this.f4775d.setLayoutParams(new LayoutParams(-1, Math.round(((float) c2029i.m6500b()) * f4772a)));
        View c2394t = new C2394t(context);
        c2394t.setMinWidth(Math.round(280.0f * f4772a));
        c2394t.setMaxWidth(Math.round(375.0f * f4772a));
        ViewGroup.LayoutParams layoutParams = new LayoutParams(-1, -1);
        layoutParams.addRule(13, -1);
        c2394t.setLayoutParams(layoutParams);
        this.f4775d.addView(c2394t);
        ViewGroup linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(1);
        linearLayout.setLayoutParams(new LayoutParams(-1, -1));
        c2394t.addView(linearLayout);
        switch (c2029i) {
            case HEIGHT_400:
                m6440a(linearLayout);
                break;
            case HEIGHT_300:
                break;
        }
        m6441a(linearLayout, relativeLayout3);
        m6442a(linearLayout, c2029i);
        eVar.a(this.f4775d, this.f4776e);
        LayoutParams layoutParams2 = (LayoutParams) relativeLayout2.getLayoutParams();
        layoutParams2.addRule(11);
        layoutParams2.setMargins(Math.round(f4772a * 4.0f), Math.round(f4772a * 4.0f), Math.round(f4772a * 4.0f), Math.round(f4772a * 4.0f));
        c2394t.addView(relativeLayout2);
    }

    private void m6440a(ViewGroup viewGroup) {
        View c2207h = new C2207h(this.f4775d.getContext(), this.f4774c, this.f4773b);
        c2207h.setLayoutParams(new LinearLayout.LayoutParams(-1, Math.round(110.0f * f4772a)));
        viewGroup.addView(c2207h);
    }

    private void m6441a(ViewGroup viewGroup, RelativeLayout relativeLayout) {
        View relativeLayout2 = new RelativeLayout(this.f4775d.getContext());
        relativeLayout2.setLayoutParams(new LinearLayout.LayoutParams(-1, Math.round(f4772a * BitmapDescriptorFactory.HUE_CYAN)));
        relativeLayout2.setBackgroundColor(this.f4773b.m6483b());
        relativeLayout2.addView(relativeLayout);
        ViewGroup.LayoutParams layoutParams = new LayoutParams(-1, (int) (f4772a * BitmapDescriptorFactory.HUE_CYAN));
        layoutParams.addRule(13, -1);
        relativeLayout.setLayoutParams(layoutParams);
        viewGroup.addView(relativeLayout2);
        this.f4776e.add(relativeLayout);
    }

    private void m6442a(ViewGroup viewGroup, C2029i c2029i) {
        View c2202c = new C2202c(this.f4775d.getContext(), this.f4774c, this.f4773b, m6443a(c2029i), m6444b(c2029i));
        c2202c.setLayoutParams(new LinearLayout.LayoutParams(-1, Math.round(((float) m6444b(c2029i)) * f4772a)));
        viewGroup.addView(c2202c);
        this.f4776e.add(c2202c.getIconView());
        this.f4776e.add(c2202c.getCallToActionView());
    }

    private boolean m6443a(C2029i c2029i) {
        return c2029i == C2029i.HEIGHT_300 || c2029i == C2029i.HEIGHT_120;
    }

    private int m6444b(C2029i c2029i) {
        switch (c2029i) {
            case HEIGHT_400:
                return (c2029i.m6500b() - 180) / 2;
            case HEIGHT_300:
                return c2029i.m6500b() - 180;
            case HEIGHT_100:
            case HEIGHT_120:
                return c2029i.m6500b();
            default:
                return 0;
        }
    }

    public void m6445a() {
        this.f4774c.D();
    }
}
