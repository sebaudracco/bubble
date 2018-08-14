package com.stepleaderdigital.reveal;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.os.PowerManager;
import android.os.Process;
import android.support.v4.content.ContextCompat;
import android.support.v4.internal.view.SupportMenu;
import android.support.v4.view.MotionEventCompat;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import com.appnext.base.p019a.p022c.C1028c;
import com.appnext.base.p023b.C1048i;
import com.bgjd.ici.p025b.C1408j.C1403a;
import com.fyber.ads.videos.RewardedVideoActivity;
import com.github.lzyzsd.jsbridge.BridgeUtil;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient$Info;
import com.loopj.android.http.RequestParams;
import com.mopub.common.Constants;
import com.mopub.mobileads.dfp.adapters.MoPubAdapter;
import com.stripe.android.model.Card;
import cz.msebera.android.httpclient.client.methods.HttpPost;
import cz.msebera.android.httpclient.protocol.HTTP;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Reveal implements ActivityLifecycleCallbacks {
    public static final String BLUETOOTH_SCAN_DURATION_KEY = "BLUETOOTH_SCAN_DURATION_KEY";
    public static final String BLUETOOTH_SCAN_INTERVAL_KEY = "BLUETOOTH_SCAN_INTERVAL_KEY";
    public static final String CACHE_PREFS = "cachePrefs";
    public static final long CHECK_DELAY = 2000;
    public static final String DWELL_PREFS = "dwellManager";
    public static final String FOUND_BEACONS_PREFERENCE_KEY = "FOUND_BEACONS_KEY";
    public static final String FOUND_BEACONS_PREFERENCE_NAME = "Reveal_Beacon_Preferences";
    private static final int MAX_PERMISSION_POLL_CHECK = 30000;
    public static final String PENDING_BEACONS_PREFERENCE_KEY = "PENDING_BEACONS_KEY";
    public static final String PERSONAS_PREFERENCE_KEY = "personas";
    public static final String PERSONAS_PREFERENCE_NAME = "Reveal_Personas";
    private static final String REVEAL_API_BASE_PRODUCTION = "https://sdk.revealmobile.com";
    private static final String REVEAL_API_BASE_SANDBOX = "http://sandboxsdk.revealmobile.com";
    public static final String STATUS_BLUETOOTH = "bluetooth";
    public static final int STATUS_FAIL = 0;
    public static final int STATUS_IN_PROGRESS = 2;
    public static final String STATUS_LOCATION = "location";
    public static final String STATUS_MEMORY = "memory";
    public static final String STATUS_NETWORK = "network";
    public static final String STATUS_SCAN = "scan";
    public static final int STATUS_SUCCEED = 1;
    private static Reveal sharedInstance = null;
    private String apiBaseURL = REVEAL_API_BASE_PRODUCTION;
    private String apiKey = null;
    private Context applicationContext = null;
    private boolean batchBackgroundSend = true;
    private BeaconService beaconService;
    private Runnable check;
    private Activity current;
    private List<String> debugUUIDs = new ArrayList();
    private DistanceCalculator defaultDistanceCalculator = null;
    private DistanceCalculator distanceCalculator = null;
    private DozeModeReceiver dozeReciever = new DozeModeReceiver();
    private RevealDwellManager dwellManager = new RevealDwellManager();
    private RevealEventCache eventCache = new RevealEventCache();
    private ConcurrentHashMap<String, Integer> failureStats = new ConcurrentHashMap();
    private boolean foreground;
    private Handler handler = new Handler();
    private long incompleteBeaconSendTime = 3600;
    private Boolean isBackgroundScanningEnabled = Boolean.valueOf(true);
    private Boolean isBeaconScanningEnabled = Boolean.valueOf(true);
    private Boolean isDebug = Boolean.valueOf(false);
    private OnLocationFoundListener locationFoundListener = null;
    private LocationService locationService;
    private Boolean locationSharingEnabled = Boolean.valueOf(false);
    private UserLogListener logger;
    private Timer memoryCheckTimer = null;
    private TimerTask memoryCheckTimerTask = null;
    private boolean needRegisteration = true;
    private HashMap<String, Boolean> permissionStates = new HashMap();
    private List<String> personas = new ArrayList();
    private long retryDelayAfterTimeout = 60;
    private int simulateMemoryWarning = 0;
    private Date startTime = new Date();
    private HashMap<String, RevealStatus> statuses = new HashMap();
    private ConcurrentHashMap<String, Integer> successStats = new ConcurrentHashMap();

    public interface RevealDwellEventListener {
        void onEventReady(RevealEvent revealEvent);
    }

    class C39981 implements RevealDwellEventListener {
        C39981() {
        }

        public void onEventReady(RevealEvent event) {
            Reveal.getInstance().addEvent(Reveal.getInstance().applicationContext, event);
        }
    }

    public interface RevealEventListener {
        void onCacheReady(ArrayList<RevealEvent> arrayList);
    }

    class C39992 implements RevealEventListener {
        C39992() {
        }

        public void onCacheReady(ArrayList<RevealEvent> events) {
            Reveal.this.sendBatch(events);
        }
    }

    public interface RevealNetworkClientCallback {
        void onFailure(String str);

        void onSuccess(JSONObject jSONObject);
    }

    public static class AdUtils {
        private static final String KEY_ID = "key_adid";
        public static final String PREFS_NAME = "adid";

        public static void setupAdvertisingId(Context context) {
            getAdvertisingId(context);
        }

        public static String getAdvertisingId(final Context context) {
            if (context == null) {
                return "";
            }
            new Thread(new Runnable() {
                public void run() {
                    AdvertisingIdClient$Info adInfo = null;
                    try {
                        adInfo = AdvertisingIdClient.getAdvertisingIdInfo(context);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (adInfo != null) {
                        String adid = "";
                        if (!adInfo.isLimitAdTrackingEnabled()) {
                            adid = adInfo.getId();
                        }
                        Editor editor = context.getSharedPreferences(AdUtils.PREFS_NAME, 0).edit();
                        editor.putString(AdUtils.KEY_ID, adid);
                        editor.apply();
                    }
                }
            }).start();
            return context.getSharedPreferences(PREFS_NAME, 0).getString(KEY_ID, Card.UNKNOWN);
        }
    }

    public static class BeaconScanningProperties {
        private List<Integer> additionalSecureCastManufacturerCodes = new ArrayList();
        private double cacheTTL = 3600.0d;
        private List<String> debugUUIDs = new ArrayList();
        private Integer eddystoneTimeout = Integer.valueOf(220);
        private Boolean isBackgroundScanningEnabled = Boolean.valueOf(true);
        private double locationTimeOut = 30.0d;
        private Integer scanDuration = Integer.valueOf(30);
        private Integer scanInterval = Integer.valueOf(180);

        public Integer getEddystoneTimeout() {
            return this.eddystoneTimeout;
        }

        public void setEddystoneTimeout(Integer eddystoneTimeout) {
            this.eddystoneTimeout = eddystoneTimeout;
        }

        public Integer getScanInterval() {
            return this.scanInterval;
        }

        public void setScanInterval(Integer scanInterval) {
            this.scanInterval = scanInterval;
        }

        public Integer getScanDuration() {
            return this.scanDuration;
        }

        public void setScanDuration(Integer scanDuration) {
            this.scanDuration = scanDuration;
        }

        public List<Integer> getAdditionalSecureCastManufacturerCodes() {
            return this.additionalSecureCastManufacturerCodes;
        }

        public void setAdditionalSecureCastManufacturerCodes(List<Integer> additionalSecureCastManufacturerCodes) {
            this.additionalSecureCastManufacturerCodes = additionalSecureCastManufacturerCodes;
        }

        public Boolean getBackgroundEnabled() {
            return this.isBackgroundScanningEnabled;
        }

        public void setBackgroundEnabled(Boolean backgroundEnabled) {
            this.isBackgroundScanningEnabled = backgroundEnabled;
        }

        public List<String> getDebugUUIDs() {
            return this.debugUUIDs;
        }

        public void setDebugUUIDs(List<String> debugUUIDs) {
            this.debugUUIDs = debugUUIDs;
        }

        public double getCacheTTL() {
            return this.cacheTTL;
        }

        public void setCacheTTL(double cacheTTL) {
            this.cacheTTL = cacheTTL;
        }

        public double getLocationTimeOut() {
            return this.locationTimeOut;
        }

        public void setLocationTimeOut(double locationTimeOut) {
            this.locationTimeOut = locationTimeOut;
        }
    }

    public interface BeaconService {
        List<RevealBeacon> getBeacons();

        void setBeaconFoundListener(OnBeaconFoundListener onBeaconFoundListener);

        void setupIfNeeded();

        void startBeaconScanning(Context context, BeaconScanningProperties beaconScanningProperties);

        void stopBeaconScanning(Context context);
    }

    public static class BluetoothItem {
        private String address;
        private Map<String, Object> beacon;
        private String beaconType;
        private BluetoothDevice device;
        private String hex;
        private String name;
        private ArrayList<PDU> pdus;
        private int rssi;
        private HashMap<String, Object> userData = new HashMap();
        private ArrayList<String> uuids;

        public boolean isBeacon() {
            if (this.beacon == null || this.beacon.size() <= 0) {
                return false;
            }
            return true;
        }

        public ArrayList<PDU> getPDUTypes(int[] types) {
            ArrayList<PDU> result = new ArrayList();
            Iterator it = this.pdus.iterator();
            while (it.hasNext()) {
                PDU pdu = (PDU) it.next();
                for (int type : types) {
                    if (type == pdu.type) {
                        result.add(pdu);
                    }
                }
            }
            return result;
        }

        public PDU getPDUType(int type) {
            ArrayList<PDU> matches = getPDUTypes(new int[]{type});
            if (matches.size() > 0) {
                return (PDU) matches.get(0);
            }
            return null;
        }

        public String toString() {
            String result = this.address;
            if (this.beaconType != null) {
                result = result + this.beaconType;
            }
            if (this.pdus != null) {
                Iterator it = this.pdus.iterator();
                while (it.hasNext()) {
                    result = result + "\n   " + ((PDU) it.next());
                }
            }
            return result;
        }

        public String getAddress() {
            return this.address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getName() {
            return this.name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getHex() {
            return this.hex;
        }

        public void setHex(String hex) {
            this.hex = hex;
        }

        public int getRssi() {
            return this.rssi;
        }

        public void setRssi(int rssi) {
            this.rssi = rssi;
        }

        public Map<String, Object> getBeacon() {
            return this.beacon;
        }

        public void setBeacon(Map<String, Object> beacon) {
            this.beacon = beacon;
        }

        public String getBeaconType() {
            return this.beaconType;
        }

        public void setBeaconType(String beaconType) {
            this.beaconType = beaconType;
        }

        public ArrayList<String> getUuids() {
            return this.uuids;
        }

        public void setUuids(ArrayList<String> uuids) {
            this.uuids = uuids;
        }

        public ArrayList<PDU> getPdus() {
            return this.pdus;
        }

        public void setPdus(ArrayList<PDU> pdus) {
            this.pdus = pdus;
        }

        public BluetoothDevice getDevice() {
            return this.device;
        }

        public void setDevice(BluetoothDevice device) {
            this.device = device;
        }

        public HashMap<String, Object> getUserData() {
            return this.userData;
        }

        public void setUserData(HashMap<String, Object> userData) {
            this.userData = userData;
        }
    }

    public interface JSONAble {
        JSONObject getJSON();

        JSONObject getJSON(boolean z);

        boolean setJSON(JSONObject jSONObject);
    }

    public static class GlobalLocation implements JSONAble, Parcelable {
        public static final Creator<GlobalLocation> CREATOR = new C40091();
        private float accuracy;
        private double altitude;
        private double bearing;
        private long lastNotificationTime;
        private double latitude;
        private double longitude;
        private String provider;
        private double speed;
        private long time;

        static class C40091 implements Creator<GlobalLocation> {
            C40091() {
            }

            public GlobalLocation createFromParcel(Parcel in) {
                return new GlobalLocation(in);
            }

            public GlobalLocation[] newArray(int size) {
                return new GlobalLocation[size];
            }
        }

        public GlobalLocation(Location gps) {
            if (gps != null) {
                this.latitude = gps.getLatitude();
                this.longitude = gps.getLongitude();
                this.altitude = gps.getAltitude();
                this.speed = (double) gps.getSpeed();
                this.accuracy = gps.getAccuracy();
                this.provider = gps.getProvider();
                this.bearing = (double) gps.getBearing();
                this.time = gps.getTime();
            }
        }

        private GlobalLocation(Parcel in) {
            this.latitude = in.readDouble();
            this.longitude = in.readDouble();
            this.altitude = in.readDouble();
            this.speed = in.readDouble();
            this.accuracy = in.readFloat();
            this.provider = in.readString();
            this.bearing = in.readDouble();
            this.time = in.readLong();
        }

        public void writeToParcel(Parcel out, int flags) {
            out.writeDouble(this.latitude);
            out.writeDouble(this.longitude);
            out.writeDouble(this.altitude);
            out.writeDouble(this.speed);
            out.writeFloat(this.accuracy);
            out.writeString(this.provider);
            out.writeDouble(this.bearing);
            out.writeLong(this.time);
        }

        public JSONObject getJSON() {
            return getJSON(false);
        }

        public JSONObject getJSON(boolean includeExtras) {
            JSONObject result = new JSONObject();
            try {
                double age = (((double) getTime()) / 1000.0d) - (((double) new Date().getTime()) / 1000.0d);
                result.put(C1048i.ko, getLatitude());
                result.put("lon", getLongitude());
                result.put("time", System.currentTimeMillis() - getTime());
                result.put("altitude", getAltitude());
                result.put("accuracy", (double) getAccuracy());
                result.put("provider", getProvider());
                result.put("speed", getSpeed());
                result.put("age", age);
                if (includeExtras) {
                    result.put("discoveryTime", getTime());
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return result;
        }

        public boolean setJSON(JSONObject json) {
            try {
                if (json.has(C1048i.ko)) {
                    setLatitude(json.getDouble(C1048i.ko));
                }
                if (json.has("lon")) {
                    setLongitude(json.getDouble(C1048i.ko));
                }
                if (json.has("time")) {
                    setTime(json.getLong("time"));
                }
                if (json.has("altitude")) {
                    setAltitude(json.getDouble("time"));
                }
                if (json.has("accuracy")) {
                    setAccuracy((float) json.getDouble("accuracy"));
                }
                if (json.has("provider")) {
                    setProvider(json.getString("provider"));
                }
                if (json.has("speed")) {
                    setSpeed(json.getDouble("speed"));
                }
                if (json.has("discoveryTime")) {
                    setTime(json.getLong("discoveryTime"));
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
                return false;
            }
        }

        public int describeContents() {
            return hashCode();
        }

        public double getLatitude() {
            return this.latitude;
        }

        public void setLatitude(double latitude) {
            this.latitude = latitude;
        }

        public double getLongitude() {
            return this.longitude;
        }

        public void setLongitude(double longitude) {
            this.longitude = longitude;
        }

        public double getAltitude() {
            return this.altitude;
        }

        public void setAltitude(double altitude) {
            this.altitude = altitude;
        }

        public long getTime() {
            return this.time;
        }

        public void setTime(long time) {
            this.time = time;
        }

        public double getSpeed() {
            return this.speed;
        }

        public void setSpeed(double speed) {
            this.speed = speed;
        }

        public double getBearing() {
            return this.bearing;
        }

        public void setBearing(double bearing) {
            this.bearing = bearing;
        }

        public float getAccuracy() {
            return this.accuracy;
        }

        public void setAccuracy(float accuracy) {
            this.accuracy = accuracy;
        }

        public String getProvider() {
            return this.provider;
        }

        public void setProvider(String provider) {
            this.provider = provider;
        }

        public long getLastNotificationTime() {
            return this.lastNotificationTime;
        }

        public void setLastNotificationTime(long lastNotificationTime) {
            this.lastNotificationTime = lastNotificationTime;
        }
    }

    public interface LocationService {

        public interface OnValidLocationCallback {
            void onLocationFound();
        }

        Location getCurrentLocation(Context context);

        double getLocationValidTime();

        boolean isLocationDiscoveryActive();

        void setLocationValidTime(double d);

        void setOnValidLocationCallback(OnValidLocationCallback onValidLocationCallback);

        void startLocationMonitoring(Context context);

        void stopLocationMonitoring(Context context);

        void waitForValidLocation(OnValidLocationCallback onValidLocationCallback);
    }

    public interface OnBeaconFoundListener {
        void onBeaconDiscovered(Map<String, Object> map);

        void onBeaconLeave(String str);
    }

    public interface OnLocationFoundListener {
        void onLocationDiscovered(GlobalLocation globalLocation);
    }

    public static class PDU {
        static final int APPEARANCE = 25;
        static final int COMPLETE_NAME = 9;
        static final int FLAGS = 1;
        static final int FLAGS_BE_EDR_SUPPORTED = 4;
        static final int FLAGS_CONTROLLER_DUAL = 8;
        static final int FLAGS_HOST_DUAL = 16;
        static final int FLAGS_LE_GENERAL_DISCOVERABLE = 2;
        static final int FLAGS_LE_LIMITED_DISCOVERABLE = 1;
        static final int MANUFACTURER_SPECIFIC_DATA = 255;
        static final int SERVICE_DATA = 22;
        static final int SHORT_NAME = 8;
        static final int TX_POWER = 10;
        static final int UUID128 = 7;
        static final int UUID16 = 3;
        static final int UUID16_INCOMPLETE = 2;
        static final int UUID32_INCOMPLETE = 6;
        private byte length = (byte) 0;
        private ArrayList<Byte> payload = new ArrayList();
        private int type = 0;

        public String toString() {
            String result = "";
            switch (this.type) {
                case 1:
                    result = result + "FLAGS";
                    break;
                case 2:
                    result = result + "UUID16-I";
                    break;
                case 3:
                    result = result + "UUID16";
                    break;
                case 6:
                    result = result + "UUID32-I";
                    break;
                case 7:
                    result = result + "UUID128";
                    break;
                case 8:
                    result = result + "NAME-S";
                    break;
                case 9:
                    result = result + "NAME";
                    break;
                case 10:
                    result = result + "TX";
                    break;
                case 22:
                    result = result + "SERVICE-DATA";
                    break;
                case 25:
                    result = result + "APPEARANCE";
                    break;
                case 255:
                    result = result + "DATA-" + manufacturerName(int16at(0));
                    break;
                default:
                    result = result + "PDU-(" + this.type + ")";
                    break;
            }
            switch (this.type) {
                case 8:
                case 9:
                    return result + ": " + getString();
                default:
                    return result + ": " + getHex();
            }
        }

        public static ArrayList<PDU> PDUList(byte[] data) {
            int current = 0;
            boolean done = false;
            ArrayList<PDU> result = new ArrayList();
            while (!done) {
                if (current >= data.length) {
                    done = true;
                } else {
                    byte length = data[current];
                    if (length < (byte) 1) {
                        done = true;
                    } else if (length + current >= data.length) {
                        done = true;
                    } else {
                        PDU pdu = new PDU(data, current);
                        result.add(pdu);
                        current = (pdu.getLength() + current) + 1;
                    }
                }
            }
            return result;
        }

        PDU(byte[] data, int start) {
            this.length = data[start];
            this.type = data[start + 1] & 255;
            for (int index = 0; index < this.length - 1; index++) {
                this.payload.add(Byte.valueOf(data[(start + 2) + index]));
            }
        }

        public String getHex() {
            String result = "";
            Iterator it = this.payload.iterator();
            while (it.hasNext()) {
                String byteHex = Integer.toHexString(((Byte) it.next()).byteValue() & 255);
                if (byteHex.length() == 1) {
                    byteHex = SchemaSymbols.ATTVAL_FALSE_0 + byteHex;
                }
                result = result + byteHex;
            }
            return result;
        }

        public String getString() {
            String result = "";
            Iterator it = this.payload.iterator();
            while (it.hasNext()) {
                result = result + ((char) ((Byte) it.next()).byteValue());
            }
            return result;
        }

        public String getUUID16At(int index) {
            return getHexAt(index, 4) + "-" + getHexAt(index + 4, 2) + "-" + getHexAt(index + 6, 2) + "-" + getHexAt(index + 8, 2) + "-" + getHexAt(index + 10, 6);
        }

        public String getHexAt(int index, int length) {
            String result = "";
            for (int i = 0; i < length; i++) {
                String byteHex = Integer.toHexString(int8at(index + i));
                if (byteHex.length() == 1) {
                    byteHex = SchemaSymbols.ATTVAL_FALSE_0 + byteHex;
                }
                result = result + byteHex;
            }
            return result;
        }

        public int int8at(int index) {
            if (index < this.payload.size()) {
                return ((Byte) this.payload.get(index)).byteValue() & 255;
            }
            return 0;
        }

        public int int16at(int index) {
            return (int8at(index + 1) << 8) + int8at(index);
        }

        public int int16FlippedAt(int index) {
            return (int8at(index) << 8) + int8at(index + 1);
        }

        public byte[] bytesAt(int index) {
            return bytesAt(index, this.payload.size() - index);
        }

        public byte[] bytesAt(int index, int size) {
            byte[] byteArray = new byte[this.payload.size()];
            for (int i = 0; i < size; i++) {
                byteArray[i] = ((Byte) this.payload.get(i + index)).byteValue();
            }
            return byteArray;
        }

        public static String manufacturerName(int code) {
            switch (code) {
                case 76:
                    return RevealBeaconService.TYPE_NAME_ESTIMOTE;
                case 117:
                    return "Samsung Electronics Co. Ltd.";
                case 138:
                    return "Jawbone";
                case PDUiBeacon.Gimbal /*140*/:
                    return RevealBeaconService.TYPE_NAME_GIMBAL;
                case PDUiBeacon.Swirl /*181*/:
                    return "Swirl";
                case 197:
                    return "Onset Computer Corporation";
                case 249:
                    return "StickNFind";
                case 272:
                    return "Nippon Seiki Co.";
                case PDUiBeacon.Radius /*280*/:
                    return "Radius";
                case PDUiBeacon.Estimote /*349*/:
                    return "Estimote";
                default:
                    return Card.UNKNOWN + code;
            }
        }

        public byte getLength() {
            return this.length;
        }

        public void setLength(byte length) {
            this.length = length;
        }

        public int getType() {
            return this.type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public ArrayList<Byte> getPayload() {
            return this.payload;
        }

        public void setPayload(ArrayList<Byte> payload) {
            this.payload = payload;
        }
    }

    public static class PDUiBeacon {
        public static final int Apple = 76;
        public static final int Estimote = 349;
        public static final int Gimbal = 140;
        public static final int Radius = 280;
        public static final int Swirl = 181;
        public final int LMP = SupportMenu.USER_MASK;
        private BluetoothItem device;
        private int flags;
        private int ind0;
        private int ind1;
        private int major;
        private String manufacturer;
        private int manufacturerCode = SupportMenu.USER_MASK;
        private int minor;
        private int power;
        private ArrayList<Byte> serviceData;
        private int serviceUUID;
        private String url;
        private String uuid;

        PDUiBeacon(BluetoothItem device) {
            setDevice(device);
        }

        public boolean isValid() {
            if (this.manufacturerCode == SupportMenu.USER_MASK) {
                return false;
            }
            if ((this.flags & 3) == 0) {
                return false;
            }
            if ((this.flags & 24) != 0) {
                return false;
            }
            return true;
        }

        public int getManufacturerCode() {
            return this.manufacturerCode;
        }

        public void setManufacturerCode(int manufacturerCode) {
            this.manufacturerCode = manufacturerCode;
        }

        public String getManufacturer() {
            return this.manufacturer;
        }

        public void setManufacturer(String manufacturer) {
            this.manufacturer = manufacturer;
        }

        public BluetoothItem getDevice() {
            return this.device;
        }

        public void clear() {
            this.manufacturerCode = SupportMenu.USER_MASK;
            this.manufacturer = null;
        }

        public void setDevice(BluetoothItem device) {
            boolean needClear = true;
            this.device = device;
            if (this.device != null) {
                PDU ad0 = this.device.getPDUType(1);
                PDU ad1 = this.device.getPDUType(255);
                PDU serviceData = this.device.getPDUType(22);
                if (serviceData == null) {
                    serviceData = this.device.getPDUType(255);
                }
                if (serviceData != null) {
                    this.serviceData = serviceData.getPayload();
                }
                PDU serviceUUID = this.device.getPDUType(3);
                if (serviceUUID != null) {
                    this.serviceUUID = serviceUUID.int16at(0);
                }
                if (!(ad0 == null || ad1 == null)) {
                    this.flags = ad0.int8at(0);
                    if (ad1.getLength() >= (byte) 23) {
                        needClear = false;
                        this.manufacturerCode = ad1.int16at(0);
                        this.manufacturer = PDU.manufacturerName(this.manufacturerCode);
                        this.ind0 = ad1.int8at(2);
                        this.ind1 = ad1.int8at(3);
                        this.uuid = ad1.getUUID16At(4);
                        this.major = ad1.int16FlippedAt(20);
                        this.minor = ad1.int16FlippedAt(22);
                        this.power = ad1.int8at(24);
                    }
                }
            }
            if (needClear) {
                clear();
            }
        }

        public String getUuid() {
            return this.uuid;
        }

        public void setUuid(String uuid) {
            this.uuid = uuid;
        }

        public int getMajor() {
            return this.major;
        }

        public void setMajor(int major) {
            this.major = major;
        }

        public int getMinor() {
            return this.minor;
        }

        public void setMinor(int minor) {
            this.minor = minor;
        }

        public int getInd0() {
            return this.ind0;
        }

        public void setInd0(int ind0) {
            this.ind0 = ind0;
        }

        public int getInd1() {
            return this.ind1;
        }

        public void setInd1(int ind1) {
            this.ind1 = ind1;
        }

        public int getPower() {
            return this.power;
        }

        public void setPower(int power) {
            this.power = power;
        }

        public int getLMP() {
            return SupportMenu.USER_MASK;
        }

        public ArrayList<Byte> getServiceData() {
            return this.serviceData;
        }

        public void setServiceData(ArrayList<Byte> serviceData) {
            this.serviceData = serviceData;
        }

        public int getServiceUUID() {
            return this.serviceUUID;
        }

        public void setServiceUUID(int serviceUUID) {
            this.serviceUUID = serviceUUID;
        }

        public String getUrl() {
            return this.url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getFlags() {
            return this.flags;
        }

        public void setFlags(int flags) {
            this.flags = flags;
        }
    }

    public static class RevealAddress implements Parcelable {
        public static final Creator<RevealAddress> CREATOR = new C40101();
        private String city;
        private String country;
        private String state;
        private String street;
        private String zip;

        static class C40101 implements Creator<RevealAddress> {
            C40101() {
            }

            public RevealAddress createFromParcel(Parcel in) {
                return new RevealAddress(in);
            }

            public RevealAddress[] newArray(int size) {
                return new RevealAddress[size];
            }
        }

        public HashMap<String, Object> getProperties() {
            HashMap<String, Object> result = new HashMap();
            result.put("street", this.street);
            result.put("city", this.city);
            result.put("state", this.state);
            result.put("zip", this.zip);
            result.put("country", this.country);
            return result;
        }

        private RevealAddress(Parcel in) {
            this.street = in.readString();
            this.city = in.readString();
            this.state = in.readString();
            this.zip = in.readString();
            this.country = in.readString();
        }

        public void writeToParcel(Parcel out, int flags) {
            out.writeString(this.street);
            out.writeString(this.city);
            out.writeString(this.state);
            out.writeString(this.zip);
            out.writeString(this.country);
        }

        public int describeContents() {
            return hashCode();
        }

        public String toString() {
            String result = "";
            if (this.street != null) {
                result = result + this.street;
            }
            if (this.city != null) {
                if (result.length() > 0) {
                    result = result + ", ";
                }
                result = result + this.city;
            }
            if (this.state != null) {
                if (result.length() > 0) {
                    result = result + ", ";
                }
                result = result + this.state;
            }
            if (this.zip != null) {
                if (result.length() > 0) {
                    result = result + " ";
                }
                result = result + this.zip;
            }
            if (this.country == null) {
                return result;
            }
            if (result.length() > 0) {
                result = result + ", ";
            }
            return result + this.country;
        }

        public String getStreet() {
            return this.street;
        }

        public void setStreet(String street) {
            this.street = street;
        }

        public String getCity() {
            return this.city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getState() {
            return this.state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getZip() {
            return this.zip;
        }

        public void setZip(String zip) {
            this.zip = zip;
        }

        public String getCountry() {
            return this.country;
        }

        public void setCountry(String country) {
            this.country = country;
        }
    }

    public static class RevealEventBase {
        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public org.json.JSONObject getBaseJSON(com.stepleaderdigital.reveal.Reveal.RevealEvent r9, boolean r10) {
            /*
            r8 = this;
            r1 = new org.json.JSONObject;
            r1.<init>();
            r4 = "dwellTime";
            r6 = r9.getDwellTime();	 Catch:{ JSONException -> 0x0080 }
            r1.put(r4, r6);	 Catch:{ JSONException -> 0x0080 }
            r4 = "lastSeenTime";
            r6 = r9.getLastSeen();	 Catch:{ JSONException -> 0x0080 }
            r1.put(r4, r6);	 Catch:{ JSONException -> 0x0080 }
            r4 = "discoveryTime";
            r6 = r9.getDiscoveryTime();	 Catch:{ JSONException -> 0x0080 }
            r1.put(r4, r6);	 Catch:{ JSONException -> 0x0080 }
            r3 = r9.getEventState();	 Catch:{ JSONException -> 0x0080 }
            if (r3 == 0) goto L_0x003d;
        L_0x0029:
            r4 = com.stepleaderdigital.reveal.Reveal.C40077.f9318x4cea7052;	 Catch:{ JSONException -> 0x0080 }
            r5 = r3.ordinal();	 Catch:{ JSONException -> 0x0080 }
            r4 = r4[r5];	 Catch:{ JSONException -> 0x0080 }
            switch(r4) {
                case 1: goto L_0x0076;
                case 2: goto L_0x009c;
                default: goto L_0x0034;
            };	 Catch:{ JSONException -> 0x0080 }
        L_0x0034:
            r4 = "event_state";
            r5 = "unknown";
            r1.put(r4, r5);	 Catch:{ JSONException -> 0x0080 }
        L_0x003d:
            r2 = r9.getCurrentSSID();	 Catch:{ JSONException -> 0x00a6 }
            if (r2 == 0) goto L_0x0049;
        L_0x0043:
            r4 = "currentSSID";
            r1.put(r4, r2);	 Catch:{ JSONException -> 0x00a6 }
        L_0x0049:
            r2 = r9.getCurrentBSSID();	 Catch:{ JSONException -> 0x00a6 }
            if (r2 == 0) goto L_0x0055;
        L_0x004f:
            r4 = "currentBSSID";
            r1.put(r4, r2);	 Catch:{ JSONException -> 0x00a6 }
        L_0x0055:
            if (r10 == 0) goto L_0x0075;
        L_0x0057:
            r4 = "lastNotificationTime";
            r6 = r9.getLastNotificationTime();	 Catch:{ JSONException -> 0x0080 }
            r1.put(r4, r6);	 Catch:{ JSONException -> 0x0080 }
            r4 = "notes";
            r5 = r9.getNotes();	 Catch:{ JSONException -> 0x0080 }
            r1.put(r4, r5);	 Catch:{ JSONException -> 0x0080 }
            r4 = "eventType";
            r5 = r9.getEventType();	 Catch:{ JSONException -> 0x0080 }
            r1.put(r4, r5);	 Catch:{ JSONException -> 0x0080 }
        L_0x0075:
            return r1;
        L_0x0076:
            r4 = "event_state";
            r5 = "OPEN";
            r1.put(r4, r5);	 Catch:{ JSONException -> 0x0080 }
            goto L_0x003d;
        L_0x0080:
            r0 = move-exception;
            r4 = new java.lang.StringBuilder;
            r4.<init>();
            r5 = "getBaseJSON() JSONException: ";
            r4 = r4.append(r5);
            r4 = r4.append(r0);
            r4 = r4.toString();
            com.stepleaderdigital.reveal.Reveal.RevealLogger.m12433e(r4);
            r0.printStackTrace();
            goto L_0x0075;
        L_0x009c:
            r4 = "event_state";
            r5 = "CLOSED";
            r1.put(r4, r5);	 Catch:{ JSONException -> 0x0080 }
            goto L_0x003d;
        L_0x00a6:
            r0 = move-exception;
            r0.printStackTrace();	 Catch:{ JSONException -> 0x0080 }
            goto L_0x0055;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.stepleaderdigital.reveal.Reveal.RevealEventBase.getBaseJSON(com.stepleaderdigital.reveal.Reveal$RevealEvent, boolean):org.json.JSONObject");
        }

        public void setBaseJSON(RevealEvent event, JSONObject json) {
            try {
                if (json.has("lastSeenTime")) {
                    event.setLastNotificationTime(json.getLong("lastSeenTime"));
                }
                if (json.has("discoveryTime")) {
                    event.setDiscoveryTime(json.getLong("discoveryTime"));
                }
                if (json.has("event_state")) {
                    String state = json.getString("event_state");
                    if (state != null) {
                        if (state.equalsIgnoreCase("OPEN")) {
                            event.setEventState(EventState.OPEN);
                        } else {
                            event.setEventState(EventState.CLOSED);
                        }
                    }
                }
                if (json.has("currentSSID")) {
                    event.setCurrentSSID(json.getString("currentSSID"));
                }
                if (json.has("currentBSSID")) {
                    event.setCurrentBSSID(json.getString("currentBSSID"));
                }
            } catch (JSONException e) {
                RevealLogger.m12433e("getBaseJSON() JSONException: " + e);
                e.printStackTrace();
            }
        }
    }

    public interface RevealEvent {

        public enum EventState {
            OPEN,
            CLOSED,
            ERROR
        }

        public enum EventType {
            UNKNOWN,
            BEACON,
            ENTER,
            DWELL,
            EXIT,
            START,
            WIFI_ENTER,
            LOCATION
        }

        String getCurrentBSSID();

        String getCurrentSSID();

        long getDiscoveryTime();

        double getDwellTime();

        EventState getEventState();

        EventType getEventType();

        String getIdentifier();

        long getLastNotificationTime();

        long getLastSeen();

        String getNotes();

        void setCurrentBSSID(String str);

        void setCurrentSSID(String str);

        void setDiscoveryTime(long j);

        void setEventState(EventState eventState);

        void setLastNotificationTime(long j);

        void setLastSeen(long j);

        void setNotes(String str);
    }

    public static class RevealBeacon extends RevealEventBase implements Parcelable, RevealEvent, JSONAble {
        public static final Creator<RevealBeacon> CREATOR = new C40111();
        public static final String[] PROXIMITY = new String[]{"unknown", "immediate", "near", "far"};
        private double accuracy;
        private String address;
        protected final int beaconSchemaVersion;
        private String beaconType;
        private int beaconTypeCode;
        private boolean complete;
        private String currentBSSID;
        private String currentSSID;
        private long discoveryTime;
        private double distance;
        private EventState eventState;
        private String identifier;
        private long lastNotificationTime;
        private long lastSeen;
        private GlobalLocation location;
        protected final int magic;
        private String major;
        private String minor;
        private String name;
        private String notes;
        private byte[] payload;
        private int proximity;
        private String proximityUUID;
        private int rssi;
        private int sendCount;
        private CountDownTimer sendTimer;
        private RevealAddress streetAddress;
        private int txPower;
        private String url;

        static class C40111 implements Creator<RevealBeacon> {
            C40111() {
            }

            public RevealBeacon createFromParcel(Parcel in) {
                return new RevealBeacon(in);
            }

            public RevealBeacon[] newArray(int size) {
                return new RevealBeacon[size];
            }
        }

        public RevealBeacon() {
            this.eventState = EventState.OPEN;
            this.sendCount = 0;
            this.magic = 9000;
            this.beaconSchemaVersion = 1;
            this.discoveryTime = new Date().getTime() / 1000;
        }

        public RevealBeacon(PDUiBeacon pduBeacon) {
            this.eventState = EventState.OPEN;
            this.sendCount = 0;
            this.magic = 9000;
            this.beaconSchemaVersion = 1;
            this.discoveryTime = new Date().getTime() / 1000;
            this.minor = "" + pduBeacon.getMinor();
            this.major = "" + pduBeacon.getMajor();
            this.identifier = pduBeacon.getUuid();
            this.proximityUUID = pduBeacon.getUuid();
            this.txPower = pduBeacon.getPower();
            this.name = pduBeacon.getDevice().getName();
            this.address = pduBeacon.getDevice().getAddress();
        }

        public EventType getEventType() {
            return EventType.BEACON;
        }

        public int getBeaconTypeCode() {
            return this.beaconTypeCode;
        }

        public static double calculateAccuracy(int txPower, double rssi) {
            double accuracy = -1.0d;
            if (!(rssi == 0.0d || txPower == 0)) {
                double ratio = (MoPubAdapter.DEFAULT_MOPUB_IMAGE_SCALE * rssi) / ((double) txPower);
                accuracy = ratio < MoPubAdapter.DEFAULT_MOPUB_IMAGE_SCALE ? Math.pow(ratio, 10.0d) : (0.89976d * Math.pow(ratio, 7.7095d)) + 0.111d;
            }
            if (Double.isInfinite(accuracy)) {
                return Double.MAX_VALUE;
            }
            try {
                return Double.valueOf(new DecimalFormat("#.00").format(accuracy)).doubleValue();
            } catch (NumberFormatException e) {
                return Double.MAX_VALUE;
            }
        }

        public long age() {
            return (new Date().getTime() / 1000) - this.discoveryTime;
        }

        public JSONObject getJSON() {
            return getJSON(false);
        }

        public JSONObject getJSON(boolean includeExtras) {
            JSONObject result = getBaseJSON(this, includeExtras);
            try {
                String major = getMajor();
                String minor = getMinor();
                result.put("name", getName());
                result.put("txpower", getTxPower());
                result.put("beacon_uuid", getProximityUUID());
                if (major != null) {
                    result.put("beacon_major", major);
                }
                if (minor != null) {
                    result.put("beacon_minor", minor);
                }
                result.put("beacon_mac", getAddress());
                result.put("beacon_proximity", PROXIMITY[getProximity()]);
                result.put("beacon_accuracy", getAccuracy());
                result.put("beacon_distance", getDistance());
                result.put("beacon_type", getBeaconType());
                result.put("beacon_rssi", getRssi());
                if (getUrl() != null) {
                    result.put("beacon_url", getUrl());
                }
                result.put("beacon_vendor", getBeaconTypeCode());
                result.put("dwellTime", getDwellTime());
                result.put("lastSeenTime", getLastSeen());
                result.put("discoveryTime", getDiscoveryTime());
                result.put("proximityInt", getProximity());
                result.put("type", getBeaconType());
                result.put("rssi", getRssi());
                result.put("proximity", PROXIMITY[getProximity()]);
                result.put("accuracy", getAccuracy());
                result.put(HTTP.IDENTITY_CODING, getProximityUUID());
                if (major != null) {
                    result.put("major", major);
                }
                if (minor != null) {
                    result.put("minor", minor);
                }
                GlobalLocation location = getLocation();
                if (location != null) {
                    result.put("location", location.getJSON(includeExtras));
                } else {
                    RevealLogger.m12441w("Sending response to the server without a location");
                }
                if (getPayload() != null) {
                    byte[] data = getPayload();
                    result.put("beacon_payload", Base64.encodeToString(data, 0, data.length, 2));
                }
                String type = getBeaconType();
                if (type != null && type.equals("SecureCast")) {
                    try {
                        String prox = getProximityUUID();
                        if (prox.startsWith("0x")) {
                            prox = prox.substring(2);
                        }
                        if (prox.length() <= 8) {
                            long value = Long.parseLong(prox, 16);
                            result.put("beacon_vendor_key", value);
                            result.put(C1028c.gv, value);
                        }
                    } catch (Exception e) {
                        RevealLogger.m12433e("ERROR converting value: beacon \"" + getProximityUUID() + "\": " + e);
                    }
                }
            } catch (JSONException e2) {
            }
            return result;
        }

        public boolean setJSON(JSONObject json) {
            setBaseJSON(this, json);
            try {
                if (json.has("name")) {
                    setName(json.getString("name"));
                }
                if (json.has("txpower")) {
                    setTxPower(json.getInt("txpower"));
                }
                if (json.has("beacon_uuid")) {
                    setProximityUUID(json.getString("beacon_uuid"));
                }
                if (json.has("beacon_major")) {
                    setMajor(json.getString("beacon_major"));
                }
                if (json.has("beacon_minor")) {
                    setMinor(json.getString("beacon_minor"));
                }
                if (json.has("beacon_mac")) {
                    setAddress(json.getString("beacon_mac"));
                }
                if (json.has("proximityInt")) {
                    setProximity(json.getInt("proximityInt"));
                }
                if (json.has("beacon_accuracy")) {
                    setAccuracy(json.getDouble("beacon_accuracy"));
                }
                if (json.has("beacon_distance")) {
                    setDistance(json.getDouble("beacon_distance"));
                }
                if (json.has("beacon_type")) {
                    setBeaconType(json.getString("beacon_type"));
                }
                if (json.has("beacon_rssi")) {
                    setRssi(json.getInt("beacon_rssi"));
                }
                if (json.has("beacon_url")) {
                    setUrl(json.getString("beacon_url"));
                }
                if (json.has("beacon_vendor")) {
                    setBeaconTypeCode(json.getInt("beacon_vendor"));
                }
                if (json.has("beacon_payload")) {
                    String base64String = json.getString("beacon_payload");
                    if (base64String != null) {
                        setPayload(Base64.decode(base64String, 0));
                    }
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
                return false;
            }
        }

        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            RevealBeacon beacon = (RevealBeacon) o;
            if (this.address != null) {
                return this.address.equals(beacon.address);
            }
            if (beacon.address != null) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return this.address != null ? this.address.hashCode() : 0;
        }

        EventType eventType() {
            return EventType.BEACON;
        }

        public String getUrl() {
            return this.url;
        }

        public String getBeaconType() {
            if (this.beaconType != null) {
                return this.beaconType;
            }
            return "Unknown (" + getBeaconTypeCode() + ")";
        }

        public HashMap<String, Object> getProperties() {
            HashMap<String, Object> result = new HashMap();
            result.put("bluetoothAddress", this.address);
            result.put("name", this.name);
            result.put("beacon_uuid", this.proximityUUID);
            result.put("major", this.major);
            result.put("minor", this.minor);
            result.put("txPower", Integer.valueOf(this.txPower));
            result.put("beacon_proximity", Integer.valueOf(this.proximity));
            result.put("beacon_accuracy", Double.valueOf(this.accuracy));
            result.put("url", this.url);
            result.put("beacon_rssi", Integer.valueOf(this.rssi));
            result.put("beacon_type", this.beaconType);
            result.put("distance", Double.valueOf(this.distance));
            result.put("payload", this.payload);
            result.put("discoveryTime", Long.valueOf(this.discoveryTime));
            result.put("dwellTime", Double.valueOf(getDwellTime()));
            result.put("lastSeen", Long.valueOf(this.lastSeen));
            result.put("currentSSID", this.currentSSID);
            result.put("currentBSSID", this.currentBSSID);
            result.put("identifier", this.identifier);
            result.put("notes", this.notes);
            if (this.location != null) {
                HashMap<String, Object> locationMap = new HashMap();
                locationMap.put("lon", Double.valueOf(this.location.getLongitude()));
                locationMap.put(C1048i.ko, Double.valueOf(this.location.getLatitude()));
                result.put("location", locationMap);
            }
            if (this.streetAddress != null) {
                result.put("address", this.streetAddress.getProperties());
            }
            return result;
        }

        public double getDwellTime() {
            return (double) Math.abs(this.lastSeen - this.discoveryTime);
        }

        public long getLastSeen() {
            return this.lastSeen;
        }

        public void setLastSeen(long lastSeen) {
            this.lastSeen = lastSeen;
        }

        private RevealBeacon(Parcel in) {
            this.eventState = EventState.OPEN;
            this.sendCount = 0;
            this.magic = 9000;
            this.beaconSchemaVersion = 1;
            getClass();
            getClass();
            if (Utils.magicCheck(in, 9000, 1, "RevealBeacon")) {
                this.address = in.readString();
                this.name = in.readString();
                this.proximityUUID = in.readString();
                this.major = in.readString();
                this.minor = in.readString();
                this.txPower = in.readInt();
                this.proximity = in.readInt();
                this.accuracy = in.readDouble();
                this.rssi = in.readInt();
                this.url = in.readString();
                this.distance = in.readDouble();
                this.discoveryTime = in.readLong();
                this.currentSSID = in.readString();
                this.currentBSSID = in.readString();
                this.identifier = in.readString();
                String payloadString = in.readString();
                if (payloadString != null) {
                    this.payload = hexStringToByteArray(payloadString);
                }
                this.notes = in.readString();
                if (in.readInt() == 1) {
                    this.location = (GlobalLocation) GlobalLocation.CREATOR.createFromParcel(in);
                }
            }
        }

        public void writeToParcel(Parcel out, int flags) {
            out.writeInt(9000);
            out.writeInt(1);
            out.writeString(this.address);
            out.writeString(this.name);
            out.writeString(this.proximityUUID);
            out.writeString(this.major);
            out.writeString(this.minor);
            out.writeInt(this.txPower);
            out.writeInt(this.proximity);
            out.writeDouble(this.accuracy);
            out.writeInt(this.rssi);
            out.writeString(this.url);
            out.writeDouble(this.distance);
            out.writeLong(this.discoveryTime);
            out.writeString(this.currentBSSID);
            out.writeString(this.currentBSSID);
            out.writeString(this.identifier);
            String payloadString = "";
            if (this.payload != null) {
                payloadString = bytesToHex(this.payload);
            }
            out.writeString(payloadString);
            String notesString = "";
            if (this.notes != null) {
                notesString = this.notes;
            }
            out.writeString(notesString);
            if (this.location != null) {
                out.writeInt(1);
                out.writeParcelable(this.location, 0);
                return;
            }
            out.writeInt(0);
        }

        public int describeContents() {
            return hashCode();
        }

        public String beaconID() {
            StringBuilder builder = new StringBuilder();
            if (this.proximityUUID != null) {
                builder.append(this.proximityUUID);
            }
            if (this.major != null) {
                builder.append(" (").append(this.major);
                if (this.minor != null) {
                    builder.append(BridgeUtil.SPLIT_MARK).append(this.minor);
                }
                builder.append(")");
            }
            if (this.url == null && this.major == null && this.address != null) {
                builder.append(" ").append(this.address);
            }
            return builder.toString();
        }

        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append(this.beaconType).append(" ").append(beaconID());
            if (this.name != null) {
                builder.append(" name: ").append(this.name);
            }
            if (this.url != null) {
                builder.append(" ").append(this.url);
            }
            if (this.payload != null) {
                builder.append(" payload: ").append(bytesToHex(this.payload));
            }
            return builder.toString();
        }

        public String fullDescription() {
            StringBuilder builder = new StringBuilder();
            builder.append("Beacon [address: ").append(this.address).append(", name: ").append(this.name).append(", type: ").append(this.beaconType).append(", proximityUUID: ").append(this.proximityUUID).append(", major: ").append(this.major).append(", minor: ").append(this.minor).append(", txPower: ").append(this.txPower).append(", proximity: ").append(this.proximity).append(", distance: ").append(this.distance).append(", accuracy: ").append(this.accuracy).append(", rssi: ").append(this.rssi).append(", location: ").append(this.location).append(", url: ").append(this.url).append(", payload=: ").append(bytesToHex(this.payload)).append("]");
            return builder.toString();
        }

        public static String bytesToHex(byte[] chars) {
            StringBuilder hex = new StringBuilder();
            if (chars != null) {
                for (byte b : chars) {
                    String byteHex = Integer.toHexString(b & 255);
                    if (byteHex.length() == 1) {
                        byteHex = SchemaSymbols.ATTVAL_FALSE_0 + byteHex;
                    }
                    hex.append(byteHex);
                }
            }
            return hex.toString();
        }

        public static byte[] hexStringToByteArray(String s) {
            int len = s.length();
            byte[] data = new byte[(len / 2)];
            for (int i = 0; i < len; i += 2) {
                data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i + 1), 16));
            }
            return data;
        }

        public String getAddress() {
            return this.address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getName() {
            return this.name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getProximityUUID() {
            return this.proximityUUID;
        }

        public void setProximityUUID(String proximityUUID) {
            this.proximityUUID = proximityUUID;
        }

        public String getMajor() {
            return this.major;
        }

        public void setMajor(String major) {
            this.major = major;
        }

        public String getMinor() {
            return this.minor;
        }

        public void setMinor(String minor) {
            this.minor = minor;
        }

        public byte[] getPayload() {
            return this.payload;
        }

        public void setPayload(byte[] payload) {
            this.payload = payload;
        }

        public int getTxPower() {
            return this.txPower;
        }

        public void setTxPower(int txPower) {
            this.txPower = txPower;
        }

        public int getProximity() {
            return this.proximity;
        }

        public void setProximity(int proximity) {
            this.proximity = proximity;
        }

        public double getAccuracy() {
            return this.accuracy;
        }

        public void setAccuracy(double accuracy) {
            this.accuracy = accuracy;
        }

        public int getRssi() {
            return this.rssi;
        }

        public void setRssi(int rssi) {
            this.rssi = rssi;
        }

        public GlobalLocation getLocation() {
            return this.location;
        }

        public void setLocation(GlobalLocation location) {
            this.location = location;
        }

        public void setBeaconTypeCode(int beaconTypeCode) {
            this.beaconTypeCode = beaconTypeCode;
        }

        public void setComplete(boolean complete) {
            this.complete = complete;
        }

        public boolean isComplete() {
            return this.complete;
        }

        public void setBeaconType(String beaconType) {
            this.beaconType = beaconType;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public double getDistance() {
            return this.distance;
        }

        public void setDistance(double distance) {
            this.distance = distance;
        }

        public long getDiscoveryTime() {
            return this.discoveryTime;
        }

        public void setDiscoveryTime(long discoveryTime) {
            this.discoveryTime = discoveryTime;
        }

        public String getIdentifier() {
            return this.identifier;
        }

        public void setIdentifier(String identifier) {
            this.identifier = identifier;
        }

        public CountDownTimer getSendTimer() {
            return this.sendTimer;
        }

        public void setSendTimer(CountDownTimer sendTimer) {
            this.sendTimer = sendTimer;
        }

        public String getCurrentSSID() {
            return this.currentSSID;
        }

        public void setCurrentSSID(String currentSSID) {
            this.currentSSID = currentSSID;
        }

        public String getCurrentBSSID() {
            return this.currentBSSID;
        }

        public void setCurrentBSSID(String currentBSSID) {
            this.currentBSSID = currentBSSID;
        }

        public RevealAddress getStreetAddress() {
            return this.streetAddress;
        }

        public void setStreetAddress(RevealAddress streetAddress) {
            this.streetAddress = streetAddress;
        }

        public long getLastNotificationTime() {
            return this.lastNotificationTime;
        }

        public void setLastNotificationTime(long lastNotificationTime) {
            this.lastNotificationTime = lastNotificationTime;
        }

        public EventState getEventState() {
            return this.eventState;
        }

        public void setEventState(EventState eventState) {
            this.eventState = eventState;
        }

        public String getNotes() {
            return this.notes;
        }

        public void setNotes(String notes) {
            this.notes = notes;
        }

        public void combineWith(RevealBeacon other) {
            if (other.distance < this.distance) {
                this.distance = other.distance;
                this.proximity = other.proximity;
                this.rssi = other.rssi;
            }
            if (other.location != null) {
                this.location = other.location;
            }
            if (other.address != null) {
                this.address = other.address;
            }
            if (other.streetAddress != null) {
                this.streetAddress = other.streetAddress;
            }
            if (other.url != null) {
                this.url = other.url;
            }
            if (other.payload != null) {
                this.payload = other.payload;
            }
        }
    }

    public static class RevealDwellManager implements JSONAble, Parcelable {
        public static final Creator<RevealDwellManager> CREATOR = new C40121();
        protected final int beaconSchemaVersion = 1;
        protected RevealDwellEventListener eventReadyListener;
        protected HashMap<EventType, Double> eventTypeTimes = new HashMap();
        protected final int magic = 9090;
        protected final ConcurrentHashMap<String, RevealEvent> pendingEvents = new ConcurrentHashMap();

        static class C40121 implements Creator<RevealDwellManager> {
            C40121() {
            }

            public RevealDwellManager createFromParcel(Parcel in) {
                return new RevealDwellManager(in);
            }

            public RevealDwellManager[] newArray(int size) {
                return new RevealDwellManager[size];
            }
        }

        public void addEvent(RevealEvent event) {
            if (event != null) {
                String key = event.getIdentifier();
                RevealLogger.m12430d("[IDLE-TEST] RevealBeacon addEvent: " + event);
                event.setLastSeen(new Date().getTime() / 1000);
                if (key != null) {
                    if (key.length() > 0) {
                        RevealEvent old = (RevealEvent) this.pendingEvents.get(key);
                        if (old == null) {
                            this.pendingEvents.put(key, event);
                            if (event.getEventType() == EventType.BEACON) {
                                ((RevealBeacon) event).location = new GlobalLocation(Reveal.getInstance().locationService.getCurrentLocation(Reveal.getInstance().applicationContext));
                            }
                        } else if (old.getEventType() == EventType.BEACON) {
                            RevealBeacon beacon = (RevealBeacon) event;
                            RevealBeacon oldBeacon = (RevealBeacon) old;
                            oldBeacon.setLastSeen(event.getLastSeen());
                            oldBeacon.combineWith(beacon);
                            RevealLogger.m12430d("[IDLE-TEST] RevealBeacon addEvent combined: " + event);
                        }
                    }
                    processPendingEvents();
                }
            }
        }

        public void addEventType(EventType type, Double lossDelay) {
            if (this.eventTypeTimes.containsKey(type)) {
                this.eventTypeTimes.remove(type);
            }
            this.eventTypeTimes.put(type, lossDelay);
        }

        public void processInProgress(long maxTimeSinceAccess) {
            if (getEventReadyListener() != null) {
                Iterator it = getOldEvents(maxTimeSinceAccess).iterator();
                while (it.hasNext()) {
                    getEventReadyListener().onEventReady((RevealEvent) it.next());
                }
            }
        }

        public ArrayList<RevealEvent> getOldEvents(long maxTimeSinceAccess) {
            ArrayList<RevealEvent> result = new ArrayList();
            long now = (long) (((double) new Date().getTime()) / 1000.0d);
            for (String key : this.pendingEvents.keySet()) {
                RevealEvent event = (RevealEvent) this.pendingEvents.get(key);
                if (event != null && now - event.getLastNotificationTime() >= maxTimeSinceAccess) {
                    event.setLastNotificationTime(now);
                    result.add(event);
                }
            }
            return result;
        }

        public void processPendingEvents() {
            boolean isIdle = false;
            PowerManager pm = (PowerManager) Reveal.getInstance().applicationContext.getSystemService("power");
            if (VERSION.SDK_INT >= 23) {
                isIdle = pm.isDeviceIdleMode();
            }
            if (isIdle) {
                Reveal.log("We are delaying processing events because we are in idle mode count: " + this.pendingEvents.size(), "IDLE-TEST");
            } else {
                Reveal.log("processPendingEvents event count: " + this.pendingEvents.size(), "IDLE-TEST]");
                for (String key : this.pendingEvents.keySet()) {
                    RevealEvent event = (RevealEvent) this.pendingEvents.get(key);
                    if (event != null && event.getLastSeen() > 0) {
                        Double now = Double.valueOf(((double) new Date().getTime()) / 1000.0d);
                        Double interval = Double.valueOf(Math.abs(now.doubleValue() - ((double) event.getLastSeen())));
                        Double threshold = Double.valueOf(0.0d);
                        if (this.eventTypeTimes.containsKey(event.getEventType())) {
                            threshold = (Double) this.eventTypeTimes.get(event.getEventType());
                        }
                        if (threshold != null && interval.doubleValue() >= threshold.doubleValue()) {
                            event.setNotes("type " + event.getEventType() + "  interval=" + interval + " > " + threshold + "  discovery=" + event.getDiscoveryTime() + " lastSeen=" + event.getLastSeen() + " now=" + now + " dwell=" + event.getDwellTime());
                            Reveal.log("[] Adding READY event because " + event.getNotes() + "");
                            if (event.getEventType() == EventType.BEACON) {
                                RevealBeacon beacon = (RevealBeacon) event;
                                Reveal.log("Beacon being released from dwell manager (normal expiration): " + beacon.beaconType + " " + beacon.identifier, "STATE");
                            } else if (event.getEventType() == EventType.ENTER) {
                                RevealLocation location = (RevealLocation) event;
                                Reveal.log("Location being released from dwell manager (normal expiration): lon: " + location.getLocation().longitude + " lat: " + location.getLocation().latitude, "STATE");
                            } else {
                                Reveal.log("Event being released from dwell manager (normal expiration): " + event, "STATE");
                            }
                            if (this.eventReadyListener != null) {
                                this.eventReadyListener.onEventReady(event);
                            }
                            this.pendingEvents.remove(key);
                        }
                    }
                }
            }
            Reveal.getInstance().storeInPreferences();
        }

        public void releaseAll() {
            for (String key : this.pendingEvents.keySet()) {
                RevealEvent event = (RevealEvent) this.pendingEvents.get(key);
                if (event.getLastSeen() > 0) {
                    if (event.getEventType() == EventType.BEACON) {
                        RevealBeacon beacon = (RevealBeacon) event;
                        Reveal.log("Beacon being released from dwell manager (releaseAll): " + beacon.beaconType + " " + beacon.identifier, "STATE");
                    } else if (event.getEventType() == EventType.ENTER) {
                        RevealLocation location = (RevealLocation) event;
                        Reveal.log("Location being released from dwell manager (releaseAll): lon: " + location.getLocation().longitude + " lat: " + location.getLocation().latitude, "STATE");
                    } else {
                        Reveal.log("Event being released from dwell manager (releaseAll): " + event, "STATE");
                    }
                    if (this.eventReadyListener != null) {
                        this.eventReadyListener.onEventReady(event);
                    }
                    this.pendingEvents.remove(key);
                }
            }
            Reveal.getInstance().storeInPreferences();
        }

        public ArrayList<RevealEvent> getPendingEvents() {
            return new ArrayList(this.pendingEvents.values());
        }

        public RevealDwellEventListener getEventReadyListener() {
            return this.eventReadyListener;
        }

        public void setEventReadyListener(RevealDwellEventListener eventReadyListener) {
            this.eventReadyListener = eventReadyListener;
        }

        public int describeContents() {
            return hashCode();
        }

        public RevealDwellManager(Parcel in) {
            String json = in.readString();
            if (json != null) {
                try {
                    setJSON(new JSONObject(json));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

        public JSONObject getJSON() {
            return getJSON(false);
        }

        public JSONObject getJSON(boolean includeExtras) {
            JSONObject result = new JSONObject();
            ArrayList<RevealEvent> events = new ArrayList(this.pendingEvents.values());
            JSONArray beacons = new JSONArray();
            JSONArray locations = new JSONArray();
            Iterator it = events.iterator();
            while (it.hasNext()) {
                RevealEvent event = (RevealEvent) it.next();
                EventType eventType = event.getEventType();
                switch (eventType) {
                    case BEACON:
                        try {
                            beacons.put(((RevealBeacon) event).getJSON(true));
                            break;
                        } catch (Exception e) {
                            e.printStackTrace();
                            break;
                        }
                    case ENTER:
                        locations.put(((RevealLocation) event).getJSON(true));
                        break;
                    case WIFI_ENTER:
                        break;
                    default:
                        RevealLogger.m12441w("Unknown event type " + eventType + " encountered");
                        break;
                }
            }
            if (beacons.length() > 0) {
                try {
                    result.put(C1403a.f2090t, beacons);
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
            }
            if (locations.length() > 0) {
                try {
                    result.put("locations", locations);
                    Reveal.log("Adding " + locations.length() + " locations to batch", "STATE");
                } catch (JSONException e22) {
                    e22.printStackTrace();
                }
            }
            return result;
        }

        public boolean setJSON(JSONObject json) {
            try {
                JSONArray array;
                int index;
                JSONObject item;
                if (json.has(C1403a.f2090t)) {
                    array = json.getJSONArray(C1403a.f2090t);
                    for (index = 0; index < array.length(); index++) {
                        item = (JSONObject) array.get(index);
                        RevealBeacon beacon = new RevealBeacon();
                        beacon.setJSON(item);
                        addEvent(beacon);
                    }
                }
                if (json.has("locations")) {
                    array = json.getJSONArray("locations");
                    for (index = 0; index < array.length(); index++) {
                        item = (JSONObject) array.get(index);
                        RevealLocation loc = new RevealLocation();
                        loc.setJSON(item);
                        addEvent(loc);
                    }
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
                return false;
            }
        }

        public void writeToParcel(Parcel out, int flags) {
            out.writeString(getJSON(true).toString());
        }

        public int getCount() {
            return this.pendingEvents.size();
        }
    }

    public static class RevealEventCache implements JSONAble {
        private RevealEventListener batchReadyListener = null;
        private ArrayList<RevealEvent> events = new ArrayList();
        private long idleTimeout = 60;
        private CountDownTimer idleTimer;
        private int maxCachedEvents = 50;
        private int maxCachedEventsOverrun = (this.maxCachedEvents * 5);

        class C40141 implements Runnable {
            C40141() {
            }

            public void run() {
                if (RevealEventCache.this.idleTimer != null) {
                    RevealEventCache.this.idleTimer.cancel();
                }
                RevealEventCache.this.idleTimer = null;
                if (RevealEventCache.this.events.size() > RevealEventCache.this.maxCachedEvents) {
                    RevealEventCache.this.flushEvents();
                } else {
                    RevealEventCache.this.idleTimer = new CountDownTimer(RevealEventCache.this.idleTimeout * 1000, 60000) {
                        public void onTick(long millisUntilFinished) {
                        }

                        public void onFinish() {
                            RevealEventCache.this.flushEvents();
                            RevealEventCache.this.idleTimer = null;
                        }
                    }.start();
                }
            }
        }

        public void addEvent(RevealEvent event) {
            synchronized (this) {
                this.events.add(event);
                while (this.events.size() > this.maxCachedEventsOverrun) {
                    this.events.remove(0);
                }
            }
            RevealLogger.m12440v("RevealEventCache add (" + this.events.size() + ") added: " + event);
            new Handler(Looper.getMainLooper()).post(new C40141());
        }

        public void flushEvents() {
            boolean inBackground = Reveal.getInstance().isInBackGround();
            boolean sendInBackground = Reveal.getInstance().batchBackgroundSend;
            if ((!inBackground || sendInBackground || count(EventType.ENTER) > 0) && !Reveal.getInstance().isIdle()) {
                ArrayList<RevealEvent> eventList = getEventsAndClear();
                RevealLogger.m12440v("RevealEventCache flushEvents - have " + eventList.size() + " eventCache to send");
                if (this.batchReadyListener != null) {
                    this.batchReadyListener.onCacheReady(eventList);
                }
            }
        }

        public int count(EventType typeTofFind) {
            int result = 0;
            Iterator<RevealEvent> iter = this.events.iterator();
            while (iter.hasNext()) {
                if (((RevealEvent) iter.next()).getEventType() == typeTofFind) {
                    result++;
                }
            }
            return result;
        }

        public JSONObject getJSON() {
            return getJSON(false);
        }

        public JSONObject getJSON(boolean includeExtras) {
            JSONObject result = new JSONObject();
            try {
                ArrayList<RevealEvent> events = new ArrayList(this.events);
                JSONArray jsonEvents = new JSONArray();
                Iterator it = events.iterator();
                while (it.hasNext()) {
                    jsonEvents.put(((JSONAble) ((RevealEvent) it.next())).getJSON(true));
                }
                if (jsonEvents.length() > 0) {
                    try {
                        result.put(Constants.VIDEO_TRACKING_EVENTS_KEY, jsonEvents);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            return result;
        }

        public boolean setJSON(JSONObject json) {
            try {
                if (json.has(Constants.VIDEO_TRACKING_EVENTS_KEY)) {
                    JSONArray array = json.getJSONArray(Constants.VIDEO_TRACKING_EVENTS_KEY);
                    for (int index = 0; index < array.length(); index++) {
                        JSONObject item = (JSONObject) array.get(index);
                        if (item.has("eventType")) {
                            switch (EventType.values()[item.getInt("eventType")]) {
                                case BEACON:
                                    RevealBeacon beacon = new RevealBeacon();
                                    beacon.setJSON(item);
                                    addEvent(beacon);
                                    break;
                                case LOCATION:
                                    RevealLocation location = new RevealLocation();
                                    location.setJSON(item);
                                    addEvent(location);
                                    break;
                                default:
                                    continue;
                            }
                        }
                    }
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
                return false;
            }
        }

        public ArrayList<RevealEvent> getEventsAndClear() {
            ArrayList<RevealEvent> result;
            synchronized (this) {
                result = this.events;
                this.events = new ArrayList();
                RevealLogger.m12440v("RevealEventCache getEventsAndClear - have " + result.size() + " eventCache to send");
            }
            return result;
        }

        public int getMaxCachedEvents() {
            return this.maxCachedEvents;
        }

        public void setMaxCachedEvents(int maxCachedEvents) {
            this.maxCachedEvents = maxCachedEvents;
        }

        public long getIdleTimeout() {
            return this.idleTimeout;
        }

        public void setIdleTimeout(long idleTimeout) {
            this.idleTimeout = idleTimeout;
        }

        public RevealEventListener getBatchReadyListener() {
            return this.batchReadyListener;
        }

        public void setBatchReadyListener(RevealEventListener batchReadyListener) {
            this.batchReadyListener = batchReadyListener;
        }

        public int getMaxCachedEventsOverrun() {
            return this.maxCachedEventsOverrun;
        }

        public void setMaxCachedEventsOverrun(int maxCachedEventsOverrun) {
            this.maxCachedEventsOverrun = maxCachedEventsOverrun;
        }
    }

    public static class RevealLocation extends RevealEventBase implements Parcelable, JSONAble, RevealEvent {
        public static final Creator<RevealLocation> CREATOR = new C40151();
        protected final int beaconSchemaVersion;
        private String currentBSSID;
        private String currentSSID;
        private long discoveryTime;
        private EventState eventState;
        private long lastNotificationTime;
        private long lastSeen;
        private GlobalLocation location;
        protected final int magic;
        private String notes;

        static class C40151 implements Creator<RevealLocation> {
            C40151() {
            }

            public RevealLocation createFromParcel(Parcel in) {
                return new RevealLocation(in);
            }

            public RevealLocation[] newArray(int size) {
                return new RevealLocation[size];
            }
        }

        public RevealLocation() {
            this.eventState = EventState.CLOSED;
            this.magic = 9001;
            this.beaconSchemaVersion = 1;
            this.discoveryTime = new Date().getTime() / 1000;
        }

        public JSONObject getJSON() {
            return getJSON(false);
        }

        public JSONObject getJSON(boolean includeExtras) {
            JSONObject result = getBaseJSON(this, includeExtras);
            try {
                result.put("location", this.location.getJSON(includeExtras));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return result;
        }

        public boolean setJSON(JSONObject json) {
            setBaseJSON(this, json);
            try {
                if (json.has("location")) {
                    JSONObject locJSON = json.getJSONObject("location");
                    GlobalLocation loc = new GlobalLocation();
                    loc.setJSON(locJSON);
                    setLocation(loc);
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
                return false;
            }
        }

        public EventType getEventType() {
            return EventType.ENTER;
        }

        public GlobalLocation getLocation() {
            return this.location;
        }

        public String getCurrentSSID() {
            return this.currentSSID;
        }

        public void setCurrentSSID(String currentSSID) {
            this.currentSSID = currentSSID;
        }

        public String getCurrentBSSID() {
            return this.currentBSSID;
        }

        public void setCurrentBSSID(String currentBSSID) {
            this.currentBSSID = currentBSSID;
        }

        public double getDwellTime() {
            return (double) Math.abs(this.lastSeen - this.discoveryTime);
        }

        public long getLastNotificationTime() {
            return this.lastNotificationTime;
        }

        public void setLastNotificationTime(long lastNotificationTime) {
            this.lastNotificationTime = lastNotificationTime;
        }

        public EventState getEventState() {
            return this.eventState;
        }

        public void setEventState(EventState eventState) {
            this.eventState = eventState;
        }

        public long getLastSeen() {
            return this.lastSeen;
        }

        public void setLastSeen(long lastSeen) {
            this.lastSeen = lastSeen;
        }

        public void setDiscoveryTime(long discoveryTime) {
            this.discoveryTime = discoveryTime;
        }

        public String getIdentifier() {
            return "location";
        }

        public void setLocation(GlobalLocation location) {
            this.location = location;
        }

        private RevealLocation(Parcel in) {
            this.eventState = EventState.CLOSED;
            this.magic = 9001;
            this.beaconSchemaVersion = 1;
            getClass();
            getClass();
            if (Utils.magicCheck(in, 9001, 1, "RevealLocation")) {
                if (in.readInt() == 1) {
                    this.location = (GlobalLocation) in.readParcelable(getClass().getClassLoader());
                }
                this.discoveryTime = in.readLong();
            }
        }

        public String getNotes() {
            return this.notes;
        }

        public void setNotes(String notes) {
            this.notes = notes;
        }

        public void writeToParcel(Parcel out, int flags) {
            out.writeInt(9001);
            out.writeInt(1);
            if (this.location != null) {
                out.writeInt(1);
                out.writeParcelable(this.location, 0);
            } else {
                out.writeInt(0);
            }
            out.writeLong(this.discoveryTime);
        }

        public int describeContents() {
            return hashCode();
        }

        public long getDiscoveryTime() {
            return this.discoveryTime;
        }
    }

    public static class RevealLogger {
        private static final String LOG_TAG = "Reveal";
        private static final int STACK_TRACE_LEVELS_UP = 5;
        private static boolean verboseLoggingEnabled = false;

        public static void m12430d(Object object) {
            if (Reveal.getInstance().getIsDebug().booleanValue()) {
                Log.d(LOG_TAG, getClassNameMethodNameAndLineNumber() + object);
            }
        }

        public static void m12440v(Object object) {
            if (Reveal.getInstance().getIsDebug().booleanValue() && verboseLoggingEnabled) {
                Log.v(LOG_TAG, getClassNameMethodNameAndLineNumber() + object);
            }
        }

        public static void m12433e(Object object) {
            if (Reveal.getInstance().getIsDebug().booleanValue()) {
                Log.e(LOG_TAG, getClassNameMethodNameAndLineNumber() + object);
            }
        }

        public static void m12441w(Object object) {
            if (Reveal.getInstance().getIsDebug().booleanValue()) {
                Log.w(LOG_TAG, getClassNameMethodNameAndLineNumber() + object);
            }
        }

        public static void m12429d() {
            if (Reveal.getInstance().getIsDebug().booleanValue()) {
                Log.d(LOG_TAG, getClassNameMethodNameAndLineNumber());
            }
        }

        public static void m12437i(Object object) {
            if (Reveal.getInstance().getIsDebug().booleanValue()) {
                Log.d(LOG_TAG, getClassNameMethodNameAndLineNumber() + object);
            }
        }

        public static void m12436i() {
            if (Reveal.getInstance().getIsDebug().booleanValue()) {
                Log.i(LOG_TAG, getClassNameMethodNameAndLineNumber());
            }
        }

        public static void m12439v() {
            if (Reveal.getInstance().getIsDebug().booleanValue() && verboseLoggingEnabled) {
                Log.v(LOG_TAG, getClassNameMethodNameAndLineNumber());
            }
        }

        public static void m12432e() {
            if (Reveal.getInstance().getIsDebug().booleanValue()) {
                Log.e(LOG_TAG, getClassNameMethodNameAndLineNumber());
            }
        }

        public static void m12431d(String message, Object... args) {
            if (Reveal.getInstance().getIsDebug().booleanValue()) {
                Log.d(LOG_TAG, formatString(message, args));
            }
        }

        public static void m12434e(String message, Object... args) {
            if (Reveal.getInstance().getIsDebug().booleanValue()) {
                Log.e(LOG_TAG, formatString(message, args));
            }
        }

        public static void m12442w(String message, Object... args) {
            if (Reveal.getInstance().getIsDebug().booleanValue()) {
                Log.w(LOG_TAG, formatString(message, args));
            }
        }

        public static void m12438i(String message, Object... args) {
            if (Reveal.getInstance().getIsDebug().booleanValue() && verboseLoggingEnabled) {
                Log.i(LOG_TAG, formatString(message, args));
            }
        }

        public static void m12443w(Throwable t, String message, Object... args) {
            if (Reveal.getInstance().getIsDebug().booleanValue()) {
                Log.w(LOG_TAG, formatString(message, args), t);
            }
        }

        public static void m12435e(Throwable t, String message, Object... args) {
            if (Reveal.getInstance().getIsDebug().booleanValue()) {
                Log.e(LOG_TAG, formatString(message, args), t);
            }
        }

        public static String formatString(String message, Object... args) {
            return args.length == 0 ? message : String.format(message, args);
        }

        private static int getLineNumber() {
            return Thread.currentThread().getStackTrace()[5].getLineNumber();
        }

        private static String getClassName() {
            String fileName = Thread.currentThread().getStackTrace()[5].getFileName();
            return fileName.substring(0, fileName.length() - 5);
        }

        private static String getMethodName() {
            return Thread.currentThread().getStackTrace()[5].getMethodName();
        }

        private static String getClassNameMethodNameAndLineNumber() {
            return "[" + getClassName() + "." + getMethodName() + "()-" + getLineNumber() + "]: ";
        }

        public static boolean isVerboseLoggingEnabled() {
            return verboseLoggingEnabled;
        }

        public static void setVerboseLoggingEnabled(boolean enabled) {
            verboseLoggingEnabled = enabled;
        }
    }

    public static class RevealNetworkClient {
        public static final String SEGMENT_BATCH = "/api/v3/event/batch";
        public static final String SEGMENT_BEACON = "/api/v3/event/rawbeacon";
        public static final String SEGMENT_INFO = "/api/v3/info";
        private static final ExecutorService executor = Executors.newFixedThreadPool(3);

        private static class Task implements Runnable {
            private RevealNetworkClientCallback callback = null;
            private Handler handler = null;
            private String json = null;
            private String segment = null;

            public Task(Handler handler, String segment, String json, RevealNetworkClientCallback callback) {
                this.handler = handler;
                this.segment = segment;
                this.json = json;
                this.callback = callback;
            }

            public void run() {
                Process.setThreadPriority(10);
                HttpURLConnection urlConnection = null;
                int responseCode = 0;
                InputStream in = null;
                try {
                    Reveal.getInstance().setStatus("network", 2, "Sending: " + this.segment);
                    urlConnection = (HttpURLConnection) new URL(Reveal.getInstance().getAPIBaseURL() + this.segment).openConnection();
                    urlConnection.setReadTimeout(3000);
                    urlConnection.setConnectTimeout(3000);
                    urlConnection.setChunkedStreamingMode(0);
                    urlConnection.addRequestProperty("X-API-KEY", Reveal.getInstance().getAPIKey());
                    urlConnection.addRequestProperty("Content-Type", RequestParams.APPLICATION_JSON);
                    urlConnection.setRequestProperty("Connection", "keep-alive");
                    urlConnection.setRequestProperty("Connection", "close");
                    urlConnection.setRequestMethod(HttpPost.METHOD_NAME);
                    urlConnection.setDoInput(true);
                    urlConnection.setDoOutput(true);
                    OutputStream os = urlConnection.getOutputStream();
                    os.write(this.json.getBytes(Charset.forName("UTF-8")));
                    os.close();
                    Reveal.log("Sent to server endpoint: " + this.segment + "\n" + this.json, "COMM");
                    urlConnection.connect();
                    responseCode = urlConnection.getResponseCode();
                    Reveal.log("Connection to server successful with response code " + responseCode);
                    if (responseCode < 300) {
                        in = new BufferedInputStream(urlConnection.getInputStream());
                    } else {
                        in = new BufferedInputStream(urlConnection.getErrorStream());
                    }
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder total = new StringBuilder();
                    while (true) {
                        String line = reader.readLine();
                        if (line == null) {
                            break;
                        }
                        total.append(line);
                    }
                    String content = total.toString();
                    in.close();
                    if (responseCode == 200) {
                        int length = content.length();
                        Reveal.getInstance().setStatus("network", 1, "SUCCESS: " + this.segment + " Returned " + length + " bytes");
                        if (length > 0) {
                            Reveal.log("HTTP response content: " + content, "COMM");
                            JSONObject jsonObject = new JSONObject(content);
                            Reveal.getInstance().recordEvent(this.segment);
                            success(this.callback, jsonObject);
                        } else {
                            Reveal.log("HTTP response 200 OK with no content", "COMM");
                            Reveal.getInstance().recordEvent(this.segment);
                            success(this.callback, null);
                        }
                    } else {
                        Reveal.log("HTTP Response " + responseCode + ": " + content, RewardedVideoActivity.REQUEST_STATUS_PARAMETER_ERROR, "COMM");
                        failed(this.callback, content);
                        Reveal.getInstance().setStatus("network", 0, "HTTP Response " + responseCode + ": " + content);
                    }
                    if (in != null) {
                        try {
                            in.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (urlConnection != null) {
                        urlConnection.disconnect();
                    }
                    Reveal.logMemoryUsage("Web Service callback");
                } catch (Exception ex) {
                    Reveal.log("HTTP Error " + responseCode + ": " + ex.toString(), "error", "COMM");
                    Reveal.getInstance().recordEvent(this.segment, false);
                    Reveal.getInstance().setStatus("network", 0, "HTTP Error " + responseCode + ": " + ex.toString());
                    failed(this.callback, ex.toString());
                    if (in != null) {
                        try {
                            in.close();
                        } catch (IOException e2) {
                            e2.printStackTrace();
                        }
                    }
                    if (urlConnection != null) {
                        urlConnection.disconnect();
                    }
                    Reveal.logMemoryUsage("Web Service callback");
                } catch (Throwable th) {
                    if (in != null) {
                        try {
                            in.close();
                        } catch (IOException e22) {
                            e22.printStackTrace();
                        }
                    }
                    if (urlConnection != null) {
                        urlConnection.disconnect();
                    }
                    Reveal.logMemoryUsage("Web Service callback");
                }
            }

            private void success(final RevealNetworkClientCallback callback, final JSONObject response) {
                if (callback != null) {
                    this.handler.post(new Runnable() {
                        public void run() {
                            callback.onSuccess(response);
                        }
                    });
                }
            }

            private void failed(final RevealNetworkClientCallback callback, final String response) {
                if (callback != null) {
                    this.handler.post(new Runnable() {
                        public void run() {
                            callback.onFailure(response);
                        }
                    });
                }
            }
        }

        public static void registerDevice(Context context, RevealNetworkClientCallback callback) {
            execute(SEGMENT_INFO, Utils.getJsonInitInfo(context), callback);
        }

        public static void sendBatch(Context context, List<RevealEvent> eventList, RevealNetworkClientCallback callback) {
            execute(SEGMENT_BATCH, Utils.getJsonBatch(context, eventList), callback);
        }

        private static void execute(String segment, String json, RevealNetworkClientCallback callback) {
            Reveal.log("Service call made to segment " + segment + ", callback: " + callback + ", json: " + json, "COMM");
            try {
                executor.execute(new Task(new Handler(Looper.getMainLooper()), segment, json, callback));
            } catch (Exception ex) {
                Reveal.log("Network api exception: " + ex.toString(), RewardedVideoActivity.REQUEST_STATUS_PARAMETER_ERROR, "COMM");
                Reveal.getInstance().setStatus("network", 0, ex.toString());
            }
        }
    }

    public static class RevealStatus {
        private String message = "";
        private String name = "unknown";
        private int status = 0;

        public int getStatus() {
            return this.status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getMessage() {
            String result = this.message;
            if (result == null) {
                return "Status: " + this.status;
            }
            return result;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getName() {
            return this.name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class RevealWiFi implements Parcelable, RevealEvent {
        public static final Creator<RevealWiFi> CREATOR = new C40181();
        protected String BSSID;
        protected String SSID;
        protected final int beaconSchemaVersion;
        private String currentBSSID;
        private String currentSSID;
        protected long discoveryTime;
        private EventState eventState;
        private long lastNotificationTime;
        private long lastSeen;
        protected GlobalLocation location;
        protected final int magic;
        private String notes;

        static class C40181 implements Creator<RevealWiFi> {
            C40181() {
            }

            public RevealWiFi createFromParcel(Parcel in) {
                return new RevealWiFi(in);
            }

            public RevealWiFi[] newArray(int size) {
                return new RevealWiFi[size];
            }
        }

        public RevealWiFi() {
            this.eventState = EventState.OPEN;
            this.magic = 9002;
            this.beaconSchemaVersion = 1;
            this.discoveryTime = new Date().getTime() / 1000;
        }

        public double getDwellTime() {
            return (double) Math.abs(this.lastSeen - this.discoveryTime);
        }

        public long getLastSeen() {
            return this.lastSeen;
        }

        public void setLastSeen(long lastSeen) {
            this.lastSeen = lastSeen;
        }

        public String getIdentifier() {
            return this.BSSID;
        }

        public EventType getEventType() {
            return EventType.WIFI_ENTER;
        }

        public long getLastNotificationTime() {
            return this.lastNotificationTime;
        }

        public void setLastNotificationTime(long lastNotificationTime) {
            this.lastNotificationTime = lastNotificationTime;
        }

        public EventState getEventState() {
            return this.eventState;
        }

        public void setEventState(EventState eventState) {
            this.eventState = eventState;
        }

        private RevealWiFi(Parcel in) {
            this.eventState = EventState.OPEN;
            this.magic = 9002;
            this.beaconSchemaVersion = 1;
            getClass();
            getClass();
            if (Utils.magicCheck(in, 9002, 1, "RevealWiFi")) {
                if (in.readInt() == 1) {
                    this.location = (GlobalLocation) in.readParcelable(getClass().getClassLoader());
                }
                this.discoveryTime = in.readLong();
                this.SSID = in.readString();
                this.BSSID = in.readString();
            }
        }

        public void writeToParcel(Parcel out, int flags) {
            out.writeInt(9002);
            out.writeInt(1);
            if (this.location != null) {
                out.writeInt(1);
                out.writeParcelable(this.location, 0);
            } else {
                out.writeInt(0);
            }
            out.writeLong(this.discoveryTime);
            out.writeString(this.SSID);
            out.writeString(this.BSSID);
        }

        public int describeContents() {
            return hashCode();
        }

        public long getDiscoveryTime() {
            return this.discoveryTime;
        }

        public void setDiscoveryTime(long discoveryTime) {
            this.discoveryTime = discoveryTime;
        }

        public GlobalLocation getLocation() {
            return this.location;
        }

        public void setLocation(GlobalLocation location) {
            this.location = location;
        }

        public String getCurrentSSID() {
            return this.currentSSID;
        }

        public void setCurrentSSID(String currentSSID) {
            this.currentSSID = currentSSID;
        }

        public String getCurrentBSSID() {
            return this.currentBSSID;
        }

        public void setCurrentBSSID(String currentBSSID) {
            this.currentBSSID = currentBSSID;
        }

        public String getNotes() {
            return this.notes;
        }

        public void setNotes(String notes) {
            this.notes = notes;
        }
    }

    public enum ServiceType {
        SANDBOX,
        PRODUCTION
    }

    public interface UserLogListener {
        void logDevice(BluetoothItem bluetoothItem, PDUiBeacon pDUiBeacon);

        void logMessage(String str, String str2, String str3);
    }

    public static class Utils {
        @SuppressLint({"InlinedApi", "NewApi"})
        public static boolean isBluetoothLeSupported(Context context) {
            if (VERSION.SDK_INT < 18 || context == null) {
                return false;
            }
            PackageManager packageManager = context.getPackageManager();
            if (packageManager != null) {
                return packageManager.hasSystemFeature("android.hardware.bluetooth_le");
            }
            return false;
        }

        @SuppressLint({"InlinedApi", "NewApi"})
        public static BluetoothAdapter getBluetoothAdapter(Context context) {
            if (VERSION.SDK_INT < 18 || !isBluetoothLeSupported(context)) {
                return null;
            }
            return BluetoothAdapter.getDefaultAdapter();
        }

        public static boolean isBluetoothEnabled(Context context) {
            boolean enabled = false;
            try {
                if (Reveal.selfPermissionGranted(context, "android.permission.BLUETOOTH")) {
                    BluetoothAdapter adapter = getBluetoothAdapter(context);
                    if (adapter != null) {
                        enabled = adapter.isEnabled();
                    }
                }
            } catch (SecurityException e) {
                RevealLogger.m12430d("Bluetooth access denied (KNOX)");
            }
            return enabled;
        }

        public static int swap16(int source) {
            return ((source & 255) << 8) | ((MotionEventCompat.ACTION_POINTER_INDEX_MASK & source) >> 8);
        }

        public static String getJsonInitInfo(Context context) {
            JSONObject json = getBaseJsonObject(context);
            try {
                json.put(mf.org.apache.xerces.impl.Constants.LOCALE_PROPERTY, Locale.getDefault());
                if (Reveal.getInstance().locationService.getCurrentLocation(context) != null) {
                    JSONObject loc = getLocationJson(context, null);
                    if (loc != null) {
                        json.put("location", loc);
                    }
                } else {
                    RevealLogger.m12441w("Sending/info  response to the server without a location");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (context != null) {
                if (!Reveal.selfPermissionGranted(context, "android.permission.ACCESS_WIFI_STATE")) {
                    try {
                        json.put("currentSSID", "*** NO ACCESS_WIFI_STATE PERMISSION ***");
                    } catch (JSONException e2) {
                        e2.printStackTrace();
                    }
                } else if (isWiFi(context)) {
                    WifiInfo wifiInfo = ((WifiManager) context.getApplicationContext().getSystemService("wifi")).getConnectionInfo();
                    String ssid = wifiInfo.getSSID();
                    if (ssid.startsWith("\"") && ssid.endsWith("\"")) {
                        ssid = ssid.substring(1, ssid.length() - 1);
                    }
                    try {
                        if (wifiInfo.getSSID() != null) {
                            json.put("currentSSID", ssid);
                        }
                        if (wifiInfo.getBSSID() != null) {
                            json.put("currentBSSID", wifiInfo.getBSSID());
                        }
                    } catch (JSONException e22) {
                        e22.printStackTrace();
                    }
                }
            }
            return json.toString();
        }

        public static String getJsonAppInfo(Context context, List<String> appNames) {
            JSONObject json = new JSONObject();
            try {
                json.put("device_id", getDeviceId(context));
                json.put("app_ids", new JSONArray(appNames));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return json.toString();
        }

        public static String getJsonBatch(Context context, List<RevealEvent> events) {
            JSONObject json = getBaseJsonObject(context);
            JSONArray beacons = new JSONArray();
            JSONArray locations = new JSONArray();
            for (RevealEvent event : events) {
                EventType eventType = event.getEventType();
                switch (eventType) {
                    case BEACON:
                        JSONObject jbeacon = ((RevealBeacon) event).getJSON();
                        RevealLogger.m12430d("[NAME]='event' [TYPE]='JSON' [ID]='beacon'\n" + jbeacon);
                        beacons.put(jbeacon);
                        break;
                    case ENTER:
                        JSONObject jloc = ((RevealLocation) event).getJSON();
                        RevealLogger.m12430d("[NAME]='event' [TYPE]='JSON' [ID]='location'\n" + jloc);
                        locations.put(jloc);
                        break;
                    case WIFI_ENTER:
                        break;
                    default:
                        RevealLogger.m12441w("Unknown event type " + eventType + " encountered");
                        break;
                }
            }
            if (beacons.length() > 0) {
                try {
                    json.put(C1403a.f2090t, beacons);
                    Reveal.log("Adding " + beacons.length() + " beacons to batch", "STATE");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            if (locations.length() > 0) {
                try {
                    json.put("locations", locations);
                    Reveal.log("Adding " + locations.length() + " locations to batch", "STATE");
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
            }
            if (context != null) {
                if (!Reveal.selfPermissionGranted(context, "android.permission.ACCESS_WIFI_STATE")) {
                    try {
                        json.put("currentSSID", "*** NO ACCESS_WIFI_STATE PERMISSION ***");
                    } catch (JSONException e22) {
                        e22.printStackTrace();
                    }
                } else if (isWiFi(context)) {
                    String ssid = ((WifiManager) context.getApplicationContext().getSystemService("wifi")).getConnectionInfo().getSSID();
                    if (ssid.startsWith("\"") && ssid.endsWith("\"")) {
                        ssid.substring(1, ssid.length() - 1);
                    }
                }
            }
            return json.toString();
        }

        private static JSONObject getBaseJsonObject(Context context) {
            JSONObject json = new JSONObject();
            try {
                String timeZone = Calendar.getInstance().getTimeZone().getID();
                json.put("device_id", getDeviceId(context));
                json.put("os", getOsName());
                json.put("bluetooth_enabled", isBluetoothEnabled(context));
                json.put("supports_ble", isBluetoothLeSupported(context));
                json.put("con_type", connectionType(context));
                json.put("sdk_version", Reveal.getVersion());
                json.put("version", VERSION.RELEASE);
                json.put(mf.org.apache.xerces.impl.Constants.LOCALE_PROPERTY, context.getResources().getConfiguration().locale);
                json.put("device", Build.MANUFACTURER + " " + Build.MODEL);
                String appId = context.getApplicationInfo().packageName;
                if (appId != null) {
                    json.put("app_id", appId);
                }
                json.put("app_version", getAppVersion(context));
                json.put("time_zone", timeZone);
                String adId = AdUtils.getAdvertisingId(context);
                if (!TextUtils.isEmpty(adId)) {
                    json.put("idfa", adId);
                }
                if (Reveal.getInstance().getLocationSharingEnabled().booleanValue()) {
                    json.put("locationSharingEnabled", true);
                } else {
                    json.put("locationSharingEnabled", false);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            RevealLogger.m12430d("JSON:\n" + json);
            return json;
        }

        public static String connectionType(Context context) {
            String result = null;
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager != null) {
                if (ContextCompat.checkSelfPermission(context, "android.permission.ACCESS_NETWORK_STATE") == 0) {
                    NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
                    if (networkInfo != null && networkInfo.isAvailable()) {
                        result = networkInfo.getType() == 1 ? "wifi" : networkInfo.getType() == 0 ? "mobile" : networkInfo.getType() == 6 ? "wimax" : "unknown";
                    }
                } else {
                    result = "unknown";
                }
            }
            if (result != null) {
                RevealLogger.m12440v("NetworkType: " + result);
            }
            return result;
        }

        public static boolean magicCheck(Parcel in, int magic, int version, String name) {
            int storedType = in.readInt();
            if (storedType == magic) {
                int storedVersion = in.readInt();
                if (storedVersion == version) {
                    return true;
                }
                RevealLogger.m12441w("Encountered wrong version " + name + " expecting " + version + " not " + storedVersion);
                return false;
            }
            RevealLogger.m12441w("Parcel does not contain a valid " + name + " magic constant is " + storedType + " not " + magic);
            return false;
        }

        public static boolean isWiFi(Context context) {
            String type = connectionType(context);
            if (type == null || type.compareTo("wifi") != 0) {
                return false;
            }
            return true;
        }

        private static JSONObject getLocationJson(Context context, RevealBeacon beacon) {
            return getLocationJson(context, beacon, false);
        }

        private static JSONObject getLocationJson(Context context, RevealBeacon beacon, boolean includeDiscovery) {
            GlobalLocation location = null;
            if (beacon != null) {
                location = beacon.getLocation();
            }
            if (location == null) {
                location = new GlobalLocation(getLastKnownLocation(context));
            }
            return getLocationJson(context, location, includeDiscovery);
        }

        private static JSONObject getLocationJson(Context context, GlobalLocation location, boolean includeDiscovery) {
            JSONObject jsonObject = new JSONObject();
            if (location != null) {
                try {
                    double age = (((double) location.getTime()) / 1000.0d) - (((double) new Date().getTime()) / 1000.0d);
                    jsonObject.put(C1048i.ko, location.getLatitude());
                    jsonObject.put("lon", location.getLongitude());
                    jsonObject.put("time", System.currentTimeMillis() - location.getTime());
                    jsonObject.put("altitude", location.getAltitude());
                    jsonObject.put("accuracy", (double) location.getAccuracy());
                    jsonObject.put("provider", location.getProvider());
                    jsonObject.put("speed", location.getSpeed());
                    jsonObject.put("accuracy", (double) location.getAccuracy());
                    jsonObject.put("age", age);
                    if (!includeDiscovery) {
                        return jsonObject;
                    }
                    jsonObject.put("discoveryTime", location.getTime());
                    return jsonObject;
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        private static String getOsName() {
            if (isKindle()) {
                return "kindle";
            }
            return "android";
        }

        private static boolean isKindle() {
            return Build.MANUFACTURER.toUpperCase(Locale.US).equals("AMAZON") && (Build.MODEL.toUpperCase(Locale.US).startsWith("KF") || Build.MODEL.toUpperCase(Locale.US).contains("KINDLE"));
        }

        private static String getAppVersion(Context context) {
            try {
                return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
            } catch (NameNotFoundException e) {
                e.printStackTrace();
                return "unknown";
            }
        }

        public static String getAdvertisingId(Context context) {
            return AdUtils.getAdvertisingId(context);
        }

        public static String getDeviceId(Context context) {
            String deviceId = getAdvertisingId(context);
            if (deviceId == null) {
                return createDeviceIdMD5Hash(deviceId);
            }
            return deviceId;
        }

        public static String createDeviceIdMD5Hash(String inputString) {
            String deviceId = inputString;
            try {
                MessageDigest digest = MessageDigest.getInstance("MD5");
                digest.update(("android" + deviceId).getBytes());
                byte[] messageDigest = digest.digest();
                StringBuilder hexString = new StringBuilder();
                for (byte b : messageDigest) {
                    hexString.append(Integer.toHexString(b & 255));
                }
                deviceId = hexString.toString();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return deviceId;
        }

        public static Location getLastKnownLocation(Context context) {
            if (context != null && Reveal.selfPermissionGranted(context, "android.permission.ACCESS_FINE_LOCATION")) {
                LocationManager locationManager = (LocationManager) context.getSystemService("location");
                if (locationManager != null) {
                    Location lastLocation = locationManager.getLastKnownLocation("gps");
                    if (lastLocation == null) {
                        return locationManager.getLastKnownLocation("network");
                    }
                    return lastLocation;
                }
            }
            return null;
        }
    }

    public static synchronized Reveal getInstance() {
        Reveal reveal;
        synchronized (Reveal.class) {
            if (sharedInstance == null) {
                sharedInstance = new Reveal();
            }
            reveal = sharedInstance;
        }
        return reveal;
    }

    public static String getVersion() {
        return BuildConfig.VERSION_NAME;
    }

    public Reveal() {
        setStatus(STATUS_BLUETOOTH, 0, "Bluetooth state unknown");
        setStatus("location", 0, "Location state unknown");
        setStatus("network", 0, "Network state unknown");
        setStatus(STATUS_MEMORY, 1, "No memory issues");
        setStatus(STATUS_SCAN, 1, "Not currently scanning");
    }

    public String getDeviceState(Context context) {
        String text = "";
        if (context == null) {
            context = this.applicationContext;
        }
        text = (text + "\n  Version: " + getVersion()) + "\n  Version: " + getInstance().getAPIBaseURL();
        if (context != null) {
            if (selfPermissionGranted(context, "android.permission.ACCESS_WIFI_STATE") && Utils.isWiFi(context)) {
                WifiInfo wifiInfo = ((WifiManager) context.getApplicationContext().getSystemService("wifi")).getConnectionInfo();
                if (wifiInfo != null) {
                    text = text + "\n  Wifi: " + wifiInfo.getSSID();
                }
            }
            text = text + "\n  Permissions: ";
            if (selfPermissionGranted(context, "android.permission.ACCESS_FINE_LOCATION")) {
                text = text + "FINE ";
            }
            if (selfPermissionGranted(context, "android.permission.ACCESS_COARSE_LOCATION")) {
                text = text + "COARSE ";
            }
            if (selfPermissionGranted(context, "android.permission.ACCESS_WIFI_STATE")) {
                text = text + "WIFI ";
            }
            if (selfPermissionGranted(context, "android.permission.BLUETOOTH")) {
                text = text + "BLUETOOTH ";
            }
            if (selfPermissionGranted(context, "android.permission.BLUETOOTH_ADMIN")) {
                text = text + "BT-ADMIN ";
            }
            if (selfPermissionGranted(context, "android.permission.ACCESS_NETWORK_STATE")) {
                text = text + "WIFI ";
            }
        }
        text = text + "\n  Active beacons: " + this.dwellManager.getCount();
        for (RevealStatus status : this.statuses.values()) {
            text = text + "\n  Status: " + status.getName() + "=" + status.getStatus() + " - " + status.getMessage();
        }
        Runtime runtime = Runtime.getRuntime();
        long usedMemInMB = (runtime.totalMemory() - runtime.freeMemory()) / 1048576;
        long maxHeapSizeInMB = runtime.maxMemory() / 1048576;
        return "Device state:" + (text + "\n  System Memory used: " + usedMemInMB + "MB\n  heap size: " + maxHeapSizeInMB + "MB\n  Avail: " + (maxHeapSizeInMB - usedMemInMB) + "MB");
    }

    public RevealStatus getStatus(String whichStatus) {
        if (this.statuses.containsKey(whichStatus)) {
            return (RevealStatus) this.statuses.get(whichStatus);
        }
        return null;
    }

    public void setStatus(String whichStatus, int state, String message) {
        RevealStatus status = getStatus(whichStatus);
        if (status == null) {
            status = new RevealStatus();
            status.setName(whichStatus);
            this.statuses.put(whichStatus, status);
        }
        status.setStatus(state);
        status.setMessage(message);
    }

    public boolean isIdle() {
        if (this.applicationContext == null) {
            return false;
        }
        PowerManager pm = (PowerManager) this.applicationContext.getSystemService("power");
        if (VERSION.SDK_INT < 23 || pm == null) {
            return false;
        }
        return pm.isDeviceIdleMode();
    }

    protected void setupDwellManager() {
        this.dwellManager.setEventReadyListener(new C39981());
        this.eventCache.setBatchReadyListener(new C39992());
        loadFromPreferences();
        sendBatch(this.eventCache.events);
    }

    public void sendBatch(ArrayList<RevealEvent> events) {
        RevealLogger.m12430d("[IDLE-TEST] setBatchReady beaconService=" + this.beaconService);
        if (events.size() != 0) {
            int firstEventPositionToSend = 0;
            int lastEventPositionToSend = 0;
            do {
                lastEventPositionToSend += this.eventCache.maxCachedEvents - 1;
                if (events.size() < lastEventPositionToSend + 1) {
                    lastEventPositionToSend = events.size() - 1;
                }
                final List<RevealEvent> eventsToSend = events.subList(firstEventPositionToSend, lastEventPositionToSend);
                if (!eventsToSend.isEmpty()) {
                    log("Reveal sending batch of " + eventsToSend.size() + " events to the server", "COMM");
                    RevealNetworkClient.sendBatch(this.applicationContext, eventsToSend, new RevealNetworkClientCallback() {

                        class C40001 implements Runnable {
                            C40001() {
                            }

                            public void run() {
                                for (RevealEvent event : eventsToSend) {
                                    Reveal.getInstance().addDwellEvent(event);
                                }
                                Reveal.log("Rescheduled " + eventsToSend.size() + " events to be resent", "COMM");
                            }
                        }

                        public void onSuccess(JSONObject response) {
                            Reveal.log("Batch events successfully sent", "COMM");
                            if (response != null) {
                                JSONArray personasJSONArray = response.optJSONArray(Reveal.PERSONAS_PREFERENCE_KEY);
                                if (personasJSONArray != null) {
                                    Reveal.this.setPersonasWithJSON(personasJSONArray);
                                }
                            }
                            Reveal.this.storeInPreferences();
                        }

                        public void onFailure(String response) {
                            Reveal.log("Batch upload error " + response, RewardedVideoActivity.REQUEST_STATUS_PARAMETER_ERROR, "COMM");
                            Reveal.log("Rescheduling these events after " + Reveal.getInstance().retryDelayAfterTimeout + " seconds", "COMM");
                            if (response.toLowerCase(Locale.getDefault()).contains("timed out")) {
                                new Handler().postDelayed(new C40001(), Reveal.this.getRetryDelay());
                            }
                        }
                    });
                }
                firstEventPositionToSend += this.eventCache.maxCachedEvents;
            } while (events.size() > firstEventPositionToSend);
            storeInPreferences();
        }
    }

    public void storeInPreferences() {
        Editor editor = this.applicationContext.getSharedPreferences(DWELL_PREFS, 0).edit();
        editor.putString(DWELL_PREFS, this.dwellManager.getJSON(true).toString());
        editor.putString(CACHE_PREFS, this.eventCache.getJSON(true).toString());
        editor.apply();
    }

    public void loadFromPreferences() {
        SharedPreferences sharedPreferences = this.applicationContext.getSharedPreferences(DWELL_PREFS, 0);
        String dwellJson = sharedPreferences.getString(DWELL_PREFS, "{}");
        String cacheJson = sharedPreferences.getString(CACHE_PREFS, "{}");
        try {
            this.eventCache.setJSON(new JSONObject(cacheJson));
            log("Loaded cache events from last session: " + cacheJson, "STATE");
            this.dwellManager.setJSON(new JSONObject(dwellJson));
            log("Loaded dwell events from last session: " + dwellJson, "STATE");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public long getRetryDelay() {
        return this.retryDelayAfterTimeout * 1000;
    }

    public static final boolean selfPermissionGranted(Context context, String permission) {
        if (VERSION.SDK_INT >= 23) {
            return context.checkSelfPermission(permission) == 0;
        } else {
            return true;
        }
    }

    protected void onPermissionStateChange(Context context, String permission, boolean state) {
        log("Permission state for " + permission + " changed to " + state, "STATE in context: " + context);
    }

    protected void checkPermissionState(Context context) {
        if ((checkPermissionState(context, "android.permission.ACCESS_FINE_LOCATION") || checkPermissionState(context, "android.permission.ACCESS_COARSE_LOCATION")) && checkPermissionState(context, "android.permission.BLUETOOTH") && this.needRegisteration) {
            sendRegistrationIfPermitted(context);
        }
        checkPermissionState(context, "android.permission.BLUETOOTH_ADMIN");
    }

    protected boolean checkPermissionState(Context context, String permission) {
        boolean state = selfPermissionGranted(context, permission);
        boolean existingState = false;
        if (this.permissionStates.containsKey(permission)) {
            existingState = ((Boolean) this.permissionStates.get(permission)).booleanValue();
            this.permissionStates.remove(permission);
        }
        this.permissionStates.put(permission, Boolean.valueOf(state));
        if (existingState != state) {
            onPermissionStateChange(context, permission, state);
        }
        return state && !existingState;
    }

    public void start(Application application) {
        this.needRegisteration = true;
        RevealLogger.m12430d("STARTUP - Reveal.start() called");
        StringBuilder append = new StringBuilder().append("Reveal.Start\nVERSION=");
        getInstance();
        log(append.append(getVersion()).append("\nREVEAL_API_KEY=").append(this.apiKey).append("\nSERVER_URL=").append(getAPIBaseURL()).toString(), "INIT");
        log(getDeviceState(this.applicationContext), "INIT");
        if (application != null) {
            this.applicationContext = application.getApplicationContext();
            application.registerActivityLifecycleCallbacks(this);
        }
        try {
            boolean hasBluetooth = selfPermissionGranted(application, "android.permission.BLUETOOTH");
            IntentFilter filter = new IntentFilter();
            if (VERSION.SDK_INT >= 23) {
                filter.addAction("android.os.action.DEVICE_IDLE_MODE_CHANGED");
            }
            this.applicationContext.registerReceiver(this.dozeReciever, filter);
            this.defaultDistanceCalculator = new ModelSpecificDistanceCalculator(this.applicationContext, BeaconManager.getDistanceModelUpdateUrl());
            setDistanceCalculator(this.defaultDistanceCalculator);
            if (hasBluetooth) {
                setupDwellManager();
                this.startTime = new Date();
                sendRegistrationIfPermitted(application);
                getInstance().recordEvent("start");
                return;
            }
            log("Bluetooth access denied: No bluetooth", RewardedVideoActivity.REQUEST_STATUS_PARAMETER_ERROR, "STATE");
            throw new RuntimeException("You need to add BLUETOOTH permission to use this SDK");
        } catch (SecurityException e) {
            boolean isKNOXError = false;
            String message = e.getMessage();
            if (message != null && message.toLowerCase(Locale.getDefault()).contains("need bluetooth permission")) {
                isKNOXError = true;
            }
            if (isKNOXError) {
                log("Bluetooth access denied (KNOX)", RewardedVideoActivity.REQUEST_STATUS_PARAMETER_ERROR, "STATE");
            } else {
                log("Bluetooth access denied: " + e, RewardedVideoActivity.REQUEST_STATUS_PARAMETER_ERROR, "STATE");
                stop();
                throw e;
            }
        } finally {
            scheduleTimer(3000);
            log("Reveal SDK started", "STATE");
        }
    }

    private void scheduleTimer(final int intervalMS) {
        log("Scheduled background timer starting at " + intervalMS + "ms", "STATE");
        new Handler(Looper.getMainLooper()).post(new Runnable() {

            class C40021 extends TimerTask {
                C40021() {
                }

                public void run() {
                    int interval = intervalMS;
                    Reveal.this.checkPermissionState(Reveal.this.applicationContext);
                    if (interval < 30000) {
                        if (interval > 30000) {
                            interval = 30000;
                        }
                        Reveal.this.scheduleTimer((int) (((double) interval) * 1.1d));
                    }
                    int memoryWarning = Reveal.getInstance().getSimulateMemoryWarning();
                    if (memoryWarning == 0) {
                        RunningAppProcessInfo state = new RunningAppProcessInfo();
                        ActivityManager.getMyMemoryState(state);
                        memoryWarning = state.lastTrimLevel;
                    } else {
                        Reveal.log("Simulated memory warning " + memoryWarning, "WARNING", "STATE");
                    }
                    if (memoryWarning > 60) {
                        Reveal.getInstance().setStatus(Reveal.STATUS_MEMORY, 2, "Memory recovery in progress level: " + memoryWarning);
                    } else {
                        Reveal.getInstance().setStatus(Reveal.STATUS_MEMORY, 1, "No memory issues");
                    }
                    if (memoryWarning > 60) {
                        Reveal.getInstance().setSimulateMemoryWarning(0);
                        Reveal.log("Memory warning " + memoryWarning + " sending any unsaved packets to the server", "WARNING", "STATE");
                    }
                    Reveal.getInstance().dwellManager.processInProgress(Reveal.getInstance().getIncompleteBeaconSendTime());
                }
            }

            public void run() {
                if (Reveal.this.memoryCheckTimer != null) {
                    Reveal.this.memoryCheckTimer.cancel();
                    Reveal.this.memoryCheckTimer = null;
                }
                Reveal.this.memoryCheckTimer = new Timer();
                Reveal.this.memoryCheckTimerTask = new C40021();
                Reveal.this.memoryCheckTimer.scheduleAtFixedRate(Reveal.this.memoryCheckTimerTask, (long) intervalMS, (long) intervalMS);
            }
        });
    }

    public void restart(Context context) {
        try {
            this.needRegisteration = true;
            RevealLogger.m12430d("STARTUP - Reveal.restart() called");
            if (isStarted()) {
                boolean hasBluetooth = selfPermissionGranted(this.applicationContext, "android.permission.BLUETOOTH");
                this.defaultDistanceCalculator = new ModelSpecificDistanceCalculator(this.applicationContext, BeaconManager.getDistanceModelUpdateUrl());
                setDistanceCalculator(this.defaultDistanceCalculator);
                if (!(this.locationService == null || this.locationService.isLocationDiscoveryActive())) {
                    this.locationService.startLocationMonitoring(context);
                }
                if (hasBluetooth) {
                    RevealLogger.m12430d("Reveal.restart() has bluetooth permission");
                    sendRegistrationIfPermitted(this.applicationContext);
                    getInstance().recordEvent("restart");
                    return;
                }
                throw new RuntimeException("You need to add BLUETOOTH permission to use this SDK");
            }
            RevealLogger.m12441w("restart called before start has successfully completed");
            if (this.applicationContext != null) {
                start(null);
            }
        } catch (SecurityException e) {
            boolean isKNOXError = false;
            String message = e.getMessage();
            if (message != null && message.toLowerCase(Locale.getDefault()).contains("need bluetooth permission")) {
                isKNOXError = true;
            }
            if (isKNOXError) {
                RevealLogger.m12430d("Bluetooth access denied (KNOX)");
            } else {
                stop();
                throw e;
            }
        }
    }

    public void sendRegistrationIfPermitted(Context context) {
        if (this.needRegisteration) {
            String adId = AdUtils.getAdvertisingId(context);
            if (TextUtils.isEmpty(AdUtils.getAdvertisingId(context))) {
                getInstance().setStatus("location", 2, "No advertising ID provided");
            } else if (selfPermissionGranted(context, "android.permission.ACCESS_FINE_LOCATION") || selfPermissionGranted(context, "android.permission.ACCESS_COARSE_LOCATION")) {
                registerDevice(context);
            }
        }
    }

    public void stop() {
        if (this.applicationContext != null) {
            if (this.locationService != null) {
                this.locationService.stopLocationMonitoring(this.applicationContext);
            }
            if (this.beaconService != null) {
                this.beaconService.stopBeaconScanning(this.applicationContext);
            }
        }
    }

    public static String readVersionInfoInManifest(Object obj) {
        Package objPackage = obj.getClass().getPackage();
        if (objPackage == null) {
            return null;
        }
        return objPackage.getImplementationVersion() + BridgeUtil.SPLIT_MARK + objPackage.getName();
    }

    private void registerDevice(final Context context) {
        this.needRegisteration = false;
        if (context == null) {
            throw new RuntimeException("Application Context passed into Reveal must not be null");
        } else if (this.apiKey == null) {
            throw new RuntimeException("The Reveal SDK Requires an API Key to start");
        } else {
            AdUtils.setupAdvertisingId(context);
            if (this.locationService == null) {
                RevealLogger.m12430d("No LocationService injected, using default Reveal implementation");
                this.locationService = new RevealLocationService();
            } else {
                RevealLogger.m12430d("Using existing LocationService: " + this.locationService);
            }
            this.locationService.startLocationMonitoring(context);
            try {
                if (this.beaconService == null) {
                    RevealLogger.m12430d("No BeaconService injected, using default Reveal implementation (registerDevice)");
                    setBeaconService(new RevealBeaconService(context));
                } else {
                    RevealLogger.m12430d("Using existing beaconService: " + this.beaconService);
                }
                if (this.beaconService != null) {
                    this.beaconService.setupIfNeeded();
                }
            } catch (RuntimeException e) {
                log("registerDevice exception when setting beacon service:" + e, RewardedVideoActivity.REQUEST_STATUS_PARAMETER_ERROR, "STATE");
            }
            this.locationService.waitForValidLocation(new OnValidLocationCallback() {

                class C40041 implements RevealNetworkClientCallback {
                    C40041() {
                    }

                    public void onSuccess(JSONObject response) {
                        Reveal.log("Registration successful, starting up Reveal", "STATE");
                        RevealLogger.m12430d("waitForValidLocation OnSuccess beaconService: " + Reveal.this.beaconService);
                        if (response != null) {
                            JSONArray personasJSONArray = response.optJSONArray(Reveal.PERSONAS_PREFERENCE_KEY);
                            if (personasJSONArray != null) {
                                Reveal.this.setPersonasWithJSON(personasJSONArray);
                            }
                        }
                        if (Utils.isBluetoothLeSupported(context)) {
                            Boolean discoveryEnabled = null;
                            RevealLogger.m12430d("registerService waitingForLocation beaconService=" + Reveal.this.beaconService);
                            if (response != null) {
                                discoveryEnabled = Boolean.valueOf(response.optBoolean("discovery_enabled"));
                                Integer incompleteBeaconSendTime = Integer.valueOf(response.optInt("incompleteBeaconSendTime"));
                                if (incompleteBeaconSendTime.intValue() > 0) {
                                    Reveal.getInstance().setIncompleteBeaconSendTime((long) incompleteBeaconSendTime.intValue());
                                }
                            }
                            if (discoveryEnabled == null || !discoveryEnabled.booleanValue()) {
                                Reveal.log("Beacon scanning has been disabled from the server", "WARNING", "STATE");
                                Reveal.this.stopBeaconScanning(context);
                                return;
                            } else if (Reveal.this.isBeaconScanningEnabled.booleanValue()) {
                                RevealLogger.m12430d("Beacon scanning is enabled and available, starting up beacon scanning");
                                BeaconScanningProperties scanningProperties = new BeaconScanningProperties();
                                JSONArray securecastManufacturerCodes = response.optJSONArray("securecast_manufacturer_codes");
                                if (securecastManufacturerCodes != null) {
                                    RevealLogger.m12430d("Received " + securecastManufacturerCodes.length() + " codes from server to scan for");
                                    for (int i = 0; i < securecastManufacturerCodes.length(); i++) {
                                        try {
                                            String hexString = securecastManufacturerCodes.optString(i);
                                            RevealLogger.m12430d("Scanning for securecast beacons with manufacturer code " + hexString);
                                            scanningProperties.additionalSecureCastManufacturerCodes.add(Integer.valueOf(Integer.valueOf(hexString, 16).intValue()));
                                        } catch (NumberFormatException e) {
                                            RevealLogger.m12433e(e);
                                            e.printStackTrace();
                                        }
                                    }
                                }
                                scanningProperties.setScanInterval(Integer.valueOf(response.optInt("scan_interval")));
                                scanningProperties.setScanDuration(Integer.valueOf(response.optInt("scan_length")));
                                scanningProperties.setCacheTTL(((double) response.optInt("cache_ttl")) * 60.0d);
                                scanningProperties.setEddystoneTimeout(Integer.valueOf(response.optInt("eddystone_completion_timeout")));
                                if (scanningProperties.getEddystoneTimeout().intValue() < scanningProperties.getScanInterval().intValue()) {
                                    scanningProperties.setEddystoneTimeout(scanningProperties.getScanInterval());
                                }
                                if (response.has("location_fix_timeout")) {
                                    scanningProperties.setLocationTimeOut(response.optDouble("location_fix_timeout") * 60.0d);
                                    if (Reveal.this.getLocationService() != null) {
                                        Reveal.this.getLocationService().setLocationValidTime(response.optDouble("location_fix_timeout"));
                                    }
                                }
                                Integer batchSize = Integer.valueOf(response.optInt("batch_size"));
                                Reveal.getInstance().eventCache.setMaxCachedEvents(batchSize.intValue());
                                Reveal.getInstance().eventCache.setMaxCachedEventsOverrun(batchSize.intValue() * 5);
                                Reveal.getInstance().eventCache.setIdleTimeout((long) response.optInt("batch_timeout"));
                                Reveal.getInstance().setBatchBackgroundSend(response.optBoolean("batch_background_send"));
                                Double dwellTimeout = Double.valueOf(140.0d);
                                if (response.has("beacon_exit_time")) {
                                    dwellTimeout = Double.valueOf(response.optDouble("beacon_exit_time"));
                                }
                                Reveal.this.dwellManager.addEventType(EventType.BEACON, dwellTimeout);
                                Reveal.this.dwellManager.addEventType(EventType.ENTER, dwellTimeout);
                                Reveal.this.dwellManager.addEventType(EventType.EXIT, dwellTimeout);
                                Reveal.this.dwellManager.addEventType(EventType.LOCATION, Double.valueOf(0.0d));
                                if (!(Reveal.this.debugUUIDs == null || Reveal.this.debugUUIDs.isEmpty())) {
                                    scanningProperties.setDebugUUIDs(Reveal.this.debugUUIDs);
                                }
                                scanningProperties.setBackgroundEnabled(Reveal.this.isBackgroundScanningEnabled);
                                Reveal.this.startBeaconScanning(context, scanningProperties);
                                RevealLogger.m12430d("waitForValidLocation after scanning properties beaconService: " + Reveal.this.beaconService);
                                return;
                            } else {
                                Reveal.log("Beacon scanning has been disabled manually", "WARNING", "STATE");
                                return;
                            }
                        }
                        Reveal.log("Bluetooth LE is not supported on this device, so no beacon scanning will be done", "WARNING", "STATE");
                    }

                    public void onFailure(String response) {
                        Reveal.log("Error from server on device registration call: " + response, RewardedVideoActivity.REQUEST_STATUS_PARAMETER_ERROR, "STATE");
                    }
                }

                public void onLocationFound() {
                    RevealNetworkClient.registerDevice(context, new C40041());
                }
            });
        }
    }

    @TargetApi(23)
    private void startBeaconScanning(Context context, BeaconScanningProperties scanningProperties) {
        RevealLogger.m12430d("startBeaconScanning beaconService: " + this.beaconService);
        try {
            if (this.beaconService == null) {
                RevealLogger.m12430d("No BeaconService injected, using default Reveal implementation (startBeaconScanning)");
                setBeaconService(new RevealBeaconService(context));
                LocationService ls = getLocationService();
                if (!(ls == null || scanningProperties == null)) {
                    ls.setLocationValidTime(scanningProperties.getLocationTimeOut());
                }
            }
            this.beaconService.setupIfNeeded();
            if (VERSION.SDK_INT < 23) {
                context.registerReceiver(this.dozeReciever, new IntentFilter("android.os.action.DEVICE_IDLE_MODE_CHANGED"));
            }
            log("Start beacon scan", "STATE");
            this.beaconService.startBeaconScanning(context, scanningProperties);
        } catch (RuntimeException e) {
            log("registerDevice exception:" + e, RewardedVideoActivity.REQUEST_STATUS_PARAMETER_ERROR, "STATE");
        }
    }

    private void stopBeaconScanning(Context context) {
        RevealLogger.m12430d("stopBeaconScanning beaconService=" + this.beaconService);
        if (this.beaconService != null) {
            this.beaconService.stopBeaconScanning(context);
        }
    }

    public void sendDiscoveryOfBeacon(Context context, RevealBeacon beacon) {
        RevealLogger.m12430d("sendDiscoveryOfBeacon beaconService=" + this.beaconService);
        RevealLogger.m12440v("Encountered beacon " + beacon.getBeaconType() + " : " + beacon.getIdentifier());
        getInstance().recordEvent("All beacons");
        getInstance().recordEvent(beacon.beaconType + " beacon");
        this.dwellManager.addEvent(beacon);
        RevealLogger.m12430d("sendDiscoveryOfBeacon end beaconService=" + this.beaconService);
    }

    public boolean isStarted() {
        if (this.applicationContext == null || this.startTime == null) {
            return false;
        }
        return true;
    }

    public void addDwellEvent(RevealEvent eventObject) {
        this.dwellManager.addEvent(eventObject);
        if (eventObject.getEventType() == EventType.ENTER) {
            RevealLocation locationEvent = (RevealLocation) eventObject;
            OnLocationFoundListener listener = this.locationFoundListener;
            if (listener != null) {
                listener.onLocationDiscovered(locationEvent.location);
            }
        }
    }

    public void addEvent(Context context, RevealEvent eventObject) {
        RevealLogger.m12430d("addEvent beaconService=" + this.beaconService);
        if (context != null && selfPermissionGranted(context, "android.permission.ACCESS_WIFI_STATE") && Utils.isWiFi(context)) {
            WifiInfo wifiInfo = ((WifiManager) context.getApplicationContext().getSystemService("wifi")).getConnectionInfo();
            if (wifiInfo != null) {
                if (eventObject.getCurrentSSID() == null) {
                    String ssid = wifiInfo.getSSID();
                    if (ssid.startsWith("\"") && ssid.endsWith("\"")) {
                        ssid = ssid.substring(1, ssid.length() - 1);
                    }
                    eventObject.setCurrentSSID(ssid);
                }
                if (eventObject.getCurrentBSSID() == null) {
                    eventObject.setCurrentBSSID(wifiInfo.getBSSID());
                }
            }
        }
        this.eventCache.addEvent(eventObject);
    }

    public void recordEvent(String eventName) {
        recordEvent(eventName, true);
    }

    public void recordEvent(String eventName, boolean success) {
        recordEvent(eventName, success, Integer.valueOf(1));
    }

    public void recordEvent(String eventName, boolean success, Integer count) {
        if (getInstance().getIsDebug().booleanValue()) {
            ConcurrentHashMap<String, Integer> choice;
            Integer total = Integer.valueOf(0);
            String name = eventName;
            if (isInBackGround()) {
                name = name + " (background)";
            }
            if (success) {
                choice = this.successStats;
            } else {
                choice = this.failureStats;
            }
            if (choice.containsKey(name)) {
                total = (Integer) choice.get(name);
                choice.remove(name);
            }
            if (total != null) {
                choice.put(name, Integer.valueOf(total.intValue() + count.intValue()));
            }
        }
    }

    public static void log(Object message) {
        log(message, "DEBUG");
    }

    public static void log(Object message, String group) {
        log(message, "DEBUG", group);
    }

    public static void log(Object message, String type, String group) {
        String text = message.toString();
        if (getInstance().logger != null) {
            getInstance().logger.logMessage(text, type, group);
        }
        text = "[" + group + "] " + text;
        if (type.compareToIgnoreCase("verbose") == 0) {
            RevealLogger.m12440v(text);
        } else if (type.compareToIgnoreCase("error") == 0) {
            RevealLogger.m12433e(text);
        } else if (type.compareToIgnoreCase("warning") == 0) {
            RevealLogger.m12441w(text);
        } else {
            RevealLogger.m12430d(text);
        }
    }

    public static void logMemoryUsage(String label) {
        Runtime runtime = Runtime.getRuntime();
        long usedMemInMB = (runtime.totalMemory() - runtime.freeMemory()) / 1048576;
        long maxHeapSizeInMB = runtime.maxMemory() / 1048576;
        log(label + " System Memory - used: " + usedMemInMB + "MB, heap size: " + maxHeapSizeInMB + "MB Avail: " + (maxHeapSizeInMB - usedMemInMB) + "MB", "STATE");
    }

    public void onActivityResumed(Activity activity) {
        RevealLogger.m12430d("Resuming Activity");
    }

    public void onActivityPaused(Activity activity) {
        if (!activity.isChangingConfigurations()) {
            final WeakReference<Activity> ref = new WeakReference(activity);
            Handler handler = this.handler;
            Runnable c40066 = new Runnable() {
                public void run() {
                    Reveal.this.onActivityCeased((Activity) ref.get());
                }
            };
            this.check = c40066;
            handler.postDelayed(c40066, CHECK_DELAY);
        }
    }

    public void onActivityStarted(Activity activity) {
        this.current = activity;
        if (this.check != null) {
            this.handler.removeCallbacks(this.check);
        }
        if (!this.foreground) {
            if (!(activity == null || activity.isChangingConfigurations())) {
                this.foreground = true;
                log("Application is now in the foreground", "STATE");
            }
            if (this.foreground && getInstance().isStarted()) {
                getInstance().restart(this.applicationContext);
            }
        }
    }

    public void onActivityStopped(Activity activity) {
        if (this.check != null) {
            this.handler.removeCallbacks(this.check);
        }
        onActivityCeased(activity);
    }

    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
    }

    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
    }

    public void onActivityDestroyed(Activity activity) {
    }

    private void onActivityCeased(Activity activity) {
        if (this.foreground && activity == this.current && activity != null && !activity.isChangingConfigurations()) {
            this.foreground = false;
            log("Application is now in the background", "STATE");
        }
    }

    private void setPersonasWithJSON(JSONArray personasJSONArray) {
        RevealLogger.m12430d("New Personas: " + personasJSONArray.toString());
        List<String> personas = new ArrayList();
        for (int i = 0; i < personasJSONArray.length(); i++) {
            personas.add(personasJSONArray.optString(i));
        }
        this.personas = personas;
    }

    public void setAPIKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public void setServiceType(ServiceType serviceType) {
        if (serviceType == ServiceType.SANDBOX) {
            this.apiBaseURL = REVEAL_API_BASE_SANDBOX;
        } else {
            this.apiBaseURL = REVEAL_API_BASE_PRODUCTION;
        }
    }

    public void setAPIEndpointBase(String apiEndpointBase) {
        RevealLogger.m12441w("Setting endpoint base to " + apiEndpointBase + " - only for specific installations");
        this.apiBaseURL = apiEndpointBase;
    }

    public String getAPIBaseURL() {
        return this.apiBaseURL;
    }

    public void setDebug(Boolean isDebug) {
        this.isDebug = isDebug;
    }

    public void setDebugUUIDs(List<String> debugUUIDs) {
        this.debugUUIDs = debugUUIDs;
    }

    public List<String> getPersonas() {
        return this.personas;
    }

    public String getAPIKey() {
        return this.apiKey;
    }

    public Boolean getIsDebug() {
        return this.isDebug;
    }

    public BeaconService getBeaconService() {
        return this.beaconService;
    }

    public void setBeaconService(BeaconService beaconService) {
        this.beaconService = beaconService;
    }

    public LocationService getLocationService() {
        return this.locationService;
    }

    public void setLocationService(LocationService locationService) {
        this.locationService = locationService;
    }

    public Boolean getLocationSharingEnabled() {
        return this.locationSharingEnabled;
    }

    public void setLocationSharingEnabled(Boolean locationSharingEnabled) {
        this.locationSharingEnabled = locationSharingEnabled;
    }

    public Boolean getIsBackgroundScanningEnabled() {
        return this.isBackgroundScanningEnabled;
    }

    public void setIsBackgroundScanningEnabled(Boolean backgroundScanningEnabled) {
        this.isBackgroundScanningEnabled = backgroundScanningEnabled;
    }

    public List<String> getBeaconDescriptions() {
        ArrayList<String> result = new ArrayList();
        if (this.beaconService != null) {
            List<RevealBeacon> beacons = this.beaconService.getBeacons();
            if (beacons != null) {
                for (RevealBeacon beacon : beacons) {
                    result.add(beacon.toString());
                }
            }
        }
        RevealLogger.m12440v("Found " + result.size() + " beacons");
        return result;
    }

    public boolean isBatchBackgroundSend() {
        return this.batchBackgroundSend;
    }

    public void setBatchBackgroundSend(boolean batchBackgroundSend) {
        this.batchBackgroundSend = batchBackgroundSend;
    }

    public boolean isInBackGround() {
        return !this.foreground;
    }

    public UserLogListener getLogger() {
        return this.logger;
    }

    public void setLogger(UserLogListener logger) {
        this.logger = logger;
    }

    public ConcurrentHashMap<String, Integer> getSuccessStats() {
        return this.successStats;
    }

    public void setSuccessStats(ConcurrentHashMap<String, Integer> successStats) {
        this.successStats = successStats;
    }

    public ConcurrentHashMap<String, Integer> getFailureStats() {
        return this.failureStats;
    }

    public void setFailureStats(ConcurrentHashMap<String, Integer> failureStats) {
        this.failureStats = failureStats;
    }

    public Date getStartTime() {
        return this.startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public RevealDwellManager getDwellManager() {
        return this.dwellManager;
    }

    public DistanceCalculator getDistanceCalculator() {
        return this.distanceCalculator;
    }

    public void setDistanceCalculator(DistanceCalculator distanceCalculator) {
        this.distanceCalculator = distanceCalculator;
    }

    public OnLocationFoundListener getLocationFoundListener() {
        return this.locationFoundListener;
    }

    public void setLocationFoundListener(OnLocationFoundListener locationFoundListener) {
        this.locationFoundListener = locationFoundListener;
    }

    public long getIncompleteBeaconSendTime() {
        return this.incompleteBeaconSendTime;
    }

    public void setIncompleteBeaconSendTime(long incompleteBeaconSendTime) {
        this.incompleteBeaconSendTime = incompleteBeaconSendTime;
    }

    public int getSimulateMemoryWarning() {
        return this.simulateMemoryWarning;
    }

    public void setSimulateMemoryWarning(int simulateMemoryWarning) {
        this.simulateMemoryWarning = simulateMemoryWarning;
    }

    public Context getApplicationContext() {
        return this.applicationContext;
    }
}
