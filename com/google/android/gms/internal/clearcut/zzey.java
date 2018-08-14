package com.google.android.gms.internal.clearcut;

import com.google.android.gms.internal.clearcut.zzcg.zzg;
import java.io.IOException;
import java.util.Arrays;

public final class zzey {
    private static final zzey zzoz = new zzey(0, new int[0], new Object[0], false);
    private int count;
    private boolean zzfa;
    private int zzjq;
    private Object[] zzmj;
    private int[] zzpa;

    private zzey() {
        this(0, new int[8], new Object[8], true);
    }

    private zzey(int i, int[] iArr, Object[] objArr, boolean z) {
        this.zzjq = -1;
        this.count = i;
        this.zzpa = iArr;
        this.zzmj = objArr;
        this.zzfa = z;
    }

    static zzey zza(zzey com_google_android_gms_internal_clearcut_zzey, zzey com_google_android_gms_internal_clearcut_zzey2) {
        int i = com_google_android_gms_internal_clearcut_zzey.count + com_google_android_gms_internal_clearcut_zzey2.count;
        Object copyOf = Arrays.copyOf(com_google_android_gms_internal_clearcut_zzey.zzpa, i);
        System.arraycopy(com_google_android_gms_internal_clearcut_zzey2.zzpa, 0, copyOf, com_google_android_gms_internal_clearcut_zzey.count, com_google_android_gms_internal_clearcut_zzey2.count);
        Object copyOf2 = Arrays.copyOf(com_google_android_gms_internal_clearcut_zzey.zzmj, i);
        System.arraycopy(com_google_android_gms_internal_clearcut_zzey2.zzmj, 0, copyOf2, com_google_android_gms_internal_clearcut_zzey.count, com_google_android_gms_internal_clearcut_zzey2.count);
        return new zzey(i, copyOf, copyOf2, true);
    }

    private static void zzb(int i, Object obj, zzfr com_google_android_gms_internal_clearcut_zzfr) throws IOException {
        int i2 = i >>> 3;
        switch (i & 7) {
            case 0:
                com_google_android_gms_internal_clearcut_zzfr.zzi(i2, ((Long) obj).longValue());
                return;
            case 1:
                com_google_android_gms_internal_clearcut_zzfr.zzc(i2, ((Long) obj).longValue());
                return;
            case 2:
                com_google_android_gms_internal_clearcut_zzfr.zza(i2, (zzbb) obj);
                return;
            case 3:
                if (com_google_android_gms_internal_clearcut_zzfr.zzaj() == zzg.zzko) {
                    com_google_android_gms_internal_clearcut_zzfr.zzaa(i2);
                    ((zzey) obj).zzb(com_google_android_gms_internal_clearcut_zzfr);
                    com_google_android_gms_internal_clearcut_zzfr.zzab(i2);
                    return;
                }
                com_google_android_gms_internal_clearcut_zzfr.zzab(i2);
                ((zzey) obj).zzb(com_google_android_gms_internal_clearcut_zzfr);
                com_google_android_gms_internal_clearcut_zzfr.zzaa(i2);
                return;
            case 5:
                com_google_android_gms_internal_clearcut_zzfr.zzf(i2, ((Integer) obj).intValue());
                return;
            default:
                throw new RuntimeException(zzco.zzbn());
        }
    }

    public static zzey zzea() {
        return zzoz;
    }

