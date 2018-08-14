package com.onesignal;

import android.app.Activity;
import android.app.Application;
import android.app.NotificationManager;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.util.Base64;
import android.util.Log;
import com.github.lzyzsd.jsbridge.BridgeUtil;
import com.mobfox.sdk.networking.RequestParams;
import com.mopub.common.AdType;
import com.onesignal.OSNotificationAction.ActionType;
import com.onesignal.PushRegistrator.RegisteredHandler;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class OneSignal {
    static final long MIN_ON_FOCUS_TIME = 60;
    private static final long MIN_ON_SESSION_TIME = 30;
    public static final String VERSION = "030505";
    private static int androidParamsReties = 0;
    static Context appContext;
    static String appId;
    private static JSONObject awl;
    private static boolean awlFired;
    private static OSPermissionState currentPermissionState;
    private static OSSubscriptionState currentSubscriptionState;
    private static int deviceType;
    private static boolean foreground;
    private static boolean getTagsCall;
    private static IAPUpdateJob iapUpdateJob;
    private static IdsAvailableHandler idsAvailableHandler;
    static boolean initDone;
    private static LocationPoint lastLocationPoint;
    static OSPermissionState lastPermissionState;
    private static String lastRegistrationId;
    static OSSubscriptionState lastSubscriptionState;
    private static long lastTrackedFocusTime = 1;
    private static boolean locationFired;
    private static LOG_LEVEL logCatLevel = LOG_LEVEL.WARN;
    static boolean mEnterp;
    private static String mGoogleProjectNumber;
    private static boolean mGoogleProjectNumberIsRemote;
    static Builder mInitBuilder;
    private static AdvertisingIdentifierProvider mainAdIdProvider = new AdvertisingIdProviderGPS();
    private static OSUtils osUtils;
    private static GetTagsHandler pendingGetTagsHandler;
    private static OSObservable<OSPermissionObserver, OSPermissionStateChanges> permissionStateChangesObserver;
    private static HashSet<String> postedOpenedNotifIds = new HashSet();
    private static boolean promptedLocation;
    private static boolean registerForPushFired;
    public static String sdkType = "native";
    private static boolean sendAsSession;
    static boolean shareLocation = true;
    private static int subscribableStatus;
    private static OSObservable<OSSubscriptionObserver, OSSubscriptionStateChanges> subscriptionStateChangesObserver;
    private static TrackAmazonPurchase trackAmazonPurchase;
    private static TrackGooglePurchase trackGooglePurchase;
    private static long unSentActiveTime = -1;
    private static Collection<JSONArray> unprocessedOpenedNotifis = new ArrayList();
    private static String userId = null;
    private static LOG_LEVEL visualLogLevel = LOG_LEVEL.NONE;
    private static boolean waitingToPostStateSync;

    static class C38931 implements LocationHandler {
        C38931() {
        }

        public void complete(LocationPoint point) {
            OneSignal.lastLocationPoint = point;
            OneSignal.locationFired = true;
            OneSignal.registerUser();
        }
    }

    static class C38942 implements RegisteredHandler {
        C38942() {
        }

        public void complete(String id, int status) {
            if (status < 1) {
                if (OneSignalStateSynchronizer.getRegistrationId() == null && (OneSignal.subscribableStatus == 1 || OneSignal.subscribableStatus < -6)) {
                    OneSignal.subscribableStatus = status;
                }
            } else if (OneSignal.subscribableStatus < -6) {
                OneSignal.subscribableStatus = status;
            }
            OneSignal.lastRegistrationId = id;
            OneSignal.registerForPushFired = true;
            OneSignal.getCurrentSubscriptionState(OneSignal.appContext).setPushToken(id);
            OneSignal.registerUser();
        }
    }

    static class C38963 extends ResponseHandler {

        class C38951 implements Runnable {
            C38951() {
            }

            public void run() {
                try {
                    int sleepTime = (OneSignal.androidParamsReties * 10000) + 30000;
                    if (sleepTime > 90000) {
                        sleepTime = 90000;
                    }
                    OneSignal.Log(LOG_LEVEL.INFO, "Failed to get Android parameters, trying again in " + (sleepTime / 1000) + " seconds.");
                    Thread.sleep((long) sleepTime);
                } catch (Throwable th) {
                }
                OneSignal.access$1108();
                OneSignal.makeAndroidParamsRequest();
            }
        }

        C38963() {
        }

        void onFailure(int statusCode, String response, Throwable throwable) {
            new Thread(new C38951(), "OS_PARAMS_REQUEST").start();
        }

        void onSuccess(String response) {
            try {
                JSONObject responseJson = new JSONObject(response);
                if (responseJson.has("android_sender_id")) {
                    OneSignal.mGoogleProjectNumberIsRemote = true;
                    OneSignal.mGoogleProjectNumber = responseJson.getString("android_sender_id");
                }
                OneSignal.mEnterp = responseJson.optBoolean("enterp", false);
                OneSignal.awl = responseJson.getJSONObject("awl_list");
            } catch (Throwable t) {
                t.printStackTrace();
            }
            OneSignal.awlFired = true;
            OneSignal.registerForPushToken();
        }
    }

    static class C38985 extends ResponseHandler {
        C38985() {
        }

        void onFailure(int statusCode, String response, Throwable throwable) {
            OneSignal.logHttpError("sending on_focus Failed", statusCode, throwable, response);
        }

        void onSuccess(String response) {
            OneSignal.SaveUnsentActiveTime(0);
        }
    }

    static class C38996 implements Runnable {
        C38996() {
        }

        public void run() {
            UserState userState = OneSignalStateSynchronizer.getNewUserState();
            String packageName = OneSignal.appContext.getPackageName();
            PackageManager packageManager = OneSignal.appContext.getPackageManager();
            userState.set("app_id", OneSignal.appId);
            userState.set("identifier", OneSignal.lastRegistrationId);
            String adId = OneSignal.mainAdIdProvider.getIdentifier(OneSignal.appContext);
            if (adId != null) {
                userState.set("ad_id", adId);
            }
            userState.set("device_os", VERSION.RELEASE);
            userState.set("timezone", Integer.valueOf(OneSignal.getTimeZoneOffset()));
            userState.set(SchemaSymbols.ATTVAL_LANGUAGE, OSUtils.getCorrectedLanguage());
            userState.set("sdk", OneSignal.VERSION);
            userState.set("sdk_type", OneSignal.sdkType);
            userState.set("android_package", packageName);
            userState.set("device_model", Build.MODEL);
            userState.set("device_type", Integer.valueOf(OneSignal.deviceType));
            userState.setState("subscribableStatus", Integer.valueOf(OneSignal.subscribableStatus));
            userState.setState("androidPermission", Boolean.valueOf(OneSignal.areNotificationsEnabledForSubscribedState()));
            try {
                userState.set("game_version", Integer.valueOf(packageManager.getPackageInfo(packageName, 0).versionCode));
            } catch (NameNotFoundException e) {
            }
            try {
                List<PackageInfo> packList = packageManager.getInstalledPackages(0);
                JSONArray pkgs = new JSONArray();
                MessageDigest md = MessageDigest.getInstance("SHA-256");
                for (int i = 0; i < packList.size(); i++) {
                    md.update(((PackageInfo) packList.get(i)).packageName.getBytes());
                    String pck = Base64.encodeToString(md.digest(), 2);
                    if (OneSignal.awl.has(pck)) {
                        pkgs.put(pck);
                    }
                }
                userState.set("pkgs", pkgs);
            } catch (Throwable th) {
            }
            userState.set("net_type", OneSignal.osUtils.getNetType());
            userState.set("carrier", OneSignal.osUtils.getCarrierName());
            userState.set("rooted", Boolean.valueOf(RootToolsInternalMethods.isRooted()));
            if (OneSignal.lastLocationPoint != null) {
                userState.setLocation(OneSignal.lastLocationPoint);
            }
            OneSignalStateSynchronizer.postUpdate(userState, OneSignal.sendAsSession);
            OneSignal.waitingToPostStateSync = false;
            OneSignalChromeTab.setup(OneSignal.appContext, OneSignal.appId, OneSignal.userId, AdvertisingIdProviderGPS.getLastValue());
        }
    }

    static class C39029 implements Runnable {
        C39029() {
        }

        public void run() {
            OneSignal.internalFireIdsAvailableCallback();
        }
    }

    public static class Builder {
        Context mContext;
        boolean mDisableGmsMissingPrompt;
        OSInFocusDisplayOption mDisplayOption;
        boolean mDisplayOptionCarryOver;
        boolean mFilterOtherGCMReceivers;
        NotificationOpenedHandler mNotificationOpenedHandler;
        NotificationReceivedHandler mNotificationReceivedHandler;
        boolean mPromptLocation;
        boolean mUnsubscribeWhenNotificationsAreDisabled;

        private Builder() {
            this.mDisplayOption = OSInFocusDisplayOption.InAppAlert;
        }

        private Builder(Context context) {
            this.mDisplayOption = OSInFocusDisplayOption.InAppAlert;
            this.mContext = context;
        }

        private void setDisplayOptionCarryOver(boolean carryOver) {
            this.mDisplayOptionCarryOver = carryOver;
        }

        public Builder setNotificationOpenedHandler(NotificationOpenedHandler handler) {
            this.mNotificationOpenedHandler = handler;
            return this;
        }

        public Builder setNotificationReceivedHandler(NotificationReceivedHandler handler) {
            this.mNotificationReceivedHandler = handler;
            return this;
        }

        public Builder autoPromptLocation(boolean enable) {
            this.mPromptLocation = enable;
            return this;
        }

        public Builder disableGmsMissingPrompt(boolean disable) {
            this.mDisableGmsMissingPrompt = disable;
            return this;
        }

        public Builder inFocusDisplaying(OSInFocusDisplayOption displayOption) {
            OneSignal.getCurrentOrNewInitBuilder().mDisplayOptionCarryOver = false;
            this.mDisplayOption = displayOption;
            return this;
        }

        public Builder unsubscribeWhenNotificationsAreDisabled(boolean set) {
            this.mUnsubscribeWhenNotificationsAreDisabled = set;
            return this;
        }

        public Builder filterOtherGCMReceivers(boolean set) {
            this.mFilterOtherGCMReceivers = set;
            return this;
        }

        public void init() {
            OneSignal.init(this);
        }
    }

    public interface GetTagsHandler {
        void tagsAvailable(JSONObject jSONObject);
    }

    private static class IAPUpdateJob {
        boolean newAsExisting;
        ResponseHandler restResponseHandler;
        JSONArray toReport;

        IAPUpdateJob(JSONArray toReport) {
            this.toReport = toReport;
        }
    }

    public interface IdsAvailableHandler {
        void idsAvailable(String str, String str2);
    }

    public enum LOG_LEVEL {
        NONE,
        FATAL,
        ERROR,
        WARN,
        INFO,
        DEBUG,
        VERBOSE
    }

    public interface NotificationOpenedHandler {
        void notificationOpened(OSNotificationOpenResult oSNotificationOpenResult);
    }

    public interface NotificationReceivedHandler {
        void notificationReceived(OSNotification oSNotification);
    }

    public enum OSInFocusDisplayOption {
        None,
        InAppAlert,
        Notification
    }

    public interface PostNotificationResponseHandler {
        void onFailure(JSONObject jSONObject);

        void onSuccess(JSONObject jSONObject);
    }

    public static void clearOneSignalNotifications() {
        /* JADX: method processing error */
/*
Error: java.util.NoSuchElementException
	at java.util.HashMap$HashIterator.nextNode(HashMap.java:1431)
	at java.util.HashMap$KeyIterator.next(HashMap.java:1453)
	at jadx.core.dex.visitors.blocksmaker.BlockFinallyExtract.applyRemove(BlockFinallyExtract.java:535)
	at jadx.core.dex.visitors.blocksmaker.BlockFinallyExtract.extractFinally(BlockFinallyExtract.java:175)
	at jadx.core.dex.visitors.blocksmaker.BlockFinallyExtract.processExceptionHandler(BlockFinallyExtract.java:79)
	at jadx.core.dex.visitors.blocksmaker.BlockFinallyExtract.visit(BlockFinallyExtract.java:51)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:199)
*/
        /*
        r1 = appContext;
        if (r1 != 0) goto L_0x000d;
    L_0x0004:
        r1 = com.onesignal.OneSignal.LOG_LEVEL.ERROR;
        r3 = "OneSignal.init has not been called. Could not clear notifications.";
        Log(r1, r3);
    L_0x000c:
        return;
    L_0x000d:
        r1 = appContext;
        r3 = "notification";
        r11 = r1.getSystemService(r3);
        r11 = (android.app.NotificationManager) r11;
        r1 = appContext;
        r9 = com.onesignal.OneSignalDbHelper.getInstance(r1);
        r8 = 0;
        r0 = r9.getReadableDbWithRetries();	 Catch:{ Throwable -> 0x008a, all -> 0x00a9, Throwable -> 0x0099 }
        r1 = 1;	 Catch:{ Throwable -> 0x008a, all -> 0x00a9, Throwable -> 0x0099 }
        r2 = new java.lang.String[r1];	 Catch:{ Throwable -> 0x008a, all -> 0x00a9, Throwable -> 0x0099 }
        r1 = 0;	 Catch:{ Throwable -> 0x008a, all -> 0x00a9, Throwable -> 0x0099 }
        r3 = "android_notification_id";	 Catch:{ Throwable -> 0x008a, all -> 0x00a9, Throwable -> 0x0099 }
        r2[r1] = r3;	 Catch:{ Throwable -> 0x008a, all -> 0x00a9, Throwable -> 0x0099 }
        r1 = "notification";	 Catch:{ Throwable -> 0x008a, all -> 0x00a9, Throwable -> 0x0099 }
        r3 = "dismissed = 0 AND opened = 0";	 Catch:{ Throwable -> 0x008a, all -> 0x00a9, Throwable -> 0x0099 }
        r4 = 0;	 Catch:{ Throwable -> 0x008a, all -> 0x00a9, Throwable -> 0x0099 }
        r5 = 0;	 Catch:{ Throwable -> 0x008a, all -> 0x00a9, Throwable -> 0x0099 }
        r6 = 0;	 Catch:{ Throwable -> 0x008a, all -> 0x00a9, Throwable -> 0x0099 }
        r7 = 0;	 Catch:{ Throwable -> 0x008a, all -> 0x00a9, Throwable -> 0x0099 }
        r8 = r0.query(r1, r2, r3, r4, r5, r6, r7);	 Catch:{ Throwable -> 0x008a, all -> 0x00a9, Throwable -> 0x0099 }
        r1 = r8.moveToFirst();	 Catch:{ Throwable -> 0x008a, all -> 0x00a9, Throwable -> 0x0099 }
        if (r1 == 0) goto L_0x0054;	 Catch:{ Throwable -> 0x008a, all -> 0x00a9, Throwable -> 0x0099 }
    L_0x0040:
        r1 = "android_notification_id";	 Catch:{ Throwable -> 0x008a, all -> 0x00a9, Throwable -> 0x0099 }
        r1 = r8.getColumnIndex(r1);	 Catch:{ Throwable -> 0x008a, all -> 0x00a9, Throwable -> 0x0099 }
        r10 = r8.getInt(r1);	 Catch:{ Throwable -> 0x008a, all -> 0x00a9, Throwable -> 0x0099 }
        r11.cancel(r10);	 Catch:{ Throwable -> 0x008a, all -> 0x00a9, Throwable -> 0x0099 }
        r1 = r8.moveToNext();	 Catch:{ Throwable -> 0x008a, all -> 0x00a9, Throwable -> 0x0099 }
        if (r1 != 0) goto L_0x0040;
    L_0x0054:
        r15 = 0;
        r15 = r9.getWritableDbWithRetries();	 Catch:{ Throwable -> 0x008a, all -> 0x00a9, Throwable -> 0x0099 }
        r15.beginTransaction();	 Catch:{ Throwable -> 0x008a, all -> 0x00a9, Throwable -> 0x0099 }
        r14 = "opened = 0";	 Catch:{ Throwable -> 0x008a, all -> 0x00a9, Throwable -> 0x0099 }
        r13 = new android.content.ContentValues;	 Catch:{ Throwable -> 0x008a, all -> 0x00a9, Throwable -> 0x0099 }
        r13.<init>();	 Catch:{ Throwable -> 0x008a, all -> 0x00a9, Throwable -> 0x0099 }
        r1 = "dismissed";	 Catch:{ Throwable -> 0x008a, all -> 0x00a9, Throwable -> 0x0099 }
        r3 = 1;	 Catch:{ Throwable -> 0x008a, all -> 0x00a9, Throwable -> 0x0099 }
        r3 = java.lang.Integer.valueOf(r3);	 Catch:{ Throwable -> 0x008a, all -> 0x00a9, Throwable -> 0x0099 }
        r13.put(r1, r3);	 Catch:{ Throwable -> 0x008a, all -> 0x00a9, Throwable -> 0x0099 }
        r1 = "notification";	 Catch:{ Throwable -> 0x008a, all -> 0x00a9, Throwable -> 0x0099 }
        r3 = 0;	 Catch:{ Throwable -> 0x008a, all -> 0x00a9, Throwable -> 0x0099 }
        r15.update(r1, r13, r14, r3);	 Catch:{ Throwable -> 0x008a, all -> 0x00a9, Throwable -> 0x0099 }
        r15.setTransactionSuccessful();	 Catch:{ Throwable -> 0x008a, all -> 0x00a9, Throwable -> 0x0099 }
        if (r15 == 0) goto L_0x007e;
    L_0x007b:
        r15.endTransaction();	 Catch:{ Throwable -> 0x008a, all -> 0x00a9, Throwable -> 0x0099 }
    L_0x007e:
        r1 = 0;	 Catch:{ Throwable -> 0x008a, all -> 0x00a9, Throwable -> 0x0099 }
        r3 = appContext;	 Catch:{ Throwable -> 0x008a, all -> 0x00a9, Throwable -> 0x0099 }
        com.onesignal.BadgeCountUpdater.updateCount(r1, r3);	 Catch:{ Throwable -> 0x008a, all -> 0x00a9, Throwable -> 0x0099 }
        if (r8 == 0) goto L_0x000c;
    L_0x0086:
        r8.close();
        goto L_0x000c;
    L_0x008a:
        r12 = move-exception;
        r1 = com.onesignal.OneSignal.LOG_LEVEL.ERROR;	 Catch:{ Throwable -> 0x008a, all -> 0x00a9, Throwable -> 0x0099 }
        r3 = "Error marking all notifications as dismissed! ";	 Catch:{ Throwable -> 0x008a, all -> 0x00a9, Throwable -> 0x0099 }
        Log(r1, r3, r12);	 Catch:{ Throwable -> 0x008a, all -> 0x00a9, Throwable -> 0x0099 }
        if (r15 == 0) goto L_0x007e;
    L_0x0095:
        r15.endTransaction();	 Catch:{ Throwable -> 0x008a, all -> 0x00a9, Throwable -> 0x0099 }
        goto L_0x007e;
    L_0x0099:
        r12 = move-exception;
        r1 = com.onesignal.OneSignal.LOG_LEVEL.ERROR;	 Catch:{ all -> 0x00b0 }
        r3 = "Error canceling all notifications! ";	 Catch:{ all -> 0x00b0 }
        Log(r1, r3, r12);	 Catch:{ all -> 0x00b0 }
        if (r8 == 0) goto L_0x000c;
    L_0x00a4:
        r8.close();
        goto L_0x000c;
    L_0x00a9:
        r1 = move-exception;
        if (r15 == 0) goto L_0x00af;
    L_0x00ac:
        r15.endTransaction();	 Catch:{ Throwable -> 0x008a, all -> 0x00a9, Throwable -> 0x0099 }
    L_0x00af:
        throw r1;	 Catch:{ Throwable -> 0x008a, all -> 0x00a9, Throwable -> 0x0099 }
    L_0x00b0:
        r1 = move-exception;
        if (r8 == 0) goto L_0x00b6;
    L_0x00b3:
        r8.close();
    L_0x00b6:
        throw r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onesignal.OneSignal.clearOneSignalNotifications():void");
    }

    static /* synthetic */ int access$1108() {
        int i = androidParamsReties;
        androidParamsReties = i + 1;
        return i;
    }

    private static OSPermissionState getCurrentPermissionState(Context context) {
        if (context == null) {
            return null;
        }
        if (currentPermissionState == null) {
            currentPermissionState = new OSPermissionState(false);
            currentPermissionState.observable.addObserverStrong(new OSPermissionChangedInternalObserver());
        }
        return currentPermissionState;
    }

    private static OSPermissionState getLastPermissionState(Context context) {
        if (context == null) {
            return null;
        }
        if (lastPermissionState == null) {
            lastPermissionState = new OSPermissionState(true);
        }
        return lastPermissionState;
    }

    static OSObservable<OSPermissionObserver, OSPermissionStateChanges> getPermissionStateChangesObserver() {
        if (permissionStateChangesObserver == null) {
            permissionStateChangesObserver = new OSObservable("onOSPermissionChanged", true);
        }
        return permissionStateChangesObserver;
    }

    private static OSSubscriptionState getCurrentSubscriptionState(Context context) {
        if (context == null) {
            return null;
        }
        if (currentSubscriptionState == null) {
            currentSubscriptionState = new OSSubscriptionState(false, getCurrentPermissionState(context).getEnabled());
            getCurrentPermissionState(context).observable.addObserver(currentSubscriptionState);
            currentSubscriptionState.observable.addObserverStrong(new OSSubscriptionChangedInternalObserver());
        }
        return currentSubscriptionState;
    }

    private static OSSubscriptionState getLastSubscriptionState(Context context) {
        if (context == null) {
            return null;
        }
        if (lastSubscriptionState == null) {
            lastSubscriptionState = new OSSubscriptionState(true, false);
        }
        return lastSubscriptionState;
    }

    static OSObservable<OSSubscriptionObserver, OSSubscriptionStateChanges> getSubscriptionStateChangesObserver() {
        if (subscriptionStateChangesObserver == null) {
            subscriptionStateChangesObserver = new OSObservable("onOSSubscriptionChanged", true);
        }
        return subscriptionStateChangesObserver;
    }

    private static Builder getCurrentOrNewInitBuilder() {
        if (mInitBuilder == null) {
            mInitBuilder = new Builder();
        }
        return mInitBuilder;
    }

    public static Builder startInit(Context context) {
        return new Builder(context);
    }

    private static void init(Builder inBuilder) {
        if (getCurrentOrNewInitBuilder().mDisplayOptionCarryOver) {
            inBuilder.mDisplayOption = getCurrentOrNewInitBuilder().mDisplayOption;
        }
        mInitBuilder = inBuilder;
        Context context = mInitBuilder.mContext;
        mInitBuilder.mContext = null;
        try {
            Bundle bundle = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData;
            String sender_id = bundle.getString("onesignal_google_project_number");
            if (sender_id != null && sender_id.length() > 4) {
                sender_id = sender_id.substring(4);
            }
            init(context, sender_id, bundle.getString("onesignal_app_id"), mInitBuilder.mNotificationOpenedHandler, mInitBuilder.mNotificationReceivedHandler);
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    public static void init(Context context, String googleProjectNumber, String oneSignalAppId) {
        init(context, googleProjectNumber, oneSignalAppId, null, null);
    }

    public static void init(Context context, String googleProjectNumber, String oneSignalAppId, NotificationOpenedHandler notificationOpenedHandler) {
        init(context, googleProjectNumber, oneSignalAppId, notificationOpenedHandler, null);
    }

    public static void init(Context context, String googleProjectNumber, String oneSignalAppId, NotificationOpenedHandler notificationOpenedHandler, NotificationReceivedHandler notificationReceivedHandler) {
        mInitBuilder = getCurrentOrNewInitBuilder();
        mInitBuilder.mDisplayOptionCarryOver = false;
        mInitBuilder.mNotificationOpenedHandler = notificationOpenedHandler;
        mInitBuilder.mNotificationReceivedHandler = notificationReceivedHandler;
        if (!mGoogleProjectNumberIsRemote) {
            mGoogleProjectNumber = googleProjectNumber;
        }
        osUtils = new OSUtils();
        deviceType = osUtils.getDeviceType();
        subscribableStatus = osUtils.initializationChecker(deviceType, oneSignalAppId);
        if (subscribableStatus != -999) {
            if (initDone) {
                if (context != null) {
                    appContext = context.getApplicationContext();
                }
                if (mInitBuilder.mNotificationOpenedHandler != null) {
                    fireCallbackForOpenedNotifications();
                    return;
                }
                return;
            }
            boolean contextIsActivity = context instanceof Activity;
            foreground = contextIsActivity;
            appId = oneSignalAppId;
            appContext = context.getApplicationContext();
            saveFilterOtherGCMReceivers(mInitBuilder.mFilterOtherGCMReceivers);
            if (contextIsActivity) {
                ActivityLifecycleHandler.curActivity = (Activity) context;
                NotificationRestorer.asyncRestore(appContext);
                startSyncService();
            } else {
                ActivityLifecycleHandler.nextResumeIsFirstActivity = true;
            }
            lastTrackedFocusTime = SystemClock.elapsedRealtime();
            OneSignalStateSynchronizer.initUserState(appContext);
            if (VERSION.SDK_INT > 13) {
                ((Application) appContext).registerActivityLifecycleCallbacks(new ActivityLifecycleListener());
            } else {
                ActivityLifecycleListenerCompat.startListener();
            }
            try {
                Class.forName("com.amazon.device.iap.PurchasingListener");
                trackAmazonPurchase = new TrackAmazonPurchase(appContext);
            } catch (ClassNotFoundException e) {
            }
            String oldAppId = getSavedAppId();
            if (oldAppId == null) {
                BadgeCountUpdater.updateCount(0, appContext);
                SaveAppId(appId);
            } else if (!oldAppId.equals(appId)) {
                Log(LOG_LEVEL.DEBUG, "APP ID changed, clearing user id as it is no longer valid.");
                SaveAppId(appId);
                OneSignalStateSynchronizer.resetCurrentState();
            }
            OSPermissionChangedInternalObserver.handleInternalChanges(getCurrentPermissionState(appContext));
            if (foreground || getUserId() == null) {
                sendAsSession = isPastOnSessionTime();
                setLastSessionTime(System.currentTimeMillis());
                startRegistrationOrOnSession();
            }
            if (mInitBuilder.mNotificationOpenedHandler != null) {
                fireCallbackForOpenedNotifications();
            }
            if (TrackGooglePurchase.CanTrack(appContext)) {
                trackGooglePurchase = new TrackGooglePurchase(appContext);
            }
            initDone = true;
        }
    }

    private static void startRegistrationOrOnSession() {
        boolean z = false;
        if (!waitingToPostStateSync) {
            waitingToPostStateSync = true;
            registerForPushFired = false;
            if (sendAsSession) {
                locationFired = false;
            }
            startLocationUpdate();
            makeAndroidParamsRequest();
            if (promptedLocation || mInitBuilder.mPromptLocation) {
                z = true;
            }
            promptedLocation = z;
        }
    }

    private static void startLocationUpdate() {
        Context context = appContext;
        boolean z = mInitBuilder.mPromptLocation && !promptedLocation;
        LocationGMS.getLocation(context, z, new C38931());
    }

    private static void registerForPushToken() {
        PushRegistrator pushRegistrator;
        if (deviceType == 2) {
            pushRegistrator = new PushRegistratorADM();
        } else {
            pushRegistrator = new PushRegistratorGPS();
        }
        pushRegistrator.registerForPush(appContext, mGoogleProjectNumber, new C38942());
    }

    private static void makeAndroidParamsRequest() {
        if (awlFired) {
            registerForPushToken();
            return;
        }
        ResponseHandler responseHandler = new C38963();
        String awl_url = "apps/" + appId + "/android_params.js";
        String userId = getUserId();
        if (userId != null) {
            awl_url = awl_url + "?player_id=" + userId;
        }
        Log(LOG_LEVEL.DEBUG, "Starting request to get Android parameters.");
        OneSignalRestClient.get(awl_url, responseHandler);
    }

    private static void fireCallbackForOpenedNotifications() {
        for (JSONArray dataArray : unprocessedOpenedNotifis) {
            runNotificationOpenedCallback(dataArray, true, false);
        }
        unprocessedOpenedNotifis.clear();
    }

    public static void setLogLevel(LOG_LEVEL inLogCatLevel, LOG_LEVEL inVisualLogLevel) {
        logCatLevel = inLogCatLevel;
        visualLogLevel = inVisualLogLevel;
    }

    public static void setLogLevel(int inLogCatLevel, int inVisualLogLevel) {
        setLogLevel(getLogLevel(inLogCatLevel), getLogLevel(inVisualLogLevel));
    }

    private static LOG_LEVEL getLogLevel(int level) {
        switch (level) {
            case 0:
                return LOG_LEVEL.NONE;
            case 1:
                return LOG_LEVEL.FATAL;
            case 2:
                return LOG_LEVEL.ERROR;
            case 3:
                return LOG_LEVEL.WARN;
            case 4:
                return LOG_LEVEL.INFO;
            case 5:
                return LOG_LEVEL.DEBUG;
            case 6:
                return LOG_LEVEL.VERBOSE;
            default:
                if (level < 0) {
                    return LOG_LEVEL.NONE;
                }
                return LOG_LEVEL.VERBOSE;
        }
    }

    private static boolean atLogLevel(LOG_LEVEL level) {
        return level.compareTo(visualLogLevel) < 1 || level.compareTo(logCatLevel) < 1;
    }

    static void Log(LOG_LEVEL level, String message) {
        Log(level, message, null);
    }

    static void Log(final LOG_LEVEL level, String message, Throwable throwable) {
        String TAG = "OneSignal";
        if (level.compareTo(logCatLevel) < 1) {
            if (level == LOG_LEVEL.VERBOSE) {
                Log.v("OneSignal", message, throwable);
            } else if (level == LOG_LEVEL.DEBUG) {
                Log.d("OneSignal", message, throwable);
            } else if (level == LOG_LEVEL.INFO) {
                Log.i("OneSignal", message, throwable);
            } else if (level == LOG_LEVEL.WARN) {
                Log.w("OneSignal", message, throwable);
            } else if (level == LOG_LEVEL.ERROR || level == LOG_LEVEL.FATAL) {
                Log.e("OneSignal", message, throwable);
            }
        }
        if (level.compareTo(visualLogLevel) < 1 && ActivityLifecycleHandler.curActivity != null) {
            try {
                String fullMessage = message + "\n";
                if (throwable != null) {
                    fullMessage = fullMessage + throwable.getMessage();
                    StringWriter sw = new StringWriter();
                    throwable.printStackTrace(new PrintWriter(sw));
                    fullMessage = fullMessage + sw.toString();
                }
                final String finalFullMessage = fullMessage;
                OSUtils.runOnMainUIThread(new Runnable() {
                    public void run() {
                        if (ActivityLifecycleHandler.curActivity != null) {
                            new android.app.AlertDialog.Builder(ActivityLifecycleHandler.curActivity).setTitle(level.toString()).setMessage(finalFullMessage).show();
                        }
                    }
                });
            } catch (Throwable t) {
                Log.e("OneSignal", "Error showing logging message.", t);
            }
        }
    }

    private static void logHttpError(String errorString, int statusCode, Throwable throwable, String errorResponse) {
        String jsonError = "";
        if (errorResponse != null && atLogLevel(LOG_LEVEL.INFO)) {
            jsonError = "\n" + errorResponse + "\n";
        }
        Log(LOG_LEVEL.WARN, "HTTP code: " + statusCode + " " + errorString + jsonError, throwable);
    }

    static boolean onAppLostFocus(boolean onlySave) {
        boolean z = true;
        foreground = false;
        if (!initDone) {
            return false;
        }
        if (trackAmazonPurchase != null) {
            trackAmazonPurchase.checkListener();
        }
        if (lastTrackedFocusTime == -1) {
            return false;
        }
        long time_elapsed = (long) ((((double) (SystemClock.elapsedRealtime() - lastTrackedFocusTime)) / 1000.0d) + 0.5d);
        lastTrackedFocusTime = SystemClock.elapsedRealtime();
        if (time_elapsed < 0 || time_elapsed > 86400) {
            return false;
        }
        if (appContext == null) {
            Log(LOG_LEVEL.ERROR, "Android Context not found, please call OneSignal.init when your app starts.");
            return false;
        }
        setLastSessionTime(System.currentTimeMillis());
        long totalTimeActive = GetUnsentActiveTime() + time_elapsed;
        if (onlySave || totalTimeActive < 60 || getUserId() == null) {
            SaveUnsentActiveTime(totalTimeActive);
            if (totalTimeActive < 60) {
                z = false;
            }
            return z;
        }
        sendOnFocus(totalTimeActive, true);
        return false;
    }

    static void sendOnFocus(long totalTimeActive, boolean synchronous) {
        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("app_id", appId);
            jsonBody.put("type", 1);
            jsonBody.put("state", "ping");
            jsonBody.put("active_time", totalTimeActive);
            addNetType(jsonBody);
            String url = "players/" + getUserId() + "/on_focus";
            ResponseHandler responseHandler = new C38985();
            if (synchronous) {
                OneSignalRestClient.postSync(url, jsonBody, responseHandler);
            } else {
                OneSignalRestClient.post(url, jsonBody, responseHandler);
            }
        } catch (Throwable t) {
            Log(LOG_LEVEL.ERROR, "Generating on_focus:JSON Failed.", t);
        }
    }

    static void onAppFocus() {
        startSyncService();
        foreground = true;
        lastTrackedFocusTime = SystemClock.elapsedRealtime();
        sendAsSession = isPastOnSessionTime();
        setLastSessionTime(System.currentTimeMillis());
        startRegistrationOrOnSession();
        if (trackGooglePurchase != null) {
            trackGooglePurchase.trackIAP();
        }
        NotificationRestorer.asyncRestore(appContext);
        getCurrentPermissionState(appContext).refreshAsTo();
    }

    static boolean isForeground() {
        return foreground;
    }

    private static void addNetType(JSONObject jsonObj) {
        try {
            jsonObj.put("net_type", osUtils.getNetType());
        } catch (Throwable th) {
        }
    }

    private static int getTimeZoneOffset() {
        TimeZone timezone = Calendar.getInstance().getTimeZone();
        int offset = timezone.getRawOffset();
        if (timezone.inDaylightTime(new Date())) {
            offset += timezone.getDSTSavings();
        }
        return offset / 1000;
    }

    private static void registerUser() {
        Log(LOG_LEVEL.DEBUG, "registerUser: registerForPushFired:" + registerForPushFired + ", locationFired: " + locationFired + ", awlFired: " + awlFired);
        if (registerForPushFired && locationFired && awlFired) {
            new Thread(new C38996(), "OS_REG_USER").start();
        }
    }

    public static void syncHashedEmail(String email) {
        if (appContext == null) {
            Log(LOG_LEVEL.ERROR, "You must initialize OneSignal before calling syncHashedEmail! Omitting this operation.");
        } else if (OSUtils.isValidEmail(email)) {
            OneSignalStateSynchronizer.syncHashedEmail(email.trim().toLowerCase());
        }
    }

    public static void sendTag(String key, String value) {
        try {
            sendTags(new JSONObject().put(key, value));
        } catch (JSONException t) {
            t.printStackTrace();
        }
    }

    public static void sendTags(String jsonString) {
        try {
            sendTags(new JSONObject(jsonString));
        } catch (JSONException t) {
            Log(LOG_LEVEL.ERROR, "Generating JSONObject for sendTags failed!", t);
        }
    }

    public static void sendTags(JSONObject keyValues) {
        if (appContext == null) {
            Log(LOG_LEVEL.ERROR, "You must initialize OneSignal before modifying tags! Omitting this tag operation.");
        } else if (keyValues != null) {
            JSONObject existingKeys = OneSignalStateSynchronizer.getTags(false).result;
            JSONObject toSend = new JSONObject();
            Iterator<String> keys = keyValues.keys();
            while (keys.hasNext()) {
                String key = (String) keys.next();
                try {
                    Object value = keyValues.opt(key);
                    if ((value instanceof JSONArray) || (value instanceof JSONObject)) {
                        Log(LOG_LEVEL.ERROR, "Omitting key '" + key + "'! sendTags DO NOT supported nested values!");
                    } else if (!keyValues.isNull(key) && !"".equals(value)) {
                        toSend.put(key, value.toString());
                    } else if (existingKeys != null && existingKeys.has(key)) {
                        toSend.put(key, "");
                    }
                } catch (Throwable th) {
                }
            }
            if (!toSend.toString().equals("{}")) {
                OneSignalStateSynchronizer.sendTags(toSend);
            }
        }
    }

    public static void postNotification(String json, PostNotificationResponseHandler handler) {
        try {
            postNotification(new JSONObject(json), handler);
        } catch (JSONException e) {
            Log(LOG_LEVEL.ERROR, "Invalid postNotification JSON format: " + json);
        }
    }

    public static void postNotification(JSONObject json, final PostNotificationResponseHandler handler) {
        try {
            if (!json.has("app_id")) {
                json.put("app_id", getSavedAppId());
            }
            OneSignalRestClient.post("notifications/", json, new ResponseHandler() {
                public void onSuccess(String response) {
                    OneSignal.Log(LOG_LEVEL.DEBUG, "HTTP create notification success: " + (response != null ? response : "null"));
                    if (handler != null) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            if (jsonObject.has("errors")) {
                                handler.onFailure(jsonObject);
                            } else {
                                handler.onSuccess(new JSONObject(response));
                            }
                        } catch (Throwable t) {
                            t.printStackTrace();
                        }
                    }
                }

                void onFailure(int statusCode, String response, Throwable throwable) {
                    OneSignal.logHttpError("create notification failed", statusCode, throwable, response);
                    if (handler != null) {
                        if (statusCode == 0) {
                            try {
                                response = "{\"error\": \"HTTP no response error\"}";
                            } catch (Throwable th) {
                                try {
                                    handler.onFailure(new JSONObject("{\"error\": \"Unknown response!\"}"));
                                    return;
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    return;
                                }
                            }
                        }
                        handler.onFailure(new JSONObject(response));
                    }
                }
            });
        } catch (JSONException e) {
            Log(LOG_LEVEL.ERROR, "HTTP create notification json exception!", e);
            if (handler != null) {
                try {
                    handler.onFailure(new JSONObject("{'error': 'HTTP create notification json exception!'}"));
                } catch (JSONException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    public static void getTags(GetTagsHandler getTagsHandler) {
        if (appContext == null) {
            Log(LOG_LEVEL.ERROR, "You must initialize OneSignal before getting tags! Omitting this tag operation.");
        } else if (getTagsHandler == null) {
            Log(LOG_LEVEL.ERROR, "getTagsHandler is null!");
        } else if (getUserId() == null) {
            pendingGetTagsHandler = getTagsHandler;
        } else {
            internalFireGetTagsCallback(getTagsHandler);
        }
    }

    private static void internalFireGetTagsCallback(final GetTagsHandler getTagsHandler) {
        if (getTagsHandler != null) {
            new Thread(new Runnable() {
                public void run() {
                    GetTagsResult tags = OneSignalStateSynchronizer.getTags(!OneSignal.getTagsCall);
                    if (tags.serverSuccess) {
                        OneSignal.getTagsCall = true;
                    }
                    if (tags.result == null || tags.toString().equals("{}")) {
                        getTagsHandler.tagsAvailable(null);
                    } else {
                        getTagsHandler.tagsAvailable(tags.result);
                    }
                }
            }, "OS_GETTAGS_CALLBACK").start();
        }
    }

    public static void deleteTag(String key) {
        Collection tempList = new ArrayList(1);
        tempList.add(key);
        deleteTags(tempList);
    }

    public static void deleteTags(Collection<String> keys) {
        try {
            JSONObject jsonTags = new JSONObject();
            for (String key : keys) {
                jsonTags.put(key, "");
            }
            sendTags(jsonTags);
        } catch (Throwable t) {
            Log(LOG_LEVEL.ERROR, "Failed to generate JSON for deleteTags.", t);
        }
    }

    public static void deleteTags(String jsonArrayString) {
        try {
            JSONObject jsonTags = new JSONObject();
            JSONArray jsonArray = new JSONArray(jsonArrayString);
            for (int i = 0; i < jsonArray.length(); i++) {
                jsonTags.put(jsonArray.getString(i), "");
            }
            sendTags(jsonTags);
        } catch (Throwable t) {
            Log(LOG_LEVEL.ERROR, "Failed to generate JSON for deleteTags.", t);
        }
    }

    public static void idsAvailable(IdsAvailableHandler inIdsAvailableHandler) {
        idsAvailableHandler = inIdsAvailableHandler;
        if (getUserId() != null) {
            internalFireIdsAvailableCallback();
        }
    }

    private static void fireIdsAvailableCallback() {
        if (idsAvailableHandler != null) {
            OSUtils.runOnMainUIThread(new C39029());
        }
    }

    private static synchronized void internalFireIdsAvailableCallback() {
        synchronized (OneSignal.class) {
            if (idsAvailableHandler != null) {
                String regId = OneSignalStateSynchronizer.getRegistrationId();
                if (!OneSignalStateSynchronizer.getSubscribed()) {
                    regId = null;
                }
                String userId = getUserId();
                if (userId != null) {
                    idsAvailableHandler.idsAvailable(userId, regId);
                    if (regId != null) {
                        idsAvailableHandler = null;
                    }
                }
            }
        }
    }

    static void sendPurchases(JSONArray purchases, boolean newAsExisting, ResponseHandler responseHandler) {
        if (getUserId() == null) {
            iapUpdateJob = new IAPUpdateJob(purchases);
            iapUpdateJob.newAsExisting = newAsExisting;
            iapUpdateJob.restResponseHandler = responseHandler;
            return;
        }
        try {
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("app_id", appId);
            if (newAsExisting) {
                jsonBody.put("existing", true);
            }
            jsonBody.put("purchases", purchases);
            OneSignalRestClient.post("players/" + getUserId() + "/on_purchase", jsonBody, responseHandler);
        } catch (Throwable t) {
            Log(LOG_LEVEL.ERROR, "Failed to generate JSON for sendPurchases.", t);
        }
    }

    private static boolean openURLFromNotification(Context context, JSONArray dataArray) {
        int jsonArraySize = dataArray.length();
        boolean urlOpened = false;
        for (int i = 0; i < jsonArraySize; i++) {
            try {
                JSONObject data = dataArray.getJSONObject(i);
                if (data.has(AdType.CUSTOM)) {
                    JSONObject customJSON = new JSONObject(data.optString(AdType.CUSTOM));
                    if (customJSON.has(RequestParams.f9038U)) {
                        String url = customJSON.optString(RequestParams.f9038U, null);
                        if (!url.contains("://")) {
                            url = "http://" + url;
                        }
                        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(url.trim()));
                        intent.addFlags(1476919296);
                        context.startActivity(intent);
                        urlOpened = true;
                    }
                }
            } catch (Throwable t) {
                Log(LOG_LEVEL.ERROR, "Error parsing JSON item " + i + BridgeUtil.SPLIT_MARK + jsonArraySize + " for launching a web URL.", t);
            }
        }
        return urlOpened;
    }

    private static void runNotificationOpenedCallback(JSONArray dataArray, boolean shown, boolean fromAlert) {
        if (mInitBuilder == null || mInitBuilder.mNotificationOpenedHandler == null) {
            unprocessedOpenedNotifis.add(dataArray);
        } else {
            fireNotificationOpenedHandler(generateOsNotificationOpenResult(dataArray, shown, fromAlert));
        }
    }

    @NonNull
    private static OSNotificationOpenResult generateOsNotificationOpenResult(JSONArray dataArray, boolean shown, boolean fromAlert) {
        int jsonArraySize = dataArray.length();
        boolean firstMessage = true;
        OSNotificationOpenResult openResult = new OSNotificationOpenResult();
        OSNotification notification = new OSNotification();
        notification.isAppInFocus = isAppActive();
        notification.shown = shown;
        notification.androidNotificationId = dataArray.optJSONObject(0).optInt("notificationId");
        String actionSelected = null;
        for (int i = 0; i < jsonArraySize; i++) {
            try {
                JSONObject data = dataArray.getJSONObject(i);
                notification.payload = NotificationBundleProcessor.OSNotificationPayloadFrom(data);
                if (actionSelected == null && data.has("actionSelected")) {
                    actionSelected = data.optString("actionSelected", null);
                }
                if (firstMessage) {
                    firstMessage = false;
                } else {
                    if (notification.groupedNotifications == null) {
                        notification.groupedNotifications = new ArrayList();
                    }
                    notification.groupedNotifications.add(notification.payload);
                }
            } catch (Throwable t) {
                Log(LOG_LEVEL.ERROR, "Error parsing JSON item " + i + BridgeUtil.SPLIT_MARK + jsonArraySize + " for callback.", t);
            }
        }
        openResult.notification = notification;
        openResult.action = new OSNotificationAction();
        openResult.action.actionID = actionSelected;
        openResult.action.type = actionSelected != null ? ActionType.ActionTaken : ActionType.Opened;
        if (fromAlert) {
            openResult.notification.displayType = OSNotification$DisplayType.InAppAlert;
        } else {
            openResult.notification.displayType = OSNotification$DisplayType.Notification;
        }
        return openResult;
    }

    private static void fireNotificationOpenedHandler(final OSNotificationOpenResult openedResult) {
        OSUtils.runOnMainUIThread(new Runnable() {
            public void run() {
                OneSignal.mInitBuilder.mNotificationOpenedHandler.notificationOpened(openedResult);
            }
        });
    }

    static void handleNotificationReceived(JSONArray data, boolean displayed, boolean fromAlert) {
        if (mInitBuilder != null && mInitBuilder.mNotificationReceivedHandler != null) {
            mInitBuilder.mNotificationReceivedHandler.notificationReceived(generateOsNotificationOpenResult(data, displayed, fromAlert).notification);
        }
    }

    public static void handleNotificationOpen(Context inContext, JSONArray data, boolean fromAlert) {
        notificationOpenedRESTCall(inContext, data);
        boolean urlOpened = false;
        boolean defaultOpenActionDisabled = "DISABLE".equals(OSUtils.getManifestMeta(inContext, "com.onesignal.NotificationOpened.DEFAULT"));
        if (!defaultOpenActionDisabled) {
            urlOpened = openURLFromNotification(inContext, data);
        }
        runNotificationOpenedCallback(data, true, fromAlert);
        if (!fromAlert && !urlOpened && !defaultOpenActionDisabled) {
            fireIntentFromNotificationOpen(inContext);
        }
    }

    private static void fireIntentFromNotificationOpen(Context inContext) {
        Intent launchIntent = inContext.getPackageManager().getLaunchIntentForPackage(inContext.getPackageName());
        if (launchIntent != null) {
            launchIntent.setFlags(268566528);
            inContext.startActivity(launchIntent);
        }
    }

    private static void notificationOpenedRESTCall(Context inContext, JSONArray dataArray) {
        for (int i = 0; i < dataArray.length(); i++) {
            try {
                String notificationId = new JSONObject(dataArray.getJSONObject(i).optString(AdType.CUSTOM, null)).optString(RequestParams.IP, null);
                if (!postedOpenedNotifIds.contains(notificationId)) {
                    postedOpenedNotifIds.add(notificationId);
                    JSONObject jsonBody = new JSONObject();
                    jsonBody.put("app_id", getSavedAppId(inContext));
                    jsonBody.put("player_id", getSavedUserId(inContext));
                    jsonBody.put(NotificationTable.COLUMN_NAME_OPENED, true);
                    OneSignalRestClient.put("notifications/" + notificationId, jsonBody, new ResponseHandler() {
                        void onFailure(int statusCode, String response, Throwable throwable) {
                            OneSignal.logHttpError("sending Notification Opened Failed", statusCode, throwable, response);
                        }
                    });
                }
            } catch (Throwable t) {
                Log(LOG_LEVEL.ERROR, "Failed to generate JSON to send notification opened.", t);
            }
        }
    }

    private static void SaveAppId(String appId) {
        if (appContext != null) {
            Editor editor = getGcmPreferences(appContext).edit();
            editor.putString("GT_APP_ID", appId);
            editor.commit();
        }
    }

    static String getSavedAppId() {
        return getSavedAppId(appContext);
    }

    private static String getSavedAppId(Context inContext) {
        if (inContext == null) {
            return "";
        }
        return getGcmPreferences(inContext).getString("GT_APP_ID", null);
    }

    private static String getSavedUserId(Context inContext) {
        if (inContext == null) {
            return "";
        }
        return getGcmPreferences(inContext).getString("GT_PLAYER_ID", null);
    }

    static String getUserId() {
        if (userId == null && appContext != null) {
            userId = getGcmPreferences(appContext).getString("GT_PLAYER_ID", null);
        }
        return userId;
    }

    static void saveUserId(String inUserId) {
        userId = inUserId;
        if (appContext != null) {
            Editor editor = getGcmPreferences(appContext).edit();
            editor.putString("GT_PLAYER_ID", userId);
            editor.commit();
        }
    }

    static boolean getFilterOtherGCMReceivers(Context context) {
        return getGcmPreferences(context).getBoolean("OS_FILTER_OTHER_GCM_RECEIVERS", false);
    }

    static void saveFilterOtherGCMReceivers(boolean set) {
        if (appContext != null) {
            Editor editor = getGcmPreferences(appContext).edit();
            editor.putBoolean("OS_FILTER_OTHER_GCM_RECEIVERS", set);
            editor.commit();
        }
    }

    static void updateUserIdDependents(String userId) {
        saveUserId(userId);
        fireIdsAvailableCallback();
        internalFireGetTagsCallback(pendingGetTagsHandler);
        getCurrentSubscriptionState(appContext).setUserId(userId);
        if (iapUpdateJob != null) {
            sendPurchases(iapUpdateJob.toReport, iapUpdateJob.newAsExisting, iapUpdateJob.restResponseHandler);
            iapUpdateJob = null;
        }
        OneSignalChromeTab.setup(appContext, appId, userId, AdvertisingIdProviderGPS.getLastValue());
    }

    public static void enableVibrate(boolean enable) {
        if (appContext != null) {
            Editor editor = getGcmPreferences(appContext).edit();
            editor.putBoolean("GT_VIBRATE_ENABLED", enable);
            editor.apply();
        }
    }

    static boolean getVibrate(Context context) {
        return getGcmPreferences(context).getBoolean("GT_VIBRATE_ENABLED", true);
    }

    public static void enableSound(boolean enable) {
        if (appContext != null) {
            Editor editor = getGcmPreferences(appContext).edit();
            editor.putBoolean("GT_SOUND_ENABLED", enable);
            editor.apply();
        }
    }

    static boolean getSoundEnabled(Context context) {
        return getGcmPreferences(context).getBoolean("GT_SOUND_ENABLED", true);
    }

    static void setLastSessionTime(long time) {
        Editor editor = getGcmPreferences(appContext).edit();
        editor.putLong("OS_LAST_SESSION_TIME", time);
        editor.apply();
    }

    private static long getLastSessionTime(Context context) {
        return getGcmPreferences(context).getLong("OS_LAST_SESSION_TIME", -31000);
    }

    public static void setInFocusDisplaying(OSInFocusDisplayOption displayOption) {
        getCurrentOrNewInitBuilder().mDisplayOptionCarryOver = true;
        getCurrentOrNewInitBuilder().mDisplayOption = displayOption;
    }

    public static void setInFocusDisplaying(int displayOption) {
        setInFocusDisplaying(getInFocusDisplaying(displayOption));
    }

    private static OSInFocusDisplayOption getInFocusDisplaying(int displayOption) {
        switch (displayOption) {
            case 0:
                return OSInFocusDisplayOption.None;
            case 1:
                return OSInFocusDisplayOption.InAppAlert;
            case 2:
                return OSInFocusDisplayOption.Notification;
            default:
                if (displayOption < 0) {
                    return OSInFocusDisplayOption.None;
                }
                return OSInFocusDisplayOption.Notification;
        }
    }

    static boolean getNotificationsWhenActiveEnabled() {
        if (mInitBuilder == null || mInitBuilder.mDisplayOption == OSInFocusDisplayOption.Notification) {
            return true;
        }
        return false;
    }

    static boolean getInAppAlertNotificationEnabled() {
        if (mInitBuilder != null && mInitBuilder.mDisplayOption == OSInFocusDisplayOption.InAppAlert) {
            return true;
        }
        return false;
    }

    public static void setSubscription(boolean enable) {
        if (appContext == null) {
            Log(LOG_LEVEL.ERROR, "OneSignal.init has not been called. Could not set subscription.");
            return;
        }
        getCurrentSubscriptionState(appContext).setUserSubscriptionSetting(enable);
        OneSignalStateSynchronizer.setSubscription(enable);
    }

    public static void setLocationShared(boolean enable) {
        shareLocation = enable;
        if (!enable) {
            OneSignalStateSynchronizer.clearLocation();
        }
        Log(LOG_LEVEL.DEBUG, "shareLocation:" + shareLocation);
    }

    public static void promptLocation() {
        if (appContext == null) {
            Log(LOG_LEVEL.ERROR, "OneSignal.init has not been called. Could not prompt for location.");
            return;
        }
        LocationGMS.getLocation(appContext, true, new LocationHandler() {
            public void complete(LocationPoint point) {
                if (point != null) {
                    OneSignalStateSynchronizer.updateLocation(point);
                }
            }
        });
        promptedLocation = true;
    }

    public static void cancelNotification(int id) {
        if (appContext == null) {
            Log(LOG_LEVEL.ERROR, "OneSignal.init has not been called. Could not clear notification id: " + id);
            return;
        }
        SQLiteDatabase writableDb = null;
        try {
            writableDb = OneSignalDbHelper.getInstance(appContext).getWritableDbWithRetries();
            writableDb.beginTransaction();
            String whereStr = "android_notification_id = " + id + " AND " + NotificationTable.COLUMN_NAME_OPENED + " = 0 AND " + NotificationTable.COLUMN_NAME_DISMISSED + " = 0";
            ContentValues values = new ContentValues();
            values.put(NotificationTable.COLUMN_NAME_DISMISSED, Integer.valueOf(1));
            if (writableDb.update("notification", values, whereStr, null) > 0) {
                NotificationSummaryManager.updatePossibleDependentSummaryOnDismiss(appContext, writableDb, id);
            }
            BadgeCountUpdater.update(writableDb, appContext);
            writableDb.setTransactionSuccessful();
            if (writableDb != null) {
                writableDb.endTransaction();
            }
        } catch (Throwable th) {
            if (writableDb != null) {
                writableDb.endTransaction();
            }
        }
        ((NotificationManager) appContext.getSystemService("notification")).cancel(id);
    }

    public static void removeNotificationOpenedHandler() {
        getCurrentOrNewInitBuilder().mNotificationOpenedHandler = null;
    }

    public static void removeNotificationReceivedHandler() {
        getCurrentOrNewInitBuilder().mNotificationReceivedHandler = null;
    }

    public static void addPermissionObserver(OSPermissionObserver observer) {
        if (appContext == null) {
            Log(LOG_LEVEL.ERROR, "OneSignal.init has not been called. Could not add permission observer");
            return;
        }
        getPermissionStateChangesObserver().addObserver(observer);
        if (getCurrentPermissionState(appContext).compare(getLastPermissionState(appContext))) {
            OSPermissionChangedInternalObserver.fireChangesToPublicObserver(getCurrentPermissionState(appContext));
        }
    }

    public static void removePermissionObserver(OSPermissionObserver observer) {
        if (appContext == null) {
            Log(LOG_LEVEL.ERROR, "OneSignal.init has not been called. Could not modify permission observer");
        } else {
            getPermissionStateChangesObserver().removeObserver(observer);
        }
    }

    public static void addSubscriptionObserver(OSSubscriptionObserver observer) {
        if (appContext == null) {
            Log(LOG_LEVEL.ERROR, "OneSignal.init has not been called. Could not add subscription observer");
            return;
        }
        getSubscriptionStateChangesObserver().addObserver(observer);
        if (getCurrentSubscriptionState(appContext).compare(getLastSubscriptionState(appContext))) {
            OSSubscriptionChangedInternalObserver.fireChangesToPublicObserver(getCurrentSubscriptionState(appContext));
        }
    }

    public static void removeSubscriptionObserver(OSSubscriptionObserver observer) {
        if (appContext == null) {
            Log(LOG_LEVEL.ERROR, "OneSignal.init has not been called. Could not modify subscription observer");
        } else {
            getSubscriptionStateChangesObserver().removeObserver(observer);
        }
    }

    public static OSPermissionSubscriptionState getPermissionSubscriptionState() {
        if (appContext == null) {
            Log(LOG_LEVEL.ERROR, "OneSignal.init has not been called. Could not get OSPermissionSubscriptionState");
            return null;
        }
        OSPermissionSubscriptionState status = new OSPermissionSubscriptionState();
        status.subscriptionStatus = getCurrentSubscriptionState(appContext);
        status.permissionStatus = getCurrentPermissionState(appContext);
        return status;
    }

    static long GetUnsentActiveTime() {
        if (unSentActiveTime == -1 && appContext != null) {
            unSentActiveTime = getGcmPreferences(appContext).getLong("GT_UNSENT_ACTIVE_TIME", 0);
        }
        Log(LOG_LEVEL.INFO, "GetUnsentActiveTime: " + unSentActiveTime);
        return unSentActiveTime;
    }

    private static void SaveUnsentActiveTime(long time) {
        unSentActiveTime = time;
        if (appContext != null) {
            Log(LOG_LEVEL.INFO, "SaveUnsentActiveTime: " + unSentActiveTime);
            Editor editor = getGcmPreferences(appContext).edit();
            editor.putLong("GT_UNSENT_ACTIVE_TIME", time);
            editor.commit();
        }
    }

    static SharedPreferences getGcmPreferences(Context context) {
        return context.getSharedPreferences(OneSignal.class.getSimpleName(), 0);
    }

    private static boolean isDuplicateNotification(String id, Context context) {
        if (id == null || "".equals(id)) {
            return false;
        }
        boolean exists = false;
        Cursor cursor = null;
        try {
            cursor = OneSignalDbHelper.getInstance(context).getReadableDbWithRetries().query("notification", new String[]{NotificationTable.COLUMN_NAME_NOTIFICATION_ID}, "notification_id = ?", new String[]{id}, null, null, null);
            exists = cursor.moveToFirst();
            if (cursor != null) {
                cursor.close();
            }
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
        }
        if (!exists) {
            return false;
        }
        Log(LOG_LEVEL.DEBUG, "Duplicate GCM message received, skip processing of " + id);
        return true;
    }

    static boolean notValidOrDuplicated(Context context, JSONObject jsonPayload) {
        String id = getNotificationIdFromGCMJsonPayload(jsonPayload);
        return id == null || isDuplicateNotification(id, context);
    }

    static String getNotificationIdFromGCMBundle(Bundle bundle) {
        if (bundle.isEmpty()) {
            return null;
        }
        try {
            if (bundle.containsKey(AdType.CUSTOM)) {
                JSONObject customJSON = new JSONObject(bundle.getString(AdType.CUSTOM));
                if (customJSON.has(RequestParams.IP)) {
                    return customJSON.optString(RequestParams.IP, null);
                }
                Log(LOG_LEVEL.DEBUG, "Not a OneSignal formatted GCM message. No 'i' field in custom.");
                return null;
            }
            Log(LOG_LEVEL.DEBUG, "Not a OneSignal formatted GCM message. No 'custom' field in the bundle.");
            return null;
        } catch (Throwable t) {
            Log(LOG_LEVEL.DEBUG, "Could not parse bundle, probably not a OneSignal notification.", t);
            return null;
        }
    }

    private static String getNotificationIdFromGCMJsonPayload(JSONObject jsonPayload) {
        String str = null;
        try {
            str = new JSONObject(jsonPayload.optString(AdType.CUSTOM)).optString(RequestParams.IP, null);
        } catch (Throwable th) {
        }
        return str;
    }

    static boolean isAppActive() {
        return initDone && isForeground();
    }

    static void updateOnSessionDependents() {
        sendAsSession = false;
        setLastSessionTime(System.currentTimeMillis());
    }

    private static boolean isPastOnSessionTime() {
        return (System.currentTimeMillis() - getLastSessionTime(appContext)) / 1000 >= 30;
    }

    private static void startSyncService() {
        Intent intent = new Intent(appContext, SyncService.class);
        intent.putExtra("task", 0);
        appContext.startService(intent);
    }

    static boolean areNotificationsEnabledForSubscribedState() {
        if (mInitBuilder.mUnsubscribeWhenNotificationsAreDisabled) {
            return OSUtils.areNotificationsEnabled(appContext);
        }
        return true;
    }
}
