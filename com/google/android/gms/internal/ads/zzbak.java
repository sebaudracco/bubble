package com.google.android.gms.internal.ads;

final class zzbak extends zzbao {
    private final int zzdpt;
    private final int zzdpu;

    zzbak(byte[] bArr, int i, int i2) {
        super(bArr);
        zzbah.zzd(i, i + i2, bArr.length);
        this.zzdpt = i;
        this.zzdpu = i2;
    }

    public final int size() {
        return this.zzdpu;
    }

    protected final void zza(byte[] bArr, int i, int i2, int i3) {
        System.arraycopy(this.zzdpw, zzabh(), bArr, 0, i3);
    }

    protected final int zzabh() {
        return this.zzdpt;
    }

    public final byte zzbn(int i) {
        int size = size();
        if (((size - (i + 1)) | i) >= 0) {
            return this.zzdpw[this.zzdpt + i];
        }
        if (i < 0) {
            throw new ArrayIndexOutOfBoundsException("Index < 0: " + i);
        }
        throw new ArrayIndexOutOfBoundsException("Index > length: " + i + ", " + size);
    }
}
