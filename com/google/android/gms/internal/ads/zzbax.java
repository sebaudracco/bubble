package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzbbo.zze;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

final class zzbax implements zzbey {
    private final zzbav zzdpv;

    private zzbax(zzbav com_google_android_gms_internal_ads_zzbav) {
        this.zzdpv = (zzbav) zzbbq.zza((Object) com_google_android_gms_internal_ads_zzbav, "output");
        this.zzdpv.zzdqn = this;
    }

    public static zzbax zza(zzbav com_google_android_gms_internal_ads_zzbav) {
        return com_google_android_gms_internal_ads_zzbav.zzdqn != null ? com_google_android_gms_internal_ads_zzbav.zzdqn : new zzbax(com_google_android_gms_internal_ads_zzbav);
    }

    public final void zza(int i, double d) throws IOException {
        this.zzdpv.zza(i, d);
    }

    public final void zza(int i, float f) throws IOException {
        this.zzdpv.zza(i, f);
    }

    public final void zza(int i, long j) throws IOException {
        this.zzdpv.zza(i, j);
    }

    public final void zza(int i, zzbah com_google_android_gms_internal_ads_zzbah) throws IOException {
        this.zzdpv.zza(i, com_google_android_gms_internal_ads_zzbah);
    }

    public final <K, V> void zza(int i, zzbcn<K, V> com_google_android_gms_internal_ads_zzbcn_K__V, Map<K, V> map) throws IOException {
        for (Entry entry : map.entrySet()) {
            this.zzdpv.zzl(i, 2);
            this.zzdpv.zzca(zzbcm.zza(com_google_android_gms_internal_ads_zzbcn_K__V, entry.getKey(), entry.getValue()));
            zzbcm.zza(this.zzdpv, com_google_android_gms_internal_ads_zzbcn_K__V, entry.getKey(), entry.getValue());
        }
    }

    public final void zza(int i, Object obj) throws IOException {
        if (obj instanceof zzbah) {
            this.zzdpv.zzb(i, (zzbah) obj);
        } else {
            this.zzdpv.zza(i, (zzbcu) obj);
        }
    }

    public final void zza(int i, Object obj, zzbdm com_google_android_gms_internal_ads_zzbdm) throws IOException {
        this.zzdpv.zza(i, (zzbcu) obj, com_google_android_gms_internal_ads_zzbdm);
    }

