package com.facebook.ads.internal.view.component;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.text.TextUtils.TruncateAt;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.facebook.ads.internal.n.e;
import com.facebook.ads.internal.p033n.C2028h;
import com.facebook.ads.internal.view.C2381p;
import com.facebook.ads.internal.view.C2393s;
import com.mopub.mobileads.resource.DrawableConstants.CtaButton;

public class C2202c extends LinearLayout {
    private ImageView f5355a;
    private C2201b f5356b;
    private TextView f5357c;
    private LinearLayout f5358d = new LinearLayout(getContext());

    public C2202c(Context context, e eVar, C2028h c2028h, boolean z, int i) {
        super(context);
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        setVerticalGravity(16);
        setOrientation(1);
        View linearLayout = new LinearLayout(getContext());
        linearLayout.setOrientation(1);
        linearLayout.setGravity(16);
        LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -1);
        layoutParams.setMargins(Math.round(CtaButton.TEXT_SIZE_SP * displayMetrics.density), Math.round(CtaButton.TEXT_SIZE_SP * displayMetrics.density), Math.round(CtaButton.TEXT_SIZE_SP * displayMetrics.density), Math.round(CtaButton.TEXT_SIZE_SP * displayMetrics.density));
        linearLayout.setLayoutParams(layoutParams);
        addView(linearLayout);
        layoutParams = new LinearLayout.LayoutParams(-1, 0);
        this.f5358d.setOrientation(0);
        this.f5358d.setGravity(16);
        layoutParams.weight = 3.0f;
        this.f5358d.setLayoutParams(layoutParams);
        linearLayout.addView(this.f5358d);
        this.f5355a = new C2206g(getContext());
        int a = m7045a(z, i);
        LayoutParams layoutParams2 = new LinearLayout.LayoutParams(Math.round(((float) a) * displayMetrics.density), Math.round(((float) a) * displayMetrics.density));
        layoutParams2.setMargins(0, 0, Math.round(CtaButton.TEXT_SIZE_SP * displayMetrics.density), 0);
        this.f5355a.setLayoutParams(layoutParams2);
        e.a(eVar.i(), this.f5355a);
        this.f5358d.addView(this.f5355a);
        View linearLayout2 = new LinearLayout(getContext());
        linearLayout2.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
        linearLayout2.setOrientation(0);
        linearLayout2.setGravity(16);
        this.f5358d.addView(linearLayout2);
        this.f5356b = new C2201b(getContext(), eVar, c2028h);
        layoutParams2 = new LinearLayout.LayoutParams(-2, -1);
        layoutParams2.setMargins(0, 0, Math.round(CtaButton.TEXT_SIZE_SP * displayMetrics.density), 0);
        layoutParams2.weight = 0.5f;
        this.f5356b.setLayoutParams(layoutParams2);
        linearLayout2.addView(this.f5356b);
        this.f5357c = new TextView(getContext());
        this.f5357c.setPadding(Math.round(6.0f * displayMetrics.density), Math.round(6.0f * displayMetrics.density), Math.round(6.0f * displayMetrics.density), Math.round(6.0f * displayMetrics.density));
        this.f5357c.setText(eVar.p());
        this.f5357c.setTextColor(c2028h.m6492f());
        this.f5357c.setTextSize(14.0f);
        this.f5357c.setTypeface(c2028h.m6479a(), 1);
        this.f5357c.setMaxLines(2);
        this.f5357c.setEllipsize(TruncateAt.END);
        this.f5357c.setGravity(17);
        Drawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(c2028h.m6490e());
        gradientDrawable.setCornerRadius(displayMetrics.density * 5.0f);
        gradientDrawable.setStroke(1, c2028h.m6494g());
        this.f5357c.setBackgroundDrawable(gradientDrawable);
        LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-2, -2);
        layoutParams3.weight = 0.25f;
        this.f5357c.setLayoutParams(layoutParams3);
        if (!eVar.h()) {
            this.f5357c.setVisibility(4);
        }
        linearLayout2.addView(this.f5357c);
        if (z) {
            View c2393s = new C2393s(getContext());
            c2393s.setText(eVar.n());
            C2381p.m7527b(c2393s, c2028h);
            c2393s.setMinTextSize((float) (c2028h.m6496i() - 1));
            layoutParams = new LinearLayout.LayoutParams(-1, 0);
            layoutParams.weight = 1.0f;
            c2393s.setLayoutParams(layoutParams);
            c2393s.setGravity(80);
            linearLayout.addView(c2393s);
        }
    }

    private int m7045a(boolean z, int i) {
        return (int) (((double) (i - 30)) * (3.0d / ((double) ((z ? 1 : 0) + 3))));
    }

    public TextView getCallToActionView() {
        return this.f5357c;
    }

    public ImageView getIconView() {
        return this.f5355a;
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        TextView titleTextView = this.f5356b.getTitleTextView();
        if (titleTextView.getLayout().getLineEnd(titleTextView.getLineCount() - 1) < this.f5356b.getMinVisibleTitleCharacters()) {
            this.f5358d.removeView(this.f5355a);
            super.onMeasure(i, i2);
        }
    }
}
