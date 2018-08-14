package com.firebase.jobdispatcher;

import android.app.Service;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Looper;
import android.os.Messenger;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.v4.util.SimpleArrayMap;
import android.util.Log;
import android.util.Pair;

public class GooglePlayReceiver extends Service implements JobFinishedCallback {
    @VisibleForTesting
    static final String ACTION_EXECUTE = "com.google.android.gms.gcm.ACTION_TASK_READY";
    @VisibleForTesting
    static final String ACTION_INITIALIZE = "com.google.android.gms.gcm.SERVICE_ACTION_INITIALIZE";
    private static final String ERROR_NO_DATA = "No data provided, terminating";
    private static final String ERROR_NULL_INTENT = "Null Intent passed, terminating";
    private static final String ERROR_UNKNOWN_ACTION = "Unknown action received, terminating";
    static final String TAG = "FJD.GooglePlayReceiver";
    private static final JobCoder prefixedCoder = new JobCoder("com.firebase.jobdispatcher.", true);
    private final GooglePlayCallbackExtractor callbackExtractor = new GooglePlayCallbackExtractor();
    private SimpleArrayMap<String, SimpleArrayMap<String, JobCallback>> callbacks = new SimpleArrayMap(1);
    private ExecutionDelegator executionDelegator;
    private int latestStartId;
    private final Object lock = new Object();
    @VisibleForTesting
    Messenger serviceMessenger;

    private static void sendResultSafely(JobCallback callback, int result) {
        try {
            callback.jobFinished(result);
        } catch (Throwable e) {
            Log.e(TAG, "Encountered error running callback", e.getCause());
        }
    }

    public final int onStartCommand(Intent intent, int flags, int startId) {
        try {
            super.onStartCommand(intent, flags, startId);
            if (intent == null) {
                Log.w(TAG, ERROR_NULL_INTENT);
                synchronized (this) {
                    this.latestStartId = startId;
                    if (this.callbacks.isEmpty()) {
                        stopSelf(this.latestStartId);
                    }
                }
            } else {
                String action = intent.getAction();
                if ("com.google.android.gms.gcm.ACTION_TASK_READY".equals(action)) {
                    getExecutionDelegator().executeJob(prepareJob(intent));
                    synchronized (this) {
                        this.latestStartId = startId;
                        if (this.callbacks.isEmpty()) {
                            stopSelf(this.latestStartId);
                        }
                    }
                } else if ("com.google.android.gms.gcm.SERVICE_ACTION_INITIALIZE".equals(action)) {
                    synchronized (this) {
                        this.latestStartId = startId;
                        if (this.callbacks.isEmpty()) {
                            stopSelf(this.latestStartId);
                        }
                    }
                } else {
                    Log.e(TAG, ERROR_UNKNOWN_ACTION);
                    synchronized (this) {
                        this.latestStartId = startId;
                        if (this.callbacks.isEmpty()) {
                            stopSelf(this.latestStartId);
                        }
                    }
                }
            }
            return 2;
        } catch (Throwable th) {
            synchronized (this) {
                this.latestStartId = startId;
                if (this.callbacks.isEmpty()) {
                    stopSelf(this.latestStartId);
                }
            }
        }
    }

    @Nullable
    public IBinder onBind(Intent intent) {
        if (intent == null || VERSION.SDK_INT < 21 || !"com.google.android.gms.gcm.ACTION_TASK_READY".equals(intent.getAction())) {
            return null;
        }
        return getServiceMessenger().getBinder();
    }

    private Messenger getServiceMessenger() {
        Messenger messenger;
        synchronized (this.lock) {
            if (this.serviceMessenger == null) {
                this.serviceMessenger = new Messenger(new GooglePlayMessageHandler(Looper.getMainLooper(), this));
            }
            messenger = this.serviceMessenger;
        }
        return messenger;
    }

    ExecutionDelegator getExecutionDelegator() {
        ExecutionDelegator executionDelegator;
        synchronized (this.lock) {
            if (this.executionDelegator == null) {
                this.executionDelegator = new ExecutionDelegator(this, this);
            }
            executionDelegator = this.executionDelegator;
        }
        return executionDelegator;
    }

    @Nullable
    @VisibleForTesting
    JobInvocation prepareJob(Intent intent) {
        Bundle intentExtras = intent.getExtras();
        if (intentExtras == null) {
            Log.e(TAG, ERROR_NO_DATA);
            return null;
        }
        Pair<JobCallback, Bundle> extraction = this.callbackExtractor.extractCallback(intentExtras);
        if (extraction != null) {
            return prepareJob((JobCallback) extraction.first, (Bundle) extraction.second);
        }
        Log.i(TAG, "no callback found");
        return null;
    }

