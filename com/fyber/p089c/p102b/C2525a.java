package com.fyber.p089c.p102b;

import android.content.Context;
import android.graphics.Paint.Style;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.ArcShape;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;
import com.fyber.p089c.C2524a;

/* compiled from: CloseButtonLayout */
public final class C2525a extends C2524a {
    public C2525a(Context context) {
        super(context);
        int a = m8023a(30);
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(-16777216);
        gradientDrawable.setShape(1);
        gradientDrawable.setAlpha(128);
        gradientDrawable.setSize(a, a);
        ShapeDrawable shapeDrawable = new ShapeDrawable(new ArcShape(0.0f, 360.0f));
        shapeDrawable.getPaint().setColor(-1);
        shapeDrawable.getPaint().setStrokeWidth((float) Math.round(1.5f));
        shapeDrawable.getPaint().setStyle(Style.STROKE);
        shapeDrawable.getPaint().setAntiAlias(true);
        shapeDrawable.setIntrinsicHeight(a);
        shapeDrawable.setIntrinsicWidth(a);
        m8024a(new LayerDrawable(new Drawable[]{shapeDrawable, gradientDrawable}));
        a = m8023a(15);
        View c2527c = new C2527c(context, 1.5f);
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(a, a);
        layoutParams.addRule(13);
        c2527c.setLayoutParams(layoutParams);
        addView(c2527c);
        setContentDescription("closeButton");
    }

    public final int m8025a() {
        return m8023a(60);
    }
}
