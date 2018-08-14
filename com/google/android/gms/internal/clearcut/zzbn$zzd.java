package com.google.android.gms.internal.clearcut;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

final class zzbn$zzd extends zzbn {
    private final int zzgb;
    private final ByteBuffer zzgc;
    private final ByteBuffer zzgd;

    zzbn$zzd(ByteBuffer byteBuffer) {
        super(null);
        this.zzgc = byteBuffer;
        this.zzgd = byteBuffer.duplicate().order(ByteOrder.LITTLE_ENDIAN);
        this.zzgb = byteBuffer.position();
    }

    private final void zzi(String str) throws IOException {
        try {
            zzff.zza(str, this.zzgd);
        } catch (Throwable e) {
            throw new zzbn$zzc(e);
        }
    }

    public final void flush() {
        this.zzgc.position(this.zzgd.position());
    }

    public final void write(byte[] bArr, int i, int i2) throws IOException {
        try {
            this.zzgd.put(bArr, i, i2);
        } catch (Throwable e) {
            throw new zzbn$zzc(e);
        } catch (Throwable e2) {
            throw new zzbn$zzc(e2);
        }
    }

    public final void zza(byte b) throws IOException {
        try {
            this.zzgd.put(b);
        } catch (Throwable e) {
            throw new zzbn$zzc(e);
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
        zza(com_google_android_gms_internal_clearcut_zzdo, com_google_android_gms_internal_clearcut_zzef);
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
        return this.zzgd.remaining();
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
        while ((-128 & j) != 0) {
            this.zzgd.put((byte) ((((int) j) & 127) | 128));
            j >>>= 7;
        }
        try {
            this.zzgd.put((byte) ((int) j));
        } catch (Throwable e) {
            throw new zzbn$zzc(e);
        }
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
            this.zzgd.putLong(j);
        } catch (Throwable e) {
            throw new zzbn$zzc(e);
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
        int position = this.zzgd.position();
        try {
            int zzt = zzt(str.length() * 3);
            int zzt2 = zzt(str.length());
            if (zzt2 == zzt) {
                zzt = this.zzgd.position() + zzt2;
                this.zzgd.position(zzt);
                zzi(str);
                zzt2 = this.zzgd.position();
                this.zzgd.position(position);
                zzo(zzt2 - zzt);
                this.zzgd.position(zzt2);
                return;
            }
            zzo(zzff.zza(str));
            zzi(str);
        } catch (zzfi e) {
            this.zzgd.position(position);
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
        while ((i & -128) != 0) {
            this.zzgd.put((byte) ((i & 127) | 128));
            i >>>= 7;
        }
        try {
            this.zzgd.put((byte) i);
        } catch (Throwable e) {
            throw new zzbn$zzc(e);
        }
    }

    public final void zzq(int i) throws IOException {
        try {
            this.zzgd.putInt(i);
        } catch (Throwable e) {
            throw new zzbn$zzc(e);
        }
    }
}
