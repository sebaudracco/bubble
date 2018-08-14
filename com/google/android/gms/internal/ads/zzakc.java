package com.google.android.gms.internal.ads;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.google.android.gms.ads.internal.zzbv;

@zzadh
public final class zzakc extends Handler {
    public zzakc(Looper looper) {
        super(looper);
    }

    public final void dispatchMessage(Message message) {
        try {
            super.dispatchMessage(message);
        } catch (Throwable th) {
            zzbv.zzek();
            zzakk.zza(zzbv.zzeo().getApplicationContext(), th);
        }
    }

    public final void handleMessage(Message message) {
        try {
            super.handleMessage(message);
        } catch (Throwable e) {
            zzbv.zzeo().zza(e, "AdMobHandler.handleMessage");
        }
    }
}
