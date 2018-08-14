package org.altbeacon.beacon;

import java.util.List;

public class Beacon$Builder {
    protected final Beacon mBeacon = new Beacon();
    private Identifier mId1;
    private Identifier mId2;
    private Identifier mId3;

    public Beacon build() {
        if (this.mId1 != null) {
            this.mBeacon.mIdentifiers.add(this.mId1);
            if (this.mId2 != null) {
                this.mBeacon.mIdentifiers.add(this.mId2);
                if (this.mId3 != null) {
                    this.mBeacon.mIdentifiers.add(this.mId3);
                }
            }
        }
        return this.mBeacon;
    }

    public Beacon$Builder copyBeaconFields(Beacon beacon) {
        setIdentifiers(beacon.getIdentifiers());
        setBeaconTypeCode(beacon.getBeaconTypeCode());
        setDataFields(beacon.getDataFields());
        setBluetoothAddress(beacon.getBluetoothAddress());
        setBluetoothName(beacon.getBluetoothName());
        setExtraDataFields(beacon.getExtraDataFields());
        setManufacturer(beacon.getManufacturer());
        setTxPower(beacon.getTxPower());
        setRssi(beacon.getRssi());
        setServiceUuid(beacon.getServiceUuid());
        setMultiFrameBeacon(beacon.isMultiFrameBeacon());
        return this;
    }

    public Beacon$Builder setIdentifiers(List<Identifier> identifiers) {
        this.mId1 = null;
        this.mId2 = null;
        this.mId3 = null;
        this.mBeacon.mIdentifiers = identifiers;
        return this;
    }

    public Beacon$Builder setId1(String id1String) {
        this.mId1 = Identifier.parse(id1String);
        return this;
    }

    public Beacon$Builder setId2(String id2String) {
        this.mId2 = Identifier.parse(id2String);
        return this;
    }

    public Beacon$Builder setId3(String id3String) {
        this.mId3 = Identifier.parse(id3String);
        return this;
    }

    public Beacon$Builder setRssi(int rssi) {
        this.mBeacon.mRssi = rssi;
        return this;
    }

    public Beacon$Builder setRunningAverageRssi(double rssi) {
        Beacon.access$002(this.mBeacon, Double.valueOf(rssi));
        return this;
    }

    public Beacon$Builder setTxPower(int txPower) {
        this.mBeacon.mTxPower = txPower;
        return this;
    }

    public Beacon$Builder setBeaconTypeCode(int beaconTypeCode) {
        this.mBeacon.mBeaconTypeCode = beaconTypeCode;
        return this;
    }

    public Beacon$Builder setServiceUuid(int serviceUuid) {
        this.mBeacon.mServiceUuid = serviceUuid;
        return this;
    }

    public Beacon$Builder setBluetoothAddress(String bluetoothAddress) {
        this.mBeacon.mBluetoothAddress = bluetoothAddress;
        return this;
    }

    public Beacon$Builder setDataFields(List<Long> dataFields) {
        this.mBeacon.mDataFields = dataFields;
        return this;
    }

    public Beacon$Builder setExtraDataFields(List<Long> extraDataFields) {
        this.mBeacon.mExtraDataFields = extraDataFields;
        return this;
    }

    public Beacon$Builder setManufacturer(int manufacturer) {
        this.mBeacon.mManufacturer = manufacturer;
        return this;
    }

    public Beacon$Builder setBluetoothName(String name) {
        this.mBeacon.mBluetoothName = name;
        return this;
    }

    public Beacon$Builder setParserIdentifier(String id) {
        this.mBeacon.mParserIdentifier = id;
        return this;
    }

    public Beacon$Builder setMultiFrameBeacon(boolean multiFrameBeacon) {
        this.mBeacon.mMultiFrameBeacon = multiFrameBeacon;
        return this;
    }
}
