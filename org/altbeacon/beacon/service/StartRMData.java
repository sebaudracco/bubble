package org.altbeacon.beacon.service;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import java.io.Serializable;
import org.altbeacon.beacon.Region;

public class StartRMData implements Serializable, Parcelable {
    private static final String BACKGROUND_FLAG_KEY = "backgroundFlag";
    private static final String BETWEEN_SCAN_PERIOD_KEY = "betweenScanPeriod";
    private static final String CALLBACK_PACKAGE_NAME_KEY = "callbackPackageName";
    public static final Creator<StartRMData> CREATOR = new C47931();
    private static final String REGION_KEY = "region";
    private static final String SCAN_PERIOD_KEY = "scanPeriod";
    private boolean mBackgroundFlag;
    private long mBetweenScanPeriod;
    private String mCallbackPackageName;
    private Region mRegion;
    private long mScanPeriod;

    static class C47931 implements Creator<StartRMData> {
        C47931() {
        }

        public StartRMData createFromParcel(Parcel in) {
            return new StartRMData(in);
        }

        public StartRMData[] newArray(int size) {
            return new StartRMData[size];
        }
    }

    private StartRMData() {
    }

    public StartRMData(@NonNull Region region, @NonNull String callbackPackageName) {
        this.mRegion = region;
        this.mCallbackPackageName = callbackPackageName;
    }

    public StartRMData(long scanPeriod, long betweenScanPeriod, boolean backgroundFlag) {
        this.mScanPeriod = scanPeriod;
        this.mBetweenScanPeriod = betweenScanPeriod;
        this.mBackgroundFlag = backgroundFlag;
    }

    public StartRMData(@NonNull Region region, @NonNull String callbackPackageName, long scanPeriod, long betweenScanPeriod, boolean backgroundFlag) {
        this.mScanPeriod = scanPeriod;
        this.mBetweenScanPeriod = betweenScanPeriod;
        this.mRegion = region;
        this.mCallbackPackageName = callbackPackageName;
        this.mBackgroundFlag = backgroundFlag;
    }

    public long getScanPeriod() {
        return this.mScanPeriod;
    }

    public long getBetweenScanPeriod() {
        return this.mBetweenScanPeriod;
    }

    public Region getRegionData() {
        return this.mRegion;
    }

    public String getCallbackPackageName() {
        return this.mCallbackPackageName;
    }

    public boolean getBackgroundFlag() {
        return this.mBackgroundFlag;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeParcelable(this.mRegion, flags);
        out.writeString(this.mCallbackPackageName);
        out.writeLong(this.mScanPeriod);
        out.writeLong(this.mBetweenScanPeriod);
        out.writeByte((byte) (this.mBackgroundFlag ? 1 : 0));
    }

    private StartRMData(Parcel in) {
        this.mRegion = (Region) in.readParcelable(StartRMData.class.getClassLoader());
        this.mCallbackPackageName = in.readString();
        this.mScanPeriod = in.readLong();
        this.mBetweenScanPeriod = in.readLong();
        this.mBackgroundFlag = in.readByte() != (byte) 0;
    }

    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putLong(SCAN_PERIOD_KEY, this.mScanPeriod);
        bundle.putLong(BETWEEN_SCAN_PERIOD_KEY, this.mBetweenScanPeriod);
        bundle.putBoolean(BACKGROUND_FLAG_KEY, this.mBackgroundFlag);
        bundle.putString(CALLBACK_PACKAGE_NAME_KEY, this.mCallbackPackageName);
        if (this.mRegion != null) {
            bundle.putSerializable("region", this.mRegion);
        }
        return bundle;
    }

    public static StartRMData fromBundle(@NonNull Bundle bundle) {
        bundle.setClassLoader(Region.class.getClassLoader());
        boolean valid = false;
        StartRMData data = new StartRMData();
        if (bundle.containsKey("region")) {
            data.mRegion = (Region) bundle.getSerializable("region");
            valid = true;
        }
        if (bundle.containsKey(SCAN_PERIOD_KEY)) {
            data.mScanPeriod = ((Long) bundle.get(SCAN_PERIOD_KEY)).longValue();
            valid = true;
        }
        if (bundle.containsKey(BETWEEN_SCAN_PERIOD_KEY)) {
            data.mBetweenScanPeriod = ((Long) bundle.get(BETWEEN_SCAN_PERIOD_KEY)).longValue();
        }
        if (bundle.containsKey(BACKGROUND_FLAG_KEY)) {
            data.mBackgroundFlag = ((Boolean) bundle.get(BACKGROUND_FLAG_KEY)).booleanValue();
        }
        if (bundle.containsKey(CALLBACK_PACKAGE_NAME_KEY)) {
            data.mCallbackPackageName = (String) bundle.get(CALLBACK_PACKAGE_NAME_KEY);
        }
        return valid ? data : null;
    }
}
