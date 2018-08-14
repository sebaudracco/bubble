package com.stepleaderdigital.reveal;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences.Editor;
import android.os.Build.VERSION;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.RemoteException;
import com.bgjd.ici.p025b.C1408j.C1407c;
import com.fyber.ads.videos.RewardedVideoActivity;
import com.github.lzyzsd.jsbridge.BridgeUtil;
import com.stepleaderdigital.reveal.BeaconManager.BeaconLeScanCallback;
import com.stepleaderdigital.reveal.Reveal.BeaconScanningProperties;
import com.stepleaderdigital.reveal.Reveal.BeaconService;
import com.stepleaderdigital.reveal.Reveal.BluetoothItem;
import com.stepleaderdigital.reveal.Reveal.GlobalLocation;
import com.stepleaderdigital.reveal.Reveal.LocationService;
import com.stepleaderdigital.reveal.Reveal.LocationService.OnValidLocationCallback;
import com.stepleaderdigital.reveal.Reveal.OnBeaconFoundListener;
import com.stepleaderdigital.reveal.Reveal.PDU;
import com.stepleaderdigital.reveal.Reveal.PDUiBeacon;
import com.stepleaderdigital.reveal.Reveal.RevealBeacon;
import com.stepleaderdigital.reveal.Reveal.RevealLogger;
import com.stepleaderdigital.reveal.Reveal.UserLogListener;
import com.stepleaderdigital.reveal.Reveal.Utils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;

public class RevealBeaconService implements BeaconService, BeaconConsumer {
    public static final int PROXIMITY_FAR = 3;
    public static final int PROXIMITY_IMMEDIATE = 1;
    public static final int PROXIMITY_NEAR = 2;
    public static final int PROXIMITY_UNKNOWN = 0;
    public static final int TYPE_EDDYSTONE = 16;
    public static final int TYPE_EDDYSTONE2 = 0;
    public static final int TYPE_ESTIMOTE = 533;
    public static final String TYPE_NAME_EDDYSTONE = "Eddystone";
    public static final String TYPE_NAME_ESTIMOTE = "iBeacon";
    public static final String TYPE_NAME_GATT = "GATT";
    public static final String TYPE_NAME_GIMBAL = "Gimbal";
    public static final String TYPE_NAME_SECURECAST = "Securecast";
    private Context applicationContext;
    private BackgroundPowerSaver backgroundPowerSaver;
    private OnBeaconFoundListener beaconFoundListener = null;
    private BeaconManager beaconManager;
    private BeaconScanningProperties beaconScanningProperties;
    private ArrayList<RevealScanGroup> bluetoothDeviceScanGroups = new ArrayList();
    private CCObjectCache<String, RevealBeacon> cachedBeacons = new CCObjectCache();
    private Set<String> foundBeaconAddressSet = new HashSet();
    private Boolean isBackgroundScanningEnabled = Boolean.valueOf(true);
    private HashMap<String, RevealBeacon> partialEddystonePackets = new HashMap();
    private boolean setupComplete = false;

    public interface CCObjectCacheListener<K, T> {
        void onItemAccessed(CCObjectCacheEntry<K, T> cCObjectCacheEntry);

        void onItemAdded(CCObjectCacheEntry<K, T> cCObjectCacheEntry);

        void onItemRemoved(CCObjectCacheEntry<K, T> cCObjectCacheEntry);
    }

    class C40191 implements CCObjectCacheListener<String, RevealBeacon> {
        C40191() {
        }

        public void onItemAdded(CCObjectCacheEntry<String, RevealBeacon> cCObjectCacheEntry) {
        }

        public void onItemRemoved(CCObjectCacheEntry<String, RevealBeacon> entry) {
            if (RevealBeaconService.this.beaconFoundListener != null) {
                RevealBeaconService.this.beaconFoundListener.onBeaconLeave((String) entry.key);
            }
        }

        public void onItemAccessed(CCObjectCacheEntry<String, RevealBeacon> cCObjectCacheEntry) {
        }
    }

    class C40202 implements BeaconLeScanCallback {
        C40202() {
        }

