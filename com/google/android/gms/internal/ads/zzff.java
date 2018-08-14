package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.internal.gmsg.zzab;
import com.google.android.gms.ads.internal.gmsg.zzv;
import com.google.android.gms.ads.internal.zzbv;
import org.json.JSONObject;

@zzadh
public final class zzff implements zzfo {
    private final Context mContext;
    private final zzet zzafq;
    private final zzv<zzwb> zzafs = new zzfk(this);
    private final zzv<zzwb> zzaft = new zzfl(this);
    private final zzv<zzwb> zzafu = new zzfm(this);
    private final zzab zzafw;
    private zzvs zzafx;
    private boolean zzafy;
    private final zzv<zzwb> zzafz = new zzfn(this);

    public zzff(zzet com_google_android_gms_internal_ads_zzet, zzvf com_google_android_gms_internal_ads_zzvf, Context context) {
        this.zzafq = com_google_android_gms_internal_ads_zzet;
        this.mContext = context;
        this.zzafw = new zzab(this.mContext);
        this.zzafx = com_google_android_gms_internal_ads_zzvf.zzb(null);
        this.zzafx.zza(new zzfg(this), new zzfh(this));
        String str = "Core JS tracking ad unit: ";
        String valueOf = String.valueOf(this.zzafq.zzaet.zzfy());
        zzane.zzck(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
    }

    final void zza(zzwb com_google_android_gms_internal_ads_zzwb) {
        com_google_android_gms_internal_ads_zzwb.zza("/updateActiveView", this.zzafs);
        com_google_android_gms_internal_ads_zzwb.zza("/untrackActiveViewUnit", this.zzaft);
        com_google_android_gms_internal_ads_zzwb.zza("/visibilityChanged", this.zzafu);
        if (zzbv.zzfh().zzs(this.mContext)) {
            com_google_android_gms_internal_ads_zzwb.zza("/logScionEvent", this.zzafz);
        }
    }

    final void zzb(zzwb com_google_android_gms_internal_ads_zzwb) {
        com_google_android_gms_internal_ads_zzwb.zzb("/visibilityChanged", this.zzafu);
        com_google_android_gms_internal_ads_zzwb.zzb("/untrackActiveViewUnit", this.zzaft);
        com_google_android_gms_internal_ads_zzwb.zzb("/updateActiveView", this.zzafs);
        if (zzbv.zzfh().zzs(this.mContext)) {
            com_google_android_gms_internal_ads_zzwb.zzb("/logScionEvent", this.zzafz);
        }
    }

    public final void zzb(JSONObject jSONObject, boolean z) {
        this.zzafx.zza(new zzfi(this, jSONObject), new zzaon());
    }

    public final boolean zzgk() {
        return this.zzafy;
    }

    public final void zzgl() {
        this.zzafx.zza(new zzfj(this), new zzaon());
        this.zzafx.release();
    }
}
