package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.gmsg.zzv;
import org.json.JSONObject;

@zzadh
public final class zzfb implements zzfo {
    private final zzet zzafq;
    private final zzaqw zzafr;
    private final zzv<zzaqw> zzafs = new zzfc(this);
    private final zzv<zzaqw> zzaft = new zzfd(this);
    private final zzv<zzaqw> zzafu = new zzfe(this);

    public zzfb(zzet com_google_android_gms_internal_ads_zzet, zzaqw com_google_android_gms_internal_ads_zzaqw) {
        this.zzafq = com_google_android_gms_internal_ads_zzet;
        this.zzafr = com_google_android_gms_internal_ads_zzaqw;
        zzaqw com_google_android_gms_internal_ads_zzaqw2 = this.zzafr;
        com_google_android_gms_internal_ads_zzaqw2.zza("/updateActiveView", this.zzafs);
        com_google_android_gms_internal_ads_zzaqw2.zza("/untrackActiveViewUnit", this.zzaft);
        com_google_android_gms_internal_ads_zzaqw2.zza("/visibilityChanged", this.zzafu);
        String str = "Custom JS tracking ad unit: ";
        String valueOf = String.valueOf(this.zzafq.zzaet.zzfy());
        zzane.zzck(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
    }

    public final void zzb(JSONObject jSONObject, boolean z) {
        if (z) {
            this.zzafq.zzb(this);
        } else {
            this.zzafr.zzb("AFMA_updateActiveView", jSONObject);
        }
    }

    public final boolean zzgk() {
        return true;
    }

    public final void zzgl() {
        zzaqw com_google_android_gms_internal_ads_zzaqw = this.zzafr;
        com_google_android_gms_internal_ads_zzaqw.zzb("/visibilityChanged", this.zzafu);
        com_google_android_gms_internal_ads_zzaqw.zzb("/untrackActiveViewUnit", this.zzaft);
        com_google_android_gms_internal_ads_zzaqw.zzb("/updateActiveView", this.zzafs);
    }
}
