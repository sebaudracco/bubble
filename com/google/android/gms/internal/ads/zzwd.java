package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.gmsg.zzv;
import java.util.AbstractMap.SimpleEntry;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONObject;

@zzadh
public final class zzwd implements zzuo, zzwc {
    private final zzwb zzbqz;
    private final HashSet<SimpleEntry<String, zzv<? super zzwb>>> zzbra = new HashSet();

    public zzwd(zzwb com_google_android_gms_internal_ads_zzwb) {
        this.zzbqz = com_google_android_gms_internal_ads_zzwb;
    }

    public final void zza(String str, zzv<? super zzwb> com_google_android_gms_ads_internal_gmsg_zzv__super_com_google_android_gms_internal_ads_zzwb) {
        this.zzbqz.zza(str, com_google_android_gms_ads_internal_gmsg_zzv__super_com_google_android_gms_internal_ads_zzwb);
        this.zzbra.add(new SimpleEntry(str, com_google_android_gms_ads_internal_gmsg_zzv__super_com_google_android_gms_internal_ads_zzwb));
    }

    public final void zza(String str, Map map) {
        zzup.zza(this, str, map);
    }

    public final void zza(String str, JSONObject jSONObject) {
        zzup.zzb(this, str, jSONObject);
    }

    public final void zzb(String str, zzv<? super zzwb> com_google_android_gms_ads_internal_gmsg_zzv__super_com_google_android_gms_internal_ads_zzwb) {
        this.zzbqz.zzb(str, com_google_android_gms_ads_internal_gmsg_zzv__super_com_google_android_gms_internal_ads_zzwb);
        this.zzbra.remove(new SimpleEntry(str, com_google_android_gms_ads_internal_gmsg_zzv__super_com_google_android_gms_internal_ads_zzwb));
    }

    public final void zzb(String str, JSONObject jSONObject) {
        zzup.zza(this, str, jSONObject);
    }

    public final void zzbe(String str) {
        this.zzbqz.zzbe(str);
    }

    public final void zzf(String str, String str2) {
        zzup.zza(this, str, str2);
    }

    public final void zzmd() {
        Iterator it = this.zzbra.iterator();
        while (it.hasNext()) {
            SimpleEntry simpleEntry = (SimpleEntry) it.next();
            String str = "Unregistering eventhandler: ";
            String valueOf = String.valueOf(((zzv) simpleEntry.getValue()).toString());
            zzakb.m3428v(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
            this.zzbqz.zzb((String) simpleEntry.getKey(), (zzv) simpleEntry.getValue());
        }
        this.zzbra.clear();
    }
}
