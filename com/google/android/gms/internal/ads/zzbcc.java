package com.google.android.gms.internal.ads;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.RandomAccess;

public final class zzbcc extends zzbab<String> implements zzbcd, RandomAccess {
    private static final zzbcc zzdvn;
    private static final zzbcd zzdvo = zzdvn;
    private final List<Object> zzdvp;

    static {
        zzbab com_google_android_gms_internal_ads_zzbcc = new zzbcc();
        zzdvn = com_google_android_gms_internal_ads_zzbcc;
        com_google_android_gms_internal_ads_zzbcc.zzaaz();
    }

    public zzbcc() {
        this(10);
    }

    public zzbcc(int i) {
        this(new ArrayList(i));
    }

    private zzbcc(ArrayList<Object> arrayList) {
        this.zzdvp = arrayList;
    }

    private static String zzq(Object obj) {
        return obj instanceof String ? (String) obj : obj instanceof zzbah ? ((zzbah) obj).zzabd() : zzbbq.zzt((byte[]) obj);
    }

    public final /* synthetic */ void add(int i, Object obj) {
        String str = (String) obj;
        zzaba();
        this.zzdvp.add(i, str);
        this.modCount++;
    }

    public final boolean addAll(int i, Collection<? extends String> collection) {
        Collection zzadw;
        zzaba();
        if (collection instanceof zzbcd) {
            zzadw = ((zzbcd) collection).zzadw();
        }
        boolean addAll = this.zzdvp.addAll(i, zzadw);
        this.modCount++;
        return addAll;
    }

    public final boolean addAll(Collection<? extends String> collection) {
        return addAll(size(), collection);
    }

    public final void clear() {
        zzaba();
        this.zzdvp.clear();
        this.modCount++;
    }

    public final /* bridge */ /* synthetic */ boolean equals(Object obj) {
        return super.equals(obj);
    }

    public final /* synthetic */ Object get(int i) {
        Object obj = this.zzdvp.get(i);
        if (obj instanceof String) {
            return (String) obj;
        }
        String zzabd;
        if (obj instanceof zzbah) {
            zzbah com_google_android_gms_internal_ads_zzbah = (zzbah) obj;
            zzabd = com_google_android_gms_internal_ads_zzbah.zzabd();
            if (com_google_android_gms_internal_ads_zzbah.zzabe()) {
                this.zzdvp.set(i, zzabd);
            }
            return zzabd;
        }
        byte[] bArr = (byte[]) obj;
        zzabd = zzbbq.zzt(bArr);
        if (zzbbq.zzs(bArr)) {
            this.zzdvp.set(i, zzabd);
        }
        return zzabd;
    }

    public final /* bridge */ /* synthetic */ int hashCode() {
        return super.hashCode();
    }

    public final /* synthetic */ Object remove(int i) {
        zzaba();
        Object remove = this.zzdvp.remove(i);
        this.modCount++;
        return zzq(remove);
    }

    public final /* bridge */ /* synthetic */ boolean removeAll(Collection collection) {
        return super.removeAll(collection);
    }

    public final /* bridge */ /* synthetic */ boolean retainAll(Collection collection) {
        return super.retainAll(collection);
    }

    public final /* synthetic */ Object set(int i, Object obj) {
        String str = (String) obj;
        zzaba();
        return zzq(this.zzdvp.set(i, str));
    }

    public final int size() {
        return this.zzdvp.size();
    }

    public final /* bridge */ /* synthetic */ boolean zzaay() {
        return super.zzaay();
    }

    public final List<?> zzadw() {
        return Collections.unmodifiableList(this.zzdvp);
    }

    public final zzbcd zzadx() {
        return zzaay() ? new zzbeh(this) : this;
    }

    public final void zzap(zzbah com_google_android_gms_internal_ads_zzbah) {
        zzaba();
        this.zzdvp.add(com_google_android_gms_internal_ads_zzbah);
        this.modCount++;
    }

    public final /* synthetic */ zzbbt zzbm(int i) {
        if (i < size()) {
            throw new IllegalArgumentException();
        }
        ArrayList arrayList = new ArrayList(i);
        arrayList.addAll(this.zzdvp);
        return new zzbcc(arrayList);
    }

    public final Object zzcp(int i) {
        return this.zzdvp.get(i);
    }
}
