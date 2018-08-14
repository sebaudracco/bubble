package com.altbeacon.beacon;

import android.bluetooth.BluetoothDevice;
import com.stepleaderdigital.reveal.Reveal.PDUiBeacon;
import org.altbeacon.beacon.BeaconParser;

public class C0820a extends C0819c {
    public C0820a() {
        this.u = new int[]{PDUiBeacon.Radius};
        m1576a(BeaconParser.ALTBEACON_LAYOUT);
        this.t = "altbeacon";
    }

    public Beacon mo1871a(byte[] bArr, int i, BluetoothDevice bluetoothDevice) {
        return m1575a(bArr, i, bluetoothDevice, new AltBeacon());
    }
}
