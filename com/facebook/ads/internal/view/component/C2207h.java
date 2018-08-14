package com.facebook.ads.internal.view.component;

import android.content.Context;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.facebook.ads.internal.n.e;
import com.facebook.ads.internal.p033n.C2028h;
import com.facebook.ads.internal.p056q.p057a.C2133v;
import com.facebook.ads.internal.view.C2381p;
import com.mopub.mobileads.resource.DrawableConstants.CtaButton;

public class C2207h extends LinearLayout {
    public C2207h(Context context, e eVar, C2028h c2028h) {
        super(context);
        float f = C2133v.f5083b;
        View linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(1);
        linearLayout.setGravity(17);
        linearLayout.setVerticalGravity(16);
        LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -1);
        layoutParams.setMargins(Math.round(CtaButton.TEXT_SIZE_SP * f), Math.round(CtaButton.TEXT_SIZE_SP * f), Math.round(CtaButton.TEXT_SIZE_SP * f), Math.round(f * CtaButton.TEXT_SIZE_SP));
        linearLayout.setLayoutParams(layoutParams);
        addView(linearLayout);
        CharSequence m = eVar.m();
        View textView = new TextView(getContext());
        if (TextUtils.isEmpty(m)) {
            m = eVar.l();
        }
        textView.setText(m);
        C2381p.m7526a(textView, c2028h);
        textView.setEllipsize(TruncateAt.END);
        textView.setSingleLine(true);
        linearLayout.addView(textView);
        View textView2 = new TextView(getContext());
        textView2.setText(eVar.n());
        C2381p.m7527b(textView2, c2028h);
        textView2.setEllipsize(TruncateAt.END);
        textView2.setMaxLines(2);
        linearLayout.addView(textView2);
    }
}
