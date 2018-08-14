package com.inmobi.sdk;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.location.Location;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.SystemClock;
import com.google.android.gms.plus.PlusShare;
import com.inmobi.ads.C3063j;
import com.inmobi.ads.C3072m;
import com.inmobi.commons.core.configs.C3121b;
import com.inmobi.commons.core.p114b.C3113c;
import com.inmobi.commons.core.p115d.C3135c;
import com.inmobi.commons.core.utilities.C3152a;
import com.inmobi.commons.core.utilities.C3152a.C2982b;
import com.inmobi.commons.core.utilities.C3156e;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.commons.core.utilities.info.C3163e;
import com.inmobi.commons.core.utilities.uid.C3168c;
import com.inmobi.commons.core.utilities.uid.ImIdShareBroadCastReceiver;
import com.inmobi.commons.p112a.C3105a;
import com.inmobi.commons.p112a.C3106b;
import com.inmobi.rendering.p118a.C3213c;
import com.inmobi.signals.C3277o;
import com.mobfox.sdk.networking.RequestParams;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public final class InMobiSdk {
    private static final String TAG = InMobiSdk.class.getSimpleName();

    static class C32371 implements Runnable {
        C32371() {
        }

        public void run() {
            try {
                C3072m.m9903a().m9915b();
                C3072m.m9903a().m9918d();
            } catch (Exception e) {
                Logger.m10359a(InternalLogLevel.INTERNAL, InMobiSdk.TAG, "Error in starting Asset Cache : (" + e.getMessage() + ")");
            }
        }
    }

    static class C32382 implements C2982b {
        C32382() {
        }

        public void mo6162a(boolean z) {
            C3105a.m10075a(z);
            if (z) {
                try {
                    InMobiSdk.initComponents();
                    return;
                } catch (Exception e) {
                    Logger.m10359a(InternalLogLevel.INTERNAL, InMobiSdk.TAG, "Encountered unexpected error in the onFocusChanged handler: " + e.getMessage());
                    Logger.m10359a(InternalLogLevel.DEBUG, InMobiSdk.TAG, "SDK encountered an unexpected error; some components may not work as advertised");
                    return;
                }
            }
            InMobiSdk.deInitComponents();
        }
    }

    static class C32393 extends Thread {
        C32393() {
        }

        public void run() {
            int i = 0;
            String[] strArr = new String[]{"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_WIFI_STATE", "android.permission.CHANGE_WIFI_STATE", "android.permission.VIBRATE", "android.permission.READ_CALENDAR", "android.permission.WRITE_CALENDAR", "com.google.android.gms.permission.ACTIVITY_RECOGNITION"};
            StringBuilder stringBuilder = new StringBuilder("Permissions granted to SDK are :\nandroid.permission.INTERNET\nandroid.permission.ACCESS_NETWORK_STATE");
            int length = strArr.length;
            while (i < length) {
                String str = strArr[i];
                if (C3156e.m10410a(C3105a.m10078b(), "ads", str)) {
                    stringBuilder.append("\n").append(str);
                }
                i++;
            }
            Logger.m10359a(InternalLogLevel.DEBUG, InMobiSdk.TAG, stringBuilder.toString());
        }
    }

    static class C32404 implements Runnable {
        C32404() {
        }

        public void run() {
            try {
                C3168c.m10513a().m10523b();
                C3168c.m10513a().m10525d();
                C3121b.m10178a().m10191b();
                C3213c.m10698a().m10714b();
                C3113c.m10129a();
                C3135c.m10255a().m10282b();
                C3277o.m10989a().m10991b();
                C3063j.m9855a().m9875b();
            } catch (Exception e) {
                Logger.m10359a(InternalLogLevel.INTERNAL, InMobiSdk.TAG, "Encountered unexpected error in starting SDK components: " + e.getMessage());
                Logger.m10359a(InternalLogLevel.DEBUG, InMobiSdk.TAG, "SDK encountered unexpected error while starting internal components");
            }
        }
    }

    public enum AgeGroup {
        BELOW_18("below18"),
        BETWEEN_18_AND_20("between18and20"),
        BETWEEN_21_AND_24("between21and24"),
        BETWEEN_25_AND_34("between25and34"),
        BETWEEN_35_AND_54("between35and54"),
        ABOVE_55("above55");
        
        private String f8147a;

        private AgeGroup(String str) {
            this.f8147a = str;
        }

        public String toString() {
            return this.f8147a;
        }
    }

    public enum Education {
        HIGH_SCHOOL_OR_LESS("highschoolorless"),
        COLLEGE_OR_GRADUATE("collegeorgraduate"),
        POST_GRADUATE_OR_ABOVE("postgraduateorabove");
        
        private String f8148a;

        private Education(String str) {
            this.f8148a = str;
        }

        public String toString() {
            return this.f8148a;
        }
    }

    public enum Ethnicity {
        AFRICAN_AMERICAN("africanamerican"),
        ASIAN("asian"),
        CAUCASIAN("caucasian"),
        HISPANIC("hispanic"),
        OTHER("other");
        
        private String f8149a;

        private Ethnicity(String str) {
            this.f8149a = str;
        }

        public String toString() {
            return this.f8149a;
        }
    }

    public enum Gender {
        FEMALE("f"),
        MALE(RequestParams.f9036M);
        
        private String f8150a;

        private Gender(String str) {
            this.f8150a = str;
        }

        public String toString() {
            return this.f8150a;
        }
    }

    public enum HouseHoldIncome {
        BELOW_USD_5K("belowusd5k"),
        BETWEEN_USD_5K_AND_10K("betweenusd5kand10k"),
        BETWEEN_USD_10K_AND_15K("betweenusd10kand15k"),
        BETWEEN_USD_15K_AND_20K("betweenusd15kand20k"),
        BETWEEN_USD_20K_AND_25K("betweenusd20kand25k"),
        BETWEEN_USD_25K_AND_50K("betweenusd25kand50k"),
        BETWEEN_USD_50K_AND_75K("betweenusd50kand75k"),
        BETWEEN_USD_75K_AND_100K("betweenusd75kand100k"),
        BETWEEN_USD_100K_AND_150K("betweenusd100kand150k"),
        ABOVE_USD_150K("aboveusd150k");
        
        private String f8151a;

        private HouseHoldIncome(String str) {
            this.f8151a = str;
        }

        public String toString() {
            return this.f8151a;
        }
    }

    public enum ImIdType {
        LOGIN,
        SESSION
    }

    public enum LogLevel {
        NONE,
        ERROR,
        DEBUG
    }

    public static void init(Context context, String str) {
        String str2;
        Map hashMap;
        Exception exception;
        long elapsedRealtime = SystemClock.elapsedRealtime();
        try {
            if (VERSION.SDK_INT < C3106b.m10096b()) {
                Logger.m10359a(InternalLogLevel.ERROR, TAG, "The minimum supported Android API level is " + C3106b.m10096b() + ", SDK could not be initialized.");
            } else if (context == null) {
                Logger.m10359a(InternalLogLevel.ERROR, TAG, "Context supplied as null, SDK could not be initialized.");
            } else {
                if (str != null) {
                    if (str.trim().length() != 0) {
                        Intent intent = new Intent();
                        intent.setClassName(context.getPackageName(), "com.inmobi.rendering.InMobiAdActivity");
                        if (context.getPackageManager().resolveActivity(intent, 65536) == null) {
                            Logger.m10359a(InternalLogLevel.ERROR, TAG, "The activity com.inmobi.rendering.InMobiAdActivity not present in AndroidManifest. SDK could not be initialized.");
                            return;
                        } else if (!isBroadcastReceiverDeclared(context)) {
                            Logger.m10359a(InternalLogLevel.ERROR, TAG, "The BroadcastReceiver com.inmobi.commons.core.utilities.uid.ImIdShareBroadCastReceiver not present in AndroidManifest. SDK could not be initialized.");
                            return;
                        } else if (C3156e.m10410a(context, "ads", "android.permission.INTERNET") && C3156e.m10410a(context, "ads", "android.permission.ACCESS_NETWORK_STATE")) {
                            if (!(C3156e.m10410a(context, "ads", "android.permission.ACCESS_COARSE_LOCATION") || C3156e.m10410a(context, "ads", "android.permission.ACCESS_FINE_LOCATION"))) {
                                Logger.m10359a(InternalLogLevel.ERROR, TAG, "Please grant the location permissions (ACCESS_COARSE_LOCATION or ACCESS_FINE_LOCATION, or both) for better ad targeting.");
                            }
                            String trim = str.trim();
                            try {
                                if (!(trim.length() == 32 || trim.length() == 36)) {
                                    Logger.m10359a(InternalLogLevel.DEBUG, TAG, "Invalid account id passed to init. Please provide a valid account id.");
                                }
                                if (C3105a.m10076a()) {
                                    Logger.m10359a(InternalLogLevel.INTERNAL, TAG, "SDK already initialized");
                                    try {
                                        C3135c.m10255a().m10280a("root", "InitRequested", null);
                                        return;
                                    } catch (Exception e) {
                                        Logger.m10359a(InternalLogLevel.INTERNAL, TAG, "Error in submitting telemetry event : (" + e.getMessage() + ")");
                                        return;
                                    }
                                }
                                if (hasSdkVersionChanged(context)) {
                                    C3106b.m10095a(context, C3242a.m10807a(context));
                                    C3106b.m10094a(context, C3106b.m10098c());
                                    resetMediaCache(context.getApplicationContext());
                                }
                                C3105a.m10073a(context, trim);
                                C3121b.m10178a().m10191b();
                                C3135c.m10255a().m10282b();
                                if (C3106b.m10097b(context)) {
                                    List<String> b = C3242a.m10809b(context);
                                    for (String str22 : b) {
                                        sendDbDeletionTelemetryEvent(str22);
                                    }
                                    if (b.isEmpty()) {
                                        C3106b.m10095a(context, false);
                                    }
                                }
                                C3163e.m10450b();
                                initComponents();
                                C3121b.m10178a().m10193d();
                                new Thread(new C32371()).start();
                                if (context instanceof Activity) {
                                    C3152a a = C3152a.m10381a();
                                    if (a != null) {
                                        a.m10392a(new C32382());
                                    }
                                }
                                try {
                                    C3135c.m10255a().m10280a("root", "InitRequested", null);
                                } catch (Exception e2) {
                                    Logger.m10359a(InternalLogLevel.INTERNAL, TAG, "Error in submitting telemetry event : (" + e2.getMessage() + ")");
                                }
                                str22 = trim;
                                Logger.m10359a(InternalLogLevel.DEBUG, TAG, "InMobi SDK initialized with account id: " + str22);
                                try {
                                    hashMap = new HashMap();
                                    hashMap.put("initTime", Long.valueOf(SystemClock.elapsedRealtime() - elapsedRealtime));
                                    C3135c.m10255a().m10280a("root", "SdkInitialized", hashMap);
                                } catch (Exception e22) {
                                    Logger.m10359a(InternalLogLevel.INTERNAL, TAG, "Error in submitting telemetry event : (" + e22.getMessage() + ")");
                                }
                                printGrantedPermissions();
                            } catch (Exception e222) {
                                Exception exception2 = e222;
                                str22 = trim;
                                exception = exception2;
                                C3105a.m10071a(null);
                                Logger.m10359a(InternalLogLevel.ERROR, TAG, "SDK could not be initialized; an unexpected error was encountered");
                                Logger.m10359a(InternalLogLevel.INTERNAL, TAG, "Encountered unexpected error while initializing the SDK: " + exception.getMessage());
                                Logger.m10359a(InternalLogLevel.DEBUG, TAG, "InMobi SDK initialized with account id: " + str22);
                                hashMap = new HashMap();
                                hashMap.put("initTime", Long.valueOf(SystemClock.elapsedRealtime() - elapsedRealtime));
                                C3135c.m10255a().m10280a("root", "SdkInitialized", hashMap);
                                printGrantedPermissions();
                            }
                        } else {
                            Logger.m10359a(InternalLogLevel.ERROR, TAG, "Please grant the mandatory permissions : INTERNET and ACCESS_NETWORK_STATE, SDK could not be initialized.");
                            return;
                        }
                    }
                }
                Logger.m10359a(InternalLogLevel.ERROR, TAG, "Account id cannot be null or empty. Please provide a valid account id.");
            }
        } catch (Exception e2222) {
            exception = e2222;
            str22 = str;
            C3105a.m10071a(null);
            Logger.m10359a(InternalLogLevel.ERROR, TAG, "SDK could not be initialized; an unexpected error was encountered");
            Logger.m10359a(InternalLogLevel.INTERNAL, TAG, "Encountered unexpected error while initializing the SDK: " + exception.getMessage());
            Logger.m10359a(InternalLogLevel.DEBUG, TAG, "InMobi SDK initialized with account id: " + str22);
            hashMap = new HashMap();
            hashMap.put("initTime", Long.valueOf(SystemClock.elapsedRealtime() - elapsedRealtime));
            C3135c.m10255a().m10280a("root", "SdkInitialized", hashMap);
            printGrantedPermissions();
        }
    }

    private static void sendDbDeletionTelemetryEvent(String str) {
        if (C3105a.m10076a()) {
            try {
                Map hashMap = new HashMap();
                hashMap.put("filename", str);
                hashMap.put(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_DESCRIPTION, "DB Deleted : " + str);
                C3135c.m10255a().m10280a("ads", "PersistentDataCleanFail", hashMap);
            } catch (Exception e) {
                Logger.m10359a(InternalLogLevel.INTERNAL, TAG, "Error in submitting telemetry event : (" + e.getMessage() + ")");
            }
        }
    }

    private static boolean hasSdkVersionChanged(Context context) {
        return C3106b.m10093a(context) == null || !C3106b.m10093a(context).equals(C3106b.m10098c());
    }

    private static boolean isBroadcastReceiverDeclared(Context context) {
        try {
            return context.getPackageManager().getReceiverInfo(new ComponentName(context, ImIdShareBroadCastReceiver.class), 65536).enabled;
        } catch (NameNotFoundException e) {
            return false;
        }
    }

    private static void printGrantedPermissions() {
        new C32393().start();
    }

    private static void initComponents() {
        HandlerThread handlerThread = new HandlerThread("initComponents");
        handlerThread.start();
        new Handler(handlerThread.getLooper()).post(new C32404());
    }

    private static void deInitComponents() {
        try {
            C3121b.m10178a().m10192c();
            C3135c.m10255a().m10284c();
            C3277o.m10989a().m10992c();
            C3063j.m9855a().m9877c();
            C3072m.m9903a().m9917c();
        } catch (Exception e) {
            Logger.m10359a(InternalLogLevel.INTERNAL, TAG, "Encountered unexpected error in stopping SDK components; " + e.getMessage());
            Logger.m10359a(InternalLogLevel.ERROR, TAG, "SDK encountered unexpected error while stopping internal components");
        }
    }

    private static void resetMediaCache(Context context) {
        File b = C3105a.m10079b(context);
        C3105a.m10074a(b, null);
        C3105a.m10083c(context);
        if (!b.mkdir() && !b.isDirectory()) {
            Logger.m10359a(InternalLogLevel.INTERNAL, TAG, "Cannot create media cache directory");
        }
    }

    public static String getVersion() {
        return C3106b.m10098c();
    }

    public static void setLogLevel(LogLevel logLevel) {
        switch (logLevel) {
            case NONE:
                Logger.m10358a(InternalLogLevel.NONE);
                return;
            case ERROR:
                Logger.m10358a(InternalLogLevel.ERROR);
                return;
            case DEBUG:
                Logger.m10358a(InternalLogLevel.DEBUG);
                return;
            default:
                return;
        }
    }

    public static final void addIdType(ImIdType imIdType, String str) {
        if (imIdType == ImIdType.LOGIN) {
            C3163e.m10477n(str);
        } else if (imIdType == ImIdType.SESSION) {
            C3163e.m10479o(str);
        }
    }

    public static final void removeIdType(ImIdType imIdType) {
        if (imIdType == ImIdType.LOGIN) {
            C3163e.m10477n(null);
        } else if (imIdType == ImIdType.SESSION) {
            C3163e.m10479o(null);
        }
    }

    public static final void setAge(int i) {
        C3163e.m10446a(i);
    }

    public static final void setAgeGroup(AgeGroup ageGroup) {
        C3163e.m10448a(ageGroup.toString().toLowerCase(Locale.ENGLISH));
    }

    public static final void setAreaCode(String str) {
        C3163e.m10452b(str);
    }

    public static final void setPostalCode(String str) {
        C3163e.m10455c(str);
    }

    public static final void setLocationWithCityStateCountry(String str, String str2, String str3) {
        C3163e.m10457d(str);
        C3163e.m10459e(str2);
        C3163e.m10461f(str3);
    }

    public static final void setYearOfBirth(int i) {
        C3163e.m10451b(i);
    }

    public static final void setGender(Gender gender) {
        C3163e.m10463g(gender.toString().toLowerCase(Locale.ENGLISH));
    }

    public static final void setEthnicity(Ethnicity ethnicity) {
        C3163e.m10465h(ethnicity.toString().toLowerCase(Locale.ENGLISH));
    }

    public static final void setEducation(Education education) {
        C3163e.m10467i(education.toString().toLowerCase(Locale.ENGLISH));
    }

    public static final void setLanguage(String str) {
        C3163e.m10469j(str);
    }

    public static final void setIncome(int i) {
        C3163e.m10454c(i);
    }

    public static final void setHouseHoldIncome(HouseHoldIncome houseHoldIncome) {
        C3163e.m10471k(houseHoldIncome.toString().toLowerCase(Locale.ENGLISH));
    }

    public static final void setInterests(String str) {
        C3163e.m10473l(str);
    }

    public static final void setNationality(String str) {
        C3163e.m10475m(str);
    }

    public static final void setLocation(Location location) {
        C3163e.m10447a(location);
    }
}
