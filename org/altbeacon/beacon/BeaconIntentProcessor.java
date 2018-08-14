package org.altbeacon.beacon;

import android.app.IntentService;
import android.content.Intent;

public class BeaconIntentProcessor extends IntentService {
    private static final String TAG = "BeaconIntentProcessor";

    public BeaconIntentProcessor() {
        super(TAG);
    }

    protected void onHandleIntent(Intent intent) {
        new IntentHandler().convertIntentsToCallbacks(getApplicationContext(), intent);
    }
}
