package com.inmobi.signals;

import android.annotation.TargetApi;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.provider.Settings.Secure;
import android.provider.Settings.SettingNotFoundException;
import com.inmobi.commons.core.p115d.C3110g;
import com.inmobi.commons.core.p115d.C3132b;
import com.inmobi.commons.core.p115d.C3135c;
import com.inmobi.commons.core.utilities.C3156e;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.commons.core.utilities.info.C3163e;
import com.inmobi.commons.p112a.C3105a;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

public class LocationInfo implements LocationListener {
    private static final String f8152a = LocationInfo.class.getSimpleName();
    private static LocationInfo f8153b;
    private static Object f8154c = new Object();
    private static boolean f8155d = false;
    private static LocationManager f8156e;
    private static Object f8157f = null;
    private static C3244a f8158g = null;
    private static boolean f8159h = false;

    public enum LocationConsentStatus {
        AUTHORISED,
        DENIED,
        UNDETERMINED
    }

    private static class C3244a implements InvocationHandler {
        private C3244a() {
        }

        public void m10811a(Bundle bundle) {
            Logger.m10359a(InternalLogLevel.INTERNAL, LocationInfo.f8152a, "Successfully connected to Google API client.");
            LocationInfo.f8159h = true;
        }

        public void m10810a(int i) {
            LocationInfo.f8159h = false;
            Logger.m10359a(InternalLogLevel.INTERNAL, LocationInfo.f8152a, "Google API client connection suspended");
        }

