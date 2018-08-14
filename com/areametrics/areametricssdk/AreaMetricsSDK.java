package com.areametrics.areametricssdk;

import android.app.Activity;
import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import com.adcolony.sdk.AdColonyUserMetadata;
import com.areametrics.areametricssdk.C1381h.C1380a;
import com.fyber.mediation.admob.AdMobMediationAdapter;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import java.io.UnsupportedEncodingException;
import java.lang.Thread.UncaughtExceptionHandler;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.BeaconParser;
import org.altbeacon.beacon.Region;
import org.altbeacon.beacon.logging.LogManager;
import org.altbeacon.beacon.logging.Loggers;
import org.altbeacon.beacon.powersave.BackgroundPowerSaver;
import org.altbeacon.beacon.startup.BootstrapNotifier;
import org.altbeacon.beacon.startup.RegionBootstrap;
import org.altbeacon.bluetooth.BluetoothMedic;
import org.json.JSONObject;

public enum AreaMetricsSDK implements ActivityLifecycleCallbacks, BootstrapNotifier {
    ;
    
    private static final String f1826a = null;
    private static UncaughtExceptionHandler f1827o;
    private static UncaughtExceptionHandler f1828p;
    private String f1830b;
    private String f1831c;
    private Context f1832d;
    public AreaMetricsSDKDelegate delegate;
    private FusedLocationProviderClient f1833e;
    private C1378g f1834f;
    private C1381h f1835g;
    private C1372e f1836h;
    private C1367d f1837i;
    private C1343b f1838j;
    private boolean f1839k;
    private int f1840l;
    private RegionBootstrap f1841m;
    private BackgroundPowerSaver f1842n;
    private int f1843q;
    private int f1844r;

    static class C13321 implements UncaughtExceptionHandler {
        C13321() {
        }

        public final void uncaughtException(Thread thread, Throwable th) {
            Object obj = null;
            Throwable th2 = th;
            while (th2 != null) {
                if ((th2 instanceof SecurityException) && th2.getMessage() != null && th2.getMessage().contains("Too many alarms")) {
                    obj = 1;
                    break;
                }
                th2 = th2.getCause();
            }
            if (obj == null) {
                AreaMetricsSDK.f1827o.uncaughtException(thread, th);
            }
        }
    }

    class C13332 implements Runnable {
        final /* synthetic */ AreaMetricsSDK f1824a;

        C13332(AreaMetricsSDK areaMetricsSDK) {
            this.f1824a = areaMetricsSDK;
        }

        public final void run() {
            try {
                this.f1824a.f1838j.m2443a();
                new C1334a().execute(new Void[0]);
                this.f1824a.getNetworkController().m2533g();
                this.f1824a.getNetworkController().m2532f();
                this.f1824a.getNetworkController().m2535i();
                LogManager.setLogger(Loggers.empty());
                LogManager.setVerboseLoggingEnabled(false);
                BeaconManager instanceForApplication = BeaconManager.getInstanceForApplication(this.f1824a.f1832d);
                instanceForApplication.getBeaconParsers().add(new BeaconParser("ibeacon").setBeaconLayout("m:2-3=0215,i:4-19,i:20-21,i:22-23,p:24-24"));
                this.f1824a.f1841m = new RegionBootstrap(AreaMetricsSDK.INSTANCE, new Region("AnyBeacon", null, null, null));
                this.f1824a.f1842n = new BackgroundPowerSaver(this.f1824a.f1832d);
                if (VERSION.SDK_INT >= 21) {
                    BluetoothMedic.getInstance().enablePowerCycleOnFailures(this.f1824a.f1832d);
                }
                this.f1824a.f1836h = new C1372e(this.f1824a.f1832d);
                instanceForApplication.bind(this.f1824a.f1836h);
                this.f1824a.getUserData().m2598a("AreaMetricsSDK Initialized!");
                if (!this.f1824a.f1839k) {
                    this.f1824a.f1839k = true;
                    if (this.f1824a.getUserData() != null) {
                        this.f1824a.getUserData().m2626k();
                    }
                    if (this.f1824a.getNetworkController() != null) {
                        this.f1824a.getNetworkController().m2533g();
                        this.f1824a.getNetworkController().m2532f();
                        this.f1824a.getNetworkController().m2535i();
                    }
                    if (this.f1824a.f1835g != null && this.f1824a.isAppInForeground()) {
                        if (this.f1824a.f1840l == 0) {
                            this.f1824a.f1840l = C1380a.f1997b;
                        }
                        this.f1824a.f1835g.m2640b(this.f1824a.f1840l);
                    }
                }
                if (this.f1824a.f1836h != null) {
                    this.f1824a.f1836h.m2557a();
                }
            } catch (NullPointerException e) {
            }
        }
    }

