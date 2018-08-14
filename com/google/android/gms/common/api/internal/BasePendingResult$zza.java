package com.google.android.gms.common.api.internal;

final class BasePendingResult$zza {
    private final /* synthetic */ BasePendingResult zzfn;

    private BasePendingResult$zza(BasePendingResult basePendingResult) {
        this.zzfn = basePendingResult;
    }

    protected final void finalize() throws Throwable {
        BasePendingResult.zzb(BasePendingResult.zza(this.zzfn));
        super.finalize();
    }
}
