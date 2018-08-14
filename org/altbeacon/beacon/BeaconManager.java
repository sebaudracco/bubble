package org.altbeacon.beacon;

import android.annotation.TargetApi;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Build.VERSION;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.stepleaderdigital.reveal.Reveal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import org.altbeacon.beacon.logging.LogManager;
import org.altbeacon.beacon.logging.Loggers;
import org.altbeacon.beacon.service.BeaconService;
import org.altbeacon.beacon.service.Callback;
import org.altbeacon.beacon.service.MonitoringStatus;
import org.altbeacon.beacon.service.RangeState;
import org.altbeacon.beacon.service.RangedBeacon;
import org.altbeacon.beacon.service.RegionMonitoringState;
import org.altbeacon.beacon.service.RunningAverageRssiFilter;
import org.altbeacon.beacon.service.ScanJobScheduler;
import org.altbeacon.beacon.service.SettingsData;
import org.altbeacon.beacon.service.StartRMData;
import org.altbeacon.beacon.service.scanner.NonBeaconLeScanCallback;
import org.altbeacon.beacon.simulator.BeaconSimulator;
import org.altbeacon.beacon.utils.ProcessUtils;

public class BeaconManager {
    public static final long DEFAULT_BACKGROUND_BETWEEN_SCAN_PERIOD = 300000;
    public static final long DEFAULT_BACKGROUND_SCAN_PERIOD = 10000;
    public static final long DEFAULT_EXIT_PERIOD = 10000;
    public static final long DEFAULT_FOREGROUND_BETWEEN_SCAN_PERIOD = 0;
    public static final long DEFAULT_FOREGROUND_SCAN_PERIOD = 1100;
    private static final Object SINGLETON_LOCK = new Object();
    @NonNull
    private static final String TAG = "BeaconManager";
    @Nullable
    protected static BeaconSimulator beaconSimulator;
    protected static String distanceModelUpdateUrl = "http://data.altbeacon.org/android-distance.json";
    protected static Class rssiFilterImplClass = RunningAverageRssiFilter.class;
    private static boolean sAndroidLScanningDisabled = false;
    private static long sExitRegionPeriod = 10000;
    @Nullable
    protected static volatile BeaconManager sInstance = null;
    private static boolean sManifestCheckingDisabled = false;
    private long backgroundBetweenScanPeriod = 300000;
    private long backgroundScanPeriod = 10000;
    @NonNull
    private final List<BeaconParser> beaconParsers = new CopyOnWriteArrayList();
    @NonNull
    private final ConcurrentMap<BeaconConsumer, ConsumerInfo> consumers = new ConcurrentHashMap();
    @Nullable
    protected RangeNotifier dataRequestNotifier = null;
    private long foregroundBetweenScanPeriod = 0;
    private long foregroundScanPeriod = 1100;
    private boolean mBackgroundMode = false;
    private boolean mBackgroundModeUninitialized = true;
    @NonNull
    private final Context mContext;
    private boolean mMainProcess = false;
    @Nullable
    private NonBeaconLeScanCallback mNonBeaconLeScanCallback;
    private boolean mRegionStatePersistenceEnabled = true;
    @Nullable
    private Boolean mScannerInSameProcess = null;
    private boolean mScheduledScanJobsEnabled = false;
    @NonNull
    protected final Set<MonitorNotifier> monitorNotifiers = new CopyOnWriteArraySet();
    @NonNull
    protected final Set<RangeNotifier> rangeNotifiers = new CopyOnWriteArraySet();
    @NonNull
    private final ArrayList<Region> rangedRegions = new ArrayList();
    @Nullable
    private Messenger serviceMessenger = null;

    @Deprecated
    public static void setDebug(boolean debug) {
        if (debug) {
            LogManager.setLogger(Loggers.verboseLogger());
            LogManager.setVerboseLoggingEnabled(true);
            return;
        }
        LogManager.setLogger(Loggers.empty());
        LogManager.setVerboseLoggingEnabled(false);
    }

    public void setForegroundScanPeriod(long p) {
        this.foregroundScanPeriod = p;
    }

    public void setForegroundBetweenScanPeriod(long p) {
        this.foregroundBetweenScanPeriod = p;
    }

    public void setBackgroundScanPeriod(long p) {
        this.backgroundScanPeriod = p;
    }

