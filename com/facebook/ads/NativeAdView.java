package com.facebook.ads;

import android.content.Context;
import android.view.View;
import com.facebook.ads.internal.p033n.C2029i;

public class NativeAdView {

    public enum Type {
        HEIGHT_100(C2029i.HEIGHT_100),
        HEIGHT_120(C2029i.HEIGHT_120),
        HEIGHT_300(C2029i.HEIGHT_300),
        HEIGHT_400(C2029i.HEIGHT_400);
        
        private final C2029i f4032a;

        private Type(C2029i c2029i) {
            this.f4032a = c2029i;
        }

        C2029i m5502a() {
            return this.f4032a;
        }

        public int getHeight() {
            return this.f4032a.m6500b();
        }

        public int getValue() {
            return this.f4032a.m6500b();
        }

        public int getWidth() {
            return this.f4032a.m6499a();
        }
    }

    public static View render(Context context, NativeAd nativeAd, Type type) {
        return render(context, nativeAd, type, null);
    }

    public static View render(Context context, NativeAd nativeAd, Type type, NativeAdViewAttributes nativeAdViewAttributes) {
        if (nativeAd.isNativeConfigEnabled()) {
            nativeAdViewAttributes = nativeAd.getAdViewAttributes();
        } else if (nativeAdViewAttributes == null) {
            nativeAdViewAttributes = new NativeAdViewAttributes();
        }
        nativeAd.a(type);
        return new ANGenericTemplateView(context, nativeAd, type, nativeAdViewAttributes != null ? nativeAdViewAttributes.m5503a() : null);
    }
}
