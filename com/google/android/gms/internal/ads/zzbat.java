package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.util.List;

final class zzbat implements zzbdl {
    private int tag;
    private final zzbaq zzdqi;
    private int zzdqj;
    private int zzdqk = 0;

    private zzbat(zzbaq com_google_android_gms_internal_ads_zzbaq) {
        this.zzdqi = (zzbaq) zzbbq.zza((Object) com_google_android_gms_internal_ads_zzbaq, "input");
        this.zzdqi.zzdqa = this;
    }

    public static zzbat zza(zzbaq com_google_android_gms_internal_ads_zzbaq) {
        return com_google_android_gms_internal_ads_zzbaq.zzdqa != null ? com_google_android_gms_internal_ads_zzbaq.zzdqa : new zzbat(com_google_android_gms_internal_ads_zzbaq);
    }

    private final Object zza(zzbes com_google_android_gms_internal_ads_zzbes, Class<?> cls, zzbbb com_google_android_gms_internal_ads_zzbbb) throws IOException {
        switch (zzbau.zzdql[com_google_android_gms_internal_ads_zzbes.ordinal()]) {
            case 1:
                return Boolean.valueOf(zzabq());
            case 2:
                return zzabs();
            case 3:
                return Double.valueOf(readDouble());
            case 4:
                return Integer.valueOf(zzabu());
            case 5:
                return Integer.valueOf(zzabp());
            case 6:
                return Long.valueOf(zzabo());
            case 7:
                return Float.valueOf(readFloat());
            case 8:
                return Integer.valueOf(zzabn());
            case 9:
                return Long.valueOf(zzabm());
            case 10:
                zzbv(2);
                return zzc(zzbdg.zzaeo().zze(cls), com_google_android_gms_internal_ads_zzbbb);
            case 11:
                return Integer.valueOf(zzabv());
            case 12:
                return Long.valueOf(zzabw());
            case 13:
                return Integer.valueOf(zzabx());
            case 14:
                return Long.valueOf(zzaby());
            case 15:
                return zzabr();
            case 16:
                return Integer.valueOf(zzabt());
            case 17:
                return Long.valueOf(zzabl());
            default:
                throw new RuntimeException("unsupported field type.");
        }
    }

    private final void zza(List<String> list, boolean z) throws IOException {
        if ((this.tag & 7) != 2) {
            throw zzbbu.zzadq();
        } else if (!(list instanceof zzbcd) || z) {
            do {
                list.add(z ? zzabr() : readString());
                if (!this.zzdqi.zzaca()) {
                    r0 = this.zzdqi.zzabk();
                } else {
                    return;
                }
            } while (r0 == this.tag);
            this.zzdqk = r0;
        } else {
            zzbcd com_google_android_gms_internal_ads_zzbcd = (zzbcd) list;
            do {
                com_google_android_gms_internal_ads_zzbcd.zzap(zzabs());
                if (!this.zzdqi.zzaca()) {
                    r0 = this.zzdqi.zzabk();
                } else {
                    return;
                }
            } while (r0 == this.tag);
            this.zzdqk = r0;
        }
    }

    private final void zzbv(int i) throws IOException {
        if ((this.tag & 7) != i) {
            throw zzbbu.zzadq();
        }
    }

    private static void zzbw(int i) throws IOException {
        if ((i & 7) != 0) {
            throw zzbbu.zzadr();
        }
    }

    private static void zzbx(int i) throws IOException {
        if ((i & 3) != 0) {
            throw zzbbu.zzadr();
        }
    }

    private final void zzby(int i) throws IOException {
        if (this.zzdqi.zzacb() != i) {
            throw zzbbu.zzadl();
        }
    }

    private final <T> T zzc(zzbdm<T> com_google_android_gms_internal_ads_zzbdm_T, zzbbb com_google_android_gms_internal_ads_zzbbb) throws IOException {
        int zzabt = this.zzdqi.zzabt();
        if (this.zzdqi.zzdpx >= this.zzdqi.zzdpy) {
            throw new zzbbu("Protocol message had too many levels of nesting.  May be malicious.  Use CodedInputStream.setRecursionLimit() to increase the depth limit.");
        }
        zzabt = this.zzdqi.zzbr(zzabt);
        T newInstance = com_google_android_gms_internal_ads_zzbdm_T.newInstance();
        zzbaq com_google_android_gms_internal_ads_zzbaq = this.zzdqi;
        com_google_android_gms_internal_ads_zzbaq.zzdpx++;
        com_google_android_gms_internal_ads_zzbdm_T.zza(newInstance, this, com_google_android_gms_internal_ads_zzbbb);
        com_google_android_gms_internal_ads_zzbdm_T.zzo(newInstance);
        this.zzdqi.zzbp(0);
        com_google_android_gms_internal_ads_zzbaq = this.zzdqi;
        com_google_android_gms_internal_ads_zzbaq.zzdpx--;
        this.zzdqi.zzbs(zzabt);
        return newInstance;
    }

