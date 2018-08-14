package com.google.android.gms.common.api.internal;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.google.android.gms.common.internal.Preconditions;

final class ListenerHolder$zza extends Handler {
    private final /* synthetic */ ListenerHolder zzlk;

    public ListenerHolder$zza(ListenerHolder listenerHolder, Looper looper) {
        this.zzlk = listenerHolder;
        super(looper);
    }

    public final void handleMessage(Message message) {
        boolean z = true;
        if (message.what != 1) {
            z = false;
        }
        Preconditions.checkArgument(z);
        this.zzlk.notifyListenerInternal((ListenerHolder$Notifier) message.obj);
    }
}
