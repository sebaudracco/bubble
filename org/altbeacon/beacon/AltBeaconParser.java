package org.altbeacon.beacon;

import android.bluetooth.BluetoothDevice;
import com.stepleaderdigital.reveal.Reveal.PDUiBeacon;

public class AltBeaconParser extends BeaconParser {
    public static final String TAG = "AltBeaconParser";

    public AltBeaconParser() {
        this.mHardwareAssistManufacturers = new int[]{PDUiBeacon.Radius};
        setBeaconLayout(BeaconParser.ALTBEACON_LAYOUT);
        this.mIdentifier = "altbeacon";
    }

    public Beacon fromScanData(byte[] scanData, int rssi, BluetoothDevice device) {
        return fromScanData(scanData, rssi, device, new AltBeacon());
    }
}
