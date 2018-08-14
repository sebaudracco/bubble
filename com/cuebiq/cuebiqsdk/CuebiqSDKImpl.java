package com.cuebiq.cuebiqsdk;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.location.Location;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.widget.TextView;
import com.cuebiq.cuebiqsdk.api.ApiConfiguration;
import com.cuebiq.cuebiqsdk.api.Environment;
import com.cuebiq.cuebiqsdk.api.GDPRConsentRequest;
import com.cuebiq.cuebiqsdk.injection.Injection;
import com.cuebiq.cuebiqsdk.model.Collector;
import com.cuebiq.cuebiqsdk.model.CoverageManager;
import com.cuebiq.cuebiqsdk.model.CoverageManager.CoverageListener;
import com.cuebiq.cuebiqsdk.model.CoverageManager.CoverageStatus;
import com.cuebiq.cuebiqsdk.model.CuebiqSDKProvider;
import com.cuebiq.cuebiqsdk.model.config.Settings;
import com.cuebiq.cuebiqsdk.model.listener.AuthSerializer;
import com.cuebiq.cuebiqsdk.model.listener.BluetoothDeviceSerializer;
import com.cuebiq.cuebiqsdk.model.listener.CoverageSettingsSerializer;
import com.cuebiq.cuebiqsdk.model.listener.DeviceSerializer;
import com.cuebiq.cuebiqsdk.model.listener.EventSerializer;
import com.cuebiq.cuebiqsdk.model.listener.GDPRConsentSerializer;
import com.cuebiq.cuebiqsdk.model.listener.GeoLocationStatsSerializer;
import com.cuebiq.cuebiqsdk.model.listener.GeoSerializer;
import com.cuebiq.cuebiqsdk.model.listener.InformationSerializer;
import com.cuebiq.cuebiqsdk.model.listener.IpAddressSerializer;
import com.cuebiq.cuebiqsdk.model.listener.RequestSerializer;
import com.cuebiq.cuebiqsdk.model.listener.ServerResponseSerializer;
import com.cuebiq.cuebiqsdk.model.listener.SettingsSerializer;
import com.cuebiq.cuebiqsdk.model.listener.WifiSerializer;
import com.cuebiq.cuebiqsdk.model.manager.CustomEventManager;
import com.cuebiq.cuebiqsdk.model.manager.LocationManagerHelper;
import com.cuebiq.cuebiqsdk.model.manager.LocationManagerHelper.OnLocationListener;
import com.cuebiq.cuebiqsdk.model.persistence.PersistenceManagerFactory;
import com.cuebiq.cuebiqsdk.model.wrapper.Auth;
import com.cuebiq.cuebiqsdk.model.wrapper.BluetoothDevice;
import com.cuebiq.cuebiqsdk.model.wrapper.CoverageSettings;
import com.cuebiq.cuebiqsdk.model.wrapper.Device;
import com.cuebiq.cuebiqsdk.model.wrapper.Event;
import com.cuebiq.cuebiqsdk.model.wrapper.GDPRConsent;
import com.cuebiq.cuebiqsdk.model.wrapper.Geo;
import com.cuebiq.cuebiqsdk.model.wrapper.GeoLocationStats;
import com.cuebiq.cuebiqsdk.model.wrapper.Information;
import com.cuebiq.cuebiqsdk.model.wrapper.IpAddress;
import com.cuebiq.cuebiqsdk.model.wrapper.ServerResponseV2;
import com.cuebiq.cuebiqsdk.model.wrapper.TrackRequest;
import com.cuebiq.cuebiqsdk.model.wrapper.Wifi;
import com.cuebiq.cuebiqsdk.receiver.CoverageReceiver;
import com.cuebiq.cuebiqsdk.utils.GDPRPopupConstants;
import com.cuebiq.cuebiqsdk.utils.Logger;
import com.cuebiq.cuebiqsdk.utils.Utils;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class CuebiqSDKImpl {
    public static final Gson GSON = new GsonBuilder().serializeSpecialFloatingPointValues().registerTypeAdapter(Auth.class, new AuthSerializer()).registerTypeAdapter(BluetoothDevice.class, new BluetoothDeviceSerializer()).registerTypeAdapter(CoverageSettings.class, new CoverageSettingsSerializer()).registerTypeAdapter(Device.class, new DeviceSerializer()).registerTypeAdapter(Event.class, new EventSerializer()).registerTypeAdapter(GeoLocationStats.class, new GeoLocationStatsSerializer()).registerTypeAdapter(Geo.class, new GeoSerializer()).registerTypeAdapter(Information.class, new InformationSerializer()).registerTypeAdapter(IpAddress.class, new IpAddressSerializer()).registerTypeAdapter(TrackRequest.class, new RequestSerializer()).registerTypeAdapter(ServerResponseV2.class, new ServerResponseSerializer()).registerTypeAdapter(Settings.class, new SettingsSerializer()).registerTypeAdapter(Wifi.class, new WifiSerializer()).registerTypeAdapter(GDPRConsent.class, new GDPRConsentSerializer()).create();
    private static HandlerThread mHandlerThread;
    private static boolean mLogEnabled = false;

    final class C15401 implements CoverageListener {
        final /* synthetic */ Context val$context;

        C15401(Context context) {
            this.val$context = context;
        }

        public final void onCountryCovered(CoverageSettings coverageSettings) {
            if (this.val$context == null) {
                CuebiqSDKImpl.log("Country covered but context is null.");
                return;
            }
            CoverageManager.get().setCoverageStatus(this.val$context, CoverageStatus.CHECKED);
            CoverageManager.get().updateCoverageSettings(this.val$context, coverageSettings);
            CuebiqSDKImpl.log("CuebiqSDK -> Start tracking");
            CuebiqSDKImpl.activateImmediately(this.val$context);
        }

        public final void onCountryNotCovered(int i) {
            if (this.val$context != null) {
                CoverageManager.get().scheduleCheckCoverage(this.val$context, (long) i);
                CuebiqSDKImpl.disableTracking(this.val$context);
            }
        }

        public final void onError(String str) {
            if (this.val$context != null) {
                CuebiqSDKImpl.disableTracking(this.val$context);
            }
        }
    }

    final class C15422 implements Runnable {
        final /* synthetic */ Context val$context;
        final /* synthetic */ String val$eventInfo1;
        final /* synthetic */ String val$eventInfo2;
        final /* synthetic */ String val$eventInfo3;
        final /* synthetic */ String val$eventInfo4;
        final /* synthetic */ String val$eventName;

        class C15411 implements OnLocationListener {
            C15411() {
            }

            public void onLocation(Location location) {
                if (CuebiqSDKImpl.getBeAudienceConfiguration(C15422.this.val$context).getTlowo() == 0 && location == null) {
                    CuebiqSDKImpl.log("CuebiqSDK -> Location not available. Skip tracking.");
                } else {
                    CustomEventManager.gatherCustomEvent(C15422.this.val$context, C15422.this.val$eventName, C15422.this.val$eventInfo1, C15422.this.val$eventInfo2, C15422.this.val$eventInfo3, C15422.this.val$eventInfo4, location);
                }
            }
        }

        C15422(Context context, String str, String str2, String str3, String str4, String str5) {
            this.val$context = context;
            this.val$eventName = str;
            this.val$eventInfo1 = str2;
            this.val$eventInfo2 = str3;
            this.val$eventInfo3 = str4;
            this.val$eventInfo4 = str5;
        }

        public final void run() {
            new LocationManagerHelper().getLocation(this.val$context, new C15411());
        }
    }

    final class C15443 implements CoverageListener {
        final /* synthetic */ Activity val$context;
        final /* synthetic */ String val$rationale;
        final /* synthetic */ int val$requestCode;

        C15443(Activity activity, String str, int i) {
            this.val$context = activity;
            this.val$rationale = str;
            this.val$requestCode = i;
        }

        public final void onCountryCovered(final CoverageSettings coverageSettings) {
            CoverageManager.get().setCoverageStatus(this.val$context, CoverageStatus.CHECKED);
            CoverageManager.get().updateCoverageSettings(this.val$context, coverageSettings);
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                public void run() {
                    CuebiqSDKImpl.checkGDPRConditions(C15443.this.val$context, C15443.this.val$rationale, C15443.this.val$requestCode, coverageSettings.isGDPRCountry());
                }
            });
        }

        public final void onCountryNotCovered(int i) {
            CoverageManager.get().scheduleCheckCoverage(this.val$context, (long) i);
            CuebiqSDKImpl.disableTracking(this.val$context);
        }

        public final void onError(String str) {
            CuebiqSDKImpl.disableTracking(this.val$context);
        }
    }

    final class C15464 implements OnClickListener {
        final /* synthetic */ Activity val$context;
        final /* synthetic */ String val$rationale;
        final /* synthetic */ int val$requestCode;

        class C15451 implements OnClickListener {
            C15451() {
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                CuebiqSDKImpl.log("GDPR compliance granted at second popup, save and send consent...");
                CuebiqSDKImpl.saveConsentAndRequestLocation(C15464.this.val$context, C15464.this.val$rationale, C15464.this.val$requestCode);
                CuebiqSDKImpl.sendConsentToServer(C15464.this.val$context, GDPRPopupConstants.SECOND.getPopupValue());
                CuebiqSDKImpl.activateImmediately(C15464.this.val$context.getApplicationContext());
            }
        }

        C15464(Activity activity, String str, int i) {
            this.val$context = activity;
            this.val$rationale = str;
            this.val$requestCode = i;
        }

        public final void onClick(DialogInterface dialogInterface, int i) {
            AlertDialog create = new Builder(this.val$context).setMessage(Html.fromHtml("Cuebiq is invested in the movement to drive innovation and enhance the quality of life across the globe.<br><br>We collect and process only de-identified data from users who consent to location data sharing via our partners apps for purposes related to advertising, attribution, analytics and research to support causes such as improving quality of life in underserved communities, natural disaster response, and smart city development through our Data for Good initiative.<br><br>Cuebiq may share the data with its trusted partners for similar purposes.<br>You can withdraw your consent to share location data with Cuebiq Inc. without losing any app features:\n<br>\n- From your device settings<br>\n- By contacting Cuebiq Inc.<br><br>\n\nFor more information visit Cuebiq's <a href=\"https://www.cuebiq.com/privacypolicy/\">Privacy Policy</a>.\n")).setPositiveButton("Proceed", new C15451()).setCancelable(false).create();
            create.show();
            ((TextView) create.findViewById(16908299)).setMovementMethod(LinkMovementMethod.getInstance());
        }
    }

    final class C15475 implements OnClickListener {
        final /* synthetic */ Activity val$context;
        final /* synthetic */ String val$rationale;
        final /* synthetic */ int val$requestCode;

        C15475(Activity activity, String str, int i) {
            this.val$context = activity;
            this.val$rationale = str;
            this.val$requestCode = i;
        }

        public final void onClick(DialogInterface dialogInterface, int i) {
            CuebiqSDKImpl.log("GDPR compliance granted at first popup, save and send consent...");
            CuebiqSDKImpl.saveConsentAndRequestLocation(this.val$context, this.val$rationale, this.val$requestCode);
            CuebiqSDKImpl.sendConsentToServer(this.val$context, GDPRPopupConstants.FIRST.getPopupValue());
            CuebiqSDKImpl.activateImmediately(this.val$context.getApplicationContext());
        }
    }

    final class C15486 implements OnClickListener {
        final /* synthetic */ Activity val$context;
        final /* synthetic */ int val$requestCode;

        C15486(Activity activity, int i) {
            this.val$context = activity;
            this.val$requestCode = i;
        }

        public final void onClick(DialogInterface dialogInterface, int i) {
            ActivityCompat.requestPermissions(this.val$context, new String[]{"android.permission.ACCESS_FINE_LOCATION"}, this.val$requestCode);
        }
    }

    final class C15497 implements Callback {
        final /* synthetic */ Context val$context;

        C15497(Context context) {
            this.val$context = context;
        }

        public final void onFailure(Call call, IOException iOException) {
            PersistenceManagerFactory.get().saveGDPRConsentSentSuccesfully(this.val$context, false);
        }

        public final void onResponse(Call call, Response response) throws IOException {
            if (response.isSuccessful()) {
                PersistenceManagerFactory.get().saveGDPRConsentSentSuccesfully(this.val$context, true);
            } else {
                PersistenceManagerFactory.get().saveGDPRConsentSentSuccesfully(this.val$context, false);
            }
        }
    }

    final class C15518 implements OnClickListener {
        final /* synthetic */ Activity val$context;

        class C15501 implements OnClickListener {
            C15501() {
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                Log.i("CuebiqSDK", "GDPR compliance granted at second popup.");
            }
        }

        C15518(Activity activity) {
            this.val$context = activity;
        }

        public final void onClick(DialogInterface dialogInterface, int i) {
            AlertDialog create = new Builder(this.val$context).setMessage(Html.fromHtml("Cuebiq is invested in the movement to drive innovation and enhance the quality of life across the globe.<br><br>We collect and process only de-identified data from users who consent to location data sharing via our partners apps for purposes related to advertising, attribution, analytics and research to support causes such as improving quality of life in underserved communities, natural disaster response, and smart city development through our Data for Good initiative.<br><br>Cuebiq may share the data with its trusted partners for similar purposes.<br>You can withdraw your consent to share location data with Cuebiq Inc. without losing any app features:\n<br>\n- From your device settings<br>\n- By contacting Cuebiq Inc.<br><br>\n\nFor more information visit Cuebiq's <a href=\"https://www.cuebiq.com/privacypolicy/\">Privacy Policy</a>.\n")).setPositiveButton("Proceed", new C15501()).setCancelable(false).create();
            create.show();
            ((TextView) create.findViewById(16908299)).setMovementMethod(LinkMovementMethod.getInstance());
        }
    }

    final class C15529 implements OnClickListener {
        C15529() {
        }

        public final void onClick(DialogInterface dialogInterface, int i) {
            Log.i("CuebiqSDK", "GDPR compliance granted at first popup.");
        }
    }

    public static void SDKCollection(Context context, boolean z) {
        PersistenceManagerFactory.get().setSDKCollectionEnabled(context, z);
        if (z) {
            initializeSDKafterOptIn(context);
        } else {
            disableTracking(context);
        }
    }

    @SuppressLint({"MissingPermission"})
    public static void activateImmediately(Context context) {
        boolean retrieveIsGDPRCountry = PersistenceManagerFactory.get().retrieveIsGDPRCountry(context);
        boolean retrieveGDPRComplianceAlreadyRun = PersistenceManagerFactory.get().retrieveGDPRComplianceAlreadyRun(context);
        if (!retrieveIsGDPRCountry || retrieveGDPRComplianceAlreadyRun) {
            FusedLocationProviderClient fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context);
            if (VERSION.SDK_INT <= 22 || context.checkSelfPermission("android.permission.ACCESS_FINE_LOCATION") == 0) {
                log("CuebiqSDK -> Start location updates!");
                try {
                    fusedLocationProviderClient.requestLocationUpdates(createLocationRequest(PersistenceManagerFactory.get().retrieveBeAudienceConfiguration(context)), PendingIntent.getBroadcast(context, 100, new Intent(context, CoverageReceiver.class), 134217728));
                    return;
                } catch (Exception e) {
                    e.printStackTrace();
                    return;
                }
            }
            log("LocationManager -> Permission about LOCATION is not granted. Unable to acquire location data.");
            return;
        }
        log("GDPR compliance not available. Stop collection.");
    }

    public static void checkCoverageAndActivateIfOpened(Context context) {
        CoverageManager.get().checkCoverage(context, false, new C15401(context));
    }

    private static void checkGDPRConditions(Activity activity, String str, int i, boolean z) {
        if (!z) {
            log("GDPR is not available in the current country, SDK can run.");
            requestLocationPermission(activity, str, i);
        } else if (PersistenceManagerFactory.get().retrieveGDPRComplianceAlreadyRun(activity)) {
            log("GDPR compliance granted, check location permission...");
            requestLocationPermission(activity, str, i);
            if (!PersistenceManagerFactory.get().retrieveGDPRConsentSentSuccessfully(activity)) {
                log("GDPR compliance consent has not been sent to server, retry now...");
                sendConsentToServer(activity, GDPRPopupConstants.INTERNET_CONNECTION.getPopupValue());
            }
        } else {
            log("GDPR flow managed by Cuebiq, show popups...");
            showStep1GDPRCompliance(activity, str, i);
        }
    }

    public static void collectCustomEvents(Context context, String str, String str2, String str3, String str4, String str5, Location location) {
        try {
            if (!Utils.isAndroidVersionNotSupported(getBeAudienceConfiguration(context).getAmvs())) {
                if (context == null) {
                    throw new IllegalStateException("You must provide a description for your custom event.");
                } else if (!PersistenceManagerFactory.get().isSDKCollectionEnabled(context)) {
                    log("CuebiqSDK -> SDK has explicit opt-out, stop tracking.");
                } else if (Utils.isOptedOut(context)) {
                    log("CuebiqSDK -> SDK is opted-out, events will not be tracked.");
                } else {
                    log("Custom Event -> Track custom event...");
                    if (location == null) {
                        new Handler(Looper.getMainLooper()).post(new C15422(context, str, str2, str3, str4, str5));
                    } else {
                        CustomEventManager.gatherCustomEvent(context, str, str2, str3, str4, str5, location);
                    }
                }
            }
        } catch (Throwable th) {
        }
    }

    public static void collectCustomPublisherID(Context context, String str) {
        PersistenceManagerFactory.get().saveCustomPublisherID(context, str);
    }

    private static LocationRequest createLocationRequest(Settings settings) {
        LocationRequest locationRequest = new LocationRequest();
        locationRequest.setInterval((long) ((settings.getLri() * 60) * 1000));
        locationRequest.setFastestInterval((long) ((settings.getLrf() * 60) * 1000));
        locationRequest.setSmallestDisplacement(settings.getLrsd());
        locationRequest.setMaxWaitTime((long) ((settings.getLrmw() * 60) * 1000));
        locationRequest.setPriority(102);
        return locationRequest;
    }

    public static void disableSDKCollection(Context context) {
        PersistenceManagerFactory.get().setSDKCollectionEnabled(context, false);
        disableTracking(context);
    }

    public static void disableTracking(Context context) {
        FusedLocationProviderClient fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context);
        if (VERSION.SDK_INT <= 22 || context.checkSelfPermission("android.permission.ACCESS_FINE_LOCATION") == 0) {
            log("CuebiqSDK -> Remove location updates!");
            try {
                fusedLocationProviderClient.removeLocationUpdates(PendingIntent.getBroadcast(context, 100, new Intent(context, CoverageReceiver.class), 134217728));
                return;
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
        log("LocationManager -> Permission about LOCATION is not granted. Unable to acquire location data.");
    }

    public static void enableLogging() {
        mLogEnabled = true;
    }

    public static void enableSDKCollection(Context context) {
        PersistenceManagerFactory.get().setSDKCollectionEnabled(context, true);
        initializeSDKafterOptIn(context);
    }

    public static Settings getBeAudienceConfiguration(Context context) {
        return PersistenceManagerFactory.get().retrieveBeAudienceConfiguration(context);
    }

    public static HandlerThread getWorkerThread() {
        return mHandlerThread;
    }

    static void initGDPRCompliance(Activity activity, String str, int i) {
        Object obj = null;
        log("Init GDPR compliance flow...");
        try {
            if (!"".equals(PersistenceManagerFactory.get().retrieveCountry(activity))) {
                obj = 1;
            }
            boolean retrieveIsGDPRCountry = PersistenceManagerFactory.get().retrieveIsGDPRCountry(activity);
            if (obj != null) {
                checkGDPRConditions(activity, str, i, retrieveIsGDPRCountry);
            } else {
                CoverageManager.get().checkCoverage(activity, false, new C15443(activity, str, i));
            }
        } catch (Exception e) {
        }
    }

    public static void initialize(Context context, String str, Environment environment) {
        if (!Utils.isAndroidVersionNotSupported(getBeAudienceConfiguration(context).getAmvs())) {
            if (context == null) {
                throw new IllegalStateException("Initialize params mustn't be null.");
            } else if (PersistenceManagerFactory.get().isSDKCollectionEnabled(context)) {
                if (mHandlerThread == null) {
                    mHandlerThread = new HandlerThread("CuebiqWorkerThread");
                }
                if (!mHandlerThread.isAlive()) {
                    mHandlerThread.start();
                }
                removeOldAlarms(context);
                if (str == null || str.isEmpty()) {
                    throw new IllegalStateException("CuebiqSDK: AppKey must not be null or empty.");
                }
                PersistenceManagerFactory.get().saveAppKey(context, str);
                ApiConfiguration.setEnvironment(environment);
                if (!CoverageManager.get().isCoverageOpened(context)) {
                    checkCoverageAndActivateIfOpened(context);
                } else if (!Utils.isOptedOut(context) && CoverageManager.get().isCoverageOpened(context)) {
                    activateImmediately(context);
                }
                Log.i("CuebiqSDK", "CuebiqSDK -> CuebiqSDK v5.1.0 initialized successfully!");
            } else {
                log("CuebiqSDK -> SDK has explicit opt-out, stop tracking.");
            }
        }
    }

    private static void initializeSDKafterOptIn(Context context) {
        try {
            String string = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData.getString(CuebiqSDKProvider.CUEBIQ_APPKEY);
            if (string == null || "".equals(string)) {
                log("CuebiqSDK AppKey must not be null. Did you forget to add it in your AndroidManifest as metadata?");
                string = "aWildcard";
            }
            CuebiqSDK.initialize(context, string);
        } catch (NameNotFoundException e) {
            log("...Failed to re-initialize CuebiqSDK: " + e.getMessage());
        } catch (NullPointerException e2) {
            log("...Failed to re-initialize CuebiqSDK: " + e2.getMessage());
        }
    }

    public static void log(String str) {
        if (mLogEnabled) {
            Logger.log(str);
        }
    }

    public static void onRequestPermissionsResult(Context context) {
        activateImmediately(context);
    }

    private static void removeOldAlarms(Context context) {
        try {
            Intent intent = new Intent(context, CoverageReceiver.class);
            intent.putExtra(CoverageReceiver.REQUEST_CODE_KEY, 111);
            PendingIntent broadcast = PendingIntent.getBroadcast(context, 111, intent, 134217728);
            PendingIntent service = PendingIntent.getService(context, 111, new Intent(context, Collector.class), 134217728);
            AlarmManager alarmManager = (AlarmManager) context.getSystemService(NotificationCompat.CATEGORY_ALARM);
            alarmManager.cancel(broadcast);
            alarmManager.cancel(service);
        } catch (Exception e) {
        }
    }

    private static void requestLocationPermission(Activity activity, String str, int i) {
        try {
            if (ContextCompat.checkSelfPermission(activity, "android.permission.ACCESS_FINE_LOCATION") == 0) {
                return;
            }
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity, "android.permission.ACCESS_FINE_LOCATION")) {
                new Builder(activity).setMessage(str).setPositiveButton("Proceed", new C15486(activity, i)).setCancelable(false).create().show();
                return;
            }
            ActivityCompat.requestPermissions(activity, new String[]{"android.permission.ACCESS_FINE_LOCATION"}, i);
        } catch (Exception e) {
        }
    }

    private static void saveConsentAndRequestLocation(Activity activity, String str, int i) {
        PersistenceManagerFactory.get().saveGDPRComplianceAlreadyRun(activity);
        requestLocationPermission(activity, str, i);
    }

    private static void sendConsentToServer(Context context, int i) {
        String retrieveAppKey = PersistenceManagerFactory.get().retrieveAppKey(context);
        String retrieveGoogleAdvID = PersistenceManagerFactory.get().retrieveGoogleAdvID(context);
        if (!"".equals(retrieveGoogleAdvID)) {
            Injection.provideNetworkLayer().callAsync(new GDPRConsentRequest(retrieveAppKey, new GDPRConsent(retrieveGoogleAdvID, i)), new C15497(context));
        }
    }

    public static void setUserCOPAProtected(Context context, boolean z) {
        PersistenceManagerFactory.get().setUserCOPAProtected(context, z);
        if (z) {
            disableTracking(context);
        } else {
            initializeSDKafterOptIn(context);
        }
    }

    private static void showStep1GDPRCompliance(Activity activity, String str, int i) {
        try {
            AlertDialog create = new Builder(activity).setMessage(Html.fromHtml("Location information allows us to provide you with an exceptional user experience.<br><br>By clicking \"Allow\" on the location permission you consent to share it with <a href=\"https://www.cuebiq.com/privacypolicy/\">Cuebiq</a> for purposes related to enhancing your advertising experience, measuring ads performance and provide analytics, including research to benefit non-profit causes.<br><br>You are not required to consent and you can withdraw it at any time.")).setPositiveButton("Proceed", new C15475(activity, str, i)).setNeutralButton("More Info on Cuebiq", new C15464(activity, str, i)).setCancelable(false).create();
            create.show();
            ((TextView) create.findViewById(16908299)).setMovementMethod(LinkMovementMethod.getInstance());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void userGaveGDPRConsent(Context context) {
        PersistenceManagerFactory.get().saveGDPRComplianceAlreadyRun(context);
        sendConsentToServer(context, GDPRPopupConstants.PUBLISHER.getPopupValue());
        activateImmediately(context);
    }

    public static void viewGDPRFlow(Activity activity) {
        try {
            AlertDialog create = new Builder(activity).setMessage(Html.fromHtml("*** TEST MODE - DO NOT CALL THIS METHOD IN PRODUCTION ***<br><br>Location information allows us to provide you with an exceptional user experience.<br><br>By clicking \"Allow\" on the location permission you consent to share it with <a href=\"https://www.cuebiq.com/privacypolicy/\">Cuebiq</a> for purposes related to enhancing your advertising experience, measuring ads performance and provide analytics, including research to benefit non-profit causes.<br><br>You are not required to consent and you can withdraw it at any time.")).setPositiveButton("Proceed", new C15529()).setNeutralButton("More Info on Cuebiq", new C15518(activity)).setCancelable(false).create();
            create.show();
            ((TextView) create.findViewById(16908299)).setMovementMethod(LinkMovementMethod.getInstance());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
