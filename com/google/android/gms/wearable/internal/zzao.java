package com.google.android.gms.wearable.internal;

import android.app.Activity;
import android.content.Context;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Looper;
import android.support.annotation.NonNull;
import com.google.android.gms.common.api.GoogleApi.Settings;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.ListenerHolders;
import com.google.android.gms.common.internal.PendingResultUtil;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.wearable.Channel;
import com.google.android.gms.wearable.ChannelApi.ChannelListener;
import com.google.android.gms.wearable.ChannelClient;
import com.google.android.gms.wearable.ChannelClient.ChannelCallback;
import java.io.InputStream;
import java.io.OutputStream;

public final class zzao extends ChannelClient {
    private final zzaj zzcg = new zzaj();

    public zzao(@NonNull Activity activity, @NonNull Settings settings) {
        super(activity, settings);
    }

    public zzao(@NonNull Context context, @NonNull Settings settings) {
        super(context, settings);
    }

    private static zzay zza(@NonNull Channel channel) {
        Preconditions.checkNotNull(channel, "channel must not be null");
        return (zzay) channel;
    }

    private static zzay zza(@NonNull ChannelClient.Channel channel) {
        Preconditions.checkNotNull(channel, "channel must not be null");
        return (zzay) channel;
    }

    public final Task<Void> close(@NonNull ChannelClient.Channel channel) {
        return PendingResultUtil.toVoidTask(zza(channel).close(asGoogleApiClient()));
    }

    public final Task<Void> close(@NonNull ChannelClient.Channel channel, int i) {
        return PendingResultUtil.toVoidTask(zza(channel).close(asGoogleApiClient(), i));
    }

    public final Task<InputStream> getInputStream(@NonNull ChannelClient.Channel channel) {
        return PendingResultUtil.toTask(zza(channel).getInputStream(asGoogleApiClient()), zzaq.zzbx);
    }

    public final Task<OutputStream> getOutputStream(@NonNull ChannelClient.Channel channel) {
        return PendingResultUtil.toTask(zza(channel).getOutputStream(asGoogleApiClient()), zzar.zzbx);
    }

    public final Task<ChannelClient.Channel> openChannel(@NonNull String str, @NonNull String str2) {
        return PendingResultUtil.toTask(this.zzcg.openChannel(asGoogleApiClient(), str, str2), zzap.zzbx);
    }

    public final Task<Void> receiveFile(@NonNull ChannelClient.Channel channel, @NonNull Uri uri, boolean z) {
        return PendingResultUtil.toVoidTask(zza(channel).receiveFile(asGoogleApiClient(), uri, z));
    }

    public final Task<Void> registerChannelCallback(@NonNull ChannelClient.Channel channel, @NonNull ChannelCallback channelCallback) {
        String zzc = ((zzay) channel).zzc();
        Preconditions.checkNotNull(channelCallback, "listener is null");
        Looper looper = getLooper();
        String str = "ChannelListener:";
        String valueOf = String.valueOf(zzc);
        ListenerHolder createListenerHolder = ListenerHolders.createListenerHolder(channelCallback, looper, valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
        IntentFilter[] intentFilterArr = new IntentFilter[]{zzgj.zzc("com.google.android.gms.wearable.CHANNEL_EVENT")};
        ChannelListener com_google_android_gms_wearable_internal_zzas = new zzas(channelCallback);
        return doRegisterEventListener(new zzat(com_google_android_gms_wearable_internal_zzas, zzc, intentFilterArr, createListenerHolder, ListenerHolders.createListenerHolder(com_google_android_gms_wearable_internal_zzas, getLooper(), "ChannelListener")), new zzau(com_google_android_gms_wearable_internal_zzas, zzc, createListenerHolder.getListenerKey()));
    }

    public final Task<Void> registerChannelCallback(@NonNull ChannelCallback channelCallback) {
        Preconditions.checkNotNull(channelCallback, "listener is null");
        ListenerHolder createListenerHolder = ListenerHolders.createListenerHolder(channelCallback, getLooper(), "ChannelListener");
        IntentFilter[] intentFilterArr = new IntentFilter[]{zzgj.zzc("com.google.android.gms.wearable.CHANNEL_EVENT")};
        ChannelListener com_google_android_gms_wearable_internal_zzas = new zzas(channelCallback);
        return doRegisterEventListener(new zzat(com_google_android_gms_wearable_internal_zzas, null, intentFilterArr, createListenerHolder, ListenerHolders.createListenerHolder(com_google_android_gms_wearable_internal_zzas, getLooper(), "ChannelListener")), new zzau(com_google_android_gms_wearable_internal_zzas, null, createListenerHolder.getListenerKey()));
    }

    public final Task<Void> sendFile(@NonNull ChannelClient.Channel channel, @NonNull Uri uri) {
        return PendingResultUtil.toVoidTask(zza(channel).sendFile(asGoogleApiClient(), uri));
    }

    public final Task<Void> sendFile(@NonNull ChannelClient.Channel channel, @NonNull Uri uri, long j, long j2) {
        return PendingResultUtil.toVoidTask(zza(channel).sendFile(asGoogleApiClient(), uri, j, j2));
    }

    public final Task<Boolean> unregisterChannelCallback(@NonNull ChannelClient.Channel channel, @NonNull ChannelCallback channelCallback) {
        String zzc = zza(channel).zzc();
        Looper looper = getLooper();
        String str = "ChannelListener:";
        zzc = String.valueOf(zzc);
        return doUnregisterEventListener(ListenerHolders.createListenerHolder(channelCallback, looper, zzc.length() != 0 ? str.concat(zzc) : new String(str)).getListenerKey());
    }

    public final Task<Boolean> unregisterChannelCallback(@NonNull ChannelCallback channelCallback) {
        return doUnregisterEventListener(ListenerHolders.createListenerHolder(channelCallback, getLooper(), "ChannelListener").getListenerKey());
    }
}
