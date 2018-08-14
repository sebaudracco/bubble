package com.onesignal;

import android.app.NotificationManager;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import org.json.JSONException;
import org.json.JSONObject;

class NotificationSummaryManager {
    private static void restoreSummary(android.content.Context r11, java.lang.String r12) {
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
        r9 = com.onesignal.OneSignalDbHelper.getInstance(r11);
        r8 = 0;
        r1 = 1;
        r4 = new java.lang.String[r1];
        r1 = 0;
        r4[r1] = r12;
        r0 = r9.getReadableDbWithRetries();	 Catch:{ Throwable -> 0x002d, all -> 0x0042 }
        r1 = "notification";	 Catch:{ Throwable -> 0x002d, all -> 0x0042 }
        r2 = com.onesignal.NotificationRestorer.COLUMNS_FOR_RESTORE;	 Catch:{ Throwable -> 0x002d, all -> 0x0042 }
        r3 = "group_id = ? AND dismissed = 0 AND opened = 0 AND is_summary = 0";	 Catch:{ Throwable -> 0x002d, all -> 0x0042 }
        r5 = 0;	 Catch:{ Throwable -> 0x002d, all -> 0x0042 }
        r6 = 0;	 Catch:{ Throwable -> 0x002d, all -> 0x0042 }
        r7 = 0;	 Catch:{ Throwable -> 0x002d, all -> 0x0042 }
        r8 = r0.query(r1, r2, r3, r4, r5, r6, r7);	 Catch:{ Throwable -> 0x002d, all -> 0x0042 }
        com.onesignal.NotificationRestorer.showNotifications(r11, r8);	 Catch:{ Throwable -> 0x002d, all -> 0x0042 }
        if (r8 == 0) goto L_0x002c;
    L_0x0023:
        r1 = r8.isClosed();
        if (r1 != 0) goto L_0x002c;
    L_0x0029:
        r8.close();
    L_0x002c:
        return;
    L_0x002d:
        r10 = move-exception;
        r1 = com.onesignal.OneSignal.LOG_LEVEL.ERROR;	 Catch:{ Throwable -> 0x002d, all -> 0x0042 }
        r2 = "Error restoring notification records! ";	 Catch:{ Throwable -> 0x002d, all -> 0x0042 }
        com.onesignal.OneSignal.Log(r1, r2, r10);	 Catch:{ Throwable -> 0x002d, all -> 0x0042 }
        if (r8 == 0) goto L_0x002c;
    L_0x0038:
        r1 = r8.isClosed();
        if (r1 != 0) goto L_0x002c;
    L_0x003e:
        r8.close();
        goto L_0x002c;
    L_0x0042:
        r1 = move-exception;
        if (r8 == 0) goto L_0x004e;
    L_0x0045:
        r2 = r8.isClosed();
        if (r2 != 0) goto L_0x004e;
    L_0x004b:
        r8.close();
    L_0x004e:
        throw r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onesignal.NotificationSummaryManager.restoreSummary(android.content.Context, java.lang.String):void");
    }

    static void updateSummaryNotificationAfterChildRemoved(android.content.Context r4, android.database.sqlite.SQLiteDatabase r5, java.lang.String r6, boolean r7) {
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
        r0 = 0;
        r0 = internalUpdateSummaryNotificationAfterChildRemoved(r4, r5, r6, r7);	 Catch:{ Throwable -> 0x0011, all -> 0x0026 }
        if (r0 == 0) goto L_0x0010;
    L_0x0007:
        r2 = r0.isClosed();
        if (r2 != 0) goto L_0x0010;
    L_0x000d:
        r0.close();
    L_0x0010:
        return;
    L_0x0011:
        r1 = move-exception;
        r2 = com.onesignal.OneSignal.LOG_LEVEL.ERROR;	 Catch:{ Throwable -> 0x0011, all -> 0x0026 }
        r3 = "Error running updateSummaryNotificationAfterChildRemoved!";	 Catch:{ Throwable -> 0x0011, all -> 0x0026 }
        com.onesignal.OneSignal.Log(r2, r3, r1);	 Catch:{ Throwable -> 0x0011, all -> 0x0026 }
        if (r0 == 0) goto L_0x0010;
    L_0x001c:
        r2 = r0.isClosed();
        if (r2 != 0) goto L_0x0010;
    L_0x0022:
        r0.close();
        goto L_0x0010;
    L_0x0026:
        r2 = move-exception;
        if (r0 == 0) goto L_0x0032;
    L_0x0029:
        r3 = r0.isClosed();
        if (r3 != 0) goto L_0x0032;
    L_0x002f:
        r0.close();
    L_0x0032:
        throw r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onesignal.NotificationSummaryManager.updateSummaryNotificationAfterChildRemoved(android.content.Context, android.database.sqlite.SQLiteDatabase, java.lang.String, boolean):void");
    }

