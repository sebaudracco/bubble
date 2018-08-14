package com.google.android.gms.iid;

import android.os.Handler.Callback;
import android.os.Message;

final /* synthetic */ class zzu implements Callback {
    private final zzt zzch;

    zzu(zzt com_google_android_gms_iid_zzt) {
        this.zzch = com_google_android_gms_iid_zzt;
    }

    public final boolean handleMessage(Message message) {
        return this.zzch.zzd(message);
    }
}
