package com.google.android.gms.internal.ads;

import android.support.annotation.Nullable;
import android.support.v4.util.SimpleArrayMap;
import android.view.View;
import android.widget.FrameLayout;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.Arrays;
import java.util.List;

@zzadh
public final class zzos extends zzqt implements zzpb {
    private final Object mLock = new Object();
    private final zzoj zzbie;
    @Nullable
    private zzlo zzbif;
    @Nullable
    private View zzbig;
    private zzoz zzbij;
    private final String zzbio;
    private final SimpleArrayMap<String, zzon> zzbip;
    private final SimpleArrayMap<String, String> zzbiq;

    public zzos(String str, SimpleArrayMap<String, zzon> simpleArrayMap, SimpleArrayMap<String, String> simpleArrayMap2, zzoj com_google_android_gms_internal_ads_zzoj, zzlo com_google_android_gms_internal_ads_zzlo, View view) {
        this.zzbio = str;
        this.zzbip = simpleArrayMap;
        this.zzbiq = simpleArrayMap2;
        this.zzbie = com_google_android_gms_internal_ads_zzoj;
        this.zzbif = com_google_android_gms_internal_ads_zzlo;
        this.zzbig = view;
    }

    public final void destroy() {
        zzakk.zzcrm.post(new zzou(this));
        this.zzbif = null;
        this.zzbig = null;
    }

    public final List<String> getAvailableAssetNames() {
        int i = 0;
        String[] strArr = new String[(this.zzbip.size() + this.zzbiq.size())];
        int i2 = 0;
        for (int i3 = 0; i3 < this.zzbip.size(); i3++) {
            strArr[i2] = (String) this.zzbip.keyAt(i3);
            i2++;
        }
        while (i < this.zzbiq.size()) {
            strArr[i2] = (String) this.zzbiq.keyAt(i);
            i++;
            i2++;
        }
        return Arrays.asList(strArr);
    }

    public final String getCustomTemplateId() {
        return this.zzbio;
    }

    public final zzlo getVideoController() {
        return this.zzbif;
    }

    public final void performClick(String str) {
        synchronized (this.mLock) {
            if (this.zzbij == null) {
                zzane.m3427e("#001 Attempt to perform click before app native ad initialized.");
                return;
            }
            this.zzbij.zza(null, str, null, null, null);
        }
    }

    public final void recordImpression() {
        synchronized (this.mLock) {
            if (this.zzbij == null) {
                zzane.m3427e("#002 Attempt to record impression before native ad initialized.");
                return;
            }
            this.zzbij.zza(null, null);
        }
    }

    public final String zzao(String str) {
        return (String) this.zzbiq.get(str);
    }

    public final zzpw zzap(String str) {
        return (zzpw) this.zzbip.get(str);
    }

    public final void zzb(zzoz com_google_android_gms_internal_ads_zzoz) {
        synchronized (this.mLock) {
            this.zzbij = com_google_android_gms_internal_ads_zzoz;
        }
    }

    public final boolean zzh(IObjectWrapper iObjectWrapper) {
        if (this.zzbij == null) {
            zzane.m3427e("Attempt to call renderVideoInMediaView before ad initialized.");
            return false;
        } else if (this.zzbig == null) {
            return false;
        } else {
            FrameLayout frameLayout = (FrameLayout) ObjectWrapper.unwrap(iObjectWrapper);
            this.zzbij.zza(frameLayout, new zzot(this));
            return true;
        }
    }

    public final IObjectWrapper zzka() {
        return ObjectWrapper.wrap(this.zzbij);
    }

    public final String zzkb() {
        return "3";
    }

    public final zzoj zzkc() {
        return this.zzbie;
    }

    public final View zzkd() {
        return this.zzbig;
    }

    public final IObjectWrapper zzkh() {
        return ObjectWrapper.wrap(this.zzbij.getContext().getApplicationContext());
    }
}