    NotificationSummaryManager() {
    }

    static void updatePossibleDependentSummaryOnDismiss(Context context, SQLiteDatabase writableDb, int androidNotificationId) {
        String[] strArr = new String[]{"group_id"};
        String str = "android_notification_id = " + androidNotificationId;
        Cursor cursor = writableDb.query("notification", strArr, str, null, null, null, null);
        if (cursor.moveToFirst()) {
            String group = cursor.getString(cursor.getColumnIndex("group_id"));
            cursor.close();
            if (group != null) {
                updateSummaryNotificationAfterChildRemoved(context, writableDb, group, true);
                return;
            }
            return;
        }
        cursor.close();
    }

    private static Cursor internalUpdateSummaryNotificationAfterChildRemoved(Context context, SQLiteDatabase writableDb, String group, boolean dismissed) {
        Cursor cursor = writableDb.query("notification", new String[]{NotificationTable.COLUMN_NAME_ANDROID_NOTIFICATION_ID, NotificationTable.COLUMN_NAME_CREATED_TIME}, "group_id = ? AND dismissed = 0 AND opened = 0 AND is_summary = 0", new String[]{group}, null, null, "_id DESC");
        int notifsInGroup = cursor.getCount();
        if (notifsInGroup == 0) {
            cursor.close();
            Integer androidNotifId = getSummaryNotificationId(writableDb, group);
            if (androidNotifId != null) {
                ((NotificationManager) context.getSystemService("notification")).cancel(androidNotifId.intValue());
                ContentValues values = new ContentValues();
                values.put(dismissed ? NotificationTable.COLUMN_NAME_DISMISSED : NotificationTable.COLUMN_NAME_OPENED, Integer.valueOf(1));
                writableDb.update("notification", values, "android_notification_id = " + androidNotifId, null);
            }
        } else if (notifsInGroup == 1) {
            cursor.close();
            if (getSummaryNotificationId(writableDb, group) != null) {
                restoreSummary(context, group);
            }
        } else {
            try {
                cursor.moveToFirst();
                Long datetime = Long.valueOf(cursor.getLong(cursor.getColumnIndex(NotificationTable.COLUMN_NAME_CREATED_TIME)));
                cursor.close();
                if (getSummaryNotificationId(writableDb, group) != null) {
                    NotificationGenerationJob notifJob = new NotificationGenerationJob(context);
                    notifJob.restoring = true;
                    notifJob.shownTimeStamp = datetime;
                    JSONObject payload = new JSONObject();
                    payload.put("grp", group);
                    notifJob.jsonPayload = payload;
                    GenerateNotification.updateSummaryNotification(notifJob);
                }
            } catch (JSONException e) {
            }
        }
        return cursor;
    }

    private static Integer getSummaryNotificationId(SQLiteDatabase writableDb, String group) {
        Integer androidNotifId = null;
        Cursor cursor = null;
        try {
            SQLiteDatabase sQLiteDatabase = writableDb;
            cursor = sQLiteDatabase.query("notification", new String[]{NotificationTable.COLUMN_NAME_ANDROID_NOTIFICATION_ID}, "group_id = ? AND dismissed = 0 AND opened = 0 AND is_summary = 1", new String[]{group}, null, null, null);
            if (cursor.moveToFirst()) {
                androidNotifId = Integer.valueOf(cursor.getInt(cursor.getColumnIndex(NotificationTable.COLUMN_NAME_ANDROID_NOTIFICATION_ID)));
                cursor.close();
                if (!(cursor == null || cursor.isClosed())) {
                    cursor.close();
                }
                return androidNotifId;
            }
            cursor.close();
            if (!(cursor == null || cursor.isClosed())) {
                cursor.close();
            }
            return null;
        } catch (Throwable th) {
            if (!(cursor == null || cursor.isClosed())) {
                cursor.close();
            }
        }
    }
}
