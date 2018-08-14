package com.google.android.gms.internal.measurement;

public class zzzy {
    private static final zzzi zzbsw = zzzi.zzte();
    private zzyw zzbsx;
    private volatile zzaal zzbsy;
    private volatile zzyw zzbsz;

    private final zzaal zzb(zzaal com_google_android_gms_internal_measurement_zzaal) {
        if (this.zzbsy == null) {
            synchronized (this) {
                if (this.zzbsy != null) {
                } else {
                    try {
                        this.zzbsy = com_google_android_gms_internal_measurement_zzaal;
                        this.zzbsz = zzyw.zzbqx;
                    } catch (zzzt e) {
                        this.zzbsy = com_google_android_gms_internal_measurement_zzaal;
                        this.zzbsz = zzyw.zzbqx;
                    }
                }
            }
        }
        return this.zzbsy;
    }

    private final zzyw zztp() {
        if (this.zzbsz != null) {
            return this.zzbsz;
        }
        synchronized (this) {
            if (this.zzbsz != null) {
                zzyw com_google_android_gms_internal_measurement_zzyw = this.zzbsz;
                return com_google_android_gms_internal_measurement_zzyw;
            }
            if (this.zzbsy == null) {
                this.zzbsz = zzyw.zzbqx;
            } else {
                this.zzbsz = this.zzbsy.zztp();
            }
            com_google_android_gms_internal_measurement_zzyw = this.zzbsz;
            return com_google_android_gms_internal_measurement_zzyw;
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzzy)) {
            return false;
        }
        zzzy com_google_android_gms_internal_measurement_zzzy = (zzzy) obj;
        zzaal com_google_android_gms_internal_measurement_zzaal = this.zzbsy;
        zzaal com_google_android_gms_internal_measurement_zzaal2 = com_google_android_gms_internal_measurement_zzzy.zzbsy;
        return (com_google_android_gms_internal_measurement_zzaal == null && com_google_android_gms_internal_measurement_zzaal2 == null) ? zztp().equals(com_google_android_gms_internal_measurement_zzzy.zztp()) : (com_google_android_gms_internal_measurement_zzaal == null || com_google_android_gms_internal_measurement_zzaal2 == null) ? com_google_android_gms_internal_measurement_zzaal != null ? com_google_android_gms_internal_measurement_zzaal.equals(com_google_android_gms_internal_measurement_zzzy.zzb(com_google_android_gms_internal_measurement_zzaal.zztz())) : zzb(com_google_android_gms_internal_measurement_zzaal2.zztz()).equals(com_google_android_gms_internal_measurement_zzaal2) : com_google_android_gms_internal_measurement_zzaal.equals(com_google_android_gms_internal_measurement_zzaal2);
    }

    public int hashCode() {
        return 1;
    }

    public final zzaal zzc(zzaal com_google_android_gms_internal_measurement_zzaal) {
        zzaal com_google_android_gms_internal_measurement_zzaal2 = this.zzbsy;
        this.zzbsx = null;
        this.zzbsz = null;
        this.zzbsy = com_google_android_gms_internal_measurement_zzaal;
        return com_google_android_gms_internal_measurement_zzaal2;
    }
}
