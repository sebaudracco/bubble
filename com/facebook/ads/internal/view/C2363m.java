package com.facebook.ads.internal.view;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Build.VERSION;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.RelativeLayout;
import com.facebook.ads.AudienceNetworkActivity;
import com.facebook.ads.internal.adapters.C1886d;
import com.facebook.ads.internal.adapters.C1900j;
import com.facebook.ads.internal.adapters.C1936v;
import com.facebook.ads.internal.p056q.p057a.C2128q;
import com.facebook.ads.internal.p056q.p057a.C2128q.C2127a;
import com.facebook.ads.internal.p056q.p057a.C2133v;
import com.facebook.ads.internal.p069m.C2012c;
import com.facebook.ads.internal.view.C1923a.C1832a;
import com.facebook.ads.internal.view.C2348g.C2347a;

public abstract class C2363m extends RelativeLayout implements C1923a {
    protected static final int f5834a = ((int) (56.0f * C2133v.f5083b));
    protected final C2012c f5835b;
    protected final C2348g f5836c = new C2348g(getContext());
    protected C1900j f5837d;
    protected C1900j f5838e;
    @Nullable
    private C1832a f5839f;
    private final C2128q f5840g = new C2128q(this);

    C2363m(Context context, C2012c c2012c) {
        super(context.getApplicationContext());
        this.f5835b = c2012c;
    }

    private void mo3824a() {
        removeAllViews();
        C2133v.m6836b(this);
    }

    void m7466a(View view, boolean z, int i) {
        int d;
        this.f5840g.m6817a(C2127a.DEFAULT);
        mo3824a();
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams.setMargins(0, z ? 0 : f5834a, 0, 0);
        addView(view, layoutParams);
        LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, f5834a);
        layoutParams2.addRule(10);
        if (i == 1) {
            d = this.f5837d.m5832d(z);
            this.f5836c.m7420a(this.f5837d, z);
        } else {
            d = this.f5838e.m5832d(z);
            this.f5836c.m7420a(this.f5838e, z);
        }
        addView(this.f5836c, layoutParams2);
        C2133v.m6833a((View) this, d);
        if (this.f5839f != null) {
            this.f5839f.mo3560a((View) this);
            if (z && VERSION.SDK_INT >= 19) {
                this.f5840g.m6817a(C2127a.FULL_SCREEN);
            }
        }
    }

    public void m7467a(final AudienceNetworkActivity audienceNetworkActivity, C1936v c1936v) {
        this.f5840g.m6816a(audienceNetworkActivity.getWindow());
        this.f5837d = c1936v.m6115h();
        this.f5838e = c1936v.m6116i();
        this.f5836c.m7422a(c1936v.m6109b(), c1936v.m6110c(), c1936v.m6114g(), c1936v.m6112e(), c1936v.m6108a(), ((C1886d) c1936v.m6111d().get(0)).m5768l());
        this.f5836c.setToolbarListener(new C2347a(this) {
            final /* synthetic */ C2363m f5888b;

            public void mo3826a() {
                audienceNetworkActivity.finish();
            }
        });
    }

    C1832a getAudienceNetworkListener() {
        return this.f5839f;
    }

    protected void onConfigurationChanged(Configuration configuration) {
        this.f5836c.m7428d();
        super.onConfigurationChanged(configuration);
        final ViewTreeObserver viewTreeObserver = getViewTreeObserver();
        viewTreeObserver.addOnGlobalLayoutListener(new OnGlobalLayoutListener(this) {
            final /* synthetic */ C2363m f5890b;

            @RequiresApi(api = 16)
            public void onGlobalLayout() {
                this.f5890b.f5836c.m7429e();
                if (VERSION.SDK_INT >= 14) {
                    viewTreeObserver.removeOnGlobalLayoutListener(this);
                }
            }
        });
    }

    public void onDestroy() {
        this.f5840g.m6815a();
        this.f5836c.setToolbarListener(null);
        mo3824a();
    }

    public void setListener(C1832a c1832a) {
        this.f5839f = c1832a;
    }
}
