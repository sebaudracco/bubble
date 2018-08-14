package com.google.android.gms.internal.clearcut;

import com.google.firebase.analytics.FirebaseAnalytics$Param;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

final class zzbn$zze extends zzbn {
    private final ByteBuffer zzgc;
    private final ByteBuffer zzgd;
    private final long zzge;
    private final long zzgf;
    private final long zzgg;
    private final long zzgh = (this.zzgg - 10);
    private long zzgi = this.zzgf;

    zzbn$zze(ByteBuffer byteBuffer) {
        super(null);
        this.zzgc = byteBuffer;
        this.zzgd = byteBuffer.duplicate().order(ByteOrder.LITTLE_ENDIAN);
        this.zzge = zzfd.zzb(byteBuffer);
        this.zzgf = this.zzge + ((long) byteBuffer.position());
        this.zzgg = this.zzge + ((long) byteBuffer.limit());
    }

    private final void zzk(long j) {
        this.zzgd.position((int) (j - this.zzge));
    }

    public final void flush() {
        this.zzgc.position((int) (this.zzgi - this.zzge));
    }

    public final void write(byte[] bArr, int i, int i2) throws IOException {
        if (bArr != null && i >= 0 && i2 >= 0 && bArr.length - i2 >= i && this.zzgg - ((long) i2) >= this.zzgi) {
            zzfd.zza(bArr, (long) i, this.zzgi, (long) i2);
            this.zzgi += (long) i2;
        } else if (bArr == null) {
            throw new NullPointerException(FirebaseAnalytics$Param.VALUE);
        } else {
            throw new zzbn$zzc(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Long.valueOf(this.zzgi), Long.valueOf(this.zzgg), Integer.valueOf(i2)}));
        }
    }

    public final void zza(byte b) throws IOException {
        if (this.zzgi >= this.zzgg) {
            throw new zzbn$zzc(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Long.valueOf(this.zzgi), Long.valueOf(this.zzgg), Integer.valueOf(1)}));
        }
        long j = this.zzgi;
        this.zzgi = 1 + j;
        zzfd.zza(j, b);
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
        return (int) (this.zzgg - this.zzgi);
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
        long j2;
        if (this.zzgi <= this.zzgh) {
            while ((j & -128) != 0) {
                j2 = this.zzgi;
                this.zzgi = j2 + 1;
                zzfd.zza(j2, (byte) ((((int) j) & 127) | 128));
                j >>>= 7;
            }
            j2 = this.zzgi;
            this.zzgi = j2 + 1;
            zzfd.zza(j2, (byte) ((int) j));
            return;
        }
        while (this.zzgi < this.zzgg) {
            if ((j & -128) == 0) {
                j2 = this.zzgi;
                this.zzgi = j2 + 1;
                zzfd.zza(j2, (byte) ((int) j));
                return;
            }
            j2 = this.zzgi;
            this.zzgi = j2 + 1;
            zzfd.zza(j2, (byte) ((((int) j) & 127) | 128));
            j >>>= 7;
        }
        throw new zzbn$zzc(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Long.valueOf(this.zzgi), Long.valueOf(this.zzgg), Integer.valueOf(1)}));
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
        this.zzgd.putLong((int) (this.zzgi - this.zzge), j);
        this.zzgi += 8;
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
        long j = this.zzgi;
        try {
            int zzt = zzt(str.length() * 3);
            int zzt2 = zzt(str.length());
            if (zzt2 == zzt) {
                zzt = ((int) (this.zzgi - this.zzge)) + zzt2;
                this.zzgd.position(zzt);
                zzff.zza(str, this.zzgd);
                zzt = this.zzgd.position() - zzt;
                zzo(zzt);
                this.zzgi = ((long) zzt) + this.zzgi;
                return;
            }
            zzt = zzff.zza(str);
            zzo(zzt);
            zzk(this.zzgi);
            zzff.zza(str, this.zzgd);
            this.zzgi = ((long) zzt) + this.zzgi;
        } catch (zzfi e) {
            this.zzgi = j;
            zzk(this.zzgi);
            zza(str, e);
        } catch (Throwable e2) {
            throw new zzbn$zzc(e2);
        } catch (Throwable e22) {
            throw new zzbn$zzc(e22);
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
        long j;
        if (this.zzgi <= this.zzgh) {
            while ((i & -128) != 0) {
                j = this.zzgi;
                this.zzgi = j + 1;
                zzfd.zza(j, (byte) ((i & 127) | 128));
                i >>>= 7;
            }
            j = this.zzgi;
            this.zzgi = j + 1;
            zzfd.zza(j, (byte) i);
            return;
        }
        while (this.zzgi < this.zzgg) {
            if ((i & -128) == 0) {
                j = this.zzgi;
                this.zzgi = j + 1;
                zzfd.zza(j, (byte) i);
                return;
            }
            j = this.zzgi;
            this.zzgi = j + 1;
            zzfd.zza(j, (byte) ((i & 127) | 128));
            i >>>= 7;
        }
        throw new zzbn$zzc(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Long.valueOf(this.zzgi), Long.valueOf(this.zzgg), Integer.valueOf(1)}));
    }

    public final void zzq(int i) throws IOException {
        this.zzgd.putInt((int) (this.zzgi - this.zzge), i);
        this.zzgi += 4;
    }
}
