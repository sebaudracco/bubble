package com.google.android.gms.internal.ads;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

final class zzbcq implements zzbcp {
    zzbcq() {
    }

    public final int zzb(int i, Object obj, Object obj2) {
        zzbco com_google_android_gms_internal_ads_zzbco = (zzbco) obj;
        if (!com_google_android_gms_internal_ads_zzbco.isEmpty()) {
            Iterator it = com_google_android_gms_internal_ads_zzbco.entrySet().iterator();
            if (it.hasNext()) {
                Entry entry = (Entry) it.next();
                entry.getKey();
                entry.getValue();
                throw new NoSuchMethodError();
            }
        }
        return 0;
    }

    public final Object zzb(Object obj, Object obj2) {
        obj = (zzbco) obj;
        zzbco com_google_android_gms_internal_ads_zzbco = (zzbco) obj2;
        if (!com_google_android_gms_internal_ads_zzbco.isEmpty()) {
            if (!obj.isMutable()) {
                obj = obj.zzaec();
            }
            obj.zza(com_google_android_gms_internal_ads_zzbco);
        }
        return obj;
    }

    public final Map<?, ?> zzs(Object obj) {
        return (zzbco) obj;
    }

    public final Map<?, ?> zzt(Object obj) {
        return (zzbco) obj;
    }

    public final boolean zzu(Object obj) {
        return !((zzbco) obj).isMutable();
    }

    public final Object zzv(Object obj) {
        ((zzbco) obj).zzaaz();
        return obj;
    }

    public final Object zzw(Object obj) {
        return zzbco.zzaeb().zzaec();
    }

    public final zzbcn<?, ?> zzx(Object obj) {
        throw new NoSuchMethodError();
    }
}
