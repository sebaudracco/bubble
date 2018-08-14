package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map.Entry;

final class zzbda<T> implements zzbdm<T> {
    private final zzbcu zzdwl;
    private final boolean zzdwm;
    private final zzbee<?, ?> zzdwv;
    private final zzbbd<?> zzdww;

    private zzbda(zzbee<?, ?> com_google_android_gms_internal_ads_zzbee___, zzbbd<?> com_google_android_gms_internal_ads_zzbbd_, zzbcu com_google_android_gms_internal_ads_zzbcu) {
        this.zzdwv = com_google_android_gms_internal_ads_zzbee___;
        this.zzdwm = com_google_android_gms_internal_ads_zzbbd_.zzh(com_google_android_gms_internal_ads_zzbcu);
        this.zzdww = com_google_android_gms_internal_ads_zzbbd_;
        this.zzdwl = com_google_android_gms_internal_ads_zzbcu;
    }

    static <T> zzbda<T> zza(zzbee<?, ?> com_google_android_gms_internal_ads_zzbee___, zzbbd<?> com_google_android_gms_internal_ads_zzbbd_, zzbcu com_google_android_gms_internal_ads_zzbcu) {
        return new zzbda(com_google_android_gms_internal_ads_zzbee___, com_google_android_gms_internal_ads_zzbbd_, com_google_android_gms_internal_ads_zzbcu);
    }

    public final boolean equals(T t, T t2) {
        return !this.zzdwv.zzac(t).equals(this.zzdwv.zzac(t2)) ? false : this.zzdwm ? this.zzdww.zzm(t).equals(this.zzdww.zzm(t2)) : true;
    }

    public final int hashCode(T t) {
        int hashCode = this.zzdwv.zzac(t).hashCode();
        return this.zzdwm ? (hashCode * 53) + this.zzdww.zzm(t).hashCode() : hashCode;
    }

    public final T newInstance() {
        return this.zzdwl.zzadf().zzadj();
    }

    public final void zza(T t, zzbdl com_google_android_gms_internal_ads_zzbdl, zzbbb com_google_android_gms_internal_ads_zzbbb) throws IOException {
        zzbee com_google_android_gms_internal_ads_zzbee = this.zzdwv;
        zzbbd com_google_android_gms_internal_ads_zzbbd = this.zzdww;
        Object zzad = com_google_android_gms_internal_ads_zzbee.zzad(t);
        zzbbg zzn = com_google_android_gms_internal_ads_zzbbd.zzn(t);
        while (com_google_android_gms_internal_ads_zzbdl.zzaci() != Integer.MAX_VALUE) {
            try {
                boolean zza;
                int tag = com_google_android_gms_internal_ads_zzbdl.getTag();
                Object zza2;
                if (tag != 11) {
                    if ((tag & 7) == 2) {
                        zza2 = com_google_android_gms_internal_ads_zzbbd.zza(com_google_android_gms_internal_ads_zzbbb, this.zzdwl, tag >>> 3);
                        if (zza2 != null) {
                            com_google_android_gms_internal_ads_zzbbd.zza(com_google_android_gms_internal_ads_zzbdl, zza2, com_google_android_gms_internal_ads_zzbbb, zzn);
                        } else {
                            zza = com_google_android_gms_internal_ads_zzbee.zza(zzad, com_google_android_gms_internal_ads_zzbdl);
                            continue;
                        }
                    } else {
                        zza = com_google_android_gms_internal_ads_zzbdl.zzacj();
                        continue;
                    }
                    if (!zza) {
                        return;
                    }
                }
                zzbah com_google_android_gms_internal_ads_zzbah = null;
                int i = 0;
                zza2 = null;
                while (com_google_android_gms_internal_ads_zzbdl.zzaci() != Integer.MAX_VALUE) {
                    int tag2 = com_google_android_gms_internal_ads_zzbdl.getTag();
                    if (tag2 == 16) {
                        i = com_google_android_gms_internal_ads_zzbdl.zzabt();
                        zza2 = com_google_android_gms_internal_ads_zzbbd.zza(com_google_android_gms_internal_ads_zzbbb, this.zzdwl, i);
                    } else if (tag2 == 26) {
                        if (zza2 != null) {
                            com_google_android_gms_internal_ads_zzbbd.zza(com_google_android_gms_internal_ads_zzbdl, zza2, com_google_android_gms_internal_ads_zzbbb, zzn);
                        } else {
                            com_google_android_gms_internal_ads_zzbah = com_google_android_gms_internal_ads_zzbdl.zzabs();
                        }
                    } else if (!com_google_android_gms_internal_ads_zzbdl.zzacj()) {
                        break;
                    }
                }
                if (com_google_android_gms_internal_ads_zzbdl.getTag() != 12) {
                    throw zzbbu.zzadp();
                } else if (com_google_android_gms_internal_ads_zzbah != null) {
                    if (zza2 != null) {
                        com_google_android_gms_internal_ads_zzbbd.zza(com_google_android_gms_internal_ads_zzbah, zza2, com_google_android_gms_internal_ads_zzbbb, zzn);
                    } else {
                        com_google_android_gms_internal_ads_zzbee.zza(zzad, i, com_google_android_gms_internal_ads_zzbah);
                    }
                }
                zza = true;
                continue;
                if (zza) {
                    return;
                }
            } finally {
                com_google_android_gms_internal_ads_zzbee.zzf(t, zzad);
            }
        }
        com_google_android_gms_internal_ads_zzbee.zzf(t, zzad);
    }

