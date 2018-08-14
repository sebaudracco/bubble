package com.facebook.ads.internal.view.p080c;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.cuebiq.cuebiqsdk.model.config.Settings;

public class C2190b extends RelativeLayout {
    private final Paint f5320a = new Paint();
    private final RectF f5321b;

    public C2190b(Context context, String str) {
        super(context);
        float f = context.getResources().getDisplayMetrics().density;
        View textView = new TextView(context);
        textView.setTextColor(-16777216);
        textView.setTextSize(16.0f);
        textView.setText(str);
        textView.setTypeface(Typeface.defaultFromStyle(1));
        setGravity(17);
        int i = (int) (f * 6.0f);
        textView.setPadding(i, i, i, i);
        addView(textView);
        this.f5320a.setStyle(Style.FILL);
        this.f5320a.setColor(-1);
        this.f5321b = new RectF();
        setBackgroundColor(0);
    }

    protected void onDraw(Canvas canvas) {
        float f = getContext().getResources().getDisplayMetrics().density;
        this.f5321b.set(0.0f, 0.0f, (float) getWidth(), (float) getHeight());
        canvas.drawRoundRect(this.f5321b, Settings.LOCATION_REQUEST_SMALLEST_DISPLACEMENT * f, f * Settings.LOCATION_REQUEST_SMALLEST_DISPLACEMENT, this.f5320a);
        super.onDraw(canvas);
    }
}
