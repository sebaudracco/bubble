package com.facebook.ads.internal.view;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.facebook.ads.internal.n.e;
import com.facebook.ads.internal.p033n.C2028h;

public abstract class C2381p {
    public static LinearLayout m7525a(Context context, e eVar, C2028h c2028h) {
        LinearLayout linearLayout = new LinearLayout(context);
        View c2393s = new C2393s(context);
        c2393s.setText(eVar.q());
        C2381p.m7527b(c2393s, c2028h);
        linearLayout.addView(c2393s);
        return linearLayout;
    }

    public static void m7526a(TextView textView, C2028h c2028h) {
        textView.setTextColor(c2028h.m6486c());
        textView.setTextSize((float) c2028h.m6495h());
        textView.setTypeface(c2028h.m6479a(), 1);
    }

    public static void m7527b(TextView textView, C2028h c2028h) {
        textView.setTextColor(c2028h.m6488d());
        textView.setTextSize((float) c2028h.m6496i());
        textView.setTypeface(c2028h.m6479a());
    }
}
