package com.inmobi.ads;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.inmobi.commons.core.utilities.info.DisplayInfo;
import com.mopub.mobileads.dfp.adapters.MoPubAdapter;
import java.util.concurrent.TimeUnit;

public class NativeStrandTimerView extends View {
    private Bitmap f6786a;
    private Canvas f6787b;
    private RectF f6788c;
    private RectF f6789d;
    private Rect f6790e;
    private long f6791f;
    private Paint f6792g;
    private Paint f6793h;
    private Paint f6794i;
    private Paint f6795j;
    private Paint f6796k;
    private float f6797l;
    private long f6798m;
    private ValueAnimator f6799n;
    private C2936a f6800o;

    class C29351 implements AnimatorUpdateListener {
        final /* synthetic */ NativeStrandTimerView f6785a;

        C29351(NativeStrandTimerView nativeStrandTimerView) {
            this.f6785a = nativeStrandTimerView;
        }

        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            this.f6785a.m8859a(((Float) valueAnimator.getAnimatedValue()).floatValue());
        }
    }

    interface C2936a {
        void mo6168a();
    }

    public NativeStrandTimerView(Context context) {
        this(context, null);
    }

    public NativeStrandTimerView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public NativeStrandTimerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f6791f = 0;
        int parseColor = Color.parseColor("#F4F4F4");
        this.f6792g = new Paint();
        this.f6792g.setAntiAlias(true);
        this.f6792g.setColor(parseColor);
        this.f6796k = new Paint();
        this.f6796k.setAntiAlias(true);
        this.f6796k.setColor(-16777216);
        this.f6796k.setTextAlign(Align.CENTER);
        this.f6796k.setAntiAlias(true);
        this.f6790e = new Rect();
        this.f6793h = new Paint();
        this.f6793h.setAntiAlias(true);
        this.f6793h.setColor(-16777216);
        this.f6794i = new Paint();
        this.f6794i.setAntiAlias(true);
        this.f6794i.setColor(0);
        this.f6794i.setXfermode(new PorterDuffXfermode(Mode.CLEAR));
        this.f6795j = new Paint();
        this.f6795j.setStyle(Style.STROKE);
        this.f6795j.setAntiAlias(true);
        this.f6795j.setColor(-16777216);
    }

    public void setTimerEventsListener(C2936a c2936a) {
        this.f6800o = c2936a;
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i);
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        if (!(i == i3 && i2 == i4)) {
            this.f6786a = Bitmap.createBitmap(i, i2, Config.ARGB_8888);
            this.f6786a.eraseColor(0);
            this.f6787b = new Canvas(this.f6786a);
        }
        super.onSizeChanged(i, i2, i3, i4);
        m8861e();
    }

    protected void onDraw(Canvas canvas) {
        this.f6787b.drawColor(0, Mode.CLEAR);
        int width = getWidth() / 2;
        int height = getHeight() / 2;
        int min = Math.min(width, height);
        int a = DisplayInfo.m10419a((int) ((7.0f * ((float) getWidth())) * 0.007f));
        canvas.drawCircle((float) width, (float) height, (float) min, this.f6792g);
        canvas.drawCircle((float) width, (float) height, (float) (min - a), this.f6795j);
        if (this.f6799n != null) {
            height = (int) (this.f6791f - (this.f6799n.getCurrentPlayTime() / 1000));
            if (((double) ((Float) this.f6799n.getAnimatedValue()).floatValue()) >= MoPubAdapter.DEFAULT_MOPUB_IMAGE_SCALE) {
                width = 0;
            } else {
                width = height;
            }
            m8863a(canvas, this.f6796k, this.f6790e, String.valueOf(width));
            if (((double) ((Float) this.f6799n.getAnimatedValue()).floatValue()) >= MoPubAdapter.DEFAULT_MOPUB_IMAGE_SCALE && this.f6800o != null) {
                this.f6800o.mo6168a();
                this.f6799n.cancel();
                this.f6799n = null;
            }
        }
        if (this.f6797l > 0.0f) {
            this.f6787b.drawArc(this.f6788c, BitmapDescriptorFactory.HUE_VIOLET, this.f6797l, true, this.f6793h);
            this.f6787b.drawOval(this.f6789d, this.f6794i);
        }
        canvas.drawBitmap(this.f6786a, 0.0f, 0.0f, null);
    }

    public void setTimerValue(long j) {
        this.f6791f = j;
    }

    public void m8862a() {
        this.f6799n = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        this.f6799n.setDuration(TimeUnit.SECONDS.toMillis(this.f6791f));
        this.f6799n.setInterpolator(new LinearInterpolator());
        this.f6799n.addUpdateListener(new C29351(this));
        this.f6799n.start();
    }

    public void m8864b() {
        if (this.f6799n != null && this.f6799n.isRunning()) {
            this.f6799n.setCurrentPlayTime(this.f6791f * 1000);
            m8859a(1.0f);
        }
    }

    public void m8865c() {
        if (this.f6799n != null && this.f6799n.isRunning()) {
            this.f6798m = this.f6799n.getCurrentPlayTime();
            this.f6799n.cancel();
        }
    }

    public void m8866d() {
        if (this.f6799n != null && !this.f6799n.isRunning()) {
            this.f6799n.setCurrentPlayTime(this.f6798m);
            this.f6799n.start();
        }
    }

    private void m8859a(float f) {
        this.f6797l = 360.0f * f;
        invalidate();
    }

    private void m8861e() {
        float a = (float) DisplayInfo.m10419a((int) ((4.0f * ((float) getWidth())) * 0.007f));
        float a2 = (float) DisplayInfo.m10419a((int) ((14.0f * ((float) getWidth())) * 0.007f));
        float a3 = (float) DisplayInfo.m10419a((int) ((5.0f * ((float) getWidth())) * 0.007f));
        float a4 = (float) DisplayInfo.m10419a((int) ((1.5f * ((float) getWidth())) * 0.007f));
        this.f6788c = new RectF(a3, a3, ((float) getWidth()) - a3, ((float) getHeight()) - a3);
        this.f6789d = new RectF(this.f6788c.left + a, this.f6788c.top + a, this.f6788c.right - a, this.f6788c.bottom - a);
        this.f6795j.setStrokeWidth(a4);
        this.f6796k.setTextSize(a2);
        invalidate();
    }

    protected void m8863a(@NonNull Canvas canvas, @NonNull Paint paint, @NonNull Rect rect, @NonNull String str) {
        paint.getTextBounds(str, 0, str.length(), rect);
        canvas.drawText(str, (float) (getWidth() / 2), (((paint.descent() - paint.ascent()) / 2.0f) - paint.descent()) + ((float) (getHeight() / 2)), paint);
    }
}
