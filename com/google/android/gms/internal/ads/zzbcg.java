package com.google.android.gms.internal.ads;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

final class zzbcg extends zzbce {
    private static final Class<?> zzdvs = Collections.unmodifiableList(Collections.emptyList()).getClass();

    private zzbcg() {
        super();
    }

    private static <L> List<L> zza(Object obj, long j, int i) {
        List<L> zzc = zzc(obj, j);
        if (zzc.isEmpty()) {
            Object com_google_android_gms_internal_ads_zzbcc = zzc instanceof zzbcd ? new zzbcc(i) : new ArrayList(i);
            zzbek.zza(obj, j, com_google_android_gms_internal_ads_zzbcc);
            return com_google_android_gms_internal_ads_zzbcc;
        } else if (zzdvs.isAssignableFrom(zzc.getClass())) {
            r1 = new ArrayList(zzc.size() + i);
            r1.addAll(zzc);
            zzbek.zza(obj, j, (Object) r1);
            return r1;
        } else if (!(zzc instanceof zzbeh)) {
            return zzc;
        } else {
            r1 = new zzbcc(zzc.size() + i);
            r1.addAll((zzbeh) zzc);
            zzbek.zza(obj, j, (Object) r1);
            return r1;
        }
    }

    private static <E> List<E> zzc(Object obj, long j) {
        return (List) zzbek.zzp(obj, j);
    }

    final <L> List<L> zza(Object obj, long j) {
        return zza(obj, j, 10);
    }

    final <E> void zza(Object obj, Object obj2, long j) {
        Collection zzc = zzc(obj2, j);
        Object zza = zza(obj, j, zzc.size());
        int size = zza.size();
        int size2 = zzc.size();
        if (size > 0 && size2 > 0) {
            zza.addAll(zzc);
        }
        if (size <= 0) {
            Collection collection = zzc;
        }
        zzbek.zza(obj, j, zza);
    }

    final void zzb(Object obj, long j) {
        Object zzadx;
        List list = (List) zzbek.zzp(obj, j);
        if (list instanceof zzbcd) {
            zzadx = ((zzbcd) list).zzadx();
        } else if (!zzdvs.isAssignableFrom(list.getClass())) {
            zzadx = Collections.unmodifiableList(list);
        } else {
            return;
        }
        zzbek.zza(obj, j, zzadx);
    }
}
