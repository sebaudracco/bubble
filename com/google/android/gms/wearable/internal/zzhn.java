package com.google.android.gms.wearable.internal;

import com.google.android.gms.common.api.internal.ListenerHolder$Notifier;
import com.google.android.gms.wearable.ChannelApi.ChannelListener;

final class zzhn implements ListenerHolder$Notifier<ChannelListener> {
    private final /* synthetic */ zzaw zzav;

    zzhn(zzaw com_google_android_gms_wearable_internal_zzaw) {
        this.zzav = com_google_android_gms_wearable_internal_zzaw;
    }

    public final /* synthetic */ void notifyListener(Object obj) {
        this.zzav.zza((ChannelListener) obj);
    }

    public final void onNotifyListenerFailed() {
    }
}
