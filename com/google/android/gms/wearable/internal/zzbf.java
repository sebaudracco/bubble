package com.google.android.gms.wearable.internal;

import android.content.IntentFilter;
import android.os.RemoteException;
import com.google.android.gms.common.api.internal.BaseImplementation.ResultHolder;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.wearable.ChannelApi.ChannelListener;

final class zzbf implements zzc<ChannelListener> {
    private final /* synthetic */ IntentFilter[] zzbr;
    private final /* synthetic */ String zzcs;

    zzbf(String str, IntentFilter[] intentFilterArr) {
        this.zzcs = str;
        this.zzbr = intentFilterArr;
    }

    public final /* synthetic */ void zza(zzhg com_google_android_gms_wearable_internal_zzhg, ResultHolder resultHolder, Object obj, ListenerHolder listenerHolder) throws RemoteException {
        com_google_android_gms_wearable_internal_zzhg.zza(resultHolder, (ChannelListener) obj, listenerHolder, this.zzcs, this.zzbr);
    }
}