    public void setBackgroundBetweenScanPeriod(long p) {
        this.backgroundBetweenScanPeriod = p;
    }

    public static void setRegionExitPeriod(long regionExitPeriod) {
        sExitRegionPeriod = regionExitPeriod;
        BeaconManager instance = sInstance;
        if (instance != null) {
            instance.applySettings();
        }
    }

    public static long getRegionExitPeriod() {
        return sExitRegionPeriod;
    }

    @NonNull
    public static BeaconManager getInstanceForApplication(@NonNull Context context) {
        BeaconManager instance = sInstance;
        if (instance == null) {
            synchronized (SINGLETON_LOCK) {
                try {
                    instance = sInstance;
                    if (instance == null) {
                        BeaconManager instance2 = new BeaconManager(context);
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

    protected BeaconManager(@NonNull Context context) {
        boolean z = true;
        this.mContext = context.getApplicationContext();
        checkIfMainProcess();
        if (!sManifestCheckingDisabled) {
            verifyServiceDeclaration();
        }
        this.beaconParsers.add(new AltBeaconParser());
        if (VERSION.SDK_INT < 26) {
            z = false;
        }
        this.mScheduledScanJobsEnabled = z;
    }

    public boolean isMainProcess() {
        return this.mMainProcess;
    }

    public boolean isScannerInDifferentProcess() {
        return (this.mScannerInSameProcess == null || this.mScannerInSameProcess.booleanValue()) ? false : true;
    }

    public void setScannerInSameProcess(boolean isScanner) {
        this.mScannerInSameProcess = Boolean.valueOf(isScanner);
    }

    protected void checkIfMainProcess() {
        ProcessUtils processUtils = new ProcessUtils(this.mContext);
        String processName = processUtils.getProcessName();
        String packageName = processUtils.getPackageName();
        int pid = processUtils.getPid();
        this.mMainProcess = processUtils.isMainProcess();
        LogManager.i(TAG, "BeaconManager started up on pid " + pid + " named '" + processName + "' for application package '" + packageName + "'.  isMainProcess=" + this.mMainProcess, new Object[0]);
    }

    @NonNull
    public List<BeaconParser> getBeaconParsers() {
        return this.beaconParsers;
    }

    @TargetApi(18)
    public boolean checkAvailability() throws BleNotAvailableException {
        if (isBleAvailable()) {
            return ((BluetoothManager) this.mContext.getSystemService(Reveal.STATUS_BLUETOOTH)).getAdapter().isEnabled();
        }
        throw new BleNotAvailableException("Bluetooth LE not supported by this device");
    }

    public void bind(@NonNull BeaconConsumer consumer) {
        if (!isBleAvailable()) {
            LogManager.w(TAG, "Method invocation will be ignored.", new Object[0]);
        } else if (this.mContext.getPackageManager().hasSystemFeature("android.hardware.bluetooth_le")) {
            synchronized (this.consumers) {
                ConsumerInfo newConsumerInfo = new ConsumerInfo(this);
                if (((ConsumerInfo) this.consumers.putIfAbsent(consumer, newConsumerInfo)) != null) {
                    LogManager.d(TAG, "This consumer is already bound", new Object[0]);
                } else {
                    LogManager.d(TAG, "This consumer is not bound.  Binding now: %s", new Object[]{consumer});
                    if (this.mScheduledScanJobsEnabled) {
                        LogManager.d(TAG, "Not starting beacon scanning service. Using scheduled jobs", new Object[0]);
                        consumer.onBeaconServiceConnect();
                    } else {
                        LogManager.d(TAG, "Binding to service", new Object[0]);
                        consumer.bindService(new Intent(consumer.getApplicationContext(), BeaconService.class), newConsumerInfo.beaconServiceConnection, 1);
                    }
                    LogManager.d(TAG, "consumer count is now: %s", new Object[]{Integer.valueOf(this.consumers.size())});
                }
            }
        } else {
            LogManager.w(TAG, "This device does not support bluetooth LE.  Will not start beacon scanning.", new Object[0]);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void unbind(@android.support.annotation.NonNull org.altbeacon.beacon.BeaconConsumer r8) {
        /*
        r7 = this;
        r4 = 0;
        r2 = r7.isBleAvailable();
        if (r2 != 0) goto L_0x0013;
    L_0x0007:
        r2 = "BeaconManager";
        r3 = "Method invocation will be ignored.";
        r4 = new java.lang.Object[r4];
        org.altbeacon.beacon.logging.LogManager.w(r2, r3, r4);
    L_0x0012:
        return;
    L_0x0013:
        r3 = r7.consumers;
        monitor-enter(r3);
        r2 = r7.consumers;	 Catch:{ all -> 0x0053 }
        r2 = r2.containsKey(r8);	 Catch:{ all -> 0x0053 }
        if (r2 == 0) goto L_0x0064;
    L_0x001e:
        r2 = "BeaconManager";
        r4 = "Unbinding";
        r5 = 0;
        r5 = new java.lang.Object[r5];	 Catch:{ all -> 0x0053 }
        org.altbeacon.beacon.logging.LogManager.d(r2, r4, r5);	 Catch:{ all -> 0x0053 }
        r2 = r7.mScheduledScanJobsEnabled;	 Catch:{ all -> 0x0053 }
        if (r2 == 0) goto L_0x0056;
    L_0x002e:
        r2 = "BeaconManager";
        r4 = "Not unbinding from scanning service as we are using scan jobs.";
        r5 = 0;
        r5 = new java.lang.Object[r5];	 Catch:{ all -> 0x0053 }
        org.altbeacon.beacon.logging.LogManager.d(r2, r4, r5);	 Catch:{ all -> 0x0053 }
    L_0x003a:
        r2 = r7.consumers;	 Catch:{ all -> 0x0053 }
        r2.remove(r8);	 Catch:{ all -> 0x0053 }
        r2 = r7.consumers;	 Catch:{ all -> 0x0053 }
        r2 = r2.size();	 Catch:{ all -> 0x0053 }
        if (r2 != 0) goto L_0x0051;
    L_0x0047:
        r2 = 0;
        r7.serviceMessenger = r2;	 Catch:{ all -> 0x0053 }
        r2 = 0;
        r7.mBackgroundMode = r2;	 Catch:{ all -> 0x0053 }
        r2 = r7.mScheduledScanJobsEnabled;	 Catch:{ all -> 0x0053 }
        if (r2 == 0) goto L_0x0051;
    L_0x0051:
        monitor-exit(r3);	 Catch:{ all -> 0x0053 }
        goto L_0x0012;
    L_0x0053:
        r2 = move-exception;
        monitor-exit(r3);	 Catch:{ all -> 0x0053 }
        throw r2;
    L_0x0056:
        r2 = r7.consumers;	 Catch:{ all -> 0x0053 }
        r2 = r2.get(r8);	 Catch:{ all -> 0x0053 }
        r2 = (org.altbeacon.beacon.BeaconManager.ConsumerInfo) r2;	 Catch:{ all -> 0x0053 }
        r2 = r2.beaconServiceConnection;	 Catch:{ all -> 0x0053 }
        r8.unbindService(r2);	 Catch:{ all -> 0x0053 }
        goto L_0x003a;
    L_0x0064:
        r2 = "BeaconManager";
        r4 = "This consumer is not bound to: %s";
        r5 = 1;
        r5 = new java.lang.Object[r5];	 Catch:{ all -> 0x0053 }
        r6 = 0;
        r5[r6] = r8;	 Catch:{ all -> 0x0053 }
        org.altbeacon.beacon.logging.LogManager.d(r2, r4, r5);	 Catch:{ all -> 0x0053 }
        r2 = "BeaconManager";
        r4 = "Bound consumers: ";
        r5 = 0;
        r5 = new java.lang.Object[r5];	 Catch:{ all -> 0x0053 }
        org.altbeacon.beacon.logging.LogManager.d(r2, r4, r5);	 Catch:{ all -> 0x0053 }
        r2 = r7.consumers;	 Catch:{ all -> 0x0053 }
        r1 = r2.entrySet();	 Catch:{ all -> 0x0053 }
        r2 = r1.iterator();	 Catch:{ all -> 0x0053 }
    L_0x0089:
        r4 = r2.hasNext();	 Catch:{ all -> 0x0053 }
        if (r4 == 0) goto L_0x0051;
    L_0x008f:
        r0 = r2.next();	 Catch:{ all -> 0x0053 }
        r0 = (java.util.Map.Entry) r0;	 Catch:{ all -> 0x0053 }
        r4 = "BeaconManager";
        r5 = r0.getValue();	 Catch:{ all -> 0x0053 }
        r5 = java.lang.String.valueOf(r5);	 Catch:{ all -> 0x0053 }
        r6 = 0;
        r6 = new java.lang.Object[r6];	 Catch:{ all -> 0x0053 }
        org.altbeacon.beacon.logging.LogManager.d(r4, r5, r6);	 Catch:{ all -> 0x0053 }
        goto L_0x0089;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.altbeacon.beacon.BeaconManager.unbind(org.altbeacon.beacon.BeaconConsumer):void");
    }

    public boolean isBound(@NonNull BeaconConsumer consumer) {
        boolean z;
        synchronized (this.consumers) {
            if (consumer != null) {
                if (!(this.consumers.get(consumer) == null || this.serviceMessenger == null)) {
                    z = true;
                }
            }
            z = false;
        }
        return z;
    }

    public boolean isAnyConsumerBound() {
        boolean z;
        synchronized (this.consumers) {
            z = this.consumers.isEmpty() && this.serviceMessenger != null;
        }
        return z;
    }

    public void setBackgroundMode(boolean backgroundMode) {
        if (isBleAvailable()) {
            this.mBackgroundModeUninitialized = false;
            if (backgroundMode != this.mBackgroundMode) {
                this.mBackgroundMode = backgroundMode;
                try {
                    updateScanPeriods();
                    return;
                } catch (RemoteException e) {
                    LogManager.e(TAG, "Cannot contact service to set scan periods", new Object[0]);
                    return;
                }
            }
            return;
        }
        LogManager.w(TAG, "Method invocation will be ignored.", new Object[0]);
    }

    public void setEnableScheduledScanJobs(boolean enabled) {
        if (isAnyConsumerBound()) {
            LogManager.e(TAG, "ScanJob may not be configured because a consumer is already bound.", new Object[0]);
            throw new IllegalStateException("Method must be called before calling bind()");
        } else if (!enabled || VERSION.SDK_INT >= 21) {
            this.mScheduledScanJobsEnabled = enabled;
        } else {
            LogManager.e(TAG, "ScanJob may not be configured because JobScheduler is not availble prior to Android 5.0", new Object[0]);
        }
    }

    public boolean getScheduledScanJobsEnabled() {
        return this.mScheduledScanJobsEnabled;
    }

    public boolean getBackgroundMode() {
        return this.mBackgroundMode;
    }

    public long getBackgroundScanPeriod() {
        return this.backgroundScanPeriod;
    }

    public long getBackgroundBetweenScanPeriod() {
        return this.backgroundBetweenScanPeriod;
    }

    public long getForegroundScanPeriod() {
        return this.foregroundScanPeriod;
    }

    public long getForegroundBetweenScanPeriod() {
        return this.foregroundBetweenScanPeriod;
    }

    public boolean isBackgroundModeUninitialized() {
        return this.mBackgroundModeUninitialized;
    }

    @Deprecated
    public void setRangeNotifier(@Nullable RangeNotifier notifier) {
        this.rangeNotifiers.clear();
        if (notifier != null) {
            addRangeNotifier(notifier);
        }
    }

    public void addRangeNotifier(@NonNull RangeNotifier notifier) {
        if (notifier != null) {
            this.rangeNotifiers.add(notifier);
        }
    }

    public boolean removeRangeNotifier(@NonNull RangeNotifier notifier) {
        return this.rangeNotifiers.remove(notifier);
    }

    public void removeAllRangeNotifiers() {
        this.rangeNotifiers.clear();
    }

    @Deprecated
    public void setMonitorNotifier(@Nullable MonitorNotifier notifier) {
        if (!determineIfCalledFromSeparateScannerProcess()) {
            this.monitorNotifiers.clear();
            if (notifier != null) {
                addMonitorNotifier(notifier);
            }
        }
    }

    public void addMonitorNotifier(@NonNull MonitorNotifier notifier) {
        if (!determineIfCalledFromSeparateScannerProcess() && notifier != null) {
            this.monitorNotifiers.add(notifier);
        }
    }

    @Deprecated
    public boolean removeMonitoreNotifier(@NonNull MonitorNotifier notifier) {
        return removeMonitorNotifier(notifier);
    }

    public boolean removeMonitorNotifier(@NonNull MonitorNotifier notifier) {
        if (determineIfCalledFromSeparateScannerProcess()) {
            return false;
        }
        return this.monitorNotifiers.remove(notifier);
    }

    public void removeAllMonitorNotifiers() {
        if (!determineIfCalledFromSeparateScannerProcess()) {
            this.monitorNotifiers.clear();
        }
    }

    @Deprecated
    public void setRegionStatePeristenceEnabled(boolean enabled) {
        setRegionStatePersistenceEnabled(enabled);
    }

    public void setRegionStatePersistenceEnabled(boolean enabled) {
        this.mRegionStatePersistenceEnabled = enabled;
        if (!isScannerInDifferentProcess()) {
            if (enabled) {
                MonitoringStatus.getInstanceForApplication(this.mContext).startStatusPreservation();
            } else {
                MonitoringStatus.getInstanceForApplication(this.mContext).stopStatusPreservation();
            }
        }
        applySettings();
    }

    public boolean isRegionStatePersistenceEnabled() {
        return this.mRegionStatePersistenceEnabled;
    }

    public void requestStateForRegion(@NonNull Region region) {
        if (!determineIfCalledFromSeparateScannerProcess()) {
            RegionMonitoringState stateObj = MonitoringStatus.getInstanceForApplication(this.mContext).stateOf(region);
            int state = 0;
            if (stateObj != null && stateObj.getInside()) {
                state = 1;
            }
            for (MonitorNotifier notifier : this.monitorNotifiers) {
                notifier.didDetermineStateForRegion(state, region);
            }
        }
    }

    @TargetApi(18)
    public void startRangingBeaconsInRegion(@NonNull Region region) throws RemoteException {
        if (!isBleAvailable()) {
            LogManager.w(TAG, "Method invocation will be ignored.", new Object[0]);
        } else if (!determineIfCalledFromSeparateScannerProcess()) {
            synchronized (this.rangedRegions) {
                this.rangedRegions.add(region);
            }
            applyChangesToServices(2, region);
        }
    }

    @TargetApi(18)
    public void stopRangingBeaconsInRegion(@NonNull Region region) throws RemoteException {
        if (!isBleAvailable()) {
            LogManager.w(TAG, "Method invocation will be ignored.", new Object[0]);
        } else if (!determineIfCalledFromSeparateScannerProcess()) {
            synchronized (this.rangedRegions) {
                Region regionToRemove = null;
                Iterator it = this.rangedRegions.iterator();
                while (it.hasNext()) {
                    Region rangedRegion = (Region) it.next();
                    if (region.getUniqueId().equals(rangedRegion.getUniqueId())) {
                        regionToRemove = rangedRegion;
                    }
                }
                this.rangedRegions.remove(regionToRemove);
            }
            applyChangesToServices(3, region);
        }
    }

    public void applySettings() {
        if (!determineIfCalledFromSeparateScannerProcess()) {
            if (!isAnyConsumerBound()) {
                LogManager.d(TAG, "Not synchronizing settings to service, as it has not started up yet", new Object[0]);
            } else if (isScannerInDifferentProcess()) {
                LogManager.d(TAG, "Synchronizing settings to service", new Object[0]);
                syncSettingsToService();
            } else {
                LogManager.d(TAG, "Not synchronizing settings to service, as it is in the same process", new Object[0]);
            }
        }
    }

    protected void syncSettingsToService() {
        if (this.mScheduledScanJobsEnabled) {
            ScanJobScheduler.getInstance().applySettingsToScheduledJob(this.mContext, this);
            return;
        }
        try {
            applyChangesToServices(7, null);
        } catch (RemoteException e) {
            LogManager.e(TAG, "Failed to sync settings to service", new Object[]{e});
        }
    }

    @TargetApi(18)
    public void startMonitoringBeaconsInRegion(@NonNull Region region) throws RemoteException {
        if (!isBleAvailable()) {
            LogManager.w(TAG, "Method invocation will be ignored.", new Object[0]);
        } else if (!determineIfCalledFromSeparateScannerProcess()) {
            if (this.mScheduledScanJobsEnabled) {
                MonitoringStatus.getInstanceForApplication(this.mContext).addRegion(region, new Callback(callbackPackageName()));
            }
            applyChangesToServices(4, region);
            if (isScannerInDifferentProcess()) {
                MonitoringStatus.getInstanceForApplication(this.mContext).addLocalRegion(region);
            }
            requestStateForRegion(region);
        }
    }

    @TargetApi(18)
    public void stopMonitoringBeaconsInRegion(@NonNull Region region) throws RemoteException {
        if (!isBleAvailable()) {
            LogManager.w(TAG, "Method invocation will be ignored.", new Object[0]);
        } else if (!determineIfCalledFromSeparateScannerProcess()) {
            if (this.mScheduledScanJobsEnabled) {
                MonitoringStatus.getInstanceForApplication(this.mContext).removeRegion(region);
            }
            applyChangesToServices(5, region);
            if (isScannerInDifferentProcess()) {
                MonitoringStatus.getInstanceForApplication(this.mContext).removeLocalRegion(region);
            }
        }
    }

    @TargetApi(18)
    public void updateScanPeriods() throws RemoteException {
        if (!isBleAvailable()) {
            LogManager.w(TAG, "Method invocation will be ignored.", new Object[0]);
        } else if (!determineIfCalledFromSeparateScannerProcess()) {
            LogManager.d(TAG, "updating background flag to %s", new Object[]{Boolean.valueOf(this.mBackgroundMode)});
            LogManager.d(TAG, "updating scan period to %s, %s", new Object[]{Long.valueOf(getScanPeriod()), Long.valueOf(getBetweenScanPeriod())});
            applyChangesToServices(6, null);
        }
    }

    @TargetApi(18)
    private void applyChangesToServices(int type, Region region) throws RemoteException {
        if (this.mScheduledScanJobsEnabled) {
            ScanJobScheduler.getInstance().applySettingsToScheduledJob(this.mContext, this);
        } else if (this.serviceMessenger == null) {
            throw new RemoteException("The BeaconManager is not bound to the service.  Call beaconManager.bind(BeaconConsumer consumer) and wait for a callback to onBeaconServiceConnect()");
        } else {
            Message msg = Message.obtain(null, type, 0, 0);
            if (type == 6) {
                msg.setData(new StartRMData(getScanPeriod(), getBetweenScanPeriod(), this.mBackgroundMode).toBundle());
            } else if (type == 7) {
                msg.setData(new SettingsData().collect(this.mContext).toBundle());
            } else {
                msg.setData(new StartRMData(region, callbackPackageName(), getScanPeriod(), getBetweenScanPeriod(), this.mBackgroundMode).toBundle());
            }
            this.serviceMessenger.send(msg);
        }
    }

    private String callbackPackageName() {
        LogManager.d(TAG, "callback packageName: %s", new Object[]{this.mContext.getPackageName()});
        return this.mContext.getPackageName();
    }

    @Nullable
    @Deprecated
    public MonitorNotifier getMonitoringNotifier() {
        Iterator<MonitorNotifier> iterator = this.monitorNotifiers.iterator();
        if (iterator.hasNext()) {
            return (MonitorNotifier) iterator.next();
        }
        return null;
    }

    @NonNull
    public Set<MonitorNotifier> getMonitoringNotifiers() {
        return Collections.unmodifiableSet(this.monitorNotifiers);
    }

    @Nullable
    @Deprecated
    public RangeNotifier getRangingNotifier() {
        Iterator<RangeNotifier> iterator = this.rangeNotifiers.iterator();
        if (iterator.hasNext()) {
            return (RangeNotifier) iterator.next();
        }
        return null;
    }

    @NonNull
    public Set<RangeNotifier> getRangingNotifiers() {
        return Collections.unmodifiableSet(this.rangeNotifiers);
    }

    @NonNull
    public Collection<Region> getMonitoredRegions() {
        return MonitoringStatus.getInstanceForApplication(this.mContext).regions();
    }

    @NonNull
    public Collection<Region> getRangedRegions() {
        Collection arrayList;
        synchronized (this.rangedRegions) {
            arrayList = new ArrayList(this.rangedRegions);
        }
        return arrayList;
    }

    @Deprecated
    public static void logDebug(String tag, String message) {
        LogManager.d(tag, message, new Object[0]);
    }

    @Deprecated
    public static void logDebug(String tag, String message, Throwable t) {
        LogManager.d(t, tag, message, new Object[0]);
    }

    public static String getDistanceModelUpdateUrl() {
        return distanceModelUpdateUrl;
    }

    public static void setDistanceModelUpdateUrl(@NonNull String url) {
        warnIfScannerNotInSameProcess();
        distanceModelUpdateUrl = url;
    }

    public static void setRssiFilterImplClass(@NonNull Class c) {
        warnIfScannerNotInSameProcess();
        rssiFilterImplClass = c;
    }

    public static Class getRssiFilterImplClass() {
        return rssiFilterImplClass;
    }

    public static void setUseTrackingCache(boolean useTrackingCache) {
        RangeState.setUseTrackingCache(useTrackingCache);
        if (sInstance != null) {
            sInstance.applySettings();
        }
    }

    public void setMaxTrackingAge(int maxTrackingAge) {
        RangedBeacon.setMaxTrackinAge(maxTrackingAge);
    }

    public static void setBeaconSimulator(BeaconSimulator beaconSimulator) {
        warnIfScannerNotInSameProcess();
        beaconSimulator = beaconSimulator;
    }

    @Nullable
    public static BeaconSimulator getBeaconSimulator() {
        return beaconSimulator;
    }

    protected void setDataRequestNotifier(@Nullable RangeNotifier notifier) {
        this.dataRequestNotifier = notifier;
    }

    @Nullable
    protected RangeNotifier getDataRequestNotifier() {
        return this.dataRequestNotifier;
    }

    @Nullable
    public NonBeaconLeScanCallback getNonBeaconLeScanCallback() {
        return this.mNonBeaconLeScanCallback;
    }

    public void setNonBeaconLeScanCallback(@Nullable NonBeaconLeScanCallback callback) {
        this.mNonBeaconLeScanCallback = callback;
    }

    private boolean isBleAvailable() {
        if (VERSION.SDK_INT < 18) {
            LogManager.w(TAG, "Bluetooth LE not supported prior to API 18.", new Object[0]);
            return false;
        } else if (this.mContext.getPackageManager().hasSystemFeature("android.hardware.bluetooth_le")) {
            return true;
        } else {
            LogManager.w(TAG, "This device does not support bluetooth LE.", new Object[0]);
            return false;
        }
    }

    private long getScanPeriod() {
        if (this.mBackgroundMode) {
            return this.backgroundScanPeriod;
        }
        return this.foregroundScanPeriod;
    }

    private long getBetweenScanPeriod() {
        if (this.mBackgroundMode) {
            return this.backgroundBetweenScanPeriod;
        }
        return this.foregroundBetweenScanPeriod;
    }

    private void verifyServiceDeclaration() {
        List<ResolveInfo> resolveInfo = this.mContext.getPackageManager().queryIntentServices(new Intent(this.mContext, BeaconService.class), 65536);
        if (resolveInfo != null && resolveInfo.isEmpty()) {
            throw new ServiceNotDeclaredException(this);
        }
    }

    public static boolean isAndroidLScanningDisabled() {
        return sAndroidLScanningDisabled;
    }

    public static void setAndroidLScanningDisabled(boolean disabled) {
        sAndroidLScanningDisabled = disabled;
        BeaconManager instance = sInstance;
        if (instance != null) {
            instance.applySettings();
        }
    }

    @Deprecated
    public static void setsManifestCheckingDisabled(boolean disabled) {
        sManifestCheckingDisabled = disabled;
    }

    public static void setManifestCheckingDisabled(boolean disabled) {
        sManifestCheckingDisabled = disabled;
    }

    public static boolean getManifestCheckingDisabled() {
        return sManifestCheckingDisabled;
    }

    private boolean determineIfCalledFromSeparateScannerProcess() {
        if (!isScannerInDifferentProcess() || isMainProcess()) {
            return false;
        }
        LogManager.w(TAG, "Ranging/Monitoring may not be controlled from a separate BeaconScanner process.  To remove this warning, please wrap this call in: if (beaconManager.isMainProcess())", new Object[0]);
        return true;
    }

    private static void warnIfScannerNotInSameProcess() {
        BeaconManager instance = sInstance;
        if (instance != null && instance.isScannerInDifferentProcess()) {
            LogManager.w(TAG, "Unsupported configuration change made for BeaconScanner in separate process", new Object[0]);
        }
    }
}
