package com.fyber.p089c.p105e;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.fyber.p089c.C2524a;

/* compiled from: SpinnerLayout */
public final class C2545b extends C2524a {
    private Animation f6360b = new RotateAnimation(0.0f, 360.0f, 1, 0.5f, 1, 0.5f);
    private View f6361c;
    private ImageView f6362d;

    public C2545b(Context context) {
        super(context);
        this.f6361c = new View(context);
        this.f6360b.setRepeatCount(-1);
        this.f6360b.setDuration(1000);
        this.f6360b.setInterpolator(new LinearInterpolator());
        this.f6360b.setFillAfter(true);
        int a = m8023a(100);
        int a2 = m8023a(90);
        this.f6361c.setLayoutParams(new LayoutParams(a, a));
        View view = this.f6361c;
        Drawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(-1304543682);
        gradientDrawable.setShape(0);
        gradientDrawable.setUseLevel(false);
        gradientDrawable.setCornerRadius(8.0f);
        view.setBackgroundDrawable(gradientDrawable);
        addView(this.f6361c);
        ViewGroup.LayoutParams layoutParams = new RelativeLayout.LayoutParams(a2, a2);
        layoutParams.addRule(13, -1);
        this.f6362d = m8024a(new C2544a());
        this.f6362d.setLayoutParams(layoutParams);
        setContentDescription("loadingSpinner");
    }

    public final void m8103a() {
        this.f6362d.startAnimation(this.f6360b);
    }

    public final void m8104b() {
        this.f6362d.clearAnimation();
    }
}
