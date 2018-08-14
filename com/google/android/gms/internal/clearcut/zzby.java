package com.google.android.gms.internal.clearcut;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

final class zzby<FieldDescriptorType extends zzca<FieldDescriptorType>> {
    private static final zzby zzgw = new zzby(true);
    private final zzei<FieldDescriptorType, Object> zzgt = zzei.zzaj(16);
    private boolean zzgu;
    private boolean zzgv = false;

    private zzby() {
    }

    private zzby(boolean z) {
        zzv();
    }

    static int zza(zzfl com_google_android_gms_internal_clearcut_zzfl, int i, Object obj) {
        int i2;
        int zzr = zzbn.zzr(i);
        if (com_google_android_gms_internal_clearcut_zzfl == zzfl.GROUP) {
            zzci.zzf((zzdo) obj);
            i2 = zzr << 1;
        } else {
            i2 = zzr;
        }
        return i2 + zzb(com_google_android_gms_internal_clearcut_zzfl, obj);
    }

    private final Object zza(FieldDescriptorType fieldDescriptorType) {
        Object obj = this.zzgt.get(fieldDescriptorType);
        return obj instanceof zzcr ? zzcr.zzbr() : obj;
    }

    static void zza(zzbn com_google_android_gms_internal_clearcut_zzbn, zzfl com_google_android_gms_internal_clearcut_zzfl, int i, Object obj) throws IOException {
        if (com_google_android_gms_internal_clearcut_zzfl == zzfl.GROUP) {
            zzci.zzf((zzdo) obj);
            zzdo com_google_android_gms_internal_clearcut_zzdo = (zzdo) obj;
            com_google_android_gms_internal_clearcut_zzbn.zzb(i, 3);
            com_google_android_gms_internal_clearcut_zzdo.zzb(com_google_android_gms_internal_clearcut_zzbn);
            com_google_android_gms_internal_clearcut_zzbn.zzb(i, 4);
            return;
        }
        com_google_android_gms_internal_clearcut_zzbn.zzb(i, com_google_android_gms_internal_clearcut_zzfl.zzel());
        switch (zzbz.zzgq[com_google_android_gms_internal_clearcut_zzfl.ordinal()]) {
            case 1:
                com_google_android_gms_internal_clearcut_zzbn.zza(((Double) obj).doubleValue());
                return;
            case 2:
                com_google_android_gms_internal_clearcut_zzbn.zza(((Float) obj).floatValue());
                return;
            case 3:
                com_google_android_gms_internal_clearcut_zzbn.zzb(((Long) obj).longValue());
                return;
            case 4:
                com_google_android_gms_internal_clearcut_zzbn.zzb(((Long) obj).longValue());
                return;
            case 5:
                com_google_android_gms_internal_clearcut_zzbn.zzn(((Integer) obj).intValue());
                return;
            case 6:
                com_google_android_gms_internal_clearcut_zzbn.zzd(((Long) obj).longValue());
                return;
            case 7:
                com_google_android_gms_internal_clearcut_zzbn.zzq(((Integer) obj).intValue());
                return;
            case 8:
                com_google_android_gms_internal_clearcut_zzbn.zza(((Boolean) obj).booleanValue());
                return;
            case 9:
                ((zzdo) obj).zzb(com_google_android_gms_internal_clearcut_zzbn);
                return;
            case 10:
                com_google_android_gms_internal_clearcut_zzbn.zzb((zzdo) obj);
                return;
            case 11:
                if (obj instanceof zzbb) {
                    com_google_android_gms_internal_clearcut_zzbn.zza((zzbb) obj);
                    return;
                } else {
                    com_google_android_gms_internal_clearcut_zzbn.zzg((String) obj);
                    return;
                }
            case 12:
                if (obj instanceof zzbb) {
                    com_google_android_gms_internal_clearcut_zzbn.zza((zzbb) obj);
                    return;
                }
                byte[] bArr = (byte[]) obj;
                com_google_android_gms_internal_clearcut_zzbn.zzd(bArr, 0, bArr.length);
                return;
            case 13:
                com_google_android_gms_internal_clearcut_zzbn.zzo(((Integer) obj).intValue());
                return;
            case 14:
                com_google_android_gms_internal_clearcut_zzbn.zzq(((Integer) obj).intValue());
                return;
            case 15:
                com_google_android_gms_internal_clearcut_zzbn.zzd(((Long) obj).longValue());
                return;
            case 16:
                com_google_android_gms_internal_clearcut_zzbn.zzp(((Integer) obj).intValue());
                return;
            case 17:
                com_google_android_gms_internal_clearcut_zzbn.zzc(((Long) obj).longValue());
                return;
            case 18:
                if (obj instanceof zzcj) {
                    com_google_android_gms_internal_clearcut_zzbn.zzn(((zzcj) obj).zzc());
                    return;
                } else {
                    com_google_android_gms_internal_clearcut_zzbn.zzn(((Integer) obj).intValue());
                    return;
                }
            default:
                return;
        }
    }

