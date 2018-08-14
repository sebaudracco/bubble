package com.facebook.ads;

import android.view.View;
import com.facebook.ads.internal.p056q.p057a.C2118i;

public enum NativeAd$NativeComponentTag {
    AD_ICON(C2118i.INTERNAL_AD_ICON),
    AD_TITLE(C2118i.INTERNAL_AD_TITLE),
    AD_COVER_IMAGE(C2118i.INTERNAL_AD_COVER_IMAGE),
    AD_SUBTITLE(C2118i.INTERNAL_AD_SUBTITLE),
    AD_BODY(C2118i.INTERNAL_AD_BODY),
    AD_CALL_TO_ACTION(C2118i.INTERNAL_AD_CALL_TO_ACTION),
    AD_SOCIAL_CONTEXT(C2118i.INTERNAL_AD_SOCIAL_CONTEXT),
    AD_CHOICES_ICON(C2118i.INTERNAL_AD_CHOICES_ICON),
    AD_MEDIA(C2118i.INTERNAL_AD_MEDIA);
    
    private final C2118i f4019a;

    private NativeAd$NativeComponentTag(C2118i c2118i) {
        this.f4019a = c2118i;
    }

    public static void tagView(View view, NativeAd$NativeComponentTag nativeAd$NativeComponentTag) {
        if (view != null && nativeAd$NativeComponentTag != null) {
            C2118i.m6796a(view, nativeAd$NativeComponentTag.f4019a);
        }
    }
}
