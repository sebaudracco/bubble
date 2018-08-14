package com.google.android.gms.internal.wearable;

public final class zzp implements Cloneable {
    private static final zzq zzhe = new zzq();
    private int mSize;
    private boolean zzhf;
    private int[] zzhg;
    private zzq[] zzhh;

    zzp() {
        this(10);
    }

    private zzp(int i) {
        this.zzhf = false;
        int idealIntArraySize = idealIntArraySize(i);
        this.zzhg = new int[idealIntArraySize];
        this.zzhh = new zzq[idealIntArraySize];
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

    private final int zzq(int i) {
        int i2 = 0;
        int i3 = this.mSize - 1;
        while (i2 <= i3) {
            int i4 = (i2 + i3) >>> 1;
            int i5 = this.zzhg[i4];
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
        zzp com_google_android_gms_internal_wearable_zzp = new zzp(i);
        System.arraycopy(this.zzhg, 0, com_google_android_gms_internal_wearable_zzp.zzhg, 0, i);
        for (int i2 = 0; i2 < i; i2++) {
            if (this.zzhh[i2] != null) {
                com_google_android_gms_internal_wearable_zzp.zzhh[i2] = (zzq) this.zzhh[i2].clone();
            }
        }
        com_google_android_gms_internal_wearable_zzp.mSize = i;
        return com_google_android_gms_internal_wearable_zzp;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzp)) {
            return false;
        }
        zzp com_google_android_gms_internal_wearable_zzp = (zzp) obj;
        if (this.mSize != com_google_android_gms_internal_wearable_zzp.mSize) {
            return false;
        }
        int i;
        boolean z;
        int[] iArr = this.zzhg;
        int[] iArr2 = com_google_android_gms_internal_wearable_zzp.zzhg;
        int i2 = this.mSize;
        for (i = 0; i < i2; i++) {
            if (iArr[i] != iArr2[i]) {
                z = false;
                break;
            }
        }
        z = true;
        if (z) {
            zzq[] com_google_android_gms_internal_wearable_zzqArr = this.zzhh;
            zzq[] com_google_android_gms_internal_wearable_zzqArr2 = com_google_android_gms_internal_wearable_zzp.zzhh;
            i2 = this.mSize;
            for (i = 0; i < i2; i++) {
                if (!com_google_android_gms_internal_wearable_zzqArr[i].equals(com_google_android_gms_internal_wearable_zzqArr2[i])) {
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
            i = (((i * 31) + this.zzhg[i2]) * 31) + this.zzhh[i2].hashCode();
        }
        return i;
    }

    public final boolean isEmpty() {
        return this.mSize == 0;
    }

    final int size() {
        return this.mSize;
    }

    final void zza(int i, zzq com_google_android_gms_internal_wearable_zzq) {
        int zzq = zzq(i);
        if (zzq >= 0) {
            this.zzhh[zzq] = com_google_android_gms_internal_wearable_zzq;
            return;
        }
        zzq ^= -1;
        if (zzq >= this.mSize || this.zzhh[zzq] != zzhe) {
            if (this.mSize >= this.zzhg.length) {
                int idealIntArraySize = idealIntArraySize(this.mSize + 1);
                Object obj = new int[idealIntArraySize];
                Object obj2 = new zzq[idealIntArraySize];
                System.arraycopy(this.zzhg, 0, obj, 0, this.zzhg.length);
                System.arraycopy(this.zzhh, 0, obj2, 0, this.zzhh.length);
                this.zzhg = obj;
                this.zzhh = obj2;
            }
            if (this.mSize - zzq != 0) {
                System.arraycopy(this.zzhg, zzq, this.zzhg, zzq + 1, this.mSize - zzq);
                System.arraycopy(this.zzhh, zzq, this.zzhh, zzq + 1, this.mSize - zzq);
            }
            this.zzhg[zzq] = i;
            this.zzhh[zzq] = com_google_android_gms_internal_wearable_zzq;
            this.mSize++;
            return;
        }
        this.zzhg[zzq] = i;
        this.zzhh[zzq] = com_google_android_gms_internal_wearable_zzq;
    }

    final zzq zzo(int i) {
        int zzq = zzq(i);
        return (zzq < 0 || this.zzhh[zzq] == zzhe) ? null : this.zzhh[zzq];
    }

    final zzq zzp(int i) {
        return this.zzhh[i];
    }
}
