package com.google.android.gms.internal.ads;

public final class zzbfe implements Cloneable {
    private static final zzbff zzebm = new zzbff();
    private int mSize;
    private boolean zzebn;
    private int[] zzebo;
    private zzbff[] zzebp;

    zzbfe() {
        this(10);
    }

    private zzbfe(int i) {
        this.zzebn = false;
        int idealIntArraySize = idealIntArraySize(i);
        this.zzebo = new int[idealIntArraySize];
        this.zzebp = new zzbff[idealIntArraySize];
        this.mSize = 0;
    }

    private static int idealIntArraySize(int i) {
        int i2 = i << 2;
        for (int i3 = 4; i3 < 32; i3++) {
            if (i2 <= (1 << i3) - 12) {
                i2 = (1 << i3) - 12;
                break;
            }
        }
        return i2 / 4;
    }

    private final int zzdh(int i) {
        int i2 = 0;
        int i3 = this.mSize - 1;
        while (i2 <= i3) {
            int i4 = (i2 + i3) >>> 1;
            int i5 = this.zzebo[i4];
            if (i5 < i) {
                i2 = i4 + 1;
            } else if (i5 <= i) {
                return i4;
            } else {
                i3 = i4 - 1;
            }
        }
        return i2 ^ -1;
    }

    public final /* synthetic */ Object clone() throws CloneNotSupportedException {
        int i = this.mSize;
        zzbfe com_google_android_gms_internal_ads_zzbfe = new zzbfe(i);
        System.arraycopy(this.zzebo, 0, com_google_android_gms_internal_ads_zzbfe.zzebo, 0, i);
        for (int i2 = 0; i2 < i; i2++) {
            if (this.zzebp[i2] != null) {
                com_google_android_gms_internal_ads_zzbfe.zzebp[i2] = (zzbff) this.zzebp[i2].clone();
            }
        }
        com_google_android_gms_internal_ads_zzbfe.mSize = i;
        return com_google_android_gms_internal_ads_zzbfe;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzbfe)) {
            return false;
        }
        zzbfe com_google_android_gms_internal_ads_zzbfe = (zzbfe) obj;
        if (this.mSize != com_google_android_gms_internal_ads_zzbfe.mSize) {
            return false;
        }
        int i;
        boolean z;
        int[] iArr = this.zzebo;
        int[] iArr2 = com_google_android_gms_internal_ads_zzbfe.zzebo;
        int i2 = this.mSize;
        for (i = 0; i < i2; i++) {
            if (iArr[i] != iArr2[i]) {
                z = false;
                break;
            }
        }
        z = true;
        if (z) {
            zzbff[] com_google_android_gms_internal_ads_zzbffArr = this.zzebp;
            zzbff[] com_google_android_gms_internal_ads_zzbffArr2 = com_google_android_gms_internal_ads_zzbfe.zzebp;
            i2 = this.mSize;
            for (i = 0; i < i2; i++) {
                if (!com_google_android_gms_internal_ads_zzbffArr[i].equals(com_google_android_gms_internal_ads_zzbffArr2[i])) {
                    z = false;
                    break;
                }
            }
            z = true;
            if (z) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        int i = 17;
        for (int i2 = 0; i2 < this.mSize; i2++) {
            i = (((i * 31) + this.zzebo[i2]) * 31) + this.zzebp[i2].hashCode();
        }
        return i;
    }

    final int size() {
        return this.mSize;
    }

    final void zza(int i, zzbff com_google_android_gms_internal_ads_zzbff) {
        int zzdh = zzdh(i);
        if (zzdh >= 0) {
            this.zzebp[zzdh] = com_google_android_gms_internal_ads_zzbff;
            return;
        }
        zzdh ^= -1;
        if (zzdh >= this.mSize || this.zzebp[zzdh] != zzebm) {
            if (this.mSize >= this.zzebo.length) {
                int idealIntArraySize = idealIntArraySize(this.mSize + 1);
                Object obj = new int[idealIntArraySize];
                Object obj2 = new zzbff[idealIntArraySize];
                System.arraycopy(this.zzebo, 0, obj, 0, this.zzebo.length);
                System.arraycopy(this.zzebp, 0, obj2, 0, this.zzebp.length);
                this.zzebo = obj;
                this.zzebp = obj2;
            }
            if (this.mSize - zzdh != 0) {
                System.arraycopy(this.zzebo, zzdh, this.zzebo, zzdh + 1, this.mSize - zzdh);
                System.arraycopy(this.zzebp, zzdh, this.zzebp, zzdh + 1, this.mSize - zzdh);
            }
            this.zzebo[zzdh] = i;
            this.zzebp[zzdh] = com_google_android_gms_internal_ads_zzbff;
            this.mSize++;
            return;
        }
        this.zzebo[zzdh] = i;
        this.zzebp[zzdh] = com_google_android_gms_internal_ads_zzbff;
    }

    final zzbff zzdf(int i) {
        int zzdh = zzdh(i);
        return (zzdh < 0 || this.zzebp[zzdh] == zzebm) ? null : this.zzebp[zzdh];
    }

    final zzbff zzdg(int i) {
        return this.zzebp[i];
    }
}
