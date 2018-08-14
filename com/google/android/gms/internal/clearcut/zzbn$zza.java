package com.google.android.gms.internal.clearcut;

import java.io.IOException;

class zzbn$zza extends zzbn {
    private final byte[] buffer;
    private final int limit;
    private final int offset;
    private int position;

    zzbn$zza(byte[] bArr, int i, int i2) {
        super(null);
        if (bArr == null) {
            throw new NullPointerException("buffer");
        } else if (((i | i2) | (bArr.length - (i + i2))) < 0) {
            throw new IllegalArgumentException(String.format("Array range is invalid. Buffer.length=%d, offset=%d, length=%d", new Object[]{Integer.valueOf(bArr.length), Integer.valueOf(i), Integer.valueOf(i2)}));
        } else {
            this.buffer = bArr;
            this.offset = i;
            this.position = i;
            this.limit = i + i2;
        }
    }

    public void flush() {
    }

    public final void write(byte[] bArr, int i, int i2) throws IOException {
        try {
            System.arraycopy(bArr, i, this.buffer, this.position, i2);
            this.position += i2;
        } catch (Throwable e) {
            throw new zzbn$zzc(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.position), Integer.valueOf(this.limit), Integer.valueOf(i2)}), e);
        }
    }

    public final void zza(byte b) throws IOException {
        try {
            byte[] bArr = this.buffer;
            int i = this.position;
            this.position = i + 1;
            bArr[i] = b;
        } catch (Throwable e) {
            throw new zzbn$zzc(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.position), Integer.valueOf(this.limit), Integer.valueOf(1)}), e);
        }
    }

    public final void zza(int i, long j) throws IOException {
        zzb(i, 0);
        zzb(j);
    }

    public final void zza(int i, zzbb com_google_android_gms_internal_clearcut_zzbb) throws IOException {
        zzb(i, 2);
        zza(com_google_android_gms_internal_clearcut_zzbb);
    }

    public final void zza(int i, zzdo com_google_android_gms_internal_clearcut_zzdo) throws IOException {
        zzb(i, 2);
        zzb(com_google_android_gms_internal_clearcut_zzdo);
    }

    final void zza(int i, zzdo com_google_android_gms_internal_clearcut_zzdo, zzef com_google_android_gms_internal_clearcut_zzef) throws IOException {
        zzb(i, 2);
        zzas com_google_android_gms_internal_clearcut_zzas = (zzas) com_google_android_gms_internal_clearcut_zzdo;
        int zzs = com_google_android_gms_internal_clearcut_zzas.zzs();
        if (zzs == -1) {
            zzs = com_google_android_gms_internal_clearcut_zzef.zzm(com_google_android_gms_internal_clearcut_zzas);
            com_google_android_gms_internal_clearcut_zzas.zzf(zzs);
        }
        zzo(zzs);
        com_google_android_gms_internal_clearcut_zzef.zza(com_google_android_gms_internal_clearcut_zzdo, this.zzfz);
    }

    public final void zza(int i, String str) throws IOException {
        zzb(i, 2);
        zzg(str);
    }

    public final void zza(zzbb com_google_android_gms_internal_clearcut_zzbb) throws IOException {
        zzo(com_google_android_gms_internal_clearcut_zzbb.size());
        com_google_android_gms_internal_clearcut_zzbb.zza((zzba) this);
    }

    final void zza(zzdo com_google_android_gms_internal_clearcut_zzdo, zzef com_google_android_gms_internal_clearcut_zzef) throws IOException {
        zzas com_google_android_gms_internal_clearcut_zzas = (zzas) com_google_android_gms_internal_clearcut_zzdo;
        int zzs = com_google_android_gms_internal_clearcut_zzas.zzs();
        if (zzs == -1) {
            zzs = com_google_android_gms_internal_clearcut_zzef.zzm(com_google_android_gms_internal_clearcut_zzas);
            com_google_android_gms_internal_clearcut_zzas.zzf(zzs);
        }
        zzo(zzs);
        com_google_android_gms_internal_clearcut_zzef.zza(com_google_android_gms_internal_clearcut_zzdo, this.zzfz);
    }

    public final void zza(byte[] bArr, int i, int i2) throws IOException {
        write(bArr, i, i2);
    }

    public final int zzag() {
        return this.limit - this.position;
    }

    public final int zzai() {
        return this.position - this.offset;
    }

    public final void zzb(int i, int i2) throws IOException {
        zzo((i << 3) | i2);
    }

    public final void zzb(int i, zzbb com_google_android_gms_internal_clearcut_zzbb) throws IOException {
        zzb(1, 3);
        zzd(2, i);
        zza(3, com_google_android_gms_internal_clearcut_zzbb);
        zzb(1, 4);
    }

    public final void zzb(int i, zzdo com_google_android_gms_internal_clearcut_zzdo) throws IOException {
        zzb(1, 3);
        zzd(2, i);
        zza(3, com_google_android_gms_internal_clearcut_zzdo);
        zzb(1, 4);
    }

    public final void zzb(int i, boolean z) throws IOException {
        int i2 = 0;
        zzb(i, 0);
        if (z) {
            i2 = 1;
        }
        zza((byte) i2);
    }

    public final void zzb(long j) throws IOException {
        byte[] bArr;
        int i;
        if (!zzbn.zzah() || zzag() < 10) {
            while ((j & -128) != 0) {
                try {
                    bArr = this.buffer;
                    i = this.position;
                    this.position = i + 1;
                    bArr[i] = (byte) ((((int) j) & 127) | 128);
                    j >>>= 7;
                } catch (Throwable e) {
                    throw new zzbn$zzc(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.position), Integer.valueOf(this.limit), Integer.valueOf(1)}), e);
                }
            }
            bArr = this.buffer;
            i = this.position;
            this.position = i + 1;
            bArr[i] = (byte) ((int) j);
            return;
        }
        while ((j & -128) != 0) {
            bArr = this.buffer;
            i = this.position;
            this.position = i + 1;
            zzfd.zza(bArr, (long) i, (byte) ((((int) j) & 127) | 128));
            j >>>= 7;
        }
        bArr = this.buffer;
        i = this.position;
        this.position = i + 1;
        zzfd.zza(bArr, (long) i, (byte) ((int) j));
    }

    public final void zzb(zzdo com_google_android_gms_internal_clearcut_zzdo) throws IOException {
        zzo(com_google_android_gms_internal_clearcut_zzdo.zzas());
        com_google_android_gms_internal_clearcut_zzdo.zzb(this);
    }

    public final void zzc(int i, int i2) throws IOException {
        zzb(i, 0);
        zzn(i2);
    }

    public final void zzc(int i, long j) throws IOException {
        zzb(i, 1);
        zzd(j);
    }

    public final void zzd(int i, int i2) throws IOException {
        zzb(i, 0);
        zzo(i2);
    }

    public final void zzd(long j) throws IOException {
        try {
            byte[] bArr = this.buffer;
            int i = this.position;
            this.position = i + 1;
            bArr[i] = (byte) ((int) j);
            bArr = this.buffer;
            i = this.position;
            this.position = i + 1;
            bArr[i] = (byte) ((int) (j >> 8));
            bArr = this.buffer;
            i = this.position;
            this.position = i + 1;
            bArr[i] = (byte) ((int) (j >> 16));
            bArr = this.buffer;
            i = this.position;
            this.position = i + 1;
            bArr[i] = (byte) ((int) (j >> 24));
            bArr = this.buffer;
            i = this.position;
            this.position = i + 1;
            bArr[i] = (byte) ((int) (j >> 32));
            bArr = this.buffer;
            i = this.position;
            this.position = i + 1;
            bArr[i] = (byte) ((int) (j >> 40));
            bArr = this.buffer;
            i = this.position;
            this.position = i + 1;
            bArr[i] = (byte) ((int) (j >> 48));
            bArr = this.buffer;
            i = this.position;
            this.position = i + 1;
            bArr[i] = (byte) ((int) (j >> 56));
        } catch (Throwable e) {
            throw new zzbn$zzc(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.position), Integer.valueOf(this.limit), Integer.valueOf(1)}), e);
        }
    }

    public final void zzd(byte[] bArr, int i, int i2) throws IOException {
        zzo(i2);
        write(bArr, 0, i2);
    }

    public final void zzf(int i, int i2) throws IOException {
        zzb(i, 5);
        zzq(i2);
    }

    public final void zzg(String str) throws IOException {
        int i = this.position;
        try {
            int zzt = zzt(str.length() * 3);
            int zzt2 = zzt(str.length());
            if (zzt2 == zzt) {
                this.position = i + zzt2;
                zzt = zzff.zza(str, this.buffer, this.position, zzag());
                this.position = i;
                zzo((zzt - i) - zzt2);
                this.position = zzt;
                return;
            }
            zzo(zzff.zza(str));
            this.position = zzff.zza(str, this.buffer, this.position, zzag());
        } catch (zzfi e) {
            this.position = i;
            zza(str, e);
        } catch (Throwable e2) {
            throw new zzbn$zzc(e2);
        }
    }

    public final void zzn(int i) throws IOException {
        if (i >= 0) {
            zzo(i);
        } else {
            zzb((long) i);
        }
    }

    public final void zzo(int i) throws IOException {
        byte[] bArr;
        int i2;
        if (!zzbn.zzah() || zzag() < 10) {
            while ((i & -128) != 0) {
                try {
                    bArr = this.buffer;
                    i2 = this.position;
                    this.position = i2 + 1;
                    bArr[i2] = (byte) ((i & 127) | 128);
                    i >>>= 7;
                } catch (Throwable e) {
                    throw new zzbn$zzc(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.position), Integer.valueOf(this.limit), Integer.valueOf(1)}), e);
                }
            }
            bArr = this.buffer;
            i2 = this.position;
            this.position = i2 + 1;
            bArr[i2] = (byte) i;
            return;
        }
        while ((i & -128) != 0) {
            bArr = this.buffer;
            i2 = this.position;
            this.position = i2 + 1;
            zzfd.zza(bArr, (long) i2, (byte) ((i & 127) | 128));
            i >>>= 7;
        }
        bArr = this.buffer;
        i2 = this.position;
        this.position = i2 + 1;
        zzfd.zza(bArr, (long) i2, (byte) i);
    }

    public final void zzq(int i) throws IOException {
        try {
            byte[] bArr = this.buffer;
            int i2 = this.position;
            this.position = i2 + 1;
            bArr[i2] = (byte) i;
            bArr = this.buffer;
            i2 = this.position;
            this.position = i2 + 1;
            bArr[i2] = (byte) (i >> 8);
            bArr = this.buffer;
            i2 = this.position;
            this.position = i2 + 1;
            bArr[i2] = (byte) (i >> 16);
            bArr = this.buffer;
            i2 = this.position;
            this.position = i2 + 1;
            bArr[i2] = i >> 24;
        } catch (Throwable e) {
            throw new zzbn$zzc(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.position), Integer.valueOf(this.limit), Integer.valueOf(1)}), e);
        }
    }
}
