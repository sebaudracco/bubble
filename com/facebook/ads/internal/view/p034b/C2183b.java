package com.facebook.ads.internal.view.p034b;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import com.facebook.ads.internal.p056q.p057a.C2118i;

public class C2183b extends LinearLayout {
    private final ImageView f5258a;
    private final ImageView f5259b;
    private final ImageView f5260c;
    private Bitmap f5261d;
    private Bitmap f5262e;
    private Bitmap f5263f;
    private Bitmap f5264g;
    private int f5265h;
    private int f5266i;
    private int f5267j;
    private int f5268k;
    private int f5269l;
    private int f5270m;
    private double f5271n;
    private double f5272o;

    public C2183b(Context context) {
        super(context);
        this.f5258a = new ImageView(context);
        this.f5259b = new ImageView(context);
        this.f5260c = new ImageView(context);
        m6981a();
    }

    public C2183b(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f5258a = new ImageView(context, attributeSet);
        this.f5259b = new ImageView(context, attributeSet);
        this.f5260c = new ImageView(context, attributeSet);
        m6981a();
    }

    public C2183b(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f5258a = new ImageView(context, attributeSet, i);
        this.f5259b = new ImageView(context, attributeSet, i);
        this.f5260c = new ImageView(context, attributeSet, i);
        m6981a();
    }

    @TargetApi(21)
    public C2183b(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.f5258a = new ImageView(context, attributeSet, i, i2);
        this.f5259b = new ImageView(context, attributeSet, i, i2);
        this.f5260c = new ImageView(context, attributeSet, i, i2);
        m6981a();
    }

    private void m6981a() {
        setOrientation(1);
        this.f5258a.setScaleType(ScaleType.FIT_XY);
        addView(this.f5258a);
        this.f5259b.setLayoutParams(new LayoutParams(-1, -1));
        this.f5259b.setScaleType(ScaleType.FIT_XY);
        addView(this.f5259b);
        this.f5260c.setScaleType(ScaleType.FIT_XY);
        addView(this.f5260c);
        C2118i.m6796a(this.f5258a, C2118i.INTERNAL_AD_MEDIA);
        C2118i.m6796a(this.f5259b, C2118i.INTERNAL_AD_MEDIA);
        C2118i.m6796a(this.f5260c, C2118i.INTERNAL_AD_MEDIA);
    }

    private void m6982b() {
        if (getHeight() > 0 && getWidth() > 0) {
            this.f5272o = ((double) getMeasuredWidth()) / ((double) getMeasuredHeight());
            this.f5271n = ((double) this.f5261d.getWidth()) / ((double) this.f5261d.getHeight());
            if (this.f5271n > this.f5272o) {
                m6983c();
            } else {
                m6984d();
            }
        }
    }

    private void m6983c() {
        this.f5267j = (int) Math.round(((double) getWidth()) / this.f5271n);
        this.f5268k = getWidth();
        this.f5265h = (int) Math.ceil((double) (((float) (getHeight() - this.f5267j)) / 2.0f));
        if (this.f5262e != null) {
            Matrix matrix = new Matrix();
            matrix.preScale(1.0f, -1.0f);
            this.f5266i = (int) Math.floor((double) (((float) (getHeight() - this.f5267j)) / 2.0f));
            float height = ((float) this.f5261d.getHeight()) / ((float) this.f5267j);
            int min = Math.min(Math.round(((float) this.f5265h) * height), this.f5262e.getHeight());
            if (min > 0) {
                this.f5263f = Bitmap.createBitmap(this.f5262e, 0, 0, this.f5262e.getWidth(), min, matrix, true);
                this.f5258a.setImageBitmap(this.f5263f);
            }
            min = Math.min(Math.round(((float) this.f5266i) * height), this.f5262e.getHeight());
            if (min > 0) {
                this.f5264g = Bitmap.createBitmap(this.f5262e, 0, this.f5262e.getHeight() - min, this.f5262e.getWidth(), min, matrix, true);
                this.f5260c.setImageBitmap(this.f5264g);
            }
        }
    }

    private void m6984d() {
        this.f5268k = (int) Math.round(((double) getHeight()) * this.f5271n);
        this.f5267j = getHeight();
        this.f5269l = (int) Math.ceil((double) (((float) (getWidth() - this.f5268k)) / 2.0f));
        if (this.f5262e != null) {
            Matrix matrix = new Matrix();
            matrix.preScale(-1.0f, 1.0f);
            this.f5270m = (int) Math.floor((double) (((float) (getWidth() - this.f5268k)) / 2.0f));
            float width = ((float) this.f5261d.getWidth()) / ((float) this.f5268k);
            int min = Math.min(Math.round(((float) this.f5269l) * width), this.f5262e.getWidth());
            if (min > 0) {
                this.f5263f = Bitmap.createBitmap(this.f5262e, 0, 0, min, this.f5262e.getHeight(), matrix, true);
                this.f5258a.setImageBitmap(this.f5263f);
            }
            int min2 = Math.min(Math.round(((float) this.f5270m) * width), this.f5262e.getWidth());
            if (min2 > 0) {
                this.f5264g = Bitmap.createBitmap(this.f5262e, this.f5262e.getWidth() - min2, 0, min2, this.f5262e.getHeight(), matrix, true);
                this.f5260c.setImageBitmap(this.f5264g);
            }
        }
    }

    private boolean m6985e() {
        return ((this.f5265h + this.f5267j) + this.f5266i == getMeasuredHeight() && (this.f5269l + this.f5268k) + this.f5270m == getMeasuredWidth()) ? false : true;
    }

    public void m6986a(Bitmap bitmap, Bitmap bitmap2) {
        if (bitmap2 == null) {
            this.f5258a.setImageDrawable(null);
            this.f5260c.setImageDrawable(null);
        }
        if (bitmap == null) {
            this.f5259b.setImageDrawable(null);
            return;
        }
        this.f5259b.setImageBitmap(Bitmap.createBitmap(bitmap));
        this.f5261d = bitmap;
        this.f5262e = bitmap2;
        m6982b();
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        if (this.f5261d == null) {
            super.onLayout(z, i, i2, i3, i4);
            return;
        }
        if (this.f5263f == null || m6985e()) {
            m6982b();
        }
        if (this.f5271n > this.f5272o) {
            this.f5258a.layout(i, i2, i3, this.f5265h);
            this.f5259b.layout(i, this.f5265h + i2, i3, this.f5265h + this.f5267j);
            this.f5260c.layout(i, (this.f5265h + i2) + this.f5267j, i3, i4);
            return;
        }
        this.f5258a.layout(i, i2, this.f5269l, i4);
        this.f5259b.layout(this.f5269l + i, i2, this.f5269l + this.f5268k, i4);
        this.f5260c.layout((this.f5269l + i) + this.f5268k, i2, i3, i4);
    }
}
