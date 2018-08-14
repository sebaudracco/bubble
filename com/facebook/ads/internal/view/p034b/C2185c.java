package com.facebook.ads.internal.view.p034b;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.annotation.IntRange;
import android.support.annotation.Nullable;
import android.text.Layout.Alignment;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import com.facebook.ads.internal.p070r.C2154a;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.mopub.mobileads.resource.DrawableConstants.CloseButton;
import com.stripe.android.model.Card;
import java.lang.ref.WeakReference;

public class C2185c extends Drawable {
    private final Paint f5274a = new Paint();
    private final Paint f5275b = new Paint();
    private final Path f5276c = new Path();
    private final TextPaint f5277d = new TextPaint();
    private final Paint f5278e = new Paint();
    private int f5279f;
    private int f5280g;
    private String f5281h;
    private int f5282i;
    private boolean f5283j;
    @Nullable
    private String f5284k;
    private String f5285l;
    private long f5286m;
    private final Handler f5287n = new Handler();
    @Nullable
    private WeakReference<C2154a> f5288o;
    private final Runnable f5289p = new C21841(this);

    class C21841 implements Runnable {
        final /* synthetic */ C2185c f5273a;

        C21841(C2185c c2185c) {
            this.f5273a = c2185c;
        }

        public void run() {
            this.f5273a.m6990c();
            if (this.f5273a.f5283j) {
                this.f5273a.f5287n.postDelayed(this.f5273a.f5289p, 250);
            }
        }
    }

    public C2185c() {
        this.f5274a.setColor(Color.argb(127, 36, 36, 36));
        this.f5274a.setStyle(Style.FILL_AND_STROKE);
        this.f5275b.setAntiAlias(true);
        this.f5275b.setColor(Color.argb(191, 0, 255, 0));
        this.f5275b.setStrokeWidth(CloseButton.TEXT_SIZE_SP);
        this.f5275b.setStyle(Style.STROKE);
        this.f5277d.setAntiAlias(true);
        this.f5277d.setColor(-1);
        this.f5277d.setStyle(Style.FILL_AND_STROKE);
        this.f5277d.setTextSize(BitmapDescriptorFactory.HUE_ORANGE);
        this.f5278e.setColor(Color.argb(212, 0, 0, 0));
        this.f5278e.setStyle(Style.FILL_AND_STROKE);
        m6998b();
    }

    private void m6990c() {
        int i;
        StringBuilder stringBuilder = new StringBuilder();
        if (this.f5279f <= 0) {
            if (!TextUtils.isEmpty(this.f5284k)) {
                stringBuilder.append(this.f5284k);
                stringBuilder.append("\n");
            }
            if (!TextUtils.isEmpty(this.f5285l)) {
                stringBuilder.append(this.f5285l);
                stringBuilder.append("\n");
            }
            stringBuilder.append("Sdk ");
            stringBuilder.append("4.28.1");
            stringBuilder.append(", Loaded ");
            if (this.f5286m > 0) {
                long max = Math.max(0, System.currentTimeMillis() - this.f5286m);
                i = (int) (max / 3600000);
                max %= 3600000;
                int i2 = (int) (max / 60000);
                int i3 = (int) ((max % 60000) / 1000);
                if (i > 0) {
                    stringBuilder.append(i);
                    stringBuilder.append("h ");
                }
                if (i > 0 || i2 > 0) {
                    stringBuilder.append(i2);
                    stringBuilder.append("m ");
                }
                stringBuilder.append(i3);
                stringBuilder.append("s ago");
            } else {
                stringBuilder.append(Card.UNKNOWN);
            }
        } else {
            stringBuilder.append("Card ");
            stringBuilder.append(this.f5280g + 1);
            stringBuilder.append(" of ");
            stringBuilder.append(this.f5279f);
        }
        stringBuilder.append("\nView: ");
        if (this.f5288o == null || this.f5288o.get() == null) {
            stringBuilder.append("Viewability Checker not set");
        } else {
            stringBuilder.append(((C2154a) this.f5288o.get()).m6922c());
        }
        this.f5281h = stringBuilder.toString();
        float f = -2.14748365E9f;
        for (String str : this.f5281h.split("\n")) {
            f = Math.max(f, this.f5277d.measureText(str, 0, str.length()));
        }
        this.f5282i = (int) (0.5f + f);
        invalidateSelf();
    }

    public void m6992a(int i, int i2) {
        this.f5279f = i;
        this.f5280g = i2;
        m6990c();
    }

    public void m6993a(long j) {
        this.f5286m = j;
        m6990c();
    }

    public void m6994a(C2154a c2154a) {
        this.f5288o = new WeakReference(c2154a);
        m6990c();
    }

    public void m6995a(String str) {
        this.f5284k = str;
        m6990c();
    }

    public void m6996a(boolean z) {
        this.f5283j = z;
        if (this.f5283j) {
            this.f5287n.post(this.f5289p);
        } else {
            this.f5287n.removeCallbacks(this.f5289p);
        }
        invalidateSelf();
    }

    public boolean m6997a() {
        return this.f5283j;
    }

    public void m6998b() {
        this.f5279f = 0;
        this.f5280g = -1;
        this.f5281h = "Initializing...";
        this.f5282i = 100;
        this.f5284k = null;
        this.f5286m = -1;
        this.f5288o = null;
        m6996a(false);
    }

    public void m6999b(String str) {
        this.f5285l = str;
        m6990c();
    }

    public void draw(Canvas canvas) {
        if (this.f5283j) {
            int width = canvas.getWidth();
            int height = canvas.getHeight();
            canvas.drawRect(0.0f, 0.0f, (float) width, (float) height, this.f5274a);
            StaticLayout staticLayout = new StaticLayout(this.f5281h, this.f5277d, this.f5282i, Alignment.ALIGN_CENTER, 1.0f, 0.0f, false);
            float f = ((float) width) / 2.0f;
            float f2 = ((float) height) / 2.0f;
            float width2 = ((float) staticLayout.getWidth()) / 2.0f;
            float height2 = ((float) staticLayout.getHeight()) / 2.0f;
            canvas.drawRect((f - width2) - 40.0f, (f2 - height2) - 40.0f, 40.0f + (f + width2), 40.0f + (f2 + height2), this.f5278e);
            canvas.save();
            canvas.translate(f - width2, f2 - height2);
            staticLayout.draw(canvas);
            canvas.restore();
            this.f5276c.reset();
            this.f5276c.moveTo(0.0f, 0.0f);
            this.f5276c.lineTo((float) width, 0.0f);
            this.f5276c.lineTo((float) width, (float) height);
            this.f5276c.lineTo(0.0f, (float) height);
            this.f5276c.lineTo(0.0f, 0.0f);
            canvas.drawPath(this.f5276c, this.f5275b);
        }
    }

    public int getOpacity() {
        return -3;
    }

    public void setAlpha(@IntRange(from = 0, to = 255) int i) {
    }

    public void setColorFilter(@Nullable ColorFilter colorFilter) {
    }
}
