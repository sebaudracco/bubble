package com.google.android.gms.internal.ads;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import org.json.JSONObject;

final class zzake extends zzakg {
    private final /* synthetic */ Context zzcrg;
    private final /* synthetic */ zzakd zzcrh;

    zzake(zzakd com_google_android_gms_internal_ads_zzakd, Context context) {
        this.zzcrh = com_google_android_gms_internal_ads_zzakd;
        this.zzcrg = context;
        super();
    }

    public final void zzdn() {
        SharedPreferences sharedPreferences = this.zzcrg.getSharedPreferences("admob", 0);
        Editor edit = sharedPreferences.edit();
        synchronized (zzakd.zza(this.zzcrh)) {
            zzakd.zza(this.zzcrh, sharedPreferences);
            this.zzcrh.zzcqw = edit;
            zzakd.zza(this.zzcrh, zzakd.zzb(this.zzcrh));
            zzakd.zzb(this.zzcrh, zzakd.zzd(this.zzcrh).getBoolean("use_https", zzakd.zzc(this.zzcrh)));
            zzakd.zzc(this.zzcrh, zzakd.zzd(this.zzcrh).getBoolean("content_url_opted_out", zzakd.zze(this.zzcrh)));
            zzakd.zza(this.zzcrh, zzakd.zzd(this.zzcrh).getString("content_url_hashes", zzakd.zzf(this.zzcrh)));
            zzakd.zzd(this.zzcrh, zzakd.zzd(this.zzcrh).getBoolean("auto_collect_location", zzakd.zzg(this.zzcrh)));
            zzakd.zze(this.zzcrh, zzakd.zzd(this.zzcrh).getBoolean("content_vertical_opted_out", zzakd.zzh(this.zzcrh)));
            zzakd.zzb(this.zzcrh, zzakd.zzd(this.zzcrh).getString("content_vertical_hashes", zzakd.zzi(this.zzcrh)));
            zzakd.zza(this.zzcrh, zzakd.zzd(this.zzcrh).getInt("version_code", zzakd.zzj(this.zzcrh)));
            zzakd.zzc(this.zzcrh, zzakd.zzd(this.zzcrh).getString("app_settings_json", zzakd.zzk(this.zzcrh)));
            zzakd.zza(this.zzcrh, zzakd.zzd(this.zzcrh).getLong("app_settings_last_update_ms", zzakd.zzl(this.zzcrh)));
            zzakd.zzb(this.zzcrh, zzakd.zzd(this.zzcrh).getLong("app_last_background_time_ms", zzakd.zzm(this.zzcrh)));
            zzakd.zzb(this.zzcrh, zzakd.zzd(this.zzcrh).getInt("request_in_session_count", zzakd.zzn(this.zzcrh)));
            zzakd.zzc(this.zzcrh, zzakd.zzd(this.zzcrh).getLong("first_ad_req_time_ms", zzakd.zzo(this.zzcrh)));
            zzakd.zza(this.zzcrh, zzakd.zzd(this.zzcrh).getStringSet("never_pool_slots", zzakd.zzp(this.zzcrh)));
            try {
                zzakd.zza(this.zzcrh, new JSONObject(zzakd.zzd(this.zzcrh).getString("native_advanced_settings", "{}")));
            } catch (Throwable e) {
                zzakb.zzc("Could not convert native advanced settings to json object", e);
            }
            zzakd.zza(this.zzcrh, zzakd.zzq(this.zzcrh));
        }
    }
}
