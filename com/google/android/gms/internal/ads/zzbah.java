package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.Iterator;

public abstract class zzbah implements Serializable, Iterable<Byte> {
    public static final zzbah zzdpq = new zzbao(zzbbq.zzduq);
    private static final zzbal zzdpr = (zzbac.zzabb() ? new zzbap() : new zzbaj());
    private int zzdpa = 0;

    zzbah() {
    }

    static zzbam zzbo(int i) {
        return new zzbam(i);
    }

    public static zzbah zzc(byte[] bArr, int i, int i2) {
        return new zzbao(zzdpr.zzd(bArr, i, i2));
    }

    static int zzd(int i, int i2, int i3) {
        int i4 = i2 - i;
        if ((((i | i2) | i4) | (i3 - i2)) >= 0) {
            return i4;
        }
        if (i < 0) {
            throw new IndexOutOfBoundsException("Beginning index: " + i + " < 0");
        } else if (i2 < i) {
            throw new IndexOutOfBoundsException("Beginning index larger than ending index: " + i + ", " + i2);
        } else {
            throw new IndexOutOfBoundsException("End index: " + i2 + " >= " + i3);
        }
    }

    public static zzbah zzem(String str) {
        return new zzbao(str.getBytes(zzbbq.UTF_8));
    }

    public static zzbah zzo(byte[] bArr) {
        return zzc(bArr, 0, bArr.length);
    }

    static zzbah zzp(byte[] bArr) {
        return new zzbao(bArr);
    }

    public abstract boolean equals(Object obj);

    public final int hashCode() {
        int i = this.zzdpa;
        if (i == 0) {
            i = size();
            i = zzc(i, 0, i);
            if (i == 0) {
                i = 1;
            }
            this.zzdpa = i;
        }
        return i;
    }

    public /* synthetic */ Iterator iterator() {
        return new zzbai(this);
    }

    public abstract int size();

    public final byte[] toByteArray() {
        int size = size();
        if (size == 0) {
            return zzbbq.zzduq;
        }
        byte[] bArr = new byte[size];
        zza(bArr, 0, 0, size);
        return bArr;
    }

    public final String toString() {
        return String.format("<ByteString@%s size=%d>", new Object[]{Integer.toHexString(System.identityHashCode(this)), Integer.valueOf(size())});
    }

    protected abstract String zza(Charset charset);

    abstract void zza(zzbag com_google_android_gms_internal_ads_zzbag) throws IOException;

    protected abstract void zza(byte[] bArr, int i, int i2, int i3);

    public final String zzabd() {
        return size() == 0 ? "" : zza(zzbbq.UTF_8);
    }

    public abstract boolean zzabe();

    public abstract zzbaq zzabf();

    protected final int zzabg() {
        return this.zzdpa;
    }

    public abstract byte zzbn(int i);

    protected abstract int zzc(int i, int i2, int i3);

    public abstract zzbah zzk(int i, int i2);
}
