package com.google.android.gms.internal.ads;

import java.io.IOException;

class zzbav$zza extends zzbav {
    private final byte[] buffer;
    private final int limit;
    private final int offset;
    private int position;

    zzbav$zza(byte[] bArr, int i, int i2) {
        super(null);
        if (bArr == null) {
            throw new NullPointerException("buffer");
        } else if (((i2 | 0) | (bArr.length - (i2 + 0))) < 0) {
            throw new IllegalArgumentException(String.format("Array range is invalid. Buffer.length=%d, offset=%d, length=%d", new Object[]{Integer.valueOf(bArr.length), Integer.valueOf(0), Integer.valueOf(i2)}));
        } else {
            this.buffer = bArr;
            this.offset = 0;
            this.position = 0;
            this.limit = i2 + 0;
        }
    }

    public final void write(byte[] bArr, int i, int i2) throws IOException {
        try {
            System.arraycopy(bArr, i, this.buffer, this.position, i2);
            this.position += i2;
        } catch (Throwable e) {
            throw new zzbav$zzb(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.position), Integer.valueOf(this.limit), Integer.valueOf(i2)}), e);
        }
    }

    public final void zza(byte b) throws IOException {
        try {
            byte[] bArr = this.buffer;
            int i = this.position;
            this.position = i + 1;
            bArr[i] = b;
        } catch (Throwable e) {
            throw new zzbav$zzb(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.position), Integer.valueOf(this.limit), Integer.valueOf(1)}), e);
        }
    }

    public final void zza(int i, long j) throws IOException {
        zzl(i, 0);
        zzm(j);
    }

    public final void zza(int i, zzbah com_google_android_gms_internal_ads_zzbah) throws IOException {
        zzl(i, 2);
        zzan(com_google_android_gms_internal_ads_zzbah);
    }

    public final void zza(int i, zzbcu com_google_android_gms_internal_ads_zzbcu) throws IOException {
        zzl(1, 3);
        zzn(2, i);
        zzl(3, 2);
        zze(com_google_android_gms_internal_ads_zzbcu);
        zzl(1, 4);
    }

    final void zza(int i, zzbcu com_google_android_gms_internal_ads_zzbcu, zzbdm com_google_android_gms_internal_ads_zzbdm) throws IOException {
        zzl(i, 2);
        zzazy com_google_android_gms_internal_ads_zzazy = (zzazy) com_google_android_gms_internal_ads_zzbcu;
        int zzaaw = com_google_android_gms_internal_ads_zzazy.zzaaw();
        if (zzaaw == -1) {
            zzaaw = com_google_android_gms_internal_ads_zzbdm.zzy(com_google_android_gms_internal_ads_zzazy);
            com_google_android_gms_internal_ads_zzazy.zzbj(zzaaw);
        }
        zzca(zzaaw);
        com_google_android_gms_internal_ads_zzbdm.zza(com_google_android_gms_internal_ads_zzbcu, this.zzdqn);
    }

    public final int zzack() {
        return this.limit - this.position;
    }

    public final void zzan(zzbah com_google_android_gms_internal_ads_zzbah) throws IOException {
        zzca(com_google_android_gms_internal_ads_zzbah.size());
        com_google_android_gms_internal_ads_zzbah.zza((zzbag) this);
    }

    public final void zzb(int i, zzbah com_google_android_gms_internal_ads_zzbah) throws IOException {
        zzl(1, 3);
        zzn(2, i);
        zza(3, com_google_android_gms_internal_ads_zzbah);
        zzl(1, 4);
    }

    public final void zzb(byte[] bArr, int i, int i2) throws IOException {
        write(bArr, i, i2);
    }

    public final void zzbz(int i) throws IOException {
        if (i >= 0) {
            zzca(i);
        } else {
            zzm((long) i);
        }
    }

    public final void zzc(int i, long j) throws IOException {
        zzl(i, 1);
        zzo(j);
    }

    public final void zzca(int i) throws IOException {
        byte[] bArr;
        int i2;
        if (!zzbav.zzacm() || zzack() < 10) {
            while ((i & -128) != 0) {
                try {
                    bArr = this.buffer;
                    i2 = this.position;
                    this.position = i2 + 1;
                    bArr[i2] = (byte) ((i & 127) | 128);
                    i >>>= 7;
                } catch (Throwable e) {
                    throw new zzbav$zzb(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.position), Integer.valueOf(this.limit), Integer.valueOf(1)}), e);
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
            zzbek.zza(bArr, (long) i2, (byte) ((i & 127) | 128));
            i >>>= 7;
        }
        bArr = this.buffer;
        i2 = this.position;
        this.position = i2 + 1;
        zzbek.zza(bArr, (long) i2, (byte) i);
    }

    public final void zzcc(int i) throws IOException {
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
            throw new zzbav$zzb(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.position), Integer.valueOf(this.limit), Integer.valueOf(1)}), e);
        }
    }

    public final void zze(zzbcu com_google_android_gms_internal_ads_zzbcu) throws IOException {
        zzca(com_google_android_gms_internal_ads_zzbcu.zzacw());
        com_google_android_gms_internal_ads_zzbcu.zzb(this);
    }

    public final void zze(byte[] bArr, int i, int i2) throws IOException {
        zzca(i2);
        write(bArr, 0, i2);
    }

    public final void zzen(String str) throws IOException {
        int i = this.position;
        try {
            int zzcf = zzcf(str.length() * 3);
            int zzcf2 = zzcf(str.length());
            if (zzcf2 == zzcf) {
                this.position = i + zzcf2;
                zzcf = zzbem.zza(str, this.buffer, this.position, zzack());
                this.position = i;
                zzca((zzcf - i) - zzcf2);
                this.position = zzcf;
                return;
            }
            zzca(zzbem.zza(str));
            this.position = zzbem.zza(str, this.buffer, this.position, zzack());
        } catch (zzbep e) {
            this.position = i;
            zza(str, e);
        } catch (Throwable e2) {
            throw new zzbav$zzb(e2);
        }
    }

    public final void zzf(int i, String str) throws IOException {
        zzl(i, 2);
        zzen(str);
    }

    public final void zzf(int i, boolean z) throws IOException {
        int i2 = 0;
        zzl(i, 0);
        if (z) {
            i2 = 1;
        }
        zza((byte) i2);
    }

    public final void zzl(int i, int i2) throws IOException {
        zzca((i << 3) | i2);
    }

    public final void zzm(int i, int i2) throws IOException {
        zzl(i, 0);
        zzbz(i2);
    }

    public final void zzm(long j) throws IOException {
        byte[] bArr;
        int i;
        if (!zzbav.zzacm() || zzack() < 10) {
            while ((j & -128) != 0) {
                try {
                    bArr = this.buffer;
                    i = this.position;
                    this.position = i + 1;
                    bArr[i] = (byte) ((((int) j) & 127) | 128);
                    j >>>= 7;
                } catch (Throwable e) {
                    throw new zzbav$zzb(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.position), Integer.valueOf(this.limit), Integer.valueOf(1)}), e);
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
            zzbek.zza(bArr, (long) i, (byte) ((((int) j) & 127) | 128));
            j >>>= 7;
        }
        bArr = this.buffer;
        i = this.position;
        this.position = i + 1;
        zzbek.zza(bArr, (long) i, (byte) ((int) j));
    }

    public final void zzn(int i, int i2) throws IOException {
        zzl(i, 0);
        zzca(i2);
    }

    public final void zzo(long j) throws IOException {
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
            throw new zzbav$zzb(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.position), Integer.valueOf(this.limit), Integer.valueOf(1)}), e);
        }
    }

    public final void zzp(int i, int i2) throws IOException {
        zzl(i, 5);
        zzcc(i2);
    }
}
