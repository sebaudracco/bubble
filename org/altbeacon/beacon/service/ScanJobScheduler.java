package org.altbeacon.beacon.service;

import android.app.job.JobInfo;
import android.app.job.JobInfo.Builder;
import android.app.job.JobScheduler;
import android.bluetooth.le.ScanResult;
import android.content.ComponentName;
import android.content.Context;
import android.os.Build.VERSION;
import android.os.PersistableBundle;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import java.util.ArrayList;
import java.util.List;
import org.altbeacon.beacon.BeaconLocalBroadcastProcessor;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.logging.LogManager;

@RequiresApi(api = 21)
public class ScanJobScheduler {
    private static final long MIN_MILLIS_BETWEEN_SCAN_JOB_SCHEDULING = 10000;
    private static final Object SINGLETON_LOCK = new Object();
    private static final String TAG = ScanJobScheduler.class.getSimpleName();
    @Nullable
    private static volatile ScanJobScheduler sInstance = null;
    @NonNull
    private List<ScanResult> mBackgroundScanResultQueue = new ArrayList();
    @Nullable
    private BeaconLocalBroadcastProcessor mBeaconNotificationProcessor;
    @NonNull
    private Long mScanJobScheduleTime = Long.valueOf(0);

    @NonNull
    public static ScanJobScheduler getInstance() {
        ScanJobScheduler instance = sInstance;
        if (instance == null) {
            synchronized (SINGLETON_LOCK) {
                try {
                    instance = sInstance;
                    if (instance == null) {
                        ScanJobScheduler instance2 = new ScanJobScheduler();
                        try {
                            sInstance = instance2;
                            instance = instance2;
                        } catch (Throwable th) {
                            Throwable th2 = th;
                            instance = instance2;
                            throw th2;
                        }
                    }
                } catch (Throwable th3) {
                    th2 = th3;
                    throw th2;
                }
            }
        }
        return instance;
    }

    private ScanJobScheduler() {
    }

    private void ensureNotificationProcessorSetup(Context context) {
        if (this.mBeaconNotificationProcessor == null) {
            this.mBeaconNotificationProcessor = new BeaconLocalBroadcastProcessor(context);
            this.mBeaconNotificationProcessor.register();
        }
    }

    List<ScanResult> dumpBackgroundScanResultQueue() {
        List<ScanResult> retval = this.mBackgroundScanResultQueue;
        this.mBackgroundScanResultQueue = new ArrayList();
        return retval;
    }

    private void applySettingsToScheduledJob(Context context, BeaconManager beaconManager, ScanState scanState) {
        scanState.applyChanges(beaconManager);
        LogManager.m16371d(TAG, "Applying scan job settings with background mode " + scanState.getBackgroundMode(), new Object[0]);
        schedule(context, scanState, false);
    }

    public void applySettingsToScheduledJob(Context context, BeaconManager beaconManager) {
        LogManager.m16371d(TAG, "Applying settings to ScanJob", new Object[0]);
        JobScheduler jobScheduler = (JobScheduler) context.getSystemService("jobscheduler");
        applySettingsToScheduledJob(context, beaconManager, ScanState.restore(context));
    }

    public void scheduleAfterBackgroundWakeup(Context context, List<ScanResult> scanResults) {
        if (scanResults != null) {
            this.mBackgroundScanResultQueue.addAll(scanResults);
        }
        synchronized (this) {
            if (System.currentTimeMillis() - this.mScanJobScheduleTime.longValue() > 10000) {
                LogManager.m16371d(TAG, "scheduling an immediate scan job because last did " + (System.currentTimeMillis() - this.mScanJobScheduleTime.longValue()) + "seconds ago.", new Object[0]);
                this.mScanJobScheduleTime = Long.valueOf(System.currentTimeMillis());
                schedule(context, ScanState.restore(context), true);
                return;
            }
            LogManager.m16371d(TAG, "Not scheduling an immediate scan job because we just did recently.", new Object[0]);
        }
    }

    public void forceScheduleNextScan(Context context) {
        schedule(context, ScanState.restore(context), false);
    }

    private void schedule(Context context, ScanState scanState, boolean backgroundWakeup) {
        long millisToNextJobStart;
        int error;
        ensureNotificationProcessorSetup(context);
        long betweenScanPeriod = (long) (scanState.getScanJobIntervalMillis() - scanState.getScanJobRuntimeMillis());
        if (backgroundWakeup) {
            LogManager.m16371d(TAG, "We just woke up in the background based on a new scan result.  Start scan job immediately.", new Object[0]);
            millisToNextJobStart = 0;
        } else {
            if (betweenScanPeriod > 0) {
                millisToNextJobStart = SystemClock.elapsedRealtime() % ((long) scanState.getScanJobIntervalMillis());
            } else {
                millisToNextJobStart = 0;
            }
            if (millisToNextJobStart < 50) {
                millisToNextJobStart = 50;
            }
        }
        JobScheduler jobScheduler = (JobScheduler) context.getSystemService("jobscheduler");
        if (!backgroundWakeup && scanState.getBackgroundMode().booleanValue()) {
            LogManager.m16371d(TAG, "Not scheduling an immediate scan because we are in background mode.   Cancelling existing immediate scan.", new Object[0]);
            jobScheduler.cancel(ScanJob.getImmediateScanJobId(context));
        } else if (millisToNextJobStart < ((long) (scanState.getScanJobIntervalMillis() - 50))) {
            LogManager.m16371d(TAG, "Scheduling immediate ScanJob to run in " + millisToNextJobStart + " millis", new Object[0]);
            error = jobScheduler.schedule(new Builder(ScanJob.getImmediateScanJobId(context), new ComponentName(context, ScanJob.class)).setPersisted(true).setExtras(new PersistableBundle()).setMinimumLatency(millisToNextJobStart).setOverrideDeadline(millisToNextJobStart).build());
            if (error < 0) {
                LogManager.m16373e(TAG, "Failed to schedule scan job.  Beacons will not be detected. Error: " + error, new Object[0]);
            }
        } else {
            LogManager.m16371d(TAG, "Not scheduling immediate scan, assuming periodic is about to run", new Object[0]);
        }
        Builder periodicJobBuilder = new Builder(ScanJob.getPeriodicScanJobId(context), new ComponentName(context, ScanJob.class)).setPersisted(true).setExtras(new PersistableBundle());
        if (VERSION.SDK_INT >= 24) {
            periodicJobBuilder.setPeriodic((long) scanState.getScanJobIntervalMillis(), 0).build();
        } else {
            periodicJobBuilder.setPeriodic((long) scanState.getScanJobIntervalMillis()).build();
        }
        JobInfo jobInfo = periodicJobBuilder.build();
        LogManager.m16371d(TAG, "Scheduling ScanJob " + jobInfo + " to run every " + scanState.getScanJobIntervalMillis() + " millis", new Object[0]);
        error = jobScheduler.schedule(jobInfo);
        if (error < 0) {
            LogManager.m16373e(TAG, "Failed to schedule scan job.  Beacons will not be detected. Error: " + error, new Object[0]);
        }
    }
}
