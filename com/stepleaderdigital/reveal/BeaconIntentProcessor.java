package com.stepleaderdigital.reveal;

import android.app.IntentService;
import android.content.Intent;
import com.stepleaderdigital.reveal.Reveal.RevealLogger;

public class BeaconIntentProcessor extends IntentService {
    public BeaconIntentProcessor() {
        super("BeaconIntentProcessor");
    }

    protected void onHandleIntent(Intent intent) {
        RevealLogger.m12430d("Received a beacon intent: " + intent);
    }
}
