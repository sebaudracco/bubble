package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public interface ListenerHolder$Notifier<L> {
    @KeepForSdk
    void notifyListener(L l);

    @KeepForSdk
    void onNotifyListenerFailed();
}
