package com.google.android.gms.common.api.internal;

import android.os.RemoteException;
import com.google.android.gms.common.Feature;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzcf extends TaskApiCall<A, ResultT> {
    private final /* synthetic */ TaskApiCall$Builder zzmc;

    zzcf(TaskApiCall$Builder taskApiCall$Builder, Feature[] featureArr, boolean z) {
        this.zzmc = taskApiCall$Builder;
        super(featureArr, z, null);
    }

    protected final void doExecute(A a, TaskCompletionSource<ResultT> taskCompletionSource) throws RemoteException {
        this.zzmc.zzmb.accept(a, taskCompletionSource);
    }
}
