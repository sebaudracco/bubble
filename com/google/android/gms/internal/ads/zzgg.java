package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import com.google.android.gms.common.util.PlatformVersion;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.GuardedBy;

@zzadh
@ParametersAreNonnullByDefault
public final class zzgg {
    private final Object zzaho = new Object();
    @GuardedBy("mActivityTrackerLock")
    private zzgh zzahp = null;
    @GuardedBy("mActivityTrackerLock")
    private boolean zzahq = false;

    @Nullable
    public final Activity getActivity() {
        Activity activity = null;
        synchronized (this.zzaho) {
            if (!PlatformVersion.isAtLeastIceCreamSandwich()) {
            } else if (this.zzahp != null) {
                activity = this.zzahp.getActivity();
            }
        }
        return activity;
    }

    @Nullable
    public final Context getContext() {
        Context context = null;
        synchronized (this.zzaho) {
            if (!PlatformVersion.isAtLeastIceCreamSandwich()) {
            } else if (this.zzahp != null) {
                context = this.zzahp.getContext();
            }
        }
        return context;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void initialize(android.content.Context r5) {
        /*
        r4 = this;
        r2 = r4.zzaho;
        monitor-enter(r2);
        r0 = r4.zzahq;	 Catch:{ all -> 0x0023 }
        if (r0 != 0) goto L_0x0051;
    L_0x0007:
        r0 = com.google.android.gms.common.util.PlatformVersion.isAtLeastIceCreamSandwich();	 Catch:{ all -> 0x0023 }
        if (r0 != 0) goto L_0x000f;
    L_0x000d:
        monitor-exit(r2);	 Catch:{ all -> 0x0023 }
    L_0x000e:
        return;
    L_0x000f:
        r0 = com.google.android.gms.internal.ads.zznk.zzayg;	 Catch:{ all -> 0x0023 }
        r1 = com.google.android.gms.internal.ads.zzkb.zzik();	 Catch:{ all -> 0x0023 }
        r0 = r1.zzd(r0);	 Catch:{ all -> 0x0023 }
        r0 = (java.lang.Boolean) r0;	 Catch:{ all -> 0x0023 }
        r0 = r0.booleanValue();	 Catch:{ all -> 0x0023 }
        if (r0 != 0) goto L_0x0026;
    L_0x0021:
        monitor-exit(r2);	 Catch:{ all -> 0x0023 }
        goto L_0x000e;
    L_0x0023:
        r0 = move-exception;
        monitor-exit(r2);	 Catch:{ all -> 0x0023 }
        throw r0;
    L_0x0026:
        r1 = 0;
        r0 = r5.getApplicationContext();	 Catch:{ all -> 0x0023 }
        if (r0 != 0) goto L_0x002e;
    L_0x002d:
        r0 = r5;
    L_0x002e:
        r3 = r0 instanceof android.app.Application;	 Catch:{ all -> 0x0023 }
        if (r3 == 0) goto L_0x0053;
    L_0x0032:
        r0 = (android.app.Application) r0;	 Catch:{ all -> 0x0023 }
    L_0x0034:
        if (r0 != 0) goto L_0x003e;
    L_0x0036:
        r0 = "Can not cast Context to Application";
        com.google.android.gms.internal.ads.zzane.zzdk(r0);	 Catch:{ all -> 0x0023 }
        monitor-exit(r2);	 Catch:{ all -> 0x0023 }
        goto L_0x000e;
    L_0x003e:
        r1 = r4.zzahp;	 Catch:{ all -> 0x0023 }
        if (r1 != 0) goto L_0x0049;
    L_0x0042:
        r1 = new com.google.android.gms.internal.ads.zzgh;	 Catch:{ all -> 0x0023 }
        r1.<init>();	 Catch:{ all -> 0x0023 }
        r4.zzahp = r1;	 Catch:{ all -> 0x0023 }
    L_0x0049:
        r1 = r4.zzahp;	 Catch:{ all -> 0x0023 }
        r1.zza(r0, r5);	 Catch:{ all -> 0x0023 }
        r0 = 1;
        r4.zzahq = r0;	 Catch:{ all -> 0x0023 }
    L_0x0051:
        monitor-exit(r2);	 Catch:{ all -> 0x0023 }
        goto L_0x000e;
    L_0x0053:
        r0 = r1;
        goto L_0x0034;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzgg.initialize(android.content.Context):void");
    }

    public final void zza(zzgj com_google_android_gms_internal_ads_zzgj) {
        synchronized (this.zzaho) {
            if (PlatformVersion.isAtLeastIceCreamSandwich()) {
                if (((Boolean) zzkb.zzik().zzd(zznk.zzayg)).booleanValue()) {
                    if (this.zzahp == null) {
                        this.zzahp = new zzgh();
                    }
                    this.zzahp.zza(com_google_android_gms_internal_ads_zzgj);
                    return;
                }
                return;
            }
        }
    }
}
