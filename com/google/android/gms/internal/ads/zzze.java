package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.view.View;
import com.google.android.gms.ads.formats.NativeAd.Image;
import com.google.android.gms.ads.mediation.UnifiedNativeAdMapper;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@zzadh
public final class zzze extends zzyg {
    private final UnifiedNativeAdMapper zzbvh;

    public zzze(UnifiedNativeAdMapper unifiedNativeAdMapper) {
        this.zzbvh = unifiedNativeAdMapper;
    }

    public final String getAdvertiser() {
        return this.zzbvh.getAdvertiser();
    }

    public final String getBody() {
        return this.zzbvh.getBody();
    }

    public final String getCallToAction() {
        return this.zzbvh.getCallToAction();
    }

    public final Bundle getExtras() {
        return this.zzbvh.getExtras();
    }

    public final String getHeadline() {
        return this.zzbvh.getHeadline();
    }

    public final List getImages() {
        List<Image> images = this.zzbvh.getImages();
        List arrayList = new ArrayList();
        if (images != null) {
            for (Image image : images) {
                arrayList.add(new zzon(image.getDrawable(), image.getUri(), image.getScale()));
            }
        }
        return arrayList;
    }

    public final boolean getOverrideClickHandling() {
        return this.zzbvh.getOverrideClickHandling();
    }

    public final boolean getOverrideImpressionRecording() {
        return this.zzbvh.getOverrideImpressionRecording();
    }

    public final String getPrice() {
        return this.zzbvh.getPrice();
    }

    public final double getStarRating() {
        return this.zzbvh.getStarRating() != null ? this.zzbvh.getStarRating().doubleValue() : -1.0d;
    }

    public final String getStore() {
        return this.zzbvh.getStore();
    }

    public final zzlo getVideoController() {
        return this.zzbvh.getVideoController() != null ? this.zzbvh.getVideoController().zzbc() : null;
    }

    public final void recordImpression() {
        this.zzbvh.recordImpression();
    }

    public final void zzb(IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2, IObjectWrapper iObjectWrapper3) {
        this.zzbvh.trackViews((View) ObjectWrapper.unwrap(iObjectWrapper), (HashMap) ObjectWrapper.unwrap(iObjectWrapper2), (HashMap) ObjectWrapper.unwrap(iObjectWrapper3));
    }

    public final void zzj(IObjectWrapper iObjectWrapper) {
        this.zzbvh.handleClick((View) ObjectWrapper.unwrap(iObjectWrapper));
    }

    public final zzpw zzjz() {
        Image icon = this.zzbvh.getIcon();
        return icon != null ? new zzon(icon.getDrawable(), icon.getUri(), icon.getScale()) : null;
    }

    public final IObjectWrapper zzke() {
        Object zzbh = this.zzbvh.zzbh();
        return zzbh == null ? null : ObjectWrapper.wrap(zzbh);
    }

    public final zzps zzkf() {
        return null;
    }

    public final void zzl(IObjectWrapper iObjectWrapper) {
        this.zzbvh.untrackView((View) ObjectWrapper.unwrap(iObjectWrapper));
    }

    public final IObjectWrapper zzmv() {
        View adChoicesContent = this.zzbvh.getAdChoicesContent();
        return adChoicesContent == null ? null : ObjectWrapper.wrap(adChoicesContent);
    }

    public final IObjectWrapper zzmw() {
        View zzvy = this.zzbvh.zzvy();
        return zzvy == null ? null : ObjectWrapper.wrap(zzvy);
    }
}
