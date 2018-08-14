package com.google.android.gms.internal.measurement;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

final class zzzm<FieldDescriptorType extends zzzo<FieldDescriptorType>> {
    private static final zzzm zzbru = new zzzm(true);
    private boolean zzbls;
    private final zzaay<FieldDescriptorType, Object> zzbrs = zzaay.zzag(16);
    private boolean zzbrt = false;

    private zzzm() {
    }

    private zzzm(boolean z) {
        if (!this.zzbls) {
            this.zzbrs.zzrg();
            this.zzbls = true;
        }
    }

    private static void zza(zzabp com_google_android_gms_internal_measurement_zzabp, Object obj) {
        boolean z = false;
        zzzr.checkNotNull(obj);
        switch (zzzn.zzbrv[com_google_android_gms_internal_measurement_zzabp.zzuv().ordinal()]) {
            case 1:
                z = obj instanceof Integer;
                break;
            case 2:
                z = obj instanceof Long;
                break;
            case 3:
                z = obj instanceof Float;
                break;
            case 4:
                z = obj instanceof Double;
                break;
            case 5:
                z = obj instanceof Boolean;
                break;
            case 6:
                z = obj instanceof String;
                break;
            case 7:
                if ((obj instanceof zzyw) || (obj instanceof byte[])) {
                    z = true;
                    break;
                }
            case 8:
                if ((obj instanceof Integer) || (obj instanceof zzzs)) {
                    z = true;
                    break;
                }
            case 9:
                if ((obj instanceof zzaal) || (obj instanceof zzzu)) {
                    z = true;
                    break;
                }
        }
        if (!z) {
            throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
        }
    }

    private final void zza(FieldDescriptorType fieldDescriptorType, Object obj) {
        Object obj2;
        if (!fieldDescriptorType.zztk()) {
            zza(fieldDescriptorType.zztj(), obj);
            obj2 = obj;
        } else if (obj instanceof List) {
            obj2 = new ArrayList();
            obj2.addAll((List) obj);
            ArrayList arrayList = (ArrayList) obj2;
            int size = arrayList.size();
            int i = 0;
            while (i < size) {
                Object obj3 = arrayList.get(i);
                i++;
                zza(fieldDescriptorType.zztj(), obj3);
            }
        } else {
            throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
        }
        if (obj2 instanceof zzzu) {
            this.zzbrt = true;
        }
        this.zzbrs.zza((Comparable) fieldDescriptorType, obj2);
    }

    public static <T extends zzzo<T>> zzzm<T> zzti() {
        return zzbru;
    }

    public final /* synthetic */ Object clone() throws CloneNotSupportedException {
        zzzm com_google_android_gms_internal_measurement_zzzm = new zzzm();
        for (int i = 0; i < this.zzbrs.zzuj(); i++) {
            Entry zzah = this.zzbrs.zzah(i);
            com_google_android_gms_internal_measurement_zzzm.zza((zzzo) zzah.getKey(), zzah.getValue());
        }
        for (Entry entry : this.zzbrs.zzuk()) {
            com_google_android_gms_internal_measurement_zzzm.zza((zzzo) entry.getKey(), entry.getValue());
        }
        com_google_android_gms_internal_measurement_zzzm.zzbrt = this.zzbrt;
        return com_google_android_gms_internal_measurement_zzzm;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzzm)) {
            return false;
        }
        return this.zzbrs.equals(((zzzm) obj).zzbrs);
    }

    public final int hashCode() {
        return this.zzbrs.hashCode();
    }

    public final Iterator<Entry<FieldDescriptorType, Object>> iterator() {
        return this.zzbrt ? new zzzx(this.zzbrs.entrySet().iterator()) : this.zzbrs.entrySet().iterator();
    }
}
