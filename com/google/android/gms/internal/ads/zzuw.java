package com.google.android.gms.internal.ads;

import android.content.Context;
import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.gmsg.zzv;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.ads.internal.zzw;
import java.util.Map;
import javax.annotation.ParametersAreNonnullByDefault;
import org.json.JSONObject;

@zzadh
@ParametersAreNonnullByDefault
public final class zzuw implements zzuo, zzuu {
    private final Context mContext;
    private final zzaqw zzbnd;

    public zzuw(Context context, zzang com_google_android_gms_internal_ads_zzang, @Nullable zzci com_google_android_gms_internal_ads_zzci, zzw com_google_android_gms_ads_internal_zzw) throws zzarg {
        this.mContext = context;
        zzbv.zzel();
        this.zzbnd = zzarc.zza(context, zzasi.zzvq(), "", false, false, com_google_android_gms_internal_ads_zzci, com_google_android_gms_internal_ads_zzang, null, null, null, zzhs.zzhm());
        this.zzbnd.getView().setWillNotDraw(true);
    }

    private static void runOnUiThread(Runnable runnable) {
        zzkb.zzif();
        if (zzamu.zzsh()) {
            runnable.run();
        } else {
            zzakk.zzcrm.post(runnable);
        }
    }

    public final void destroy() {
        this.zzbnd.destroy();
    }

    public final void zza(zzuv com_google_android_gms_internal_ads_zzuv) {
        zzasc zzuf = this.zzbnd.zzuf();
        com_google_android_gms_internal_ads_zzuv.getClass();
        zzuf.zza(zzuz.zzb(com_google_android_gms_internal_ads_zzuv));
    }

    public final void zza(String str, zzv<? super zzwb> com_google_android_gms_ads_internal_gmsg_zzv__super_com_google_android_gms_internal_ads_zzwb) {
        this.zzbnd.zza(str, new zzvd(this, com_google_android_gms_ads_internal_gmsg_zzv__super_com_google_android_gms_internal_ads_zzwb));
    }

    public final void zza(String str, Map map) {
        zzup.zza(this, str, map);
    }

    public final void zza(String str, JSONObject jSONObject) {
        zzup.zzb(this, str, jSONObject);
    }

    public final void zzb(String str, zzv<? super zzwb> com_google_android_gms_ads_internal_gmsg_zzv__super_com_google_android_gms_internal_ads_zzwb) {
        this.zzbnd.zza(str, new zzuy(com_google_android_gms_ads_internal_gmsg_zzv__super_com_google_android_gms_internal_ads_zzwb));
    }

    public final void zzb(String str, JSONObject jSONObject) {
        zzup.zza(this, str, jSONObject);
    }

    public final void zzbb(String str) {
        runOnUiThread(new zzva(this, String.format("<!DOCTYPE html><html><head><script src=\"%s\"></script></head><body></body></html>", new Object[]{str})));
    }

    public final void zzbc(String str) {
        runOnUiThread(new zzvb(this, str));
    }

    public final void zzbd(String str) {
        runOnUiThread(new zzvc(this, str));
    }

    public final void zzbe(String str) {
        runOnUiThread(new zzux(this, str));
    }

    final /* synthetic */ void zzbi(String str) {
        this.zzbnd.zzbe(str);
    }

    public final void zzf(String str, String str2) {
        zzup.zza(this, str, str2);
    }

    public final zzwc zzlw() {
        return new zzwd(this);
    }
}