        public void onBeaconLeScan(BluetoothDevice bluetoothDevice, int rssi, byte[] bytes) {
            if (VERSION.SDK_INT >= 18 && bluetoothDevice != null) {
                ArrayList<PDU> pdus;
                byte[] payload;
                UserLogListener listener = Reveal.getInstance().getLogger();
                String hex;
                if (bytes != null) {
                    hex = RevealBeacon.bytesToHex(bytes);
                    pdus = PDU.PDUList(bytes);
                } else {
                    hex = "No payload data";
                    pdus = new ArrayList();
                }
                BluetoothItem info = new BluetoothItem();
                info.setDevice(bluetoothDevice);
                info.setAddress(bluetoothDevice.getAddress());
                info.setPdus(pdus);
                info.setRssi(rssi);
                info.setUuids(new ArrayList());
                PDUiBeacon iBeacon = new PDUiBeacon(info);
                PDUiBeacon iBeaconData = iBeacon;
                String name = bluetoothDevice.getName();
                RevealBeacon beacon = null;
                Integer bits = Integer.valueOf(0);
                switch (iBeacon.getServiceUUID()) {
                    case 3902:
                        beacon = new RevealBeacon();
                        beacon.setBeaconType("TrackR");
                        payload = Arrays.copyOfRange(bytes, 13, bytes.length);
                        for (byte b : payload) {
                            bits = Integer.valueOf(bits.intValue() | b);
                        }
                        if (bits.intValue() != 0) {
                            beacon.setPayload(payload);
                        }
                        beacon.setComplete(true);
                        break;
                    case 65194:
                        ArrayList<Byte> serviceData = iBeacon.getServiceData();
                        beacon = new RevealBeacon();
                        beacon.setComplete(false);
                        Iterator it = info.getPDUTypes(new int[]{22}).iterator();
                        while (it.hasNext()) {
                            PDU item = (PDU) it.next();
                            int type = item.int8at(2);
                            beacon.setBeaconType(RevealBeaconService.TYPE_NAME_EDDYSTONE);
                            RevealBeacon otherEddystoneBeacon;
                            switch (type) {
                                case 0:
                                    otherEddystoneBeacon = (RevealBeacon) RevealBeaconService.this.partialEddystonePackets.get(beacon.getAddress());
                                    if (otherEddystoneBeacon != null) {
                                        beacon.setUrl(otherEddystoneBeacon.getUrl());
                                    } else {
                                        RevealBeaconService.this.partialEddystonePackets.put(beacon.getAddress(), beacon);
                                    }
                                    beacon.setProximityUUID(item.getHexAt(4, 10));
                                    beacon.setMajor("0x" + item.getHexAt(14, 6));
                                    beacon.setBeaconType(RevealBeaconService.TYPE_NAME_EDDYSTONE);
                                    String url = beacon.getUrl();
                                    if (url != null && url.length() > 0) {
                                        beacon.setComplete(true);
                                        RevealBeaconService.this.partialEddystonePackets.remove(beacon.getAddress());
                                        break;
                                    }
                                case 16:
                                    otherEddystoneBeacon = (RevealBeacon) RevealBeaconService.this.partialEddystonePackets.get(beacon.getAddress());
                                    if (otherEddystoneBeacon != null) {
                                        beacon.setProximityUUID(otherEddystoneBeacon.getProximityUUID());
                                        beacon.setMajor(otherEddystoneBeacon.getMajor());
                                    } else {
                                        RevealBeaconService.this.partialEddystonePackets.put(beacon.getAddress(), beacon);
                                    }
                                    beacon.setBeaconType(RevealBeaconService.TYPE_NAME_EDDYSTONE);
                                    beacon.setUrl(UrlBeaconUrlCompressor.uncompress(item.bytesAt(4)));
                                    if (beacon.getMajor() != null && beacon.getMajor().length() > 0) {
                                        beacon.setComplete(true);
                                        RevealBeaconService.this.partialEddystonePackets.remove(beacon.getAddress());
                                        break;
                                    }
                                default:
                                    break;
                            }
                        }
                        break;
                    case 65261:
                        beacon = new RevealBeacon();
                        beacon.setBeaconType("Tile");
                        payload = Arrays.copyOfRange(bytes, 13, bytes.length);
                        bits = Integer.valueOf(0);
                        for (byte b2 : payload) {
                            bits = Integer.valueOf(bits.intValue() | b2);
                        }
                        if (bits.intValue() != 0) {
                            beacon.setPayload(payload);
                        }
                        beacon.setComplete(true);
                        break;
                    default:
                        switch (iBeacon.getManufacturerCode()) {
                            case 76:
                                if (iBeacon.getServiceData() != null && iBeacon.isValid()) {
                                    RevealLogger.m12440v("PDUiBeacon found " + iBeacon.getUuid() + " - " + iBeacon.getMajor() + BridgeUtil.SPLIT_MARK + iBeacon.getMinor());
                                    beacon = new RevealBeacon(iBeacon);
                                    beacon.setBeaconType(iBeacon.getManufacturer());
                                    if (iBeacon.getUuid() != null) {
                                        beacon.setIdentifier(iBeacon.getUuid() + "-" + iBeacon.getMajor() + "-" + iBeacon.getMinor());
                                        break;
                                    }
                                }
                                break;
                            default:
                                if (!iBeacon.isValid()) {
                                    iBeacon = null;
                                    break;
                                }
                                RevealLogger.m12440v("PDUiBeacon found " + iBeacon.getUuid() + " - " + iBeacon.getMajor() + BridgeUtil.SPLIT_MARK + iBeacon.getMinor());
                                beacon = new RevealBeacon(iBeacon);
                                beacon.setBeaconType(iBeacon.getManufacturer());
                                if (iBeacon.getUuid() != null) {
                                    beacon.setIdentifier(iBeacon.getUuid());
                                    break;
                                }
                                break;
                        }
                }
                if (beacon != null) {
                    beacon.setName(name);
                    beacon.setAddress(bluetoothDevice.getAddress());
                    beacon.setRssi(rssi);
                    if (beacon.getBeaconTypeCode() == 0) {
                        beacon.setBeaconTypeCode(iBeacon.getManufacturerCode());
                    }
                    if (beacon.getIdentifier() == null) {
                        if (name != null) {
                            beacon.setIdentifier(name + "-" + bluetoothDevice.getAddress());
                        } else {
                            beacon.setIdentifier(bluetoothDevice.getAddress());
                        }
                    }
                    beacon.setComplete(true);
                    int power = beacon.getTxPower();
                    if (power == 0) {
                        power = -47;
                    }
                    if (Reveal.getInstance().getDistanceCalculator() != null) {
                        beacon.setDistance(Reveal.getInstance().getDistanceCalculator().calculateDistance(power, (double) rssi));
                    } else {
                        RevealLogger.m12430d("Distance calculator not set.  Distance will bet set to -1");
                        beacon.setDistance(-1.0d);
                    }
                    beacon.setAccuracy(RevealBeacon.calculateAccuracy(power, (double) rssi));
                    if (beacon.getAccuracy() < 0.0d) {
                        beacon.setProximity(0);
                    } else if (beacon.getAccuracy() < 2.0d) {
                        beacon.setProximity(1);
                    } else if (beacon.getAccuracy() < 15.0d) {
                        beacon.setProximity(2);
                    } else {
                        beacon.setProximity(3);
                    }
                    RevealBeaconService.this.processBeacon(beacon);
                }
                if ((bluetoothDevice.getType() == 2 || bluetoothDevice.getType() == 3) && hex.startsWith("02010609030318")) {
                    beacon = new RevealBeacon();
                    payload = Arrays.copyOfRange(bytes, 13, bytes.length);
                    bits = Integer.valueOf(0);
                    for (byte b22 : payload) {
                        bits = Integer.valueOf(bits.intValue() | b22);
                    }
                    if (bits.intValue() != 0) {
                        beacon.setPayload(payload);
                    }
                    beacon.setName(name);
                    beacon.setBeaconType("PebbleBee");
                    beacon.setAddress(bluetoothDevice.getAddress());
                    beacon.setRssi(rssi);
                    if (name != null) {
                        beacon.setIdentifier(name + "-" + bluetoothDevice.getAddress());
                    } else {
                        beacon.setIdentifier(bluetoothDevice.getAddress());
                    }
                    beacon.setComplete(true);
                    RevealBeaconService.this.processBeacon(beacon);
                }
                if (listener != null) {
                    listener.logDevice(info, iBeaconData);
                }
            }
        }
    }

