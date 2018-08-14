package com.google.android.gms.internal.wearable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

final class zzq implements Cloneable {
    private Object value;
    private zzo<?, ?> zzhi;
    private List<zzv> zzhj = new ArrayList();

    zzq() {
    }

    private final byte[] toByteArray() throws IOException {
        byte[] bArr = new byte[zzg()];
        zza(zzl.zzb(bArr));
        return bArr;
    }

    private final zzq zzt() {
        zzq com_google_android_gms_internal_wearable_zzq = new zzq();
        try {
            com_google_android_gms_internal_wearable_zzq.zzhi = this.zzhi;
            if (this.zzhj == null) {
                com_google_android_gms_internal_wearable_zzq.zzhj = null;
            } else {
                com_google_android_gms_internal_wearable_zzq.zzhj.addAll(this.zzhj);
            }
            if (this.value != null) {
                if (this.value instanceof zzt) {
                    com_google_android_gms_internal_wearable_zzq.value = (zzt) ((zzt) this.value).clone();
                } else if (this.value instanceof byte[]) {
                    com_google_android_gms_internal_wearable_zzq.value = ((byte[]) this.value).clone();
                } else if (this.value instanceof byte[][]) {
                    byte[][] bArr = (byte[][]) this.value;
                    r4 = new byte[bArr.length][];
                    com_google_android_gms_internal_wearable_zzq.value = r4;
                    for (r2 = 0; r2 < bArr.length; r2++) {
                        r4[r2] = (byte[]) bArr[r2].clone();
                    }
                } else if (this.value instanceof boolean[]) {
                    com_google_android_gms_internal_wearable_zzq.value = ((boolean[]) this.value).clone();
                } else if (this.value instanceof int[]) {
                    com_google_android_gms_internal_wearable_zzq.value = ((int[]) this.value).clone();
                } else if (this.value instanceof long[]) {
                    com_google_android_gms_internal_wearable_zzq.value = ((long[]) this.value).clone();
                } else if (this.value instanceof float[]) {
                    com_google_android_gms_internal_wearable_zzq.value = ((float[]) this.value).clone();
                } else if (this.value instanceof double[]) {
                    com_google_android_gms_internal_wearable_zzq.value = ((double[]) this.value).clone();
                } else if (this.value instanceof zzt[]) {
                    zzt[] com_google_android_gms_internal_wearable_zztArr = (zzt[]) this.value;
                    r4 = new zzt[com_google_android_gms_internal_wearable_zztArr.length];
                    com_google_android_gms_internal_wearable_zzq.value = r4;
                    for (r2 = 0; r2 < com_google_android_gms_internal_wearable_zztArr.length; r2++) {
                        r4[r2] = (zzt) com_google_android_gms_internal_wearable_zztArr[r2].clone();
                    }
                }
            }
            return com_google_android_gms_internal_wearable_zzq;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    public final /* synthetic */ Object clone() throws CloneNotSupportedException {
        return zzt();
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzq)) {
            return false;
        }
        zzq com_google_android_gms_internal_wearable_zzq = (zzq) obj;
        if (this.value != null && com_google_android_gms_internal_wearable_zzq.value != null) {
            return this.zzhi == com_google_android_gms_internal_wearable_zzq.zzhi ? !this.zzhi.zzhd.isArray() ? this.value.equals(com_google_android_gms_internal_wearable_zzq.value) : this.value instanceof byte[] ? Arrays.equals((byte[]) this.value, (byte[]) com_google_android_gms_internal_wearable_zzq.value) : this.value instanceof int[] ? Arrays.equals((int[]) this.value, (int[]) com_google_android_gms_internal_wearable_zzq.value) : this.value instanceof long[] ? Arrays.equals((long[]) this.value, (long[]) com_google_android_gms_internal_wearable_zzq.value) : this.value instanceof float[] ? Arrays.equals((float[]) this.value, (float[]) com_google_android_gms_internal_wearable_zzq.value) : this.value instanceof double[] ? Arrays.equals((double[]) this.value, (double[]) com_google_android_gms_internal_wearable_zzq.value) : this.value instanceof boolean[] ? Arrays.equals((boolean[]) this.value, (boolean[]) com_google_android_gms_internal_wearable_zzq.value) : Arrays.deepEquals((Object[]) this.value, (Object[]) com_google_android_gms_internal_wearable_zzq.value) : false;
        } else {
            if (this.zzhj != null && com_google_android_gms_internal_wearable_zzq.zzhj != null) {
                return this.zzhj.equals(com_google_android_gms_internal_wearable_zzq.zzhj);
            }
            try {
                return Arrays.equals(toByteArray(), com_google_android_gms_internal_wearable_zzq.toByteArray());
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

    final void zza(zzl com_google_android_gms_internal_wearable_zzl) throws IOException {
        if (this.value != null) {
            throw new NoSuchMethodError();
        }
        for (zzv com_google_android_gms_internal_wearable_zzv : this.zzhj) {
            com_google_android_gms_internal_wearable_zzl.zzl(com_google_android_gms_internal_wearable_zzv.tag);
            com_google_android_gms_internal_wearable_zzl.zzc(com_google_android_gms_internal_wearable_zzv.zzhm);
        }
    }

    final void zza(zzv com_google_android_gms_internal_wearable_zzv) throws IOException {
        if (this.zzhj != null) {
            this.zzhj.add(com_google_android_gms_internal_wearable_zzv);
        } else if (this.value instanceof zzt) {
            byte[] bArr = com_google_android_gms_internal_wearable_zzv.zzhm;
            zzk zza = zzk.zza(bArr, 0, bArr.length);
            int zzk = zza.zzk();
            if (zzk != bArr.length - zzl.zzi(zzk)) {
                throw zzs.zzu();
            }
            zzt zza2 = ((zzt) this.value).zza(zza);
            this.zzhi = this.zzhi;
            this.value = zza2;
            this.zzhj = null;
        } else if (this.value instanceof zzt[]) {
            Collections.singletonList(com_google_android_gms_internal_wearable_zzv);
            throw new NoSuchMethodError();
        } else {
            Collections.singletonList(com_google_android_gms_internal_wearable_zzv);
            throw new NoSuchMethodError();
        }
    }

    final int zzg() {
        if (this.value != null) {
            throw new NoSuchMethodError();
        }
        int i = 0;
        for (zzv com_google_android_gms_internal_wearable_zzv : this.zzhj) {
            i = (com_google_android_gms_internal_wearable_zzv.zzhm.length + (zzl.zzm(com_google_android_gms_internal_wearable_zzv.tag) + 0)) + i;
        }
        return i;
    }
}
