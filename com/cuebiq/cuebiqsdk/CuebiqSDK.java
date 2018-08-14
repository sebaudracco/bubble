package com.cuebiq.cuebiqsdk;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import com.cuebiq.cuebiqsdk.api.Environment;

public class CuebiqSDK {
    public static void collectCustomEvents(Context context, String str, String str2, String str3, String str4, String str5) {
        collectCustomEvents(context, str, str2, str3, str4, str5, null);
    }

    public static void collectCustomEvents(Context context, String str, String str2, String str3, String str4, String str5, Location location) {
        CuebiqSDKImpl.collectCustomEvents(context, str, str2, str3, str4, str5, location);
    }

    public static void collectCustomPublisherID(Context context, String str) {
        CuebiqSDKImpl.collectCustomPublisherID(context, str);
    }

    public static void disableSDKCollection(Context context) {
        CuebiqSDKImpl.disableSDKCollection(context.getApplicationContext());
    }

    public static void enableLogging() {
        CuebiqSDKImpl.enableLogging();
    }

    public static void enableSDKCollection(Context context) {
        CuebiqSDKImpl.enableSDKCollection(context.getApplicationContext());
    }

    public static void initGDPRCompliance(Activity activity, String str, int i) {
        CuebiqSDKImpl.initGDPRCompliance(activity, str, i);
    }

    public static void initialize(Context context, String str) {
        CuebiqSDKImpl.initialize(context, str, Environment.PRODUCTION);
    }

    public static void onRequestPermissionsResult(Context context) {
        CuebiqSDKImpl.onRequestPermissionsResult(context.getApplicationContext());
    }

    public static void setUserCOPAProtected(Context context, boolean z) {
        CuebiqSDKImpl.setUserCOPAProtected(context, z);
    }

    public static void userGaveGDPRConsent(Context context) {
        CuebiqSDKImpl.userGaveGDPRConsent(context.getApplicationContext());
    }

    public static void viewGDPRFlow(Activity activity) {
        CuebiqSDKImpl.viewGDPRFlow(activity);
    }
}
