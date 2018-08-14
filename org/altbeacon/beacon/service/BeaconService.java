package org.altbeacon.beacon.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageItemInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Binder;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.support.annotation.MainThread;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.v4.app.NotificationCompat;
import com.bgjd.ici.p025b.C1408j.C1403a;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;
import org.altbeacon.beacon.Beacon;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.BuildConfig;
import org.altbeacon.beacon.Region;
import org.altbeacon.beacon.distance.ModelSpecificDistanceCalculator;
import org.altbeacon.beacon.logging.LogManager;
import org.altbeacon.beacon.service.scanner.CycledLeScanCallback;
import org.altbeacon.beacon.startup.StartupBroadcastReceiver;
import org.altbeacon.beacon.utils.ProcessUtils;
import org.altbeacon.bluetooth.BluetoothCrashResolver;

public class BeaconService extends Service {
    public static final int MSG_SET_SCAN_PERIODS = 6;
    public static final int MSG_START_MONITORING = 4;
    public static final int MSG_START_RANGING = 2;
    public static final int MSG_STOP_MONITORING = 5;
    public static final int MSG_STOP_RANGING = 3;
    public static final int MSG_SYNC_SETTINGS = 7;
    public static final String TAG = "BeaconService";
    private BluetoothCrashResolver bluetoothCrashResolver;
    private final Handler handler = new Handler();
    final Messenger mMessenger = new Messenger(new IncomingHandler(this));
    private ScanHelper mScanHelper;

    public class BeaconBinder extends Binder {
        public BeaconService getService() {
            LogManager.m16375i(BeaconService.TAG, "getService of BeaconBinder called", new Object[0]);
            return BeaconService.this;
        }
    }

    static class IncomingHandler extends Handler {
        private final WeakReference<BeaconService> mService;

        IncomingHandler(BeaconService service) {
            super(Looper.getMainLooper());
            this.mService = new WeakReference(service);
        }

        @MainThread
        public void handleMessage(Message msg) {
            BeaconService service = (BeaconService) this.mService.get();
            if (service != null) {
                StartRMData startRMData = StartRMData.fromBundle(msg.getData());
                if (startRMData != null) {
                    switch (msg.what) {
                        case 2:
                            LogManager.m16375i(BeaconService.TAG, "start ranging received", new Object[0]);
                            service.startRangingBeaconsInRegion(startRMData.getRegionData(), new Callback(startRMData.getCallbackPackageName()));
                            service.setScanPeriods(startRMData.getScanPeriod(), startRMData.getBetweenScanPeriod(), startRMData.getBackgroundFlag());
                            return;
                        case 3:
                            LogManager.m16375i(BeaconService.TAG, "stop ranging received", new Object[0]);
                            service.stopRangingBeaconsInRegion(startRMData.getRegionData());
                            service.setScanPeriods(startRMData.getScanPeriod(), startRMData.getBetweenScanPeriod(), startRMData.getBackgroundFlag());
                            return;
                        case 4:
                            LogManager.m16375i(BeaconService.TAG, "start monitoring received", new Object[0]);
                            service.startMonitoringBeaconsInRegion(startRMData.getRegionData(), new Callback(startRMData.getCallbackPackageName()));
                            service.setScanPeriods(startRMData.getScanPeriod(), startRMData.getBetweenScanPeriod(), startRMData.getBackgroundFlag());
                            return;
                        case 5:
                            LogManager.m16375i(BeaconService.TAG, "stop monitoring received", new Object[0]);
                            service.stopMonitoringBeaconsInRegion(startRMData.getRegionData());
                            service.setScanPeriods(startRMData.getScanPeriod(), startRMData.getBetweenScanPeriod(), startRMData.getBackgroundFlag());
                            return;
                        case 6:
                            LogManager.m16375i(BeaconService.TAG, "set scan intervals received", new Object[0]);
                            service.setScanPeriods(startRMData.getScanPeriod(), startRMData.getBetweenScanPeriod(), startRMData.getBackgroundFlag());
                            return;
                        default:
                            super.handleMessage(msg);
                            return;
                    }
                } else if (msg.what == 7) {
                    LogManager.m16375i(BeaconService.TAG, "Received settings update from other process", new Object[0]);
                    SettingsData settingsData = SettingsData.fromBundle(msg.getData());
                    if (settingsData != null) {
                        settingsData.apply(service);
                    } else {
                        LogManager.m16379w(BeaconService.TAG, "Settings data missing", new Object[0]);
                    }
                } else {
                    LogManager.m16375i(BeaconService.TAG, "Received unknown message from other process : " + msg.what, new Object[0]);
                }
            }
        }
    }

