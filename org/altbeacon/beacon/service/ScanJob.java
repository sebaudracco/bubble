package org.altbeacon.beacon.service;

import android.annotation.TargetApi;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.bluetooth.le.ScanRecord;
import android.bluetooth.le.ScanResult;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageItemInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build.VERSION;
import android.os.Handler;
import java.util.ArrayList;
import org.altbeacon.beacon.Beacon;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.BuildConfig;
import org.altbeacon.beacon.Region;
import org.altbeacon.beacon.distance.ModelSpecificDistanceCalculator;
import org.altbeacon.beacon.logging.LogManager;
import org.altbeacon.beacon.utils.ProcessUtils;

@TargetApi(21)
public class ScanJob extends JobService {
    private static final String TAG = ScanJob.class.getSimpleName();
    private static int sOverrideImmediateScanJobId = -1;
    private static int sOverridePeriodicScanJobId = -1;
    private boolean mInitialized = false;
    private ScanHelper mScanHelper;
    private ScanState mScanState;
    private Handler mStopHandler = new Handler();

    public boolean onStartJob(final JobParameters jobParameters) {
        boolean startedScan;
        initialzeScanHelper();
        if (jobParameters.getJobId() == getImmediateScanJobId(this)) {
            LogManager.m16375i(TAG, "Running immediate scan job: instance is " + this, new Object[0]);
        } else {
            LogManager.m16375i(TAG, "Running periodic scan job: instance is " + this, new Object[0]);
        }
        LogManager.m16371d(TAG, "Processing %d queued scan resuilts", Integer.valueOf(ScanJobScheduler.getInstance().dumpBackgroundScanResultQueue().size()));
        for (ScanResult result : queuedScanResults) {
            ScanRecord scanRecord = result.getScanRecord();
            if (scanRecord != null) {
                this.mScanHelper.processScanResult(result.getDevice(), result.getRssi(), scanRecord.getBytes());
            }
        }
        LogManager.m16371d(TAG, "Done processing queued scan resuilts", new Object[0]);
        if (this.mInitialized) {
            LogManager.m16371d(TAG, "Scanning already started.  Resetting for current parameters", new Object[0]);
            startedScan = restartScanning();
        } else {
            startedScan = startScanning();
        }
        this.mStopHandler.removeCallbacksAndMessages(null);
        if (startedScan) {
            LogManager.m16375i(TAG, "Scan job running for " + this.mScanState.getScanJobRuntimeMillis() + " millis", new Object[0]);
            this.mStopHandler.postDelayed(new Runnable() {

                class C47911 implements Runnable {
                    C47911() {
                    }

                    public void run() {
                        ScanJob.this.scheduleNextScan();
                    }
                }

                public void run() {
                    LogManager.m16375i(ScanJob.TAG, "Scan job runtime expired: " + ScanJob.this, new Object[0]);
                    ScanJob.this.stopScanning();
                    ScanJob.this.mScanState.save();
                    ScanJob.this.jobFinished(jobParameters, false);
                    ScanJob.this.mStopHandler.post(new C47911());
                }
            }, (long) this.mScanState.getScanJobRuntimeMillis());
        } else {
            LogManager.m16375i(TAG, "Scanning not started so Scan job is complete.", new Object[0]);
            jobFinished(jobParameters, false);
        }
        return true;
    }

    private void scheduleNextScan() {
        if (this.mScanState.getBackgroundMode().booleanValue()) {
            startPassiveScanIfNeeded();
            return;
        }
        LogManager.m16371d(TAG, "In foreground mode, schedule next scan", new Object[0]);
        ScanJobScheduler.getInstance().forceScheduleNextScan(this);
    }

    private void startPassiveScanIfNeeded() {
        LogManager.m16371d(TAG, "Checking to see if we need to start a passive scan", new Object[0]);
        boolean insideAnyRegion = false;
        for (Region region : new ArrayList(this.mScanState.getMonitoringStatus().regions())) {
            RegionMonitoringState state = this.mScanState.getMonitoringStatus().stateOf(region);
            if (state != null && state.getInside()) {
                insideAnyRegion = true;
            }
        }
        if (insideAnyRegion) {
            LogManager.m16375i(TAG, "We are inside a beacon region.  We will not scan between cycles.", new Object[0]);
        } else if (VERSION.SDK_INT >= 26) {
            this.mScanHelper.startAndroidOBackgroundScan(this.mScanState.getBeaconParsers());
        } else {
            LogManager.m16371d(TAG, "This is not Android O.  No scanning between cycles when using ScanJob", new Object[0]);
        }
    }

    public boolean onStopJob(JobParameters params) {
        if (params.getJobId() == getPeriodicScanJobId(this)) {
            LogManager.m16375i(TAG, "onStopJob called for periodic scan " + this, new Object[0]);
        } else {
            LogManager.m16375i(TAG, "onStopJob called for immediate scan " + this, new Object[0]);
        }
        this.mStopHandler.removeCallbacksAndMessages(null);
        stopScanning();
        startPassiveScanIfNeeded();
        return false;
    }