    public interface AreaMetricsSDKDelegate {
        void didGeneratePersona(JSONObject jSONObject);
    }

    private class C1334a extends AsyncTask<Void, Void, String> {
        final /* synthetic */ AreaMetricsSDK f1825a;

        private C1334a(AreaMetricsSDK areaMetricsSDK) {
            this.f1825a = areaMetricsSDK;
        }

        private String m2418a() {
            String str = null;
            if (this.f1825a.isGooglePlayServicesAvailable(this.f1825a.f1832d)) {
                try {
                    Info advertisingIdInfo = AdvertisingIdClient.getAdvertisingIdInfo(this.f1825a.f1832d);
                    if (!(advertisingIdInfo == null || advertisingIdInfo.getId() == null)) {
                        str = advertisingIdInfo.getId();
                    }
                } catch (Exception e) {
                }
            }
            return str;
        }

        protected final /* synthetic */ Object doInBackground(Object[] objArr) {
            return m2418a();
        }

        protected final /* synthetic */ void onPostExecute(Object obj) {
            String str = (String) obj;
            if (str != null && this.f1825a.f1834f != null) {
                this.f1825a.f1834f.m2611c(str);
            }
        }
    }

    static {
        f1826a = "AMS-" + AreaMetricsSDK.class.getSimpleName();
        f1828p = new C13321();
    }

    private AreaMetricsSDK(String str) {
        this.f1830b = null;
        this.f1831c = null;
        this.f1832d = null;
        this.f1839k = false;
        this.f1840l = 0;
        this.f1843q = 0;
        this.f1844r = 0;
        this.f1834f = new C1378g();
        this.f1835g = new C1381h();
        this.f1838j = new C1343b();
    }

    private static boolean m2425a(String str) {
        if (str == null) {
            return true;
        }
        try {
            return str.length() == 0 || str.getBytes("UTF-8").length != 64;
        } catch (UnsupportedEncodingException e) {
            return true;
        }
    }

    public static AreaMetricsSDK getInstance() {
        return INSTANCE;
    }

    public final void didDetermineStateForRegion(int i, Region region) {
        if (this.f1836h == null) {
            this.f1836h = new C1372e(getContext());
        }
        C1372e.m2547b();
    }

    public final void didEnterRegion(Region region) {
        if (this.f1836h == null) {
            this.f1836h = new C1372e(getContext());
        }
        this.f1836h.m2562a(region);
    }

    public final void didExitRegion(Region region) {
        if (this.f1836h == null) {
            this.f1836h = new C1372e(getContext());
        }
        this.f1836h.m2564b(region);
    }

    public final void feedbackEnabled(boolean z) {
        this.f1834f.f1991c = !z;
    }

    protected final String getApiKey() {
        return this.f1831c;
    }

    protected final String getAppID() {
        return this.f1830b;
    }

    public final Context getApplicationContext() {
        return getContext();
    }

    protected final C1372e getBeaconTracker() {
        return this.f1836h;
    }

    public final JSONObject getCachedPersona() {
        return this.f1834f.m2593a();
    }

    protected final Context getContext() {
        return this.f1832d;
    }

    protected final FusedLocationProviderClient getLocProviderClient() {
        return this.f1833e;
    }

    protected final C1343b getLocationChangeListener() {
        return this.f1838j;
    }

    protected final C1367d getNetworkController() {
        return this.f1837i;
    }

    protected final C1378g getUserData() {
        return this.f1834f;
    }

    protected final C1381h getUserDataController() {
        return this.f1835g;
    }

    protected final boolean isAppInForeground() {
        return this.f1844r > 0;
    }

    protected final boolean isAppVisible() {
        return this.f1843q > 0;
    }

    public final boolean isGooglePlayServicesAvailable(Context context) {
        return GoogleApiAvailability.getInstance() != null && GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(context) == 0;
    }

