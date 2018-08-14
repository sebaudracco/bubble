package com.google.android.gms.internal.ads;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

final class zzbbm extends zzbab<Float> implements zzbbt<Float>, RandomAccess {
    private static final zzbbm zzdtq;
    private int size;
    private float[] zzdtr;

    static {
        zzbab com_google_android_gms_internal_ads_zzbbm = new zzbbm();
        zzdtq = com_google_android_gms_internal_ads_zzbbm;
        com_google_android_gms_internal_ads_zzbbm.zzaaz();
    }

    zzbbm() {
        this(new float[10], 0);
    }

    private zzbbm(float[] fArr, int i) {
        this.zzdtr = fArr;
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

    private final void zzc(int i, float f) {
        zzaba();
        if (i < 0 || i > this.size) {
            throw new IndexOutOfBoundsException(zzbl(i));
        }
        if (this.size < this.zzdtr.length) {
            System.arraycopy(this.zzdtr, i, this.zzdtr, i + 1, this.size - i);
        } else {
            Object obj = new float[(((this.size * 3) / 2) + 1)];
            System.arraycopy(this.zzdtr, 0, obj, 0, i);
            System.arraycopy(this.zzdtr, i, obj, i + 1, this.size - i);
            this.zzdtr = obj;
        }
        this.zzdtr[i] = f;
        this.size++;
        this.modCount++;
    }

    public final /* synthetic */ void add(int i, Object obj) {
        zzc(i, ((Float) obj).floatValue());
    }

    public final boolean addAll(Collection<? extends Float> collection) {
        zzaba();
        zzbbq.checkNotNull(collection);
        if (!(collection instanceof zzbbm)) {
            return super.addAll(collection);
        }
        zzbbm com_google_android_gms_internal_ads_zzbbm = (zzbbm) collection;
        if (com_google_android_gms_internal_ads_zzbbm.size == 0) {
            return false;
        }
        if (Integer.MAX_VALUE - this.size < com_google_android_gms_internal_ads_zzbbm.size) {
            throw new OutOfMemoryError();
        }
        int i = this.size + com_google_android_gms_internal_ads_zzbbm.size;
        if (i > this.zzdtr.length) {
            this.zzdtr = Arrays.copyOf(this.zzdtr, i);
        }
        System.arraycopy(com_google_android_gms_internal_ads_zzbbm.zzdtr, 0, this.zzdtr, this.size, com_google_android_gms_internal_ads_zzbbm.size);
        this.size = i;
        this.modCount++;
        return true;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzbbm)) {
            return super.equals(obj);
        }
        zzbbm com_google_android_gms_internal_ads_zzbbm = (zzbbm) obj;
        if (this.size != com_google_android_gms_internal_ads_zzbbm.size) {
            return false;
        }
        float[] fArr = com_google_android_gms_internal_ads_zzbbm.zzdtr;
        for (int i = 0; i < this.size; i++) {
            if (this.zzdtr[i] != fArr[i]) {
                return false;
            }
        }
        return true;
    }

    public final /* synthetic */ Object get(int i) {
        zzbk(i);
        return Float.valueOf(this.zzdtr[i]);
    }

    public final int hashCode() {
        int i = 1;
        for (int i2 = 0; i2 < this.size; i2++) {
            i = (i * 31) + Float.floatToIntBits(this.zzdtr[i2]);
        }
        return i;
    }

    public final /* synthetic */ Object remove(int i) {
        zzaba();
        zzbk(i);
        float f = this.zzdtr[i];
        if (i < this.size - 1) {
            System.arraycopy(this.zzdtr, i + 1, this.zzdtr, i, this.size - i);
        }
        this.size--;
        this.modCount++;
        return Float.valueOf(f);
    }

    public final boolean remove(Object obj) {
        zzaba();
        for (int i = 0; i < this.size; i++) {
            if (obj.equals(Float.valueOf(this.zzdtr[i]))) {
                System.arraycopy(this.zzdtr, i + 1, this.zzdtr, i, this.size - i);
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
        System.arraycopy(this.zzdtr, i2, this.zzdtr, i, this.size - i2);
        this.size -= i2 - i;
        this.modCount++;
    }

    public final /* synthetic */ Object set(int i, Object obj) {
        float floatValue = ((Float) obj).floatValue();
        zzaba();
        zzbk(i);
        float f = this.zzdtr[i];
        this.zzdtr[i] = floatValue;
        return Float.valueOf(f);
    }

    public final int size() {
        return this.size;
    }

    public final /* synthetic */ zzbbt zzbm(int i) {
        if (i >= this.size) {
            return new zzbbm(Arrays.copyOf(this.zzdtr, i), this.size);
        }
        throw new IllegalArgumentException();
    }

    public final void zzd(float f) {
        zzc(this.size, f);
    }
}
