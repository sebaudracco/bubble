package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.List;
import javax.annotation.ParametersAreNonnullByDefault;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;

@zzadh
@ParametersAreNonnullByDefault
public final class zzoq extends zzqp implements zzpc {
    private Bundle mExtras;
    private Object mLock = new Object();
    private String zzbhw;
    private List<zzon> zzbhx;
    private String zzbhy;
    private String zzbia;
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
    private zzpw zzbil;
    private String zzbim;

    public zzoq(String str, List<zzon> list, String str2, zzpw com_google_android_gms_internal_ads_zzpw, String str3, String str4, @Nullable zzoj com_google_android_gms_internal_ads_zzoj, Bundle bundle, zzlo com_google_android_gms_internal_ads_zzlo, View view, IObjectWrapper iObjectWrapper, String str5) {
        this.zzbhw = str;
        this.zzbhx = list;
        this.zzbhy = str2;
        this.zzbil = com_google_android_gms_internal_ads_zzpw;
        this.zzbia = str3;
        this.zzbim = str4;
        this.zzbie = com_google_android_gms_internal_ads_zzoj;
        this.mExtras = bundle;
        this.zzbif = com_google_android_gms_internal_ads_zzlo;
        this.zzbig = view;
        this.zzbih = iObjectWrapper;
        this.zzbii = str5;
    }

    public final void destroy() {
        zzakk.zzcrm.post(new zzor(this));
        this.zzbhw = null;
        this.zzbhx = null;
        this.zzbhy = null;
        this.zzbil = null;
        this.zzbia = null;
        this.zzbim = null;
        this.zzbie = null;
        this.mExtras = null;
        this.mLock = null;
        this.zzbif = null;
        this.zzbig = null;
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

    public final void zzb(zzoz com_google_android_gms_internal_ads_zzoz) {
        synchronized (this.mLock) {
            this.zzbij = com_google_android_gms_internal_ads_zzoz;
        }
    }

    public final IObjectWrapper zzka() {
        return ObjectWrapper.wrap(this.zzbij);
    }

    public final String zzkb() {
        return SchemaSymbols.ATTVAL_TRUE_1;
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

    public final zzpw zzkg() {
        return this.zzbil;
    }
}
