package com.facebook.ads.internal.view;

import android.animation.LayoutTransition;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.net.Uri;
import android.os.Build.VERSION;
import android.support.annotation.Nullable;
import android.support.v4.graphics.ColorUtils;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.PopupMenu.OnDismissListener;
import android.widget.PopupMenu.OnMenuItemClickListener;
import com.cuebiq.cuebiqsdk.model.config.Settings;
import com.facebook.ads.internal.adapters.C1900j;
import com.facebook.ads.internal.p056q.p057a.C2133v;
import com.facebook.ads.internal.p056q.p075b.C2136b;
import com.facebook.ads.internal.p056q.p075b.C2137c;
import com.facebook.ads.internal.p056q.p076c.C2149g;
import com.facebook.ads.internal.view.component.CircularProgressView;
import com.facebook.ads.internal.view.p053e.C2249b;
import com.facebook.ads.internal.view.p053e.p054b.C1844c;
import com.facebook.ads.internal.view.p053e.p054b.C2229b;
import com.facebook.ads.internal.view.p053e.p054b.C2236n;
import com.facebook.ads.internal.view.p053e.p054b.C2237o;
import com.facebook.ads.internal.view.p053e.p083a.C2223b;
import com.facebook.ads.internal.view.p080c.C2191c;

public class C2348g extends LinearLayout implements C2223b {
    private static final float f5752c = Resources.getSystem().getDisplayMetrics().density;
    private static final int f5753d = ((int) (40.0f * f5752c));
    private static final int f5754e = ((int) (44.0f * f5752c));
    private static final int f5755f = ((int) (Settings.LOCATION_REQUEST_SMALLEST_DISPLACEMENT * f5752c));
    private static final int f5756g = ((int) (16.0f * f5752c));
    private static final int f5757h = (f5756g - f5755f);
    private static final int f5758i = ((f5756g * 2) - f5755f);
    private final C2237o f5759a = new C23411(this);
    private final C1844c f5760b = new C23422(this);
    private final ImageView f5761j;
    private final FrameLayout f5762k;
    private final ImageView f5763l;
    private final CircularProgressView f5764m;
    private final C2191c f5765n;
    private final PopupMenu f5766o;
    @Nullable
    private C2347a f5767p;
    @Nullable
    private C2249b f5768q;
    private int f5769r = 0;
    private boolean f5770s = false;
    private boolean f5771t = false;
    private OnDismissListener f5772u;

    class C23411 extends C2237o {
        final /* synthetic */ C2348g f5744a;

        C23411(C2348g c2348g) {
            this.f5744a = c2348g;
        }

        public void m7407a(C2236n c2236n) {
            if (this.f5744a.f5768q != null && this.f5744a.f5769r != 0 && this.f5744a.f5764m.isShown()) {
                float currentPosition = ((float) this.f5744a.f5768q.getCurrentPosition()) / Math.min(((float) this.f5744a.f5769r) * 1000.0f, (float) this.f5744a.f5768q.getDuration());
                this.f5744a.f5764m.setProgressWithAnimation(100.0f * currentPosition);
                if (currentPosition >= 1.0f) {
                    this.f5744a.m7423a(true);
                    this.f5744a.f5768q.getEventBus().m6330b(this.f5744a.f5759a, this.f5744a.f5760b);
                }
            }
        }
    }

    class C23422 extends C1844c {
        final /* synthetic */ C2348g f5745a;

        C23422(C2348g c2348g) {
            this.f5745a = c2348g;
        }

        public void m7409a(C2229b c2229b) {
            if (this.f5745a.f5768q != null && this.f5745a.f5769r != 0 && this.f5745a.f5764m.isShown() && !this.f5745a.f5771t) {
                this.f5745a.m7423a(true);
                this.f5745a.f5768q.getEventBus().m6330b(this.f5745a.f5759a, this.f5745a.f5760b);
            }
        }
    }

    class C23433 implements OnDismissListener {
        final /* synthetic */ C2348g f5746a;

        C23433(C2348g c2348g) {
            this.f5746a = c2348g;
        }

        public void onDismiss(PopupMenu popupMenu) {
            this.f5746a.f5770s = false;
        }
    }

    class C23444 implements OnClickListener {
        final /* synthetic */ C2348g f5747a;

        C23444(C2348g c2348g) {
            this.f5747a = c2348g;
        }

        public void onClick(View view) {
            if (this.f5747a.f5767p != null && this.f5747a.f5771t) {
                this.f5747a.f5767p.mo3826a();
            }
        }
    }

    class C23455 implements OnClickListener {
        final /* synthetic */ C2348g f5748a;

        C23455(C2348g c2348g) {
            this.f5748a = c2348g;
        }

        public void onClick(View view) {
            this.f5748a.f5766o.show();
            this.f5748a.f5770s = true;
        }
    }

    public interface C2347a {
        void mo3826a();
    }

