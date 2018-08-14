package com.google.android.gms.internal.ads;

import com.mobfox.sdk.bannerads.SizeUtils;
import java.io.IOException;
import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.ReadOnlyBufferException;

public final class zzbfa {
    private final ByteBuffer zzebj;

    private zzbfa(ByteBuffer byteBuffer) {
        this.zzebj = byteBuffer;
        this.zzebj.order(ByteOrder.LITTLE_ENDIAN);
    }

    private zzbfa(byte[] bArr, int i, int i2) {
        this(ByteBuffer.wrap(bArr, i, i2));
    }

    private static int zza(CharSequence charSequence) {
        int i = 0;
        int length = charSequence.length();
        int i2 = 0;
        while (i2 < length && charSequence.charAt(i2) < '') {
            i2++;
        }
        int i3 = length;
        while (i2 < length) {
            char charAt = charSequence.charAt(i2);
            if (charAt < 'ࠀ') {
                i3 += (127 - charAt) >>> 31;
                i2++;
            } else {
                int length2 = charSequence.length();
                while (i2 < length2) {
                    char charAt2 = charSequence.charAt(i2);
                    if (charAt2 < 'ࠀ') {
                        i += (127 - charAt2) >>> 31;
                    } else {
                        i += 2;
                        if ('?' <= charAt2 && charAt2 <= '?') {
                            if (Character.codePointAt(charSequence, i2) < 65536) {
                                throw new IllegalArgumentException("Unpaired surrogate at index " + i2);
                            }
                            i2++;
                        }
                    }
                    i2++;
                }
                i2 = i3 + i;
                if (i2 < length) {
                    return i2;
                }
                throw new IllegalArgumentException("UTF-8 length does not fit in int: " + (((long) i2) + 4294967296L));
            }
        }
        i2 = i3;
        if (i2 < length) {
            return i2;
        }
        throw new IllegalArgumentException("UTF-8 length does not fit in int: " + (((long) i2) + 4294967296L));
    }

