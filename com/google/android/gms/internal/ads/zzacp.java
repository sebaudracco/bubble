package com.google.android.gms.internal.ads;

import android.support.v4.util.SimpleArrayMap;
import android.view.View;
import com.silvermob.sdk.Const.BannerType;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@zzadh
public final class zzacp implements zzacd<zzos> {
    private final boolean zzcbk;

    public zzacp(boolean z) {
        this.zzcbk = z;
    }

    public final /* synthetic */ zzpb zza(zzabv com_google_android_gms_internal_ads_zzabv, JSONObject jSONObject) throws JSONException, InterruptedException, ExecutionException {
        String valueOf;
        View view = null;
        SimpleArrayMap simpleArrayMap = new SimpleArrayMap();
        SimpleArrayMap simpleArrayMap2 = new SimpleArrayMap();
        Future zzg = com_google_android_gms_internal_ads_zzabv.zzg(jSONObject);
        zzanz zzc = com_google_android_gms_internal_ads_zzabv.zzc(jSONObject, "video");
        JSONArray jSONArray = jSONObject.getJSONArray("custom_assets");
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject jSONObject2 = jSONArray.getJSONObject(i);
            String string = jSONObject2.getString("type");
            if (SchemaSymbols.ATTVAL_STRING.equals(string)) {
                simpleArrayMap2.put(jSONObject2.getString("name"), jSONObject2.getString("string_value"));
            } else if (BannerType.IMAGE.equals(string)) {
                simpleArrayMap.put(jSONObject2.getString("name"), com_google_android_gms_internal_ads_zzabv.zza(jSONObject2, "image_value", this.zzcbk));
            } else {
                String str = "Unknown custom asset type: ";
                valueOf = String.valueOf(string);
                zzane.zzdk(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
            }
        }
        zzaqw zzc2 = zzabv.zzc(zzc);
        valueOf = jSONObject.getString("custom_template_id");
        SimpleArrayMap simpleArrayMap3 = new SimpleArrayMap();
        for (int i2 = 0; i2 < simpleArrayMap.size(); i2++) {
            simpleArrayMap3.put(simpleArrayMap.keyAt(i2), ((Future) simpleArrayMap.valueAt(i2)).get());
        }
        zzoj com_google_android_gms_internal_ads_zzoj = (zzoj) zzg.get();
        zzlo zztm = zzc2 != null ? zzc2.zztm() : null;
        if (zzc2 != null) {
            view = zzc2.getView();
        }
        return new zzos(valueOf, simpleArrayMap3, simpleArrayMap2, com_google_android_gms_internal_ads_zzoj, zztm, view);
    }
}
