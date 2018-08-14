package com.onesignal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;

class NotificationRestorer {
    static final String[] COLUMNS_FOR_RESTORE = new String[]{NotificationTable.COLUMN_NAME_ANDROID_NOTIFICATION_ID, NotificationTable.COLUMN_NAME_FULL_DATA, NotificationTable.COLUMN_NAME_CREATED_TIME};
    public static boolean restored;

    public static void restore(android.content.Context r12) {
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
        r1 = restored;
        if (r1 == 0) goto L_0x0005;
    L_0x0004:
        return;
    L_0x0005:
        r1 = 1;
        restored = r1;
        r9 = com.onesignal.OneSignalDbHelper.getInstance(r12);
        r11 = 0;
        r11 = r9.getWritableDbWithRetries();	 Catch:{ Throwable -> 0x0093, all -> 0x00a3 }
        r11.beginTransaction();	 Catch:{ Throwable -> 0x0093, all -> 0x00a3 }
        com.onesignal.NotificationBundleProcessor.deleteOldNotifications(r11);	 Catch:{ Throwable -> 0x0093, all -> 0x00a3 }
        r11.setTransactionSuccessful();	 Catch:{ Throwable -> 0x0093, all -> 0x00a3 }
        if (r11 == 0) goto L_0x001f;
    L_0x001c:
        r11.endTransaction();
    L_0x001f:
        r8 = 0;
        r0 = r9.getReadableDbWithRetries();	 Catch:{ Throwable -> 0x00aa, all -> 0x00c0 }
        r1 = "notification";	 Catch:{ Throwable -> 0x00aa, all -> 0x00c0 }
        r2 = COLUMNS_FOR_RESTORE;	 Catch:{ Throwable -> 0x00aa, all -> 0x00c0 }
        r3 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x00aa, all -> 0x00c0 }
        r3.<init>();	 Catch:{ Throwable -> 0x00aa, all -> 0x00c0 }
        r4 = "created_time > ";	 Catch:{ Throwable -> 0x00aa, all -> 0x00c0 }
        r3 = r3.append(r4);	 Catch:{ Throwable -> 0x00aa, all -> 0x00c0 }
        r4 = java.lang.System.currentTimeMillis();	 Catch:{ Throwable -> 0x00aa, all -> 0x00c0 }
        r6 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;	 Catch:{ Throwable -> 0x00aa, all -> 0x00c0 }
        r4 = r4 / r6;	 Catch:{ Throwable -> 0x00aa, all -> 0x00c0 }
        r6 = 604800; // 0x93a80 float:8.47505E-40 double:2.98811E-318;	 Catch:{ Throwable -> 0x00aa, all -> 0x00c0 }
        r4 = r4 - r6;	 Catch:{ Throwable -> 0x00aa, all -> 0x00c0 }
        r3 = r3.append(r4);	 Catch:{ Throwable -> 0x00aa, all -> 0x00c0 }
        r4 = " AND ";	 Catch:{ Throwable -> 0x00aa, all -> 0x00c0 }
        r3 = r3.append(r4);	 Catch:{ Throwable -> 0x00aa, all -> 0x00c0 }
        r4 = "dismissed";	 Catch:{ Throwable -> 0x00aa, all -> 0x00c0 }
        r3 = r3.append(r4);	 Catch:{ Throwable -> 0x00aa, all -> 0x00c0 }
        r4 = " = 0 AND ";	 Catch:{ Throwable -> 0x00aa, all -> 0x00c0 }
        r3 = r3.append(r4);	 Catch:{ Throwable -> 0x00aa, all -> 0x00c0 }
        r4 = "opened";	 Catch:{ Throwable -> 0x00aa, all -> 0x00c0 }
        r3 = r3.append(r4);	 Catch:{ Throwable -> 0x00aa, all -> 0x00c0 }
        r4 = " = 0 AND ";	 Catch:{ Throwable -> 0x00aa, all -> 0x00c0 }
        r3 = r3.append(r4);	 Catch:{ Throwable -> 0x00aa, all -> 0x00c0 }
        r4 = "is_summary";	 Catch:{ Throwable -> 0x00aa, all -> 0x00c0 }
        r3 = r3.append(r4);	 Catch:{ Throwable -> 0x00aa, all -> 0x00c0 }
        r4 = " = 0";	 Catch:{ Throwable -> 0x00aa, all -> 0x00c0 }
        r3 = r3.append(r4);	 Catch:{ Throwable -> 0x00aa, all -> 0x00c0 }
        r3 = r3.toString();	 Catch:{ Throwable -> 0x00aa, all -> 0x00c0 }
        r4 = 0;	 Catch:{ Throwable -> 0x00aa, all -> 0x00c0 }
        r5 = 0;	 Catch:{ Throwable -> 0x00aa, all -> 0x00c0 }
        r6 = 0;	 Catch:{ Throwable -> 0x00aa, all -> 0x00c0 }
        r7 = "_id ASC";	 Catch:{ Throwable -> 0x00aa, all -> 0x00c0 }
        r8 = r0.query(r1, r2, r3, r4, r5, r6, r7);	 Catch:{ Throwable -> 0x00aa, all -> 0x00c0 }
        showNotifications(r12, r8);	 Catch:{ Throwable -> 0x00aa, all -> 0x00c0 }
        if (r8 == 0) goto L_0x0004;
    L_0x0088:
        r1 = r8.isClosed();
        if (r1 != 0) goto L_0x0004;
    L_0x008e:
        r8.close();
        goto L_0x0004;
    L_0x0093:
        r10 = move-exception;
        r1 = com.onesignal.OneSignal.LOG_LEVEL.ERROR;	 Catch:{ Throwable -> 0x0093, all -> 0x00a3 }
        r2 = "Error deleting old notification records! ";	 Catch:{ Throwable -> 0x0093, all -> 0x00a3 }
        com.onesignal.OneSignal.Log(r1, r2, r10);	 Catch:{ Throwable -> 0x0093, all -> 0x00a3 }
        if (r11 == 0) goto L_0x001f;
    L_0x009e:
        r11.endTransaction();
        goto L_0x001f;
    L_0x00a3:
        r1 = move-exception;
        if (r11 == 0) goto L_0x00a9;
    L_0x00a6:
        r11.endTransaction();
    L_0x00a9:
        throw r1;
    L_0x00aa:
        r10 = move-exception;
        r1 = com.onesignal.OneSignal.LOG_LEVEL.ERROR;	 Catch:{ Throwable -> 0x00aa, all -> 0x00c0 }
        r2 = "Error restoring notification records! ";	 Catch:{ Throwable -> 0x00aa, all -> 0x00c0 }
        com.onesignal.OneSignal.Log(r1, r2, r10);	 Catch:{ Throwable -> 0x00aa, all -> 0x00c0 }
        if (r8 == 0) goto L_0x0004;
    L_0x00b5:
        r1 = r8.isClosed();
        if (r1 != 0) goto L_0x0004;
    L_0x00bb:
        r8.close();
        goto L_0x0004;
    L_0x00c0:
        r1 = move-exception;
        if (r8 == 0) goto L_0x00cc;
    L_0x00c3:
        r2 = r8.isClosed();
        if (r2 != 0) goto L_0x00cc;
    L_0x00c9:
        r8.close();
    L_0x00cc:
        throw r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onesignal.NotificationRestorer.restore(android.content.Context):void");
    }

    NotificationRestorer() {
    }

    static void asyncRestore(final Context context) {
        new Thread(new Runnable() {
            public void run() {
                NotificationRestorer.restore(context);
            }
        }, "OS_RESTORE_NOTIFS").start();
    }

    static void showNotifications(Context context, Cursor cursor) {
        if (cursor.moveToFirst()) {
            boolean useExtender = NotificationExtenderService.getIntent(context) != null;
            do {
                Intent serviceIntent;
                int existingId = cursor.getInt(cursor.getColumnIndex(NotificationTable.COLUMN_NAME_ANDROID_NOTIFICATION_ID));
                String fullData = cursor.getString(cursor.getColumnIndex(NotificationTable.COLUMN_NAME_FULL_DATA));
                Long datetime = Long.valueOf(cursor.getLong(cursor.getColumnIndex(NotificationTable.COLUMN_NAME_CREATED_TIME)));
                if (useExtender) {
                    serviceIntent = NotificationExtenderService.getIntent(context);
                } else {
                    serviceIntent = new Intent().setComponent(new ComponentName(context.getPackageName(), GcmIntentService.class.getName()));
                }
                serviceIntent.putExtra("json_payload", fullData);
                serviceIntent.putExtra("android_notif_id", existingId);
                serviceIntent.putExtra("restoring", true);
                serviceIntent.putExtra("timestamp", datetime);
                startService(context, serviceIntent);
            } while (cursor.moveToNext());
        }
    }

    private static void startService(Context context, Intent intent) {
        context.startService(intent);
    }
}