    static zzey zzeb() {
        return new zzey();
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof zzey)) {
            return false;
        }
        zzey com_google_android_gms_internal_clearcut_zzey = (zzey) obj;
        if (this.count == com_google_android_gms_internal_clearcut_zzey.count) {
            int i;
            boolean z;
            int[] iArr = this.zzpa;
            int[] iArr2 = com_google_android_gms_internal_clearcut_zzey.zzpa;
            int i2 = this.count;
            for (i = 0; i < i2; i++) {
                if (iArr[i] != iArr2[i]) {
                    z = false;
                    break;
                }
            }
            z = true;
            if (z) {
                Object[] objArr = this.zzmj;
                Object[] objArr2 = com_google_android_gms_internal_clearcut_zzey.zzmj;
                i2 = this.count;
                for (i = 0; i < i2; i++) {
                    if (!objArr[i].equals(objArr2[i])) {
                        z = false;
                        break;
                    }
                }
                z = true;
                if (z) {
                    return true;
                }
            }
        }
        return false;
    }

    public final int hashCode() {
        int i;
        int i2 = 17;
        int i3 = 0;
        int i4 = (this.count + 527) * 31;
        int[] iArr = this.zzpa;
        int i5 = 17;
        for (i = 0; i < this.count; i++) {
            i5 = (i5 * 31) + iArr[i];
        }
        i = (i4 + i5) * 31;
        Object[] objArr = this.zzmj;
        while (i3 < this.count) {
            i2 = (i2 * 31) + objArr[i3].hashCode();
            i3++;
        }
        return i + i2;
    }

    final void zza(zzfr com_google_android_gms_internal_clearcut_zzfr) throws IOException {
        int i;
        if (com_google_android_gms_internal_clearcut_zzfr.zzaj() == zzg.zzkp) {
            for (i = this.count - 1; i >= 0; i--) {
                com_google_android_gms_internal_clearcut_zzfr.zza(this.zzpa[i] >>> 3, this.zzmj[i]);
            }
            return;
        }
        for (i = 0; i < this.count; i++) {
            com_google_android_gms_internal_clearcut_zzfr.zza(this.zzpa[i] >>> 3, this.zzmj[i]);
        }
    }

    final void zza(StringBuilder stringBuilder, int i) {
        for (int i2 = 0; i2 < this.count; i2++) {
            zzdr.zza(stringBuilder, i, String.valueOf(this.zzpa[i2] >>> 3), this.zzmj[i2]);
        }
    }

    public final int zzas() {
        int i = this.zzjq;
        if (i == -1) {
            i = 0;
            for (int i2 = 0; i2 < this.count; i2++) {
                int i3 = this.zzpa[i2];
                int i4 = i3 >>> 3;
                switch (i3 & 7) {
                    case 0:
                        i += zzbn.zze(i4, ((Long) this.zzmj[i2]).longValue());
                        break;
                    case 1:
                        i += zzbn.zzg(i4, ((Long) this.zzmj[i2]).longValue());
                        break;
                    case 2:
                        i += zzbn.zzc(i4, (zzbb) this.zzmj[i2]);
                        break;
                    case 3:
                        i += ((zzey) this.zzmj[i2]).zzas() + (zzbn.zzr(i4) << 1);
                        break;
                    case 5:
                        i += zzbn.zzj(i4, ((Integer) this.zzmj[i2]).intValue());
                        break;
                    default:
                        throw new IllegalStateException(zzco.zzbn());
                }
            }
            this.zzjq = i;
        }
        return i;
    }

    final void zzb(int i, Object obj) {
        if (this.zzfa) {
            if (this.count == this.zzpa.length) {
                int i2 = (this.count < 4 ? 8 : this.count >> 1) + this.count;
                this.zzpa = Arrays.copyOf(this.zzpa, i2);
                this.zzmj = Arrays.copyOf(this.zzmj, i2);
            }
            this.zzpa[this.count] = i;
            this.zzmj[this.count] = obj;
            this.count++;
            return;
        }
        throw new UnsupportedOperationException();
    }

    public final void zzb(zzfr com_google_android_gms_internal_clearcut_zzfr) throws IOException {
        if (this.count != 0) {
            int i;
            if (com_google_android_gms_internal_clearcut_zzfr.zzaj() == zzg.zzko) {
                for (i = 0; i < this.count; i++) {
                    zzb(this.zzpa[i], this.zzmj[i], com_google_android_gms_internal_clearcut_zzfr);
                }
                return;
            }
            for (i = this.count - 1; i >= 0; i--) {
                zzb(this.zzpa[i], this.zzmj[i], com_google_android_gms_internal_clearcut_zzfr);
            }
        }
    }

    public final int zzec() {
        int i = this.zzjq;
        if (i == -1) {
            i = 0;
            for (int i2 = 0; i2 < this.count; i2++) {
                i += zzbn.zzd(this.zzpa[i2] >>> 3, (zzbb) this.zzmj[i2]);
            }
            this.zzjq = i;
        }
        return i;
    }

    public final void zzv() {
        this.zzfa = false;
    }
}
