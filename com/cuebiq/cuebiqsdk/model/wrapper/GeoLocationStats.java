package com.cuebiq.cuebiqsdk.model.wrapper;

import android.content.Context;
import com.cuebiq.cuebiqsdk.CuebiqSDKImpl;
import com.cuebiq.cuebiqsdk.model.persistence.PersistenceManagerFactory;
import com.cuebiq.cuebiqsdk.utils.Utils;

public class GeoLocationStats {
    private Boolean afterCoverage;
    private String appKey;
    private String googleAid;
    private Boolean isGAIDOptout;
    private Boolean locationON;
    private String mcc;
    private String mnc;
    private Boolean wifiAlwaysScanning;
    private Boolean wifiEnabled;

    public static GeoLocationStats build(Context context, Boolean bool) {
        GeoLocationStats geoLocationStats = new GeoLocationStats();
        geoLocationStats.setAfterCoverage(bool);
        geoLocationStats.setAppKey(PersistenceManagerFactory.get().retrieveAppKey(context));
        geoLocationStats.setLocationON(Boolean.valueOf(Utils.isLocationEnabled(context)));
        geoLocationStats.setWifiEnabled(Boolean.valueOf(Utils.isWifiEnabled(context)));
        geoLocationStats.setWifiAlwaysScanning(Boolean.valueOf(Utils.isWifiAlwaysScanning(context)));
        String[] mCCandMNC = Utils.getMCCandMNC(context);
        if (mCCandMNC != null) {
            try {
                geoLocationStats.setMcc(mCCandMNC[0]);
                geoLocationStats.setMnc(mCCandMNC[1]);
            } catch (Throwable th) {
            }
        }
        return geoLocationStats;
    }

    public String getAppKey() {
        return this.appKey;
    }

    public String getGoogleAid() {
        return this.googleAid;
    }

    public String getMcc() {
        return this.mcc;
    }

    public String getMnc() {
        return this.mnc;
    }

    public Boolean isAfterCoverage() {
        return this.afterCoverage;
    }

    public Boolean isGAIDOptout() {
        return this.isGAIDOptout;
    }

    public Boolean isLocationON() {
        return this.locationON;
    }

    public Boolean isWifiAlwaysScanning() {
        return this.wifiAlwaysScanning;
    }

    public Boolean isWifiEnabled() {
        return this.wifiEnabled;
    }

    public void setAfterCoverage(Boolean bool) {
        this.afterCoverage = bool;
    }

    public void setAppKey(String str) {
        this.appKey = str;
    }

    public void setGoogleAid(String str) {
        this.googleAid = str;
    }

    public void setIsGAIDOptout(Boolean bool) {
        this.isGAIDOptout = bool;
    }

    public void setLocationON(Boolean bool) {
        this.locationON = bool;
    }

    public void setMcc(String str) {
        this.mcc = str;
    }

    public void setMnc(String str) {
        this.mnc = str;
    }

    public void setWifiAlwaysScanning(Boolean bool) {
        this.wifiAlwaysScanning = bool;
    }

    public void setWifiEnabled(Boolean bool) {
        this.wifiEnabled = bool;
    }

    public String toString() {
        return CuebiqSDKImpl.GSON.toJson((Object) this);
    }
}
