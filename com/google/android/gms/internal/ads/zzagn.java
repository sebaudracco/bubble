package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.internal.zzbv;
import org.json.JSONObject;

@zzadh
public final class zzagn implements zzafr {
    private zzwf<JSONObject, JSONObject> zzcko;
    private zzwf<JSONObject, JSONObject> zzckt;

    public zzagn(Context context) {
        this.zzckt = zzbv.zzey().zzb(context, zzang.zzsl()).zza("google.afma.request.getAdDictionary", zzwk.zzbrc, zzwk.zzbrc);
        this.zzcko = zzbv.zzey().zzb(context, zzang.zzsl()).zza("google.afma.sdkConstants.getSdkConstants", zzwk.zzbrc, zzwk.zzbrc);
    }

    public final zzwf<JSONObject, JSONObject> zzof() {
        return this.zzckt;
    }

    public final zzwf<JSONObject, JSONObject> zzog() {
        return this.zzcko;
    }
}
