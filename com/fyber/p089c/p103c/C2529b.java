package com.fyber.p089c.p103c;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.fyber.p089c.C2524a;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/* compiled from: CountDownTimerLayout */
public final class C2529b extends C2524a {
    private TextView f6268b;
    private int f6269c;
    private C2528a f6270d;
    private float f6271e = (360.0f / ((float) this.f6269c));

    public C2529b(Context context, int i) {
        super(context);
        this.f6269c = i;
        this.f6268b = new TextView(context);
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(13, -1);
        this.f6268b.setLayoutParams(layoutParams);
        this.f6268b.setGravity(17);
        this.f6268b.setText(C2529b.m8027b((long) this.f6269c));
        this.f6268b.setTextColor(-1);
        this.f6268b.setContentDescription("countdownTextView");
        int a = m8023a(30);
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(-16777216);
        gradientDrawable.setShape(1);
        gradientDrawable.setAlpha(128);
        gradientDrawable.setSize(a, a);
        gradientDrawable.setUseLevel(false);
        this.f6270d = new C2528a();
        this.f6270d.m8026a(360.0f);
        m8024a(new LayerDrawable(new Drawable[]{this.f6270d, gradientDrawable}));
        addView(this.f6268b);
        setContentDescription("countdownView");
    }

    public final void m8028a(long j) {
        long j2 = ((long) this.f6269c) - j;
        if (j2 >= 0) {
            this.f6268b.setText(C2529b.m8027b(j2));
            this.f6270d.m8026a(((float) j2) * this.f6271e);
        }
    }

    private static String m8027b(long j) {
        long toSeconds = TimeUnit.MILLISECONDS.toSeconds(j + 850);
        if (toSeconds <= 59) {
            return Long.valueOf(toSeconds).toString();
        }
        return String.format(Locale.ENGLISH, "%d:%02d", new Object[]{Long.valueOf(TimeUnit.MILLISECONDS.toMinutes(850 + j) % TimeUnit.HOURS.toMinutes(1)), Long.valueOf(toSeconds % TimeUnit.MINUTES.toSeconds(1))});
    }
}
