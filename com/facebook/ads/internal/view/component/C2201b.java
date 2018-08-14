package com.facebook.ads.internal.view.component;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.facebook.ads.internal.n.e;
import com.facebook.ads.internal.p033n.C2028h;
import com.facebook.ads.internal.view.C2381p;
import com.facebook.ads.internal.view.C2392r;

public class C2201b extends LinearLayout {
    private C2392r f5353a = new C2392r(getContext(), 2);
    private int f5354b;

    public C2201b(Context context, e eVar, C2028h c2028h) {
        int i = 21;
        super(context);
        setOrientation(1);
        setVerticalGravity(16);
        this.f5353a.setMinTextSize((float) (c2028h.m6495h() - 2));
        this.f5353a.setText(eVar.l());
        C2381p.m7526a(this.f5353a, c2028h);
        this.f5353a.setLayoutParams(new LayoutParams(-2, -2));
        addView(this.f5353a);
        if (eVar.l() != null) {
            i = Math.min(eVar.l().length(), 21);
        }
        this.f5354b = i;
        addView(C2381p.m7525a(context, eVar, c2028h));
    }

    public int getMinVisibleTitleCharacters() {
        return this.f5354b;
    }

    public TextView getTitleTextView() {
        return this.f5353a;
    }
}
