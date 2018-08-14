package org.altbeacon.beacon.service;

import android.content.Context;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.BeaconParser;
import org.altbeacon.beacon.Region;
import org.altbeacon.beacon.logging.LogManager;

public class ScanState implements Serializable {
    public static int MIN_SCAN_JOB_INTERVAL_MILLIS = 300000;
    private static final String STATUS_PRESERVATION_FILE_NAME = "android-beacon-library-scan-state";
    private static final String TAG = ScanState.class.getSimpleName();
    private static final String TEMP_STATUS_PRESERVATION_FILE_NAME = "android-beacon-library-scan-state-temp";
    private long mBackgroundBetweenScanPeriod;
    private boolean mBackgroundMode;
    private long mBackgroundScanPeriod;
    private Set<BeaconParser> mBeaconParsers = new HashSet();
    private transient Context mContext;
    private ExtraDataBeaconTracker mExtraBeaconDataTracker = new ExtraDataBeaconTracker();
    private long mForegroundBetweenScanPeriod;
    private long mForegroundScanPeriod;
    private long mLastScanStartTimeMillis = 0;
    private transient MonitoringStatus mMonitoringStatus;
    private Map<Region, RangeState> mRangedRegionState = new HashMap();

    public Boolean getBackgroundMode() {
        return Boolean.valueOf(this.mBackgroundMode);
    }

    public void setBackgroundMode(Boolean backgroundMode) {
        this.mBackgroundMode = backgroundMode.booleanValue();
    }

    public Long getBackgroundBetweenScanPeriod() {
        return Long.valueOf(this.mBackgroundBetweenScanPeriod);
    }

    public void setBackgroundBetweenScanPeriod(Long backgroundBetweenScanPeriod) {
        this.mBackgroundBetweenScanPeriod = backgroundBetweenScanPeriod.longValue();
    }

    public Long getBackgroundScanPeriod() {
        return Long.valueOf(this.mBackgroundScanPeriod);
    }

    public void setBackgroundScanPeriod(Long backgroundScanPeriod) {
        this.mBackgroundScanPeriod = backgroundScanPeriod.longValue();
    }

    public Long getForegroundBetweenScanPeriod() {
        return Long.valueOf(this.mForegroundBetweenScanPeriod);
    }

    public void setForegroundBetweenScanPeriod(Long foregroundBetweenScanPeriod) {
        this.mForegroundBetweenScanPeriod = foregroundBetweenScanPeriod.longValue();
    }

    public Long getForegroundScanPeriod() {
        return Long.valueOf(this.mForegroundScanPeriod);
    }

    public void setForegroundScanPeriod(Long foregroundScanPeriod) {
        this.mForegroundScanPeriod = foregroundScanPeriod.longValue();
    }

    public ScanState(Context context) {
        this.mContext = context;
    }

    public MonitoringStatus getMonitoringStatus() {
        return this.mMonitoringStatus;
    }

    public void setMonitoringStatus(MonitoringStatus monitoringStatus) {
        this.mMonitoringStatus = monitoringStatus;
    }

    public Map<Region, RangeState> getRangedRegionState() {
        return this.mRangedRegionState;
    }

    public void setRangedRegionState(Map<Region, RangeState> rangedRegionState) {
        this.mRangedRegionState = rangedRegionState;
    }

    public ExtraDataBeaconTracker getExtraBeaconDataTracker() {
        return this.mExtraBeaconDataTracker;
    }

    public void setExtraBeaconDataTracker(ExtraDataBeaconTracker extraDataBeaconTracker) {
        this.mExtraBeaconDataTracker = extraDataBeaconTracker;
    }

    public Set<BeaconParser> getBeaconParsers() {
        return this.mBeaconParsers;
    }

    public void setBeaconParsers(Set<BeaconParser> beaconParsers) {
        this.mBeaconParsers = beaconParsers;
    }

    public long getLastScanStartTimeMillis() {
        return this.mLastScanStartTimeMillis;
    }

