package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Binder;
import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.zzbv;
import javax.annotation.concurrent.GuardedBy;

@zzadh
public final class zzhd {
    @Nullable
    @GuardedBy("mLock")
    private Context mContext;
    private final Object mLock = new Object();
    private final Runnable zzajq = new zzhe(this);
    @Nullable
    @GuardedBy("mLock")
    private zzhk zzajr;
    @Nullable
    @GuardedBy("mLock")
    private zzho zzajs;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void connect() {
        /*
        r6 = this;
        r1 = r6.mLock;
        monitor-enter(r1);
        r0 = r6.mContext;	 Catch:{ all -> 0x002f }
        if (r0 == 0) goto L_0x000b;
    L_0x0007:
        r0 = r6.zzajr;	 Catch:{ all -> 0x002f }
        if (r0 == 0) goto L_0x000d;
    L_0x000b:
        monitor-exit(r1);	 Catch:{ all -> 0x002f }
    L_0x000c:
        return;
    L_0x000d:
        r0 = new com.google.android.gms.internal.ads.zzhg;	 Catch:{ all -> 0x002f }
        r0.<init>(r6);	 Catch:{ all -> 0x002f }
        r2 = new com.google.android.gms.internal.ads.zzhh;	 Catch:{ all -> 0x002f }
        r2.<init>(r6);	 Catch:{ all -> 0x002f }
        r3 = new com.google.android.gms.internal.ads.zzhk;	 Catch:{ all -> 0x002f }
        r4 = r6.mContext;	 Catch:{ all -> 0x002f }
        r5 = com.google.android.gms.ads.internal.zzbv.zzez();	 Catch:{ all -> 0x002f }
        r5 = r5.zzsa();	 Catch:{ all -> 0x002f }
        r3.<init>(r4, r5, r0, r2);	 Catch:{ all -> 0x002f }
        r6.zzajr = r3;	 Catch:{ all -> 0x002f }
        r0 = r6.zzajr;	 Catch:{ all -> 0x002f }
        r0.checkAvailabilityAndConnect();	 Catch:{ all -> 0x002f }
        monitor-exit(r1);	 Catch:{ all -> 0x002f }
        goto L_0x000c;
    L_0x002f:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x002f }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzhd.connect():void");
    }

    private final void disconnect() {
        synchronized (this.mLock) {
            if (this.zzajr == null) {
                return;
            }
            if (this.zzajr.isConnected() || this.zzajr.isConnecting()) {
                this.zzajr.disconnect();
            }
            this.zzajr = null;
            this.zzajs = null;
            Binder.flushPendingCommands();
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void initialize(android.content.Context r4) {
        /*
        r3 = this;
        if (r4 != 0) goto L_0x0003;
    L_0x0002:
        return;
    L_0x0003:
        r1 = r3.mLock;
        monitor-enter(r1);
        r0 = r3.mContext;	 Catch:{ all -> 0x000c }
        if (r0 == 0) goto L_0x000f;
    L_0x000a:
        monitor-exit(r1);	 Catch:{ all -> 0x000c }
        goto L_0x0002;
    L_0x000c:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x000c }
        throw r0;
    L_0x000f:
        r0 = r4.getApplicationContext();	 Catch:{ all -> 0x000c }
        r3.mContext = r0;	 Catch:{ all -> 0x000c }
        r0 = com.google.android.gms.internal.ads.zznk.zzbdo;	 Catch:{ all -> 0x000c }
        r2 = com.google.android.gms.internal.ads.zzkb.zzik();	 Catch:{ all -> 0x000c }
        r0 = r2.zzd(r0);	 Catch:{ all -> 0x000c }
        r0 = (java.lang.Boolean) r0;	 Catch:{ all -> 0x000c }
        r0 = r0.booleanValue();	 Catch:{ all -> 0x000c }
        if (r0 == 0) goto L_0x002c;
    L_0x0027:
        r3.connect();	 Catch:{ all -> 0x000c }
    L_0x002a:
        monitor-exit(r1);	 Catch:{ all -> 0x000c }
        goto L_0x0002;
    L_0x002c:
        r0 = com.google.android.gms.internal.ads.zznk.zzbdn;	 Catch:{ all -> 0x000c }
        r2 = com.google.android.gms.internal.ads.zzkb.zzik();	 Catch:{ all -> 0x000c }
        r0 = r2.zzd(r0);	 Catch:{ all -> 0x000c }
        r0 = (java.lang.Boolean) r0;	 Catch:{ all -> 0x000c }
        r0 = r0.booleanValue();	 Catch:{ all -> 0x000c }
        if (r0 == 0) goto L_0x002a;
    L_0x003e:
        r0 = new com.google.android.gms.internal.ads.zzhf;	 Catch:{ all -> 0x000c }
        r0.<init>(r3);	 Catch:{ all -> 0x000c }
        r2 = com.google.android.gms.ads.internal.zzbv.zzen();	 Catch:{ all -> 0x000c }
        r2.zza(r0);	 Catch:{ all -> 0x000c }
        goto L_0x002a;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzhd.initialize(android.content.Context):void");
    }

    public final zzhi zza(zzhl com_google_android_gms_internal_ads_zzhl) {
        zzhi com_google_android_gms_internal_ads_zzhi;
        synchronized (this.mLock) {
            if (this.zzajs == null) {
                com_google_android_gms_internal_ads_zzhi = new zzhi();
            } else {
                try {
                    com_google_android_gms_internal_ads_zzhi = this.zzajs.zza(com_google_android_gms_internal_ads_zzhl);
                } catch (Throwable e) {
                    zzane.zzb("Unable to call into cache service.", e);
                    com_google_android_gms_internal_ads_zzhi = new zzhi();
                }
            }
        }
        return com_google_android_gms_internal_ads_zzhi;
    }

    public final void zzhh() {
        if (((Boolean) zzkb.zzik().zzd(zznk.zzbdp)).booleanValue()) {
            synchronized (this.mLock) {
                connect();
                zzbv.zzek();
                zzakk.zzcrm.removeCallbacks(this.zzajq);
                zzbv.zzek();
                zzakk.zzcrm.postDelayed(this.zzajq, ((Long) zzkb.zzik().zzd(zznk.zzbdq)).longValue());
            }
        }
    }
}
