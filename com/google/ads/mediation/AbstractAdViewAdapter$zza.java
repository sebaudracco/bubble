package com.google.ads.mediation;

import android.view.View;
import com.google.android.gms.ads.formats.NativeAdView;
import com.google.android.gms.ads.formats.NativeAdViewHolder;
import com.google.android.gms.ads.formats.NativeAppInstallAd;
import com.google.android.gms.ads.mediation.NativeAppInstallAdMapper;

class AbstractAdViewAdapter$zza extends NativeAppInstallAdMapper {
    private final NativeAppInstallAd zzhe;

    public AbstractAdViewAdapter$zza(NativeAppInstallAd nativeAppInstallAd) {
        this.zzhe = nativeAppInstallAd;
        setHeadline(nativeAppInstallAd.getHeadline().toString());
        setImages(nativeAppInstallAd.getImages());
        setBody(nativeAppInstallAd.getBody().toString());
        setIcon(nativeAppInstallAd.getIcon());
        setCallToAction(nativeAppInstallAd.getCallToAction().toString());
        if (nativeAppInstallAd.getStarRating() != null) {
            setStarRating(nativeAppInstallAd.getStarRating().doubleValue());
        }
        if (nativeAppInstallAd.getStore() != null) {
            setStore(nativeAppInstallAd.getStore().toString());
        }
        if (nativeAppInstallAd.getPrice() != null) {
            setPrice(nativeAppInstallAd.getPrice().toString());
        }
        setOverrideImpressionRecording(true);
        setOverrideClickHandling(true);
        zza(nativeAppInstallAd.getVideoController());
    }

    public final void trackView(View view) {
        if (view instanceof NativeAdView) {
            ((NativeAdView) view).setNativeAd(this.zzhe);
        }
        NativeAdViewHolder nativeAdViewHolder = (NativeAdViewHolder) NativeAdViewHolder.zzvk.get(view);
        if (nativeAdViewHolder != null) {
            nativeAdViewHolder.setNativeAd(this.zzhe);
        }
    }
}
