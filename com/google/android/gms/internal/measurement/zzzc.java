package com.google.android.gms.internal.measurement;

class zzzc extends zzzb {
    protected final byte[] zzbrc;

    zzzc(byte[] bArr) {
        this.zzbrc = bArr;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzyw)) {
            return false;
        }
        if (size() != ((zzyw) obj).size()) {
            return false;
        }
        if (size() == 0) {
            return true;
        }
        if (!(obj instanceof zzzc)) {
            return obj.equals(this);
        }
        zzzc com_google_android_gms_internal_measurement_zzzc = (zzzc) obj;
        int zzsx = zzsx();
        int zzsx2 = com_google_android_gms_internal_measurement_zzzc.zzsx();
        return (zzsx == 0 || zzsx2 == 0 || zzsx == zzsx2) ? zza((zzzc) obj, 0, size()) : false;
    }

    public int size() {
        return this.zzbrc.length;
    }

    protected final int zza(int i, int i2, int i3) {
        return zzzr.zza(i, this.zzbrc, zzsy(), i3);
    }

    final boolean zza(zzyw com_google_android_gms_internal_measurement_zzyw, int i, int i2) {
        if (i2 > com_google_android_gms_internal_measurement_zzyw.size()) {
            throw new IllegalArgumentException("Length too large: " + i2 + size());
        } else if (i2 > com_google_android_gms_internal_measurement_zzyw.size()) {
            throw new IllegalArgumentException("Ran off end of other: 0, " + i2 + ", " + com_google_android_gms_internal_measurement_zzyw.size());
        } else if (!(com_google_android_gms_internal_measurement_zzyw instanceof zzzc)) {
            return com_google_android_gms_internal_measurement_zzyw.zzb(0, i2).equals(zzb(0, i2));
        } else {
            zzzc com_google_android_gms_internal_measurement_zzzc = (zzzc) com_google_android_gms_internal_measurement_zzyw;
            byte[] bArr = this.zzbrc;
            byte[] bArr2 = com_google_android_gms_internal_measurement_zzzc.zzbrc;
            int zzsy = zzsy() + i2;
            int zzsy2 = zzsy();
            int zzsy3 = com_google_android_gms_internal_measurement_zzzc.zzsy();
            while (zzsy2 < zzsy) {
                if (bArr[zzsy2] != bArr2[zzsy3]) {
                    return false;
                }
                zzsy2++;
                zzsy3++;
            }
            return true;
        }
    }

    public byte zzae(int i) {
        return this.zzbrc[i];
    }

    public final zzyw zzb(int i, int i2) {
        int zzb = zzyw.zzb(0, i2, size());
        return zzb == 0 ? zzyw.zzbqx : new zzyz(this.zzbrc, zzsy(), zzb);
    }

    protected int zzsy() {
        return 0;
    }
}
