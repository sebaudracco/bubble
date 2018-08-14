package org.altbeacon.beacon.service;

import android.annotation.TargetApi;
import android.app.Service;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.le.ScanResult;
import android.content.pm.ApplicationInfo;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.altbeacon.beacon.Beacon;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.BeaconParser;
import org.altbeacon.beacon.Region;
import org.altbeacon.beacon.logging.LogManager;
import org.altbeacon.beacon.service.scanner.NonBeaconLeScanCallback;

public class ScanDataProcessor {
    private static final String TAG = ScanDataProcessor.class.getSimpleName();
    private Set<BeaconParser> mBeaconParsers = new HashSet();
    private DetectionTracker mDetectionTracker = DetectionTracker.getInstance();
    private ExtraDataBeaconTracker mExtraDataBeaconTracker;
    private MonitoringStatus mMonitoringStatus;
    private NonBeaconLeScanCallback mNonBeaconLeScanCallback;
    private Map<Region, RangeState> mRangedRegionState = new HashMap();
    private Service mService;
    int trackedBeaconsPacketCount;

    private class ScanData {
        BluetoothDevice device;
        int rssi;
        byte[] scanRecord;

        public ScanData(BluetoothDevice device, int rssi, byte[] scanRecord) {
            this.device = device;
            this.rssi = rssi;
            this.scanRecord = scanRecord;
        }
    }

    public ScanDataProcessor(Service scanService, ScanState scanState) {
        this.mService = scanService;
        this.mMonitoringStatus = scanState.getMonitoringStatus();
        this.mRangedRegionState = scanState.getRangedRegionState();
        this.mMonitoringStatus = scanState.getMonitoringStatus();
        this.mExtraDataBeaconTracker = scanState.getExtraBeaconDataTracker();
        this.mBeaconParsers = scanState.getBeaconParsers();
    }

    @TargetApi(21)
    public void process(ScanResult scanResult) {
        process(new ScanData(scanResult.getDevice(), scanResult.getRssi(), scanResult.getScanRecord().getBytes()));
    }

    public void process(ScanData scanData) {
        Beacon beacon = null;
        for (BeaconParser parser : this.mBeaconParsers) {
            beacon = parser.fromScanData(scanData.scanRecord, scanData.rssi, scanData.device);
            if (beacon != null) {
                break;
            }
        }
        if (beacon != null) {
            this.mDetectionTracker.recordDetection();
            this.trackedBeaconsPacketCount++;
            processBeaconFromScan(beacon);
        } else if (this.mNonBeaconLeScanCallback != null) {
            this.mNonBeaconLeScanCallback.onNonBeaconLeScan(scanData.device, scanData.rssi, scanData.scanRecord);
        }
    }

    private void processBeaconFromScan(Beacon beacon) {
        if (Stats.getInstance().isEnabled()) {
            Stats.getInstance().log(beacon);
        }
        if (LogManager.isVerboseLoggingEnabled()) {
            LogManager.m16371d(TAG, "beacon detected : %s", beacon.toString());
        }
        beacon = this.mExtraDataBeaconTracker.track(beacon);
        if (beacon != null) {
            this.mMonitoringStatus.updateNewlyInsideInRegionsContaining(beacon);
            LogManager.m16371d(TAG, "looking for ranging region matches for this beacon out of " + this.mRangedRegionState.keySet().size() + " regions.", new Object[0]);
            synchronized (this.mRangedRegionState) {
                for (Region region : matchingRegions(beacon, this.mRangedRegionState.keySet())) {
                    LogManager.m16371d(TAG, "matches ranging region: %s", region);
                    RangeState rangeState = (RangeState) this.mRangedRegionState.get(region);
                    if (rangeState != null) {
                        rangeState.addBeacon(beacon);
                    }
                }
            }
        } else if (LogManager.isVerboseLoggingEnabled()) {
            LogManager.m16371d(TAG, "not processing detections for GATT extra data beacon", new Object[0]);
        }
    }

    private List<Region> matchingRegions(Beacon beacon, Collection<Region> regions) {
        List<Region> matched = new ArrayList();
        for (Region region : regions) {
            if (region.matchesBeacon(beacon)) {
                matched.add(region);
            } else {
                LogManager.m16371d(TAG, "This region (%s) does not match beacon: %s", region, beacon);
            }
        }
        return matched;
    }

    public void onCycleEnd() {
        this.mMonitoringStatus.updateNewlyOutside();
        processRangeData();
        if (BeaconManager.getBeaconSimulator() == null) {
            return;
        }
        if (BeaconManager.getBeaconSimulator().getBeacons() != null) {
            ApplicationInfo applicationInfo = this.mService.getApplicationContext().getApplicationInfo();
            int i = applicationInfo.flags & 2;
            applicationInfo.flags = i;
            if (i != 0) {
                for (Beacon beacon : BeaconManager.getBeaconSimulator().getBeacons()) {
                    processBeaconFromScan(beacon);
                }
                return;
            }
            LogManager.m16379w(TAG, "Beacon simulations provided, but ignored because we are not running in debug mode.  Please remove beacon simulations for production.", new Object[0]);
            return;
        }
        LogManager.m16379w(TAG, "getBeacons is returning null. No simulated beacons to report.", new Object[0]);
    }

    private void processRangeData() {
        synchronized (this.mRangedRegionState) {
            for (Region region : this.mRangedRegionState.keySet()) {
                RangeState rangeState = (RangeState) this.mRangedRegionState.get(region);
                LogManager.m16371d(TAG, "Calling ranging callback", new Object[0]);
                new Callback(this.mService.getPackageName()).call(this.mService, "rangingData", new RangingData(rangeState.finalizeBeacons(), region).toBundle());
            }
        }
    }
}
