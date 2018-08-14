package com.fyber.ads.banners;

import android.app.Activity;
import android.view.ViewGroup;
import com.fyber.ads.Ad;
import com.fyber.ads.AdFormat;
import com.fyber.ads.banners.p087a.C2418d;
import com.fyber.ads.internal.C2419b;

public class BannerAd extends Ad<BannerAd, BannerAdListener> {
    public static final int POSITION_BOTTOM = 80;
    public static final int POSITION_TOP = 48;
    private C2418d f6017c;

    public BannerAd(String str, C2419b<BannerAdListener> c2419b, C2418d c2418d) {
        super(str, c2419b);
        this.f6017c = c2418d;
    }

    public BannerAd displayInView(ViewGroup viewGroup) {
        this.f6017c.mo3846a(viewGroup);
        return this;
    }

    public BannerAd withPosition(int i) {
        this.f6017c.mo3850c(i);
        return this;
    }

    public BannerAd displayAtTop() {
        return withPosition(48);
    }

    public BannerAd displayAtBottom() {
        return withPosition(80);
    }

    public void hide() {
        this.f6017c.mo3852k();
    }

    public void show() {
        this.f6017c.mo3853l();
    }

    public void start(Activity activity) {
        this.f6017c.mo3845a(activity);
    }

    public void destroy() {
        this.f6017c.mo3854m();
    }

    public boolean canStart() {
        return this.f6017c.mo3855n();
    }

    public AdFormat getAdFormat() {
        return AdFormat.BANNER;
    }
}
