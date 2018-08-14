package com.google.android.gms.internal.measurement;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.annotation.MainThread;

final class zzfz implements ServiceConnection {
    final /* synthetic */ zzfx zzaky;

    private zzfz(zzfx com_google_android_gms_internal_measurement_zzfx) {
        this.zzaky = com_google_android_gms_internal_measurement_zzfx;
    }

    @MainThread
    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        if (iBinder == null) {
            this.zzaky.zzacw.zzge().zzip().log("Install Referrer connection returned with null binder");
            return;
        }
        try {
            this.zzaky.zzakw = zzs.zza(iBinder);
            if (this.zzaky.zzakw == null) {
                this.zzaky.zzacw.zzge().zzip().log("Install Referrer Service implementation was not found");
                return;
            }
            this.zzaky.zzacw.zzge().zzir().log("Install Referrer Service connected");
            this.zzaky.zzacw.zzgd().zzc(new zzga(this));
        } catch (Exception e) {
            this.zzaky.zzacw.zzge().zzip().zzg("Exception occurred while calling Install Referrer API", e);
        }
    }

    @MainThread
    public final void onServiceDisconnected(ComponentName componentName) {
        this.zzaky.zzakw = null;
        this.zzaky.zzacw.zzge().zzir().log("Install Referrer Service disconnected");
    }
}