    @MainThread
    public void onCreate() {
        this.bluetoothCrashResolver = new BluetoothCrashResolver(this);
        this.bluetoothCrashResolver.start();
        this.mScanHelper = new ScanHelper(this);
        if (this.mScanHelper.getCycledScanner() == null) {
            this.mScanHelper.createCycledLeScanner(false, this.bluetoothCrashResolver);
        }
        this.mScanHelper.setMonitoringStatus(MonitoringStatus.getInstanceForApplication(this));
        this.mScanHelper.setRangedRegionState(new HashMap());
        this.mScanHelper.setBeaconParsers(new HashSet());
        this.mScanHelper.setExtraDataBeaconTracker(new ExtraDataBeaconTracker());
        BeaconManager beaconManager = BeaconManager.getInstanceForApplication(getApplicationContext());
        beaconManager.setScannerInSameProcess(true);
        if (beaconManager.isMainProcess()) {
            LogManager.m16375i(TAG, "beaconService version %s is starting up on the main process", BuildConfig.VERSION_NAME);
        } else {
            LogManager.m16375i(TAG, "beaconService version %s is starting up on a separate process", BuildConfig.VERSION_NAME);
            ProcessUtils processUtils = new ProcessUtils(this);
            LogManager.m16375i(TAG, "beaconService PID is " + processUtils.getPid() + " with process name " + processUtils.getProcessName(), new Object[0]);
        }
        try {
            PackageItemInfo info = getPackageManager().getServiceInfo(new ComponentName(this, BeaconService.class), 128);
            if (!(info == null || info.metaData == null || info.metaData.get("longScanForcingEnabled") == null || !info.metaData.get("longScanForcingEnabled").toString().equals(SchemaSymbols.ATTVAL_TRUE))) {
                LogManager.m16375i(TAG, "longScanForcingEnabled to keep scans going on Android N for > 30 minutes", new Object[0]);
                this.mScanHelper.getCycledScanner().setLongScanForcingEnabled(true);
            }
        } catch (NameNotFoundException e) {
        }
        this.mScanHelper.reloadParsers();
        Beacon.setDistanceCalculator(new ModelSpecificDistanceCalculator(this, BeaconManager.getDistanceModelUpdateUrl()));
        try {
            this.mScanHelper.setSimulatedScanData((List) Class.forName("org.altbeacon.beacon.SimulatedScanData").getField(C1403a.f2090t).get(null));
        } catch (ClassNotFoundException e2) {
            LogManager.m16371d(TAG, "No org.altbeacon.beacon.SimulatedScanData class exists.", new Object[0]);
        } catch (Exception e3) {
            LogManager.m16374e(e3, TAG, "Cannot get simulated Scan data.  Make sure your org.altbeacon.beacon.SimulatedScanData class defines a field with the signature 'public static List<Beacon> beacons'", new Object[0]);
        }
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        String str;
        String str2 = TAG;
        if (intent == null) {
            str = "starting with null intent";
        } else {
            str = "starting with intent " + intent.toString();
        }
        LogManager.m16375i(str2, str, new Object[0]);
        return super.onStartCommand(intent, flags, startId);
    }

    public IBinder onBind(Intent intent) {
        LogManager.m16375i(TAG, "binding", new Object[0]);
        return this.mMessenger.getBinder();
    }

    public boolean onUnbind(Intent intent) {
        LogManager.m16375i(TAG, "unbinding", new Object[0]);
        return false;
    }

    @MainThread
    public void onDestroy() {
        LogManager.m16373e(TAG, "onDestroy()", new Object[0]);
        if (VERSION.SDK_INT < 18) {
            LogManager.m16379w(TAG, "Not supported prior to API 18.", new Object[0]);
            return;
        }
        this.bluetoothCrashResolver.stop();
        LogManager.m16375i(TAG, "onDestroy called.  stopping scanning", new Object[0]);
        this.handler.removeCallbacksAndMessages(null);
        this.mScanHelper.getCycledScanner().stop();
        this.mScanHelper.getCycledScanner().destroy();
        this.mScanHelper.getMonitoringStatus().stopStatusPreservation();
    }

