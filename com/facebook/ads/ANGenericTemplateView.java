package com.facebook.ads;

import android.content.Context;
import android.widget.RelativeLayout;
import com.facebook.ads.NativeAdView.Type;
import com.facebook.ads.internal.p033n.C2019a;
import com.facebook.ads.internal.p033n.C2028h;

public class ANGenericTemplateView extends RelativeLayout {
    private final C2019a f3937a;

    public ANGenericTemplateView(Context context, NativeAd nativeAd, Type type, C2028h c2028h) {
        super(context);
        RelativeLayout mediaView = new MediaView(getContext());
        mediaView.setNativeAd(nativeAd);
        this.f3937a = new C2019a(context, nativeAd.getInternalNativeAd(), this, new AdChoicesView(getContext(), nativeAd, true), mediaView, type.m5502a(), c2028h);
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.f3937a.m6445a();
    }
}
