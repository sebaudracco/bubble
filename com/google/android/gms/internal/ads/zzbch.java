package com.google.android.gms.internal.ads;

import java.util.Collection;
import java.util.List;

final class zzbch extends zzbce {
    private zzbch() {
        super();
    }

    private static <E> zzbbt<E> zzd(Object obj, long j) {
        return (zzbbt) zzbek.zzp(obj, j);
    }

    final <L> List<L> zza(Object obj, long j) {
        zzbbt zzd = zzd(obj, j);
        if (zzd.zzaay()) {
            return zzd;
        }
        int size = zzd.size();
        Object zzbm = zzd.zzbm(size == 0 ? 10 : size << 1);
        zzbek.zza(obj, j, zzbm);
        return zzbm;
    }

    final <E> void zza(Object obj, Object obj2, long j) {
        Object zzd = zzd(obj, j);
        Collection zzd2 = zzd(obj2, j);
        int size = zzd.size();
        int size2 = zzd2.size();
        if (size > 0 && size2 > 0) {
            if (!zzd.zzaay()) {
                zzd = zzd.zzbm(size2 + size);
            }
            zzd.addAll(zzd2);
        }
        if (size <= 0) {
            Collection collection = zzd2;
        }
        zzbek.zza(obj, j, zzd);
    }

    final void zzb(Object obj, long j) {
        zzd(obj, j).zzaaz();
    }
}
