package org.altbeacon.beacon.distance;

import android.os.Build;
import android.os.Build.VERSION;
import org.altbeacon.beacon.logging.LogManager;

public class AndroidModel {
    private static final String TAG = "AndroidModel";
    String mBuildNumber;
    String mManufacturer;
    String mModel;
    String mVersion;

    public AndroidModel(String version, String buildNumber, String model, String manufacturer) {
        this.mVersion = version;
        this.mBuildNumber = buildNumber;
        this.mModel = model;
        this.mManufacturer = manufacturer;
    }

    public static AndroidModel forThisDevice() {
        return new AndroidModel(VERSION.RELEASE, Build.ID, Build.MODEL, Build.MANUFACTURER);
    }

    public String getVersion() {
        return this.mVersion;
    }

    public void setVersion(String mVersion) {
        this.mVersion = mVersion;
    }

    public String getBuildNumber() {
        return this.mBuildNumber;
    }

    public String getModel() {
        return this.mModel;
    }

    public String getManufacturer() {
        return this.mManufacturer;
    }

    public void setBuildNumber(String mBuildNumber) {
        this.mBuildNumber = mBuildNumber;
    }

    public void setModel(String mModel) {
        this.mModel = mModel;
    }

    public void setManufacturer(String mManufacturer) {
        this.mManufacturer = mManufacturer;
    }

    public int matchScore(AndroidModel otherModel) {
        int score = 0;
        if (this.mManufacturer.equalsIgnoreCase(otherModel.mManufacturer)) {
            score = 1;
        }
        if (score == 1 && this.mModel.equals(otherModel.mModel)) {
            score = 2;
        }
        if (score == 2 && this.mBuildNumber.equals(otherModel.mBuildNumber)) {
            score = 3;
        }
        if (score == 3 && this.mVersion.equals(otherModel.mVersion)) {
            score = 4;
        }
        LogManager.m16371d(TAG, "Score is %s for %s compared to %s", Integer.valueOf(score), toString(), otherModel);
        return score;
    }

    public String toString() {
        return "" + this.mManufacturer + ";" + this.mModel + ";" + this.mBuildNumber + ";" + this.mVersion;
    }
}
