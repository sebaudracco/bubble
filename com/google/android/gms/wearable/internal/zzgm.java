package com.google.android.gms.wearable.internal;

import com.google.android.gms.common.api.internal.BaseImplementation.ResultHolder;

class zzgm<T> extends zza {
    private ResultHolder<T> zzet;

    public zzgm(ResultHolder<T> resultHolder) {
        this.zzet = resultHolder;
    }

    public final void zza(T t) {
        ResultHolder resultHolder = this.zzet;
        if (resultHolder != null) {
            resultHolder.setResult(t);
            this.zzet = null;
        }
    }
}
