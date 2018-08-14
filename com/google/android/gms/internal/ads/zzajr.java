package com.google.android.gms.internal.ads;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import com.google.android.gms.ads.AdActivity;
import com.google.android.gms.common.util.VisibleForTesting;
import javax.annotation.concurrent.GuardedBy;

@zzadh
public final class zzajr {
    private final Object mLock = new Object();
    @VisibleForTesting
    private final String zzasc;
    @VisibleForTesting
    private long zzcqd = -1;
    @VisibleForTesting
    private long zzcqe = -1;
    @GuardedBy("mLock")
    @VisibleForTesting
    private int zzcqf = -1;
    @VisibleForTesting
    int zzcqg = -1;
    @VisibleForTesting
    private long zzcqh = 0;
    @GuardedBy("mLock")
    @VisibleForTesting
    private int zzcqi = 0;
    @GuardedBy("mLock")
    @VisibleForTesting
    private int zzcqj = 0;

    public zzajr(String str) {
        this.zzasc = str;
    }

    private static boolean zzah(Context context) {
        int identifier = context.getResources().getIdentifier("Theme.Translucent", "style", "android");
        if (identifier == 0) {
            zzane.zzdj("Please set theme of AdActivity to @android:style/Theme.Translucent to enable transparent background interstitial ad.");
            return false;
        }
        try {
            if (identifier == context.getPackageManager().getActivityInfo(new ComponentName(context.getPackageName(), AdActivity.CLASS_NAME), 0).theme) {
                return true;
            }
            zzane.zzdj("Please set theme of AdActivity to @android:style/Theme.Translucent to enable transparent background interstitial ad.");
            return false;
        } catch (NameNotFoundException e) {
            zzane.zzdk("Fail to fetch AdActivity theme");
            zzane.zzdj("Please set theme of AdActivity to @android:style/Theme.Translucent to enable transparent background interstitial ad.");
            return false;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzb(com.google.android.gms.internal.ads.zzjj r11, long r12) {
        /*
        r10 = this;
        r1 = r10.mLock;
        monitor-enter(r1);
        r0 = com.google.android.gms.ads.internal.zzbv.zzeo();	 Catch:{ all -> 0x0062 }
        r0 = r0.zzqh();	 Catch:{ all -> 0x0062 }
        r2 = r0.zzrb();	 Catch:{ all -> 0x0062 }
        r0 = com.google.android.gms.ads.internal.zzbv.zzer();	 Catch:{ all -> 0x0062 }
        r4 = r0.currentTimeMillis();	 Catch:{ all -> 0x0062 }
        r6 = r10.zzcqe;	 Catch:{ all -> 0x0062 }
        r8 = -1;
        r0 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1));
        if (r0 != 0) goto L_0x0065;
    L_0x001f:
        r2 = r4 - r2;
        r0 = com.google.android.gms.internal.ads.zznk.zzayi;	 Catch:{ all -> 0x0062 }
        r6 = com.google.android.gms.internal.ads.zzkb.zzik();	 Catch:{ all -> 0x0062 }
        r0 = r6.zzd(r0);	 Catch:{ all -> 0x0062 }
        r0 = (java.lang.Long) r0;	 Catch:{ all -> 0x0062 }
        r6 = r0.longValue();	 Catch:{ all -> 0x0062 }
        r0 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1));
        if (r0 <= 0) goto L_0x0053;
    L_0x0035:
        r0 = -1;
        r10.zzcqg = r0;	 Catch:{ all -> 0x0062 }
    L_0x0038:
        r10.zzcqe = r12;	 Catch:{ all -> 0x0062 }
        r2 = r10.zzcqe;	 Catch:{ all -> 0x0062 }
        r10.zzcqd = r2;	 Catch:{ all -> 0x0062 }
    L_0x003e:
        if (r11 == 0) goto L_0x0068;
    L_0x0040:
        r0 = r11.extras;	 Catch:{ all -> 0x0062 }
        if (r0 == 0) goto L_0x0068;
    L_0x0044:
        r0 = r11.extras;	 Catch:{ all -> 0x0062 }
        r2 = "gw";
        r3 = 2;
        r0 = r0.getInt(r2, r3);	 Catch:{ all -> 0x0062 }
        r2 = 1;
        if (r0 != r2) goto L_0x0068;
    L_0x0051:
        monitor-exit(r1);	 Catch:{ all -> 0x0062 }
    L_0x0052:
        return;
    L_0x0053:
        r0 = com.google.android.gms.ads.internal.zzbv.zzeo();	 Catch:{ all -> 0x0062 }
        r0 = r0.zzqh();	 Catch:{ all -> 0x0062 }
        r0 = r0.zzrc();	 Catch:{ all -> 0x0062 }
        r10.zzcqg = r0;	 Catch:{ all -> 0x0062 }
        goto L_0x0038;
    L_0x0062:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x0062 }
        throw r0;
    L_0x0065:
        r10.zzcqd = r12;	 Catch:{ all -> 0x0062 }
        goto L_0x003e;
    L_0x0068:
        r0 = r10.zzcqf;	 Catch:{ all -> 0x0062 }
        r0 = r0 + 1;
        r10.zzcqf = r0;	 Catch:{ all -> 0x0062 }
        r0 = r10.zzcqg;	 Catch:{ all -> 0x0062 }
        r0 = r0 + 1;
        r10.zzcqg = r0;	 Catch:{ all -> 0x0062 }
        r0 = r10.zzcqg;	 Catch:{ all -> 0x0062 }
        if (r0 != 0) goto L_0x0089;
    L_0x0078:
        r2 = 0;
        r10.zzcqh = r2;	 Catch:{ all -> 0x0062 }
        r0 = com.google.android.gms.ads.internal.zzbv.zzeo();	 Catch:{ all -> 0x0062 }
        r0 = r0.zzqh();	 Catch:{ all -> 0x0062 }
        r0.zzk(r4);	 Catch:{ all -> 0x0062 }
    L_0x0087:
        monitor-exit(r1);	 Catch:{ all -> 0x0062 }
        goto L_0x0052;
    L_0x0089:
        r0 = com.google.android.gms.ads.internal.zzbv.zzeo();	 Catch:{ all -> 0x0062 }
        r0 = r0.zzqh();	 Catch:{ all -> 0x0062 }
        r2 = r0.zzrd();	 Catch:{ all -> 0x0062 }
        r2 = r4 - r2;
        r10.zzcqh = r2;	 Catch:{ all -> 0x0062 }
        goto L_0x0087;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzajr.zzb(com.google.android.gms.internal.ads.zzjj, long):void");
    }

    public final Bundle zzk(Context context, String str) {
        Bundle bundle;
        synchronized (this.mLock) {
            bundle = new Bundle();
            bundle.putString("session_id", this.zzasc);
            bundle.putLong("basets", this.zzcqe);
            bundle.putLong("currts", this.zzcqd);
            bundle.putString("seq_num", str);
            bundle.putInt("preqs", this.zzcqf);
            bundle.putInt("preqs_in_session", this.zzcqg);
            bundle.putLong("time_in_session", this.zzcqh);
            bundle.putInt("pclick", this.zzcqi);
            bundle.putInt("pimp", this.zzcqj);
            bundle.putBoolean("support_transparent_background", zzah(context));
        }
        return bundle;
    }

    public final void zzpm() {
        synchronized (this.mLock) {
            this.zzcqj++;
        }
    }

    public final void zzpn() {
        synchronized (this.mLock) {
            this.zzcqi++;
        }
    }
}
