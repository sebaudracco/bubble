package com.google.android.gms.internal.ads;

import android.content.res.Resources;
import android.os.Bundle;
import com.google.android.gms.ads.impl.R;
import com.google.android.gms.ads.internal.zzbv;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import org.json.JSONException;
import org.json.JSONObject;

@zzadh
public final class zzaco implements zzacd<zzoq> {
    private final boolean zzbto;
    private final boolean zzcbk;
    private final boolean zzcbl;

    public zzaco(boolean z, boolean z2, boolean z3) {
        this.zzcbk = z;
        this.zzcbl = z2;
        this.zzbto = z3;
    }

    public final /* synthetic */ zzpb zza(zzabv com_google_android_gms_internal_ads_zzabv, JSONObject jSONObject) throws JSONException, InterruptedException, ExecutionException {
        String string;
        List<zzanz> zza = com_google_android_gms_internal_ads_zzabv.zza(jSONObject, "images", false, this.zzcbk, this.zzcbl);
        Future zza2 = com_google_android_gms_internal_ads_zzabv.zza(jSONObject, "secondary_image", false, this.zzcbk);
        Future zzg = com_google_android_gms_internal_ads_zzabv.zzg(jSONObject);
        zzanz zzc = com_google_android_gms_internal_ads_zzabv.zzc(jSONObject, "video");
        List arrayList = new ArrayList();
        for (zzanz com_google_android_gms_internal_ads_zzanz : zza) {
            arrayList.add((zzon) com_google_android_gms_internal_ads_zzanz.get());
        }
        zzaqw zzc2 = zzabv.zzc(zzc);
        String string2 = jSONObject.getString("headline");
        if (this.zzbto) {
            if (((Boolean) zzkb.zzik().zzd(zznk.zzbfr)).booleanValue()) {
                Resources resources = zzbv.zzeo().getResources();
                string = resources != null ? resources.getString(R.string.s7) : "Test Ad";
                if (string2 != null) {
                    string = new StringBuilder((String.valueOf(string).length() + 3) + String.valueOf(string2).length()).append(string).append(" : ").append(string2).toString();
                }
                return new zzoq(string, arrayList, jSONObject.getString("body"), (zzpw) zza2.get(), jSONObject.getString("call_to_action"), jSONObject.getString("advertiser"), (zzoj) zzg.get(), new Bundle(), zzc2 == null ? zzc2.zztm() : null, zzc2 == null ? zzc2.getView() : null, null, null);
            }
        }
        string = string2;
        if (zzc2 == null) {
        }
        if (zzc2 == null) {
        }
        return new zzoq(string, arrayList, jSONObject.getString("body"), (zzpw) zza2.get(), jSONObject.getString("call_to_action"), jSONObject.getString("advertiser"), (zzoj) zzg.get(), new Bundle(), zzc2 == null ? zzc2.zztm() : null, zzc2 == null ? zzc2.getView() : null, null, null);
    }
}
