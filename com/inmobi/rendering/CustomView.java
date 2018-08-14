package com.inmobi.rendering;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Path.FillType;
import android.graphics.RectF;
import android.view.View;
import com.cuebiq.cuebiqsdk.model.config.Settings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.mopub.mobileads.resource.DrawableConstants.CloseButton;
import com.mopub.mobileads.resource.DrawableConstants.CtaButton;

public class CustomView extends View {
    private float f7842a;
    private float f7843b;
    private float f7844c;
    private float f7845d;
    private float f7846e;
    private SwitchIconType f7847f;
    private int f7848g;
    private Paint f7849h;
    private Path f7850i;
    private RectF f7851j;

    public enum SwitchIconType {
        CLOSE_BUTTON,
        CLOSE_TRANSPARENT,
        CLOSE_ICON,
        REFRESH,
        BACK,
        FORWARD_ACTIVE,
        FORWARD_INACTIVE,
        PLAY_BUTTON,
        PAUSE_BUTTON,
        MUTE_BUTTON,
        UNMUTE_BUTTON,
        MINIMIZE_BUTTON
    }

    private CustomView(Context context) {
        super(context);
    }

    public CustomView(Context context, float f, SwitchIconType switchIconType) {
        this(context);
        this.f7847f = switchIconType;
        this.f7842a = f;
        this.f7848g = 15;
        this.f7849h = new Paint(1);
        this.f7851j = new RectF();
        this.f7850i = new Path();
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.f7849h.reset();
        Canvas canvas2;
        float f;
        float f2;
        switch (this.f7847f) {
            case MINIMIZE_BUTTON:
                this.f7845d = (50.0f * this.f7842a) / 2.0f;
                this.f7843b = this.f7842a * 3.0f;
                this.f7844c = this.f7842a * 3.0f;
                this.f7849h.setStyle(Style.STROKE);
                this.f7849h.setStrokeWidth(4.0f);
                this.f7849h.setColor(-1);
                this.f7850i.moveTo(this.f7845d - this.f7843b, (this.f7845d - this.f7844c) - (this.f7842a * 5.0f));
                this.f7850i.lineTo(this.f7845d - this.f7843b, this.f7845d - this.f7844c);
                this.f7850i.lineTo((this.f7845d - this.f7843b) - (this.f7842a * 5.0f), this.f7845d - this.f7844c);
                this.f7850i.moveTo(this.f7845d + this.f7843b, (this.f7845d - this.f7844c) - (this.f7842a * 5.0f));
                this.f7850i.lineTo(this.f7845d + this.f7843b, this.f7845d - this.f7844c);
                this.f7850i.lineTo((this.f7845d + this.f7843b) + (this.f7842a * 5.0f), this.f7845d - this.f7844c);
                this.f7850i.moveTo(this.f7845d - this.f7843b, (this.f7845d + this.f7844c) + (this.f7842a * 5.0f));
                this.f7850i.lineTo(this.f7845d - this.f7843b, this.f7845d + this.f7844c);
                this.f7850i.lineTo((this.f7845d - this.f7843b) - (this.f7842a * 5.0f), this.f7845d + this.f7844c);
                this.f7850i.moveTo(this.f7845d + this.f7843b, (this.f7845d + this.f7844c) + (this.f7842a * 5.0f));
                this.f7850i.lineTo(this.f7845d + this.f7843b, this.f7845d + this.f7844c);
                this.f7850i.lineTo((this.f7845d + this.f7843b) + (this.f7842a * 5.0f), this.f7845d + this.f7844c);
                canvas.drawPath(this.f7850i, this.f7849h);
                return;
            case UNMUTE_BUTTON:
                m10541a(canvas);
                this.f7849h.setColor(-1);
                this.f7849h.setStrokeWidth(4.0f);
                this.f7849h.setStyle(Style.STROKE);
                this.f7850i.moveTo(this.f7845d + (12.0f * this.f7842a), this.f7845d - this.f7844c);
                this.f7850i.lineTo(this.f7845d + (CloseButton.TEXT_SIZE_SP * this.f7842a), this.f7845d + this.f7844c);
                this.f7850i.moveTo(this.f7845d + (CloseButton.TEXT_SIZE_SP * this.f7842a), this.f7845d - this.f7844c);
                this.f7850i.lineTo(this.f7845d + (12.0f * this.f7842a), this.f7845d + this.f7844c);
                canvas.drawPath(this.f7850i, this.f7849h);
                return;
            case MUTE_BUTTON:
                m10541a(canvas);
                m10542b(canvas);
                canvas.drawPath(this.f7850i, this.f7849h);
                return;
            case PAUSE_BUTTON:
                m10543c(canvas);
                this.f7843b = this.f7846e / 4.0f;
                this.f7844c = this.f7846e / 3.0f;
                canvas2 = canvas;
                canvas2.drawLine(this.f7845d - this.f7843b, this.f7845d - this.f7844c, this.f7845d - this.f7843b, this.f7844c + this.f7845d, this.f7849h);
                canvas2 = canvas;
                canvas2.drawLine(this.f7843b + this.f7845d, this.f7845d - this.f7844c, this.f7843b + this.f7845d, this.f7844c + this.f7845d, this.f7849h);
                return;
            case PLAY_BUTTON:
                m10543c(canvas);
                this.f7843b = this.f7846e / 3.0f;
                this.f7844c = this.f7846e / 3.0f;
                this.f7849h.setStyle(Style.FILL);
                this.f7850i.moveTo(this.f7845d + this.f7843b, this.f7845d);
                this.f7850i.lineTo(this.f7845d - this.f7843b, this.f7845d - this.f7844c);
                this.f7850i.lineTo(this.f7845d - this.f7843b, this.f7845d + this.f7844c);
                this.f7850i.lineTo(this.f7845d + this.f7843b, this.f7845d);
                canvas.drawPath(this.f7850i, this.f7849h);
                return;
            case CLOSE_BUTTON:
                float f3 = (50.0f * this.f7842a) / 2.0f;
                float f4 = (BitmapDescriptorFactory.HUE_ORANGE * this.f7842a) / 2.0f;
                f = f3 - (f4 / 3.0f);
                float f5 = f3 + (f4 / 3.0f);
                this.f7849h.setAntiAlias(true);
                this.f7849h.setColor(-16777216);
                this.f7849h.setStrokeWidth(3.0f);
                this.f7849h.setStyle(Style.FILL_AND_STROKE);
                canvas.drawCircle(f3, f3, f4, this.f7849h);
                this.f7849h.setColor(-1);
                this.f7849h.setStyle(Style.STROKE);
                canvas.drawLine(f, f, f5, f5, this.f7849h);
                canvas.drawLine(f, f5, f5, f, this.f7849h);
                canvas.drawCircle(f3, f3, f4, this.f7849h);
                return;
            case REFRESH:
                f2 = (50.0f * this.f7842a) / 2.0f;
                f = (BitmapDescriptorFactory.HUE_ORANGE * this.f7842a) / 2.0f;
                this.f7850i.reset();
                this.f7849h.setAntiAlias(true);
                this.f7849h.setColor(-16777216);
                this.f7849h.setStrokeWidth(3.0f);
                this.f7849h.setStyle(Style.FILL_AND_STROKE);
                canvas.drawCircle(f2, f2, f, this.f7849h);
                this.f7849h.setColor(-1);
                this.f7849h.setStyle(Style.STROKE);
                canvas.drawCircle(f2, f2, f, this.f7849h);
                this.f7851j.set(((float) (getWidth() / 2)) - ((((float) this.f7848g) * this.f7842a) / 2.0f), ((float) (getHeight() / 2)) - ((((float) this.f7848g) * this.f7842a) / 2.0f), ((float) (getWidth() / 2)) + ((((float) this.f7848g) * this.f7842a) / 2.0f), ((float) (getHeight() / 2)) + ((((float) this.f7848g) * this.f7842a) / 2.0f));
                canvas.drawArc(this.f7851j, 0.0f, BitmapDescriptorFactory.HUE_VIOLET, false, this.f7849h);
                this.f7850i.setFillType(FillType.EVEN_ODD);
                this.f7850i.moveTo(((float) (getWidth() / 2)) + ((((float) this.f7848g) * this.f7842a) / 2.0f), ((float) (getHeight() / 2)) - (this.f7842a * 2.0f));
                this.f7850i.lineTo((((float) (getWidth() / 2)) + ((((float) this.f7848g) * this.f7842a) / 2.0f)) - (this.f7842a * 2.0f), (float) (getHeight() / 2));
                this.f7850i.lineTo((((float) (getWidth() / 2)) + ((((float) this.f7848g) * this.f7842a) / 2.0f)) + (this.f7842a * 2.0f), (float) (getHeight() / 2));
                this.f7850i.lineTo(((float) (getWidth() / 2)) + ((((float) this.f7848g) * this.f7842a) / 2.0f), ((float) (getHeight() / 2)) - (this.f7842a * 2.0f));
                this.f7850i.close();
                this.f7849h.setStyle(Style.FILL_AND_STROKE);
                canvas.drawPath(this.f7850i, this.f7849h);
                return;
            case CLOSE_TRANSPARENT:
                f2 = (50.0f * this.f7842a) / 2.0f;
                this.f7849h.setAntiAlias(true);
                this.f7849h.setColor(0);
                this.f7849h.setStrokeWidth(3.0f);
                this.f7849h.setStyle(Style.FILL_AND_STROKE);
                canvas.drawCircle(f2, f2, f2, this.f7849h);
                return;
            case FORWARD_ACTIVE:
                this.f7850i.reset();
                this.f7850i.setFillType(FillType.EVEN_ODD);
                this.f7850i.moveTo(((float) (getWidth() / 2)) - ((((float) this.f7848g) * this.f7842a) / 2.0f), ((float) (getHeight() / 2)) - ((((float) this.f7848g) * this.f7842a) / 2.0f));
                this.f7850i.lineTo(((float) (getWidth() / 2)) + ((((float) this.f7848g) * this.f7842a) / 2.0f), (float) (getHeight() / 2));
                this.f7850i.lineTo(((float) (getWidth() / 2)) - ((((float) this.f7848g) * this.f7842a) / 2.0f), ((float) (getHeight() / 2)) + ((((float) this.f7848g) * this.f7842a) / 2.0f));
                this.f7850i.lineTo(((float) (getWidth() / 2)) - ((((float) this.f7848g) * this.f7842a) / 2.0f), ((float) (getHeight() / 2)) - ((((float) this.f7848g) * this.f7842a) / 2.0f));
                this.f7850i.close();
                this.f7849h.setAntiAlias(true);
                this.f7849h.setColor(-16777216);
                this.f7849h.setStrokeWidth(3.0f);
                this.f7849h.setStyle(Style.FILL_AND_STROKE);
                canvas.drawPath(this.f7850i, this.f7849h);
                return;
            case FORWARD_INACTIVE:
                this.f7850i.reset();
                this.f7850i.setFillType(FillType.EVEN_ODD);
                this.f7850i.moveTo(((float) (getWidth() / 2)) - ((((float) this.f7848g) * this.f7842a) / 2.0f), ((float) (getHeight() / 2)) - ((((float) this.f7848g) * this.f7842a) / 2.0f));
                this.f7850i.lineTo(((float) (getWidth() / 2)) + ((((float) this.f7848g) * this.f7842a) / 2.0f), (float) (getHeight() / 2));
                this.f7850i.lineTo(((float) (getWidth() / 2)) - ((((float) this.f7848g) * this.f7842a) / 2.0f), ((float) (getHeight() / 2)) + ((((float) this.f7848g) * this.f7842a) / 2.0f));
                this.f7850i.lineTo(((float) (getWidth() / 2)) - ((((float) this.f7848g) * this.f7842a) / 2.0f), ((float) (getHeight() / 2)) - ((((float) this.f7848g) * this.f7842a) / 2.0f));
                this.f7850i.close();
                this.f7849h.setAntiAlias(true);
                this.f7849h.setColor(-12303292);
                this.f7849h.setStrokeWidth(3.0f);
                this.f7849h.setStyle(Style.FILL_AND_STROKE);
                canvas.drawPath(this.f7850i, this.f7849h);
                return;
            case BACK:
                this.f7850i.reset();
                this.f7850i.setFillType(FillType.EVEN_ODD);
                this.f7850i.moveTo(((float) (getWidth() / 2)) - ((((float) this.f7848g) * this.f7842a) / 2.0f), (float) (getHeight() / 2));
                this.f7850i.lineTo(((float) (getWidth() / 2)) + ((((float) this.f7848g) * this.f7842a) / 2.0f), ((float) (getHeight() / 2)) - ((((float) this.f7848g) * this.f7842a) / 2.0f));
                this.f7850i.lineTo(((float) (getWidth() / 2)) + ((((float) this.f7848g) * this.f7842a) / 2.0f), ((float) (getHeight() / 2)) + ((((float) this.f7848g) * this.f7842a) / 2.0f));
                this.f7850i.lineTo(((float) (getWidth() / 2)) - ((((float) this.f7848g) * this.f7842a) / 2.0f), (float) (getHeight() / 2));
                this.f7850i.close();
                this.f7849h.setAntiAlias(true);
                this.f7849h.setColor(-16777216);
                this.f7849h.setStrokeWidth(3.0f);
                this.f7849h.setStyle(Style.FILL_AND_STROKE);
                canvas.drawPath(this.f7850i, this.f7849h);
                return;
            case CLOSE_ICON:
                this.f7849h.setAntiAlias(true);
                this.f7849h.setColor(-1);
                this.f7849h.setStrokeWidth(5.0f);
                this.f7849h.setStyle(Style.STROKE);
                canvas2 = canvas;
                canvas2.drawLine(((float) (getWidth() / 2)) - ((((float) this.f7848g) * this.f7842a) / 2.0f), ((float) (getHeight() / 2)) - ((((float) this.f7848g) * this.f7842a) / 2.0f), ((((float) this.f7848g) * this.f7842a) / 2.0f) + ((float) (getWidth() / 2)), ((((float) this.f7848g) * this.f7842a) / 2.0f) + ((float) (getHeight() / 2)), this.f7849h);
                canvas2 = canvas;
                canvas2.drawLine(((float) (getWidth() / 2)) - ((((float) this.f7848g) * this.f7842a) / 2.0f), ((((float) this.f7848g) * this.f7842a) / 2.0f) + ((float) (getHeight() / 2)), ((((float) this.f7848g) * this.f7842a) / 2.0f) + ((float) (getWidth() / 2)), ((float) (getHeight() / 2)) - ((((float) this.f7848g) * this.f7842a) / 2.0f), this.f7849h);
                return;
            default:
                return;
        }
    }

