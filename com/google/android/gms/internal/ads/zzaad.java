package com.google.android.gms.internal.ads;

import com.appnext.core.Ad;
import com.google.android.gms.ads.internal.zzbv;
import java.util.Map;

@zzadh
public final class zzaad {
    private final zzaqw zzbnd;
    private final boolean zzbwm;
    private final String zzbwn;

    public zzaad(zzaqw com_google_android_gms_internal_ads_zzaqw, Map<String, String> map) {
        this.zzbnd = com_google_android_gms_internal_ads_zzaqw;
        this.zzbwn = (String) map.get("forceOrientation");
        if (map.containsKey("allowOrientationChange")) {
            this.zzbwm = Boolean.parseBoolean((String) map.get("allowOrientationChange"));
        } else {
            this.zzbwm = true;
        }
    }

    public final void execute() {
        if (this.zzbnd == null) {
            zzane.zzdk("AdWebView is null");
            return;
        }
        int zzrm = Ad.ORIENTATION_PORTRAIT.equalsIgnoreCase(this.zzbwn) ? zzbv.zzem().zzrm() : Ad.ORIENTATION_LANDSCAPE.equalsIgnoreCase(this.zzbwn) ? zzbv.zzem().zzrl() : this.zzbwm ? -1 : zzbv.zzem().zzrn();
        this.zzbnd.setRequestedOrientation(zzrm);
    }
}
