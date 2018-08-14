package com.mopub.mobileads.resource;

import android.graphics.Canvas;
import android.graphics.Paint;
import com.mopub.mobileads.resource.DrawableConstants.CloseButton;

public class CloseButtonDrawable extends BaseWidgetDrawable {
    private final Paint closeButtonPaint;
    private final float halfStrokeWidth;

    public CloseButtonDrawable() {
        this(8.0f);
    }

    public CloseButtonDrawable(float strokeWidth) {
        this.halfStrokeWidth = strokeWidth / 2.0f;
        this.closeButtonPaint = new Paint();
        this.closeButtonPaint.setColor(-1);
        this.closeButtonPaint.setStrokeWidth(strokeWidth);
        this.closeButtonPaint.setStrokeCap(CloseButton.STROKE_CAP);
    }

    public void draw(Canvas canvas) {
        int w = getBounds().width();
        int h = getBounds().height();
        canvas.drawLine(0.0f + this.halfStrokeWidth, ((float) h) - this.halfStrokeWidth, ((float) w) - this.halfStrokeWidth, 0.0f + this.halfStrokeWidth, this.closeButtonPaint);
        canvas.drawLine(0.0f + this.halfStrokeWidth, 0.0f + this.halfStrokeWidth, ((float) w) - this.halfStrokeWidth, ((float) h) - this.halfStrokeWidth, this.closeButtonPaint);
    }
}
