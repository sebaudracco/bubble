package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.view.View;
import com.google.android.gms.ads.formats.NativeAd.Image;
import com.google.android.gms.ads.mediation.NativeContentAdMapper;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@zzadh
public final class zzyn extends zzyd {
    private final NativeContentAdMapper zzbuz;

    public zzyn(NativeContentAdMapper nativeContentAdMapper) {
        this.zzbuz = nativeContentAdMapper;
    }

    public final String getAdvertiser() {
        return this.zzbuz.getAdvertiser();
    }

    public final String getBody() {
        return this.zzbuz.getBody();
    }

    public final String getCallToAction() {
        return this.zzbuz.getCallToAction();
    }

    public final Bundle getExtras() {
        return this.zzbuz.getExtras();
    }

    public final String getHeadline() {
        return this.zzbuz.getHeadline();
    }

    public final List getImages() {
        List<Image> images = this.zzbuz.getImages();
        if (images == null) {
            return null;
        }
        List arrayList = new ArrayList();
        for (Image image : images) {
            arrayList.add(new zzon(image.getDrawable(), image.getUri(), image.getScale()));
        }
        return arrayList;
    }

    public final boolean getOverrideClickHandling() {
        return this.zzbuz.getOverrideClickHandling();
    }

    public final boolean getOverrideImpressionRecording() {
        return this.zzbuz.getOverrideImpressionRecording();
    }

    public final zzlo getVideoController() {
        return this.zzbuz.getVideoController() != null ? this.zzbuz.getVideoController().zzbc() : null;
    }

    public final void recordImpression() {
        this.zzbuz.recordImpression();
    }

    public final void zzb(IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2, IObjectWrapper iObjectWrapper3) {
        this.zzbuz.trackViews((View) ObjectWrapper.unwrap(iObjectWrapper), (HashMap) ObjectWrapper.unwrap(iObjectWrapper2), (HashMap) ObjectWrapper.unwrap(iObjectWrapper3));
    }

    public final void zzj(IObjectWrapper iObjectWrapper) {
        this.zzbuz.handleClick((View) ObjectWrapper.unwrap(iObjectWrapper));
    }

    public final void zzk(IObjectWrapper iObjectWrapper) {
        this.zzbuz.trackView((View) ObjectWrapper.unwrap(iObjectWrapper));
    }

    public final IObjectWrapper zzke() {
        return null;
    }

    public final zzps zzkf() {
        return null;
    }

    public final zzpw zzkg() {
        Image logo = this.zzbuz.getLogo();
        return logo != null ? new zzon(logo.getDrawable(), logo.getUri(), logo.getScale()) : null;
    }

    public final void zzl(IObjectWrapper iObjectWrapper) {
        this.zzbuz.untrackView((View) ObjectWrapper.unwrap(iObjectWrapper));
    }

    public final IObjectWrapper zzmv() {
        View adChoicesContent = this.zzbuz.getAdChoicesContent();
        return adChoicesContent == null ? null : ObjectWrapper.wrap(adChoicesContent);
    }

    public final IObjectWrapper zzmw() {
        View zzvy = this.zzbuz.zzvy();
        return zzvy == null ? null : ObjectWrapper.wrap(zzvy);
    }
}
