package com.google.android.gms.internal.ads;

import android.text.TextUtils;
import com.google.android.gms.ads.internal.gmsg.zzv;
import java.util.Map;

final class zzarj implements zzv<zzaqw> {
    private final /* synthetic */ zzari zzdec;

    zzarj(zzari com_google_android_gms_internal_ads_zzari) {
        this.zzdec = com_google_android_gms_internal_ads_zzari;
    }

    public final /* synthetic */ void zza(Object obj, Map map) {
        if (map != null) {
            String str = (String) map.get("height");
            if (!TextUtils.isEmpty(str)) {
                try {
                    int parseInt = Integer.parseInt(str);
                    synchronized (this.zzdec) {
                        if (zzari.zza(this.zzdec) != parseInt) {
                            zzari.zza(this.zzdec, parseInt);
                            this.zzdec.requestLayout();
                        }
                    }
                } catch (Throwable e) {
                    zzakb.zzc("Exception occurred while getting webview content height", e);
                }
            }
        }
    }
}
