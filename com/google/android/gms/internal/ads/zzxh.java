package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.internal.zzbv;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@zzadh
public final class zzxh implements zzww {
    private final Context mContext;
    private final Object mLock = new Object();
    private final long mStartTime;
    private final boolean zzael;
    private final zzwy zzbtj;
    private final boolean zzbtn;
    private final boolean zzbto;
    private final zzaef zzbuc;
    private final long zzbud;
    private final int zzbue;
    private boolean zzbuf = false;
    private final Map<zzanz<zzxe>, zzxb> zzbug = new HashMap();
    private final String zzbuh;
    private List<zzxe> zzbui = new ArrayList();
    private final zzxn zzwh;

    public zzxh(Context context, zzaef com_google_android_gms_internal_ads_zzaef, zzxn com_google_android_gms_internal_ads_zzxn, zzwy com_google_android_gms_internal_ads_zzwy, boolean z, boolean z2, String str, long j, long j2, int i, boolean z3) {
        this.mContext = context;
        this.zzbuc = com_google_android_gms_internal_ads_zzaef;
        this.zzwh = com_google_android_gms_internal_ads_zzxn;
        this.zzbtj = com_google_android_gms_internal_ads_zzwy;
        this.zzael = z;
        this.zzbtn = z2;
        this.zzbuh = str;
        this.mStartTime = j;
        this.zzbud = j2;
        this.zzbue = 2;
        this.zzbto = z3;
    }

