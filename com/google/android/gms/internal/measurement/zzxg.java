package com.google.android.gms.internal.measurement;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

final class zzxg extends WeakReference<Throwable> {
    private final int zzboc;

    public zzxg(Throwable th, ReferenceQueue<Throwable> referenceQueue) {
        super(th, null);
        if (th == null) {
            throw new NullPointerException("The referent cannot be null");
        }
        this.zzboc = System.identityHashCode(th);
    }

    public final boolean equals(Object obj) {
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        zzxg com_google_android_gms_internal_measurement_zzxg = (zzxg) obj;
        return this.zzboc == com_google_android_gms_internal_measurement_zzxg.zzboc && get() == com_google_android_gms_internal_measurement_zzxg.get();
    }

    public final int hashCode() {
        return this.zzboc;
    }
}