    public class CCObjectCache<K, T> {
        private double cacheTime = 3600.0d;
        protected ConcurrentHashMap<K, CCObjectCacheEntry<K, T>> items = new ConcurrentHashMap();
        private CCObjectCacheListener<K, T> objectCacheListener = null;
        private Boolean resetOnEveryAccess = Boolean.valueOf(false);
        private Boolean resetOnEveryAdd = Boolean.valueOf(false);

        public double getCacheTime() {
            return this.cacheTime;
        }

        public void setCacheTime(double cacheTime) {
            this.cacheTime = cacheTime;
        }

        public Boolean getResetOnEveryAdd() {
            return this.resetOnEveryAdd;
        }

        public void setResetOnEveryAdd(Boolean resetOnEveryAdd) {
            this.resetOnEveryAdd = resetOnEveryAdd;
        }

        public Boolean getResetOnEveryAccess() {
            return this.resetOnEveryAccess;
        }

        public void setResetOnEveryAccess(Boolean resetOnEveryAccess) {
            this.resetOnEveryAccess = resetOnEveryAccess;
        }

        public T get(K key) {
            purgeOld();
            T result = null;
            CCObjectCacheEntry<K, T> entry = (CCObjectCacheEntry) this.items.get(key);
            Date now = new Date();
            if (entry != null) {
                if (this.resetOnEveryAccess.booleanValue()) {
                    Calendar cal = GregorianCalendar.getInstance();
                    cal.setTime(now);
                    cal.add(13, (int) this.cacheTime);
                    entry.setDateOut(cal.getTime());
                }
                result = entry.getObject();
                if (this.objectCacheListener != null) {
                    this.objectCacheListener.onItemAccessed(entry);
                }
            }
            return result;
        }

