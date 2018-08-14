package com.onesignal;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;

public class GcmIntentService extends IntentService {
    public GcmIntentService() {
        super("GcmIntentService");
        setIntentRedelivery(true);
    }

    protected void onHandleIntent(Intent intent) {
        Bundle extras = intent.getExtras();
        if (extras != null) {
            NotificationBundleProcessor.ProcessFromGCMIntentService(this, extras, null);
            GcmBroadcastReceiver.completeWakefulIntent(intent);
        }
    }
}
