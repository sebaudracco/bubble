package org.altbeacon.beacon;

import android.support.annotation.NonNull;

class BeaconManager$ConsumerInfo {
    @NonNull
    public BeaconManager$BeaconServiceConnection beaconServiceConnection;
    public boolean isConnected;
    final /* synthetic */ BeaconManager this$0;

    public BeaconManager$ConsumerInfo(BeaconManager beaconManager) {
        this.this$0 = beaconManager;
        this.isConnected = false;
        this.isConnected = false;
        this.beaconServiceConnection = new BeaconManager$BeaconServiceConnection(beaconManager);
    }
}
