package org.altbeacon.beacon;

import android.bluetooth.le.AdvertiseCallback;
import android.bluetooth.le.AdvertiseSettings;
import org.altbeacon.beacon.logging.LogManager;

class BeaconTransmitter$1 extends AdvertiseCallback {
    final /* synthetic */ BeaconTransmitter this$0;

    BeaconTransmitter$1(BeaconTransmitter this$0) {
        this.this$0 = this$0;
    }

    public void onStartFailure(int errorCode) {
        LogManager.m16373e("BeaconTransmitter", "Advertisement start failed, code: %s", Integer.valueOf(errorCode));
        if (BeaconTransmitter.access$000(this.this$0) != null) {
            BeaconTransmitter.access$000(this.this$0).onStartFailure(errorCode);
        }
    }

    public void onStartSuccess(AdvertiseSettings settingsInEffect) {
        LogManager.m16375i("BeaconTransmitter", "Advertisement start succeeded.", new Object[0]);
        BeaconTransmitter.access$102(this.this$0, true);
        if (BeaconTransmitter.access$000(this.this$0) != null) {
            BeaconTransmitter.access$000(this.this$0).onStartSuccess(settingsInEffect);
        }
    }
}
