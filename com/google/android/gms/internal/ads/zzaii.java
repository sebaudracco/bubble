package com.google.android.gms.internal.ads;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.view.View;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.common.wrappers.Wrappers;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.GuardedBy;
import org.json.JSONArray;
import org.json.JSONObject;

@zzadh
@ParametersAreNonnullByDefault
public final class zzaii implements zzait {
    private static List<Future<Void>> zzcml = Collections.synchronizedList(new ArrayList());
    private static ScheduledExecutorService zzcmm = Executors.newSingleThreadScheduledExecutor();
    private final Context mContext;
    private final Object mLock = new Object();
    private final zzaiq zzciy;
    @GuardedBy("mLock")
    private final zzbfm zzcmn;
    @GuardedBy("mLock")
    private final LinkedHashMap<String, zzbfu> zzcmo;
    @GuardedBy("mLock")
    private final List<String> zzcmp = new ArrayList();
    @GuardedBy("mLock")
    private final List<String> zzcmq = new ArrayList();
    private final zzaiv zzcmr;
    @VisibleForTesting
    private boolean zzcms;
    private final zzaiw zzcmt;
    private HashSet<String> zzcmu = new HashSet();
    private boolean zzcmv = false;
    private boolean zzcmw = false;
    private boolean zzcmx = false;

    public zzaii(Context context, zzang com_google_android_gms_internal_ads_zzang, zzaiq com_google_android_gms_internal_ads_zzaiq, String str, zzaiv com_google_android_gms_internal_ads_zzaiv) {
        Preconditions.checkNotNull(com_google_android_gms_internal_ads_zzaiq, "SafeBrowsing config is not present.");
        if (context.getApplicationContext() != null) {
            context = context.getApplicationContext();
        }
        this.mContext = context;
        this.zzcmo = new LinkedHashMap();
        this.zzcmr = com_google_android_gms_internal_ads_zzaiv;
        this.zzciy = com_google_android_gms_internal_ads_zzaiq;
        for (String toLowerCase : this.zzciy.zzcnh) {
            this.zzcmu.add(toLowerCase.toLowerCase(Locale.ENGLISH));
        }
        this.zzcmu.remove("cookie".toLowerCase(Locale.ENGLISH));
        zzbfm com_google_android_gms_internal_ads_zzbfm = new zzbfm();
        com_google_android_gms_internal_ads_zzbfm.zzamf = Integer.valueOf(8);
        com_google_android_gms_internal_ads_zzbfm.url = str;
        com_google_android_gms_internal_ads_zzbfm.zzech = str;
        com_google_android_gms_internal_ads_zzbfm.zzecj = new zzbfn();
        com_google_android_gms_internal_ads_zzbfm.zzecj.zzcnd = this.zzciy.zzcnd;
        zzbfv com_google_android_gms_internal_ads_zzbfv = new zzbfv();
        com_google_android_gms_internal_ads_zzbfv.zzedv = com_google_android_gms_internal_ads_zzang.zzcw;
        com_google_android_gms_internal_ads_zzbfv.zzedx = Boolean.valueOf(Wrappers.packageManager(this.mContext).isCallerInstantApp());
        long apkVersion = (long) GoogleApiAvailabilityLight.getInstance().getApkVersion(this.mContext);
        if (apkVersion > 0) {
            com_google_android_gms_internal_ads_zzbfv.zzedw = Long.valueOf(apkVersion);
        }
        com_google_android_gms_internal_ads_zzbfm.zzect = com_google_android_gms_internal_ads_zzbfv;
        this.zzcmn = com_google_android_gms_internal_ads_zzbfm;
        this.zzcmt = new zzaiw(this.mContext, this.zzciy.zzcnk, this);
    }

    @Nullable
    private final zzbfu zzci(String str) {
        zzbfu com_google_android_gms_internal_ads_zzbfu;
        synchronized (this.mLock) {
            com_google_android_gms_internal_ads_zzbfu = (zzbfu) this.zzcmo.get(str);
        }
        return com_google_android_gms_internal_ads_zzbfu;
    }