    private static void zza(CharSequence charSequence, ByteBuffer byteBuffer) {
        int i = 0;
        if (byteBuffer.isReadOnly()) {
            throw new ReadOnlyBufferException();
        } else if (byteBuffer.hasArray()) {
            try {
                byte[] array = byteBuffer.array();
                r1 = byteBuffer.arrayOffset() + byteBuffer.position();
                r2 = byteBuffer.remaining();
                int length = charSequence.length();
                int i2 = r1 + r2;
                while (i < length && i + r1 < i2) {
                    r2 = charSequence.charAt(i);
                    if (r2 >= '') {
                        break;
                    }
                    array[r1 + i] = (byte) r2;
                    i++;
                }
                if (i == length) {
                    i = r1 + length;
                } else {
                    r2 = r1 + i;
                    while (i < length) {
                        char charAt = charSequence.charAt(i);
                        if (charAt < '' && r2 < i2) {
                            r1 = r2 + 1;
                            array[r2] = (byte) charAt;
                        } else if (charAt < 'ࠀ' && r2 <= i2 - 2) {
                            r7 = r2 + 1;
                            array[r2] = (byte) ((charAt >>> 6) | 960);
                            r1 = r7 + 1;
                            array[r7] = (byte) ((charAt & 63) | 128);
                        } else if ((charAt < '?' || '?' < charAt) && r2 <= i2 - 3) {
                            r1 = r2 + 1;
                            array[r2] = (byte) ((charAt >>> 12) | SizeUtils.DEFAULT_INTERSTITIAL_HEIGHT);
                            r2 = r1 + 1;
                            array[r1] = (byte) (((charAt >>> 6) & 63) | 128);
                            r1 = r2 + 1;
                            array[r2] = (byte) ((charAt & 63) | 128);
                        } else if (r2 <= i2 - 4) {
                            if (i + 1 != charSequence.length()) {
                                i++;
                                char charAt2 = charSequence.charAt(i);
                                if (Character.isSurrogatePair(charAt, charAt2)) {
                                    int toCodePoint = Character.toCodePoint(charAt, charAt2);
                                    r1 = r2 + 1;
                                    array[r2] = (byte) ((toCodePoint >>> 18) | 240);
                                    r2 = r1 + 1;
                                    array[r1] = (byte) (((toCodePoint >>> 12) & 63) | 128);
                                    r7 = r2 + 1;
                                    array[r2] = (byte) (((toCodePoint >>> 6) & 63) | 128);
                                    r1 = r7 + 1;
                                    array[r7] = (byte) ((toCodePoint & 63) | 128);
                                }
                            }
                            throw new IllegalArgumentException("Unpaired surrogate at index " + (i - 1));
                        } else {
                            throw new ArrayIndexOutOfBoundsException("Failed writing " + charAt + " at index " + r2);
                        }
                        i++;
                        r2 = r1;
                    }
                    i = r2;
                }
                byteBuffer.position(i - byteBuffer.arrayOffset());
            } catch (Throwable e) {
                BufferOverflowException bufferOverflowException = new BufferOverflowException();
                bufferOverflowException.initCause(e);
                throw bufferOverflowException;
            }
        } else {
            r1 = charSequence.length();
            while (i < r1) {
                r2 = charSequence.charAt(i);
                if (r2 < '') {
                    byteBuffer.put((byte) r2);
                } else if (r2 < 'ࠀ') {
                    byteBuffer.put((byte) ((r2 >>> 6) | 960));
                    byteBuffer.put((byte) ((r2 & 63) | 128));
                } else if (r2 < '?' || '?' < r2) {
                    byteBuffer.put((byte) ((r2 >>> 12) | SizeUtils.DEFAULT_INTERSTITIAL_HEIGHT));
                    byteBuffer.put((byte) (((r2 >>> 6) & 63) | 128));
                    byteBuffer.put((byte) ((r2 & 63) | 128));
                } else {
                    if (i + 1 != charSequence.length()) {
                        i++;
                        char charAt3 = charSequence.charAt(i);
                        if (Character.isSurrogatePair(r2, charAt3)) {
                            r2 = Character.toCodePoint(r2, charAt3);
                            byteBuffer.put((byte) ((r2 >>> 18) | 240));
                            byteBuffer.put((byte) (((r2 >>> 12) & 63) | 128));
                            byteBuffer.put((byte) (((r2 >>> 6) & 63) | 128));
                            byteBuffer.put((byte) ((r2 & 63) | 128));
                        }
                    }
                    throw new IllegalArgumentException("Unpaired surrogate at index " + (i - 1));
                }
                i++;
            }
        }
    }

    public static int zzb(int i, zzbfi com_google_android_gms_internal_ads_zzbfi) {
        int zzcd = zzcd(i);
        int zzacw = com_google_android_gms_internal_ads_zzbfi.zzacw();
        return zzcd + (zzacw + zzcl(zzacw));
    }

    public static int zzb(int i, byte[] bArr) {
        return zzcd(i) + zzv(bArr);
    }

    public static int zzcd(int i) {
        return zzcl(i << 3);
    }

    public static int zzce(int i) {
        return i >= 0 ? zzcl(i) : 10;
    }

    public static int zzcl(int i) {
        return (i & -128) == 0 ? 1 : (i & -16384) == 0 ? 2 : (-2097152 & i) == 0 ? 3 : (-268435456 & i) == 0 ? 4 : 5;
    }

    public static int zzd(int i, long j) {
        return zzcd(i) + zzy(j);
    }

    private final void zzdd(int i) throws IOException {
        byte b = (byte) i;
        if (this.zzebj.hasRemaining()) {
            this.zzebj.put(b);
            return;
        }
        throw new zzbfb(this.zzebj.position(), this.zzebj.limit());
    }

    public static int zze(int i, long j) {
        return zzcd(i) + zzy(j);
    }

    public static int zzeo(String str) {
        int zza = zza(str);
        return zza + zzcl(zza);
    }

    public static int zzg(int i, String str) {
        return zzcd(i) + zzeo(str);
    }

    public static zzbfa zzj(byte[] bArr, int i, int i2) {
        return new zzbfa(bArr, 0, i2);
    }

    public static int zzq(int i, int i2) {
        return zzcd(i) + zzce(i2);
    }

