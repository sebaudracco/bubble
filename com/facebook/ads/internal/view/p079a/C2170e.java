package com.facebook.ads.internal.view.p079a;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.facebook.ads.internal.p056q.p075b.C2136b;
import com.facebook.ads.internal.p056q.p075b.C2137c;
import com.mopub.mobileads.resource.DrawableConstants.CloseButton;
import com.mopub.mobileads.resource.DrawableConstants.CtaButton;

@TargetApi(19)
public class C2170e extends LinearLayout {
    private TextView f5230a;
    private TextView f5231b;
    private Drawable f5232c;

    public C2170e(Context context) {
        super(context);
        m6953a();
    }

    private void m6953a() {
        float f = getResources().getDisplayMetrics().density;
        setOrientation(1);
        this.f5230a = new TextView(getContext());
        LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        this.f5230a.setTextColor(-16777216);
        this.f5230a.setTextSize(2, CloseButton.TEXT_SIZE_SP);
        this.f5230a.setEllipsize(TruncateAt.END);
        this.f5230a.setSingleLine(true);
        this.f5230a.setVisibility(8);
        addView(this.f5230a, layoutParams);
        this.f5231b = new TextView(getContext());
        layoutParams = new LinearLayout.LayoutParams(-1, -2);
        this.f5231b.setAlpha(0.5f);
        this.f5231b.setTextColor(-16777216);
        this.f5231b.setTextSize(2, CtaButton.TEXT_SIZE_SP);
        this.f5231b.setCompoundDrawablePadding((int) (f * 5.0f));
        this.f5231b.setEllipsize(TruncateAt.END);
        this.f5231b.setSingleLine(true);
        this.f5231b.setVisibility(8);
        addView(this.f5231b, layoutParams);
    }

    private Drawable getPadlockDrawable() {
        if (this.f5232c == null) {
            this.f5232c = C2137c.m6846a(getContext(), C2136b.BROWSER_PADLOCK);
        }
        return this.f5232c;
    }

    public void setSubtitle(String str) {
        if (TextUtils.isEmpty(str)) {
            this.f5231b.setText(null);
            this.f5231b.setVisibility(8);
            return;
        }
        Uri parse = Uri.parse(str);
        this.f5231b.setText(parse.getHost());
        this.f5231b.setCompoundDrawablesRelativeWithIntrinsicBounds("https".equals(parse.getScheme()) ? getPadlockDrawable() : null, null, null, null);
        this.f5231b.setVisibility(0);
    }

    public void setTitle(String str) {
        if (TextUtils.isEmpty(str)) {
            this.f5230a.setText(null);
            this.f5230a.setVisibility(8);
            return;
        }
        this.f5230a.setText(str);
        this.f5230a.setVisibility(0);
    }
}
