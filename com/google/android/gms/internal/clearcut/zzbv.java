package com.google.android.gms.internal.clearcut;

import com.google.android.gms.internal.clearcut.zzcg.zzd;
import java.io.IOException;
import java.util.Map.Entry;

final class zzbv extends zzbu<zze> {
    zzbv() {
    }

    final int zza(Entry<?, ?> entry) {
        return ((zze) entry.getKey()).number;
    }

    final zzby<zze> zza(Object obj) {
        return ((zzd) obj).zzjv;
    }

    final void zza(zzfr com_google_android_gms_internal_clearcut_zzfr, Entry<?, ?> entry) throws IOException {
        zze com_google_android_gms_internal_clearcut_zzcg_zze = (zze) entry.getKey();
        switch (zzbw.zzgq[com_google_android_gms_internal_clearcut_zzcg_zze.zzjx.ordinal()]) {
            case 1:
                com_google_android_gms_internal_clearcut_zzfr.zza(com_google_android_gms_internal_clearcut_zzcg_zze.number, ((Double) entry.getValue()).doubleValue());
                return;
            case 2:
                com_google_android_gms_internal_clearcut_zzfr.zza(com_google_android_gms_internal_clearcut_zzcg_zze.number, ((Float) entry.getValue()).floatValue());
                return;
            case 3:
                com_google_android_gms_internal_clearcut_zzfr.zzi(com_google_android_gms_internal_clearcut_zzcg_zze.number, ((Long) entry.getValue()).longValue());
                return;
            case 4:
                com_google_android_gms_internal_clearcut_zzfr.zza(com_google_android_gms_internal_clearcut_zzcg_zze.number, ((Long) entry.getValue()).longValue());
                return;
            case 5:
                com_google_android_gms_internal_clearcut_zzfr.zzc(com_google_android_gms_internal_clearcut_zzcg_zze.number, ((Integer) entry.getValue()).intValue());
                return;
            case 6:
                com_google_android_gms_internal_clearcut_zzfr.zzc(com_google_android_gms_internal_clearcut_zzcg_zze.number, ((Long) entry.getValue()).longValue());
                return;
            case 7:
                com_google_android_gms_internal_clearcut_zzfr.zzf(com_google_android_gms_internal_clearcut_zzcg_zze.number, ((Integer) entry.getValue()).intValue());
                return;
            case 8:
                com_google_android_gms_internal_clearcut_zzfr.zzb(com_google_android_gms_internal_clearcut_zzcg_zze.number, ((Boolean) entry.getValue()).booleanValue());
                return;
            case 9:
                com_google_android_gms_internal_clearcut_zzfr.zzd(com_google_android_gms_internal_clearcut_zzcg_zze.number, ((Integer) entry.getValue()).intValue());
                return;
            case 10:
                com_google_android_gms_internal_clearcut_zzfr.zzm(com_google_android_gms_internal_clearcut_zzcg_zze.number, ((Integer) entry.getValue()).intValue());
                return;
            case 11:
                com_google_android_gms_internal_clearcut_zzfr.zzj(com_google_android_gms_internal_clearcut_zzcg_zze.number, ((Long) entry.getValue()).longValue());
                return;
            case 12:
                com_google_android_gms_internal_clearcut_zzfr.zze(com_google_android_gms_internal_clearcut_zzcg_zze.number, ((Integer) entry.getValue()).intValue());
                return;
            case 13:
                com_google_android_gms_internal_clearcut_zzfr.zzb(com_google_android_gms_internal_clearcut_zzcg_zze.number, ((Long) entry.getValue()).longValue());
                return;
            case 14:
                com_google_android_gms_internal_clearcut_zzfr.zzc(com_google_android_gms_internal_clearcut_zzcg_zze.number, ((Integer) entry.getValue()).intValue());
                return;
            case 15:
                com_google_android_gms_internal_clearcut_zzfr.zza(com_google_android_gms_internal_clearcut_zzcg_zze.number, (zzbb) entry.getValue());
                return;
            case 16:
                com_google_android_gms_internal_clearcut_zzfr.zza(com_google_android_gms_internal_clearcut_zzcg_zze.number, (String) entry.getValue());
                return;
            case 17:
                com_google_android_gms_internal_clearcut_zzfr.zzb(com_google_android_gms_internal_clearcut_zzcg_zze.number, entry.getValue(), zzea.zzcm().zze(entry.getValue().getClass()));
                return;
            case 18:
                com_google_android_gms_internal_clearcut_zzfr.zza(com_google_android_gms_internal_clearcut_zzcg_zze.number, entry.getValue(), zzea.zzcm().zze(entry.getValue().getClass()));
                return;
            default:
                return;
        }
    }

    final void zza(Object obj, zzby<zze> com_google_android_gms_internal_clearcut_zzby_com_google_android_gms_internal_clearcut_zzcg_zze) {
        ((zzd) obj).zzjv = com_google_android_gms_internal_clearcut_zzby_com_google_android_gms_internal_clearcut_zzcg_zze;
    }

    final zzby<zze> zzb(Object obj) {
        zzby<zze> zza = zza(obj);
        if (!zza.isImmutable()) {
            return zza;
        }
        zzby com_google_android_gms_internal_clearcut_zzby = (zzby) zza.clone();
        zza(obj, com_google_android_gms_internal_clearcut_zzby);
        return com_google_android_gms_internal_clearcut_zzby;
    }

    final void zzc(Object obj) {
        zza(obj).zzv();
    }

    final boolean zze(zzdo com_google_android_gms_internal_clearcut_zzdo) {
        return com_google_android_gms_internal_clearcut_zzdo instanceof zzd;
    }
}
