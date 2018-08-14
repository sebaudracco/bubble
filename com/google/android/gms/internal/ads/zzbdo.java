package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;

final class zzbdo {
    private static final Class<?> zzdyf = zzafq();
    private static final zzbee<?, ?> zzdyg = zzas(false);
    private static final zzbee<?, ?> zzdyh = zzas(true);
    private static final zzbee<?, ?> zzdyi = new zzbeg();

    static <UT, UB> UB zza(int i, int i2, UB ub, zzbee<UT, UB> com_google_android_gms_internal_ads_zzbee_UT__UB) {
        Object zzagb;
        if (ub == null) {
            zzagb = com_google_android_gms_internal_ads_zzbee_UT__UB.zzagb();
        }
        com_google_android_gms_internal_ads_zzbee_UT__UB.zza(zzagb, i, (long) i2);
        return zzagb;
    }

    static <UT, UB> UB zza(int i, List<Integer> list, zzbbs<?> com_google_android_gms_internal_ads_zzbbs_, UB ub, zzbee<UT, UB> com_google_android_gms_internal_ads_zzbee_UT__UB) {
        if (com_google_android_gms_internal_ads_zzbbs_ == null) {
            return ub;
        }
        UB ub2;
        int intValue;
        if (list instanceof RandomAccess) {
            int size = list.size();
            int i2 = 0;
            int i3 = 0;
            ub2 = ub;
            while (i2 < size) {
                UB ub3;
                intValue = ((Integer) list.get(i2)).intValue();
                if (com_google_android_gms_internal_ads_zzbbs_.zzq(intValue) != null) {
                    if (i2 != i3) {
                        list.set(i3, Integer.valueOf(intValue));
                    }
                    intValue = i3 + 1;
                    ub3 = ub2;
                } else {
                    int i4 = i3;
                    ub3 = zza(i, intValue, (Object) ub2, (zzbee) com_google_android_gms_internal_ads_zzbee_UT__UB);
                    intValue = i4;
                }
                i2++;
                ub2 = ub3;
                i3 = intValue;
            }
            if (i3 != size) {
                list.subList(i3, size).clear();
            }
        } else {
            Object zza;
            Iterator it = list.iterator();
            while (it.hasNext()) {
                intValue = ((Integer) it.next()).intValue();
                if (com_google_android_gms_internal_ads_zzbbs_.zzq(intValue) == null) {
                    zza = zza(i, intValue, zza, (zzbee) com_google_android_gms_internal_ads_zzbee_UT__UB);
                    it.remove();
                }
            }
            ub2 = zza;
        }
        return ub2;
    }

    public static void zza(int i, List<String> list, zzbey com_google_android_gms_internal_ads_zzbey) throws IOException {
        if (list != null && !list.isEmpty()) {
            com_google_android_gms_internal_ads_zzbey.zza(i, list);
        }
    }

    public static void zza(int i, List<?> list, zzbey com_google_android_gms_internal_ads_zzbey, zzbdm com_google_android_gms_internal_ads_zzbdm) throws IOException {
        if (list != null && !list.isEmpty()) {
            com_google_android_gms_internal_ads_zzbey.zza(i, list, com_google_android_gms_internal_ads_zzbdm);
        }
    }

