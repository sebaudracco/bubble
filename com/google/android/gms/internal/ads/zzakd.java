package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.security.NetworkSecurityPolicy;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.common.util.PlatformVersion;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import org.json.JSONArray;
import org.json.JSONObject;

@zzadh
public final class zzakd {
    private final Object mLock = new Object();
    @GuardedBy("mLock")
    @Nullable
    private SharedPreferences zzatw;
    @GuardedBy("mLock")
    private boolean zzcik = true;
    @GuardedBy("mLock")
    private boolean zzcil = true;
    @GuardedBy("mLock")
    private boolean zzcim = true;
    @GuardedBy("mLock")
    private boolean zzcit = false;
    @GuardedBy("mLock")
    private String zzcpj = "";
    @GuardedBy("mLock")
    private int zzcqg = -1;
    private zzanz<?> zzcqu;
    private CopyOnWriteArraySet<zzakh> zzcqv = new CopyOnWriteArraySet();
    @GuardedBy("mLock")
    @Nullable
    Editor zzcqw;
    @GuardedBy("mLock")
    private boolean zzcqx = false;
    @GuardedBy("mLock")
    @Nullable
    private String zzcqy;
    @GuardedBy("mLock")
    @Nullable
    private String zzcqz;
    @GuardedBy("mLock")
    private long zzcra = 0;
    @GuardedBy("mLock")
    private long zzcrb = 0;
    @GuardedBy("mLock")
    private long zzcrc = 0;
    @GuardedBy("mLock")
    private int zzcrd = 0;
    @GuardedBy("mLock")
    private Set<String> zzcre = Collections.emptySet();
    @GuardedBy("mLock")
    private JSONObject zzcrf = new JSONObject();

    private final void zze(Bundle bundle) {
        new zzakf(this, bundle).zznt();
    }

    @TargetApi(23)
    private static boolean zzqq() {
        return PlatformVersion.isAtLeastM() && !NetworkSecurityPolicy.getInstance().isCleartextTrafficPermitted();
    }

    private final void zzqr() {
        Throwable e;
        if (this.zzcqu != null && !this.zzcqu.isDone()) {
            try {
                this.zzcqu.get(1, TimeUnit.SECONDS);
            } catch (Throwable e2) {
                Thread.currentThread().interrupt();
                zzane.zzc("Interrupted while waiting for preferences loaded.", e2);
            } catch (CancellationException e3) {
                e2 = e3;
                zzane.zzb("Fail to initialize AdSharedPreferenceManager.", e2);
            } catch (ExecutionException e4) {
                e2 = e4;
                zzane.zzb("Fail to initialize AdSharedPreferenceManager.", e2);
            } catch (TimeoutException e5) {
                e2 = e5;
                zzane.zzb("Fail to initialize AdSharedPreferenceManager.", e2);
            }
        }
    }

    private final Bundle zzqs() {
        Bundle bundle = new Bundle();
        bundle.putBoolean("listener_registration_bundle", true);
        synchronized (this.mLock) {
            bundle.putBoolean("use_https", this.zzcik);
            bundle.putBoolean("content_url_opted_out", this.zzcil);
            bundle.putBoolean("content_vertical_opted_out", this.zzcim);
            bundle.putBoolean("auto_collect_location", this.zzcit);
            bundle.putInt("version_code", this.zzcrd);
            bundle.putStringArray("never_pool_slots", (String[]) this.zzcre.toArray(new String[this.zzcre.size()]));
            bundle.putString("app_settings_json", this.zzcpj);
            bundle.putLong("app_settings_last_update_ms", this.zzcra);
            bundle.putLong("app_last_background_time_ms", this.zzcrb);
            bundle.putInt("request_in_session_count", this.zzcqg);
            bundle.putLong("first_ad_req_time_ms", this.zzcrc);
            bundle.putString("native_advanced_settings", this.zzcrf.toString());
            if (this.zzcqy != null) {
                bundle.putString("content_url_hashes", this.zzcqy);
            }
            if (this.zzcqz != null) {
                bundle.putString("content_vertical_hashes", this.zzcqz);
            }
        }
        return bundle;
    }

    public final void initialize(Context context) {
        if (context.getApplicationContext() != null) {
            context = context.getApplicationContext();
        }
        this.zzcqu = (zzanz) new zzake(this, context).zznt();
    }

