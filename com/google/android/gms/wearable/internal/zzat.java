package com.google.android.gms.wearable.internal;

import android.content.IntentFilter;
import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.RegisterListenerMethod;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.wearable.ChannelApi.ChannelListener;
import com.google.android.gms.wearable.ChannelClient.ChannelCallback;
import javax.annotation.Nullable;

final class zzat extends RegisterListenerMethod<zzhg, ChannelCallback> {
    private final IntentFilter[] zzba;
    @Nullable
    private final String zzce;
    private final ChannelListener zzcf;
    private final ListenerHolder<ChannelListener> zzci;

    zzat(ChannelListener channelListener, @Nullable String str, IntentFilter[] intentFilterArr, ListenerHolder<ChannelCallback> listenerHolder, ListenerHolder<ChannelListener> listenerHolder2) {
        super(listenerHolder);
        this.zzcf = channelListener;
        this.zzba = intentFilterArr;
        this.zzce = str;
        this.zzci = listenerHolder2;
    }

    protected final /* synthetic */ void registerListener(AnyClient anyClient, TaskCompletionSource taskCompletionSource) throws RemoteException {
        ((zzhg) anyClient).zza(new zzgh(taskCompletionSource), this.zzcf, this.zzci, this.zzce, this.zzba);
    }
}
