package com.google.android.gms.internal.clearcut;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

final class zzdk implements zzdj {
    zzdk() {
    }

    public final int zzb(int i, Object obj, Object obj2) {
        zzdi com_google_android_gms_internal_clearcut_zzdi = (zzdi) obj;
        if (!com_google_android_gms_internal_clearcut_zzdi.isEmpty()) {
            Iterator it = com_google_android_gms_internal_clearcut_zzdi.entrySet().iterator();
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
        obj = (zzdi) obj;
        zzdi com_google_android_gms_internal_clearcut_zzdi = (zzdi) obj2;
        if (!com_google_android_gms_internal_clearcut_zzdi.isEmpty()) {
            if (!obj.isMutable()) {
                obj = obj.zzca();
            }
            obj.zza(com_google_android_gms_internal_clearcut_zzdi);
        }
        return obj;
    }

    public final Map<?, ?> zzg(Object obj) {
        return (zzdi) obj;
    }

    public final Map<?, ?> zzh(Object obj) {
        return (zzdi) obj;
    }

    public final boolean zzi(Object obj) {
        return !((zzdi) obj).isMutable();
    }

    public final Object zzj(Object obj) {
        ((zzdi) obj).zzv();
        return obj;
    }

    public final Object zzk(Object obj) {
        return zzdi.zzbz().zzca();
    }

    public final zzdh<?, ?> zzl(Object obj) {
        throw new NoSuchMethodError();
    }
}