    public void setLastScanStartTimeMillis(long time) {
        this.mLastScanStartTimeMillis = time;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static org.altbeacon.beacon.service.ScanState restore(android.content.Context r12) {
        /*
        r6 = 0;
        r9 = org.altbeacon.beacon.service.ScanState.class;
        monitor-enter(r9);
        r3 = 0;
        r4 = 0;
        r8 = "android-beacon-library-scan-state";
        r3 = r12.openFileInput(r8);	 Catch:{ FileNotFoundException -> 0x0085, IOException -> 0x00a0, ClassNotFoundException -> 0x00e5, ClassCastException -> 0x00e8 }
        r5 = new java.io.ObjectInputStream;	 Catch:{ FileNotFoundException -> 0x0085, IOException -> 0x00a0, ClassNotFoundException -> 0x00e5, ClassCastException -> 0x00e8 }
        r5.<init>(r3);	 Catch:{ FileNotFoundException -> 0x0085, IOException -> 0x00a0, ClassNotFoundException -> 0x00e5, ClassCastException -> 0x00e8 }
        r8 = r5.readObject();	 Catch:{ FileNotFoundException -> 0x0108, IOException -> 0x0105, ClassNotFoundException -> 0x00ff, ClassCastException -> 0x0102, all -> 0x00fc }
        r0 = r8;
        r0 = (org.altbeacon.beacon.service.ScanState) r0;	 Catch:{ FileNotFoundException -> 0x0108, IOException -> 0x0105, ClassNotFoundException -> 0x00ff, ClassCastException -> 0x0102, all -> 0x00fc }
        r6 = r0;
        r6.mContext = r12;	 Catch:{ FileNotFoundException -> 0x0108, IOException -> 0x0105, ClassNotFoundException -> 0x00ff, ClassCastException -> 0x0102, all -> 0x00fc }
        if (r3 == 0) goto L_0x0021;
    L_0x001e:
        r3.close();	 Catch:{ IOException -> 0x00eb, all -> 0x00f6 }
    L_0x0021:
        if (r5 == 0) goto L_0x0112;
    L_0x0023:
        r5.close();	 Catch:{ IOException -> 0x0081, all -> 0x00f6 }
        r4 = r5;
        r7 = r6;
    L_0x0028:
        if (r7 != 0) goto L_0x010c;
    L_0x002a:
        r6 = new org.altbeacon.beacon.service.ScanState;	 Catch:{ all -> 0x00f9 }
        r6.<init>(r12);	 Catch:{ all -> 0x00f9 }
    L_0x002f:
        r8 = r6.mExtraBeaconDataTracker;	 Catch:{ all -> 0x00de }
        if (r8 != 0) goto L_0x003a;
    L_0x0033:
        r8 = new org.altbeacon.beacon.service.ExtraDataBeaconTracker;	 Catch:{ all -> 0x00de }
        r8.<init>();	 Catch:{ all -> 0x00de }
        r6.mExtraBeaconDataTracker = r8;	 Catch:{ all -> 0x00de }
    L_0x003a:
        r8 = org.altbeacon.beacon.service.MonitoringStatus.getInstanceForApplication(r12);	 Catch:{ all -> 0x00de }
        r6.mMonitoringStatus = r8;	 Catch:{ all -> 0x00de }
        r8 = TAG;	 Catch:{ all -> 0x00de }
        r10 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00de }
        r10.<init>();	 Catch:{ all -> 0x00de }
        r11 = "Scan state restore regions: monitored=";
        r10 = r10.append(r11);	 Catch:{ all -> 0x00de }
        r11 = r6.getMonitoringStatus();	 Catch:{ all -> 0x00de }
        r11 = r11.regions();	 Catch:{ all -> 0x00de }
        r11 = r11.size();	 Catch:{ all -> 0x00de }
        r10 = r10.append(r11);	 Catch:{ all -> 0x00de }
        r11 = " ranged=";
        r10 = r10.append(r11);	 Catch:{ all -> 0x00de }
        r11 = r6.getRangedRegionState();	 Catch:{ all -> 0x00de }
        r11 = r11.keySet();	 Catch:{ all -> 0x00de }
        r11 = r11.size();	 Catch:{ all -> 0x00de }
        r10 = r10.append(r11);	 Catch:{ all -> 0x00de }
        r10 = r10.toString();	 Catch:{ all -> 0x00de }
        r11 = 0;
        r11 = new java.lang.Object[r11];	 Catch:{ all -> 0x00de }
        org.altbeacon.beacon.logging.LogManager.m16371d(r8, r10, r11);	 Catch:{ all -> 0x00de }
        monitor-exit(r9);	 Catch:{ all -> 0x00de }
        return r6;
    L_0x0081:
        r8 = move-exception;
        r4 = r5;
        r7 = r6;
        goto L_0x0028;
    L_0x0085:
        r2 = move-exception;
    L_0x0086:
        r8 = TAG;	 Catch:{ all -> 0x00d2 }
        r10 = "Serialized ScanState does not exist.  This may be normal on first run.";
        r11 = 0;
        r11 = new java.lang.Object[r11];	 Catch:{ all -> 0x00d2 }
        org.altbeacon.beacon.logging.LogManager.m16379w(r8, r10, r11);	 Catch:{ all -> 0x00d2 }
        if (r3 == 0) goto L_0x0096;
    L_0x0093:
        r3.close();	 Catch:{ IOException -> 0x00ee }
    L_0x0096:
        if (r4 == 0) goto L_0x010f;
    L_0x0098:
        r4.close();	 Catch:{ IOException -> 0x009d }
        r7 = r6;
        goto L_0x0028;
    L_0x009d:
        r8 = move-exception;
        r7 = r6;
        goto L_0x0028;
    L_0x00a0:
        r8 = move-exception;
    L_0x00a1:
        r1 = r8;
    L_0x00a2:
        r8 = r1 instanceof java.io.InvalidClassException;	 Catch:{ all -> 0x00d2 }
        if (r8 == 0) goto L_0x00be;
    L_0x00a6:
        r8 = TAG;	 Catch:{ all -> 0x00d2 }
        r10 = "Serialized ScanState has wrong class. Just ignoring saved state...";
        r11 = 0;
        r11 = new java.lang.Object[r11];	 Catch:{ all -> 0x00d2 }
        org.altbeacon.beacon.logging.LogManager.m16371d(r8, r10, r11);	 Catch:{ all -> 0x00d2 }
    L_0x00b1:
        if (r3 == 0) goto L_0x00b6;
    L_0x00b3:
        r3.close();	 Catch:{ IOException -> 0x00f0 }
    L_0x00b6:
        if (r4 == 0) goto L_0x010f;
    L_0x00b8:
        r4.close();	 Catch:{ IOException -> 0x00e1 }
        r7 = r6;
        goto L_0x0028;
    L_0x00be:
        r8 = TAG;	 Catch:{ all -> 0x00d2 }
        r10 = "Deserialization exception";
        r11 = 0;
        r11 = new java.lang.Object[r11];	 Catch:{ all -> 0x00d2 }
        org.altbeacon.beacon.logging.LogManager.m16373e(r8, r10, r11);	 Catch:{ all -> 0x00d2 }
        r8 = TAG;	 Catch:{ all -> 0x00d2 }
        r10 = "error: ";
        android.util.Log.e(r8, r10, r1);	 Catch:{ all -> 0x00d2 }
        goto L_0x00b1;
    L_0x00d2:
        r8 = move-exception;
    L_0x00d3:
        if (r3 == 0) goto L_0x00d8;
    L_0x00d5:
        r3.close();	 Catch:{ IOException -> 0x00f2 }
    L_0x00d8:
        if (r4 == 0) goto L_0x00dd;
    L_0x00da:
        r4.close();	 Catch:{ IOException -> 0x00f4 }
    L_0x00dd:
        throw r8;	 Catch:{ all -> 0x00de }
    L_0x00de:
        r8 = move-exception;
    L_0x00df:
        monitor-exit(r9);	 Catch:{ all -> 0x00de }
        throw r8;
    L_0x00e1:
        r8 = move-exception;
        r7 = r6;
        goto L_0x0028;
    L_0x00e5:
        r8 = move-exception;
    L_0x00e6:
        r1 = r8;
        goto L_0x00a2;
    L_0x00e8:
        r8 = move-exception;
    L_0x00e9:
        r1 = r8;
        goto L_0x00a2;
    L_0x00eb:
        r8 = move-exception;
        goto L_0x0021;
    L_0x00ee:
        r8 = move-exception;
        goto L_0x0096;
    L_0x00f0:
        r8 = move-exception;
        goto L_0x00b6;
    L_0x00f2:
        r10 = move-exception;
        goto L_0x00d8;
    L_0x00f4:
        r10 = move-exception;
        goto L_0x00dd;
    L_0x00f6:
        r8 = move-exception;
        r4 = r5;
        goto L_0x00df;
    L_0x00f9:
        r8 = move-exception;
        r6 = r7;
        goto L_0x00df;
    L_0x00fc:
        r8 = move-exception;
        r4 = r5;
        goto L_0x00d3;
    L_0x00ff:
        r8 = move-exception;
        r4 = r5;
        goto L_0x00e6;
    L_0x0102:
        r8 = move-exception;
        r4 = r5;
        goto L_0x00e9;
    L_0x0105:
        r8 = move-exception;
        r4 = r5;
        goto L_0x00a1;
    L_0x0108:
        r2 = move-exception;
        r4 = r5;
        goto L_0x0086;
    L_0x010c:
        r6 = r7;
        goto L_0x002f;
    L_0x010f:
        r7 = r6;
        goto L_0x0028;
    L_0x0112:
        r4 = r5;
        r7 = r6;
        goto L_0x0028;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.altbeacon.beacon.service.ScanState.restore(android.content.Context):org.altbeacon.beacon.service.ScanState");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void save() {
        /*
        r12 = this;
        r7 = org.altbeacon.beacon.service.ScanState.class;
        monitor-enter(r7);
        r4 = 0;
        r2 = 0;
        r6 = r12.mContext;	 Catch:{ IOException -> 0x00aa }
        r8 = "android-beacon-library-scan-state-temp";
        r9 = 0;
        r4 = r6.openFileOutput(r8, r9);	 Catch:{ IOException -> 0x00aa }
        r3 = new java.io.ObjectOutputStream;	 Catch:{ IOException -> 0x00aa }
        r3.<init>(r4);	 Catch:{ IOException -> 0x00aa }
        r3.writeObject(r12);	 Catch:{ IOException -> 0x00e7, all -> 0x00e4 }
        r1 = new java.io.File;	 Catch:{ IOException -> 0x00e7, all -> 0x00e4 }
        r6 = r12.mContext;	 Catch:{ IOException -> 0x00e7, all -> 0x00e4 }
        r6 = r6.getFilesDir();	 Catch:{ IOException -> 0x00e7, all -> 0x00e4 }
        r8 = "android-beacon-library-scan-state";
        r1.<init>(r6, r8);	 Catch:{ IOException -> 0x00e7, all -> 0x00e4 }
        r5 = new java.io.File;	 Catch:{ IOException -> 0x00e7, all -> 0x00e4 }
        r6 = r12.mContext;	 Catch:{ IOException -> 0x00e7, all -> 0x00e4 }
        r6 = r6.getFilesDir();	 Catch:{ IOException -> 0x00e7, all -> 0x00e4 }
        r8 = "android-beacon-library-scan-state-temp";
        r5.<init>(r6, r8);	 Catch:{ IOException -> 0x00e7, all -> 0x00e4 }
        r6 = TAG;	 Catch:{ IOException -> 0x00e7, all -> 0x00e4 }
        r8 = new java.lang.StringBuilder;	 Catch:{ IOException -> 0x00e7, all -> 0x00e4 }
        r8.<init>();	 Catch:{ IOException -> 0x00e7, all -> 0x00e4 }
        r9 = "Temp file is ";
        r8 = r8.append(r9);	 Catch:{ IOException -> 0x00e7, all -> 0x00e4 }
        r9 = r5.getAbsolutePath();	 Catch:{ IOException -> 0x00e7, all -> 0x00e4 }
        r8 = r8.append(r9);	 Catch:{ IOException -> 0x00e7, all -> 0x00e4 }
        r8 = r8.toString();	 Catch:{ IOException -> 0x00e7, all -> 0x00e4 }
        r9 = 0;
        r9 = new java.lang.Object[r9];	 Catch:{ IOException -> 0x00e7, all -> 0x00e4 }
        org.altbeacon.beacon.logging.LogManager.m16371d(r6, r8, r9);	 Catch:{ IOException -> 0x00e7, all -> 0x00e4 }
        r6 = TAG;	 Catch:{ IOException -> 0x00e7, all -> 0x00e4 }
        r8 = new java.lang.StringBuilder;	 Catch:{ IOException -> 0x00e7, all -> 0x00e4 }
        r8.<init>();	 Catch:{ IOException -> 0x00e7, all -> 0x00e4 }
        r9 = "Perm file is ";
        r8 = r8.append(r9);	 Catch:{ IOException -> 0x00e7, all -> 0x00e4 }
        r9 = r1.getAbsolutePath();	 Catch:{ IOException -> 0x00e7, all -> 0x00e4 }
        r8 = r8.append(r9);	 Catch:{ IOException -> 0x00e7, all -> 0x00e4 }
        r8 = r8.toString();	 Catch:{ IOException -> 0x00e7, all -> 0x00e4 }
        r9 = 0;
        r9 = new java.lang.Object[r9];	 Catch:{ IOException -> 0x00e7, all -> 0x00e4 }
        org.altbeacon.beacon.logging.LogManager.m16371d(r6, r8, r9);	 Catch:{ IOException -> 0x00e7, all -> 0x00e4 }
        r6 = r1.delete();	 Catch:{ IOException -> 0x00e7, all -> 0x00e4 }
        if (r6 != 0) goto L_0x0084;
    L_0x0079:
        r6 = TAG;	 Catch:{ IOException -> 0x00e7, all -> 0x00e4 }
        r8 = "Error while saving scan status to file: Cannot delete existing file.";
        r9 = 0;
        r9 = new java.lang.Object[r9];	 Catch:{ IOException -> 0x00e7, all -> 0x00e4 }
        org.altbeacon.beacon.logging.LogManager.m16373e(r6, r8, r9);	 Catch:{ IOException -> 0x00e7, all -> 0x00e4 }
    L_0x0084:
        r6 = r5.renameTo(r1);	 Catch:{ IOException -> 0x00e7, all -> 0x00e4 }
        if (r6 != 0) goto L_0x0095;
    L_0x008a:
        r6 = TAG;	 Catch:{ IOException -> 0x00e7, all -> 0x00e4 }
        r8 = "Error while saving scan status to file: Cannot rename temp file.";
        r9 = 0;
        r9 = new java.lang.Object[r9];	 Catch:{ IOException -> 0x00e7, all -> 0x00e4 }
        org.altbeacon.beacon.logging.LogManager.m16373e(r6, r8, r9);	 Catch:{ IOException -> 0x00e7, all -> 0x00e4 }
    L_0x0095:
        if (r4 == 0) goto L_0x009a;
    L_0x0097:
        r4.close();	 Catch:{ IOException -> 0x00d9, all -> 0x00e1 }
    L_0x009a:
        if (r3 == 0) goto L_0x00ea;
    L_0x009c:
        r3.close();	 Catch:{ IOException -> 0x00a7, all -> 0x00e1 }
        r2 = r3;
    L_0x00a0:
        r6 = r12.mMonitoringStatus;	 Catch:{ all -> 0x00d6 }
        r6.saveMonitoringStatusIfOn();	 Catch:{ all -> 0x00d6 }
        monitor-exit(r7);	 Catch:{ all -> 0x00d6 }
        return;
    L_0x00a7:
        r6 = move-exception;
        r2 = r3;
        goto L_0x00a0;
    L_0x00aa:
        r0 = move-exception;
    L_0x00ab:
        r6 = TAG;	 Catch:{ all -> 0x00ca }
        r8 = "Error while saving scan status to file: ";
        r9 = 1;
        r9 = new java.lang.Object[r9];	 Catch:{ all -> 0x00ca }
        r10 = 0;
        r11 = r0.getMessage();	 Catch:{ all -> 0x00ca }
        r9[r10] = r11;	 Catch:{ all -> 0x00ca }
        org.altbeacon.beacon.logging.LogManager.m16373e(r6, r8, r9);	 Catch:{ all -> 0x00ca }
        if (r4 == 0) goto L_0x00c2;
    L_0x00bf:
        r4.close();	 Catch:{ IOException -> 0x00db }
    L_0x00c2:
        if (r2 == 0) goto L_0x00a0;
    L_0x00c4:
        r2.close();	 Catch:{ IOException -> 0x00c8 }
        goto L_0x00a0;
    L_0x00c8:
        r6 = move-exception;
        goto L_0x00a0;
    L_0x00ca:
        r6 = move-exception;
    L_0x00cb:
        if (r4 == 0) goto L_0x00d0;
    L_0x00cd:
        r4.close();	 Catch:{ IOException -> 0x00dd }
    L_0x00d0:
        if (r2 == 0) goto L_0x00d5;
    L_0x00d2:
        r2.close();	 Catch:{ IOException -> 0x00df }
    L_0x00d5:
        throw r6;	 Catch:{ all -> 0x00d6 }
    L_0x00d6:
        r6 = move-exception;
    L_0x00d7:
        monitor-exit(r7);	 Catch:{ all -> 0x00d6 }
        throw r6;
    L_0x00d9:
        r6 = move-exception;
        goto L_0x009a;
    L_0x00db:
        r6 = move-exception;
        goto L_0x00c2;
    L_0x00dd:
        r8 = move-exception;
        goto L_0x00d0;
    L_0x00df:
        r8 = move-exception;
        goto L_0x00d5;
    L_0x00e1:
        r6 = move-exception;
        r2 = r3;
        goto L_0x00d7;
    L_0x00e4:
        r6 = move-exception;
        r2 = r3;
        goto L_0x00cb;
    L_0x00e7:
        r0 = move-exception;
        r2 = r3;
        goto L_0x00ab;
    L_0x00ea:
        r2 = r3;
        goto L_0x00a0;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.altbeacon.beacon.service.ScanState.save():void");
    }

    public int getScanJobIntervalMillis() {
        long cyclePeriodMillis;
        if (getBackgroundMode().booleanValue()) {
            cyclePeriodMillis = getBackgroundScanPeriod().longValue() + getBackgroundBetweenScanPeriod().longValue();
        } else {
            cyclePeriodMillis = getForegroundScanPeriod().longValue() + getForegroundBetweenScanPeriod().longValue();
        }
        int scanJobIntervalMillis = MIN_SCAN_JOB_INTERVAL_MILLIS;
        if (cyclePeriodMillis > ((long) MIN_SCAN_JOB_INTERVAL_MILLIS)) {
            return (int) cyclePeriodMillis;
        }
        return scanJobIntervalMillis;
    }

    public int getScanJobRuntimeMillis() {
        long scanPeriodMillis;
        LogManager.m16371d(TAG, "ScanState says background mode for ScanJob is " + getBackgroundMode(), new Object[0]);
        if (getBackgroundMode().booleanValue()) {
            scanPeriodMillis = getBackgroundScanPeriod().longValue();
        } else {
            scanPeriodMillis = getForegroundScanPeriod().longValue();
        }
        if (getBackgroundMode().booleanValue() || scanPeriodMillis >= ((long) MIN_SCAN_JOB_INTERVAL_MILLIS)) {
            return (int) scanPeriodMillis;
        }
        return MIN_SCAN_JOB_INTERVAL_MILLIS;
    }

    public void applyChanges(BeaconManager beaconManager) {
        this.mBeaconParsers = new HashSet(beaconManager.getBeaconParsers());
        this.mForegroundScanPeriod = beaconManager.getForegroundScanPeriod();
        this.mForegroundBetweenScanPeriod = beaconManager.getForegroundBetweenScanPeriod();
        this.mBackgroundScanPeriod = beaconManager.getBackgroundScanPeriod();
        this.mBackgroundBetweenScanPeriod = beaconManager.getBackgroundBetweenScanPeriod();
        this.mBackgroundMode = beaconManager.getBackgroundMode();
        ArrayList<Region> existingMonitoredRegions = new ArrayList(this.mMonitoringStatus.regions());
        ArrayList<Region> existingRangedRegions = new ArrayList(this.mRangedRegionState.keySet());
        ArrayList<Region> newMonitoredRegions = new ArrayList(beaconManager.getMonitoredRegions());
        ArrayList<Region> newRangedRegions = new ArrayList(beaconManager.getRangedRegions());
        LogManager.m16371d(TAG, "ranged regions: old=" + existingRangedRegions.size() + " new=" + newRangedRegions.size(), new Object[0]);
        LogManager.m16371d(TAG, "monitored regions: old=" + existingMonitoredRegions.size() + " new=" + newMonitoredRegions.size(), new Object[0]);
        Iterator it = newRangedRegions.iterator();
        while (it.hasNext()) {
            Region newRangedRegion = (Region) it.next();
            if (!existingRangedRegions.contains(newRangedRegion)) {
                LogManager.m16371d(TAG, "Starting ranging region: " + newRangedRegion, new Object[0]);
                this.mRangedRegionState.put(newRangedRegion, new RangeState(new Callback(this.mContext.getPackageName())));
            }
        }
        it = existingRangedRegions.iterator();
        while (it.hasNext()) {
            Region existingRangedRegion = (Region) it.next();
            if (!newRangedRegions.contains(existingRangedRegion)) {
                LogManager.m16371d(TAG, "Stopping ranging region: " + existingRangedRegion, new Object[0]);
                this.mRangedRegionState.remove(existingRangedRegion);
            }
        }
        LogManager.m16371d(TAG, "Updated state with " + newRangedRegions.size() + " ranging regions and " + newMonitoredRegions.size() + " monitoring regions.", new Object[0]);
        save();
    }
}
