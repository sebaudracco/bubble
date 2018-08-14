package com.google.android.gms.wearable.internal;

import android.content.IntentFilter;
import android.os.RemoteException;
import com.google.android.gms.common.api.internal.BaseImplementation.ResultHolder;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.wearable.DataApi.DataListener;

final class zzce implements zzc<DataListener> {
    private final /* synthetic */ IntentFilter[] zzbr;

    zzce(IntentFilter[] intentFilterArr) {
        this.zzbr = intentFilterArr;
    }

    public final /* synthetic */ void zza(zzhg com_google_android_gms_wearable_internal_zzhg, ResultHolder resultHolder, Object obj, ListenerHolder listenerHolder) throws RemoteException {
        com_google_android_gms_wearable_internal_zzhg.zza(resultHolder, (DataListener) obj, listenerHolder, this.zzbr);
    }
}