        public Boolean put(T obj, K key) {
            purgeOld();
            Boolean result = Boolean.valueOf(false);
            CCObjectCacheEntry<K, T> entry = (CCObjectCacheEntry) this.items.get(key);
            Date now = new Date();
            if (entry != null) {
                RevealLogger.m12440v("Cache already contains " + key.toString());
                if (!this.resetOnEveryAdd.booleanValue()) {
                    return result;
                }
                Calendar cal = GregorianCalendar.getInstance();
                cal.setTime(now);
                cal.add(13, (int) this.cacheTime);
                entry.setDateOut(cal.getTime());
                return result;
            }
            entry = new CCObjectCacheEntry();
            entry.setKey(key);
            entry.setObject(obj);
            entry.setDateIn(now);
            cal = GregorianCalendar.getInstance();
            cal.setTime(now);
            cal.add(13, (int) this.cacheTime);
            entry.setDateOut(cal.getTime());
            this.items.put(key, entry);
            RevealLogger.m12440v("Cache aadded " + key.toString());
            if (this.objectCacheListener != null) {
                this.objectCacheListener.onItemAdded(entry);
            }
            return Boolean.valueOf(true);
        }

        public void purgeOld() {
            Date now = new Date();
            synchronized (this.items) {
                for (CCObjectCacheEntry<K, T> item : allValues()) {
                    if (item.getDateOut().compareTo(now) < 0) {
                        if (this.objectCacheListener != null) {
                            this.objectCacheListener.onItemAdded(item);
                        }
                        this.items.remove(item.getKey());
                    }
                }
            }
        }

        public List<CCObjectCacheEntry<K, T>> allValues() {
            ArrayList<CCObjectCacheEntry<K, T>> result = new ArrayList();
            synchronized (this.items) {
                for (CCObjectCacheEntry<K, T> obj : this.items.values()) {
                    result.add(obj);
                }
            }
            return result;
        }

        public List<T> values() {
            ArrayList<T> result = new ArrayList();
            synchronized (this.items) {
                for (CCObjectCacheEntry<K, T> obj : this.items.values()) {
                    result.add(obj.getObject());
                }
            }
            return result;
        }

        public CCObjectCacheListener<K, T> getObjectCacheListener() {
            return this.objectCacheListener;
        }

        public void setObjectCacheListener(CCObjectCacheListener<K, T> objectCacheListener) {
            this.objectCacheListener = objectCacheListener;
        }
    }

    public class CCObjectCacheEntry<K, T> {
        private Date dateIn;
        private Date dateOut;
        private K key;
        private T object;

        public K getKey() {
            return this.key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public T getObject() {
            return this.object;
        }

        public void setObject(T object) {
            this.object = object;
        }

        public Date getDateIn() {
            return this.dateIn;
        }

        public void setDateIn(Date dateIn) {
            this.dateIn = dateIn;
        }

        public Date getDateOut() {
            return this.dateOut;
        }

        public void setDateOut(Date dateOut) {
            this.dateOut = dateOut;
        }
    }

    public enum CacheState {
        New,
        Closer,
        Existing
    }

    public class RevealScanGroup {
        private String configurationString;
        private int endVendor;
        private String name;
        private String serviceId;
        private int startVendor;
        private String type;
        private int vendorIdEnd = 1;
        private int vendorIdStart = 0;

