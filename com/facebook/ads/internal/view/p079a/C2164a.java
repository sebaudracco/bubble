package com.facebook.ads.internal.view.p079a;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffColorFilter;
import android.net.Uri;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import com.facebook.ads.internal.p056q.p075b.C2136b;
import com.facebook.ads.internal.p056q.p075b.C2137c;
import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;
import java.util.List;

@TargetApi(19)
public class C2164a extends LinearLayout {
    private static final int f5199a = Color.rgb(224, 224, 224);
    private static final Uri f5200b = Uri.parse("http://www.facebook.com");
    private static final OnTouchListener f5201c = new C21601();
    private static final int f5202d = Color.argb(34, 0, 0, 0);
    private ImageView f5203e;
    private C2170e f5204f;
    private ImageView f5205g;
    private C2163a f5206h;
    private String f5207i;

    static class C21601 implements OnTouchListener {
        C21601() {
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            switch (motionEvent.getAction()) {
                case 0:
                    view.setBackgroundColor(C2164a.f5202d);
                    break;
                case 1:
                    view.setBackgroundColor(0);
                    break;
            }
            return false;
        }
    }

    class C21612 implements OnClickListener {
        final /* synthetic */ C2164a f5197a;

        C21612(C2164a c2164a) {
            this.f5197a = c2164a;
        }

        public void onClick(View view) {
            if (this.f5197a.f5206h != null) {
                this.f5197a.f5206h.mo3765a();
            }
        }
    }

    class C21623 implements OnClickListener {
        final /* synthetic */ C2164a f5198a;

        C21623(C2164a c2164a) {
            this.f5198a = c2164a;
        }

        public void onClick(View view) {
            if (!TextUtils.isEmpty(this.f5198a.f5207i) && !"about:blank".equals(this.f5198a.f5207i)) {
                Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(this.f5198a.f5207i));
                intent.addFlags(ErrorDialogData.BINDER_CRASH);
                this.f5198a.getContext().startActivity(intent);
            }
        }
    }

    public interface C2163a {
        void mo3765a();
    }

    public C2164a(Context context) {
        super(context);
        m6936a(context);
    }

    private void m6936a(Context context) {
        float f = getResources().getDisplayMetrics().density;
        int i = (int) (50.0f * f);
        int i2 = (int) (f * 4.0f);
        setBackgroundColor(-1);
        setGravity(16);
        this.f5203e = new ImageView(context);
        LayoutParams layoutParams = new LinearLayout.LayoutParams(i, i);
        this.f5203e.setScaleType(ScaleType.CENTER);
        this.f5203e.setImageBitmap(C2137c.m6843a(C2136b.BROWSER_CLOSE));
        this.f5203e.setOnTouchListener(f5201c);
        this.f5203e.setOnClickListener(new C21612(this));
        addView(this.f5203e, layoutParams);
        this.f5204f = new C2170e(context);
        layoutParams = new LinearLayout.LayoutParams(0, -2);
        layoutParams.weight = 1.0f;
        this.f5204f.setPadding(0, i2, 0, i2);
        addView(this.f5204f, layoutParams);
        this.f5205g = new ImageView(context);
        LayoutParams layoutParams2 = new LinearLayout.LayoutParams(i, i);
        this.f5205g.setScaleType(ScaleType.CENTER);
        this.f5205g.setOnTouchListener(f5201c);
        this.f5205g.setOnClickListener(new C21623(this));
        addView(this.f5205g, layoutParams2);
        setupDefaultNativeBrowser(context);
    }

    private void setupDefaultNativeBrowser(Context context) {
        Bitmap bitmap;
        List queryIntentActivities = context.getPackageManager().queryIntentActivities(new Intent("android.intent.action.VIEW", f5200b), 65536);
        if (queryIntentActivities.size() == 0) {
            this.f5205g.setVisibility(8);
            bitmap = null;
        } else {
            bitmap = (queryIntentActivities.size() == 1 && "com.android.chrome".equals(((ResolveInfo) queryIntentActivities.get(0)).activityInfo.packageName)) ? C2137c.m6843a(C2136b.BROWSER_LAUNCH_CHROME) : C2137c.m6843a(C2136b.BROWSER_LAUNCH_NATIVE);
        }
        this.f5205g.setImageBitmap(bitmap);
    }

    public void setListener(C2163a c2163a) {
        this.f5206h = c2163a;
    }

    public void setTitle(String str) {
        this.f5204f.setTitle(str);
    }

    public void setUrl(String str) {
        this.f5207i = str;
        if (TextUtils.isEmpty(str) || "about:blank".equals(str)) {
            this.f5204f.setSubtitle(null);
            this.f5205g.setEnabled(false);
            this.f5205g.setColorFilter(new PorterDuffColorFilter(f5199a, Mode.SRC_IN));
            return;
        }
        this.f5204f.setSubtitle(str);
        this.f5205g.setEnabled(true);
        this.f5205g.setColorFilter(null);
    }
}