    private final void zza(zzanz<zzxe> com_google_android_gms_internal_ads_zzanz_com_google_android_gms_internal_ads_zzxe) {
        zzakk.zzcrm.post(new zzxj(this, com_google_android_gms_internal_ads_zzanz_com_google_android_gms_internal_ads_zzxe));
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final com.google.android.gms.internal.ads.zzxe zzi(java.util.List<com.google.android.gms.internal.ads.zzanz<com.google.android.gms.internal.ads.zzxe>> r5) {
        /*
        r4 = this;
        r2 = r4.mLock;
        monitor-enter(r2);
        r0 = r4.zzbuf;	 Catch:{ all -> 0x003d }
        if (r0 == 0) goto L_0x000f;
    L_0x0007:
        r1 = new com.google.android.gms.internal.ads.zzxe;	 Catch:{ all -> 0x003d }
        r0 = -1;
        r1.<init>(r0);	 Catch:{ all -> 0x003d }
        monitor-exit(r2);	 Catch:{ all -> 0x003d }
    L_0x000e:
        return r1;
    L_0x000f:
        monitor-exit(r2);	 Catch:{ all -> 0x003d }
        r2 = r5.iterator();
    L_0x0014:
        r0 = r2.hasNext();
        if (r0 == 0) goto L_0x0040;
    L_0x001a:
        r0 = r2.next();
        r0 = (com.google.android.gms.internal.ads.zzanz) r0;
        r1 = r0.get();	 Catch:{ InterruptedException -> 0x0035, ExecutionException -> 0x004b }
        r1 = (com.google.android.gms.internal.ads.zzxe) r1;	 Catch:{ InterruptedException -> 0x0035, ExecutionException -> 0x004b }
        r3 = r4.zzbui;	 Catch:{ InterruptedException -> 0x0035, ExecutionException -> 0x004b }
        r3.add(r1);	 Catch:{ InterruptedException -> 0x0035, ExecutionException -> 0x004b }
        if (r1 == 0) goto L_0x0014;
    L_0x002d:
        r3 = r1.zzbtv;	 Catch:{ InterruptedException -> 0x0035, ExecutionException -> 0x004b }
        if (r3 != 0) goto L_0x0014;
    L_0x0031:
        r4.zza(r0);	 Catch:{ InterruptedException -> 0x0035, ExecutionException -> 0x004b }
        goto L_0x000e;
    L_0x0035:
        r0 = move-exception;
    L_0x0036:
        r1 = "Exception while processing an adapter; continuing with other adapters";
        com.google.android.gms.internal.ads.zzane.zzc(r1, r0);
        goto L_0x0014;
    L_0x003d:
        r0 = move-exception;
        monitor-exit(r2);	 Catch:{ all -> 0x003d }
        throw r0;
    L_0x0040:
        r0 = 0;
        r4.zza(r0);
        r1 = new com.google.android.gms.internal.ads.zzxe;
        r0 = 1;
        r1.<init>(r0);
        goto L_0x000e;
    L_0x004b:
        r0 = move-exception;
        goto L_0x0036;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzxh.zzi(java.util.List):com.google.android.gms.internal.ads.zzxe");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final com.google.android.gms.internal.ads.zzxe zzj(java.util.List<com.google.android.gms.internal.ads.zzanz<com.google.android.gms.internal.ads.zzxe>> r16) {
        /*
        r15 = this;
        r1 = r15.mLock;
        monitor-enter(r1);
        r0 = r15.zzbuf;	 Catch:{ all -> 0x007e }
        if (r0 == 0) goto L_0x000f;
    L_0x0007:
        r2 = new com.google.android.gms.internal.ads.zzxe;	 Catch:{ all -> 0x007e }
        r0 = -1;
        r2.<init>(r0);	 Catch:{ all -> 0x007e }
        monitor-exit(r1);	 Catch:{ all -> 0x007e }
    L_0x000e:
        return r2;
    L_0x000f:
        monitor-exit(r1);	 Catch:{ all -> 0x007e }
        r4 = -1;
        r3 = 0;
        r2 = 0;
        r0 = r15.zzbtj;
        r0 = r0.zzbsy;
        r6 = -1;
        r0 = (r0 > r6 ? 1 : (r0 == r6 ? 0 : -1));
        if (r0 == 0) goto L_0x0081;
    L_0x001d:
        r0 = r15.zzbtj;
        r0 = r0.zzbsy;
    L_0x0021:
        r8 = r16.iterator();
        r6 = r0;
    L_0x0026:
        r0 = r8.hasNext();
        if (r0 == 0) goto L_0x00b9;
    L_0x002c:
        r0 = r8.next();
        r0 = (com.google.android.gms.internal.ads.zzanz) r0;
        r1 = com.google.android.gms.ads.internal.zzbv.zzer();
        r10 = r1.currentTimeMillis();
        r12 = 0;
        r1 = (r6 > r12 ? 1 : (r6 == r12 ? 0 : -1));
        if (r1 != 0) goto L_0x0084;
    L_0x0040:
        r1 = r0.isDone();	 Catch:{ InterruptedException -> 0x00c6, ExecutionException -> 0x00c8, RemoteException -> 0x008d, TimeoutException -> 0x00ca }
        if (r1 == 0) goto L_0x0084;
    L_0x0046:
        r1 = r0.get();	 Catch:{ InterruptedException -> 0x00c6, ExecutionException -> 0x00c8, RemoteException -> 0x008d, TimeoutException -> 0x00ca }
        r1 = (com.google.android.gms.internal.ads.zzxe) r1;	 Catch:{ InterruptedException -> 0x00c6, ExecutionException -> 0x00c8, RemoteException -> 0x008d, TimeoutException -> 0x00ca }
    L_0x004c:
        r5 = r15.zzbui;	 Catch:{ InterruptedException -> 0x00c6, ExecutionException -> 0x00c8, RemoteException -> 0x008d, TimeoutException -> 0x00ca }
        r5.add(r1);	 Catch:{ InterruptedException -> 0x00c6, ExecutionException -> 0x00c8, RemoteException -> 0x008d, TimeoutException -> 0x00ca }
        if (r1 == 0) goto L_0x00cc;
    L_0x0053:
        r5 = r1.zzbtv;	 Catch:{ InterruptedException -> 0x00c6, ExecutionException -> 0x00c8, RemoteException -> 0x008d, TimeoutException -> 0x00ca }
        if (r5 != 0) goto L_0x00cc;
    L_0x0057:
        r5 = r1.zzbua;	 Catch:{ InterruptedException -> 0x00c6, ExecutionException -> 0x00c8, RemoteException -> 0x008d, TimeoutException -> 0x00ca }
        if (r5 == 0) goto L_0x00cc;
    L_0x005b:
        r9 = r5.zzmm();	 Catch:{ InterruptedException -> 0x00c6, ExecutionException -> 0x00c8, RemoteException -> 0x008d, TimeoutException -> 0x00ca }
        if (r9 <= r4) goto L_0x00cc;
    L_0x0061:
        r2 = r5.zzmm();	 Catch:{ InterruptedException -> 0x00c6, ExecutionException -> 0x00c8, RemoteException -> 0x008d, TimeoutException -> 0x00ca }
        r14 = r1;
        r1 = r0;
        r0 = r14;
    L_0x0068:
        r3 = com.google.android.gms.ads.internal.zzbv.zzer();
        r4 = r3.currentTimeMillis();
        r4 = r4 - r10;
        r4 = r6 - r4;
        r6 = 0;
        r4 = java.lang.Math.max(r4, r6);
        r6 = r4;
        r3 = r1;
        r4 = r2;
        r2 = r0;
        goto L_0x0026;
    L_0x007e:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x007e }
        throw r0;
    L_0x0081:
        r0 = 10000; // 0x2710 float:1.4013E-41 double:4.9407E-320;
        goto L_0x0021;
    L_0x0084:
        r1 = java.util.concurrent.TimeUnit.MILLISECONDS;	 Catch:{ InterruptedException -> 0x00c6, ExecutionException -> 0x00c8, RemoteException -> 0x008d, TimeoutException -> 0x00ca }
        r1 = r0.get(r6, r1);	 Catch:{ InterruptedException -> 0x00c6, ExecutionException -> 0x00c8, RemoteException -> 0x008d, TimeoutException -> 0x00ca }
        r1 = (com.google.android.gms.internal.ads.zzxe) r1;	 Catch:{ InterruptedException -> 0x00c6, ExecutionException -> 0x00c8, RemoteException -> 0x008d, TimeoutException -> 0x00ca }
        goto L_0x004c;
    L_0x008d:
        r0 = move-exception;
    L_0x008e:
        r1 = "Exception while processing an adapter; continuing with other adapters";
        com.google.android.gms.internal.ads.zzane.zzc(r1, r0);	 Catch:{ all -> 0x00a7 }
        r0 = com.google.android.gms.ads.internal.zzbv.zzer();
        r0 = r0.currentTimeMillis();
        r0 = r0 - r10;
        r0 = r6 - r0;
        r6 = 0;
        r0 = java.lang.Math.max(r0, r6);
        r6 = r0;
        goto L_0x0026;
    L_0x00a7:
        r0 = move-exception;
        r1 = com.google.android.gms.ads.internal.zzbv.zzer();
        r2 = r1.currentTimeMillis();
        r2 = r2 - r10;
        r2 = r6 - r2;
        r4 = 0;
        java.lang.Math.max(r2, r4);
        throw r0;
    L_0x00b9:
        r15.zza(r3);
        if (r2 != 0) goto L_0x000e;
    L_0x00be:
        r2 = new com.google.android.gms.internal.ads.zzxe;
        r0 = 1;
        r2.<init>(r0);
        goto L_0x000e;
    L_0x00c6:
        r0 = move-exception;
        goto L_0x008e;
    L_0x00c8:
        r0 = move-exception;
        goto L_0x008e;
    L_0x00ca:
        r0 = move-exception;
        goto L_0x008e;
    L_0x00cc:
        r0 = r2;
        r1 = r3;
        r2 = r4;
        goto L_0x0068;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzxh.zzj(java.util.List):com.google.android.gms.internal.ads.zzxe");
    }

    public final void cancel() {
        synchronized (this.mLock) {
            this.zzbuf = true;
            for (zzxb cancel : this.zzbug.values()) {
                cancel.cancel();
            }
        }
    }

    public final zzxe zzh(List<zzwx> list) {
        zzane.zzck("Starting mediation.");
        ArrayList arrayList = new ArrayList();
        zzjn com_google_android_gms_internal_ads_zzjn = this.zzbuc.zzacv;
        int[] iArr = new int[2];
        if (com_google_android_gms_internal_ads_zzjn.zzard != null) {
            zzbv.zzfd();
            if (zzxg.zza(this.zzbuh, iArr)) {
                int i = iArr[0];
                int i2 = iArr[1];
                for (zzjn com_google_android_gms_internal_ads_zzjn2 : com_google_android_gms_internal_ads_zzjn.zzard) {
                    if (i == com_google_android_gms_internal_ads_zzjn2.width && i2 == com_google_android_gms_internal_ads_zzjn2.height) {
                        break;
                    }
                }
            }
        }
        zzjn com_google_android_gms_internal_ads_zzjn22 = com_google_android_gms_internal_ads_zzjn;
        for (zzwx com_google_android_gms_internal_ads_zzwx : list) {
            String str = "Trying mediation network: ";
            String valueOf = String.valueOf(com_google_android_gms_internal_ads_zzwx.zzbrs);
            zzane.zzdj(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
            for (String com_google_android_gms_internal_ads_zzxb : com_google_android_gms_internal_ads_zzwx.zzbrt) {
                zzxb com_google_android_gms_internal_ads_zzxb2 = new zzxb(this.mContext, com_google_android_gms_internal_ads_zzxb, this.zzwh, this.zzbtj, com_google_android_gms_internal_ads_zzwx, this.zzbuc.zzccv, com_google_android_gms_internal_ads_zzjn22, this.zzbuc.zzacr, this.zzael, this.zzbtn, this.zzbuc.zzadj, this.zzbuc.zzads, this.zzbuc.zzcdk, this.zzbuc.zzcef, this.zzbto);
                zzanz zza = zzaki.zza(new zzxi(this, com_google_android_gms_internal_ads_zzxb2));
                this.zzbug.put(zza, com_google_android_gms_internal_ads_zzxb2);
                arrayList.add(zza);
            }
        }
        switch (this.zzbue) {
            case 2:
                return zzj(arrayList);
            default:
                return zzi(arrayList);
        }
    }

    public final List<zzxe> zzme() {
        return this.zzbui;
    }
}
