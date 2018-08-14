package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

final class zzbbg<FieldDescriptorType extends zzbbi<FieldDescriptorType>> {
    private static final zzbbg zzdra = new zzbbg(true);
    private final zzbdp<FieldDescriptorType, Object> zzdqx = zzbdp.zzcx(16);
    private boolean zzdqy;
    private boolean zzdqz = false;

    private zzbbg() {
    }

    private zzbbg(boolean z) {
        zzaaz();
    }

    static int zza(zzbes com_google_android_gms_internal_ads_zzbes, int i, Object obj) {
        int i2;
        int zzcd = zzbav.zzcd(i);
        if (com_google_android_gms_internal_ads_zzbes == zzbes.GROUP) {
            zzbbq.zzi((zzbcu) obj);
            i2 = zzcd << 1;
        } else {
            i2 = zzcd;
        }
        return i2 + zzb(com_google_android_gms_internal_ads_zzbes, obj);
    }

    private final Object zza(FieldDescriptorType fieldDescriptorType) {
        Object obj = this.zzdqx.get(fieldDescriptorType);
        return obj instanceof zzbbx ? zzbbx.zzadu() : obj;
    }

    static void zza(zzbav com_google_android_gms_internal_ads_zzbav, zzbes com_google_android_gms_internal_ads_zzbes, int i, Object obj) throws IOException {
        if (com_google_android_gms_internal_ads_zzbes == zzbes.GROUP) {
            zzbbq.zzi((zzbcu) obj);
            zzbcu com_google_android_gms_internal_ads_zzbcu = (zzbcu) obj;
            com_google_android_gms_internal_ads_zzbav.zzl(i, 3);
            com_google_android_gms_internal_ads_zzbcu.zzb(com_google_android_gms_internal_ads_zzbav);
            com_google_android_gms_internal_ads_zzbav.zzl(i, 4);
            return;
        }
        com_google_android_gms_internal_ads_zzbav.zzl(i, com_google_android_gms_internal_ads_zzbes.zzagm());
        switch (zzbbh.zzdql[com_google_android_gms_internal_ads_zzbes.ordinal()]) {
            case 1:
                com_google_android_gms_internal_ads_zzbav.zzb(((Double) obj).doubleValue());
                return;
            case 2:
                com_google_android_gms_internal_ads_zzbav.zzb(((Float) obj).floatValue());
                return;
            case 3:
                com_google_android_gms_internal_ads_zzbav.zzm(((Long) obj).longValue());
                return;
            case 4:
                com_google_android_gms_internal_ads_zzbav.zzm(((Long) obj).longValue());
                return;
            case 5:
                com_google_android_gms_internal_ads_zzbav.zzbz(((Integer) obj).intValue());
                return;
            case 6:
                com_google_android_gms_internal_ads_zzbav.zzo(((Long) obj).longValue());
                return;
            case 7:
                com_google_android_gms_internal_ads_zzbav.zzcc(((Integer) obj).intValue());
                return;
            case 8:
                com_google_android_gms_internal_ads_zzbav.zzap(((Boolean) obj).booleanValue());
                return;
            case 9:
                ((zzbcu) obj).zzb(com_google_android_gms_internal_ads_zzbav);
                return;
            case 10:
                com_google_android_gms_internal_ads_zzbav.zze((zzbcu) obj);
                return;
            case 11:
                if (obj instanceof zzbah) {
                    com_google_android_gms_internal_ads_zzbav.zzan((zzbah) obj);
                    return;
                } else {
                    com_google_android_gms_internal_ads_zzbav.zzen((String) obj);
                    return;
                }
            case 12:
                if (obj instanceof zzbah) {
                    com_google_android_gms_internal_ads_zzbav.zzan((zzbah) obj);
                    return;
                }
                byte[] bArr = (byte[]) obj;
                com_google_android_gms_internal_ads_zzbav.zze(bArr, 0, bArr.length);
                return;
            case 13:
                com_google_android_gms_internal_ads_zzbav.zzca(((Integer) obj).intValue());
                return;
            case 14:
                com_google_android_gms_internal_ads_zzbav.zzcc(((Integer) obj).intValue());
                return;
            case 15:
                com_google_android_gms_internal_ads_zzbav.zzo(((Long) obj).longValue());
                return;
            case 16:
                com_google_android_gms_internal_ads_zzbav.zzcb(((Integer) obj).intValue());
                return;
            case 17:
                com_google_android_gms_internal_ads_zzbav.zzn(((Long) obj).longValue());
                return;
            case 18:
                if (obj instanceof zzbbr) {
                    com_google_android_gms_internal_ads_zzbav.zzbz(((zzbbr) obj).zzhq());
                    return;
                } else {
                    com_google_android_gms_internal_ads_zzbav.zzbz(((Integer) obj).intValue());
                    return;
                }
            default:
                return;
        }
    }

