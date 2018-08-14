package com.silvermob.sdk;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import com.appnext.base.p023b.C1048i;
import com.appnext.core.Ad;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient$Info;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient$Builder;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.location.LocationServices;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.json.JSONObject;

class Tools {
    private static GoogleApiClient mGoogleApiClient;
    private static Location mLastLocation;
    private static Boolean mLocationServicesInitialized = Boolean.valueOf(false);

    static class C39671 implements OnConnectionFailedListener {
        C39671() {
        }

        public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
            Log.e("Tools", connectionResult.getErrorMessage());
        }
    }

    static class C39682 implements ConnectionCallbacks {
        C39682() {
        }

        public void onConnected(@Nullable Bundle bundle) {
            Tools.mLastLocation = LocationServices.FusedLocationApi.getLastLocation(Tools.mGoogleApiClient);
        }

        public void onConnectionSuspended(int i) {
        }
    }

    Tools() {
    }

    static JSONObject jsonRequest(String url) throws IOException {
        Exception e;
        HttpURLConnection urlConnection = (HttpURLConnection) new URL(url).openConnection();
        try {
            StringBuilder result = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(new BufferedInputStream(urlConnection.getInputStream())));
            while (true) {
                String line = reader.readLine();
                if (line != null) {
                    result.append(line);
                } else {
                    JSONObject res = new JSONObject(result.toString());
                    urlConnection.disconnect();
                    return res;
                }
            }
        } catch (Exception e2) {
            e = e2;
            try {
                e.printStackTrace();
                return null;
            } finally {
                urlConnection.disconnect();
            }
        } catch (Exception e22) {
            e = e22;
            e.printStackTrace();
            return null;
        }
    }

    static void initLocationServices(Context context) {
        if (VERSION.SDK_INT >= 9 && !mLocationServicesInitialized.booleanValue()) {
            if (ActivityCompat.checkSelfPermission(context, "android.permission.ACCESS_FINE_LOCATION") == 0 || ActivityCompat.checkSelfPermission(context, "android.permission.ACCESS_COARSE_LOCATION") == 0) {
                mLocationServicesInitialized = Boolean.valueOf(true);
                mGoogleApiClient = new GoogleApiClient$Builder(context).addConnectionCallbacks(new C39682()).addOnConnectionFailedListener(new C39671()).addApi(LocationServices.API).build();
                mGoogleApiClient.connect();
            }
        }
    }

    private static String getCarrierName(Context context) {
        return ((TelephonyManager) context.getSystemService("phone")).getNetworkOperatorName();
    }

    private static String getConnectionType(Context context) {
        try {
            if (ContextCompat.checkSelfPermission(context, "android.permission.ACCESS_NETWORK_STATE") != 0) {
                return "";
            }
            NetworkInfo info = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (info.getType() == 1) {
                return "wifi";
            }
            if (info.getType() != 0) {
                return "";
            }
            switch (((TelephonyManager) context.getSystemService("phone")).getNetworkType()) {
                case 1:
                case 2:
                case 4:
                case 7:
                case 11:
                    return "2g";
                case 3:
                case 5:
                case 6:
                case 8:
                case 9:
                case 10:
                case 12:
                case 14:
                case 15:
                    return "3g";
                case 13:
                    return "4g";
                default:
                    return "";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    private static String getDeviceModel() {
        return Build.MODEL;
    }

    private static String getDeviceManufacturer() {
        return Build.MANUFACTURER;
    }

    private static Pair<Double, Double> getLocation(Context context) {
        Pair<Double, Double> loc = new Pair(Double.valueOf(0.0d), Double.valueOf(0.0d));
        try {
            LocationManager lm = (LocationManager) context.getSystemService("location");
            List<String> providers = lm.getProviders(true);
            Location location = null;
            if (ActivityCompat.checkSelfPermission(context, "android.permission.ACCESS_FINE_LOCATION") != 0) {
                if (ActivityCompat.checkSelfPermission(context, "android.permission.ACCESS_COARSE_LOCATION") != 0) {
                    for (String provider : providers) {
                        Location l = lm.getLastKnownLocation(provider);
                        if (l != null && (location == null || l.getAccuracy() < location.getAccuracy())) {
                            location = l;
                        }
                    }
                }
            }
            if (location == null) {
                return loc;
            }
            return new Pair(Double.valueOf(location.getLongitude()), Double.valueOf(location.getLatitude()));
        } catch (Exception e) {
            e.printStackTrace();
            return loc;
        }
    }

    private static String getOsVersion() {
        return VERSION.RELEASE;
    }

    private static String getBundleId(Context context) {
        return context.getPackageName();
    }

    private static String getInstallerApp(Context context) {
        String installerPackageName = "";
        try {
            installerPackageName = context.getPackageManager().getInstallerPackageName(getBundleId(context));
            if (installerPackageName == null) {
                return "";
            }
            return installerPackageName;
        } catch (Exception e) {
            e.printStackTrace();
            return installerPackageName;
        }
    }

    private static String getAdvertisingId(Context context) {
        String advertisingId = "";
        try {
            if (GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(context) == 0) {
                AdvertisingIdClient$Info adInfo = AdvertisingIdClient.getAdvertisingIdInfo(context.getApplicationContext());
                if (!adInfo.isLimitAdTrackingEnabled()) {
                    advertisingId = adInfo.getId();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return advertisingId;
    }

    private static ArrayList<Pair<String, String>> getTargetingData(Context context) {
        ArrayList<Pair<String, String>> params = new ArrayList();
        try {
            String installerAppName = getInstallerApp(context);
            String bundleId = getBundleId(context);
            String screenOrientation = context.getResources().getConfiguration().orientation == 2 ? Ad.ORIENTATION_LANDSCAPE : Ad.ORIENTATION_PORTRAIT;
            Pair<Double, Double> loc = getLocation(context);
            params.add(new Pair("device_manufacturer", getDeviceManufacturer()));
            params.add(new Pair("device_model", getDeviceModel()));
            params.add(new Pair("connection_type", getConnectionType(context)));
            params.add(new Pair("carrier", getCarrierName(context)));
            params.add(new Pair("osv", getOsVersion()));
            params.add(new Pair("bundle", bundleId));
            params.add(new Pair("installer", installerAppName));
            params.add(new Pair("aid", getAdvertisingId(context)));
            params.add(new Pair("lang", Locale.getDefault().getLanguage()));
            params.add(new Pair("sor", screenOrientation));
            if (installerAppName.startsWith("com.android.vending")) {
                params.add(new Pair("su", "https://play.google.com/store/apps/details?id=" + bundleId));
            }
            if (((Double) loc.first).doubleValue() == 0.0d || ((Double) loc.second).doubleValue() == 0.0d) {
                if (mLastLocation != null) {
                    params.add(new Pair("lon", String.valueOf(mLastLocation.getLongitude())));
                    params.add(new Pair(C1048i.ko, String.valueOf(mLastLocation.getLatitude())));
                }
                return params;
            }
            params.add(new Pair("lon", String.valueOf(loc.first)));
            params.add(new Pair(C1048i.ko, String.valueOf(loc.second)));
            return params;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static String getTargetingDataAsGetParams(Context context) {
        ArrayList<Pair<String, String>> params = getTargetingData(context);
        ArrayList<String> paramsConcat = new ArrayList();
        Iterator it = params.iterator();
        while (it.hasNext()) {
            Pair<String, String> p = (Pair) it.next();
            try {
                paramsConcat.add(((String) p.first) + "=" + URLEncoder.encode((String) p.second, "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return TextUtils.join("&", paramsConcat);
    }
}