    public final void zza(zzakh com_google_android_gms_internal_ads_zzakh) {
        synchronized (this.mLock) {
            if (this.zzcqu != null && this.zzcqu.isDone()) {
                com_google_android_gms_internal_ads_zzakh.zzd(zzqs());
            }
            this.zzcqv.add(com_google_android_gms_internal_ads_zzakh);
        }
    }

    public final void zza(String str, String str2, boolean z) {
        int i = 0;
        zzqr();
        synchronized (this.mLock) {
            Bundle bundle;
            JSONArray optJSONArray = this.zzcrf.optJSONArray(str);
            JSONArray jSONArray = optJSONArray == null ? new JSONArray() : optJSONArray;
            int length = jSONArray.length();
            while (i < jSONArray.length()) {
                JSONObject optJSONObject = jSONArray.optJSONObject(i);
                if (optJSONObject == null) {
                    return;
                } else if (str2.equals(optJSONObject.optString("template_id"))) {
                    if (z && optJSONObject.optBoolean("uses_media_view", false) == z) {
                        return;
                    }
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("template_id", str2);
                    jSONObject.put("uses_media_view", z);
                    jSONObject.put("timestamp_ms", zzbv.zzer().currentTimeMillis());
                    jSONArray.put(i, jSONObject);
                    this.zzcrf.put(str, jSONArray);
                    if (this.zzcqw != null) {
                        this.zzcqw.putString("native_advanced_settings", this.zzcrf.toString());
                        this.zzcqw.apply();
                    }
                    bundle = new Bundle();
                    bundle.putString("native_advanced_settings", this.zzcrf.toString());
                    zze(bundle);
                } else {
                    i++;
                }
            }
            i = length;
            try {
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("template_id", str2);
                jSONObject2.put("uses_media_view", z);
                jSONObject2.put("timestamp_ms", zzbv.zzer().currentTimeMillis());
                jSONArray.put(i, jSONObject2);
                this.zzcrf.put(str, jSONArray);
            } catch (Throwable e) {
                zzane.zzc("Could not update native advanced settings", e);
            }
            if (this.zzcqw != null) {
                this.zzcqw.putString("native_advanced_settings", this.zzcrf.toString());
                this.zzcqw.apply();
            }
            bundle = new Bundle();
            bundle.putString("native_advanced_settings", this.zzcrf.toString());
            zze(bundle);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzab(boolean r4) {
        /*
        r3 = this;
        r3.zzqr();
        r1 = r3.mLock;
        monitor-enter(r1);
        r0 = r3.zzcik;	 Catch:{ all -> 0x0033 }
        if (r0 != r4) goto L_0x000c;
    L_0x000a:
        monitor-exit(r1);	 Catch:{ all -> 0x0033 }
    L_0x000b:
        return;
    L_0x000c:
        r3.zzcik = r4;	 Catch:{ all -> 0x0033 }
        r0 = r3.zzcqw;	 Catch:{ all -> 0x0033 }
        if (r0 == 0) goto L_0x001f;
    L_0x0012:
        r0 = r3.zzcqw;	 Catch:{ all -> 0x0033 }
        r2 = "use_https";
        r0.putBoolean(r2, r4);	 Catch:{ all -> 0x0033 }
        r0 = r3.zzcqw;	 Catch:{ all -> 0x0033 }
        r0.apply();	 Catch:{ all -> 0x0033 }
    L_0x001f:
        r0 = r3.zzcqx;	 Catch:{ all -> 0x0033 }
        if (r0 != 0) goto L_0x0031;
    L_0x0023:
        r0 = new android.os.Bundle;	 Catch:{ all -> 0x0033 }
        r0.<init>();	 Catch:{ all -> 0x0033 }
        r2 = "use_https";
        r0.putBoolean(r2, r4);	 Catch:{ all -> 0x0033 }
        r3.zze(r0);	 Catch:{ all -> 0x0033 }
    L_0x0031:
        monitor-exit(r1);	 Catch:{ all -> 0x0033 }
        goto L_0x000b;
    L_0x0033:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x0033 }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzakd.zzab(boolean):void");
    }

    public final void zzac(boolean z) {
        zzqr();
        synchronized (this.mLock) {
            if (this.zzcil == z) {
                return;
            }
            this.zzcil = z;
            if (this.zzcqw != null) {
                this.zzcqw.putBoolean("content_url_opted_out", z);
                this.zzcqw.apply();
            }
            Bundle bundle = new Bundle();
            bundle.putBoolean("content_url_opted_out", this.zzcil);
            bundle.putBoolean("content_vertical_opted_out", this.zzcim);
            zze(bundle);
        }
    }

    public final void zzad(boolean z) {
        zzqr();
        synchronized (this.mLock) {
            if (this.zzcim == z) {
                return;
            }
            this.zzcim = z;
            if (this.zzcqw != null) {
                this.zzcqw.putBoolean("content_vertical_opted_out", z);
                this.zzcqw.apply();
            }
            Bundle bundle = new Bundle();
            bundle.putBoolean("content_url_opted_out", this.zzcil);
            bundle.putBoolean("content_vertical_opted_out", this.zzcim);
            zze(bundle);
        }
    }

    public final void zzae(int i) {
        zzqr();
        synchronized (this.mLock) {
            if (this.zzcrd == i) {
                return;
            }
            this.zzcrd = i;
            if (this.zzcqw != null) {
                this.zzcqw.putInt("version_code", i);
                this.zzcqw.apply();
            }
            Bundle bundle = new Bundle();
            bundle.putInt("version_code", i);
            zze(bundle);
        }
    }

    public final void zzae(boolean z) {
        zzqr();
        synchronized (this.mLock) {
            if (this.zzcit == z) {
                return;
            }
            this.zzcit = z;
            if (this.zzcqw != null) {
                this.zzcqw.putBoolean("auto_collect_location", z);
                this.zzcqw.apply();
            }
            Bundle bundle = new Bundle();
            bundle.putBoolean("auto_collect_location", z);
            zze(bundle);
        }
    }

    public final void zzaf(int i) {
        zzqr();
        synchronized (this.mLock) {
            if (this.zzcqg == i) {
                return;
            }
            this.zzcqg = i;
            if (this.zzcqw != null) {
                this.zzcqw.putInt("request_in_session_count", i);
                this.zzcqw.apply();
            }
            Bundle bundle = new Bundle();
            bundle.putInt("request_in_session_count", i);
            zze(bundle);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzcn(@javax.annotation.Nullable java.lang.String r4) {
        /*
        r3 = this;
        r3.zzqr();
        r1 = r3.mLock;
        monitor-enter(r1);
        if (r4 == 0) goto L_0x0010;
    L_0x0008:
        r0 = r3.zzcqy;	 Catch:{ all -> 0x0035 }
        r0 = r4.equals(r0);	 Catch:{ all -> 0x0035 }
        if (r0 == 0) goto L_0x0012;
    L_0x0010:
        monitor-exit(r1);	 Catch:{ all -> 0x0035 }
    L_0x0011:
        return;
    L_0x0012:
        r3.zzcqy = r4;	 Catch:{ all -> 0x0035 }
        r0 = r3.zzcqw;	 Catch:{ all -> 0x0035 }
        if (r0 == 0) goto L_0x0025;
    L_0x0018:
        r0 = r3.zzcqw;	 Catch:{ all -> 0x0035 }
        r2 = "content_url_hashes";
        r0.putString(r2, r4);	 Catch:{ all -> 0x0035 }
        r0 = r3.zzcqw;	 Catch:{ all -> 0x0035 }
        r0.apply();	 Catch:{ all -> 0x0035 }
    L_0x0025:
        r0 = new android.os.Bundle;	 Catch:{ all -> 0x0035 }
        r0.<init>();	 Catch:{ all -> 0x0035 }
        r2 = "content_url_hashes";
        r0.putString(r2, r4);	 Catch:{ all -> 0x0035 }
        r3.zze(r0);	 Catch:{ all -> 0x0035 }
        monitor-exit(r1);	 Catch:{ all -> 0x0035 }
        goto L_0x0011;
    L_0x0035:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x0035 }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzakd.zzcn(java.lang.String):void");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzco(@javax.annotation.Nullable java.lang.String r4) {
        /*
        r3 = this;
        r3.zzqr();
        r1 = r3.mLock;
        monitor-enter(r1);
        if (r4 == 0) goto L_0x0010;
    L_0x0008:
        r0 = r3.zzcqz;	 Catch:{ all -> 0x0035 }
        r0 = r4.equals(r0);	 Catch:{ all -> 0x0035 }
        if (r0 == 0) goto L_0x0012;
    L_0x0010:
        monitor-exit(r1);	 Catch:{ all -> 0x0035 }
    L_0x0011:
        return;
    L_0x0012:
        r3.zzcqz = r4;	 Catch:{ all -> 0x0035 }
        r0 = r3.zzcqw;	 Catch:{ all -> 0x0035 }
        if (r0 == 0) goto L_0x0025;
    L_0x0018:
        r0 = r3.zzcqw;	 Catch:{ all -> 0x0035 }
        r2 = "content_vertical_hashes";
        r0.putString(r2, r4);	 Catch:{ all -> 0x0035 }
        r0 = r3.zzcqw;	 Catch:{ all -> 0x0035 }
        r0.apply();	 Catch:{ all -> 0x0035 }
    L_0x0025:
        r0 = new android.os.Bundle;	 Catch:{ all -> 0x0035 }
        r0.<init>();	 Catch:{ all -> 0x0035 }
        r2 = "content_vertical_hashes";
        r0.putString(r2, r4);	 Catch:{ all -> 0x0035 }
        r3.zze(r0);	 Catch:{ all -> 0x0035 }
        monitor-exit(r1);	 Catch:{ all -> 0x0035 }
        goto L_0x0011;
    L_0x0035:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x0035 }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzakd.zzco(java.lang.String):void");
    }

    public final void zzcp(String str) {
        zzqr();
        synchronized (this.mLock) {
            if (this.zzcre.contains(str)) {
                return;
            }
            this.zzcre.add(str);
            if (this.zzcqw != null) {
                this.zzcqw.putStringSet("never_pool_slots", this.zzcre);
                this.zzcqw.apply();
            }
            Bundle bundle = new Bundle();
            bundle.putStringArray("never_pool_slots", (String[]) this.zzcre.toArray(new String[this.zzcre.size()]));
            zze(bundle);
        }
    }

    public final void zzcq(String str) {
        zzqr();
        synchronized (this.mLock) {
            if (this.zzcre.contains(str)) {
                this.zzcre.remove(str);
                if (this.zzcqw != null) {
                    this.zzcqw.putStringSet("never_pool_slots", this.zzcre);
                    this.zzcqw.apply();
                }
                Bundle bundle = new Bundle();
                bundle.putStringArray("never_pool_slots", (String[]) this.zzcre.toArray(new String[this.zzcre.size()]));
                zze(bundle);
                return;
            }
        }
    }

    public final boolean zzcr(String str) {
        boolean contains;
        zzqr();
        synchronized (this.mLock) {
            contains = this.zzcre.contains(str);
        }
        return contains;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzcs(java.lang.String r6) {
        /*
        r5 = this;
        r5.zzqr();
        r1 = r5.mLock;
        monitor-enter(r1);
        r0 = com.google.android.gms.ads.internal.zzbv.zzer();	 Catch:{ all -> 0x004d }
        r2 = r0.currentTimeMillis();	 Catch:{ all -> 0x004d }
        r5.zzcra = r2;	 Catch:{ all -> 0x004d }
        if (r6 == 0) goto L_0x001a;
    L_0x0012:
        r0 = r5.zzcpj;	 Catch:{ all -> 0x004d }
        r0 = r6.equals(r0);	 Catch:{ all -> 0x004d }
        if (r0 == 0) goto L_0x001c;
    L_0x001a:
        monitor-exit(r1);	 Catch:{ all -> 0x004d }
    L_0x001b:
        return;
    L_0x001c:
        r5.zzcpj = r6;	 Catch:{ all -> 0x004d }
        r0 = r5.zzcqw;	 Catch:{ all -> 0x004d }
        if (r0 == 0) goto L_0x0037;
    L_0x0022:
        r0 = r5.zzcqw;	 Catch:{ all -> 0x004d }
        r4 = "app_settings_json";
        r0.putString(r4, r6);	 Catch:{ all -> 0x004d }
        r0 = r5.zzcqw;	 Catch:{ all -> 0x004d }
        r4 = "app_settings_last_update_ms";
        r0.putLong(r4, r2);	 Catch:{ all -> 0x004d }
        r0 = r5.zzcqw;	 Catch:{ all -> 0x004d }
        r0.apply();	 Catch:{ all -> 0x004d }
    L_0x0037:
        r0 = new android.os.Bundle;	 Catch:{ all -> 0x004d }
        r0.<init>();	 Catch:{ all -> 0x004d }
        r4 = "app_settings_json";
        r0.putString(r4, r6);	 Catch:{ all -> 0x004d }
        r4 = "app_settings_last_update_ms";
        r0.putLong(r4, r2);	 Catch:{ all -> 0x004d }
        r5.zze(r0);	 Catch:{ all -> 0x004d }
        monitor-exit(r1);	 Catch:{ all -> 0x004d }
        goto L_0x001b;
    L_0x004d:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x004d }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzakd.zzcs(java.lang.String):void");
    }

    public final void zzj(long j) {
        zzqr();
        synchronized (this.mLock) {
            if (this.zzcrb == j) {
                return;
            }
            this.zzcrb = j;
            if (this.zzcqw != null) {
                this.zzcqw.putLong("app_last_background_time_ms", j);
                this.zzcqw.apply();
            }
            Bundle bundle = new Bundle();
            bundle.putLong("app_last_background_time_ms", j);
            zze(bundle);
        }
    }

    public final void zzk(long j) {
        zzqr();
        synchronized (this.mLock) {
            if (this.zzcrc == j) {
                return;
            }
            this.zzcrc = j;
            if (this.zzcqw != null) {
                this.zzcqw.putLong("first_ad_req_time_ms", j);
                this.zzcqw.apply();
            }
            Bundle bundle = new Bundle();
            bundle.putLong("first_ad_req_time_ms", j);
            zze(bundle);
        }
    }

    public final boolean zzqt() {
        boolean z;
        zzqr();
        synchronized (this.mLock) {
            z = this.zzcik || this.zzcqx;
        }
        return z;
    }

    public final boolean zzqu() {
        boolean z;
        zzqr();
        synchronized (this.mLock) {
            z = this.zzcil;
        }
        return z;
    }

    @Nullable
    public final String zzqv() {
        String str;
        zzqr();
        synchronized (this.mLock) {
            str = this.zzcqy;
        }
        return str;
    }

    public final boolean zzqw() {
        boolean z;
        zzqr();
        synchronized (this.mLock) {
            z = this.zzcim;
        }
        return z;
    }

    @Nullable
    public final String zzqx() {
        String str;
        zzqr();
        synchronized (this.mLock) {
            str = this.zzcqz;
        }
        return str;
    }

    public final boolean zzqy() {
        boolean z;
        zzqr();
        synchronized (this.mLock) {
            z = this.zzcit;
        }
        return z;
    }

    public final int zzqz() {
        int i;
        zzqr();
        synchronized (this.mLock) {
            i = this.zzcrd;
        }
        return i;
    }

    public final zzajl zzra() {
        zzajl com_google_android_gms_internal_ads_zzajl;
        zzqr();
        synchronized (this.mLock) {
            com_google_android_gms_internal_ads_zzajl = new zzajl(this.zzcpj, this.zzcra);
        }
        return com_google_android_gms_internal_ads_zzajl;
    }

    public final long zzrb() {
        long j;
        zzqr();
        synchronized (this.mLock) {
            j = this.zzcrb;
        }
        return j;
    }

    public final int zzrc() {
        int i;
        zzqr();
        synchronized (this.mLock) {
            i = this.zzcqg;
        }
        return i;
    }

    public final long zzrd() {
        long j;
        zzqr();
        synchronized (this.mLock) {
            j = this.zzcrc;
        }
        return j;
    }

    public final JSONObject zzre() {
        JSONObject jSONObject;
        zzqr();
        synchronized (this.mLock) {
            jSONObject = this.zzcrf;
        }
        return jSONObject;
    }

    public final void zzrf() {
        zzqr();
        synchronized (this.mLock) {
            this.zzcrf = new JSONObject();
            if (this.zzcqw != null) {
                this.zzcqw.remove("native_advanced_settings");
                this.zzcqw.apply();
            }
            Bundle bundle = new Bundle();
            bundle.putString("native_advanced_settings", "{}");
            zze(bundle);
        }
    }
}
