package com.google.ads.mediation;

import android.view.View;
import com.google.android.gms.ads.formats.NativeAdView;
import com.google.android.gms.ads.formats.NativeAdViewHolder;
import com.google.android.gms.ads.formats.NativeContentAd;
import com.google.android.gms.ads.mediation.NativeContentAdMapper;

class AbstractAdViewAdapter$zzb extends NativeContentAdMapper {
    private final NativeContentAd zzhf;

    public AbstractAdViewAdapter$zzb(NativeContentAd nativeContentAd) {
        this.zzhf = nativeContentAd;
        setHeadline(nativeContentAd.getHeadline().toString());
        setImages(nativeContentAd.getImages());
        setBody(nativeContentAd.getBody().toString());
        if (nativeContentAd.getLogo() != null) {
            setLogo(nativeContentAd.getLogo());
        }
        setCallToAction(nativeContentAd.getCallToAction().toString());
        setAdvertiser(nativeContentAd.getAdvertiser().toString());
        setOverrideImpressionRecording(true);
        setOverrideClickHandling(true);
        zza(nativeContentAd.getVideoController());
    }

    public final void trackView(View view) {
        if (view instanceof NativeAdView) {
            ((NativeAdView) view).setNativeAd(this.zzhf);
        }
        NativeAdViewHolder nativeAdViewHolder = (NativeAdViewHolder) NativeAdViewHolder.zzvk.get(view);
        if (nativeAdViewHolder != null) {
            nativeAdViewHolder.setNativeAd(this.zzhf);
        }
    }
}
