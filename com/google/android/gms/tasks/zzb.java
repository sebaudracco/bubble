package com.google.android.gms.tasks;

final class zzb implements OnSuccessListener<Void> {
    private final /* synthetic */ OnTokenCanceledListener zzafi;

    zzb(zza com_google_android_gms_tasks_zza, OnTokenCanceledListener onTokenCanceledListener) {
        this.zzafi = onTokenCanceledListener;
    }

    public final /* synthetic */ void onSuccess(Object obj) {
        this.zzafi.onCanceled();
    }
}