    public static void zza(int i, List<Double> list, zzbey com_google_android_gms_internal_ads_zzbey, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            com_google_android_gms_internal_ads_zzbey.zzg(i, list, z);
        }
    }

    static <T, FT extends zzbbi<FT>> void zza(zzbbd<FT> com_google_android_gms_internal_ads_zzbbd_FT, T t, T t2) {
        zzbbg zzm = com_google_android_gms_internal_ads_zzbbd_FT.zzm(t2);
        if (!zzm.isEmpty()) {
            com_google_android_gms_internal_ads_zzbbd_FT.zzn(t).zza(zzm);
        }
    }

    static <T> void zza(zzbcp com_google_android_gms_internal_ads_zzbcp, T t, T t2, long j) {
        zzbek.zza((Object) t, j, com_google_android_gms_internal_ads_zzbcp.zzb(zzbek.zzp(t, j), zzbek.zzp(t2, j)));
    }

    static <T, UT, UB> void zza(zzbee<UT, UB> com_google_android_gms_internal_ads_zzbee_UT__UB, T t, T t2) {
        com_google_android_gms_internal_ads_zzbee_UT__UB.zze(t, com_google_android_gms_internal_ads_zzbee_UT__UB.zzg(com_google_android_gms_internal_ads_zzbee_UT__UB.zzac(t), com_google_android_gms_internal_ads_zzbee_UT__UB.zzac(t2)));
    }

    static int zzaf(List<Long> list) {
        int i = 0;
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int i2;
        if (list instanceof zzbci) {
            zzbci com_google_android_gms_internal_ads_zzbci = (zzbci) list;
            i2 = 0;
            while (i2 < size) {
                int zzp = zzbav.zzp(com_google_android_gms_internal_ads_zzbci.getLong(i2)) + i;
                i2++;
                i = zzp;
            }
            return i;
        }
        i2 = 0;
        for (zzp = 0; zzp < size; zzp++) {
            i2 += zzbav.zzp(((Long) list.get(zzp)).longValue());
        }
        return i2;
    }

    public static zzbee<?, ?> zzafn() {
        return zzdyg;
    }

    public static zzbee<?, ?> zzafo() {
        return zzdyh;
    }

    public static zzbee<?, ?> zzafp() {
        return zzdyi;
    }

    private static Class<?> zzafq() {
        try {
            return Class.forName("com.google.protobuf.GeneratedMessage");
        } catch (Throwable th) {
            return null;
        }
    }

    private static Class<?> zzafr() {
        try {
            return Class.forName("com.google.protobuf.UnknownFieldSetSchema");
        } catch (Throwable th) {
            return null;
        }
    }

    static int zzag(List<Long> list) {
        int i = 0;
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int i2;
        if (list instanceof zzbci) {
            zzbci com_google_android_gms_internal_ads_zzbci = (zzbci) list;
            i2 = 0;
            while (i2 < size) {
                int zzq = zzbav.zzq(com_google_android_gms_internal_ads_zzbci.getLong(i2)) + i;
                i2++;
                i = zzq;
            }
            return i;
        }
        i2 = 0;
        for (zzq = 0; zzq < size; zzq++) {
            i2 += zzbav.zzq(((Long) list.get(zzq)).longValue());
        }
        return i2;
    }

    static int zzah(List<Long> list) {
        int i = 0;
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int i2;
        if (list instanceof zzbci) {
            zzbci com_google_android_gms_internal_ads_zzbci = (zzbci) list;
            i2 = 0;
            while (i2 < size) {
                int zzr = zzbav.zzr(com_google_android_gms_internal_ads_zzbci.getLong(i2)) + i;
                i2++;
                i = zzr;
            }
            return i;
        }
        i2 = 0;
        for (zzr = 0; zzr < size; zzr++) {
            i2 += zzbav.zzr(((Long) list.get(zzr)).longValue());
        }
        return i2;
    }

    static int zzai(List<Integer> list) {
        int i = 0;
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int i2;
        if (list instanceof zzbbp) {
            zzbbp com_google_android_gms_internal_ads_zzbbp = (zzbbp) list;
            i2 = 0;
            while (i2 < size) {
                int zzcj = zzbav.zzcj(com_google_android_gms_internal_ads_zzbbp.getInt(i2)) + i;
                i2++;
                i = zzcj;
            }
            return i;
        }
        i2 = 0;
        for (zzcj = 0; zzcj < size; zzcj++) {
            i2 += zzbav.zzcj(((Integer) list.get(zzcj)).intValue());
        }
        return i2;
    }

    static int zzaj(List<Integer> list) {
        int i = 0;
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int i2;
        if (list instanceof zzbbp) {
            zzbbp com_google_android_gms_internal_ads_zzbbp = (zzbbp) list;
            i2 = 0;
            while (i2 < size) {
                int zzce = zzbav.zzce(com_google_android_gms_internal_ads_zzbbp.getInt(i2)) + i;
                i2++;
                i = zzce;
            }
            return i;
        }
        i2 = 0;
        for (zzce = 0; zzce < size; zzce++) {
            i2 += zzbav.zzce(((Integer) list.get(zzce)).intValue());
        }
        return i2;
    }

    static int zzak(List<Integer> list) {
        int i = 0;
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int i2;
        if (list instanceof zzbbp) {
            zzbbp com_google_android_gms_internal_ads_zzbbp = (zzbbp) list;
            i2 = 0;
            while (i2 < size) {
                int zzcf = zzbav.zzcf(com_google_android_gms_internal_ads_zzbbp.getInt(i2)) + i;
                i2++;
                i = zzcf;
            }
            return i;
        }
        i2 = 0;
        for (zzcf = 0; zzcf < size; zzcf++) {
            i2 += zzbav.zzcf(((Integer) list.get(zzcf)).intValue());
        }
        return i2;
    }

    static int zzal(List<Integer> list) {
        int i = 0;
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int i2;
        if (list instanceof zzbbp) {
            zzbbp com_google_android_gms_internal_ads_zzbbp = (zzbbp) list;
            i2 = 0;
            while (i2 < size) {
                int zzcg = zzbav.zzcg(com_google_android_gms_internal_ads_zzbbp.getInt(i2)) + i;
                i2++;
                i = zzcg;
            }
            return i;
        }
        i2 = 0;
        for (zzcg = 0; zzcg < size; zzcg++) {
            i2 += zzbav.zzcg(((Integer) list.get(zzcg)).intValue());
        }
        return i2;
    }

    static int zzam(List<?> list) {
        return list.size() << 2;
    }

    static int zzan(List<?> list) {
        return list.size() << 3;
    }

    static int zzao(List<?> list) {
        return list.size();
    }

    private static zzbee<?, ?> zzas(boolean z) {
        try {
            Class zzafr = zzafr();
            if (zzafr == null) {
                return null;
            }
            return (zzbee) zzafr.getConstructor(new Class[]{Boolean.TYPE}).newInstance(new Object[]{Boolean.valueOf(z)});
        } catch (Throwable th) {
            return null;
        }
    }

    public static void zzb(int i, List<zzbah> list, zzbey com_google_android_gms_internal_ads_zzbey) throws IOException {
        if (list != null && !list.isEmpty()) {
            com_google_android_gms_internal_ads_zzbey.zzb(i, list);
        }
    }

    public static void zzb(int i, List<?> list, zzbey com_google_android_gms_internal_ads_zzbey, zzbdm com_google_android_gms_internal_ads_zzbdm) throws IOException {
        if (list != null && !list.isEmpty()) {
            com_google_android_gms_internal_ads_zzbey.zzb(i, list, com_google_android_gms_internal_ads_zzbdm);
        }
    }

    public static void zzb(int i, List<Float> list, zzbey com_google_android_gms_internal_ads_zzbey, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            com_google_android_gms_internal_ads_zzbey.zzf(i, list, z);
        }
    }

    static int zzc(int i, Object obj, zzbdm com_google_android_gms_internal_ads_zzbdm) {
        return obj instanceof zzbcb ? zzbav.zza(i, (zzbcb) obj) : zzbav.zzb(i, (zzbcu) obj, com_google_android_gms_internal_ads_zzbdm);
    }

    static int zzc(int i, List<?> list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int zzcd = zzbav.zzcd(i) * size;
        int i2;
        if (list instanceof zzbcd) {
            zzbcd com_google_android_gms_internal_ads_zzbcd = (zzbcd) list;
            i2 = 0;
            while (i2 < size) {
                Object zzcp = com_google_android_gms_internal_ads_zzbcd.zzcp(i2);
                i2++;
                zzcd = zzcp instanceof zzbah ? zzbav.zzao((zzbah) zzcp) + zzcd : zzbav.zzeo((String) zzcp) + zzcd;
            }
            return zzcd;
        }
        i2 = 0;
        while (i2 < size) {
            zzcp = list.get(i2);
            i2++;
            zzcd = zzcp instanceof zzbah ? zzbav.zzao((zzbah) zzcp) + zzcd : zzbav.zzeo((String) zzcp) + zzcd;
        }
        return zzcd;
    }

    static int zzc(int i, List<?> list, zzbdm com_google_android_gms_internal_ads_zzbdm) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int zzcd = zzbav.zzcd(i) * size;
        int i2 = 0;
        while (i2 < size) {
            Object obj = list.get(i2);
            i2++;
            zzcd = obj instanceof zzbcb ? zzbav.zza((zzbcb) obj) + zzcd : zzbav.zza((zzbcu) obj, com_google_android_gms_internal_ads_zzbdm) + zzcd;
        }
        return zzcd;
    }

    public static void zzc(int i, List<Long> list, zzbey com_google_android_gms_internal_ads_zzbey, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            com_google_android_gms_internal_ads_zzbey.zzc(i, list, z);
        }
    }

    static int zzd(int i, List<zzbah> list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int zzcd = size * zzbav.zzcd(i);
        for (size = 0; size < list.size(); size++) {
            zzcd += zzbav.zzao((zzbah) list.get(size));
        }
        return zzcd;
    }

    static int zzd(int i, List<zzbcu> list, zzbdm com_google_android_gms_internal_ads_zzbdm) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < size; i3++) {
            i2 += zzbav.zzc(i, (zzbcu) list.get(i3), com_google_android_gms_internal_ads_zzbdm);
        }
        return i2;
    }

    public static void zzd(int i, List<Long> list, zzbey com_google_android_gms_internal_ads_zzbey, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            com_google_android_gms_internal_ads_zzbey.zzd(i, list, z);
        }
    }

    static boolean zzd(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    public static void zze(int i, List<Long> list, zzbey com_google_android_gms_internal_ads_zzbey, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            com_google_android_gms_internal_ads_zzbey.zzn(i, list, z);
        }
    }

    public static boolean zze(int i, int i2, int i3) {
        return i2 < 40 || ((((long) i2) - ((long) i)) + 1) + 9 <= ((2 * ((long) i3)) + 3) + ((((long) i3) + 3) * 3);
    }

    public static void zzf(int i, List<Long> list, zzbey com_google_android_gms_internal_ads_zzbey, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            com_google_android_gms_internal_ads_zzbey.zze(i, list, z);
        }
    }

    public static void zzf(Class<?> cls) {
        if (!zzbbo.class.isAssignableFrom(cls) && zzdyf != null && !zzdyf.isAssignableFrom(cls)) {
            throw new IllegalArgumentException("Message classes must extend GeneratedMessage or GeneratedMessageLite");
        }
    }

    public static void zzg(int i, List<Long> list, zzbey com_google_android_gms_internal_ads_zzbey, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            com_google_android_gms_internal_ads_zzbey.zzl(i, list, z);
        }
    }

    public static void zzh(int i, List<Integer> list, zzbey com_google_android_gms_internal_ads_zzbey, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            com_google_android_gms_internal_ads_zzbey.zza(i, list, z);
        }
    }

    public static void zzi(int i, List<Integer> list, zzbey com_google_android_gms_internal_ads_zzbey, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            com_google_android_gms_internal_ads_zzbey.zzj(i, list, z);
        }
    }

    public static void zzj(int i, List<Integer> list, zzbey com_google_android_gms_internal_ads_zzbey, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            com_google_android_gms_internal_ads_zzbey.zzm(i, list, z);
        }
    }

    public static void zzk(int i, List<Integer> list, zzbey com_google_android_gms_internal_ads_zzbey, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            com_google_android_gms_internal_ads_zzbey.zzb(i, list, z);
        }
    }

    public static void zzl(int i, List<Integer> list, zzbey com_google_android_gms_internal_ads_zzbey, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            com_google_android_gms_internal_ads_zzbey.zzk(i, list, z);
        }
    }

    public static void zzm(int i, List<Integer> list, zzbey com_google_android_gms_internal_ads_zzbey, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            com_google_android_gms_internal_ads_zzbey.zzh(i, list, z);
        }
    }

    public static void zzn(int i, List<Boolean> list, zzbey com_google_android_gms_internal_ads_zzbey, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            com_google_android_gms_internal_ads_zzbey.zzi(i, list, z);
        }
    }

    static int zzo(int i, List<Long> list, boolean z) {
        return list.size() == 0 ? 0 : zzaf(list) + (list.size() * zzbav.zzcd(i));
    }

    static int zzp(int i, List<Long> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return (size * zzbav.zzcd(i)) + zzag(list);
    }

    static int zzq(int i, List<Long> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return (size * zzbav.zzcd(i)) + zzah(list);
    }

    static int zzr(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return (size * zzbav.zzcd(i)) + zzai(list);
    }

    static int zzs(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return (size * zzbav.zzcd(i)) + zzaj(list);
    }

    static int zzt(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return (size * zzbav.zzcd(i)) + zzak(list);
    }

    static int zzu(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return (size * zzbav.zzcd(i)) + zzal(list);
    }

    static int zzv(int i, List<?> list, boolean z) {
        int size = list.size();
        return size == 0 ? 0 : zzbav.zzt(i, 0) * size;
    }

    static int zzw(int i, List<?> list, boolean z) {
        int size = list.size();
        return size == 0 ? 0 : size * zzbav.zzg(i, 0);
    }

    static int zzx(int i, List<?> list, boolean z) {
        int size = list.size();
        return size == 0 ? 0 : size * zzbav.zzg(i, true);
    }
}
