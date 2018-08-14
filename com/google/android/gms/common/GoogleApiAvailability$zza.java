package com.google.android.gms.common;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

@SuppressLint({"HandlerLeak"})
class GoogleApiAvailability$zza extends Handler {
    private final Context zzau;
    private final /* synthetic */ GoogleApiAvailability zzav;

    public GoogleApiAvailability$zza(GoogleApiAvailability googleApiAvailability, Context context) {
        this.zzav = googleApiAvailability;
        super(Looper.myLooper() == null ? Looper.getMainLooper() : Looper.myLooper());
        this.zzau = context.getApplicationContext();
    }

    public final void handleMessage(Message message) {
        switch (message.what) {
            case 1:
                int isGooglePlayServicesAvailable = this.zzav.isGooglePlayServicesAvailable(this.zzau);
                if (this.zzav.isUserResolvableError(isGooglePlayServicesAvailable)) {
                    this.zzav.showErrorNotification(this.zzau, isGooglePlayServicesAvailable);
                    return;
                }
                return;
            default:
                Log.w("GoogleApiAvailability", "Don't know how to handle this message: " + message.what);
                return;
        }
    }
}
