package com.facebook.ads.internal.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.os.Build.VERSION;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;
import com.facebook.ads.internal.p056q.p057a.C2133v;

public class C2219d extends View {
    private Paint f5407a;
    private Paint f5408b;
    private Paint f5409c;
    private int f5410d;
    private boolean f5411e;

    public C2219d(Context context) {
        this(context, 60, true);
    }

    public C2219d(Context context, int i, boolean z) {
        super(context);
        this.f5410d = i;
        this.f5411e = z;
        if (z) {
            this.f5407a = new Paint();
            this.f5407a.setColor(-3355444);
            this.f5407a.setStyle(Style.STROKE);
            this.f5407a.setStrokeWidth(3.0f);
            this.f5407a.setAntiAlias(true);
            this.f5408b = new Paint();
            this.f5408b.setColor(-1287371708);
            this.f5408b.setStyle(Style.FILL);
            this.f5408b.setAntiAlias(true);
            this.f5409c = new Paint();
            this.f5409c.setColor(-1);
            this.f5409c.setStyle(Style.STROKE);
            this.f5409c.setStrokeWidth(6.0f);
            this.f5409c.setAntiAlias(true);
        }
        m7076a();
    }

    private void m7076a() {
        float f = C2133v.f5083b;
        LayoutParams layoutParams = new RelativeLayout.LayoutParams((int) (((float) this.f5410d) * f), (int) (f * ((float) this.f5410d)));
        layoutParams.addRule(10);
        layoutParams.addRule(11);
        setLayoutParams(layoutParams);
    }

    protected void onDraw(Canvas canvas) {
        if (this.f5411e) {
            if (canvas.isHardwareAccelerated() && VERSION.SDK_INT < 17) {
                setLayerType(1, null);
            }
            int min = Math.min(canvas.getWidth(), canvas.getHeight());
            int i = min / 2;
            int i2 = min / 2;
            int i3 = (i * 2) / 3;
            canvas.drawCircle((float) i, (float) i2, (float) i3, this.f5407a);
            canvas.drawCircle((float) i, (float) i2, (float) (i3 - 2), this.f5408b);
            int i4 = min / 3;
            int i5 = min / 3;
            canvas.drawLine((float) i4, (float) i5, (float) (i4 * 2), (float) (i5 * 2), this.f5409c);
            canvas.drawLine((float) (i4 * 2), (float) i5, (float) i4, (float) (i5 * 2), this.f5409c);
        }
        super.onDraw(canvas);
    }
}
