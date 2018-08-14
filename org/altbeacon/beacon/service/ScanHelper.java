package org.altbeacon.beacon.service;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.PendingIntent;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanSettings;
import android.bluetooth.le.ScanSettings.Builder;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.os.AsyncTask;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.annotation.WorkerThread;
import com.stepleaderdigital.reveal.Reveal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import org.altbeacon.beacon.Beacon;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.BeaconParser;
import org.altbeacon.beacon.Region;
import org.altbeacon.beacon.logging.LogManager;
import org.altbeacon.beacon.service.scanner.CycledLeScanCallback;
import org.altbeacon.beacon.service.scanner.CycledLeScanner;
import org.altbeacon.beacon.service.scanner.DistinctPacketDetector;
import org.altbeacon.beacon.service.scanner.NonBeaconLeScanCallback;
import org.altbeacon.beacon.service.scanner.ScanFilterUtils;
import org.altbeacon.beacon.startup.StartupBroadcastReceiver;
import org.altbeacon.bluetooth.BluetoothCrashResolver;

class ScanHelper {
    private static final String TAG = ScanHelper.class.getSimpleName();
    private BeaconManager mBeaconManager;
    private Set<BeaconParser> mBeaconParsers = new HashSet();
    private Context mContext;
    private final CycledLeScanCallback mCycledLeScanCallback = new C47901();
    private CycledLeScanner mCycledScanner;
    private DistinctPacketDetector mDistinctPacketDetector = new DistinctPacketDetector();
    private ExecutorService mExecutor;
    @NonNull
    private ExtraDataBeaconTracker mExtraDataBeaconTracker = new ExtraDataBeaconTracker();
    private MonitoringStatus mMonitoringStatus;
    private final Map<Region, RangeState> mRangedRegionState = new HashMap();
    private List<Beacon> mSimulatedScanData = null;

    class C47901 implements CycledLeScanCallback {
        C47901() {
        }

        @TargetApi(11)
        @MainThread
        public void onLeScan(BluetoothDevice device, int rssi, byte[] scanRecord) {
            ScanHelper.this.processScanResult(device, rssi, scanRecord);
        }

        @SuppressLint({"WrongThread"})
        @MainThread
        public void onCycleEnd() {
            ScanHelper.this.mDistinctPacketDetector.clearDetections();
            ScanHelper.this.mMonitoringStatus.updateNewlyOutside();
            ScanHelper.this.processRangeData();
            if (ScanHelper.this.mSimulatedScanData != null) {
                int i;
                LogManager.m16379w(ScanHelper.TAG, "Simulated scan data is deprecated and will be removed in a future release. Please use the new BeaconSimulator interface instead.", new Object[0]);
                ApplicationInfo applicationInfo = ScanHelper.this.mContext.getApplicationInfo();
                i = applicationInfo.flags & 2;
                applicationInfo.flags = i;
                if (i != 0) {
                    for (Beacon beacon : ScanHelper.this.mSimulatedScanData) {
                        ScanHelper.this.processBeaconFromScan(beacon);
                    }
                } else {
                    LogManager.m16379w(ScanHelper.TAG, "Simulated scan data provided, but ignored because we are not running in debug mode.  Please remove simulated scan data for production.", new Object[0]);
                }
            }
            if (BeaconManager.getBeaconSimulator() == null) {
                return;
            }
            if (BeaconManager.getBeaconSimulator().getBeacons() != null) {
                applicationInfo = ScanHelper.this.mContext.getApplicationInfo();
                i = applicationInfo.flags & 2;
                applicationInfo.flags = i;
                if (i != 0) {
                    for (Beacon beacon2 : BeaconManager.getBeaconSimulator().getBeacons()) {
                        ScanHelper.this.processBeaconFromScan(beacon2);
                    }
                    return;
                }
                LogManager.m16379w(ScanHelper.TAG, "Beacon simulations provided, but ignored because we are not running in debug mode.  Please remove beacon simulations for production.", new Object[0]);
                return;
            }
            LogManager.m16379w(ScanHelper.TAG, "getBeacons is returning null. No simulated beacons to report.", new Object[0]);
        }
    }

    private class ScanData {
        @NonNull
        BluetoothDevice device;
        final int rssi;
        @NonNull
        byte[] scanRecord;

        ScanData(@NonNull BluetoothDevice device, int rssi, @NonNull byte[] scanRecord) {
            this.device = device;
            this.rssi = rssi;
            this.scanRecord = scanRecord;
        }
    }