    @VisibleForTesting
    private final zzanz<Void> zzpk() {
        Object obj = 1;
        if (!(this.zzcms && this.zzciy.zzcnj) && (!(this.zzcmx && this.zzciy.zzcni) && (this.zzcms || !this.zzciy.zzcng))) {
            obj = null;
        }
        if (obj == null) {
            return zzano.zzi(null);
        }
        zzanz zza;
        synchronized (this.mLock) {
            this.zzcmn.zzeck = new zzbfu[this.zzcmo.size()];
            this.zzcmo.values().toArray(this.zzcmn.zzeck);
            this.zzcmn.zzecu = (String[]) this.zzcmp.toArray(new String[0]);
            this.zzcmn.zzecv = (String[]) this.zzcmq.toArray(new String[0]);
            if (zzais.isEnabled()) {
                String str = this.zzcmn.url;
                String str2 = this.zzcmn.zzecl;
                StringBuilder stringBuilder = new StringBuilder(new StringBuilder((String.valueOf(str).length() + 53) + String.valueOf(str2).length()).append("Sending SB report\n  url: ").append(str).append("\n  clickUrl: ").append(str2).append("\n  resources: \n").toString());
                for (zzbfu com_google_android_gms_internal_ads_zzbfu : this.zzcmn.zzeck) {
                    stringBuilder.append("    [");
                    stringBuilder.append(com_google_android_gms_internal_ads_zzbfu.zzedu.length);
                    stringBuilder.append("] ");
                    stringBuilder.append(com_google_android_gms_internal_ads_zzbfu.url);
                }
                zzais.zzck(stringBuilder.toString());
            }
            zza = new zzalt(this.mContext).zza(1, this.zzciy.zzcne, null, zzbfi.zzb(this.zzcmn));
            if (zzais.isEnabled()) {
                zza.zza(new zzain(this), zzaki.zzcrj);
            }
        }
        return zzano.zza(zza, zzaik.zzcmz, zzaoe.zzcvz);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(java.lang.String r9, java.util.Map<java.lang.String, java.lang.String> r10, int r11) {
        /*
        r8 = this;
        r1 = 3;
        r2 = r8.mLock;
        monitor-enter(r2);
        if (r11 != r1) goto L_0x0009;
    L_0x0006:
        r0 = 1;
        r8.zzcmx = r0;	 Catch:{ all -> 0x00af }
    L_0x0009:
        r0 = r8.zzcmo;	 Catch:{ all -> 0x00af }
        r0 = r0.containsKey(r9);	 Catch:{ all -> 0x00af }
        if (r0 == 0) goto L_0x0023;
    L_0x0011:
        if (r11 != r1) goto L_0x0021;
    L_0x0013:
        r0 = r8.zzcmo;	 Catch:{ all -> 0x00af }
        r0 = r0.get(r9);	 Catch:{ all -> 0x00af }
        r0 = (com.google.android.gms.internal.ads.zzbfu) r0;	 Catch:{ all -> 0x00af }
        r1 = java.lang.Integer.valueOf(r11);	 Catch:{ all -> 0x00af }
        r0.zzedt = r1;	 Catch:{ all -> 0x00af }
    L_0x0021:
        monitor-exit(r2);	 Catch:{ all -> 0x00af }
    L_0x0022:
        return;
    L_0x0023:
        r3 = new com.google.android.gms.internal.ads.zzbfu;	 Catch:{ all -> 0x00af }
        r3.<init>();	 Catch:{ all -> 0x00af }
        r0 = java.lang.Integer.valueOf(r11);	 Catch:{ all -> 0x00af }
        r3.zzedt = r0;	 Catch:{ all -> 0x00af }
        r0 = r8.zzcmo;	 Catch:{ all -> 0x00af }
        r0 = r0.size();	 Catch:{ all -> 0x00af }
        r0 = java.lang.Integer.valueOf(r0);	 Catch:{ all -> 0x00af }
        r3.zzedn = r0;	 Catch:{ all -> 0x00af }
        r3.url = r9;	 Catch:{ all -> 0x00af }
        r0 = new com.google.android.gms.internal.ads.zzbfp;	 Catch:{ all -> 0x00af }
        r0.<init>();	 Catch:{ all -> 0x00af }
        r3.zzedo = r0;	 Catch:{ all -> 0x00af }
        r0 = r8.zzcmu;	 Catch:{ all -> 0x00af }
        r0 = r0.size();	 Catch:{ all -> 0x00af }
        if (r0 <= 0) goto L_0x00c7;
    L_0x004b:
        if (r10 == 0) goto L_0x00c7;
    L_0x004d:
        r4 = new java.util.ArrayList;	 Catch:{ all -> 0x00af }
        r4.<init>();	 Catch:{ all -> 0x00af }
        r0 = r10.entrySet();	 Catch:{ all -> 0x00af }
        r5 = r0.iterator();	 Catch:{ all -> 0x00af }
    L_0x005a:
        r0 = r5.hasNext();	 Catch:{ all -> 0x00af }
        if (r0 == 0) goto L_0x00ba;
    L_0x0060:
        r0 = r5.next();	 Catch:{ all -> 0x00af }
        r0 = (java.util.Map.Entry) r0;	 Catch:{ all -> 0x00af }
        r1 = r0.getKey();	 Catch:{ UnsupportedEncodingException -> 0x00a7 }
        if (r1 == 0) goto L_0x00b2;
    L_0x006c:
        r1 = r0.getKey();	 Catch:{ UnsupportedEncodingException -> 0x00a7 }
        r1 = (java.lang.String) r1;	 Catch:{ UnsupportedEncodingException -> 0x00a7 }
    L_0x0072:
        r6 = r0.getValue();	 Catch:{ UnsupportedEncodingException -> 0x00a7 }
        if (r6 == 0) goto L_0x00b6;
    L_0x0078:
        r0 = r0.getValue();	 Catch:{ UnsupportedEncodingException -> 0x00a7 }
        r0 = (java.lang.String) r0;	 Catch:{ UnsupportedEncodingException -> 0x00a7 }
    L_0x007e:
        r6 = java.util.Locale.ENGLISH;	 Catch:{ UnsupportedEncodingException -> 0x00a7 }
        r6 = r1.toLowerCase(r6);	 Catch:{ UnsupportedEncodingException -> 0x00a7 }
        r7 = r8.zzcmu;	 Catch:{ UnsupportedEncodingException -> 0x00a7 }
        r6 = r7.contains(r6);	 Catch:{ UnsupportedEncodingException -> 0x00a7 }
        if (r6 == 0) goto L_0x005a;
    L_0x008c:
        r6 = new com.google.android.gms.internal.ads.zzbfo;	 Catch:{ UnsupportedEncodingException -> 0x00a7 }
        r6.<init>();	 Catch:{ UnsupportedEncodingException -> 0x00a7 }
        r7 = "UTF-8";
        r1 = r1.getBytes(r7);	 Catch:{ UnsupportedEncodingException -> 0x00a7 }
        r6.zzecx = r1;	 Catch:{ UnsupportedEncodingException -> 0x00a7 }
        r1 = "UTF-8";
        r0 = r0.getBytes(r1);	 Catch:{ UnsupportedEncodingException -> 0x00a7 }
        r6.zzecy = r0;	 Catch:{ UnsupportedEncodingException -> 0x00a7 }
        r4.add(r6);	 Catch:{ UnsupportedEncodingException -> 0x00a7 }
        goto L_0x005a;
    L_0x00a7:
        r0 = move-exception;
        r0 = "Cannot convert string to bytes, skip header.";
        com.google.android.gms.internal.ads.zzais.zzck(r0);	 Catch:{ all -> 0x00af }
        goto L_0x005a;
    L_0x00af:
        r0 = move-exception;
        monitor-exit(r2);	 Catch:{ all -> 0x00af }
        throw r0;
    L_0x00b2:
        r1 = "";
        goto L_0x0072;
    L_0x00b6:
        r0 = "";
        goto L_0x007e;
    L_0x00ba:
        r0 = r4.size();	 Catch:{ all -> 0x00af }
        r0 = new com.google.android.gms.internal.ads.zzbfo[r0];	 Catch:{ all -> 0x00af }
        r4.toArray(r0);	 Catch:{ all -> 0x00af }
        r1 = r3.zzedo;	 Catch:{ all -> 0x00af }
        r1.zzeda = r0;	 Catch:{ all -> 0x00af }
    L_0x00c7:
        r0 = r8.zzcmo;	 Catch:{ all -> 0x00af }
        r0.put(r9, r3);	 Catch:{ all -> 0x00af }
        monitor-exit(r2);	 Catch:{ all -> 0x00af }
        goto L_0x0022;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzaii.zza(java.lang.String, java.util.Map, int):void");
    }

    public final String[] zzb(String[] strArr) {
        return (String[]) this.zzcmt.zzc(strArr).toArray(new String[0]);
    }

    public final void zzcf(String str) {
        synchronized (this.mLock) {
            this.zzcmn.zzecl = str;
        }
    }

    final void zzcg(String str) {
        synchronized (this.mLock) {
            this.zzcmp.add(str);
        }
    }

    final void zzch(String str) {
        synchronized (this.mLock) {
            this.zzcmq.add(str);
        }
    }

    final /* synthetic */ zzanz zzm(Map map) throws Exception {
        if (map != null) {
            for (String str : map.keySet()) {
                String str2;
                JSONArray optJSONArray = new JSONObject((String) map.get(str2)).optJSONArray("matches");
                if (optJSONArray != null) {
                    synchronized (this.mLock) {
                        int length = optJSONArray.length();
                        zzbfu zzci = zzci(str2);
                        if (zzci == null) {
                            String str3 = "Cannot find the corresponding resource object for ";
                            str2 = String.valueOf(str2);
                            zzais.zzck(str2.length() != 0 ? str3.concat(str2) : new String(str3));
                        } else {
                            zzci.zzedu = new String[length];
                            for (int i = 0; i < length; i++) {
                                zzci.zzedu[i] = optJSONArray.getJSONObject(i).getString("threat_type");
                            }
                            this.zzcms = (length > 0 ? 1 : 0) | this.zzcms;
                        }
                    }
                }
            }
        }
        try {
            if (this.zzcms) {
                synchronized (this.mLock) {
                    this.zzcmn.zzamf = Integer.valueOf(9);
                }
            }
            return zzpk();
        } catch (Throwable e) {
            r1 = e;
            r2 = "Failed to get SafeBrowsing metadata";
            if (((Boolean) zzkb.zzik().zzd(zznk.zzbdi)).booleanValue()) {
                Throwable th;
                String str4;
                zzane.zza(str4, th);
            }
            return zzano.zzd(new Exception("Safebrowsing report transmission failed."));
        }
    }

    public final zzaiq zzpg() {
        return this.zzciy;
    }

    public final boolean zzph() {
        return PlatformVersion.isAtLeastKitKat() && this.zzciy.zzcnf && !this.zzcmw;
    }

    public final void zzpi() {
        this.zzcmv = true;
    }

    public final void zzpj() {
        synchronized (this.mLock) {
            zzanz zza = zzano.zza(this.zzcmr.zza(this.mContext, this.zzcmo.keySet()), new zzaij(this), zzaoe.zzcvz);
            zzanz zza2 = zzano.zza(zza, 10, TimeUnit.SECONDS, zzcmm);
            zzano.zza(zza, new zzaim(this, zza2), zzaoe.zzcvz);
            zzcml.add(zza2);
        }
    }

    public final void zzr(View view) {
        if (this.zzciy.zzcnf && !this.zzcmw) {
            zzbv.zzek();
            Bitmap zzt = zzakk.zzt(view);
            if (zzt == null) {
                zzais.zzck("Failed to capture the webview bitmap.");
                return;
            }
            this.zzcmw = true;
            zzakk.zzd(new zzail(this, zzt));
        }
    }
}
