package com.fyber.ads.banners.mediation;

import android.view.View;
import com.fyber.ads.banners.p087a.C2417c;

public abstract class BannerWrapper {
    private C2417c bannerEventListener;

    public abstract void destroy();

    public abstract View getView();

    public void onBannerLoaded() {
        if (this.bannerEventListener != null) {
            C2417c c2417c = this.bannerEventListener;
            getView();
            c2417c.mo3856o();
        }
    }

    public void onBannerClick() {
        if (this.bannerEventListener != null) {
            C2417c c2417c = this.bannerEventListener;
            getView();
            c2417c.mo3857p();
        }
    }

    public void onBannerError(String str) {
        if (this.bannerEventListener != null) {
            C2417c c2417c = this.bannerEventListener;
            getView();
            c2417c.mo3848a(str);
        }
    }

    public void onBannerLeftApplication() {
        if (this.bannerEventListener != null) {
            C2417c c2417c = this.bannerEventListener;
            getView();
            c2417c.mo3858q();
        }
    }

    public void setBannerEventListener(C2417c c2417c) {
        this.bannerEventListener = c2417c;
    }
}
