package com.google.android.gms.iid;

import android.content.Intent;

final class zzf implements Runnable {
    private final /* synthetic */ Intent zzbb;
    private final /* synthetic */ Intent zzbc;
    private final /* synthetic */ zze zzbd;

    zzf(zze com_google_android_gms_iid_zze, Intent intent, Intent intent2) {
        this.zzbd = com_google_android_gms_iid_zze;
        this.zzbb = intent;
        this.zzbc = intent2;
    }

    public final void run() {
        this.zzbd.handleIntent(this.zzbb);
        this.zzbd.zzf(this.zzbc);
    }
}
