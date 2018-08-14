package org.altbeacon.beacon.service;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.altbeacon.beacon.Beacon;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.BeaconParser;
import org.altbeacon.beacon.Region;
import org.altbeacon.beacon.logging.LogManager;

public class SettingsData implements Serializable {
    private static final String SETTINGS_DATA_KEY = "SettingsData";
    private static final String TAG = SettingsData.class.getSimpleName();
    Boolean mAndroidLScanningDisabled;
    ArrayList<BeaconParser> mBeaconParsers;
    Boolean mHardwareEqualityEnforced;
    Long mRegionExitPeriod;
    Boolean mRegionStatePersistenceEnabled;
    Boolean mUseTrackingCache;

    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putSerializable(SETTINGS_DATA_KEY, this);
        return bundle;
    }

    public static SettingsData fromBundle(@NonNull Bundle bundle) {
        bundle.setClassLoader(Region.class.getClassLoader());
        if (bundle.get(SETTINGS_DATA_KEY) != null) {
            return (SettingsData) bundle.getSerializable(SETTINGS_DATA_KEY);
        }
        return null;
    }

    public void apply(@NonNull BeaconService scanService) {
        LogManager.m16371d(TAG, "Applying settings changes to scanner in other process", new Object[0]);
        BeaconManager beaconManager = BeaconManager.getInstanceForApplication(scanService);
        List<BeaconParser> beaconParsers = beaconManager.getBeaconParsers();
        boolean beaconParsersChanged = false;
        if (beaconParsers.size() == this.mBeaconParsers.size()) {
            for (int i = 0; i < beaconParsers.size(); i++) {
                if (!((BeaconParser) beaconParsers.get(i)).equals(this.mBeaconParsers.get(i))) {
                    LogManager.m16371d(TAG, "Beacon parsers have changed to: " + ((BeaconParser) this.mBeaconParsers.get(i)).getLayout(), new Object[0]);
                    beaconParsersChanged = true;
                    break;
                }
            }
        } else {
            beaconParsersChanged = true;
            LogManager.m16371d(TAG, "Beacon parsers have been added or removed.", new Object[0]);
        }
        if (beaconParsersChanged) {
            LogManager.m16371d(TAG, "Updating beacon parsers", new Object[0]);
            beaconManager.getBeaconParsers().clear();
            beaconManager.getBeaconParsers().addAll(this.mBeaconParsers);
            scanService.reloadParsers();
        } else {
            LogManager.m16371d(TAG, "Beacon parsers unchanged.", new Object[0]);
        }
        MonitoringStatus monitoringStatus = MonitoringStatus.getInstanceForApplication(scanService);
        if (monitoringStatus.isStatePreservationOn() && !this.mRegionStatePersistenceEnabled.booleanValue()) {
            monitoringStatus.stopStatusPreservation();
        } else if (!monitoringStatus.isStatePreservationOn() && this.mRegionStatePersistenceEnabled.booleanValue()) {
            monitoringStatus.startStatusPreservation();
        }
        BeaconManager.setAndroidLScanningDisabled(this.mAndroidLScanningDisabled.booleanValue());
        BeaconManager.setRegionExitPeriod(this.mRegionExitPeriod.longValue());
        RangeState.setUseTrackingCache(this.mUseTrackingCache.booleanValue());
        Beacon.setHardwareEqualityEnforced(this.mHardwareEqualityEnforced.booleanValue());
    }

    public SettingsData collect(@NonNull Context context) {
        BeaconManager beaconManager = BeaconManager.getInstanceForApplication(context);
        this.mBeaconParsers = new ArrayList(beaconManager.getBeaconParsers());
        this.mRegionStatePersistenceEnabled = Boolean.valueOf(beaconManager.isRegionStatePersistenceEnabled());
        this.mAndroidLScanningDisabled = Boolean.valueOf(BeaconManager.isAndroidLScanningDisabled());
        this.mRegionExitPeriod = Long.valueOf(BeaconManager.getRegionExitPeriod());
        this.mUseTrackingCache = Boolean.valueOf(RangeState.getUseTrackingCache());
        this.mHardwareEqualityEnforced = Boolean.valueOf(Beacon.getHardwareEqualityEnforced());
        return this;
    }
}
