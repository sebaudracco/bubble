package com.vungle.publisher;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.ShapeDrawable;
import android.view.View.MeasureSpec;

/* compiled from: vungle */
public class oj extends oa {
    private ShapeDrawable f10861a;
    private int f10862b;
    private int f10863c;
    private int f10864d;
    private int f10865e;

    private oj(Context context) {
        super(context);
        this.f10861a = new ShapeDrawable();
        this.f10862b = -1;
        this.f10861a.getPaint().setColor(-13659954);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.f10861a.draw(canvas);
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        this.f10862b = MeasureSpec.getSize(widthMeasureSpec);
    }

    private void setProgressBarWidth(float percent) {
        this.f10861a.setBounds(0, 0, (int) (((float) this.f10862b) * percent), this.f10864d);
    }

    public void setMaxTimeMillis(int maxTimeMillis) {
        this.f10863c = maxTimeMillis;
    }

    public void setCurrentTimeMillis(int currentTimeMillis) {
        setProgressBarWidth(((float) currentTimeMillis) / ((float) this.f10863c));
        invalidate();
    }

    public int getProgressBarHeight() {
        return this.f10864d;
    }

    public int getId() {
        return this.f10865e;
    }
}