    private void m10541a(Canvas canvas) {
        this.f7845d = (50.0f * this.f7842a) / 2.0f;
        this.f7843b = this.f7842a * 5.0f;
        this.f7844c = this.f7842a * 5.0f;
        this.f7849h.setStyle(Style.FILL);
        this.f7849h.setColor(-1);
        this.f7849h.setStrokeWidth(4.0f);
        this.f7849h.setAntiAlias(true);
        this.f7850i.moveTo(this.f7845d - this.f7843b, this.f7845d - this.f7844c);
        this.f7850i.lineTo(this.f7845d, this.f7845d - this.f7844c);
        this.f7850i.lineTo(this.f7845d + (this.f7842a * 7.0f), (this.f7845d - this.f7844c) - (this.f7842a * 6.0f));
        this.f7850i.lineTo(this.f7845d + (this.f7842a * 7.0f), (this.f7845d + this.f7844c) + (this.f7842a * 6.0f));
        this.f7850i.lineTo(this.f7845d, this.f7845d + this.f7844c);
        this.f7850i.lineTo(this.f7845d - this.f7843b, this.f7845d + this.f7844c);
        this.f7850i.lineTo(this.f7845d - this.f7843b, this.f7845d - this.f7844c);
        canvas.drawPath(this.f7850i, this.f7849h);
    }

