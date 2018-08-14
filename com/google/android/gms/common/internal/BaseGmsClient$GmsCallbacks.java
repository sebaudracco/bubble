package com.google.android.gms.common.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.BinderThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import com.google.android.gms.common.internal.IGmsCallbacks.Stub;
import com.google.android.gms.common.util.VisibleForTesting;

@VisibleForTesting
public final class BaseGmsClient$GmsCallbacks extends Stub {
    private BaseGmsClient zzrw;
    private final int zzrx;

    public BaseGmsClient$GmsCallbacks(@NonNull BaseGmsClient baseGmsClient, int i) {
        this.zzrw = baseGmsClient;
        this.zzrx = i;
    }

    @BinderThread
    public final void onAccountValidationComplete(int i, @Nullable Bundle bundle) {
        Log.wtf("GmsClient", "received deprecated onAccountValidationComplete callback, ignoring", new Exception());
    }

    @BinderThread
    public final void onPostInitComplete(int i, @NonNull IBinder iBinder, @Nullable Bundle bundle) {
        Preconditions.checkNotNull(this.zzrw, "onPostInitComplete can be called only once per call to getRemoteService");
        this.zzrw.onPostInitHandler(i, iBinder, bundle, this.zzrx);
        this.zzrw = null;
    }

    @BinderThread
    public final void onPostInitCompleteWithConnectionInfo(int i, @NonNull IBinder iBinder, @NonNull ConnectionInfo connectionInfo) {
        Preconditions.checkNotNull(this.zzrw, "onPostInitCompleteWithConnectionInfo can be called only once per call togetRemoteService");
        Preconditions.checkNotNull(connectionInfo);
        BaseGmsClient.zza(this.zzrw, connectionInfo);
        onPostInitComplete(i, iBinder, connectionInfo.getResolutionBundle());
    }
}
