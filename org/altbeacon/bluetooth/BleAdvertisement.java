package org.altbeacon.bluetooth;

import java.util.ArrayList;
import java.util.List;

public class BleAdvertisement {
    private static final String TAG = "BleAdvertisement";
    private byte[] mBytes;
    private List<Pdu> mPdus = parsePdus();

    public BleAdvertisement(byte[] bytes) {
        this.mBytes = bytes;
    }

    private List<Pdu> parsePdus() {
        ArrayList<Pdu> pdus = new ArrayList();
        int index = 0;
        do {
            Pdu pdu = Pdu.parse(this.mBytes, index);
            if (pdu != null) {
                index = (pdu.getDeclaredLength() + index) + 1;
                pdus.add(pdu);
            }
            if (pdu == null) {
                break;
            }
        } while (index < this.mBytes.length);
        return pdus;
    }

    public List<Pdu> getPdus() {
        return this.mPdus;
    }
}
