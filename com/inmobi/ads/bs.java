package com.inmobi.ads;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import com.inmobi.ads.C3046c.C3044h;
import com.inmobi.ads.ViewableAd.AdEvent;
import com.inmobi.rendering.RenderView;

/* compiled from: ViewableHtmlAd */
public class bs extends ViewableAd {
    @NonNull
    private final RenderView f7312b;

    public bs(@NonNull RenderView renderView) {
        super(renderView);
        this.f7312b = renderView;
    }

    public View mo6225a() {
        m9103a(this.f7312b);
        return this.f7312b;
    }

    public View mo6226a(View view, ViewGroup viewGroup, boolean z) {
        return mo6225a();
    }

    public void mo6228a(@NonNull C3044h c3044h, @Nullable View... viewArr) {
    }

    public void mo6229c() {
    }

    public void mo6227a(AdEvent adEvent) {
    }

    public void mo6230d() {
        ViewParent parent = this.f7312b.getParent();
        if (parent != null) {
            ((ViewGroup) parent).removeView(this.f7312b);
        }
        this.f7312b.removeAllViews();
        super.mo6230d();
    }
}
