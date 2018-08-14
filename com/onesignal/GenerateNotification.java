package com.onesignal;

import android.R.drawable;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build.VERSION;
import android.support.v4.app.NotificationCompat.BigPictureStyle;
import android.support.v4.app.NotificationCompat.BigTextStyle;
import android.support.v4.app.NotificationCompat.Builder;
import android.support.v4.app.NotificationCompat.InboxStyle;
import android.support.v4.app.NotificationCompat.Style;
import android.support.v4.app.NotificationManagerCompat;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.widget.RemoteViews;
import com.github.lzyzsd.jsbridge.BridgeUtil;
import com.mopub.common.AdType;
import com.onesignal.OneSignal.LOG_LEVEL;
import java.lang.reflect.Field;
import java.math.BigInteger;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class GenerateNotification {
    private static Resources contextResources = null;
    private static Context currentContext = null;
    private static Class<?> notificationOpenedClass;
    private static boolean openerIsBroadcast;
    private static String packageName = null;

    private static class OneSignalNotificationBuilder {
        Builder compatBuilder;
        boolean hasLargeIcon;

        private OneSignalNotificationBuilder() {
        }
    }

    private static void createSummaryIdDatabaseEntry(com.onesignal.OneSignalDbHelper r5, java.lang.String r6, int r7) {
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
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:199)
*/
        /*
        r2 = 0;
        r2 = r5.getWritableDbWithRetries();	 Catch:{ Throwable -> 0x0038, all -> 0x0047 }
        r2.beginTransaction();	 Catch:{ Throwable -> 0x0038, all -> 0x0047 }
        r1 = new android.content.ContentValues;	 Catch:{ Throwable -> 0x0038, all -> 0x0047 }
        r1.<init>();	 Catch:{ Throwable -> 0x0038, all -> 0x0047 }
        r3 = "android_notification_id";	 Catch:{ Throwable -> 0x0038, all -> 0x0047 }
        r4 = java.lang.Integer.valueOf(r7);	 Catch:{ Throwable -> 0x0038, all -> 0x0047 }
        r1.put(r3, r4);	 Catch:{ Throwable -> 0x0038, all -> 0x0047 }
        r3 = "group_id";	 Catch:{ Throwable -> 0x0038, all -> 0x0047 }
        r1.put(r3, r6);	 Catch:{ Throwable -> 0x0038, all -> 0x0047 }
        r3 = "is_summary";	 Catch:{ Throwable -> 0x0038, all -> 0x0047 }
        r4 = 1;	 Catch:{ Throwable -> 0x0038, all -> 0x0047 }
        r4 = java.lang.Integer.valueOf(r4);	 Catch:{ Throwable -> 0x0038, all -> 0x0047 }
        r1.put(r3, r4);	 Catch:{ Throwable -> 0x0038, all -> 0x0047 }
        r3 = "notification";	 Catch:{ Throwable -> 0x0038, all -> 0x0047 }
        r4 = 0;	 Catch:{ Throwable -> 0x0038, all -> 0x0047 }
        r2.insertOrThrow(r3, r4, r1);	 Catch:{ Throwable -> 0x0038, all -> 0x0047 }
        r2.setTransactionSuccessful();	 Catch:{ Throwable -> 0x0038, all -> 0x0047 }
        if (r2 == 0) goto L_0x0037;
    L_0x0034:
        r2.endTransaction();
    L_0x0037:
        return;
    L_0x0038:
        r0 = move-exception;
        r3 = com.onesignal.OneSignal.LOG_LEVEL.ERROR;	 Catch:{ Throwable -> 0x0038, all -> 0x0047 }
        r4 = "Error adding summary notification record! ";	 Catch:{ Throwable -> 0x0038, all -> 0x0047 }
        com.onesignal.OneSignal.Log(r3, r4, r0);	 Catch:{ Throwable -> 0x0038, all -> 0x0047 }
        if (r2 == 0) goto L_0x0037;
    L_0x0043:
        r2.endTransaction();
        goto L_0x0037;
    L_0x0047:
        r3 = move-exception;
        if (r2 == 0) goto L_0x004d;
    L_0x004a:
        r2.endTransaction();
    L_0x004d:
        throw r3;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onesignal.GenerateNotification.createSummaryIdDatabaseEntry(com.onesignal.OneSignalDbHelper, java.lang.String, int):void");
    }

    GenerateNotification() {
    }

    static void setStatics(Context inContext) {
        currentContext = inContext;
        packageName = currentContext.getPackageName();
        contextResources = currentContext.getResources();
        PackageManager packageManager = currentContext.getPackageManager();
        Intent intent = new Intent(currentContext, NotificationOpenedReceiver.class);
        intent.setPackage(currentContext.getPackageName());
        if (packageManager.queryBroadcastReceivers(intent, 0).size() > 0) {
            openerIsBroadcast = true;
            notificationOpenedClass = NotificationOpenedReceiver.class;
            return;
        }
        notificationOpenedClass = NotificationOpenedActivity.class;
    }

    static void fromJsonPayload(NotificationGenerationJob notifJob) {
        setStatics(notifJob.context);
        if (notifJob.restoring || !notifJob.showAsAlert || ActivityLifecycleHandler.curActivity == null) {
            showNotification(notifJob);
        } else {
            showNotificationAsAlert(notifJob.jsonPayload, ActivityLifecycleHandler.curActivity, notifJob.getAndroidId().intValue());
        }
    }

    private static void showNotificationAsAlert(final JSONObject gcmJson, final Activity activity, final int notificationId) {
        activity.runOnUiThread(new Runnable() {
            public void run() {
                AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                builder.setTitle(GenerateNotification.getTitle(gcmJson));
                builder.setMessage(gcmJson.optString("alert"));
                List<String> buttonsLabels = new ArrayList();
                List<String> buttonIds = new ArrayList();
                GenerateNotification.addAlertButtons(activity, gcmJson, buttonsLabels, buttonIds);
                final List<String> finalButtonIds = buttonIds;
                Intent buttonIntent = GenerateNotification.getNewBaseIntent(notificationId);
                buttonIntent.putExtra("action_button", true);
                buttonIntent.putExtra("from_alert", true);
                buttonIntent.putExtra("onesignal_data", gcmJson.toString());
                if (gcmJson.has("grp")) {
                    buttonIntent.putExtra("grp", gcmJson.optString("grp"));
                }
                final Intent finalButtonIntent = buttonIntent;
                OnClickListener buttonListener = new OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        int index = which + 3;
                        if (finalButtonIds.size() > 1) {
                            try {
                                JSONObject newJsonData = new JSONObject(gcmJson.toString());
                                newJsonData.put("actionSelected", finalButtonIds.get(index));
                                finalButtonIntent.putExtra("onesignal_data", newJsonData.toString());
                                NotificationOpenedProcessor.processIntent(activity, finalButtonIntent);
                                return;
                            } catch (Throwable th) {
                                return;
                            }
                        }
                        NotificationOpenedProcessor.processIntent(activity, finalButtonIntent);
                    }
                };
                builder.setOnCancelListener(new OnCancelListener() {
                    public void onCancel(DialogInterface dialogInterface) {
                        NotificationOpenedProcessor.processIntent(activity, finalButtonIntent);
                    }
                });
                for (int i = 0; i < buttonsLabels.size(); i++) {
                    if (i == 0) {
                        builder.setNeutralButton((CharSequence) buttonsLabels.get(i), buttonListener);
                    } else if (i == 1) {
                        builder.setNegativeButton((CharSequence) buttonsLabels.get(i), buttonListener);
                    } else if (i == 2) {
                        builder.setPositiveButton((CharSequence) buttonsLabels.get(i), buttonListener);
                    }
                }
                AlertDialog alertDialog = builder.create();
                alertDialog.setCanceledOnTouchOutside(false);
                alertDialog.show();
            }
        });
    }

    private static CharSequence getTitle(JSONObject gcmBundle) {
        CharSequence title = gcmBundle.optString("title", null);
        return title != null ? title : currentContext.getPackageManager().getApplicationLabel(currentContext.getApplicationInfo());
    }

    private static PendingIntent getNewActionPendingIntent(int requestCode, Intent intent) {
        if (openerIsBroadcast) {
            return PendingIntent.getBroadcast(currentContext, requestCode, intent, 134217728);
        }
        return PendingIntent.getActivity(currentContext, requestCode, intent, 134217728);
    }

    private static Intent getNewBaseIntent(int notificationId) {
        Intent intent = new Intent(currentContext, notificationOpenedClass).putExtra("notificationId", notificationId);
        return openerIsBroadcast ? intent : intent.addFlags(603979776);
    }

    private static Intent getNewBaseDeleteIntent(int notificationId) {
        Intent intent = new Intent(currentContext, notificationOpenedClass).putExtra("notificationId", notificationId).putExtra(NotificationTable.COLUMN_NAME_DISMISSED, true);
        return openerIsBroadcast ? intent : intent.addFlags(402718720);
    }

    private static OneSignalNotificationBuilder getBaseOneSignalNotificationBuilder(NotificationGenerationJob notifJob) {
        BigInteger bigInteger;
        int visibility;
        Bitmap largeIcon;
        Bitmap bigPictureIcon;
        Uri soundUri;
        JSONObject gcmBundle = notifJob.jsonPayload;
        OneSignalNotificationBuilder oneSignalNotificationBuilder = new OneSignalNotificationBuilder();
        int notificationIcon = getSmallIconId(gcmBundle);
        int notificationDefaults = 0;
        if (OneSignal.getVibrate(currentContext)) {
            notificationDefaults = 2;
        }
        String message = gcmBundle.optString("alert", null);
        Builder notifBuilder = new Builder(currentContext).setAutoCancel(true).setSmallIcon(notificationIcon).setContentTitle(getTitle(gcmBundle)).setStyle(new BigTextStyle().bigText(message)).setContentText(message).setTicker(message);
        if (notifJob.shownTimeStamp != null) {
            try {
                notifBuilder.setWhen(notifJob.shownTimeStamp.longValue() * 1000);
            } catch (Throwable th) {
            }
        }
        try {
            BigInteger accentColor = getAccentColor(gcmBundle);
            if (accentColor != null) {
                notifBuilder.setColor(accentColor.intValue());
            }
        } catch (Throwable th2) {
        }
        if (gcmBundle.has("ledc")) {
            try {
                BigInteger ledColor = new BigInteger(gcmBundle.optString("ledc"), 16);
                try {
                    notifBuilder.setLights(ledColor.intValue(), 2000, 5000);
                    bigInteger = ledColor;
                } catch (Throwable th3) {
                    bigInteger = ledColor;
                    notificationDefaults |= 4;
                    visibility = 1;
                    if (gcmBundle.has("vis")) {
                        visibility = Integer.parseInt(gcmBundle.optString("vis"));
                    }
                    notifBuilder.setVisibility(visibility);
                    largeIcon = getLargeIcon(gcmBundle);
                    if (largeIcon != null) {
                        oneSignalNotificationBuilder.hasLargeIcon = true;
                        notifBuilder.setLargeIcon(largeIcon);
                    }
                    bigPictureIcon = getBitmap(gcmBundle.optString("bicon", null));
                    if (bigPictureIcon != null) {
                        notifBuilder.setStyle(new BigPictureStyle().bigPicture(bigPictureIcon).setSummaryText(message));
                    }
                    if (isSoundEnabled(gcmBundle)) {
                        soundUri = getCustomSound(gcmBundle);
                        if (soundUri != null) {
                            notificationDefaults |= 1;
                        } else {
                            notifBuilder.setSound(soundUri);
                        }
                    }
                    notifBuilder.setDefaults(notificationDefaults);
                    if (gcmBundle.optInt("pri", 0) > 9) {
                        notifBuilder.setPriority(2);
                    }
                    oneSignalNotificationBuilder.compatBuilder = notifBuilder;
                    return oneSignalNotificationBuilder;
                }
            } catch (Throwable th4) {
                notificationDefaults |= 4;
                visibility = 1;
                if (gcmBundle.has("vis")) {
                    visibility = Integer.parseInt(gcmBundle.optString("vis"));
                }
                notifBuilder.setVisibility(visibility);
                largeIcon = getLargeIcon(gcmBundle);
                if (largeIcon != null) {
                    oneSignalNotificationBuilder.hasLargeIcon = true;
                    notifBuilder.setLargeIcon(largeIcon);
                }
                bigPictureIcon = getBitmap(gcmBundle.optString("bicon", null));
                if (bigPictureIcon != null) {
                    notifBuilder.setStyle(new BigPictureStyle().bigPicture(bigPictureIcon).setSummaryText(message));
                }
                if (isSoundEnabled(gcmBundle)) {
                    soundUri = getCustomSound(gcmBundle);
                    if (soundUri != null) {
                        notifBuilder.setSound(soundUri);
                    } else {
                        notificationDefaults |= 1;
                    }
                }
                notifBuilder.setDefaults(notificationDefaults);
                if (gcmBundle.optInt("pri", 0) > 9) {
                    notifBuilder.setPriority(2);
                }
                oneSignalNotificationBuilder.compatBuilder = notifBuilder;
                return oneSignalNotificationBuilder;
            }
        }
        notificationDefaults |= 4;
        visibility = 1;
        try {
            if (gcmBundle.has("vis")) {
                visibility = Integer.parseInt(gcmBundle.optString("vis"));
            }
            notifBuilder.setVisibility(visibility);
        } catch (Throwable th5) {
        }
        largeIcon = getLargeIcon(gcmBundle);
        if (largeIcon != null) {
            oneSignalNotificationBuilder.hasLargeIcon = true;
            notifBuilder.setLargeIcon(largeIcon);
        }
        bigPictureIcon = getBitmap(gcmBundle.optString("bicon", null));
        if (bigPictureIcon != null) {
            notifBuilder.setStyle(new BigPictureStyle().bigPicture(bigPictureIcon).setSummaryText(message));
        }
        if (isSoundEnabled(gcmBundle)) {
            soundUri = getCustomSound(gcmBundle);
            if (soundUri != null) {
                notifBuilder.setSound(soundUri);
            } else {
                notificationDefaults |= 1;
            }
        }
        notifBuilder.setDefaults(notificationDefaults);
        if (gcmBundle.optInt("pri", 0) > 9) {
            notifBuilder.setPriority(2);
        }
        oneSignalNotificationBuilder.compatBuilder = notifBuilder;
        return oneSignalNotificationBuilder;
    }

    private static void removeNotifyOptions(Builder builder) {
        builder.setDefaults(0).setSound(null).setVibrate(null).setTicker(null);
    }

    private static void showNotification(NotificationGenerationJob notifJob) {
        Notification notification;
        Random random = new Random();
        int notificationId = notifJob.getAndroidId().intValue();
        JSONObject gcmBundle = notifJob.jsonPayload;
        String group = gcmBundle.optString("grp", null);
        OneSignalNotificationBuilder oneSignalNotificationBuilder = getBaseOneSignalNotificationBuilder(notifJob);
        Builder notifBuilder = oneSignalNotificationBuilder.compatBuilder;
        addNotificationActionButtons(gcmBundle, notifBuilder, notificationId, null);
        try {
            addBackgroundImage(gcmBundle, notifBuilder);
        } catch (Throwable t) {
            OneSignal.Log(LOG_LEVEL.ERROR, "Could not set background notification image!", t);
        }
        if (!(notifJob.overrideSettings == null || notifJob.overrideSettings.extender == null)) {
            notifBuilder.extend(notifJob.overrideSettings.extender);
        }
        if (notifJob.restoring) {
            removeNotifyOptions(notifBuilder);
        }
        if (group != null) {
            notifBuilder.setContentIntent(getNewActionPendingIntent(random.nextInt(), getNewBaseIntent(notificationId).putExtra("onesignal_data", gcmBundle.toString()).putExtra("grp", group)));
            notifBuilder.setDeleteIntent(getNewActionPendingIntent(random.nextInt(), getNewBaseDeleteIntent(notificationId).putExtra("grp", group)));
            notifBuilder.setGroup(group);
            notification = notifBuilder.build();
            createSummaryNotification(notifJob, oneSignalNotificationBuilder);
        } else {
            notifBuilder.setContentIntent(getNewActionPendingIntent(random.nextInt(), getNewBaseIntent(notificationId).putExtra("onesignal_data", gcmBundle.toString())));
            notifBuilder.setDeleteIntent(getNewActionPendingIntent(random.nextInt(), getNewBaseDeleteIntent(notificationId)));
            notification = notifBuilder.build();
        }
        if (group == null || VERSION.SDK_INT > 17) {
            addXiaomiSettings(oneSignalNotificationBuilder, notification);
            NotificationManagerCompat.from(currentContext).notify(notificationId, notification);
        }
    }

    private static void addXiaomiSettings(OneSignalNotificationBuilder oneSignalNotificationBuilder, Notification notification) {
        if (VERSION.SDK_INT >= 11 && oneSignalNotificationBuilder.hasLargeIcon) {
            try {
                Object miuiNotification = Class.forName("android.app.MiuiNotification").newInstance();
                Field customizedIconField = miuiNotification.getClass().getDeclaredField("customizedIcon");
                customizedIconField.setAccessible(true);
                customizedIconField.set(miuiNotification, Boolean.valueOf(true));
                Field extraNotificationField = notification.getClass().getField("extraNotification");
                extraNotificationField.setAccessible(true);
                extraNotificationField.set(notification, miuiNotification);
            } catch (Throwable th) {
            }
        }
    }

    static void updateSummaryNotification(NotificationGenerationJob notifJob) {
        setStatics(notifJob.context);
        createSummaryNotification(notifJob, null);
    }

    static void createSummaryNotification(NotificationGenerationJob notifJob, OneSignalNotificationBuilder notifBuilder) {
        Throwable th;
        boolean updateSummary = notifJob.restoring;
        JSONObject gcmBundle = notifJob.jsonPayload;
        String group = gcmBundle.optString("grp", null);
        Random random = new Random();
        PendingIntent summaryDeleteIntent = getNewActionPendingIntent(random.nextInt(), getNewBaseDeleteIntent(0).putExtra("summary", group));
        Integer summaryNotificationId = null;
        String firstFullData = null;
        Collection<SpannableString> summaryList = null;
        OneSignalDbHelper dbHelper = OneSignalDbHelper.getInstance(currentContext);
        Cursor cursor = null;
        try {
            Notification summaryNotification;
            SQLiteDatabase readableDb = dbHelper.getReadableDbWithRetries();
            String[] retColumn = new String[]{NotificationTable.COLUMN_NAME_ANDROID_NOTIFICATION_ID, NotificationTable.COLUMN_NAME_FULL_DATA, NotificationTable.COLUMN_NAME_IS_SUMMARY, "title", "message"};
            String whereStr = "group_id = ? AND dismissed = 0 AND opened = 0";
            String[] whereArgs = new String[]{group};
            if (!(updateSummary || notifJob.getAndroidId().intValue() == -1)) {
                whereStr = whereStr + " AND android_notification_id <> " + notifJob.getAndroidId();
            }
            cursor = readableDb.query("notification", retColumn, whereStr, whereArgs, null, null, "_id DESC");
            if (cursor.moveToFirst()) {
                Collection<SpannableString> summaryList2 = new ArrayList();
                do {
                    try {
                        if (cursor.getInt(cursor.getColumnIndex(NotificationTable.COLUMN_NAME_IS_SUMMARY)) == 1) {
                            summaryNotificationId = Integer.valueOf(cursor.getInt(cursor.getColumnIndex(NotificationTable.COLUMN_NAME_ANDROID_NOTIFICATION_ID)));
                        } else {
                            String title = cursor.getString(cursor.getColumnIndex("title"));
                            if (title == null) {
                                title = "";
                            } else {
                                title = title + " ";
                            }
                            SpannableString spannableString = new SpannableString(title + cursor.getString(cursor.getColumnIndex("message")));
                            if (title.length() > 0) {
                                spannableString.setSpan(new StyleSpan(1), 0, title.length(), 0);
                            }
                            summaryList2.add(spannableString);
                            if (firstFullData == null) {
                                firstFullData = cursor.getString(cursor.getColumnIndex(NotificationTable.COLUMN_NAME_FULL_DATA));
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (Throwable th2) {
                        th = th2;
                        summaryList = summaryList2;
                    }
                } while (cursor.moveToNext());
                if (updateSummary && firstFullData != null) {
                    summaryList = summaryList2;
                    gcmBundle = new JSONObject(firstFullData);
                }
                summaryList = summaryList2;
            }
            if (!(cursor == null || cursor.isClosed())) {
                cursor.close();
            }
            if (summaryNotificationId == null) {
                summaryNotificationId = Integer.valueOf(random.nextInt());
                createSummaryIdDatabaseEntry(dbHelper, group, summaryNotificationId.intValue());
            }
            PendingIntent summaryContentIntent = getNewActionPendingIntent(random.nextInt(), createBaseSummaryIntent(summaryNotificationId.intValue(), gcmBundle, group));
            Builder summaryBuilder;
            if (summaryList == null || ((!updateSummary || summaryList.size() <= 1) && (updateSummary || summaryList.size() <= 0))) {
                summaryBuilder = notifBuilder.compatBuilder;
                summaryBuilder.mActions.clear();
                addNotificationActionButtons(gcmBundle, summaryBuilder, summaryNotificationId.intValue(), group);
                summaryBuilder.setContentIntent(summaryContentIntent).setDeleteIntent(summaryDeleteIntent).setOnlyAlertOnce(updateSummary).setGroup(group).setGroupSummary(true);
                summaryNotification = summaryBuilder.build();
                addXiaomiSettings(notifBuilder, summaryNotification);
            } else {
                int notificationCount = summaryList.size() + (updateSummary ? 0 : 1);
                String summaryMessage = gcmBundle.optString("grp_msg", null);
                if (summaryMessage == null) {
                    summaryMessage = notificationCount + " new messages";
                } else {
                    summaryMessage = summaryMessage.replace("$[notif_count]", "" + notificationCount);
                }
                summaryBuilder = getBaseOneSignalNotificationBuilder(notifJob).compatBuilder;
                if (updateSummary) {
                    removeNotifyOptions(summaryBuilder);
                }
                summaryBuilder.setContentIntent(summaryContentIntent).setDeleteIntent(summaryDeleteIntent).setContentTitle(currentContext.getPackageManager().getApplicationLabel(currentContext.getApplicationInfo())).setContentText(summaryMessage).setNumber(notificationCount).setSmallIcon(getDefaultSmallIconId()).setLargeIcon(getDefaultLargeIcon()).setOnlyAlertOnce(updateSummary).setGroup(group).setGroupSummary(true);
                if (!updateSummary) {
                    summaryBuilder.setTicker(summaryMessage);
                }
                Style inboxStyle = new InboxStyle();
                if (!updateSummary) {
                    String line1Title = gcmBundle.optString("title", null);
                    if (line1Title == null) {
                        line1Title = "";
                    } else {
                        line1Title = line1Title + " ";
                    }
                    CharSequence spannableString2 = new SpannableString(line1Title + gcmBundle.optString("alert"));
                    if (line1Title.length() > 0) {
                        spannableString2.setSpan(new StyleSpan(1), 0, line1Title.length(), 0);
                    }
                    inboxStyle.addLine(spannableString2);
                }
                for (SpannableString line : summaryList) {
                    inboxStyle.addLine(line);
                }
                inboxStyle.setBigContentTitle(summaryMessage);
                summaryBuilder.setStyle(inboxStyle);
                summaryNotification = summaryBuilder.build();
            }
            NotificationManagerCompat.from(currentContext).notify(summaryNotificationId.intValue(), summaryNotification);
        } catch (Throwable th3) {
            th = th3;
            if (!(cursor == null || cursor.isClosed())) {
                cursor.close();
            }
            throw th;
        }
    }

    private static Intent createBaseSummaryIntent(int summaryNotificationId, JSONObject gcmBundle, String group) {
        return getNewBaseIntent(summaryNotificationId).putExtra("onesignal_data", gcmBundle.toString()).putExtra("summary", group);
    }

    private static void addBackgroundImage(JSONObject gcmBundle, Builder notifBuilder) throws Throwable {
        if (VERSION.SDK_INT >= 16) {
            Bitmap bg_image = null;
            JSONObject jsonBgImage = null;
            String jsonStrBgImage = gcmBundle.optString("bg_img", null);
            if (jsonStrBgImage != null) {
                jsonBgImage = new JSONObject(jsonStrBgImage);
                bg_image = getBitmap(jsonBgImage.optString("img", null));
            }
            if (bg_image == null) {
                bg_image = getBitmapFromAssetsOrResourceName("onesignal_bgimage_default_image");
            }
            if (bg_image != null) {
                RemoteViews customView = new RemoteViews(currentContext.getPackageName(), R.layout.onesignal_bgimage_notif_layout);
                customView.setTextViewText(R.id.os_bgimage_notif_title, getTitle(gcmBundle));
                customView.setTextViewText(R.id.os_bgimage_notif_body, gcmBundle.optString("alert"));
                setTextColor(customView, jsonBgImage, R.id.os_bgimage_notif_title, "tc", "onesignal_bgimage_notif_title_color");
                setTextColor(customView, jsonBgImage, R.id.os_bgimage_notif_body, "bc", "onesignal_bgimage_notif_body_color");
                String alignSetting = null;
                if (jsonBgImage == null || !jsonBgImage.has("img_align")) {
                    int iAlignSetting = contextResources.getIdentifier("onesignal_bgimage_notif_image_align", SchemaSymbols.ATTVAL_STRING, packageName);
                    if (iAlignSetting != 0) {
                        alignSetting = contextResources.getString(iAlignSetting);
                    }
                } else {
                    alignSetting = jsonBgImage.getString("img_align");
                }
                if ("right".equals(alignSetting)) {
                    customView.setViewPadding(R.id.os_bgimage_notif_bgimage_align_layout, -5000, 0, 0, 0);
                    customView.setImageViewBitmap(R.id.os_bgimage_notif_bgimage_right_aligned, bg_image);
                    customView.setViewVisibility(R.id.os_bgimage_notif_bgimage_right_aligned, 0);
                    customView.setViewVisibility(R.id.os_bgimage_notif_bgimage, 2);
                } else {
                    customView.setImageViewBitmap(R.id.os_bgimage_notif_bgimage, bg_image);
                }
                notifBuilder.setContent(customView);
                notifBuilder.setStyle(null);
            }
        }
    }

    private static void setTextColor(RemoteViews customView, JSONObject gcmBundle, int viewId, String colorPayloadKey, String colorDefaultResource) {
        Integer color = safeGetColorFromHex(gcmBundle, colorPayloadKey);
        if (color != null) {
            customView.setTextColor(viewId, color.intValue());
            return;
        }
        int colorId = contextResources.getIdentifier(colorDefaultResource, "color", packageName);
        if (colorId != 0) {
            customView.setTextColor(viewId, ContextCompat.getColor(currentContext, colorId));
        }
    }

    private static Integer safeGetColorFromHex(JSONObject gcmBundle, String colorKey) {
        if (gcmBundle != null) {
            try {
                if (gcmBundle.has(colorKey)) {
                    return Integer.valueOf(new BigInteger(gcmBundle.optString(colorKey), 16).intValue());
                }
            } catch (Throwable th) {
            }
        }
        return null;
    }

    private static boolean isValidResourceName(String name) {
        return (name == null || name.matches("^[0-9]")) ? false : true;
    }

    private static Bitmap getLargeIcon(JSONObject gcmBundle) {
        if (VERSION.SDK_INT < 11) {
            return null;
        }
        Bitmap bitmap = getBitmap(gcmBundle.optString("licon"));
        if (bitmap == null) {
            bitmap = getBitmapFromAssetsOrResourceName("ic_onesignal_large_icon_default");
        }
        if (bitmap != null) {
            return resizeBitmapForLargeIconArea(bitmap);
        }
        return null;
    }

    private static Bitmap getDefaultLargeIcon() {
        if (VERSION.SDK_INT < 11) {
            return null;
        }
        return resizeBitmapForLargeIconArea(getBitmapFromAssetsOrResourceName("ic_onesignal_large_icon_default"));
    }

    private static Bitmap resizeBitmapForLargeIconArea(Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        try {
            int systemLargeIconHeight = (int) contextResources.getDimension(17104902);
            int systemLargeIconWidth = (int) contextResources.getDimension(17104901);
            int bitmapHeight = bitmap.getHeight();
            int bitmapWidth = bitmap.getWidth();
            if (bitmapWidth <= systemLargeIconWidth && bitmapHeight <= systemLargeIconHeight) {
                return bitmap;
            }
            int newWidth = systemLargeIconWidth;
            int newHeight = systemLargeIconHeight;
            if (bitmapHeight > bitmapWidth) {
                newWidth = (int) (((float) newHeight) * (((float) bitmapWidth) / ((float) bitmapHeight)));
            } else if (bitmapWidth > bitmapHeight) {
                newHeight = (int) (((float) newWidth) * (((float) bitmapHeight) / ((float) bitmapWidth)));
            }
            return Bitmap.createScaledBitmap(bitmap, newWidth, newHeight, true);
        } catch (Throwable th) {
            return bitmap;
        }
    }

    private static Bitmap getBitmapFromAssetsOrResourceName(String bitmapStr) {
        Bitmap bitmap = null;
        try {
            bitmap = BitmapFactory.decodeStream(currentContext.getAssets().open(bitmapStr));
        } catch (Throwable th) {
        }
        if (bitmap != null) {
            return bitmap;
        }
        try {
            for (String extension : Arrays.asList(new String[]{".png", ".webp", ".jpg", ".gif", ".bmp"})) {
                try {
                    bitmap = BitmapFactory.decodeStream(currentContext.getAssets().open(bitmapStr + extension));
                    continue;
                } catch (Throwable th2) {
                    continue;
                }
                if (bitmap != null) {
                    return bitmap;
                }
            }
            int bitmapId = getResourceIcon(bitmapStr);
            if (bitmapId != 0) {
                return BitmapFactory.decodeResource(contextResources, bitmapId);
            }
        } catch (Throwable th3) {
        }
        return null;
    }

    private static Bitmap getBitmapFromURL(String location) {
        try {
            return BitmapFactory.decodeStream(new URL(location).openConnection().getInputStream());
        } catch (Throwable t) {
            OneSignal.Log(LOG_LEVEL.WARN, "Could not download image!", t);
            return null;
        }
    }

    private static Bitmap getBitmap(String name) {
        if (name == null) {
            return null;
        }
        String trimmedName = name.trim();
        if (trimmedName.startsWith("http://") || trimmedName.startsWith("https://")) {
            return getBitmapFromURL(trimmedName);
        }
        return getBitmapFromAssetsOrResourceName(name);
    }

    private static int getResourceIcon(String iconName) {
        if (iconName == null) {
            return 0;
        }
        String trimmedIconName = iconName.trim();
        if (!isValidResourceName(trimmedIconName)) {
            return 0;
        }
        int notificationIcon = getDrawableId(trimmedIconName);
        if (notificationIcon != 0) {
            return notificationIcon;
        }
        try {
            return drawable.class.getField(iconName).getInt(null);
        } catch (Throwable th) {
            return 0;
        }
    }

    private static int getSmallIconId(JSONObject gcmBundle) {
        int notificationIcon = getResourceIcon(gcmBundle.optString("sicon", null));
        return notificationIcon != 0 ? notificationIcon : getDefaultSmallIconId();
    }

    private static int getDefaultSmallIconId() {
        int notificationIcon = getDrawableId("ic_stat_onesignal_default");
        if (notificationIcon != 0) {
            return notificationIcon;
        }
        notificationIcon = getDrawableId("corona_statusbar_icon_default");
        if (notificationIcon != 0) {
            return notificationIcon;
        }
        notificationIcon = getDrawableId("ic_os_notification_fallback_white_24dp");
        return notificationIcon == 0 ? 17301598 : notificationIcon;
    }

    private static int getDrawableId(String name) {
        return contextResources.getIdentifier(name, "drawable", packageName);
    }

    private static boolean isSoundEnabled(JSONObject gcmBundle) {
        String sound = gcmBundle.optString("sound", null);
        if ("null".equals(sound) || "nil".equals(sound)) {
            return false;
        }
        return OneSignal.getSoundEnabled(currentContext);
    }

    private static Uri getCustomSound(JSONObject gcmBundle) {
        int soundId;
        String sound = gcmBundle.optString("sound", null);
        if (isValidResourceName(sound)) {
            soundId = contextResources.getIdentifier(sound, "raw", packageName);
            if (soundId != 0) {
                return Uri.parse("android.resource://" + packageName + BridgeUtil.SPLIT_MARK + soundId);
            }
        }
        soundId = contextResources.getIdentifier("onesignal_default_sound", "raw", packageName);
        if (soundId != 0) {
            return Uri.parse("android.resource://" + packageName + BridgeUtil.SPLIT_MARK + soundId);
        }
        return null;
    }

    private static BigInteger getAccentColor(JSONObject gcmBundle) {
        try {
            if (gcmBundle.has("bgac")) {
                return new BigInteger(gcmBundle.optString("bgac", null), 16);
            }
        } catch (Throwable th) {
        }
        try {
            String defaultColor = OSUtils.getManifestMeta(currentContext, "com.onesignal.NotificationAccentColor.DEFAULT");
            if (defaultColor != null) {
                return new BigInteger(defaultColor, 16);
            }
        } catch (Throwable th2) {
        }
        return null;
    }

    private static void addNotificationActionButtons(JSONObject gcmBundle, Builder mBuilder, int notificationId, String groupSummary) {
        try {
            JSONObject customJson = new JSONObject(gcmBundle.optString(AdType.CUSTOM));
            if (customJson.has("a")) {
                JSONObject additionalDataJSON = customJson.getJSONObject("a");
                if (additionalDataJSON.has("actionButtons")) {
                    JSONArray buttons = additionalDataJSON.getJSONArray("actionButtons");
                    for (int i = 0; i < buttons.length(); i++) {
                        JSONObject button = buttons.optJSONObject(i);
                        JSONObject bundle = new JSONObject(gcmBundle.toString());
                        Intent buttonIntent = getNewBaseIntent(notificationId);
                        buttonIntent.setAction("" + i);
                        buttonIntent.putExtra("action_button", true);
                        bundle.put("actionSelected", button.optString("id"));
                        buttonIntent.putExtra("onesignal_data", bundle.toString());
                        if (groupSummary != null) {
                            buttonIntent.putExtra("summary", groupSummary);
                        } else if (gcmBundle.has("grp")) {
                            buttonIntent.putExtra("grp", gcmBundle.optString("grp"));
                        }
                        PendingIntent buttonPIntent = getNewActionPendingIntent(notificationId, buttonIntent);
                        int buttonIcon = 0;
                        if (button.has("icon")) {
                            buttonIcon = getResourceIcon(button.optString("icon"));
                        }
                        mBuilder.addAction(buttonIcon, button.optString("text"), buttonPIntent);
                    }
                }
            }
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    private static void addAlertButtons(Context context, JSONObject gcmBundle, List<String> buttonsLabels, List<String> buttonsIds) {
        try {
            JSONObject customJson = new JSONObject(gcmBundle.optString(AdType.CUSTOM));
            if (customJson.has("a")) {
                JSONObject additionalDataJSON = customJson.getJSONObject("a");
                if (additionalDataJSON.has("actionButtons")) {
                    JSONArray buttons = additionalDataJSON.optJSONArray("actionButtons");
                    for (int i = 0; i < buttons.length(); i++) {
                        JSONObject button = buttons.getJSONObject(i);
                        buttonsLabels.add(button.optString("text"));
                        buttonsIds.add(button.optString("id"));
                    }
                }
            }
            if (buttonsLabels.size() < 3) {
                buttonsLabels.add(OSUtils.getResourceString(context, "onesignal_in_app_alert_ok_button_text", "Ok"));
                buttonsIds.add("__DEFAULT__");
            }
        } catch (Throwable t) {
            OneSignal.Log(LOG_LEVEL.ERROR, "Failed to parse buttons for alert dialog.", t);
        }
    }
}
