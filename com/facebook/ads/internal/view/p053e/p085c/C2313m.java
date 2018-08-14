package com.facebook.ads.internal.view.p053e.p085c;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build.VERSION;
import android.widget.Button;

public class C2313m extends Button {
    private final Path f5592a;
    private final Path f5593b;
    private final Paint f5594c;
    private final Path f5595d;
    private boolean f5596e;

    public C2313m(Context context) {
        this(context, false);
    }

    public C2313m(Context context, final boolean z) {
        super(context);
        this.f5596e = false;
        this.f5592a = new Path();
        this.f5593b = new Path();
        this.f5595d = new Path();
        this.f5594c = new Paint(this) {
            final /* synthetic */ C2313m f5591b;
        };
        setClickable(true);
        setBackgroundColor(0);
    }

    protected void onDraw(Canvas canvas) {
        if (canvas.isHardwareAccelerated() && VERSION.SDK_INT < 17) {
            setLayerType(1, null);
        }
        float max = ((float) Math.max(canvas.getWidth(), canvas.getHeight())) / 100.0f;
        if (this.f5596e) {
            this.f5595d.rewind();
            this.f5595d.moveTo(26.5f * max, 15.5f * max);
            this.f5595d.lineTo(26.5f * max, 84.5f * max);
            this.f5595d.lineTo(90.0f * max, 50.0f * max);
            this.f5595d.lineTo(26.5f * max, max * 15.5f);
            this.f5595d.close();
            canvas.drawPath(this.f5595d, this.f5594c);
        } else {
            this.f5592a.rewind();
            this.f5592a.moveTo(29.0f * max, 21.0f * max);
            this.f5592a.lineTo(29.0f * max, 79.0f * max);
            this.f5592a.lineTo(45.0f * max, 79.0f * max);
            this.f5592a.lineTo(45.0f * max, 21.0f * max);
            this.f5592a.lineTo(29.0f * max, 21.0f * max);
            this.f5592a.close();
            this.f5593b.rewind();
            this.f5593b.moveTo(55.0f * max, 21.0f * max);
            this.f5593b.lineTo(55.0f * max, 79.0f * max);
            this.f5593b.lineTo(71.0f * max, 79.0f * max);
            this.f5593b.lineTo(71.0f * max, 21.0f * max);
            this.f5593b.lineTo(55.0f * max, max * 21.0f);
            this.f5593b.close();
            canvas.drawPath(this.f5592a, this.f5594c);
            canvas.drawPath(this.f5593b, this.f5594c);
        }
        super.onDraw(canvas);
    }

    public void setChecked(boolean z) {
        this.f5596e = z;
        refreshDrawableState();
        invalidate();
    }
}
