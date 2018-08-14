package com.oneaudience.sdk.model;

public class CellTowerInfo {
    int baseStationId;
    int cid;
    int dbm;
    boolean isRegistered;
    int lac;
    int latitude;
    int level;
    int longitude;
    int mcc;
    int mnc;
    int networkId;
    int systemId;
    String type;

    public CellTowerInfo(String str, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, boolean z) {
        this.type = str;
        this.cid = i;
        this.lac = i2;
        this.mcc = i3;
        this.mnc = i4;
        this.baseStationId = i5;
        this.latitude = i6;
        this.longitude = i7;
        this.networkId = i8;
        this.systemId = i9;
        this.level = i10;
        this.dbm = i11;
        this.isRegistered = z;
    }
}
