package com.onesignal;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import com.onesignal.OneSignal.LOG_LEVEL;

public class SyncService extends Service {
    static final int TASK_APP_STARTUP = 0;
    static final int TASK_SYNC = 1;
    private static boolean startedFromActivity;

    class C39221 implements LocationHandler {
        C39221() {
        }

        public void complete(LocationPoint point) {
            if (point != null) {
                OneSignalStateSynchronizer.updateLocation(point);
            }
        }
    }

    class C39242 implements Runnable {

        class C39231 implements LocationHandler {
            C39231() {
            }

            public void complete(LocationPoint point) {
                if (point != null) {
                    OneSignalStateSynchronizer.updateLocation(point);
                }
                OneSignalStateSynchronizer.syncUserState(true);
                SyncService.checkOnFocusSync();
                SyncService.this.stopSelf();
            }
        }

        C39242() {
        }

        public void run() {
            if (OneSignal.getUserId() == null) {
                SyncService.this.stopSelf();
                return;
            }
            OneSignal.appId = OneSignal.getSavedAppId();
            OneSignalStateSynchronizer.initUserState(OneSignal.appContext);
            LocationGMS.getLocation(OneSignal.appContext, false, new C39231());
        }
    }

    private static void checkOnFocusSync() {
        long unsentTime = OneSignal.GetUnsentActiveTime();
        if (unsentTime >= 60) {
            OneSignal.sendOnFocus(unsentTime, true);
        }
    }

    private void doSync() {
        if (startedFromActivity) {
            doForegroundSync();
        } else {
            doBackgroundSync();
        }
    }

    private void doForegroundSync() {
        LocationGMS.getLocation(this, false, new C39221());
    }

    private void doBackgroundSync() {
        OneSignal.appContext = getApplicationContext();
        new Thread(new C39242(), "OS_SYNCSRV_BG_SYNC").start();
    }

    public void onCreate() {
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        int task;
        if (intent != null) {
            task = intent.getIntExtra("task", 0);
        } else {
            task = 1;
        }
        if (task == 0) {
            startedFromActivity = true;
        } else if (task == 1) {
            doSync();
        }
        if (startedFromActivity) {
            return 1;
        }
        return 2;
    }

    @Nullable
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onTaskRemoved(Intent rootIntent) {
        super.onTaskRemoved(rootIntent);
        onTaskRemoved((Service) this);
    }

    static void onTaskRemoved(Service service) {
        OneSignal.Log(LOG_LEVEL.VERBOSE, "Starting SyncService:onTaskRemoved.");
        ActivityLifecycleHandler.focusHandlerThread.stopScheduledRunnable();
        boolean scheduleServerRestart = OneSignal.onAppLostFocus(true) || OneSignalStateSynchronizer.stopAndPersist();
        OneSignal.Log(LOG_LEVEL.VERBOSE, "Completed SyncService:onTaskRemoved.");
        service.stopSelf();
        if (scheduleServerRestart) {
            scheduleServiceSyncTask(service, System.currentTimeMillis() + 10000);
        } else {
            LocationGMS.scheduleUpdate(service);
        }
    }

    static void scheduleServiceSyncTask(Context context, long atTime) {
        OneSignal.Log(LOG_LEVEL.VERBOSE, "scheduleServiceSyncTask:atTime: " + atTime);
        Intent intent = new Intent(context, SyncService.class);
        intent.putExtra("task", 1);
        ((AlarmManager) context.getSystemService(NotificationCompat.CATEGORY_ALARM)).set(0, atTime, PendingIntent.getService(context, 0, intent, 134217728));
    }
}
