package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.gmsg.zzf;
import com.google.android.gms.ads.internal.zzbv;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import org.json.JSONObject;

@zzadh
@ParametersAreNonnullByDefault
public final class zzwq<I, O> implements zzwf<I, O> {
    private final zzvf zzbrh;
    private final zzwh<O> zzbri;
    private final zzwi<I> zzbrj;
    private final String zzbrk;

    zzwq(zzvf com_google_android_gms_internal_ads_zzvf, String str, zzwi<I> com_google_android_gms_internal_ads_zzwi_I, zzwh<O> com_google_android_gms_internal_ads_zzwh_O) {
        this.zzbrh = com_google_android_gms_internal_ads_zzvf;
        this.zzbrk = str;
        this.zzbrj = com_google_android_gms_internal_ads_zzwi_I;
        this.zzbri = com_google_android_gms_internal_ads_zzwh_O;
    }

    private final void zza(zzvs com_google_android_gms_internal_ads_zzvs, zzwb com_google_android_gms_internal_ads_zzwb, I i, zzaoj<O> com_google_android_gms_internal_ads_zzaoj_O) {
        try {
            zzbv.zzek();
            String zzrh = zzakk.zzrh();
            zzf.zzbmc.zza(zzrh, new zzwt(this, com_google_android_gms_internal_ads_zzvs, com_google_android_gms_internal_ads_zzaoj_O));
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("id", zzrh);
            jSONObject.put("args", this.zzbrj.zzg(i));
            com_google_android_gms_internal_ads_zzwb.zzb(this.zzbrk, jSONObject);
        } catch (Throwable e) {
            com_google_android_gms_internal_ads_zzaoj_O.setException(e);
            zzane.zzb("Unable to invokeJavaScript", e);
        } finally {
            com_google_android_gms_internal_ads_zzvs.release();
        }
    }

    public final zzanz<O> zzc(@Nullable I i) throws Exception {
        return zzf(i);
    }

    public final zzanz<O> zzf(I i) {
        zzanz com_google_android_gms_internal_ads_zzaoj = new zzaoj();
        zzaop zzb = this.zzbrh.zzb(null);
        zzb.zza(new zzwr(this, zzb, i, com_google_android_gms_internal_ads_zzaoj), new zzws(this, com_google_android_gms_internal_ads_zzaoj, zzb));
        return com_google_android_gms_internal_ads_zzaoj;
    }
}