    private final <T> T zzd(zzbdm<T> com_google_android_gms_internal_ads_zzbdm_T, zzbbb com_google_android_gms_internal_ads_zzbbb) throws IOException {
        int i = this.zzdqj;
        this.zzdqj = ((this.tag >>> 3) << 3) | 4;
        try {
            T newInstance = com_google_android_gms_internal_ads_zzbdm_T.newInstance();
            com_google_android_gms_internal_ads_zzbdm_T.zza(newInstance, this, com_google_android_gms_internal_ads_zzbbb);
            com_google_android_gms_internal_ads_zzbdm_T.zzo(newInstance);
            if (this.tag == this.zzdqj) {
                return newInstance;
            }
            throw zzbbu.zzadr();
        } finally {
            this.zzdqj = i;
        }
    }

    public final int getTag() {
        return this.tag;
    }

    public final double readDouble() throws IOException {
        zzbv(1);
        return this.zzdqi.readDouble();
    }

    public final float readFloat() throws IOException {
        zzbv(5);
        return this.zzdqi.readFloat();
    }

    public final String readString() throws IOException {
        zzbv(2);
        return this.zzdqi.readString();
    }

    public final void readStringList(List<String> list) throws IOException {
        zza((List) list, false);
    }

    public final <T> T zza(zzbdm<T> com_google_android_gms_internal_ads_zzbdm_T, zzbbb com_google_android_gms_internal_ads_zzbbb) throws IOException {
        zzbv(2);
        return zzc(com_google_android_gms_internal_ads_zzbdm_T, com_google_android_gms_internal_ads_zzbbb);
    }

