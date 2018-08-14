package com.google.android.gms.iid;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

final class zzag extends Handler {
    private final /* synthetic */ zzaf zzcx;

    zzag(zzaf com_google_android_gms_iid_zzaf, Looper looper) {
        this.zzcx = com_google_android_gms_iid_zzaf;
        super(looper);
    }

    public final void handleMessage(Message message) {
        this.zzcx.zze(message);
    }
}