    public void onTaskRemoved(Intent rootIntent) {
        super.onTaskRemoved(rootIntent);
        LogManager.m16371d(TAG, "task removed", new Object[0]);
        if (VERSION.RELEASE.contains("4.4.1") || VERSION.RELEASE.contains("4.4.2") || VERSION.RELEASE.contains("4.4.3")) {
            ((AlarmManager) getApplicationContext().getSystemService(NotificationCompat.CATEGORY_ALARM)).set(0, System.currentTimeMillis() + 1000, getRestartIntent());
            LogManager.m16371d(TAG, "Setting a wakeup alarm to go off due to Android 4.4.2 service restarting bug.", new Object[0]);
        }
    }

    private PendingIntent getRestartIntent() {
        return PendingIntent.getBroadcast(getApplicationContext(), 1, new Intent(getApplicationContext(), StartupBroadcastReceiver.class), 1073741824);
    }

    @MainThread
    public void startRangingBeaconsInRegion(Region region, Callback callback) {
        synchronized (this.mScanHelper.getRangedRegionState()) {
            if (this.mScanHelper.getRangedRegionState().containsKey(region)) {
                LogManager.m16375i(TAG, "Already ranging that region -- will replace existing region.", new Object[0]);
                this.mScanHelper.getRangedRegionState().remove(region);
            }
            this.mScanHelper.getRangedRegionState().put(region, new RangeState(callback));
            LogManager.m16371d(TAG, "Currently ranging %s regions.", Integer.valueOf(this.mScanHelper.getRangedRegionState().size()));
        }
        this.mScanHelper.getCycledScanner().start();
    }

    @MainThread
    public void stopRangingBeaconsInRegion(Region region) {
        synchronized (this.mScanHelper.getRangedRegionState()) {
            this.mScanHelper.getRangedRegionState().remove(region);
            int rangedRegionCount = this.mScanHelper.getRangedRegionState().size();
            LogManager.m16371d(TAG, "Currently ranging %s regions.", Integer.valueOf(this.mScanHelper.getRangedRegionState().size()));
        }
        if (rangedRegionCount == 0 && this.mScanHelper.getMonitoringStatus().regionsCount() == 0) {
            this.mScanHelper.getCycledScanner().stop();
        }
    }

    @MainThread
    public void startMonitoringBeaconsInRegion(Region region, Callback callback) {
        LogManager.m16371d(TAG, "startMonitoring called", new Object[0]);
        this.mScanHelper.getMonitoringStatus().addRegion(region, callback);
        LogManager.m16371d(TAG, "Currently monitoring %s regions.", Integer.valueOf(this.mScanHelper.getMonitoringStatus().regionsCount()));
        this.mScanHelper.getCycledScanner().start();
    }

    @MainThread
    public void stopMonitoringBeaconsInRegion(Region region) {
        LogManager.m16371d(TAG, "stopMonitoring called", new Object[0]);
        this.mScanHelper.getMonitoringStatus().removeRegion(region);
        LogManager.m16371d(TAG, "Currently monitoring %s regions.", Integer.valueOf(this.mScanHelper.getMonitoringStatus().regionsCount()));
        if (this.mScanHelper.getMonitoringStatus().regionsCount() == 0 && this.mScanHelper.getRangedRegionState().size() == 0) {
            this.mScanHelper.getCycledScanner().stop();
        }
    }

    @MainThread
    public void setScanPeriods(long scanPeriod, long betweenScanPeriod, boolean backgroundFlag) {
        this.mScanHelper.getCycledScanner().setScanPeriods(scanPeriod, betweenScanPeriod, backgroundFlag);
    }

    public void reloadParsers() {
        this.mScanHelper.reloadParsers();
    }

    @RestrictTo({Scope.TESTS})
    protected CycledLeScanCallback getCycledLeScanCallback() {
        return this.mScanHelper.getCycledLeScanCallback();
    }
}
