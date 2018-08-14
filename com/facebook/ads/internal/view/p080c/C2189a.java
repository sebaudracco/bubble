package com.facebook.ads.internal.view.p080c;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.cuebiq.cuebiqsdk.model.config.Settings;
import com.facebook.ads.internal.p059a.C1873a;
import com.facebook.ads.internal.p059a.C1874b;
import com.facebook.ads.internal.p069m.C2012c;
import com.facebook.ads.internal.view.p053e.C2249b;
import com.facebook.ads.internal.view.p053e.p054b.C2228a;
import java.util.HashMap;

public class C2189a extends RelativeLayout {
    private final String f5316a;
    private C2249b f5317b;
    private final Paint f5318c = new Paint();
    private final RectF f5319d;

    public C2189a(Context context, String str, String str2, int i, C2249b c2249b, final C2012c c2012c, final String str3) {
        super(context);
        this.f5316a = str;
        this.f5317b = c2249b;
        View textView = new TextView(context);
        textView.setTextColor(-1);
        textView.setTextSize(16.0f);
        textView.setText(str2);
        textView.setTypeface(Typeface.defaultFromStyle(1));
        setGravity(17);
        addView(textView);
        this.f5318c.setStyle(Style.FILL);
        this.f5318c.setColor(i);
        this.f5319d = new RectF();
        setBackgroundColor(0);
        setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ C2189a f5315c;

            public void onClick(View view) {
                try {
                    Uri parse = Uri.parse(this.f5315c.f5316a);
                    this.f5315c.f5317b.getEventBus().m6327a(new C2228a(parse));
                    C1873a a = C1874b.m5632a(this.f5315c.getContext(), c2012c, str3, parse, new HashMap());
                    if (a != null) {
                        a.mo3622b();
                    }
                } catch (Throwable e) {
                    Log.e(String.valueOf(C2189a.class), "Error while opening " + this.f5315c.f5316a, e);
                } catch (Throwable e2) {
                    Log.e(String.valueOf(C2189a.class), "Error executing action", e2);
                }
            }
        });
    }

    protected void onDraw(Canvas canvas) {
        float f = getContext().getResources().getDisplayMetrics().density;
        this.f5319d.set(0.0f, 0.0f, (float) getWidth(), (float) getHeight());
        canvas.drawRoundRect(this.f5319d, Settings.LOCATION_REQUEST_SMALLEST_DISPLACEMENT * f, f * Settings.LOCATION_REQUEST_SMALLEST_DISPLACEMENT, this.f5318c);
        super.onDraw(canvas);
    }
}