    private class ScanProcessor extends AsyncTask<ScanData, Void, Void> {
        final DetectionTracker mDetectionTracker = DetectionTracker.getInstance();
        private final NonBeaconLeScanCallback mNonBeaconLeScanCallback;

        ScanProcessor(NonBeaconLeScanCallback nonBeaconLeScanCallback) {
            this.mNonBeaconLeScanCallback = nonBeaconLeScanCallback;
        }

        @WorkerThread
        protected Void doInBackground(ScanData... params) {
            ScanData scanData = params[0];
            Beacon beacon = null;
            for (BeaconParser parser : ScanHelper.this.mBeaconParsers) {
                beacon = parser.fromScanData(scanData.scanRecord, scanData.rssi, scanData.device);
                if (beacon != null) {
                    break;
                }
            }
            if (beacon != null) {
                if (LogManager.isVerboseLoggingEnabled()) {
                    LogManager.m16371d(ScanHelper.TAG, "Beacon packet detected for: " + beacon + " with rssi " + beacon.getRssi(), new Object[0]);
                }
                this.mDetectionTracker.recordDetection();
                if (!(ScanHelper.this.mCycledScanner == null || ScanHelper.this.mCycledScanner.getDistinctPacketsDetectedPerScan() || ScanHelper.this.mDistinctPacketDetector.isPacketDistinct(scanData.device.getAddress(), scanData.scanRecord))) {
                    LogManager.m16375i(ScanHelper.TAG, "Non-distinct packets detected in a single scan.  Restarting scans unecessary.", new Object[0]);
                    ScanHelper.this.mCycledScanner.setDistinctPacketsDetectedPerScan(true);
                }
                ScanHelper.this.processBeaconFromScan(beacon);
            } else if (this.mNonBeaconLeScanCallback != null) {
                this.mNonBeaconLeScanCallback.onNonBeaconLeScan(scanData.device, scanData.rssi, scanData.scanRecord);
            }
            return null;
        }

        protected void onPostExecute(Void result) {
        }

        protected void onPreExecute() {
        }

        protected void onProgressUpdate(Void... values) {
        }
    }

