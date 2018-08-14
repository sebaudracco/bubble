package com.bgjd.ici.p029d;

import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Looper;
import com.bgjd.ici.json.JSONException;
import com.bgjd.ici.json.JSONObject;
import com.bgjd.ici.p025b.C1395h;
import com.bgjd.ici.p025b.C1402i;
import com.bgjd.ici.p025b.C1408j.C1407c.C1406b;
import com.bgjd.ici.p025b.C1409k;
import com.bgjd.ici.p025b.ac;
import com.mobfox.sdk.networking.RequestParams;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Locale;

public class C1460k extends C1409k<JSONObject> implements LocationListener {
    private static final String f2302f = "LOC";
    private boolean f2303g;
    private Location f2304h;

    public /* synthetic */ Object mo2325d() {
        return m3024f();
    }

    public C1460k(C1395h c1395h) {
        super(c1395h);
        this.f2303g = false;
        this.f2304h = null;
        this.b = C1406b.f2156f;
    }

    public JSONObject m3024f() {
        Object obj;
        JSONException e;
        Object obj2;
        Object obj3;
        C1468o c1468o;
        int i;
        Object obj4;
        LocationManager locationManager;
        float longitude;
        float longitude2;
        String bestProvider;
        int i2;
        JSONObject jSONObject;
        double latitude;
        double longitude3;
        float latitude2;
        float longitude4;
        JSONObject jSONObject2;
        List fromLocation;
        JSONObject jSONObject3;
        Address address;
        float f;
        Location location;
        Object obj5 = null;
        try {
            Object obj6;
            if (this.a.getSupportedFeatures().has("location")) {
                int i3;
                int i4;
                switch (this.a.getSupportedFeatures().getInt("location")) {
                    case 4:
                        i3 = 1;
                        obj6 = null;
                        break;
                    case 5:
                        obj = null;
                        i4 = 1;
                        break;
                    case 6:
                        i3 = 1;
                        i4 = 1;
                        break;
                    default:
                        obj = null;
                        obj6 = null;
                        break;
                }
            }
            obj = null;
            obj6 = null;
            if (obj == null || !ac.m2862a(this.a.getContext(), "android.permission.ACCESS_FINE_LOCATION")) {
                obj = null;
            } else {
                obj = 1;
            }
            if (obj6 != null) {
                try {
                    if (ac.m2862a(this.a.getContext(), "android.permission.ACCESS_COARSE_LOCATION")) {
                        obj5 = 1;
                    }
                } catch (JSONException e2) {
                    e = e2;
                    e.printStackTrace();
                    obj2 = null;
                    obj3 = obj;
                    if (m3022g()) {
                        obj = null;
                    } else {
                        c1468o = new C1468o(this.a.getLatitude(), this.a.getLongitude());
                        try {
                            c1468o.m3042a(this.a.getContext());
                            i = 0;
                            while (!c1468o.m3043a()) {
                                Thread.sleep(500);
                                i++;
                                if (i >= 70) {
                                    i = 0;
                                    while (!c1468o.m3045c()) {
                                        Thread.sleep(500);
                                        i++;
                                        if (i >= 70) {
                                            this.f2304h = c1468o.m3046d();
                                            if (this.f2304h != null) {
                                                obj5 = 1;
                                                c1468o.m3047e();
                                                obj = obj5;
                                            }
                                            obj5 = null;
                                            c1468o.m3047e();
                                            obj = obj5;
                                        }
                                    }
                                    this.f2304h = c1468o.m3046d();
                                    if (this.f2304h != null) {
                                        obj5 = 1;
                                        c1468o.m3047e();
                                        obj = obj5;
                                    }
                                    obj5 = null;
                                    c1468o.m3047e();
                                    obj = obj5;
                                }
                            }
                            i = 0;
                            while (!c1468o.m3045c()) {
                                Thread.sleep(500);
                                i++;
                                if (i >= 70) {
                                    this.f2304h = c1468o.m3046d();
                                    if (this.f2304h != null) {
                                        obj5 = 1;
                                        c1468o.m3047e();
                                        obj = obj5;
                                    }
                                    obj5 = null;
                                    c1468o.m3047e();
                                    obj = obj5;
                                }
                            }
                            this.f2304h = c1468o.m3046d();
                            if (this.f2304h != null) {
                                obj5 = 1;
                                c1468o.m3047e();
                                obj = obj5;
                            }
                            obj5 = null;
                            try {
                                c1468o.m3047e();
                                obj = obj5;
                            } catch (Exception e3) {
                                obj = obj5;
                                if (this.f2304h != null) {
                                    obj4 = obj;
                                } else {
                                    locationManager = (LocationManager) this.a.getContext().getSystemService("location");
                                    this.f2304h = locationManager.getLastKnownLocation("gps");
                                    if (this.f2304h != null) {
                                        longitude = (float) this.f2304h.getLongitude();
                                        this.f2304h = null;
                                    }
                                    if (this.f2304h != null) {
                                        obj4 = 1;
                                        this.f2304h = locationManager.getLastKnownLocation("network");
                                        if (this.f2304h != null) {
                                            longitude2 = (float) this.f2304h.getLongitude();
                                            this.f2304h = null;
                                        }
                                        bestProvider = locationManager.getBestProvider(new Criteria(), false);
                                        locationManager.requestLocationUpdates(bestProvider, 400, 1.0f, this, Looper.getMainLooper());
                                        i2 = 0;
                                        do {
                                            try {
                                                if (this.f2303g) {
                                                    Thread.sleep(500);
                                                    i2++;
                                                }
                                            } catch (Exception e4) {
                                            }
                                            locationManager.removeUpdates(this);
                                        } while (i2 < 70);
                                        this.f2304h = locationManager.getLastKnownLocation(bestProvider);
                                        locationManager.removeUpdates(this);
                                    }
                                    obj4 = obj;
                                    this.f2304h = locationManager.getLastKnownLocation("network");
                                    if (this.f2304h != null) {
                                        longitude2 = (float) this.f2304h.getLongitude();
                                        this.f2304h = null;
                                    }
                                    bestProvider = locationManager.getBestProvider(new Criteria(), false);
                                    locationManager.requestLocationUpdates(bestProvider, 400, 1.0f, this, Looper.getMainLooper());
                                    i2 = 0;
                                    do {
                                        if (this.f2303g) {
                                            Thread.sleep(500);
                                            i2++;
                                        }
                                        locationManager.removeUpdates(this);
                                    } while (i2 < 70);
                                    this.f2304h = locationManager.getLastKnownLocation(bestProvider);
                                    locationManager.removeUpdates(this);
                                }
                                jSONObject = new JSONObject();
                                if (this.f2304h == null) {
                                    try {
                                        return new JSONObject("{\"a\":0,\"b\":0,\"c\":0,\"d\":{\"a\":\"\",\"b\":\"\",\"c\":\"\",\"d\":\"\",\"e\":\"\",\"f\":\"\",\"g\":\"\",\"h\":\"\"},\"e\":0,\"f\":0,\"g\":0,\"h\":0,\"i\":0,\"j\":0}}");
                                    } catch (JSONException e5) {
                                        return jSONObject;
                                    }
                                }
                                C1402i.m2901b(f2302f, "Started...");
                                latitude = this.f2304h.getLatitude();
                                longitude3 = this.f2304h.getLongitude();
                                try {
                                    latitude2 = this.a.getLatitude();
                                    longitude4 = this.a.getLongitude();
                                    this.a.setLocation((float) latitude, (float) longitude3);
                                    jSONObject.put("a", latitude);
                                    jSONObject.put("b", longitude3);
                                    bestProvider = "c";
                                    if (obj4 == null) {
                                    }
                                    i = obj2 == null ? 0 : 2;
                                    jSONObject.put(bestProvider, i);
                                    jSONObject2 = new JSONObject();
                                    try {
                                        fromLocation = new Geocoder(this.a.getContext(), Locale.getDefault()).getFromLocation(latitude, longitude3, 1);
                                        if (fromLocation == null) {
                                            jSONObject3 = jSONObject2;
                                        } else if (fromLocation.size() <= 0) {
                                            jSONObject3 = new JSONObject("{\"a\":\"\",\"b\":\"\",\"c\":\"\",\"d\":\"\",\"e\":\"\",\"f\":\"\",\"g\":\"\",\"h\":\"\"}");
                                        } else {
                                            address = (Address) fromLocation.get(0);
                                            jSONObject2.put("a", ac.m2876e(address.getAddressLine(0)));
                                            jSONObject2.put("b", ac.m2876e(address.getPostalCode()));
                                            jSONObject2.put("c", ac.m2876e(address.getAdminArea()));
                                            jSONObject2.put("d", ac.m2876e(address.getLocality()));
                                            jSONObject2.put("e", ac.m2876e(address.getSubLocality()));
                                            jSONObject2.put("f", ac.m2876e(address.getCountryCode()));
                                            jSONObject2.put("g", ac.m2876e(address.getCountryName()));
                                            jSONObject2.put(RequestParams.f9035H, ac.m2876e(address.getFeatureName()));
                                            jSONObject3 = jSONObject2;
                                        }
                                        obj2 = jSONObject3;
                                    } catch (IOException e6) {
                                    } catch (Exception e7) {
                                    }
                                    f = 0.0f;
                                    try {
                                        location = new Location("bearing_provider");
                                        location.setLatitude((double) latitude2);
                                        location.setLongitude((double) longitude4);
                                        f = location.bearingTo(this.f2304h);
                                    } catch (Exception e8) {
                                    }
                                    jSONObject.put("d", obj2);
                                    jSONObject.put("e", (double) this.f2304h.getAccuracy());
                                    jSONObject.put("f", (double) this.f2304h.getSpeed());
                                    jSONObject.put("g", (double) this.f2304h.getBearing());
                                    jSONObject.put(RequestParams.f9035H, this.f2304h.getAltitude());
                                    jSONObject.put(RequestParams.IP, (double) f);
                                    try {
                                        if (VERSION.SDK_INT < 26) {
                                            jSONObject.put("j", 0.0d);
                                        } else {
                                            jSONObject.put("j", (double) ((Float) this.f2304h.getClass().getMethod("getVerticalAccuracyMeters", new Class[0]).invoke(this.f2304h, new Object[0])).floatValue());
                                        }
                                    } catch (NoSuchMethodException e9) {
                                        jSONObject.put("j", 0.0d);
                                    } catch (IllegalAccessException e10) {
                                        jSONObject.put("j", 0.0d);
                                    } catch (InvocationTargetException e11) {
                                        jSONObject.put("j", 0.0d);
                                    }
                                } catch (JSONException e12) {
                                }
                                C1402i.m2901b(f2302f, "Completed...");
                                return jSONObject;
                            }
                        } catch (Exception e13) {
                            obj5 = null;
                        }
                    }
                    if (this.f2304h != null) {
                        locationManager = (LocationManager) this.a.getContext().getSystemService("location");
                        this.f2304h = locationManager.getLastKnownLocation("gps");
                        if (this.f2304h != null) {
                            longitude = (float) this.f2304h.getLongitude();
                            this.f2304h = null;
                        }
                        if (this.f2304h != null) {
                            obj4 = 1;
                            this.f2304h = locationManager.getLastKnownLocation("network");
                            if (this.f2304h != null) {
                                longitude2 = (float) this.f2304h.getLongitude();
                                this.f2304h = null;
                            }
                            bestProvider = locationManager.getBestProvider(new Criteria(), false);
                            locationManager.requestLocationUpdates(bestProvider, 400, 1.0f, this, Looper.getMainLooper());
                            i2 = 0;
                            do {
                                if (this.f2303g) {
                                    Thread.sleep(500);
                                    i2++;
                                }
                                locationManager.removeUpdates(this);
                            } while (i2 < 70);
                            this.f2304h = locationManager.getLastKnownLocation(bestProvider);
                            locationManager.removeUpdates(this);
                        }
                        obj4 = obj;
                        this.f2304h = locationManager.getLastKnownLocation("network");
                        if (this.f2304h != null) {
                            longitude2 = (float) this.f2304h.getLongitude();
                            this.f2304h = null;
                        }
                        bestProvider = locationManager.getBestProvider(new Criteria(), false);
                        locationManager.requestLocationUpdates(bestProvider, 400, 1.0f, this, Looper.getMainLooper());
                        i2 = 0;
                        do {
                            if (this.f2303g) {
                                Thread.sleep(500);
                                i2++;
                            }
                            locationManager.removeUpdates(this);
                        } while (i2 < 70);
                        this.f2304h = locationManager.getLastKnownLocation(bestProvider);
                        locationManager.removeUpdates(this);
                    } else {
                        obj4 = obj;
                    }
                    jSONObject = new JSONObject();
                    if (this.f2304h == null) {
                        return new JSONObject("{\"a\":0,\"b\":0,\"c\":0,\"d\":{\"a\":\"\",\"b\":\"\",\"c\":\"\",\"d\":\"\",\"e\":\"\",\"f\":\"\",\"g\":\"\",\"h\":\"\"},\"e\":0,\"f\":0,\"g\":0,\"h\":0,\"i\":0,\"j\":0}}");
                    }
                    C1402i.m2901b(f2302f, "Started...");
                    latitude = this.f2304h.getLatitude();
                    longitude3 = this.f2304h.getLongitude();
                    latitude2 = this.a.getLatitude();
                    longitude4 = this.a.getLongitude();
                    this.a.setLocation((float) latitude, (float) longitude3);
                    jSONObject.put("a", latitude);
                    jSONObject.put("b", longitude3);
                    bestProvider = "c";
                    if (obj4 == null) {
                    }
                    if (obj2 == null) {
                    }
                    jSONObject.put(bestProvider, i);
                    jSONObject2 = new JSONObject();
                    fromLocation = new Geocoder(this.a.getContext(), Locale.getDefault()).getFromLocation(latitude, longitude3, 1);
                    if (fromLocation == null) {
                        jSONObject3 = jSONObject2;
                    } else if (fromLocation.size() <= 0) {
                        address = (Address) fromLocation.get(0);
                        jSONObject2.put("a", ac.m2876e(address.getAddressLine(0)));
                        jSONObject2.put("b", ac.m2876e(address.getPostalCode()));
                        jSONObject2.put("c", ac.m2876e(address.getAdminArea()));
                        jSONObject2.put("d", ac.m2876e(address.getLocality()));
                        jSONObject2.put("e", ac.m2876e(address.getSubLocality()));
                        jSONObject2.put("f", ac.m2876e(address.getCountryCode()));
                        jSONObject2.put("g", ac.m2876e(address.getCountryName()));
                        jSONObject2.put(RequestParams.f9035H, ac.m2876e(address.getFeatureName()));
                        jSONObject3 = jSONObject2;
                    } else {
                        jSONObject3 = new JSONObject("{\"a\":\"\",\"b\":\"\",\"c\":\"\",\"d\":\"\",\"e\":\"\",\"f\":\"\",\"g\":\"\",\"h\":\"\"}");
                    }
                    obj2 = jSONObject3;
                    f = 0.0f;
                    location = new Location("bearing_provider");
                    location.setLatitude((double) latitude2);
                    location.setLongitude((double) longitude4);
                    f = location.bearingTo(this.f2304h);
                    jSONObject.put("d", obj2);
                    jSONObject.put("e", (double) this.f2304h.getAccuracy());
                    jSONObject.put("f", (double) this.f2304h.getSpeed());
                    jSONObject.put("g", (double) this.f2304h.getBearing());
                    jSONObject.put(RequestParams.f9035H, this.f2304h.getAltitude());
                    jSONObject.put(RequestParams.IP, (double) f);
                    if (VERSION.SDK_INT < 26) {
                        jSONObject.put("j", (double) ((Float) this.f2304h.getClass().getMethod("getVerticalAccuracyMeters", new Class[0]).invoke(this.f2304h, new Object[0])).floatValue());
                    } else {
                        jSONObject.put("j", 0.0d);
                    }
                    C1402i.m2901b(f2302f, "Completed...");
                    return jSONObject;
                }
            }
            obj2 = obj5;
            obj3 = obj;
        } catch (JSONException e14) {
            JSONException jSONException = e14;
            obj = null;
            e = jSONException;
            e.printStackTrace();
            obj2 = null;
            obj3 = obj;
            if (m3022g()) {
                c1468o = new C1468o(this.a.getLatitude(), this.a.getLongitude());
                c1468o.m3042a(this.a.getContext());
                i = 0;
                while (!c1468o.m3043a()) {
                    Thread.sleep(500);
                    i++;
                    if (i >= 70) {
                        i = 0;
                        while (!c1468o.m3045c()) {
                            Thread.sleep(500);
                            i++;
                            if (i >= 70) {
                                this.f2304h = c1468o.m3046d();
                                if (this.f2304h != null) {
                                    obj5 = 1;
                                    c1468o.m3047e();
                                    obj = obj5;
                                }
                                obj5 = null;
                                c1468o.m3047e();
                                obj = obj5;
                            }
                        }
                        this.f2304h = c1468o.m3046d();
                        if (this.f2304h != null) {
                            obj5 = 1;
                            c1468o.m3047e();
                            obj = obj5;
                        }
                        obj5 = null;
                        c1468o.m3047e();
                        obj = obj5;
                    }
                }
                i = 0;
                while (!c1468o.m3045c()) {
                    Thread.sleep(500);
                    i++;
                    if (i >= 70) {
                        this.f2304h = c1468o.m3046d();
                        if (this.f2304h != null) {
                            obj5 = 1;
                            c1468o.m3047e();
                            obj = obj5;
                        }
                        obj5 = null;
                        c1468o.m3047e();
                        obj = obj5;
                    }
                }
                this.f2304h = c1468o.m3046d();
                if (this.f2304h != null) {
                    obj5 = 1;
                    c1468o.m3047e();
                    obj = obj5;
                }
                obj5 = null;
                c1468o.m3047e();
                obj = obj5;
            } else {
                obj = null;
            }
            if (this.f2304h != null) {
                obj4 = obj;
            } else {
                locationManager = (LocationManager) this.a.getContext().getSystemService("location");
                this.f2304h = locationManager.getLastKnownLocation("gps");
                if (this.f2304h != null) {
                    longitude = (float) this.f2304h.getLongitude();
                    this.f2304h = null;
                }
                if (this.f2304h != null) {
                    obj4 = 1;
                    this.f2304h = locationManager.getLastKnownLocation("network");
                    if (this.f2304h != null) {
                        longitude2 = (float) this.f2304h.getLongitude();
                        this.f2304h = null;
                    }
                    bestProvider = locationManager.getBestProvider(new Criteria(), false);
                    locationManager.requestLocationUpdates(bestProvider, 400, 1.0f, this, Looper.getMainLooper());
                    i2 = 0;
                    do {
                        if (this.f2303g) {
                            Thread.sleep(500);
                            i2++;
                        }
                        locationManager.removeUpdates(this);
                    } while (i2 < 70);
                    this.f2304h = locationManager.getLastKnownLocation(bestProvider);
                    locationManager.removeUpdates(this);
                }
                obj4 = obj;
                this.f2304h = locationManager.getLastKnownLocation("network");
                if (this.f2304h != null) {
                    longitude2 = (float) this.f2304h.getLongitude();
                    this.f2304h = null;
                }
                bestProvider = locationManager.getBestProvider(new Criteria(), false);
                locationManager.requestLocationUpdates(bestProvider, 400, 1.0f, this, Looper.getMainLooper());
                i2 = 0;
                do {
                    if (this.f2303g) {
                        Thread.sleep(500);
                        i2++;
                    }
                    locationManager.removeUpdates(this);
                } while (i2 < 70);
                this.f2304h = locationManager.getLastKnownLocation(bestProvider);
                locationManager.removeUpdates(this);
            }
            jSONObject = new JSONObject();
            if (this.f2304h == null) {
                return new JSONObject("{\"a\":0,\"b\":0,\"c\":0,\"d\":{\"a\":\"\",\"b\":\"\",\"c\":\"\",\"d\":\"\",\"e\":\"\",\"f\":\"\",\"g\":\"\",\"h\":\"\"},\"e\":0,\"f\":0,\"g\":0,\"h\":0,\"i\":0,\"j\":0}}");
            }
            C1402i.m2901b(f2302f, "Started...");
            latitude = this.f2304h.getLatitude();
            longitude3 = this.f2304h.getLongitude();
            latitude2 = this.a.getLatitude();
            longitude4 = this.a.getLongitude();
            this.a.setLocation((float) latitude, (float) longitude3);
            jSONObject.put("a", latitude);
            jSONObject.put("b", longitude3);
            bestProvider = "c";
            if (obj4 == null) {
            }
            if (obj2 == null) {
            }
            jSONObject.put(bestProvider, i);
            jSONObject2 = new JSONObject();
            fromLocation = new Geocoder(this.a.getContext(), Locale.getDefault()).getFromLocation(latitude, longitude3, 1);
            if (fromLocation == null) {
                jSONObject3 = jSONObject2;
            } else if (fromLocation.size() <= 0) {
                jSONObject3 = new JSONObject("{\"a\":\"\",\"b\":\"\",\"c\":\"\",\"d\":\"\",\"e\":\"\",\"f\":\"\",\"g\":\"\",\"h\":\"\"}");
            } else {
                address = (Address) fromLocation.get(0);
                jSONObject2.put("a", ac.m2876e(address.getAddressLine(0)));
                jSONObject2.put("b", ac.m2876e(address.getPostalCode()));
                jSONObject2.put("c", ac.m2876e(address.getAdminArea()));
                jSONObject2.put("d", ac.m2876e(address.getLocality()));
                jSONObject2.put("e", ac.m2876e(address.getSubLocality()));
                jSONObject2.put("f", ac.m2876e(address.getCountryCode()));
                jSONObject2.put("g", ac.m2876e(address.getCountryName()));
                jSONObject2.put(RequestParams.f9035H, ac.m2876e(address.getFeatureName()));
                jSONObject3 = jSONObject2;
            }
            obj2 = jSONObject3;
            f = 0.0f;
            location = new Location("bearing_provider");
            location.setLatitude((double) latitude2);
            location.setLongitude((double) longitude4);
            f = location.bearingTo(this.f2304h);
            jSONObject.put("d", obj2);
            jSONObject.put("e", (double) this.f2304h.getAccuracy());
            jSONObject.put("f", (double) this.f2304h.getSpeed());
            jSONObject.put("g", (double) this.f2304h.getBearing());
            jSONObject.put(RequestParams.f9035H, this.f2304h.getAltitude());
            jSONObject.put(RequestParams.IP, (double) f);
            if (VERSION.SDK_INT < 26) {
                jSONObject.put("j", 0.0d);
            } else {
                jSONObject.put("j", (double) ((Float) this.f2304h.getClass().getMethod("getVerticalAccuracyMeters", new Class[0]).invoke(this.f2304h, new Object[0])).floatValue());
            }
            C1402i.m2901b(f2302f, "Completed...");
            return jSONObject;
        }
        if (m3022g()) {
            c1468o = new C1468o(this.a.getLatitude(), this.a.getLongitude());
            c1468o.m3042a(this.a.getContext());
            i = 0;
            while (!c1468o.m3043a() && !c1468o.m3044b()) {
                Thread.sleep(500);
                i++;
                if (i >= 70) {
                }
            }
            if (c1468o.m3043a() && !c1468o.m3044b()) {
                i = 0;
                while (!c1468o.m3045c() && !c1468o.m3044b()) {
                    Thread.sleep(500);
                    i++;
                    if (i >= 70) {
                    }
                }
                if (c1468o.m3045c() && !c1468o.m3044b()) {
                    this.f2304h = c1468o.m3046d();
                    if (this.f2304h != null) {
                        obj5 = 1;
                        c1468o.m3047e();
                        obj = obj5;
                    }
                }
            }
            obj5 = null;
            c1468o.m3047e();
            obj = obj5;
        } else {
            obj = null;
        }
        if (this.f2304h != null) {
            locationManager = (LocationManager) this.a.getContext().getSystemService("location");
            if (!(locationManager == null || r8 == null || !locationManager.isProviderEnabled("gps"))) {
                this.f2304h = locationManager.getLastKnownLocation("gps");
                if (this.f2304h != null) {
                    longitude = (float) this.f2304h.getLongitude();
                    if (this.a.getLatitude() == ((float) this.f2304h.getLatitude()) && this.a.getLongitude() == longitude) {
                        this.f2304h = null;
                    }
                }
                if (this.f2304h != null) {
                    obj4 = 1;
                    if (this.f2304h == null && locationManager != null && obj2 != null && locationManager.isProviderEnabled("network")) {
                        this.f2304h = locationManager.getLastKnownLocation("network");
                        if (this.f2304h != null) {
                            longitude2 = (float) this.f2304h.getLongitude();
                            if (this.a.getLatitude() == ((float) this.f2304h.getLatitude()) && this.a.getLongitude() == longitude2) {
                                this.f2304h = null;
                            }
                        }
                    }
                    if (this.f2304h == null && !(r8 == null && obj2 == null)) {
                        bestProvider = locationManager.getBestProvider(new Criteria(), false);
                        locationManager.requestLocationUpdates(bestProvider, 400, 1.0f, this, Looper.getMainLooper());
                        i2 = 0;
                        do {
                            if (this.f2303g) {
                                Thread.sleep(500);
                                i2++;
                            }
                            locationManager.removeUpdates(this);
                        } while (i2 < 70);
                        this.f2304h = locationManager.getLastKnownLocation(bestProvider);
                        locationManager.removeUpdates(this);
                    }
                }
            }
            obj4 = obj;
            this.f2304h = locationManager.getLastKnownLocation("network");
            if (this.f2304h != null) {
                longitude2 = (float) this.f2304h.getLongitude();
                this.f2304h = null;
            }
            bestProvider = locationManager.getBestProvider(new Criteria(), false);
            locationManager.requestLocationUpdates(bestProvider, 400, 1.0f, this, Looper.getMainLooper());
            i2 = 0;
            do {
                if (this.f2303g) {
                    Thread.sleep(500);
                    i2++;
                }
                locationManager.removeUpdates(this);
            } while (i2 < 70);
            this.f2304h = locationManager.getLastKnownLocation(bestProvider);
            locationManager.removeUpdates(this);
        } else {
            obj4 = obj;
        }
        jSONObject = new JSONObject();
        if (this.f2304h == null) {
            return new JSONObject("{\"a\":0,\"b\":0,\"c\":0,\"d\":{\"a\":\"\",\"b\":\"\",\"c\":\"\",\"d\":\"\",\"e\":\"\",\"f\":\"\",\"g\":\"\",\"h\":\"\"},\"e\":0,\"f\":0,\"g\":0,\"h\":0,\"i\":0,\"j\":0}}");
        }
        C1402i.m2901b(f2302f, "Started...");
        latitude = this.f2304h.getLatitude();
        longitude3 = this.f2304h.getLongitude();
        latitude2 = this.a.getLatitude();
        longitude4 = this.a.getLongitude();
        this.a.setLocation((float) latitude, (float) longitude3);
        jSONObject.put("a", latitude);
        jSONObject.put("b", longitude3);
        bestProvider = "c";
        if (obj4 == null && r8 != null) {
            i = 1;
        } else if (obj2 == null) {
        }
        jSONObject.put(bestProvider, i);
        jSONObject2 = new JSONObject();
        fromLocation = new Geocoder(this.a.getContext(), Locale.getDefault()).getFromLocation(latitude, longitude3, 1);
        if (fromLocation == null) {
            jSONObject3 = jSONObject2;
        } else if (fromLocation.size() <= 0) {
            address = (Address) fromLocation.get(0);
            jSONObject2.put("a", ac.m2876e(address.getAddressLine(0)));
            jSONObject2.put("b", ac.m2876e(address.getPostalCode()));
            jSONObject2.put("c", ac.m2876e(address.getAdminArea()));
            jSONObject2.put("d", ac.m2876e(address.getLocality()));
            jSONObject2.put("e", ac.m2876e(address.getSubLocality()));
            jSONObject2.put("f", ac.m2876e(address.getCountryCode()));
            jSONObject2.put("g", ac.m2876e(address.getCountryName()));
            jSONObject2.put(RequestParams.f9035H, ac.m2876e(address.getFeatureName()));
            jSONObject3 = jSONObject2;
        } else {
            jSONObject3 = new JSONObject("{\"a\":\"\",\"b\":\"\",\"c\":\"\",\"d\":\"\",\"e\":\"\",\"f\":\"\",\"g\":\"\",\"h\":\"\"}");
        }
        obj2 = jSONObject3;
        f = 0.0f;
        if (!(latitude2 == 0.0f || longitude4 == 0.0f)) {
            location = new Location("bearing_provider");
            location.setLatitude((double) latitude2);
            location.setLongitude((double) longitude4);
            f = location.bearingTo(this.f2304h);
        }
        jSONObject.put("d", obj2);
        jSONObject.put("e", (double) this.f2304h.getAccuracy());
        jSONObject.put("f", (double) this.f2304h.getSpeed());
        jSONObject.put("g", (double) this.f2304h.getBearing());
        jSONObject.put(RequestParams.f9035H, this.f2304h.getAltitude());
        jSONObject.put(RequestParams.IP, (double) f);
        if (VERSION.SDK_INT < 26) {
            jSONObject.put("j", (double) ((Float) this.f2304h.getClass().getMethod("getVerticalAccuracyMeters", new Class[0]).invoke(this.f2304h, new Object[0])).floatValue());
        } else {
            jSONObject.put("j", 0.0d);
        }
        C1402i.m2901b(f2302f, "Completed...");
        return jSONObject;
    }

    public void onLocationChanged(Location location) {
        this.f2304h = location;
        this.f2303g = true;
    }

    public void onStatusChanged(String str, int i, Bundle bundle) {
    }

    public void onProviderEnabled(String str) {
        this.f2303g = false;
    }

    public void onProviderDisabled(String str) {
        this.f2303g = true;
    }

    private boolean m3022g() {
        try {
            return (Class.forName("com.google.android.gms.common.api.GoogleApiClient") == null || Class.forName("com.google.android.gms.location.LocationServices") == null) ? false : true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }
}
