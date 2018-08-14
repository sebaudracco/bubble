package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.dynamite.descriptors.com.google.android.gms.ads.dynamite.ModuleDescriptor;
import javax.annotation.concurrent.GuardedBy;

@zzadh
public final class zzaan {
    private static final Object sLock = new Object();
    @GuardedBy("sLock")
    @VisibleForTesting
    private static boolean zzbxg = false;
    @GuardedBy("sLock")
    @VisibleForTesting
    private static boolean zzsh = false;
    @VisibleForTesting
    private zzatn zzbxh;

    @VisibleForTesting
    private final void zzj(Context context) {
        synchronized (sLock) {
            if (((Boolean) zzkb.zzik().zzd(zznk.zzbet)).booleanValue() && !zzbxg) {
                try {
                    zzbxg = true;
                    this.zzbxh = zzato.zzab(DynamiteModule.load(context, DynamiteModule.PREFER_REMOTE, ModuleDescriptor.MODULE_ID).instantiate("com.google.android.gms.ads.omid.DynamiteOmid"));
                } catch (Throwable e) {
                    zzane.zzd("#007 Could not call remote method.", e);
                }
            }
        }
    }

    @Nullable
    public final String getVersion(Context context) {
        Throwable e;
        if (!((Boolean) zzkb.zzik().zzd(zznk.zzbet)).booleanValue()) {
            return null;
        }
        try {
            zzj(context);
            String str = "a.";
            String valueOf = String.valueOf(this.zzbxh.getVersion());
            return valueOf.length() != 0 ? str.concat(valueOf) : new String(str);
        } catch (RemoteException e2) {
            e = e2;
            zzane.zzd("#007 Could not call remote method.", e);
            return null;
        } catch (NullPointerException e3) {
            e = e3;
            zzane.zzd("#007 Could not call remote method.", e);
            return null;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @android.support.annotation.Nullable
    public final com.google.android.gms.dynamic.IObjectWrapper zza(java.lang.String r8, android.webkit.WebView r9, java.lang.String r10, java.lang.String r11, java.lang.String r12) {
        /*
        r7 = this;
        r6 = 0;
        r1 = sLock;
        monitor-enter(r1);
        r0 = com.google.android.gms.internal.ads.zznk.zzbet;	 Catch:{ all -> 0x002d }
        r2 = com.google.android.gms.internal.ads.zzkb.zzik();	 Catch:{ all -> 0x002d }
        r0 = r2.zzd(r0);	 Catch:{ all -> 0x002d }
        r0 = (java.lang.Boolean) r0;	 Catch:{ all -> 0x002d }
        r0 = r0.booleanValue();	 Catch:{ all -> 0x002d }
        if (r0 == 0) goto L_0x001a;
    L_0x0016:
        r0 = zzsh;	 Catch:{ all -> 0x002d }
        if (r0 != 0) goto L_0x001d;
    L_0x001a:
        monitor-exit(r1);	 Catch:{ all -> 0x002d }
        r0 = r6;
    L_0x001c:
        return r0;
    L_0x001d:
        monitor-exit(r1);	 Catch:{ all -> 0x002d }
        r0 = r7.zzbxh;	 Catch:{ RemoteException -> 0x0039, NullPointerException -> 0x0030 }
        r2 = com.google.android.gms.dynamic.ObjectWrapper.wrap(r9);	 Catch:{ RemoteException -> 0x0039, NullPointerException -> 0x0030 }
        r1 = r8;
        r3 = r10;
        r4 = r11;
        r5 = r12;
        r0 = r0.zza(r1, r2, r3, r4, r5);	 Catch:{ RemoteException -> 0x0039, NullPointerException -> 0x0030 }
        goto L_0x001c;
    L_0x002d:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x002d }
        throw r0;
    L_0x0030:
        r0 = move-exception;
    L_0x0031:
        r1 = "#007 Could not call remote method.";
        com.google.android.gms.internal.ads.zzane.zzd(r1, r0);
        r0 = r6;
        goto L_0x001c;
    L_0x0039:
        r0 = move-exception;
        goto L_0x0031;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzaan.zza(java.lang.String, android.webkit.WebView, java.lang.String, java.lang.String, java.lang.String):com.google.android.gms.dynamic.IObjectWrapper");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(com.google.android.gms.dynamic.IObjectWrapper r4, android.view.View r5) {
        /*
        r3 = this;
        r1 = sLock;
        monitor-enter(r1);
        r0 = com.google.android.gms.internal.ads.zznk.zzbet;	 Catch:{ all -> 0x002e }
        r2 = com.google.android.gms.internal.ads.zzkb.zzik();	 Catch:{ all -> 0x002e }
        r0 = r2.zzd(r0);	 Catch:{ all -> 0x002e }
        r0 = (java.lang.Boolean) r0;	 Catch:{ all -> 0x002e }
        r0 = r0.booleanValue();	 Catch:{ all -> 0x002e }
        if (r0 == 0) goto L_0x0019;
    L_0x0015:
        r0 = zzsh;	 Catch:{ all -> 0x002e }
        if (r0 != 0) goto L_0x001b;
    L_0x0019:
        monitor-exit(r1);	 Catch:{ all -> 0x002e }
    L_0x001a:
        return;
    L_0x001b:
        monitor-exit(r1);	 Catch:{ all -> 0x002e }
        r0 = r3.zzbxh;	 Catch:{ RemoteException -> 0x0026, NullPointerException -> 0x0031 }
        r1 = com.google.android.gms.dynamic.ObjectWrapper.wrap(r5);	 Catch:{ RemoteException -> 0x0026, NullPointerException -> 0x0031 }
        r0.zza(r4, r1);	 Catch:{ RemoteException -> 0x0026, NullPointerException -> 0x0031 }
        goto L_0x001a;
    L_0x0026:
        r0 = move-exception;
    L_0x0027:
        r1 = "#007 Could not call remote method.";
        com.google.android.gms.internal.ads.zzane.zzd(r1, r0);
        goto L_0x001a;
    L_0x002e:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x002e }
        throw r0;
    L_0x0031:
        r0 = move-exception;
        goto L_0x0027;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzaan.zza(com.google.android.gms.dynamic.IObjectWrapper, android.view.View):void");
    }

    public final boolean zzi(Context context) {
        Throwable e;
        synchronized (sLock) {
            if (!((Boolean) zzkb.zzik().zzd(zznk.zzbet)).booleanValue()) {
                return false;
            } else if (zzsh) {
                return true;
            } else {
                try {
                    zzj(context);
                    boolean zzy = this.zzbxh.zzy(ObjectWrapper.wrap(context));
                    zzsh = zzy;
                    return zzy;
                } catch (RemoteException e2) {
                    e = e2;
                    zzane.zzd("#007 Could not call remote method.", e);
                    return false;
                } catch (NullPointerException e3) {
                    e = e3;
                    zzane.zzd("#007 Could not call remote method.", e);
                    return false;
                }
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzm(com.google.android.gms.dynamic.IObjectWrapper r4) {
        /*
        r3 = this;
        r1 = sLock;
        monitor-enter(r1);
        r0 = com.google.android.gms.internal.ads.zznk.zzbet;	 Catch:{ all -> 0x002a }
        r2 = com.google.android.gms.internal.ads.zzkb.zzik();	 Catch:{ all -> 0x002a }
        r0 = r2.zzd(r0);	 Catch:{ all -> 0x002a }
        r0 = (java.lang.Boolean) r0;	 Catch:{ all -> 0x002a }
        r0 = r0.booleanValue();	 Catch:{ all -> 0x002a }
        if (r0 == 0) goto L_0x0019;
    L_0x0015:
        r0 = zzsh;	 Catch:{ all -> 0x002a }
        if (r0 != 0) goto L_0x001b;
    L_0x0019:
        monitor-exit(r1);	 Catch:{ all -> 0x002a }
    L_0x001a:
        return;
    L_0x001b:
        monitor-exit(r1);	 Catch:{ all -> 0x002a }
        r0 = r3.zzbxh;	 Catch:{ RemoteException -> 0x0022, NullPointerException -> 0x002d }
        r0.zzm(r4);	 Catch:{ RemoteException -> 0x0022, NullPointerException -> 0x002d }
        goto L_0x001a;
    L_0x0022:
        r0 = move-exception;
    L_0x0023:
        r1 = "#007 Could not call remote method.";
        com.google.android.gms.internal.ads.zzane.zzd(r1, r0);
        goto L_0x001a;
    L_0x002a:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x002a }
        throw r0;
    L_0x002d:
        r0 = move-exception;
        goto L_0x0023;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzaan.zzm(com.google.android.gms.dynamic.IObjectWrapper):void");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzn(com.google.android.gms.dynamic.IObjectWrapper r4) {
        /*
        r3 = this;
        r1 = sLock;
        monitor-enter(r1);
        r0 = com.google.android.gms.internal.ads.zznk.zzbet;	 Catch:{ all -> 0x002a }
        r2 = com.google.android.gms.internal.ads.zzkb.zzik();	 Catch:{ all -> 0x002a }
        r0 = r2.zzd(r0);	 Catch:{ all -> 0x002a }
        r0 = (java.lang.Boolean) r0;	 Catch:{ all -> 0x002a }
        r0 = r0.booleanValue();	 Catch:{ all -> 0x002a }
        if (r0 == 0) goto L_0x0019;
    L_0x0015:
        r0 = zzsh;	 Catch:{ all -> 0x002a }
        if (r0 != 0) goto L_0x001b;
    L_0x0019:
        monitor-exit(r1);	 Catch:{ all -> 0x002a }
    L_0x001a:
        return;
    L_0x001b:
        monitor-exit(r1);	 Catch:{ all -> 0x002a }
        r0 = r3.zzbxh;	 Catch:{ RemoteException -> 0x0022, NullPointerException -> 0x002d }
        r0.zzn(r4);	 Catch:{ RemoteException -> 0x0022, NullPointerException -> 0x002d }
        goto L_0x001a;
    L_0x0022:
        r0 = move-exception;
    L_0x0023:
        r1 = "#007 Could not call remote method.";
        com.google.android.gms.internal.ads.zzane.zzd(r1, r0);
        goto L_0x001a;
    L_0x002a:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x002a }
        throw r0;
    L_0x002d:
        r0 = move-exception;
        goto L_0x0023;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzaan.zzn(com.google.android.gms.dynamic.IObjectWrapper):void");
    }
}