    private final void zza(FieldDescriptorType fieldDescriptorType, Object obj) {
        Object obj2;
        if (!fieldDescriptorType.zzada()) {
            zza(fieldDescriptorType.zzacy(), obj);
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
                zza(fieldDescriptorType.zzacy(), obj3);
            }
        } else {
            throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
        }
        if (obj2 instanceof zzbbx) {
            this.zzdqz = true;
        }
        this.zzdqx.zza((Comparable) fieldDescriptorType, obj2);
    }

    private static void zza(zzbes com_google_android_gms_internal_ads_zzbes, Object obj) {
        boolean z = false;
        zzbbq.checkNotNull(obj);
        switch (zzbbh.zzdrb[com_google_android_gms_internal_ads_zzbes.zzagl().ordinal()]) {
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
                if ((obj instanceof zzbah) || (obj instanceof byte[])) {
                    z = true;
                    break;
                }
            case 8:
                if ((obj instanceof Integer) || (obj instanceof zzbbr)) {
                    z = true;
                    break;
                }
            case 9:
                if ((obj instanceof zzbcu) || (obj instanceof zzbbx)) {
                    z = true;
                    break;
                }
        }
        if (!z) {
            throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
        }
    }

    public static <T extends zzbbi<T>> zzbbg<T> zzacv() {
        return zzdra;
    }

    private static int zzb(zzbbi<?> com_google_android_gms_internal_ads_zzbbi_, Object obj) {
        int i = 0;
        zzbes zzacy = com_google_android_gms_internal_ads_zzbbi_.zzacy();
        int zzhq = com_google_android_gms_internal_ads_zzbbi_.zzhq();
        if (!com_google_android_gms_internal_ads_zzbbi_.zzada()) {
            return zza(zzacy, zzhq, obj);
        }
        if (com_google_android_gms_internal_ads_zzbbi_.zzadb()) {
            for (Object zzb : (List) obj) {
                i += zzb(zzacy, zzb);
            }
            return zzbav.zzcl(i) + (zzbav.zzcd(zzhq) + i);
        }
        for (Object zzb2 : (List) obj) {
            i += zza(zzacy, zzhq, zzb2);
        }
        return i;
    }

    private static int zzb(zzbes com_google_android_gms_internal_ads_zzbes, Object obj) {
        switch (zzbbh.zzdql[com_google_android_gms_internal_ads_zzbes.ordinal()]) {
            case 1:
                return zzbav.zzc(((Double) obj).doubleValue());
            case 2:
                return zzbav.zzc(((Float) obj).floatValue());
            case 3:
                return zzbav.zzp(((Long) obj).longValue());
            case 4:
                return zzbav.zzq(((Long) obj).longValue());
            case 5:
                return zzbav.zzce(((Integer) obj).intValue());
            case 6:
                return zzbav.zzs(((Long) obj).longValue());
            case 7:
                return zzbav.zzch(((Integer) obj).intValue());
            case 8:
                return zzbav.zzaq(((Boolean) obj).booleanValue());
            case 9:
                return zzbav.zzg((zzbcu) obj);
            case 10:
                return obj instanceof zzbbx ? zzbav.zza((zzbbx) obj) : zzbav.zzf((zzbcu) obj);
            case 11:
                return obj instanceof zzbah ? zzbav.zzao((zzbah) obj) : zzbav.zzeo((String) obj);
            case 12:
                return obj instanceof zzbah ? zzbav.zzao((zzbah) obj) : zzbav.zzr((byte[]) obj);
            case 13:
                return zzbav.zzcf(((Integer) obj).intValue());
            case 14:
                return zzbav.zzci(((Integer) obj).intValue());
            case 15:
                return zzbav.zzt(((Long) obj).longValue());
            case 16:
                return zzbav.zzcg(((Integer) obj).intValue());
            case 17:
                return zzbav.zzr(((Long) obj).longValue());
            case 18:
                return obj instanceof zzbbr ? zzbav.zzcj(((zzbbr) obj).zzhq()) : zzbav.zzcj(((Integer) obj).intValue());
            default:
                throw new RuntimeException("There is no way to get here, but the compiler thinks otherwise.");
        }
    }

    private static boolean zzb(Entry<FieldDescriptorType, Object> entry) {
        zzbbi com_google_android_gms_internal_ads_zzbbi = (zzbbi) entry.getKey();
        if (com_google_android_gms_internal_ads_zzbbi.zzacz() == zzbex.MESSAGE) {
            if (com_google_android_gms_internal_ads_zzbbi.zzada()) {
                for (zzbcu isInitialized : (List) entry.getValue()) {
                    if (!isInitialized.isInitialized()) {
                        return false;
                    }
                }
            }
            Object value = entry.getValue();
            if (value instanceof zzbcu) {
                if (!((zzbcu) value).isInitialized()) {
                    return false;
                }
            } else if (value instanceof zzbbx) {
                return true;
            } else {
                throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
            }
        }
        return true;
    }

    private final void zzc(Entry<FieldDescriptorType, Object> entry) {
        Comparable comparable = (zzbbi) entry.getKey();
        Object value = entry.getValue();
        if (value instanceof zzbbx) {
            value = zzbbx.zzadu();
        }
        Object zza;
        if (comparable.zzada()) {
            zza = zza((zzbbi) comparable);
            if (zza == null) {
                zza = new ArrayList();
            }
            for (Object zzp : (List) value) {
                ((List) zza).add(zzp(zzp));
            }
            this.zzdqx.zza(comparable, zza);
        } else if (comparable.zzacz() == zzbex.MESSAGE) {
            zza = zza((zzbbi) comparable);
            if (zza == null) {
                this.zzdqx.zza(comparable, zzp(value));
            } else {
                this.zzdqx.zza(comparable, zza instanceof zzbdb ? comparable.zza((zzbdb) zza, (zzbdb) value) : comparable.zza(((zzbcu) zza).zzade(), (zzbcu) value).zzadk());
            }
        } else {
            this.zzdqx.zza(comparable, zzp(value));
        }
    }

    private static int zzd(Entry<FieldDescriptorType, Object> entry) {
        zzbbi com_google_android_gms_internal_ads_zzbbi = (zzbbi) entry.getKey();
        Object value = entry.getValue();
        return (com_google_android_gms_internal_ads_zzbbi.zzacz() != zzbex.MESSAGE || com_google_android_gms_internal_ads_zzbbi.zzada() || com_google_android_gms_internal_ads_zzbbi.zzadb()) ? zzb(com_google_android_gms_internal_ads_zzbbi, value) : value instanceof zzbbx ? zzbav.zzb(((zzbbi) entry.getKey()).zzhq(), (zzbbx) value) : zzbav.zzb(((zzbbi) entry.getKey()).zzhq(), (zzbcu) value);
    }

    private static Object zzp(Object obj) {
        if (obj instanceof zzbdb) {
            return ((zzbdb) obj).zzaek();
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
        zzbbg com_google_android_gms_internal_ads_zzbbg = new zzbbg();
        for (int i = 0; i < this.zzdqx.zzafs(); i++) {
            Entry zzcy = this.zzdqx.zzcy(i);
            com_google_android_gms_internal_ads_zzbbg.zza((zzbbi) zzcy.getKey(), zzcy.getValue());
        }
        for (Entry entry : this.zzdqx.zzaft()) {
            com_google_android_gms_internal_ads_zzbbg.zza((zzbbi) entry.getKey(), entry.getValue());
        }
        com_google_android_gms_internal_ads_zzbbg.zzdqz = this.zzdqz;
        return com_google_android_gms_internal_ads_zzbbg;
    }

    final Iterator<Entry<FieldDescriptorType, Object>> descendingIterator() {
        return this.zzdqz ? new zzbca(this.zzdqx.zzafu().iterator()) : this.zzdqx.zzafu().iterator();
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzbbg)) {
            return false;
        }
        return this.zzdqx.equals(((zzbbg) obj).zzdqx);
    }

    public final int hashCode() {
        return this.zzdqx.hashCode();
    }

    final boolean isEmpty() {
        return this.zzdqx.isEmpty();
    }

    public final boolean isImmutable() {
        return this.zzdqy;
    }

    public final boolean isInitialized() {
        for (int i = 0; i < this.zzdqx.zzafs(); i++) {
            if (!zzb(this.zzdqx.zzcy(i))) {
                return false;
            }
        }
        for (Entry zzb : this.zzdqx.zzaft()) {
            if (!zzb(zzb)) {
                return false;
            }
        }
        return true;
    }

    public final Iterator<Entry<FieldDescriptorType, Object>> iterator() {
        return this.zzdqz ? new zzbca(this.zzdqx.entrySet().iterator()) : this.zzdqx.entrySet().iterator();
    }

    public final void zza(zzbbg<FieldDescriptorType> com_google_android_gms_internal_ads_zzbbg_FieldDescriptorType) {
        for (int i = 0; i < com_google_android_gms_internal_ads_zzbbg_FieldDescriptorType.zzdqx.zzafs(); i++) {
            zzc(com_google_android_gms_internal_ads_zzbbg_FieldDescriptorType.zzdqx.zzcy(i));
        }
        for (Entry zzc : com_google_android_gms_internal_ads_zzbbg_FieldDescriptorType.zzdqx.zzaft()) {
            zzc(zzc);
        }
    }

    public final void zzaaz() {
        if (!this.zzdqy) {
            this.zzdqx.zzaaz();
            this.zzdqy = true;
        }
    }

    public final int zzacw() {
        int i = 0;
        for (int i2 = 0; i2 < this.zzdqx.zzafs(); i2++) {
            Entry zzcy = this.zzdqx.zzcy(i2);
            i += zzb((zzbbi) zzcy.getKey(), zzcy.getValue());
        }
        for (Entry entry : this.zzdqx.zzaft()) {
            i += zzb((zzbbi) entry.getKey(), entry.getValue());
        }
        return i;
    }

    public final int zzacx() {
        int i = 0;
        int i2 = 0;
        while (i < this.zzdqx.zzafs()) {
            i2 += zzd(this.zzdqx.zzcy(i));
            i++;
        }
        for (Entry zzd : this.zzdqx.zzaft()) {
            i2 += zzd(zzd);
        }
        return i2;
    }
}
