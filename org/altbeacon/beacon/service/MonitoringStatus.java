package org.altbeacon.beacon.service;

import android.content.Context;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.altbeacon.beacon.Beacon;
import org.altbeacon.beacon.Region;
import org.altbeacon.beacon.logging.LogManager;

public class MonitoringStatus {
    private static final int MAX_REGIONS_FOR_STATUS_PRESERVATION = 50;
    private static final int MAX_STATUS_PRESERVATION_FILE_AGE_TO_RESTORE_SECS = 900;
    private static final Object SINGLETON_LOCK = new Object();
    public static final String STATUS_PRESERVATION_FILE_NAME = "org.altbeacon.beacon.service.monitoring_status_state";
    private static final String TAG = MonitoringStatus.class.getSimpleName();
    private static volatile MonitoringStatus sInstance;
    private Context mContext;
    private Map<Region, RegionMonitoringState> mRegionsStatesMap;
    private boolean mStatePreservationIsOn = true;

    public static MonitoringStatus getInstanceForApplication(Context context) {
        MonitoringStatus instance = sInstance;
        if (instance == null) {
            synchronized (SINGLETON_LOCK) {
                try {
                    instance = sInstance;
                    if (instance == null) {
                        MonitoringStatus instance2 = new MonitoringStatus(context.getApplicationContext());
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

    public MonitoringStatus(Context context) {
        this.mContext = context;
    }

    public synchronized void addRegion(Region region, Callback callback) {
        addLocalRegion(region, callback);
        saveMonitoringStatusIfOn();
    }

    public synchronized void removeRegion(Region region) {
        removeLocalRegion(region);
        saveMonitoringStatusIfOn();
    }

    public synchronized Set<Region> regions() {
        return getRegionsStateMap().keySet();
    }

    public synchronized int regionsCount() {
        return regions().size();
    }

    public synchronized RegionMonitoringState stateOf(Region region) {
        return (RegionMonitoringState) getRegionsStateMap().get(region);
    }

    public synchronized void updateNewlyOutside() {
        boolean needsMonitoringStateSaving = false;
        for (Region region : regions()) {
            RegionMonitoringState state = stateOf(region);
            if (state.markOutsideIfExpired()) {
                needsMonitoringStateSaving = true;
                LogManager.m16371d(TAG, "found a monitor that expired: %s", region);
                state.getCallback().call(this.mContext, "monitoringData", new MonitoringData(state.getInside(), region).toBundle());
            }
        }
        if (needsMonitoringStateSaving) {
            saveMonitoringStatusIfOn();
        } else {
            updateMonitoringStatusTime(System.currentTimeMillis());
        }
    }

    public synchronized void updateNewlyInsideInRegionsContaining(Beacon beacon) {
        boolean needsMonitoringStateSaving = false;
        for (Region region : regionsMatchingTo(beacon)) {
            RegionMonitoringState state = (RegionMonitoringState) getRegionsStateMap().get(region);
            if (state != null && state.markInside()) {
                needsMonitoringStateSaving = true;
                state.getCallback().call(this.mContext, "monitoringData", new MonitoringData(state.getInside(), region).toBundle());
            }
        }
        if (needsMonitoringStateSaving) {
            saveMonitoringStatusIfOn();
        } else {
            updateMonitoringStatusTime(System.currentTimeMillis());
        }
    }

    private Map<Region, RegionMonitoringState> getRegionsStateMap() {
        if (this.mRegionsStatesMap == null) {
            restoreOrInitializeMonitoringStatus();
        }
        return this.mRegionsStatesMap;
    }

    private void restoreOrInitializeMonitoringStatus() {
        long millisSinceLastMonitor = System.currentTimeMillis() - getLastMonitoringStatusUpdateTime();
        this.mRegionsStatesMap = new ConcurrentHashMap();
        if (!this.mStatePreservationIsOn) {
            LogManager.m16371d(TAG, "Not restoring monitoring state because persistence is disabled", new Object[0]);
        } else if (millisSinceLastMonitor > 900000) {
            LogManager.m16371d(TAG, "Not restoring monitoring state because it was recorded too many milliseconds ago: " + millisSinceLastMonitor, new Object[0]);
        } else {
            restoreMonitoringStatus();
            LogManager.m16371d(TAG, "Done restoring monitoring status", new Object[0]);
        }
    }

    private List<Region> regionsMatchingTo(Beacon beacon) {
        List<Region> matched = new ArrayList();
        for (Region region : regions()) {
            if (region.matchesBeacon(beacon)) {
                matched.add(region);
            } else {
                LogManager.m16371d(TAG, "This region (%s) does not match beacon: %s", region, beacon);
            }
        }
        return matched;
    }

    protected void saveMonitoringStatusIfOn() {
        IOException e;
        Throwable th;
        if (this.mStatePreservationIsOn) {
            LogManager.m16371d(TAG, "saveMonitoringStatusIfOn()", new Object[0]);
            if (getRegionsStateMap().size() > 50) {
                LogManager.m16379w(TAG, "Too many regions being monitored.  Will not persist region state", new Object[0]);
                this.mContext.deleteFile(STATUS_PRESERVATION_FILE_NAME);
                return;
            }
            FileOutputStream outputStream = null;
            ObjectOutputStream objectOutputStream = null;
            try {
                outputStream = this.mContext.openFileOutput(STATUS_PRESERVATION_FILE_NAME, 0);
                ObjectOutputStream objectOutputStream2 = new ObjectOutputStream(outputStream);
                try {
                    Map<Region, RegionMonitoringState> map = getRegionsStateMap();
                    HashMap<Region, RegionMonitoringState> serializableMap = new HashMap();
                    for (Region region : map.keySet()) {
                        serializableMap.put(region, map.get(region));
                    }
                    objectOutputStream2.writeObject(serializableMap);
                    if (outputStream != null) {
                        try {
                            outputStream.close();
                        } catch (IOException e2) {
                        }
                    }
                    if (objectOutputStream2 != null) {
                        try {
                            objectOutputStream2.close();
                        } catch (IOException e3) {
                        }
                    }
                } catch (IOException e4) {
                    e = e4;
                    objectOutputStream = objectOutputStream2;
                    try {
                        LogManager.m16373e(TAG, "Error while saving monitored region states to file ", e);
                        e.printStackTrace(System.err);
                        if (outputStream != null) {
                            try {
                                outputStream.close();
                            } catch (IOException e5) {
                            }
                        }
                        if (objectOutputStream != null) {
                            try {
                                objectOutputStream.close();
                            } catch (IOException e6) {
                            }
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        if (outputStream != null) {
                            try {
                                outputStream.close();
                            } catch (IOException e7) {
                            }
                        }
                        if (objectOutputStream != null) {
                            try {
                                objectOutputStream.close();
                            } catch (IOException e8) {
                            }
                        }
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    objectOutputStream = objectOutputStream2;
                    if (outputStream != null) {
                        outputStream.close();
                    }
                    if (objectOutputStream != null) {
                        objectOutputStream.close();
                    }
                    throw th;
                }
            } catch (IOException e9) {
                e = e9;
                LogManager.m16373e(TAG, "Error while saving monitored region states to file ", e);
                e.printStackTrace(System.err);
                if (outputStream != null) {
                    outputStream.close();
                }
                if (objectOutputStream != null) {
                    objectOutputStream.close();
                }
            }
        }
    }

    protected void updateMonitoringStatusTime(long time) {
        this.mContext.getFileStreamPath(STATUS_PRESERVATION_FILE_NAME).setLastModified(time);
    }

    protected long getLastMonitoringStatusUpdateTime() {
        return this.mContext.getFileStreamPath(STATUS_PRESERVATION_FILE_NAME).lastModified();
    }

    protected void restoreMonitoringStatus() {
        Exception e;
        Exception e2;
        Throwable th;
        FileInputStream inputStream = null;
        ObjectInputStream objectInputStream = null;
        try {
            inputStream = this.mContext.openFileInput(STATUS_PRESERVATION_FILE_NAME);
            ObjectInputStream objectInputStream2 = new ObjectInputStream(inputStream);
            try {
                Map<Region, RegionMonitoringState> obj = (Map) objectInputStream2.readObject();
                LogManager.m16371d(TAG, "Restored region monitoring state for " + obj.size() + " regions.", new Object[0]);
                for (Region region : obj.keySet()) {
                    LogManager.m16371d(TAG, "Region  " + region + " uniqueId: " + region.getUniqueId() + " state: " + obj.get(region), new Object[0]);
                }
                for (RegionMonitoringState regionMonitoringState : obj.values()) {
                    if (regionMonitoringState.getInside()) {
                        regionMonitoringState.markInside();
                    }
                }
                this.mRegionsStatesMap.putAll(obj);
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e3) {
                    }
                }
                if (objectInputStream2 != null) {
                    try {
                        objectInputStream2.close();
                        objectInputStream = objectInputStream2;
                        return;
                    } catch (IOException e4) {
                        objectInputStream = objectInputStream2;
                        return;
                    }
                }
            } catch (IOException e5) {
                e = e5;
                objectInputStream = objectInputStream2;
                e2 = e;
                try {
                    if (e2 instanceof InvalidClassException) {
                        LogManager.m16371d(TAG, "Serialized Monitoring State has wrong class. Just ignoring saved state...", new Object[0]);
                    } else {
                        LogManager.m16373e(TAG, "Deserialization exception, message: %s", e2.getMessage());
                    }
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException e6) {
                        }
                    }
                    if (objectInputStream != null) {
                        try {
                            objectInputStream.close();
                        } catch (IOException e7) {
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException e8) {
                        }
                    }
                    if (objectInputStream != null) {
                        try {
                            objectInputStream.close();
                        } catch (IOException e9) {
                        }
                    }
                    throw th;
                }
            } catch (ClassNotFoundException e10) {
                e = e10;
                objectInputStream = objectInputStream2;
                e2 = e;
                if (e2 instanceof InvalidClassException) {
                    LogManager.m16371d(TAG, "Serialized Monitoring State has wrong class. Just ignoring saved state...", new Object[0]);
                } else {
                    LogManager.m16373e(TAG, "Deserialization exception, message: %s", e2.getMessage());
                }
                if (inputStream != null) {
                    inputStream.close();
                }
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
            } catch (ClassCastException e11) {
                e = e11;
                objectInputStream = objectInputStream2;
                e2 = e;
                if (e2 instanceof InvalidClassException) {
                    LogManager.m16373e(TAG, "Deserialization exception, message: %s", e2.getMessage());
                } else {
                    LogManager.m16371d(TAG, "Serialized Monitoring State has wrong class. Just ignoring saved state...", new Object[0]);
                }
                if (inputStream != null) {
                    inputStream.close();
                }
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
            } catch (Throwable th3) {
                th = th3;
                objectInputStream = objectInputStream2;
                if (inputStream != null) {
                    inputStream.close();
                }
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
                throw th;
            }
        } catch (IOException e12) {
            e = e12;
            e2 = e;
            if (e2 instanceof InvalidClassException) {
                LogManager.m16371d(TAG, "Serialized Monitoring State has wrong class. Just ignoring saved state...", new Object[0]);
            } else {
                LogManager.m16373e(TAG, "Deserialization exception, message: %s", e2.getMessage());
            }
            if (inputStream != null) {
                inputStream.close();
            }
            if (objectInputStream != null) {
                objectInputStream.close();
            }
        } catch (ClassNotFoundException e13) {
            e = e13;
            e2 = e;
            if (e2 instanceof InvalidClassException) {
                LogManager.m16373e(TAG, "Deserialization exception, message: %s", e2.getMessage());
            } else {
                LogManager.m16371d(TAG, "Serialized Monitoring State has wrong class. Just ignoring saved state...", new Object[0]);
            }
            if (inputStream != null) {
                inputStream.close();
            }
            if (objectInputStream != null) {
                objectInputStream.close();
            }
        } catch (ClassCastException e14) {
            e = e14;
            e2 = e;
            if (e2 instanceof InvalidClassException) {
                LogManager.m16371d(TAG, "Serialized Monitoring State has wrong class. Just ignoring saved state...", new Object[0]);
            } else {
                LogManager.m16373e(TAG, "Deserialization exception, message: %s", e2.getMessage());
            }
            if (inputStream != null) {
                inputStream.close();
            }
            if (objectInputStream != null) {
                objectInputStream.close();
            }
        }
    }

    public synchronized void stopStatusPreservation() {
        this.mContext.deleteFile(STATUS_PRESERVATION_FILE_NAME);
        this.mStatePreservationIsOn = false;
    }

    public synchronized void startStatusPreservation() {
        if (!this.mStatePreservationIsOn) {
            this.mStatePreservationIsOn = true;
            saveMonitoringStatusIfOn();
        }
    }

    public boolean isStatePreservationOn() {
        return this.mStatePreservationIsOn;
    }

    public synchronized void clear() {
        this.mContext.deleteFile(STATUS_PRESERVATION_FILE_NAME);
        getRegionsStateMap().clear();
    }

    public void updateLocalState(Region region, Integer state) {
        RegionMonitoringState internalState = (RegionMonitoringState) getRegionsStateMap().get(region);
        if (internalState == null) {
            internalState = addLocalRegion(region);
        }
        if (state != null) {
            if (state.intValue() == 0) {
                internalState.markOutside();
            }
            if (state.intValue() == 1) {
                internalState.markInside();
            }
        }
    }

    public void removeLocalRegion(Region region) {
        getRegionsStateMap().remove(region);
    }

    public RegionMonitoringState addLocalRegion(Region region) {
        return addLocalRegion(region, new Callback(null));
    }

    private RegionMonitoringState addLocalRegion(Region region, Callback callback) {
        if (getRegionsStateMap().containsKey(region)) {
            for (Region existingRegion : getRegionsStateMap().keySet()) {
                if (existingRegion.equals(region)) {
                    if (existingRegion.hasSameIdentifiers(region)) {
                        return (RegionMonitoringState) getRegionsStateMap().get(existingRegion);
                    }
                    LogManager.m16371d(TAG, "Replacing region with unique identifier " + region.getUniqueId(), new Object[0]);
                    LogManager.m16371d(TAG, "Old definition: " + existingRegion, new Object[0]);
                    LogManager.m16371d(TAG, "New definition: " + region, new Object[0]);
                    LogManager.m16371d(TAG, "clearing state", new Object[0]);
                    getRegionsStateMap().remove(region);
                }
            }
        }
        RegionMonitoringState monitoringState = new RegionMonitoringState(callback);
        getRegionsStateMap().put(region, monitoringState);
        return monitoringState;
    }
}