    private void m10542b(Canvas canvas) {
        RectF rectF = new RectF(this.f7845d - (this.f7842a * Settings.LOCATION_REQUEST_SMALLEST_DISPLACEMENT), (this.f7845d - this.f7844c) - (this.f7842a * 3.0f), this.f7845d + (CtaButton.TEXT_SIZE_SP * this.f7842a), (this.f7845d + this.f7844c) + (this.f7842a * 3.0f));
        RectF rectF2 = new RectF(this.f7845d - (this.f7842a * Settings.LOCATION_REQUEST_SMALLEST_DISPLACEMENT), (this.f7845d - this.f7844c) - (this.f7842a * 5.0f), this.f7845d + (CloseButton.TEXT_SIZE_SP * this.f7842a), (this.f7845d + this.f7844c) + (this.f7842a * 5.0f));
        this.f7849h.setColor(-1);
        this.f7849h.setStrokeWidth(4.0f);
        this.f7849h.setStyle(Style.STROKE);
        canvas.drawArc(rectF, -45.0f, 90.0f, false, this.f7849h);
        canvas.drawArc(rectF2, -45.0f, 90.0f, false, this.f7849h);
        canvas.drawPath(this.f7850i, this.f7849h);
    }

    private void m10543c(Canvas canvas) {
        this.f7846e = 25.0f * this.f7842a;
        this.f7845d = BitmapDescriptorFactory.HUE_ORANGE * this.f7842a;
        this.f7849h.setAntiAlias(true);
        this.f7849h.setColor(-1);
        this.f7849h.setStrokeWidth(7.0f);
        this.f7849h.setStyle(Style.STROKE);
        canvas.drawCircle(this.f7845d, this.f7845d, this.f7846e, this.f7849h);
    }

    public void setSwitchInt(SwitchIconType switchIconType) {
        this.f7847f = switchIconType;
    }
}
