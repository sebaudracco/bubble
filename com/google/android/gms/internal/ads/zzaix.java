package com.google.android.gms.internal.ads;

import android.content.Context;

@zzadh
public final class zzaix implements zzft {
    private final Object mLock;
    private final Context zzatx;
    private boolean zzcno;
    private String zzye;

    public zzaix(Context context, String str) {
        if (context.getApplicationContext() != null) {
            context = context.getApplicationContext();
        }
        this.zzatx = context;
        this.zzye = str;
        this.zzcno = false;
        this.mLock = new Object();
    }

    public final void setAdUnitId(String str) {
        this.zzye = str;
    }

    public final void zza(zzfs com_google_android_gms_internal_ads_zzfs) {
        zzx(com_google_android_gms_internal_ads_zzfs.zztg);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzx(boolean r5) {
        /*
        r4 = this;
        r0 = com.google.android.gms.ads.internal.zzbv.zzfh();
        r1 = r4.zzatx;
        r0 = r0.zzs(r1);
        if (r0 != 0) goto L_0x000d;
    L_0x000c:
        return;
    L_0x000d:
        r1 = r4.mLock;
        monitor-enter(r1);
        r0 = r4.zzcno;	 Catch:{ all -> 0x0016 }
        if (r0 != r5) goto L_0x0019;
    L_0x0014:
        monitor-exit(r1);	 Catch:{ all -> 0x0016 }
        goto L_0x000c;
    L_0x0016:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x0016 }
        throw r0;
    L_0x0019:
        r4.zzcno = r5;	 Catch:{ all -> 0x0016 }
        r0 = r4.zzye;	 Catch:{ all -> 0x0016 }
        r0 = android.text.TextUtils.isEmpty(r0);	 Catch:{ all -> 0x0016 }
        if (r0 == 0) goto L_0x0025;
    L_0x0023:
        monitor-exit(r1);	 Catch:{ all -> 0x0016 }
        goto L_0x000c;
    L_0x0025:
        r0 = r4.zzcno;	 Catch:{ all -> 0x0016 }
        if (r0 == 0) goto L_0x0036;
    L_0x0029:
        r0 = com.google.android.gms.ads.internal.zzbv.zzfh();	 Catch:{ all -> 0x0016 }
        r2 = r4.zzatx;	 Catch:{ all -> 0x0016 }
        r3 = r4.zzye;	 Catch:{ all -> 0x0016 }
        r0.zzb(r2, r3);	 Catch:{ all -> 0x0016 }
    L_0x0034:
        monitor-exit(r1);	 Catch:{ all -> 0x0016 }
        goto L_0x000c;
    L_0x0036:
        r0 = com.google.android.gms.ads.internal.zzbv.zzfh();	 Catch:{ all -> 0x0016 }
        r2 = r4.zzatx;	 Catch:{ all -> 0x0016 }
        r3 = r4.zzye;	 Catch:{ all -> 0x0016 }
        r0.zzc(r2, r3);	 Catch:{ all -> 0x0016 }
        goto L_0x0034;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzaix.zzx(boolean):void");
    }
}
