package com.facebook.ads.internal.view.component;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v4.graphics.ColorUtils;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import com.facebook.ads.internal.adapters.C1900j;
import com.facebook.ads.internal.p056q.p057a.C2133v;
import com.facebook.ads.internal.p059a.C1873a;
import com.facebook.ads.internal.p059a.C1874b;
import com.facebook.ads.internal.p069m.C2012c;
import com.facebook.ads.internal.view.C1923a.C1832a;
import java.util.Locale;
import java.util.Map;

public class C2200a extends Button {
    public static final int f5345a = ((int) (16.0f * C2133v.f5083b));
    private static final int f5346b = ((int) (4.0f * C2133v.f5083b));
    private final Paint f5347c = new Paint();
    private final RectF f5348d;
    private final boolean f5349e;
    private final String f5350f;
    @Nullable
    private final C2012c f5351g;
    @Nullable
    private final C1832a f5352h;

    public C2200a(Context context, boolean z, boolean z2, String str, C1900j c1900j, C2012c c2012c, C1832a c1832a) {
        super(context);
        this.f5351g = c2012c;
        this.f5352h = c1832a;
        this.f5349e = z;
        this.f5350f = str;
        setTextSize(2, 16.0f);
        setTypeface(Typeface.create("sans-serif-medium", 0));
        setGravity(17);
        setPadding(f5345a, f5345a, f5345a, f5345a);
        setTextColor(c1900j.m5834f(z2));
        int e = c1900j.m5833e(z2);
        int blendARGB = ColorUtils.blendARGB(e, -16777216, 0.1f);
        this.f5347c.setStyle(Style.FILL);
        this.f5347c.setColor(e);
        this.f5348d = new RectF();
        if (!z) {
            Drawable stateListDrawable = new StateListDrawable();
            stateListDrawable.addState(new int[]{16842919}, new ColorDrawable(blendARGB));
            stateListDrawable.addState(new int[0], new ColorDrawable(e));
            setBackgroundDrawable(stateListDrawable);
        }
    }

    public void m7044a(String str, final String str2, final String str3, final Map<String, String> map) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || this.f5351g == null) {
            setVisibility(8);
            return;
        }
        setText(str.toUpperCase(Locale.US));
        setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ C2200a f5334d;

            public void onClick(View view) {
                try {
                    C1873a a = C1874b.m5632a(this.f5334d.getContext(), this.f5334d.f5351g, str3, Uri.parse(str2), map);
                    if (a != null) {
                        a.mo3622b();
                    }
                    if (this.f5334d.f5352h != null) {
                        this.f5334d.f5352h.mo3561a(this.f5334d.f5350f);
                    }
                } catch (Throwable e) {
                    Log.e(String.valueOf(C2200a.class), "Error while opening " + str2, e);
                } catch (Throwable e2) {
                    Log.e(String.valueOf(C2200a.class), "Error executing action", e2);
                }
            }
        });
    }

    protected void onDraw(Canvas canvas) {
        if (this.f5349e) {
            this.f5348d.set(0.0f, 0.0f, (float) getWidth(), (float) getHeight());
            canvas.drawRoundRect(this.f5348d, (float) f5346b, (float) f5346b, this.f5347c);
        }
        super.onDraw(canvas);
    }
}
