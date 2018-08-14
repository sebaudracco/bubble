package com.google.android.gms.internal.ads;

import java.util.concurrent.ExecutionException;

public final class zzdo extends zzei {
    private static final Object zztn = new Object();
    private static volatile zzbj zzto = null;
    private zzax zztp = null;

    public zzdo(zzcz com_google_android_gms_internal_ads_zzcz, String str, String str2, zzba com_google_android_gms_internal_ads_zzba, int i, int i2, zzax com_google_android_gms_internal_ads_zzax) {
        super(com_google_android_gms_internal_ads_zzcz, str, str2, com_google_android_gms_internal_ads_zzba, i, 27);
        this.zztp = com_google_android_gms_internal_ads_zzax;
    }

    private final String zzas() {
        try {
            if (this.zzps.zzak() != null) {
                this.zzps.zzak().get();
            }
            zzba zzaj = this.zzps.zzaj();
            if (!(zzaj == null || zzaj.zzcx == null)) {
                return zzaj.zzcx;
            }
        } catch (InterruptedException e) {
        } catch (ExecutionException e2) {
        }
        return null;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected final void zzar() throws java.lang.IllegalAccessException, java.lang.reflect.InvocationTargetException {
        /*
        r10 = this;
        r3 = 3;
        r4 = 2;
        r1 = 1;
        r2 = 0;
        r0 = zzto;
        if (r0 == 0) goto L_0x002c;
    L_0x0008:
        r0 = zzto;
        r0 = r0.zzcx;
        r0 = com.google.android.gms.internal.ads.zzdg.zzo(r0);
        if (r0 != 0) goto L_0x002c;
    L_0x0012:
        r0 = zzto;
        r0 = r0.zzcx;
        r5 = "E";
        r0 = r0.equals(r5);
        if (r0 != 0) goto L_0x002c;
    L_0x001f:
        r0 = zzto;
        r0 = r0.zzcx;
        r5 = "0000000000000000000000000000000000000000000000000000000000000000";
        r0 = r0.equals(r5);
        if (r0 == 0) goto L_0x00be;
    L_0x002c:
        r0 = r1;
    L_0x002d:
        if (r0 == 0) goto L_0x0089;
    L_0x002f:
        r5 = zztn;
        monitor-enter(r5);
        r0 = r10.zztp;	 Catch:{ all -> 0x0112 }
        r0 = 0;
        r0 = com.google.android.gms.internal.ads.zzdg.zzo(r0);	 Catch:{ all -> 0x0112 }
        if (r0 != 0) goto L_0x00c1;
    L_0x003b:
        r0 = 4;
        r3 = r0;
    L_0x003d:
        r6 = r10.zztz;	 Catch:{ all -> 0x0112 }
        r7 = 0;
        r0 = 3;
        r8 = new java.lang.Object[r0];	 Catch:{ all -> 0x0112 }
        r0 = 0;
        r9 = r10.zzps;	 Catch:{ all -> 0x0112 }
        r9 = r9.getContext();	 Catch:{ all -> 0x0112 }
        r8[r0] = r9;	 Catch:{ all -> 0x0112 }
        r9 = 1;
        if (r3 != r4) goto L_0x0106;
    L_0x004f:
        r0 = r1;
    L_0x0050:
        r0 = java.lang.Boolean.valueOf(r0);	 Catch:{ all -> 0x0112 }
        r8[r9] = r0;	 Catch:{ all -> 0x0112 }
        r0 = 2;
        r1 = com.google.android.gms.internal.ads.zznk.zzbav;	 Catch:{ all -> 0x0112 }
        r2 = com.google.android.gms.internal.ads.zzkb.zzik();	 Catch:{ all -> 0x0112 }
        r1 = r2.zzd(r1);	 Catch:{ all -> 0x0112 }
        r8[r0] = r1;	 Catch:{ all -> 0x0112 }
        r0 = r6.invoke(r7, r8);	 Catch:{ all -> 0x0112 }
        r0 = (java.lang.String) r0;	 Catch:{ all -> 0x0112 }
        r1 = new com.google.android.gms.internal.ads.zzbj;	 Catch:{ all -> 0x0112 }
        r1.<init>(r0);	 Catch:{ all -> 0x0112 }
        zzto = r1;	 Catch:{ all -> 0x0112 }
        r0 = r1.zzcx;	 Catch:{ all -> 0x0112 }
        r0 = com.google.android.gms.internal.ads.zzdg.zzo(r0);	 Catch:{ all -> 0x0112 }
        if (r0 != 0) goto L_0x0085;
    L_0x0078:
        r0 = zzto;	 Catch:{ all -> 0x0112 }
        r0 = r0.zzcx;	 Catch:{ all -> 0x0112 }
        r1 = "E";
        r0 = r0.equals(r1);	 Catch:{ all -> 0x0112 }
        if (r0 == 0) goto L_0x0088;
    L_0x0085:
        switch(r3) {
            case 3: goto L_0x0115;
            case 4: goto L_0x0109;
            default: goto L_0x0088;
        };	 Catch:{ all -> 0x0112 }
    L_0x0088:
        monitor-exit(r5);	 Catch:{ all -> 0x0112 }
    L_0x0089:
        r1 = r10.zztq;
        monitor-enter(r1);
        r0 = zzto;	 Catch:{ all -> 0x0125 }
        if (r0 == 0) goto L_0x00bc;
    L_0x0090:
        r0 = r10.zztq;	 Catch:{ all -> 0x0125 }
        r2 = zzto;	 Catch:{ all -> 0x0125 }
        r2 = r2.zzcx;	 Catch:{ all -> 0x0125 }
        r0.zzcx = r2;	 Catch:{ all -> 0x0125 }
        r0 = r10.zztq;	 Catch:{ all -> 0x0125 }
        r2 = zzto;	 Catch:{ all -> 0x0125 }
        r2 = r2.zzhx;	 Catch:{ all -> 0x0125 }
        r2 = java.lang.Long.valueOf(r2);	 Catch:{ all -> 0x0125 }
        r0.zzeb = r2;	 Catch:{ all -> 0x0125 }
        r0 = r10.zztq;	 Catch:{ all -> 0x0125 }
        r2 = zzto;	 Catch:{ all -> 0x0125 }
        r2 = r2.zzcz;	 Catch:{ all -> 0x0125 }
        r0.zzcz = r2;	 Catch:{ all -> 0x0125 }
        r0 = r10.zztq;	 Catch:{ all -> 0x0125 }
        r2 = zzto;	 Catch:{ all -> 0x0125 }
        r2 = r2.zzda;	 Catch:{ all -> 0x0125 }
        r0.zzda = r2;	 Catch:{ all -> 0x0125 }
        r0 = r10.zztq;	 Catch:{ all -> 0x0125 }
        r2 = zzto;	 Catch:{ all -> 0x0125 }
        r2 = r2.zzdb;	 Catch:{ all -> 0x0125 }
        r0.zzdb = r2;	 Catch:{ all -> 0x0125 }
    L_0x00bc:
        monitor-exit(r1);	 Catch:{ all -> 0x0125 }
        return;
    L_0x00be:
        r0 = r2;
        goto L_0x002d;
    L_0x00c1:
        r0 = r10.zztp;	 Catch:{ all -> 0x0112 }
        r0 = 0;
        com.google.android.gms.internal.ads.zzdg.zzo(r0);	 Catch:{ all -> 0x0112 }
        r0 = 0;
        r0 = java.lang.Boolean.valueOf(r0);	 Catch:{ all -> 0x0112 }
        r0 = r0.booleanValue();	 Catch:{ all -> 0x0112 }
        if (r0 == 0) goto L_0x0101;
    L_0x00d2:
        r0 = r10.zzps;	 Catch:{ all -> 0x0112 }
        r0 = r0.zzah();	 Catch:{ all -> 0x0112 }
        if (r0 == 0) goto L_0x0104;
    L_0x00da:
        r0 = com.google.android.gms.internal.ads.zznk.zzbbb;	 Catch:{ all -> 0x0112 }
        r6 = com.google.android.gms.internal.ads.zzkb.zzik();	 Catch:{ all -> 0x0112 }
        r0 = r6.zzd(r0);	 Catch:{ all -> 0x0112 }
        r0 = (java.lang.Boolean) r0;	 Catch:{ all -> 0x0112 }
        r0 = r0.booleanValue();	 Catch:{ all -> 0x0112 }
        if (r0 == 0) goto L_0x0104;
    L_0x00ec:
        r0 = com.google.android.gms.internal.ads.zznk.zzbbc;	 Catch:{ all -> 0x0112 }
        r6 = com.google.android.gms.internal.ads.zzkb.zzik();	 Catch:{ all -> 0x0112 }
        r0 = r6.zzd(r0);	 Catch:{ all -> 0x0112 }
        r0 = (java.lang.Boolean) r0;	 Catch:{ all -> 0x0112 }
        r0 = r0.booleanValue();	 Catch:{ all -> 0x0112 }
        if (r0 == 0) goto L_0x0104;
    L_0x00fe:
        r0 = r1;
    L_0x00ff:
        if (r0 != 0) goto L_0x003d;
    L_0x0101:
        r3 = r4;
        goto L_0x003d;
    L_0x0104:
        r0 = r2;
        goto L_0x00ff;
    L_0x0106:
        r0 = r2;
        goto L_0x0050;
    L_0x0109:
        r0 = zzto;	 Catch:{ all -> 0x0112 }
        r1 = 0;
        r1 = r1.zzcx;	 Catch:{ all -> 0x0112 }
        r0.zzcx = r1;	 Catch:{ all -> 0x0112 }
        goto L_0x0088;
    L_0x0112:
        r0 = move-exception;
        monitor-exit(r5);	 Catch:{ all -> 0x0112 }
        throw r0;
    L_0x0115:
        r0 = r10.zzas();	 Catch:{ all -> 0x0112 }
        r1 = com.google.android.gms.internal.ads.zzdg.zzo(r0);	 Catch:{ all -> 0x0112 }
        if (r1 != 0) goto L_0x0088;
    L_0x011f:
        r1 = zzto;	 Catch:{ all -> 0x0112 }
        r1.zzcx = r0;	 Catch:{ all -> 0x0112 }
        goto L_0x0088;
    L_0x0125:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x0125 }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzdo.zzar():void");
    }
}
