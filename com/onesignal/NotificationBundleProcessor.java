package com.onesignal;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.content.WakefulBroadcastReceiver;
import com.mobfox.sdk.networking.RequestParams;
import com.mopub.common.AdType;
import com.onesignal.NotificationExtenderService.OverrideSettings;
import com.onesignal.OSNotificationPayload.ActionButton;
import com.onesignal.OSNotificationPayload.BackgroundImageLayout;
import com.onesignal.OneSignal.LOG_LEVEL;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class NotificationBundleProcessor {
    static final String DEFAULT_ACTION = "__DEFAULT__";

    static class ProcessedBundleResult {
        boolean hasExtenderService;
        boolean isDup;
        boolean isOneSignalPayload;

        ProcessedBundleResult() {
        }

        boolean processed() {
            return !this.isOneSignalPayload || this.hasExtenderService || this.isDup;
        }
    }

    private static void processCollapseKey(com.onesignal.NotificationGenerationJob r13) {
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
        r1 = r13.restoring;
        if (r1 == 0) goto L_0x0005;
    L_0x0004:
        return;
    L_0x0005:
        r1 = r13.jsonPayload;
        r2 = "collapse_key";
        r1 = r1.has(r2);
        if (r1 == 0) goto L_0x0004;
    L_0x0010:
        r1 = "do_not_collapse";
        r2 = r13.jsonPayload;
        r3 = "collapse_key";
        r2 = r2.optString(r3);
        r1 = r1.equals(r2);
        if (r1 != 0) goto L_0x0004;
    L_0x0022:
        r1 = r13.jsonPayload;
        r2 = "collapse_key";
        r9 = r1.optString(r2);
        r1 = r13.context;
        r11 = com.onesignal.OneSignalDbHelper.getInstance(r1);
        r10 = 0;
        r0 = r11.getReadableDbWithRetries();	 Catch:{ Throwable -> 0x0076, all -> 0x008c }
        r1 = "notification";	 Catch:{ Throwable -> 0x0076, all -> 0x008c }
        r2 = 1;	 Catch:{ Throwable -> 0x0076, all -> 0x008c }
        r2 = new java.lang.String[r2];	 Catch:{ Throwable -> 0x0076, all -> 0x008c }
        r3 = 0;	 Catch:{ Throwable -> 0x0076, all -> 0x008c }
        r4 = "android_notification_id";	 Catch:{ Throwable -> 0x0076, all -> 0x008c }
        r2[r3] = r4;	 Catch:{ Throwable -> 0x0076, all -> 0x008c }
        r3 = "collapse_id = ? AND dismissed = 0 AND opened = 0 ";	 Catch:{ Throwable -> 0x0076, all -> 0x008c }
        r4 = 1;	 Catch:{ Throwable -> 0x0076, all -> 0x008c }
        r4 = new java.lang.String[r4];	 Catch:{ Throwable -> 0x0076, all -> 0x008c }
        r5 = 0;	 Catch:{ Throwable -> 0x0076, all -> 0x008c }
        r4[r5] = r9;	 Catch:{ Throwable -> 0x0076, all -> 0x008c }
        r5 = 0;	 Catch:{ Throwable -> 0x0076, all -> 0x008c }
        r6 = 0;	 Catch:{ Throwable -> 0x0076, all -> 0x008c }
        r7 = 0;	 Catch:{ Throwable -> 0x0076, all -> 0x008c }
        r10 = r0.query(r1, r2, r3, r4, r5, r6, r7);	 Catch:{ Throwable -> 0x0076, all -> 0x008c }
        r1 = r10.moveToFirst();	 Catch:{ Throwable -> 0x0076, all -> 0x008c }
        if (r1 == 0) goto L_0x006a;	 Catch:{ Throwable -> 0x0076, all -> 0x008c }
    L_0x0058:
        r1 = "android_notification_id";	 Catch:{ Throwable -> 0x0076, all -> 0x008c }
        r1 = r10.getColumnIndex(r1);	 Catch:{ Throwable -> 0x0076, all -> 0x008c }
        r8 = r10.getInt(r1);	 Catch:{ Throwable -> 0x0076, all -> 0x008c }
        r1 = java.lang.Integer.valueOf(r8);	 Catch:{ Throwable -> 0x0076, all -> 0x008c }
        r13.setAndroidIdWithOutOverriding(r1);	 Catch:{ Throwable -> 0x0076, all -> 0x008c }
    L_0x006a:
        if (r10 == 0) goto L_0x0004;
    L_0x006c:
        r1 = r10.isClosed();
        if (r1 != 0) goto L_0x0004;
    L_0x0072:
        r10.close();
        goto L_0x0004;
    L_0x0076:
        r12 = move-exception;
        r1 = com.onesignal.OneSignal.LOG_LEVEL.ERROR;	 Catch:{ Throwable -> 0x0076, all -> 0x008c }
        r2 = "Could not read DB to find existing collapse_key!";	 Catch:{ Throwable -> 0x0076, all -> 0x008c }
        com.onesignal.OneSignal.Log(r1, r2, r12);	 Catch:{ Throwable -> 0x0076, all -> 0x008c }
        if (r10 == 0) goto L_0x0004;
    L_0x0081:
        r1 = r10.isClosed();
        if (r1 != 0) goto L_0x0004;
    L_0x0087:
        r10.close();
        goto L_0x0004;
    L_0x008c:
        r1 = move-exception;
        if (r10 == 0) goto L_0x0098;
    L_0x008f:
        r2 = r10.isClosed();
        if (r2 != 0) goto L_0x0098;
    L_0x0095:
        r10.close();
    L_0x0098:
        throw r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onesignal.NotificationBundleProcessor.processCollapseKey(com.onesignal.NotificationGenerationJob):void");
    }

    NotificationBundleProcessor() {
    }

    static void ProcessFromGCMIntentService(Context context, Bundle bundle, OverrideSettings overrideSettings) {
        try {
            String jsonStrPayload = bundle.getString("json_payload");
            if (jsonStrPayload == null) {
                OneSignal.Log(LOG_LEVEL.ERROR, "json_payload key is nonexistent from bundle passed to ProcessFromGCMIntentService: " + bundle);
                return;
            }
            NotificationGenerationJob notifJob = new NotificationGenerationJob(context);
            notifJob.restoring = bundle.getBoolean("restoring", false);
            notifJob.shownTimeStamp = Long.valueOf(bundle.getLong("timestamp"));
            notifJob.jsonPayload = new JSONObject(jsonStrPayload);
            if (notifJob.restoring || !OneSignal.notValidOrDuplicated(context, notifJob.jsonPayload)) {
                if (bundle.containsKey("android_notif_id")) {
                    if (overrideSettings == null) {
                        overrideSettings = new OverrideSettings();
                    }
                    overrideSettings.androidNotificationId = Integer.valueOf(bundle.getInt("android_notif_id"));
                }
                notifJob.overrideSettings = overrideSettings;
                Process(notifJob);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    static int Process(NotificationGenerationJob notifJob) {
        boolean z = true;
        if (!(OneSignal.getInAppAlertNotificationEnabled() && OneSignal.isAppActive())) {
            z = false;
        }
        notifJob.showAsAlert = z;
        processCollapseKey(notifJob);
        GenerateNotification.fromJsonPayload(notifJob);
        if (!notifJob.restoring) {
            saveNotification(notifJob.context, notifJob.jsonPayload, false, notifJob.getAndroidId().intValue());
            try {
                JSONObject jsonObject = new JSONObject(notifJob.jsonPayload.toString());
                jsonObject.put("notificationId", notifJob.getAndroidId());
                OneSignal.handleNotificationReceived(newJsonArray(jsonObject), true, notifJob.showAsAlert);
            } catch (Throwable th) {
            }
        }
        return notifJob.getAndroidId().intValue();
    }

    static JSONArray bundleAsJsonArray(Bundle bundle) {
        JSONArray jsonArray = new JSONArray();
        jsonArray.put(bundleAsJSONObject(bundle));
        return jsonArray;
    }

    static void saveNotification(Context context, Bundle bundle, boolean opened, int notificationId) {
        saveNotification(context, bundleAsJSONObject(bundle), opened, notificationId);
    }

    static void saveNotification(Context context, JSONObject jsonPayload, boolean opened, int notificationId) {
        int i = 1;
        try {
            JSONObject customJSON = new JSONObject(jsonPayload.optString(AdType.CUSTOM));
            SQLiteDatabase writableDb = null;
            try {
                ContentValues values;
                writableDb = OneSignalDbHelper.getInstance(context).getWritableDbWithRetries();
                writableDb.beginTransaction();
                deleteOldNotifications(writableDb);
                if (notificationId != -1) {
                    String whereStr = "android_notification_id = " + notificationId;
                    values = new ContentValues();
                    values.put(NotificationTable.COLUMN_NAME_DISMISSED, Integer.valueOf(1));
                    writableDb.update("notification", values, whereStr, null);
                    BadgeCountUpdater.update(writableDb, context);
                }
                values = new ContentValues();
                values.put(NotificationTable.COLUMN_NAME_NOTIFICATION_ID, customJSON.optString(RequestParams.IP));
                if (jsonPayload.has("grp")) {
                    values.put("group_id", jsonPayload.optString("grp"));
                }
                if (jsonPayload.has("collapse_key") && !"do_not_collapse".equals(jsonPayload.optString("collapse_key"))) {
                    values.put(NotificationTable.COLUMN_NAME_COLLAPSE_ID, jsonPayload.optString("collapse_key"));
                }
                String str = NotificationTable.COLUMN_NAME_OPENED;
                if (!opened) {
                    i = 0;
                }
                values.put(str, Integer.valueOf(i));
                if (!opened) {
                    values.put(NotificationTable.COLUMN_NAME_ANDROID_NOTIFICATION_ID, Integer.valueOf(notificationId));
                }
                if (jsonPayload.has("title")) {
                    values.put("title", jsonPayload.optString("title"));
                }
                values.put("message", jsonPayload.optString("alert"));
                values.put(NotificationTable.COLUMN_NAME_FULL_DATA, jsonPayload.toString());
                writableDb.insertOrThrow("notification", null, values);
                if (!opened) {
                    BadgeCountUpdater.update(writableDb, context);
                }
                writableDb.setTransactionSuccessful();
                if (writableDb != null) {
                    writableDb.endTransaction();
                }
            } catch (Exception e) {
                OneSignal.Log(LOG_LEVEL.ERROR, "Error saving notification record! ", e);
                if (writableDb != null) {
                    writableDb.endTransaction();
                }
            } catch (Throwable th) {
                if (writableDb != null) {
                    writableDb.endTransaction();
                }
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    static void deleteOldNotifications(SQLiteDatabase writableDb) {
        writableDb.delete("notification", "created_time < " + ((System.currentTimeMillis() / 1000) - 2419200), null);
    }

    static JSONObject bundleAsJSONObject(Bundle bundle) {
        JSONObject json = new JSONObject();
        for (String key : bundle.keySet()) {
            try {
                json.put(key, bundle.get(key));
            } catch (JSONException e) {
                OneSignal.Log(LOG_LEVEL.ERROR, "bundleAsJSONObject error for key: " + key, e);
            }
        }
        return json;
    }

    private static void prepareBundle(Bundle gcmBundle) {
        if (gcmBundle.containsKey("o")) {
            try {
                JSONObject additionalDataJSON;
                JSONObject customJSON = new JSONObject(gcmBundle.getString(AdType.CUSTOM));
                if (customJSON.has("a")) {
                    additionalDataJSON = customJSON.getJSONObject("a");
                } else {
                    additionalDataJSON = new JSONObject();
                }
                JSONArray buttons = new JSONArray(gcmBundle.getString("o"));
                gcmBundle.remove("o");
                for (int i = 0; i < buttons.length(); i++) {
                    String buttonId;
                    JSONObject button = buttons.getJSONObject(i);
                    String buttonText = button.getString("n");
                    button.remove("n");
                    if (button.has(RequestParams.IP)) {
                        buttonId = button.getString(RequestParams.IP);
                        button.remove(RequestParams.IP);
                    } else {
                        buttonId = buttonText;
                    }
                    button.put("id", buttonId);
                    button.put("text", buttonText);
                    if (button.has("p")) {
                        button.put("icon", button.getString("p"));
                        button.remove("p");
                    }
                }
                additionalDataJSON.put("actionButtons", buttons);
                additionalDataJSON.put("actionSelected", DEFAULT_ACTION);
                if (!customJSON.has("a")) {
                    customJSON.put("a", additionalDataJSON);
                }
                gcmBundle.putString(AdType.CUSTOM, customJSON.toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    static OSNotificationPayload OSNotificationPayloadFrom(JSONObject currentJsonPayload) {
        OSNotificationPayload notification = new OSNotificationPayload();
        try {
            JSONObject customJson = new JSONObject(currentJsonPayload.optString(AdType.CUSTOM));
            notification.notificationID = customJson.optString(RequestParams.IP);
            notification.rawPayload = currentJsonPayload.toString();
            notification.additionalData = customJson.optJSONObject("a");
            notification.launchURL = customJson.optString(RequestParams.f9038U, null);
            notification.body = currentJsonPayload.optString("alert", null);
            notification.title = currentJsonPayload.optString("title", null);
            notification.smallIcon = currentJsonPayload.optString("sicon", null);
            notification.bigPicture = currentJsonPayload.optString("bicon", null);
            notification.largeIcon = currentJsonPayload.optString("licon", null);
            notification.sound = currentJsonPayload.optString("sound", null);
            notification.groupKey = currentJsonPayload.optString("grp", null);
            notification.groupMessage = currentJsonPayload.optString("grp_msg", null);
            notification.smallIconAccentColor = currentJsonPayload.optString("bgac", null);
            notification.ledColor = currentJsonPayload.optString("ledc", null);
            String visibility = currentJsonPayload.optString("vis", null);
            if (visibility != null) {
                notification.lockScreenVisibility = Integer.parseInt(visibility);
            }
            notification.fromProjectNumber = currentJsonPayload.optString("from", null);
            notification.priority = currentJsonPayload.optInt("pri", 0);
            String collapseKey = currentJsonPayload.optString("collapse_key", null);
            if (!"do_not_collapse".equals(collapseKey)) {
                notification.collapseId = collapseKey;
            }
            setActionButtons(notification);
        } catch (Throwable t) {
            OneSignal.Log(LOG_LEVEL.ERROR, "Error assigning OSNotificationPayload values!", t);
        }
        try {
            setBackgroundImageLayout(notification, currentJsonPayload);
        } catch (Throwable t2) {
            OneSignal.Log(LOG_LEVEL.ERROR, "Error assigning OSNotificationPayload.backgroundImageLayout values!", t2);
        }
        return notification;
    }

    private static void setActionButtons(OSNotificationPayload notification) throws Throwable {
        if (notification.additionalData != null && notification.additionalData.has("actionButtons")) {
            JSONArray jsonActionButtons = notification.additionalData.getJSONArray("actionButtons");
            notification.actionButtons = new ArrayList();
            for (int i = 0; i < jsonActionButtons.length(); i++) {
                JSONObject jsonActionButton = jsonActionButtons.getJSONObject(i);
                ActionButton actionButton = new ActionButton();
                actionButton.id = jsonActionButton.optString("id", null);
                actionButton.text = jsonActionButton.optString("text", null);
                actionButton.icon = jsonActionButton.optString("icon", null);
                notification.actionButtons.add(actionButton);
            }
            notification.additionalData.remove("actionSelected");
            notification.additionalData.remove("actionButtons");
        }
    }

    private static void setBackgroundImageLayout(OSNotificationPayload notification, JSONObject currentJsonPayload) throws Throwable {
        String jsonStrBgImage = currentJsonPayload.optString("bg_img", null);
        if (jsonStrBgImage != null) {
            JSONObject jsonBgImage = new JSONObject(jsonStrBgImage);
            notification.backgroundImageLayout = new BackgroundImageLayout();
            notification.backgroundImageLayout.image = jsonBgImage.optString("img");
            notification.backgroundImageLayout.titleTextColor = jsonBgImage.optString("tc");
            notification.backgroundImageLayout.bodyTextColor = jsonBgImage.optString("bc");
        }
    }

    static ProcessedBundleResult processBundle(Context context, final Bundle bundle) {
        ProcessedBundleResult result = new ProcessedBundleResult();
        if (OneSignal.getNotificationIdFromGCMBundle(bundle) != null) {
            result.isOneSignalPayload = true;
            prepareBundle(bundle);
            Intent overrideIntent = NotificationExtenderService.getIntent(context);
            if (overrideIntent != null) {
                overrideIntent.putExtra("json_payload", bundleAsJSONObject(bundle).toString());
                WakefulBroadcastReceiver.startWakefulService(context, overrideIntent);
                result.hasExtenderService = true;
            } else {
                result.isDup = OneSignal.notValidOrDuplicated(context, bundleAsJSONObject(bundle));
                if (!result.isDup) {
                    String alert = bundle.getString("alert");
                    boolean z = (alert == null || "".equals(alert)) ? false : true;
                    if (!shouldDisplay(z)) {
                        saveNotification(context, bundle, true, -1);
                        new Thread(new Runnable() {
                            public void run() {
                                OneSignal.handleNotificationReceived(NotificationBundleProcessor.bundleAsJsonArray(bundle), false, false);
                            }
                        }, "OS_PROC_BUNDLE").start();
                    }
                }
            }
        }
        return result;
    }

    static boolean shouldDisplay(boolean hasBody) {
        return hasBody && (OneSignal.getNotificationsWhenActiveEnabled() || OneSignal.getInAppAlertNotificationEnabled() || !OneSignal.isAppActive());
    }

    static JSONArray newJsonArray(JSONObject jsonObject) {
        JSONArray jsonArray = new JSONArray();
        jsonArray.put(jsonObject);
        return jsonArray;
    }
}