        public RevealScanGroup(String type, String name, int vendor, String configString) {
            this.type = type;
            this.name = name;
            this.startVendor = vendor;
            this.endVendor = vendor;
            this.configurationString = configString;
        }

        public ArrayList<String> getBeaconStrings() {
            ArrayList<String> result = new ArrayList();
            for (int vendor = this.startVendor; vendor <= this.endVendor; vendor++) {
                StringBuilder builder = new StringBuilder();
                String vendorString = Long.toHexString((long) vendor);
                int digits = ((this.vendorIdEnd - this.vendorIdStart) + 1) * 2;
                while (vendorString.length() < digits) {
                    vendorString = SchemaSymbols.ATTVAL_FALSE_0 + vendorString;
                }
                if (this.serviceId != null) {
                    builder.append("s:0-1=").append(this.serviceId).append(",");
                }
                builder.append("m:").append(this.vendorIdStart).append("-").append(this.vendorIdEnd).append("=").append(vendorString).append(",").append(this.configurationString);
                result.add(builder.toString());
            }
            return result;
        }

        public String getType() {
            return this.type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getName() {
            return this.name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getServiceId() {
            return this.serviceId;
        }

        public void setServiceId(String serviceId) {
            this.serviceId = serviceId;
        }

        public int getStartVendor() {
            return this.startVendor;
        }

        public void setStartVendor(int startVendor) {
            this.startVendor = startVendor;
        }

        public int getEndVendor() {
            return this.endVendor;
        }

        public void setEndVendor(int endVendor) {
            this.endVendor = endVendor;
        }

        public int getVendorIdStart() {
            return this.vendorIdStart;
        }

        public void setVendorIdStart(int vendorIdStart) {
            this.vendorIdStart = vendorIdStart;
        }

        public int getVendorIdEnd() {
            return this.vendorIdEnd;
        }

        public void setVendorIdEnd(int vendorIdEnd) {
            this.vendorIdEnd = vendorIdEnd;
        }

        public String getConfigurationString() {
            return this.configurationString;
        }

        public void setConfigurationString(String configurationString) {
            this.configurationString = configurationString;
        }
    }

    public RevealBeaconService(Context applicationContext) {
        this.applicationContext = applicationContext;
        setupIfNeeded();
    }

    public void setupIfNeeded() {
        if (!this.setupComplete) {
            setup();
        }
    }

    private void setup() {
        boolean fineAccess = Reveal.selfPermissionGranted(this.applicationContext, "android.permission.ACCESS_FINE_LOCATION");
        boolean courseAccess = Reveal.selfPermissionGranted(this.applicationContext, "android.permission.ACCESS_COARSE_LOCATION");
        Reveal.log("RevealBeaconService fine=" + fineAccess + ", course=" + courseAccess, "STATE");
        if (fineAccess || courseAccess) {
            if (Utils.isBluetoothEnabled(this.applicationContext)) {
                Reveal.getInstance().setStatus(Reveal.STATUS_BLUETOOTH, 1, "Bluetooth permission granted");
            } else {
                Reveal.getInstance().setStatus(Reveal.STATUS_BLUETOOTH, 0, "Bluetooth disabled");
            }
            this.beaconManager = BeaconManager.getInstanceForApplication(this.applicationContext);
            this.beaconManager.bind(this);
            if (!this.beaconManager.isAnyConsumerBound()) {
                deleteScanGroups(TYPE_NAME_SECURECAST);
            }
            loadFoundBeacons();
            this.setupComplete = true;
            this.cachedBeacons.setObjectCacheListener(new C40191());
            return;
        }
        Reveal.log("Beacon scanning disabled due to location permissions", "WARNING", "STATE");
        Reveal.getInstance().setStatus(Reveal.STATUS_BLUETOOTH, 1, "Bluetooth disabled due to permissions");
    }

    public void loadBeaconParsers(List<Integer> list) {
        RevealLogger.m12430d("Scanning for beacons:\n" + getBeaconStringList());
        BeaconManager manager = this.beaconManager;
        if (manager != null) {
            RevealLogger.m12440v("Non-beacon hook: " + manager.getBeaconCallback());
            manager.setBeaconCallback(new C40202());
            return;
        }
        Reveal.log("Beacon scanning disabled due to location permissions", RewardedVideoActivity.REQUEST_STATUS_PARAMETER_ERROR, "STATE");
    }

    public void startBeaconScanning(Context context, BeaconScanningProperties properties) {
        boolean fineAccess = Reveal.selfPermissionGranted(context, "android.permission.ACCESS_FINE_LOCATION");
        boolean courseAccess = Reveal.selfPermissionGranted(context, "android.permission.ACCESS_COARSE_LOCATION");
        Reveal.log("startBeaconScanning fine=" + fineAccess + ", course=" + courseAccess, "STATE");
        if (fineAccess || courseAccess) {
            if (Utils.isBluetoothEnabled(context)) {
                Reveal.getInstance().setStatus(Reveal.STATUS_BLUETOOTH, 1, "Bluetooth permission granted");
            } else {
                Reveal.getInstance().setStatus(Reveal.STATUS_BLUETOOTH, 0, "Bluetooth disabled");
            }
            clearBeaconsFound(context);
            this.beaconScanningProperties = properties;
            this.cachedBeacons.cacheTime = properties.getCacheTTL();
            BeaconManager manager = this.beaconManager;
            loadBeaconParsers(this.beaconScanningProperties.getAdditionalSecureCastManufacturerCodes());
            if (this.beaconManager != null) {
                manager.setForegroundScanPeriod((long) (properties.getScanDuration().intValue() * 1001));
                if (properties.getBackgroundEnabled().booleanValue()) {
                    manager.setBackgroundScanPeriod((long) (properties.getScanDuration().intValue() * 1001));
                } else {
                    manager.setBackgroundScanPeriod(0);
                }
                manager.setForegroundBetweenScanPeriod((long) (properties.getScanInterval().intValue() * 1001));
                if (properties.getBackgroundEnabled().booleanValue()) {
                    manager.setForegroundBetweenScanPeriod((long) (properties.getScanInterval().intValue() * 1001));
                } else {
                    manager.setForegroundBetweenScanPeriod(0);
                }
                try {
                    this.beaconManager.startScanning();
                    return;
                } catch (RemoteException e) {
                    e.printStackTrace();
                    Reveal.getInstance().setStatus(Reveal.STATUS_BLUETOOTH, 0, e.toString());
                    return;
                }
            }
            Reveal.log("beaconManager not set in startBeaconScanning", "WARNING", "STATE");
            Reveal.getInstance().setStatus(Reveal.STATUS_BLUETOOTH, 0, "beaconManager not set in startBeaconScanning");
            return;
        }
        Reveal.log("No location provided for beacon scanner", "WARNING", "STATE");
        Reveal.getInstance().setStatus(Reveal.STATUS_BLUETOOTH, 0, "No location provided for beacon scanner");
    }

    public void stopBeaconScanning(Context context) {
        handleTimedOutEddyStonePartials();
        if (this.isBackgroundScanningEnabled.booleanValue() && this.beaconManager != null) {
            try {
                this.beaconManager.stopScanning();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        synchronized (this) {
            this.foundBeaconAddressSet = new HashSet();
        }
        storeFoundBeacons();
        if (this.beaconManager != null) {
            this.beaconManager.setBeaconCallback(null);
        }
    }

    public List<RevealBeacon> getBeacons() {
        return this.cachedBeacons.values();
    }

    private RevealBeacon processBeacon(RevealBeacon revealBeacon) {
        DetectionTracker.getInstance().recordDetection();
        Boolean complete = Boolean.valueOf(revealBeacon.isComplete());
        final RevealBeacon result = revealBeacon;
        Reveal.getInstance().setStatus(Reveal.STATUS_BLUETOOTH, 1, "Beacons found");
        if (complete.booleanValue()) {
            RevealLogger.m12440v("Have complete beacon: " + revealBeacon.toString());
            switch (updateCacheWithBeacon(revealBeacon)) {
                case New:
                case Closer:
                    if (revealBeacon.getDistance() >= 2.0d) {
                        new Handler(this.applicationContext.getMainLooper()).post(new Runnable() {
                            public void run() {
                                RevealLogger.m12440v("processBeacon, we are waiting to get closer to " + result.getIdentifier() + " @ " + result.getDistance() + " meters");
                                CountDownTimer timer = result.getSendTimer();
                                if (timer != null) {
                                    timer.cancel();
                                    result.setSendTimer(null);
                                }
                                result.setSendTimer(new CountDownTimer(30000, 1000) {
                                    public void onTick(long millisUntilFinished) {
                                    }

                                    public void onFinish() {
                                        RevealLogger.m12440v("processBeacon, we have waited long enough " + result.getIdentifier() + " @ " + result.getDistance() + " meters");
                                        RevealBeaconService.this.processCompleteBeacon(result);
                                    }
                                }.start());
                            }
                        });
                        break;
                    }
                    RevealLogger.m12440v("processBeacon, we are within a couple of meters so sending " + revealBeacon.getIdentifier() + " @ " + revealBeacon.getDistance() + " meters");
                    processCompleteBeacon(revealBeacon);
                    break;
                case Existing:
                    RevealLogger.m12440v("Beacon already sent");
                    break;
            }
        }
        RevealLogger.m12440v("Not sending incomplete beacon: " + revealBeacon.toString());
        return result;
    }

    private CacheState updateCacheWithBeacon(RevealBeacon revealBeacon) {
        CacheState result = CacheState.New;
        RevealBeacon old = (RevealBeacon) this.cachedBeacons.get(revealBeacon.getIdentifier());
        if (old == null) {
            Reveal.log("New beacon encountered: " + revealBeacon.getBeaconType() + " " + revealBeacon.getIdentifier(), "STATE");
            processCompleteBeacon(revealBeacon);
            return result;
        } else if (revealBeacon.getDistance() >= old.getDistance()) {
            return CacheState.Existing;
        } else {
            result = CacheState.Closer;
            old.setDistance(revealBeacon.getDistance());
            old.setProximity(revealBeacon.getProximity());
            this.cachedBeacons.put(revealBeacon, revealBeacon.getIdentifier());
            Reveal.log("Moved closer to beacon: " + revealBeacon.getBeaconType() + " " + revealBeacon.getIdentifier(), "STATE");
            return result;
        }
    }

    private RevealBeacon processCompleteBeacon(RevealBeacon originalBeacon) {
        final RevealBeacon revealBeacon = originalBeacon;
        if (this.cachedBeacons.put(revealBeacon, revealBeacon.getIdentifier()).booleanValue()) {
            storeFoundBeacons();
            synchronized (this) {
                this.foundBeaconAddressSet.add(revealBeacon.getAddress());
            }
            final LocationService locationService = Reveal.getInstance().getLocationService();
            if (locationService != null) {
                RevealLogger.m12440v("Waiting for a location to be retrieved for " + revealBeacon);
                locationService.waitForValidLocation(new OnValidLocationCallback() {
                    public void onLocationFound() {
                        revealBeacon.setLocation(new GlobalLocation(locationService.getCurrentLocation(RevealBeaconService.this.getApplicationContext())));
                        RevealLogger.m12440v("onLocationFound retrieved for " + revealBeacon);
                        if (revealBeacon.getLocation() == null) {
                            revealBeacon.setLocation(new GlobalLocation(Utils.getLastKnownLocation(RevealBeaconService.this.applicationContext)));
                            RevealLogger.m12440v("Using last known location location since because we never received one");
                        } else {
                            RevealLogger.m12440v("Location received");
                        }
                        Reveal.getInstance().sendDiscoveryOfBeacon(RevealBeaconService.this.applicationContext, revealBeacon);
                        if (RevealBeaconService.this.beaconFoundListener != null) {
                            Map<String, Object> properties = revealBeacon.getProperties();
                            properties.put(C1407c.f2164d, revealBeacon);
                            RevealBeaconService.this.beaconFoundListener.onBeaconDiscovered(properties);
                        }
                    }
                });
            } else {
                RevealLogger.m12440v("No location service provided sending Beacon without location");
                Reveal.getInstance().sendDiscoveryOfBeacon(this.applicationContext, revealBeacon);
            }
        } else {
            RevealLogger.m12440v("processCompleteBeacon Beacon already sent: " + revealBeacon);
        }
        return revealBeacon;
    }

    private void handleTimedOutEddyStonePartials() {
        ArrayList<String> removed = new ArrayList();
        for (Entry entry : this.partialEddystonePackets.entrySet()) {
            RevealBeacon beacon = (RevealBeacon) entry.getValue();
            if (!(beacon == null || this.beaconScanningProperties == null)) {
                RevealLogger.m12440v("testing " + beacon.getIdentifier() + " AGE: " + beacon.age() + " complete: " + beacon.isComplete() + " @ " + beacon.getDiscoveryTime());
                if (!beacon.isComplete() && beacon.age() > ((long) this.beaconScanningProperties.getEddystoneTimeout().intValue())) {
                    beacon.setComplete(true);
                    processBeacon(beacon);
                    removed.add(beacon.getAddress());
                }
            }
        }
        Iterator it = removed.iterator();
        while (it.hasNext()) {
            this.partialEddystonePackets.remove((String) it.next());
        }
    }

    public void onBeaconServiceConnect() {
        Reveal.log("Beacon service connected", "STATE");
    }

    public boolean bindService(Intent intent, ServiceConnection connection, int mode) {
        return this.applicationContext.bindService(intent, connection, mode);
    }

    public void unbindService(ServiceConnection connection) {
        this.applicationContext.unbindService(connection);
    }

    public Context getApplicationContext() {
        return this.applicationContext;
    }

    private void storeFoundBeacons() {
        Editor editor = this.applicationContext.getSharedPreferences(Reveal.FOUND_BEACONS_PREFERENCE_NAME, 0).edit();
        Set<String> beacons = new HashSet();
        synchronized (this) {
            beacons.addAll(this.foundBeaconAddressSet);
        }
        String value = "";
        for (String item : beacons) {
            if (value.length() > 0) {
                value = value + ",";
            }
            value = value + item;
        }
        editor.putString(Reveal.FOUND_BEACONS_PREFERENCE_KEY, value);
        editor.apply();
    }

    private void loadFoundBeacons() {
        List<String> list = Arrays.asList(this.applicationContext.getSharedPreferences(Reveal.FOUND_BEACONS_PREFERENCE_NAME, 0).getString(Reveal.FOUND_BEACONS_PREFERENCE_KEY, "").split(","));
        synchronized (this) {
            this.foundBeaconAddressSet = new HashSet(list);
        }
    }

    private void clearBeaconsFound(Context context) {
        Editor editor = context.getSharedPreferences(Reveal.FOUND_BEACONS_PREFERENCE_NAME, 0).edit();
        editor.putString(Reveal.FOUND_BEACONS_PREFERENCE_KEY, "");
        editor.apply();
    }

    private void debugReadBeacons() {
        for (Entry pair : this.applicationContext.getSharedPreferences(Reveal.FOUND_BEACONS_PREFERENCE_NAME, 0).getAll().entrySet()) {
            RevealLogger.m12440v("   MAP ENTRY: " + pair.getKey() + " = " + pair.getValue());
        }
    }

    public void addScanGroup(RevealScanGroup group) {
        this.bluetoothDeviceScanGroups.add(group);
    }

    public List<String> getBeaconStringList() {
        ArrayList<String> result = new ArrayList();
        Iterator it = this.bluetoothDeviceScanGroups.iterator();
        while (it.hasNext()) {
            Iterator it2 = ((RevealScanGroup) it.next()).getBeaconStrings().iterator();
            while (it2.hasNext()) {
                result.add((String) it2.next());
            }
        }
        return result;
    }

    public void deleteScanGroups(String type) {
        if (type != null) {
            ArrayList<RevealScanGroup> originals = new ArrayList();
            originals.addAll(this.bluetoothDeviceScanGroups);
            Iterator it = originals.iterator();
            while (it.hasNext()) {
                RevealScanGroup group = (RevealScanGroup) it.next();
                if (type.equals(group.type)) {
                    this.bluetoothDeviceScanGroups.remove(group);
                }
            }
            return;
        }
        this.bluetoothDeviceScanGroups = new ArrayList();
    }

    public RevealScanGroup getBeaconGroup(int vendor, String serviceId) {
        Iterator it = this.bluetoothDeviceScanGroups.iterator();
        while (it.hasNext()) {
            RevealScanGroup group = (RevealScanGroup) it.next();
            if (serviceId == null || group.getServiceId() == null) {
                if (vendor >= group.getStartVendor() && vendor <= group.getEndVendor() && serviceId == null && group.getServiceId() == null) {
                    return group;
                }
            } else if (serviceId.equals(group.getServiceId())) {
                return group;
            }
        }
        return null;
    }

    public void setBeaconScanningProperties(BeaconScanningProperties beaconScanningProperties) {
        this.beaconScanningProperties = beaconScanningProperties;
    }

    public OnBeaconFoundListener getBeaconFoundListener() {
        return this.beaconFoundListener;
    }

    public void setBeaconFoundListener(OnBeaconFoundListener beaconFoundListener) {
        this.beaconFoundListener = beaconFoundListener;
    }
}
