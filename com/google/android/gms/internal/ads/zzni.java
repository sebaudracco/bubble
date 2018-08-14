package com.google.android.gms.internal.ads;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.ConditionVariable;
import android.support.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

@zzadh
@ParametersAreNonnullByDefault
public final class zzni {
    private final Object mLock = new Object();
    private final ConditionVariable zzatv = new ConditionVariable();
    @Nullable
    private SharedPreferences zzatw = null;
    private Context zzatx;
    private volatile boolean zzzv = false;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void initialize(android.content.Context r4) {
        /*
        r3 = this;
        r0 = r3.zzzv;
        if (r0 == 0) goto L_0x0005;
    L_0x0004:
        return;
    L_0x0005:
        r1 = r3.mLock;
        monitor-enter(r1);
        r0 = r3.zzzv;	 Catch:{ all -> 0x000e }
        if (r0 == 0) goto L_0x0011;
    L_0x000c:
        monitor-exit(r1);	 Catch:{ all -> 0x000e }
        goto L_0x0004;
    L_0x000e:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x000e }
        throw r0;
    L_0x0011:
        r0 = r4.getApplicationContext();	 Catch:{ all -> 0x000e }
        if (r0 != 0) goto L_0x0031;
    L_0x0017:
        r0 = r4;
    L_0x0018:
        r3.zzatx = r0;	 Catch:{ all -> 0x000e }
        r0 = com.google.android.gms.common.GooglePlayServicesUtilLight.getRemoteContext(r4);	 Catch:{ all -> 0x004f }
        if (r0 != 0) goto L_0x0056;
    L_0x0020:
        if (r4 == 0) goto L_0x0056;
    L_0x0022:
        r0 = r4.getApplicationContext();	 Catch:{ all -> 0x004f }
        if (r0 != 0) goto L_0x0036;
    L_0x0028:
        if (r4 != 0) goto L_0x0038;
    L_0x002a:
        r0 = r3.zzatv;	 Catch:{ all -> 0x000e }
        r0.open();	 Catch:{ all -> 0x000e }
        monitor-exit(r1);	 Catch:{ all -> 0x000e }
        goto L_0x0004;
    L_0x0031:
        r0 = r4.getApplicationContext();	 Catch:{ all -> 0x000e }
        goto L_0x0018;
    L_0x0036:
        r4 = r0;
        goto L_0x0028;
    L_0x0038:
        com.google.android.gms.internal.ads.zzkb.zzii();	 Catch:{ all -> 0x004f }
        r0 = "google_ads_flags";
        r2 = 0;
        r0 = r4.getSharedPreferences(r0, r2);	 Catch:{ all -> 0x004f }
        r3.zzatw = r0;	 Catch:{ all -> 0x004f }
        r0 = 1;
        r3.zzzv = r0;	 Catch:{ all -> 0x004f }
        r0 = r3.zzatv;	 Catch:{ all -> 0x000e }
        r0.open();	 Catch:{ all -> 0x000e }
        monitor-exit(r1);	 Catch:{ all -> 0x000e }
        goto L_0x0004;
    L_0x004f:
        r0 = move-exception;
        r2 = r3.zzatv;	 Catch:{ all -> 0x000e }
        r2.open();	 Catch:{ all -> 0x000e }
        throw r0;	 Catch:{ all -> 0x000e }
    L_0x0056:
        r4 = r0;
        goto L_0x0028;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzni.initialize(android.content.Context):void");
    }

    public final <T> T zzd(zzna<T> com_google_android_gms_internal_ads_zzna_T) {
        if (this.zzatv.block(5000)) {
            if (!this.zzzv || this.zzatw == null) {
                synchronized (this.mLock) {
                    if (!this.zzzv || this.zzatw == null) {
                        T zzja = com_google_android_gms_internal_ads_zzna_T.zzja();
                        return zzja;
                    }
                }
            }
            return zzaml.zza(this.zzatx, new zznj(this, com_google_android_gms_internal_ads_zzna_T));
        }
        throw new IllegalStateException("Flags.initialize() was not called!");
    }
}