        public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
            String str = "onConnected";
            str = "onConnectionSuspended";
            if (objArr != null) {
                if (method.getName().equals("onConnected")) {
                    m10811a((Bundle) objArr[0]);
                    return null;
                } else if (method.getName().equals("onConnectionSuspended")) {
                    m10810a(((Integer) objArr[0]).intValue());
                    return null;
                }
            }
            return method.invoke(this, objArr);
        }
    }

    public static LocationInfo m10813a() {
        LocationInfo locationInfo = f8153b;
        if (locationInfo == null) {
            synchronized (f8154c) {
                locationInfo = f8153b;
                if (locationInfo == null) {
                    f8153b = new LocationInfo();
                    locationInfo = f8153b;
                }
            }
        }
        return locationInfo;
    }

    private LocationInfo() {
        f8156e = (LocationManager) C3105a.m10078b().getSystemService("location");
    }

    void m10828b() {
        try {
            if (f8155d && C3267h.m10943a()) {
                m10816a(C3105a.m10078b());
            }
            m10830d();
        } catch (Exception e) {
            Logger.m10359a(InternalLogLevel.INTERNAL, f8152a, "SDK encountered unexpected error in initializing location collection; " + e.getMessage());
        }
    }

    void m10829c() {
        if (f8155d && m10820i() && m10822k() && f8156e != null) {
            f8156e.removeUpdates(this);
        }
    }

    private boolean m10820i() {
        try {
            if (C3156e.m10410a(C3105a.m10078b(), "signals", "android.permission.ACCESS_FINE_LOCATION") || C3156e.m10410a(C3105a.m10078b(), "signals", "android.permission.ACCESS_COARSE_LOCATION")) {
                return true;
            }
            return false;
        } catch (Exception e) {
            Logger.m10359a(InternalLogLevel.INTERNAL, f8152a, "SDK encountered unexpected error checking location permission; will assume it is not granted");
            return false;
        }
    }

    public void m10830d() {
        if (f8155d && m10820i() && m10822k() && f8156e != null) {
            Criteria criteria = new Criteria();
            criteria.setBearingAccuracy(2);
            criteria.setPowerRequirement(2);
            criteria.setCostAllowed(false);
            String bestProvider = f8156e.getBestProvider(criteria, true);
            if (bestProvider != null) {
                Logger.m10359a(InternalLogLevel.INTERNAL, f8152a, "Trying to get location fix. Provider being used:" + bestProvider);
                f8156e.requestSingleUpdate(bestProvider, this, null);
                return;
            }
            Logger.m10359a(InternalLogLevel.INTERNAL, f8152a, "No enabled providers found matching the supplied criteria");
            Logger.m10359a(InternalLogLevel.INTERNAL, f8152a, "Skipping the location fix");
        }
    }

    public void onLocationChanged(Location location) {
        if (location != null) {
            try {
                Logger.m10359a(InternalLogLevel.INTERNAL, f8152a, "location changed. ts:" + location.getTime() + " lat:" + location.getLatitude() + ":" + location.getLongitude() + " accu:" + location.getAccuracy());
            } catch (Throwable e) {
                Logger.m10360a(InternalLogLevel.INTERNAL, f8152a, "Exception in onLocationChanged", e);
                C3135c.m10255a().m10279a(new C3132b(e));
                return;
            }
        }
        if (m10820i()) {
            f8156e.removeUpdates(this);
        }
    }

    public void onStatusChanged(String str, int i, Bundle bundle) {
    }

    public void onProviderEnabled(String str) {
    }

    public void onProviderDisabled(String str) {
    }

    private void m10816a(Context context) {
        if (f8157f == null) {
            Logger.m10359a(InternalLogLevel.INTERNAL, f8152a, "Connecting Google API client for location.");
            f8158g = new C3244a();
            f8157f = C3267h.m10941a(context, f8158g, f8158g, "com.google.android.gms.location.LocationServices");
            C3267h.m10942a(f8157f);
        }
    }

    public synchronized HashMap<String, Object> m10831e() {
        return m10815a(m10823l(), true);
    }

    public HashMap<String, String> m10832f() {
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put("loc-consent-status", m10821j().toString().toLowerCase(Locale.ENGLISH));
        return hashMap;
    }

    private LocationConsentStatus m10821j() {
        if (!m10820i()) {
            return LocationConsentStatus.DENIED;
        }
        if (m10822k()) {
            return LocationConsentStatus.AUTHORISED;
        }
        return LocationConsentStatus.DENIED;
    }

    public synchronized HashMap<String, String> m10833g() {
        HashMap<String, String> hashMap;
        hashMap = new HashMap();
        Location l = m10823l();
        HashMap a;
        if (l != null) {
            a = m10815a(l, true);
        } else {
            a = m10815a(C3163e.m10460f(), false);
        }
        for (Entry entry : r0.entrySet()) {
            hashMap.put(entry.getKey(), entry.getValue().toString());
        }
        return hashMap;
    }

    public void m10827a(boolean z) {
        f8155d = z;
    }

    @TargetApi(19)
    private boolean m10822k() {
        boolean z = true;
        Context b = C3105a.m10078b();
        if (b == null) {
            return false;
        }
        if (VERSION.SDK_INT >= 19) {
            int i;
            try {
                i = Secure.getInt(b.getContentResolver(), "location_mode");
            } catch (SettingNotFoundException e) {
                i = 0;
            }
            if (i == 0) {
                z = false;
            }
            return z;
        } else if (f8156e == null) {
            return false;
        } else {
            boolean z2;
            boolean isProviderEnabled;
            if (C3156e.m10410a(b, "signals", "android.permission.ACCESS_FINE_LOCATION")) {
                isProviderEnabled = f8156e.isProviderEnabled("gps");
                z2 = false;
            } else if (C3156e.m10410a(b, "signals", "android.permission.ACCESS_COARSE_LOCATION")) {
                z2 = f8156e.isProviderEnabled("network");
                isProviderEnabled = false;
            } else {
                isProviderEnabled = false;
                z2 = false;
            }
            if (z2 || r2) {
                return true;
            }
            return false;
        }
    }

    private Location m10823l() {
        Exception e;
        Location location = null;
        Location n;
        try {
            if (f8155d && m10822k()) {
                if (f8159h) {
                    n = m10825n();
                } else {
                    n = null;
                }
                try {
                    if (f8156e != null) {
                        location = m10824m();
                    }
                } catch (Exception e2) {
                    e = e2;
                    Logger.m10359a(InternalLogLevel.INTERNAL, f8152a, "SDK encountered unexpected error in getting a location fix; " + e.getMessage());
                    return m10812a(n, location);
                }
                return m10812a(n, location);
            }
            n = null;
            return m10812a(n, location);
        } catch (Exception e3) {
            e = e3;
            n = null;
            Logger.m10359a(InternalLogLevel.INTERNAL, f8152a, "SDK encountered unexpected error in getting a location fix; " + e.getMessage());
            return m10812a(n, location);
        }
    }

    private Location m10812a(Location location, Location location2) {
        if (location != null || location2 != null) {
            return m10817b(location, location2);
        }
        Logger.m10359a(InternalLogLevel.INTERNAL, f8152a, "Location info not available by any client:");
        try {
            C3135c.m10255a().m10279a(new C3110g("signals", "LocationFixFailed"));
        } catch (Exception e) {
            Logger.m10359a(InternalLogLevel.INTERNAL, f8152a, "Error in submitting telemetry event : (" + e.getMessage() + ")");
        }
        return null;
    }

    private Location m10817b(Location location, Location location2) {
        Object obj = 1;
        if (location == null) {
            Logger.m10359a(InternalLogLevel.INTERNAL, f8152a, "Location info provided by Android Api client:" + location2 + " ts : " + location2.getTime());
            return location2;
        } else if (location2 == null) {
            Logger.m10359a(InternalLogLevel.INTERNAL, f8152a, "Location info provided by Google Api client:" + location + " ts : " + location.getTime());
            return location;
        } else {
            Object obj2;
            long time = location.getTime() - location2.getTime();
            Object obj3 = time > 120000 ? 1 : null;
            if (time < -120000) {
                obj2 = 1;
            } else {
                obj2 = null;
            }
            Object obj4;
            if (time > 0) {
                obj4 = 1;
            } else {
                obj4 = null;
            }
            if (obj3 != null) {
                Logger.m10359a(InternalLogLevel.INTERNAL, f8152a, "Location info provided by Google Api client:" + location + " ts : " + location.getTime());
                return location;
            } else if (obj2 != null) {
                Logger.m10359a(InternalLogLevel.INTERNAL, f8152a, "Location info provided by Android Api client:" + location2 + " ts : " + location2.getTime());
                return location2;
            } else {
                int accuracy = (int) (location.getAccuracy() - location2.getAccuracy());
                obj3 = accuracy > 0 ? 1 : null;
                if (accuracy < 0) {
                    obj2 = 1;
                } else {
                    obj2 = null;
                }
                if (accuracy <= 200) {
                    obj = null;
                }
                if (obj2 != null || (r4 != null && (obj3 == null || r0 == null))) {
                    Logger.m10359a(InternalLogLevel.INTERNAL, f8152a, "Location info provided by Google Api client:" + location + " ts : " + location.getTime());
                    return location;
                }
                Logger.m10359a(InternalLogLevel.INTERNAL, f8152a, "Location info provided by Android Api client:" + location2 + " ts : " + location2.getTime());
                return location2;
            }
        }
    }

    private Location m10824m() {
        boolean z = true;
        Location location = null;
        Criteria criteria = new Criteria();
        if (C3156e.m10410a(C3105a.m10078b(), "signals", "android.permission.ACCESS_FINE_LOCATION")) {
            criteria.setAccuracy(1);
        } else if (C3156e.m10410a(C3105a.m10078b(), "signals", "android.permission.ACCESS_COARSE_LOCATION")) {
            criteria.setAccuracy(2);
        }
        criteria.setCostAllowed(false);
        String bestProvider = f8156e.getBestProvider(criteria, true);
        if (bestProvider != null) {
            try {
                location = f8156e.getLastKnownLocation(bestProvider);
            } catch (Exception e) {
                try {
                    Logger.m10359a(InternalLogLevel.INTERNAL, f8152a, "Failed to acquire a location fix; access seems to be disabled");
                    Map hashMap = new HashMap();
                    hashMap.put("type", "SecurityException");
                    hashMap.put("message", e.getMessage() + "");
                    C3135c.m10255a().m10280a("signals", "ExceptionCaught", hashMap);
                } catch (Exception e2) {
                    Logger.m10359a(InternalLogLevel.INTERNAL, f8152a, "Error in submitting telemetry event : (" + e2.getMessage() + ")");
                }
            }
            if (location == null) {
                location = m10826o();
            }
        }
        InternalLogLevel internalLogLevel = InternalLogLevel.INTERNAL;
        String str = f8152a;
        StringBuilder append = new StringBuilder().append("Location info provided by Location manager:");
        if (location == null) {
            z = false;
        }
        Logger.m10359a(internalLogLevel, str, append.append(z).toString());
        return location;
    }

    private Location m10825n() {
        try {
            Field declaredField = Class.forName("com.google.android.gms.location.LocationServices").getDeclaredField("FusedLocationApi");
            Class cls = Class.forName("com.google.android.gms.common.api.GoogleApiClient");
            return (Location) Class.forName("com.google.android.gms.location.FusedLocationProviderApi").getMethod("getLastLocation", new Class[]{cls}).invoke(declaredField.get(null), new Object[]{f8157f});
        } catch (Throwable e) {
            Logger.m10360a(InternalLogLevel.INTERNAL, f8152a, "Unable to request activity updates from ActivityRecognition client", e);
            return null;
        } catch (Throwable e2) {
            Logger.m10360a(InternalLogLevel.INTERNAL, f8152a, "Unable to request activity updates from ActivityRecognition client", e2);
            return null;
        } catch (Throwable e22) {
            Logger.m10360a(InternalLogLevel.INTERNAL, f8152a, "Unable to request activity updates from ActivityRecognition client", e22);
            return null;
        } catch (Throwable e222) {
            Logger.m10360a(InternalLogLevel.INTERNAL, f8152a, "Unable to request activity updates from ActivityRecognition client", e222);
            return null;
        } catch (Throwable e2222) {
            Logger.m10360a(InternalLogLevel.INTERNAL, f8152a, "Unable to request activity updates from ActivityRecognition client", e2222);
            return null;
        }
    }

    private Location m10826o() {
        if (f8156e == null) {
            return null;
        }
        List providers = f8156e.getProviders(true);
        int size = providers.size() - 1;
        Location location = null;
        while (size >= 0) {
            Location lastKnownLocation;
            String str = (String) providers.get(size);
            if (f8156e.isProviderEnabled(str)) {
                try {
                    lastKnownLocation = f8156e.getLastKnownLocation(str);
                } catch (SecurityException e) {
                    Logger.m10359a(InternalLogLevel.INTERNAL, f8152a, "Failed to acquire a location fix; access seems to be disabled");
                    try {
                        Map hashMap = new HashMap();
                        hashMap.put("type", "SecurityException");
                        hashMap.put("message", e.getMessage() + "");
                        C3135c.m10255a().m10280a("signals", "ExceptionCaught", hashMap);
                        lastKnownLocation = location;
                    } catch (Exception e2) {
                        Logger.m10359a(InternalLogLevel.INTERNAL, f8152a, "Error in submitting telemetry event : (" + e2.getMessage() + ")");
                        lastKnownLocation = location;
                    }
                }
                if (lastKnownLocation != null) {
                    return lastKnownLocation;
                }
            } else {
                lastKnownLocation = location;
            }
            size--;
            location = lastKnownLocation;
        }
        return location;
    }

    private HashMap<String, Object> m10815a(Location location, boolean z) {
        int i = 1;
        HashMap<String, Object> hashMap = new HashMap();
        Context b = C3105a.m10078b();
        if (b == null) {
            return hashMap;
        }
        if (location != null) {
            if (location.getTime() > 0) {
                hashMap.put("u-ll-ts", Long.valueOf(location.getTime()));
            }
            hashMap.put("u-latlong-accu", m10814a(location));
            hashMap.put("sdk-collected", Integer.valueOf(z ? 1 : 0));
        }
        if (f8155d) {
            String str = "loc-allowed";
            if (!m10822k()) {
                i = 0;
            }
            hashMap.put(str, Integer.valueOf(i));
        }
        if (m10822k() && m10820i()) {
            if (C3156e.m10410a(b, "signals", "android.permission.ACCESS_COARSE_LOCATION")) {
                hashMap.put("loc-granularity", "coarse");
            }
            if (C3156e.m10410a(b, "signals", "android.permission.ACCESS_FINE_LOCATION")) {
                hashMap.put("loc-granularity", "fine");
            }
        } else {
            hashMap.put("loc-granularity", "none");
        }
        return hashMap;
    }

    private String m10814a(Location location) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(location.getLatitude());
        stringBuilder.append(",");
        stringBuilder.append(location.getLongitude());
        stringBuilder.append(",");
        stringBuilder.append((int) location.getAccuracy());
        return stringBuilder.toString();
    }
}
