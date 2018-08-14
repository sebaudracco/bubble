package com.google.android.gms.tasks;

final class zzs implements OnTokenCanceledListener {
    private final /* synthetic */ TaskCompletionSource zzagc;

    zzs(TaskCompletionSource taskCompletionSource) {
        this.zzagc = taskCompletionSource;
    }

    public final void onCanceled() {
        TaskCompletionSource.zza(this.zzagc).zzdp();
    }
}