    @Nullable
    JobInvocation prepareJob(JobCallback callback, Bundle bundle) {
        JobInvocation job = prefixedCoder.decodeIntentBundle(bundle);
        if (job == null) {
            Log.e(TAG, "unable to decode job");
            sendResultSafely(callback, 2);
            return null;
        }
        synchronized (this.lock) {
            SimpleArrayMap<String, JobCallback> map = (SimpleArrayMap) this.callbacks.get(job.getService());
            if (map == null) {
                map = new SimpleArrayMap(1);
                this.callbacks.put(job.getService(), map);
            }
            map.put(job.getTag(), callback);
        }
        return job;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onJobFinished(@android.support.annotation.NonNull com.firebase.jobdispatcher.JobInvocation r7, int r8) {
        /*
        r6 = this;
        r3 = r6.lock;
        monitor-enter(r3);
        r2 = r6.callbacks;	 Catch:{ all -> 0x0083 }
        r4 = r7.getService();	 Catch:{ all -> 0x0083 }
        r1 = r2.get(r4);	 Catch:{ all -> 0x0083 }
        r1 = (android.support.v4.util.SimpleArrayMap) r1;	 Catch:{ all -> 0x0083 }
        if (r1 != 0) goto L_0x0020;
    L_0x0011:
        r2 = r6.callbacks;	 Catch:{ all -> 0x0080 }
        r2 = r2.isEmpty();	 Catch:{ all -> 0x0080 }
        if (r2 == 0) goto L_0x001e;
    L_0x0019:
        r2 = r6.latestStartId;	 Catch:{ all -> 0x0080 }
        r6.stopSelf(r2);	 Catch:{ all -> 0x0080 }
    L_0x001e:
        monitor-exit(r3);	 Catch:{ all -> 0x0080 }
    L_0x001f:
        return;
    L_0x0020:
        r2 = r7.getTag();	 Catch:{ all -> 0x0083 }
        r0 = r1.remove(r2);	 Catch:{ all -> 0x0083 }
        r0 = (com.firebase.jobdispatcher.JobCallback) r0;	 Catch:{ all -> 0x0083 }
        if (r0 == 0) goto L_0x0062;
    L_0x002c:
        r2 = "FJD.GooglePlayReceiver";
        r4 = 2;
        r2 = android.util.Log.isLoggable(r2, r4);	 Catch:{ all -> 0x0083 }
        if (r2 == 0) goto L_0x005f;
    L_0x0036:
        r2 = "FJD.GooglePlayReceiver";
        r4 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0083 }
        r4.<init>();	 Catch:{ all -> 0x0083 }
        r5 = "sending jobFinished for ";
        r4 = r4.append(r5);	 Catch:{ all -> 0x0083 }
        r5 = r7.getTag();	 Catch:{ all -> 0x0083 }
        r4 = r4.append(r5);	 Catch:{ all -> 0x0083 }
        r5 = " = ";
        r4 = r4.append(r5);	 Catch:{ all -> 0x0083 }
        r4 = r4.append(r8);	 Catch:{ all -> 0x0083 }
        r4 = r4.toString();	 Catch:{ all -> 0x0083 }
        android.util.Log.v(r2, r4);	 Catch:{ all -> 0x0083 }
    L_0x005f:
        sendResultSafely(r0, r8);	 Catch:{ all -> 0x0083 }
    L_0x0062:
        r2 = r1.isEmpty();	 Catch:{ all -> 0x0083 }
        if (r2 == 0) goto L_0x0071;
    L_0x0068:
        r2 = r6.callbacks;	 Catch:{ all -> 0x0083 }
        r4 = r7.getService();	 Catch:{ all -> 0x0083 }
        r2.remove(r4);	 Catch:{ all -> 0x0083 }
    L_0x0071:
        r2 = r6.callbacks;	 Catch:{ all -> 0x0080 }
        r2 = r2.isEmpty();	 Catch:{ all -> 0x0080 }
        if (r2 == 0) goto L_0x007e;
    L_0x0079:
        r2 = r6.latestStartId;	 Catch:{ all -> 0x0080 }
        r6.stopSelf(r2);	 Catch:{ all -> 0x0080 }
    L_0x007e:
        monitor-exit(r3);	 Catch:{ all -> 0x0080 }
        goto L_0x001f;
    L_0x0080:
        r2 = move-exception;
        monitor-exit(r3);	 Catch:{ all -> 0x0080 }
        throw r2;
    L_0x0083:
        r2 = move-exception;
        r4 = r6.callbacks;	 Catch:{ all -> 0x0080 }
        r4 = r4.isEmpty();	 Catch:{ all -> 0x0080 }
        if (r4 == 0) goto L_0x0091;
    L_0x008c:
        r4 = r6.latestStartId;	 Catch:{ all -> 0x0080 }
        r6.stopSelf(r4);	 Catch:{ all -> 0x0080 }
    L_0x0091:
        throw r2;	 Catch:{ all -> 0x0080 }
        */
        throw new UnsupportedOperationException("Method not decompiled: com.firebase.jobdispatcher.GooglePlayReceiver.onJobFinished(com.firebase.jobdispatcher.JobInvocation, int):void");
    }

    static JobCoder getJobCoder() {
        return prefixedCoder;
    }
}
