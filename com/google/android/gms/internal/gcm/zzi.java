package com.google.android.gms.internal.gcm;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

final class zzi extends WeakReference<Throwable> {
    private final int zzdh;

    public zzi(Throwable th, ReferenceQueue<Throwable> referenceQueue) {
        super(th, referenceQueue);
        if (th == null) {
            throw new NullPointerException("The referent cannot be null");
        }
        this.zzdh = System.identityHashCode(th);
    }

    public final boolean equals(Object obj) {
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        zzi com_google_android_gms_internal_gcm_zzi = (zzi) obj;
        return this.zzdh == com_google_android_gms_internal_gcm_zzi.zzdh && get() == com_google_android_gms_internal_gcm_zzi.get();
    }

    public final int hashCode() {
        return this.zzdh;
    }
}