    public final void onActivityCreated(Activity activity, Bundle bundle) {
        if (bundle != null && bundle.getBoolean(f1826a)) {
            this.f1839k = true;
            this.f1840l = C1380a.f2000e;
        }
    }

    public final void onActivityDestroyed(Activity activity) {
        this.f1839k = false;
        this.f1840l = 0;
    }

    public final void onActivityPaused(Activity activity) {
        this.f1844r--;
        this.f1839k = false;
        this.f1840l = 0;
    }

    public final void onActivityResumed(Activity activity) {
        this.f1844r++;
        if (this.f1840l == 0) {
            this.f1840l = C1380a.f1998c;
        }
        if (!this.f1839k) {
            this.f1839k = true;
            if (getNetworkController() != null) {
                getNetworkController().m2533g();
                getUserData().m2626k();
                getNetworkController().m2532f();
                getNetworkController().m2535i();
            }
            if (this.f1835g != null) {
                this.f1835g.m2640b(this.f1840l);
            }
        }
        this.f1838j.m2443a();
        if (this.f1836h != null) {
            this.f1836h.m2557a();
        }
    }

    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        if (activity != null && !activity.isFinishing() && bundle != null) {
            bundle.putBoolean(f1826a, true);
        }
    }

    public final void onActivityStarted(Activity activity) {
        this.f1843q++;
        if (this.f1840l == 0) {
            this.f1840l = C1380a.f1997b;
        }
        if (!this.f1839k) {
            this.f1839k = true;
            if (getNetworkController() != null) {
                getNetworkController().m2533g();
                getUserData().m2626k();
                getNetworkController().m2532f();
                getNetworkController().m2535i();
            }
            if (this.f1835g != null && isAppInForeground()) {
                this.f1835g.m2640b(this.f1840l);
            }
        }
        this.f1838j.m2443a();
        if (this.f1836h != null) {
            this.f1836h.m2557a();
        }
    }

    public final void onActivityStopped(Activity activity) {
        this.f1843q--;
        this.f1839k = false;
        this.f1840l = 0;
    }

    public final void setCustomData(String str, String str2) {
        this.f1834f.m2599a(str, str2);
    }

    public final void setUserAge(int i) {
        this.f1834f.m2616d("age", Integer.toString(i));
    }

    public final void setUserGender(boolean z) {
        C1378g c1378g = this.f1834f;
        if (Boolean.valueOf(z).booleanValue()) {
            c1378g.m2616d(AdMobMediationAdapter.GENDER_KEY, AdColonyUserMetadata.USER_MALE);
        } else {
            c1378g.m2616d(AdMobMediationAdapter.GENDER_KEY, AdColonyUserMetadata.USER_FEMALE);
        }
    }

    public final void setUserID(String str) {
        this.f1834f.m2616d("user_id", str);
    }

    public final void setUserZipCode(String str) {
        this.f1834f.m2616d("zip_code", str);
    }

    public final void startService(Application application, String str, String str2) {
        if (VERSION.SDK_INT != 19) {
            String str3 = null;
            if (m2425a(str) || m2425a(str2)) {
                str3 = "Called startService with invalid AppId or ApiKey";
            }
            if (str3 == null && Integer.parseInt(str.substring(str.length() - 2), 16) < 128) {
                str3 = "Called startService with non-Android AppId";
            }
            if (str3 == null && str.equals(str2)) {
                str3 = "Called startService with identical AppId and ApiKey";
            }
            if (str3 == null && application == null) {
                str3 = "Called startService without Application context";
            }
            if (str3 != null) {
                Log.e(f1826a, str3);
                throw new IllegalArgumentException(str3);
            }
            this.f1830b = str;
            this.f1831c = str2;
            this.f1832d = application;
            if (getUserData().m2628m().booleanValue()) {
                this.f1840l = C1380a.f1996a;
            }
            this.f1833e = LocationServices.getFusedLocationProviderClient(this.f1832d);
            application.registerActivityLifecycleCallbacks(INSTANCE);
            this.f1837i = new C1367d(this.f1835g);
            f1827o = Thread.getDefaultUncaughtExceptionHandler();
            Thread.setDefaultUncaughtExceptionHandler(f1828p);
            new Handler().postDelayed(new C13332(this), 1000);
        }
    }
}