    public C2348g(Context context) {
        super(context);
        setGravity(16);
        if (VERSION.SDK_INT >= 14) {
            this.f5772u = new C23433(this);
        }
        this.f5763l = new ImageView(context);
        this.f5763l.setPadding(f5755f, f5755f, f5755f, f5755f);
        this.f5763l.setScaleType(ScaleType.FIT_CENTER);
        this.f5763l.setImageBitmap(C2137c.m6843a(C2136b.INTERSTITIAL_CLOSE));
        this.f5763l.setOnClickListener(new C23444(this));
        this.f5764m = new CircularProgressView(context);
        this.f5764m.setPadding(f5755f, f5755f, f5755f, f5755f);
        this.f5764m.setProgress(0.0f);
        LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        layoutParams.setMargins(f5757h, f5757h, f5758i, f5757h);
        LayoutParams layoutParams2 = new LinearLayout.LayoutParams(f5754e, f5754e);
        this.f5762k = new FrameLayout(context);
        this.f5762k.setLayoutTransition(new LayoutTransition());
        this.f5762k.addView(this.f5763l, layoutParams2);
        this.f5762k.addView(this.f5764m, layoutParams2);
        addView(this.f5762k, layoutParams);
        this.f5765n = new C2191c(context);
        layoutParams = new LinearLayout.LayoutParams(0, -2);
        layoutParams.gravity = 17;
        layoutParams.weight = 1.0f;
        addView(this.f5765n, layoutParams);
        this.f5761j = new ImageView(context);
        this.f5761j.setPadding(f5755f, f5755f, f5755f, f5755f);
        this.f5761j.setScaleType(ScaleType.FIT_CENTER);
        this.f5761j.setImageBitmap(C2137c.m6843a(C2136b.INTERSTITIAL_AD_CHOICES));
        this.f5761j.setOnClickListener(new C23455(this));
        this.f5766o = new PopupMenu(context, this.f5761j);
        this.f5766o.getMenu().add("Ad Choices");
        layoutParams = new LinearLayout.LayoutParams(f5753d, f5753d);
        layoutParams.setMargins(0, f5756g / 2, f5756g / 2, f5756g / 2);
        addView(this.f5761j, layoutParams);
    }

    public void m7420a(C1900j c1900j, boolean z) {
        int a = c1900j.m5829a(z);
        this.f5765n.m7018a(c1900j.m5835g(z), a);
        this.f5761j.setColorFilter(a);
        this.f5763l.setColorFilter(a);
        this.f5764m.m7021a(ColorUtils.setAlphaComponent(a, 77), a);
        if (z) {
            Drawable gradientDrawable = new GradientDrawable(Orientation.TOP_BOTTOM, new int[]{-1778384896, 0});
            gradientDrawable.setCornerRadius(0.0f);
            C2133v.m6834a((View) this, gradientDrawable);
            return;
        }
        C2133v.m6833a((View) this, 0);
    }

    public void mo3777a(C2249b c2249b) {
        this.f5768q = c2249b;
        this.f5768q.getEventBus().m6328a(this.f5759a, this.f5760b);
    }

    public void m7422a(String str, String str2, String str3, final String str4, final String str5, int i) {
        this.f5769r = i;
        this.f5765n.m7020a(str, str2, str3);
        this.f5766o.setOnMenuItemClickListener(new OnMenuItemClickListener(this) {
            final /* synthetic */ C2348g f5751c;

            public boolean onMenuItemClick(MenuItem menuItem) {
                this.f5751c.f5770s = false;
                if (!TextUtils.isEmpty(str4)) {
                    C2149g.m6880a(new C2149g(), this.f5751c.getContext(), Uri.parse(str4), str5);
                }
                return true;
            }
        });
        if (VERSION.SDK_INT >= 14) {
            this.f5766o.setOnDismissListener(this.f5772u);
        }
        m7423a(this.f5769r <= 0);
    }

    public void m7423a(boolean z) {
        int i = 0;
        this.f5771t = z;
        this.f5762k.setVisibility(0);
        this.f5764m.setVisibility(z ? 4 : 0);
        ImageView imageView = this.f5763l;
        if (!z) {
            i = 4;
        }
        imageView.setVisibility(i);
    }

    public boolean m7424a() {
        return this.f5771t;
    }

    public void m7425b() {
        this.f5771t = false;
        this.f5762k.setVisibility(4);
        this.f5764m.setVisibility(4);
        this.f5763l.setVisibility(4);
    }

    public void mo3778b(C2249b c2249b) {
        if (this.f5768q != null) {
            this.f5768q.getEventBus().m6330b(this.f5759a, this.f5760b);
            this.f5768q = null;
        }
    }

    public void m7427c() {
        this.f5765n.setVisibility(4);
    }

    public void m7428d() {
        if (VERSION.SDK_INT >= 14) {
            this.f5766o.setOnDismissListener(null);
        }
        this.f5766o.dismiss();
        if (VERSION.SDK_INT >= 14) {
            this.f5766o.setOnDismissListener(this.f5772u);
        }
    }

    public void m7429e() {
        if (this.f5770s && VERSION.SDK_INT >= 14) {
            this.f5766o.show();
        }
    }

    public void setToolbarListener(C2347a c2347a) {
        this.f5767p = c2347a;
    }
}
