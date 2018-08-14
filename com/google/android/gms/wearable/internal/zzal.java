package com.google.android.gms.wearable.internal;

import android.content.IntentFilter;
import android.os.RemoteException;
import com.google.android.gms.common.api.internal.BaseImplementation.ResultHolder;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.wearable.ChannelApi.ChannelListener;

final class zzal implements zzc<ChannelListener> {
    private final /* synthetic */ IntentFilter[] zzbr;

    zzal(IntentFilter[] intentFilterArr) {
        this.zzbr = intentFilterArr;
    }

    public final /* synthetic */ void zza(zzhg com_google_android_gms_wearable_internal_zzhg, ResultHolder resultHolder, Object obj, ListenerHolder listenerHolder) throws RemoteException {
        com_google_android_gms_wearable_internal_zzhg.zza(resultHolder, (ChannelListener) obj, listenerHolder, null, this.zzbr);
    }
}