    public final void zza(int i, List<String> list) throws IOException {
        int i2 = 0;
        if (list instanceof zzbcd) {
            zzbcd com_google_android_gms_internal_ads_zzbcd = (zzbcd) list;
            for (int i3 = 0; i3 < list.size(); i3++) {
                Object zzcp = com_google_android_gms_internal_ads_zzbcd.zzcp(i3);
                if (zzcp instanceof String) {
                    this.zzdpv.zzf(i, (String) zzcp);
                } else {
                    this.zzdpv.zza(i, (zzbah) zzcp);
                }
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzdpv.zzf(i, (String) list.get(i2));
            i2++;
        }
    }

    public final void zza(int i, List<?> list, zzbdm com_google_android_gms_internal_ads_zzbdm) throws IOException {
        for (int i2 = 0; i2 < list.size(); i2++) {
            zza(i, list.get(i2), com_google_android_gms_internal_ads_zzbdm);
        }
    }

    public final void zza(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzdpv.zzl(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzbav.zzce(((Integer) list.get(i4)).intValue());
            }
            this.zzdpv.zzca(i3);
            while (i2 < list.size()) {
                this.zzdpv.zzbz(((Integer) list.get(i2)).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzdpv.zzm(i, ((Integer) list.get(i2)).intValue());
            i2++;
        }
    }

    public final int zzacn() {
        return zze.zzdul;
    }

    public final void zzb(int i, long j) throws IOException {
        this.zzdpv.zzb(i, j);
    }

    public final void zzb(int i, Object obj, zzbdm com_google_android_gms_internal_ads_zzbdm) throws IOException {
        zzbav com_google_android_gms_internal_ads_zzbav = this.zzdpv;
        zzbcu com_google_android_gms_internal_ads_zzbcu = (zzbcu) obj;
        com_google_android_gms_internal_ads_zzbav.zzl(i, 3);
        com_google_android_gms_internal_ads_zzbdm.zza(com_google_android_gms_internal_ads_zzbcu, com_google_android_gms_internal_ads_zzbav.zzdqn);
        com_google_android_gms_internal_ads_zzbav.zzl(i, 4);
    }

    public final void zzb(int i, List<zzbah> list) throws IOException {
        for (int i2 = 0; i2 < list.size(); i2++) {
            this.zzdpv.zza(i, (zzbah) list.get(i2));
        }
    }

    public final void zzb(int i, List<?> list, zzbdm com_google_android_gms_internal_ads_zzbdm) throws IOException {
        for (int i2 = 0; i2 < list.size(); i2++) {
            zzb(i, list.get(i2), com_google_android_gms_internal_ads_zzbdm);
        }
    }

    public final void zzb(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzdpv.zzl(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzbav.zzch(((Integer) list.get(i4)).intValue());
            }
            this.zzdpv.zzca(i3);
            while (i2 < list.size()) {
                this.zzdpv.zzcc(((Integer) list.get(i2)).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzdpv.zzp(i, ((Integer) list.get(i2)).intValue());
            i2++;
        }
    }

    public final void zzc(int i, long j) throws IOException {
        this.zzdpv.zzc(i, j);
    }

    public final void zzc(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzdpv.zzl(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzbav.zzp(((Long) list.get(i4)).longValue());
            }
            this.zzdpv.zzca(i3);
            while (i2 < list.size()) {
                this.zzdpv.zzm(((Long) list.get(i2)).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzdpv.zza(i, ((Long) list.get(i2)).longValue());
            i2++;
        }
    }

    public final void zzcm(int i) throws IOException {
        this.zzdpv.zzl(i, 3);
    }

    public final void zzcn(int i) throws IOException {
        this.zzdpv.zzl(i, 4);
    }

    public final void zzd(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzdpv.zzl(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzbav.zzq(((Long) list.get(i4)).longValue());
            }
            this.zzdpv.zzca(i3);
            while (i2 < list.size()) {
                this.zzdpv.zzm(((Long) list.get(i2)).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzdpv.zza(i, ((Long) list.get(i2)).longValue());
            i2++;
        }
    }

    public final void zze(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzdpv.zzl(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzbav.zzs(((Long) list.get(i4)).longValue());
            }
            this.zzdpv.zzca(i3);
            while (i2 < list.size()) {
                this.zzdpv.zzo(((Long) list.get(i2)).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzdpv.zzc(i, ((Long) list.get(i2)).longValue());
            i2++;
        }
    }

    public final void zzf(int i, String str) throws IOException {
        this.zzdpv.zzf(i, str);
    }

    public final void zzf(int i, List<Float> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzdpv.zzl(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzbav.zzc(((Float) list.get(i4)).floatValue());
            }
            this.zzdpv.zzca(i3);
            while (i2 < list.size()) {
                this.zzdpv.zzb(((Float) list.get(i2)).floatValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzdpv.zza(i, ((Float) list.get(i2)).floatValue());
            i2++;
        }
    }

    public final void zzf(int i, boolean z) throws IOException {
        this.zzdpv.zzf(i, z);
    }

    public final void zzg(int i, List<Double> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzdpv.zzl(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzbav.zzc(((Double) list.get(i4)).doubleValue());
            }
            this.zzdpv.zzca(i3);
            while (i2 < list.size()) {
                this.zzdpv.zzb(((Double) list.get(i2)).doubleValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzdpv.zza(i, ((Double) list.get(i2)).doubleValue());
            i2++;
        }
    }

    public final void zzh(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzdpv.zzl(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzbav.zzcj(((Integer) list.get(i4)).intValue());
            }
            this.zzdpv.zzca(i3);
            while (i2 < list.size()) {
                this.zzdpv.zzbz(((Integer) list.get(i2)).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzdpv.zzm(i, ((Integer) list.get(i2)).intValue());
            i2++;
        }
    }

    public final void zzi(int i, long j) throws IOException {
        this.zzdpv.zza(i, j);
    }

    public final void zzi(int i, List<Boolean> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzdpv.zzl(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzbav.zzaq(((Boolean) list.get(i4)).booleanValue());
            }
            this.zzdpv.zzca(i3);
            while (i2 < list.size()) {
                this.zzdpv.zzap(((Boolean) list.get(i2)).booleanValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzdpv.zzf(i, ((Boolean) list.get(i2)).booleanValue());
            i2++;
        }
    }

    public final void zzj(int i, long j) throws IOException {
        this.zzdpv.zzc(i, j);
    }

    public final void zzj(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzdpv.zzl(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzbav.zzcf(((Integer) list.get(i4)).intValue());
            }
            this.zzdpv.zzca(i3);
            while (i2 < list.size()) {
                this.zzdpv.zzca(((Integer) list.get(i2)).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzdpv.zzn(i, ((Integer) list.get(i2)).intValue());
            i2++;
        }
    }

    public final void zzk(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzdpv.zzl(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzbav.zzci(((Integer) list.get(i4)).intValue());
            }
            this.zzdpv.zzca(i3);
            while (i2 < list.size()) {
                this.zzdpv.zzcc(((Integer) list.get(i2)).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzdpv.zzp(i, ((Integer) list.get(i2)).intValue());
            i2++;
        }
    }

    public final void zzl(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzdpv.zzl(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzbav.zzt(((Long) list.get(i4)).longValue());
            }
            this.zzdpv.zzca(i3);
            while (i2 < list.size()) {
                this.zzdpv.zzo(((Long) list.get(i2)).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzdpv.zzc(i, ((Long) list.get(i2)).longValue());
            i2++;
        }
    }

    public final void zzm(int i, int i2) throws IOException {
        this.zzdpv.zzm(i, i2);
    }

    public final void zzm(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzdpv.zzl(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzbav.zzcg(((Integer) list.get(i4)).intValue());
            }
            this.zzdpv.zzca(i3);
            while (i2 < list.size()) {
                this.zzdpv.zzcb(((Integer) list.get(i2)).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzdpv.zzo(i, ((Integer) list.get(i2)).intValue());
            i2++;
        }
    }

    public final void zzn(int i, int i2) throws IOException {
        this.zzdpv.zzn(i, i2);
    }

    public final void zzn(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzdpv.zzl(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzbav.zzr(((Long) list.get(i4)).longValue());
            }
            this.zzdpv.zzca(i3);
            while (i2 < list.size()) {
                this.zzdpv.zzn(((Long) list.get(i2)).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzdpv.zzb(i, ((Long) list.get(i2)).longValue());
            i2++;
        }
    }

    public final void zzo(int i, int i2) throws IOException {
        this.zzdpv.zzo(i, i2);
    }

    public final void zzp(int i, int i2) throws IOException {
        this.zzdpv.zzp(i, i2);
    }

    public final void zzw(int i, int i2) throws IOException {
        this.zzdpv.zzp(i, i2);
    }

    public final void zzx(int i, int i2) throws IOException {
        this.zzdpv.zzm(i, i2);
    }
}
