package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

final class zzan {
    final String zza;
    final long zzb;
    final long zzc;
    long zzca;
    final String zzcb;
    final long zzd;
    final long zze;
    final List<zzl> zzg;

    zzan(String str, zzc com_google_android_gms_internal_ads_zzc) {
        List list;
        String str2 = com_google_android_gms_internal_ads_zzc.zza;
        long j = com_google_android_gms_internal_ads_zzc.zzb;
        long j2 = com_google_android_gms_internal_ads_zzc.zzc;
        long j3 = com_google_android_gms_internal_ads_zzc.zzd;
        long j4 = com_google_android_gms_internal_ads_zzc.zze;
        if (com_google_android_gms_internal_ads_zzc.zzg != null) {
            list = com_google_android_gms_internal_ads_zzc.zzg;
        } else {
            Map map = com_google_android_gms_internal_ads_zzc.zzf;
            list = new ArrayList(map.size());
            for (Entry entry : map.entrySet()) {
                list.add(new zzl((String) entry.getKey(), (String) entry.getValue()));
            }
        }
        this(str, str2, j, j2, j3, j4, list);
        this.zzca = (long) com_google_android_gms_internal_ads_zzc.data.length;
    }

    private zzan(String str, String str2, long j, long j2, long j3, long j4, List<zzl> list) {
        this.zzcb = str;
        if ("".equals(str2)) {
            str2 = null;
        }
        this.zza = str2;
        this.zzb = j;
        this.zzc = j2;
        this.zzd = j3;
        this.zze = j4;
        this.zzg = list;
    }

    static zzan zzc(zzao com_google_android_gms_internal_ads_zzao) throws IOException {
        if (zzam.zzb((InputStream) com_google_android_gms_internal_ads_zzao) == 538247942) {
            return new zzan(zzam.zza(com_google_android_gms_internal_ads_zzao), zzam.zza(com_google_android_gms_internal_ads_zzao), zzam.zzc(com_google_android_gms_internal_ads_zzao), zzam.zzc(com_google_android_gms_internal_ads_zzao), zzam.zzc(com_google_android_gms_internal_ads_zzao), zzam.zzc(com_google_android_gms_internal_ads_zzao), zzam.zzb(com_google_android_gms_internal_ads_zzao));
        }
        throw new IOException();
    }

    final boolean zza(OutputStream outputStream) {
        try {
            zzam.zza(outputStream, 538247942);
            zzam.zza(outputStream, this.zzcb);
            zzam.zza(outputStream, this.zza == null ? "" : this.zza);
            zzam.zza(outputStream, this.zzb);
            zzam.zza(outputStream, this.zzc);
            zzam.zza(outputStream, this.zzd);
            zzam.zza(outputStream, this.zze);
            List<zzl> list = this.zzg;
            if (list != null) {
                zzam.zza(outputStream, list.size());
                for (zzl com_google_android_gms_internal_ads_zzl : list) {
                    zzam.zza(outputStream, com_google_android_gms_internal_ads_zzl.getName());
                    zzam.zza(outputStream, com_google_android_gms_internal_ads_zzl.getValue());
                }
            } else {
                zzam.zza(outputStream, 0);
            }
            outputStream.flush();
            return true;
        } catch (IOException e) {
            zzaf.m8608d("%s", e.toString());
            return false;
        }
    }
}