    private final void zza(FieldDescriptorType fieldDescriptorType, Object obj) {
        Object obj2;
        if (!fieldDescriptorType.zzaw()) {
            zza(fieldDescriptorType.zzau(), obj);
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
                zza(fieldDescriptorType.zzau(), obj3);
            }
        } else {
            throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
        }
        if (obj2 instanceof zzcr) {
            this.zzgv = true;
        }
        this.zzgt.zza((Comparable) fieldDescriptorType, obj2);
    }

    private static void zza(zzfl com_google_android_gms_internal_clearcut_zzfl, Object obj) {
        boolean z = false;
        zzci.checkNotNull(obj);
        switch (zzbz.zzgx[com_google_android_gms_internal_clearcut_zzfl.zzek().ordinal()]) {
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
                if ((obj instanceof zzbb) || (obj instanceof byte[])) {
                    z = true;
                    break;
                }
            case 8:
                if ((obj instanceof Integer) || (obj instanceof zzcj)) {
                    z = true;
                    break;
                }
            case 9:
                if ((obj instanceof zzdo) || (obj instanceof zzcr)) {
                    z = true;
                    break;
                }
        }
        if (!z) {
            throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
        }
    }

    public static <T extends zzca<T>> zzby<T> zzar() {
        return zzgw;
    }

    private static int zzb(zzca<?> com_google_android_gms_internal_clearcut_zzca_, Object obj) {
        int i = 0;
        zzfl zzau = com_google_android_gms_internal_clearcut_zzca_.zzau();
        int zzc = com_google_android_gms_internal_clearcut_zzca_.zzc();
        if (!com_google_android_gms_internal_clearcut_zzca_.zzaw()) {
            return zza(zzau, zzc, obj);
        }
        if (com_google_android_gms_internal_clearcut_zzca_.zzax()) {
            for (Object zzb : (List) obj) {
                i += zzb(zzau, zzb);
            }
            return zzbn.zzz(i) + (zzbn.zzr(zzc) + i);
        }
        for (Object zzb2 : (List) obj) {
            i += zza(zzau, zzc, zzb2);
        }
        return i;
    }

    private static int zzb(zzfl com_google_android_gms_internal_clearcut_zzfl, Object obj) {
        switch (zzbz.zzgq[com_google_android_gms_internal_clearcut_zzfl.ordinal()]) {
            case 1:
                return zzbn.zzb(((Double) obj).doubleValue());
            case 2:
                return zzbn.zzb(((Float) obj).floatValue());
            case 3:
                return zzbn.zze(((Long) obj).longValue());
            case 4:
                return zzbn.zzf(((Long) obj).longValue());
            case 5:
                return zzbn.zzs(((Integer) obj).intValue());
            case 6:
                return zzbn.zzh(((Long) obj).longValue());
            case 7:
                return zzbn.zzv(((Integer) obj).intValue());
            case 8:
                return zzbn.zzb(((Boolean) obj).booleanValue());
            case 9:
                return zzbn.zzd((zzdo) obj);
            case 10:
                return obj instanceof zzcr ? zzbn.zza((zzcr) obj) : zzbn.zzc((zzdo) obj);
            case 11:
                return obj instanceof zzbb ? zzbn.zzb((zzbb) obj) : zzbn.zzh((String) obj);
            case 12:
                return obj instanceof zzbb ? zzbn.zzb((zzbb) obj) : zzbn.zzd((byte[]) obj);
            case 13:
                return zzbn.zzt(((Integer) obj).intValue());
            case 14:
                return zzbn.zzw(((Integer) obj).intValue());
            case 15:
                return zzbn.zzi(((Long) obj).longValue());
            case 16:
                return zzbn.zzu(((Integer) obj).intValue());
            case 17:
                return zzbn.zzg(((Long) obj).longValue());
            case 18:
                return obj instanceof zzcj ? zzbn.zzx(((zzcj) obj).zzc()) : zzbn.zzx(((Integer) obj).intValue());
            default:
                throw new RuntimeException("There is no way to get here, but the compiler thinks otherwise.");
        }
    }

    private static boolean zzb(Entry<FieldDescriptorType, Object> entry) {
        zzca com_google_android_gms_internal_clearcut_zzca = (zzca) entry.getKey();
        if (com_google_android_gms_internal_clearcut_zzca.zzav() == zzfq.MESSAGE) {
            if (com_google_android_gms_internal_clearcut_zzca.zzaw()) {
                for (zzdo isInitialized : (List) entry.getValue()) {
                    if (!isInitialized.isInitialized()) {
                        return false;
                    }
                }
            }
            Object value = entry.getValue();
            if (value instanceof zzdo) {
                if (!((zzdo) value).isInitialized()) {
                    return false;
                }
            } else if (value instanceof zzcr) {
                return true;
            } else {
                throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
            }
        }
        return true;
    }

    private final void zzc(Entry<FieldDescriptorType, Object> entry) {
        Comparable comparable = (zzca) entry.getKey();
        Object value = entry.getValue();
        if (value instanceof zzcr) {
            value = zzcr.zzbr();
        }
        Object zza;
        if (comparable.zzaw()) {
            zza = zza((zzca) comparable);
            if (zza == null) {
                zza = new ArrayList();
            }
            for (Object zzd : (List) value) {
                ((List) zza).add(zzd(zzd));
            }
            this.zzgt.zza(comparable, zza);
        } else if (comparable.zzav() == zzfq.MESSAGE) {
            zza = zza((zzca) comparable);
            if (zza == null) {
                this.zzgt.zza(comparable, zzd(value));
            } else {
                this.zzgt.zza(comparable, zza instanceof zzdv ? comparable.zza((zzdv) zza, (zzdv) value) : comparable.zza(((zzdo) zza).zzbc(), (zzdo) value).zzbj());
            }
        } else {
            this.zzgt.zza(comparable, zzd(value));
        }
    }

    private static int zzd(Entry<FieldDescriptorType, Object> entry) {
        zzca com_google_android_gms_internal_clearcut_zzca = (zzca) entry.getKey();
        Object value = entry.getValue();
        return (com_google_android_gms_internal_clearcut_zzca.zzav() != zzfq.MESSAGE || com_google_android_gms_internal_clearcut_zzca.zzaw() || com_google_android_gms_internal_clearcut_zzca.zzax()) ? zzb(com_google_android_gms_internal_clearcut_zzca, value) : value instanceof zzcr ? zzbn.zzb(((zzca) entry.getKey()).zzc(), (zzcr) value) : zzbn.zzd(((zzca) entry.getKey()).zzc(), (zzdo) value);
    }

    private static Object zzd(Object obj) {
        if (obj instanceof zzdv) {
            return ((zzdv) obj).zzci();
        }
        if (!(obj instanceof byte[])) {
            return obj;
        }
        byte[] bArr = (byte[]) obj;
        Object obj2 = new byte[bArr.length];
        System.arraycopy(bArr, 0, obj2, 0, bArr.length);
        return obj2;
    }

    public final /* synthetic */ Object clone() throws CloneNotSupportedException {
        zzby com_google_android_gms_internal_clearcut_zzby = new zzby();
        for (int i = 0; i < this.zzgt.zzdr(); i++) {
            Entry zzak = this.zzgt.zzak(i);
            com_google_android_gms_internal_clearcut_zzby.zza((zzca) zzak.getKey(), zzak.getValue());
        }
        for (Entry entry : this.zzgt.zzds()) {
            com_google_android_gms_internal_clearcut_zzby.zza((zzca) entry.getKey(), entry.getValue());
        }
        com_google_android_gms_internal_clearcut_zzby.zzgv = this.zzgv;
        return com_google_android_gms_internal_clearcut_zzby;
    }

    final Iterator<Entry<FieldDescriptorType, Object>> descendingIterator() {
        return this.zzgv ? new zzcu(this.zzgt.zzdt().iterator()) : this.zzgt.zzdt().iterator();
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzby)) {
            return false;
        }
        return this.zzgt.equals(((zzby) obj).zzgt);
    }

    public final int hashCode() {
        return this.zzgt.hashCode();
    }

    final boolean isEmpty() {
        return this.zzgt.isEmpty();
    }

    public final boolean isImmutable() {
        return this.zzgu;
    }

    public final boolean isInitialized() {
        for (int i = 0; i < this.zzgt.zzdr(); i++) {
            if (!zzb(this.zzgt.zzak(i))) {
                return false;
            }
        }
        for (Entry zzb : this.zzgt.zzds()) {
            if (!zzb(zzb)) {
                return false;
            }
        }
        return true;
    }

    public final Iterator<Entry<FieldDescriptorType, Object>> iterator() {
        return this.zzgv ? new zzcu(this.zzgt.entrySet().iterator()) : this.zzgt.entrySet().iterator();
    }

    public final void zza(zzby<FieldDescriptorType> com_google_android_gms_internal_clearcut_zzby_FieldDescriptorType) {
        for (int i = 0; i < com_google_android_gms_internal_clearcut_zzby_FieldDescriptorType.zzgt.zzdr(); i++) {
            zzc(com_google_android_gms_internal_clearcut_zzby_FieldDescriptorType.zzgt.zzak(i));
        }
        for (Entry zzc : com_google_android_gms_internal_clearcut_zzby_FieldDescriptorType.zzgt.zzds()) {
            zzc(zzc);
        }
    }

    public final int zzas() {
        int i = 0;
        for (int i2 = 0; i2 < this.zzgt.zzdr(); i2++) {
            Entry zzak = this.zzgt.zzak(i2);
            i += zzb((zzca) zzak.getKey(), zzak.getValue());
        }
        for (Entry entry : this.zzgt.zzds()) {
            i += zzb((zzca) entry.getKey(), entry.getValue());
        }
        return i;
    }

    public final int zzat() {
        int i = 0;
        int i2 = 0;
        while (i < this.zzgt.zzdr()) {
            i2 += zzd(this.zzgt.zzak(i));
            i++;
        }
        for (Entry zzd : this.zzgt.zzds()) {
            i2 += zzd(zzd);
        }
        return i2;
    }

    public final void zzv() {
        if (!this.zzgu) {
            this.zzgt.zzv();
            this.zzgu = true;
        }
    }
}
