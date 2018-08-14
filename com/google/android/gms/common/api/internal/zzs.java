package com.google.android.gms.common.api.internal;

final class zzs implements Runnable {
    private final /* synthetic */ zzr zzgc;

    zzs(zzr com_google_android_gms_common_api_internal_zzr) {
        this.zzgc = com_google_android_gms_common_api_internal_zzr;
    }

    public final void run() {
        this.zzgc.zzga.lock();
        try {
            this.zzgc.zzaa();
        } finally {
            this.zzgc.zzga.unlock();
        }
    }
}
