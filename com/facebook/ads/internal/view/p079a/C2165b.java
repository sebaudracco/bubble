package com.facebook.ads.internal.view.p079a;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.util.AttributeSet;
import android.widget.ProgressBar;

@TargetApi(19)
public class C2165b extends ProgressBar {
    private static final int f5208a = Color.argb(26, 0, 0, 0);
    private static final int f5209b = Color.rgb(88, 144, 255);
    private Rect f5210c;
    private Paint f5211d;

    public C2165b(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m6938a();
    }

    private void m6938a() {
        setIndeterminate(false);
        setMax(100);
        setProgressDrawable(m6939b());
        this.f5210c = new Rect();
        this.f5211d = new Paint();
        this.f5211d.setStyle(Style.FILL);
        this.f5211d.setColor(f5208a);
    }

    private Drawable m6939b() {
        ColorDrawable colorDrawable = new ColorDrawable(0);
        ClipDrawable clipDrawable = new ClipDrawable(new ColorDrawable(f5209b), 3, 1);
        return new LayerDrawable(new Drawable[]{colorDrawable, clipDrawable});
    }

    protected synchronized void onDraw(Canvas canvas) {
        canvas.drawRect(this.f5210c, this.f5211d);
        super.onDraw(canvas);
    }

    protected synchronized void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.f5210c.set(0, 0, getMeasuredWidth(), 2);
    }

    public synchronized void setProgress(int i) {
        super.setProgress(i == 100 ? 0 : Math.max(i, 5));
    }
}
