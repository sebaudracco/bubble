package com.google.android.gms.internal.ads;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

final class zzbaf extends zzbab<Boolean> implements zzbbt<Boolean>, RandomAccess {
    private static final zzbaf zzdpo;
    private int size;
    private boolean[] zzdpp;

    static {
        zzbab com_google_android_gms_internal_ads_zzbaf = new zzbaf();
        zzdpo = com_google_android_gms_internal_ads_zzbaf;
        com_google_android_gms_internal_ads_zzbaf.zzaaz();
    }

    zzbaf() {
        this(new boolean[10], 0);
    }

    private zzbaf(boolean[] zArr, int i) {
        this.zzdpp = zArr;
        this.size = i;
    }

    private final void zzbk(int i) {
        if (i < 0 || i >= this.size) {
            throw new IndexOutOfBoundsException(zzbl(i));
        }
    }

    private final String zzbl(int i) {
        return "Index:" + i + ", Size:" + this.size;
    }

    private final void zze(int i, boolean z) {
        zzaba();
        if (i < 0 || i > this.size) {
            throw new IndexOutOfBoundsException(zzbl(i));
        }
        if (this.size < this.zzdpp.length) {
            System.arraycopy(this.zzdpp, i, this.zzdpp, i + 1, this.size - i);
        } else {
            Object obj = new boolean[(((this.size * 3) / 2) + 1)];
            System.arraycopy(this.zzdpp, 0, obj, 0, i);
            System.arraycopy(this.zzdpp, i, obj, i + 1, this.size - i);
            this.zzdpp = obj;
        }
        this.zzdpp[i] = z;
        this.size++;
        this.modCount++;
    }

    public final /* synthetic */ void add(int i, Object obj) {
        zze(i, ((Boolean) obj).booleanValue());
    }

    public final boolean addAll(Collection<? extends Boolean> collection) {
        zzaba();
        zzbbq.checkNotNull(collection);
        if (!(collection instanceof zzbaf)) {
            return super.addAll(collection);
        }
        zzbaf com_google_android_gms_internal_ads_zzbaf = (zzbaf) collection;
        if (com_google_android_gms_internal_ads_zzbaf.size == 0) {
            return false;
        }
        if (Integer.MAX_VALUE - this.size < com_google_android_gms_internal_ads_zzbaf.size) {
            throw new OutOfMemoryError();
        }
        int i = this.size + com_google_android_gms_internal_ads_zzbaf.size;
        if (i > this.zzdpp.length) {
            this.zzdpp = Arrays.copyOf(this.zzdpp, i);
        }
        System.arraycopy(com_google_android_gms_internal_ads_zzbaf.zzdpp, 0, this.zzdpp, this.size, com_google_android_gms_internal_ads_zzbaf.size);
        this.size = i;
        this.modCount++;
        return true;
    }

    public final void addBoolean(boolean z) {
        zze(this.size, z);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzbaf)) {
            return super.equals(obj);
        }
        zzbaf com_google_android_gms_internal_ads_zzbaf = (zzbaf) obj;
        if (this.size != com_google_android_gms_internal_ads_zzbaf.size) {
            return false;
        }
        boolean[] zArr = com_google_android_gms_internal_ads_zzbaf.zzdpp;
        for (int i = 0; i < this.size; i++) {
            if (this.zzdpp[i] != zArr[i]) {
                return false;
            }
        }
        return true;
    }

    public final /* synthetic */ Object get(int i) {
        zzbk(i);
        return Boolean.valueOf(this.zzdpp[i]);
    }

    public final int hashCode() {
        int i = 1;
        for (int i2 = 0; i2 < this.size; i2++) {
            i = (i * 31) + zzbbq.zzar(this.zzdpp[i2]);
        }
        return i;
    }

    public final /* synthetic */ Object remove(int i) {
        zzaba();
        zzbk(i);
        boolean z = this.zzdpp[i];
        if (i < this.size - 1) {
            System.arraycopy(this.zzdpp, i + 1, this.zzdpp, i, this.size - i);
        }
        this.size--;
        this.modCount++;
        return Boolean.valueOf(z);
    }

    public final boolean remove(Object obj) {
        zzaba();
        for (int i = 0; i < this.size; i++) {
            if (obj.equals(Boolean.valueOf(this.zzdpp[i]))) {
                System.arraycopy(this.zzdpp, i + 1, this.zzdpp, i, this.size - i);
                this.size--;
                this.modCount++;
                return true;
            }
        }
        return false;
    }

    protected final void removeRange(int i, int i2) {
        zzaba();
        if (i2 < i) {
            throw new IndexOutOfBoundsException("toIndex < fromIndex");
        }
        System.arraycopy(this.zzdpp, i2, this.zzdpp, i, this.size - i2);
        this.size -= i2 - i;
        this.modCount++;
    }

    public final /* synthetic */ Object set(int i, Object obj) {
        boolean booleanValue = ((Boolean) obj).booleanValue();
        zzaba();
        zzbk(i);
        boolean z = this.zzdpp[i];
        this.zzdpp[i] = booleanValue;
        return Boolean.valueOf(z);
    }

    public final int size() {
        return this.size;
    }

    public final /* synthetic */ zzbbt zzbm(int i) {
        if (i >= this.size) {
            return new zzbaf(Arrays.copyOf(this.zzdpp, i), this.size);
        }
        throw new IllegalArgumentException();
    }
}
