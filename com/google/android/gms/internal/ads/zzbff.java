package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

final class zzbff implements Cloneable {
    private Object value;
    private zzbfd<?, ?> zzebq;
    private List<zzbfk> zzebr = new ArrayList();

    zzbff() {
    }

    private final byte[] toByteArray() throws IOException {
        byte[] bArr = new byte[zzr()];
        zza(zzbfa.zzu(bArr));
        return bArr;
    }

    private final zzbff zzagp() {
        zzbff com_google_android_gms_internal_ads_zzbff = new zzbff();
        try {
            com_google_android_gms_internal_ads_zzbff.zzebq = this.zzebq;
            if (this.zzebr == null) {
                com_google_android_gms_internal_ads_zzbff.zzebr = null;
            } else {
                com_google_android_gms_internal_ads_zzbff.zzebr.addAll(this.zzebr);
            }
            if (this.value != null) {
                if (this.value instanceof zzbfi) {
                    com_google_android_gms_internal_ads_zzbff.value = (zzbfi) ((zzbfi) this.value).clone();
                } else if (this.value instanceof byte[]) {
                    com_google_android_gms_internal_ads_zzbff.value = ((byte[]) this.value).clone();
                } else if (this.value instanceof byte[][]) {
                    byte[][] bArr = (byte[][]) this.value;
                    r4 = new byte[bArr.length][];
                    com_google_android_gms_internal_ads_zzbff.value = r4;
                    for (r2 = 0; r2 < bArr.length; r2++) {
                        r4[r2] = (byte[]) bArr[r2].clone();
                    }
                } else if (this.value instanceof boolean[]) {
                    com_google_android_gms_internal_ads_zzbff.value = ((boolean[]) this.value).clone();
                } else if (this.value instanceof int[]) {
                    com_google_android_gms_internal_ads_zzbff.value = ((int[]) this.value).clone();
                } else if (this.value instanceof long[]) {
                    com_google_android_gms_internal_ads_zzbff.value = ((long[]) this.value).clone();
                } else if (this.value instanceof float[]) {
                    com_google_android_gms_internal_ads_zzbff.value = ((float[]) this.value).clone();
                } else if (this.value instanceof double[]) {
                    com_google_android_gms_internal_ads_zzbff.value = ((double[]) this.value).clone();
                } else if (this.value instanceof zzbfi[]) {
                    zzbfi[] com_google_android_gms_internal_ads_zzbfiArr = (zzbfi[]) this.value;
                    r4 = new zzbfi[com_google_android_gms_internal_ads_zzbfiArr.length];
                    com_google_android_gms_internal_ads_zzbff.value = r4;
                    for (r2 = 0; r2 < com_google_android_gms_internal_ads_zzbfiArr.length; r2++) {
                        r4[r2] = (zzbfi) com_google_android_gms_internal_ads_zzbfiArr[r2].clone();
                    }
                }
            }
            return com_google_android_gms_internal_ads_zzbff;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    public final /* synthetic */ Object clone() throws CloneNotSupportedException {
        return zzagp();
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzbff)) {
            return false;
        }
        zzbff com_google_android_gms_internal_ads_zzbff = (zzbff) obj;
        if (this.value != null && com_google_android_gms_internal_ads_zzbff.value != null) {
            return this.zzebq == com_google_android_gms_internal_ads_zzbff.zzebq ? !this.zzebq.zzebl.isArray() ? this.value.equals(com_google_android_gms_internal_ads_zzbff.value) : this.value instanceof byte[] ? Arrays.equals((byte[]) this.value, (byte[]) com_google_android_gms_internal_ads_zzbff.value) : this.value instanceof int[] ? Arrays.equals((int[]) this.value, (int[]) com_google_android_gms_internal_ads_zzbff.value) : this.value instanceof long[] ? Arrays.equals((long[]) this.value, (long[]) com_google_android_gms_internal_ads_zzbff.value) : this.value instanceof float[] ? Arrays.equals((float[]) this.value, (float[]) com_google_android_gms_internal_ads_zzbff.value) : this.value instanceof double[] ? Arrays.equals((double[]) this.value, (double[]) com_google_android_gms_internal_ads_zzbff.value) : this.value instanceof boolean[] ? Arrays.equals((boolean[]) this.value, (boolean[]) com_google_android_gms_internal_ads_zzbff.value) : Arrays.deepEquals((Object[]) this.value, (Object[]) com_google_android_gms_internal_ads_zzbff.value) : false;
        } else {
            if (this.zzebr != null && com_google_android_gms_internal_ads_zzbff.zzebr != null) {
                return this.zzebr.equals(com_google_android_gms_internal_ads_zzbff.zzebr);
            }
            try {
                return Arrays.equals(toByteArray(), com_google_android_gms_internal_ads_zzbff.toByteArray());
            } catch (Throwable e) {
                throw new IllegalStateException(e);
            }
        }
    }

    public final int hashCode() {
        try {
            return Arrays.hashCode(toByteArray()) + 527;
        } catch (Throwable e) {
            throw new IllegalStateException(e);
        }
    }

    final void zza(zzbfa com_google_android_gms_internal_ads_zzbfa) throws IOException {
        if (this.value != null) {
            throw new NoSuchMethodError();
        }
        for (zzbfk com_google_android_gms_internal_ads_zzbfk : this.zzebr) {
            com_google_android_gms_internal_ads_zzbfa.zzde(com_google_android_gms_internal_ads_zzbfk.tag);
            com_google_android_gms_internal_ads_zzbfa.zzw(com_google_android_gms_internal_ads_zzbfk.zzdpw);
        }
    }

    final void zza(zzbfk com_google_android_gms_internal_ads_zzbfk) throws IOException {
        if (this.zzebr != null) {
            this.zzebr.add(com_google_android_gms_internal_ads_zzbfk);
        } else if (this.value instanceof zzbfi) {
            byte[] bArr = com_google_android_gms_internal_ads_zzbfk.zzdpw;
            zzbez zzi = zzbez.zzi(bArr, 0, bArr.length);
            int zzacc = zzi.zzacc();
            if (zzacc != bArr.length - zzbfa.zzce(zzacc)) {
                throw zzbfh.zzagq();
            }
            zzbfi zza = ((zzbfi) this.value).zza(zzi);
            this.zzebq = this.zzebq;
            this.value = zza;
            this.zzebr = null;
        } else if (this.value instanceof zzbfi[]) {
            Collections.singletonList(com_google_android_gms_internal_ads_zzbfk);
            throw new NoSuchMethodError();
        } else {
            Collections.singletonList(com_google_android_gms_internal_ads_zzbfk);
            throw new NoSuchMethodError();
        }
    }

    final int zzr() {
        if (this.value != null) {
            throw new NoSuchMethodError();
        }
        int i = 0;
        for (zzbfk com_google_android_gms_internal_ads_zzbfk : this.zzebr) {
            i = (com_google_android_gms_internal_ads_zzbfk.zzdpw.length + (zzbfa.zzcl(com_google_android_gms_internal_ads_zzbfk.tag) + 0)) + i;
        }
        return i;
    }
}