    public final <T> void zza(List<T> list, zzbdm<T> com_google_android_gms_internal_ads_zzbdm_T, zzbbb com_google_android_gms_internal_ads_zzbbb) throws IOException {
        if ((this.tag & 7) != 2) {
            throw zzbbu.zzadq();
        }
        int zzabk;
        int i = this.tag;
        do {
            list.add(zzc(com_google_android_gms_internal_ads_zzbdm_T, com_google_android_gms_internal_ads_zzbbb));
            if (!this.zzdqi.zzaca() && this.zzdqk == 0) {
                zzabk = this.zzdqi.zzabk();
            } else {
                return;
            }
        } while (zzabk == i);
        this.zzdqk = zzabk;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final <K, V> void zza(java.util.Map<K, V> r7, com.google.android.gms.internal.ads.zzbcn<K, V> r8, com.google.android.gms.internal.ads.zzbbb r9) throws java.io.IOException {
        /*
        r6 = this;
        r0 = 2;
        r6.zzbv(r0);
        r0 = r6.zzdqi;
        r0 = r0.zzabt();
        r1 = r6.zzdqi;
        r2 = r1.zzbr(r0);
        r1 = r8.zzdvz;
        r0 = r8.zzdwb;
    L_0x0014:
        r3 = r6.zzaci();	 Catch:{ all -> 0x0047 }
        r4 = 2147483647; // 0x7fffffff float:NaN double:1.060997895E-314;
        if (r3 == r4) goto L_0x0064;
    L_0x001d:
        r4 = r6.zzdqi;	 Catch:{ all -> 0x0047 }
        r4 = r4.zzaca();	 Catch:{ all -> 0x0047 }
        if (r4 != 0) goto L_0x0064;
    L_0x0025:
        switch(r3) {
            case 1: goto L_0x004e;
            case 2: goto L_0x0057;
            default: goto L_0x0028;
        };
    L_0x0028:
        r3 = r6.zzacj();	 Catch:{ zzbbv -> 0x0037 }
        if (r3 != 0) goto L_0x0014;
    L_0x002e:
        r3 = new com.google.android.gms.internal.ads.zzbbu;	 Catch:{ zzbbv -> 0x0037 }
        r4 = "Unable to parse map entry.";
        r3.<init>(r4);	 Catch:{ zzbbv -> 0x0037 }
        throw r3;	 Catch:{ zzbbv -> 0x0037 }
    L_0x0037:
        r3 = move-exception;
        r3 = r6.zzacj();	 Catch:{ all -> 0x0047 }
        if (r3 != 0) goto L_0x0014;
    L_0x003e:
        r0 = new com.google.android.gms.internal.ads.zzbbu;	 Catch:{ all -> 0x0047 }
        r1 = "Unable to parse map entry.";
        r0.<init>(r1);	 Catch:{ all -> 0x0047 }
        throw r0;	 Catch:{ all -> 0x0047 }
    L_0x0047:
        r0 = move-exception;
        r1 = r6.zzdqi;
        r1.zzbs(r2);
        throw r0;
    L_0x004e:
        r3 = r8.zzdvy;	 Catch:{ zzbbv -> 0x0037 }
        r4 = 0;
        r5 = 0;
        r1 = r6.zza(r3, r4, r5);	 Catch:{ zzbbv -> 0x0037 }
        goto L_0x0014;
    L_0x0057:
        r3 = r8.zzdwa;	 Catch:{ zzbbv -> 0x0037 }
        r4 = r8.zzdwb;	 Catch:{ zzbbv -> 0x0037 }
        r4 = r4.getClass();	 Catch:{ zzbbv -> 0x0037 }
        r0 = r6.zza(r3, r4, r9);	 Catch:{ zzbbv -> 0x0037 }
        goto L_0x0014;
    L_0x0064:
        r7.put(r1, r0);	 Catch:{ all -> 0x0047 }
        r0 = r6.zzdqi;
        r0.zzbs(r2);
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzbat.zza(java.util.Map, com.google.android.gms.internal.ads.zzbcn, com.google.android.gms.internal.ads.zzbbb):void");
    }

    public final void zzaa(List<Integer> list) throws IOException {
        int zzabt;
        if (list instanceof zzbbp) {
            zzbbp com_google_android_gms_internal_ads_zzbbp = (zzbbp) list;
            switch (this.tag & 7) {
                case 0:
                    break;
                case 2:
                    zzabt = this.zzdqi.zzabt() + this.zzdqi.zzacb();
                    do {
                        com_google_android_gms_internal_ads_zzbbp.zzco(this.zzdqi.zzabu());
                    } while (this.zzdqi.zzacb() < zzabt);
                    zzby(zzabt);
                    return;
                default:
                    throw zzbbu.zzadq();
            }
            do {
                com_google_android_gms_internal_ads_zzbbp.zzco(this.zzdqi.zzabu());
                if (!this.zzdqi.zzaca()) {
                    zzabt = this.zzdqi.zzabk();
                } else {
                    return;
                }
            } while (zzabt == this.tag);
            this.zzdqk = zzabt;
            return;
        }
        switch (this.tag & 7) {
            case 0:
                break;
            case 2:
                zzabt = this.zzdqi.zzabt() + this.zzdqi.zzacb();
                do {
                    list.add(Integer.valueOf(this.zzdqi.zzabu()));
                } while (this.zzdqi.zzacb() < zzabt);
                zzby(zzabt);
                return;
            default:
                throw zzbbu.zzadq();
        }
        do {
            list.add(Integer.valueOf(this.zzdqi.zzabu()));
            if (!this.zzdqi.zzaca()) {
                zzabt = this.zzdqi.zzabk();
            } else {
                return;
            }
        } while (zzabt == this.tag);
        this.zzdqk = zzabt;
    }

    public final void zzab(List<Integer> list) throws IOException {
        int zzabt;
        if (list instanceof zzbbp) {
            zzbbp com_google_android_gms_internal_ads_zzbbp = (zzbbp) list;
            switch (this.tag & 7) {
                case 2:
                    zzabt = this.zzdqi.zzabt();
                    zzbx(zzabt);
                    zzabt += this.zzdqi.zzacb();
                    do {
                        com_google_android_gms_internal_ads_zzbbp.zzco(this.zzdqi.zzabv());
                    } while (this.zzdqi.zzacb() < zzabt);
                    return;
                case 5:
                    break;
                default:
                    throw zzbbu.zzadq();
            }
            do {
                com_google_android_gms_internal_ads_zzbbp.zzco(this.zzdqi.zzabv());
                if (!this.zzdqi.zzaca()) {
                    zzabt = this.zzdqi.zzabk();
                } else {
                    return;
                }
            } while (zzabt == this.tag);
            this.zzdqk = zzabt;
            return;
        }
        switch (this.tag & 7) {
            case 2:
                zzabt = this.zzdqi.zzabt();
                zzbx(zzabt);
                zzabt += this.zzdqi.zzacb();
                do {
                    list.add(Integer.valueOf(this.zzdqi.zzabv()));
                } while (this.zzdqi.zzacb() < zzabt);
                return;
            case 5:
                break;
            default:
                throw zzbbu.zzadq();
        }
        do {
            list.add(Integer.valueOf(this.zzdqi.zzabv()));
            if (!this.zzdqi.zzaca()) {
                zzabt = this.zzdqi.zzabk();
            } else {
                return;
            }
        } while (zzabt == this.tag);
        this.zzdqk = zzabt;
    }

    public final long zzabl() throws IOException {
        zzbv(0);
        return this.zzdqi.zzabl();
    }

    public final long zzabm() throws IOException {
        zzbv(0);
        return this.zzdqi.zzabm();
    }

    public final int zzabn() throws IOException {
        zzbv(0);
        return this.zzdqi.zzabn();
    }

    public final long zzabo() throws IOException {
        zzbv(1);
        return this.zzdqi.zzabo();
    }

    public final int zzabp() throws IOException {
        zzbv(5);
        return this.zzdqi.zzabp();
    }

    public final boolean zzabq() throws IOException {
        zzbv(0);
        return this.zzdqi.zzabq();
    }

    public final String zzabr() throws IOException {
        zzbv(2);
        return this.zzdqi.zzabr();
    }

    public final zzbah zzabs() throws IOException {
        zzbv(2);
        return this.zzdqi.zzabs();
    }

    public final int zzabt() throws IOException {
        zzbv(0);
        return this.zzdqi.zzabt();
    }

    public final int zzabu() throws IOException {
        zzbv(0);
        return this.zzdqi.zzabu();
    }

    public final int zzabv() throws IOException {
        zzbv(5);
        return this.zzdqi.zzabv();
    }

    public final long zzabw() throws IOException {
        zzbv(1);
        return this.zzdqi.zzabw();
    }

    public final int zzabx() throws IOException {
        zzbv(0);
        return this.zzdqi.zzabx();
    }

    public final long zzaby() throws IOException {
        zzbv(0);
        return this.zzdqi.zzaby();
    }

    public final void zzac(List<Long> list) throws IOException {
        int zzabt;
        if (list instanceof zzbci) {
            zzbci com_google_android_gms_internal_ads_zzbci = (zzbci) list;
            switch (this.tag & 7) {
                case 1:
                    break;
                case 2:
                    zzabt = this.zzdqi.zzabt();
                    zzbw(zzabt);
                    zzabt += this.zzdqi.zzacb();
                    do {
                        com_google_android_gms_internal_ads_zzbci.zzw(this.zzdqi.zzabw());
                    } while (this.zzdqi.zzacb() < zzabt);
                    return;
                default:
                    throw zzbbu.zzadq();
            }
            do {
                com_google_android_gms_internal_ads_zzbci.zzw(this.zzdqi.zzabw());
                if (!this.zzdqi.zzaca()) {
                    zzabt = this.zzdqi.zzabk();
                } else {
                    return;
                }
            } while (zzabt == this.tag);
            this.zzdqk = zzabt;
            return;
        }
        switch (this.tag & 7) {
            case 1:
                break;
            case 2:
                zzabt = this.zzdqi.zzabt();
                zzbw(zzabt);
                zzabt += this.zzdqi.zzacb();
                do {
                    list.add(Long.valueOf(this.zzdqi.zzabw()));
                } while (this.zzdqi.zzacb() < zzabt);
                return;
            default:
                throw zzbbu.zzadq();
        }
        do {
            list.add(Long.valueOf(this.zzdqi.zzabw()));
            if (!this.zzdqi.zzaca()) {
                zzabt = this.zzdqi.zzabk();
            } else {
                return;
            }
        } while (zzabt == this.tag);
        this.zzdqk = zzabt;
    }

    public final int zzaci() throws IOException {
        if (this.zzdqk != 0) {
            this.tag = this.zzdqk;
            this.zzdqk = 0;
        } else {
            this.tag = this.zzdqi.zzabk();
        }
        return (this.tag == 0 || this.tag == this.zzdqj) ? Integer.MAX_VALUE : this.tag >>> 3;
    }

    public final boolean zzacj() throws IOException {
        return (this.zzdqi.zzaca() || this.tag == this.zzdqj) ? false : this.zzdqi.zzbq(this.tag);
    }

    public final void zzad(List<Integer> list) throws IOException {
        int zzabt;
        if (list instanceof zzbbp) {
            zzbbp com_google_android_gms_internal_ads_zzbbp = (zzbbp) list;
            switch (this.tag & 7) {
                case 0:
                    break;
                case 2:
                    zzabt = this.zzdqi.zzabt() + this.zzdqi.zzacb();
                    do {
                        com_google_android_gms_internal_ads_zzbbp.zzco(this.zzdqi.zzabx());
                    } while (this.zzdqi.zzacb() < zzabt);
                    zzby(zzabt);
                    return;
                default:
                    throw zzbbu.zzadq();
            }
            do {
                com_google_android_gms_internal_ads_zzbbp.zzco(this.zzdqi.zzabx());
                if (!this.zzdqi.zzaca()) {
                    zzabt = this.zzdqi.zzabk();
                } else {
                    return;
                }
            } while (zzabt == this.tag);
            this.zzdqk = zzabt;
            return;
        }
        switch (this.tag & 7) {
            case 0:
                break;
            case 2:
                zzabt = this.zzdqi.zzabt() + this.zzdqi.zzacb();
                do {
                    list.add(Integer.valueOf(this.zzdqi.zzabx()));
                } while (this.zzdqi.zzacb() < zzabt);
                zzby(zzabt);
                return;
            default:
                throw zzbbu.zzadq();
        }
        do {
            list.add(Integer.valueOf(this.zzdqi.zzabx()));
            if (!this.zzdqi.zzaca()) {
                zzabt = this.zzdqi.zzabk();
            } else {
                return;
            }
        } while (zzabt == this.tag);
        this.zzdqk = zzabt;
    }

    public final void zzae(List<Long> list) throws IOException {
        int zzabt;
        if (list instanceof zzbci) {
            zzbci com_google_android_gms_internal_ads_zzbci = (zzbci) list;
            switch (this.tag & 7) {
                case 0:
                    break;
                case 2:
                    zzabt = this.zzdqi.zzabt() + this.zzdqi.zzacb();
                    do {
                        com_google_android_gms_internal_ads_zzbci.zzw(this.zzdqi.zzaby());
                    } while (this.zzdqi.zzacb() < zzabt);
                    zzby(zzabt);
                    return;
                default:
                    throw zzbbu.zzadq();
            }
            do {
                com_google_android_gms_internal_ads_zzbci.zzw(this.zzdqi.zzaby());
                if (!this.zzdqi.zzaca()) {
                    zzabt = this.zzdqi.zzabk();
                } else {
                    return;
                }
            } while (zzabt == this.tag);
            this.zzdqk = zzabt;
            return;
        }
        switch (this.tag & 7) {
            case 0:
                break;
            case 2:
                zzabt = this.zzdqi.zzabt() + this.zzdqi.zzacb();
                do {
                    list.add(Long.valueOf(this.zzdqi.zzaby()));
                } while (this.zzdqi.zzacb() < zzabt);
                zzby(zzabt);
                return;
            default:
                throw zzbbu.zzadq();
        }
        do {
            list.add(Long.valueOf(this.zzdqi.zzaby()));
            if (!this.zzdqi.zzaca()) {
                zzabt = this.zzdqi.zzabk();
            } else {
                return;
            }
        } while (zzabt == this.tag);
        this.zzdqk = zzabt;
    }

    public final <T> T zzb(zzbdm<T> com_google_android_gms_internal_ads_zzbdm_T, zzbbb com_google_android_gms_internal_ads_zzbbb) throws IOException {
        zzbv(3);
        return zzd(com_google_android_gms_internal_ads_zzbdm_T, com_google_android_gms_internal_ads_zzbbb);
    }

    public final <T> void zzb(List<T> list, zzbdm<T> com_google_android_gms_internal_ads_zzbdm_T, zzbbb com_google_android_gms_internal_ads_zzbbb) throws IOException {
        if ((this.tag & 7) != 3) {
            throw zzbbu.zzadq();
        }
        int zzabk;
        int i = this.tag;
        do {
            list.add(zzd(com_google_android_gms_internal_ads_zzbdm_T, com_google_android_gms_internal_ads_zzbbb));
            if (!this.zzdqi.zzaca() && this.zzdqk == 0) {
                zzabk = this.zzdqi.zzabk();
            } else {
                return;
            }
        } while (zzabk == i);
        this.zzdqk = zzabk;
    }

    public final void zzp(List<Double> list) throws IOException {
        int zzabt;
        if (list instanceof zzbay) {
            zzbay com_google_android_gms_internal_ads_zzbay = (zzbay) list;
            switch (this.tag & 7) {
                case 1:
                    break;
                case 2:
                    zzabt = this.zzdqi.zzabt();
                    zzbw(zzabt);
                    zzabt += this.zzdqi.zzacb();
                    do {
                        com_google_android_gms_internal_ads_zzbay.zzd(this.zzdqi.readDouble());
                    } while (this.zzdqi.zzacb() < zzabt);
                    return;
                default:
                    throw zzbbu.zzadq();
            }
            do {
                com_google_android_gms_internal_ads_zzbay.zzd(this.zzdqi.readDouble());
                if (!this.zzdqi.zzaca()) {
                    zzabt = this.zzdqi.zzabk();
                } else {
                    return;
                }
            } while (zzabt == this.tag);
            this.zzdqk = zzabt;
            return;
        }
        switch (this.tag & 7) {
            case 1:
                break;
            case 2:
                zzabt = this.zzdqi.zzabt();
                zzbw(zzabt);
                zzabt += this.zzdqi.zzacb();
                do {
                    list.add(Double.valueOf(this.zzdqi.readDouble()));
                } while (this.zzdqi.zzacb() < zzabt);
                return;
            default:
                throw zzbbu.zzadq();
        }
        do {
            list.add(Double.valueOf(this.zzdqi.readDouble()));
            if (!this.zzdqi.zzaca()) {
                zzabt = this.zzdqi.zzabk();
            } else {
                return;
            }
        } while (zzabt == this.tag);
        this.zzdqk = zzabt;
    }

    public final void zzq(List<Float> list) throws IOException {
        int zzabt;
        if (list instanceof zzbbm) {
            zzbbm com_google_android_gms_internal_ads_zzbbm = (zzbbm) list;
            switch (this.tag & 7) {
                case 2:
                    zzabt = this.zzdqi.zzabt();
                    zzbx(zzabt);
                    zzabt += this.zzdqi.zzacb();
                    do {
                        com_google_android_gms_internal_ads_zzbbm.zzd(this.zzdqi.readFloat());
                    } while (this.zzdqi.zzacb() < zzabt);
                    return;
                case 5:
                    break;
                default:
                    throw zzbbu.zzadq();
            }
            do {
                com_google_android_gms_internal_ads_zzbbm.zzd(this.zzdqi.readFloat());
                if (!this.zzdqi.zzaca()) {
                    zzabt = this.zzdqi.zzabk();
                } else {
                    return;
                }
            } while (zzabt == this.tag);
            this.zzdqk = zzabt;
            return;
        }
        switch (this.tag & 7) {
            case 2:
                zzabt = this.zzdqi.zzabt();
                zzbx(zzabt);
                zzabt += this.zzdqi.zzacb();
                do {
                    list.add(Float.valueOf(this.zzdqi.readFloat()));
                } while (this.zzdqi.zzacb() < zzabt);
                return;
            case 5:
                break;
            default:
                throw zzbbu.zzadq();
        }
        do {
            list.add(Float.valueOf(this.zzdqi.readFloat()));
            if (!this.zzdqi.zzaca()) {
                zzabt = this.zzdqi.zzabk();
            } else {
                return;
            }
        } while (zzabt == this.tag);
        this.zzdqk = zzabt;
    }

    public final void zzr(List<Long> list) throws IOException {
        int zzabt;
        if (list instanceof zzbci) {
            zzbci com_google_android_gms_internal_ads_zzbci = (zzbci) list;
            switch (this.tag & 7) {
                case 0:
                    break;
                case 2:
                    zzabt = this.zzdqi.zzabt() + this.zzdqi.zzacb();
                    do {
                        com_google_android_gms_internal_ads_zzbci.zzw(this.zzdqi.zzabl());
                    } while (this.zzdqi.zzacb() < zzabt);
                    zzby(zzabt);
                    return;
                default:
                    throw zzbbu.zzadq();
            }
            do {
                com_google_android_gms_internal_ads_zzbci.zzw(this.zzdqi.zzabl());
                if (!this.zzdqi.zzaca()) {
                    zzabt = this.zzdqi.zzabk();
                } else {
                    return;
                }
            } while (zzabt == this.tag);
            this.zzdqk = zzabt;
            return;
        }
        switch (this.tag & 7) {
            case 0:
                break;
            case 2:
                zzabt = this.zzdqi.zzabt() + this.zzdqi.zzacb();
                do {
                    list.add(Long.valueOf(this.zzdqi.zzabl()));
                } while (this.zzdqi.zzacb() < zzabt);
                zzby(zzabt);
                return;
            default:
                throw zzbbu.zzadq();
        }
        do {
            list.add(Long.valueOf(this.zzdqi.zzabl()));
            if (!this.zzdqi.zzaca()) {
                zzabt = this.zzdqi.zzabk();
            } else {
                return;
            }
        } while (zzabt == this.tag);
        this.zzdqk = zzabt;
    }

    public final void zzs(List<Long> list) throws IOException {
        int zzabt;
        if (list instanceof zzbci) {
            zzbci com_google_android_gms_internal_ads_zzbci = (zzbci) list;
            switch (this.tag & 7) {
                case 0:
                    break;
                case 2:
                    zzabt = this.zzdqi.zzabt() + this.zzdqi.zzacb();
                    do {
                        com_google_android_gms_internal_ads_zzbci.zzw(this.zzdqi.zzabm());
                    } while (this.zzdqi.zzacb() < zzabt);
                    zzby(zzabt);
                    return;
                default:
                    throw zzbbu.zzadq();
            }
            do {
                com_google_android_gms_internal_ads_zzbci.zzw(this.zzdqi.zzabm());
                if (!this.zzdqi.zzaca()) {
                    zzabt = this.zzdqi.zzabk();
                } else {
                    return;
                }
            } while (zzabt == this.tag);
            this.zzdqk = zzabt;
            return;
        }
        switch (this.tag & 7) {
            case 0:
                break;
            case 2:
                zzabt = this.zzdqi.zzabt() + this.zzdqi.zzacb();
                do {
                    list.add(Long.valueOf(this.zzdqi.zzabm()));
                } while (this.zzdqi.zzacb() < zzabt);
                zzby(zzabt);
                return;
            default:
                throw zzbbu.zzadq();
        }
        do {
            list.add(Long.valueOf(this.zzdqi.zzabm()));
            if (!this.zzdqi.zzaca()) {
                zzabt = this.zzdqi.zzabk();
            } else {
                return;
            }
        } while (zzabt == this.tag);
        this.zzdqk = zzabt;
    }

    public final void zzt(List<Integer> list) throws IOException {
        int zzabt;
        if (list instanceof zzbbp) {
            zzbbp com_google_android_gms_internal_ads_zzbbp = (zzbbp) list;
            switch (this.tag & 7) {
                case 0:
                    break;
                case 2:
                    zzabt = this.zzdqi.zzabt() + this.zzdqi.zzacb();
                    do {
                        com_google_android_gms_internal_ads_zzbbp.zzco(this.zzdqi.zzabn());
                    } while (this.zzdqi.zzacb() < zzabt);
                    zzby(zzabt);
                    return;
                default:
                    throw zzbbu.zzadq();
            }
            do {
                com_google_android_gms_internal_ads_zzbbp.zzco(this.zzdqi.zzabn());
                if (!this.zzdqi.zzaca()) {
                    zzabt = this.zzdqi.zzabk();
                } else {
                    return;
                }
            } while (zzabt == this.tag);
            this.zzdqk = zzabt;
            return;
        }
        switch (this.tag & 7) {
            case 0:
                break;
            case 2:
                zzabt = this.zzdqi.zzabt() + this.zzdqi.zzacb();
                do {
                    list.add(Integer.valueOf(this.zzdqi.zzabn()));
                } while (this.zzdqi.zzacb() < zzabt);
                zzby(zzabt);
                return;
            default:
                throw zzbbu.zzadq();
        }
        do {
            list.add(Integer.valueOf(this.zzdqi.zzabn()));
            if (!this.zzdqi.zzaca()) {
                zzabt = this.zzdqi.zzabk();
            } else {
                return;
            }
        } while (zzabt == this.tag);
        this.zzdqk = zzabt;
    }

    public final void zzu(List<Long> list) throws IOException {
        int zzabt;
        if (list instanceof zzbci) {
            zzbci com_google_android_gms_internal_ads_zzbci = (zzbci) list;
            switch (this.tag & 7) {
                case 1:
                    break;
                case 2:
                    zzabt = this.zzdqi.zzabt();
                    zzbw(zzabt);
                    zzabt += this.zzdqi.zzacb();
                    do {
                        com_google_android_gms_internal_ads_zzbci.zzw(this.zzdqi.zzabo());
                    } while (this.zzdqi.zzacb() < zzabt);
                    return;
                default:
                    throw zzbbu.zzadq();
            }
            do {
                com_google_android_gms_internal_ads_zzbci.zzw(this.zzdqi.zzabo());
                if (!this.zzdqi.zzaca()) {
                    zzabt = this.zzdqi.zzabk();
                } else {
                    return;
                }
            } while (zzabt == this.tag);
            this.zzdqk = zzabt;
            return;
        }
        switch (this.tag & 7) {
            case 1:
                break;
            case 2:
                zzabt = this.zzdqi.zzabt();
                zzbw(zzabt);
                zzabt += this.zzdqi.zzacb();
                do {
                    list.add(Long.valueOf(this.zzdqi.zzabo()));
                } while (this.zzdqi.zzacb() < zzabt);
                return;
            default:
                throw zzbbu.zzadq();
        }
        do {
            list.add(Long.valueOf(this.zzdqi.zzabo()));
            if (!this.zzdqi.zzaca()) {
                zzabt = this.zzdqi.zzabk();
            } else {
                return;
            }
        } while (zzabt == this.tag);
        this.zzdqk = zzabt;
    }

    public final void zzv(List<Integer> list) throws IOException {
        int zzabt;
        if (list instanceof zzbbp) {
            zzbbp com_google_android_gms_internal_ads_zzbbp = (zzbbp) list;
            switch (this.tag & 7) {
                case 2:
                    zzabt = this.zzdqi.zzabt();
                    zzbx(zzabt);
                    zzabt += this.zzdqi.zzacb();
                    do {
                        com_google_android_gms_internal_ads_zzbbp.zzco(this.zzdqi.zzabp());
                    } while (this.zzdqi.zzacb() < zzabt);
                    return;
                case 5:
                    break;
                default:
                    throw zzbbu.zzadq();
            }
            do {
                com_google_android_gms_internal_ads_zzbbp.zzco(this.zzdqi.zzabp());
                if (!this.zzdqi.zzaca()) {
                    zzabt = this.zzdqi.zzabk();
                } else {
                    return;
                }
            } while (zzabt == this.tag);
            this.zzdqk = zzabt;
            return;
        }
        switch (this.tag & 7) {
            case 2:
                zzabt = this.zzdqi.zzabt();
                zzbx(zzabt);
                zzabt += this.zzdqi.zzacb();
                do {
                    list.add(Integer.valueOf(this.zzdqi.zzabp()));
                } while (this.zzdqi.zzacb() < zzabt);
                return;
            case 5:
                break;
            default:
                throw zzbbu.zzadq();
        }
        do {
            list.add(Integer.valueOf(this.zzdqi.zzabp()));
            if (!this.zzdqi.zzaca()) {
                zzabt = this.zzdqi.zzabk();
            } else {
                return;
            }
        } while (zzabt == this.tag);
        this.zzdqk = zzabt;
    }

    public final void zzw(List<Boolean> list) throws IOException {
        int zzabt;
        if (list instanceof zzbaf) {
            zzbaf com_google_android_gms_internal_ads_zzbaf = (zzbaf) list;
            switch (this.tag & 7) {
                case 0:
                    break;
                case 2:
                    zzabt = this.zzdqi.zzabt() + this.zzdqi.zzacb();
                    do {
                        com_google_android_gms_internal_ads_zzbaf.addBoolean(this.zzdqi.zzabq());
                    } while (this.zzdqi.zzacb() < zzabt);
                    zzby(zzabt);
                    return;
                default:
                    throw zzbbu.zzadq();
            }
            do {
                com_google_android_gms_internal_ads_zzbaf.addBoolean(this.zzdqi.zzabq());
                if (!this.zzdqi.zzaca()) {
                    zzabt = this.zzdqi.zzabk();
                } else {
                    return;
                }
            } while (zzabt == this.tag);
            this.zzdqk = zzabt;
            return;
        }
        switch (this.tag & 7) {
            case 0:
                break;
            case 2:
                zzabt = this.zzdqi.zzabt() + this.zzdqi.zzacb();
                do {
                    list.add(Boolean.valueOf(this.zzdqi.zzabq()));
                } while (this.zzdqi.zzacb() < zzabt);
                zzby(zzabt);
                return;
            default:
                throw zzbbu.zzadq();
        }
        do {
            list.add(Boolean.valueOf(this.zzdqi.zzabq()));
            if (!this.zzdqi.zzaca()) {
                zzabt = this.zzdqi.zzabk();
            } else {
                return;
            }
        } while (zzabt == this.tag);
        this.zzdqk = zzabt;
    }

    public final void zzx(List<String> list) throws IOException {
        zza((List) list, true);
    }

    public final void zzy(List<zzbah> list) throws IOException {
        if ((this.tag & 7) != 2) {
            throw zzbbu.zzadq();
        }
        int zzabk;
        do {
            list.add(zzabs());
            if (!this.zzdqi.zzaca()) {
                zzabk = this.zzdqi.zzabk();
            } else {
                return;
            }
        } while (zzabk == this.tag);
        this.zzdqk = zzabk;
    }

    public final void zzz(List<Integer> list) throws IOException {
        int zzabt;
        if (list instanceof zzbbp) {
            zzbbp com_google_android_gms_internal_ads_zzbbp = (zzbbp) list;
            switch (this.tag & 7) {
                case 0:
                    break;
                case 2:
                    zzabt = this.zzdqi.zzabt() + this.zzdqi.zzacb();
                    do {
                        com_google_android_gms_internal_ads_zzbbp.zzco(this.zzdqi.zzabt());
                    } while (this.zzdqi.zzacb() < zzabt);
                    zzby(zzabt);
                    return;
                default:
                    throw zzbbu.zzadq();
            }
            do {
                com_google_android_gms_internal_ads_zzbbp.zzco(this.zzdqi.zzabt());
                if (!this.zzdqi.zzaca()) {
                    zzabt = this.zzdqi.zzabk();
                } else {
                    return;
                }
            } while (zzabt == this.tag);
            this.zzdqk = zzabt;
            return;
        }
        switch (this.tag & 7) {
            case 0:
                break;
            case 2:
                zzabt = this.zzdqi.zzabt() + this.zzdqi.zzacb();
                do {
                    list.add(Integer.valueOf(this.zzdqi.zzabt()));
                } while (this.zzdqi.zzacb() < zzabt);
                zzby(zzabt);
                return;
            default:
                throw zzbbu.zzadq();
        }
        do {
            list.add(Integer.valueOf(this.zzdqi.zzabt()));
            if (!this.zzdqi.zzaca()) {
                zzabt = this.zzdqi.zzabk();
            } else {
                return;
            }
        } while (zzabt == this.tag);
        this.zzdqk = zzabt;
    }
}