    ScanHelper(Context context) {
        this.mContext = context;
        this.mBeaconManager = BeaconManager.getInstanceForApplication(context);
        this.mExecutor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() + 1);
    }

    CycledLeScanner getCycledScanner() {
        return this.mCycledScanner;
    }

    MonitoringStatus getMonitoringStatus() {
        return this.mMonitoringStatus;
    }

    void setMonitoringStatus(MonitoringStatus monitoringStatus) {
        this.mMonitoringStatus = monitoringStatus;
    }

    Map<Region, RangeState> getRangedRegionState() {
        return this.mRangedRegionState;
    }

    void setRangedRegionState(Map<Region, RangeState> rangedRegionState) {
        synchronized (this.mRangedRegionState) {
            this.mRangedRegionState.clear();
            this.mRangedRegionState.putAll(rangedRegionState);
        }
    }

    void setExtraDataBeaconTracker(@NonNull ExtraDataBeaconTracker extraDataBeaconTracker) {
        this.mExtraDataBeaconTracker = extraDataBeaconTracker;
    }

    void setBeaconParsers(Set<BeaconParser> beaconParsers) {
        this.mBeaconParsers = beaconParsers;
    }

    void setSimulatedScanData(List<Beacon> simulatedScanData) {
        this.mSimulatedScanData = simulatedScanData;
    }

    void createCycledLeScanner(boolean backgroundMode, BluetoothCrashResolver crashResolver) {
        this.mCycledScanner = CycledLeScanner.createScanner(this.mContext, 1100, 0, backgroundMode, this.mCycledLeScanCallback, crashResolver);
    }

    @TargetApi(11)
    void processScanResult(BluetoothDevice device, int rssi, byte[] scanRecord) {
        try {
            new ScanProcessor(this.mBeaconManager.getNonBeaconLeScanCallback()).executeOnExecutor(this.mExecutor, new ScanData[]{new ScanData(device, rssi, scanRecord)});
        } catch (RejectedExecutionException e) {
            LogManager.m16379w(TAG, "Ignoring scan result because we cannot keep up.", new Object[0]);
        }
    }

    void reloadParsers() {
        HashSet<BeaconParser> newBeaconParsers = new HashSet();
        boolean matchBeaconsByServiceUUID = true;
        newBeaconParsers.addAll(this.mBeaconManager.getBeaconParsers());
        for (BeaconParser beaconParser : this.mBeaconManager.getBeaconParsers()) {
            if (beaconParser.getExtraDataParsers().size() > 0) {
                matchBeaconsByServiceUUID = false;
                newBeaconParsers.addAll(beaconParser.getExtraDataParsers());
            }
        }
        this.mBeaconParsers = newBeaconParsers;
        this.mExtraDataBeaconTracker = new ExtraDataBeaconTracker(matchBeaconsByServiceUUID);
    }

    @RequiresApi(api = 26)
    void startAndroidOBackgroundScan(Set<BeaconParser> beaconParsers) {
        ScanSettings settings = new Builder().setScanMode(0).build();
        List<ScanFilter> filters = new ScanFilterUtils().createScanFiltersForBeaconParsers(new ArrayList(beaconParsers));
        try {
            BluetoothAdapter bluetoothAdapter = ((BluetoothManager) this.mContext.getApplicationContext().getSystemService(Reveal.STATUS_BLUETOOTH)).getAdapter();
            if (bluetoothAdapter == null) {
                LogManager.m16379w(TAG, "Failed to construct a BluetoothAdapter", new Object[0]);
            } else if (bluetoothAdapter.isEnabled()) {
                int result = bluetoothAdapter.getBluetoothLeScanner().startScan(filters, settings, getScanCallbackIntent());
                if (result != 0) {
                    LogManager.m16373e(TAG, "Failed to start background scan on Android O.  Code: " + result, new Object[0]);
                } else {
                    LogManager.m16371d(TAG, "Started passive beacon scan", new Object[0]);
                }
            } else {
                LogManager.m16379w(TAG, "Failed to start background scan on Android O: BluetoothAdapter is not enabled", new Object[0]);
            }
        } catch (SecurityException e) {
            LogManager.m16373e(TAG, "SecurityException making Android O background scanner", new Object[0]);
        }
    }

    @RequiresApi(api = 26)
    void stopAndroidOBackgroundScan() {
        try {
            BluetoothAdapter bluetoothAdapter = ((BluetoothManager) this.mContext.getApplicationContext().getSystemService(Reveal.STATUS_BLUETOOTH)).getAdapter();
            if (bluetoothAdapter == null) {
                LogManager.m16379w(TAG, "Failed to construct a BluetoothAdapter", new Object[0]);
            } else if (bluetoothAdapter.isEnabled()) {
                BluetoothLeScanner scanner = bluetoothAdapter.getBluetoothLeScanner();
                if (scanner != null) {
                    scanner.stopScan(getScanCallbackIntent());
                }
            } else {
                LogManager.m16379w(TAG, "BluetoothAdapter is not enabled", new Object[0]);
            }
        } catch (SecurityException e) {
            LogManager.m16373e(TAG, "SecurityException stopping Android O background scanner", new Object[0]);
        }
    }

    PendingIntent getScanCallbackIntent() {
        Intent intent = new Intent(this.mContext, StartupBroadcastReceiver.class);
        intent.putExtra("o-scan", true);
        return PendingIntent.getBroadcast(this.mContext, 0, intent, 134217728);
    }

    @RestrictTo({Scope.TESTS})
    CycledLeScanCallback getCycledLeScanCallback() {
        return this.mCycledLeScanCallback;
    }

    private void processRangeData() {
        synchronized (this.mRangedRegionState) {
            for (Region region : this.mRangedRegionState.keySet()) {
                RangeState rangeState = (RangeState) this.mRangedRegionState.get(region);
                LogManager.m16371d(TAG, "Calling ranging callback", new Object[0]);
                rangeState.getCallback().call(this.mContext, "rangingData", new RangingData(rangeState.finalizeBeacons(), region).toBundle());
            }
        }
    }

    @WorkerThread
    private void processBeaconFromScan(@NonNull Beacon beacon) {
        if (Stats.getInstance().isEnabled()) {
            Stats.getInstance().log(beacon);
        }
        if (LogManager.isVerboseLoggingEnabled()) {
            LogManager.m16371d(TAG, "beacon detected : %s", beacon.toString());
        }
        beacon = this.mExtraDataBeaconTracker.track(beacon);
        if (beacon != null) {
            this.mMonitoringStatus.updateNewlyInsideInRegionsContaining(beacon);
            LogManager.m16371d(TAG, "looking for ranging region matches for this beacon", new Object[0]);
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
}
