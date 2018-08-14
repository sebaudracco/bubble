package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.List;
import javax.annotation.ParametersAreNonnullByDefault;

@zzadh
@ParametersAreNonnullByDefault
public final class zzov extends zzrs implements zzpc {
    private Bundle mExtras;
    private Object mLock = new Object();
    private String zzbhw;
    private List<zzon> zzbhx;
    private String zzbhy;
    private zzpw zzbhz;
    private String zzbia;
    private double zzbib;
    private String zzbic;
    private String zzbid;
    @Nullable
    private zzoj zzbie;
    @Nullable
    private zzlo zzbif;
    @Nullable
    private View zzbig;
    @Nullable
    private IObjectWrapper zzbih;
    @Nullable
    private String zzbii;
    private zzoz zzbij;
    private String zzbim;

    public zzov(String str, List<zzon> list, String str2, zzpw com_google_android_gms_internal_ads_zzpw, String str3, String str4, double d, String str5, String str6, @Nullable zzoj com_google_android_gms_internal_ads_zzoj, zzlo com_google_android_gms_internal_ads_zzlo, View view, IObjectWrapper iObjectWrapper, String str7, Bundle bundle) {
        this.zzbhw = str;
        this.zzbhx = list;
        this.zzbhy = str2;
        this.zzbhz = com_google_android_gms_internal_ads_zzpw;
        this.zzbia = str3;
        this.zzbim = str4;
        this.zzbib = d;
        this.zzbic = str5;
        this.zzbid = str6;
        this.zzbie = com_google_android_gms_internal_ads_zzoj;
        this.zzbif = com_google_android_gms_internal_ads_zzlo;
        this.zzbig = view;
        this.zzbih = iObjectWrapper;
        this.zzbii = str7;
        this.mExtras = bundle;
    }

    public final void cancelUnconfirmedClick() {
        this.zzbij.cancelUnconfirmedClick();
    }

    public final void destroy() {
        zzakk.zzcrm.post(new zzow(this));
    }

    public final String getAdvertiser() {
        return this.zzbim;
    }

    public final String getBody() {
        return this.zzbhy;
    }

    public final String getCallToAction() {
        return this.zzbia;
    }

    public final String getCustomTemplateId() {
        return "";
    }

    public final Bundle getExtras() {
        return this.mExtras;
    }

    public final String getHeadline() {
        return this.zzbhw;
    }

    public final List getImages() {
        return this.zzbhx;
    }

    @Nullable
    public final String getMediationAdapterClassName() {
        return this.zzbii;
    }

    public final String getPrice() {
        return this.zzbid;
    }

    public final double getStarRating() {
        return this.zzbib;
    }

    public final String getStore() {
        return this.zzbic;
    }

    public final zzlo getVideoController() {
        return this.zzbif;
    }

    public final void performClick(Bundle bundle) {
        synchronized (this.mLock) {
            if (this.zzbij == null) {
                zzane.m3427e("#001 Attempt to perform click before app native ad initialized.");
                return;
            }
            this.zzbij.performClick(bundle);
        }
    }

    public final boolean recordImpression(Bundle bundle) {
        boolean z;
        synchronized (this.mLock) {
            if (this.zzbij == null) {
                zzane.m3427e("#002 Attempt to record impression before native ad initialized.");
                z = false;
            } else {
                z = this.zzbij.recordImpression(bundle);
            }
        }
        return z;
    }

    public final void reportTouchEvent(Bundle bundle) {
        synchronized (this.mLock) {
            if (this.zzbij == null) {
                zzane.m3427e("#003 Attempt to report touch event before native ad initialized.");
                return;
            }
            this.zzbij.reportTouchEvent(bundle);
        }
    }

    public final void zza(zzro com_google_android_gms_internal_ads_zzro) {
        this.zzbij.zza(com_google_android_gms_internal_ads_zzro);
    }

    public final void zzb(zzoz com_google_android_gms_internal_ads_zzoz) {
        synchronized (this.mLock) {
            this.zzbij = com_google_android_gms_internal_ads_zzoz;
        }
    }

    public final zzpw zzjz() {
        return this.zzbhz;
    }

    public final IObjectWrapper zzka() {
        return ObjectWrapper.wrap(this.zzbij);
    }

    public final String zzkb() {
        return "6";
    }

    public final zzoj zzkc() {
        return this.zzbie;
    }

    public final View zzkd() {
        return this.zzbig;
    }

    public final IObjectWrapper zzke() {
        return this.zzbih;
    }

    public final zzps zzkf() {
        return this.zzbie;
    }
}
