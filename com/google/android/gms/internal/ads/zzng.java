package com.google.android.gms.internal.ads;

import android.content.SharedPreferences.Editor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.json.JSONObject;

@zzadh
public final class zzng {
    private final Collection<zzna<?>> zzats = new ArrayList();
    private final Collection<zzna<String>> zzatt = new ArrayList();
    private final Collection<zzna<String>> zzatu = new ArrayList();

    public final void zza(Editor editor, int i, JSONObject jSONObject) {
        for (zzna com_google_android_gms_internal_ads_zzna : this.zzats) {
            if (com_google_android_gms_internal_ads_zzna.getSource() == 1) {
                com_google_android_gms_internal_ads_zzna.zza(editor, com_google_android_gms_internal_ads_zzna.zzb(jSONObject));
            }
        }
    }

    public final void zza(zzna com_google_android_gms_internal_ads_zzna) {
        this.zzats.add(com_google_android_gms_internal_ads_zzna);
    }

    public final void zzb(zzna<String> com_google_android_gms_internal_ads_zzna_java_lang_String) {
        this.zzatt.add(com_google_android_gms_internal_ads_zzna_java_lang_String);
    }

    public final void zzc(zzna<String> com_google_android_gms_internal_ads_zzna_java_lang_String) {
        this.zzatu.add(com_google_android_gms_internal_ads_zzna_java_lang_String);
    }

    public final List<String> zzjb() {
        List<String> arrayList = new ArrayList();
        for (zzna zzd : this.zzatt) {
            String str = (String) zzkb.zzik().zzd(zzd);
            if (str != null) {
                arrayList.add(str);
            }
        }
        return arrayList;
    }

    public final List<String> zzjc() {
        List<String> zzjb = zzjb();
        for (zzna zzd : this.zzatu) {
            String str = (String) zzkb.zzik().zzd(zzd);
            if (str != null) {
                zzjb.add(str);
            }
        }
        return zzjb;
    }
}
