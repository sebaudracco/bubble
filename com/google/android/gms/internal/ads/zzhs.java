package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.internal.ads.zzhu.zza.zzb;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.concurrent.GuardedBy;

@zzadh
public final class zzhs {
    private final zzhx zzakc;
    @GuardedBy("this")
    private final zzii zzakd;
    private final boolean zzake;

    private zzhs() {
        this.zzake = false;
        this.zzakc = new zzhx();
        this.zzakd = new zzii();
        zzhn();
    }

    public zzhs(zzhx com_google_android_gms_internal_ads_zzhx) {
        this.zzakc = com_google_android_gms_internal_ads_zzhx;
        this.zzake = ((Boolean) zzkb.zzik().zzd(zznk.zzbeo)).booleanValue();
        this.zzakd = new zzii();
        zzhn();
    }

    private final synchronized void zzb(zzb com_google_android_gms_internal_ads_zzhu_zza_zzb) {
        this.zzakd.zzanl = zzho();
        this.zzakc.zzd(zzbfi.zzb(this.zzakd)).zzs(com_google_android_gms_internal_ads_zzhu_zza_zzb.zzhq()).zzbd();
        String str = "Logging Event with event code : ";
        String valueOf = String.valueOf(Integer.toString(com_google_android_gms_internal_ads_zzhu_zza_zzb.zzhq(), 10));
        zzakb.m3428v(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final synchronized void zzc(com.google.android.gms.internal.ads.zzhu.zza.zzb r4) {
        /*
        r3 = this;
        monitor-enter(r3);
        r0 = android.os.Environment.getExternalStorageDirectory();	 Catch:{ all -> 0x003b }
        if (r0 != 0) goto L_0x0009;
    L_0x0007:
        monitor-exit(r3);
        return;
    L_0x0009:
        r1 = new java.io.File;	 Catch:{ all -> 0x003b }
        r2 = "clearcut_events.txt";
        r1.<init>(r0, r2);	 Catch:{ all -> 0x003b }
        r2 = new java.io.FileOutputStream;	 Catch:{ FileNotFoundException -> 0x0033 }
        r0 = 1;
        r2.<init>(r1, r0);	 Catch:{ FileNotFoundException -> 0x0033 }
        r0 = r3.zzd(r4);	 Catch:{ IOException -> 0x003e }
        r0 = r0.getBytes();	 Catch:{ IOException -> 0x003e }
        r2.write(r0);	 Catch:{ IOException -> 0x003e }
        r0 = 10;
        r2.write(r0);	 Catch:{ IOException -> 0x003e }
        r2.close();	 Catch:{ IOException -> 0x002b }
        goto L_0x0007;
    L_0x002b:
        r0 = move-exception;
        r0 = "Could not close Clearcut output stream.";
        com.google.android.gms.internal.ads.zzakb.m3428v(r0);	 Catch:{ FileNotFoundException -> 0x0033 }
        goto L_0x0007;
    L_0x0033:
        r0 = move-exception;
        r0 = "Could not find file for Clearcut";
        com.google.android.gms.internal.ads.zzakb.m3428v(r0);	 Catch:{ all -> 0x003b }
        goto L_0x0007;
    L_0x003b:
        r0 = move-exception;
        monitor-exit(r3);
        throw r0;
    L_0x003e:
        r0 = move-exception;
        r0 = "Could not write Clearcut to file.";
        com.google.android.gms.internal.ads.zzakb.m3428v(r0);	 Catch:{ all -> 0x0051 }
        r2.close();	 Catch:{ IOException -> 0x0049 }
        goto L_0x0007;
    L_0x0049:
        r0 = move-exception;
        r0 = "Could not close Clearcut output stream.";
        com.google.android.gms.internal.ads.zzakb.m3428v(r0);	 Catch:{ FileNotFoundException -> 0x0033 }
        goto L_0x0007;
    L_0x0051:
        r0 = move-exception;
        r2.close();	 Catch:{ IOException -> 0x0056 }
    L_0x0055:
        throw r0;	 Catch:{ FileNotFoundException -> 0x0033 }
    L_0x0056:
        r1 = move-exception;
        r1 = "Could not close Clearcut output stream.";
        com.google.android.gms.internal.ads.zzakb.m3428v(r1);	 Catch:{ FileNotFoundException -> 0x0033 }
        goto L_0x0055;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzhs.zzc(com.google.android.gms.internal.ads.zzhu$zza$zzb):void");
    }

    private final synchronized String zzd(zzb com_google_android_gms_internal_ads_zzhu_zza_zzb) {
        return String.format("id=%s,timestamp=%s,event=%s", new Object[]{this.zzakd.zzanh, Long.valueOf(zzbv.zzer().elapsedRealtime()), Integer.valueOf(com_google_android_gms_internal_ads_zzhu_zza_zzb.zzhq())});
    }

    public static zzhs zzhm() {
        return new zzhs();
    }

    private final synchronized void zzhn() {
        this.zzakd.zzanp = new zzib();
        this.zzakd.zzanp.zzalw = new zzie();
        this.zzakd.zzanm = new zzig();
    }

    private static long[] zzho() {
        int i = 0;
        List<String> zzjc = zznk.zzjc();
        List arrayList = new ArrayList();
        for (String split : zzjc) {
            for (String valueOf : split.split(",")) {
                try {
                    arrayList.add(Long.valueOf(valueOf));
                } catch (NumberFormatException e) {
                    zzakb.m3428v("Experiment ID is not a number");
                }
            }
        }
        long[] jArr = new long[arrayList.size()];
        ArrayList arrayList2 = (ArrayList) arrayList;
        int size = arrayList2.size();
        int i2 = 0;
        while (i < size) {
            Object obj = arrayList2.get(i);
            i++;
            jArr[i2] = ((Long) obj).longValue();
            i2++;
        }
        return jArr;
    }

    public final synchronized void zza(zzht com_google_android_gms_internal_ads_zzht) {
        if (this.zzake) {
            try {
                com_google_android_gms_internal_ads_zzht.zza(this.zzakd);
            } catch (Throwable e) {
                zzbv.zzeo().zza(e, "AdMobClearcutLogger.modify");
            }
        }
    }

    public final synchronized void zza(zzb com_google_android_gms_internal_ads_zzhu_zza_zzb) {
        if (this.zzake) {
            if (((Boolean) zzkb.zzik().zzd(zznk.zzbep)).booleanValue()) {
                zzc(com_google_android_gms_internal_ads_zzhu_zza_zzb);
            } else {
                zzb(com_google_android_gms_internal_ads_zzhu_zza_zzb);
            }
        }
    }
}
