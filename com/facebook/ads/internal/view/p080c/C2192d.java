package com.facebook.ads.internal.view.p080c;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.RectF;
import android.os.Build.VERSION;
import android.widget.ImageView;

public class C2192d extends ImageView {
    public C2192d(Context context) {
        super(context);
    }

    protected void onDraw(Canvas canvas) {
        if (canvas.isHardwareAccelerated() && VERSION.SDK_INT < 17) {
            setLayerType(1, null);
        }
        Path path = new Path();
        float min = (float) (Math.min(getWidth(), getHeight()) / 2);
        path.addRoundRect(new RectF(0.0f, 0.0f, (float) getWidth(), (float) getHeight()), min, min, Direction.CW);
        canvas.clipPath(path);
        super.onDraw(canvas);
    }
}
