package com.core42matters.android.registrar;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;

class TaskQueue extends Handler {
    private static final int MSG_DESTROY = -1;
    static final int MSG_REGISTRATION = 201;
    static final int MSG_SETTINGS_UPDATE = 202;
    static TaskQueue instance;
    static HandlerThread thread;

    private TaskQueue(Looper looper) {
        super(looper);
    }

    static synchronized TaskQueue get() {
        TaskQueue taskQueue;
        synchronized (TaskQueue.class) {
            if (instance == null) {
                thread = new HandlerThread("profiler task queue");
                thread.start();
                instance = new TaskQueue(thread.getLooper());
            }
            taskQueue = instance;
        }
        return taskQueue;
    }

    static synchronized void destroy() {
        synchronized (TaskQueue.class) {
            if (thread != null) {
                thread.quit();
            }
            thread = null;
            instance = null;
        }
    }

    public void handleMessage(Message msg) {
        if (msg.what == -1) {
            destroy();
            return;
        }
        try {
            Runnable task = msg.obj;
            if (task != null) {
                task.run();
            }
        } catch (Exception e) {
        }
    }

    static synchronized void schedule(int what, Runnable task) {
        synchronized (TaskQueue.class) {
            TaskQueue taskQueue = get();
            taskQueue.removeMessages(-1);
            taskQueue.obtainMessage(what, task).sendToTarget();
            taskQueue.sendEmptyMessage(-1);
        }
    }

    static synchronized void scheduleExclusive(int what, Runnable task) {
        synchronized (TaskQueue.class) {
            TaskQueue taskQueue = get();
            taskQueue.removeMessages(-1);
            taskQueue.removeMessages(what);
            taskQueue.obtainMessage(what, task).sendToTarget();
            taskQueue.sendEmptyMessage(-1);
        }
    }

    static void scheduleSettingsUpdate(Context context, AppId appId) {
        scheduleExclusive(202, new SettingsUpdateTask(context, new Store(context), appId));
    }

    static void scheduleRegistration(Context context, AppId appId) {
        scheduleExclusive(201, new RegistrationTask(context, new Store(context), appId));
    }
}
