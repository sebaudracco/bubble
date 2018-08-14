package com.google.android.gms.ads.internal;

import android.view.View;
import com.google.android.gms.ads.internal.gmsg.zzv;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.ads.zzakb;
import com.google.android.gms.internal.ads.zzaqw;
import com.google.android.gms.internal.ads.zzxz;
import com.google.android.gms.internal.ads.zzyc;
import java.util.Map;

final class zzax implements zzv<zzaqw> {
    private final /* synthetic */ zzxz zzzr;
    private final /* synthetic */ zzac zzzs;
    private final /* synthetic */ zzyc zzzt;

    zzax(zzxz com_google_android_gms_internal_ads_zzxz, zzac com_google_android_gms_ads_internal_zzac, zzyc com_google_android_gms_internal_ads_zzyc) {
        this.zzzr = com_google_android_gms_internal_ads_zzxz;
        this.zzzs = com_google_android_gms_ads_internal_zzac;
        this.zzzt = com_google_android_gms_internal_ads_zzyc;
    }

    public final /* synthetic */ void zza(Object obj, Map map) {
        zzaqw com_google_android_gms_internal_ads_zzaqw = (zzaqw) obj;
        View view = com_google_android_gms_internal_ads_zzaqw.getView();
        if (view != null) {
            try {
                if (this.zzzr != null) {
                    if (this.zzzr.getOverrideClickHandling()) {
                        zzas.zze(com_google_android_gms_internal_ads_zzaqw);
                        return;
                    }
                    this.zzzr.zzj(ObjectWrapper.wrap(view));
                    this.zzzs.zzxl.onAdClicked();
                } else if (this.zzzt == null) {
                } else {
                    if (this.zzzt.getOverrideClickHandling()) {
                        zzas.zze(com_google_android_gms_internal_ads_zzaqw);
                        return;
                    }
                    this.zzzt.zzj(ObjectWrapper.wrap(view));
                    this.zzzs.zzxl.onAdClicked();
                }
            } catch (Throwable e) {
                zzakb.zzc("Unable to call handleClick on mapper", e);
            }
        }
    }
}