    public final void zza(T t, zzbey com_google_android_gms_internal_ads_zzbey) throws IOException {
        Iterator it = this.zzdww.zzm(t).iterator();
        while (it.hasNext()) {
            Entry entry = (Entry) it.next();
            zzbbi com_google_android_gms_internal_ads_zzbbi = (zzbbi) entry.getKey();
            if (com_google_android_gms_internal_ads_zzbbi.zzacz() != zzbex.MESSAGE || com_google_android_gms_internal_ads_zzbbi.zzada() || com_google_android_gms_internal_ads_zzbbi.zzadb()) {
                throw new IllegalStateException("Found invalid MessageSet item.");
            } else if (entry instanceof zzbbz) {
                com_google_android_gms_internal_ads_zzbey.zza(com_google_android_gms_internal_ads_zzbbi.zzhq(), ((zzbbz) entry).zzadv().zzaav());
            } else {
                com_google_android_gms_internal_ads_zzbey.zza(com_google_android_gms_internal_ads_zzbbi.zzhq(), entry.getValue());
            }
        }
        zzbee com_google_android_gms_internal_ads_zzbee = this.zzdwv;
        com_google_android_gms_internal_ads_zzbee.zzc(com_google_android_gms_internal_ads_zzbee.zzac(t), com_google_android_gms_internal_ads_zzbey);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(T r10, byte[] r11, int r12, int r13, com.google.android.gms.internal.ads.zzbae r14) throws java.io.IOException {
        /*
        r9 = this;
        r7 = 2;
        r0 = r10;
        r0 = (com.google.android.gms.internal.ads.zzbbo) r0;
        r4 = r0.zzdtt;
        r0 = com.google.android.gms.internal.ads.zzbef.zzagc();
        if (r4 != r0) goto L_0x0014;
    L_0x000c:
        r4 = com.google.android.gms.internal.ads.zzbef.zzagd();
        r10 = (com.google.android.gms.internal.ads.zzbbo) r10;
        r10.zzdtt = r4;
    L_0x0014:
        if (r12 >= r13) goto L_0x0074;
    L_0x0016:
        r2 = com.google.android.gms.internal.ads.zzbad.zza(r11, r12, r14);
        r0 = r14.zzdpl;
        r1 = 11;
        if (r0 == r1) goto L_0x0031;
    L_0x0020:
        r1 = r0 & 7;
        if (r1 != r7) goto L_0x002c;
    L_0x0024:
        r1 = r11;
        r3 = r13;
        r5 = r14;
        r12 = com.google.android.gms.internal.ads.zzbad.zza(r0, r1, r2, r3, r4, r5);
        goto L_0x0014;
    L_0x002c:
        r12 = com.google.android.gms.internal.ads.zzbad.zza(r0, r11, r2, r13, r14);
        goto L_0x0014;
    L_0x0031:
        r1 = 0;
        r0 = 0;
        r8 = r0;
        r0 = r2;
        r2 = r1;
        r1 = r8;
    L_0x0037:
        if (r0 >= r13) goto L_0x0069;
    L_0x0039:
        r0 = com.google.android.gms.internal.ads.zzbad.zza(r11, r0, r14);
        r3 = r14.zzdpl;
        r5 = r3 >>> 3;
        r6 = r3 & 7;
        switch(r5) {
            case 2: goto L_0x004f;
            case 3: goto L_0x005b;
            default: goto L_0x0046;
        };
    L_0x0046:
        r5 = 12;
        if (r3 == r5) goto L_0x0069;
    L_0x004a:
        r0 = com.google.android.gms.internal.ads.zzbad.zza(r3, r11, r0, r13, r14);
        goto L_0x0037;
    L_0x004f:
        if (r6 != 0) goto L_0x0046;
    L_0x0051:
        r2 = com.google.android.gms.internal.ads.zzbad.zza(r11, r0, r14);
        r0 = r14.zzdpl;
        r8 = r0;
        r0 = r2;
        r2 = r8;
        goto L_0x0037;
    L_0x005b:
        if (r6 != r7) goto L_0x0046;
    L_0x005d:
        r1 = com.google.android.gms.internal.ads.zzbad.zze(r11, r0, r14);
        r0 = r14.zzdpn;
        r0 = (com.google.android.gms.internal.ads.zzbah) r0;
        r8 = r0;
        r0 = r1;
        r1 = r8;
        goto L_0x0037;
    L_0x0069:
        if (r1 == 0) goto L_0x0072;
    L_0x006b:
        r2 = r2 << 3;
        r2 = r2 | 2;
        r4.zzb(r2, r1);
    L_0x0072:
        r12 = r0;
        goto L_0x0014;
    L_0x0074:
        if (r12 == r13) goto L_0x007b;
    L_0x0076:
        r0 = com.google.android.gms.internal.ads.zzbbu.zzadr();
        throw r0;
    L_0x007b:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzbda.zza(java.lang.Object, byte[], int, int, com.google.android.gms.internal.ads.zzbae):void");
    }

    public final boolean zzaa(T t) {
        return this.zzdww.zzm(t).isInitialized();
    }

    public final void zzc(T t, T t2) {
        zzbdo.zza(this.zzdwv, (Object) t, (Object) t2);
        if (this.zzdwm) {
            zzbdo.zza(this.zzdww, (Object) t, (Object) t2);
        }
    }

    public final void zzo(T t) {
        this.zzdwv.zzo(t);
        this.zzdww.zzo(t);
    }

    public final int zzy(T t) {
        zzbee com_google_android_gms_internal_ads_zzbee = this.zzdwv;
        int zzae = com_google_android_gms_internal_ads_zzbee.zzae(com_google_android_gms_internal_ads_zzbee.zzac(t)) + 0;
        return this.zzdwm ? zzae + this.zzdww.zzm(t).zzacx() : zzae;
    }
}
