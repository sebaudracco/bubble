package com.mobfox.sdk.services;

import android.app.Service;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.IBinder;
import android.os.Process;
import android.util.Log;
import com.mobfox.sdk.runnables.WorkerThread;
import com.mobfox.sdk.utils.Utils;
import java.util.Calendar;
import java.util.Random;

public class MobFoxService extends Service {
    public static final int INTERVAL_TIME_TO_SEND = 3600000;
    public static final String START_FILE = "mobfox-uam-start";
    private WorkerThread mWorkerThread;

    public void onCreate() {
        super.onCreate();
        Log.d("MobFoxService", "start service");
        if (VERSION.SDK_INT < 24 && isTimeToStart()) {
            this.mWorkerThread = new WorkerThread(this, 3600000);
            this.mWorkerThread.startThread();
        }
    }

    private boolean isTimeToStart() {
        String next = Utils.read(this, START_FILE);
        if (next == null || next.isEmpty()) {
            setStartTime();
        } else if (isRipe(next)) {
            return true;
        }
        return false;
    }

    private boolean isRipe(String next) {
        Calendar startTime = Calendar.getInstance();
        startTime.setTimeInMillis(Long.parseLong(next.trim()));
        return Calendar.getInstance().after(startTime);
    }

    private void setStartTime() {
        Calendar updateTime = Calendar.getInstance();
        updateTime.add(6, new Random().nextInt(6) + 1);
        Utils.write(this, START_FILE, String.valueOf(updateTime.getTimeInMillis()));
    }

    public void onDestroy() {
        try {
            if (this.mWorkerThread != null) {
                this.mWorkerThread.interruptThread();
            }
            super.onDestroy();
            Process.killProcess(Process.myPid());
        } catch (Exception e) {
            Log.d("MobFoxService", "error in onDestroy" + e.getLocalizedMessage());
        }
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        return 1;
    }

    public IBinder onBind(Intent intent) {
        return null;
    }
}
