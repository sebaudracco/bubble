package com.google.android.gms.gcm;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

final class zzf extends Handler {
    private final /* synthetic */ GoogleCloudMessaging zzai;

    zzf(GoogleCloudMessaging googleCloudMessaging, Looper looper) {
        this.zzai = googleCloudMessaging;
        super(looper);
    }

    public final void handleMessage(Message message) {
        if (message == null || !(message.obj instanceof Intent)) {
            Log.w("GCM", "Dropping invalid message");
        }
        Intent intent = (Intent) message.obj;
        if ("com.google.android.c2dm.intent.REGISTRATION".equals(intent.getAction())) {
            GoogleCloudMessaging.zzd(this.zzai).add(intent);
        } else if (!GoogleCloudMessaging.zzd(this.zzai, intent)) {
            intent.setPackage(GoogleCloudMessaging.zze(this.zzai).getPackageName());
            GoogleCloudMessaging.zze(this.zzai).sendBroadcast(intent);
        }
    }
}
