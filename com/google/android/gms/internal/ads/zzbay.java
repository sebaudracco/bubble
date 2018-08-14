package com.google.android.gms.internal.ads;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

final class zzbay extends zzbab<Double> implements zzbbt<Double>, RandomAccess {
    private static final zzbay zzdqo;
    private int size;
    private double[] zzdqp;

    static {
        zzbab com_google_android_gms_internal_ads_zzbay = new zzbay();
        zzdqo = com_google_android_gms_internal_ads_zzbay;
        com_google_android_gms_internal_ads_zzbay.zzaaz();
    }

    zzbay() {
        this(new double[10], 0);
    }

    private zzbay(double[] dArr, int i) {
        this.zzdqp = dArr;
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

    private final void zzc(int i, double d) {
        zzaba();
        if (i < 0 || i > this.size) {
            throw new IndexOutOfBoundsException(zzbl(i));
        }
        if (this.size < this.zzdqp.length) {
            System.arraycopy(this.zzdqp, i, this.zzdqp, i + 1, this.size - i);
        } else {
            Object obj = new double[(((this.size * 3) / 2) + 1)];
            System.arraycopy(this.zzdqp, 0, obj, 0, i);
            System.arraycopy(this.zzdqp, i, obj, i + 1, this.size - i);
            this.zzdqp = obj;
        }
        this.zzdqp[i] = d;
        this.size++;
        this.modCount++;
    }

    public final /* synthetic */ void add(int i, Object obj) {
        zzc(i, ((Double) obj).doubleValue());
    }

    public final boolean addAll(Collection<? extends Double> collection) {
        zzaba();
        zzbbq.checkNotNull(collection);
        if (!(collection instanceof zzbay)) {
            return super.addAll(collection);
        }
        zzbay com_google_android_gms_internal_ads_zzbay = (zzbay) collection;
        if (com_google_android_gms_internal_ads_zzbay.size == 0) {
            return false;
        }
        if (Integer.MAX_VALUE - this.size < com_google_android_gms_internal_ads_zzbay.size) {
            throw new OutOfMemoryError();
        }
        int i = this.size + com_google_android_gms_internal_ads_zzbay.size;
        if (i > this.zzdqp.length) {
            this.zzdqp = Arrays.copyOf(this.zzdqp, i);
        }
        System.arraycopy(com_google_android_gms_internal_ads_zzbay.zzdqp, 0, this.zzdqp, this.size, com_google_android_gms_internal_ads_zzbay.size);
        this.size = i;
        this.modCount++;
        return true;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzbay)) {
            return super.equals(obj);
        }
        zzbay com_google_android_gms_internal_ads_zzbay = (zzbay) obj;
        if (this.size != com_google_android_gms_internal_ads_zzbay.size) {
            return false;
        }
        double[] dArr = com_google_android_gms_internal_ads_zzbay.zzdqp;
        for (int i = 0; i < this.size; i++) {
            if (this.zzdqp[i] != dArr[i]) {
                return false;
            }
        }
        return true;
    }

    public final /* synthetic */ Object get(int i) {
        zzbk(i);
        return Double.valueOf(this.zzdqp[i]);
    }

    public final int hashCode() {
        int i = 1;
        for (int i2 = 0; i2 < this.size; i2++) {
            i = (i * 31) + zzbbq.zzv(Double.doubleToLongBits(this.zzdqp[i2]));
        }
        return i;
    }

    public final /* synthetic */ Object remove(int i) {
        zzaba();
        zzbk(i);
        double d = this.zzdqp[i];
        if (i < this.size - 1) {
            System.arraycopy(this.zzdqp, i + 1, this.zzdqp, i, this.size - i);
        }
        this.size--;
        this.modCount++;
        return Double.valueOf(d);
    }

    public final boolean remove(Object obj) {
        zzaba();
        for (int i = 0; i < this.size; i++) {
            if (obj.equals(Double.valueOf(this.zzdqp[i]))) {
                System.arraycopy(this.zzdqp, i + 1, this.zzdqp, i, this.size - i);
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
        System.arraycopy(this.zzdqp, i2, this.zzdqp, i, this.size - i2);
        this.size -= i2 - i;
        this.modCount++;
    }

    public final /* synthetic */ Object set(int i, Object obj) {
        double doubleValue = ((Double) obj).doubleValue();
        zzaba();
        zzbk(i);
        double d = this.zzdqp[i];
        this.zzdqp[i] = doubleValue;
        return Double.valueOf(d);
    }

    public final int size() {
        return this.size;
    }

    public final /* synthetic */ zzbbt zzbm(int i) {
        if (i >= this.size) {
            return new zzbay(Arrays.copyOf(this.zzdqp, i), this.size);
        }
        throw new IllegalArgumentException();
    }

    public final void zzd(double d) {
        zzc(this.size, d);
    }
}