    public static zzbfa zzu(byte[] bArr) {
        return zzj(bArr, 0, bArr.length);
    }

    public static int zzv(byte[] bArr) {
        return zzcl(bArr.length) + bArr.length;
    }

    private final void zzx(long j) throws IOException {
        while ((-128 & j) != 0) {
            zzdd((((int) j) & 127) | 128);
            j >>>= 7;
        }
        zzdd((int) j);
    }

    public static int zzy(long j) {
        return (-128 & j) == 0 ? 1 : (-16384 & j) == 0 ? 2 : (-2097152 & j) == 0 ? 3 : (-268435456 & j) == 0 ? 4 : (-34359738368L & j) == 0 ? 5 : (-4398046511104L & j) == 0 ? 6 : (-562949953421312L & j) == 0 ? 7 : (-72057594037927936L & j) == 0 ? 8 : (Long.MIN_VALUE & j) == 0 ? 9 : 10;
    }

    public final void zza(int i, long j) throws IOException {
        zzl(i, 0);
        zzx(j);
    }

    public final void zza(int i, zzbfi com_google_android_gms_internal_ads_zzbfi) throws IOException {
        zzl(i, 2);
        if (com_google_android_gms_internal_ads_zzbfi.zzebt < 0) {
            com_google_android_gms_internal_ads_zzbfi.zzacw();
        }
        zzde(com_google_android_gms_internal_ads_zzbfi.zzebt);
        com_google_android_gms_internal_ads_zzbfi.zza(this);
    }

    public final void zza(int i, byte[] bArr) throws IOException {
        zzl(i, 2);
        zzde(bArr.length);
        zzw(bArr);
    }

    public final void zzacl() {
        if (this.zzebj.remaining() != 0) {
            throw new IllegalStateException(String.format("Did not write as much data as expected, %s bytes remaining.", new Object[]{Integer.valueOf(this.zzebj.remaining())}));
        }
    }

    public final void zzde(int i) throws IOException {
        while ((i & -128) != 0) {
            zzdd((i & 127) | 128);
            i >>>= 7;
        }
        zzdd(i);
    }

    public final void zzf(int i, String str) throws IOException {
        zzl(i, 2);
        try {
            int zzcl = zzcl(str.length());
            if (zzcl == zzcl(str.length() * 3)) {
                int position = this.zzebj.position();
                if (this.zzebj.remaining() < zzcl) {
                    throw new zzbfb(zzcl + position, this.zzebj.limit());
                }
                this.zzebj.position(position + zzcl);
                zza((CharSequence) str, this.zzebj);
                int position2 = this.zzebj.position();
                this.zzebj.position(position);
                zzde((position2 - position) - zzcl);
                this.zzebj.position(position2);
                return;
            }
            zzde(zza(str));
            zza((CharSequence) str, this.zzebj);
        } catch (Throwable e) {
            zzbfb com_google_android_gms_internal_ads_zzbfb = new zzbfb(this.zzebj.position(), this.zzebj.limit());
            com_google_android_gms_internal_ads_zzbfb.initCause(e);
            throw com_google_android_gms_internal_ads_zzbfb;
        }
    }

    public final void zzf(int i, boolean z) throws IOException {
        int i2 = 0;
        zzl(i, 0);
        if (z) {
            i2 = 1;
        }
        byte b = (byte) i2;
        if (this.zzebj.hasRemaining()) {
            this.zzebj.put(b);
            return;
        }
        throw new zzbfb(this.zzebj.position(), this.zzebj.limit());
    }

    public final void zzi(int i, long j) throws IOException {
        zzl(i, 0);
        zzx(j);
    }

    public final void zzl(int i, int i2) throws IOException {
        zzde((i << 3) | i2);
    }

    public final void zzm(int i, int i2) throws IOException {
        zzl(i, 0);
        if (i2 >= 0) {
            zzde(i2);
        } else {
            zzx((long) i2);
        }
    }

    public final void zzw(byte[] bArr) throws IOException {
        int length = bArr.length;
        if (this.zzebj.remaining() >= length) {
            this.zzebj.put(bArr, 0, length);
            return;
        }
        throw new zzbfb(this.zzebj.position(), this.zzebj.limit());
    }
}
