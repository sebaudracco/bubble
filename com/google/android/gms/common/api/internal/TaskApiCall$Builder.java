package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.Feature;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.util.BiConsumer;
import com.google.android.gms.tasks.TaskCompletionSource;

@KeepForSdk
public class TaskApiCall$Builder<A extends AnyClient, ResultT> {
    private Feature[] zzlz;
    private boolean zzma;
    private BiConsumer<A, TaskCompletionSource<ResultT>> zzmb;

    private TaskApiCall$Builder() {
        this.zzma = true;
    }

    @KeepForSdk
    public TaskApiCall<A, ResultT> build() {
        if (this.zzmb != null) {
            return new zzcf(this, this.zzlz, this.zzma);
        }
        throw new IllegalArgumentException("execute parameter required");
    }

    @KeepForSdk
    public TaskApiCall$Builder<A, ResultT> execute(BiConsumer<A, TaskCompletionSource<ResultT>> biConsumer) {
        this.zzmb = biConsumer;
        return this;
    }

    @KeepForSdk
    public TaskApiCall$Builder<A, ResultT> setAutoResolveMissingFeatures(boolean z) {
        this.zzma = z;
        return this;
    }

    @KeepForSdk
    public TaskApiCall$Builder<A, ResultT> setFeatures(Feature[] featureArr) {
        this.zzlz = featureArr;
        return this;
    }
}
