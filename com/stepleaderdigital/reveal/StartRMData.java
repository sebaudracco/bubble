package com.stepleaderdigital.reveal;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class StartRMData implements Parcelable {
    public static final Creator<StartRMData> CREATOR = new C40271();
    private boolean backgroundFlag;
    private long betweenScanPeriod;
    private String callbackPackageName;
    private long scanPeriod;

    static class C40271 implements Creator<StartRMData> {
        C40271() {
        }

        public StartRMData createFromParcel(Parcel in) {
            return new StartRMData(in);
        }

        public StartRMData[] newArray(int size) {
            return new StartRMData[size];
        }
    }

    public StartRMData(String callbackPackageName) {
        this.callbackPackageName = callbackPackageName;
    }

    public StartRMData(long scanPeriod, long betweenScanPeriod, boolean backgroundFlag) {
        this.scanPeriod = scanPeriod;
        this.betweenScanPeriod = betweenScanPeriod;
        this.backgroundFlag = backgroundFlag;
    }

    public StartRMData(String callbackPackageName, long scanPeriod, long betweenScanPeriod, boolean backgroundFlag) {
        this.scanPeriod = scanPeriod;
        this.betweenScanPeriod = betweenScanPeriod;
        this.callbackPackageName = callbackPackageName;
        this.backgroundFlag = backgroundFlag;
    }

    public long getScanPeriod() {
        return this.scanPeriod;
    }

    public long getBetweenScanPeriod() {
        return this.betweenScanPeriod;
    }

    public String getCallbackPackageName() {
        return this.callbackPackageName;
    }

    public boolean getBackgroundFlag() {
        return this.backgroundFlag;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeString(this.callbackPackageName);
        out.writeLong(this.scanPeriod);
        out.writeLong(this.betweenScanPeriod);
        out.writeByte((byte) (this.backgroundFlag ? 1 : 0));
    }

    private StartRMData(Parcel in) {
        this.callbackPackageName = in.readString();
        this.scanPeriod = in.readLong();
        this.betweenScanPeriod = in.readLong();
        this.backgroundFlag = in.readByte() != (byte) 0;
    }
}