    private void stopScanning() {
        this.mInitialized = false;
        this.mScanHelper.getCycledScanner().stop();
        this.mScanHelper.getCycledScanner().destroy();
        LogManager.m16371d(TAG, "Scanning stopped", new Object[0]);
    }

    private void initialzeScanHelper() {
        this.mScanHelper = new ScanHelper(this);
        this.mScanState = ScanState.restore(this);
        this.mScanState.setLastScanStartTimeMillis(System.currentTimeMillis());
        this.mScanHelper.setMonitoringStatus(this.mScanState.getMonitoringStatus());
        this.mScanHelper.setRangedRegionState(this.mScanState.getRangedRegionState());
        this.mScanHelper.setBeaconParsers(this.mScanState.getBeaconParsers());
        this.mScanHelper.setExtraDataBeaconTracker(this.mScanState.getExtraBeaconDataTracker());
        if (this.mScanHelper.getCycledScanner() == null) {
            this.mScanHelper.createCycledLeScanner(this.mScanState.getBackgroundMode().booleanValue(), null);
        }
    }

    private boolean restartScanning() {
        Long backgroundBetweenScanPeriod;
        if (VERSION.SDK_INT >= 26) {
            this.mScanHelper.stopAndroidOBackgroundScan();
        }
        long scanPeriod = (this.mScanState.getBackgroundMode().booleanValue() ? this.mScanState.getBackgroundScanPeriod() : this.mScanState.getForegroundScanPeriod()).longValue();
        if (this.mScanState.getBackgroundMode().booleanValue()) {
            backgroundBetweenScanPeriod = this.mScanState.getBackgroundBetweenScanPeriod();
        } else {
            backgroundBetweenScanPeriod = this.mScanState.getForegroundBetweenScanPeriod();
        }
        this.mScanHelper.getCycledScanner().setScanPeriods(scanPeriod, backgroundBetweenScanPeriod.longValue(), this.mScanState.getBackgroundMode().booleanValue());
        this.mInitialized = true;
        if (scanPeriod <= 0) {
            LogManager.m16379w(TAG, "Starting scan with scan period of zero.  Exiting ScanJob.", new Object[0]);
            this.mScanHelper.getCycledScanner().stop();
            return false;
        } else if (this.mScanHelper.getRangedRegionState().size() > 0 || this.mScanHelper.getMonitoringStatus().regions().size() > 0) {
            this.mScanHelper.getCycledScanner().start();
            return true;
        } else {
            this.mScanHelper.getCycledScanner().stop();
            return false;
        }
    }

    private boolean startScanning() {
        BeaconManager beaconManager = BeaconManager.getInstanceForApplication(getApplicationContext());
        beaconManager.setScannerInSameProcess(true);
        if (beaconManager.isMainProcess()) {
            LogManager.m16375i(TAG, "scanJob version %s is starting up on the main process", BuildConfig.VERSION_NAME);
        } else {
            LogManager.m16375i(TAG, "beaconScanJob library version %s is starting up on a separate process", BuildConfig.VERSION_NAME);
            ProcessUtils processUtils = new ProcessUtils(this);
            LogManager.m16375i(TAG, "beaconScanJob PID is " + processUtils.getPid() + " with process name " + processUtils.getProcessName(), new Object[0]);
        }
        Beacon.setDistanceCalculator(new ModelSpecificDistanceCalculator(this, BeaconManager.getDistanceModelUpdateUrl()));
        return restartScanning();
    }

    public static void setOverrideImmediateScanJobId(int id) {
        sOverrideImmediateScanJobId = id;
    }

    public static void setOverridePeriodicScanJobId(int id) {
        sOverridePeriodicScanJobId = id;
    }

    public static int getImmediateScanJobId(Context context) {
        if (sOverrideImmediateScanJobId < 0) {
            return getJobIdFromManifest(context, "immediateScanJobId");
        }
        LogManager.m16375i(TAG, "Using ImmediateScanJobId from static override: " + sOverrideImmediateScanJobId, new Object[0]);
        return sOverrideImmediateScanJobId;
    }

    public static int getPeriodicScanJobId(Context context) {
        if (sOverrideImmediateScanJobId < 0) {
            return getJobIdFromManifest(context, "periodicScanJobId");
        }
        LogManager.m16375i(TAG, "Using PeriodicScanJobId from static override: " + sOverridePeriodicScanJobId, new Object[0]);
        return sOverridePeriodicScanJobId;
    }

    private static int getJobIdFromManifest(Context context, String name) {
        PackageItemInfo info = null;
        try {
            info = context.getPackageManager().getServiceInfo(new ComponentName(context, ScanJob.class), 128);
        } catch (NameNotFoundException e) {
        }
        if (info == null || info.metaData == null || info.metaData.get(name) == null) {
            throw new RuntimeException("Cannot get job id from manifest.  Make sure that the " + name + " is configured in the manifest for the ScanJob.");
        }
        int jobId = info.metaData.getInt(name);
        LogManager.m16375i(TAG, "Using " + name + " from manifest: " + jobId, new Object[0]);
        return jobId;
    }
}
