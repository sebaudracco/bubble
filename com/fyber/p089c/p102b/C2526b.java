package com.fyber.p089c.p102b;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;
import com.fyber.p089c.C2524a;

/* compiled from: CloseXButtonLayout */
public final class C2526b extends C2524a {
    public C2526b(Context context) {
        super(context);
        int a = m8023a(15);
        View c2527c = new C2527c(context, 4.5f);
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(a, a);
        layoutParams.addRule(13, -1);
        c2527c.setLayoutParams(layoutParams);
        setPadding(this.a, this.a, this.a, this.a);
        addView(c2527c);
    }
}
